package com.forth.boonterm.exception;


import com.forth.boonterm.constant.KErrorCode;

/**
 * Created by mvisionmacmini2 on 4/11/2016 AD.
 */
public class BoonTermServiceException extends BaseException {
  public BoonTermServiceException(String desc) {
    super(KErrorCode.BT_SERVICE_RETURN, desc);
  }

  public BoonTermServiceException(String code,String desc) {
    super(code, desc);
  }
}

