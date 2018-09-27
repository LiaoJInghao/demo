Ext.define('Admin.model.contract.ContractModel', {
	extend: 'Admin.model.Base',
	requires: [
		'Ext.data.proxy.Rest'
	],
	fields: [
		{type:'int',name:'id'}
		,{type:'string',name:'userId'}
		,{type:'string',name:'contractType'}
		,{type:'string',name:'processStatus'}
		,{type:'string',name:'processInstanceId'}
	],
	proxy: {
		type: 'rest',
		url: '/contract',
	}
});
