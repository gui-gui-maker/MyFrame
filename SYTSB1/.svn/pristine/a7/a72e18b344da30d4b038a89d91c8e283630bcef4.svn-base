<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://khnt.com/tags/qm" prefix="q" %>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>通用查询</title>
    <%@include file="/k/kui-base-list.jsp" %>
    <script test="text/javascript">
		var qmUserConfig = {
			sp_fields: [
				{label: "String", name: "t_string", compare: "="},
				{group:[
					{label: "date from", name: "t_date", compare: ">="},
					{label: "to", name: "t_date", compare: "<=",labelWidth:20,labelAlign:"center"}
				]}
			],
			tbar: [
				{ text: 'EXCEL 数据库导入', id: 'import1', icon: 'add', click: impdb },
				{ text: 'EXCEL Bean导入', id: 'import2', icon: 'add', click: impbean },
				{ text: 'EXCEL Hashmap导入', id: 'import4', icon: 'add', click: impMap },
				{ text: 'EXCEL 业务自行导入', id: 'import3', icon: 'add', click: impbus },
				{ text: 'EXCEL 数据库导出', id: 'export1', icon: 'add', click: expdb },
				{ text: 'EXCEL Bean导出', id: 'export2', icon: 'add', click: expbean },
				{ text: 'EXCEL Hashmap导出', id: 'export4', icon: 'add', click: expMap },
				{ text: 'EXCEL 业务自行导出', id: 'export3', icon: 'add', click: expbus }
			]
		};
		function impbus() {
			top.$.dialog({
				width: 450,
				height: 250,
				lock: true,
				title: "EXCEL数据导入",
				content: "url:pub/expimp/import.jsp?cfg=test_excel_bus_import",
				data: {callback:impcallback}
			});
		}
		function impdb() {
			top.$.dialog({
				width: 450,
				height: 250,
				lock: true,
				title: "EXCEL数据导入",
				content: "url:pub/expimp/import.jsp?cfg=test_excel_db_import",
				data: {callback:impcallback}
			});
		}
		function impbean() {
			top.$.dialog({
				width: 450,
				height: 250,
				lock: true,
				title: "EXCEL数据导入",
				content: "url:pub/expimp/import.jsp?cfg=test_excel_bean_import",
				data: {callback:impcallback}
			});
		}
		
		function impMap(){
			top.$.dialog({
				width: 450,
				height: 250,
				lock: true,
				title: "EXCEL数据导入",
				content: "url:pub/expimp/import.jsp?cfg=test_excel_map_import",
				data: {callback:impcallback}
			});
		}
		
		function impcallback(){
			Qm.refreshGrid();
		}
		
		function expdb(){
			location.href=$("base").attr("href") + "pub/expimp/export.do?config=test_excel_db_import";
		}
		function expbean(){
			location.href=$("base").attr("href") + "pub/expimp/export.do?config=exp_imp_demo";
		}
		function expbus(){
			location.href=$("base").attr("href") + "pub/expimp/export.do?config=exp_imp_demo";
		}
		function expMap(){
			location.href=$("base").attr("href") + "pub/expimp/export.do?config=exp_imp_demo";
		}
    </script>
</head>
<body>
<q:qm pageid="demo_expimp" />
</script>
</body>
</html>