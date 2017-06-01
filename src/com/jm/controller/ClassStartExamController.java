package com.jm.controller;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jm.json.jsonResult;
import com.jm.pojo.ClassExamFile;
import com.jm.pojo.ClassStartExam;
import com.jm.pojo.StudentExamRecord;
import com.jm.pojo.User;
import com.jm.query.ClassExamFileQueryParams;
import com.jm.query.ClassStartExamQueryParams;
import com.jm.query.StudentExamRecordQueryParams;
import com.jm.query.UserQueryParams;
import com.jm.serviceDao.ClassExamFileServiceDao;
import com.jm.serviceDao.ClassStartExamServiceDao;
import com.jm.serviceDao.UserServiceDao;
import com.jm.websocket.websocketMessage;
import com.jm.websocket.util.SocketSessionUtil;
import com.jm.serviceDao.StudentExamRecordServiceDao;
@Controller
@RequestMapping("/classStartExam")
public class ClassStartExamController extends actionTemplate<ClassStartExam, ClassStartExamServiceDao, ClassStartExamQueryParams>{

	@Autowired
	UserServiceDao userServiceDao;
	@Autowired
	ClassExamFileServiceDao classExamFileServiceDao;
	@Autowired
	StudentExamRecordServiceDao studentExamRecordServiceDao;
	
	
	public jsonResult save(ClassStartExam p) throws Exception {
		jsonResult result=super.save(p);
		ClassExamFileQueryParams ceqParam=new ClassExamFileQueryParams();
		ceqParam.setClassInfo_id(p.getClass_id());
		ceqParam.setCourse_id(p.getCourse_id());
		List<ClassExamFile> examFiles=classExamFileServiceDao.getBasic(ceqParam);
		Random rand=new Random();
		int index=0;
		UserQueryParams uParam=new UserQueryParams();
		uParam.setClass_id(p.getClass_id());
		List<User> students=userServiceDao.getBasic(uParam);
		StudentExamRecord ser=null;
		for(int i=0;i<students.size();i++){
			ser=new StudentExamRecord();
			ser.setCourse_id(p.getCourse_id());
			index=rand.nextInt(examFiles.size());
			ser.setCourseExamFile_id(examFiles.get(index).getId());
			ser.setStu_id(students.get(i).getId());
			studentExamRecordServiceDao.insert(ser);
		}
		return result;
	}

	@RequestMapping("/startExam")
	public jsonResult startExam(@RequestParam("id") Integer id,HttpSession session) throws Exception {
		ClassStartExam p=serviceDao.getBasicById(id);
		 p.setStartTime(new Date());
		 serviceDao.updateAllField(p);
		jsonResult result=new jsonResult("开考成功", p);

		
		UserQueryParams uParam=new UserQueryParams();
		uParam.setClass_id(p.getClass_id());
		List<User> students=userServiceDao.getBasic(uParam);
		websocketMessage message;
		User user=(User)session.getAttribute("user");
		for(int i=0;i<students.size();i++){
			message=new websocketMessage();
			message.setMsg("开始考试");
			message.setClassName("开始考试");
			message.setFromId(user.getId());
			message.setToId(students.get(i).getId());
			SocketSessionUtil.sendMessage(message);
		}
		return result;
	}
	

	public UserServiceDao getUserServiceDao() {
		return userServiceDao;
	}


	public ClassExamFileServiceDao getClassExamFileServiceDao() {
		return classExamFileServiceDao;
	}


	public StudentExamRecordServiceDao getStudentExamRecordServiceDao() {
		return studentExamRecordServiceDao;
	}


	public void setUserServiceDao(UserServiceDao userServiceDao) {
		this.userServiceDao = userServiceDao;
	}


	public void setClassExamFileServiceDao(ClassExamFileServiceDao classExamFileServiceDao) {
		this.classExamFileServiceDao = classExamFileServiceDao;
	}


	public void setStudentExamRecordServiceDao(StudentExamRecordServiceDao studentExamRecordServiceDao) {
		this.studentExamRecordServiceDao = studentExamRecordServiceDao;
	}

	
	
}
