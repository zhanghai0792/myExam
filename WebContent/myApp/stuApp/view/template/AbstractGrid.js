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
				me.dockedItems = [{
							xtype : 'toolbar',
							dock : 'top',
							itemId : "topToolbar",
							items : [{
										xtype : 'button',
										itemId : 'add',
										text : '添加',/*,
                                         
										iconCls : 'add'*/
										handler:function(){
										 var me=this;
										/*alert(me.up("grid").getStore().count());
										var store=me.up("grid").getStore();
										alert(store.storeId);
										alert(Ext.getStore("studentexamrecord").count());
										*/}
									}, {
										xtype : 'tbseparator'
									}, {
										xtype : 'button',
										itemId : 'delete',
										text : '删除'/*,
										iconCls : 'save_all'*/
									}, {
										xtype : 'tbseparator'
									}/*, {
										xtype : 'button',
										itemId : 'cancel',
										text : '刷新',
										iconCls : 'cancel'
									}, *//*{
										xtype : 'tbseparator'
									}, {
										xtype : 'button',
										itemId : 'clearFilter',
										text : '清除查询条件',
										iconCls : 'clear_filter'
									}*/]
						}

				];
}
				/*me.columns = Ext.Array.merge(me.columns, [{
									xtype : "datecolumn",
									text : '修改时间',
									flex:1,
									dataIndex : 'lastUpdate',
									format : "Y-m-j H:i:s",
									filter : true
								}, {
									xtype : 'actioncolumn',
									width : 30,
									sortable : false,
									menuDisabled : true,
									items : [{
										iconCls:'delete',
										tooltip:"删除",
										handler : function(view, rowIndex,
												colIndex, item, e) {
											this.fireEvent("itemclick", this,
													'delete', view, rowIndex,
													colIndex, item, e);
										}
									}]
								}]);*/

				me.callParent(arguments);
			}
		});