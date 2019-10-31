
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head pageStatus="${param.status}">
		<title></title>
		<!-- 每个页面引入，页面编码、引入js，页面标题 -->
		<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="app/inspection/inspection_service.js"></script>
<%
	CurrentSessionUser user = SecurityUtil.getSecurityUser();
%>
		<script type="text/javascript">
		var pageStatus = "${param.status}";
		//收费情况
		var charge = <u:dict code="charge_situation"/>
		//设备类别
		//var deviceType=<u:dict sql="select t.id,t.code_table_values_id pid,t.value code,t.name text from PUB_CODE_TABLE_VALUES t,pub_code_table t1 where t.code_table_id=t1.id and   t1.code = 'device_classify' and t.code_table_values_id is null"/>
		var deviceType=<u:dict code="device_classify"/>
		//部门名称
		var unitname=<u:dict sql="select t.id, t.parent_id, t.id code, t.org_name text from sys_org t "/>
		var fzr=<u:dict sql="select t.id code,t.name text from employee t where t.org_id='${param.org_id}' "/>
		var checkType=<u:dict code="BASE_CHECK_TYPE"/>;
		var reportType =null;
		
		
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
						//id : 'close',
						icon : 'cancel',
						click : close
					} ],
					getSuccess : function(res) {
				
						
						
						degreeGrid.loadData({
							
							
							Rows :  res.inspectionDatas
						
						});
						
					//	$("#base").setValues({id:res.data.id,hall_no:res.data.hall_no,reg_date:res.data.reg_date,com_name:res.data.com_name,reg_op:res.data.reg_op,inspection_type:res.data.inspection_type,fee_detail:res.data.fee_detail,remark:res.data.remark});
						//$("#form1").setValues({id:res.inspection.id,fk_unit_id:res.inspection.fk_unit_id,com_name:res.inspection.com_name,com_address:res.inspection.com_address,check_type:res.inspection.check_type});
					}
				});
			})
		
		function close(url)
			{	
				 api.close();
			}
			function save(url)
			{
				var op_name=null;
				var check_name=null;
				var formData = $("#base").getValues();
				if($('#inc_op_name').val()=='' || $('#inc_op_name').val()==null){
					
				}else{
					op_name=$('#inc_op_name').val();
				}
				if($('#check_op_name').val()=='' || $('#check_op_name').val()==null){
					
				}else{
					check_name=$('#check_op_name').val();
				}
				
				
				//url = "department/basic/saveServicePlan.do?flowId=${param.flowId}&device_id=${param.id}&op_ids="+op_ids+"&check_ids="+check_ids+"&op_name="+encodeURIComponent(op_name)+"&check_name="+encodeURIComponent(check_name)+"&inc_time="+inc_time;
				url = "department/basic/saveServicePlan.do?flowId=${param.flowId}&ywid=${param.ywid}&acc_id=${param.acc_id}&flow_num=${param.flow_num}&op_name="+encodeURIComponent(op_name)+"&check_name="+encodeURIComponent(check_name);
				
					
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
			       // alert();
			        data["inspectionInfo"] = degreeGrid.getData();
			       
			       
			     
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
			                    top.$.dialog.notice({content:'保存成功！'});
			                    api.close();
			                }else
			                {
			                	
			                	 top.$.dialog.notice({content:'保存失败！'});
			                }
			            },
			            error: function (data,stats) {
			            	$("body").unmask();
			                top.$.dialog.notice({content:'保存失败！'});
			            }
			        });
				}
			}
			
			
			function setValue(valuex,name){
				
				if(valuex==""){
					return;
				}

	           

	            var selected = degreeGrid.rows;
	            if (!selected) { alert('请选择行'); return; }

	            var item_op_id;
	            var check_op_id;
				for(var i in selected){
					
					if(name=='item_op_id'){
						
	            		if(valuex==''|| valuex==null || valuex == undefined){
	            			item_op_id = selected[i].item_op_id;
	            		}else{
	            			//item_op_id = valuex;
							var text= $("input[name='item_op_ids']").ligerGetComboBoxManager().getValue();
							item_op_id = text;
							$('#inc_op_name').val(valuex)
	            		}
	            	}
					
					if(name=='check_op_id'){
	            		if(valuex==''|| valuex==null || valuex == undefined){
	            			check_op_id = selected[i].check_op_id;
	            		}else{
	            			//check_op_id = valuex;
	            			var text= $("input[name='check_op_ids']").ligerGetComboBoxManager().getValue();
	            			$('#check_op_name').val(valuex)
	            			check_op_id = text;
	            		}
	            	}
					
					degreeGrid.updateRow(selected[i],{
						item_op_id: item_op_id,
		            	check_op_id: check_op_id
		                
		            });
					
				}
				
				
				
			}
			
	</script>
	</head>
	<body>
		
	<form id="form1"  getAction="department/basic/getAllot.do?id=${param.ywid}">

				<fieldset class="l-fieldset" >
					<legend class="l-legend">
						<div>
							任务派工信息
						</div>
					</legend>
					<div id="degree"></div>
				</fieldset>

				<fieldset class="l-fieldset" >
					<legend class="l-legend">
						<div>
						便捷填写
						</div>
					</legend>
					   <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table" id="base">
         			<input type="hidden" name="id" id="id" value="${param.id}"/>
					<input type="hidden" name="flowId" id="flowId"  value="${param.flowId}"/>	
				<tr>
					<!-- 
						<td class="l-t-td-left">编制人员：</td>
						<td class="l-t-td-right">
						<input type="text"  name="item_op_ids" id="item_op_ids"  ltype="select" onchange="setValue(this.value,'item_op_id')" validate="{required:false}"
						ligerui="{
						
						readonly:true,
						tree:{checkbox:false,data: <u:dict sql="select t.id code,t.name text from employee t where t.org_id='${param.org_id}' "/>}
						}"/>
						<input type="hidden"  name="inc_op_name" id="inc_op_name"/>
						</td>
					 -->
						<td class="l-t-td-left">参检人员：</td>
						<td class="l-t-td-right" >
						<input type="text" name="check_op_ids" id="check_op_ids" ltype="select" onchange="setValue(this.value,'check_op_id')" validate="{required:false}" ligerui="{
						
						readonly:true,
						tree:{checkbox:true,data: <u:dict sql="select t.id code,t.name text from employee t where t.org_id='${param.org_id}' "/>}
						}"/>
						<input type="hidden"  name="check_op_name" id="check_op_name"/>
						
						</td>
				</tr>
		</table>
				</fieldset>
				
		</form>
	</body>
</html>
