package com.forth.boonterm.model.request;

import com.forth.boonterm.base.BaseRequestModel;
import com.forth.boonterm.constant.BTConstant;
import com.forth.boonterm.model.common.KeyValue;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mvisionmacmini2 on 4/20/2016 AD.
 */
public class BillReqModelImpl extends BaseRequestModel {

  public BillReqModelImpl(String ref, String chan, String serverName, ArrayList<KeyValue> option) {
    request.put("refId", ref);
    request.put("channel", chan);
    request.put("serviceName", serverName);
    request.put("options", option);

    HashMap<String, Object> refIdValidator = new HashMap<String, Object>();
    setValidateLength(refIdValidator, "gt", 10, "lte", 25);

    HashMap<String, Object> chanValidator = new HashMap<String, Object>();
    setValidateLength(chanValidator, "gt", 3, "lte", 20);

    HashMap<String, Object> serviceNameValidator = new HashMap<String, Object>();
    setValidateLength(serviceNameValidator, "gte", 8, "lte", 30);

    HashMap<String, Object> optionValidator = new HashMap<String, Object>();
    setValidateArrayLength(optionValidator, "gte", 2);

    validator.put("refId", refIdValidator);
    validator.put("channel", chanValidator);
    validator.put("serviceName", serviceNameValidator);
    validator.put("options", optionValidator);

  }
}
