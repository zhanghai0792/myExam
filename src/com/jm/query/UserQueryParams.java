package com.jm.query;
import com.jm.pojo.User;
public class UserQueryParams extends QueryParams<User> {
  private String userName;
  private String pwd;
  private Integer class_id;//用户所属班级的id
  
public String getUserName() {
	return userName;
}
public String getPwd() {
	return pwd;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public void setPwd(String pwd) {
	this.pwd = pwd;
}
public Integer getClass_id() {
	return class_id;
}
public void setClass_id(Integer class_id) {
	this.class_id = class_id;
}
  
}