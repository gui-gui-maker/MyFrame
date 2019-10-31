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
			text: '提醒设置', id: 'setting', icon: 'setting', click: setting
		}, "-", {
			text: '批量设置', id: 'settings', icon: 'setting', click: settings
		}
		<sec:authorize access="hasRole('sys_administrate')">
		, "-", {
			text: '发送提醒', id: 'send', icon: 'setting', click: send
		}
		</sec:authorize> 
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
		         	positive:count>0,
		         	setting: count==1,
		         	settings: count>1
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
        function setting(){
        	var idCard = Qm.getValueByName("emp_id_card");
        	if(idCard){
        		top.$.dialog({
      		         width: 500,
      		         height: 280,
      		         lock: true,
      		         parent: api,
      		         data: {
      		       	 window: window
      		         },
      		         title: "生日提醒设置",
      		         content: 'url:app/humanresources/employee_birthday_setting.jsp?pageStatus=modify&name='
      		        		 +encodeURI(encodeURI(Qm.getValueByName("emp_name")))+'&id='+Qm.getValueByName("id")
      		        		 +'&emp_id_card='+Qm.getValueByName("emp_id_card")
      	          });
        	}else{
        		$.ligerDialog.alert("身份证号信息不完整，无法进行设置！");
        	}
          }
        function settings(){
         	 top.$.dialog({
  		         width: 500,
  		         height: 280,
  		         lock: true,
  		         parent: api,
  		         data: {
  		       	 window: window
  		         },
  		         title: "生日提醒批量设置",
  		       	 content: 'url:app/humanresources/employee_birthday_settings.jsp?pageStatus=modify&&ids='+Qm.getValuesByName("id")
  	          });
         	
         }
        function send(){
        	$.ajax({
            	url: "remindMessageAction/sendBirthdayRemind.do",
                type: "POST",
                datatype: "json",
                contentType: "application/json; charset=utf-8",
                success: function (resp) {
                },
                error: function (data) {
                	$.ligerDialog.alert("获取信息失败！");
                }
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