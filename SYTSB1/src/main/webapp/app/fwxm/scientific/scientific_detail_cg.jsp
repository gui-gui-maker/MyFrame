<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.DateUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<%@ include file="/k/kui-base-form.jsp"%>
<%
	DateFormat ds = new SimpleDateFormat("yyyyMMddHHmmss");
	String nowNum = ds.format(new Date());
%>
<script type="text/javascript" src="app/fwxm/scientific/js/doc_order.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
 <link type="text/css" rel="stylesheet" href="app/finance/css/form_detail.css" />
<script type="text/javascript">
	var type="";
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
			success : function(response) {//处理成功
				
			},
			//取得图片
			getSuccess : function(res) {
				if (res.data && res.data.idNo) {
					p.setIdn(res.data.idNo);
				} else {
					if (res.data && res.data.picture) {
						p.setPictureByPath(res.data.picture);
					}
				}
			}
		});
	})

	function save(){
		var audit_name=$("#auditName").val();
		var audit_id=$("#auditId").val();
		
		$.ajax({
            url: "tjy2ScientificResearchAction/distribution.do",
            type: "POST",
           /*    dataType: "json", 
            contentType: "application/json; charset=utf-8",  */
            data:{"id":"${param.id}","audit_name":audit_name,"audit_id":audit_id},
            
            success : function(data) {
				$("body").unmask();
				if (data["success"]) {
					top.$.dialog.notice({
						content : '保存成功'
					});
					api.data.window.Qm.refreshGrid();
					api.close();
				} else {
					$.ligerDialog.error('提示：' + data.message);
				}
			},
            error : function(data) {
                $("body").unmask();
                $.ligerDialog.error('保存数据失败！');
            }
        });
		
		
	}
	
	  function selectUser() {
	        selectUnitOrUser(1, 1, "auditId", "auditName");
	    }

</script>
</head>
<body>
	<form name="baseForm" id="baseForm" method="post">
		<table cellpadding="3" cellspacing="0" class="l-detail-table">
			<tr>
			    <td class="l-t-td-left">审核人:</td>
				<td class="l-t-td-right"><input type="hidden" name="auditId" id="auditId"></input>
				<input type="text" ltype='text' validate="{required:true}"
					name="auditName" id="auditName" onClick="selectUser()" readonly="readonly" /></td> </td>
			</tr>
			
		</table>
	</form>
</body>
</html>