Ext.define("core.store.StoreTemplate", {
			extend : 'Ext.data.Store',
			alias : 'store.storeTemplate',
			requires:["core.util.Alert","core.util.Util","core.proxy.Myproxy"],
			/*proxy : {
				type : 'ajax',
				reader : {
					type : 'json',
					root : 'datas'
				},
				writer : {
					type : 'json',
					allowSingle : false,
					dateFormat : 'Y-m-d'
				}
			},*/
			constructor:function(cfg){
				
				var me=this;
				//console.dir(me);
				if(me.proxy){
				 Ext.apply(me.proxy,me.buildProxy());
				}else{
				 me.proxy=me.buildProxy();
				}
				this.callParent(arguments);
			},
			buildProxy : function() {
				return {
			 type:"myajaxproxy"
			};
			},
			listeners:{
			load:function(me, records, successful, eOpts ){
			 /*console.log(records.length);
			 alert(me.storeId);*/
			}
			
			}
			/*,
			listeners : {
				exception : function(proxy, response, operation, eOpts) {
					requestMessageProcessor(proxy, response);
				}
			},
			requestMessageProcessor : function(proxy, response) {
				if (response && proxy) {
					try {
						var responseData = proxy.reader
								.getResponseData(response);

						if (responseData.msg) {
							var messageDescription = 'Information'; // title
							// of
							// the
							// alert
							// box
							var messageIcon = Ext.MessageBox.INFO;

							if (!responseData.success) {
								var messageDescription = 'Error';
								var messageIcon = Ext.MessageBox.ERROR;
							}

							Ext.MessageBox.show({
										title : messageDescription,
										msg : responseData.msg,
										buttons : Ext.MessageBox.OK,
										icon : messageIcon
									});
						}
					} catch (err) {
						// Malformed response most likely
						console.log(err);
					}
				}
			},
            mySync:function(){
             var me=this;
             me.sync({
             success:function(batch,options){
             	console.dir(batch);
             	console.dir(options);
             	var deal=batch.operations[0].action;
             	
             	 create  : undefined,
    read    : undefined,
    update  : undefined,
    destroy : undefined
             	 * 
             	var dealMsg="处理成功";
             	if(deal=="destory"){
             	dealMsg="删除成功";}
             	if(deal==="create"){
             	dealMsg="添加成功";}
             	if(deal==="update"){
             	dealMsg="更新成功";}
             	var num=batch.operations[0].request.scope.reader.jsonData["total"];
             	var msg=batch.operations[0].request.scope.reader.jsonData["msg"]
             	Packet.util.Alert.msg(dealMsg, msg+"<br>处理成功["+num+"]条记录");
             },
             failure:function(batch,options){
             	console.dir(batch);
             	console.dir(options);
             	Packet.util.Util.showErrorMsg("错误提示:&nbsp;&nbsp;["+batch.operations[0].request.scope.reader.jsonData["msg"]+"]");
              me.rejectChanges();
             }
             });
            }*/
		})