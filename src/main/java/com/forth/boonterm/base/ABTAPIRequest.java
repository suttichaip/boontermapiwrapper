package com.forth.boonterm.base;

import com.forth.boonterm.base.general.BaseResultModel;
import com.forth.boonterm.exception.BaseException;
import com.forth.boonterm.exception.RequestParamsInvalidException;
import com.forth.boonterm.model.response.ResponseModel;

import java.util.HashMap;
import java.util.List;

/**
 * Created by mvisionmacmini2 on 3/22/2016 AD.
 * * To implement new api call we need
 * 1. create class which extends from ABTAPIRequest to be a external class end point
 * 2. create class which extends from BaseRequestModel to be boonterm api request params
 * 3. create class which extends from APIThread to remote call boonterm api as a thread if support callback
 * 4. create class which extends from BaseResponse if boonterm API response not match with baseResponse or use BaseResponse right away
 */
public abstract class ABTAPIRequest {
  protected String callbackUrl;
  public abstract APIThread getAPIThread();
  public abstract BaseResponse callBTApi();

  public abstract IRequestModel getRequestParams();

  public BaseResultModel sendRequest() throws BaseException {


    // Validate request body
    List<HashMap<String, String>> validateList = getRequestParams().getValidateList();
    if (validateList.size() > 0) {
      // Throw exception
//      System.out.println("validateList "+validateList+"\n\n\n\n\n");
       throw new RequestParamsInvalidException(validateList);
    }

    // if callback not specific then call Boonterm api and wait for result
    if (callbackUrl == null) {
      BaseResponse result = callBTApi();
      if (result.getCode().equals("0000")) {
        return ResponseModel.getResultSuccess(result);
      } else {
        return ResponseModel.getResultError("", result.getCode(), result.getDesc(), "");
      }
    } else {
      // Spawn new java thread to send request to Boonterm API
      Thread t = new Thread(getAPIThread());
      t.start();
      return ResponseModel.getResultSuccess("Waiting for callback call");
    }
  }
}



