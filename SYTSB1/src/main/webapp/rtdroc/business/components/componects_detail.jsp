<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<title>详情页面</title>
<%@include file="/k/kui-base-form.jsp" %>
<script src="rtdroc/app/draw/util/RtDraw.js"></script>
<script type="text/javascript">
	$(function () {
		$("#formObj").initForm({    //参数设置示例
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
	
	
	function draw() {
		var fileId=$("#fkAttIdDraw").val();
		RtDroc(fileId,function(result){
			$("#drawData").val(result.drawData);
			console.log("cdata:"+$("#drawData").val());
			$("#imgData").val(result.imgData);
		});
	}
	

</script>
</head>
<body>
<form id="formObj" action="com/rtd/components/saveComponents.do" getAction="com/rtd/components/detail.do?id=${param.id}">
    <input type="hidden" id="id" name="id" />
    <input type="hidden" id="fkAttIdDraw" name="fkAttIdDraw" />
    <input type="hidden" id="fkAttIdImg" name="fkAttIdImg" />
    <input type="hidden" id="handleTime" name="handleTime" />
    <input type="hidden" id="enableTime" name="enableTime" />
    <input type="hidden" id="status" name="status" />
    <input type="hidden" id="isDel" name="isDel" />
    <input type="hidden" id="imgData" name="imgData" />
    <table cellpadding="3" class="l-detail-table" >
        <tr>
            <td class="l-t-td-left">组件名：</td>
            <td class="l-t-td-right">
            	<input name="componentName" id="componentName" type="text" ltype="text" validate="{required:true,maxlength:100}" />
            </td>
    	</tr>
    	<tr>    
	   		<td class="l-t-td-left">组件类型：</td>
            <td class="l-t-td-right">
            	<input name="componentType" id="componentType" type="text" ltype="text" validate="{required:true,maxlength:20}" 
            	/>
            </td>
        </tr>
        <tr>    
	   		<td class="l-t-td-left">组件数据：</td>
            <td class="l-t-td-right">
            	<input name="drawData" id="drawData" type="text" ltype="text" validate="{required:false}" ligerui="{iconItems:[{icon:'role',click:draw}]}" />
            </td>
        </tr>
        <tr>    
	   		<td class="l-t-td-left">描述：</td>
            <td class="l-t-td-right">
            	<input name="info" id="info" type="text" ltype="text" validate="{required:false,maxlength:2000}" />
            </td>
        </tr>
        <tr>
        </tr>
    </table>
</form>
</body>
</html>
