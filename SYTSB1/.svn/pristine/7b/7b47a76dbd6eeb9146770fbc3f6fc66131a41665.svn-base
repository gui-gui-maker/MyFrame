<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %> 
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>

<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<!DOCTYPE html>
<html>
<head>
<title>微信审核</title>
<!-- 请休假id -->
<% String businessId=request.getParameter("businessId");
    if(businessId==null){
    	businessId=(String)session.getAttribute("businessId");
    }
    String intoType = request.getParameter("intoType");
%>
	<%
	CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
	User uu = (User)curUser.getSysUser();
	com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
	String userId=e.getId();//员工ID
	String uId = SecurityUtil.getSecurityUser().getId();//用户ID
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black"/>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/k/km/lib/app-end.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/k/km/lib/kh-mobile.css"/>
<script src="${pageContext.request.contextPath}/k/km/lib/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/k/km/lib/kh-mobile.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/pub/bpm/js/util.js"></script>
<link rel="stylesheet" href="k/jqm/skins/default.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/app/weixin/js/timejs/common.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/weixin/js/timejs/date.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/weixin/js/timejs/iscroll.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/weixin/js/timejs/mobiscroll.2.13.2.js"></script>
<link href="${pageContext.request.contextPath}/app/weixin/js/timejs/mobiscroll.2.13.2.css" rel="stylesheet" />
<script src="k/jqm/a-main.js" type="text/javascript"></script>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript" src="pub/worktask/js/worktask.js"></script>   
<script type="text/javascript">
//jQuery页面载入事件
var serviceId = "${requestScope.serviceId}";//提交数据的id
var activityId = "${requestScope.activityId}";//流程id
var processId = "${requestScope.processId}";//过程
var areaFlag;//改变状态
<bpm:ifPer function="TJY2_LEAVE_BMFZRQZ" activityId = "${requestScope.activityId}">areaFlag="2";</bpm:ifPer>//部门负责人签字
<bpm:ifPer function="TJY2_LEAVE_RSYJ" activityId = "${requestScope.activityId}">areaFlag="3";</bpm:ifPer>//人事意见
<bpm:ifPer function="TJY2_LEAVE_FGLDYJ" activityId = "${requestScope.activityId}">areaFlag="4";</bpm:ifPer>//分管领导意见
<bpm:ifPer function="TJY2_LEAVE_YZYJ" activityId = "${requestScope.activityId}">areaFlag="5";</bpm:ifPer>//院长意见
//公务外出
<bpm:ifPer function="GWWC_BZSH" activityId = "${requestScope.activityId}">areaFlag="2";</bpm:ifPer>//部长审核
<bpm:ifPer function="GWWC_FHLDSH" activityId = "${requestScope.activityId}">areaFlag="4";</bpm:ifPer>//分管领导审核
<bpm:ifPer function="GWWC_YZSH" activityId = "${requestScope.activityId}">areaFlag="5";</bpm:ifPer>//院长审核

