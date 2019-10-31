<%@page contentType="text/html;charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<title></title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
	$(function() {
		initGrid();
		$("#form1").initForm({
			toolbar : [{
				text : '关闭',
				id : 'close',
				icon : 'close',
				click : function() {
					api.close();
				}
			}],
		});
		$.post("uploadsAction/a/queryCountByUploadType.do",{fileId:'${param.id}'},function(res){
			if(res.success){
				if(res.data.length>0){
					var rows = [];
					for(var i=0;i<res.data.length;i++){
						var row = {};
						row.upload_type = res.data[i][0];
						row.pic_count = res.data[i][1];
						rows.push(row);
					}
					g.loadData({
						Rows : rows
					});
				}
			}else{
				alert(res.msg);
			}
		});
	});
	//初始化选择框
	var g;
	function initGrid() {
		g = $("#g").ligerGrid({
								columns : [
											{
												display : '上传类型',
												name : 'upload_type',
												width : '30%',
												render : function(item) {
													var clsses = [ {
														text : '检验报告',
														id : '0'
													}, {
														text : '原始记录',
														id : '1'
													}, {
														text : '自检报告',
														id : '2'
													}, {
														text : '其他',
														id : '3'
													} ];
													for (var i = 0; i < clsses.length; i++) {
														if (item["upload_type"] == clsses[i].id) {
															return clsses[i].text;
														}
													}
												}
											},
											{
												display : '文件数量',
												name : 'pic_count',
												width : '30%'
											},
											{
												display : '操作',
												isAllowHide : false,
												render : function(row) {
													var html = '<a href="JavaScript:void(0)" onclick="downloads('+row.upload_type+')">下载</a>';
													return html;
												}
											} ],
							rownumbers : true,
							height : "280",
							width : "98%",
							frozenRownumbers : false,
							usePager : false,
							data : {
								Rows : []
							}
						});
	}

	function downloads(type){
		window.open("<%=basePath%>uploadsAction/a/downloadByUploadType.do?id=${param.id}&upload_type="+type+"&sn=${param.sn}");
		/* $.post("uploadsAction/a/downloadByUploadType.do",{upload_type:type,id:'${param.id}',sn:'${param.sn}'},function(res){
			
		}); */
	}
</script>
</head>

<body>
	<form name="form1" id="form1" method="post" action="">
		<fieldset class="l-fieldset">
			<legend class="l-legend"> 文件列表 </legend>
			<div id="g" style="height: 300px;"></div>
		</fieldset>
	</form>
</body>
</html>