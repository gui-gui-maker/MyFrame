<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<title></title>
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@include file="/k/kui-base-form.jsp"%>
<head>
<script type="text/javascript">
	$(function() {
		$("#form1").initForm({ //参数设置示例
			toolbar : [ {
				text : '保存',
				//id : 'save',
				icon : 'save',
				click : save
			}, {
				text : '关闭',
				//id : 'close',
				icon : 'cancel',
				click : close
			} ],
			getSuccess : function(res) {

			}
		});
	})

	function close() {
		api.close();
		api.data.window.submitAction();
	}
	
	function save() {
		if ($('#revise_remark').val() == null
				|| $('#revise_remark').val() == undefined
				|| $('#revise_remark').val() == "") {
			top.$.dialog.notice({
				content : '请填写退回原因！'
			});
			return;
		}

		// 回退到报告领取
		var	url = "department/basic/end_backCheck.do?type=${param.type}&flow_num=${param.flow_num}&acc_id=${param.acc_id}&infoId=${param.infoId}";
		
		//验证表单数据
		if ($('#form1').validate().form()) {
			var formData = $("#form1").getValues();
			var data = {};
			data = formData;
			$("body").mask("正在保存数据，请稍后！");
			$.ajax({
				url : url,
				type : "POST",
				datatype : "json",
				data : {
					dataStr : $.ligerui.toJSON(data)
				},
				success : function(data, stats) {
					$("body").unmask();
					if (data["success"]) {
						if (api.data.window.Qm) {
							api.data.window.Qm.refreshGrid();
						}
						top.$.dialog.notice({
							content : '保存成功'
						});
						api.data.window.Qm.refreshGrid();
						api.close();
					} else {
						$.ligerDialog.error('提示：' + data.msg);
					}
				},
				error : function(data, stats) {
					$("body").unmask();
					$.ligerDialog.error('提示：' + data.msg);
				}
			});
		}
	}
</script>
</head>
<body>
	<form id="form1">
		<table border="1" cellpadding="0" cellspacing="0" width=""
			class="l-detail-table">
			<tr>
				<td class="l-t-td-left">回退原因：</td>
				<td class="l-t-td-right" colspan="2"><textarea
						name="revise_remark" id="revise_remark" rows="3" cols=""
						ext_type="string" ext_maxLength="200" ext_name="备注" isNull="Y"
						class="area_text"></textarea>
				</td>
			</tr>
			<!-- 
			<tr id="back">
				<td class="l-t-td-left">回退步骤：</td>
				<td class="l-t-td-right"><input type="radio" name="backStep"
						id="backStep" ltype="radioGroup" validate="{required:false}"
						ligerui="{value:'1',data: [ { text:'退回上一步', id:'1' }, { text:'退回报告录入', id:'2' } ] }" />
				</td>
			</tr>
			 -->
		</table>
	</form>
</body>
</html>
