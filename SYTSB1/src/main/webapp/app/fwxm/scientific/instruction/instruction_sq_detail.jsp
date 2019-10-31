<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
    CurrentSessionUser user=(CurrentSessionUser)request.getSession().getAttribute("currentSessionUser");
%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!--获取当前登录人  -->
<%
	CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
	User uu = (User)curUser.getSysUser();
	com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
	String userId=e.getId();
	String userid = SecurityUtil.getSecurityUser().getId();
	String users=curUser.getName();
	%>
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<head pageStatus="${param.status}">
<title>设备采购申请</title>
<%@ include file="/k/kui-base-form.jsp"%>
<%
	String status = request.getParameter("status");
%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript" src="app/fwxm/scientific/instruction/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript" src="app/common/js/render.js"></script>
<link rel="Stylesheet" href="app/finance/css/jquery.autocomplete.css" />
<!-- <link type="text/css" rel="stylesheet" href="app/finance/css/form_detail.css" />  -->
<script type="text/javascript" src="app/humanresources/js/doc_order.js"></script>
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="text/javascript" src="app/common/lodop/LodopFuncs.js"></script>
<script type="text/javascript">
	//jQuery页面载入事件
var pageStatus="<%=status%>";
var columns=[];
	$(function(){
		//设置文件名称，文件编号
    	var file_name=api.data.file_name;
 		var file_num=api.data.file_num;
 		if(file_name!=""){
 			$("#projectName").val(file_name);
 		}
 		if(file_num!=""){
 			$("#projectNum").val(file_num);
 		}	 
		$("#formObj").initForm({ //参数设置示例
          	toolbar : [ {
   	        	text : '保存',
   	        	id : 'save',
   		        icon : 'save',
   		        click : save
   	          },{
   	        	text : '提交',
   	        	id : 'submit',
   		        icon : 'save',
   		        click : submit
   	          }, {
   	           	text : '关闭',
   		        id : 'close',
   		        icon : 'cancel',
   		        click : close
   	          } ],
			getSuccess : function(res) {
				if(res.success){
					/* deviceGrid.loadData({
						Rows : res.inspectionDatas
					}); */
					$("#formObj").setValues({id:res.id});
					$("#formObj").setValues(res.data);
					
				}
			}
		
		});
		$("body").append('<object style="height:1px;" id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0><embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed></object>');
		
	});

	 
	function submit(){
		if($('#formObj').validate().form()){
		if(${param.tj!=0}){//审批、审核
			 top.$.dialog({
					width : 400,
					height : 200,
					lock: true,
					parent: null,
					data: {window: window},
					title: "审核结论",
					content: 'url:app/fwxm/scientific/instruction/choose_opinion_sq.jsp',
					cancel: true,
					ok : function() {
						  var data = this.iframe.contentWindow.getSelectResult();
						  if(${param.tj==2}&& data.opinion=="0"){//审批
							  doSubmit(null,null,data.opinion,data.remark);
						  }else{
							  selectUnitOrUser1(1,0, null, null, function(datas){
						           			if(datas.code!=null&&datas.code.length>0&&datas.code!="undefined"){
						           				doSubmit(datas.code,datas.name,data.opinion,data.remark);
						           			}else{
						           				top.$.dialog.alert("请选择一个用户!");
						           			}
					            	   });  
						  }
						  
						
					}
			 });
		}else{
	            	   selectUnitOrUser1(1,0, null, null, function(data){
		           			if(data.code!=null&&data.code.length>0&&data.code!="undefined"){
		           				doSubmit(data.code,data.name,null,null)
		           			}else{
		           				top.$.dialog.alert("请选择一个用户!");
		           			}
	           			});
			
		}
		}
	}
	
	function doSubmit(tjUserId,tjUserName,opinion,remark){
    	var formData = $("#formObj").getValues();
        formData["tjUserId"]=tjUserId;
        formData["tjUserName"]=tjUserName;
        formData["tjType"]="2";
        formData["auditOpinion"]=remark;
        if(${param.tj==1}){//审核环节
//             formData["auditOpinion"]=remark;
            formData["opinion"]=opinion;
        }
        if(${param.tj==2}){//审批环节
//             formData["signOpinion"]=remark;
            formData["opinion"]=opinion;
        }
    $("body").mask("提交中...");
       $.ajax({
           url: "com/tjy2/instructionProject/saveBasic.do",
           type: "POST",
           data:{"instruction":$.ligerui.toJSON(formData)},
           success : function(data, stats) {
				$("body").unmask();
				if (data["success"]) {
					if("${param.tj!=1}"){
						top.$.dialog.notice({
							content : '提交成功'
						});
						api.data.window.Qm.refreshGrid();
						api.close();
					}
				} else {
					$.ligerDialog.error('提示：' + data.message);
				}
			},
           error : function(data) {
               $("body").unmask();
               $.ligerDialog.error('保存数据失败！');
           }
           });
	}
	
	function save(){
		//alert($("#type").ligerComboBox().getValue());
		//$("#formObj").submit();
		
		if($('#formObj').validate().form()){
		var formData = $("#formObj").getValues();
		 $.ajax({
	           url: "com/tjy2/instructionProject/saveBasic.do",
	           type: "POST",
	           data:{"instruction":$.ligerui.toJSON(formData)},
	           success : function(data, stats) {
					$("body").unmask();
					if (data["success"]) {
						if("${param.tj!=1}"){
							top.$.dialog.notice({
								content : '提交成功'
							});
							api.data.window.Qm.refreshGrid();
							api.close();
						}
					} else {
						$.ligerDialog.error('提示：' + data.message);
					}
				},
	           error : function(data) {
	               $("body").unmask();
	               $.ligerDialog.error('保存数据失败！');
	           }
	           });
	}
		
	}
	function close(){	
		 api.close();
	}		
	

	
	
  
      </script>
      
