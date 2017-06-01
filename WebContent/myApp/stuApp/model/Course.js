 Ext.define("core.model.Course", {
			extend : 'core.model.basicModel',
			requires:["core.model.basicModel"],
			fields : [{
						name : "courseName"
					}, {
						name : "examFilePath"
					}, {
						name : 'stusExamFilePath'
					},{
					name : 'courseExamFiles',type:'object'
					}
					]				
		})
/*
 private String courseName;//课程名称
private java.util.Date createTime=new Date();//
private String examFilePath;//考题的目录
private String stusExamFilePath;//学生提交答案的存储总目录
private Set<CourseExamFile> courseExamFiles=new HashSet<CourseExamFile>(0);
 */
		