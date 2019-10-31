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
		sp_defaults: {columnWidth: 0.33, labelAlign: 'right'}, //可以自己定义 layout:column,float,labelSeparator,labelWidth
		
		sp_fields: [ 
			{name: "applier",compare: "="}, 
			{name: "receive_org",compare: "="}, 
			{group: [
				{name: "apply_time", compare: ">="},
				{label: "到", name: "apply_time", compare: "<=", labelAlign: "center", labelWidth:20}
			]}
		],
		tbar: [ {
			text: '详情', id: 'detail', icon: 'detail', click: detail
		}, "-", {
			text: '新增', id: 'add', icon: 'add', click: add
		}, "-", {
			text: '修改', id: 'edit', icon: 'modify', click: edit
		}, "-", {
			text: '提交', id: 'sub', icon: 'submit', click: sub
		}, "-", {
			text: '审批', id: 'approve', icon: 'dispose', click: approve
		}, "-", {
			text: '删除', id: 'del', icon: 'delete', click: del
		}],
		listeners: {
			rowClick: function(rowData, rowIndex) {
			},
			rowDblClick: function(rowData, rowIndex) {
				detail();
			},
			rowAttrRender: function (rowData, rowid) {
                if(rowData.status=='审批通过') // 记录为绿色
                {
                    return "style='color:green'";
                }
                if(rowData.status=='待审批') // 记录为红色
                {
                    return "style='color:red'";
                }
            },
			selectionChange: function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();
				var status = Qm.getValuesByName("status");
				Qm.setTbar({
					detail: count==1,
					edit: count==1&&shouldDo(status,["未提交","审批未通过"]),
					del: count>0&&shouldDo(status,["未提交","审批未通过"]),
					sub: count>0&&shouldDo(status,["未提交","审批未通过"]),
					approve:count>0&&shouldDo(status,["待审批"])
				});
			}
		}
	};
	function shouldDo(status,arr){
		for(var i in status){
			var c = "";
			for(var j in arr){
				if(j=="0"){
					c+="'"+status[i]+"'!='"+arr[j]+"'";
				}else{
					c+="&&'"+status[i]+"'!='"+arr[j]+"'";
				}
			}
			if(eval(c)){
				 return false;
			}
		}
		return true;
	}
	
	function add() {
		top.$.dialog({
			width: 850,
			height: 600,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "新增",
			content: 'url:app/fwxm/library/receive_detail.jsp?pageStatus=add'
		}).max();
	}

	function edit() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 850,
			height: 600,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "修改",
			content: 'url:app/fwxm/library/receive_detail.jsp?id=' + id + '&pageStatus=modify'
		});
	}
	function approve() {
		var id = Qm.getValuesByName("id");
		top.$.dialog({
			width: 850,
			height: 400,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "修改",
			content: 'url:app/fwxm/library/receive_approve.jsp?ids=' + id + '&pageStatus=modify'
		});
	}
	function sub() {
		$.del("确认提交？", "lib/receive/sub.do", {
			"ids": Qm.getValuesByName("id").toString()
		});
	}

	function detail() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 850,
			height: 600,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "详情",
			content: 'url:app/fwxm/library/receive_detail.jsp?id=' + id + '&pageStatus=detail'
		});
	}

	function del() {
		$.del(kFrameConfig.DEL_MSG, "lib/receive/delete.do", {
			"ids": Qm.getValuesByName("id").toString()
		});
	}
</script>
</head>
<body>
	<qm:qm pageid="lib_receive" script="false" singleSelect="false">
    	 <!--qm:param name="str1" compare="like" value="A"/-->
    </qm:qm>
</body>
</html>