Ext.define("core.view.user.UsersImport",{
extend:'Ext.window.Window',
alias : 'widget.usersimport',
title:'导入考生信息',
width:300,
items:[
{xtype:'form',
 bodyPadding: 5,
  defaultType: 'textfield',
 items:[
 {
  fieldLabel: '请选择班级',
        name: 'className',
        xtype:"combo",
        itemId:'classSelect',
       /* store: "core.store.ClassInfo",*/
        store: "ClassInfos",
    queryMode: 'local',
    displayField: 'className',
    valueField: 'className'
 },
 {
  xtype:"hidden",
        name: 'class_id'
 },
 {fieldLabel: '请选择学生名单',
  xtype:"filefield",
        name: 'studentsFile'
 }
 
 ],
 buttons:[{text:'添加',itemId:'studentsImport'},
 	      {text:'取消',itemId:'studentsCancel'}],
buttonAlign:'center'
}
]
})