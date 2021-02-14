package com.forth.boonterm.model.response;

import com.forth.boonterm.base.BaseResponse;
import com.forth.boonterm.model.common.KeyValue;

import java.util.List;

/**
 * Created by mvisionmacmini2 on 4/21/2016 AD.
 */
public class ServiceModel extends BaseResponse {
  private List<KeyValue> services;

  public List<KeyValue> getServices() {
    return services;
  }

  public void setServices(List<KeyValue> services) {
    this.services = services;
  }
}
