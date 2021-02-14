package com.forth.boonterm.model.request;

import com.forth.boonterm.base.BaseRequestModel;
import com.forth.boonterm.constant.BTConstant;

import java.util.HashMap;

/**
 * Created by thananan on 5/9/17.
 */
public class KBankDepositReqModelImpl extends BaseRequestModel {

    public KBankDepositReqModelImpl(){

    }

    public KBankDepositReqModelImpl(String ref, String chan, String accountNo, String mobile, Integer amount){

        request.put("REFID", ref);
        request.put("Channel", chan);
        request.put("Account", accountNo);
        request.put("Mobile", mobile);
        request.put("Amount", amount);

        HashMap<String, Object> refIdValidator = new HashMap<String, Object>();
        setValidateLength(refIdValidator, "gt", 10, "lte", 25);

        HashMap<String, Object> chanValidator = new HashMap<String, Object>();
        setValidateLength(chanValidator, "gt", 3, "lte", 20);

        HashMap<String, Object> accountNoValidator = new HashMap<String, Object>();
        setValidateLength(accountNoValidator, "gte", 1, "lte", 15);

        HashMap<String, Object> mobileValidator = new HashMap<String, Object>();
        setValidateLength(mobileValidator, "gte", 10, "lte", 11);
        mobileValidator.put("format", BTConstant.MOBILE_FORMAT);

        validator.put("REFID", refIdValidator);
        validator.put("Channel", chanValidator);
        validator.put("Account", accountNoValidator);
        validator.put("Mobile", mobileValidator);

    }

}
