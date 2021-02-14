package com.forth.boonterm.model.response;

import java.util.List;

import com.forth.boonterm.model.common.Stations;
import com.google.gson.annotations.SerializedName;

public class ResponseChargerStatus{

	@SerializedName("Desc")
	private String desc;

	@SerializedName("Data")
	private List<Stations> data;

	@SerializedName("REFID")
	private String rEFID;

	@SerializedName("Code")
	private String code;

	public void setDesc(String desc){
		this.desc = desc;
	}

	public String getDesc(){
		return desc;
	}

	public void setData(List<Stations> data){
		this.data = data;
	}

	public List<Stations> getData(){
		return data;
	}

	public void setREFID(String rEFID){
		this.rEFID = rEFID;
	}

	public String getREFID(){
		return rEFID;
	}

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}
}