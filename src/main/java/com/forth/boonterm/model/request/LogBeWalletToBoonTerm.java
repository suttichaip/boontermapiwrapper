package com.forth.boonterm.model.request;

import com.forth.boonterm.base.BaseResponse;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by thananan on 6/5/17.
 */
public class LogBeWalletToBoonTerm {

    private Date timeSend;

    private Date timeResponse;

    private Long timeSendTimeStamp;

    private Long timeResponseTimeStamp;

    private HashMap<String , Object> request;

    private String pathUrl;

    private String responseCode;

    private String transactionType;

    private String serviceName;

    private String responseStr;

    private HashMap<String , Object> responseBepay;

    public Date getTimeSend() {
        return timeSend;
    }

    public void setTimeSend(Date timeSend) {
        this.timeSend = timeSend;
    }

    public Date getTimeResponse() {
        return timeResponse;
    }

    public void setTimeResponse(Date timeResponse) {
        this.timeResponse = timeResponse;
    }

    public Long getTimeSendTimeStamp() {
        return timeSendTimeStamp;
    }

    public void setTimeSendTimeStamp(Long timeSendTimeStamp) {
        this.timeSendTimeStamp = timeSendTimeStamp;
    }

    public Long getTimeResponseTimeStamp() {
        return timeResponseTimeStamp;
    }

    public void setTimeResponseTimeStamp(Long timeResponseTimeStamp) {
        this.timeResponseTimeStamp = timeResponseTimeStamp;
    }

    public HashMap<String, Object> getRequest() {
        return request;
    }

    public void setRequest(HashMap<String, Object> request) {
        this.request = request;
    }

    public String getPathUrl() {
        return pathUrl;
    }

    public void setPathUrl(String pathUrl) {
        this.pathUrl = pathUrl;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getResponseStr() {
        return responseStr;
    }

    public void setResponseStr(String responseStr) {
        this.responseStr = responseStr;
    }

    public HashMap<String, Object> getResponseBepay() {
        return responseBepay;
    }

    public void setResponseBepay(HashMap<String, Object> responseBepay) {
        this.responseBepay = responseBepay;
    }
}
