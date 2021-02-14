package com.forth.boonterm.model.common;

import java.io.Serializable;

/**
 * Created by mvisionmacmini2 on 4/1/2016 AD.
 */

public class KeyValue implements Serializable {

  private static final long serialVersionUID = 1L;

  private String key;
  private Object value;


  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public Object getValue() {
    return value;
  }

  public void setValue(Object value) {
    this.value = value;
  }

}
