package com.forth.boonterm.model.response;

import com.forth.boonterm.base.BaseResponse;
import com.forth.boonterm.model.common.Stations;

import java.util.List;

/**
 * Created by mvisionmacmini2 on 4/22/2016 AD.
 */
public class ChargerStatus extends BaseResponse {
  private List<Stations> stations;

    public List<Stations> getStations() {
        return stations;
    }

    public void setStations(List<Stations> stations) {
        this.stations = stations;
    }
}
