<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	CurrentSessionUser user = SecurityUtil.getSecurityUser();
	String orgId = user.getUnit().getId();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	/* function two(){
		document.getElementById("two1").src = "app/oa/car/car_type_two.jsp?checkbox=${param.checkbox}&status=${param.status}";
	} */
	$(function (){
          $("#tabele6").ligerTab({ onBeforeSelectTabItem: function (tabId){
	        	  if(tabId=='certTab0'){
	        		  document.getElementById("one1").src = "app/oa/car/car_type_one.jsp?checkbox=${param.checkbox}&status=${param.status}&op_type=${param.op_type}";
	        	  }else if(tabId=='certTab1'){
	        		  document.getElementById("two1").src = "app/oa/car/car_type_two.jsp?checkbox=${param.checkbox}&status=${param.status}&op_type=${param.op_type}";
	        	  }else if(tabId=='certTab2'){
	        		  document.getElementById("three1").src = "app/oa/car/car_type_three.jsp?checkbox=${param.checkbox}&status=${param.status}&op_type=${param.op_type}";
	        	  }else if(tabId=='certTab3'){
	        		  document.getElementById("four1").src = "app/oa/car/car_type_four.jsp?checkbox=${param.checkbox}&status=${param.status}&op_type=${param.op_type}";
	        	  }else if(tabId=='certTab4'){
	        		  document.getElementById("four1").src = "app/oa/car/car_type_five.jsp?status=${param.status}&op_type=${param.op_type}";
	        	  }
	          }, onAfterSelectTabItem: function (tabId){
	        	  if(tabId=='certTab0'){
	        		  document.getElementById("one1").src = "app/oa/car/car_type_one.jsp?checkbox=${param.checkbox}&status=${param.status}&op_type=${param.op_type}";
	        	  }else if(tabId=='certTab1'){
	        		  document.getElementById("two1").src = "app/oa/car/car_type_two.jsp?checkbox=${param.checkbox}&status=${param.status}&op_type=${param.op_type}";
	        	  }else if(tabId=='certTab2'){
	        		  document.getElementById("three1").src = "app/oa/car/car_type_three.jsp?checkbox=${param.checkbox}&status=${param.status}&op_type=${param.op_type}";
	        	  }else if(tabId=='certTab3'){
	        		  document.getElementById("four1").src = "app/oa/car/car_type_four.jsp?checkbox=${param.checkbox}&status=${param.status}&op_type=${param.op_type}";
	        	  }else if(tabId=='certTab4'){
	        		  document.getElementById("five1").src = "app/oa/car/car_type_five.jsp?status=${param.status}&op_type=${param.op_type}";
	        	  }
	          } 
          });
      });
</script>
</head>
<body>

<div class="navtab" style="width: 100%;height: 100%" id="tabele6" >
	<div title="一类车辆" id="one" lselected="true" style="width: 100%;height: 100%"  tabId="certTab0"  ligeruiid="tab0">
		<iframe name="oneIfr" id="one1" src="app/oa/car/car_type_one.jsp?checkbox=${param.checkbox}&status=${param.status}&op_type=${param.op_type}" width="100%" height="100%"></iframe>
	</div>
	<div class="div1" title="二类车辆" id="two"  tabId="certTab1">
		<iframe frameborder="1" name="twoifr" id="two1" src=""  ></iframe>
	</div>
	<div title="三类车辆" id="three" tabId="certTab2">
		<iframe  frameborder="1"  name="threeifr" id="three1" src="" ></iframe>
	</div>
	<div title="其他类车辆" id="four" tabId="certTab3">
		<iframe name="fourifr" id="four1" src="" ></iframe>
	</div>
	<c:if test="${param.checkbox=='0'}">
		<div title="维修中" id="five" tabId="certTab4">
			<iframe name="fourifr" id="five1" src="" ></iframe>
		</div>
	</c:if>
</div>
				
</body>
</html>