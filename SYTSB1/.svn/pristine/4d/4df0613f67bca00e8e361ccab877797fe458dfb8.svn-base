<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.rbac.bean.Org"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="main_head">
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
		sp_fields : [ {
			name : "advicenote_name",
			compare : "like",
			value : ""
		},{
			name : "advicenote_type",
			compare : "=",
			value : ""
		},{
			name : "check_result",
			compare : "=",
			value : ""
		}],
		tbar : [ {
			text : '详情',
			id : 'detail',
			click : detail,
			icon : 'detail'
		}, '-', {
			text : '新增',
			id : 'add',
			click : add,
			icon : 'add'
		}, '-', {
			text : '修改',
			id : 'modify',
			click : modify,
			icon : 'modify'
		}, '-', {
			text : '删除',
			id : 'del',
			click : del,
			icon : 'del'
		}, '-', {
			text : '打印',
			id : 'print',
			click : print,
			icon : 'print'
		} ],
		listeners : {
			rowClick : function(rowData, rowIndex) {
			},
			rowDblClick : function(rowData, rowIndex) {
				detail();
			},
			selectionChange : function(rowData, rowIndex) {
				selectionChange()
			},
			rowAttrRender : function(rowData, rowid) {

			}
		}
	};

	//行选择改变事件
	function selectionChange() {
		var count = Qm.getSelectedCount();//行选择个数
		if (count == 0) {
			Qm.setTbar({
				modify : false,
				del : false,
				detail : false,
				print : false
			});
		} else if (count == 1) {
			var check_result = Qm.getValueByName("check_result");
			if (check_result == "未审核") {
				Qm.setTbar({
					modify : true,
					del : true,
					detail : true,
					print : false
				});
			} else if (check_result == "通过") {
				Qm.setTbar({
					modify : false,
					del : false,
					detail : true,
					print : true
				});
			} else {
				Qm.setTbar({
					modify : true,
					del : false,
					detail : true,
					print : false
				});
			}
		} else {
			var n = 0;
			var m = 0;
			var f = 0;
			var records = Qm.getQmgrid().getSelecteds();
			var l = records.length;
			for ( var i = 0; i < l; i++) {
				var status = records[i]["check_result"];
				if (status == "未审核") {
					n++;
				} else if (status == "通过") {
					m++;
				}else if (status == "未通过") {
					f++;
				}
			}

			if (( n > 0  || f > 0 ) && m > 0) {
				Qm.setTbar({
					modify : false,
					del : false,
					detail : false,
					print : false
				});
			} else if (( n > 0  || f > 0 ) && m < 1) {
				Qm.setTbar({
					modify : false,
					del : true,
					detail : false,
					print : false
				});
			} else if (( n < 1 || f < 1 ) && m > 0) {
				Qm.setTbar({
					modify : false,
					del : false,
					detail : false,
					print : false
				});
			}
		}
	}

	//新增
	function add() {
		top.$.dialog({
					width : 500,
					height : 200,
					lock : true,
					title : "新增",
					data : {
						"window" : window
					},
					content : 'url:app/advicenote/advicenote_detail.jsp?status=add'
				});
	}

	//修改
	function modify() {
		var record = Qm.getQmgrid().getSelected();
		if (record.check_result == "通过") {
			$.ligerDialog.success('数据已审核通过，不能修改！');
			return;
		}
		top.$.dialog({
					width : 500,
					height : 200,
					lock : true,
					title : "修改",
					data : {
						"window" : window
					},
					content : 'url:app/advicenote/advicenote_detail.jsp?status=modify&id='+ record.id
				});
	}

	//查看
	function detail() {
		var selectedId = Qm.getValuesByName("id");
		if (selectedId.length < 1) {
			$.ligerDialog.alert('请先选择要查看的记录！', "提示");
			return;
		}
		top.$.dialog({
					width : 500,
					height : 240,
					lock : true,
					title : "详情",
					data : {
						"window" : window
					},//把当前页面窗口传入下一个窗口，以便调用。
					content : 'url:app/advicenote/advicenote_detail.jsp?status=detail&id='+ Qm.getValueByName("id")
				});
	}

	//删除
	function del() {
		var selectedId = Qm.getValuesByName("id");
		if (selectedId.length < 1) {
			$.ligerDialog.alert('请先选择要删除的记录！', "提示");
			return;
		}
		$.ligerDialog.confirm(kui.DEL_MSG, function(yes) {
			if (yes) {
				$.ajax({
					url : "advicenote/deleteAdviceNote.do?ids=" + selectedId,
					type : "post",
					async : false,
					success : function(data) {
						if (data.success) {
							top.$.notice("删除成功！");
							Qm.refreshGrid();
						} else {
							top.$.notice("删除失败！" + data.msg);
						}
					}
				});
			}
		});
	}
	
	// 打印
	function print() {
		var id = Qm.getValueByName("id");
		var docId = Qm.getValueByName("docId");
		var type = Qm.getValueByName("type");
		top.$.dialog({
			width : $(top).width(),
			height :  $(top).height()-40,
			lock : true,
			title : "正文内容",
			parent: api,
			content : 'url:app/advicenote/docEditor.jsp?status=print&docId='+docId+'&type='+type,
			data: {pwindow: window,bean: id}
		}).max();
	}

	function refreshGrid() {
		Qm.refreshGrid();
	}
</script>
</head>
<%
	CurrentSessionUser user = SecurityUtil.getSecurityUser();
	String userId = user.getId();
%>
<body>
	<qm:qm pageid="advicenote_list" script="false">
		<qm:param name="enter_user_id" value="<%=userId%>" compare="=" />
	</qm:qm>
</body>
</html>
