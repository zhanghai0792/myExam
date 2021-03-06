Ext.define("core.controller.StudentExamController",{
	extend : "Ext.app.Controller",
	requires:["core.util.Util","core.util.Alert","core.util.SocketMessageManage"],
	
	init : function(){
		var self = this;
				/**下在是控制部分*/
		this.control({
			"studentexamview":{
			render:self.stuExamPanelRender,
			destroy:self.stuExamPanelDestroy
			},
			"studentexamview form button#down":{
			click:self.downFile
			},
			"studentexamview form button#upload":{
			click:self.upload
			}
		});
		//console.log("studentexamcontroller init方法调用");
	},
	stuExamPanelDestroy:function(abstractComponent,event){
		var me=this;
		 if( me.getStudentexamview().isStartSocket==false){
		  core.util.SocketMessageManage.start();
       
     core.util.SocketMessageManage.un("message",me.messageDeal);}
	},
	messageDeal:function(obj,data){
	  console.dir(data);
	},
	upload:function(btn){
		var me=this;
		me.getStuFormPanel().getEl().mask("上传答案中……");
		me.getStuFormPanel().getForm().submit({
		url: 'studentExamRecord/uploadExamFile',
    success: function(form, action) {
    	me.getStuFormPanel().getEl().unmask();
       Ext.Msg.alert('成功', action.result.msg);
    },
    failure: function(form, action) {
    	me.getStuFormPanel().getEl().unmask();
        switch (action.failureType) {
            case Ext.form.action.Action.CLIENT_INVALID:
                Ext.Msg.alert('失败', 'Form fields may not be submitted with invalid values');
                break;
            case Ext.form.action.Action.CONNECT_FAILURE:
                Ext.Msg.alert('失败', 'Ajax communication failed');
                break;
            case Ext.form.action.Action.SERVER_INVALID:
               Ext.Msg.alert('失败', action.result.msg);
       }
    }
});
	},
	downFile:function(btn){
		var me=this;
	window.open(contextPath+"/studentExamRecord/downExamFile1?id="+me.stuExam.id,"_blank");
		

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
       if(stuExamRecord&&stuExamRecord.datas&&stuExamRecord.datas[0]){
           me.getStuFormPanel().getForm().setValues(stuExamRecord.datas[0]);
           me.stuExam=stuExamRecord.datas[0];}
           
     },
     stuExamNoStart:function(stuExamRecord){
     	var me=this;
     	//console.dir(stuExamRecord);
       core.util.Alert.msg("提示",stuExamRecord.msg);
        core.util.SocketMessageManage.start();
        me.getStudentexamview().isStartSocket=true;
     core.util.SocketMessageManage.on("message",me.messageDeal);
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