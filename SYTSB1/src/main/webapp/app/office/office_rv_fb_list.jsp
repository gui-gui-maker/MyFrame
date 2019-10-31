<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<%
CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
User uu = (User)curUser.getSysUser();
com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
e.getId();

String userId=e.getId();
String uId = SecurityUtil.getSecurityUser().getId();
CurrentSessionUser useres = SecurityUtil.getSecurityUser();
%>
<script type="text/javascript">
	var qmUserConfig={	
			sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	// 默认值，可自定义
			sp_fields:[
			           {name:"status",compare:"like"},
			           {name:"main_dep",compare: "like"},
			           {name:"main_lead_name",compare: "like"},
						{name:"main_duty_name",compare: "like"},
			            {group:[
			                    
			            	{name:"start_time",compare:">="},
			            	{label:"到",name:"start_time",compare:"<=",labelAlign: "center", labelWidth:20}
			            ]},
			            {group:[
			                    
				            	{name:"finish_time",compare:">="},
				            	{label:"到",name:"finish_time",compare:"<=",labelAlign: "center", labelWidth:20}
				            ]}
					   
					   
			],
			tbar:[{text: '详情', id: 'detail', icon: 'detail', click: detail} ,
			     	'-',
			      {text: '接收', id: 'receive' ,icon: 'give-back', click:receive },
			     	'-',
			      {text: '反馈', id: 'feedback' , icon: 'feedback', click:feedback}
			    ],
			    listeners:{
					rowClick:function(rowData,rowIndex){
					},
					rowDblClick:function(rowDate,rowIndex){
						detail();
					},
					selectionChange:function(rowDate,rowIndex){
						var count = Qm.getSelectedCount();
						Qm.setTbar({
							detail:count==1
						});
				
					},
					selectionChange: function(rowData, rowIndex) {
	
						var count = Qm.getSelectedCount();
					
						var up_status = Qm.getValueByName("status");
						var status={};
						if(count==0){
	     					status={detail:false, receive:false,feedback:false};
	     				}else if(count==1){
	     					if("未开始"==up_status ){
	     						status={detail:true, receive:true,feedback:false};
	   						}else if("进行中"==up_status){
	   							status={detail:true, receive:false,feedback:true};
	   						} else if("已完成"==up_status){
	   							status={detail:true, receive:false,feedback:false};
	   						}else if("未完成"==up_status){
	     						status={detail:true, receive:false,feedback:false};
	   						}else{
	   							status={detail:true, receive:false,feedback:false};
	 
	   						}		
	     					
	     				}
	     				Qm.setTbar(status);

				 },
					rowAttrRender : function(rowData, rowid) {
			            var fontColor="black";
			            if (rowData.status=='已完成'){
			                fontColor="green";
			            }else if(rowData.status=='未完成') {
			                fontColor="red";
			            }else if(rowData.status=='未开始') {
			                fontColor="orange";
			            }else if(rowData.status=='进行中'){
			            	fontColor="blue";
			            }
			            return "style='color:"+fontColor+"'";
			        }
			}
	
				
	};
	
	function detail() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 900,
			height: 400,
			lock: true,
			parent: api,
			title: "详情",
			content: 'url:app/office/office_fb_datali1.jsp?pageStatus=detail&id='+ id,
			data: {window: window}
		});
	}
	
	function receive(){
	    var id = Qm.getValueByName("id");
	    $.ligerDialog.confirm('是否收接任务？', function (yes){
	        if(!yes){return false;}
	        top.$.ajax({
	                     url: "weighty/Task/pcReceive.do?id="+id,
	                     type: "GET",
	                     dataType:'json',
	                     async: false,
	                     success:function (data) {
	                        if(data.success){
	                        	top.$.notice('接收成功！！！',3);
	                            Qm.refreshGrid();//刷新
	                        }else{
	                            $.ligerDialog.warn(data.msg);
	                        }
	                     },
	                     error:function () {
	                         $.ligerDialog.warn("接收失败！");
	                     }
	                 });
	    });
	}		

	
	function feedback(){
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 805,
			height: 400,
			lock: true,
			data: {
				window: window
			},
			title: "反馈任务",
			content: 'url:app/office/office_rv_fb_datail.jsp?pageStatus=modify&id='+ id
		});
	}
	
	function refreshGrid() {
	    Qm.refreshGrid();
	}
	
</script>




</head>
<body>
	<div class="item-tm" id="titleElement">
        <div class="l-page-title">
            <div class="l-page-title-note">提示：列表数据项
                <font color="green">“绿色”</font>代表已完成，
                <font color="orange">“橙色”</font>代表未开始，
                <font color="red">“红色”</font>代表未完成，
                <font color="blue">“蓝色”</font>代表进行中。
            </div>
        </div>
    </div>


	<qm:qm pageid="TJY2_BG_JSFK" script="false" singleSelect="true">
		<qm:param name="main_duty_id" value="<%=userId%>" compare="like" />
	</qm:qm>
</body>
</html>