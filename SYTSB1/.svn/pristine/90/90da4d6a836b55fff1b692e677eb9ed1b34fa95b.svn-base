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
		     {name: "work_department", compare: "like"},
                   {name: "emp_sex", compare: "like"},
                   {name: "emp_status", compare: "="},
                   {name: "emp_position", compare: "="},
                   {name: "ischeck", compare: "="}
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
	        	var fontColor="#8E8E8E";
	            if(rowData.ischeck=='已确认'){
	           	 fontColor="black";
	            }
	            return "style='color:"+fontColor+"'";
            	/* var fontColor="black";
            	//合同到期时间
            	var contractStopDate=rowData.contract_stop_date;
            	//工作职称到期时间
            	var workTitleDate=rowData.work_title_date;
            	//员工生日
            	var empBirthday=rowData.emp_birthday;
            	 var month= empBirthday.substr(0,2); 
            	var day=empBirthday.substr(3,4); 
            	//员工身份证号
            	var empIdCard=rowData.emp_id_card;
            	var empSex=rowData.emp_sex;
            	var year=empIdCard.substr(6,4); 
            	//获取年 ，月，日
             var con=new Date(contractStopDate.replace("-", "/").replace("-", "/"));  
             var work=new Date(workTitleDate.replace("-", "/").replace("-", "/"));  
             var date=new Date();
             var month1= date.getMonth()+1;
             var day1=date.getDay();
             var year1=date.getFullYear();
             if(empSex=="0"&&(year1-year)>=60){
            	 fontColor="red";
             }
             if(empSex=="1"&&(year1-year)>=55){
            	 fontColor="red";
             }
             if(month==month1&&day==day){
            	 fontColor="red";
             }
             if(con<date){
            	 fontColor="red";
             }
             if(work<date){
            	 fontColor="red";
             }
             
	       			return "style='color:"+fontColor+"'"; */
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
<div class="item-tm" id="titleElement">
        <div class="l-page-title">
            <div class="l-page-title-note">提示：列表数据项
                <font color="black">“黑色”</font>代表已确认，
                <font color="#8E8E8E">“灰色”</font>代表未确认。
            </div>
        </div>
    </div>
      <!-- <div class="item-tm"  id="titleElement">
       <div class="l-page-title">
			<div class="l-page-title-note">提示：列表数据项
				<font color="red">“红色”</font>代表员工生日,退休,合同到期,职称到期。
			</div> 
		</div>
		</div> -->
		  <qm:qm pageid="TJY2_RL_EMPLOYEE">
       <qm:param name="emp_status" value="(4,3)" compare="in" />
       	<c:if test='${param.type=="1"}'>
       	  <qm:param name="emp_title" value="技术员" compare="=" />
       	</c:if>
       	  	<c:if test='${param.type=="2"}'>
       	  <qm:param name="emp_title" value="助理工程师" compare="=" />
       	</c:if>
       	  	<c:if test='${param.type=="3"}'>
       	  <qm:param name="emp_title" value="工程师" compare="=" />
       	</c:if>
       	  	<c:if test='${param.type=="4"}'>
       	  <qm:param name="emp_title" value="高级工程师（副高级）" compare="=" />
       	</c:if>
       	  	<c:if test='${param.type=="5"}'>
       	  <qm:param name="emp_title" value="教授级高级工程师（正高级）" compare="=" />
       	</c:if>
       	  	<c:if test='${param.type=="6"}'>
       	  <qm:param name="emp_title" value="其他" compare="=" />
       	</c:if>
       </qm:qm>
</body>
</html>