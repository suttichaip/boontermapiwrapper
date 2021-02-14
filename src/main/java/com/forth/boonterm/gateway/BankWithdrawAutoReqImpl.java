package com.forth.boonterm.gateway;

import com.forth.boonterm.base.ABTAPIRequest;
import com.forth.boonterm.base.APIThread;
import com.forth.boonterm.base.BaseResponse;
import com.forth.boonterm.base.IRequestModel;
import com.forth.boonterm.constant.BTConstant;
import com.forth.boonterm.endpoint.BankWithdrawAutoAPIThread;
import com.forth.boonterm.exception.BoonTermServiceException;
import com.forth.boonterm.model.request.BankWithdrawAutoReqModelImpl;
import com.forth.boonterm.model.request.LogBeWalletToBoonTerm;
import com.forth.boonterm.model.response.BankWithdrawAuto;
import com.forth.boonterm.utils.JsonUtil;
import com.forth.boonterm.utils.MongodbUtils;
import com.forth.boonterm.utils.SSLCertificateValidation;
import com.mongodb.BasicDBObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/**
 * Created by mvisiondev on 8/10/2017 AD.
 */
public class BankWithdrawAutoReqImpl extends ABTAPIRequest {

    private final static Logger logger = LoggerFactory.getLogger(BankWithdrawAutoReqImpl.class);

    private BankWithdrawAutoReqModelImpl reqParams;

    public BankWithdrawAutoReqImpl() {

    }

    public BankWithdrawAutoReqImpl(BankWithdrawAutoReqModelImpl reqParams) {
        this.reqParams = reqParams;
    }

    public BankWithdrawAutoReqImpl(BankWithdrawAutoReqModelImpl reqParams, String callback) {
        this.reqParams = reqParams;
        this.callbackUrl = callback;
    }

    @Override
    public APIThread getAPIThread() {
        return new BankWithdrawAutoAPIThread(BTConstant.BT_API_KBANK + BTConstant.BT_BANK_WITHDRAW_AUTO, reqParams.request, null);
    }

    @Override
    public BaseResponse callBTApi() {
        new SSLCertificateValidation().disable();
        BankWithdrawAuto res = new BankWithdrawAuto();
        RestTemplate rTemplate = new RestTemplate();

        //  Create Log BeWallet To Boonterm
        Date timeSend = new Date();
        LogBeWalletToBoonTerm logBeWalletToBoonTerm = new LogBeWalletToBoonTerm();
        logBeWalletToBoonTerm.setTimeSend(timeSend);
        logBeWalletToBoonTerm.setTimeSendTimeStamp(timeSend.getTime());
        logBeWalletToBoonTerm.setRequest(reqParams.request);
        logBeWalletToBoonTerm.setPathUrl(BTConstant.BT_API_BANK_AUTO + BTConstant.BT_BANK_WITHDRAW_AUTO);
        logBeWalletToBoonTerm.setResponseCode("WAIT");
        logBeWalletToBoonTerm.setTransactionType("bankwithdrawauto");
        logBeWalletToBoonTerm.setServiceName(reqParams.request.get("serviceName").toString());
        BasicDBObject basicDBObject = MongodbUtils.insertLogMongo(logBeWalletToBoonTerm);
        //

        String resStr = rTemplate.postForObject(BTConstant.BT_API_BANK_AUTO + BTConstant.BT_BANK_WITHDRAW_AUTO, reqParams.request, String.class);


        // update log v1
        Date timeResponse = new Date();
        logBeWalletToBoonTerm.setTimeResponse(timeResponse);
        logBeWalletToBoonTerm.setTimeResponseTimeStamp(timeResponse.getTime());
        logBeWalletToBoonTerm.setResponseStr(resStr);
        BasicDBObject basicDBObjectUpdate = MongodbUtils.updateLogMongo(basicDBObject,logBeWalletToBoonTerm);
        //

        try {
            res = JsonUtil.fromJson(resStr, BankWithdrawAuto.class);
        } catch (Exception ex) {
            logger.error("bt endpoint exception");
        }

        //update log v2
        logBeWalletToBoonTerm.setResponseCode(res.getCode());
        MongodbUtils.updateLogMongo(basicDBObjectUpdate,logBeWalletToBoonTerm);
        //

        if (!res.getCode().equals("0000")) {
            throw new BoonTermServiceException(res.getDesc());
        }
        return res;
    }

    @Override
    public IRequestModel getRequestParams() {
        return this.reqParams;
    }
}
