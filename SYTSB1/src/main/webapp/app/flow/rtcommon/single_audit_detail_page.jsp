<%@page import="com.khnt.base.Factory"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head pageStatus="${param.status}">
<%@ include file="/k/kui-base-form.jsp"%>
<%
String type = request.getParameter("type");
String flowButtonName = "04".equals(type)?"审核":"签发";
%>
<script type="text/javascript" src="rtbox/app/templates/rtbox.js"></script>
<script type="text/javascript">
var onekey = false ;	//是否允许一键操作
var autoSub = false ;	//是否自动提交
var infon = 0;
var pageStatus="${param.status}"; 
var bigClassify="${param.bigClassify}";
var little_type = "${param.device_sort}";
var checkOk = {};
	var navtab;
	$(function () {
		
		var height = $(window).height();
		$('#contain').css({height:height-45});
		var bar =[];
		
			bar = [ { text : '报告<%=flowButtonName%>', icon : 'save', id:'save', click : doAudit } ,
			        { text : '关闭', icon : 'cancel', click : close }  ];
		
		
	  $("#formObj").initForm({    //参数设置示例
	       toolbar:bar,
	        getSuccess:function(res){
	        	
	        }
	        
	    });
	})

	
	
	//关闭
	function close(url)
	{	
		api.close();
	}
	//保存
	function save(url)
	{
		
		share.data("isBatch",true);
		//打开提交页面
		doSub();
	}
	
	//报告审核页面
	function doAudit(){
	
		var type = ${param.type};
		
		var titel="";
		
		if(type=="04"){
			titel="报告审核";
			
		}else if(type=="05"){
			titel="报告签发";
			
		}

		
		//$('#sssss').hide()
		$.post("report/query/getTime.do",{"id":"${param.ins_info_id}"},function(data){
				//取时间
				var enter_time = data["data"].enter_time==null?'':data["data"].enter_time;
				var advance_time = data["data"].advance_time==null?'':data["data"].advance_time;
				var confirm_date = data["data"].confirm_date==null?'':data["data"].confirm_date;
				var examine_date = data["data"].examine_date==null?'':data["data"].examine_date;

			top.$.dialog({
				width : 600, 
				height : 500, 
				lock : true, 
				title:titel,
				parent:api,
				content: 'url:app/flow/report/page/item_take_revise.jsp?acc_id=${param.acc_id}&flow_num=${param.flow_num}&'+
						'type=${param.type}&device_type=${param.device_type}&device_sort_code=${param.device_sort_code}'+
						'&check_flow=${param.check_flow}&id=${param.id}&org_id=${param.org_id}&'+
						'advance_time='+advance_time+'&check_time='+examine_date+'&enter_time='+enter_time,
				data : {"window" : window}
			});
		})
		
	}
	
	//禁用一键操作按钮
	function disableallBtn(){
		$("#save").attr("disabled",true);
		$("#all").attr("disabled",true);
		
	}
	function enableallBtn(){
		$("#save").attr("disabled",false);
		$("#all").attr("disabled",false);
		
	}
	
	//禁用操作按钮
	function disableBtn(){
		$("#save").attr("disabled",true);
	}
	//启用操作按钮
	function ableBtn(){
		$("#save").attr("disabled",false);
	}
	function showBB(){
		enableallBtn();
	}
	
	//报告校核页面
	function doSub(){
		disableBtn();
		var id="${param.id}";
		var infoId = "${param.id}";
		var flowNodeId = "${param.flowNodeId}";
		var activityId = "${param.activityId}";

		if(autoSub){

			$.post("report/query/getTime.do",{id:id},function(data){
				//取时间
				var enter_time = data["data"].enter_time;
				var inspect_date = data["data"].inspect_date;
				var confirm_time = data["data"].confirm_time;
				var audit_time = data["data"].audit_date;

				top.$.dialog({
					width : 600, 
					height : 400, 
					lock : true, 
					title: "报告"+"<%=flowButtonName%>",
					parent:api,
					content: 'url:'+api.data.popURL+'?enter_time='+enter_time
							+"&inspect_date="+inspect_date
							+"&confirm_time="+confirm_time
							+"&audit_time="+audit_time
							+"&infoId="+infoId
							+"&flowNodeId="+flowNodeId
							+"&activityId="+activityId+"&rt=true",
					data : {"window" : window, "infoId":infoId},
					close: function(data){
						/* if(checkOk[infon]==undefined){
							showBB();
						} */
						
					}
				});	
			});	
		} else {
			//alert(api.data.popURL+"--"+infoId)
			//不自动提交
			$.ajax({
	            url: api.data.popURL,
	            type: "POST",
	            datatype: "json",
	            data: {infoIds:infoId},
	            success: function (data, stats) {
	            	$("body").unmask();
	                if (data["success"]) {
	                	top.$.dialog.notice({content:"<%=flowButtonName%>"+"成功"});
	                	api.data.window.Qm.refreshGrid();
	                	api.close();
	                    	/* auditOk(infoId);
	                    	//禁用操作按钮
	                    	if(checkOk[infon]==undefined){
								showBB();
							} */
	                    	//api.data.window.Qm.refreshGrid();
	                   		//showBB();
	                }else {
	                	$("body").unmask();
	                	$.ligerDialog.error('提示：' + data.msg);
	                	if(checkOk[infon]==undefined){
							showBB();
						}
	                }
	            },
	            error: function (data,stats) {
	            	$("body").unmask();
	                $.ligerDialog.error('提示：' + data.msg);
	                if(checkOk[infon]==undefined){
						showBB();
					}
	            }
	        });
		}
	}

	
	function auditOk(){
		var img = "<img alt=' ' src='k/kui/images/icons/16/accept.png' border='0'>";
		$("#title"+infon).prepend(img);
		checkOk[infon]= true;
	}
	
</script>
</head>
<body>
	<form id="formObj" action="" getAction="">
		
	<%-- <%@include file="rtbox/app/templates/${param.code }/index.jsp?code=${param.code}&pageStatus=modify&status=detail&fk_report_id=${param.fk_report_id }&id=${param.id }" %>
	 --%>
	<iframe id="contain" src="${param.view }?code=${param.code}
	&pageStatus=detail&status=modify&fk_report_id=${param.fk_report_id }&id=${param.id }&singleAudit=1" style="border: 0px;width: 100%;"></iframe>
	
	</form>
</body>
	
</html>
