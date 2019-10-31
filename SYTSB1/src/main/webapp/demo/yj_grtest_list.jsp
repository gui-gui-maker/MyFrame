<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib uri="http://khnt.com/tags/qm" prefix="q" %>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>新申报登记</title>
<%@include file="/k/kui-base-list.jsp" %>

<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript" src="pub/rbac/js/area.js"></script>
<script type="text/javascript">
		
					var qmUserConfig = {
						sp_fields: [
							{label: "姓       名：", name: "name", compare: "like", value: "", labelWidth: 0.3},
							{label: "身  份  证：", name: "idn", compare: "=", value: "", labelWidth: 0.3},
							{label: "性       别：", name: "sex", compare: "=", value: "", labelWidth: 0.3},
							{label: "民       族：", name: "nation", compare: "=", value: "", labelWidth: 0.3},
							{label: "入党时间：", name: "birthday", compare: "=", value: "", labelWidth: 0.3}

						],
						tbar: [
							{ text: '详情', id: 'detail', icon: 'detail', click: detail},
							"-",
							{ text: '新增', id: 'add', icon: 'add', click: add},
							"-",
							{ text: '修改', id: 'edit', icon: 'modify', click: edit},
							"-",
							{ text: '删除', id: 'del', icon: 'delete', click: del}


						],
						//提供以下4个事件
						listeners: {
							rowClick: function (rowData, rowIndex) {
							},
							rowDblClick: function (rowData, rowIndex) {
								detail();
							}, selectionChange: function (rowData, rowIndex) {
								var count = Qm.getSelectedCount();
								//var sta = Qm.getValueByName("status");
								var status = {};
								if (count == 0) {
									status = {detail: false, edit: false, del: false};
								} else if (count == 1) {
									status = {detail: true, edit: true, del: true, out: true};
								} else {
									status = {detail: false, edit: false, del: true};
								}
								Qm.setTbar(status);
							}
						}
					};
					function add() {
						var windows = top.$.dialog({
							width: 1000,
							height: 800,
							lock: true,
							parent: api,
							data: {window: window},
							title: "新增",
							content: 'url:demo/yj_grtest_detail.jsp?pageStatus=add'
						});
					}
					function edit() {
						var id = Qm.getValueByName("id");
						var windows = top.$.dialog({
							width: 1000,
							height: 800,
							lock: true,
							parent: api,
							data: {window: window},
							title: "修改",
							content: 'url:demo/yj_grtest_detail.jsp?id=' + id + '&pageStatus=modify'
						});
					}
					function del() {
						$.del(kFrameConfig.DEL_MSG, "gr/user/delete.do", {"ids": Qm.getValuesByName("id").toString()});
					}
					function detail() {
						var id = Qm.getValueByName("id");
						var windows = top.$.dialog({
							width: 1000,
							height: 800,
							lock: true,
							parent: api,
							data: {window: window},
							title: "详情",
							content: 'url:demo/yj_grtest_detail.jsp?id=' + id + '&pageStatus=detail'
						});
					}
		</script>
</head>
<body>
	<q:qm pageid="gr_test">
	</q:qm>
</body>
</html>