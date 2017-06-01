 Ext.define("core.model.User", {
			extend : 'core.model.basicModel',
			requires:["core.model.basicModel"],
			fields : [{
						name : "loginName"
					}, {
						name : "no"
					}, {
						name : 'type'
					},{
					name : 'class_id'
					},{
					name : 'className'
					},{
					name : 'name'
					}
					]				
		})
		/*
private String loginName;//登录名称，学生的长学号
private String no;//短学号或者职工号
private Byte type;//用户类型，0学生，1教师
@JsonIgnore
private String pwd;//密码，初始密码长学号
private Integer class_id;//用户所属班级的id
private java.util.Date createTime=new Date();//创建日期时间
private String className;
private String name; 
		 */