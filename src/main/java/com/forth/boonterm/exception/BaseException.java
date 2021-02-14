package com.forth.boonterm.exception;

import java.util.Arrays;

/**
 * Created by mvisionmacmini2 on 4/19/2016 AD.
 */
public class BaseException extends RuntimeException {
  private static final long serialVersionUID = 4610594353475733159L;
  public static final String DEFAULT_ERROR_CODE = "00500";
  protected String params[], errorCode=DEFAULT_ERROR_CODE;
  public BaseException() {
  }
  public BaseException(String errorCode, String message) {
    super(message);
    this.errorCode = errorCode;
  }
  public BaseException(String errorCode, Throwable e) {
    super(e);
    this.errorCode = errorCode;
  }
  public BaseException(String errorCode, String params[]) {
    this.errorCode = errorCode;
    this.params = params;
  }
  public BaseException(String errorCode, String params[], Throwable e) {
    super(e);
    this.errorCode = errorCode;
    this.params = params;
  }
  public String getErrorCode() {
    return errorCode;
  }
  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }
  public String[] getParams() {
    return params;
  }
  public void setParams(String[] params) {
    this.params = params;
  }
  public String toString() {
    return super.toString()+"[errorCode="+getErrorCode()+
        ", params="+(getParams()!=null? Arrays.asList(getParams()):null)+"]";
  }
}
