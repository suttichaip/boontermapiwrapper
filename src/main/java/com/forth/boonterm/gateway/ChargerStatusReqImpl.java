package com.forth.boonterm.gateway;

import com.forth.boonterm.base.ABTAPIRequest;
import com.forth.boonterm.base.APIThread;
import com.forth.boonterm.base.BaseResponse;
import com.forth.boonterm.base.IRequestModel;
import com.forth.boonterm.constant.BTConstant;
import com.forth.boonterm.endpoint.ChargerStatusListAPIThread;
import com.forth.boonterm.exception.BoonTermServiceException;
import com.forth.boonterm.model.response.ChargerStatus;
import com.forth.boonterm.utils.JsonUtil;
import com.forth.boonterm.utils.SSLCertificateValidation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;


/**
 * Created by mvisionmacmini2 on 4/21/2016 AD.
 */
public class ChargerStatusReqImpl extends ABTAPIRequest {

    private final static Logger logger = LoggerFactory.getLogger(ChargerStatusReqImpl.class);


    public ChargerStatusReqImpl() {
    }


    public ChargerStatusReqImpl(String callback) {
        this.callbackUrl = callback;
    }

    @Override
    public APIThread getAPIThread() {
        return new ChargerStatusListAPIThread(BTConstant.BT_API_URL + BTConstant.BT_CHG + "/status", null);
    }

    @Override
    public BaseResponse callBTApi() {
        new SSLCertificateValidation().disable();
        ChargerStatus res = new ChargerStatus();
        RestTemplate rTemplate = new RestTemplate();
        String resStr = rTemplate.getForObject(BTConstant.BT_API_URL + BTConstant.BT_CHG + "/status", String.class);

        try {
            res = JsonUtil.fromJson(resStr, ChargerStatus.class);
        } catch (Exception ex) {
            logger.error("bt endpoint exception");
        }

        if (!res.getCode().equals("0000")) {
            throw new BoonTermServiceException(res.getDesc());
        }
        return res;
    }

    @Override
    public IRequestModel getRequestParams() {
        return null;
    }
}
