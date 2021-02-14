package com.forth.boonterm.model.response;

import com.forth.boonterm.base.BaseResponse;
import com.forth.boonterm.model.common.KeyValue;

import java.util.List;

/**
 * Created by mvisionmacmini2 on 4/1/2016 AD.
 */
public class PaymentInfo extends BaseResponse {
  private List<KeyValue> options;

  public List<KeyValue> getOptions() {
    return options;
  }

  public void setOptions(List<KeyValue> option) {
    this.options = option;
  }
}
