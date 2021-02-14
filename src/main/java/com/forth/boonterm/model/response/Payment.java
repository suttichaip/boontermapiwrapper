package com.forth.boonterm.model.response;

import com.forth.boonterm.base.BaseResponse;
import com.forth.boonterm.model.common.KeyValue;

import java.util.List;

/**
 * Created by mvisionmacmini2 on 4/22/2016 AD.
 */
public class Payment extends BaseResponse {
  private List<KeyValue> reference;
  private  List<KeyValue> options;

  public List<KeyValue> getOptions() {
    return options;
  }

  public void setOptions(List<KeyValue> options) {
    this.options = options;
  }

  public List<KeyValue> getReference() {
    return reference;
  }

  public void setReference(List<KeyValue> reference) {
    this.reference = reference;
  }
}