var flowId='';
var param='';
var users={};
  $(function(){
	  initTable()
	  
	    var businessId="${data.id}";
	  //	getcodetabl("TJY2_RL_LEAVE_TYPE","leaveType");
	  	getsqltabl("${data.next_op_sql}","next_op_id");
		function getcodetabl(code,id){   
		   $.ajax({
			url : "${pageContext.request.contextPath}/WxLeaveAction/getcodetabl.do?tablname="+code,
			type : "POST",
			async : false,
			success : function(json) {		
				var	s="";
				if(json.success){
				for(var i in json.data) {	
				s+="<option value='"+json.data[i].value+"'>"+json.data[i].name+"</option>";				    				  				
				}
				$("#"+id).html(s);	
				
				}
			} 
		 });
		}
		
		function getsqltabl(sql,id){   
			   $.ajax({
				url : "${pageContext.request.contextPath}/msgLinkAuditAction/getSqltabl.do?sql="+sql,
				type : "POST",
				async : false,
				success : function(json) {		
					var	s="";
					if(json.success){
					for(var i in json.data) {	
						if(id=="next_op_id"){
							if(i==0){
								$("#next_op").val(json.data[0].name)
							}
							users[json.data[i].id]=json.data[i].name;
						}
						
					s+="<option value='"+json.data[i].id+"'>"+json.data[i].name+"</option>";				    				  				
					}
					$("#"+id).html(s);	
					
					}
				} 
			 });
			}
		if("<%=intoType%>"=="link"){
	    	$("#next3").hide();
	    }

		if("${data.next_op_sql}"==""){
			$("#nextOp").hide();
		}
		//$("#table2").hide();
		
		if(businessId!="null"&&"${data.status}"=="0"&&"${data.handle_id}"=="<%=curUser.getId()%>"){
			/* $("#next1").hide();
			$("#next2").hide();
			$("#formObj1").hide();
			$("#ztsq").show();
			$("#formObj").transform("detail"); */
		   
			$("#next1").show();
			$("#next2").show();
			$("#formObj1").show();
			//alert("${data.handle_id}----------------<%=curUser.getId()%>")
		}else{
			$("#next1").hide();
			$("#next2").hide();
			$("#table2").hide();
		}
		
		//审批通过
		$("#next1").click(function() {
			 $("#next1").hide();
			 $("#next2").hide();
			if("${data.next_op_sql}"!=""&&$("#next_op").val()==""){
				dAlert("请选择下一步操作人！");
			}
			var url = "${data.action}";
			$.post("${pageContext.request.contextPath}/"+url,
					{"conclusion":"1","next_op":$("#next_op").val(),"next_op_id":$("#next_op_id").val(),"remark":$("#remark").val()},
		        function (res) {
		        	//location.href="${pageContext.request.contextPath}/app/humanresources/wx_leave/leave_index.jsp";
		        	if(res.success){
		        		$.post("${pageContext.request.contextPath}/msgLinkAuditAction/setAuditStatus.do?id=${data.id}",{},function (data) {
				        		 dAlert("审核成功！");
					        }); 
		        	}
			        	
		        });
			/*
			$("#next2").hide();
			$("#next3").hide();
			var opinion=encodeURI(encodeURI($("#opinion").val()));
				$.ajax({
					url : "${pageContext.request.contextPath}/WxLeaveAction/subPassGwwc.do?opinion="+opinion+"&businessId="+businessId,
					type : "post",
					datatype : "json",
					contentType: "application/json; charset=utf-8",
			        success : function (data) {
			        	location.href="${pageContext.request.contextPath}/app/humanresources/wx_leave/leave_index.jsp";
		        		 dAlert("审核成功！");
			        }
				}); */
		});
		//审批不通过
		$("#next2").click(function() {
			 $("#next1").hide();
			 $("#next2").hide();
			if("${data.next_op_sql}"!=""&&$("#next_op").val()==""){
				dAlert("请选择下一步操作人！");
			}
			var url = "${data.action}";
			$.post("${pageContext.request.contextPath}/"+url,
					{"conclusion":"0","remark":$("#remark").val()},
		        function (res) {
		        	//location.href="${pageContext.request.contextPath}/app/humanresources/wx_leave/leave_index.jsp";
		        	if(res.success){
		        		$.post("${pageContext.request.contextPath}/msgLinkAuditAction/setAuditStatus.do?id=${data.id}",{},function (data) {
				        		 dAlert("审核成功！");
					        }); 
		        	}
			        	
		        });
		}); 
		//返回
		$("#next3").click(function() {
			location.href="${pageContext.request.contextPath}/app/humanresources/wx_leave/leave_pending.jsp?id=<%=userId%>";
		});
            var curr = new Date().getFullYear();
            var opt={};
			opt.date = {preset : 'date'};
			opt.datetime = {preset : 'datetime'};
			opt.time = {preset : 'time'};

          opt.default = {
				theme: 'android-holo light', //皮肤样式
		        display: 'modal', //显示方式 
		        mode: 'scroller', //日期选择模式
				dateFormat: 'yyyy-mm-dd',
				lang: 'zh',
				showNow: true,
				nowText: "今天",
				stepMinute: 5,
		        startYear: curr - 0, //开始年份
		        endYear: curr + 20 //结束年份
			};
            $('.settings').bind('change', function() {
                var demo = 'datetime';
                if (!demo.match(/select/i)) {
                    $('.demo-test-' + demo).val('');
                }
                $('.demo-test-' + demo).scroller('destroy').scroller($.extend(opt['date'], opt['default']));
                $('.demo').hide();
                $('.demo-' + demo).show();
            });
            $('#demo').trigger('change');

	
  })
  
  function initTable(){
	  var data = ${datas}
	 /*  var data = {};
	  data["申请人"]='测试';
	  data["申请时间"]='2018-03-12';
	  data["申请缘由"]='我加班啦'; */
	 $("#table1").html("");
	 for ( var key in data) {
		 $("#table1").append(
			 '<tr class="tr1">'+
			'<td class="tdtext1" style="color: #003D79;" width="30%" align="center">'+key+':</td>'+
				'<td>'
					+data[key]+
				'</td>'+
			'</tr>'
			 )
	}
  }
  
  function changeOp(id){
	 $("#next_op").val( users[id])
  }
  
