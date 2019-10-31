<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head pageStatus="${status}">
	<title></title>
	<style type="text/css">
	.table-up-filelist {border:1px solid #96c2f1;border-right:0px;border-bottom:1px;border-collapse:collapse;}
	.table-up-filelist td {padding:5px;border:1px solid #96c2f1;border-top:0px;border-left:1px;}
	.table-up-filelist-tr td {font-weight:bold}
	.table-up-filelist-td {border:1px solid #96c2f1;background:#eff7ff;text-align:center;}
	.table-up-filelist-e-td1 {width:350px;}
	.table-up-filelist-e-td2 {text-align:center;width:200px;}
	.table-up-filelist-e-td3 {text-align:center;width:200px;}
	</style>
	<!-- 每个页面引入，页面编码、引入js，页面标题 -->
	<%@include file="/k/kui-base-form.jsp"%>
	<script type="text/javascript">
		var pageStatus = "${status}";		
		var id = "${param.id}";
		var report_type = "${param.report_type}";
		var device_id = "${param.device_id}";
		$(function() {
			$("#form1").initForm({ //参数设置示例
				toolbar : [ {
						text : '确定',
						//id : 'save',
						icon : 'save',
						click : save
					}, {
						text : '关闭',
						//id : 'close',
						icon : 'cancel',
						click : close
					}
				],
				getSuccess : function(res) {
				},
				afterParse:function(){
					setValue =	function(obj,name,tdid,pageIndex,id_name){	
						$("#"+tdid).find("input[name='"+name+"']").val(obj.value);
					}	
				}
			});
		})
		
		function close(){	
			api.close();
		}
		
		function save(){
			//验证表单数据
			if($('#form1').validate().form()){
				var formData = $("#form1").getValues();
			    var data = {};
			    data = formData;
			    var cObj=document.getElementsByName("report_item");
			    var cPep=document.getElementsByName("page_inspection_op");
			    var xsq=0
			   	var cArr=[];
			  	var carr_1=[];
			 	for (var i = 0; i < cObj.length; i++) {
			    	var inputObj=cObj[i];
			    	var CpeObj = cPep[i];
			    	if (inputObj.type.toLowerCase()=="text") {
			    		cArr[cArr.length]=inputObj.value;
			    	}
			    	if (inputObj.checked) {
						if($(inputObj).parent().parent().parent().text().indexOf("限速器")!=-1){
				    		xsq=xsq+1;
				    	}
				    	cArr[cArr.length]=inputObj.value;
			    	}
			    }
			    var report_item = carr_1.join(",");
			    var xsqsb = cArr.join(",");
			 	url = "inspection/zzjd/saveItem.do?fk_inspection_info_id=${param.id}&report_item="+xsqsb+"&xsq="+xsq;			    
			    $("body").mask("正在保存数据，请稍后！");
			    $.ajax({
			    	url: url,
			   		type: "POST",
			     	dataType: "json",
			   		data: {dataStr : $.ligerui.toJSON(data)},
			     	success: function (data, stats) {
			        	$("body").unmask();
			          	if (data["success"]) {
			           		if(api.data.window.Qm){
			                	api.data.window.Qm.refreshGrid();
			                }
			                top.$.dialog.notice({content:'保存成功'});
			                top.$.dialog({
			            		width : 800, 
			            		height : 500, 
			            		lock : true, 
			            		title:"报告信息",
			            		content: 'url:inspection/zzjd/reportInfoLoad.do?isSub=no&type=input&flow_num=${param.flow_num}&report_type='+report_type+'&ins_info_id='+id+'&activity_id=${param.activity_id}',
			            		data : {"pwindow" : window}
			            	}).max();
						}else{
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
		
		var setValue = function(){}			
	</script>
	</head>
<body>
	<form id="form1">	
		<table  width="100%" height="24" class="table-up-filelist">
			<tr class="table-up-filelist-tr">
				<td width="350" class="table-up-filelist-td">报告页</td>
				<td width="200" class="table-up-filelist-td">报告页检验员</td>
				<td width="200" class="table-up-filelist-td">报告页审核人</td>
			</tr>
		</table>
		<table  width="100%" class="table-up-filelist">
			<input type="hidden" name="report_type" value="${param.report_type}">
			<input type="hidden" name="id" >
			<c:forEach items="${reportItems}" var="item" varStatus="vs"> 
				<tr id="trp-${vs.index}" class="table-up-filelist-e-tr"> 
					<c:choose>
					<c:when test='${item.is_must=="yes"}'>	
						<td   class="table-up-filelist-e-td1">
							&nbsp;√&nbsp;<div style="display:none;"><input type="checkbox" name="report_item" value="${item.page_index}"  checked /></div>&nbsp;${item.index_text}
						</td>
					</c:when>
					<c:when test='${item.is_must=="no"}'>
						<td   class="table-up-filelist-e-td1">
						
							<input style="border:0;border-bottom:1 solid black;" type="checkbox" name="report_item" value="${item.page_index}"  ${item.is_disCheck} />&nbsp;${item.index_text}
						</td>
					</c:when>
					</c:choose>
					<c:choose>
						<c:when test='${item.is_inspection=="1"}'>
						<td   class="table-up-filelist-e-td2">
							<input   type="text" name="page_inspection_id"  value="" ltype="select" 
							
							onchange="setValue(this,'page_inspection_op','trp-${vs.index}')" validate="{required:false}"
							ligerui="{
							selectBoxHeight:400,
							readonly:true,
							value:'${item.page_inspection_id}',
							tree:{checkbox:true,data: <u:dict sql="select t.id code,t.name text from employee t where t.org_id='${param.org_id}' "/>}
							}"/>
							<input type="hidden" name="inspect_seq" id="inspect_seq" value="${item.inspect_seq}"/>
							<input type="hidden" name="page_inspection_op" value="${item.page_inspection_op}" />
								
						</td>
						</c:when>
						<c:when test='${item.is_inspection=="2"}'>
							<td class="table-up-filelist-e-td2"></td>
						</c:when>
					</c:choose>			
					<c:choose>
						<c:when test='${item.is_check=="1"}'>
							<td  id="pco-${vs.index}" class="table-up-filelist-e-td3">
								<input style="border:0;border-bottom:1 solid black;" type="text" name="page_check_id" value="${item.page_check_id}"  value="" ltype="select" onchange="setValue(this,'page_check_op','trp-${vs.index}')" validate="{required:false}"
								ligerui="{
							
								readonly:true,
								value:'${item.page_check_id}',
								tree:{checkbox:false,data: <u:dict sql="select t.id code,t.name text from employee t where t.org_id='${param.org_id}' "/>}
								}"/>
								<input type="hidden" name="audit_seq" id="audit_seq" value="${item.audit_seq}"/>
								<input type="hidden" name="page_check_op" id="page_check_op"  value="${item.page_check_op}"/>
							</td>
						</c:when>
						<c:when test='${item.is_check=="2"}'>
							<td class="table-up-filelist-e-td3"></td>
						</c:when>
					</c:choose>
				</tr>
			</c:forEach>				
		</table>	
	</form>	
</body>
</html>