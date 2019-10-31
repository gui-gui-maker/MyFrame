<%@page import="com.khnt.rbac.impl.bean.Org"%>
<%@page import="util.TS_Util"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
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
		.table-up-filelist-e-td2 {text-align:center;width:150px;}
		.table-up-filelist-e-td3 {text-align:center;width:150px;}
		</style>
		<!-- 每个页面引入，页面编码、引入js，页面标题 -->
		<%@include file="/k/kui-base-form.jsp"%>
		
		<script type="text/javascript">
		var pageStatus = "${status}";
		var id = "${param.id}";
		var report_type = "${param.report_type}";
		var device_id = "${param.device_id}";
		var org_id = "${param.org_id}";
		$(function() {
			//	$.getJSON('department/basic/flow_reportInput.do',{id:id,report_type:report_type},function(data){
					//$("#"+tdid).find("input[name='"+name+"']").val(obj.value);
			//	});
				//createJzorxsGrid();
				$("#form1").initForm({ //参数设置示例
					toolbar : [ {
						text : '确定',
						//id : 'save',
						icon : 'save',
						click : save
					},{
						text : '关闭',
						//id : 'close',
						icon : 'cancel',
						click : close
					}],
					getSuccess : function(res) {
					
					},
					afterParse:function(){
						setValue =	function(obj,name,tdid,pageIndex,id_name){
							//var op_ids =$('input[name="'+id_name+'"]').ligerGetComboBoxManager().getValue();
							//判断人员是否持有相应的证书
							//$.getJSON('department/basic/checkCer.do',{userId:op_ids,infoId:'${param.id}',report_type:'${param.report_type}',pageIndex:pageIndex},function(data){
								$("#"+tdid).find("input[name='"+name+"']").val(obj.value);
						//	});
						}	
					}
				});
			})
		
			function close(url){	
				 api.close();
			}
			
			function save(url){
				//验证表单数据
				if($('#form1').validate().form())
				{
					var formData = $("#form1").getValues();
			        var data = {};
			        data = formData;
			        //var a1=$("input[name=report_item]:checked").val();$.ligerDialog.alert(a1);return;
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
							//alert($(inputObj).parent().parent().parent().text().trim().indexOf("限速器"));
				    		if($(inputObj).parent().parent().parent().text().indexOf("限速器")!=-1){
				    			xsq=xsq+1;
				    		}
			    			cArr[cArr.length]=inputObj.value;
			    		}
			    	}
			    	
			    	var report_item = carr_1.join(",");
			    	var xsqsb = cArr.join(",");
			    	var sort = "${deviceSort}"
					if(sort!='3'){
						var fee = $('#getM').val();
						url = "department/basic/saveItem.do?fk_inspection_info_id=${param.id}&report_item="+xsqsb+"&xsq="+xsq+"&fee="+fee;
					}else{
						if(org_id=='100090'){
							var fee = $('#getM').val();
							url = "department/basic/saveItem.do?fk_inspection_info_id=${param.id}&report_item="+xsqsb+"&xsq="+xsq+"&fee="+fee;
						}else{
							url = "department/basic/saveItem.do?fk_inspection_info_id=${param.id}&report_item="+xsqsb+"&xsq="+xsq;
						}
					}
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
			            			content: 'url:department/basic/reportInfoLoad.do?isSub=no&type=${param.type}&flow_num=${param.flow_num}&report_type='+report_type+'&ins_info_id='+id+'&device_id='+device_id+'&activity_id=${param.activity_id}',
			            			data : {"window" : window}
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
	<form id="form1"   >	
		<table  width="100%" height="24" class="table-up-filelist">
			<tr class="table-up-filelist-tr">
				<td width="350" class="table-up-filelist-td">报告页</td>
				<td width="150" class="table-up-filelist-td">检验员</td>
				<td width="150" class="table-up-filelist-td">审核人</td>
				<td width="150" class="table-up-filelist-td">评片人</td>
			</tr>
		</table>
		<table  width="100%" class="table-up-filelist">
			<input type="hidden" name="report_type" value="${param.report_type}">
			<input type="hidden" name="id" >
			<c:forEach items="${reportItems}" var="item" varStatus="vs"> 
				<tr id="trp-${vs.index}" class="table-up-filelist-e-tr"> 
					<input type="hidden" name="item_page_id" id="item_page_id" value="${item.item_page_id}"/>
					<input type="hidden" name="page_name" id="page_name" value="${item.page_name}"/>
					<c:choose>
						<c:when test='${item.is_must=="yes"}'>	
							<td class="table-up-filelist-e-td1">
							&nbsp;√&nbsp;<div style="display:none;"><input type="checkbox" name="report_item" value="${item.page_index}"  checked /></div>&nbsp;${item.index_text}
							</td>
						</c:when>
						<c:when test='${item.is_must=="no"}'>
							<td class="table-up-filelist-e-td1">
								<input style="border:0;border-bottom:1 solid black;" type="checkbox" name="report_item" value="${item.page_index}"  ${item.is_disCheck} />&nbsp;${item.index_text}
							</td>
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test='${item.is_inspection=="1"}'>
							<td class="table-up-filelist-e-td2">
								<input   type="text" name="page_inspection_id"  value="" ltype="select" 
									onchange="setValue(this,'page_inspection_op','trp-${vs.index}')" validate="{required:false}"
									ligerui="{
									selectBoxHeight:400,
									readonly:true,
									value:'${item.page_inspection_id}',
									tree:{checkbox:true,data: <u:dict sql="select t.id, t.pid, t.code, t.text from (select o.id as id,o.id as code,o.ORG_NAME as text,o.PARENT_ID as pid from sys_org o union select e.id as id, e.id as code, e.NAME as text, e.ORG_ID as pid from employee e where e.ORG_ID != '100049') t where t.id!='100049' start with t.id in ('100029', '100034', '100035','100033', '100065', '100036','100037', '100066','100067','100045','100030') connect by t.pid = prior t.id"/>}
									}"/>
								<input type="hidden" name="inspect_seq" id="inspect_seq" value="${item.inspect_seq}"/>
								<input type="hidden" name="page_inspection_op" value="${item.page_inspection_op}" />
							</td>
						</c:when>
						<c:otherwise>
							<td class="table-up-filelist-e-td2"></td>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test='${item.is_check=="1"}'>
							<td id="pco-${vs.index}" class="table-up-filelist-e-td3">
								<input style="border:0;border-bottom:1 solid black;" type="text" name="page_check_id" value="${item.page_check_id}"  value="" ltype="select" onchange="setValue(this,'page_check_op','trp-${vs.index}')" validate="{required:false}"
								ligerui="{
								readonly:true,
								value:'${item.page_check_id}',
								tree:{checkbox:false,data: <u:dict sql="select t.id, t.pid, t.code, t.text
  from (select o.id        as id,
               o.id        as code,
               o.ORG_NAME  as text,
               o.PARENT_ID as pid
          from sys_org o
        union
        select e.id as id, e.id as code, e.NAME as text, e.ORG_ID as pid
          from employee e, sys_user u, SYS_USER_ROLE r, sys_role role
         where e.ORG_ID != '100049' and e.id = u.employee_id
           and u.id = r.user_id
           and r.role_id = role.id
           and role.name = '无损检测审核人员') t
 where t.id != '100049'
 start with t.id in ('100034',
                     '100035',
                     '100033',
                     '100045',
                     '100065',
                     '100036',
                     '100037',
                     '100066',
                     '100067',
                     '100030')
connect by t.pid = prior t.id"/>}
								}"/>
								<input type="hidden" name="audit_seq" id="audit_seq" value="${item.audit_seq}"/>
								<input type="hidden" name="page_check_op" id="page_check_op"  value="${item.page_check_op}"/>
							</td>
						</c:when>
						<c:otherwise>
							<td class="table-up-filelist-e-td3"></td>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test='${item.is_eval_pic=="1"}'>
							<td class="table-up-filelist-e-td2">
								<input type="text" name="page_eval_pic_id"  value="" ltype="select" 
								onchange="setValue(this,'page_eval_pic_op','trp-${vs.index}')" validate="{required:false}"
								ligerui="{
								selectBoxHeight:400,
								readonly:true,
								value:'${item.page_eval_pic_id}',
								tree:{checkbox:false,data: <u:dict sql="select t.id code,t.name text from employee t where t.org_id='${param.org_id}' "/>}
								}"/>
								<input type="hidden" name="eval_pic_seq" id="eval_pic_seq" value="${item.eval_pic_seq}"/>
								<input type="hidden" name="page_eval_pic_op" value="${item.page_eval_pic_op}" />
							</td>
						</c:when>
						<c:otherwise>
							<td class="table-up-filelist-e-td2"></td>
						</c:otherwise>
					</c:choose>
				</tr>
			</c:forEach>	
			<c:choose>
				<c:when test="${deviceSort!='3'}">
					<tr>
						<td>
							收费金额：
						</td>
						<td colspan="3">
							<input type="text" name="getM" id="getM" value="${advance_fees}" />
						</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:if test="${param.org_id eq '100090'}">
						<tr>
							<td>
								收费金额：
							</td>
							<td colspan="3">
								<input type="text" name="getM" id="getM" value="${advance_fees}" />
							</td>
						</tr>
					</c:if>
				</c:otherwise>				
			</c:choose>
		</table>
	</form>
	</body>
</html>