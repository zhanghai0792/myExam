/**主控制器*/
Ext.define("core.controller.StudentExamController",{
	extend : "Ext.app.Controller",
	requires:["core.util.Util","core.util.Alert","core.util.SocketMessageManage"],
	
	init : function(){
		var self = this;
				/**下在是控制部分*/
		this.control({
			"studentexamview":{
			render:self.stuExamPanelRender
			}
		});
		//console.log("studentexamcontroller init方法调用");
	},
     stuExamPanelRender:function(stuPanel,eventObj){
     	var me=this;
     	var datas;
     	var component;
     	core.util.Util.ajax("studentExamRecord/getStudentExamInfo",{date:new Date()},true,component,me,me.stuExamEntry,me.stuExamNoStart);
     },
     stuExamEntry:function(stuExamRecord){
       var me=this;
       me.getStudentexamview().getLayout().setActiveItem(me.getStuFormPanel());
       if(stuExamRecord&&stuExamRecord.datas&&stuExamRecord.datas[0])
           me.getStuFormPanel().getForm().setValus(stuExamRecord.datas[0]);
     },
     stuExamNoStart:function(stuExamRecord){
     	//console.dir(stuExamRecord);
       core.util.Alert.msg("提示",stuExamRecord.msg);
     },
	views : [
		"core.view.StudentExamView"],
	stores : [],
	models : [],
	refs: [{
         ref: 'stuFormPanel',
         selector: 'studentexamview form'
     },
     {
         ref: 'studentexamview',
         selector: 'studentexamview'
     }]
});