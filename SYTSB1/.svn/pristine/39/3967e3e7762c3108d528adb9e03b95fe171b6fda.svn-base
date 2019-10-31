<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>报告录入</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">



</script>
</head>
<body>


		<div style="height: 80px;padding-top: 30px;" align="center">
		<fieldset class="l-fieldset" style="background:#2e92ec; color:#fff;">
				<font size="5">待处理</font>
		</fieldset>
		</div>
		
		<fieldset class="l-fieldset">
					<legend class="l-legend" style="font-size:16px; padding:6px 20px;">
						<div>
							待处理列表
						</div>
					</legend>
		<table border="0" cellpadding="3" cellspacing="0" width="" style="font-size:18px; line-height:36px; margin:5px 20px;">
				
				
				<c:forEach items="${flowData}" var="item" > 
				
					<tr>
						<td style="background:url(k/kui/images/icon_vip.gif) no-repeat left center; padding-left:25px;"><a href="app/flow/report_enter_list.jsp?flow_num=${item[3]}&function=${item[4]}&flowId=${item[5]}"><font size="3" >${item[0]}(共:${item[2]}份)&nbsp;&nbsp;&nbsp;&nbsp;---${item[1]}</font></a></td>
						
					</tr>
				</c:forEach>	
					
				</table>
	</fieldset>
	
		
			

			
			

</body>
</html>