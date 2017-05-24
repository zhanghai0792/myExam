package com.jm.controllerExceptionDeal;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jm.json.jsonResult;

@ControllerAdvice
public class controllerDeal {
	@ExceptionHandler({Exception.class})
	@ResponseBody
	public jsonResult handleException(Exception ex){
		jsonResult json=new jsonResult(false,"操作失败&nbsp;&nbsp;错误提示:["+ex.getMessage()+"]");
		return json;
	}
}
