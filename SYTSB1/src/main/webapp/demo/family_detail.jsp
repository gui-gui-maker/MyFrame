<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="${param.pageStatus}">
    <title></title>
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-form.jsp" %>

    <script type="text/javascript">
        var propTypes =<u:dict code="mzmz"/>;
		$(function () {
////                        如果不设置额外参数，不用调用此方法，初始化时会自动调用
			$("#form1").initForm({    //参数设置示例
//                toolbar:null,
				success: function (aaa) {
					$("#tree").ligerTree().treeManager.bind("success", function (data) {
						alert(data)
					});
					$("#tree").ligerTree().loadData();
				}
			});
//            $("#test").transform("detail");
			familyProp();
			familyMember();
		});
		var __area__tree;
		var __last_select_area__tree_value;
		function showAreaList() {
//            alert(this.options.valueFieldID)
			if (__area__tree) {
				__area__tree.show();
			} else {
				__area__tree = $.ligerDialog.open({ title: '选择地址', width: 350, height: 300, content: "<div id='''></div>", buttons: [
					{ text: '确定', onclick: function (item, dialog) {
					} },
					{ text: '取消', onclick: function (item, dialog) {
						dialog.hide();
					} }
				]
				});
				var imgUrl = "pub/rbac/img/";
				tree = $("#tree1").ligerTree({
					checkbox: false,
					url: 'rbac/area/getAreaTreeWithAsync.do?code=<sec:authentication property="principal.department.areaCode"/>',
					attribute: [ "pid" ],
					iconFieldName: "levels",
					iconParse: function (data) {
						return imgUrl + data["levels"] + ".png";
					},
					onBeforeExpand: function (node) {
						if (node.data.children && node.data.children.length == 0) {
							tree.loadData(node.target, "rbac/area/getAreaTreeByPid.do?id=" + node.data.id);
						}
					}
				});
			}
			return false;
		}


		var manager;
		function familyProp() {
			manager = $("#prop").ligerGrid({
				columns: [
					{ display: '财产类别', width: '20%', name: 'types', type: 'text',
						editor: { type: 'select', data: propTypes },
						render: function (item) {
							for (var i in propTypes) {
								if (propTypes[i]["id"] == item["types"])
									return propTypes[i]['text'];
							}
							return item["types"];
						}
					},
					{ display: '名称', name: 'name', width: '15%', editor: { type: 'text' }, ligerui: {autocomplete: {data: ["111", "222", "333", "中国", "美国"]}}},
					{ display: '数量', name: 'num', width: '15%', type: 'int', editor: { type: 'int'} },
					{ display: '金额', name: 'money', width: '15%', type: 'int', editor: { type: 'int'} },
					{ display: '备注', name: 'remarks', width: '20%', editor: { type: 'text' }},
					{ display: '<a href="javascript:addNewRow()">增加<img src="/k/kui/images/icons/add.gif"></a>', isSort: false, width: 120, render: function (rowdata, rowindex, value) {
						var h = "";
						if (!rowdata._editing) {
							h += "<a href='javascript:addNewRow()'>增加</a> ";
							h += "<a href='javascript:beginEdit(" + rowindex + ")'>修改</a> ";
							h += "<a href='javascript:deleteRow(" + rowindex + ")'>删除</a> ";
						}
						else {
							h += "<a href='javascript:endEdit(" + rowindex + ")'>提交</a> ";
							h += "<a href='javascript:cancelEdit(" + rowindex + ")'>取消</a> ";
						}
						return h;
					}
					}
				],
				onSelectRow: function (rowdata, rowindex) {
					$("#txtrowindex").val(rowindex);
				},
				title: '家庭财产信息',
				enabledEdit: true,
				clickToEdit: false,
				rownumbers: true,                         //是否显示行序号
				frozenRownumbers: false,
				isScroll: true,
				usePager: false,
				data: {Rows: [
					{}
				]},
				width: '',
				height: 120
			});

		}
		function familyMember() {
			var tt = $("#member").ligerGrid({
				columns: [
					{ display: '姓名', name: 'name', width: '20%', type: 'text', editor: { type: 'text' }},
					{ display: '性别', name: 'name', width: '20%', editor: { type: 'text' }},
					{ display: '身份证号', name: 'num', width: 50, type: 'int', editor: { type: 'int'} },
					{ display: '与户主关系', name: 'money', width: 50, type: 'int', editor: { type: 'int'} },
					{ display: '联系电话', name: 'money', width: 50, type: 'int', editor: { type: 'int'} },
					{ display: '<a href="javascript:addNewRow()">增加</a>', isSort: false, width: 120, render: function (rowdata, rowindex, value) {
						var h = "";
						if (!rowdata._editing) {
							h += "<a href='javascript:addNewRow()'>增加</a> ";
							h += "<a href='javascript:beginEdit(" + rowindex + ")'>修改</a> ";
							h += "<a href='javascript:deleteRow(" + rowindex + ")'>删除</a> ";
						}
						else {
							h += "<a href='javascript:endEdit(" + rowindex + ")'>提交</a> ";
							h += "<a href='javascript:cancelEdit(" + rowindex + ")'>取消</a> ";
						}
						return h;
					}
					}
				],
				onSelectRow: function (rowdata, rowindex) {
					$("#txtrowindex").val(rowindex);
				},
				title: '家庭成员信息',
				enabledEdit: true,
				clickToEdit: false,
				rownumbers: true,                         //是否显示行序号
				frozenRownumbers: false,
//         isScroll: false,
				usePager: false,
				data: {Rows: [
					{}
				]},
				width: '100%'
			});

		}
		function beginEdit() {
			var row = manager.getSelectedRow();
			if (!row) {
				alert('请选择行');
				return;
			}
			manager.beginEdit(row);
		}
		function cancelEdit() {
			var row = manager.getSelectedRow();
			if (!row) {
				alert('请选择行');
				return;
			}
			manager.cancelEdit(row);
		}
		function cancelAllEdit() {
			manager.cancelEdit();
		}
		function endEdit() {
			var row = manager.getSelectedRow();
			if (!row) {
				alert('请选择行');
				return;
			}
			manager.endEdit(row);
		}
		function endAllEdit() {
			manager.endEdit();
		}
		function deleteRow() {
			manager.deleteSelectedRow();
		}
		var newrowid = 100;
		function addNewRow() {
			manager.addEditRow();
		}

		function getSelected() {
			var row = manager.getSelectedRow();
			if (!row) {
				alert('请选择行');
				return;
			}
			alert(JSON.stringify(row));
		}
		function getData() {
			var data = manager.getData();
			alert(JSON.stringify(data));
		}
		var yearData = [];
		for (var i = 0; i < 100; i++) {
			yearData.push({id: 1930 + i, text: 1930 + i});
		}
    </script>
    <style type="text/css">
        .l-panel-header {
			background:url("");
			border-bottom:1px solid #99BBE8;
			color:#15428B;
			font-size:12px;
			font-weight:bold;
			height:24px;
			position:relative;
		}
    </style>
