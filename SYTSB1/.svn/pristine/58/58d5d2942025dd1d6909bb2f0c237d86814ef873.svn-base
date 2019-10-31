<%@page import="com.scts.discipline.PhoneSysUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.StringUtil"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%

/* PhoneSysUtil.call("13548199448","600");  */
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>业务信息查询</title>
		<%@include file="/k/kui-base-form.jsp"%>
		<link rel="stylesheet" type="text/css" href="app/discipline/css/style.css" media="all" />
		<link rel="stylesheet" type="text/css" href="app/discipline/css/animate.css" media="all" />
		

		<script type="text/javascript">
		$(function(){
			$("#business_id").val(api.data.info_id);
			$("#unit").val(api.data.unit);
			$("#create_org_name").val(api.data.unit);
			$("#com_name").val(api.data.com_name);
			$("#inspect_op").html(api.data.inspect_op);
			$("#phone_number").val(api.data.tel);
			$("#maintain_name").val(api.data.maintain_name);
			
		})
		
		function call(){

			var data = getFormValues();
			$("#call").hide();
			//return;
			 $.ajax({
		            url: "disciplineCallAction/saveCall.do",
		            type: "POST",
		            datatype: "json",
		            contentType: "application/json; charset=utf-8",
		            data: $.ligerui.toJSON(data),
		            success: function (resp) {
		                if (resp["success"]) {
		                	$("#id").val(resp.data.id)
		                		top.$.dialog.notice({content:'呼叫成功！'});
		                	//$("#callOther").show();
		        			$("#shutdown").show();
		        			$(".call").show();
		                	 $.post("disciplinePlanAction/callFlag.do",
	            					 {id:api.data.info_id},function(res){
	            						 if (resp["success"]) {
	            		                	
	            		                }
	            					 });
		                } else {
		                	$("#call").show();
		                	$.ligerDialog.error(resp.msg);
		                }
		            },
		            error: function (resp) {
		                $.ligerDialog.error('呼叫失败！' + resp.msg);
		            }
		        });
		}
		
		function shutdown(){
			
			var data =getFormValues();
			$("#shutdown").hide();
			 $.post("disciplineCallAction/shutDownCall.do",
					 {id:$("#id").val()},function(res){
						 if (res["success"]) {
		                		top.$.dialog.notice({content:'挂断成功！'});
		                		$("#call").show();
		                		//$("#callOther").hide();
		            			$("#shutdown").hide();
		            			$(".call").hide();
		                		 $.post("disciplineCallAction/getJudgeGrade.do",
		            					 {id:$("#id").val()},function(res){
		            						 if (resp["success"]) {
		            		                	
		            		                }
		            					 });
		                		 api.data.window.Qm.refreshGrid();
		                } else {
		                	$("#shutdown").show();
		                	$.ligerDialog.error(resp.msg);
		                }
					 });
		}
		
		function sendMsg(){
			
			var content = $("#content").val();
			if(content==""){
				$.ligerDialog.warn('请填写短信发送内容！');
				return;
			}
			
			var data = getFormValues();
			
			 $.ajax({
		            url: "disciplineMsgAction/saveSendMsg.do",
		            type: "POST",
		            datatype: "json",
		            contentType: "application/json; charset=utf-8",
		            data: $.ligerui.toJSON(data),
		            success: function (resp) {
		                if (resp["success"]) {
		                	$("#id").val(resp.data.id)
		                		top.$.dialog.notice({content:'呼叫成功！'});
		                } else {
		                	$.ligerDialog.error(resp.msg);
		                }
		            },
		            error: function (resp) {
		                $.ligerDialog.error('呼叫失败！' + resp.msg);
		            }
		        });
		}
		
		function getFormValues(){
			var data = {};
			data["id"]=$("#id").val();
			data["com_name"]=$("#com_name").val();
			data["business_id"]=$("#business_id").val();
			data["com_name"]=$("#com_name").val();
			data["phone_number"]=$("#phone_number").val();
			data["content"]=$("#content").val();
			data["create_org_name"]=$("#unit").val();
			return data;
		}
		
		
		function callOther(){
			var number = prompt("请输入您想要转接的电话号码", ""); //将输入的内容赋给变量 name ，
		    //这里需要注意的是，prompt有两个参数，前面是提示的话，后面是当对话框出来后，在对话框里的默认值
		    if (number) //如果返回的有内容
		    {
		     // alert("欢迎您：" + number)
		   
		    var data = getFormValues();
		    data["other_phone"] = number;
		    data["call_type"] = "2";
		    $.ajax({
	            url: "disciplineCallAction/saveCallOther.do",
	            type: "POST",
	            datatype: "json",
	            contentType: "application/json; charset=utf-8",
	            data: $.ligerui.toJSON(data),
	            success: function (resp) {
	                if (resp["success"]) {
	                	$("#id").val(resp.data.id)
	                		top.$.dialog.notice({content:'呼叫成功！'});
	                	$("#callOther").hide();
	        			$("#shutdown").show();
	        			$(".call").show();
	                } else {
	                	$.ligerDialog.error(resp.msg);
	                	$("#call").show();
	                }
	            },
	            error: function (resp) {
	                $.ligerDialog.error('呼叫失败！' + resp.msg);
	            }
	        });
		    }
		}
		
		</script>
		
	</head>
	<body>
	
	<div id="tccontent" >
	
		 		<input type="hidden" id="id" name="id" value=""/>
		 		<input type="hidden" id="fk_com_id" name="fk_com_id" value=""/>
				<input type="hidden" id="business_id" name="business_id" value=""/>
				<input type="hidden" id="create_org_name" name="create_org_name" value=""/>
	<div id="light" class="white_content md-show " >

		<div class="tankuang">
			<div class="close"><!-- <a id="t-close-btn" class="iconfont icon-esc" href="javascript:void(0)" title="关闭">x</a> --></div>
			<!--中间主要内容区  start -->
			
		
			
				<div class="m-center-main">

					<!-- <div class="u_tit">单位回访</div> -->

					<div class="person"></div>


					<div class="item-list">

						<div class="item-pane" style="display: block;">
							 
							
							 <div class="forms">
							   <div class="bd-li">
									<span class="bd-laber"><span class="hook"></span><i>检验部门：</i></span>
									<div class="bd-li-in">
										<textarea type="text" class="s_ipt" id="unit" name="unit"   ltype="text" readonly="readonly"></textarea>

									</div>
								</div>
								<div class="bd-li">
									<span class="bd-laber"><span class="hook"></span><i>检验员：</i></span>
									<div class="bd-li-in">
										<div type="text" class="s_ipt" id="inspect_op" style="height: 50px;" name="inspect_op"   ltype="text" readonly="readonly"></div>

									</div>
								</div>
								<div class="bd-li">
									<span class="bd-laber"><span class="hook"></span><i>维保单位：</i></span>
									<div class="bd-li-in">
										<textarea type="text" class="s_ipt" id="com_name" name="com_name" readonly="readonly"></textarea>
									</div>
								</div>
							 	<div class="bd-li">
									<span class="bd-laber"><span class="hook"></span><i>报检单位：</i></span>
									<div class="bd-li-in">
										<textarea type="text" class="s_ipt" id="com_name" name="com_name" readonly="readonly"></textarea>
									</div>
								</div>
							 
							 
							 	<div class="bd-li">
									<span class="bd-laber"><span class="hook"></span><i>电话号码：</i></span>
									<div class="bd-li-in">
										<textarea type="text" class="s_ipt" id="phone_number" name="phone_number"   ltype="text" readonly="readonly" rows="2"></textarea>
									</div>
								</div>									
								<div class="bd-li">
									<span class="bd-laber"><span class="hook"></span><i>短信发送内容：</i></span>
									<div class="bd-li-in">
										<textarea name="content" style="width: 70%"
										validate="{required:false }" ltype='text' id="content" rows="4"></textarea>
									</div>
								</div>	

								
							 </div>
							
							
						</div>
						<div  class="call" style="display:none;">
	
							
							 <div class="call_time">


							 	<div class="tele_ico">
							 		<div class="img"><img src="app/discipline/images/tele.png" alt="" /></div>
							 		<div class="pulse"></div> 
	     							<div class="pulse1"></div>
							 	</div>

							 <!-- 	<div class="c_time">00:05</div> -->



								
							 	
							 </div>
							 
							
							
						</div>
					</div>

					<div class="f_btn" align="center">
					 	<a href="javascript:shutdown()" class="ico01" id="shutdown" style="display: none;">挂断</a> 
						<a href="javascript:call()" class="ico02" id="call">拨号</a>
						<!-- <a href="javascript:callOther()" class="ico04" id="callOther" style="display: none;">转接</a> -->
					 	<a href="javascript:sendMsg()" class="ico03">短信</a> </div>

					</div>
			
				</div>
	</div>

	<div id="fade" class="black_overlay" style="display:block;"></div>
	
</div>
	
	</body>
</html>
