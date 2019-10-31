<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="java.util.Date"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="com.khnt.utils.DateUtil"%>
<%@page import="com.khnt.utils.StringUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%
	CurrentSessionUser user = SecurityUtil.getSecurityUser();
	String cur_user_name = user.getSysUser().getName();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<title></title>
	<!-- 每个页面引入，页面编码、引入js，页面标题 -->
	<%@include file="/k/kui-base-form.jsp"%>
	<head>
	<script type="text/javascript">			
		$(function() {		
			$("#form1").initForm({ //参数设置示例
				toolbar : [ {
					text : '保存',
					//id : 'save',
					icon : 'save',
					click : save
				}, {
					text : '关闭',
					//id : 'close',
					icon : 'cancel',
					click : close
				} ],
				getSuccess : function(res) {
						
				}
			});
		})
		
		function close(){	
			api.close();
			api.data.window.showBB();
		}
			
		function save(){
			var is_pass =$('#is_report_confirm').ligerGetRadioGroupManager().getValue();
			if(is_pass=="2"){
				if($('#report_confirm_remark').val()==null||$('#report_confirm_remark').val()==undefined||$('#revise_remark').val()==""){
					top.$.dialog.notice({content:'请填写退回原因！'});
					return;
				}
			}

			url = "report/item/record/batchCheck.do?ids=${param.ids}";					
			//验证表单数据
			if($('#form1').validate().form()){
				var formData = $("#form1").getValues();
			    var data = {};
			    data = formData;

				$("body").mask("正在保存数据，请稍后！");
				$.ajax({
			    	url: url,
			   		type: "POST",
			   		datatype: "json",
			      	data: {dataStr : $.ligerui.toJSON(data)},
					success: function (resp, stats) {
			        	$("body").unmask();
			                if (resp["success"]) {
			                	if(resp["data"] == "1"){
			                		if(api.data.window.Qm){
				                		api.data.window.Qm.refreshGrid();
				                	}
				                    top.$.dialog.notice({content:'保存成功'});
				                    api.data.window.api.data.window.Qm.refreshGrid();
				                    api.data.window.api.close();
				                    api.close();			                	
			                	}else{
			                		top.$.dialog.notice({content:'保存成功'});
				                    api.data.window.left._opid${param.opid}.innerHTML="<img src='k/kui/images/icons/16/check.png' border='0' >";
				                    close();
			                	}
			                }else
			                {
			                	$.ligerDialog.error('提示：' + resp.msg);
			                }
			            },
			            error: function (resp,stats) {
			            	$("body").unmask();
			                $.ligerDialog.error('提示：' + resp.msg);
			            }
			        });
				}
			}
	</script>
	</head>
	<body>		
	<form id="form1"  >
		<table border="1" cellpadding="0" cellspacing="0" width="" class="l-detail-table">
			<tr>
				<td class="l-t-td-left">校核结果：</td>
				<td class="l-t-td-right" >
					<input type="radio" name="is_report_confirm"  id="is_report_confirm" ltype="radioGroup" validate="{required:false}"
						ligerui="{value:'1',data: [ { text:'通过', id:'1' }, { text:'不通过', id:'2' } ] }"/></td>
			</tr>
			<tr>
				<td class="l-t-td-left">备注：</td>
				<td class="l-t-td-right"  colspan="2">
					<textarea name="report_confirm_remark" id="report_confirm_remark" rows="6" cols="" ext_type="string" 
						ext_maxLength="200" ext_name="备注" isNull="Y" class="area_text" onfocus="this.innerHTML='';">请在此处填写校核不通过的原因！</textarea>
				</td>
			</tr>
		</table>
	</form>	
	</body>
</html>
