 Ext.define("core.model.CourseExamFile", {
			extend : 'core.model.basicModel',
			requires:["core.model.basicModel"],
			fields : [{
						name : "course_id"
					}, {
						name : "courseName"
					}, {
						name : 'examName'
					},{
					name : 'realPath'
					},{
					name : 'teacher_id'
					}
					]				
		})
		
/*
private Integer course_id;//考试科目id
private String courseName;//课程名称
private String examName;//考试题目的名称,如试题A、试题B等
private String realPath;//题目的路径
private Integer teacher_id;//上传教师的id
 */