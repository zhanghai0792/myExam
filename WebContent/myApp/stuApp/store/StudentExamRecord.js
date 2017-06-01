Ext.define('core.store.StudentExamRecord', {
    extend: 'Ext.data.Store',

    requires: [
        'core.store.StoreTemplate',
        'core.proxy.Myproxy','core.model.StudentExamRecord'
    ],

    model: 'core.model.StudentExamRecord',

  autoLoad: true,

  storeId: 'studentexamrecord',

    proxy: {
        type: 'myajaxproxy',
        extraParams: {
            entity: 'StudentExamRecord'
        },
        	 api: {
        read    : 'studentExamRecord/getStudentExamInfo',
        create  : 'studentExamRecord/adds',
        update  : 'studentExamRecord/updateNoNull',
        destroy : 'studentExamRecord/deletes'
    }
    }
});