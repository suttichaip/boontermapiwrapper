package com.forth.boonterm.bean;

public class ErrorListItem{
	private String msg;
	private String errorCode;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setErrorCode(String errorCode){
		this.errorCode = errorCode;
	}

	public String getErrorCode(){
		return errorCode;
	}

	@Override
 	public String toString(){
		return 
			"ErrorListItem{" + 
			"msg = '" + msg + '\'' + 
			",errorCode = '" + errorCode + '\'' + 
			"}";
		}
}
