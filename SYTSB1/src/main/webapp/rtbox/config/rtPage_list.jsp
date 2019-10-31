<%@ page import="com.khnt.security.util.SecurityUtil" %>
<%@ page import="com.khnt.security.CurrentSessionUser" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    CurrentSessionUser user = SecurityUtil.getSecurityUser();
    String userid = user.getId();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>列表页面</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults: {columnWidth: 0.33, labelAlign: 'right', labelSeparator: '', labelWidth: 100},//可以自己定义 layout:column,float
		
		sp_fields: [ 
			{name: "rt_code",compare: "like"}, 
			{name: "rt_name",compare: "like"}, 
			{name: "report_type",compare: "like"}
		],
		
		tbar: [ {
			text: '详情', id: 'detail', icon: 'detail', click: detail
		}, "-", {
			text: '新增', id: 'add', icon: 'add', click: add
		}, "-", {
			text: '修改', id: 'edit', icon: 'modify', click: edit
		}, "-", {
			text: '删除', id: 'del', icon: 'delete', click: del
		}/* , "-", {
			text: '模板设置', id: 'set', icon: 'setting', click: set
		}  */, "-", {
            text: '模板设计', id: 'vipf', icon: 'table-edit', click: vipf
        }, "-", {
			text: '复制', id: 'copyTpl', icon: 'delete', click: copyTpl
		}, "-", {
			text: '下载模版', id: 'downTpl', icon: 'delete', click: downTpl
		}, "-", {
			text: '启用', id: 'tplEnable', icon: 'delete', click: tplEnable
		}, "-", {
			text: '禁用', id: 'tplDisable', icon: 'delete', click: tplDisable
		}],
		listeners: {
			rowClick: function(rowData, rowIndex) {
			},
			rowDblClick: function(rowData, rowIndex) {
				detail();
			},
			rowAttrRender: function (rowData, rowid) {
                if(rowData.列名1=='XXX') // 记录为绿色
                {
                    return "style='color:green'";
                }
                if(rowData.列名2=='YYY') // 记录为红色
                {
                    return "style='color:red'";
                }
            },
			selectionChange: function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();
				Qm.setTbar({
					detail: count==1,
					edit: count==1,
					del: count>0,
					set: count==1,
                    vipf:count==1,
                    copyTpl:count==1,
                    downTpl:count==1,
                    tplEnable:count==1,
                    tplDisable:count==1
				});
			}
		}
	};
	
	function add() {
		top.$.dialog({
			width:'60%',
			height:'80%',
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "新增",
			content: 'url:rtbox/config/rtPage_detail.jsp?pageStatus=add&tplId=${param.tplId}&rtCode=${param.rtCode}&rtName=' + encodeURIComponent("${param.rtName}")
		});
	}
	function edit() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width:'60%',
			height:'80%',
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "修改",
			content: 'url:rtbox/config/rtPage_detail.jsp?id=' + id + '&pageStatus=modify&tplId=${param.tplId}&rtCode=${param.rtCode}&rtName=' + encodeURIComponent("${param.rtName}")
		});
	}
	function detail() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width:'80%',
			height:'80%',
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "详情",
			content: 'url:rtbox/config/rtPage_detail.jsp?id=' + id + '&pageStatus=detail&tplId=${param.tplId}&rtCode=${param.rtCode}&rtName=' + encodeURIComponent("${param.rtName}")
		});
	}
	function del() {
		$.del(kFrameConfig.DEL_MSG, "com/rt/page/delete.do", {
			"ids": Qm.getValuesByName("id").toString()
		});
	}
	
	function set(){
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width:'50%',
			height:'80%',
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "模板",
			content: 'url:rtbox/config/rtPage_set.jsp?id=' + id + '&pageStatus=modify'
		});
	}
	function vipf() {
		var id = Qm.getValueByName("id")
        var rtCode = Qm.getValueByName("rt_code")
        var modelType = Qm.getValueByName("model_type")
        var url = "";
        if(modelType=="0"){
            url='rtbox/app/visual/templates/index.jsp?code='+rtCode+"&rtboxId="+id
        }else if(modelType=="1"){
            url='rtbox/app/visual/recordTemplates/index.jsp?code='+rtCode+"&rtboxId="+id
        }else{
            alert("报表类型错误！");
            return;
        }
        top.$.dialog({
            lock: true,
            parent: api,
            data: {
                window: window
            },
            title: "模板",
            content: 'url:'+url
        }).max();
    }
	
	// 复制
	function copyTpl(){
		var id = Qm.getValueByName("id");
		var rtCode = Qm.getValueByName("rt_code");
		var version = Qm.getValueByName("version");
		$.ligerDialog.confirm("您确定复制报表代码【<font color='red'>"+rtCode+"</font>】，版本号【<font color='red'>"+version+"</font>】的报告模版吗？", function (yes) {
		    if (yes) {
				$.post("com/rt/page/copyTpl.do",{id:id},function(data){
					if(data.success){
						top.$.notice("复制成功！",3);
						Qm.refreshGrid();
					}else{
						$.ligerDialog.alert("复制失败!");
					}
				});
		    }
		});
	}
	
	// 下载模版
	function downTpl(){
		var id = Qm.getValueByName("id");
		var rtCode = Qm.getValueByName("rt_code");
		var templete_doc_file_path = Qm.getValueByName("templete_doc_file_path");
		templete_doc_file_path = templete_doc_file_path.replace('\\',"/");

		window.location.href = $("base").attr("href") + "rtbox/public/down.jsp?name="+rtCode+"_temple.docx"+"&path="+templete_doc_file_path;
	}
	
	// 启用
	function tplEnable(){
		var id = Qm.getValueByName("id");
		var rtCode = Qm.getValueByName("rt_code");
		var version = Qm.getValueByName("version");
		$.ligerDialog.confirm("您确定启用报表代码【<font color='red'>"+rtCode+"</font>】，版本号【<font color='red'>"+version+"</font>】的报告模版吗？", function (yes) {
		    if (yes) {
				$.post("com/rt/page/enable.do",{id:id},function(data){
					if(data.success){
						top.$.notice("设置成功！",3);
						Qm.refreshGrid();
					}else{
						$.ligerDialog.alert("设置失败!");
					}
				});
		    }
		});
	}
	
	// 禁用
	function tplDisable(){
		var id = Qm.getValueByName("id");
		var rtCode = Qm.getValueByName("rt_code");
		var version = Qm.getValueByName("version");
		$.ligerDialog.confirm("您确定禁用报表代码【<font color='red'>"+rtCode+"</font>】，版本号【<font color='red'>"+version+"</font>】的报告模版吗？\n确定后将启用最大版本号报告模版。", function (yes) {
		    if (yes) {
				$.post("com/rt/page/disable.do",{id:id},function(data){
					if(data.success){
						top.$.notice("设置成功！",3);
						Qm.refreshGrid();
					}else{
						$.ligerDialog.alert("设置失败!");
					}
				});
		    }
		});
	}	
</script>
</head>
<body>
	<qm:qm pageid="rt_page" script="false" singleSelect="false">
    	 <qm:param name="fk_rt_template_id" compare="like" value="${param.tplId}"/>
    </qm:qm>
</body>
</html>