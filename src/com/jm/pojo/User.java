package com.jm.pojo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
public class User extends BasicModel {
public static final byte Type_Teacher=1;
public static final byte Type_Student=0;
private String loginName;//登录名称，学生的长学号
private String no;//短学号或者职工号
private Byte type;//用户类型，0学生，1教师
@JsonIgnore
private String pwd;//密码，初始密码长学号
private Integer class_id;//用户所属班级的id
private java.util.Date createTime=new Date();//创建日期时间
private String className;
private String name;
private String ip;//登录时的ip地址
public String getLoginName() {
return loginName;
}

public void setLoginName(String loginName) {
this.loginName = loginName == null ? null : loginName.trim();
}
public String getNo() {
return no;
}

public void setNo(String no) {
this.no = no == null ? null : no.trim();
}
public Byte getType() {
return type;
}

public void setType(Byte type) {
this.type = type;
}
public String getPwd() {
return pwd;
}

public void setPwd(String pwd) {
this.pwd = pwd == null ? null : pwd.trim();
}
public Integer getClass_id() {
return class_id;
}

public void setClass_id(Integer class_id) {
this.class_id = class_id;
}
public java.util.Date getCreateTime() {
return createTime;
}

public void setCreateTime(java.util.Date createTime) {
this.createTime = createTime;
}

public String getClassName() {
	return className;
}

public void setClassName(String className) {
	this.className = className;
}

static String[] importFormat=new String[]{"no","loginName","name","学生名单"};

public static  String[] getImportFormat() {
	return importFormat;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}



 public static List<User> excelDatasChange(List<Map> datas,String className,Integer class_id){
	 List<User> users=new ArrayList<User>(0);
	 User u=null;
	 if(datas==null||datas.size()==0)
		 return null;
	 for(int i=0;i<datas.size();i++){
		 u=new User();
		 u.setType((byte) 0);
		 u.setClass_id(class_id);
		 u.setLoginName((String)datas.get(i).get(importFormat[1]));
		 u.setPwd(u.getLoginName());
		 u.setNo(className+String.format("%02d", Integer.parseInt((String)datas.get(i).get(importFormat[0]))));
		 u.setName((String)datas.get(i).get(importFormat[2]));
		 users.add(u);
	 }
	return users;		 
 }

public String getIp() {
	return ip;
}

public void setIp(String ip) {
	this.ip = ip;
}
 
 


}