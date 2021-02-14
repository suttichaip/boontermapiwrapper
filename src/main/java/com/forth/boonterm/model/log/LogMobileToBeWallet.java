package com.forth.boonterm.model.log;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by thananan on 6/5/17.
 */
public class LogMobileToBeWallet {

    private String timeSend;

    private String timeResponse;

    private String mobilePhone;

    private HashMap<String , Object> request;

    private String refId;

    private String pathUrl;

    private String type;

    private String responseCode;

    private String platForm;



    public String getTimeSend() {
        return timeSend;
    }

    public void setTimeSend(String timeSend) {
        this.timeSend = timeSend;
    }

    public String getTimeResponse() {
        return timeResponse;
    }

    public void setTimeResponse(String timeResponse) {
        this.timeResponse = timeResponse;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public HashMap<String, Object> getRequest() {
        return request;
    }

    public void setRequest(HashMap<String, Object> request) {
        this.request = request;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getPathUrl() {
        return pathUrl;
    }

    public void setPathUrl(String pathUrl) {
        this.pathUrl = pathUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getPlatForm() {
        return platForm;
    }

    public void setPlatForm(String platForm) {
        this.platForm = platForm;
    }
}
