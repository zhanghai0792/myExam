package com.jm.dao;
import java.util.List;

import com.jm.pojo.CourseExamFile;
import com.jm.query.CourseExamFileQueryParams;
public interface CourseExamFileDao extends DaoTemplate<CourseExamFile,CourseExamFileQueryParams>{
  public int deleteCourseExamFileByCourse(List<Integer> courseIds)throws Exception;
}