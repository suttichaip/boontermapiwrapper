package com.forth.boonterm.model.request;

import com.forth.boonterm.base.BaseRequestModel;
import com.forth.boonterm.constant.BTConstant;

import java.util.HashMap;

/**
 * Created by thananan on 5/9/17.
 */
public class BankDepositAutoReqModelImpl extends BaseRequestModel {

    public BankDepositAutoReqModelImpl(){

    }

    public BankDepositAutoReqModelImpl(String ref, String chan, String accountNo,String accountName,String bankCode,String bankName,String note, String mobile, Double amount,Double fee, String serviceName){

        request.put("REFID", ref);
        request.put("Channel", chan);
        request.put("bankCode", bankCode);
        request.put("bankName ", bankName);
        request.put("accountNo", accountNo);
        request.put("accountName ", accountName);
        request.put("Mobile", mobile);
        request.put("Amount", amount);
        request.put("serviceName", serviceName);
        request.put("note", note);

        HashMap<String, Object> refIdValidator = new HashMap<String, Object>();
        setValidateLength(refIdValidator, "gt", 10, "lte", 25);

        HashMap<String, Object> chanValidator = new HashMap<String, Object>();
        setValidateLength(chanValidator, "gt", 3, "lte", 20);

        HashMap<String, Object> bankCodeValidator = new HashMap<String, Object>();
        setValidateLength(bankCodeValidator, "gt", 1, "lte", 20);

//        HashMap<String, Object> bankNameValidator = new HashMap<String, Object>();
//        setValidateLength(bankNameValidator, "gt", 1, "lte", 20);

        HashMap<String, Object> accountNoValidator = new HashMap<String, Object>();
        setValidateLength(accountNoValidator, "gte", 1, "lte", 30);
//
//        HashMap<String, Object> accountNameValidator = new HashMap<String, Object>();
//        setValidateLength(accountNameValidator, "gte", 1, "lte", 30);
//
        HashMap<String, Object> mobileValidator = new HashMap<String, Object>();
        setValidateLength(mobileValidator, "gte", 10, "lte", 11);
        mobileValidator.put("format", BTConstant.MOBILE_FORMAT);

        HashMap<String, Object> serviceNameValidator = new HashMap<String, Object>();
        setValidateLength(serviceNameValidator, "gte", 1, "lte", 30);
//
        validator.put("REFID", refIdValidator);
        validator.put("Channel", chanValidator);
        validator.put("bankCode", bankCodeValidator);
//        validator.put("bankName", bankNameValidator);
        validator.put("accountNo", accountNoValidator);
//        validator.put("accountName", accountNameValidator);
        validator.put("Mobile", mobileValidator);
//        validator.put("serviceName", serviceNameValidator);


    }

}
