<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>银行转账数据列表</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="">
$(document).ready(function() { 


<%
String ss="";

if(request.getSession().getAttribute("edcrfv")!=null){
	ss=request.getSession().getAttribute("edcrfv").toString();}%>
var ss="";
<sec:authorize access="hasRole('TJY2_CW_GZ')">
		var ss=<%=ss%>+"";
    	</sec:authorize>

if(ss!="9999"){
	var pathName=window.document.location.pathname;  
    			var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1); 
 return window.location.href=projectName+"/finance/messageCheck_detail.jsp"; 
}

}); 
</script>
<script type="text/javascript">
    var khFileUploader;
	var qmUserConfig = {
		sp_fields : [
		             {name:'name',compare:'like',columnWidth:0.3},
		],
		tbar:[//{text: '工资数据', id: 'importData', icon: 'excel-import'}
		    //  ,"-",
              {text: '详情', id: 'detail', icon: 'detail', click: detail}
		      ,"-",
		      {text: '新增', id: 'add', icon: 'add', click: add}
		       ,"-",
              {text: '修改', id: 'edit', icon: 'edit', click: edit}
               ,"-",
              {text: '删除', id: 'del', icon: 'del', click: del}
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
	  $(function () {
	        $("#btn2").css({"height":"20px","line-height":"18px"})
	        $("#btn2").ligerButton({
	            icon:"excel-export",
	            click: function (){
	                out();
	            },text:"导出"
	        });
	        
	  });
	  function out()
	    {
		    $("body").mask("正在导出数据,请等待...");
	        $("#form1").attr("action","finance/salaryAction/exportCount.do"); 
	        $("#form1").submit();
	        $("body").unmask();
		/*  var id = $("#imid").val();
		  $.ajax({
              url: "finance/salaryAction/exportCount.do?id="+id,
              type: "POST",
              datatype: "json",
              contentType: "application/json; charset=utf-8",
              success: function (data, stats) {
               
              },
              error: function (data,stats) {
                  $("body").unmask();
                  $.ligerDialog.error('提示：' + data.msg);
              }
          }); */
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
			content: 'url:app/finance/salary_detail.jsp?id=' + id + '&pageStatus=detail'
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
<div id="btn2"></div>
<form name="form1" id="form1" action="" getAction="" target="_blank">
<input type="hidden" name="imid" id="imid" value="${param.id}" type="text" ></input>
</form>

	<qm:qm pageid="TJY2_CW_SALARY_NEW" script="false" singleSelect="false">
	
	<qm:param name="IMPORT_ID" value="${param.id}" compare="=" />
	</qm:qm>
</body>
</html>