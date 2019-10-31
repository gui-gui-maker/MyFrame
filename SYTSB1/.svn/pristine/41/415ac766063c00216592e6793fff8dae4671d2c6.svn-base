<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title></title>
<script type="text/javascript">
</script>
</head>
<body>

	<form id="form_contract" action="equipmentContractAction/saveContract.do?buyApplyId=${param.id}&renew=${param.renew}" getAction="equipmentContractAction/detailContract.do?buyApplyId=${param.id}">
		<input type="hidden" id="conId" name="id" />
		<input type="hidden" id="fkEquipApplyId" name="fkEquipApplyId" />
		<table border="1" cellpadding="3" cellspacing="0" width=""
				class="l-detail-table">
					<tr >
						<td class="l-t-td-left">合同签订人</td>
						<td class="l-t-td-right"><input  ltype='text' readonly="readonly" id="signedMan" name="signedMan" type="text" onclick="choosePerson1()" ligerui="{iconItems:[{icon:'user',click:choosePerson}]}"/>
						<td class="l-t-td-left" >合同类型</td>
						<td class="l-t-td-right"><u:combo  name="contractType" code="TJY2_EQ_HT_TYPE" validate="required:true"></u:combo></td>
					
					</tr>
					<tr >
						<td class="l-t-td-left">合同签订日期</td>
						<td class="l-t-td-right"><input type="text" ltype="date" name="contractStartDate" validate="{required:true}" /></td>
						<td class="l-t-td-left">合同终止日期</td>
						<td class="l-t-td-right"><input type="text" ltype="date"  name="contractStopDate" validate="{required:true}" /></td>
					</tr>
					<tr height="80px">
					<td class="l-t-td-left">合同文档</td>
					<td class="l-t-td-right" colspan="3">
						<input name="documentName" type="hidden" id="documentName" validate="{maxlength:1000}" />
						<input name="documentDoc" type="hidden" id="documentDoc" validate="{maxlength:1000}" />
						<c:if  test='${param.status!="detail"}'>
						<p id="procufilesDIV">
							<a class="l-button" id="procufilesBtn">
								<span class="l-button-main"><span class="l-button-text">选择文件</span></span>
							</a>
						</p>
						</c:if>
				    	 <div class="l-upload-ok-list">
							<ul id="procufiles3"></ul>
						</div> 
					</td>
				</tr>
		</table>
												
	</form>
</body>
</html>