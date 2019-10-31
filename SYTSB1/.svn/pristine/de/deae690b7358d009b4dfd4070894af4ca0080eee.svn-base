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
		RtDrocDataOnly(fileId,null,function(result){
			$("#drawData").val(result.drawData);
			console.log("cdata:"+$("#drawData").val());
			$("#imgData").val(result.imgData);
		});
	}
	
	
	

</script>
</head>
<body>
<form id="formObj" action="com/rtd/files/saveFiles.do" getAction="com/rtd/files/detail.do?id=${param.id}">
    <input type="hidden" id="id" name="id" />
    <input type="hidden" id="pid" name="pid" />
    <input type="hidden" id="fkAttIdImg" name="fkAttIdImg" />
    <input type="hidden" id="fkAttIdDraw" name="fkAttIdDraw" />
    <input type="hidden" id="status" name="status" />
 
     <!--  操作人 -->
     <input type="hidden" id="handleUserName" name="handleUserName"/>
     <!--  操作人ID -->
     <input type="hidden" id="fkUserIdHandle" name="fkUserIdHandle"/>
      <!-- 操作部门 -->
     <input type="hidden" id="handleOrgName" name="handleOrgName"/>
     <!-- 操作部门ID -->
     <input type="hidden" id="fkOrgIdHandle" name="fkOrgIdHandle"/>   
     <!-- 操作时间 -->
     <input type="hidden" id="handleTime" name="handleTime"/>
     <!-- 是否删除(是：1、否：0) -->
     <input type="hidden" id="isDel" name="isDel"/>
     <!-- 最后修改人 -->
     <input type="hidden" id="fkUserIdLast" name="fkUserIdLast"/>
     <!--  最后修改时间(可计算耗时) -->
     <input type="hidden" id="lastHandleTime" name="lastHandleTime"/>
     
    <input type="hidden" id="imgData" name="imgData">
    
    
    <table cellpadding="3" class="l-detail-table">
        <tr>
            <td class="l-t-td-left">文件名：</td>
            <td class="l-t-td-right">
            	<input name="fileName" id="fileName" type="text" ltype="text" validate="{required:true,maxlength:100}" />
            </td>
    	</tr>
    	<tr>    
	   		<td class="l-t-td-left">文件类型：</td>
            <td class="l-t-td-right">
            	<input name="fileType" id="fileType" type="text" ltype="select" validate="{required:true,maxlength:20}" 
            	ligerui="{initValue:'file',data:[{id:'file','text':'文件'},{id:'folder','text':'文件夹'}]}"
            	/>
            </td>
        </tr>
        <tr>    
	   		<td class="l-t-td-left">文件数据：</td>
            <td class="l-t-td-right">
            	<input name="drawData" id="drawData" type="text" ltype="text" validate="{required:false}" readonly="readonly" ligerui="{iconItems:[{icon:'role',click:draw}]}" />
            </td>
        </tr>
        <tr>    
	   		<td class="l-t-td-left">描述：</td>
            <td class="l-t-td-right">
            	<input name="remark" id="remark" type="text" ltype="text" validate="{required:false,maxlength:2000}" />
            </td>
        </tr>
        <tr>
        </tr>
    </table>
</form>
</body>
</html>
