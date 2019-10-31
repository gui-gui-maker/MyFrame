<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%

CurrentSessionUser usee = SecurityUtil.getSecurityUser();
User uu = (User)usee.getSysUser();
com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
e.getId();
String userId=e.getId();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title></title>
		<%@include file="/k/kui-base-list.jsp"%>
		<%CurrentSessionUser user = SecurityUtil.getSecurityUser();%>
		<script type="text/javascript">
			var qmUserConfig={
					sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	// 默认值，可自定义
					sp_fields:[
					           {name:"status",compare:"like"},
					           {name:"duty_name",compare: "like"},
					           {name:'duty_dep',compare: "like"}
					           
					],
					tbar:[{ text: '详情', id: 'detail',icon:'detail',click:detail},
					      	'-' ,
						  	{text: '接收', id: 'receive',icon:'issued',click:receive}
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
								detail:count==1,
								receive:count==1
							});
							if(count > 0){
								var up_status = Qm.getValuesByName("status");
								/* var strs = new Array(); //定义一数组
				 					strs = up_status.split(","); //字符分割\ */
				 				var isUsd=true; //未提交
				 				var isStart=true; //未开始
				 				var isConduct=true; //进行中
								var Unfinished=true; //未完成
								var Complete=true; //已完成
								var Check = true; //待审核
								var NoCheck = true; //审核不通过
								for(var i=0;i<up_status.length;i++){
										/* alert(up_status[i]); */
									if(up_status[i]!="未提交"){
										isUsd = false;
									}if(up_status[i]!="未开始"){
										isStart = false;
									}if(up_status[i]!="进行中"){
										isConduct = false;
									}if(up_status[i]!="未完成"){
										Unfinished = false;
									}if(up_status[i]!="已完成"){
										Complete=false;
									}if(up_status[i]!="待审核"){
										Check = false;
									}if(up_status[i]!="审核不通过"){
										NoCheck=false;
									}
								}
								if (count == 0) {
									Qm.setTbar({
										detail: false,
										receive:false
									});
								} else if (count == 1) {
									Qm.setTbar({
										receive:true&&isStart
									});
								} else {
									Qm.setTbar({
										detail: false,
										receive:false
									});
								}
								
							}
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
				            }else if(rowData.status=='待审核'){
				            	fontColor="#6F00D2";
				            }else if(rowData.status=='审核不通过'){
	                       	 	fontColor="#8E8E8E";
	                        }
				            return "style='color:"+fontColor+"'";
				        }
				}
	};
			function edit(){
				var id = Qm.getValueByName("id");
				top.$.dialog({
					width:900,
					height:500,
					lock:true,
					parent:api,
					data:{
						window:window
					},
					title:"修改",
					content:'url:app/qualitymanage/task_allot_datail.jsp?id=' + id + '&pageStatus=modify'
				});
			}
			
			
			function detail() {
				var id = Qm.getValueByName("id");
				top.$.dialog({
					width: 900,
					height: 600,
					lock: true,
					parent: api,
					title: "详情",
					content: 'url:app/qualitymanage/task_allot_fb_datail.jsp?pageStatus=detail&id='+ id,
					data: {
						window: window
					}
				});
			}
			
			function receive(){
			    var id = Qm.getValueByName("id");
			    $.ligerDialog.confirm('是否接收任务？', function (yes){
			        if(!yes){return false;}
			        top.$.ajax({
			                     url: "taskAllot/allot/getTask.do?id="+id,
			                     type: "GET",
			                     dataType:'json',
			                     async: false,
			                     success:function (data) {
			                        if(data.success){
			                            $.ligerDialog.success("接收成功！");
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
		</script>
	
</head>
	<div class="item-tm" id="titleElement">
        <div class="l-page-title">
            <div class="l-page-title-note">提示：列表数据项
            	<font color="orange">“橙色”</font>代表未开始，
            	<font color="blue">“橙色”</font>代表进行中，
                <font color="green">“绿色”</font>代表已完成，
                <font color="red">“红色”</font>代表未完成，
                <font color="8E8E8E">"灰色"</font>代表审核不通过。
            </div>
        </div>
    </div>
<body>
	<qm:qm pageid="TJY2_ZLGL_RWXF" script="false" singleSelect="false">
		<qm:param name="duty_id" value="<%=userId%>" compare="like" />
	</qm:qm>	

</body>

</html>