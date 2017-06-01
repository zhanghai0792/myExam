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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.SocketUtils;
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
import com.jm.util.StringUtil;
import com.jm.websocket.websocketMessage;
import com.jm.websocket.util.SocketSessionUtil;
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
	
	@RequestMapping("/downExamFile1")
	public String downExamFile1(@RequestParam("id") Integer id,HttpServletResponse response,HttpSession session) throws Exception{
		User user=(User)session.getAttribute("user");
		if(user.getType()!=0)
			throw new Exception("用户类型不一致");
		
		
	
	  
	  StudentExamRecord serTemp=serviceDao.getBasicById(id);
	   
	  CourseExamFile cef=courseExamFileServiceDao.getBasicById(serTemp.getCourseExamFile_id());
	  if(cef==null)
		  throw new Exception("没有对应的考试题目");
	   if(serTemp.getStartTime()==null){
		   serTemp.setStartTime(new Date());
		   serTemp.setIp(user.getIp());
		   serviceDao.updateNotNullField(serTemp);
	   }else{
		   if(!user.getIp().equals(serTemp.getIp())){
			   websocketMessage message=new websocketMessage();
			   message.setToId((Integer)session.getAttribute("teacher_id"));
			   message.setMsg(serTemp);
			   message.setType(message.Msg_type_person);
			   message.setClassName("考生异常");
			   message.setFromId(user.getId());
			   SocketSessionUtil.sendMessage(message);
			   throw new Exception("考試异常！");
		   }
	   }
	   websocketMessage message=new websocketMessage();
	   message.setToId((Integer)session.getAttribute("teacher_id"));
	   message.setMsg(serTemp);
	   message.setType(message.Msg_type_person);
	   message.setClassName("开始考试");
	   message.setFromId(user.getId());
	   SocketSessionUtil.sendMessage(message);
	   
	 InputStream in=new FileInputStream(AppConfig.RootPath+cef.getRealPath());
	  String downFileName=(String)session.getAttribute("course_name")+FileUtil.getExtFileName(cef.getRealPath());
	  response.setHeader("content-disposition", "attachment;filename=" + new String(downFileName.getBytes(), "ISO-8859-1"));
	  response.setContentType("application/force-download");// 设置强制下载不打开
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
	 
		return null;
	}

	@RequestMapping("/downExamFile")
	public ResponseEntity<byte[]> downExamFile(@RequestParam("id") Integer id,HttpSession session) throws Exception{
		User user=(User)session.getAttribute("user");
		if(user.getType()!=0)
			throw new Exception("用户类型不一致");
		
		
	
	  
	  StudentExamRecord serTemp=serviceDao.getBasicById(id);
	   
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
			   throw new Exception("考試异常！");
		   }
	   }
	   websocketMessage message=new websocketMessage();
	   message.setToId((Integer)session.getAttribute("teacher_id"));
	   message.setMsg(serTemp);
	   message.setType(message.Msg_type_person);
	   message.setFromId(user.getId());
	   SocketSessionUtil.sendMessage(message);
	   byte [] body = null;  
	 InputStream in=new FileInputStream(AppConfig.RootPath+cef.getRealPath());
	 body = new byte[in.available()];
		in.read(body);
		HttpHeaders headers = new HttpHeaders();
		 String downFileName=(String)session.getAttribute("course_name")+FileUtil.getExtFileName(cef.getRealPath());
		headers.add("Content-Disposition", "attachment;filename="+new String(downFileName.getBytes(),"ISO-8859-1"));
