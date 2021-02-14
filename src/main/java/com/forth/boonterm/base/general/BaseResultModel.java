package com.forth.boonterm.base.general;

import com.forth.boonterm.constant.BTConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Date;


/**
 * Created by mvisionmacmini2 on 4/19/2016 AD.
 */
public class BaseResultModel implements Serializable {
  protected static final Logger logger = LoggerFactory.getLogger(BaseResultModel.class);
  private boolean success;
  private String resultCode;
  private String path;
  private String errorDescription;
  private String developerMessage;
  private long timeStamp = new Date().getTime();
  private Object result;

  private String serverError;
  private String method;
  private String requestBody;

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public String getResultCode() {
    return resultCode;
  }

  public void setResultCode(String resultCode) {
    this.resultCode = resultCode;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String getErrorDescription() {
    return errorDescription;
  }

  public void setErrorDescription(String errorDescription) {
    this.errorDescription = errorDescription;
  }

  public String getDeveloperMessage() {
    return developerMessage;
  }

  public void setDeveloperMessage(String developerMessage) {
    this.developerMessage = developerMessage;
  }

  public long getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp(long timeStamp) {
    this.timeStamp = timeStamp;
  }

  public Object getResult() {
    return result;
  }

  public void setResult(Object result) {
    this.result = result;
  }

  public String getServerError() {
    return serverError;
  }

  public void setServerError(String serverError) {
    this.serverError = serverError;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public String getRequestBody() {
    return requestBody;
  }

  public void setRequestBody(String requestBody) {
    this.requestBody = requestBody;
  }

  public static BaseResultModel getResultSuccess(String path, Object result) {
    BaseResultModel entity = new BaseResultModel();
    entity.setSuccess(true);
    entity.setResultCode("200");
    entity.setPath(path);
    entity.setResult(result);
    return entity;
  }

  public static BaseResultModel getResultSuccess(Object result) {
    return getResultSuccess(null,result);
  }
  public static BaseResultModel getResultSuccess() {
    return getResultSuccess(null);
  }

  public static BaseResultModel getResultError(String path, String resultCode, String errorDescription, String developerMessage) {
    BaseResultModel entity = new BaseResultModel();
    entity.setSuccess(false);
    entity.setPath(path);
    entity.setResultCode(resultCode);
    entity.setErrorDescription(errorDescription);
    entity.setDeveloperMessage(developerMessage);
    return entity;
  }

//  public void setWebRequestData(HttpServletRequest request){
//    try {
//      setPath(WebUtil.getRequestURL(request));
//      setMethod(request.getMethod());
//      if(request instanceof CustomHttpServletRequestWrapper && request.getContentType() != null && request.getContentType().toLowerCase().indexOf("multipart/form-data") == -1) {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
//        String line = "";
//        StringBuilder stringBuilder = new StringBuilder();
//        while ( (line=reader.readLine()) != null ) {
//          stringBuilder.append(line).append("\n");
//        }
//        setRequestBody(stringBuilder.toString());
//      }
//    } catch (Exception e) {
//      logger.error("BaseResultModel Error", e);
//    }
//  }

  public static BaseResultModel getResultError(String errorDescription) {
    return getResultError(null, "400", errorDescription, null) ;
  }

  public static BaseResultModel getResultError() {
    return getResultError(BTConstant.ERROR_MESSAGE);
  }
}
