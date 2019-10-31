<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head pageStatus="${pageStatus}">
		<title>用户信息编辑、查看</title>
		<!-- 每个页面引入，页面编码、引入js，页面标题 -->

		<%@include file="/k/kui-base-form.jsp"%>
	<script type="text/javascript" src="pub/rbac/js/area.js"></script>
		<script type="text/javascript">
	$(function() {
		$("#formObj").initForm({
		      toolbar:[   
                    {text:'保存', id:'saveSysFormTable',icon:'save', click:save },
                    {text:'关闭', id:'close',icon:'cancel', click:close } 
                ], 
			success : function(resp) {//处理成功
				if (resp.success) {
					top.$.notice("保存成功！");
					api.data.window.submitAction();//执行列表页面函数
					api.close();
				} 
			},  getSuccess:function(res){    
             
                }    
		});
		    function save() { 
	              var comboxInfo="${comBoxInfo}";
	              var comboxInfoArr =comboxInfo.split(","); 
		          for(var i=0;i<comboxInfoArr.length;i++)
		          {
		              if(comboxInfoArr[i]!="")
			          {
			          $("#"+comboxInfoArr[i]+"_hidden_value").val( $("#"+comboxInfoArr[i]).val());
			          }
		          }
                  $('#formObj').submit(); 
               } 
         
            function close(){
                api.close();
            }
	     
	});


  
	</script>

	</head>
	<body>
		<form name="formObj" id="formObj" method="post"
			action="pub/form/data/savCommonForm.do?formId=${param.formId}">
			<fieldset>


				<table id="tab1" border="0" cellpadding="3" cellspacing="0" width=""
					class="l-detail-table">
					${initFormInfo}
				</table>
			</fieldset>

		</form>
	</body>
</html>
