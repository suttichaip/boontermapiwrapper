package com.forth.boonterm.model.request;

import com.forth.boonterm.base.BaseRequestModel;
import com.forth.boonterm.constant.BTConstant;
import com.forth.boonterm.model.common.KeyValue;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mvisionmacmini2 on 4/22/2016 AD.
 */
public class PaymentReqModelImpl extends BaseRequestModel {

  public PaymentReqModelImpl(
      String ref,
      String chan,
      String serverName,
      String mobile,
      String amount,
      ArrayList<KeyValue> options) {

    request.put("refId", ref);
    request.put("channel", chan);
    request.put("serviceName", serverName);
    request.put("mobile", mobile);
    request.put("amount", amount);
    request.put("options", options);

    HashMap<String, Object> refIdValidator = new HashMap<String, Object>();
    setValidateLength(refIdValidator, "gt", 10, "lte", 26);

    HashMap<String, Object> chanValidator = new HashMap<String, Object>();
    setValidateLength(chanValidator, "gt", 3, "lte", 20);

    HashMap<String, Object> serviceNameValidator = new HashMap<String, Object>();
    setValidateLength(serviceNameValidator, "gte", 8, "lte", 30);

    HashMap<String, Object> mobileValidator = new HashMap<String, Object>();
    setValidateLength(mobileValidator, "gte", 10, "lte", 11);
    mobileValidator.put("format", BTConstant.MOBILE_FORMAT);

    HashMap<String, Object> amountValidator = new HashMap<>();
    amountValidator.put("format", BTConstant.NUMBER);

    HashMap<String, Object> optionValidator = new HashMap<String, Object>();
    setValidateArrayLength(optionValidator, "gte", 1);

    validator.put("refId", refIdValidator);
    validator.put("channel", chanValidator);
    validator.put("serviceName", serviceNameValidator);
    validator.put("mobile", mobileValidator);
    validator.put("amount", amountValidator);
    validator.put("options", optionValidator);
  }
}
