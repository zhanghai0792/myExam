package com.jm.query;
import com.jm.pojo.StudentExamRecord;
public class StudentExamRecordQueryParams extends QueryParams<StudentExamRecord> {
  private Integer stu_id;
  private Integer course_id;
public Integer getStu_id() {
	return stu_id;
}
public Integer getCourse_id() {
	return course_id;
}
public void setStu_id(Integer stu_id) {
	this.stu_id = stu_id;
}
public void setCourse_id(Integer course_id) {
	this.course_id = course_id;
}
  
}