
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
		
		
		var check_type = ${param.check_type};
		
		var paraId = "${param.paraId}";
		
		var acc_no = ${param.acc_no};
		
		var reportType =<u:dict sql="select t.fk_report_id,t.report_name from base_unit_flow t where t.check_type =${param.check_type} and t.device_type like '${param.device_type}%'"/>;
		
		
		
		
		//设备类别
		var deviceType=<u:dict code="device_classify"/>
		//报告
		
	
	  
	
			$(function() {
				//$.getJSON('report/basic/getReportType.do',{unitId:unit_id,checkType:check_type},function(data){
					
					$('#accept_no').val(acc_no);
					$('#check_type').val(check_type);
					$('#fk_hall_para_id').val(paraId);
					createdeviceGrid();
			//	})
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
						
					
						if(res.success){
						
							deviceGrid.loadData({
								Rows : res.inspectionDatas
							});
							
							$("#form1").setValues({fk_hall_para_id:res.inspection.fk_hall_para_id,id:res.inspection.id,fk_unit_id:res.inspection.fk_unit_id,com_name:res.inspection.com_name,com_address:res.inspection.com_address,accept_no:res.inspection.accept_no,check_type:res.inspection.check_type});
						}
						
					}
				});
			
			
			})
		
		function close(url)
			{	
				 api.close();
			}
			function save(url)
			{
				
				
				
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
				
			
				if(valuex==""){
					return;
				}
					var selected = deviceGrid.rows;
		            if (!selected) { alert('请选择行'); return; }
		            
		            var check_op;
		            var check_tel;
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
		            	if(name=='check_tel'){
		            		if(valuex==''|| valuex==null || valuex ==undefined){
		            			check_tel = selected[i].check_tel;
		            		}else{
		            			check_tel = valuex;
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
		            	check_op: check_op,
		            	check_tel: check_tel,
		            	advance_time: advance_time,
		            	report_type: report_type
		                
		            });
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
          <input id="id" name="id"  type="hidden" />
             <input id="fk_hall_para_id" name="fk_hall_para_id"  type="hidden" />
            <tr>
            <td class="l-t-td-left">大厅报检单位名称：</td>
						<td class="l-t-td-right" colspan="3">
            <input type="text" name="hall_com_nam" value="${param.com_name}" readonly="readonly"/>
            </td>
            </tr>
	<tr >				
	<input type="hidden" name="accept_no" id="accept_no" />
	<input type="hidden" name="check_type" id="check_type" />
	<input type="hidden" name="enter_unit_id" id="enter_unit_id" />
	<input type="hidden" name="fk_flow_index_id" id="fk_flow_index_id"  value="${param.flowId}"/>	

	
						<td class="l-t-td-left">受检单位：</td>
						<td class="l-t-td-right" colspan="3">
						<input type="hidden" name="fk_unit_id" id="fk_unit_id" />
						<input type="text" id="com_name" name="com_name"  readonly="readonly"  ltype="text"  validate="{required:true}" onclick="selectorg()"
										ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:function(){selectorg()}}]}"/>
					</tr>
					<tr class="d_tr">
						<td class="l-t-td-left">单位地址：</td>
						<td class="l-t-td-right" colspan="3"><input type="text"  ltype="text" name="com_address" id="com_address"  readonly></td>
					</tr>
		</table>
</fieldset>
<fieldset class="l-fieldset" >
					<legend class="l-legend">
						<div>
							设备信息 
						</div>
					</legend>
					<div style="height:300px;" id="device"></div>
</fieldset>				
				
		
		<fieldset class="l-fieldset">
		<legend class="l-legend">
						<div>
							便捷填写
						</div>
					</legend>
				
				 <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
					<tr>
					<td class="l-t-td-left">报告类型：</td>
					<td class="l-t-td-right" >
			<input type="text" id="report_types" name="report_types" ltype="select" validate="{required:false}"  onchange="setValues(this.value,'report_type')" ligerui="{
				
				initValue:'3000',
				readonly:true,
				data: <u:dict sql="select t.fk_report_id,t.report_name from base_unit_flow t where t.check_type =${param.check_type} and t.device_type like '${param.device_type}%'"/>,
				}"/>
						</>
						<td class="l-t-td-left">检验日期：</td>
						<td class="l-t-td-right" >
						<input name="advance_times"
					type="text" ltype="date" validate="{required:false}"
					ligerui="{initValue:'',format:'yyyy-MM-dd'}" id="advance_times"  onchange="setValues(this.value,'advance_time')"/>
						
					</tr>
					<tr>
						<td class="l-t-td-left">检验联系人：</td>
						<td class="l-t-td-right" >
						<input type="text" ltype="text" name=check_ops value="" size="20"  onchange="setValues(this.value,'check_op')"></td>
						<td class="l-t-td-left">联系电话：</td>
						<td class="l-t-td-right" >
						<input type="text" ltype="text" name="check_tels" value="" size="15"  onchange="setValues(this.value,'check_tel')"></td>
					</tr>
					
				</table>
				</fieldset>
		
				
		</form>
	</body>
</html>
