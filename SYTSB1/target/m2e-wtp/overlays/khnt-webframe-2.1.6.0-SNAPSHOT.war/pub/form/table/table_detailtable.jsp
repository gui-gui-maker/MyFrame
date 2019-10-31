<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head pageStatus="${param.pageStatus}">
		<title></title>

		<!-- 每个页面引入，页面编码、引入js，页面标题 -->
		<%@include file="/k/kui-base-form.jsp"%>

		<script type="text/javascript">
		      var pageStatus = "${param.pageStatus}";
		
		  	$(function() {
		    $("#form1").initForm({
		      toolbar:[   
               {text:'保存', id:'saveSysFormTable',icon:'save', click:save }], 
		    	success : function(resp) {//处理成功
				if (resp.success) {
					top.$.notice("保存成功！");
					$("#id").val(resp.data.id);
					parent.gotoColumn(resp.data.id,pageStatus);
					
				}else{
					top.$.notice("保存失败："+resp.msg);
				} 
			},  getSuccess:function(res){    
      
                }    
		    });
		    function save() { 
                  $('#form1').submit(); 
               } 
	});
    </script>

	</head>
	<body>


		<div>

			<form id="form1" action="pub/form/table/save.do"
				getAction="pub/form/table/detail.do?id=${param.tableId}"
				method="post" name="form1">


				<table border="0" cellspacing="0" cellpadding="3"
					style="width: 100%; height: 100%" class="l-detail-table">
					<tr>
						<input type="hidden" name="id" id="id" />
						<td class="l-t-td-left">
							表名：
						</td>
						<td class="l-t-td-right">
							<input name="tableName" id="tableName"
								<c:if test="${param.pageStatus=='modify'}">readonly="readonly"</c:if>
								ltype='text' validate="{maxlength:50,required:true}" />
						</td>
						<td class="l-t-td-left">
							注释：
						</td>
						<td class="l-t-td-right">
							<input name="tableMome" id="tableMome" ltype='text'
								validate="{maxlength:50,required:true}" />
						</td>
					</tr>

				</table>
			</form>

		</div>

	</body>
</html>
