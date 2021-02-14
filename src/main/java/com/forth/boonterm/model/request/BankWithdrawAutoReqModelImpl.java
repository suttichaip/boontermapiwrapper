package com.forth.boonterm.model.request;

import com.forth.boonterm.base.BaseRequestModel;
import com.forth.boonterm.constant.BTConstant;

import java.util.HashMap;

/**
 * Created by mvisiondev on 8/10/2017 AD.
 */
public class BankWithdrawAutoReqModelImpl extends BaseRequestModel {

    public BankWithdrawAutoReqModelImpl() {

    }

    public BankWithdrawAutoReqModelImpl(String ref, String chan, String account, String mobile, String amount, Double fee, String note, String serviceName, String bankCode,String bankName) {
        request.put("REFID",ref);
        request.put("Channel",chan);
        request.put("accountNo",account);
        request.put("Mobile",mobile);
        request.put("Amount",amount);
        request.put("fee",fee);
        request.put("note",note);
        request.put("serviceName",serviceName);
        request.put("bankCode",bankCode);
        request.put("bankName",bankName);

        HashMap<String, Object> refIdValidator = new HashMap<String, Object>();
        setValidateLength(refIdValidator, "gt", 10, "lte", 25);

        HashMap<String, Object> chanValidator = new HashMap<String, Object>();
        setValidateLength(chanValidator, "gt", 3, "lte", 20);

        HashMap<String, Object> accountValidator = new HashMap<String, Object>();
        setValidateLength(accountValidator, "gte", 1, "lte", 15);

        HashMap<String, Object> mobileValidator = new HashMap<String, Object>();
        setValidateLength(mobileValidator, "gte", 10, "lte", 11);
        mobileValidator.put("format", BTConstant.MOBILE_FORMAT);

        HashMap<String, Object> amountValidator = new HashMap<String, Object>();
        setValidateLength(amountValidator, "gte", 1, "lte",20);

        HashMap<String, Object> serviceNameValidator = new HashMap<String, Object>();
        setValidateLength(serviceNameValidator, "gte", 1, "lte",30);

        HashMap<String, Object> bankCodeValidator = new HashMap<String, Object>();
        setValidateLength(bankCodeValidator, "gte", 1, "lte",20);

//        HashMap<String, Object> bankNameValidator = new HashMap<String, Object>();
//        setValidateLength(bankNameValidator, "gte", 1, "lte",20);

        validator.put("REFID",refIdValidator);
        validator.put("Channel",chanValidator);
        validator.put("accountNo",accountValidator);
        validator.put("Mobile",mobileValidator);
        validator.put("Amount",amountValidator);
        validator.put("serviceName",serviceName);
        validator.put("bankCode",bankCodeValidator);
//        validator.put("bankName",bankNameValidator);
        
    }
}
