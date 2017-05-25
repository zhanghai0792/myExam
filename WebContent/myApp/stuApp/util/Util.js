Ext.define("core.util.Util", {
	requires :["core.util.Alert"],
			statics : {
				decodeJSON : function(text) {
					var result = Ext.decode(text, true);
					if (!result) {
						result = {};
						result.success = false;
						result.msg = conn.responseText;
					}
					return result;
				},
				showErrorMsg : function(text) {

					Ext.Msg.show({
								title : '错误',
								msg : text,
								icon : Ext.Msg.ERROR,
								buttons : Ext.Msg.OK
							});
				},
				//url访问路径,datas参数用obj对象表示,isJsonFormate是否json格式数据传递
				//component添加提交提示的组件
				//funScope回调函数的作用域；sucessFN返回成功的处理,failFn失败处理
				ajax:function(url,datas,isJsonFormate,component,funScope,successFn,failFn){
					 var maskComp;
				
				var arequest={
				"url":url,
				method : 'POST',
								failure : function(conn, response, options,eOpts) {
									if(maskComp)
									  maskComp.unmask();
									Ext.Msg.show({
												title : '<font color=red>错误<font>',
												msg : conn.responseText,
												icon : Ext.Msg.ERROR,
												buttons : Ext.Msg.OK												
											});
								},
								success : function(conn, response, options,
										eOpts) {
									if(maskComp)
									  maskComp.unmask();
									  var result=core.util.Util.decodeJSON(conn.responseText);
									if (result.success) {
										core.util.Alert.msg('处理成功',result.msg);
										if(successFn){
												       if(funScope){
												         successFn.call(funScope,result);
												       }
												     }
										
									} else {
										 if(!failFn){//没有错误处理参数
										Ext.Msg.show({
													title : '失败',
													msg : result.msg,
													icon : Ext.Msg.ERROR,
													buttons : Ext.Msg.OK	
												}
												);}else{
												//用提供的函数处理
												       if(funScope){
												         failFn.call(funScope,result);
												       }
												}
									}

								}
				};
				 if(isJsonFormate){
				 	 if(datas)
				 arequest["jsonData"]=datas;
				 }else{
				 	 if(datas)
				 	arequest["params"]=datas;
				 }
					 if(component){
					maskComp=component.getEl();
					 maskComp.mask("提交处理中……");
					}
				Ext.Ajax.request(arequest);
				}

			}

		})