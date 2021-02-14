package com.forth.boonterm.gateway;

import com.forth.boonterm.base.ABTAPIRequest;
import com.forth.boonterm.base.APIThread;
import com.forth.boonterm.base.BaseResponse;
import com.forth.boonterm.base.IRequestModel;
import com.forth.boonterm.base.general.BaseResultModel;
import com.forth.boonterm.constant.BTConstant;
import com.forth.boonterm.endpoint.KBankDepositAPIThread;
import com.forth.boonterm.exception.BaseException;
import com.forth.boonterm.exception.BoonTermServiceException;
import com.forth.boonterm.exception.RequestParamsInvalidException;
import com.forth.boonterm.model.request.BankDepositAutoReqModelImpl;
import com.forth.boonterm.model.request.LogBeWalletToBoonTerm;
import com.forth.boonterm.model.response.BankDepositAuto;
import com.forth.boonterm.model.response.ResponseModel;
import com.forth.boonterm.utils.JsonUtil;
import com.forth.boonterm.utils.MongodbUtils;
import com.forth.boonterm.utils.SSLCertificateValidation;
import com.mongodb.BasicDBObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by thananan on 5/9/17.
 */
public class BankDepositAutoReqImpl extends ABTAPIRequest {

    private final static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
    private final static Logger logger = LoggerFactory.getLogger(BankDepositAutoReqImpl.class);


    private BankDepositAutoReqModelImpl reqParams;

    public BankDepositAutoReqImpl(BankDepositAutoReqModelImpl reqParams){
        this.reqParams = reqParams;
    }

    public BankDepositAutoReqImpl(BankDepositAutoReqModelImpl reqParams, String callback) {
        this.reqParams = reqParams;
        this.callbackUrl = callback;
    }

    @Override
    public APIThread getAPIThread() {
        return new KBankDepositAPIThread(BTConstant.BT_API_KBANK + BTConstant.BT_BANK_DEPOSIT, reqParams.request, null);
    }

    @Override
    public BaseResponse callBTApi() {
        new SSLCertificateValidation().disable();
        BankDepositAuto res = new BankDepositAuto();
        RestTemplate rTemplate = new RestTemplate();

        //  Create Log BeWallet To Boonterm
        Date timeSend = new Date();
        LogBeWalletToBoonTerm logBeWalletToBoonTerm = new LogBeWalletToBoonTerm();
        logBeWalletToBoonTerm.setTimeSend(timeSend);
        logBeWalletToBoonTerm.setTimeSendTimeStamp(timeSend.getTime());
        logBeWalletToBoonTerm.setRequest(reqParams.request);
        logBeWalletToBoonTerm.setPathUrl(BTConstant.BT_API_BANK_AUTO + BTConstant.BT_BANK_DEPOSIT_AUTO);
        logBeWalletToBoonTerm.setResponseCode("WAIT");
        logBeWalletToBoonTerm.setTransactionType("banktopupauto");
        logBeWalletToBoonTerm.setServiceName(reqParams.request.get("serviceName").toString());

        BasicDBObject basicDBObject = MongodbUtils.insertLogMongo(logBeWalletToBoonTerm);
        //

        String resStr = rTemplate.postForObject(BTConstant.BT_API_BANK_AUTO + BTConstant.BT_BANK_DEPOSIT_AUTO, reqParams.request, String.class);

        // update log v1
        Date timeResponse = new Date();
        logBeWalletToBoonTerm.setTimeResponse(timeResponse);
        logBeWalletToBoonTerm.setTimeResponseTimeStamp(timeResponse.getTime());
        logBeWalletToBoonTerm.setResponseStr(resStr);
        BasicDBObject basicDBObjectUpdate = MongodbUtils.updateLogMongo(basicDBObject,logBeWalletToBoonTerm);
        //

        try {
            res = JsonUtil.fromJson(resStr, BankDepositAuto.class);
        } catch (Exception ex) {
            logger.error("bt endpoint exception : {}",ex);
        }

        //update log v2
        logBeWalletToBoonTerm.setResponseCode(res.getCode());
        MongodbUtils.updateLogMongo(basicDBObjectUpdate,logBeWalletToBoonTerm);
        //

        if (!res.getCode().equals("0000"))
        {
            throw new BoonTermServiceException(res.getDesc());
        }

        return res;
    }

    @Override
    public IRequestModel getRequestParams() {
        return this.reqParams;
    }

    @Override
    public BaseResultModel sendRequest() throws BaseException {


        // Validate request body
        BankDepositAutoReqModelImpl bankDepositAutoReqModelImpl = (BankDepositAutoReqModelImpl)this.getRequestParams();
        List<HashMap<String,String>> listValidateList = bankDepositAutoReqModelImpl.getValidateList();
        List<HashMap<String, String>> validateList = listValidateList;
        if (validateList.size() > 0) {
            // Throw exception
            throw new RequestParamsInvalidException(validateList);
        }

        // if callback not specific then call Boonterm api and wait for result
        if (callbackUrl == null) {
            BaseResponse result = this.callBTApi();
            if (result.getCode().equals("0000")) {
                return ResponseModel.getResultSuccess(result);
            } else {
                return ResponseModel.getResultError("", result.getCode(), result.getDesc(), "");
            }
        } else {
            // Spawn new java thread to send request to Boonterm API
            Thread t = new Thread(getAPIThread());
            t.start();
            return ResponseModel.getResultSuccess("Waiting for callback call");
        }
    }

}
