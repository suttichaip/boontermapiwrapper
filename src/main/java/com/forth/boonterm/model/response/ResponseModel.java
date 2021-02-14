package com.forth.boonterm.model.response;

import com.forth.boonterm.base.general.BaseResultModel;
import java.util.HashMap;

/**
 * Created by mvisionmacmini2 on 3/25/2016 AD.
 */
public class ResponseModel extends BaseResultModel {

  private HashMap<String, Object> errorData;

  public static ResponseModel getResultError(String path, String resultCode, String errorDescription, String developerMessage, HashMap<String, Object> errData) {
    ResponseModel resErr = (ResponseModel) BaseResultModel.getResultError();
    resErr.errorData = errData;
    return resErr;
  }
}
