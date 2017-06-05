package com.jm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jm.controller.util.ExcelUtil;
import com.jm.json.jsonResult;
import com.jm.pojo.Menu;
import com.jm.pojo.User;
import com.jm.query.ClassStartExamQueryParams;
import com.jm.query.UserQueryParams;
import com.jm.serviceDao.ClassStartExamServiceDao;
import com.jm.serviceDao.StudentExamRecordServiceDao;
import com.jm.serviceDao.UserServiceDao;
import com.jm.util.IpUtil;
import com.jm.util.StringUtil;

@Controller
@RequestMapping("/user")
public class UserController extends actionTemplate<User,UserServiceDao,UserQueryParams>{
	@Autowired
	ExcelUtil excelUtil;
	@Autowired
	StudentExamRecordServiceDao studentExamRecordServiceDao;
	@Autowired
	ClassStartExamServiceDao classStartExamServiceDao;
	
	 @RequestMapping("/importStudents")
	 @ResponseBody
	 public jsonResult importStudents(@RequestParam("studentsFile") MultipartFile file,@RequestParam("class_id") Integer class_id,@RequestParam("className") String className)throws Exception{
		if(file==null||file.getInputStream()==null)
			throw new Exception("没有学生名单");
		 if(class_id==null)
			 throw new Exception("没有班级ID");
		 if(StringUtil.isEmpty(className))
			 throw new Exception("没有班级名");
		 List<Map> datas=excelUtil.analysisExcel(file.getInputStream(), User.getImportFormat());
		 List<User> users=User.excelDatasChange(datas, className, class_id);
		 int size=serviceDao.inserts(users);
		 jsonResult json=new jsonResult("添加成功"+size+"条记录",users);
		 return json;
	 }
	 
	 @RequestMapping("/login")
	 public String login(UserQueryParams userParams,HttpServletRequest request,Map models){
		try{
		 User user=serviceDao.login(userParams);
		 if(user==null)
			 throw new Exception("用户名或密码不正确");
		 user.setIp(IpUtil.getIpAddr(request));//获得用户的ip地址
		 HttpSession session=request.getSession();
		 session.setAttribute("user",user);
		 System.out.println(user.getName()+"  "+user.getIp());
		 
		 return "redirect:../main.jsp";
		 }catch(Exception e){
			 e.printStackTrace();
			 models.put("errors",e.getMessage());
			 return "login";
		 }
	 }
	 
	/* @RequestMapping("/getMenus")
	 @ResponseBody
	 public Map getMenus(HttpSession session){
		 User user=(User)session.getAttribute("user");
		 byte type=user.getType();
		 Map<String,Object> menus=new HashMap<String,Object>(0);
		 Map<String,Object> result=new HashMap<String,Object>(0);
		
		 menus.put("expanded",true);
		 if(type==0)
			 menus.put("text","用户操作菜单");
		 else
			 menus.put("text","教师操作菜单");
		  List<Menu> childs=new ArrayList<Menu>(0);
		  menus.put("children",childs);
		   if(type==0){
			   //學生菜單
			   Menu childTemp=new Menu("学生考试","core.controller.StudentExamController", "core.view.StudentExamView");
		      childs.add(childTemp);
		   }else{
			   //教師菜單
			   Menu childTemp=new Menu("学生管理","core.controller.StudentManageController", "core.view.StudentManageView");
			      childs.add(childTemp); 
			      childTemp=new Menu("课程管理","core.controller.CourseManageController", "core.view.CourseManageView");
			      childs.add(childTemp); 
			      childTemp=new Menu("题目管理","core.controller.CourseExamController", "core.view.CourseExamManageView");
			      childs.add(childTemp); 
			      childTemp=new Menu("班级考试管理","core.controller.ClassExamController", "core.view.ClassExamManageView");
			      childs.add(childTemp);
			      childTemp=new Menu("考试信息监控","core.controller.StuExamRecordController", "core.view.StuExamRecordView");
			      childs.add(childTemp); 
		   }
		  
		  
		 result.put("root",menus);
		 result.put("success", true);
		 
		 return result;
	 }*/
	 
	 
	 
	 @RequestMapping("/getMenus")
	 @ResponseBody
	 public jsonResult getMenus(HttpSession session){
		 User user=(User)session.getAttribute("user");
		 byte type=user.getType();
		  List<Menu> childs=new ArrayList<Menu>(0);
		   if(type==0){
			   //學生菜單
			   Menu childTemp=new Menu("学生考试","core.controller.StudentExamController", "studentexamview");
		      childs.add(childTemp);
		   }else{
			   //教師菜單
			   Menu childTemp=new Menu("学生管理","core.controller.UserManageController", "usergrid");
			      childs.add(childTemp); 
			      childTemp=new Menu("课程管理","core.controller.CourseManageController", "core.view.CourseManageView");
			      childs.add(childTemp); 
			      childTemp=new Menu("题目管理","core.controller.CourseExamController", "core.view.CourseExamManageView");
			      childs.add(childTemp); 
			      childTemp=new Menu("班级考试管理","core.controller.ClassExamController", "core.view.ClassExamManageView");
			      childs.add(childTemp);
			      childTemp=new Menu("考试信息监控","core.controller.StudentExamRecordTeacherController", "stuexamrecordviewtseachergrid");
			      childs.add(childTemp); 
		   }
		  
		  
		
		 
		 return new jsonResult(childs);
	 }
	 

	public ExcelUtil getExcelUtil() {
		return excelUtil;
	}

	public void setExcelUtil(ExcelUtil excelUtil) {
		this.excelUtil = excelUtil;
	}

	public StudentExamRecordServiceDao getStudentExamRecordServiceDao() {
		return studentExamRecordServiceDao;
	}

	public ClassStartExamServiceDao getClassStartExamServiceDao() {
		return classStartExamServiceDao;
	}

	public void setStudentExamRecordServiceDao(StudentExamRecordServiceDao studentExamRecordServiceDao) {
		this.studentExamRecordServiceDao = studentExamRecordServiceDao;
	}

	public void setClassStartExamServiceDao(ClassStartExamServiceDao classStartExamServiceDao) {
		this.classStartExamServiceDao = classStartExamServiceDao;
	}

	
	
}
