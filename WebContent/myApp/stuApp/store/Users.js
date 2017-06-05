Ext.define('core.store.Users', {
    extend: 'core.store.StoreTemplate',

    requires: [
        'core.store.StoreTemplate',
        'core.proxy.Myproxy','core.model.User'
    ],

    model: 'core.model.User',

    autoLoad: true,

    storeId: 'users',

    proxy: {
        type: 'myajaxproxy',
        extraParams: {
            entity: 'User'
        },
        	 api: {
        read    : 'user/get',
        create  : 'user/importStudents',
        update  : 'user/updateNoNull',
        destroy : 'user/deletes'
    }
    }
});