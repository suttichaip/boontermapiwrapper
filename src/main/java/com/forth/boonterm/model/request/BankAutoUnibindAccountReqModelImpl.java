package com.forth.boonterm.model.request;

import com.forth.boonterm.base.BaseRequestModel;
import com.forth.boonterm.constant.BTConstant;

import java.util.HashMap;

/**
 * Created by thananan on 5/9/17.
 */
public class BankAutoUnibindAccountReqModelImpl extends BaseRequestModel {

    public BankAutoUnibindAccountReqModelImpl(){

    }

    public BankAutoUnibindAccountReqModelImpl(String bankCode, String bankName, String mobile, String accountNo,String accountName, String tranNo,String tranDate,String actionType){

        request.put("mobile", mobile);
        request.put("bankCode", bankCode);
        request.put("bankName", bankName);
        request.put("accountNo", accountNo);
        request.put("accountName", accountName);
        request.put("tranNo", tranNo);
        request.put("tranDate", tranDate);
        request.put("actionType", actionType);

        HashMap<String, Object> mobileValidator = new HashMap<String, Object>();
        setValidateLength(mobileValidator, "gte", 10, "lte", 11);
        mobileValidator.put("format", BTConstant.MOBILE_FORMAT);

        HashMap<String, Object> bankCodeValidator = new HashMap<String, Object>();
        setValidateLength(bankCodeValidator, "gt", 1, "lte", 20);

        HashMap<String, Object> accountNoValidator = new HashMap<String, Object>();
        setValidateLength(accountNoValidator, "gte", 1, "lte", 30);

        HashMap<String, Object> tranNoValidator = new HashMap<String, Object>();
        setValidateLength(tranNoValidator, "gt", 1, "lte", 25);

        HashMap<String, Object> tranDateValidator = new HashMap<String, Object>();
        setValidateLength(tranDateValidator, "gt", 1, "lte", 25);

        HashMap<String, Object> actionTypeValidator = new HashMap<String, Object>();
        setValidateLength(actionTypeValidator, "gte", 1, "lte", 30);

        validator.put("mobile", mobileValidator);
        validator.put("bankCode", bankCodeValidator);
        validator.put("accountNo", accountNoValidator);
        validator.put("tranNo", tranNoValidator);
        validator.put("tranDate", tranDateValidator);
        validator.put("actionType", actionTypeValidator);

    }

}