</script>
<style>
.wrapper{ margin-top:28%;}
.tr1{ font-size: 18px;}
</style>
</head>
<body>
	<div  id="a-index" class="a-index">
	<div class="header">
		<div class="bg"></div>
		<div class="logo"></div>
		<div class="user">
			<h1>四川省特检院</h1>
			<h2 id="headTitle">${data.title }</h2>
		</div>
	</div>
	</div>
	<div class="settings" style="display:none;">
          <select name="demo" id="demo">
              <option value="date">日期</option>
          </select>
        </div>
	<div id="wrapper" class="wrapper">
			<section id="reg1" class="">	
				<div class="container">		
<!-- 				<div class="page-header" align="center"> -->
<!-- 				</div> -->
				<div class="page-panel" id="m-page-panel"  width="100%" >
					<form id="formObj" name="formObj" method="post" action="" onsubmit="return false;" getAction="" pageStatus="">
						<input id="id" name="id" type="hidden" value="">
						<table border="0" cellpadding="0" cellspacing="0" width="100%" height="" align="" id='table1'>
							
				  		</table>
				  		<table border="0" cellpadding="0" cellspacing="0" width="100%" height="" align="" id='table2'>
							
							<tr  class="tr1">
							    	<td class="tdtext1" style="color: #003D79;" width="30%" align="center">审核意见：</td>
							    	<td>
							    		<textarea   type="text" id="remark" name="remark" rows="2" style="width: 100%;"></textarea>
							    		
							    	</td>
						    </tr>
						    <tr class="tr1" id="nextOp">
								<td class="tdtext1" style="color: #003D79;" width="30%" align="center">下一步审核人：</td>
						    	<td>
						    		<input type="hidden"  id="next_op" name="next_op"/>
						    		<select class="form-control" id="next_op_id" name="next_op_id" ext_isNull="0" onchange="changeOp(this.value)"></select>
						    		
						    	</td>
							</tr>
				  		</table>
					</form>
					
					<div id="datePlugin"></div>
				</div>
				<div class="bt1">
						<div class="text-center row">
							<div class=""><a id="next1" class="button button-block button-rounded button-primary">同意</a></div>
							<div >&nbsp;&nbsp;&nbsp;</div>
							<div class=""><a id="next2" class="button button-block button-rounded button-primary">不同意</a></div>
							<!-- <div class="">&nbsp;&nbsp;&nbsp;</div>
							<div class=""><a id="next3" class="button button-block button-rounded button-primary">返回</a></div> -->
						</div>
					</div>
				</div>
			</section>
	</div>
</body>
</html>

		