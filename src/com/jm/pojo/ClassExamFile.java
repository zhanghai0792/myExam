package com.jm.pojo;
import java.util.Date;
//哪个班考哪些题目
public class ClassExamFile extends BasicModel {
private java.util.Date createTime=new Date();//
private Integer course_id;//考试课程的id
private String courseName;
private Integer classInfo_id;//考试班级的
private String className;
private Integer examFile_id;//考哪套题目
private String examFileName;
public java.util.Date getCreateTime() {
return createTime;
}

public void setCreateTime(java.util.Date createTime) {
this.createTime = createTime;
}
public Integer getCourse_id() {
return course_id;
}

public void setCourse_id(Integer course_id) {
this.course_id = course_id;
}
public Integer getClassInfo_id() {
return classInfo_id;
}

public void setClassInfo_id(Integer classInfo_id) {
this.classInfo_id = classInfo_id;
}
public Integer getExamFile_id() {
return examFile_id;
}

public void setExamFile_id(Integer examFile_id) {
this.examFile_id = examFile_id;
}

public String getCourseName() {
	return courseName;
}

public String getClassName() {
	return className;
}

public String getExamFileName() {
	return examFileName;
}

public void setCourseName(String courseName) {
	this.courseName = courseName;
}

public void setClassName(String className) {
	this.className = className;
}

public void setExamFileName(String examFileName) {
	this.examFileName = examFileName;
}

}