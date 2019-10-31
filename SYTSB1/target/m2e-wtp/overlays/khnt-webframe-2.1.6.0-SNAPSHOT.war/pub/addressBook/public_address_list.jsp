<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>通讯录</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	 var qmUserConfig = {
		sp_fields: [
			{ name: "name", compare: "like", labelWidth: 40},
			{ name: "mobile_tel", compare: "like", labelWidth: 40},
			{ name: "office_tel", compare: "like", labelWidth: 60}
		],
	 	listeners: {
			rowDblClick: detailValue
		} 
	};
	function detailValue() {
		var id = Qm.getValueByName("id");
		var fk_employee_id = Qm.getValueByName("fk_employee_id");
		var res=2,lid=id;
		if(fk_employee_id && id==1){
			lid = fk_employee_id;
			res=1;
		}
		top.$.dialog({
			width: 800,
			height: 500,
			lock: true,
			parent: parent.api,
			title: "详情",
			content: 'url:pub/addressBook/public_address_detail.jsp?id=' + lid + '&pageStatus=detail&res='+res
		}); 
	}
	
	function loadGridData(orgId){
		Qm.config.defaultSearchCondition[0].value=orgId;
		Qm.searchData();
	}
</script>
</head>
<body>
	<qm:qm pageid="public_address_list"  script="false" singleSelect="false">
	    <qm:param name="my_type" compare="llike" value="${param.my_type}" />
	</qm:qm>	
</body>
</html>