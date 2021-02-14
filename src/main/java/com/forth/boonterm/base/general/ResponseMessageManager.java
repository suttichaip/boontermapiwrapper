package com.forth.boonterm.base.general;

import com.forth.boonterm.base.general.ExceptionWrapperModel;
import com.forth.boonterm.constant.BTConstant;
import com.forth.boonterm.exception.BaseException;
import com.forth.boonterm.exception.ErrorMessage;
import com.forth.boonterm.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mvisionmacmini2 on 4/19/2016 AD.
 */
public class ResponseMessageManager {
  public static final String MESSAGE_ATTRIBUTE_KEY = "MESSAGE_ATTRIBUTE_KEY";

  private List<ExceptionWrapperModel> exceptionWrapperList = new ArrayList<>();
  private Map<String, String> messageMap = new HashMap<>();

  public List<ExceptionWrapperModel> getExceptionWrapperList() {
    return exceptionWrapperList;
  }
  public void setExceptionWrapperList(List<ExceptionWrapperModel> exceptionWrapperList) {
    this.exceptionWrapperList = exceptionWrapperList;
  }
  public Map<String, String> getMessageMap() {
    return messageMap;
  }
  public void setMessageMap(Map<String, String> messageMap) {
    this.messageMap = messageMap;
  }

  private String[] getParameterNames(int n) {
    String ret[] = new String[n];
    for(int p=0; p<n; p++) ret[p] = "$("+p+")";
    return ret;
  }
  public Throwable wrapException(Throwable exception) {
    if(!(exception instanceof BaseException)) for(int i=0, s=exceptionWrapperList.size(); i<s; i++) {
      Throwable e = exceptionWrapperList.get(i).parse(exception);
      if(e != null) return e;
    }
    return exception;
  }

  @Autowired
  private MessageSource messageSource;

  public ErrorMessage getExceptionMessage(Throwable exception) {
    exception = wrapException(exception);
    String errorCode = exception instanceof BaseException?((BaseException)exception).getErrorCode():BaseException.DEFAULT_ERROR_CODE;
    String msg = messageMap.get(errorCode);
    String params[] = exception instanceof BaseException?((BaseException)exception).getParams():null;
    if(msg!=null && params!=null) msg = StringUtil.replace(msg, getParameterNames(params.length), params);
    return new ErrorMessage(errorCode, msg);
  }

  public ErrorMessage getExceptionMessage(Throwable exception, HttpServletRequest request) {
    exception = wrapException(exception);
    String errorCode = exception instanceof BaseException?((BaseException)exception).getErrorCode():BaseException.DEFAULT_ERROR_CODE;
    String params[] = exception instanceof BaseException?((BaseException)exception).getParams():null;
    String msg = messageSource.getMessage("error.code_" + errorCode, params, BTConstant.ERROR_MESSAGE,request.getLocale());
    return new ErrorMessage(errorCode, msg);
  }

  public String getMessage(String code) {
    return getMessage(code, null);
  }
  public String getMessage(String code, String params[]) {
    String msg = messageMap.get(code);
    if(msg!=null && params!=null)
      msg = StringUtil.replace(msg, getParameterNames(params.length), params);
    return msg;
  }
  public RuntimeException toRuntimeException(Throwable exception) {
    return new RuntimeException( getExceptionMessage(exception).getMessage(), exception );
  }

}
