package com.jm.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jm.pojo.Course;
import com.jm.query.CourseQueryParams;
import com.jm.serviceDao.CourseServiceDao;
import com.jm.util.AppConfig;
import com.jm.util.FileUtil;

@Controller
@RequestMapping("/course")
public class CourseController extends actionTemplate<Course, CourseServiceDao, CourseQueryParams>{
   //初始化课程考试的考题路径
	 private void initExamFilePath(Course c){
		 c.setExamFilePath("/"+c.getCourseName()+c.teachFilePath);
   	     c.setStusExamFilePath("/"+c.getCourseName()+c.stusFilePath);
   	     FileUtil.createFold(AppConfig.RootPath, c.getExamFilePath());
   	  FileUtil.createFold(AppConfig.RootPath, c.getStusExamFilePath());
	 }
	
	protected boolean isInsertBeforeDeal() {
		
		return true;
	}

	@Override
	protected boolean isUpdateBeforeDeal() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected boolean isDeleteBeforeDeal() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected boolean isDeleteAfterDeal() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected Object insertBeforeDeal(Object obj) {
              if(obj instanceof List){
            	 List<Course> coursies=(List<Course>)obj;
            	 Course c=null;
            	 for(int i=0;i<coursies.size();i++){
            		 c=coursies.get(i);
            		 initExamFilePath(c);
            	 }
              }else{
            	  Course c=(Course)obj;
            	  initExamFilePath(c);
              }
           return null;
	}

	@Override
	protected Object updateBeforeDeal(Object obj) {
		return insertBeforeDeal(obj);
	}

	@Override
	protected Object deleteBeforeDeal(Object obj) {
		return obj;
	}

	@Override
	protected Object deleteAfterDeal(Object obj) {
		 if(obj instanceof List){
        	 List<Course> coursies=(List<Course>)obj;
        	 Course c=null;
        	 for(int i=0;i<coursies.size();i++){
        		 c=coursies.get(i);
        		 FileUtil.delete(AppConfig.RootPath,"/"+c.getCourseName());
        	 }
          }else{
        	  Course c=(Course)obj;
        	  FileUtil.delete(AppConfig.RootPath,"/"+c.getCourseName());
          }
		 return null;
	}
    
}
