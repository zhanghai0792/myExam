/**主控制器*/
Ext.define("core.controller.MainController",{
	extend : "Ext.app.Controller",
	init : function(){
		var self = this;
		self.addTabPanel=function(mainTabPanel,xtypeName,p_title){
		  return mainTabPanel.add({
			      xtype:xtypeName,
			      closable:true,
			      title:p_title     
			    });
		}
		/**下在是控制部分*/
		this.control({
			/*加载菜单*/
			/*"westview":{
			render : this.onPanelRender
			},*/
			"westview":{
		/*	select : this.onTreepanelSelect,*/
			itemdblclick : this.onTreepanelItemClick
			},
			/**注销*/
			"topview button[ref=logout]" : {
				click: function(btn){
					var dis=Ext.getCmp("displaylogin");
					dis.setValue("<font color=white><b>未登录</b></font>");
								
					Ext.util.Cookies.clear("autologin");
					Ext.create("core.view.LoginWindow").show();
				}
			},
			
			/**退出系统*/
			"topview button[ref=exit]" : {
				click: function(btn){
					Ext.Msg.confirm("提示","是否要退出系统",function(btn){
						if(btn == 'yes'){
							if(document.all){//IE
								window.open('', '_parent', '');
								window.close();
							}else{//FF
								window.open('', '_self', '');
								window.close();
							}
						}
					},this);
				}
			}/*,
			
			"westview treepanel":{
				itemclick:function(tree,record,item,index,e,eOpts){
					var mainView=tree.up("mainviewlayout").down("centerview");
					*//**用户管理*//*
					if(record.data["id"]=="usermanager"){
						self.addFunItem({
							mainView:mainView,
							funViewXtype:"userlayout",
							funController:"core.user.controller.UserController",
							funViewName:"core.user.view.UserLayout"
						});
						类别管理
					}else if(record.data["id"]=="category-product"){
						self.addFunItem({
							mainView:mainView,
							funViewXtype:"categorylayout",
							funController:"core.category.controller.CategoryController",
							funViewName:"core.category.view.CategoryLayout"
						});
						*//**商品管理*//*
					}else if(record.data["id"]=="productmanager"){
						self.addFunItem({
							mainView:mainView,
							funViewXtype:"productlayout",
							funController:"core.product.controller.ProductController",
							funViewName:"core.product.view.ProductLayout"
						});
						*//**订单管理*//*
					}else if(record.data["id"]=="salesorderment"){
						self.addFunItem({
							mainView:mainView,
							funViewXtype:"salesorderlayout",
							funController:"core.salesorder.controller.SalesOrderController",
							funViewName:"core.salesorder.view.SalesOrderLayout"
						});	
					}
				}//itemclick end
			}*///"westview treepanel" end
		});
	},
	
/*	onPanelRender : function(abstractcomponent, options) {
				var me=this;
				Ext.Ajax.request({
						waitMsg : '加载菜单……', 
						url:"user/getMenus",
						method:"POST",
						timeout:4000,
						success:function(response,opts){
							var resObj=Ext.decode(response.responseText);
							if(resObj.success){
								var root=resObj.root;
								var treeStore=Ext.create("Ext.data.TreeStore",{"root":root});
								abstractcomponent.store=(treeStore);
								
					            abstractcomponent.title=resObj.text;
								console.dir(abstractcomponent);
							}else{
								Ext.Msg.alert("提示",resObj.msg);
							}
						}
			 		});
			},*/
			/*onTreepanelSelect:function(selModel,record,index,options){
				
			   var mainpanel=this.getMainTabPanel();
			  var newTab=mainpanel.down(record.raw.viewXtype);
			  
			   if(!newTab){
			   	var className=record.raw.controller;
			   	console.log("控制器"+className)
			   	this.application.getController(record.raw.controller).init();
			   	console.log("视图xtype"+record.raw.viewXtype);
			    newTab=mainpanel.add({
			      xtype:record.raw.viewXtype,
			      closable:true,
			      title:record.get("text")
			      
			    });
			   }
			    
			  // mainpanel.setActiveTab(newTab);
			   
			},*/
			onTreepanelItemClick:function(view,record,item,index,event,options){
				var me=this;
			 var mainpanel=this.getMainTabPanel();
			  var newTab=mainpanel.down(record.raw.viewXtype);
			  
			   if(!newTab){
			   //	console.log("没有panel，要创建");
			   	var className=record.raw.controller;
			   	//console.log("控制器"+className)
			   	 if (!Ext.ClassManager.isCreated(className)) { //判断controller是否被创建   
			   	//this.application.getController(record.raw.controller).init();
			   	//me.application.getController(record.raw.controller);
			   	
			   	Ext.require(record.raw.controller,function(){
			   	 	me.application.getController(record.raw.controller);
			   	 	/*newTab=mainpanel.add({
			      xtype:record.raw.viewXtype,
			      closable:true,
			      title:record.get("text")
			      
			    });*/
			   	 
			     mainpanel.setActiveTab(me.addTabPanel(mainpanel,record.raw.viewXtype,record.get("text")));
			     
			   	});
			   	}else{
			   /*	newTab=mainpanel.add({
			      xtype:record.raw.viewXtype,
			      closable:true,
			      title:record.get("text")
			      
			    });
			     mainpanel.setActiveTab(newTab);*/
			   		  mainpanel.setActiveTab(me.addTabPanel(mainpanel,record.raw.viewXtype,record.get("text")));
			   	}
			   	//console.log("视图xtype"+record.raw.viewXtype);		    
			   }else{
			   	//console.log("有panel,不用创建");
			   mainpanel.setActiveTab(newTab);}
			},
	views : [
		"core.view.TopView",
		"core.view.WestView",
		"core.view.CenterView",
		"core.view.MainViewLayout"
		
	],
	stores : ["core.store.Menu"],
	models : [],
	refs: [{
         ref: 'mainTabPanel',
         selector: 'centerview'
     }]
});