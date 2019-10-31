
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="${param.status}">
<title></title>
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="app/inspection/inspection_hall.js"></script>
<script type="text/javascript">
		
		var pageStatus = "${param.status}";
		
		//收费情况
		var charge = <u:dict code="charge_situation"/>
		//设备类别
		var deviceType=<u:dict sql="select t.id,t.code_table_values_id pid,t.value code,t.name text from PUB_CODE_TABLE_VALUES t,pub_code_table t1 where t.code_table_id=t1.id and   t1.code = 'device_classify' and t.code_table_values_id is null"/>
		//部门名称
		var unitname=<u:dict sql="select t.id, t.parent_id, t.id code, t.org_name text from sys_org t where t.parent_id is not null"/>
			$(function() {
				createDegreeGrid();
				//createJzorxsGrid();
				$("#form1").initForm({ //参数设置示例
					toolbar : [ {
						text : '保存',
						//id : 'save',
						icon : 'save',
						click : save
					}
				
					, 
					{
						text : '关闭',
						//id : 'close',
						icon : 'cancel',
						click : close
					} ],
					getSuccess : function(res) {
						degreeGrid.loadData({
							Rows : res.data["hallPara"]
						});
						
						//$("#form1").setValues();
					}
				});
			
			
			})
		
		function close(url)
			{	
				 api.close();
			}
			function save(url)
			{
				
					url = "inspection/basic/saveBasic.do";
				//验证表单数据
				if($('#form1').validate().form())
				{
					var formData = $("#form1").getValues();
			        var data = {};
			        data = formData;
			        //验证grid
			        if(!validateGrid(degreeGrid))
					{
						return false;
					}

			        data["hallPara"] = degreeGrid.getData();
			       
			     
			        $("body").mask("正在保存数据，请稍后！");
			        $.ajax({
			            url: url,
			            type: "POST",
			            datatype: "json",
			            contentType: "application/json; charset=utf-8",
			           	data: $.ligerui.toJSON(data),
			            success: function (data, stats) {
			            	$("body").unmask();
			                if (data["success"]) {
			                	if(api.data.window.Qm)
			                	{
			                		api.data.window.Qm.refreshGrid();
			                	}
			                    top.$.dialog.notice({content:'保存成功'});
			                    api.close();
			                }else
			                {
			                	$.ligerDialog.error('提示：' + data.msg);
			                }
			            },
			            error: function (data,stats) {
			            	$("body").unmask();
			                $.ligerDialog.error('提示：' + data.msg);
			            }
			        });
				}
			}
			
	</script>
</head>
<body>

	<form id="form1" getAction="inspection/basic/detail.do?id=${param.id}">

		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>基本情况</div>
			</legend>
			<table border="1" cellpadding="3" cellspacing="0" width=""
				class="l-detail-table">

				<tr>
					<input id="hall_id" name="id" type="hidden" />

					<input id="hall_no" name="hall_no" type="hidden" />

					<input id="reg_date" name="reg_date" type="hidden" />

					<td class="l-t-td-left">受检单位名称:</td>
					<td class="l-t-td-right"><input name="com_name"
						validate="{required:true,maxlength:300}" ltype='text' /></td>



					<td class="l-t-td-left">检验类别:</td>
					<td class="l-t-td-right"><u:combo name="inspection_type"
							code="BASE_CHECK_TYPE" validate="required:true" /></td>




					</td>





				</tr>
				<td class="l-t-td-left">备注:</td>

				<td class="l-t-td-right" colspan="3"><textarea name="remark"
						cols="60" rows="4" class="l-textarea"></textarea></td>

				</tr>
			</table>
		</fieldset>

		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>流转情况</div>
			</legend>
			<div id="degree"></div>
		</fieldset>

	</form>
</body>
</html>
