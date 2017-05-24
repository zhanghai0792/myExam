package com.jm.pojo;
import java.util.Date;
public class ClassInfo extends BasicModel {
private String className;//班级名称
private java.util.Date createTime=new Date();//
public String getClassName() {
return className;
}

public void setClassName(String className) {
this.className = className == null ? null : className.trim();
}
public java.util.Date getCreateTime() {
return createTime;
}

public void setCreateTime(java.util.Date createTime) {
this.createTime = createTime;
}

}