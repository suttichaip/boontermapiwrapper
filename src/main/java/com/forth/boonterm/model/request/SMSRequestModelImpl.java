package com.forth.boonterm.model.request;

import com.forth.boonterm.base.BaseRequestModel;
import com.forth.boonterm.constant.BTConstant;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mvisionmacmini2 on 3/22/2016 AD.
 */
public class SMSRequestModelImpl extends BaseRequestModel {
  private String locale;
  public SMSRequestModelImpl(String ref, String chan, String dest, String txt, String locale) {
    request.put("refId", ref);
    request.put("channel", chan);
    request.put("target", dest);
    request.put("text", txt);

    this.locale = locale;

    HashMap<String, Object> refIdValidator = new HashMap<String, Object>();
    setValidateLength(refIdValidator, "gt", 10, "lte", 25);

    HashMap<String, Object> chanValidator = new HashMap<String, Object>();
    setValidateLength(chanValidator, "gt", 3, "lte", 20);

    HashMap<String, Object> destValidator = new HashMap<String, Object>();
    setValidateLength(destValidator, "gte", 10, "lte", 10);
    destValidator.put("format", BTConstant.MOBILE_FORMAT);

    HashMap<String, Object> txtValidator = new HashMap<String, Object>();
    setValidateLength(txtValidator, "gt", 1, "lte", 138);


    validator.put("refId", refIdValidator);
    validator.put("channel", chanValidator);
    validator.put("target", destValidator);
    validator.put("text", txtValidator);
//    String jsonStr = "{" +
//      "\"refId\": {\"length\": [{\"eq\": 10}, {\"eq\": 25}]}," +
//      "\"channel\": {\"length\": [{\"eq\": 3}, {\"eq\": 20}]}," +
//      "\"target\": {\"length\": [{\"eq\": 10}, {\"eq\": 20}], \"format\": \"^[0]{1}+[689]{1}+[0-9]{8}$\"}," +
//      "\"text\": {\"length\": [{\"eq\": 1}, {\"eq\": 138}]}" +
//    "}";
  }

  public void convertUTF8Text() {
    if (this.locale.equals(BTConstant.TH)) {
      request.put("encode", "UCP");
      request.put("text", getUCP((String) request.get("text")));
    } else {
      request.put("encode", "ASCII");
      request.put("text", request.get("text"));
    }
  }


  private String getUCP(String text) {
    StringBuilder stringBuilder = new StringBuilder();
    for(int i = 0; i < text.length();i++) {
      char c = text.charAt(i);
      int codePoint = (int) c;
      String codePointHextStr = String.format("%04x", codePoint);
      String[] splits = codePointHextStr.split("(?<=\\G.{2})");
      for(String item : splits){
        stringBuilder.append("%"+item);
      }
    }
    return stringBuilder.toString();
  }
}
