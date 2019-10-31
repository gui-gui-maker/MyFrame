<%@ page import="com.khnt.security.util.SecurityUtil" %>
<%@ page import="com.khnt.security.CurrentSessionUser" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    CurrentSessionUser user = SecurityUtil.getSecurityUser();
    String userid = user.getId();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>列表页面</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults: {columnWidth: 1, labelAlign: 'right', labelSeparator: '', labelWidth: 100},//可以自己定义 layout:column,float
		
		sp_fields: [ 
			{name: "name",compare: "like"}
			
		],
		listeners : {
			onAfterShowData : function() {
				//initGridSelectRange();
			}
			<c:if test="${param.checkbox=='1'}">
				,
				onSelectRow: function(rowdata, rowindex, rowDomElement){
					parent.addOrRemoveUser(true,rowdata);
				},
	 			onUnSelectRow: function(rowdata, rowindex, rowDomElement){
	 				parent.addOrRemoveUser(false,rowdata);
	 			}
			</c:if>
			/* onAfterShowData : function() {
				initGridSelectRange();
			},
			<c:if test="${param.checkbox=='1'}">
			onCheckRow : function(checked, rowdata, rowindex, rowDomElement){
				parent.addOrRemoveUser(true,rowdata);
			},
			rowAttrRender:function(rowData,rowIndex){
				if(rowData.Status=="否"){
					  return 'style="color:#e6640d;"';
				}
			},
			onCheckAllRow:function(checked,row){
				var data = Qm.getQmgrid().getData();
				for(var i in data){
					parent.addOrRemoveUser(false, data[i]);
				}
			}
			
			</c:if>
			<c:if test="${param.checkbox=='0'}">
			onSelectRow: function(rowdata, rowindex, rowDomElement){
				parent.addOrRemoveUser(true,rowdata);
			},
			onUnSelectRow: function(rowdata, rowindex, rowDomElement){
				parent.addOrRemoveUser(false,rowdata);
			}</c:if> */
		}
	};
	
	function loadGridData(orgId) {
		Qm.config.defaultSearchCondition[0].value = orgId;
		Qm.searchData();
	}
	
	//表格渲染时，将被选择的角色勾选
	function initGridSelectRange(){
		var idRange = parent.getSelectUserArr();
		Qm.getQmgrid().selectRange("id", idRange);
	}
</script>
</head>
<body class="p0">
	<%-- <qm:qm pageid="lock_user_cid" script="false" singleSelect="${param.checkbox=='0'}" pagesize="100"></qm:qm> --%>
    <qm:qm pageid="lock_user_cid" script="false" singleSelect="true" pagesize="100"></qm:qm>
    
</body>
</html>