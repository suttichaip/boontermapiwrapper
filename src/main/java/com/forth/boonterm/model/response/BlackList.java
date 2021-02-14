package com.forth.boonterm.model.response;

import com.forth.boonterm.base.BaseResponse;

/**
 * Created by mvisionmacmini2 on 4/1/2016 AD.
 */
public class BlackList extends BaseResponse {
  private String status;
  private String level;

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }
}
