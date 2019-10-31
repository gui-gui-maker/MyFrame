<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html>
<html>
<head>
<%@page import="java.text.SimpleDateFormat"%>
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<script type="text/javascript" src="${pageContext.request.contextPath}/app/k/km/lib/app-end.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/app/k/km/lib/kh-mobile.css" />
<script src="${pageContext.request.contextPath}/app/k/km/lib/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/app/k/km/lib/kh-mobile.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/bpm/js/util.js"></script>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black"/>
<link rel="stylesheet" href="app/k/jqm/skins/default.css"/>
<script src="app/k/jqm/a-main.js" type="text/javascript"></script>
<!-- <script type="text/javascript" charset="utf-8" src="k/jqm/jquery2.js"></script>
<script type="text/javascript" charset="utf-8" src="k/jqm/jquery.mobile.js"></script> -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/app/weixin/js/timejs/common.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/weixin/js/timejs/date.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/app/weixin/js/timejs/iscroll.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/app/weixin/js/timejs/mobiscroll.2.13.2.js"></script>
    <link href="${pageContext.request.contextPath}/app/weixin/js/timejs/mobiscroll.2.13.2.css" rel="stylesheet" />
<script type="text/javascript">
<% String id=request.getParameter("id");
    if(id==null){
    	 id=(String)session.getAttribute("id"); 
    	
    }
