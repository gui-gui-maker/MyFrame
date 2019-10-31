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
				     {name: "initial_education", compare: "like"},
				     {name: "initial_major", compare: "like"},
				     {name: "mba_education", compare: "like"},
				     {name: "mba_major", compare: "like"},
				     {name: "freelance_jobs", compare: "like"},
				     {name: "emp_status", compare: "="},
				     {group:[
							{name:"create_date", compare:">=", value:""},
							{label:"到", name:"create_date", compare:"<=", value:"", labelAlign:"center", labelWidth:20}
						]}
		] , 
		tbar: [ {
			text: '详情', id: 'detail', icon: 'detail', click: detail
		}
		 ,  "-", {
			text: '修改', id: 'edit1', icon: 'modify', click: edit1 
		},  "-", {

			text: '审核通过', id: 'addAudit', icon: 'modify', click: modifyAudit
		}
		, "-", {
			text: '面试通过', id: 'edit', icon: 'modify', click: edit
			
		}, "-", {
			text: '不通过', id: 'noPass', icon: 'del', click: noPass
		}, "-", {
			text: '删除', id: 'del', icon: 'del', click: del
		}, "-", {
			text: '发送信息', id: 'sendMsg', icon: 'modify', click: sendMsg
		}
		/* , "-", {
			text: '转正', id: 'positive', icon: 'modify', click: positive
		} */],
		
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
		          	noPass: count==1,
		          	edit1: count==1,
		          	del: count>0,
		         	edit: Qm.getValueByName("emp_status")=="面试"&&count==1,
		         	addAudit: Qm.getValueByName("emp_status")=="待审核"&&count==1, 
		         	positive: Qm.getValueByName("emp_status")=="试用"&&count==1,
		         	sendMsg: Qm.getValueByName("emp_status")=="面试"&&count>0
			
		       }); 
	        },   
	        rowAttrRender : function(rowData, rowid) {
	            	var fontColor="black";
	            	var fireDate=rowData.fire_date;
	            	//获取试用日期后3个月的年 ，月，日
	            	var date1=new Date(fireDate);
	            	var year1=date1.getFullYear();
	            	var month1=date1.getMonth()+4;
	            	if(month1-12>0){
	            		month1=month1-12;
	            		year1=year1+1;
	            	}
	            	var day1=date1.getDay();
	            	//获取当前时间的年 ，月，日
	            	var date2=new Date();
	            	var year2=date1.getFullYear();
	            	var month2=date1.getMonth()+1;
	            	var day2=date1.getDay();
	            	if(year2==year1){
	            		
	            		if(month2-month1==1){
	            		
	            			if(day2+30-day1<=7){
	            				fontColor="red";
	            			}
	            		}else if(month2-month1==0){
	            			
	            			if(day2-day1<=7){
	            				fontColor="red";
	            			}
	            		}
	            	}	
	 	       			return "style='color:"+fontColor+"'";
	 				}

}
}
		
		
		
		function positive(){
     	var id=Qm.getValueByName("id");
	   $.ligerDialog.confirm('是否转正？', function (yes){
        if(!yes){return false;}
        top.$.ajax({
                     url: "employeeBaseAction/addPositive.do?id="+id,
                     type: "GET",
                     dataType:'json',
                     async: false,
                     success:function (data) {
                        if(data.success){
                            $.ligerDialog.success("提交成功！");
                            Qm.refreshGrid();//刷新
                        }else{
                            $.ligerDialog.warn(data.msg);
                        }
                     },
                     error:function () {
                         $.ligerDialog.warn("提交失败！");
                     }
                 });
    });
         }
		function del(){
			      $.del("确定删除?", "employeeBaseAction/delete.do", {
			       	"ids" : Qm.getValuesByName("id").toString()
			      })
		}
		function noPass(){
			  $.ligerDialog.confirm('是否不通过？', function (yes){
                  if(!yes){return false;}
                  top.$.ajax({
                               url: "employeeBaseAction/noPass.do?id="+id,
                               type: "GET",
                               dataType:'json',
                               async: false,
                               success:function (data) {
                                  if(data.success){
                                      $.ligerDialog.success("操作成功！");
                                      Qm.refreshGrid();//刷新
                                  }else{
                                      $.ligerDialog.warn(data.msg);
                                  }
                               },
                               error:function () {
                                   $.ligerDialog.warn("操作失败！");
                               }
                           });
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
        function modifyAudit(){
        	if(Qm.getValueByName("emp_status")!="应聘申请"){
        		
        	}
        	var id=Qm.getValueByName("id");
        	   $.ligerDialog.confirm('是否审核通过？', function (yes){
                   if(!yes){return false;}
                   top.$.ajax({
                                url: "employeeBaseAction/addAudit.do?id="+id,
                                type: "GET",
                                dataType:'json',
                                async: false,
                                success:function (data) {
                                   if(data.success){
                                       $.ligerDialog.success("审核通过成功！");
                                       Qm.refreshGrid();//刷新
                                   }else{
                                       $.ligerDialog.warn(data.msg);
                                   }
                                },
                                error:function () {
                                    $.ligerDialog.warn("审核通过失败！");
                                }
                            });
               });
        	
        }
        function edit(){
        	top.$.dialog({
		         width: 900,
		         height: 500 ,
		         lock: true,
		         parent: api,
		         data: {
		       	 window: window
		         },
		         title: "面试通过",
		         content: 'url:app/humanresources/registration_datail.jsp?pageStatus=modify&id='+Qm.getValueByName("id")+"&fkEmployee="+Qm.getValueByName("fk_employee")+"&permission=permission"
	          });
        	
        }
        function edit1(){
        	var status=Qm.getValueByName("emp_status").toString();
        	var emp_status="";
        	if(status=="待审核"){
        		emp_status="1";
        	}else if(status=="面试"){
        		emp_status="2";
        	}else if(status=="试用"){
        		emp_status="3";
        	}else if(status=="在职"){
        		emp_status="4";
        	}else if(status=="解聘"){
        		emp_status="5";
        	}else if(status=="离退休"){
        		emp_status="6";
        	}else if(status=="应聘申请"){
        		emp_status="0";
        	}
        	top.$.dialog({
		         width: 900,
		         height: 500,
		         lock: true,
		         parent: api,
		         data: {
		       	 window: window
		         },
		         title: "修改信息",
		         content: 'url:app/humanresources/registration_datail.jsp?status='+emp_status+'&pageStatus=modify&id='+Qm.getValueByName("id")+"&permission=apply&fkEmployee="+Qm.getValueByName("fk_employee")
	          });
       	
       }
        function  sendMsg(){
        	top.$.dialog({
		         width: 500,
		         height: 200,
		         lock: true,
		         parent: api,
		         data: {
		       	 window: window
		         },
		         title: "发送短信",
		         content: 'url:app/humanresources/message_send.jsp?ids='+Qm.getValuesByName("id")
	          });
        	
        }
</script>

</head>
<body>
<div class="item-tm" id="titleElement">
	    <div class="l-page-title">
			<div class="l-page-title-note">提示：列表数据项
				<font color="red">“红色”</font>代表转正日期距今小于一周。
			</div> 
		</div>
	</div>
       <qm:qm pageid="TJY2_RL_EMPLOYEE_REG">
       <%-- <qm:param name="emp_status" value="(1,2)" compare="in" /> --%>
       </qm:qm>
</body>
</html>