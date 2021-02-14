package com.forth.boonterm.base;

import com.forth.boonterm.bean.DisplayDetailBean;
import com.forth.boonterm.model.common.KeyValue;

import java.util.List;

/**
 * Created by mvisionmacmini2 on 3/29/2016 AD.
 */
public class BaseResponse {
  private String refId;
  private String code;
  private String desc;

  private  List<KeyValue> options;

  private List<DisplayDetailBean> displayDtl;

  public String getRefId() {
    return refId;
  }

  public void setRefId(String refId) {
    this.refId = refId;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public List<KeyValue> getOptions() {
    return options;
  }

  public void setOptions(List<KeyValue> options) {
    this.options = options;
  }

  public List<DisplayDetailBean> getDisplayDtl() {
    return displayDtl;
  }

  public void setDisplayDtl(List<DisplayDetailBean> displayDtl) {
    this.displayDtl = displayDtl;
  }
}
