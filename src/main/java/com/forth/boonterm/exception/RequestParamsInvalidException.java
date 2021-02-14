package com.forth.boonterm.exception;

import com.forth.boonterm.constant.KErrorCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mvisionmacmini2 on 3/22/2016 AD.
 */
public class RequestParamsInvalidException extends BaseException {

  private List<HashMap<String, String>> errorData;

  public List<HashMap<String, String>> getErrorData() {
    return errorData;
  }

  public RequestParamsInvalidException(List<HashMap<String, String>> errData) {
    super(KErrorCode.BT_REQUEST_INVALID_PARAMS,"");
    this.errorData = errData;
  }

  public String toString() {
    return super.toString()+"[errorCode="+getErrorCode()+
        ", params="+(getParams()!=null? Arrays.asList(getParams()):null)+"]";
  }
}
