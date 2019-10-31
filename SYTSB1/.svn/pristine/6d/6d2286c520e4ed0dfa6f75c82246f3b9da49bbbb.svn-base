<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
<script type="text/javascript">
	$(function() {
		$("#formAttached")
			.initFormList(
				{
					root : 'list',
					//getAction : "EquipAttachmentAction/getFjByfkEquipmentId.do?fkEquipmentId=${param.id}",
					//getActionParam:{},	//取数据时附带的参数，一般只在需要动态取特定值时用到
					actionParam:{"fkEquipmentId": $("#formAttached>#fkEquipmentId")},
					//保存时会在当前表单上附加此数据，如：{fkId:$("#form1>#id")}会把Form1下的id为id的值带上去,可以是一个对象或选择器
					delAction : 'EquipAttachmentAction/delete.do', //删除数据的action
					/* delActionParam: {name: "name"}, //默认为选择行的id
					delActionParam:{"id":$("#formAttached>#id")},
					toolbar:[
	     				  { text:'保存', click:function(){
	     						//更改form1的action
	            			    $("#formAttached").attr("action", "EquipAttachmentAction/save.do?pageStatus=${param.status}");  
	            			    //alert(document.getElementById("form1").action);
	            				//触发submit事件，提交表单 
	     					    $("#formAttached").submit();
	     					  }, icon:'save'},
     					 { text:'删除', click:function(){
     						 	alert($("#id").val());
     						    $.del("确定要删除？删除后无法恢复！","EquipAttachmentAction/delete.do",{"id":$("#id").val()});
	     					  }, icon:'delete'},
	     				  { text:'关闭', click:function(){api.data.window.Qm.refreshGrid();api.close();}, icon:'cancel'}
	     				 ], */
					columns : [ {display : 'id',name : 'id',width : '1%',hide : true}, 
						{display : 'fkEquipmentId',name : 'fkEquipmentId',width : '1%',hide : true }, 
						{display : 'createDate',name : 'createDate',width : '1%',hide : true }, 
						{display : 'createId',name : 'createId',width : '1%',hide : true }, 
						{display : 'createBy',name : 'createBy',width : '1%',hide : true }, 
						{display : 'lastModifyDate',name : 'lastModifyDate',width : '1%',hide : true }, 
						{display : 'lastModifyId',name : 'lastModifyId',width : '1%',hide : true }, 
						{display : 'lastModifyBy',name : 'lastModifyBy',width : '1%',hide : true },  
						{display : '附件名称',name : 'attachmentName',width : '20%'},  
						{display : '出厂编号',name : 'attachmentSn',width : '20%'},  
						{display : '规格型号',name : 'attachmentModel',width : '20%'},  
						{display : '数量',name : 'attachmentNum',width : '8%'},  
						{display : '制造厂家',name : 'manufacturers',width : '30%'},  
						{display : '附件状态',name : 'status',width : '8%',
							render:function(rowData){
		                    	if(rowData.status=="0"){
		                        	return "良好";
		                    	}else if(rowData.status=="1"){
		                        	return "损坏";
		                    	}else if(rowData.status=="2"){
		                        	return "遗失";
		                    	}
		                	}	
						},  
						{display : '配置箱号',name : 'configuring',width : '20%'},  
						{display : '备注',name : 'remark',width : '25%'}],
					    onSelectRow : function(rowdata, rowindex) {
						  $("#formAttached").setValues(rowdata);
						},
						 success: function (data, stats) {
				                if (data["success"]) {
				                		top.$.notice("保存成功！");
				                } else {
				                	$.ligerDialog.error('请先保存设备信息！');
				                }
				         }
			});
	});

</script>
</head>
<body>
<form id=formAttached action="EquipAttachmentAction/save.do?pageStatus=${param.status}"
	getAction="EquipAttachmentAction/getFjByfkEquipmentId.do?fkEquipmentId=${param.id}">
		<input type="hidden" name="id"/>
		<input type="hidden" id="fkEquipmentId" name="fkEquipmentId"/>
		<input type="hidden" id="createDate" name="createDate"/>
		<input type="hidden" id="createId" name="createId"/>
		<input type="hidden" id="createBy" name="createBy"/>
		<input type="hidden" id="lastModifyDate" name="lastModifyDate"/>
		<input type="hidden" id="lastModifyId" name="lastModifyId"/>
		<input type="hidden" id="lastModifyBy" name="lastModifyBy"/>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>
					附件
				</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">附件名称</td>
					<td class="l-t-td-right"><input name="attachmentName" id="attachmentName" type="text" ltype='text' validate="{maxlength:100}"/></td>
					<td class="l-t-td-left">出厂编号</td>
					<td class="l-t-td-right"><input name="attachmentSn" id="attachmentSn" type="text" ltype='text' validate="{maxlength:100,required:true}"/></td>
				</tr>
				<tr>
					<td class="l-t-td-left">规格型号</td>
					<td class="l-t-td-right"><input name="attachmentModel" id="attachmentModel" type="text" ltype='text' validate="{maxlength:100,required:true}"/></td>	
					<td class="l-t-td-left">数量</td>
					<td class="l-t-td-right"><input name="attachmentNum" id="attachmentNum" type="text" ltype='text' validate="{required:true,maxlength:32}"/></td>
				</tr>
				<tr>
					<td class="l-t-td-left">制造厂家</td>
					<td class="l-t-td-right" colspan="3">
						<input name="manufacturers" id="manufacturers" type="text" ltype='text' validate="{maxlength:200}"/>
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">附件状态</td>
					<td class="l-t-td-right">
						<!-- <input name="status" id="status" type="text" ltype='text' validate="{maxlength:32}"/> -->
						<u:combo name="status" code="EQUIP_FJ_STATUS" validate="{required:true,maxlength:32}"/>
					</td>
					<td class="l-t-td-left">配置箱号</td>
					<td class="l-t-td-right">
						<input name="configuring" id="configuring" type="text"  ltype='text' validate="{maxlength:32}"/>
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">备注</td>
					<td class="l-t-td-right" colspan="3">
						<input name="remark" id="remark" type="text" ltype='text' validate="{maxlength:500}"/>
					</td>						
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>