/**主控制器*/
Ext.define("core.app.controller.MainController",{
	extend : "Ext.app.Controller",
	init : function(){
		var self = this;
		
		
		
		
		this.addFunItem=function(funInfo){
			if(funInfo){
				var mainView=funInfo.mainView;
				var funPanel=mainView.down(funInfo.funViewXtype);
				if(!funPanel){
					self.application.getController(funInfo.funController).init();
					funPanel=Ext.create(funInfo.funViewName);
					mainView.add(funPanel);
					mainView.setActiveTab(funPanel);
				}else{									
					mainView.setActiveTab(funPanel);
				}
			}
		},
		/**下在是控制部分*/
		this.control({
			/*加载菜单*/
			/*"westview":{
			render : this.onPanelRender
			},*/
			"westview treepanel":{
			select : this.onTreepanelSelect,
			itemclick : this.onTreepanelItemClick
			},
			/**注销*/
			"topview button[ref=logout]" : {
				click: function(btn){
					var dis=Ext.getCmp("displaylogin");
					dis.setValue("<font color=white><b>未登录</b></font>");
								
					Ext.util.Cookies.clear("autologin");
					Ext.create("core.app.view.LoginWindow").show();
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
			onTreepanelSelect:function(selModel,record,index,options){
			   var mainpanel=this.getMainPanel();
			   var newTab=mainpanel.items.findBy(
			    function(tab){return tab.title===record.get("text");}
			   );
			   
			   if(!newTab){
			   	/*var className=record.raw.controllerName;
			   	alert(className);
			   	var tempClass=Ext.ClassManager.get(className);
			   	if(tempClass===undefined){
			   	 alert(className+"没有定义");
			   	}*/
			   	//this.application.getController(record.raw.controllerName).init(this.application);
			    newTab=mainpanel.add({
			      xtype:record.raw.className,
			      closable:true,
			      iconCls:record.get("iconCls"),
			      title:record.get("text")
			      
			    });
			   }
			   mainpanel.setActiveTab(newTab);
			   
			},
			onTreepanelItemClick:function(view,record,item,index,event,options){
			this.onTreepanelSelect(view,record,index,options);
			},
	views : [
		"core.app.view.TopView",
		"core.app.view.WestView",
		"core.app.view.CenterView",
		"core.app.view.MainViewLayout"
	],
	stores : ["core.app.store.Menu"],
	models : []
});