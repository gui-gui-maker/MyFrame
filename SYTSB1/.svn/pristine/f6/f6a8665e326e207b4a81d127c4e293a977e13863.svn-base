<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户信息</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
		sp_fields:[
			{name:"name",compare:"like"},
			{name : "org_id", compare : "=", value : "", treeLeafOnly: false},
			{name : "euser_type", compare : "=", value : ""}
	    ],
		tbar:[
             { text:'详情', id:'detail',icon:'detail', click: detailEmployee}
             <sec:authorize access="hasRole('delEmployee')">
             //,"-",
             //{ text:'新增', id:'add',icon:'add', click: addEmployee},
             ,"-",
             { text:'修改', id:'modify',icon:'modify', click: modifyEmployee},
             "-",
             { text:'重置独立密码', id:'initSecondPwd',icon:'modify', click: initSecondPwd}
             ,
             "-",
             { text:'报告权限', id:'permissions',icon:'modify', click: permissions}
             ,
             "-",
             { text:'上传头像', id:'pic',icon:'modify', click: pic}
             </sec:authorize>
        ],
        listeners: {
			selectionChange :function(rowData,rowIndex){
     			var count = Qm.getSelectedCount();//行选择个数
               	Qm.setTbar({modify:count==1,detail:count==1,del:count>0,initSecondPwd:count>0,permissions:count==1,pic:count==1});
			},
			rowDblClick : function(rowData, rowIndex) {
				Qm.getQmgrid().selectRange("id", [rowData.id]);
				detailEmployee();
			}
		}
	};

	//上传人员头像
	function pic() {
		top.$.dialog({
			width : 700, 
			height : 560, 
			lock : true, 
			title : "新增人员信息", 
			data : {"window" : window},
			content : 'url:app/employee/employee_ext_detail.jsp?status=modify&id='+ Qm.getValueByName("id")
		});
	}
	//新增人员信息
	function addEmployee() {
		top.$.dialog({
			width : 700, 
			height : 560, 
			lock : true, 
			title : "新增人员信息", 
			data : {"window" : window},
			content : 'url:app/employee/employee_detail.jsp?status=add'
		});
	}

	//修改人员信息
	function modifyEmployee(item) {
		top.$.dialog({
			width : 700, 
			height : 580, 
			lock : true, 
			title : "修改人员信息", 
			data : {"window" : window},
			content : 'url:app/employee/employee_detail.jsp?status=modify&id=' + Qm.getValueByName("id")
		});
	}
	
	//查看人员信息
	function detailEmployee() {
		top.$.dialog({
			width : 700, 
			height : 560, 
			lock : true, 
			title : "查看人员信息", 
			data : {"window" : window},
			cancel : true,
			content : 'url:app/employee/employee_detail.jsp?status=detail&id=' + Qm.getValueByName("id")
		});
	}
	//配置人员信息
	function permissions() {
		top.$.dialog({
			width : 700, 
			height : 560, 
			lock : true, 
			title : "配置报告权限", 
			data : {"window" : window},
			content : 'url:app/employee/employee_permissions.jsp?status=modify&id=' + Qm.getValueByName("id")
		});
	}
	
	//重置独立密码
	function initSecondPwd() {
		$.ligerDialog.confirm("确定重置吗？重置后，系统将独立密码恢复成初始密码哦！", function(yes) {
			if (yes) {
				$.ajax({
					url : "employee/basic/initSecondPwd.do?ids="+Qm.getValuesByName("id").toString(),
					type : "post",
					async : false,
					success : function(data) {
						if (data.success) {
							top.$.notice("重置成功！");
							Qm.refreshGrid();
						} else {
							top.$.notice("重置失败！" + data.msg);
						}
					}
				});
			}
		});
	}
	
	// 刷新Grid
	function refreshGrid() {
   		Qm.refreshGrid();
	}
</script>
</head>
<body>
	<qm:qm pageid="sys_employee_list" script="false" singleSelect="false">
	</qm:qm>
	<script type="text/javascript">
	// 根据 sql或码表名称获得Json格式数据
	<%--var aa=<u:dict code="community_type"></u:dict>;--%>
	Qm.config.columnsInfo.org_id.binddata=<u:dict sql="select id,parent_id pid,id code, ORG_NAME text from SYS_ORG order by orders "></u:dict>;
	</script>
</body>
</html>
