<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus = "${param.pageStatus}">
<title>固定资产信息</title>
<%@ include file="/k/kui-base-form.jsp"%>
<!-- 生成条形码JS导入 -->
<script type="text/javascript" src="app/common/lodop/LodopFuncs.js"></script>
<script type="text/javascript">
	var pageStatus = "${param.pageStatus}";
	var tbar="";
	$(function() {
		if(pageStatus=="detail"){
			tbar=[{text: "关闭", icon: "cancel", click: function(){api.close();}}];
 		}else{
 			tbar=[
 		     		{text: "保存", icon: "save", click: function(){
 		  				//表单验证
 				    	if ($("#form1").validate().form()) {
 				    		$("#form1").submit();
 				    	}else{$.ligerDialog.error('提示：' + '请将信息填写完整后保存！');}
 		  				}},
 						{text: "关闭", icon: "cancel", click: function(){api.close();}}];
 		}
		
		
		
		$("#form1").initForm({
			success: function (response) {//处理成功
				console.log(response);
	    		if (response.success) {
	            	top.$.dialog.notice({
	             		content: "保存成功！"
	            	});
					api.data.window.Qm.refreshGrid();
	            	api.close();
	    		} else {
	           		$.ligerDialog.error('保存失败！<br/>' + response.msg);
	      		}
			}, getSuccess: function (response){
				if (response.attachs != null && response.attachs != undefined)
					showAttachFile(response.attachs);
			},
			showToolbar: true,
            toolbarPosition: "bottom",
            toolbar: tbar
    	});
	});
	
</script>
</head>
<body>
	<div title="物品类型" id="formObj">
    <form id="form1" action="com/tjy2/goodsType/save.do" getAction="com/tjy2/goodsType/detail.do?id=${param.id}">
     <input type="hidden" name="id"/>
     <input type="hidden" name="createId"/>
     <input type="hidden" name="createName"/>
     <input type="hidden" name="createOrgId"/>
     <input type="hidden" name="createOrgName"/>
     <input type="hidden" name="createUnitId"/>
     <input type="hidden" name="createUnitName"/>
     <input type="hidden" name="createTime"/>
     <fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>
					物品类型
				</div>
			</legend>
       <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
       <tr>
       		<td class="l-t-td-left"></td>
       		<td class="l-t-td-right"></td>
       </tr>
       <tr>
       		<td class="l-t-td-left">物品类型名称：</td>
       		<td class="l-t-td-right" ><input name="lx_name" id="lx_name" type="text" ltype='text' validate="{required:true,maxlength:200}" /></td>
       </tr>
       <tr>
       		<td class="l-t-td-left">类型编号：</td>
       		<td class="l-t-td-right" ><input name="lx_bh" id="lx_bh" type="text" ltype='text' validate="{maxlength:200}" /></td>
       </tr>
       <tr>
       		<td class="l-t-td-left">阀值：</td>
       		<td class="l-t-td-right" ><input name="fz" id="fz" type="text" ltype='text' validate="{maxlength:200}" /></td>
       </tr>
       <tr>
		<td class="l-t-td-left">备注：</td>
		<td class="l-t-td-right" >
			<textarea name="bz"  class="l-textarea" validate="{maxlength:100}"></textarea>
		</td>						
	  </tr>
      </table>
      </fieldset>
    </form> 
</div>

</body>
</html>
