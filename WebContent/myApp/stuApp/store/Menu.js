Ext.define("core.store.Menu",{
	extend : "Ext.data.TreeStore",
	autoLoad:true,
	proxy : {  
                type : 'ajax',  
                url : 'user/getMenus',//请求  
                reader : {  
                    type : 'json',
                    root : 'datas'//数据  
                } 
            },  
        root : {  
            text : '管理菜单',  
            expanded : true           
        }
	});