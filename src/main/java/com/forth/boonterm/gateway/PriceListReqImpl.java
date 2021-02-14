package com.forth.boonterm.gateway;

import com.forth.boonterm.base.ABTAPIRequest;
import com.forth.boonterm.base.APIThread;
import com.forth.boonterm.base.BaseResponse;
import com.forth.boonterm.base.IRequestModel;
import com.forth.boonterm.constant.BTConstant;
import com.forth.boonterm.exception.BoonTermServiceException;
import com.forth.boonterm.model.request.PriceListReqModelImpl;
import com.forth.boonterm.model.response.PriceList;
import com.forth.boonterm.utils.JsonUtil;
import com.forth.boonterm.utils.MongodbUtils;
import com.forth.boonterm.utils.SSLCertificateValidation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Map;

/**
 * Created by mvisionmacmini2 on 5/11/2016 AD.
 */
public class PriceListReqImpl extends ABTAPIRequest {

    private final static Logger logger = LoggerFactory.getLogger(PriceListReqImpl.class);

    private PriceListReqModelImpl reqParams;

    public PriceListReqImpl(PriceListReqModelImpl reqParams) {
        this.reqParams = reqParams;
    }

    public PriceListReqImpl(PriceListReqModelImpl reqParams, String callback) {
        this.reqParams = reqParams;
        this.callbackUrl = callback;
    }

    @Override
    public APIThread getAPIThread() {
        return null;
    }

    @Override
    public BaseResponse callBTApi() {
        new SSLCertificateValidation().disable();
        PriceList res = new PriceList();
        RestTemplate rTemplate = new RestTemplate();
        rTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        MongodbUtils.logBoontermGateway(reqParams.request, null, BTConstant.BT_API_URL + BTConstant.BT_PRICE_LIST);
        String resStr = rTemplate.postForObject(BTConstant.BT_API_URL + BTConstant.BT_PRICE_LIST, reqParams.request, String.class);
        // Boonterm SMS response was contain only common attribute of all other boonterm api response then
        // We don't need to extens a class from BaseResponse, use BaseResponse rightaway.
        MongodbUtils.logBoontermGateway(null, resStr, BTConstant.BT_API_URL + BTConstant.BT_PRICE_LIST);
        try {
            res = JsonUtil.fromJson(resStr, PriceList.class);
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
        return this.reqParams;
    }
}
