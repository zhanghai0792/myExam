Ext.define('core.store.ClassInfo', {
    extend: 'core.store.StoreTemplate',

    requires: [
        'core.store.StoreTemplate',
        'core.proxy.Myproxy','core.model.ClassInfo'
    ],

    model: 'core.model.ClassInfo',

    autoLoad: true,

    storeId: 'classinfo',

    proxy: {
        type: 'myajaxproxy',
        extraParams: {
            entity: 'ClassInfo'
        },
        	 api: {
        read    : 'classInfo/get',
        create  : 'classInfo/importStudents',
        update  : 'classInfo/updates',
        destroy : 'classInfo/deletes'
    }
    }
});