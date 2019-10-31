<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head pageStatus="${param.status}">
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
var pageStatus="${param.pageStatus}";

	$(function () {
	    $("#formObj").initForm({    //参数设置示例
	       toolbar:[
	            { text:'保存', id:'save',icon:'save', click:save},
	            { text:'关闭', id:'close',icon:'cancel', click:close}
	        ],
	        getSuccess:function(res){
	        },
	        success: function (response) {//处理成功
	    		if (response.success) {
		      		top.$.dialog.notice({
			             content: "保存成功！"
					});
	            	api.data.window.refreshGrid();
	            	api.close();
	    		} else {
	           		$.ligerDialog.error('保存失败！<br/>' + response.msg);
	      		}
			}
	    });
	});
	
	function save(){
	    //验证表单数据
	    if ($("#formObj").validate().form()) {
	    	var ids = "${param.ids}";
			if(confirm("亲，确定外借报告吗？外借操作后不能撤销哦！")){
				formObj.action="report/error/record/borrowReport.do?ids="+ids;
				$("#formObj").submit();
			}		    				    		
		}
	    /*
			if($('#formObj').validate().form()){
		    	var ids = "${param.ids}";
		    	var formData = $("#formObj").getValues();
				var data = {};
				data = $.ligerui.toJSON(formData);
		        $.ajax({
		            url: "report/error/record/borrowReport.do?ids="+ids,
		            data: data,	//JSON.stringify(json)把json转化成字符串
		            cache:false,    
		            type: "POST",
		            datatype: "json",
		            contentType: "application/json; charset=utf-8",
		            success: function (data, stats) {
		                if (data["success"]) {
		                	top.$.notice("保存成功！");
		               		api.close();
		                	api.data.window.refreshGrid();
		                } else {
		                	$.ligerDialog.error(data.msg);
		                }
		            },
		            error: function (data) {
		                $.ligerDialog.error(data.msg);
		            }
		        });
		    }
		    */
	    }
	    
	   function close(){
	        api.close();
	    }
</script>
</head>
<body>
<form name="formObj" id="formObj" method="post" >
	<fieldset class="l-fieldset">
		<legend class="l-legend">
			<div>外借报告</div>
		</legend>
		<table width="100%">		
			<tr>
				<td class="l-t-td-left">外借人员：</td>
				<td class="l-t-td-right">
					<input type="text" name="borrow_user_id" id="borrow_user_id" ltype="select" validate="{required:true}"
						ligerui="{
							initValue:'',
							tree:{checkbox:false,data: <u:dict sql="select t.id code,t.name text from employee t where t.org_id='${param.org_id}' "/>}
							}"/>
				</td>	
				<td class="l-t-td-left"></td>
				<td class="l-t-td-right"></td>
			</tr>
			<tr>
				<td class="l-t-td-left">外借备注：</td>
				<td class="l-t-td-right" colspan="3">
					<textarea name="borrow_desc" id="borrow_desc" rows="3" cols="25" class="l-textarea" validate="{required:false,maxlength:1000}"></textarea>
				</td>	
			</tr>
		</table>
	</fieldset>
</body>
</html>
