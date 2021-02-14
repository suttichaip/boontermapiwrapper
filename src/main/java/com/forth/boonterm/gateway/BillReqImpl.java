package com.forth.boonterm.gateway;

import com.forth.boonterm.base.ABTAPIRequest;
import com.forth.boonterm.base.APIThread;
import com.forth.boonterm.base.BaseResponse;
import com.forth.boonterm.base.IRequestModel;
import com.forth.boonterm.constant.BTConstant;
import com.forth.boonterm.endpoint.PaymentInfoAPIThread;
import com.forth.boonterm.exception.BoonTermServiceException;
import com.forth.boonterm.model.request.BillReqModelImpl;
import com.forth.boonterm.model.request.LogBeWalletToBoonTerm;
import com.forth.boonterm.model.response.PaymentInfo;
import com.forth.boonterm.utils.JsonUtil;
import com.forth.boonterm.utils.MongodbUtils;
import com.forth.boonterm.utils.SSLCertificateValidation;

import com.mongodb.BasicDBObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;


import java.util.Date;

/**
 * Created by mvisionmacmini2 on 4/20/2016 AD.
 */
public class BillReqImpl extends ABTAPIRequest {

  private final static Logger logger = LoggerFactory.getLogger(BillReqImpl.class);

  private BillReqModelImpl reqParams;

  public BillReqImpl(BillReqModelImpl billReqModel) {
    this.reqParams = billReqModel;
  }

  public BillReqImpl(BillReqModelImpl billReqModel, String callback) {
    this.reqParams = billReqModel;
    this.callbackUrl = callback;
  }

  @Override
  public APIThread getAPIThread() {
    return new PaymentInfoAPIThread(BTConstant.BT_API_URL + BTConstant.BT_PAY_INFO, reqParams.request, null);
  }

  @Override
  public BaseResponse callBTApi() {
    new SSLCertificateValidation().disable();
    PaymentInfo res = new PaymentInfo();
    RestTemplate rTemplate = new RestTemplate();

    //  Create Log BeWallet To Boonterm
    String serviceName = "";
    Object objectServiceName = reqParams.request.get("serviceName");
    if(objectServiceName!= null){
      serviceName = objectServiceName.toString();
    }
    Date timeSend = new Date();
    LogBeWalletToBoonTerm logBeWalletToBoonTerm = new LogBeWalletToBoonTerm();
    logBeWalletToBoonTerm.setTimeSend(timeSend);
    logBeWalletToBoonTerm.setTimeSendTimeStamp(timeSend.getTime());
    logBeWalletToBoonTerm.setRequest(reqParams.request);
    logBeWalletToBoonTerm.setPathUrl(BTConstant.BT_API_URL + BTConstant.BT_PAY_INFO);
    logBeWalletToBoonTerm.setResponseCode("WAIT");
    logBeWalletToBoonTerm.setTransactionType("billinfo");
    logBeWalletToBoonTerm.setServiceName(serviceName);
    BasicDBObject basicDBObject = MongodbUtils.insertLogMongo(logBeWalletToBoonTerm);

    String resStr = rTemplate.postForObject(BTConstant.BT_API_URL + BTConstant.BT_PAY_INFO, reqParams.request, String.class);

    // update log v1
    Date timeResponse = new Date();
    logBeWalletToBoonTerm.setTimeResponse(timeResponse);
    logBeWalletToBoonTerm.setTimeResponseTimeStamp(timeResponse.getTime());
    BasicDBObject basicDBObjectUpdate = MongodbUtils.updateLogMongo(basicDBObject,logBeWalletToBoonTerm);
    //

    try {
       res = JsonUtil.fromJson(resStr, PaymentInfo.class);
    } catch (Exception ex) {
      throw new BoonTermServiceException(BTConstant.BT_RESULT_INVALID);
    }


    //update log v2
    logBeWalletToBoonTerm.setResponseCode(res.getCode());
    logBeWalletToBoonTerm.setResponseStr(resStr);
    MongodbUtils.updateLogMongo(basicDBObjectUpdate,logBeWalletToBoonTerm);
    //

    if (!res.getCode().equals("0000")) {
      throw new BoonTermServiceException(res.getCode(),res.getDesc());
    }
    return res;
  }

  @Override
  public IRequestModel getRequestParams() {
    return this.reqParams;
  }


}
