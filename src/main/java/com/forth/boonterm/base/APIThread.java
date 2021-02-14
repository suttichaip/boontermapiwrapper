package com.forth.boonterm.base;

import java.util.HashMap;

/**
 * Created by mvisionmacmini2 on 3/28/2016 AD.
 */
public abstract class APIThread implements Runnable {
    protected String serviceUrl;
    protected HashMap<String, Object> requestBody;
    protected String callbackUrl;

    public APIThread(String sUrl, HashMap<String, Object> reqBody, String cbUrl) {
        this.serviceUrl = sUrl;
        this.requestBody = reqBody;
        this.callbackUrl = cbUrl;
    }

    public APIThread(String sUrl, String cbUrl) {
        this.serviceUrl = sUrl;
        this.callbackUrl = cbUrl;
    }

    protected abstract void callAPI();

    protected void callCallback() {
    }

    @Override
    public void run() {
        // Call Boon Term Rest API and Wait for response
        // Call callback url should include path params such as /bill_payment_callback_url/:reference_id
//    System.out.println("run on thread");
        callAPI();
    }
}
