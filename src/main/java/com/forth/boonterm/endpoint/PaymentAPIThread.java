package com.forth.boonterm.endpoint;

import com.forth.boonterm.base.APIThread;
import com.forth.boonterm.base.BaseResponse;
import com.forth.boonterm.model.response.Payment;
import com.forth.boonterm.utils.JsonUtil;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * Created by mvisionmacmini2 on 4/25/2016 AD.
 */
public class PaymentAPIThread extends APIThread {

  @Override
  protected void callAPI(){
//    System.out.println("Payment API Thread");
    RestTemplate rTemplate = new RestTemplate();

    try {
      String resStr = rTemplate.postForObject(serviceUrl, requestBody, String.class);
//      System.out.println(resStr);
      BaseResponse res = JsonUtil.fromJson(resStr, Payment.class);
      if (res.getCode().equals("0000")) {
        callCallback();
      }
    }catch (Exception ex) {
//      System.out.println("bt endpoint exception");
    }
  }

  public PaymentAPIThread(String sUrl, HashMap<String, Object> reqBody, String cbUrl) {
    super(sUrl, reqBody, cbUrl);
  }
}
