package com.forth.boonterm.endpoint;

import com.forth.boonterm.base.APIThread;
import com.forth.boonterm.base.BaseResponse;
import com.forth.boonterm.model.response.KBankWithdraw;
import com.forth.boonterm.utils.JsonUtil;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * Created by mvisiondev on 5/9/2017 AD.
 */
public class KBankWithdrawAPIThread extends APIThread {
    @Override
    protected void callAPI() {
        RestTemplate rTemplate = new RestTemplate();

        try {
            String resStr = rTemplate.postForObject(serviceUrl, requestBody, String.class);
//      System.out.println(resStr);
            BaseResponse res = JsonUtil.fromJson(resStr, KBankWithdraw.class);
            if (res.getCode().equals("0000")) {
                callCallback();
            }
        }catch (Exception ex) {
//      System.out.println("bt endpoint exception");
        }
    }

    public KBankWithdrawAPIThread(String sUrl, HashMap<String, Object> reqBody, String cbUrl) {
        super(sUrl, reqBody, cbUrl);
    }
}
