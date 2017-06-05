Ext.define("core.view.user.UserGrid", {
			extend : "core.view.template.AbstractGrid",
			requires : ["core.view.template.AbstractGrid"],
			alias : 'widget.usergrid',
			store : "core.store.User",
			columns : [{
						text : 'id',
						flex:1,
						dataIndex : 'id'						
					},{
						text : '登录账号',
						flex:4,
						dataIndex : 'loginName'						
					}, {
						text : '学号',
						flex : 3,
						dataIndex : "no"						
					},  {
						text : '姓名',
						flex:4,
						dataIndex : 'name'},{
						text : '类型',
						flex:1,
						dataIndex : 'type',
						 renderer: function(value){
        if (value === 1) {
            return '1 教师';
        }
        return value + ' 考生';
    }
    }, {
						text : '班级名称',
						flex:4,
						dataIndex : 'className'}
					
					],
					dockedItems :[{
							xtype : 'toolbar',
							dock : 'top',
							itemId : "topToolbar",
							items : [
							{xtype:'button',text:'导入考生',itemId:'imports'}
							]}]

		});