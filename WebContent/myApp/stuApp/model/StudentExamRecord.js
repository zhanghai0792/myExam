Ext.define("core.model.StudentExamRecord", {
	/*extend:'Ext.data.Model',*/
		extend : 'core.model.basicModel',
			requires:["core.model.basicModel"],
fields : [
	{name:'id',type:'int'},
		{name:'createTime',type:'date',dateFormat:"Y-m-d H:i:s"},
	{ name : "stu_id",type:'int' }, 
	{ name : "stuId"}, 
		{ name : "stuName" }, 
			{ name : 'course_id',type:'int' },
				{ name : 'courseName' },
					{name : 'courseExamFile_id',type:'int'},
						{name : 'courseExamFileName'},
{name : 'startTime',type:'date',dateFormat:"Y-m-d H:i:s"},
{name : 'endTime',type:'date',dateFormat:"Y-m-d H:i:s"},
{name : 'ip'},
{name : 'realPath'}
					]				
		})
		
/*
id
stu_id
stuName
course_id
courseName
courseExamFile_id
courseExamFileName
startTime
endTime
ip
realPath
createTime
 * 
 */