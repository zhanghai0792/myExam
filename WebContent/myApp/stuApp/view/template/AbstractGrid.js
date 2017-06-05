Ext.define("core.view.template.AbstractGrid", {
			extend : 'Ext.grid.Panel',
			alias : 'widget.gridtemplate',
			columnLines : true,
			viewConfig : {
				stripeRows : true
			},
			initComponent : function() {
				var me = this;
				/*me.selModel = {
					selType : 'cellmodel'
				};*/
				//me.selModel={selType: 'rowmodel'};
				  me.selType="checkboxmodel";
                me.selModel={ checkOnly: false, mode: "MULTI" };
				
				
			/*	me.plugins = [Ext.create("Ext.grid.plugin.CellEditing", {
							clicksToEdit : 2,
							pluginId : 'cellplugin'
						})];*/
			/*	me.features = [Ext.create("Ext.ux.grid.FiltersFeature", {
							local : 'true'
						})];*/
							 if(userType==1){//只有教师才能显示
					  if(!me.dockedItems){
					   me.dockedItems=[{xtype : 'toolbar',
							dock : 'top',
							itemId : "topToolbar",
							items:[]
						 }];
					  }		 	
							 	
						if(me.dockedItems){
						 for(index in me.dockedItems){
						   if(me.dockedItems[index].dock&&me.dockedItems[index].dock=="top"){
						   me.dockedItems[index].items= me.dockedItems[index].items.concat(me.initTop());
						   break;
						   }
						 }
						}
						
						}	 	
					
				me.callParent(arguments);
			},
			initTop:function(){
			return [{ xtype : 'button',
					 itemId : 'add',
					 text : '添加'}, {
										xtype : 'tbseparator'
									}, {
										xtype : 'button',
										itemId : 'delete',
										text : '删除'
									}, {
										xtype : 'tbseparator'
									}
									];
			}
		});