<style type="text/css" media="print" id="pstyle">
* {font-family:"宋体";font-size:12px;letter-spacing:normal;}
* {
    font-family:"宋体";
    font-size:12px;
    letter-spacing:normal;
    
}
h1{font-family:宋体;font-size:6mm; text-align:center;margin:10px 0 0 0;}
table{ margin:-2 auto;width: 650px;}
table td{ height:40px;}
.l-detail-table td, .l-detail-table {
    border-collapse: collapse;
    border: 1px solid black;
}
#table1,#table2,#table3{
 padding:5px;
    border:0px solid #CFE3F8;
    border-top:0px;
    border-left:0px;
    word-break:break-all;
    table-layout:fixed;
}
#table4{
 padding:5px;
    border:0px solid #CFE3F8;
    border-top:0px;
    border-left:0px;
    word-break:break-all;
    table-layout:fixed;
    text-align:center;
    margin-left:48px;
}
.l-t-td-left{ text-align:center;}
.fieldset-caption{margin:15px 0px 2px 0px;}
.l-t-td-title{}
</style>
</head>
<body>
	<div title="计划表表" id="form1">
<!-- 	action="com/tjy2/instructionProject/saveBasic.do" -->
	<form name="formObj" id="formObj" method="post" 
		getAction="com/tjy2/instructionProject/detail.do?id=${param.id}">
		<h1 style="padding:5mm 0 2mm 0;font-family:微软雅黑;font-size:6mm;text-align:center;">文&nbsp;件&nbsp;申&nbsp;请&nbsp;（修&nbsp;改）</h1></br>
		<input type="hidden" id="id" name="id"/>
		<input type="hidden" id="quality_file_id" name="quality_file_id" value="${param.quality_file_id }"/>
		<input type="hidden" name="status"/>
		
		
		<input type="hidden" name="createDate"/>
		<input type="hidden" name="createId"/>
		<input type="hidden" name="createMan"/>
		<input type="hidden" name="auditId"/>
		<input type="hidden" name="auditMan"/>
		<input type="hidden" name="auditDate"/>
		<input type="hidden" name="signId"/>
		<input type="hidden" name="signMan"/>
		<input type="hidden" name="projectNo"/>
			<table id="table1" cellpadding="3" cellspacing="0" class="l-detail-table">
			     <c:if test="${param.status=='detail' }">
			      <tr>
					<td class="l-t-td-left"></td>
					<td class="l-t-td-right" >
					</td>
					<td class="l-t-td-left"></td>
					<td class="l-t-td-right" >
					</td>
					<td class="l-t-td-left">编号：</td>
					<td class="l-t-td-right">
					<input type="text" ltype='text'
					 validate="{required:true}" name="projectNo" id="projectNo" />
					</td>
				</tr>
			     </c:if>
			    
				<tr>
					<td class="l-t-td-left">文件名称：</td>
					<td class="l-t-td-right" >
					<input type="text" ltype='text'
					 validate="{required:true}" name="projectName" id="projectName" />
					</td>
					<td class="l-t-td-left">制定\修订：</td>
					<td class="l-t-td-right" >
					<input name="type" id="type" type="text" ltype="select" validate="{required:true}" ligerui="{
					initValue:'',
					readonly:true,
					onchange:function(value){
					if(${param.tj==0 }){
					if(value=='制定'){
							$('#projectNum').removeAttr('required');
							$('#projectNum').attr('class','l-text-field');
						}else{
							$('#projectNum').attr('required','required');
							$('#projectNum').attr('class','requiredstar l-text-field');
						
						}
					}
						
					},
					data: <u:dict code='instruction_type' />
					}"/>
					
					</td>
					<td class="l-t-td-left">文件编号：</td>
					<td class="l-t-td-right">
					<input type="text" ltype='text'
					 validate="{required:true}" name="projectNum" id="projectNum" />
					</td>
				</tr>
				<tr>
          <td class="l-t-td-left">修改内容：</td>
            <td colspan="5">
            <textarea name="content" rows="8" cols="25" class="l-textarea" validate="{maxlength:4000}"></textarea>
            	
            </td>
        </tr>
        <tr>
         <td class="l-t-td-left">修改理由：</td>
            <td colspan="5">
            <textarea name="reason" rows="8" cols="25" class="l-textarea" validate="{maxlength:4000}"></textarea>
            </td>
        </tr> 
				</table>
			<%if(status.equals("detail")){ %>
			<table id="table2" cellpadding="3" cellspacing="0" class="l-detail-table">
			     <tr>
			     <td class="l-t-td-left">编制：</td>
					<td class="l-t-td-right">
					<input type="hidden" name="createId"/>
					<input type="text" ltype='text'
					 validate="{required:true}" name="createMan" id="createMan" />
					</td>
				<td class="l-t-td-left">编制日期：</td>
					<td class="l-t-td-right">
					<input type="text" ltype='date'
					 validate="{required:true}" name="createDate" id="createDate" ligerui="{initValue:'',format:'yyyy-MM-dd'}"/>
					</td>
			     </tr>
			      <tr>
			     <td class="l-t-td-left">审核：</td>
					<td class="l-t-td-right">
					<input type="hidden" name="auditId"/>
					<input type="text" ltype='text'
					 validate="{required:true}" name="auditMan" id="auditMan" />
					</td>
				<td class="l-t-td-left">审核日期：</td>
					<td class="l-t-td-right">
					<input type="text" ltype='date'
					 validate="{required:true}" name="auditDate" id="auditDate" ligerui="{initValue:'',format:'yyyy-MM-dd'}"/>
					</td>
			     </tr>
			      <tr>
			     <td class="l-t-td-left">批准：</td>
					<td class="l-t-td-right">
					<input type="hidden" name="signId"/>
					<input type="text" ltype='text'
					 validate="{required:true}" name="signMan" id="signMan" />
					</td>
				<td class="l-t-td-left">批准日期：</td>
					<td class="l-t-td-right">
					<input type="text" ltype="date"
					 validate="{required:true}" name="signDate" id="signDate" ligerui="{initValue:'',format:'yyyy-MM-dd'}"/>
					</td>
			     </tr>
				</table>
				<%} %>
	</form>
	</div>
</body>
</html>
