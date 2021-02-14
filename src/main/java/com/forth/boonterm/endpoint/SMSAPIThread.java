package com.forth.boonterm.endpoint;

import com.forth.boonterm.base.APIThread;
import com.forth.boonterm.base.BaseResponse;
import com.forth.boonterm.utils.JsonUtil;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * Created by mvisionmacmini2 on 3/28/2016 AD.
 */
public class SMSAPIThread extends APIThread {

  @Override
  protected void callAPI(){
//    System.out.println("SMS API Thread");
    RestTemplate rTemplate = new RestTemplate();

    try {
      String resStr = rTemplate.postForObject(serviceUrl, requestBody, String.class);
//      System.out.println(resStr);
      BaseResponse smsRes = JsonUtil.fromJson(resStr, BaseResponse.class);
      if (smsRes.getCode().equals("0000")) {
        callCallback();
      }
    }catch (Exception ex) {
//      System.out.println("bt endpoint exception");
    }
  }

  public SMSAPIThread(String sUrl, HashMap<String, Object> reqBody, String cbUrl) {
    super(sUrl, reqBody, cbUrl);
  }
}
