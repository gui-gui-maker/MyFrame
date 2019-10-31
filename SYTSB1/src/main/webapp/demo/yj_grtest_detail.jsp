<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.khnt.utils.DateUtil" %>
<%@page import="com.khnt.security.CurrentSessionUser" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
	CurrentSessionUser sessionUser = (CurrentSessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	String areaCode = sessionUser.getDepartment().getAreaCode();
	String unit = sessionUser.getUnit().getOrgName();
	String user = sessionUser.getName();
	java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
	java.util.Date currentTime = new java.util.Date();//得到当前系统时间 
	String str_date1 = formatter.format(currentTime); //将日期时间格式化 
	String birth = formatter.format(DateUtil.adjustYear(currentTime, -18));

%>
<head pageStatus="${param.pageStatus}">
<title></title>
<%@ include file="/k/kui-base-form.jsp" %>

<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="text/javascript" src="pub/photograph/js/photograph.js"></script>
<script type="text/javascript" src="pub/rbac/js/area.js"></script>
<script type="text/javascript">
    var pageStatus = "${param.pageStatus}";
	var p;

	//载入layout。初始化页面
	function initPage() {
		$("#layout1").ligerLayout({
			leftWidth: 220,
			sapce: 5,
			allowLeftCollapse: false,
			allowRightCollapse: false
		});
	}

	$(function () {
		//如果不设置额外参数，不用调用此方法，初始化时会自动调用
		$("#form1").initForm({
			//取得图片
			getSuccess: function (res) {
				if (res.data && res.data.idn) {
					//传入身份证id、返回是否有图片(true:有,false:否)
					p.setIdn(res.data.idn);
				}
			}
		});

		//初始化页面
		initPage();

		//照片设置
		p = new photogragh("photocontainer", function (val) {
			$("#photos").val(val);
		});

		//设置是否显示上传按钮(true:显示,false:不显示)
		p.setPictureButton(true);
	});

	function getidn(data) {
		p.setIdn(data.idn);//根据身份证号取图片路径
		var idCard = data.idn;
		if (idCard != "") {
			var result = $.kh.validte(idCard);
			if (result.result == false) {
				$.ligerDialog.error("身份证号码位数不对!");
				$("#idn").val("");
				$("#birthday").val("");
				$("#sex-txt").val("");
			}
			else if (!result.msg == "") {
				$.ligerDialog.error(result.msg);
				$("#idn").val("");
				$("#birthday").val("");
				$("#sex-txt").val("");
			} else {
				var birthday = result.idInfo.birthday;
				var xb = result.idInfo.gender;
				//设置出生日期
				$("#birthday").val(birthday);
				//设置性别
				$("#sex-txt").ligerComboBox().selectValue(xb);
			}
		} else {
			$("#birthday").val("");
			$("#sex-txt").val("");
		}
	}
       
    </script>
<style type="text/css">
.l-panel-header {
	background:url("");
	border-bottom:1px solid #99BBE8;
	color:#15428B;
	font-size:12px;
	font-weight:bold;
	height:24px;
	position:relative;
}

.l-text-suffix {
	padding:0 0 0 5px !important;
	vertical-align:middle;
	width:36px;
}
</style>
</head>

	<body>
	
	<form id="form1" action="gr/user/saves.do"
		  getAction="gr/user/detail.do?id=${param.id}">
				<input type="hidden" name="id"/>
				 <input type="hidden" name="areaCode" value="<%=areaCode%>"/>
				<input type="hidden" name="status" value="0"/>
				<fieldset class="l-fieldset">
					<legend class="l-legend">
						<div>测试资料</div>
					</legend>
					<table border="1" cellpadding="3" cellspacing="0" width=""
						   class="l-detail-table">
						<tr>
							<td class="l-t-td-left">姓 名：</td>
							<td class="l-t-td-right"><input name="name" type="text"
															ltype='text' validate="{required:true,maxlength:50}"
															ligerui="{autocomplete:{url:'base/citizen/q.do',
								        option:{extraParams:{name:'name',hasHouse:true,hasFamily:true,conditions:[['birthday','>','<%=birth %>']]},
								        setValueColumn:{idn:'idn','addr':'regAddr',tel:'tel','giveOff':'energy','natives':'regAreaName'},
								        valueKey:'name',
								        displayColumn:[{name:'name',width:130},{name:'idn',width:130}]}}}"/>
							</td>
							<td class="l-t-td-left">身份证号：</td>
							<td class="l-t-td-right"><input name="idn" type="text"
															ltype='text' validate="{required:true,idno:true,maxlength:18}"
															ligerui="{
								        autocomplete:{url:'base/citizen/q.do',
								        option:{extraParams:{name:'idn',hasHouse:true,hasFamily:true,conditions:[['birthday','>','<%=birth %>']]},
								        valueKey:'idn',
								        setValueColumn:{'name':'name','birthday':'birthday','nation':'nation','sex':'sex','addr':'regAddr',tel:'tel','giveOff':'energy','natives':'regAreaName','addrAreaCode':'addrAreaId','addrAreaName':'addrAreaName'},
								        displayColumn:[{name:'name',width:130},{name:'idn',width:130}]},result:function(event, data, formatted){getidn(data);}}} "/>
							</td>
							<td class="l-t-td-left" rowspan="5">照片：<input type="hidden" name="photos" id="photos"/></td>
	                        <td class="l-t-td-right" rowspan="5" style="text-align: center" id="photocontainer"></td>
						</tr>
						<tr>
							<td class="l-t-td-left">性 别：</td>
							<td class="l-t-td-right"><u:combo name="sex" code="pub_xb"
															  validate="required:true"/></td>
							<td class="l-t-td-left">出生日期：</td>
							<td class="l-t-td-right"><input name="birthday" id="birthday" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}"/></td>
						</tr>
						<tr>
							<td class="l-t-td-left">民 族：</td>
							<td class="l-t-td-right"><u:combo name="nation" code="ba_mz"/></td>
							<td class="l-t-td-left">宗教信仰：</td>
			                <td class="l-t-td-right"><u:combo name="rel" code="ba_zjxy"/></td>
						</tr>
						<tr>
						     <td class="l-t-td-left">政治面貌：</td>
						     <td class="l-t-td-right"><u:combo name="pol" code="ba_zzmm"/></td>
						     <td class="l-t-td-left">年龄：</td>
						     <td class="l-t-td-right"><input name="age" type="text" ltype='spinner'
															 validate="{required:false}" ligerui="{type:'int',suffixWidth:'60',readonly:false}"/></td>
						</tr>
					</table>
				</fieldset>
			</form>
		
</body>
</html>