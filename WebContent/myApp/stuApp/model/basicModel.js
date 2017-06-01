Ext.define("core.model.basicModel",{
 extend:'Ext.data.Model',
 /*fields:[
 {
  name:'id',type:'int'
 },
 {
  name:'createTime',type:'date',dateFormat:"Y-m-j H:i:s"
 }
 ],*/
 idProperty:'id',
 constructor:function(cfg){
  var me=this;
   if(me.fields){
    me.fields=me.fields.concat(me.buildFields);
   // delete cfg["fields"];
   }
   console.dir(me);
   //Ext.apply(me,cfg);
   me.callParent(arguments);
 },
 buildFields : function() {
				return [
 {
  name:'id',type:'int'
 },
 {
  name:'createTime',type:'date',dateFormat:"Y-m-j H:i:s"
 }
 ];
}
})