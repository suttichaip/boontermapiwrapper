package com.forth.boonterm.gateway;

import com.forth.boonterm.base.ABTAPIRequest;
import com.forth.boonterm.base.APIThread;
import com.forth.boonterm.base.BaseResponse;
import com.forth.boonterm.base.IRequestModel;
import com.forth.boonterm.constant.BTConstant;
import com.forth.boonterm.endpoint.KBankWithdrawAPIThread;
import com.forth.boonterm.exception.BoonTermServiceException;
import com.forth.boonterm.model.request.KBankWithdrawReqModelImpl;
import com.forth.boonterm.model.request.LogBeWalletToBoonTerm;
import com.forth.boonterm.model.response.KBankWithdraw;
import com.forth.boonterm.utils.JsonUtil;
import com.forth.boonterm.utils.MongodbUtils;
import com.forth.boonterm.utils.SSLCertificateValidation;
import com.mongodb.BasicDBObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/**
 * Created by mvisiondev on 5/9/2017 AD.
 */
public class KBankWithdrawReqImpl extends ABTAPIRequest {
    private final static Logger logger = LoggerFactory.getLogger(KBankWithdrawReqImpl.class);

    private KBankWithdrawReqModelImpl reqParams;

    public KBankWithdrawReqImpl(){}

    public KBankWithdrawReqImpl(KBankWithdrawReqModelImpl reqParams) {
        this.reqParams = reqParams;
    }

    public KBankWithdrawReqImpl(KBankWithdrawReqModelImpl reqParams, String callback) {
        this.reqParams = reqParams;
        this.callbackUrl = callback;
    }

    @Override
    public APIThread getAPIThread() {
        return new KBankWithdrawAPIThread(BTConstant.BT_API_KBANK + BTConstant.BT_BANK_WITHDRAW, reqParams.request, null);
    }

    @Override
    public BaseResponse callBTApi() {
        new SSLCertificateValidation().disable();
        KBankWithdraw res = new KBankWithdraw();
        RestTemplate rTemplate = new RestTemplate();

        //  Create Log BeWallet To Boonterm
        Date timeSend = new Date();
        LogBeWalletToBoonTerm logBeWalletToBoonTerm = new LogBeWalletToBoonTerm();
        logBeWalletToBoonTerm.setTimeSend(timeSend);
        logBeWalletToBoonTerm.setTimeSendTimeStamp(timeSend.getTime());
        logBeWalletToBoonTerm.setRequest(reqParams.request);
        logBeWalletToBoonTerm.setPathUrl(BTConstant.BT_API_KBANK + BTConstant.BT_BANK_WITHDRAW);
        logBeWalletToBoonTerm.setResponseCode("WAIT");
        logBeWalletToBoonTerm.setTransactionType("bankwithdraw");
        logBeWalletToBoonTerm.setServiceName("WALLET-KBANK-WITHDRAW");
        BasicDBObject basicDBObject = MongodbUtils.insertLogMongo(logBeWalletToBoonTerm);
        //

        String resStr = rTemplate.postForObject(BTConstant.BT_API_KBANK + BTConstant.BT_BANK_WITHDRAW, reqParams.request, String.class);

        // update log v1
        Date timeResponse = new Date();
        logBeWalletToBoonTerm.setTimeResponse(timeResponse);
        logBeWalletToBoonTerm.setTimeResponseTimeStamp(timeResponse.getTime());

        BasicDBObject basicDBObjectUpdate = MongodbUtils.updateLogMongo(basicDBObject,logBeWalletToBoonTerm);
        //

        try {
            res = JsonUtil.fromJson(resStr, KBankWithdraw.class);
        } catch (Exception ex) {
            logger.error("bt endpoint exception");
        }

        //update log v2
        logBeWalletToBoonTerm.setResponseCode(res.getCode());
        logBeWalletToBoonTerm.setResponseStr(resStr);
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
