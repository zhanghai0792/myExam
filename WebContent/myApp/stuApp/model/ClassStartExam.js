/*
private Integer class_id;//考试的班级id
private String className;
private Integer course_id;//考试课程的id
private String courseName;
private java.util.Date createTime=new Date();//
private java.util.Date startTime=new Date();//开始考试的时间
private Long duration;//考试的时间长度，按照毫秒算
private Long notify;//还差多长时间时提示，毫秒算
private Integer teacher_id;
private String teacherName;
 */
 Ext.define("core.model.ClassStartExam", {
			extend : 'core.model.basicModel',
			requires:["core.model.basicModel"],
			fields : [{
						name : "class_id",type:'int'
					}, {
						name : "className"
					}, {
						name : 'course_id'
					},{
					name : 'courseName'
					},
{name : 'startTime',type:'date',dateFormat:"Y-m-j H:i:s"},
{name : 'duration',type:'int'},
{name : 'notify'},
{name : 'teacherName'},
{
						name : 'teacher_id'
					}
					]				
		})