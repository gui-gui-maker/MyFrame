<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>图表设置</title>
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
	$(function() {
		$("#formObj").initForm({
			success : function(resp) {
				if (resp.success) {
					top.$.dialog.notice("复制成功",3);
					api.data.window.Qm.refreshGrid();
					api.close();
				}
				else
				{
					top.$.dialog.notice("复制失败："+resp.msg,10);
					api.data.window.Qm.refreshGrid();
					api.close();
				}
			}
		});
	});

</script>
</head>

<body>
    <form action="lchart/conf/copyChart.do" method="post" id="formObj" style="margin-top: 1em">
		<input type="hidden" name="id" value="${param.id}" />
		<table class="l-detail-table">
			<tr>
                <td class="l-t-td-left">调用名称：</td>
                <td class="l-t-td-right"><input id="CHARTID" name="CHARTID" ltype='text' validate="{required:true,maxlength:100}" /></td> 
			</tr>
		</table>
	</form>
</body>
</html>