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
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults: {columnWidth: 0.33, labelAlign: 'right', labelSeparator: '', labelWidth: 100},//可以自己定义 layout:column,float
		
		sp_fields: [ 
			{label: "报表代码：",name: "rt_code",compare: "like"}, 
			{label: "报表名称：",name: "rt_name",compare: "like"}
		],
		
		tbar: [ {
			text: '选择', id: 'select', icon: 'check', click: select
		}],
// 		<tbar:toolBar type="tbar"></tbar:toolBar>,
		listeners: {
			rowClick: function(rowData, rowIndex) {
			},
			rowDblClick: function(rowData, rowIndex) {
				detail();
			},
			rowAttrRender: function (rowData, rowid) {
                if(rowData.列名1=='XXX') // 记录为绿色
                {
                    return "style='color:green'";
                }
                if(rowData.列名2=='YYY') // 记录为红色
                {
                    return "style='color:red'";
                }
            },
			selectionChange: function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();
				Qm.setTbar({
					select: count==1
				});
			}
		}
	};

	function select() {
		var id = Qm.getValueByName("tpl_id");
		var rt_code = Qm.getValueByName("tpl_code");
		api.data.parentWindow.callRtBack(id,rt_code,"${param.type}");
		api.close();
	}

</script>
</head>
<body>
	<qm:qm pageid="rt_template" script="false" singleSelect="true">
		<c:if test="${param.type==''}">
			<qm:param name="model_type" value="null" compare="is" dataType="user"/>
			<qm:param name="model_type" value="0" compare="=" logic="or"/>
		</c:if>
		<c:if test="${param.type!=''}">
			<qm:param name="model_type" value="${param.type}" compare="=" />
		</c:if>
    </qm:qm>
</body>
</html>