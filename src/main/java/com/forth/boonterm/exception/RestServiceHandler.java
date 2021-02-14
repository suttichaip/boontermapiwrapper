package com.forth.boonterm.exception;

import com.forth.boonterm.constant.BTConstant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import com.forth.boonterm.base.general.BaseRestExceptionHandler;

import javax.servlet.http.HttpServletRequest;
/**
 * Created by mvisionmacmini2 on 4/5/2016 AD.
 */
@ControllerAdvice(basePackages = {"com.forth.boonterm.endpoint"} )
public class RestServiceHandler extends BaseRestExceptionHandler {
  @ExceptionHandler(HttpClientErrorException.class)
  @ResponseBody
  public ResponseEntity handleException(HttpClientErrorException e, HttpServletRequest request) {
    return e.getStatusCode().is4xxClientError()?new ResponseEntity(e.getResponseBodyAsString(), HttpStatus.BAD_REQUEST):new ResponseEntity(e.getResponseBodyAsString(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @Override
  public String getServerName() {
    return BTConstant.BT_API_SERVER;
  }
}
