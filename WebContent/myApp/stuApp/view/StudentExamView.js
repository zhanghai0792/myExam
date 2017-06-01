Ext.define("core.view.StudentExamView",{
extend:'Ext.panel.Panel',
alias: 'widget.studentexamview',
title:'学生考试界面',
closable:true,
layout: 'card',
items:[
{xtype:'panel',html:'<center><span style="font-size:90px">当前尚未开考</span></center>'},
{xtype:"form",
     bodyPadding: 5,
     waitTitle:'上传中……',
    items: [
                {
                    xtype: 'displayfield',
                    fieldLabel: '考生姓名',
                    name:'stuName',
                    value: ''
                },
                 {
                    xtype: 'displayfield',
                    fieldLabel: '考试科目',
                    name:'courseName'
                },
                {
                    xtype: 'container',
                    layout: {
                        type: 'hbox',
                        align: 'stretch'
                    },
                    items: [
                        {
                            xtype: 'label',
                            flex: 0,
                            text: '下载试题'
                        },
                        {
                            xtype: 'button',
                            text: '下载',
                            itemId:'down'
                        },
                        {
                            xtype: 'tbspacer',
                            flex: 1
                        }
                    ]
                },
                {
                    xtype: 'filefield',
                    fieldLabel: '上传答案',
                    name:'mFile'
                },
                {
                    xtype: 'hiddenfield',
                    fieldLabel: 'Label',
                    name: 'id'
                }/*,
                {
                    xtype: 'hiddenfield',
                    fieldLabel: 'Label',
                    name: 'courseExamFile_id'
                }*/
            ],
         buttons:[{text:'提交答案',itemId:'upload'}],
         buttonAlign:'center'
    }
]
})