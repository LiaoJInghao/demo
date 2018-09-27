Ext.define('Admin.view.contractapprove.ContractApprove', {
    extend: 'Ext.panel.Panel',
    xtype: 'contractApprove',
	layout:'fit',
    margin: '20 20 20 20',

	controller: 'contractApproveViewController',
    viewModel : { type: 'contractApproveViewModel'},

	items: [{xtype:'contractApprovePanel'}]	//需要修改
});