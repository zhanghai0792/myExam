package com.jm.controller;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.jm.json.jsonResult;
import com.jm.pojo.Course;
import com.jm.pojo.CourseExamFile;
import com.jm.pojo.User;
import com.jm.query.CourseExamFileQueryParams;
import com.jm.serviceDao.CourseExamFileServiceDao;
import com.jm.util.AppConfig;
import com.jm.util.FileUtil;
import com.jm.util.StringUtil;

@Controller
@RequestMapping("/courseExamFile")
public class CourseExamFileController extends actionTemplate<CourseExamFile, CourseExamFileServiceDao, CourseExamFileQueryParams>{
 @RequestMapping("/addCourseExamFile")
 public jsonResult uploadCourseExamFile(CourseExamFile courseExamFile,MultipartFile mFile,HttpSession session)throws Exception{
	 User user=(User)session.getAttribute("user");
	 courseExamFile.setTeacher_id(user.getId());
	 String filePath=AppConfig.RootPath+"/"+courseExamFile.getCourseName()+"/"+Course.teachFilePath+"/"+courseExamFile.getExamName();
	 String extfile=FileUtil.getExtFileName(mFile.getName());
	 filePath=filePath+extfile;
	 OutputStream fos=new FileOutputStream(filePath);
	 FileUtil.copyFile(mFile.getInputStream(), fos);
	 courseExamFile.setRealPath(filePath);
	 serviceDao.insert(courseExamFile);
	 return new jsonResult("添加成功", courseExamFile);
 }

@Override
protected boolean isDeleteBeforeDeal() {
	return true;
}

@Override
protected boolean isDeleteAfterDeal() {
		return true;
}

@Override
protected Object deleteBeforeDeal(Object obj) {
		return obj;
}

//删除记录后，删除物理文件
protected Object deleteAfterDeal(Object obj) {
	if(obj instanceof List){
		List<CourseExamFile> cef=(List<CourseExamFile>)obj;
		CourseExamFile c=null;
		for(int i=0;i<cef.size();i++){
			c=cef.get(i);
			 if(StringUtil.isNotEmpty(c.getRealPath()))
			FileUtil.delete(c.getRealPath());
		}
	}else{
		CourseExamFile c=(CourseExamFile)obj;
		 if(StringUtil.isNotEmpty(c.getRealPath()))
		FileUtil.delete(c.getRealPath());
	}
	return null;
}
 
 
 
}
