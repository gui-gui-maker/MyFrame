<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="">
$(document).ready(function() { 


<%
String ss="";

if(request.getSession().getAttribute("edcrfv")!=null){
	ss=request.getSession().getAttribute("edcrfv").toString();}%>
var ss=<%=ss%>+"";
if(ss!="8888"){
	var pathName=window.document.location.pathname;  
    			var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1); 
 return window.location.href=projectName+"/finance/messageChecky_detail.jsp"; 
}

}); 
</script>
<script type="text/javascript">
    var khFileUploader;
	var qmUserConfig = {
		sp_fields : [
		],
		tbar:[
              {text: '详情', id: 'detail', icon: 'detail', click: detail}
            ],
		listeners: {
			rowClick: function(rowData, rowIndex) {
			},
			rowDblClick: function(rowData, rowIndex) {
				detail();
			},	
			selectionChange: function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();
				Qm.setTbar({
					detail: count==1,
					edit: count==1,
					del: count>0
				});
			}
		}
	};
	
	
function add() {
	
	var a = $("#imid").val();
	top.$.dialog({
		
		width: 900,
		height: 500,
		lock: true,
		parent: api,
		data: {
			window: window
		},
		title: "新增",
		content: 'url:app/finance/salary_detail.jsp?imid='+a+'&pageStatus=add'
	});
}
	function edit() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 900,
			height: 500,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "修改",
			content: 'url:app/finance/salary_detail.jsp?id=' + id + '&pageStatus=modify'
		});
	}
	function detail() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 900,
			height: 500,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "详情",
			content: 'url:app/finance/salaryy_detail.jsp?id=' + id + '&pageStatus=detail'
		});
	}
	function del() {
		$.del(kFrameConfig.DEL_MSG, "finance/salaryAction/delete.do", {
			"ids": Qm.getValuesByName("id").toString()
		});
	}
	
</script>
</head>
<body>
<input type="hidden"  id="imid" value="${param.id}" type="text" ></input>
	<qm:qm pageid="TJY2_GEGZCX" script="false" singleSelect="false">
	
	</qm:qm>
</body>
</html>