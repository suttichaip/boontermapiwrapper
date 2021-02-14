package com.forth.boonterm.gateway;

import com.forth.boonterm.base.ABTAPIRequest;
import com.forth.boonterm.base.APIThread;
import com.forth.boonterm.base.BaseResponse;
import com.forth.boonterm.base.IRequestModel;
import com.forth.boonterm.constant.BTConstant;
import com.forth.boonterm.endpoint.PaymentAPIThread;
import com.forth.boonterm.exception.BoonTermServiceException;
import com.forth.boonterm.model.request.LogBeWalletToBoonTerm;
import com.forth.boonterm.model.request.PaymentReqModelImpl;
import com.forth.boonterm.model.response.Payment;
import com.forth.boonterm.utils.JsonUtil;
import com.forth.boonterm.utils.MongodbUtils;
import com.forth.boonterm.utils.SSLCertificateValidation;
import com.mongodb.BasicDBObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Date;


/**
 * Created by mvisionmacmini2 on 4/25/2016 AD.
 */
public class PaymentReqImpl extends ABTAPIRequest {

    private final static Logger logger = LoggerFactory.getLogger(PaymentReqImpl.class);

    private PaymentReqModelImpl reqParams;

    public PaymentReqImpl(PaymentReqModelImpl reqParams) {
        this.reqParams = reqParams;
    }

    public PaymentReqImpl(PaymentReqModelImpl reqParams, String callback) {
        this.reqParams = reqParams;
        this.callbackUrl = callback;
    }

    @Override
    public APIThread getAPIThread() {
        return new PaymentAPIThread(BTConstant.BT_API_URL + BTConstant.BT_PAYMENT, reqParams.request, null);
    }

    @Override
    public BaseResponse callBTApi() {
        new SSLCertificateValidation().disable();
        Payment res = new Payment();
        RestTemplate rTemplate = new RestTemplate();
        String resStr = "";

        //  Create Log BeWallet To Boonterm
        String serviceName = "";
        Object objectServiceName = reqParams.request.get("serviceName");
        if (objectServiceName != null) {
            serviceName = objectServiceName.toString();
        }
        Date timeSend = new Date();
        LogBeWalletToBoonTerm logBeWalletToBoonTerm = new LogBeWalletToBoonTerm();
        logBeWalletToBoonTerm.setTimeSend(timeSend);
        logBeWalletToBoonTerm.setTimeSendTimeStamp(timeSend.getTime());
        logBeWalletToBoonTerm.setRequest(reqParams.request);
        logBeWalletToBoonTerm.setResponseCode("WAIT");
        logBeWalletToBoonTerm.setServiceName(serviceName);
        BasicDBObject basicDBObject = null;
        //

        if (reqParams.request.get("serviceName").equals("PRODUCT-CHARGER")) {

            logBeWalletToBoonTerm.setTransactionType("charger");
            if (BTConstant.BUILD_MODE.equals("UAT")) {
                logBeWalletToBoonTerm.setPathUrl("https://epay.boonterm.com:8055/" + BTConstant.BT_CHG);
                basicDBObject = MongodbUtils.insertLogMongo(logBeWalletToBoonTerm);
                resStr = rTemplate.postForObject("https://epay.boonterm.com:8055/" + BTConstant.BT_CHG, reqParams.request, String.class);
            } else {
                logBeWalletToBoonTerm.setPathUrl(BTConstant.BT_API_URL + BTConstant.BT_CHG);
                basicDBObject = MongodbUtils.insertLogMongo(logBeWalletToBoonTerm);
                resStr = rTemplate.postForObject(BTConstant.BT_API_URL + BTConstant.BT_CHG, reqParams.request, String.class);
            }
        } else {
            logBeWalletToBoonTerm.setTransactionType("billpay");
            logBeWalletToBoonTerm.setPathUrl(BTConstant.BT_API_URL + BTConstant.BT_PAYMENT);
            basicDBObject = MongodbUtils.insertLogMongo(logBeWalletToBoonTerm);
            resStr = rTemplate.postForObject(BTConstant.BT_API_URL + BTConstant.BT_PAYMENT, reqParams.request, String.class);

        }

        // update log v1
        Date timeResponse = new Date();
        logBeWalletToBoonTerm.setTimeResponse(timeResponse);
        logBeWalletToBoonTerm.setTimeResponseTimeStamp(timeResponse.getTime());
        logBeWalletToBoonTerm.setResponseStr(resStr);
        BasicDBObject basicDBObjectUpdate = MongodbUtils.updateLogMongo(basicDBObject, logBeWalletToBoonTerm);
        //

        try {
            res = JsonUtil.fromJson(resStr, Payment.class);
        } catch (Exception ex) {
            throw new BoonTermServiceException(BTConstant.BT_RESULT_INVALID);
        }

        //update log v2
        logBeWalletToBoonTerm.setResponseCode(res.getCode());
        MongodbUtils.updateLogMongo(basicDBObjectUpdate, logBeWalletToBoonTerm);
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
