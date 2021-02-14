package com.forth.boonterm.exception;


import com.forth.boonterm.constant.KErrorCode;

/**
 * Created by mvisionmacmini2 on 3/22/2016 AD.
 */
public class ServiceNotFoundException extends BaseException {
  public ServiceNotFoundException() {
    super(KErrorCode.BT_SERVICE_NOT_FOUND, "");
  }
}
