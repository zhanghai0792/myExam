Ext.define("core.controller.template.AbstractController", {
	extend : 'Ext.app.Controller',
	requires : ["core.util.Util"],
	stores : [],
	views : [],
	init : function(application) {
		var me=this;
		
		
		this.control({
					"staticdatagrid" : {
						render : this.render,
						edit : this.onEdit
					},
					"staticdatagrid button#add" : {
						click : this.onButtonClickAdd
					},
					"staticdatagrid button#save" : {
						click : this.onButtonClickSave
					},
					"staticdatagrid button#cancel" : {
						click : this.onButtonClickCancel
					},
					"staticdatagrid button#clearFilter" : {
						click : this.onButtonClickClearFilter
					},
					"staticdatagrid actioncolumn" : {
						itemclick : this.handleActionColumn
					},
					"citiesgrid button[itemId=clearGrouping]": {
                toggle: this.onButtonToggleClearGrouping
            }

				});
				
				/*this.listen({
					store : {
						'#staticDataAbstract' : {
							write : this.onStoreSync
						}
                      '*' : {
							write : this.onStoreSync
						}
					}

				});*/

				
				/* if (!Ext.getStore('countries')) {
            Ext.create('Packet.store.staticData.Countries');
        }

        if (!Ext.getStore('languages')) {
            Ext.create('Packet.store.staticData.Languages').load();
        }

        if (!Ext.getStore('actors')) {
            Ext.create('Packet.store.staticData.Actors');
        }

        if (!Ext.getStore('categories')) {
            Ext.create('Packet.store.staticData.Categories');
        }

				
				
				me.hasInit=true;
				}*/

	},

	render : function(component, options) {
		component.getStore().load();
		 if (component.xtype === 'citiesgrid' && component.features.length > 0){
            if (component.features[0].ftype === 'grouping'){
                component.down('toolbar#topToolbar').add([
                    {
                        xtype: 'tbseparator'
                    },
                    {
                        xtype: 'button',
                        itemId: 'clearGrouping',
                        text: 'Group by Country: ON',
                        iconCls: 'grouping',
                        enableToggle: true,
                        pressed: true
                    }
                ]);
            }
        }     
	},
	onButtonClickAdd : function(button, e, options) {
		var grid = button.up("staticdatagrid");
		store = grid.getStore();
		var modelName = store.getProxy().getModel().modelName;
		var cellEditing = grid.getPlugin("cellplugin");
		store.insert(0, Ext.create(modelName, {
							lastUpdate : new Date()
						}));
		cellEditing.startEditByPosition({
					row : 0,
					column : 1
				});
	},
	onButtonClickSave : function(button, e, options) {
		button.up("staticdatagrid").getStore().mySync();
	},
	onEdit : function(editor, context, options) {
		context.record.set("lastUpdate", new Date());
	},
	handleActionColumn : function(column, action, view, rowIndex, colIndex,
			item, e) {
		var store = column.up("staticdatagrid").getStore();
		rec = store.getAt(rowIndex);
		if (action == "delete") {
			store.remove(rec);
			Ext.Msg.alert("删除", "请单击[保存]按钮让删除生效！")
		}
	},
	onButtonClickCancel : function(button, e, options) {
		button.up("staticdatagrid").getStore().reload();
	},
	onButtonClickClearFilter : function(button, e, options) {
		button.up('staticdatagrid').filters.clearFilters();
	},
/*onStoreSync : function(store, operation, options) {
		var deal = operation.action;
		var dealMsg = "处理成功";
		if (deal == "destory") {
			dealMsg = "删除成功";
		}
		if (deal === "create") {
			dealMsg = "添加成功";
		}
		if (deal === "update") {
			dealMsg = "更新成功";
		}
		var num = operation.request.scope.reader.jsonData["total"];
		var msg = operation.request.scope.reader.jsonData["msg"]
		Packet.util.Alert.msg(dealMsg, msg + "<br>处理成功[" + num + "]条记录");
	},*/
	

    onButtonToggleClearGrouping: function (button, pressed, options) {

        var store = button.up('citiesgrid').getStore();

        if (pressed){
            button.setText('Group by Country: ON');
            store.group('country_id');
        } else {
            button.setText('Group by Country: OFF');
            store.clearGrouping();
        }
    }

})