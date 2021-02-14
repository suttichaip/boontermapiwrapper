package com.forth.boonterm.base.general;

import com.forth.boonterm.exception.BaseException;
import com.forth.boonterm.exception.ErrorMessage;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by mvisionmacmini2 on 4/19/2016 AD.
 */
public abstract  class BaseRestExceptionHandler {
//  protected static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

  @Autowired
  private ResponseMessageManager responseMessageManager;

  @Autowired
  private HttpServletRequest request;

  public abstract String getServerName();

  public ResponseMessageManager getResponseMessageManager() {
    return responseMessageManager;
  }

  public void setResponseMessageManager(ResponseMessageManager responseMessageManager) {
    this.responseMessageManager = responseMessageManager;
  }

  public HttpServletRequest getRequest() {
    return request;
  }

  public void setRequest(HttpServletRequest request) {
    this.request = request;
  }

  @ExceptionHandler(Exception.class)
  @ResponseBody
  public ResponseEntity handleException(Exception e, HttpServletRequest request) {
    BaseResultModel entity = BaseResultModel.getResultError();
    ErrorMessage errorMessage = responseMessageManager.getExceptionMessage(e, request);
    entity.setResultCode(errorMessage.getErrorCode());
    entity.setErrorDescription(errorMessage.getMessage());
    entity.setDeveloperMessage(e.toString());
    entity.setServerError(getServerName());
//        entity.setWebRequestData(request);
    return BaseException.DEFAULT_ERROR_CODE.equals(entity.getResultCode())?
        new ResponseEntity(entity, HttpStatus.INTERNAL_SERVER_ERROR)
        :new ResponseEntity(entity, HttpStatus.BAD_REQUEST);
  }
}
