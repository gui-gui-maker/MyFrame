<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>

<head >
    <title></title>
    <%@include file="/k/kui-base-form.jsp" %>
	<script type="text/javascript" src="pub/bpm/js/util.js"></script>
    <script type="text/javascript">
    var tbar="";
    var serviceId = "${param.serviceId}";//提交数据的id
 	var activityId = "${param.activityId}";//流程id
    var processId = "${param.processId}";//

    var areaFlag;//改变状态
 	<bpm:ifPer function="TJY2_FN_XHSQ_SQBM" activityId="${param.activityId}">areaFlag="1";</bpm:ifPer>//申请部门。
 	<bpm:ifPer function="TJY2_FN_XHSQ_KGB" activityId="${param.activityId}">areaFlag="2";</bpm:ifPer>//科管部。
 	<bpm:ifPer function="TJY2_FN_XHSQ_ZGB" activityId="${param.activityId}">areaFlag="3";</bpm:ifPer>//质管部。
 	<bpm:ifPer function="TJY2_FN_XHSQ_FGYLD" activityId="${param.activityId}">areaFlag="4";</bpm:ifPer>//分管院领导。
 	<bpm:ifPer function="TJY2_FN_XHSQ_JSFZR" activityId="${param.activityId}">areaFlag="5";</bpm:ifPer>//技术负责人。
 	<bpm:ifPer function="TJY2_FN_XHSQ_YZ" activityId="${param.activityId}">areaFlag="6";</bpm:ifPer>//院长
 	$(function () {
         tbar=[{ text: '审核不通过', id: 'del', icon: 'del', click: nosubmitSh},
               { text: '通过', id: 'up', icon: 'save', click: submitSh},
             { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
    	$("#form1").initForm({
            showToolbar: true,
            toolbarPosition: "bottom",
            toolbar: tbar
    	});
    });
 	
 	 function submitSh(){
     	var serviceId = "${param.serviceId}";//提交数据的id
     	var activityId = "${param.activityId}";//流程id
     	//alert(serviceId+"=="+activityId);
     	var obj=$("#form1").validate().form();
     	
         $.ligerDialog.confirm('是否提交审核？', function (yes){
         if(!yes){return false;}
          $("body").mask("提交中...");
          getServiceFlowConfig("TJY2_FN_XHSQ1","",function(result,data){
                 if(result){
                      top.$.ajax({
                          url: "archives/yijina/daxhtj.do?id="+serviceId+
                         		 "&typeCode=TJY2_FN_XHSQ1&status="+"&activityId="+activityId+"&areaFlag="+areaFlag,
                          type: "GET",
                          dataType:'json',
                          async: false,
                          success:function (data) {
                              if (data) {
                                	$("body").unmask();
	                            	 if(obj){
	                            		 if(areaFlag=="1"){
			                            	$("#auditStep").val("申请部门审核");
	                            		 }else if(areaFlag=="2"){
			                            	$("#auditStep").val("科管部审核");
		                            	 }else if(areaFlag=="3"){
			                            	$("#auditStep").val("质管部审核");
		                            	 }else if(areaFlag=="4"){
			                            	$("#auditStep").val("分管院领导审核");
		                            	 }else if(areaFlag=="5"){
			                            	$("#auditStep").val("技术负责人审核");
		                            	 }if(areaFlag=="6"){
			                            	$("#auditStep").val("院长审核");
		                            	 }
		                            	 $("#auditResult").val("通过");
	                           			 $("#form1").submit();
	                               	}else{
	                           		 	return;
	                           	 	}
       	                         	//$.ligerDialog.success("提交成功！");
	                            	 api.data.window.api.data.window.Qm.refreshGrid();
	 			                     api.data.window.api.close();
	 			                     api.close();
                             	 	
                              }
                          },
                      });
                 }else{
                	 $.ligerDialog.error("出错了！请重试！");
                  
                  $("body").unmask();
                 }
              });
         });
     	
     }
    function nosubmitSh(){
     	var obj=$("#form1").validate().form();
    	$.ligerDialog.confirm('是否要不通过审核？', function (yes){
           if(!yes){return false;}
    	 $("body").mask("正在处理，请稍后！");
    	 
    	 getServiceFlowConfig("TJY2_FN_XHSQ1","",function(result,data){
             if(result){
                  top.$.ajax({
                      url: "archives/yijina/daxhth.do?id="+serviceId+
                     		 "&typeCode=TJY2_FN_XHSQ1&status="+"&activityId="+activityId+"&areaFlag="+areaFlag+"&processId="+processId,
                      type: "GET",
                      dataType:'json',
                      async: false,
                      success:function (data) {
                          if (data) {
                             //$.ligerDialog.alert("操作成功！！！");
                             $("body").unmask();
                             if(obj){
                            	 if(areaFlag=="1"){
		                            	$("#auditStep").val("申请部门");
                         		 }else if(areaFlag=="2"){
	                            	$("#auditStep").val("科管部");
                            	 }else if(areaFlag=="3"){
	                            	$("#auditStep").val("质管部");
                            	 }else if(areaFlag=="4"){
	                            	$("#auditStep").val("分管院领导");
                            	 }else if(areaFlag=="5"){
	                            	$("#auditStep").val("技术负责人");
                            	 }if(areaFlag=="6"){
	                            	$("#auditStep").val("院长");
                            	 }
                            	 $("#auditResult").val("未通过已退回");
                       			 $("#form1").submit();
                           	}else{
                       		 	return;
                       	 	}
                             api.data.window.api.data.window.Qm.refreshGrid();
			                 api.data.window.api.close();
			                 api.close();
                          }
                      },
                      error:function () {
                    	  $.ligerDialog.error("出错了！请重试！");
                          $("body").unmask();
                      }
                  });
             }else{
            	 $.ligerDialog.error("出错了！请重试！！！");
              $("body").unmask();
             }
          });
     });
    }
    </script>
</head>
<body>
	<form id="form1" action="archives/yijina/savexh.do" getAction="archives/yijina/detail.do?id=${param.id}">
		<input type="hidden" id="id" name="id"/>
		<input name="auditResult" id="auditResult" type="hidden" />
		<input type="hidden" id="fileId" name="fileId" value="${param.serviceId}"/>
		<input name="auditStep" id="auditStep" type="hidden" />
		
				<fieldset class="l-fieldset">
					<legend class="l-legend">
						<div>
							意见
						</div>
					</legend>
		<table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table" id="">
					<tr>
						<td class="l-t-td-left">审核意见:</td>
						<td class="l-t-td-right"><textarea name="auditOpinion" id="auditOpinion" rows="5" cols="25" class="l-textarea"  validate="{required:,maxlength:2000}"></textarea></td>
					</tr>
					
					</table>
			</fieldset>
	</form>
</body>
</html>