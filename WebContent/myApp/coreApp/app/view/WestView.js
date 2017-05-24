/**
 * 宸﹁竟閮ㄥ垎
 */
Ext.define("core.app.view.WestView",{
	extend: 'Ext.tree.Panel',
	alias: 'widget.westview',
	collapsible: true,
	split: true,
	defaults: {
		bodyStyle: 'padding:2px'
	}, 
	rootVisible: false,
	border:1,
	margins: '2 2 0 0',
	width: 225,
	minSize: 100,
	maxSize: 250,
	title:"操作菜单",
 	store:"core.app.store.Menu",
    initComponent: function(){
    	var me=this;
    	this.callParent(arguments);
        console.log("调用构造方法");
    }
});