%>
<%-- <% String phone=request.getParameter("phone");
if(phone==null){
	phone=(String)session.getAttribute("phone"); 
	
}
%> --%>
	var peopleNumber;
	$(function() {
		var id = '<%=id%>';//员工ID
		//alert(id);
		<%-- var phone = '<%=phone%>';//员工ID --%>
		/* id="402883a051aa5c690151ab61c44d0d3e"; */
		if(id!="null"){
			$.ajax({
				url : "${pageContext.request.contextPath}/WxLeaveAction/loadUser.do?id="+id,
				type : "POST",
				async : false,
				success : function(resp) {		
					$("#sqDepartment").val(resp.workDepartmentName);
					$("#compere").val(resp.workDepartmentName);
				} 
			 });
		}else{
			alert("对不起！你的微信号未与特检平台同步！");
		}
		//会议室名称
		getcodetabl("meeting_room_type","fkOaMeetingRoom"); 
		//会议室布置形式
		getcodetabl("meeting_layout_type","hysbzxs");
		//是否制作会标
		getcodetabl("wx_meeting_IforNot","ifZzhb");
		//是否制作欢迎标语
		getcodetabl("wx_meeting_IforNot","ifHyby");
		//是否制作座牌
		getcodetabl("wx_meeting_IforNot", "ifZzhyzp");
		//是否制作就餐座牌
		getcodetabl("wx_meeting_IforNot", "ifZzjczp");
		//是否摆鲜花
		getcodetabl("wx_meeting_IforNot", "ifFlowers");
		//是否要水果
		getcodetabl("wx_meeting_IforNot", "ifFruits");
		document.getElementById("ifZzhb").value = "0";
		document.getElementById("ifHyby").value = "0";
		document.getElementById("ifZzhyzp").value = "1";
		document.getElementById("ifZzjczp").value = "0";
		document.getElementById("ifFlowers").value = "0";
		document.getElementById("ifFruits").value = "0";
		 $("#form1").initForm({
			 getSuccess: function (res){
					if(res.data==null||res.success=="false"){
					}
					
			}
    	});	
		 getRoomInfo();
		 $("#fkOaMeetingRoom").change(function () {
			 getRoomInfo();
			  var startDate=$("#startTime").val();
			  var endDate=$("#endTime").val();
			  if(startDate!=null&&endDate!=null&&startDate!=""&&endDate!=""){
				  countLeave(startDate,endDate);
			  }else{
				  dAlert("会议时间不能为空！");
			  }
		 });
		 /* $('#pnumber').change(function() {
			 alert(peopleNumber);
			 if($('#pnumber').val()>peopleNumber){
				 dAlert("参会人数大于了最大人数"+peopleNumber+"!");
			 }
		 }) */
		//提交表单
		$("#next1").click(function() {
			
			//申请部门
			var sqDepartment=encodeURI(encodeURI($("#sqDepartment").val()));
			//开始时间
			var startTime=$("#startTime").val();      
			//结束时间
			var endTime=$("#endTime").val();
			//会议名称
			var name=encodeURI(encodeURI($("#name").val()));
			//会议室名称
			var fkOaMeetingRoom=encodeURI(encodeURI($("#fkOaMeetingRoom").val()));
			//参会人数           
			var pnumber=$("#pnumber").val();	
			//会议地点
			var hyAdress=encodeURI(encodeURI($("#hyAdress").val()));	
			//会议室布置形式
			var hysbzxs=$("#hysbzxs").val();
			//是否制作会标
			var ifZzhb=$("#ifZzhb").val();
			//是否制作欢迎标语
			var ifHyby=$("#ifHyby").val();
			//是否制作座牌
			var ifZzhyzp=$("#ifZzhyzp").val();
			//是否制作就餐座牌
			var ifZzjczp=$("#ifZzjczp").val();
			//是否摆鲜花
			var ifFlowers=$("#ifFlowers").val();
			//是否要水果
			var ifFruits=$("#ifFruits").val();
			//办公室配合的其他事项
			var officeOtherPz=encodeURI(encodeURI($("#officeOtherPz").val()));	
			//备注
			var remarks=encodeURI(encodeURI($("#remarks").val()));	
			//承办人
			var compere=encodeURI(encodeURI($("#compere").val()));	
			//ROOMCODE
			var roomCode = encodeURI(encodeURI($("#fkOaMeetingRoom").text()));
			var param=""+"id="+id+"&sqDepartment="+sqDepartment+"&startTime="+startTime+"&endTime="+endTime
			+"&name="+name+"&roomCode="+roomCode+"&fkOaMeetingRoom="+fkOaMeetingRoom+"&pnumber="+pnumber+"&hyAdress="+hyAdress
			+"&hysbzxs="+hysbzxs+"&ifZzhb="+ifZzhb+"&ifHyby="+ifHyby+"&ifZzhyzp="+ifZzhyzp+"&ifZzjczp="+ifZzjczp
			+"&ifFlowers="+ifFlowers+"&ifFruits="+ifFruits+"&officeOtherPz="+officeOtherPz+"&remarks="+remarks+"&compere="+compere;
			//alert(param);
			$.ajax({
				url : "${pageContext.request.contextPath}/oa/meetingReq/info/mobileSubmit.do?"+param,
				type : "post",
				datatype : "json",
				contentType: "application/json; charset=utf-8",
		        success : function (data) {
		        	if(data.success){  
		        		//alert("成功啦");
		        		id=data.data;
		        		 dAlert("提交成功！");			
		 				 $("#form1").transform("detail"); 
		 				 $("#next1").hide();
		 		         $("#form1").setValues("${pageContext.request.contextPath}/oa/meetingReq/info/detail.do?id="+id,function(res){
		 		      });     
		        	}else{
		        		//$("body").unmask();
		        		dialogShow(data.msg, 300, 100);
		        	}
		        }
			});		
		
		});
// 		$('#startTime').date({theme:"datetime"});
// 		$('#endTime').date({theme:"datetime"});
		
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
            $('.demo-test-' + demo).scroller('destroy').scroller($.extend(opt['datetime'], opt['default']));
            $('.demo').hide();
            $('.demo-' + demo).show();
        });
        $('#demo').trigger('change');
	});

	  function setleaveCount1(){
		  var startDate=$("#startTime").val();
		  var endDate=$("#endTime").val();
		  if(startDate!=null&&endDate!=null&&startDate!=""&&endDate!=""){
			  countLeave(startDate,endDate);
		  }
	  }
	  function countLeave(startDate,endDate){
		  startDate = new Date(startDate.replace(/-/g, "/"));
		  endDate = new Date(endDate.replace(/-/g, "/"));
		  var time = endDate.getTime() - startDate.getTime();
		  var fkOaMeetingRoom = $("#fkOaMeetingRoom").val();
	  		if(time>=0){
	  			if(fkOaMeetingRoom!=""){
	  				var startDate1=$("#startTime").val()+":00";
	  	  		    var endDate1=$("#endTime").val()+":00";
	  	  			$.ajax({
	  	   	        	url: "oa/meetingReq/info/countReq.do?startTime="+startDate1+"&endTime="+endDate1+"&roomId="+$("#fkOaMeetingRoom").val(),
	  	   	            /* url: "oa/meetingReq/info/countReq.do?startTime="+startDate1+"&endTime="+endDate1, */
	  	   	        	type: "POST",
	  	   	            datatype: "json",
	  	   	            contentType: "application/json; charset=utf-8",
	  	   	            success: function (resp) {
	  	   	            	if(resp.success){
	  	   	            	}else{
	  	   	            		dAlert(resp.msg);
	  	   	            	}
	  	   	            	
	  	   	            },
	  	   	            error: function (data) {
	  	   	            	dAlert("后台查询失败！");
	  	   	            }
	  	   	        });
	  			}
			}else{
				dAlert("会议开始时间不能大于结束时间！");
			}
	}
	
	
	function getcodetabl(code,id) {
		$.ajax({
			url : "${pageContext.request.contextPath}/employeeBaseAction/getcodetabl.do?tablname=" + code,
			type : "POST",
			async : false,
			success : function(json) {
				var s = "";
				if (json.success) {
					for ( var i in json.data) {
						s += "<option value='"+json.data[i].value+"'>"
								+ json.data[i].name + "</option>";
					}
					$("#" + id).html(s);

				}
			}
		});
	}
	function getRoomInfo(){
		 if($("#fkOaMeetingRoom").val()!=""){
			 //alert($("#fkOaMeetingRoom").val());
			 $.ajax({
					url : "${pageContext.request.contextPath}/oa/meetingRoom/info/getRoomInfo.do?id="+$("#fkOaMeetingRoom").val(),
					type : "post",
					datatype : "json",
					contentType: "application/json; charset=utf-8",
			        success : function (data) {
			        	if(data.success){  
			        		 var meetingRoomInfo = data.meetingRoomInfo;
			        		 $("#hyAdress").val(meetingRoomInfo.place); 
			        		 peopleNumber = meetingRoomInfo.accommodate_no;
			        	}else{
			        		dAlert(data.msg);
			        	}
			        }
				});	
		 }
	 }
