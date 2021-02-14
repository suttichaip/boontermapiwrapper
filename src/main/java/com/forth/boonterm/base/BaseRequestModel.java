package com.forth.boonterm.base;

import com.forth.boonterm.constant.BTConstant;
import com.forth.boonterm.model.common.KeyValue;

import java.util.*;

/**
 * Created by mvisionmacmini2 on 3/24/2016 AD.
 */
public class BaseRequestModel implements IRequestModel {
  public HashMap<String , Object> request = new HashMap<>();
  protected HashMap<String , Object> validator = new HashMap<>();
  protected List<HashMap<String, String>> validateResultList = new ArrayList<>();


  protected void setValidateLength(HashMap<String, Object> validator, String chkMn, int min) {
    setValidateCommonLength("length", validator, chkMn, min, "", 0);
  }

  protected void setValidateLength(HashMap<String, Object> validator, String chkMn, int min, String chkMx, int max) {
    setValidateCommonLength("length", validator, chkMn, min, chkMx, max);
  }

  protected void setValidateArrayLength(HashMap<String, Object> validator, String chkMn, int min) {
    setValidateCommonLength("arraylen", validator, chkMn, min, "", 0);
  }

  protected void setValidateArrayLength(HashMap<String, Object> validator, String chkMn, int min, String chkMx, int max) {
    setValidateCommonLength("arraylen", validator, chkMn, min, chkMx, max);
  }

  protected void setValidateCommonLength(String keyValidate, HashMap<String, Object> validator, String chkMn, int min, String chkMx, int max) {
    HashMap<String, Integer> minLen = new HashMap<String, Integer>();
    minLen.put(chkMn, min);
    HashMap<String, Integer> maxLen = new HashMap<String, Integer>();
    maxLen.put(chkMx, max);
    List<HashMap<String, Integer>> lenlist = new ArrayList<>() ;
    lenlist.add(minLen);
    if (!chkMx.isEmpty()) {
      lenlist.add(maxLen);
    }
    validator.put(keyValidate, lenlist);
  }

  @Override
  public List<HashMap<String, String>> getValidateList() {
    validateHashMap(validator);
    return validateResultList;
  }

  private void validateFormat(String key, String target, String format) {
    if (!target.matches(format)) {
      HashMap<String, String> v = new HashMap<>();
      v.put(key, "invalid format");
      validateResultList.add(v);
    }
  }

  private boolean isIDWellForm(String id) {
    if(id.length() != 13) {
      return false;
    }

    if (!id.matches(BTConstant.ID_FORMAT)) {
      return false;
    }

    int sum = 0;
    for(int i = 0; i < 12; i++) {
      sum += (Integer.parseInt(String.valueOf(id.charAt(i))))*(13-i);
    }

    if((11- sum % 11) % 10 != Integer.parseInt(String.valueOf(id.charAt(12)))) {
      return false;
    }

    return true;
  }

  protected void validateIDWellForm(String key, String id) {
    if (!isIDWellForm(id)) {
      HashMap<String, String> v = new HashMap<>();
      v.put(key, "invalid format");
      validateResultList.add(v);
    }
  }

  private boolean validateEachLength(int targgetLen, HashMap<String, Integer> lenH) {
    Iterator<String> it = lenH.keySet().iterator();
    while(it.hasNext()){
      String key = it.next();
      switch (key) {
        case "eq":
          return targgetLen == lenH.get(key);
        case "neq":
          return targgetLen != lenH.get(key);
        case "gt":
          return targgetLen > lenH.get(key);
        case "gte":
          return targgetLen >= lenH.get(key);
        case "lt":
          return targgetLen < lenH.get(key);
        case "lte":
          return targgetLen <= lenH.get(key);
        default:
          return false;
      }
    }
    return false;
  }

  private void validateLength(String key, int targetLen, List<HashMap<String, Integer>> lenHL) {
    for (HashMap lenH: lenHL) {
      if (!validateEachLength(targetLen, lenH)) {
        HashMap<String, String> v = new HashMap<>();
        v.put(key, "invalid length");
        validateResultList.add(v);
      };
    }
  }

  private boolean validateHashMap(HashMap<String, Object> toValidateH) {
    return validateHashMap("", toValidateH);
  }

  private boolean validateHashMap(String originAttr, HashMap<String, Object> toValidateH) {
    Iterator<String> it = toValidateH.keySet().iterator();
    while(it.hasNext()){
      String key = it.next();
//      System.out.println("Key :" + key);
//      System.out.println("origin: " + originAttr);
//      System.out.println("request: " + request.get(key));
      if (toValidateH.get(key) instanceof HashMap) {
        // need to know interesting attribute
        String sendAttr = originAttr.equals("") ? key : originAttr ;
        validateHashMap(sendAttr, (HashMap) toValidateH.get(key));
      } else {
        switch (key) {
          case "length":
            validateLength(originAttr, ((String)request.get(originAttr)).length(), (List<HashMap<String, Integer>>) toValidateH.get(key));
            break;
          case "format":
            validateFormat(originAttr, (String) request.get(originAttr), (String) toValidateH.get(key));
            break;
          case "arraylen":
            validateLength(originAttr, ((List<KeyValue>) request.get(originAttr)).size(), (List<HashMap<String, Integer>>) toValidateH.get(key));
            break;
          case "thai_id_format":
            validateIDWellForm(originAttr, (String) request.get(originAttr));
            break;
        }
      };
    }
    return true;
  }


}
