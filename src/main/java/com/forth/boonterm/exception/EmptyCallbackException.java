package com.forth.boonterm.exception;

import com.forth.boonterm.constant.KErrorCode;

/**
 * Created by mvisionmacmini2 on 3/22/2016 AD.
 */
public class EmptyCallbackException extends BaseException{
  public EmptyCallbackException() {
    super(KErrorCode.BT_EMPTY_CALLBACK, "");
  }

}
