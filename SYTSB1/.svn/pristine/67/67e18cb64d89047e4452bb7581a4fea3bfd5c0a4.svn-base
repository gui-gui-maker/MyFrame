<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="${param.status}">
<title>任务分配</title>
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="app/insing/report_hall.js"></script>
<script type="text/javascript">
	var pageStatus = "detail";
	//收费情况
	var charge = <u:dict code="tzsb_charge_situation"/>
	//设备类别（大类）
	var deviceType=<u:dict code ="DEVICE_TYPE3"/>
	//设备名称（小类）
	var deviceName=<u:dict code ="DEVICE_TYPE3_ITEM"/>
	//部门名称
	var unitname=<u:dict sql="select t.id, t.parent_id, t.id code, t.org_name text from sys_org t "/>
		$(function() {
			createDegreeGrid();
			$("#form1").initForm({ //参数设置示例
				toolbar : [ {
					text : '保存',
					icon : 'save',
					click : save
				},{
					text : '关闭',
					icon : 'cancel',
					click : close
				} ],
				getSuccess : function(res) {
					degreeGrid.loadData({
						Rows : res.data.paraList
					});
				}
			});
		})
	
	function close(url){	
			 api.close();
		}
	function save(url)
	{
		var formData = $("#base").getValues();
		var inc_time=$('#inc_time').val();
		var op_ids ="";//$('#inc_op_id').ligerGetComboBoxManager().getValue();
		var check_ids=$('#check_op_ids').ligerGetComboBoxManager().getValue();
		var op_name="";//$('#inc_op_name').val();
		var check_name =$('#check_op_name').val();
		var inspection_type =$('#inspection_type').val();
		var area_code =$('#area_code_val').val();
		var dep_address =$('#dep_address').val();
		var contant_person =$('#contant_person').val();
		var contant_phone =$('#contant_phone').val();
		var com_op =$('#com_op').val();
		var remark =$('#remark').val();
		url = "reportHallAction/savePlan.do?hall_para_id=${param.id}&hall_id=${param.hall_id}";
		//验证表单数据
		if($('#form1').validate().form())
		{
	        $("body").mask("正在保存数据，请稍后！");
	        $.post(url,{"op_ids":op_ids,"check_ids":check_ids,"op_name":op_name,"check_name":check_name,"inc_time":inc_time,"inspection_type":inspection_type,"area_code":area_code,"dep_address":dep_address,"contant_person":contant_person,"contant_phone":contant_phone,"com_op":com_op,"remark":remark}, function(data){
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
			},'json');
	        
		}
	}
	function setValue(valuex,name){
		if(valuex==""){
			return;
		}
		if(name=='inc_op_name'){
		$('#inc_op_name').val(valuex)
		}
		if(name=='check_op_name'){
			$('#check_op_name').val(valuex)
			}
	}
		
</script>
</head>
<body>
<form id="form1"  getAction="reportHallAction/getDetail.do?id=${param.id}&hall_id=${param.hall_id}">
<c:if test='${param.status=="modify"}'>	
	<fieldset class="l-fieldset">
		<legend class="l-legend">
			<div>
				基本情况
			</div>
		</legend>
       <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table" >
          <tr> 
    		<input id="hall_id" name="id" type="hidden" />
    		<input id="hall_no" name="hall_no" type="hidden" />
    		<input id="reg_date" name="reg_date" type="hidden" />
            <td class="l-t-td-left">受检单位名称:</td>
			<td class="l-t-td-right"><input name="com_name" validate="{required:true,maxlength:300}" ltype='text' ligerui="{disabled:true}" /></td>
			<td class="l-t-td-left">检验类别:</td>
			<td class="l-t-td-right"><u:combo name="inspection_type" code="BASE_INSPECT_TYPE" validate="required:false" /></td>
		  </tr>
		  <tr>
			<td class="l-t-td-left">所属区域:</td>
			<td class="l-t-td-right"><input type="text" id="area_code" name="area_code" ltype="select" validate="{required:true}"  ligerui="{
						//initValue:'511101',
						readonly:true,
						tree:{checkbox:false,data: <u:dict sql="select id,parent_id pid,regional_code code, regional_name text from V_AREA_CODE"/>}
						}"/>
			</td>
			<td class="l-t-td-left">单位地址:</td>
			<td class="l-t-td-right"><input id="dep_address" name="dep_address"
					validate="{required:false,maxlength:300}" ltype='text' /></td>
					
			</tr>
			<tr>
				<td class="l-t-td-left">联系人:</td>
					<td class="l-t-td-right"><input name="contant_person"
						validate="{required:false,maxlength:10}" ltype='text' /></td>
				<td class="l-t-td-left">联系人手机:</td>
				<td class="l-t-td-right" ><input name="contant_phone"
						validate="{required:false,maxlength:24}" ltype='text' /></td>
				</tr>
				<!-- <tr>
				<td class="l-t-td-left">受理方式:</td>
				<td class="l-t-td-right" colspan="3">
				<input type="radio" name="com_op" id="com_op" ltype="radioGroup"
							ligerui="{ initValue:1,data: [  { text:'书面受理', id:'1' }, { text:'电话受理', id:'2' } , { text:'网上受理', id:'3' }  ] }"/>	
				</td>
				</tr> -->
				<tr>
				<td class="l-t-td-left">备注:</td>
				<td class="l-t-td-right" colspan="3"><textarea id="remark" name="remark"
						cols="60" rows="4" class="l-textarea"></textarea></td>
				</tr>
		</table>
	</fieldset>
	<fieldset class="l-fieldset" >
		<legend class="l-legend">
			<div>
				流转情况
			</div>
		</legend>
		<div id="degree"></div>
	</fieldset>
</c:if>		
		<fieldset class="l-fieldset" >
			<legend class="l-legend">
				<div>
				任务分配
				</div>
			</legend>
			   <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table" id="base">
	      	<tr>
	      		<!-- 
				<td class="l-t-td-left">项目负责人：</td>
				<td class="l-t-td-right">
				<input type="text"  name="inc_op_id" id="inc_op_id"  ltype="select" onchange="setValue(this.value,'inc_op_name')" validate="{required:true}"
				ligerui="{
				readonly:true,
				tree:{checkbox:false,data: <u:dict sql="select t.id code,t.name text from SYS_USER t where t.org_id='${param.org_id}' "/>}
				}"/>
				<input type="hidden"  name="inc_op_name" id="inc_op_name">
				</td>
				 -->
				 <td class="l-t-td-left">参检人员：</td>
				<td class="l-t-td-right">
				<input type="text" name="check_op_ids" id="check_op_ids" ltype="select" onchange="setValue(this.value,'check_op_name')" validate="{required:true}" ligerui="{
				readonly:true,
				tree:{checkbox:true,data: <u:dict sql="select t.id, t.pid, t.code, t.text from (select o.id as id,o.id as code,o.org_code as tcode,o.ORG_NAME as text,o.PARENT_ID as pid from sys_org o union select u.id as id,u.id as code,u.ACCOUNT as tcode,u.NAME as text,u.ORG_ID as pid from sys_user u) t start with t.id like '${param.org_id}%' connect by t.pid = prior t.id ORDER BY T.TCODE"/>}
				}"/>
				<input type="hidden"  name="check_op_name" id="check_op_name">
				
				</td>
				<td class="l-t-td-left">检验日期：</td>
				<td class="l-t-td-right"><input name="inc_time" id="inc_time"
				type="text" ltype="date" validate="{required:true}"
				ligerui="{initValue:'',format:'yyyy-MM-dd'}" /></td>
				</tr>
</table>
</fieldset>
		</form>
	</body>
</html>
