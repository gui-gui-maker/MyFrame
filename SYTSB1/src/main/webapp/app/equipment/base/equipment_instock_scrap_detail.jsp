<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>设备部分报废</title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
	$(function() {
	    $("#formObj").initForm({
			toolbar: [
				{
					text: "关闭", icon: "cancel", click: function(){
						api.close();
					}
				}
			], toolbarPosition: "bottom"
		});		
		$("input[name='_baseEquipment.eq_type-txt']").ligerComboBox().setDisabled(true);
	});
</script>
</head>
<body>
<div class="navtab">
	<div title="详情" tabId="certTab">
	<form id="detailForm" name="detailForm" method="post" action="equipment2ApplyRelationAction/scrap.do?status=${param.status}">
  	<input type="hidden" name="id" id="apply_relation_id"/>
  	<input type="hidden" name="baseEquipmentApply.id" value="${param.id}"/>
  	<input type="hidden" id="need_return" name="need_return"/>
  	<input type="hidden" id="repair_com" name="repair_com"/>
  	<input type="hidden" id="repair_price" name="repair_price"/>
  	<input type="hidden" id="repair_requirement" name="repair_requirement"/>
  	<input type="hidden" id="conduct_date" name="conduct_date"/>
  	<input type="hidden" id="apply_relation_create_by" name="create_by"/>
  	<input type="hidden" id="apply_relation_create_date" name="create_date"/>
	<table cellpadding="3" cellspacing="0" class="l-detail-table"  >
		<tr>
	       <td class="l-t-td-left">申请设备名称：</td>
			<td class="l-t-td-right">
				<input name="baseEquipment.id" id="eq_id" type="hidden" />
				<input name="baseEquipment.eq_name" id="eq_name" type="text" ltype='text' validate="{maxlength:32}" ligerui="{disabled:true}"/>
			</td>
			<td class="l-t-td-left">申请设备类别：</td>
			<td class="l-t-td-right"><u:combo name="baseEquipment.eq_type" code="BASE_EQ_TYPE" /></td>
	    </tr>
	    <tr>
	    	<td class="l-t-td-left">申请设备数量：</td>
	       	<td class="l-t-td-right"><input name="apply_count" id="apply_count" type="text" ltype='text' validate="{required:true,digits:true,maxlength:32}" ligerui="{disabled:true}"/></td>
	       	<td class="l-t-td-left">报废数量：</td>
	       	<td class="l-t-td-right"><input name="return_count" id="return_count" type="text" ltype='spinner' validate="{required:true,digits:true,maxlength:32}" /></td>
	    </tr>
	</table> 
  	<script type="text/javascript">
    $("#detailForm").initFormList({
    	success: function (response) {//处理成功
			if (response.success) {
	        	top.$.dialog.notice({
	            	content: "保存成功！"
	        	});
	       		api.data.window.refreshGrid();
	        	//api.close();
	   		} else {
	        	$.ligerDialog.error('保存失败！<br/>' + response.msg);
	   		}
		},
    	toolbar:[
			{
	      		text: "保存", icon: "save", click: function(){
	      			//表单验证
					if ($("#detailForm").validate().form()) {
						var apply_count = $("#apply_count").val();
						var return_count = $("#return_count").val();
						if(parseInt(return_count) > parseInt(apply_count)){
							$.ligerDialog.alert("报废数量不能大于申请数量，请检查！");
							return;
						}
				    	$("#detailForm").submit();
					}
	      		}
	    	}
    	],
    	root:'datalist',
        getAction:"equipment2ApplyRelationAction/getList.do?id=${param.id}",
        //getActionParam:{},	//取数据时附带的参数，一般只在需要动态取特定值时用到
        actionParam:{"baseEquipmentApply.id" : $("#formObj>[name='id']")},	//保存时会在当前表单上附加此数据，如：{fkId:$("#form1>#id")}会把Form1下的id为id的值带上去,可以是一个对象或选择器
        //delAction:'equipment2ApplyRelationAction/delete.do',	//删除数据的action
        //delActionParam:{ids:"id"},	//默认为选择行的id 
        columns:[
            //此部分配置同grid
            { display:'设备申请关联表主键', name:'id', width:'1%', hide:true},
			{ display:'设备申请主表主键', name:'fk_apply_id', width:'1%', hide:true},
			{ display:'设备基本信息表主键', name:'baseEquipment.id', width:'5%', hide:true},
            { display:'申请设备名称', name:'baseEquipment.eq_name', width:'30%'},
            { display:'申请设备类型', name:'baseEquipment.eq_type', width:'13%', render: function (item) {
                	return showEq_typeText(item["baseEquipment.eq_type"]);
            	}
            },
            { display:'申请数量', name:'apply_count', width:'10%'},
			{ display:'是否需要归还（入库）', name:'need_return', width:'20%', render: function (item) {
                	if("0" == item["need_return"]){
                		return "需要";
                	}else if("1" == item["need_return"]){
                		return "不需要";
                	}
            	}
			},
            { display:'归还（入库/报废）数量', name:'return_count', width:'20%'}
        ]
    });
    
    function showEq_typeText(val){
		if("01" == val){
        	return "检验设备";
      	}else if("02" == val){
    		return "计量器具";
     	}else if("03" == val){
   			return "办公设备";
    	}else if("08" == val){
    		return "耗材";
     	}else if("09" == val){
        	return "其他";
    	}else{
    		return val;
    	}    	
    }
    
    function showEq_typeValue(val){
		if("检验设备" == val){
        	return "01";
      	}else if("计量器具" == val){
    		return "02";
     	}else if("办公设备" == val){
   			return "03";
    	}else if("耗材" == val){
    		return "08";
     	}else if("其他" == val){
        	return "09";
    	}else{
    		return val;
    	}    	
    }
	</script>
	</form>
	</div>
	<div title="基本信息" tabId="basicTab" style="height: 400px">
	<form name="formObj" id="formObj" method="post" action="equipment2ApplyAction/save.do?status=${param.status}"
		getAction="equipment2ApplyAction/detail.do?id=${param.id}">
		<input type="hidden" name="id" id="applyid" value="${param.id}"/>
		<input type="hidden" id="apply_status" name="apply_status"/>
		<input type="hidden" id="apply_create_by" name="create_by"/>
  		<input type="hidden" id="apply_create_date" name="create_date"/>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>设备申请</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">申请类型：</td>
					<td class="l-t-td-right"><u:combo name="apply_type" code="BASE_EQ_APPLY_TYPE" validate="required:true"/></td>		
					<td class="l-t-td-left">需求时间：</td>
					<td class="l-t-td-right"><input name="apply_need_date" id="apply_need_date" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" /></td>					
				</tr>
				<tr>
					<td class="l-t-td-left">申请事由：</td>
					<td class="l-t-td-right" colspan="3"><textarea name="apply_reason" rows="2" cols="25" class="l-textarea" validate="{required:true,maxlength:1024}"></textarea></td>
				</tr>
				<tr>	
					<td class="l-t-td-left">申请人：</td>
					<td class="l-t-td-right">
						<input name="apply_id" id="apply_id" type="hidden" />
						<input name="apply_name" id="apply_name" type="text" ltype='text' validate="{required:true,maxlength:32}" onclick="selApply()" ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:function(){selApply()}}]}"/>
					</td>
					<td class="l-t-td-left">申请部门：</td>
					<td class="l-t-td-right">
						<input name="apply_unit_id" id="apply_unit_id" type="hidden" />
						<input name="apply_unit_name" id="apply_unit_name" type="text" ltype='text' validate="{required:true,maxlength:64}" ligerUi="{disabled:true}"/>
					</td>	
				</tr>
				<tr>			
					<td class="l-t-td-left">申请提交目标：</td>
					<td class="l-t-td-right">
						<input name="apply_submit_object_id" id="apply_submit_object_id" type="hidden" />
						<input name="apply_submit_object_name" id="apply_submit_object_name" type="text" ltype='text' validate="{required:true,maxlength:32}" onclick="selReceiver()" ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:function(){selReceiver()}}]}"/>
					</td>
					<td class="l-t-td-left"></td>
					<td class="l-t-td-right"></td>
				</tr>	
			</table>
		</fieldset>
	</form>
	</div>	
</div>
</body>
</html>
