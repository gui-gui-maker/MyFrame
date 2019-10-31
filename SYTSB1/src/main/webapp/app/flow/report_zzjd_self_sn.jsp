<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head pageStatus="${param.status}">
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
var pageStatus="${param.pageStatus}";
var com_ids = "${param.com_ids}";

	$(function () {
	    $("#formObj").initForm({    //参数设置示例
	       toolbar:[
	            { text:'保存', id:'save',icon:'save', click:saveClose },	          
	            { text:'关闭', id:'close',icon:'cancel', click:close }
	        ],
	        getSuccess:function(resp){

	        }
	    });
	});

	function saveClose(){
    	var report_sn = $("#report_sn").val();
    	if("" == report_sn){
			$.ligerDialog.alert("请输入报告编号！");
			return;
		}else{
			 $.ajax({
		            url: "inspection/zzjd/self_sn.do?id=${param.id}&report_sn="+report_sn,
		            data: "",	//JSON.stringify(json)把json转化成字符串
		            cache:false,    
		            type: "POST",
		            datatype: "json",
		            contentType: "application/json; charset=utf-8",
		            success: function (resp) {
		                if (resp["success"]) {
		                	top.$.notice("保存成功！");
		               		api.close();
		                	api.data.window.refreshGrid();
		                } else {
		                	$.ligerDialog.error(resp.msg);
		                }
		            },
		            error: function (resp) {
		                $.ligerDialog.error(resp.msg);
		            }
		        });
		}
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
			<div>报告编号</div>
		</legend>
		<table width="100%">		
			<tr>
				<td class="l-t-td-left">报告编号：</td>
				<td class="l-t-td-right" colspan="3">
					<input type="text" id="report_sn" name="report_sn" ltype="text" validate="{required:true,maxlength:17}" />
				</td>
			</tr>
		</table>
	</fieldset>
</body>
</html>
