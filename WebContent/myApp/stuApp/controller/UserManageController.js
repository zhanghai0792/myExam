/**主控制器*/
Ext.define("core.controller.UserManageController",{
	extend : "Ext.app.Controller",
	requires:["core.util.Util","core.util.Alert",'core.store.ClassInfo',"core.view.user.UsersImport"],
	
	init : function(){
		var me = this;
				/**下在是控制部分*/
		this.control({
			"usergrid button#imports":{
			click:me.importStudents
			}
			
		});
		//console.log("studentexamcontroller init方法调用");
	},
	importStudents:function(){
		var me=this;
		var win=Ext.create("core.view.user.UsersImport");
		win.show();
	},
	views : [
		"core.view.user.UserGrid","core.view.user.UsersImport"],
	stores : ["core.store.User",'core.store.ClassInfo']
/*	models : []*/
	/*refs: [{
         ref: 'stuFormPanel',
         selector: 'studentexamview form'
     },
     {
         ref: 'studentexamview',
         selector: 'studentexamview'
     }]*/
});