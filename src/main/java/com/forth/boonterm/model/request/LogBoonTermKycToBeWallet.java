package com.forth.boonterm.model.request;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by thananan on 6/5/17.
 */
public class LogBoonTermKycToBeWallet {

    private Date timeSend;

    private Date timeResponse;

    private Long timeSendTimeStamp;

    private Long timeResponseTimeStamp;

    private String pathUrl;

    private String responseCode;

    private String errorDescription;

    private String transactionNo;

    private String requestStr;

    private String responseStr;


    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getRequestStr() {
        return requestStr;
    }

    public void setRequestStr(String requestStr) {
        this.requestStr = requestStr;
    }

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


    public String getResponseStr() {
        return responseStr;
    }

    public void setResponseStr(String responseStr) {
        this.responseStr = responseStr;
    }

}
