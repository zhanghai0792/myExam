package com.jm.query;
import java.util.Date;

import com.jm.pojo.ClassStartExam;
public class ClassStartExamQueryParams extends QueryParams<ClassStartExam> {
 private Date currentTime=new Date();

 private Integer class_id;
public Date getCurrentTime() {
	return currentTime;
}

public void setCurrentTime(Date currentTime) {
	this.currentTime = currentTime;
}



public Integer getClass_id() {
	return class_id;
}

public void setClass_id(Integer class_id) {
	this.class_id = class_id;
}
 
 
}