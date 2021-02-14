package com.forth.boonterm.model.response;

import com.forth.boonterm.base.BaseResponse;
import com.forth.boonterm.model.common.KeyValue;

import java.util.List;

/**
 * Created by mvisionmacmini2 on 5/11/2016 AD.
 */
public class PriceList extends BaseResponse {
  private List<KeyValue> priceList;

  public List<KeyValue> getPriceList() {
    return priceList;
  }

  public void setPriceList(List<KeyValue> priceList) {
    this.priceList = priceList;
  }
}
