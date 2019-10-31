<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<title>详情页面</title>
<%@include file="/k/kui-base-form.jsp" %>
<script type="text/javascript">
	$(function () {
		$("#formObj").initForm({    //参数设置示例
			toolbar:null,
			success: function (response) {
				if(response.success){
					top.$.notice("保存成功！",3);
					api.data.window.Qm.refreshGrid();
					api.close();
				}
				else{
					$.ligerDialog.error("操作失败！<br/>" + response.msg);
				}
			}
		});
	});
	
	
	function getSelectResult(){
		var ruleStart=$("#ruleStart").val();
		var ruleShape=$("#ruleShape").val();
		var rulePrefix=$("#rulePrefix").val();
		var result={};
		result.ruleStart=ruleStart;
		result.ruleShape=ruleShape;
		result.rulePrefix=rulePrefix;
		return result;
	}
</script>
</head>
<body>
<form id="formObj" >  
    
    <table cellpadding="3" class="l-detail-table">
    	<tr>    
	   		<td class="l-t-td-left">编号开始：</td>
            <td class="l-t-td-right">
            	<input name="ruleStart" id="ruleStart" type="text" ltype="text" validate="{required:true,maxlength:20}"  value="1"
            	/>
            </td>
        </tr>
<!--         <tr> -->
<!--             <td class="l-t-td-left">形状：</td> -->
<!--             <td class="l-t-td-right"> -->
<!--             	<input name="ruleShape" id="ruleShape" type="text" ltype="select" validate="{required:true,maxlength:100}" ligerui="{initValue:'三角形',data:[{'id':'三角形','text':'三角形'},{'id':'x','text':'x'}]}" /> -->
<!--             </td> -->
<!--     	</tr> -->
    	
        <tr>    
	   		<td class="l-t-td-left">编号前缀：</td>
            <td class="l-t-td-right">
            	<input name="rulePrefix" id="rulePrefix" type="text" ltype="text" validate="{required:true,maxlength:10}" value="F"/>
            </td>
        </tr>
       
        </tr>
    </table>
</form>
</body>
</html>
