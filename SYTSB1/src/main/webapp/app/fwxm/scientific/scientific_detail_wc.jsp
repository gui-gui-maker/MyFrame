<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.DateUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="app/fwxm/scientific/js/doc_order.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
 <link type="text/css" rel="stylesheet" href="app/finance/css/form_detail.css" />
<script type="text/javascript">
	$(function() {
		$("#baseForm").initForm({
			toolbar : [{
				text : '保存',
				icon : 'save',
				click : save
			}, {
				text : '关闭',
				icon : 'cancel',
				click : function() {
					api.close();
				}
			}],
			toolbarPosition : "bottom",
			success : function(response) {//处理成功
				if (response.success) {
					//保存基本信息（主表）后，id未自动赋值，故此处手动赋值
					api.data.window.Qm.refreshGrid();
					api.close();
				} else {
					$.ligerDialog.error('保存失败！<br/>' + response.msg);
				}
			},
			//取得图片
			getSuccess : function(res) {
			$("#cost_id").val(res.data.id);
			$("#paper_id").val(res.data.id);
			$("#patent_id").val(res.data.id);
			}
		});
	})
		function save(){
		//alert($("#type").ligerComboBox().getValue());
		$("#baseForm").submit();
	}
    
</script>
</head>
<body>
<div class="navtab" style="height: 662px;margin:0 auto;border:1px solid #ccc;">
        <div title="项目基本信息" id="form1">
			<%@ include file="scientific_detail_base.jsp"%>
		</div>  
		  <div title="费用信息" id="form2">
			<%@ include file="scientific_cost.jsp"%>
		</div>  
		<div title="论文信息" id="form3">
			<%@ include file="scientific_paper.jsp"%>
		</div>
		<div title="专利信息" id="form4">
			<%@ include file="scientific_patent.jsp"%>
		</div>
		</div>
</body>
</html>