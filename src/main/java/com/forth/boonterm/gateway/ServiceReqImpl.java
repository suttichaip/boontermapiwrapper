package com.forth.boonterm.gateway;

import com.forth.boonterm.base.ABTAPIRequest;
import com.forth.boonterm.base.APIThread;
import com.forth.boonterm.base.BaseResponse;
import com.forth.boonterm.base.IRequestModel;
import com.forth.boonterm.constant.BTConstant;
import com.forth.boonterm.endpoint.ServiceAPIThread;
import com.forth.boonterm.exception.BoonTermServiceException;
import com.forth.boonterm.model.request.ServicesReqModelImpl;
import com.forth.boonterm.model.response.ServiceModel;
import com.forth.boonterm.utils.JsonUtil;
import com.forth.boonterm.utils.MongodbUtils;
import com.forth.boonterm.utils.SSLCertificateValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by mvisionmacmini2 on 4/21/2016 AD.
 */
public class ServiceReqImpl extends ABTAPIRequest {

  private final static Logger logger = LoggerFactory.getLogger(ServiceReqImpl.class);

  private ServicesReqModelImpl reqParams;

  public ServiceReqImpl(ServicesReqModelImpl reqParams) {
    this.reqParams = reqParams;
  }

  public ServiceReqImpl(ServicesReqModelImpl reqParams, String callback) {
    this.reqParams = reqParams;
    this.callbackUrl = callback;
  }

  @Override
  public APIThread getAPIThread() {
    return new ServiceAPIThread(BTConstant.BT_API_URL + BTConstant.BT_SERVICE, reqParams.request, null);
  }

  @Override
  public BaseResponse callBTApi() {
    new SSLCertificateValidation().disable();
    ServiceModel serviceRes = new ServiceModel();
    RestTemplate rTemplate = new RestTemplate();
    MongodbUtils.logBoontermGateway(reqParams.request,null,BTConstant.BT_API_URL+BTConstant.BT_SERVICE);
    String resStr = rTemplate.postForObject(BTConstant.BT_API_URL + BTConstant.BT_SERVICE, reqParams.request, String.class);
    // Boonterm SMS response was contain only common attribute of all other boonterm api response then
    // We don't need to extens a class from BaseResponse, use BaseResponse rightaway.
     MongodbUtils.logBoontermGateway(null,resStr,BTConstant.BT_API_URL+BTConstant.BT_SERVICE);
    try {
      serviceRes = JsonUtil.fromJson(resStr, ServiceModel.class);
    } catch (Exception ex) {
      logger.error("bt endpoint exception");
    }

    if (!serviceRes.getCode().equals("0000")) {
      throw new BoonTermServiceException(serviceRes.getDesc());
    }
    return serviceRes;
  }

  @Override
  public IRequestModel getRequestParams() {
    return this.reqParams;
  }
}
