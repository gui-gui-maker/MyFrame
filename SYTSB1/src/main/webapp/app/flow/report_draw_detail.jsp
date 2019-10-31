﻿<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>报告领取</title>
<%@ include file="/k/kui-base-form.jsp"%>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
<script type="text/javascript" src="app/common/js/idCard.js"></script>
<script type="text/javascript" src="app/common/js/windowprint.js"></script>
<%
	String pageStatus = request.getParameter("status");
%>
<script type="text/javascript">
	var pageStatus = "${param.status}";		
	$(function() {
	    $("#formObj").initForm({
			success: function (response) {//处理成功
	    		if (response.success) {
	            	/*top.$.dialog.notice({
	             		content: "保存成功！"
	            	});*/
	            	//$.ligerDialog.confirm('保存成功，现在打印？', function (yes)
					//{
                  	//	if (yes){
							top.$.dialog({
								width : $(top).width(),
								height :  $(top).height()-40,
								lock : true,
								title : "打印领取记录",
								parent: api,
								content : 'url:app/flow/docEditor.jsp?status=modify&isPrint=2',	
								data: {window: window, bean: response.printContent, op_user_name: response.op_user_name}
							}).max();
	           		//	}
	           			api.data.window.submitAction();
            			api.close(); 
					//});
	    		} else {
	           		$.ligerDialog.error('保存失败！<br/>' + response.msg);
	      		}
			}, getSuccess: function (response){
			
			}, toolbar: [
	      		{
	      			text: "保存", icon: "save", click: function(){
	      				$('#formObj').attr('action','department/basic/flow_saveDrow.do?status=${param.status}&acc_id='+api.data.acc_id+'&flow_num='+api.data.flow_num);
	      				$("#inspection_info_id").val(api.data.id);
	      				$("#area_name").val(api.data.area_name);
	      				$("#job_unit").val(api.data.report_com_name);
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
	    $('#formObj').attr('action','department/basic/flow_saveDrow.do?status=${param.status}&acc_id='+api.data.acc_id+'&flow_num='+api.data.flow_num);
		$("#inspection_info_id").val(api.data.id);
		$("#area_name").val(api.data.area_name);
		$("#job_unit").val(api.data.report_com_name);
		//输入领取人姓名弹出联系人手机号选项
		$('#pulldown_op').autocomplete("report/draw/getContactsInfo.do?com_name="+encodeURI(encodeURI(api.data.report_com_name)), {
                    max: 12,    //列表里的条目数
                    minChars: 1,    //自动完成激活之前填入的最小字符
                    width: 200,     //提示的宽度，溢出隐藏
                    scrollHeight: 300,   //提示的高度，溢出显示滚动条
                    scroll: false,   //当结果集大于默认高度时是否使用卷轴显示
                    matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
                    autoFill: false,    //自动填充
                    formatItem: function(row, i, max) {
                        return row.pulldown_ops + '   ' + row.linkmodes;
                    },
                    formatMatch: function(row, i, max) {
                        return row.pulldown_ops + row.linkmodes;
                    },
                    formatResult: function(row) {
                        return row.pulldown_ops;
                    }
                }).result(function(event, row, formatted) {
                	document.getElementById("linkmode").value = row.linkmodes
                });
	});
	
	function closewindow(){
		api.close();
	}
	
	function checkBasic(){
		var idNo = $("#idcard").val();
		if("" != idNo){
			if(!validateID(idNo)){
	        	$.ligerDialog.alert("您输入的身份证号码无效，请检查！");
	        	$("#idcard").focus();
	        	return false;
	        }
		} 
		return true;
	}
	
	function checkID(el) {
	        if(!validateID(el.value)){
	        	$.ligerDialog.alert("您输入的身份证号码无效，请检查！");
	        	$("#idcard").focus();
	        }
	}
	
	//验证身份证号码是否正确
	function validateID(value){
			var checkFlag = new clsIDCard(value);
	        if(checkFlag.IsValid()){
	        	//checkFlag.GetBirthDate()	此方法返回的年月日中月份不包含'0'，例如：1988-7-21
	        	//showBirthdayAndSex(value);	//根据身份证号码自动提取出生年月、性别
	        	return true;
	        }else{
	        	return false;
	        }	
	}
	
	function initInfo(){
		$.getJSON("report/draw/getComInfo.do?id="+api.data.id, function(resp){
			if(resp.success){
				$('#job_unit').val(resp.data.report_com_name);	// 工作单位
			}
		})
		$('#formObj').attr('action','department/basic/flow_saveDrow.do?status=${param.status}&acc_id='+api.data.acc_id+'&flow_num='+api.data.flow_num);
		$("#inspection_info_id").val(api.data.id);
		$("#area_name").val(api.data.area_name);
		$("#job_unit").val(api.data.report_com_name);
		
		/* $.getJSON("report/draw/getContactsInfo.do?com_name="+encodeURI(encodeURI(api.data.report_com_name)), function(resp){
			if(resp.success){
				$('#pulldown_op').val(resp.data.pulldown_op);	// 领取人
				$('#linkmode').val(resp.data.linkmode);			// 联系电话
			}
		})	 */	
	}
</script>
</head>
<body <%if("add".equals(pageStatus)){%>onload="initInfo();"<%}%>>
	<form name="formObj" id="formObj" method="post" action="">
		<input type="hidden" name="inspectionInfo.id" id="inspection_info_id" value=""/>
		<input type="hidden" name="area_name" id="area_name" value=""/>
		<input type="hidden" name="job_unit" id="job_unit" value=""/>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>报告领取</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">领取人：</td>
					<td class="l-t-td-right" colspan="3"><input name="pulldown_op" id="pulldown_op" type="text" ltype='text' validate="{required:true,maxlength:200}" /></td>
				</tr>
				<tr>
					<td class="l-t-td-left">联系电话：</td>
					<td class="l-t-td-right" colspan="3"><input name="linkmode" id="linkmode" type="text" ltype="text" validate="{required:true,maxlength:200}"/></td>			<!--  onchange="checkID(this);" -->
				</tr>
				<tr>
					<td class="l-t-td-left">报告书编号：</td>
					<td class="l-t-td-right" colspan="3">
						<input name="report_sn" id="report_sn" type="text" ltype="text" validate="{required:true,maxlength:200}"/>
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">备注：</td>
					<td class="l-t-td-right" colspan="3"><textarea name="remark" id="remark" rows="2" cols="25" class="l-textarea" validate="{maxlength:200}"></textarea></td>		
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>