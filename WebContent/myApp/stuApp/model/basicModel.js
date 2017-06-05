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
 /*constructor:function(){
  var me=this;
  console.dir(me.fields);
   if(me.fields){
    me.fields=me.fields.concat(me.buildFields());
   }
    console.dir(me.fields);
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
}*/
 fields:[{
  name:'id',type:'int'
 },
 {
  name:'createTime',type:'date',dateFormat:"Y-m-j H:i:s"
 }]
})