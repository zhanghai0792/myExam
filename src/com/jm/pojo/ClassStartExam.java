package com.jm.pojo;
import java.util.Date;
public class ClassStartExam extends BasicModel {
private Integer class_id;//考试的班级id
private String className;
private Integer course_id;//考试课程的id
private String courseName;
private java.util.Date createTime=new Date();//
private java.util.Date startTime=new Date();//开始考试的时间
private Long duration;//考试的时间长度，按照毫秒算
private Long notify;//还差多长时间时提示，毫秒算
public Integer getClass_id() {
return class_id;
}

public void setClass_id(Integer class_id) {
this.class_id = class_id;
}
public Integer getCourse_id() {
return course_id;
}

public void setCourse_id(Integer course_id) {
this.course_id = course_id;
}
public java.util.Date getCreateTime() {
return createTime;
}

public void setCreateTime(java.util.Date createTime) {
this.createTime = createTime;
}
public java.util.Date getStartTime() {
return startTime;
}

public void setStartTime(java.util.Date startTime) {
this.startTime = startTime;
}
public Long getDuration() {
return duration;
}

public void setDuration(Long duration) {
this.duration = duration;
}
public Long getNotify() {
return notify;
}

public void setNotify(Long notify) {
this.notify = notify;
}

public String getClassName() {
	return className;
}

public String getCourseName() {
	return courseName;
}

public void setClassName(String className) {
	this.className = className;
}

public void setCourseName(String courseName) {
	this.courseName = courseName;
}



}