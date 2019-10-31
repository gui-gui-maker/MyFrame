
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head pageStatus="${param.status}">
		<title></title>
		<!-- 每个页面引入，页面编码、引入js，页面标题 -->
		<%@include file="/k/kui-base-form.jsp"%>
		
	<script type="text/javascript" src="app/inspection/device_info.js"></script>
		<script type="text/javascript">
		
		var pageStatus = "${param.status}";
		var checkType=null;
		var reportType =null;
		var fzr=<u:dict sql="select t.id code,t.name text from employee t where t.org_id='${param.org_id}' "/>
		//设备类别
		var deviceType=<u:dict code="device_classify"/>
		//var deviceType=null;
		var flowId=null;
			$(function() {
				$('#check_type-txt').change(function(){
					if($(this).val()==''){
						alert();
					}else{
						checkType=$("input[name='check_type']").val();

					};
					
				} );
					
					if(pageStatus=='modify'){
						reportType=<u:dict sql="select distinct(t.fk_report_id),t.report_name from base_unit_flow t where t.check_type =${param.checkT} and t.device_type like '${param.deviceT}%'"/>;
					}else{
						reportType="";
					}
					createdeviceGrid();
			
				
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
						
						if(res.success){
						
							deviceGrid.loadData({
								Rows : res.inspectionDatas
							});
							
							$("#form1").setValues({check_op_ids:res.op_name_ids,check_op_name:res.op_name_names,id:res.inspection.id,fk_unit_id:res.inspection.fk_unit_id,com_name:res.inspection.com_name,com_address:res.inspection.com_address,check_type:res.inspection.check_type,accept_no:res.inspection.accept_no});
						}
						
					}
				});
			
			
			})
		
			
		function close(url)
			{	
				 api.close();Ren
			}
			function save(url)
			{
			
				
					
					var  check_op_name= $('#check_op_name').val();
					
					
					if(check_op_name==''){
						$.ligerDialog.alert("参检人员未选择！");
						return
					}
				
					
					//url = "department/basic/flow_saveBasic.do?flowId=${param.flowId}&check_type="+check_type+"&acc_no="+acc_no;
					url="department/basic/saveBasic2.do?check_type="+checkType+"&check_op_name="+encodeURIComponent(check_op_name)+"&status="+pageStatus;
					
				//验证表单数据
				if($('#form1').validate().form())
				{
					
					var formData = $("#form1").getValues();
			        var data = {};
			        data = formData;
			        //验证grid
			        if(!validateGrid(deviceGrid))
					{
						return false;
					}
			        
			        data["inspectionInfo"] = deviceGrid.getData();
			       
			     
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
			
			
			
			function submit(){
				url = "department/basic/flow_saveBasic.do?flowId=${param.flowId}&check_type="+check_type+"&acc_no="+acc_no;
				//验证表单数据
				if($('#form1').validate().form())
				{
					var formData = $("#form1").getValues();
			        var data = {};
			        data = formData;
			        //验证grid
			        if(!validateGrid(deviceGrid))
					{
						return false;
					}

			        data["inspectionInfo"] = deviceGrid.getData();
			       
			     
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
			function selectorg(){
				var check_type = $("input[id='check_type-txt']").ligerComboBox().getValue();
				if("" == check_type){
					$.ligerDialog.alert("请先选择“检验类别”！");
					return;
				}
				var tmp= deviceGrid.getData();
				tmp = JSON.stringify(tmp)
			
				if(tmp!="[]"){
					
					top.$.notice("请先删除设备信息！");
					return;
				}
			
				top.$.dialog({
					parent: api,
					width : 800, 
					height : 550, 
					lock : true, 
					title:"选择企业信息",
					content: 'url:app/enter/enter_open_list.jsp',
					data : {"parentWindow" : window}
				});
			}
			
			function callBack(id,name,address){
				
					$('#fk_unit_id').val(id);
					$('#com_name').val(name);
					$('#com_address').val(address);
					
			
			}

			
			function setValues(valuex,name){
				//alert(valuex+name);
			
				if(valuex==""){
					return;
				}
					var selected = deviceGrid.rows;
					
		            if (!selected) { alert('请选择行'); return; }
		            
		            var check_op;
		            var check_tel;
		            var check_op_id;
		            var check_op_name;
		            var advance_time;
		            var report_type;
		            
					
		            for(var i in selected){
		            	if(name=='check_op'){
		            		if(valuex==''|| valuex==null || valuex == undefined){
		            			check_op = selected[i].check_op;
		            		}else{
		            			check_op = valuex;
		            		}
		            	}
						//if(name=='check_op_name'){
		            		//if(valuex==''|| valuex==null || valuex == undefined){
		            		//	check_op_name = selected[i].check_op_name;
		            		//}else{
		            			//check_op_name = valuex;
		            		//}
		            //	}
						
						if(name=='check_op_id'){
							
	            		if(valuex==''|| valuex==null || valuex == undefined){
	            			check_op_id = selected[i].check_op_id;
	            		}else{
	            			//check_op_id = valuex;
	            			
	            			var text= $("input[name='check_op_ids']").ligerGetComboBoxManager().getValue();
	            			
	            			
	            			
	            			$('#check_op_name').val(valuex);
	            			
	            			check_op_name = valuex;
	            			check_op_id = text;
	            			
	            			
	            			
	            		}
	            	}
		            	
		            	if(name=='check_tel'){
		            		if(valuex==''|| valuex==null || valuex ==undefined){
		            			check_tel = selected[i].check_tel;
		            		}else{
		            			var text= $("input[name='check_tels']").ligerGetComboBoxManager().getValue();
		            			check_tel = text;
		            		}
		            	}
		            	if(name=='advance_time'){
		            		if(valuex==''|| valuex==null || valuex ==undefined){
		            			advance_time = selected[i].advance_time;
		            		}else{
		            			//var date = new Date(valuex.replace(/-/g, "/"));
								//var tt = new Date(date).format('yyyy-MM-dd');
		            			advance_time = valuex;
		            		}
		            	}
		            	if(name=='report_type'){
		            		if(valuex==''|| valuex==null || valuex ==undefined){
		            			report_type = selected[i].report_type;
		            		}else{
		            			var text= $("input[name='report_types']").ligerGetComboBoxManager().getValue();
		            			report_type = text;
		            		}
		            	}
		            	deviceGrid.updateRow(selected[i],{
		            	check_op_id:check_op_id,
		            	
		            	check_op: check_op,
		            	check_tel: check_tel,
		            	advance_time: advance_time,
		            	report_type: report_type
		                
		            });
		         }
			}
			
			
				function dd(){
					alert(1);
				}
				
			function valChk(){
				var tmp= deviceGrid.getData();
				tmp = JSON.stringify(tmp)
				if(tmp=="[]"){
					$.ligerDialog.alert("请先选择设备信息！");
					return;
				}
			}		
	</script>
	</head>
	<body>
		
	<form id="form1"  getAction="department/basic/getDetail.do?id=${param.id}">
	
	<fieldset class="l-fieldset">
					<legend class="l-legend">
						<div>
							单位信息
						</div>
					</legend>
          <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
          <input id="id" name="id"  type="hidden"  />
          <input id="accept_no" name="accept_no" type="hidden" />
            
            <tr>
           <!--  <td class="l-t-td-left">大厅报检单位名称：</td>
						<td class="l-t-td-right" colspan="3">
            <input type="text" name="hall_com_nam" value="${param.com_name}" readonly="readonly"/>
            </td> -->
            <td class="l-t-td-left">检验类别：</td>
						<td class="l-t-td-right" colspan="3">
            <u:combo name="check_type" code="BASE_CHECK_TYPE" validate="required:true"  attribute="disabled:false"   />
            </td> 
            </tr>
	<tr >				

	


	
						<td class="l-t-td-left">受检单位：</td>
						<td class="l-t-td-right" colspan="3">
						<input type="hidden" name="fk_unit_id" id="fk_unit_id" />
						<input type="text" id="com_name" name="com_name"  readonly="readonly"  ltype="text"  validate="{required:true}" onclick="selectorg()"
										ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:function(){selectorg()}}]}"/>
					</tr>
					
		</table>
</fieldset>
			
			
		
		<fieldset class="l-fieldset">
		
				
				 <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
					<tr>
					<td class="l-t-td-left">报告类型：</td>
					<td class="l-t-td-right" colspan="3" >
						<input type="text" id="report_types" name="report_types" ltype="select" validate="{required:false}" onclick="valChk()"  onchange="setValues(this.value,'report_type')" />
						</td>
						</tr>
						<tr>
						<td class="l-t-td-left">检验日期：</td>
						<td class="l-t-td-right" >
						<input name="advance_time"
					type="text" ltype="date" validate="{required:false}"
					ligerui="{initValue:'',format:'yyyy-MM-dd'}" id="advance_time"  onchange="setValues(this.value,'advance_time')"/>
						<td class="l-t-td-left">参检人员：</td>
						<td class="l-t-td-right" >
							<input type="text" name="check_op_ids" id="check_op_ids" ltype="select" onchange="setValues(this.value,'check_op_id')" validate="{required:false}" ligerui="{
							
							readonly:true,
							tree:{checkbox:true,data: <u:dict sql="select t.id code,t.name text from employee t where t.org_id='${param.org_id}' "/>}
							}"/>
							<input type="hidden"  name="check_op_name" id="check_op_name"/>
						</td>
					</tr>
					<tr>
						<td class="l-t-td-left">检验联系人：</td>
						<td class="l-t-td-right" >
						<input type="text" ltype="text" name="check_ops" value="" size="20"  onchange="setValues(this.value,'check_op')"></td>
						<td class="l-t-td-left">联系电话：</td>
						<td class="l-t-td-right" >
						<input type="text" ltype="text" name="check_tels" value="" size="15"  onchange="setValues(this.value,'check_tel')"></td>
					</tr>
					
				</table>
				</fieldset>
		
				
		</form>
	</body>
</html>
