package com.forth.boonterm.endpoint;

import com.forth.boonterm.base.APIThread;
import com.forth.boonterm.base.BaseResponse;
import com.forth.boonterm.model.response.BlackList;
import com.forth.boonterm.model.response.ChargerStatus;
import com.forth.boonterm.utils.JsonUtil;

import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * Created by mvisionmacmini2 on 4/21/2016 AD.
 */
public class ChargerStatusListAPIThread extends APIThread {

    @Override
    protected void callAPI() {
//    System.out.println("Black list API Thread");
        RestTemplate rTemplate = new RestTemplate();

        try {
            String resStr = rTemplate.getForObject(serviceUrl, String.class);
//      System.out.println(resStr);
            BaseResponse res = JsonUtil.fromJson(resStr, ChargerStatus.class);
            if (res.getCode().equals("0000")) {
                callCallback();
            }
        } catch (Exception ex) {
//      System.out.println("bt endpoint exception");
        }
    }

    public ChargerStatusListAPIThread(String sUrl, String cbUrl) {
        super(sUrl, cbUrl);
    }
}
