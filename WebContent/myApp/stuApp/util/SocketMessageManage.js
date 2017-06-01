Ext.define("core.util.SocketMessageManage",{
	mixins: {
		observable: 'Ext.util.Observable'
	} ,
	  requires:["core.util.WebSocket"],
      singleton:true,
      webSocket:null,
      isRun:false,//webSocket是否开启了
      start:function(){
       var me=this;
       if(!me.webSocket){
        me.webSocket=Ext.create ('core.util.WebSocket',{
        listeners: {
				open: function (ws) {
					me.isRun=true;
					console.log("开启了websocket");
				} ,
				message: function (ws, data) {
					/*
					 if(message.eventName){
					  me.fire(message.eventName,message);
					 }else{
					   console.log(data);
					  Ext.Error.raise ('错误的消息:'+data);
					 }*/
					 me.fireEvent("message",me,data)
				} ,
				close: function (ws) {
					
				}
			}
        });
       }
       return me;
      },
      stop:function(){
      	var me=this;
      	 if(me.webSocket)
      	   me.webSocket.close();
      },
      constructor: function () {
      	var me=this;
		/*if(Ext.isEmpty(me.webSocket)){
		 me.webSocket=Ext.create ('core.util.WebSocket', {
			url: socketUrl ,
			protocol:"ws",
			listeners: {
				open: function (ws) {
					me.isRun=true;
					console.log("开启了websocket");
				} ,
				message: function (ws, data) {
					var messsage=Ext.decode(data);
					 if(message.eventName){
					  me.fire(message.eventName,message);
					 }else{
					   console.log(data);
					  Ext.Error.raise ('错误的消息:'+data);
					 }
				} ,
				close: function (ws) {
					
				}
			}
		});
		
		}*/
//me.initConfig (cfg);
		me.mixins.observable.constructor.call (me, arguments);
		
		me.addEvents (
			'open' ,
			'error' ,
			'close' ,
			'message'
		);
		return me;
	} 
    
})