package com.jm.pojo;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class Course extends BasicModel {
public static final String teachFilePath="/考题试题集/";
public static final String stusFilePath="/学生答题集合/";
private String courseName;//课程名称
private java.util.Date createTime=new Date();//
private String examFilePath;//考题的目录
private String stusExamFilePath;//学生提交答案的存储总目录
private Set<CourseExamFile> courseExamFiles=new HashSet<CourseExamFile>(0);

public String getCourseName() {
return courseName;
}

public void setCourseName(String courseName) {
this.courseName = courseName == null ? null : courseName.trim();
}
public java.util.Date getCreateTime() {
return createTime;
}

public void setCreateTime(java.util.Date createTime) {
this.createTime = createTime;
}
public String getExamFilePath() {
return examFilePath;
}

public void setExamFilePath(String examFilePath) {
this.examFilePath = examFilePath == null ? null : examFilePath.trim();
}
public String getStusExamFilePath() {
return stusExamFilePath;
}

public void setStusExamFilePath(String stusExamFilePath) {
this.stusExamFilePath = stusExamFilePath == null ? null : stusExamFilePath.trim();
}

public Set<CourseExamFile> getCourseExamFiles() {
	return courseExamFiles;
}

public void setCourseExamFiles(Set<CourseExamFile> courseExamFiles) {
	this.courseExamFiles = courseExamFiles;
}





}