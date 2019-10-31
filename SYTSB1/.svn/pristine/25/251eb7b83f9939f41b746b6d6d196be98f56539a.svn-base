<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
CurrentSessionUser user=SecurityUtil.getSecurityUser();
String orgId=user.getDepartment().getId();
String orgName=user.getDepartment().getOrgName();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head pageStatus="${param.status}">
	<title>检验员报检</title>
	<!-- 每个页面引入，页面编码、引入js，页面标题 -->
	<%@include file="/k/kui-base-form.jsp"%>
	<script type="text/javascript" src="app/payment/unit/payment_unit_cut.js"></script>
	<script type="text/javascript" src="app/payment/unit/ChipCombobox.js"></script>
		<script type="text/javascript">
		var id = "${param.id}"
		//总金额
		var pay_received = "${param.pay_received}"; 
		
		
		var unitId = "<%=orgId%>";
		var unitName = "<%=orgName%>";
		
		var pageStatus = "${param.status}";
		var check_type = "";
		//设备类别
		$(function() {
			initGrid();
			$("#form-opinsp").initForm({ //参数设置示例
				toolbar : [ {
					text : '保存',
					icon : 'save',
					click : save
				},{
					text : '关闭',
					//id : 'close',
					icon : 'cancel',
					click : close
				} ],
				getSuccess : function(res) {
					if(res.success){
						if(res.data.payInfoUnits!=null){
							deviceGrid.loadData({Rows : res.data.payInfoUnits});
						}
						//$("#form-opinsp").setValues({fkFlowIndexId:res.inspection.id,fk_unit_id:res.inspection.fk_unit_id,com_name:res.inspection.com_name,com_address:res.inspection.com_address,accept_no:res.inspection.accept_no,check_type:res.inspection.check_type});
					}
					
				}
			});
			
			$("#company_name").val(api.data.company_name);
			var unitname=<u:dict sql="select t.id, t.parent_id, t.id code, t.org_name text from sys_org t where t.parent_id is not null  and status='used'"/>
			setInspecUnits(unitname);
			
		})
		
		
		function close(url){	
			 api.close();
		}
		function save(url)
		{
			url = "pay/info/unit/savePayUnit.do";
			//验证表单数据
			if($('#form-opinsp').validate().form())
			{
			
				 if(tmp==""){
						
						top.$.notice("请填写收费分配信息！");
						return;
					}
				if(!count()){
					$.ligerDialog.warn('提示：' + "分配金额总和与收费金额总和不相等，请检查后提交！");
					return;
				}
				
				var formData = $("#form-opinsp").getValues();
				
		        var data = {};
		        data = formData;
		        
		        var tmp= getData();
		        
		      
		       
		      
		        //验证grid
		        if(!validateGrid(deviceGrid))
				{
					return false;
				}
				
		        data["payInfoUnits"] = deviceGrid.getData();
		        $("body").mask("正在保存数据，请稍后！");
		        $.ajax({
		        	url: url,
		            type: "POST",
		           	data:{"data": $.ligerui.toJSON(data)},
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
						alert(JSON.stringify(data));
		            	$("body").unmask();
		                $.ligerDialog.error('提示：' + data.msg);
		            }
		        });
			}
		}
			
		function selectorg(type){
			var tmp= deviceGrid.getData();
			if(tmp!=""){
				top.$.notice("请先删除设备信息！");
				return;
			}
			top.$.dialog({
				parent: api,
				width : 800, 
				height : 550, 
				lock : true, 
				title:"选择企业信息",
				content: 'url:app/tzsb/inspection/flow/insing/support/enter_list.jsp?com_type='+type,
				data : {"parentWindow" : window}
			});
		}
		//选择报检单位回调
		function callBack(id,name,code,address){
				$('#fk_unit_id').val(id);
				$('#com_name').val(name);
				$('#com_address').val(address);
			
		}
		//选择报检设备回调方法
		function onChooseDeviceBack(){
		        reReportTypes();
				
		}
		function setValues(valuex,name){
			if(valuex==""){
				return;
			}
				var selected = getRows();
	            if (!selected) { alert('请选择行'); return; }
	            
	            var checkOpId;//参检员
	            var checkOpName;//参检员
	            var itemOpId;//检验负责人
	            var itemOpName;//检验负责人
	            var fkReportId;//报告类型
	            var reportName;
	            var inspectDate;//检验日期
	            var checkTel;
	            var checkOp;
	            for(var i in selected){
	            	if(name=='checkOpId'){
	            		if(valuex==''|| valuex==null || valuex == undefined){
	            			checkOpId = selected[i].checkOpId;
	            			checkOpName = selected[i].checkOpName;
	            		}else{
	            		    var value= $("#quick_checkOpId").ligerGetComboBoxManager().getValue();
	            		    var text= $("#quick_checkOpId").val();
							checkOpId = value;
							checkOpName = text;
	            		    /*var svalue=selected[i].checkOpId;
	            			if(svalue==''||svalue==null||svalue==undefined){
	            			  checkOpId = value;
	            			}else{
	            			  checkOpId = svalue;
	            			}
	            			
	            			 var tvalue=selected[i].checkOpName;
	            			if(tvalue==''||tvalue==null||tvalue==undefined){
	            			  checkOpName = text;
	            			}else{
	            			  checkOpName = tvalue;
	            			}*/
	            			
	            		}
	            	}
					if(name=='itemOpId'){
	            		if(valuex==''|| valuex==null || valuex == undefined){
	            			itemOpId = selected[i].itemOpId;
	            			itemOpName = selected[i].itemOpName;
	            		}else{
	            			var value= $("#quick_itemOpId").ligerGetComboBoxManager().getValue();
	            		    var text= $("#quick_itemOpId").val();
							itemOpId = value;
							itemOpName = text;
	            			/* var svalue=selected[i].itemOpId;
	            			if(svalue==''||svalue==null||svalue==undefined){
	            			  itemOpId = value;
	            			}else{
	            			  itemOpId = svalue;
	            			}
	            			 var tvalue=selected[i].itemOpName;
	            			if(tvalue==''||tvalue==null||tvalue==undefined){
	            			  itemOpName = text;
	            			}else{
	            			  itemOpName = tvalue;
	            			}*/
	            		}
	            	}
	            	
	            	if(name=='fkReportId'){
	            		if(valuex==''|| valuex==null || valuex == undefined){
	            			fkReportId = selected[i].fkReportId;
	            			reportName = selected[i].reportName;
	            		}else{
	            			
	            			var value= $("#quick_fkReportId").ligerGetComboBoxManager().getValue();
	            		    var text= $("#quick_fkReportId").val();
							 fkReportId = value;
							 reportName = text;
	            			/* var svalue=selected[i].fkReportId;
	            			if(svalue==''||svalue==null){
	            			  fkReportId = value;
	            			}else{
	            			  fkReportId=svalue;
	            			}
	            			
	            			 var tvalue=selected[i].reportName;
	            			if(tvalue==''||tvalue==null||tvalue==undefined){
	            			  reportName = text;
	            			}else{
	            			  reportName = tvalue;
	            			}*/
	            		}
	            	}
	            	if(name=='checkTel'){
	            		if(valuex==''|| valuex==null || valuex ==undefined){
	            			checkTel = selected[i].checkTel;
	            		}else{
						    checkTel = valuex;
	            			/*var tvalue=selected[i].checkTel;
	            			if(tvalue==''||tvalue==null||tvalue==undefined){
	            			  checkTel = valuex;
	            			}else{
	            			  checkTel = tvalue;
	            			}*/
	            		}
	            	}
	            	if(name=='checkOp'){
	            		if(valuex==''|| valuex==null || valuex ==undefined){
	            			checkOp = selected[i].checkOp;
	            		}else{
							checkOp = valuex;
	            			/*var tvalue=selected[i].checkOp;
	            			if(tvalue==''||tvalue==null||tvalue==undefined){
	            				checkOp = valuex;
	            			}else{
	            				checkOp = tvalue;
	            			}*/
	            		}
	            	}
	            	if(name=='inspectDate'){
	            		if(valuex==''|| valuex==null || valuex ==undefined){
	            			inspectDate = selected[i].inspectDate;
	            		}else{
						    inspectDate = valuex;
	            			//var date = new Date(valuex.replace(/-/g, "/"));
							//var tt = new Date(date).format('yyyy-MM-dd');
	            			/*var tvalue=selected[i].inspectDate;
	            			if(tvalue==''||tvalue==null||tvalue==undefined){
	            			  inspectDate = valuex;
	            			}else{
	            			  inspectDate = tvalue;
	            			}*/
	            		}
	            	}
	            	
	            	updateRow(selected[i],{
					checkOpId: checkOpId,
					checkOpName: checkOpName,
	            	itemOpId: itemOpId,
	            	itemOpName: itemOpName,
	            	fkReportId: fkReportId,
	            	reportName: reportName,
	            	inspectDate: inspectDate,
	            	checkTel: checkTel,
	            	checkOp: checkOp
	                
	            });
	         }
		}
	</script>
	</head>
	<body>
	<form id="form-opinsp"  getAction="pay/info/unit/getDetailByPayId.do?payId=${param.id}">
	<fieldset class="l-fieldset">
		<legend class="l-legend">
			<div>
				单位信息;
			</div>
		</legend>
          <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
          <input id="id" name="id"  type="hidden" value="${param.id }"/>
          
			<tr class="d_tr">
				<td class="l-t-td-left">收费总金额：</td>
				<td class="l-t-td-right" >
				<input type="text" id="pay_received" ltype="text" value="${param.pay_received }"/>
				</td>
				<td class="l-t-td-left">开票名称：</td>
				<td class="l-t-td-right" >
				<input type="text" id="company_name" ltype="text"/>
				</td>
			</tr>
		</table>
	</fieldset>
	<fieldset class="l-fieldset" >
		<legend class="l-legend">
			<div>收费分配信息 </div>
		</legend>
		<div style="height:300px;" id="device"></div>
	</fieldset>				
	
		</form>
		<div id="panl_select" style="overflow: auto;"></div>
	</body>
</html>
