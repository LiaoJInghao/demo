﻿Ext.define('Admin.view.contract.ContractPanel', {
	extend: 'Ext.panel.Panel',
	xtype: 'contractPanel',
	requires: [
		'Ext.grid.Panel',
		'Ext.toolbar.Paging',
		'Ext.form.field.ComboBox',
		'Ext.selection.CheckboxModel',
		'Ext.form.field.Date',
		'Ext.grid.column.Date'
	],
	layout: 'fit',
	items: [{
		xtype: 'gridpanel',
		title: 'ContractGrid Results',
		//routeId: 'user',
		bind: '{contractLists}',
		scrollable: false,
		selModel: {type: 'checkboxmodel'},
		columns: [
			 {header: 'id',dataIndex:'id',width: 60,sortable: true,hidden:true}
			,{header: 'processStatus',dataIndex: 'processStatus',width: 60,sortable: true,
	            renderer: function(val) {
		            if (val =='NEW') {
			            return '<span style="color:green;">新建</span>';
			        } else if (val =='APPROVAL') {
			            return '<span style="color:blue;">审批中...</span>';
			        } else if (val =='COMPLETE') {
			            return '<span style="color:orange;">完成审批</span>';
			        }else{
			        	return '<span style="color:red;">取消申请</span>';
			        }
			        return val;
	            }
			}
			,{header: 'userId',dataIndex: 'userId',width: 60,sortable: true}
			,{header: 'contractType',dataIndex: 'contractType',width: 120,sortable: true}
			,{xtype: 'actioncolumn',cls: 'content-column', width: 120,text: 'Actions',tooltip: 'edit ',
				items: [
					{xtype: 'button', iconCls: 'x-fa fa-pencil',handler: 'openEditWindow'},
					{xtype: 'button',iconCls: 'x-fa fa-close',handler: 'deleteOneRow'},
					{
		                xtype: 'button',iconCls: 'x-fa fa-star',tooltip: '发起审批',
		                getClass: function(v, meta, rec) {
		                    if (rec.get('processInstanceId')!="") {
		                        return 'x-hidden';
		                    }
		                    return 'x-fa fa-star';
		                },
		                handler: 'starContractProcess'
		            },{
		                xtype: 'button',iconCls: 'x-fa fa-ban',tooltip: '取消审批',
		                getClass: function(v, meta, rec) {
		                    if (rec.get('processInstanceId')=="") {
		                        return 'x-hidden';
		                    }
		                    return 'x-fa fa-ban';
		                },
		                handler: 'cancelContractProcess'
		            }
				]
			}
		],
		tbar: [/*{
			xtype: 'combobox',
			reference:'searchFieldName',
			hideLabel: true,
			store:Ext.create("Ext.data.Store", {
				fields: ["name", "value"],
				data: [{ name: '请假时间', value: 'leaveTime' }]
			}),
			displayField: 'name',
			valueField:'value',
			value:'leaveTime',
			editable: false,
			queryMode: 'local',
			triggerAction: 'all',
			emptyText: 'Select a state...',
			width: 135,
			listeners:{
				select: 'searchComboboxSelectChuang'
			}
		}, '-',{
			xtype: 'datefield',
			hideLabel: true,
			//hidden:true,
			format: 'Y/m/d H:i:s',
			reference:'searchDataFieldValue',
			fieldLabel: 'From',
			name: 'from_date'
		}, {
			xtype: 'datefield',
			hideLabel: true,
			//hidden:true,
			format: 'Y/m/d H:i:s',
			reference:'searchDataFieldValue2',
			fieldLabel: 'To',
			name: 'to_date'
		},'-',{
			text: 'Search',
			iconCls: 'fa fa-search',
			handler: 'quickSearch'
		}, '-',{
			text: 'Search More',
			iconCls: 'fa fa-search-plus',
			handler: 'openSearchWindow'	
		}, '->',*/{
			text: 'Add',
			tooltip: 'Add a new row',
			iconCls: 'fa fa-plus',
			handler: 'openAddWindow'	
		},'-',{
			text: 'Removes',
			tooltip: 'Remove the selected item',
			iconCls:'fa fa-trash',
			itemId: 'leaveGridPanelRemove',
			disabled: true,
			handler: 'deleteMoreRows'	
		}],			
		dockedItems: [{
			xtype: 'pagingtoolbar',
			dock: 'bottom',
			displayInfo: true,
			bind: '{contractLists}'
		}],
		listeners: {
			selectionchange: function(selModel, selections){
				this.down('#leaveGridPanelRemove').setDisabled(selections.length === 0);
			}
		}		
	}]
});