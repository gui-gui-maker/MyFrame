
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
		var pageStatus = "detail";
		//收费情况
		var charge = <u:dict code="charge_situation"/>
		//设备类别
		var deviceType=<u:dict sql="select t.id,t.code_table_values_id pid,t.value code,t.name text from PUB_CODE_TABLE_VALUES t,pub_code_table t1 where t.code_table_id=t1.id and   t1.code = 'device_classify' and t.code_table_values_id is null"/>
		//部门名称
		var unitname=<u:dict sql="select t.id, t.parent_id, t.id code, t.org_name text from sys_org t "/>
		
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
							
							
							Rows : res.data.paraList
						
						});
						
					//	$("#base").setValues({id:res.data.id,hall_no:res.data.hall_no,reg_date:res.data.reg_date,com_name:res.data.com_name,reg_op:res.data.reg_op,inspection_type:res.data.inspection_type,fee_detail:res.data.fee_detail,remark:res.data.remark});
					}
				});
			
			
			})
		
		function close(url)
			{	
				 api.close();
			}
			function save(url)
			{
				
				var formData = $("#base").getValues();
				
				
				
				
				
				
				var inc_time=$('#inc_time').val();
				var op_ids =$('#inc_op_id').ligerGetComboBoxManager().getValue();
				var check_ids=$('#check_op_ids').ligerGetComboBoxManager().getValue();
				var op_name=$('#inc_op_name').val();
				var check_name =$('#check_op_name').val();
				
	
				
					url = "inspection/basic/savePlan.do?device_id=${param.id}&op_ids="+op_ids+"&check_ids="+check_ids+"&op_name="+encodeURIComponent(op_name)+"&check_name="+encodeURIComponent(check_name)+"&inc_time="+inc_time;
					
					
				//验证表单数据
				if($('#form1').validate().form())
				{
					
			      
					
			     
			        $("body").mask("正在保存数据，请稍后！");
			        $.ajax({
			            url: url,
			            type: "POST",
			            datatype: "json",
			            contentType: "application/json; charset=utf-8",
			          // 	data: "
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
		
	<form id="form1"  getAction="inspection/basic/getDetail.do?id=${param.id}&hall_id=${param.hall_id}">
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
       					 
        <td class="l-t-td-left"> 受检单位名称:</td>
        <td class="l-t-td-right"><input name="com_name" validate="{required:true,maxlength:300}"  ltype='text' ligerui="{disabled:true}"  /> 
        </td>
        
     
     
        <td class="l-t-td-left"> 检验类别:</td>
        <td class="l-t-td-right">
        
        <u:combo name="inspection_type" code="BASE_CHECK_TYPE" validate="required:true"  attribute="disabled:true"  />
        
         
        </td>
        
      
        </td>
        
        
        
        
        
        </tr>
        <td class="l-t-td-left"> 备注:</td>
   
        <td class="l-t-td-right" colspan="3"> 
       <textarea name="remark" cols="60"
				rows="4" class="l-textarea" ligerui="{disabled:true}"></textarea>	
        </td>
        
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
          <sec:authorize access="hasRole('luojuyong')">
				<tr>
						<td class="l-t-td-left">项目负责人：</td>
						<td class="l-t-td-right">
						<input type="text"  name="inc_op_id" id="inc_op_id"  ltype="select" onchange="setValue(this.value,'inc_op_name')" validate="{required:true}"
						ligerui="{
						
						readonly:true,
						tree:{checkbox:false,data: <u:dict sql="select t.id code,t.name text from employee t where t.org_id='100012' or t.name='罗俊勇'"/>}
						}"/>
						<input type="hidden"  name="inc_op_name" id="inc_op_name">
						</td>
						<td class="l-t-td-left">检验日期：</td>
						<td class="l-t-td-right"><input name="inc_time" id="inc_time"
						type="text" ltype="date" validate="{required:true}"
						ligerui="{initValue:'',format:'yyyy-MM-dd'}" /></td>
						</tr>
						<tr>	
						<td class="l-t-td-left">参检人员：</td>
						<td class="l-t-td-right" colspan="3">
						<input type="text" name="check_op_ids" id="check_op_ids" ltype="select" onchange="setValue(this.value,'check_op_name')" validate="{required:true}" ligerui="{
						
						readonly:true,
						tree:{checkbox:true,data: <u:dict sql="select t.id code,t.name text from employee t where t.org_id='100012’ or t.name='罗俊勇'"/>}
						}"/>
						<input type="hidden"  name="check_op_name" id="check_op_name">
						
						</td>
					</tr>
				
			</sec:authorize>
			<sec:authorize access="hasRole('py')">
				<tr>
						<td class="l-t-td-left">项目负责人：</td>
						<td class="l-t-td-right">
						<input type="text"  name="inc_op_id" id="inc_op_id"  ltype="select" onchange="setValue(this.value,'inc_op_name')" validate="{required:true}"
						ligerui="{
						
						readonly:true,
						tree:{checkbox:false,data: <u:dict sql="select t.id code,t.name text from employee t where t.org_id='100005' or t.name='潘洋' "/>}
						}"/>
						<input type="hidden"  name="inc_op_name" id="inc_op_name">
						</td>
						<td class="l-t-td-left">检验日期：</td>
						<td class="l-t-td-right"><input name="inc_time" id="inc_time"
						type="text" ltype="date" validate="{required:true}"
						ligerui="{initValue:'',format:'yyyy-MM-dd'}" /></td>
						</tr>
						<tr>	
						<td class="l-t-td-left">参检人员：</td>
						<td class="l-t-td-right" colspan="3">
						<input type="text" name="check_op_ids" id="check_op_ids" ltype="select" onchange="setValue(this.value,'check_op_name')" validate="{required:true}" ligerui="{
						
						readonly:true,
						tree:{checkbox:true,data: <u:dict sql="select t.id code,t.name text from employee t where t.org_id='100005' or t.name='潘洋' "/>}
						}"/>
						<input type="hidden"  name="check_op_name" id="check_op_name">
						
						</td>
					</tr>
				
			</sec:authorize>
			<sec:authorize access="hasRole('rwfp')">
				<tr>
						<td class="l-t-td-left">项目负责人：</td>
						<td class="l-t-td-right">
						<input type="text"  name="inc_op_id" id="inc_op_id"  ltype="select" onchange="setValue(this.value,'inc_op_name')" validate="{required:true}"
						ligerui="{
						
						readonly:true,
						tree:{checkbox:false,data: <u:dict sql="select t.id code,t.name text from employee t where t.org_id='${param.org_id}' "/>}
						}"/>
						<input type="hidden"  name="inc_op_name" id="inc_op_name">
						</td>
						<td class="l-t-td-left">检验日期：</td>
						<td class="l-t-td-right"><input name="inc_time" id="inc_time"
						type="text" ltype="date" validate="{required:true}"
						ligerui="{initValue:'',format:'yyyy-MM-dd'}" /></td>
						</tr>
						<tr>	
						<td class="l-t-td-left">参检人员：</td>
						<td class="l-t-td-right" colspan="3">
						<input type="text" name="check_op_ids" id="check_op_ids" ltype="select" onchange="setValue(this.value,'check_op_name')" validate="{required:true}" ligerui="{
						
						readonly:true,
						tree:{checkbox:true,data: <u:dict sql="select t.id code,t.name text from employee t where t.org_id='${param.org_id}' "/>}
						}"/>
						<input type="hidden"  name="check_op_name" id="check_op_name">
						
						</td>
					</tr>
			</sec:authorize>
       					
		</table>
				</fieldset>
				
		</form>
	</body>
</html>
