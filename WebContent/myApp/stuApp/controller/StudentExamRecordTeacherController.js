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
			},
			"stuexamrecordviewtseachergrid combo#classInfo":{
			  select:self.classInfoChange
			}
		});
		//console.log("studentexamcontroller init方法调用");
	},
	classInfoChange:function(combo,records, eOpts ){
		/*console.dir(records);
		console.log(combo.getValue());*/
	  var me=this;
	   var store=Ext.getStore("core.store.StudentExamRecord");
	  var proxy=store.getProxy()
	   if(proxy.extraParams&&proxy.extraParams.hasOwnProperty("classId"))
	  delete proxy.extraParams.classId;
	   if(combo.getValue()>0){
	     proxy.extraParams={classId:combo.getValue()};
	     store.reload();}
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
   if(index>=0){
   store.removeAt(index);
  store.insert(index, data.msg);
  }
 
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