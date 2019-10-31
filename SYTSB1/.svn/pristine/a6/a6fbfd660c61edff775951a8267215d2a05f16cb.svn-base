<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.*" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html;charset=UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
    <title></title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/fileupload1/fileupload/js/fileupload.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript" src="app/archives/js/doc_order.js"></script>
<script type="text/javascript">
	var status="${param.pageStatus}";
	$(function(){
		
		$("#form1").initForm({ //参数设置示例
			toolbar: [
			      		{
			      			text: "保存", icon: "save", click: function(){
			      				//表单验证
						    	if ($("#form1").validate().form()) {
							    	//表单提交
							    	$("#form1").submit();
						    	}
			      			}
			      		},
						{
							text: "关闭", icon: "cancel", click: function(){
								api.close();
								api.data.window.showBB();
							}
						}
					],
					toolbarPosition: "bottom",
			success: function (response) {//处理成功
	    		if (response.success) {
			      	if(api.data.window.Qm)
			    	{
			         	api.data.window.Qm.refreshGrid();
			       	}
					top.$.dialog.notice({
	             		content: "保存成功！"
	            	});	
			     	api.close();  
	    		} else {
	           		$.ligerDialog.error('保存失败！<br/>' + response.msg);
	      		}
			},
			getSuccess : function(res) {
				console.log(res);
				if(res.data){
					$('#form1').setValues(res.data);
				}else{
					var node = api.data.node;
					$('#device_classify_id').val(node.id);
					$('#device_classify_name').ligerGetTextBoxManager().setValue(node.text);
				}
			}
		});
	});
	
    </script>
</head>

<body>
	<form id="form1" method="post" action="certificateRuleAction/save.do"
		  getAction="certificateRuleAction/detailByDeviceClassify.do?id=${param.device}">
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<input type="hidden" id="id" name="id" value=""/>
				<input type="hidden" id="device_classify_id" name="device_classify_id" value=""/>
				<tr>
					<td class="l-t-td-left">设备类别</td>
					<td class="l-t-td-right">
						<input id="device_classify_name" name="device_classify_name" type="text" ltype="text" />
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">分配规则</td>
					<td class="l-t-td-right">
						<u:combo name="certificate_rule" ltype="radioGroup" code="distribute_rule" validate="{required:true}" attribute="initValue:'1'"/>
					</td>
				</tr>
			</table>
	</form>
</body>
</html>