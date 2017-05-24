/**北部*/
Ext.define("core.view.TopView", {
	extend:"Ext.panel.Panel",
	alias : 'widget.topview',
	id:"topview",
	height : 100,
	bodyStyle : {
		background : '#7598e0',
		padding : '80px'
	},
	//html : "<h1><font color=white size=5>&nbsp;&nbsp;&nbsp;&nbsp;我佳炒货网上商城后台管理系统</font></h1>",
	layout : "absolute",
	items : [{
				x : 20,
				y : 0,
				width:450,
				bodyStyle : {
					background : '#7598e0',
					border:0,
					padding : '10px'
				},
				html : "<h1><font color=white size=5>&nbsp;&nbsp;&nbsp;&nbsp;简易考试系统</font></h1>"
			},{
				x : 800,
				y : 20,
				ref : "logininfo",
				xtype : "displayfield",
				id:"displaylogin",
				value : "<font color=white><b>"+appConfig.userName+"</b></font>"
			}, {
				x : 890,
				y : 20,
				xtype : "button",
				ref : "logout",
				text : "注销"
			}, {
				x : 960,
				y : 20,
				xtype : "button",
				ref : "exit",
				text : "退出系统"
			}]
});
