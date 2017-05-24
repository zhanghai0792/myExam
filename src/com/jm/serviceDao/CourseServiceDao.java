package com.jm.serviceDao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jm.dao.CourseDao;
import com.jm.dao.CourseExamFileDao;
import com.jm.pojo.Course;
@Service
public class CourseServiceDao extends ServiceDaoTemplate<Course, CourseDao>{
     @Autowired
	CourseExamFileDao courseExamFileDao;
     //删除课程时要将课程下的题库及学生考试的记录及文件删除	
     public int deleteCourseExamFiles(List<Integer> courseIds)throws Exception{
    	return courseExamFileDao.deleteCourseExamFileByCourse(courseIds);
 	}
     
	@Override
	public int deleteById(Integer id) throws Exception {
		 List<Integer> courseIds=new ArrayList<Integer>(0);
		 courseIds.add(id);
		int size= super.deleteById(id);
		deleteCourseExamFiles(courseIds);
		return size;
	}

	@Override
	public int deleteObject(Course p) throws Exception {
		
		//return super.deleteObject(p);
		 List<Integer> courseIds=new ArrayList<Integer>(0);
		 courseIds.add(p.getId());
		int size= super.deleteObject(p);
		deleteCourseExamFiles(courseIds);
		return size;
	}

	@Override
	public int deleteByIds(List<Integer> ids) throws Exception {
		// TODO Auto-generated method stub
		int size=super.deleteByIds(ids);
		deleteCourseExamFiles(ids);
		return size;
	}

	@Override
	public int deleteObjects(List<Course> objs) throws Exception {
	
		//return super.deleteObjects(objs);
		 List<Integer> courseIds=new ArrayList<Integer>(0);
		
		int size=super.deleteObjects(objs);
		 for(int i=0;i<objs.size();i++)
			  courseIds.add(objs.get(i).getId());
		deleteCourseExamFiles(courseIds);
		return size;
	}

	public CourseExamFileDao getCourseExamFileDao() {
		return courseExamFileDao;
	}

	public void setCourseExamFileDao(CourseExamFileDao courseExamFileDao) {
		this.courseExamFileDao = courseExamFileDao;
	}

	
	
}
