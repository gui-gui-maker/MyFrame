<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>设备报废申请</title>
<%@ include file="/k/kui-base-form.jsp"%>
<%
	String status = request.getParameter("status");
%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript" src="app/common/js/render.js"></script>
<script type="text/javascript">
var applyType="${param.apply_type}";
// 检验设备类型
var category_01 = <u:dict code="BASE_EQ_JY_CATEGORY"/>
// 办公设备类型
var category_03 = <u:dict code="BASE_EQ_BG_CATEGORY"/>
// 耗材
var category_08 = <u:dict code="BASE_EQ_HC_CATEGORY"/>

	$(function() {
		chooseApplyType(${param.apply_type});
	    $("#formObj").initForm({
			success: function (response) {//处理成功
	    		if (response.success) {
	            	top.$.dialog.notice({
	             		content: "保存成功！"
	            	});
	          		//保存基本信息（主表）后，id未自动赋值，故此处手动赋值
	          		$("#applyid").attr("value",response.data.id);
	          		applyType = response.data.apply_type;
	         		api.data.window.refreshGrid();
	            	//api.close();
	    		} else {
	           		$.ligerDialog.error('保存失败！<br/>' + response.msg);
	      		}
			}, getSuccess: function (resp){
				chooseApplyType(resp.data.apply_type);
			}, toolbar: [
	      		{
	      			text: "保存", icon: "save", click: function(){
	      				//表单验证
				    	if ($("#formObj").validate().form()) {
				    		$("#formObj").submit();
				    	}
	      			}
	      		},
				{
					text: "关闭", icon: "cancel", click: function(){
						api.data.window.refreshGrid();
						api.close();
					}
				}
			], toolbarPosition: "bottom"
		});		
	});
	
	function closewindow(){
		api.close();
	}
	
	//选择申请人
	function selApply() {
		var receiverIdNode = "apply_id";
		var receiverNameNode = "apply_name";
		selectUnitOrUser(1, 0, receiverIdNode, receiverNameNode, function(
				callbackData) {
			//$("#apply_unit_id").val(callbackData.jsonObj.deptId);
		   	//$("#apply_unit_name").val(callbackData.jsonObj.deptName);
		});
	}

	//选择申请提交目标
	function selReceiver() {
		var receiverIdNode = "apply_submit_object_id";
		var receiverNameNode = "apply_submit_object_name";
		selectUnitOrUser(1, 0, receiverIdNode, receiverNameNode, function(
				callbackData) {			
		});
	}
	
	// 选择设备
	function selEquipment(){	
		top.$.dialog({
			parent: api,
			width : 800, 
			height : 550, 
			lock : true, 
			title:"选择设备信息",
			content: 'url:app/equipment/base/equipment_basic_choose_list.jsp?isScrap=1',
			data : {"parentWindow" : window}
		});
	}
	
	function callBack(data){
		$('#eq_id').val(data.id);	// 设备ID
		$('#eq_name').val(data.eq_name);	// 设备名称	
		
		if("04" != applyType){	// 报废			
			$.ligerDialog.alert("请先保存基本信息！");
			return;
		}   
		$('#eq_no').val(data.eq_no);	// 设备编号
		$('#eq_model').val(data.eq_model);	// 规格/型号
		$('#eq_manufacturer').val(data.eq_manufacturer);	// 生产厂家
		$('#eq_sn').val(data.eq_sn);	// 出厂编号
		
		if("耗材" == data.eq_type){
			$('#apply_count').val("");
			$('#apply_count').removeAttr('readonly');   	 			  
		}else{
			$('#apply_count').val("1");
			$('#apply_count').attr('readonly', 'true'); 
			if("04" == applyType){
				if("检验设备" == data.eq_type || "计量器具" == data.eq_type){
					$("#eq_jdTr").attr('style','display:block');
					$('#eq_accurate').val(data.eq_accurate);	// 精度
				}else{
					$("#eq_jdTr").attr('style','display:none');
					$('#eq_accurate').val("");	
				}
			}else{
				$.ligerDialog.alert("请先保存基本信息！");
				return;
			}       
		}    
	}
	
	function chooseApplyType(apply_type){
		if("03" == apply_type){	// 03：借用
			document.getElementById("requiredDiv").style.display="block";
			document.getElementById("requiredDiv2").style.display="block";
			document.getElementById("requiredDiv3").style.display="none";  
			document.getElementById("requiredDiv4").style.display="none";  
		}else{
			document.getElementById("requiredDiv3").style.display="block";
			document.getElementById("requiredDiv4").style.display="block";   
			document.getElementById("requiredDiv").style.display="none";
			document.getElementById("requiredDiv2").style.display="none";      
		}
		applyType = apply_type;
	}
