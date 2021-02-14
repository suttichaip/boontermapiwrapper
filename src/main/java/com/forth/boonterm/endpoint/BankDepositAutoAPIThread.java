package com.forth.boonterm.endpoint;

import com.forth.boonterm.base.APIThread;
import com.forth.boonterm.base.BaseResponse;
import com.forth.boonterm.model.response.BankDepositAuto;
import com.forth.boonterm.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * Created by thananan on 5/9/17.
 */
public class BankDepositAutoAPIThread extends APIThread {

    private final static Logger logger = LoggerFactory.getLogger(BankDepositAutoAPIThread.class);

    @Override
    protected void callAPI(){

        RestTemplate rTemplate = new RestTemplate();

        try {
            String resStr = rTemplate.postForObject(serviceUrl, requestBody, String.class);

            BaseResponse res = JsonUtil.fromJson(resStr, BankDepositAuto.class);
            if (res.getCode().equals("0000")) {
                callCallback();
            }
        }catch (Exception ex) {
            logger.error("BankDepositAutoAPIThread : {}",ex);
        }
    }

    public BankDepositAutoAPIThread(String sUrl, HashMap<String, Object> reqBody, String cbUrl) {
        super(sUrl, reqBody, cbUrl);
    }

}