HttpStatus statusCode = HttpStatus.OK;
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(body, headers, statusCode);
		
		return response;
	}
	
	
	
	@RequestMapping("/uploadExamFile")
	@ResponseBody
	public jsonResult upload(@RequestParam("id") Integer stuExamRord_id,HttpSession session,MultipartFile mFile)throws Exception{
		 if(mFile==null||mFile.getInputStream()==null)
			 throw new Exception("没有上传文件");
		User user=(User)session.getAttribute("user");		 
		StudentExamRecord serTemp=serviceDao.getBasicById(stuExamRord_id);
		if(!user.getIp().equals(serTemp.getIp())){
			   //通知教師
			 logger.error(user.getNo()+"-"+user.getName()+" "+user.getIp()+"ip地址异常"); 
			   throw new Exception("考生的登录信息异常");
		   }
		
		StudentExamRecord serTemp1=serviceDao.getBasicById(stuExamRord_id);
		
		 if(StringUtil.isNotEmpty(serTemp1.getRealPath())){
			 FileUtil.delete(serTemp1.getRealPath());
			 
			 //logger.info(u.getNo()+"-"+u.getName()+" "+u.getIp()+"重新上传答案");
		 }
		String saveFilePath=(String)session.getAttribute("savePath");
		saveFilePath=saveFilePath+"/"+user.getNo()+"-"+user.getName()+FileUtil.getExtFileName(mFile.getOriginalFilename());
		System.out.println(saveFilePath);
		OutputStream outputStream=new FileOutputStream(saveFilePath);
		FileUtil.copyFile(mFile.getInputStream(), outputStream);
		serTemp.setEndTime(new Date());
		serTemp.setRealPath(saveFilePath);
		serviceDao.updateNotNullField(serTemp);
		 if(StringUtil.isNotEmpty(serTemp1.getRealPath())){
		  logger.info(user.getNo()+"-"+user.getName()+" "+user.getIp()+"重新上传答案");
		 }else{
			 websocketMessage message=new websocketMessage();
			   message.setToId((Integer)session.getAttribute("teacher_id"));
			   message.setMsg(serTemp);
			   message.setType(message.Msg_type_person);
			   message.setClassName("考试结束");
			   message.setFromId(user.getId());
			   SocketSessionUtil.sendMessage(message);
			 logger.info(user.getNo()+"-"+user.getName()+" "+user.getIp()+"上传答案"); 
		 }
		 websocketMessage message=new websocketMessage();
		   message.setToId((Integer)session.getAttribute("teacher_id"));
		   message.setMsg(serTemp);
		   message.setType(message.Msg_type_person);
		   message.setFromId(user.getId());
		   SocketSessionUtil.sendMessage(message);
		
		//通知教師，保存改學生保存成功了
		return new jsonResult("考试答案上传成功，可以结束考试");
	}
	
	
	@RequestMapping("/getStudentExamInfo")
	@ResponseBody
	public jsonResult getExamInfo(HttpSession session)throws Exception{
		User user=(User)session.getAttribute("user");
		byte type=user.getType();
		if(type==0){
		 
		ClassStartExamQueryParams classSeq=new ClassStartExamQueryParams();
		classSeq.setClass_id(user.getClass_id());
		List<ClassStartExam> classExams= classStartExamService.getBasic(classSeq);
		if(ListUtil.isEmpty(classExams))
			return new jsonResult(false, "尚未开始考试");
		session.setAttribute("course_id",classExams.get(0).getCourse_id());
		session.setAttribute("course_name",classExams.get(0).getCourseName());
		session.setAttribute("savePath", AppConfig.RootPath+"/"+classExams.get(0).getCourseName()+"/"+Course.stusFilePath);
		session.setAttribute("teacher_id", classExams.get(0).getTeacher_id());
		StudentExamRecordQueryParams serqp=new StudentExamRecordQueryParams();
		serqp.setStu_id(user.getId());
		serqp.setCourse_id(classExams.get(0).getCourse_id());
		List<StudentExamRecord> ses=serviceDao.getBasic(serqp);
		 if(ListUtil.isEmpty(ses))
			 throw new Exception("学生的考试记录不存在");		 
		return new jsonResult("考试开始", ses.get(0));}
		else{
			StudentExamRecordQueryParams serqp=new StudentExamRecordQueryParams();
			List<StudentExamRecord> ses=serviceDao.getBasic(serqp);
			return new jsonResult(ses);
		}
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
