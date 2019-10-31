<%@ page contentType="text/html;charset=UTF-8"%>
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
 	var processId= "${param.processId}";//过程id
    var areaFlag;//改变状态
	<bpm:ifPer function="TJY2_JHLZ" activityId = "${param.activityId}">areaFlag="2";</bpm:ifPer>//计划论证
	<bpm:ifPer function="TJY2_SQBMSP" activityId = "${param.activityId}">areaFlag="3";</bpm:ifPer>//申请部门审批
	<bpm:ifPer function="TJY2_ZNBMSP" activityId = "${param.activityId}">areaFlag="4";</bpm:ifPer>//职能部门审批


    
 	$(function () {
         tbar=[{ text: '审核不通过', id: 'shbtg', icon: 'del', click: shbtg},
               { text: '通过', id: 'shtg', icon: 'save', click: shtg},
             { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
        
    	$("#form1").initForm({
            showToolbar: true,
            toolbarPosition: "bottom",
            toolbar: tbar,
            success: function (response) {
    			if(response.success){
    				top.$.notice("保存成功！",3);
    				api.data.window.Qm.refreshGrid();
    				api.close();
    			}
    			else{
    				$.ligerDialog.error("操作失败！<br/>" + response.msg);
    			}
    		}
    	 });
    	});
 	
 	 function shtg(){
 		var opinion=$("#auditOpinion").val();
 		 $("#auditResult").val("通过");
     	var obj=$("#form1").validate().form();
         $.ligerDialog.confirm('是否提交审核？', function (yes){
         if(!yes){return false;}
          $("body").mask("提交中...");
          getServiceFlowConfig("TJY2_EQUIPMENT_BUY","",function(result,data){
                 if(result){
                     top.$.ajax({
                          url: "buyYijianAction/zltj.do?id="+serviceId+
                         		 "&typeCode=TJY2_EQUIPMENT_BUY&status="+"&activityId="+activityId+"&areaFlag="+areaFlag,
                          type: "POST",
                          dataType:'json',
                          data:"&opinion="+opinion,
                          async: false,
                          success:function (data) {
                        	  api.close();
                        	  api.data.window.api.close();
                        	  api.data.window.api.data.window.Qm.refreshGrid();
                              if (data) {
                                	$("body").unmask();
	                           		$("#form1").submit();
                              }else{
                            	  return;
                            	  api.colse();
                              }
                          },
                      });
                 }else{
                  $.ligerDialog.alert("出错了！请重试！");
                  $("body").unmask();
                 }
              });
         });        
     	
     }
 	 
 	 
    function directChange(){ 
    	
    } 
    function shbtg(){
    	var opinion=$("#auditOpinion").val();
    	$("#auditResult").val("不通过");
     	var obj=$("#form1").validate().form();
    	$.ligerDialog.confirm('是否要不通过审核？', function (yes){
           if(!yes){return false;}
    	 $("body").mask("正在处理，请稍后！");
    	 
    	 getServiceFlowConfig("TJY2_EQUIPMENT_BUY","",function(result,data){
             
    		 if(result){
                  top.$.ajax({
                      url: "buyYijianAction/xgth.do?id="+serviceId+
                     		 "&typeCode=TJY2_EQUIPMENT_BUY&status="+"&activityId="+activityId+"&areaFlag="+areaFlag+"&processId="+processId,
                      type: "POST",
                      dataType:'json',
                      data:"&opinion="+opinion,
                      async: false,
                      success:function (data) { 
                    	  
                          if (data) {
                             $("body").unmask();
                             //if(obj){
                       			 $("#form1").submit();
                           }else{
                       		 	return;
                          }
                      },
                      error:function () {
                    	  $.ligerDialog.alert("出错了!！请重试！");
                          $("body").unmask();
                      }
                  });
             }else{
              $.ligerDialog.alert("出错了！请重试！");
              $("body").unmask();
             }
    		
          });
     });
    }
    </script>
</head>
<body>
	<form id="form1" action="buyYijianAction/saveShyj.do" getAction="buyYijianAction/detail.do?id=${param.id}">
		<input type="hidden" id="id" name="id"/>
		<input name="auditResult" id="auditResult" type="hidden" />
		<input type="hidden" id="fileId" name="fileId" value="${param.serviceId}"/>
		<table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table" id="">
					<tr>
						<td class="l-t-td-left">审核意见:</td>
						<td class="l-t-td-right"><textarea name="auditOpinion" id="auditOpinion" rows="5" cols="25" class="l-textarea"  validate="{maxlength:600}"></textarea></td>
					</tr>
					
					</table>
</body>
</html>