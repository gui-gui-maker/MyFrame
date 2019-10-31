<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<title></title>
	<!-- 每个页面引入，页面编码、引入js，页面标题 -->
	<%@include file="/k/kui-base-form.jsp"%>
	<head>
		<script type="text/javascript">
			$(function() {
				$("#form1").initForm({ //参数设置示例
					toolbar : [ {
						text : '保存',
						//id : 'save',
						icon : 'save',
						click : save
					}, {
						text : '关闭',
						//id : 'close',
						icon : 'cancel',
						click : close
					}],
					getSuccess : function(res) {
					}
				});			
			})
		
			function close(){
				api.data.window.Qm.refreshGrid();			
				api.close();
			}
			
			function save(){
				var  flow_type= ${param.type};
				if($('#revise_remark').val()==null || $('#revise_remark').val()==undefined || $('#revise_remark').val()==""){
					top.$.dialog.notice({content:'请填写退回原因！'});
					return;
				}

				//判断是否是报告签发 报告签发回退分2步 一步回退上一步，一步回退到报告起草
				if(flow_type=="05"){
					var backType=$('#backStep').ligerGetRadioGroupManager().getValue();
					url = "inspection/zzjd/backReport.do?type=${param.type}&flow_num=${param.flow_num}&acc_id=${param.acc_id}&infoId=${param.infoId}&backType="+backType;
				}else{
					url = "inspection/zzjd/backReport.do?type=${param.type}&flow_num=${param.flow_num}&acc_id=${param.acc_id}&infoId=${param.infoId}";
				}
				
				//验证表单数据
				if($('#form1').validate().form()){
					var formData = $("#form1").getValues();
			        var data = {};
			        data = formData;
			 
			        $("body").mask("正在保存数据，请稍后！");
			        $.ajax({
			            url: url,
			            type: "POST",
			            datatype: "json",
			           	data: {dataStr : $.ligerui.toJSON(data)},
			            success: function (resp, item) {
			            	$("body").unmask();
			                if (resp["success"]) {
			                	if(api.data.window.Qm)
			                	{
			                		api.data.window.Qm.refreshGrid();
			                	}
			                    top.$.dialog.notice({content:'退回成功！'});
			                    api.data.window.Qm.refreshGrid();			                    
			                    api.close();
			                }else{
			                	$.ligerDialog.error('提示：' + resp.msg);
			                }
			            },
			            error: function (resp, item) {
			            	$("body").unmask();
			                $.ligerDialog.error('提示：' + resp.msg);
			            }
			        });
				}
			}	
		</script>
	</head>
	<body>
		<form id="form1"  >
			<table border="1" cellpadding="0" cellspacing="0" width="" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">退回原因：</td>
					<td class="l-t-td-right"  colspan="2">
						<textarea name="revise_remark" id="revise_remark" rows="3" cols="" ext_type="string" ext_maxLength="200" ext_name="备注" 
							isNull="Y" class="area_text"></textarea>
					</td>
				</tr>
				<c:if test='${param.type=="05"}'>
				<tr id="back" >
					<td class="l-t-td-left">退回步骤：</td>
					<td class="l-t-td-right" >
						<input type="radio" name="backStep"  id="backStep" ltype="radioGroup" validate="{required:false}"
								ligerui="{value:'1',data: [ { text:'退回至【上一步】进行报告审核', id:'1' }, { text:'退回至【报告送审】进行报告修改', id:'2' } ] }"/>
					</td>
				</tr>
				</c:if>		
			</table>			
		</form>	
	</body>
</html>
