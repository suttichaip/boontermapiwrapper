package com.forth.boonterm.bean;

import java.util.List;

public class ErrorMpayBean{
	private List<ErrorListItem> errorList;

	public void setErrorList(List<ErrorListItem> errorList){
		this.errorList = errorList;
	}

	public List<ErrorListItem> getErrorList(){
		return errorList;
	}

	@Override
 	public String toString(){
		return 
			"ErrorMpayBean{" + 
			"errorList = '" + errorList + '\'' + 
			"}";
		}
}