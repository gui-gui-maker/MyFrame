<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	CurrentSessionUser user = SecurityUtil.getSecurityUser();
	String orgId = user.getUnit().getId();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@include file="/k/kui-base-list.jsp"%>
    <title>证书/报告授权签字人</title>
    <script type="text/javascript">
    var qmUserConfig = {
    		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
    		sp_fields:[{name:"employee_name",compare: "like"},
		    	       {name:"device_type_id",compare: "="}],
		    	       
    		tbar: [ {text: '详情', id: 'detail', icon: 'detail', click: detail}, "-", 
	    			{text: '添加', id: 'add', icon: 'add', click: add}, "-", 
	    			{text: '修改', id: 'edit', icon: 'modify', click: edit}, "-", 
	    			/* {text: '关联报告类型', id: 'refer', icon: 'same', click: refer}, "-", 
	    			{text: '查看关联报告', id: 'lookRefer', icon: 'search', click: lookRefer}, "-",  */
	    			{text: '启用替代人', id: 'accept', icon: 'accept', click: accept}, "-", 
	    			{text: '停用替代人', id: 'forbid', icon: 'forbid', click: forbid}, "-", 
	    			{text: '启用', id: 'start', icon: 'accept', click: start}, "-", 
	    			{text: '停用', id: 'stop', icon: 'forbid', click: stop}, "-", 
	    			{text: '修改规则', id: 'rule', icon: 'modify', click: rule}, "-", 
	    			{text: '删除', id: 'del', icon: 'delete', click: del}/* , "-", 
	    			{text: '同单位优先', id: 'toOne', icon: 'lock', click: toOne}, "-", 
	    			{text: '量最少优先', id: 'toLeast', icon: 'lock', click: toLeast} */],
	    			
    		listeners: {
    			rowClick: function(rowData, rowIndex) {
    			},
    			rowDblClick: function(rowData, rowIndex) {
    				detail();
    			},
    			selectionChange: function(rowData, rowIndex) {
    				var count = Qm.getSelectedCount();
    				Qm.setTbar({
    					detail: count==1,
    					edit: count==1,
    				/* 	refer:count==1,
    					lookRefer:count==1, */
    					start:count>0,
    					stop:count>0,
    					accept:count>0,
    					forbid:count>0,
    					/* toOne:count>0,
    					toLeast:count>0, */
    					del: count>0
    				});
    			},
    			
    	        rowAttrRender : function(rowData, rowid) {
                  /*   var fontColor="black";
                    return "style='color:"+fontColor+"'"; */
                }
    		}
    	};
    function add() {
		top.$.dialog({
			width: 900,
			height: 650,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "添加",
			content: 'url:app/approve/certificate_by_detail.jsp?pageStatus=add'
		});
	}
    function del() {
    	$.del(kFrameConfig.DEL_MSG, "certificateByAction/delete.do", {
			"ids": Qm.getValuesByName("id").toString()
		});
	}
    function detail() {
    	var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 900,
			height: 650,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "添加",
			content: 'url:app/approve/certificate_by_detail.jsp?id=' + id + '&pageStatus=detail'
		});
	}
    function edit() {
    	var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 900,
			height: 650,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "添加",
			content: 'url:app/approve/certificate_by_detail.jsp?id=' + id + '&pageStatus=modify'
		});
	}
    function refer() {
    	/* var isMayRefer = Qm.getValueByName("is_specific");
    	if(isMayRefer=='否'){
    		$.ligerDialog.alert('不能关联具体报告！');
    		return;
    	} */
    	var id = Qm.getValueByName("id");
		top.$.dialog({
			width:900,
			height: 650,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "添加",
			content: 'url:app/approve/certificate_refer_report.jsp?id=' + id + '&pageStatus=modify'
		});
	}
    function lookRefer(){
    	/* var isMayRefer = Qm.getValueByName("is_specific");
    	if(isMayRefer=='否'){
    		$.ligerDialog.alert('该记录不关联具体报告！');
    		return;
    	} */
    	var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 900,
			height: 650,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "添加",
			content: 'url:app/approve/certificate_report_list.jsp?id=' + id 
		});
    }
    function start() {
    	var status = Qm.getValuesByName("status").toString();
    	if(status.indexOf('启用')!=-1){
    		$.ligerDialog.alert('所选条目中包含已启用数据，请重新选择！');
    		return;
    	}
    	var ids = Qm.getValuesByName("id");
    	$.ligerDialog.confirm("确定要启用？", function(yes) {
			if (yes) {
				$.ajax({
					url : "certificateByAction/start.do?ids=" + ids,
					type : "post",
					async : false,
					success : function(data) {
						if (data.success) {
							top.$.notice("启用成功！");
							Qm.refreshGrid();
						} else {
							top.$.notice("启用失败！" + data.msg);
						}
					}
				});
			}
		});
	}
    function stop() {
    	var status = Qm.getValuesByName("status").toString();
    	if(status.indexOf('停用')!=-1){
    		$.ligerDialog.alert('所选条目中包含已停用数据，请重新选择！');
    		return;
    	}
    	var ids = Qm.getValuesByName("id");
    	$.ligerDialog.confirm("确定要停用？", function(yes) {
			if (yes) {
				$.ajax({
					url : "certificateByAction/stop.do?ids=" + ids,
					type : "post",
					async : false,
					success : function(data) {
						if (data.success) {
							top.$.notice("停用成功！");
							Qm.refreshGrid();
						} else {
							top.$.notice("停用失败！" + data.msg);
						}
					}
				});
			}
		});
	}
    function accept(){
    	var status = Qm.getValuesByName("IS_USE_SUBSTITUTE").toString();
    	if(status.indexOf('是')!=-1){
    		$.ligerDialog.alert('所选条目中包含已启用数据，请重新选择！');
    		return;
    	}
    	var ids = Qm.getValuesByName("id");
    	$.ligerDialog.confirm("确定要启用？", function(yes) {
			if (yes) {
				$.ajax({
					url : "certificateByAction/useSubstitute.do?ids=" + ids,
					type : "post",
					async : false,
					success : function(data) {
						if (data.success) {
							top.$.notice("启用成功！");
							Qm.refreshGrid();
						} else {
							top.$.notice("启用失败！" + data.msg);
						}
					}
				});
			}
		});
    }
    function forbid(){
    	var status = Qm.getValuesByName("IS_USE_SUBSTITUTE").toString();
    	if(status.indexOf('否')!=-1){
    		$.ligerDialog.alert('所选条目中包含已未启用数据，请重新选择！');
    		return;
    	}
    	var ids = Qm.getValuesByName("id");
    	$.ligerDialog.confirm("确定要停用？", function(yes) {
			if (yes) {
				$.ajax({
					url : "certificateByAction/unuseSubstitute.do?ids=" + ids,
					type : "post",
					async : false,
					success : function(data) {
						if (data.success) {
							top.$.notice("停用成功！");
							Qm.refreshGrid();
						} else {
							top.$.notice("停用失败！" + data.msg);
						}
					}
				});
			}
		});
    }
    function toOne(){
    	var status = Qm.getValuesByName("IS_DISTRIBUTE_ONE").toString();
    	var ids = Qm.getValuesByName("id");
    	var f1= status.indexOf('是')!=-1;
    	var f2= status.indexOf('否')!=-1;
    	if(f1&&f2){
    		$.ligerDialog.alert('所选条目中既包含‘是’，也包含‘否’请重新选择！');
    		return;
    	}else if(status.indexOf('是')!=-1&&status.indexOf('否')==-1){
        	$.ligerDialog.confirm("确定要置为'否'？", function(yes) {
    			if (yes) {
    				$.ajax({
    					url : "certificateByAction/notToOne.do?ids=" + ids,
    					type : "post",
    					async : false,
    					success : function(data) {
    						if (data.success) {
    							top.$.notice("状态改变成功！");
    							Qm.refreshGrid();
    						} else {
    							top.$.notice("状态改变失败！" + data.msg);
    						}
    					}
    				});
    			}
    		});
    	}else if(status.indexOf('是')==-1&&status.indexOf('否')!=-1){
    		$.ligerDialog.confirm("确定要置为'是'？", function(yes) {
    			if (yes) {
    				$.ajax({
    					url : "certificateByAction/toOne.do?ids=" + ids,
    					type : "post",
    					async : false,
    					success : function(data) {
    						if (data.success) {
    							top.$.notice("状态改变成功！");
    							Qm.refreshGrid();
    						} else {
    							top.$.notice("状态改变失败！" + data.msg);
    						}
    					}
    				});
    			}
    		});
    	}
    	
    }
    function toLeast(){
    	var status = Qm.getValuesByName("IS_ASIGN_TO_LEAST").toString();
    	var ids = Qm.getValuesByName("id");
    	if(status.indexOf('是')!=-1&&status.indexOf('否')!=-1){
    		$.ligerDialog.alert('所选条目中既包含‘是’，也包含‘否’请重新选择！');
    		return;
    	}else if(status.indexOf('是')!=-1&&status.indexOf('否')==-1){
        	$.ligerDialog.confirm("确定要置为'否'？", function(yes) {
    			if (yes) {
    				$.ajax({
    					url : "certificateByAction/notToLeast.do?ids=" + ids,
    					type : "post",
    					async : false,
    					success : function(data) {
    						if (data.success) {
    							top.$.notice("状态改变成功！");
    							Qm.refreshGrid();
    						} else {
    							top.$.notice("状态改变失败！" + data.msg);
    						}
    					}
    				});
    			}
    		});
    	}else if(status.indexOf('是')==-1&&status.indexOf('否')!=-1){
    		$.ligerDialog.confirm("确定要置为'是'？", function(yes) {
    			if (yes) {
    				$.ajax({
    					url : "certificateByAction/toLeast.do?ids=" + ids,
    					type : "post",
    					async : false,
    					success : function(data) {
    						if (data.success) {
    							top.$.notice("状态改变成功！");
    							Qm.refreshGrid();
    						} else {
    							top.$.notice("状态改变失败！" + data.msg);
    						}
    					}
    				});
    			}
    		});
    	}
    }
    var device;
    var node={};
    function rule(){
    	if(device){
    		top.$.dialog({
    			width: 600,
    			height: 350,
    			lock: true,
    			parent: api,
    			data: {
    				window: window,
    				node : node
    			},
    			title: "添加",
    			content: 'url:app/approve/certificate_rule_detail.jsp?device=' + device + '&pageStatus=modify'
    		});
    	}else{
    		$.ligerDialog.alert('请选择设备类别！');	
    	}
    }
    
    function loadGridData(deviceType,treeNode) {
    	//console.log(treeNode);
    	node = treeNode;
    	device = deviceType;
		if (deviceType != null) {
			Qm.setCondition([ {
				name : "device_type_id",
				compare : "like",
				value : deviceType.substring(0, 1)+"%"
			} ]);
		} else {
			Qm.setCondition([]);
		}
		Qm.searchData(); 
	}
    
    </script>
</head>
<body>
	<qm:qm pageid="certificate_men" script="false" singleSelect="false"></qm:qm>
</body>
</html>