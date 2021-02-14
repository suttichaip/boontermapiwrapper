package com.forth.boonterm.model.request;

import com.forth.boonterm.base.BaseRequestModel;

import java.util.HashMap;

/**
 * Created by mvisionmacmini2 on 5/11/2016 AD.
 */
public class PriceListReqModelImpl extends BaseRequestModel {
  public PriceListReqModelImpl(String ref, String chan) {
    request.put("refId", ref);
    request.put("channel", chan);

    HashMap<String, Object> refIdValidator = new HashMap<String, Object>();
    setValidateLength(refIdValidator, "gt", 10, "lte", 25);

    HashMap<String, Object> chanValidator = new HashMap<String, Object>();
    setValidateLength(chanValidator, "gt", 3, "lte", 20);

    validator.put("refId", refIdValidator);
    validator.put("channel", chanValidator);
  }
}