</script>
</head>
<body>
<div class="navtab">
	<div title="基本信息" tabId="basicTab" style="height: 400px">
	<form name="formObj" id="formObj" method="post" action="equipment2ApplyAction/save.do?status=${param.status}"
		getAction="equipment2ApplyAction/detail.do?id=${param.id}">
		<input type="hidden" name="id" id="applyid" value="${param.id}"/>
		<input type="hidden" name="apply_type" id="apply_type" value="${param.apply_type}"/>
		<input type="hidden" id="apply_status" name="apply_status"/>
		<input type="hidden" id="apply_create_by" name="create_by"/>
  		<input type="hidden" id="apply_create_date" name="create_date"/>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>设备报废申请</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<!-- 
					<td class="l-t-td-left">申请类型：</td>
					<td class="l-t-td-right"><u:combo name="apply_type" code="BASE_EQ_APPLY_TYPE" validate="required:true" attribute="onSelected:function(){chooseApplyType('')}"/></td>
					 -->		
					<td class="l-t-td-left">报废原因：</td>
					<td class="l-t-td-right" colspan="3"><textarea name="apply_reason" rows="2" cols="25" class="l-textarea" validate="{required:true,maxlength:1024}"></textarea></td>				
				</tr>
				<tr>
					<td class="l-t-td-left">需求开始时间：</td>
					<td class="l-t-td-right"><input name="apply_need_date" id="apply_need_date" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" /></td>	
					<td class="l-t-td-left" id="requiredDiv" style="display:none;">需求结束时间：</td>
					<td class="l-t-td-right" id="requiredDiv2" style="display:none;"><input name="apply_end_date" id="apply_end_date" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" /></td>	
					<td class="l-t-td-left" id="requiredDiv3" style="display:none;"></td>
					<td class="l-t-td-right" id="requiredDiv4" style="display:none;"></td>				
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
						<input name="apply_unit_name" id="apply_unit_name" type="text" ltype='text' validate="{required:false,maxlength:64}" ligerUi="{disabled:true}"/>
					 
					
					</td>	
				</tr>
				<tr>			
					<td class="l-t-td-left">申请提交目标：</td>
					<td class="l-t-td-right">
						<input name="apply_submit_object_id" id="apply_submit_object_id" type="hidden" />
						<input name="apply_submit_object_name" id="apply_submit_object_name" type="text" ltype='text' validate="{required:true,maxlength:32}" onclick="selReceiver()" ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:function(){selReceiver()}}]}"/>
					</td>
					<c:if test="${'detail' eq param.status}">
					<td class="l-t-td-left">审核人：</td>
					<td class="l-t-td-right">
						<input name="apply_audit_name" id="apply_audit_name" type="text" ltype='text' />
					</td>
					</c:if>
				</tr>	
				<c:if test="${'detail' eq param.status}">
				<tr>	
					<td class="l-t-td-left">审核结果：</td>
					<td class="l-t-td-right">
						<u:combo name="apply_audit_result" code="BASE_EQ_APPLY_RESULT"/>
					</td>	
					<td class="l-t-td-left">审核意见：</td>
					<td class="l-t-td-right">
						<textarea name="apply_audit_desc" rows="2" cols="25" class="l-textarea"></textarea>
					</td>
				</tr>
				</c:if>
			</table>
		</fieldset>
	</form>
	</div>	
	<div title="报废设备详情" tabId="certTab">
	<form id="detailForm" name="detailForm" method="post" action="equipment2ApplyRelationAction/save.do?status=${param.status}">
  	<input type="hidden" name="id" id="apply_relation_id"/>
  	<input type="hidden" name="baseEquipment2Apply.id" value="${param.id}"/>
  	<input type="hidden" id="apply_relation_create_by" name="create_by"/>
  	<input type="hidden" id="apply_relation_create_date" name="create_date"/>
	<table cellpadding="3" cellspacing="0" class="l-detail-table">
		<tr>
	       	<td class="l-t-td-left">设备名称：</td>
			<td class="l-t-td-right">
				<input name="baseEquipment2.id" id="eq_id" type="hidden" />
				<input name="baseEquipment2.eq_name" id="eq_name" type="text" ltype='text' validate="{required:true,maxlength:32}" onclick="selEquipment()" ligerui="{value:'',iconItems:[{icon:'add',click:function(){selEquipment()}}]}"/>
			</td>
			<td class="l-t-td-left">设备编号：</td>
			<td class="l-t-td-right">
				<input name="baseEquipment2.eq_no" id="eq_no" type="text" ltype='text' validate="{maxlength:32}" />
			</td>
	    </tr>
	    <tr>
	       	<td class="l-t-td-left">规格/型号：</td>
			<td class="l-t-td-right">
				<input name="baseEquipment2.eq_model" id="eq_model" type="text" ltype='text' validate="{maxlength:32}" />
			</td>
			<td class="l-t-td-left">生产厂家：</td>
			<td class="l-t-td-right">
				<input name="baseEquipment2.eq_manufacturer" id="eq_manufacturer" type="text" ltype='text' validate="{maxlength:64}" />
			</td>
	    </tr>
	    <tr id="eq_jdTr" style="display:none;">
			<td class="l-t-td-left">精度：</td>
			<td class="l-t-td-right">
				<input name="baseEquipment2.eq_accurate" id="eq_accurate" type="text" ltype='text' validate="{maxlength:32}" />
			</td>
			<c:if test="${'detail' eq param.status}">
			    <td class="l-t-td-left">报废日期：</td>
				<td class="l-t-td-right">
					<input name="conduct_date" id="conduct_date" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" />
				</td>	
	    	</c:if>
	    </tr>
	    <tr>
	    	<td class="l-t-td-left">出厂编号：</td>
			<td class="l-t-td-right">
				<input name="baseEquipment2.eq_sn" id="eq_sn" type="text" ltype='text' validate="{maxlength:32}" />
			</td>
	    	<td class="l-t-td-left">数量：</td>
	       	<td class="l-t-td-right"><input name="apply_count" id="apply_count" type="text" ltype='text' validate="{required:true,digits:true,maxlength:32}" /></td>
	    </tr>
	    <!-- 
	    <tr>
	    	<td class="l-t-td-left">报废原因：</td>
			<td class="l-t-td-right" colspan="3">
				<textarea name="apply_reason" rows="2" cols="25" class="l-textarea" validate="{maxlength:255}"></textarea>
			</td>	
	    </tr>
	   	 -->
	</table> 
  	<script type="text/javascript">
   	$("#detailForm").initFormList({
    	root:'datalist',
        getAction:"equipment2ApplyRelationAction/getList.do?id=${param.id}",
        //getActionParam:{},	//取数据时附带的参数，一般只在需要动态取特定值时用到
        actionParam:{"baseEquipment2Apply.id" : $("#formObj>[name='id']")},	//保存时会在当前表单上附加此数据，如：{fkId:$("#form1>#id")}会把Form1下的id为id的值带上去,可以是一个对象或选择器
        delAction:'equipment2ApplyRelationAction/delete.do',	//删除数据的action
        delActionParam:{ids:"id"},	//默认为选择行的id 
        columns:[
            //此部分配置同grid
            { display:'设备申请关联表主键', name:'id', width:'5%', hide:true},
			{ display:'设备申请主表主键', name:'fk_apply_id', width:'5%', hide:true},
			{ display:'设备基本信息表主键', name:'baseEquipment2.id', width:'5%', hide:true},
            { display:'设备名称', name:'baseEquipment2.eq_name', width:'30%'},
            { display:'设备编号', name:'baseEquipment2.eq_no', width:'15%'},
            { display:'规格/型号', name:'baseEquipment2.eq_model', width:'15%'},
            { display:'生产厂家', name:'baseEquipment2.eq_manufacturer', width:'15%'},
            { display:'出厂编号', name:'baseEquipment2.eq_sn', width:'15%'},
            { display:'精度', name:'baseEquipment2.eq_accurate', width:'15%'},
            { display:'数量', name:'apply_count', width:'15%'},
            { display:'报废日期', name:'conduct_date', width:'15%'},
			{ display:'是否需要归还/入库', name:'need_return', width:'20%', render: function (item) {
                	if("0" == item["need_return"]){
                		return "需要";
                	}else if("1" == item["need_return"]){
                		return "不需要";
                	}
            	}
			}
            <%
            	if("detail".equals(status)){
            		%>
            		,
            		{ display:'归还/入库数量', name:'return_count', width:'20%'}
            		<%
            	}
            %>
        ],
        success: function (resp) {//处理成功
			if (resp.success) {
	        	top.$.dialog.notice({
	            	content: "保存成功！"
	        	});
	       		api.data.window.refreshGrid();
	        	//api.close();
	   		} else {
	        	$.ligerDialog.error('保存失败！<br/>' + response.msg);
	   		}
		},onSelectRow: function(rowdata, rowid, rowobj){
			var eq_type = rowdata["baseEquipment2.eq_type"];
			if("01" == eq_type || "02" == eq_type){
				$("#eq_jdTr").attr('style','display:block');
				//$('#eq_measuring_rang').val(rowdata["eq_measuring_rang"]);	// 测量范围
				$('#eq_accurate').val(rowdata["eq_accurate"]);	// 精度
			}else{
				$("#eq_jdTr").attr('style','display:none');
				//$('#eq_measuring_rang').val("");
				$('#eq_accurate').val("");	
			}
			$("#detailForm").setValues(rowdata);
		}
    });
	</script>
	</form>
  	</div>
</div>
</body>
</html>
