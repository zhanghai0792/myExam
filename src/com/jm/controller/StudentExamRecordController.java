package com.jm.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jm.json.jsonResult;
import com.jm.pojo.ClassExamFile;
import com.jm.pojo.ClassStartExam;
import com.jm.pojo.Course;
import com.jm.pojo.CourseExamFile;
import com.jm.pojo.StudentExamRecord;
import com.jm.pojo.User;
import com.jm.query.ClassStartExamQueryParams;
import com.jm.query.StudentExamRecordQueryParams;
import com.jm.serviceDao.ClassExamFileServiceDao;
import com.jm.serviceDao.ClassStartExamServiceDao;
import com.jm.serviceDao.CourseExamFileServiceDao;
import com.jm.serviceDao.CourseServiceDao;
import com.jm.serviceDao.StudentExamRecordServiceDao;
import com.jm.util.AppConfig;
import com.jm.util.FileUtil;
import com.jm.util.ListUtil;
@Controller
@RequestMapping("/studentExamRecord")
public class StudentExamRecordController extends actionTemplate<StudentExamRecord, StudentExamRecordServiceDao, StudentExamRecordQueryParams>{
    @Autowired
	ClassExamFileServiceDao classExamFileServiceDao;
	@Autowired
	CourseServiceDao courseServiceDao;
	@Autowired
	CourseExamFileServiceDao courseExamFileServiceDao;
	@Autowired
	ClassStartExamServiceDao classStartExamService;
	
	@RequestMapping("/downExamFile")
	public OutputStream downExamFile(@RequestParam("stuExamRord_id") Integer stuExamRord_id ,HttpServletResponse response,HttpSession session) throws Exception{
		User user=(User)session.getAttribute("user");
		if(user.getType()!=0)
			throw new Exception("用户类型不一致");
		
		
	
	  
	  StudentExamRecord serTemp=serviceDao.getBasicById(stuExamRord_id);
	   
	  CourseExamFile cef=courseExamFileServiceDao.getBasicById(serTemp.getCourseExamFile_id());
	  if(cef==null)
		  throw new Exception("没有对应的考试题目");
	   if(serTemp.getStartTime()==null){
		   serTemp.setStartTime(new Date());
		   serTemp.setIp(user.getIp());
		   serviceDao.updateNotNullField(serTemp);
	   }else{
		   if(!user.getIp().equals(serTemp.getIp())){
			   //通知教師
			   throw new Exception("考試異常！");
		   }
	   }
	  
	 InputStream in=new FileInputStream(cef.getRealPath());
	  String downFileName=(String)session.getAttribute("course_name")+FileUtil.getExtFileName(cef.getRealPath());
	  response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(downFileName, "UTF-8"));
	  OutputStream out=response.getOutputStream();
	 try{ int len = 0;  
      byte[] buffer = new byte[1024];  
     while((len = in.read(buffer)) > 0) {  
         out.write(buffer,0,len);  
     } 
      out.flush();
	 }catch(Exception e){}finally{
    	 if(in!=null)
    		 in.close();
     } 
		return out;
	}

	@RequestMapping("/uploadExamFile")
	@ResponseBody
	public jsonResult upload(@RequestParam("stuExamRord_id") Integer stuExamRord_id,HttpSession session,MultipartFile mFile)throws Exception{
		 if(mFile==null||mFile.getInputStream()==null)
			 throw new Exception("沒有上傳文件");
		User user=(User)session.getAttribute("user");		 
		StudentExamRecord serTemp=serviceDao.getBasicById(stuExamRord_id);
		if(!user.getIp().equals(serTemp.getIp())){
			   //通知教師
			   throw new Exception("考生的登錄信息異常");
		   }
		String saveFilePath=(String)session.getAttribute("savePath");
		saveFilePath=saveFilePath+"/"+user.getNo()+"-"+user.getName()+FileUtil.getExtFileName(mFile.getOriginalFilename());
		OutputStream outputStream=new FileOutputStream(saveFilePath);
		FileUtil.copyFile(mFile.getInputStream(), outputStream);
		serTemp.setEndTime(new Date());
		serTemp.setRealPath(saveFilePath);
		serviceDao.updateNotNullField(serTemp);
		//通知教師，保存改學生保存成功了
		return new jsonResult("保存成功");
	}
	
	
	@RequestMapping("/getStudentExamInfo")
	@ResponseBody
	public jsonResult getExamInfo(HttpSession session)throws Exception{
		User user=(User)session.getAttribute("user");
		if(user.getType()!=0)
		 throw new Exception("非学生用户");
		ClassStartExamQueryParams classSeq=new ClassStartExamQueryParams();
		classSeq.setClass_id(user.getClass_id());
		List<ClassStartExam> classExams= classStartExamService.getBasic(classSeq);
		if(ListUtil.isEmpty(classExams))
			return new jsonResult(false, "尚未开始考试");
		session.setAttribute("course_id",classExams.get(0).getCourse_id());
		session.setAttribute("course_name",classExams.get(0).getCourseName());
		session.setAttribute("savePath", AppConfig.RootPath+"/"+classExams.get(0).getCourseName()+"/"+Course.stusFilePath);
		StudentExamRecordQueryParams serqp=new StudentExamRecordQueryParams();
		serqp.setStu_id(user.getId());
		serqp.setCourse_id(classExams.get(0).getCourse_id());
		List<StudentExamRecord> ses=serviceDao.getBasic(serqp);
		 if(ListUtil.isEmpty(ses))
			 throw new Exception("学生的考试记录不存在");		 
		return new jsonResult("考试开始", ses.get(0));
	}

	
	
	public ClassExamFileServiceDao getClassExamFileServiceDao() {
		return classExamFileServiceDao;
	}


	public CourseServiceDao getCourseServiceDao() {
		return courseServiceDao;
	}


	public CourseExamFileServiceDao getCourseExamFileServiceDao() {
		return courseExamFileServiceDao;
	}


	public ClassStartExamServiceDao getClassStartExamService() {
		return classStartExamService;
	}


	public void setClassExamFileServiceDao(ClassExamFileServiceDao classExamFileServiceDao) {
		this.classExamFileServiceDao = classExamFileServiceDao;
	}


	public void setCourseServiceDao(CourseServiceDao courseServiceDao) {
		this.courseServiceDao = courseServiceDao;
	}


	public void setCourseExamFileServiceDao(CourseExamFileServiceDao courseExamFileServiceDao) {
		this.courseExamFileServiceDao = courseExamFileServiceDao;
	}


	public void setClassStartExamService(ClassStartExamServiceDao classStartExamService) {
		this.classStartExamService = classStartExamService;
	}
	
	
	
}
