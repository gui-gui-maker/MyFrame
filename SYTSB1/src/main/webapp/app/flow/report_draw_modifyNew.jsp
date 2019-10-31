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
	            { text:'保存', id:'saveAdd',icon:'save', click:saveAdd},
	          
	            { text:'关闭', id:'close',icon:'cancel', click:close}
	        ],
	        getSuccess:function(res){
	        }
	    });
		function saveAdd(){
			var id="${param.id}";
			var pulldown_op=$("#pulldown_op").val();
			var linkmode=$("#linkmode").val();
			$.ajax({
				type:'POST',
				url:'report/draw/updateReportPeople.do?ids='+id+'&pulldown_op='+encodeURI(encodeURI(pulldown_op))+'&linkmode='+linkmode,
				data:{},
				dataType:'JSON',
				success:function(resp){
					if(resp){
						top.$.notice("保存成功！");
						api.close();
						api.data.window.submitAction();
					}else{
						top.$.notice("保存失败！");
					}
				}
			})
			
	   	}
	   	
	   	function close(){
	        api.close();
	    }
	   	$.ajax({
			type:'POST',
			url:'payment/payInfo/selectReportSn.do?reportSn=${param.report_sn}',
			data:{},
			dataType:'JSON',
			success:function(resp){
				//alert(JSON.stringify(resp));
				$("#report_sn").val(resp.data);
			}
		})
	});
	
	function tels(){
		var linkmode=$("#linkmode").val();
		if(linkmode==''){
			$.ligerDialog.error("亲，请输入手机号码！");
		}else{
			var isPhone = /^1[3|4|5|7|8|9][0-9]{9}$/;
			if(!isPhone.test(linkmode)){
				$.ligerDialog.error("亲，请输入正确的手机号码！");
			}
		}
		
	}
	
</script>
</head>
<body>



	<form name="formObj" id="formObj" method="post" >
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>报告领取记录</div>
			</legend>
			<table width="100%">		
				<tr>
					<td class="l-t-td-left">领取人：</td>
					<td class="l-t-td-right">
						<input name="pulldown_op" id="pulldown_op" type="text" ltype='text'  validate="{maxlength:20,required:true}" />
					</td>
					<td class="l-t-td-left">联系电话：</td>
					<td class="l-t-td-right">
						<input name="linkmode" id="linkmode" type="text" ltype="text" validate="{maxlength:40}" onchange="tels();"/>
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">报告书编号：</td>
					<td class="l-t-td-right" colspan="3">
						<!-- <input name="report_sn" id="report_sn" readonly="readonly" type="text" ltype="text" validate="{maxlength:200}"/> -->
						<textarea name="report_sn" id="report_sn" rows="10" cols="25" readonly="readonly" class="l-textarea" validate="{required:true}" ></textarea>
					</td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>
