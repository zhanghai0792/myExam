Ext.define("core.view.StuExamRecordViewTeacher", {
			extend : "core.view.template.AbstractGrid",
			requires : ["core.view.template.AbstractGrid"],
			alias : 'widget.stuexamrecordviewtseachergrid',
			store : "core.store.StudentExamRecord",
			columns : [{
						text : 'id',
						width : 80,
						dataIndex : 'id'						
					},{
						text : '学生_id',
						width : 80,
						dataIndex : 'stu_id'						
					}, {
						text : '学生姓名',
						flex : 2,
						dataIndex : "stuName"						
					}, {
						text : '开考时间',
						flex:3,
						dataIndex : 'startTime',
						xtype: 'datecolumn',format:'Y-m-d H:i:s'}, {
						text : '结束时间',
						flex:3,
						dataIndex : 'endTime',
						xtype: 'datecolumn', format:'Y-m-d H:i:s'},{
						text : '考试科目编号',
						width : 80,
						dataIndex : 'course_id'}
						, {
						text : '考试科目名称',
						flex:3,
						dataIndex : 'courseName'}, {
						text : '试题编号',
						width : 80,
						dataIndex : 'courseExamFile_id'}, {
						text : '试题名称',
						flex:3,
						dataIndex : 'courseExamFileName'},  {
						text : 'ip地址',
						flex:2,
						dataIndex : 'ip'}, {
						text : '考试路径',
						flex:4,
						dataIndex : 'realPath'}
					
					]
		});