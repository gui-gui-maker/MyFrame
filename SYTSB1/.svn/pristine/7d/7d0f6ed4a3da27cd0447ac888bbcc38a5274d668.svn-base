<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>订餐订单信息</title>
<%@ include file="/k/kui-base-form.jsp"%>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="text/javascript" src="app/common/js/idCard.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
	$(function() {
	    $("#formObj").initForm({
			success: function (response) {//处理成功
	    		if (response.success) {
	            	top.$.dialog.notice({
	             		content: "保存成功！"
	            	});
	          		//保存基本信息（主表）后，id未自动赋值，故此处手动赋值
	          		$("#id").attr("value",response.data.id);
	         		api.data.window.refreshGrid();
	            	api.close();
	    		} else {
	           		$.ligerDialog.error('保存失败！<br/>' + response.msg);
	      		}
			}, getSuccess: function (response){
				//alert(">>>>>>");
			}, toolbar: [
	      		{
	      			text: "保存", icon: "save", click: function(){
	      				//表单验证
				    	if ($("#formObj").validate().form()) {
				    		//if(checkBasic()){
				    			if(confirm("确定保存？")){
				    				//表单提交
				    				$("#formObj").submit();
					    		}
				    		//}				    		
				    	}
	      			}
	      		},
				{
					text: "关闭", icon: "cancel", click: function(){
						api.close();
					}
				}
			], toolbarPosition: "bottom"
		});	 
	});	 
	
	function closewindow(){
		api.close();
	}
</script>

</head>
<body>
	<div title="评价">
		<form id="formObj" name="formObj" method="post" action="dining/eval/saveEval.do" getAction="dining/eval/detailEval.do?id=${param.id }">
		 	<input type="hidden" name="id" id="id"/>
		 	<input type="hidden" name="pubm_id" value="${param.id}"/>
		    <table cellpadding="3" cellspacing="0" class="l-detail-table">
		        <tr>
		           <td class="l-t-td-left">评价等级：</td>
		           <td class="l-t-td-right"><u:combo name="grade" code="food_evaluation_grade" validate="required:true"/></td>
		        </tr>
				<tr>
		           <td class="l-t-td-left">评价内容：</td>
		           <td class="l-t-td-right"><textarea name="content" row="15" col="20" validate="maxLength:512"></textarea></td>
		        </tr>
				<tr>
		           <td class="l-t-td-left">期望菜品：</td>
		           <td class="l-t-td-right"><textarea name="expect_food" row="10" col="20" validate="maxLength:100"></textarea></td>
		        </tr>
		    </table> 
		</form>
	</div>
</body>
</html>
