<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@ include file="/k/kui-base-form.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/k/kui-base-form.jsp" %>
<script type="text/javascript">
	$(function () {
		$("#formObj").initForm({    //参数设置示例
			toolbar : null,
			success: function (response) {
				if(response.success){
					top.$.notice("保存成功！",3);
					api.data.window.Qm.refreshGrid();
					api.close();
				}else{
					$.ligerDialog.error("操作失败！<br/>" + response.msg);
				}
			}
		});
	});
</script>
</head>
<body>
<div class="navtab">
	<div title="车辆类别" lselected="true">
		<form id="formObj" action="">
			<fieldset class="l-fieldset">
					<legend class="l-legend">
						<div>一类车辆</div>
					</legend>
					<table width="100%" border="1" bordercolor="#99CCFF" cellspacing="0" cellpadding="1">
						<tr style="height: 30px;" bgcolor="#CCFFFF">
							<td  align=center width="5%" >车辆品牌</td>
							<td  align=center width="5%" >车牌号</td>
							<td  align=center width="5%">车型</td>
							<td  align=center width="10%">发动机号</td>
							<td  align=center width="10%">车架号码</td>
							<td  align=center width="5%">排量(L)</td>
							<td  align=center width="5%">核载人数(人)</td>
							<td  align=center width="10%">产地</td>
							<td  align=center width="5%">颜色</td>
							<td  align=center width="10%">购车日期</td>
							<td  align=center width="5%">所属部门</td>
						</tr>
						<c:if test="${list1=='[]'}">
							<tr align="center" style="height: 26px;">
								<td  align=center colspan="11" >没有数据！</td>
							</tr>
						</c:if>
						<c:if test="${list1!='[]'}">
							<c:forEach items="${list1}" var="list1">
									<tr style="height: 26px;">
										<td align=left height="15px;"><c:out value="${list1.carBrand}"></c:out></td>
										<td align=center height="15px;"><c:out value="${list1.carNum}"></c:out></td>
										<td align=left height="15px;"><c:out value="${list1.carType}"></c:out></td>
										<td align=center height="15px;"><c:out value="${list1.engineNo}"></c:out></td>
										<td align=center height="15px;"><c:out value="${list1.frameNo}"></c:out></td>
										<td align=left height="15px;"><c:out value="${list1.carDisplacement}"></c:out>(L)</td>
										<td align=left height="15px;"><c:out value="${list1.loadNumber}"></c:out></td>
										<td align=left height="15px;"><c:out value="${list1.address}"></c:out></td>
										<td align=left height="15px;"><c:out value="${list1.color}"></c:out></td>
										<td align=center height="15px;"><c:out value="${list1.buyDate}"></c:out></td>
										<td align=left height="15px;"><c:out value="${list1.managerRoomName}"></c:out></td>
									</tr>
							</c:forEach>
						</c:if>
					</table>
			</fieldset>
		</form>
	</div>
	
	<div title="二类车辆" >
		<fieldset class="l-fieldset">
				<legend class="l-legend">
					<div>二类车辆</div>
				</legend>
				<table width="100%" border="1" bordercolor="#99CCFF" cellspacing="0" cellpadding="1">
					<tr style="height: 30px;" bgcolor="#CCFFFF">
						<td  align=center width="5%" >车辆品牌</td>
						<td  align=center width="5%" >车牌号</td>
						<td  align=center width="5%">车型</td>
						<td  align=center width="10%">发动机号</td>
						<td  align=center width="10%">车架号码</td>
						<td  align=center width="5%">排量(L)</td>
						<td  align=center width="5%">核载人数(人)</td>
						<td  align=center width="10%">产地</td>
						<td  align=center width="5%">颜色</td>
						<td  align=center width="10%">购车日期</td>
						<td  align=center width="5%">所属部门</td>
					</tr>
					<c:if test="${list2=='[]'}">
						<tr align="center" style="height: 26px;">
							<td  align=center colspan="11" >没有数据！</td>
						</tr>
					</c:if>
					<c:if test="${list2!='[]'}">	
						<c:forEach items="${list2}" var="list2">
							<tr style="height: 26px;">
								<td align=left height="15px;"><c:out value="${list2.carBrand}"></c:out></td>
								<td align=center height="15px;"><c:out value="${list2.carNum}"></c:out></td>
								<td align=left height="15px;"><c:out value="${list2.carType}"></c:out></td>
								<td align=center height="15px;"><c:out value="${list2.engineNo}"></c:out></td>
								<td align=center height="15px;"><c:out value="${list2.frameNo}"></c:out></td>
								<td align=left height="15px;"><c:out value="${list2.carDisplacement}"></c:out>(L)</td>
								<td align=left height="15px;"><c:out value="${list2.loadNumber}"></c:out></td>
								<td align=left height="15px;"><c:out value="${list2.address}"></c:out></td>
								<td align=left height="15px;"><c:out value="${list2.color}"></c:out></td>
								<td align=center height="15px;"><c:out value="${list2.buyDate}"></c:out></td>
								<td align=left height="15px;"><c:out value="${list2.managerRoomName}"></c:out></td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
		</fieldset>
	</div>
	<div title="三类车辆" >
		<fieldset class="l-fieldset">
				<legend class="l-legend">
					<div>三类车辆</div>
				</legend>
				<table width="100%" border="1" bordercolor="#99CCFF" cellspacing="0" cellpadding="1">
					<tr style="height: 30px;" bgcolor="#CCFFFF">
						<td  align=center width="5%" >车辆品牌</td>
						<td  align=center width="5%" >车牌号</td>
						<td  align=center width="5%">车型</td>
						<td  align=center width="10%">发动机号</td>
						<td  align=center width="10%">车架号码</td>
						<td  align=center width="5%">排量(L)</td>
						<td  align=center width="5%">核载人数(人)</td>
						<td  align=center width="10%">产地</td>
						<td  align=center width="5%">颜色</td>
						<td  align=center width="10%">购车日期</td>
						<td  align=center width="5%">所属部门</td>
					</tr>
					<c:if test="${list3=='[]'}">
						<tr align="center" style="height: 26px;">
							<td  align=center colspan="11" >没有数据！</td>
						</tr>
					</c:if>
					<c:if test="${list3!='[]'}">
						<c:forEach items="${list3}" var="list3">
							<tr style="height: 26px;">
								<td align=left height="15px;"><c:out value="${list3.carBrand}"></c:out></td>
								<td align=center height="15px;"><c:out value="${list3.carNum}"></c:out></td>
								<td align=left height="15px;"><c:out value="${list3.carType}"></c:out></td>
								<td align=center height="15px;"><c:out value="${list3.engineNo}"></c:out></td>
								<td align=center height="15px;"><c:out value="${list3.frameNo}"></c:out></td>
								<td align=left height="15px;"><c:out value="${list3.carDisplacement}"></c:out>(L)</td>
								<td align=left height="15px;"><c:out value="${list3.loadNumber}"></c:out></td>
								<td align=left height="15px;"><c:out value="${list3.address}"></c:out></td>
								<td align=left height="15px;"><c:out value="${list3.color}"></c:out></td>
								<td align=center height="15px;"><c:out value="${list3.buyDate}"></c:out></td>
								<td align=left height="15px;"><c:out value="${list3.managerRoomName}"></c:out></td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
		</fieldset>
	</div>
	<div title="其他类车辆" >
		<fieldset class="l-fieldset">
					<legend class="l-legend">
						<div>其他类车辆</div>
					</legend>
					<table width="100%" border="1" bordercolor="#99CCFF" cellspacing="0" cellpadding="1">
						<tr style="height: 30px;" bgcolor="#CCFFFF">
							<td  align=center width="5%" >车辆品牌</td>
							<td  align=center width="5%" >车牌号</td>
							<td  align=center width="5%">车型</td>
							<td  align=center width="10%">发动机号</td>
							<td  align=center width="10%">车架号码</td>
							<td  align=center width="5%">排量(L)</td>
							<td  align=center width="5%">核载人数(人)</td>
							<td  align=center width="10%">产地</td>
							<td  align=center width="5%">颜色</td>
							<td  align=center width="10%">购车日期</td>
							<td  align=center width="5%">所属部门</td>
						</tr>
						<c:if test="${list4=='[]'}">
							<tr align="center" style="height: 26px;">
								<td  align=center colspan="11" >没有数据！</td>
							</tr>
						</c:if>
						<c:if test="${list4!='[]'}">
							<c:forEach items="${list4}" var="list4">
								<tr style="height: 26px;">
									<td align=left height="15px;"><c:out value="${list4.carBrand}"></c:out></td>
									<td align=center height="15px;"><c:out value="${list4.carNum}"></c:out></td>
									<td align=left height="15px;"><c:out value="${list4.carType}"></c:out></td>
									<td align=center height="15px;"><c:out value="${list4.engineNo}"></c:out></td>
									<td align=center height="15px;"><c:out value="${list4.frameNo}"></c:out></td>
									<td align=left height="15px;"><c:out value="${list4.carDisplacement}"></c:out>(L)</td>
									<td align=left height="15px;"><c:out value="${list4.loadNumber}"></c:out></td>
									<td align=left height="15px;"><c:out value="${list4.address}"></c:out></td>
									<td align=left height="15px;"><c:out value="${list4.color}"></c:out></td>
									<td align=center height="15px;"><c:out value="${list4.buyDate}"></c:out></td>
									<td align=left height="15px;"><c:out value="${list4.managerRoomName}"></c:out></td>
								</tr>
							</c:forEach>
						</c:if>
					</table>
			</fieldset>
	</div>
	<div title="维修车辆" >
		<fieldset class="l-fieldset">
					<legend class="l-legend">
						<div>维修车辆</div>
					</legend>
					<table width="100%" border="1" bordercolor="#99CCFF" cellspacing="0" cellpadding="1">
						<tr style="height: 30px;" bgcolor="#CCFFFF">
							<td  align=center width="5%" >车辆品牌</td>
							<td  align=center width="5%" >车牌号</td>
							<td  align=center width="5%">车型</td>
							<td  align=center width="10%">发动机号</td>
							<td  align=center width="10%">车架号码</td>
							<td  align=center width="5%">排量(L)</td>
							<td  align=center width="5%">核载人数(人)</td>
							<td  align=center width="10%">产地</td>
							<td  align=center width="5%">颜色</td>
							<td  align=center width="10%">购车日期</td>
							<td  align=center width="5%">所属部门</td>
						</tr>
						<c:if test="${list5=='[]'}">
							<tr align="center" style="height: 26px;">
								<td  align=center colspan="11" >没有数据！</td>
							</tr>
						</c:if>
						<c:if test="${list5!='[]'}">
							<c:forEach items="${list5}" var="list5">
								<tr style="height: 26px;">
									<td align=left height="15px;"><c:out value="${list5.carBrand}"></c:out></td>
									<td align=center height="15px;"><c:out value="${list5.carNum}"></c:out></td>
									<td align=left height="15px;"><c:out value="${list5.carType}"></c:out></td>
									<td align=center height="15px;"><c:out value="${list5.engineNo}"></c:out></td>
									<td align=center height="15px;"><c:out value="${list5.frameNo}"></c:out></td>
									<td align=left height="15px;"><c:out value="${list5.carDisplacement}"></c:out>(L)</td>
									<td align=left height="15px;"><c:out value="${list5.loadNumber}"></c:out></td>
									<td align=left height="15px;"><c:out value="${list5.address}"></c:out></td>
									<td align=left height="15px;"><c:out value="${list5.color}"></c:out></td>
									<td align=center height="15px;"><c:out value="${list5.buyDate}"></c:out></td>
									<td align=left height="15px;"><c:out value="${list5.managerRoomName}"></c:out></td>
								</tr>
							</c:forEach>
						</c:if>
					</table>
			</fieldset>
	</div>
</div>
</body>
</html>