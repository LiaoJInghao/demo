Ext.define('Admin.view.contract.Contract', {
    extend: 'Ext.container.Container',
    xtype: 'contract',
    controller: 'contractViewController',
    viewModel: {type: 'contractViewModel'},

    layout: 'fit',
    items: [{xtype:'contractPanel'}]
});