</head>
<body style="margin: 5px">

<form id="form1" action="demo/test.json" getAction="demo/test.json">
    <input type="hidden" id="id" name="id">
    <fieldset>
        <legend>家庭基本信息</legend>
        <table border="1" cellpadding="3" cellspacing="0" width="98%" class="l-detail-table">
            <tr>

                <td class="l-t-td-left">房屋使用分类：</td>
                <td class="l-t-td-right"><u:combo name="clazz" code="ba_fwsyfl"/></td>
                <td class="l-t-td-left">房主姓名：</td>
                <td class="l-t-td-right"><input name="name" type="text" value="" ltype='text' validate="{maxlength:50}"/></td>
                <td class="l-t-td-left">房主身份证号：</td>
                <td class="l-t-td-right"><input name="idn" type="text" value="" ltype='text' validate="{maxlength:18}"/></td>
            </tr>

            <tr>
                <td class="l-t-td-left">房屋性质：</td>
                <td class="l-t-td-right"><u:combo name="prop" code="ba_fwxz"/></td>
                <td class="l-t-td-left">房屋结构：</td>
                <td class="l-t-td-right"><u:combo name="struct" code="ba_fwjg"/></td>
                <td class="l-t-td-left">房屋面积：</td>
                <td class="l-t-td-right"><input name="area" type="text" value="" ltype='text' ligerui="{suffix:'㎡'}"/></td>

            </tr>
            <tr>
                <td class="l-t-td-left">房屋地址：</td>
                <td class="l-t-td-right"><input name="fk_area_id" type="text" value="" ltype='select' validate="{maxlength:30}" ligerui="{valueFieldID:'fk_area_id',onBeforeOpen:showAreaList}"/></td>
                <td class="l-t-td-right" colspan="2"><input name="addr" type="text" value="" ltype='text' validate="{maxlength:50}"/></td>
                 <td class="l-t-td-left">购房/修建日期：</td>
                <td class="l-t-td-right"><input type="text" id="dates-txt" name="dates_txt" ltype="select" ligerui="{initValue:2000,data:yearData}"/></td>
            </tr>
            <tr>
                <td class="l-t-td-left">家庭月收入：</td>
                <td class="l-t-td-right"><input name="salary" type="text" value="" ltype='spinner' ligerui="{type:'int',suffix:'元'}"/></td>
                <td class="l-t-td-left">银行贷款情况：</td>
                <td class="l-t-td-right"><input name="bank" type="text" value="" ltype='spinner' ligerui="{type:'int',suffix:'元'}"/></td>
                <td class="l-t-td-left">私有贷款情况：</td>
                <td class="l-t-td-right"><input name="priv" type="text" value="" ltype='spinner' ligerui="{type:'int',suffix:'元'}"/></td>
            </tr>
            <tr>
                <td class="l-t-td-left">能源信息：</td>
                <td class="l-t-td-right" colspan="3"><u:combo name="energy" code="ba_zyny"/></td>
                <td class="l-t-td-left">饮用水情况：</td>
                <td class="l-t-td-right"><u:combo name="water" code="ba_yys"/></td>
            </tr>
        </table>
    </fieldset>

    <div id="prop" style="margin-top:20px;margin-right:10px;width:98%"></div>
    <div id="member" style="margin-top:20px"></div>

</form>

</body>
</html>
