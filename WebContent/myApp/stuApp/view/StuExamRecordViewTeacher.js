Ext.define("core.view.StuExamRecordViewTeacher", {
			extend : "core.view.template.AbstractGrid",
			requires : ["core.view.template.AbstractGrid"],
			alias : 'widget.stuexamrecordviewtseachergrid',
			/*store : "core.store.StudentExamRecords",*/
			store : "StudentExamRecords",
			columns : [{
						text : 'id',
						width : 80,
						dataIndex : 'id'						
					},{
						text : '学号',
						flex:2,
						dataIndex : 'stuId'						
					}, {
						text : '学生姓名',
						flex : 2,
						dataIndex : "stuName"						
					}, {
						text : '开考时间',
						flex:5,
						dataIndex : 'startTime',
						xtype: 'datecolumn',format:'Y-m-d H:i:s'}, {
						text : '结束时间',
						flex:5,
						dataIndex : 'endTime',
						xtype: 'datecolumn', format:'Y-m-d H:i:s'}/*,{
						text : '考试科目编号',
						width : 80,
						dataIndex : 'course_id'}*/
						, {
						text : '考试科目名称',
						flex:3,
						dataIndex : 'courseName'}, /*{
						text : '试题编号',
						width : 80,
						dataIndex : 'courseExamFile_id'},*/ {
						text : '试题名称',
						flex:3,
						dataIndex : 'courseExamFileName'},  {
						text : 'ip地址',
						flex:2,
						dataIndex : 'ip'}/*, {
						text : '考试路径',
						flex:4,
						dataIndex : 'realPath'}*/
					
					],
					dockedItems :[{
							xtype : 'toolbar',
							dock : 'top',
							itemId : "topToolbar",
							items : [/*{
										xtype : 'button',
										itemId : 'add',
										text : '添加',,
                                         
										iconCls : 'add'
										handler:function(){
										 var me=this;
										alert(me.up("grid").getStore().count());
										var store=me.up("grid").getStore();
										alert(store.storeId);
										alert(Ext.getStore("studentexamrecord").count());
										}
									}, {
										xtype : 'tbseparator'
									}, {
										xtype : 'button',
										itemId : 'delete',
										text : '删除',
										iconCls : 'save_all'
									}, {
										xtype : 'tbseparator'
									},*/{
									  xtype:"combo",
									  itemId:"classInfo",
									  fieldLabel: '选择班级',
    store: Ext.create('Ext.data.Store', {
    fields: ['id', 'name'],
    data : [
        {"id":-1, "name":"全部"},
        {"id":1, "name":"信息A1521"},
        {"id":2, "name":"信息A1522"}
    ]
}),
    queryMode: 'local',
    displayField: 'name',
    valueField: 'id'
									
									}
/*, {
										xtype : 'button',
										itemId : 'cancel',
										text : '刷新',
										iconCls : 'cancel'
									}, *//*{
										xtype : 'tbseparator'
									}, {
										xtype : 'button',
										itemId : 'clearFilter',
										text : '清除查询条件',
										iconCls : 'clear_filter'
									}*/]
						}

				]

		});