/**主控制器*/
Ext.define("core.controller.UserManageController",{
	extend : "Ext.app.Controller",
	requires:["core.util.Util","core.util.Alert",'core.store.ClassInfos','core.store.Users',"core.view.user.UsersImport"],
	
	init : function(){
		var me = this;
				/**下在是控制部分*/
		this.control({
			"usergrid button#imports":{
			click:me.importStudents
			},
			"usersimport combo#classSelect":{
			select:me.classChange
			},
			"usersimport button#studentsImport":{
			click:me.importSubmit
			}
			
		});
		//console.log("studentexamcontroller init方法调用");
	},
	importSubmit:function(btn,events){
		var me=this;
		var win=btn.up("window");
	  btn.up("form").getForm().submit({
     url: contextPath+'/user/importStudents',
     waitMsg:'提交服务器处理中……',
    success: function(form, action) {
       Ext.Msg.show({title:'导入成功',
                     msg:action.result.msg,
                     buttons: Ext.Msg.OK,
                     fn:function(){
                     win.close();
                     me.getUsersStore().reload();
                     }
       });
    },
    failure: function(form, action) {
        switch (action.failureType) {
            case Ext.form.action.Action.CLIENT_INVALID:
                Ext.Msg.alert('Failure', 'Form fields may not be submitted with invalid values');
                break;
            case Ext.form.action.Action.CONNECT_FAILURE:
                Ext.Msg.alert('Failure', 'Ajax communication failed');
                break;
            case Ext.form.action.Action.SERVER_INVALID:
               Ext.Msg.alert('Failure', action.result.msg);
       }
    }
});
	},
	classChange:function(combo, records, eOpts ){
	var classId=combo.up("form").down("hidden");
	  classId.setValue(records[0].get("id"));
	},
	importStudents:function(){
		var me=this;
		var win=Ext.create("core.view.user.UsersImport");
		win.show();
	},
	views : [
		"core.view.user.UserGrid","core.view.user.UsersImport"],
	/*stores : ["core.store.Users",'core.store.ClassInfos']*/
		stores : ["Users",'ClassInfos']
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