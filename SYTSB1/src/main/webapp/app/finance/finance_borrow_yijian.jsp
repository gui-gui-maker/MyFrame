<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*" %> 
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>

<head pageStatus="${param.pageStatus}">
    <title></title>
    <%@include file="/k/kui-base-form.jsp" %>
	<script type="text/javascript" src="pub/bpm/js/util.js"></script>
    <script type="text/javascript">
    var tbar="";
	var id="${param.id}";
	var up_status="${param.status}";
    var ss=$("#handleOpinion").val();
    var a="1";
    var b="2";
 	$(function () {
         tbar=[{ text: '审核不通过', id: 'del', icon: 'del', click: directChange2},
               { text: '通过', id: 'up', icon: 'save', click: directChange},
             { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
        	$("#jksq").hide();
        if ("${param.pageStatus}"=="detail")
         tbar=[{ text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
    	$("#form1").initForm({
            showToolbar: true,
            toolbarPosition: "bottom",
            toolbar: tbar
    	});
    });
 	 function directChange(){ 
//  	   	var obj=$("#form1").validate().form();
//  		 if(obj){
//  			 $("#auditResult").val("初审通过");
//  			 $("#form1").submit();
//  		 }else{
//  			 return;
//  		}
 		var fileId=$("#fileId").val();
 		var auditOpinion=$("#auditOpinion").val();
 		var auditResult=$("#auditResult").val("初审通过");
 		$.ligerDialog.confirm('是否要审批？', function (yes){
       	 if(!yes){return false;}
           top.$.ajax({
                url: "cw/yijian/save11.do?fileId="+fileId,
                type: "POST",
                dataType:'json',
                data:"&auditOpinion="+auditOpinion+"&a="+a,
                async: false,
                success:function (data) {
                	$("body").unmask();
                    if (data["success"]) {
                        if(api.data.window.api.data.window.Qm){
                            api.data.window.api.data.window.Qm.refreshGrid();
                        }
                        api.close();
                        top.$.dialog.notice({content:'操作成功！'});
                        api.data.window.reLoad();
                        setTimeout("close()","300");
                    }else{
                        $.ligerDialog.error('提示：' + data.msg);
                        $("#save").removeAttr("disabled");
                    }
//	                	 if(data){
//	                         top.$.notice('审批成功！',3);
// 	                         api.data.window.api.data.window.Qm.refreshGrid();
// 			                 api.data.window.api.close();
//			                 api.close();
//	                     }else{
//	                         $.ligerDialog.warn(data.msg);
// 	                         api.data.window.api.data.window.Qm.refreshGrid();
// 		                     api.data.window.api.close();
//		                     api.close();
//	                     }
                },
                error:function () {
                	 $("body").unmask();
                    $.ligerDialog.error("审批失败！");
                     $("#save").removeAttr("disabled");
                }
            });
       });
 		 }
 	function directChange2(){ 
 		var fileId=$("#fileId").val();
 		var auditOpinion=$("#auditOpinion").val();
 		var auditResult=$("#auditResult").val("初审通过");
 		$.ligerDialog.confirm('是否要审批？', function (yes){
       	 if(!yes){return false;}
           top.$.ajax({
                url: "cw/yijian/save11.do?fileId="+fileId,
                type: "POST",
                dataType:'json',
                data:"&auditOpinion="+auditOpinion+"&a="+b,
                async: false,
                success:function (data) {
                	 top.$.notice('审批成功！',3);
                     api.data.window.api.data.window.Qm.refreshGrid();
                     api.close();
                },
                error:function () {
                    $.ligerDialog.error("审批失败！");
                }
            });
       });}
 	
    </script>
</head>
<body>
	<form id="form1" action="cw/yijian/save11.do" getAction="cw/yijian/detail.do?id=${param.id}">
		<input type="hidden" id="id" name="id"/>
		<input type="hidden" id="fileId" name="fileId" value="${param.id}"/>
    	<input type="hidden" id="auditResult" name="auditResult"/>
    	
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>
						意见
				</div>
			</legend>
			<table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table" id="">
				<tr>
					<td class="l-t-td-left">审核人</td>
					<td class="l-t-td-right">
	                    <input id="auditMan" name="auditMan" type="text" ltype='text' ligerUi="{disabled:true}" validate="{required:true}" value="<sec:authentication property="principal.name"/>"/>
	               	</td>
	               	<td class="l-t-td-left">审核时间</td>
						<%SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
						String nowTime=""; 
						nowTime = dateFormat.format(new Date());%>
        			<td class="l-t-td-right"><input name="borrowMoneyDate" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" value="<%=nowTime%>" /></td>
				</tr>
				<tr>
					<td class="l-t-td-left">审核意见</td>
					<td class="l-t-td-right" colspan="3"><textarea name="auditOpinion" id="auditOpinion" rows="5" cols="25" class="l-textarea"  validate="{required:,maxlength:2000}"></textarea></td>
				</tr>
			</table>
		</fieldset>
		
	</form>
</body>
</html>