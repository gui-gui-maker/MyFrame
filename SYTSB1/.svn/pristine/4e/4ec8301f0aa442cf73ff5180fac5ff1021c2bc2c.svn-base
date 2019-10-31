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
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},		
		sp_fields:[
				     {name: "emp_name", compare: "like"},
                     {name: "emp_sex", compare: "like"},
                     {name: "emp_status", compare: "="}
		] , 
		tbar: [ {
			text: '详情', id: 'detail', icon: 'detail', click: detail
		}, "-", {
			text: '任免', id: 'add', icon: 'setting', click: add
		}, "-", {
			text: '修改', id: 'modify', icon: 'modify', click: modify
		}, "-", {
			text: '记录', id: 'record', icon: 'modify', click: record
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
		         	add: count==1,
		         	modify: count==1,
		         	record: count==1
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
        function add(){
          	 top.$.dialog({
   		         width: 500,
   		         height: 280,
   		         lock: true,
   		         parent: api,
   		         data: {
   		       	 window: window
   		         },
   		         title: "职务任免",
   		         content: 'url:app/humanresources/employee_worktitle_setting.jsp?pageStatus=add&empId='+Qm.getValueByName("id")+'&recordId='
   	          });
          	
          }
        function modify(){
         	 top.$.dialog({
  		         width: 500,
  		         height: 280,
  		         lock: true,
  		         parent: api,
  		         data: {
  		       	 window: window
  		         },
  		         title: "职务任免",
  		         content: 'url:app/humanresources/employee_worktitle_setting.jsp?pageStatus=modify&empId='+Qm.getValueByName("id")+'&recordId='
  	          });
         	
         }
        function record(){
        	 top.$.dialog({
 		         width: 800,
 		         height: 400,
 		         lock: true,
 		         parent: api,
 		         data: {
 		       	 window: window
 		         },
 		         title: "职务记录",
 		         content: 'url:app/humanresources/employee_worktitle_record.jsp?pageStatus=detail&empId='+Qm.getValueByName("id")
 	          });
        	
        }
</script>
 
</head>
<body>
      <!-- <div class="item-tm"  id="titleElement">
       <div class="l-page-title">
			<div class="l-page-title-note">提示：列表数据项
				<font color="red">“红色”</font>代表员工生日,退休,合同到期,职称到期。
			</div> 
		</div>
		</div> -->
		  <qm:qm pageid="TJY2_RL_EMPLOYEE">
       <qm:param name="emp_status" value="(4,3)" compare="in" />
       </qm:qm>
</body>
</html>