/**
 * 程序布局放大中间的部分
 */
Ext.define("core.view.CenterView",{
	extend: 'Ext.tab.Panel',
	alias: 'widget.centerview',
	/*requires:["core.view.StudentExamView"],*/
	id:'centerid',
	//margins: '2 0 0 0',
	border : 0,
	bodyStyle: 'padding:0px',
	menuAlign:"center",
	
	items:[{
		title:'<center height=40>首页</center>',
//		iconCls:'home',
		bodyPadding :5,
		layout:'fit',
		html:'简易考试系统',
		tabConfig  : {//标签配置参数
			
        }
	}/*,{xtype:'studentexamview'}*/],
	initComponent:function(){
		this.callParent(arguments);
	}
});