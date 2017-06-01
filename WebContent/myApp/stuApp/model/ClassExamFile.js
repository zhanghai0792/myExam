 Ext.define("core.model.ClassExamFile", {
			extend : 'core.model.basicModel',
			requires:["core.model.basicModel"],
			fields : [{
						name : "course_id",type:'int'
					}, {
						name : "courseName"
					}, {
						name : 'classInfo_id'
					},{
					name : 'className'
					},
{name : 'examFileName'},
{name : 'examFile_id'}
					]				
		})
/*

private Integer course_id;//考试课程的id
private String courseName;
private Integer classInfo_id;//考试班级的
private String className;
private Integer examFile_id;//考哪套题目
private String examFileName; 
 */