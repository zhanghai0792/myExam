Ext.define("core.proxy.Myproxy", {
			extend : 'Ext.data.proxy.Ajax',
			alias : 'proxy.myajaxproxy',
			// type : 'ajax',
			actionMethods : {
				create : 'POST',
				read : 'POST',
				update : 'POST',
				destroy : 'POST'
			},
			reader : {
				type : 'json',
				root : 'datas',
				idProperty :'id',
				successProperty:"success",
				totalProperty :'total'
			},
			writer : {
				type : 'json',
				allowSingle : false,
				dateFormat : 'Y-m-d H:i:s'
				/*
				 * encode:true, root:"datas"
				 */
			}/*,
			listeners : {
				exception : function(proxy, response, operation, eOpts) {
					console.dir(operation);
					var deal = operation.action;
					var dealMsg = "处理失败";
					if (deal == "destory") {
						dealMsg = "删除失败";
					}
					if (deal === "create") {
						dealMsg = "添加失败";
					}
					if (deal === "update") {
						dealMsg = "更新失败";
					}
					Ext.Msg.show({
								title : dealMsg,
								msg : operation.request.scope.reader.jsonData["msg"],
								icon : Ext.Msg.ERROR,
								buttons : Ext.Msg.OK
							});
				}
			}*/
		})