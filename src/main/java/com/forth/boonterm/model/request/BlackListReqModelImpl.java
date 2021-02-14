package com.forth.boonterm.model.request;

import com.forth.boonterm.base.BaseRequestModel;
import com.forth.boonterm.constant.BTConstant;

import java.util.HashMap;

/**
 * Created by mvisionmacmini2 on 4/21/2016 AD.
 */
public class BlackListReqModelImpl extends BaseRequestModel {

  public BlackListReqModelImpl(){}

  public BlackListReqModelImpl(String ref, String chan, String account, String mobile, String idNum) {

    request.put("refId", ref);
    request.put("channel", chan);
    request.put("account", account);
    request.put("mobile", mobile);
    request.put("idNum", idNum);

    HashMap<String, Object> refIdValidator = new HashMap<String, Object>();
    setValidateLength(refIdValidator, "gt", 10, "lte", 25);

    HashMap<String, Object> chanValidator = new HashMap<String, Object>();
    setValidateLength(chanValidator, "gt", 3, "lte", 20);

    HashMap<String, Object> accountValidator = new HashMap<String, Object>();
    setValidateLength(accountValidator, "gte", 1, "lte", 20);

    HashMap<String, Object> mobileValidator = new HashMap<String, Object>();
    setValidateLength(mobileValidator, "gte", 10, "lte", 11);
    mobileValidator.put("format", BTConstant.MOBILE_FORMAT);

    HashMap<String, Object> idValidator = new HashMap<String, Object>();
    setValidateLength(idValidator, "eq", 13);
    idValidator.put("thai_id_format", true);

    validator.put("refId", refIdValidator);
    validator.put("channel", chanValidator);
    validator.put("account", accountValidator);
    validator.put("mobile", mobileValidator);
    validator.put("idNum", idValidator);
  }
}
