package com.forth.boonterm.gateway;

import com.forth.boonterm.base.ABTAPIRequest;
import com.forth.boonterm.base.APIThread;
import com.forth.boonterm.base.BaseResponse;
import com.forth.boonterm.base.IRequestModel;
import com.forth.boonterm.constant.BTConstant;
import com.forth.boonterm.endpoint.BlackListAPIThread;
import com.forth.boonterm.exception.BoonTermServiceException;
import com.forth.boonterm.model.request.BlackListReqModelImpl;
import com.forth.boonterm.model.response.BlackList;
import com.forth.boonterm.utils.JsonUtil;
import com.forth.boonterm.utils.MongodbUtils;
import com.forth.boonterm.utils.SSLCertificateValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.client.RestTemplate;
import java.util.Map;


/**
 * Created by mvisionmacmini2 on 4/21/2016 AD.
 */
public class BlackListReqImpl extends ABTAPIRequest {

  private final static Logger logger = LoggerFactory.getLogger(BlackListReqImpl.class);

  private BlackListReqModelImpl reqParams;

  public BlackListReqImpl(){}

  public BlackListReqImpl(BlackListReqModelImpl reqParams) {
    this.reqParams = reqParams;
  }

  public BlackListReqImpl(BlackListReqModelImpl reqParams, String callback) {
    this.reqParams = reqParams;
    this.callbackUrl = callback;
  }

  @Override
  public APIThread getAPIThread() {
    return new BlackListAPIThread(BTConstant.BT_API_URL + BTConstant.BT_BLACKLIST, reqParams.request, null);
  }

  @Override
  public BaseResponse callBTApi() {
    new SSLCertificateValidation().disable();
    BlackList res = new BlackList();
    RestTemplate rTemplate = new RestTemplate();
    MongodbUtils.logBoontermGateway(reqParams.request,null,BTConstant.BT_API_URL+BTConstant.BT_BLACKLIST);
    String resStr = rTemplate.postForObject(BTConstant.BT_API_URL + BTConstant.BT_BLACKLIST, reqParams.request, String.class);
    
//    String resStr = "{\"REFID\":\"0er9e9re0rewkdsfsdfi9\",\"Code\":\"0000\",\"Status\":\"NORMAL\"}";
    MongodbUtils.logBoontermGateway(null,resStr,BTConstant.BT_API_URL+BTConstant.BT_BLACKLIST);
    logger.debug("BLACKLIST REQUEST "+reqParams.request.get("mobile"));
    try {
      res = JsonUtil.fromJson(resStr, BlackList.class);
    } catch (Exception ex) {
      logger.error("bt endpoint exception");
    }

    if (!res.getCode().equals("0000")) {
      throw new BoonTermServiceException(res.getDesc());
    }
    logger.debug("BLACKLIST RESPONSE "+reqParams.request.get("mobile"));
    return res;
  }

  @Override
  public IRequestModel getRequestParams() {
    return this.reqParams;
  }

}
