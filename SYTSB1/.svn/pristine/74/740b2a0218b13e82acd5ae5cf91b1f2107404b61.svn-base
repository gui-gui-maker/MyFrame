<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd"); 
	String nowTime=""; 
	nowTime = dateFormat.format(new Date());
	
	//String sql="select t.*,to_char(sysdate, 'yyyy')-substr(emp_id_card, 7, 4) as age from TJY2_RL_EMPLOYEE_BASE t  where ( work_title_date<=sysdate+30 and work_title_date>sysdate)or  work_title_date<sysdate";
	String sql="select t.*, to_char(sysdate, 'yyyy') - substr(emp_id_card, 7, 4) as age "
			  +"from TJY2_RL_EMPLOYEE_BASE t "
			  +"where (t.work_title_warn_except = '0' or t.work_title_warn_except is null) "
			  +"  and ((work_title_date <= sysdate + 30 and work_title_date > sysdate) or "
			  +"      work_title_date < sysdate)";%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},		
		sp_fields:[
		           {name: "emp_name", compare: "like"},
				     {name: "emp_sex", compare: "like"},
                   {name: "work_department", compare: "="}
		] , 
		tbar: [ {
			text: '详情', id: 'detail', icon: 'detail', click: detail
		}, "-", {
			text: '例外人员', id: 'except', icon: 'user', click: except
		}/* , "-", {
			text: '新增', id: 'add', icon: 'add', click: add
		}, "-", {
			text: '修改', id: 'edit', icon: 'modify', click: edit
		}
		, "-", {
			text: '删除', id: 'positive', icon: 'del', click: positive
		}, "-", {
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
		         	positive:count>0,
		         	dismissal: count==1,
		         	retired: count==1
			
		       });
	        },
	        rowAttrRender : function(rowData, rowid) {
				}
}
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
        function except(){
       	 top.$.dialog({
		         width: 1000,
		         height: 500,
		         lock: true,
		         parent: api,
		         data: {
		       	 window: window
		         },
		         title: "例外人员管理",
		         content: 'url:app/humanresources/employee_warn_except.jsp?exceptType=title'
	          });
       	
       }
</script>
 
</head>
<body>
      <div class="item-tm"  id="titleElement">
     
		  <qm:qm pageid="TJY2_RL_EMPLOYEE" sql="<%=sql %>">
       <qm:param name="emp_status" value="4" compare="in" />
       </qm:qm>
</body>
</html>