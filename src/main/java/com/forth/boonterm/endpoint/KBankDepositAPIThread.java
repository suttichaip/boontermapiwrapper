package com.forth.boonterm.endpoint;

import com.forth.boonterm.base.APIThread;
import com.forth.boonterm.base.BaseResponse;
import com.forth.boonterm.model.response.KBankDeposit;
import com.forth.boonterm.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * Created by thananan on 5/9/17.
 */
public class KBankDepositAPIThread extends APIThread {

    private final static Logger logger = LoggerFactory.getLogger(KBankDepositAPIThread.class);

    @Override
    protected void callAPI(){

        RestTemplate rTemplate = new RestTemplate();

        try {
            String resStr = rTemplate.postForObject(serviceUrl, requestBody, String.class);

            BaseResponse res = JsonUtil.fromJson(resStr, KBankDeposit.class);
            if (res.getCode().equals("0000")) {
                callCallback();
            }
        }catch (Exception ex) {
            logger.error("KBankDepositAPIThread : {}",ex);
        }
    }

    public KBankDepositAPIThread(String sUrl, HashMap<String, Object> reqBody, String cbUrl) {
        super(sUrl, reqBody, cbUrl);
    }

}
