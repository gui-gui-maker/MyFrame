<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd"); 
	String nowTime=""; 
	nowTime = dateFormat.format(new Date());%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
var qmUserConfig = {
		sp_fields:[
			{name: "type", compare: "like", columWidth: 0.33}
		] , 
		tbar: [ {
			text: '详情', id: 'detail', icon: 'detail', click: detail
		}
		
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
		         	positive:count>0,
		         	dismissal: count==1,
		         	retired: count==1,
		         	renew: count==1,
		         	initSecondPwd: count>0,
		         	dateAndDays: count==1,
		         	empSort: count==1
		       });
	        },
	        rowAttrRender : function(rowData, rowid) {
	        	
				}
}
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
		function positive(){
	      $.del("确定删除?", "employeeBaseAction/deleteBase.do", {
	       	"ids" : Qm.getValuesByName("id").toString()
	      });
	
         }
		
		function detail(){
	         top.$.dialog({
		         width: 900,
		         height: 500,
		         lock: true,
		         parent: api,
		         data: {
		       	 window: window
		         },
		         title: "详情",
		         content: 'url:app/humanresources/registration_datail.jsp?pageStatus=detail&id='+Qm.getValueByName("id")
	          });
        }
        function add(){
        	 top.$.dialog({
		         width: 900,
		         height: 500,
		         lock: true,
		         parent: api,
		         data: {
		       	 window: window
		         },
		         title: "新增信息",
		         content: 'url:app/humanresources/registration_datail.jsp?pageStatus=add&status=4&permission=formal'
	          });
        	
        }
        function renew(){
        	   top.$.dialog({
        	         width: 900,
        	         height: 500,
        	         lock: true,
        	         parent: api,
        	         data: {
        	       	 window: window
        	         },
        	         title: "续签合同",
        	         content: 'url:app/humanresources/registration_datail.jsp?pageStatus=modify&id='+Qm.getValueByName("id")+"&contract=contract&renew=renew&permission=pppp"
        	      });
        	
        }
        function dismissal(){
        	 top.$.dialog({
		         width: 500,
		         height: 200,
		         lock: true,
		         parent: api,
		         data: {
		       	 window: window
		         },
		         title: "解聘员工",
		         content: 'url:app/humanresources/employee_remove.jsp?pageStatus=add&id='+Qm.getValueByName("id")+"&status=0"
	          });
        	
        	
        }
        function retired(){
        	 top.$.dialog({
		         width: 500,
		         height: 200,
		         lock: true,
		         parent: api,
		         data: {
		       	 window: window
		         },
		         title: "退休员工",
		         content: 'url:app/humanresources/employee_remove.jsp?pageStatus=add&id='+Qm.getValueByName("id")+"&status=1"
	          });
        }
        function edit(){
        	 top.$.dialog({
		         width: 900,
		         height: 500,
		         lock: true,
		         parent: api,
		         data: {
		       	 window: window
		         },
		         title: "修改信息",
		         content: 'url:app/humanresources/registration_datail.jsp?pageStatus=modify&id='+Qm.getValueByName("id")+"&status=4&permission=formal&fkEmployee="+Qm.getValueByName("fk_employee")
	          });
        	
        }
        function dateAndDays(){
        	top.$.dialog({
		         width: 400,
		         height: 235,
		         lock: true,
		         parent: api,
		         data: {
		       	 window: window
		         },
		         title: "工龄及可休假天数",
		         content: 'url:app/humanresources/employee_dateanddays_detail.jsp?pageStatus=modify&id='+Qm.getValueByName("id")
	          });
        }
        function loadGridData(treeId) {
    		if (treeId != null&&treeId !="100000") {
    			Qm.setCondition([ {
    				name : "work_department",
    				compare : "like",
    				value : treeId
    			} ]);
    		} else {
    			Qm.setCondition([]);
    		}
    		Qm.searchData();
    	}
      	//排序设置
    	function empSort() {
    		var url = "app/humanresources/employee_sort_setting.jsp";
    		top.$.dialog({
    			width : 500, 
    			height : 220, 
    			lock : true, 
    			title : "排序设置", 
    			parent: api,
    			data: {
    				window: window
    			},
    			content : 'url:'+url+'?pageStatus=modify&id='+Qm.getValueByName("id")
    		});
    	}
        
</script>
 
</head>
<body>

		  <qm:qm pageid="EMPLOYEE_PERSON_TYPE">
       
         </qm:qm>
</body>
</html>