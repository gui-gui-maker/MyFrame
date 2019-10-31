
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="${param.status}">
<title></title>
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="app/insing/report_hall.js"></script>
<script type="text/javascript">

		var pageStatus = "${param.status}";
		//收费情况
		var charge = <u:dict code="tzsb_charge_situation"/>
		//设备类别（大类）
		var deviceType=<u:dict code ="DEVICE_TYPE3"/>
		//设备名称（小类）
		var deviceName=<u:dict code ="DEVICE_TYPE3_ITEM"/>
		//部门名称
		var unitname=<u:dict sql="select t.id, t.parent_id, t.id code, t.org_name text from sys_org t where t.parent_id is not null  and t.status='used' and t.org_code like 'jd%' and t.org_code!='jd6'"/>
			$(function() {
				createDegreeGrid();
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
						icon : 'cancel',
						click : close
					} ],
					getSuccess : function(res) {
						degreeGrid.loadData({
							Rows : res.data["hallPara"]
						});
						
					}
				});
			
			
			})
		
		function close(url)
			{	
				 api.close();
			}
			function save(url)
			{
				
					url = "reportHallAction/saveBasic.do";
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
			        if(degreeGrid.getData().length==0){
						$.ligerDialog.warn('至少添加一条流转数据！');
			     		return;
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

	<form id="form1" getAction="reportHallAction/detail.do?id=${param.id}">

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

					<td class="l-t-td-left">报检公司名称：</td>
					<td class="l-t-td-right"><input name="com_name"
						validate="{required:true,maxlength:200}" ltype='text' /></td>



					<td class="l-t-td-left">检验类别：</td>
					<td class="l-t-td-right"><u:combo name="inspection_type"
							code="MO_BASE_INSPECT_TYPE" validate="required:true" /></td>
				</tr>

				
				<tr>
				<td class="l-t-td-left">所属区域：</td>
					<td class="l-t-td-right">
					<input type="text" id="area_code" name="area_code" ltype="select" validate="{required:true}"  ligerui="{
						//initValue:'511101',
						readonly:true,
						tree:{checkbox:false,data: <u:dict sql="select id,parent_id pid,regional_code code, regional_name text from V_AREA_CODE t where t.ID in ('510104','510106','510109','510122','510189')"/>}
						}"/>
				</td>
				<td class="l-t-td-left">单位地址：</td>
					<td class="l-t-td-right"><input name="dep_address"
						validate="{required:true,maxlength:100}" ltype='text' /></td>
						
				</tr>
				<tr>
				<td class="l-t-td-left">联系人：</td>
					<td class="l-t-td-right"><input name="contant_person"
						validate="{required:true,maxlength:10}" ltype='text' /></td>
				<td class="l-t-td-left">联系人手机：</td>
				<td class="l-t-td-right" ><input name="contant_phone"
						validate="{required:true,maxlength:24}" ltype='text' /></td>
				</tr>
				<tr>
				<!-- <td class="l-t-td-left">受理方式:</td>

				<td class="l-t-td-right" colspan="3">
					<input type="radio" name="com_op" id="com_op" ltype="radioGroup"
						ligerui="{ initValue:1,data: [  { text:'书面受理', id:'1' }, { text:'电话受理', id:'2' } , { text:'网上受理', id:'3' }  ] }"/>	
				</td> -->

				</tr>

				<tr>
				<td class="l-t-td-left">备注：</td>

				<td class="l-t-td-right" colspan="3"><textarea name="remark"
						cols="60" rows="2" class="l-textarea"></textarea></td>

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
