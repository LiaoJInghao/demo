﻿Ext.define('Admin.view.contractapprove.ContractApproveViewController', {
    extend: Ext.app.ViewController,
    alias: 'controller.contractApproveViewController',
    //1.签收任务
    onClickLeaveApproveClaimButton: function(view, recIndex, cellIndex, item, e, record) {
        Ext.Ajax.request({
            url: 'contract/claim/' + record.get('taskId'),
            method: 'post',
            success: function(response, options) {
                var json = Ext.util.JSON.decode(response.responseText);
                if (json.success) {
                    Ext.Msg.alert('操作成功', json.msg, function() {
                        view.getStore().reload();
                    });
                } else {
                    Ext.Msg.alert('操作失败', json.msg);
                }
            }
        });
    },
    //2.创建审批表单（并绑定Task id）
    setCurrentView: function(view, form, title) {
		var cfg = Ext.apply({
			xtype: 'contractApproveWindow',
			items: [{xtype: form}]
		},{
			title: title
		});
		var win = Ext.widget(cfg);
        view.up('panel').up('container').add(win);
        return win;
    },
    onClickLeaveApproveCompleteWindowButton: function(view, recIndex, cellIndex, item, e, record) {
    	//选中点击的行
        var taskDefinitionKey = record.get('taskDefinitionKey');
        if (taskDefinitionKey == 'deptLeaderAudit') {
            //店长审批
            var win = this.setCurrentView(view,taskDefinitionKey, '店长审批');
            win.down('form').getForm().loadRecord(record);
        } 
        else if (taskDefinitionKey == 'managerAudit') {
        	//经理审批
        	var win = this.setCurrentView(view,taskDefinitionKey,'经理审批');
        	win.down('form').getForm().loadRecord(record);
        }
        else if (taskDefinitionKey == 'modifyApply') {
        	//申请人调整申请：可以编写到工具类中
        	var win = this.setCurrentView(view,taskDefinitionKey,'调整申请表单');
        	win.down('form').getForm().loadRecord(record);
        }
    },
    //3.封装审批表单数据,并以Ajax提交到后台完成任务的流程变量封装对象中。
	complete: function(url, variables,form){
		// 转换JSON为字符串
	    var keys = "", values = "", types = "";
		if (variables) {
			Ext.each(variables, function (item) {
				if (keys != "") {
					keys += ",";
					values += ",";
					types += ",";
				}
				keys += item.key;
				values += item.value;
				types += item.type;
            });
		}
		Ext.Ajax.request({
            url: url,
            method: 'post',
            params : { 
			 	keys: keys,
		        values: values,
		        types: types
			}, 
            success: function(response, options) {
                var json = Ext.util.JSON.decode(response.responseText);
                if (json.success) {
                    Ext.Msg.alert('操作成功', json.msg, function() {
                    	form.up('window').close();
                        //grid.getStore().reload();
                        Ext.data.StoreManager.lookup('contractApproveStore').load();
                    });
                } else {
                    Ext.Msg.alert('操作失败', json.msg);
                }
            }
        });
	},
	//店长审批
    onClickDeptleaderAuditFormSubmitButton: function(btn) {
    	var form = btn.up('form');
    	var values = form.getValues();
    	var url = 'contract/complete/' + values.taskId;
    	var variables = [{
			key: 'deptLeaderPass',
			value: values.deptLeaderPass,//获取表单选择的value
			type: 'B'
		},{
			key: 'deptLeaderBackReason',
			value: values.deptLeaderBackReason,//获取表单选择的value
			type: 'S'
		}];
        this.complete(url,variables,form);
    },
    //经理审批
    onClickHrAuditFormSubmitButton: function(btn) {
        var form = btn.up('form');
    	var values = form.getValues();
    	var url = 'contract/complete/' + values.taskId;
    	var variables = [{
			key: 'manLeaderPass',
			value: values.manLeaderPass,//获取表单选择的value
			type: 'B'
		},{
			key: 'hrBackReason',
			value: values.hrBackReason,//获取表单选择的value
			type: 'S'
		}];
        this.complete(url,variables,form);
    },
    //调整申请
    onClickModifyApplyFormSubmitButton: function(btn) {
        var form = btn.up('form');
    	var values = form.getValues();
    	var url = 'contract/complete/' + values.taskId;
    	var variables = [{
			key: 'reApply',
			value: values.reApply,//获取表单选择的value
			type: 'B'
		},{
			key: 'leaveType',
			value: values.leaveType,//获取表单选择的value
			type: 'S'
		},{
			key: 'startTime',
			value: values.startTime,//获取表单选择的value
			type: 'D'
		},{
			key: 'endTime',
			value: values.endTime,//获取表单选择的value
			type: 'D'
		},{
			key: 'reason',
			value: values.reason,//获取表单选择的value
			type: 'S'
		}];
        this.complete(url,variables,form);
    },
    //流程跟踪
    onClickGraphTraceButton : function(btn) {
        alert("on Click Add Button!");
    }
});