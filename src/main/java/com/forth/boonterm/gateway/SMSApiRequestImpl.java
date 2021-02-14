package com.forth.boonterm.gateway;

import com.forth.boonterm.constant.BTConstant;
import com.forth.boonterm.base.ABTAPIRequest;
import com.forth.boonterm.base.APIThread;
import com.forth.boonterm.endpoint.SMSAPIThread;
import com.forth.boonterm.base.IRequestModel;
import com.forth.boonterm.exception.BoonTermServiceException;
import com.forth.boonterm.model.request.SMSRequestModelImpl;
import com.forth.boonterm.base.BaseResponse;
import com.forth.boonterm.utils.JsonUtil;
import com.forth.boonterm.utils.SSLCertificateValidation;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.*;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

/**
 * Created by mvisionmacmini2 on 3/22/2016 AD.
 */
public class SMSApiRequestImpl extends ABTAPIRequest {
  private final static Logger logger = LoggerFactory.getLogger(SMSApiRequestImpl.class);
  private SMSRequestModelImpl reqParams;

  public SMSApiRequestImpl(SMSRequestModelImpl smsModel) {
    this.reqParams = smsModel;
  }

  public SMSApiRequestImpl(SMSRequestModelImpl smsModel, String callback) {
    this.reqParams = smsModel;
    this.callbackUrl = callback;
  }

  @Override
  public APIThread getAPIThread() {
      return new SMSAPIThread(BTConstant.BT_API_URL + BTConstant.BT_SMS, reqParams.request, null);
//      return new SMSAPIThread("https://epay.boonterm.com:8055/" + BTConstant.BT_SMS, reqParams.request, null);
  }

  @Override
  public BaseResponse callBTApi() {
    BaseResponse smsRes = new BaseResponse();
//    String resStr = rTemplate.postForObject(BTConstant.BT_API_URL + BTConstant.BT_SMS, reqParams.request, String.class);
    // Boonterm SMS response was contain only common attribute of all other boonterm api response then
    // We don't need to extens a class from BaseResponse, use BaseResponse rightaway.
    try {
        new SSLCertificateValidation().disable();
        RestTemplate rTemplate = new RestTemplate();
        reqParams.convertUTF8Text();
        String resStr = rTemplate.postForObject(BTConstant.BT_API_URL + BTConstant.BT_SMS, reqParams.request, String.class);
//        String resStr = rTemplate.postForObject("https://epay.boonterm.com:8055/" + BTConstant.BT_SMS, reqParams.request, String.class);
        smsRes = JsonUtil.fromJson(resStr, BaseResponse.class);
    } catch (Exception ex) {
        logger.error("bt endpoint exception");
    }
    if (!smsRes.getCode().equals("0000")) {
      throw new BoonTermServiceException(smsRes.getDesc());
    }

    return smsRes;
  }

  @Override
  public IRequestModel getRequestParams() {
    return this.reqParams;
  }

}