</script>
<style>
.wrapper{ margin-top:28%;}

</style>
</head>
<body>
<div  id="a-index" class="a-index">
	<div class="header">
		<div class="bg"></div>
		<div class="logo"></div>
		<div class="user">
			<h1>四川省特检院</h1>
			<h2>办公助手-会议室申请</h2>
		</div>
	</div>
	</div>
	<div class="settings" style="display:none;">
          <select name="demo" id="demo">
              <option value="date">日期</option>
          </select>
        </div>
	<div >
		<div id="wrapper" class="wrapper">

	<div class="container">
		<div class="page-panel" id="m-page-panel">
			<form id="form1" name="form1" method="post" action="" onsubmit="return false;" getAction="">
				<table border="0" cellpadding="0" cellspacing="0" width="100%" height="" align="">
						<tr class="tr1">
							<td width="100" class="tdtext1">申请部门：</td>
							<td><input type="text" class="form-control" name="sqDepartment"  id="sqDepartment" readonly="readonly" ext_name="申请部门" ext_isNull="1" ></td>
						</tr>				
				        <tr class="tr1">
							<td class="tdtext1">会议开始时间：</td>
							<td><input type="text" class="form-control demo-test-date demo-test-datetime demo-test-time demo-test-credit" id="startTime" name="startTime" placeholder="yyyy-MM-dd hh:mm:ss" ext_name="会议开始时间" maxLength="100" ></td>
						</tr>
						<tr class="tr1">
							<td class="tdtext1">会议结束时间：</td>
							<td><input type="text"  class="form-control demo-test-date demo-test-datetime demo-test-time demo-test-credit" id="endTime" name="endTime" placeholder="yyyy-MM-dd hh:mm:ss" ext_name="会议结束时间" maxLength="100" ></td>
						</tr>
			            <tr class="tr1">
							<td class="tdtext1">会议名称：</td>
							<td><input class="form-control"  name="name"  id="name" placeholder="请输入会议名称"  ext_name="会议名称"  ></td>
						</tr>
			             <tr class="tr1">
							<td class="tdtext1">会议室名称：</td>
							<td><select  class="form-control" id="fkOaMeetingRoom" name="fkOaMeetingRoom"  ext_name="会议室名称" ></select></td>
						</tr>
						<tr class="tr1">
							<td class="tdtext1">参 会 人 数：</td>
							<td><input type="text" class="form-control" id="pnumber" name="pnumber"   placeholder="请输入整数" ext_name="参会人数" ext_isNull="1" maxLength="11"></td>
						</tr>
						<tr class="tr1">
							<td class="tdtext1">会 议 地 点：</td>
							<td><input type="text" class="form-control" id="hyAdress" name="hyAdress" ext_isNull="1"></td>
						</tr>
						<tr class="tr1">
							<td class="tdtext1">会议室布置形式：</td>
					    	<td><select class="form-control" id="hysbzxs" name="hysbzxs"></select></td>
						</tr>
						<tr class="tr1">
							<td class="tdtext1">是否制作会标：</td>
					    	<td><select class="form-control" id="ifZzhb" name="ifZzhb"  ext_name="是否需要制作座牌" ></select></td>
						</tr>
						<tr class="tr1">
							<td class="tdtext1">是否制作欢迎标语：</td>
					    	<td><select class="form-control" id="ifHyby" name="ifHyby"  ext_name="是否制作欢迎标语" ></select></td>
						</tr>
						<tr class="tr1">
							<td class="tdtext1">是否需要制作座牌：</td>
					    	<td><select class="form-control" id="ifZzhyzp" name="ifZzhyzp"  ext_name="是否需要制作座牌" ></select></td>
						</tr>
						<tr class="tr1">
							<td class="tdtext1">是否制作就餐座牌：</td>
					    	<td><select class="form-control" id="ifZzjczp" name="ifZzjczp"  ext_name="是否制作就餐座牌" ></select></td>
						</tr>
						<tr class="tr1">
							<td class="tdtext1">是否摆鲜花：</td>
					    	<td><select class="form-control" id="ifFlowers" name="ifFlowers"  ext_name="是否摆鲜花" ></select></td>
						</tr>
						<tr class="tr1">
							<td class="tdtext1">是否要水果：</td>
					    	<td><select class="form-control" id="ifFruits" name="ifFruits"  ext_name="是否要水果" ></select></td>
						</tr>
						<tr class="tr1">
							<td class="tdtext1">需要办公室配合的其它事项：</td>
					    	<td><input class="form-control" id="officeOtherPz" name="officeOtherPz"></select></td>
						</tr>
						<tr class="tr1">
							<td class="tdtext1">备注：</td>
					    	<td><input class="form-control" id="remarks" name="remarks"></td>
						</tr>
			            <tr class="tr1">
							<td class="tdtext1">承办人：</td>
							<td>
							 <input class="form-control" id="compere"  name="compere"   ext_name="承办人" ext_isNull="1"></td>
						</tr>
			  </table>
			</form>
		<div id="datePlugin"></div>
		</div>
		<div class="bt1" >
				<div class="text-center row">
					<div class=""><a id="next1" class="button button-block button-rounded button-primary">提交</a></div>
				</div>
			</div>
		</div>
	</div>
	</div>
</body>
</html>
