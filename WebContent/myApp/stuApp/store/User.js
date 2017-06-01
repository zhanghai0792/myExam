Ext.define('core.store.User', {
    extend: 'core.store.StoreTemplate',

    requires: [
        'core.store.StoreTemplate',
        'Packet.proxy.Myproxy','core.model.User'
    ],

    model: 'core.model.User',

    autoLoad: true,

    storeId: 'user',

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