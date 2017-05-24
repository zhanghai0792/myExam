package com.jm.query;
import com.jm.pojo.ClassExamFile;
public class ClassExamFileQueryParams extends QueryParams<ClassExamFile> {
 private Integer course_id;
 private Integer classInfo_id;
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
 


}