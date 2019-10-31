<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<title>四川省特种设备检验研究院 简历在线填报系统</title>

</head>
<body>

	<form id="formObj" action="" getAction="employeeBaseAction/detailBasic.do?id=${param.id}">
		<input type="hidden" id="empId" name="id" />
		<input type="hidden" id="fkEmployee" name="fkEmployee" />
		<input type="hidden" id="empPhoto" name="empPhoto" />
		<input type="hidden" id="isCheck" name="isCheck" />
		<input type="hidden" id="empStatus" name="empStatus" />
		<input type="hidden" id="createDate" name="createDate" />
		<h1 class="l-label" style="padding:5mm 0 2mm 0;font-family:微软雅黑;font-size:5mm;">四 川 省 特 种 设 备 检 验 研 究 院 简 历 在 线 填 报 系 统 </h1>
		<h1 class="l-label" style="padding:5mm 0 2mm 0;font-family:微软雅黑;font-size:6mm;">个&nbsp;人&nbsp;简&nbsp;历&nbsp;</h1><div style="height:2px">&nbsp;</div>
	   <div class="fieldset-caption">
            <span>基本信息</span>
        </div>
	 	<table border="1" cellpadding="3" cellspacing="0" width=""
				class="l-detail-table" id="table1">
					 <tr>
						<td class="l-t-td-left">姓名</td>
						<td class="l-t-td-right"><input type="text" ltype="text" name="empName" validate="{required:true,maxlength:32}" /></td>
						<td class="l-t-td-left">性别</td>
						<td class="l-t-td-right"><u:combo  name="empSex" code="BASE_SEX" validate="required:true"></u:combo></td>
						<td class="l-t-td-right" rowspan="9" align="center" width="190px">
						<div><img id="image" src="" style="display: none;width:180px;height: 220px"></img></div>
						<c:if test='${param.pageStatus!="detail" }'>
						<span id="onefileDIV">
						         <a class="l-button" id="onefileBtn"><span
									class="l-button-main"><span class="l-button-text">选择图片</span>
								</span>
								</a>
						</span>
						</c:if></td>
					</tr> 
					<tr>
						<td class="l-t-td-left">民族</td>
						<td class="l-t-td-right"><input type="text" ltype="text" name="empNation" validate="{maxlength:32}"  /></td>
						<td class="l-t-td-left">籍贯</td>
						<td class="l-t-td-right"><input type="text" ltype="text"  name="empNativePlace" validate="{maxlength:50}"/></td>
					</tr>
					<tr>
						<td class="l-t-td-left">身份证号</td>
						<td class="l-t-td-right"><input type="text" ltype="text" id="empIdCard" name="empIdCard" validate="{required:true,idno:true}" onblur="ageAndBirthday()"/></td>
						<td class="l-t-td-left">政治面貌</td>
						<td class="l-t-td-right"><input type="text" ltype="text"  name="empPolitical" validate="{maxlength:32}"/></td>
					</tr>
					<tr>
						<td class="l-t-td-left">身高(cm)</td>
						<td class="l-t-td-right"><input type="text" ltype="spinner" name="empStature" ligerui="{type:'int',isNegative:false}" /></td>
						<td class="l-t-td-left">体重(kg)</td>
						<td class="l-t-td-right"><input type="text"  ltype="spinner"  name="empWeight" ligerui="{type:'int',isNegative:false}" /></td>
					</tr>
					<tr>
						<td class="l-t-td-left">出生日期</td>
						<td class="l-t-td-right"><input type="text" ltype="text" id="birthDate1" name="birthDate1" readonly="readonly" /></td>
						<td class="l-t-td-left">年龄</td>
						<td class="l-t-td-right"><input type="text"  ltype="text"  id="age" name="age" readonly="readonly" /></td>
					</tr>
					<tr>
						<td class="l-t-td-left">职称</td>
						<td class="l-t-td-right"><input type="text" ltype="text" name="empTitle" validate="{maxlength:32}" /></td>
						<td class="l-t-td-left">职称证书编号</td>
						<td class="l-t-td-right"><input type="text" ltype="text"  name="empTitleNum" validate="{maxlength:32}"/></td>
					</tr>
					<tr>
						<td class="l-t-td-left">检验资格</td>
						<td class="l-t-td-right"><input type="text" ltype="text" name="empInspection"  validate="{maxlength:32}"/></td>
						<td class="l-t-td-left">持证情况</td>
						<td class="l-t-td-right"><input type="text" ltype="text"  name="empPermit" validate="{maxlength:32}"/></td>
					</tr>
					<tr>
						<td class="l-t-td-left">联系电话</td>
						<td class="l-t-td-right"><input type="text" ltype="text" name="empPhone"  validate="{maxlength:32}"/></td>
						<td class="l-t-td-left">期望工资(月)</td>
						<td class="l-t-td-right"><input type="text" ltype="spinner"  name="expectSalary" ligerui="{type:'int',isNegative:false}" /></td>
					</tr>
					<tr>
						<td class="l-t-td-left">参加工作时间</td>
						<td class="l-t-td-right"><input type="text" ltype="date" name="joinWorkDate"  /></td>
						<td class="l-t-td-left">入党时间</td>
						<td class="l-t-td-right"><input type="text" ltype="date"  name="joinPartyDate"  /></td>
					</tr>
					<tr>
						<td class="l-t-td-left">应聘岗位</td>
						<td class="l-t-td-right"colspan="4"><input type="text" ltype="text" name="freelanceJobs"  validate="{maxlength:32}"/></td>
					</tr>
					</table>
		</form>
</body>
</html>