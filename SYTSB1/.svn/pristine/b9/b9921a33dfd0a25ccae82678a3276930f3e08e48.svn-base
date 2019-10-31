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
    var st = "${param.st}";
    var typeCode="";
    var areaFlag = "${param.areaFlag}";//改变状态
    <bpm:ifPer function="TJY2_ZL_SGCJJYBG_BMFZR" activityId = "${requestScope.activityId}">areaFlag="1";</bpm:ifPer>//部门负责人
 	<bpm:ifPer function="TJY2_ZL_SGCJJYBG_JYRJFZR" activityId = "${requestScope.activityId}">areaFlag="2";</bpm:ifPer>//检验软件负责人
 	<bpm:ifPer function="TJY2_ZL_SGCJJYBG_YWFWBJBR" activityId = "${requestScope.activityId}">areaFlag="3";</bpm:ifPer>//业务服务部经办人
 	<bpm:ifPer function="TJY2_ZL_SGCJJYBG_ZLFZR" activityId = "${requestScope.activityId}">areaFlag="4";</bpm:ifPer>//质量监督管理部负责人（这条是后加的(+﹏+)~狂晕）
    
 	$(function () {
 		if(st=="F"){
			typeCode="TJY2_ZL_SGCJJYBG";
		}else{
			typeCode="TJY2_ZL_SGCJJYBG_WT";
		}
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
    	var zlshyj =$("#auditOpinion").val();
		

        $.ligerDialog.confirm('是否要通过？', function (yes){
        if(!yes){return false;}
         $("body").mask("提交中...");
         getServiceFlowConfig("TJY2_ZL_SGCJJYBG","",function(result,data){
                if(result){
                     top.$.ajax({
                         url: "quality/sgcjjybg/sgcjjybgsh.do?id="+serviceId+
                        		 "&typeCode="+typeCode+"&status="+"&activityId="+activityId+"&areaFlag="+areaFlag+
                        		 "&bgbh="+"&bgbh2="+"&bgbh3="+"&bgbh4="+"&bgbh5="+"&bgbh6="+"&bgbh7=",
                         type: "POST",
                         dataType:'json',
                         data:"&zlshyj="+zlshyj,
                         async: false,
                         success:function (data) {
                             if (data) {
                                $("body").unmask();
                                top.$.notice("审核成功！！！",4);
                                api.data.window.api.data.window.Qm.refreshGrid();//刷新
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
 	 
 	 
    function directChange(){ 
    	
    } 
    function nosubmitSh(){
    	var zlshyj =$("#auditOpinion").val();
        $.ligerDialog.confirm('是否要不通过？', function (yes){
        if(!yes){return false;}
         $("body").mask("提交中...");
         getServiceFlowConfig("TJY2_ZL_SGCJJYBG_WT","",function(result,data){
                if(result){
                     top.$.ajax({
                         url: "quality/sgcjjybg/sgcjjybgth.do?id="+serviceId+
                        		 "&typeCode="+typeCode+"&status="+"&activityId="+activityId+"&areaFlag="
                        		 +areaFlag+"&processId="+processId,
                         type: "POST",
                         dataType:'json',
                         data:"&zlshyj="+zlshyj,
                         async: false,
                         success:function (data) {
                             if (data) {
                                $("body").unmask();
                                top.$.notice("审核成功！！！");
                                api.data.window.api.data.window.Qm.refreshGrid();//刷新
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
    </script>
</head>
<body>
	<form id="form1" action="" getAction="">
		
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