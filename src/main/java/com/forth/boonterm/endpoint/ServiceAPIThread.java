package com.forth.boonterm.endpoint;

import com.forth.boonterm.base.APIThread;
import com.forth.boonterm.base.BaseResponse;
import com.forth.boonterm.model.response.ServiceModel;
import com.forth.boonterm.utils.JsonUtil;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * Created by mvisionmacmini2 on 4/21/2016 AD.
 */
public class ServiceAPIThread extends APIThread {

  @Override
  protected void callAPI(){
//    System.out.println("Service API Thread");
    RestTemplate rTemplate = new RestTemplate();

    try {
      String resStr = rTemplate.postForObject(serviceUrl, requestBody, String.class);
//      System.out.println(resStr);
      BaseResponse smsRes = JsonUtil.fromJson(resStr, ServiceModel.class);
      if (smsRes.getCode().equals("0000")) {
        callCallback();
      }
    }catch (Exception ex) {
//      System.out.println("bt endpoint exception");
    }
  }

  public ServiceAPIThread(String sUrl, HashMap<String, Object> reqBody, String cbUrl) {
    super(sUrl, reqBody, cbUrl);
  }
}
