package com.jm.meException;

public class myException extends Exception{
  private String msg;

public myException() {
	super();
	// TODO Auto-generated constructor stub
}

public myException(String message) {
	super(message);
	this.msg=message;
}

public myException(Throwable cause) {
	super(cause);
	this.msg=cause.getMessage();
}

public String getMsg() {
	return msg;
}

public void setMsg(String msg) {
	this.msg = msg;
}
  


}
