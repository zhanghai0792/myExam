package com.jm.pojo;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
public class StudentExamRecord extends BasicModel {
private Integer stu_id;//
private String stuName;
private Integer course_id;//考试科目
private String courseName;
private Integer courseExamFile_id;//考试题目的题号
private String courseExamFileName;
@JsonFormat(pattern="yyyy-MM-dd kk:mm:ss")
private java.util.Date startTime;//开始考试时间
@JsonFormat(pattern="yyyy-MM-dd kk:mm:ss")
private java.util.Date endTime;//考试结束的时间
private String ip;//首次登陆的ip地址
private String realPath;//上传的答案的路径
private java.util.Date createTime=new Date();//
public Integer getStu_id() {
return stu_id;
}

public void setStu_id(Integer stu_id) {
this.stu_id = stu_id;
}
public Integer getCourse_id() {
return course_id;
}

public void setCourse_id(Integer course_id) {
this.course_id = course_id;
}
public Integer getCourseExamFile_id() {
return courseExamFile_id;
}

public void setCourseExamFile_id(Integer courseExamFile_id) {
this.courseExamFile_id = courseExamFile_id;
}
public java.util.Date getStartTime() {
return startTime;
}

public void setStartTime(java.util.Date startTime) {
this.startTime = startTime;
}
public java.util.Date getEndTime() {
return endTime;
}

public void setEndTime(java.util.Date endTime) {
this.endTime = endTime;
}
public String getIp() {
return ip;
}

public void setIp(String ip) {
this.ip = ip == null ? null : ip.trim();
}
public String getRealPath() {
return realPath;
}

public void setRealPath(String realPath) {
this.realPath = realPath == null ? null : realPath.trim();
}
public java.util.Date getCreateTime() {
return createTime;
}

public void setCreateTime(java.util.Date createTime) {
this.createTime = createTime;
}

public String getStuName() {
	return stuName;
}

public String getCourseName() {
	return courseName;
}

public String getCourseExamFileName() {
	return courseExamFileName;
}

public void setStuName(String stuName) {
	this.stuName = stuName;
}

public void setCourseName(String courseName) {
	this.courseName = courseName;
}

public void setCourseExamFileName(String courseExamFileName) {
	this.courseExamFileName = courseExamFileName;
}

}