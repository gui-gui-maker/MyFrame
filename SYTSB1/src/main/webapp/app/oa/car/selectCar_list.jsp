<%@page import="com.khnt.rbac.impl.bean.Org"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.StringUtil"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%
	String type = request.getParameter("type");
	String state=request.getParameter("state");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>通用查询</title>
<%@ include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
			sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
			sp_fields : [
				{
					name : "car_num", compare : "like"
				}, {
					name : "car_brand", compare : "like"
				}
		],
		<%if ("apply".equals(type)) {%>
		tbar : [{
			text : '查看使用情况',
			id : 'detail',
			icon : 'detail',
			click : detail
		}],
		<%}%>
		listeners : {
			rowClick : function(rowData, rowIndex) {
			}, rowDblClick : function(rowData, rowIndex) {
			}, selectionChange : function(rowData, rowIndex) {
		<%if ("apply".equals(type)) {%>
			selectionChange();
		<%}%>
			}, rowAttrRender : function(rowData, rowid) {
				if (rowData.change_num != "")
					rowData.car_num = rowData.car_num + "(" + rowData.change_num + ")";
			}
		}
	};

	//选择结果
	function getSelectResult() {
		var result = {
			carid : "", carnum : "", driver : ""
		};
		result.carid = Qm.getValuesByName("id");
		result.carnum = Qm.getValuesByName("car_num");
		result.driver = Qm.getValuesByName("driver");
		return result;
	}

	function selectionChange() {
		var count = Qm.getSelectedCount();//行选择个数
		var noticeState = Qm.getValuesByName("release").toString();//行选择个数

		if (count == 0) {
			Qm.setTbar({
				detail : false
			});
		} else if (count == 1) {
			Qm.setTbar({
				detail : true
			});
		} else {
			Qm.setTbar({
				detail : false
			});
		}
	}

	function detail() {
		var title = "查看车辆使用情况";
		var windows = top.$.dialog({
			width : 750, height : 500, lock : true, title : title, parent : api, cancel : true,
			data : {
				"window" : window
			}, content : 'url:app/oa/car/seeCarUsed_list.jsp?id=' + Qm.getValuesByName("id")
		});
	}
</script>
</head>
<%
	String unitId = SecurityUtil.getSecurityUser().getUnit().getId();
	Org org = (Org)SecurityUtil.getSecurityUser().getDepartment();
%>
<body>
	<qm:qm pageid="oa_selcar" script="false" singleSelect="true">
		<qm:param name="org_code" value="<%=unitId %>" compare="=" />
		<sec:authorize access="hasRole('oa_car_info')">
			<qm:param name="level_code" value="" compare="like" />
		</sec:authorize>
		<sec:authorize ifNotGranted="oa_car_info">
			<qm:param name="level_code" value="<%=org.getLevelCode() %>" compare="like" logic="and"/>
			<%
				if(StringUtil.isNotEmpty(state)){
					%>
					<qm:param name="state" value="<%=state%>" compare="="/>
					<%
				}
			%>
		</sec:authorize>
	</qm:qm>
</body>
</html>