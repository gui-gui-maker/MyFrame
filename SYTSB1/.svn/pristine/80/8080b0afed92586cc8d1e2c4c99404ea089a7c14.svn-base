<%@page import="com.lsts.report.bean.SysOrg"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%
	String exceptType=request.getParameter("exceptType");
	System.out.println("-------------例外人员查看类型："+exceptType+"-------------");
	SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd"); 
	String nowTime=""; 
	nowTime = dateFormat.format(new Date());
	String sql="";
	if(exceptType.equals("title")){
		sql="select t.*, to_char(sysdate, 'yyyy') - substr(emp_id_card, 7, 4) as age "
		        +"from TJY2_RL_EMPLOYEE_BASE t "
		        +"where t.work_title_warn_except = '1'";
	}else if(exceptType.equals("retired")){
		sql="select t.*, to_char(sysdate, 'yyyy') - substr(emp_id_card, 7, 4) as age "
				  +"from TJY2_RL_EMPLOYEE_BASE t "
				  +"where t.retired_warn_except = '1'";
	}
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
var exceptType="${param.exceptType}";
var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},		
		sp_fields:[
		           {name: "emp_name", compare: "like"},
				     {name: "emp_sex", compare: "like"},
                   {name: "work_department", compare: "="}
		] , 
		tbar: [ {
			text: '详情', id: 'detail', icon: 'detail', click: detail
		}
		 <sec:authorize access="hasRole('TJY2_RL_EXCEPT_MANAGE')">
		, "-", {
			text: '新增', id: 'add', icon: 'add', click: add
		}, "-", {
			text: '移除', id: 'remove', icon: 'del', click: remove
		}
		</sec:authorize> 
		/*, "-", {
			text: '修改', id: 'edit', icon: 'modify', click: edit
		} , "-", {
			text: '解聘', id: 'dismissal', icon: 'del', click: dismissal
		}, "-", {
			text: '退休', id: 'retired', icon: 'del', click: retired
		} */
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
		         	remove:count>0,
		         	dismissal: count==1,
		         	retired: count==1
			
		       });
	        },
	        rowAttrRender : function(rowData, rowid) {
				}
}
}
		
		
		
		function remove(){
			var ids=Qm.getValuesByName("id").toString();
			var url="";
			if(exceptType=="title"){
				url="employeeBaseAction/removeWorkTitleWarnExcept.do?ids="+ids;
			}else if(exceptType=="retired"){
				url="employeeBaseAction/removeRetiredWarnExcept.do?ids="+ids;
			}
			$.ligerDialog.confirm('确定移除？', function (yes){
	        	if(!yes){return false;}
	            top.$.ajax({
	                 url: url,
	                 type: "GET",
	                 dataType:'json',
	                 async: false,
	                 success:function (data) {
	                    if(data.success){
	                       top.$.notice(data.msg,3);
	                        Qm.refreshGrid();//刷新
	                    }else{
	                        $.ligerDialog.warn(data.msg);
	                    }
	                 },
	                 error:function () {
	                     //$.ligerDialog.warn("提交失败！");
	                	 $.ligerDialog.error("出错了！请重试！！！");
	                 }
	             });
	        });
	      /* $.del("确定移除?", "employeeBaseAction/removeWorkTitleWarnExcept.do", {
	       	"ids" : Qm.getValuesByName("id").toString()
	      }); */
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
		         width: 500,
		         height: 300,
		         lock: true,
		         parent: api,
		         data: {
		       	 window: window
		         },
		         title: "添加例外人员",
		         content: 'url:app/humanresources/warn_except_add.jsp?pageStatus=add&exceptType='+exceptType
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
</script>
 
</head>
<body>
       <qm:qm pageid="TJY2_RL_EMPLOYEE" sql="<%=sql %>">
       <%-- <qm:qm pageid="TJY2_RL_EMPLOYEE"> --%>
       <qm:param name="emp_status" value="4" compare="in" />
       </qm:qm>
</body>
</html>