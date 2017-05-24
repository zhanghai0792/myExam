package com.jm.pojo;
import java.util.Date;
public class CourseExamFile extends BasicModel {
private Integer course_id;//考试科目id
private String courseName;//课程名称
private String examName;//考试题目的名称,如试题A、试题B等
private String realPath;//题目的路径
private Integer teacher_id;//上传教师的id
private java.util.Date createTime=new Date();//
public Integer getCourse_id() {
return course_id;
}

public void setCourse_id(Integer course_id) {
this.course_id = course_id;
}
public String getExamName() {
return examName;
}

public void setExamName(String examName) {
this.examName = examName == null ? null : examName.trim();
}
public String getRealPath() {
return realPath;
}

public void setRealPath(String realPath) {
this.realPath = realPath == null ? null : realPath.trim();
}
public Integer getTeacher_id() {
return teacher_id;
}

public void setTeacher_id(Integer teacher_id) {
this.teacher_id = teacher_id;
}
public java.util.Date getCreateTime() {
return createTime;
}

public void setCreateTime(java.util.Date createTime) {
this.createTime = createTime;
}

public String getCourseName() {
	return courseName;
}

public void setCourseName(String courseName) {
	this.courseName = courseName;
}


}