
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head pageStatus="${param.status}">
		<title></title>
		<!-- 每个页面引入，页面编码、引入js，页面标题 -->
		<%@include file="/k/kui-base-form.jsp"%>

		<script type="text/javascript">
		
		
		
			$(function() {
				
				$("#form1").initForm({ //参数设置示例
					toolbar : [ {
						text : '提交',
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
				
				
				
				
				
				
				
				
			
				var report_type =$('#report_type').ligerGetComboBoxManager().getValue();
				
		
					url = "department/basic/subRport.do?ids=${param.id}&report_type="+report_type+"&flowId=${param.flowId}";
					
					
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
			
	
			
	</script>
	</head>
	<body>
		
	<form id="form1"  >

				<fieldset class="l-fieldset" >
					<legend class="l-legend">
						<div>
						监检证书
						</div>
					</legend>
					   <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table" >
          
				<tr>
						<td class="l-t-td-left">证书类型：</td>
						<td class="l-t-td-right">
						<input type="text"  name="report_type" id="report_type"  ltype="select"  validate="{required:true}"
						ligerui="{
					
						readonly:true,
						tree:{checkbox:false,data: <u:dict sql="select t.id, t.report_file, t.id code, t.report_name text   from base_reports t where  Substr(t.report_name,0,3)='产品-'"/>}
						}"/>
						
						</td>
						
					</tr>
       					
		</table>
				</fieldset>
				
		</form>
	</body>
</html>
