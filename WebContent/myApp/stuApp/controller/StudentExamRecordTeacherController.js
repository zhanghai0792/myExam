Ext.define("core.controller.StudentExamRecordTeacherController",{
	extend : "Ext.app.Controller",
	requires:["core.util.Util","core.util.Alert","core.util.SocketMessageManage","core.store.StudentExamRecord"],
	
	init : function(){
		var self = this;
				/**下在是控制部分*/
		this.control({
			"stuexamrecordviewtseachergrid":{
			render:self.stuExamRecordGridPanelRender,
			destroy:self.stuExamRecordGridPanelDestroy
			}
		});
		//console.log("studentexamcontroller init方法调用");
	},
	stuExamRecordGridPanelDestroy:function(abstractcomponent, options){
		var me=this;
	core.util.SocketMessageManage.un("message",me.messageDeal);
	core.util.SocketMessageManage.stop();
	},
stuExamRecordGridPanelRender:function(abstractcomponent, options){
	var me=this;
 core.util.SocketMessageManage.start();
 core.util.SocketMessageManage.on("message",me.messageDeal);
},
messageDeal:function(obj,data){
	var me=this;
	console.dir(data);
   var store=Ext.getStore("core.store.StudentExamRecord");
   var index=store.find("id",data.msg.id);
   store.removeAt(index);
  store.insert(index, data.msg);
 
},
views : [
		"core.view.StuExamRecordViewTeacher"],
	stores : ["core.store.StudentExamRecord"],
	models : [],
	refs: [{
         ref: 'stuFormPanel',
         selector: 'stuexamrecordviewtseachergrid'
     }]
});