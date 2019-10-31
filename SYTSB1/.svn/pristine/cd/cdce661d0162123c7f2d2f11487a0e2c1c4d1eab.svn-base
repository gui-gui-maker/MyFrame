<%@page import="com.khnt.rbac.impl.bean.Org"%>
<%@page import="com.khnt.utils.DateUtil"%>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>通用查询</title>
		<%@ include file="/k/kui-base-list.jsp"%>
		<%@page import="com.khnt.security.util.SecurityUtil"%>
		<%@page import="com.khnt.security.CurrentSessionUser"%>
		<%@page import="com.khnt.rbac.impl.bean.User"%>
		<script type="text/javascript" src="app/oa/select/org_select.js"></script>
		<script type="text/javascript" src="pub/bpm/js/util.js"></script>
		<script type="text/javascript" src="pub/worktask/js/worktask.js"></script>
		<!--获取当前登录人  -->
		<%
			CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
			User uu = (User)curUser.getSysUser();
			com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
			String userId=e.getId();//员工ID
			String uId = SecurityUtil.getSecurityUser().getId();//用户ID
		%>
		<script type="text/javascript">
			var qmUserConfig = {
					sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
					sp_fields:[
			           {name:"use_department",compare:"like",columnWidth:0.33},
			           {name:"car_num",compare: "like",columnWidth:0.33},
			           {group: [
						{name: "senrepair_date", compare: ">="},
						{label: "到", name: "senrepair_date", compare: "<=", labelAlign: "center", labelWidth:20}
				 ],columnWidth:0.33}
			],
				tbar:[
	            	{ text:'详情', id:'detail',icon:'detail', click: detail}
	            	,"-",
	                { text:'增加', id:'add',icon:'add', click: add}
	            	,"-",
	                { text:'修改', id:'modify',icon:'modify', click:modify}
	            	,"-",
	                { text:'删除', id:'del',icon:'delete', click:del}
	            	,"-",
	                { text:'提交', id:'submit',icon:'submit', click:submit}
	            	,"-",
	                { text:'处理', id:'deal',icon:'dispose', click:deal}
	            	,"-",
	                { text:'查看审批进度', id:'progress',icon:'view-progress', click:progress}
	            	,"-",
	                { text:'预览', id:'preview',icon:'preview', click:preview}
	            ],
				listeners : {
					rowClick : function(rowData, rowIndex) {
					},
					rowDblClick : function(rowData, rowIndex) {
						modify(rowData);
					},
					selectionChange : function(rowData,rowIndex){
	                	var count = Qm.getSelectedCount(); // 行选择个数
	        				Qm.setTbar({
	        					detail: count==1,
	        					modify: count==1,
	        					del: count>0,
	        					submit: count==1,
	        					deal: count==1,
	        					progress: count==1,
	        					preview: count==1
	        				});
	        				if(count>0){
	        					//判断按钮可用情况
	        					var status =Qm.getValuesByName("status").toString();
	        					var strs= new Array(); //定义一数组
	        	 				strs=status.split(","); //字符分割
	        	 				//已提交的情况
	        	 				for (i=0;i<count;i++){
	        	 					if(strs[i] == '未提交'){
	        							Qm.setTbar({
	        								deal:false,
	        								progress:false
	        							});
	        						}else if(strs[i] == '审核通过' ||strs[i] == '审核不通过'){
	        							Qm.setTbar({
	        								modify:false,
	        								del:false,
	        								submit: false,
	        								deal:false,
	        							});
	        						}else{
	        							Qm.setTbar({
	        								modify:false,
	        								del:false,
	        								submit: false
	        							});
	        						}
	        					}
	        				}
	                },
					rowAttrRender : function(rowData, rowid) {
		                var fontColor="black";
		                if (rowData.status=='审核通过'){
		                    fontColor="green";
		                }else if(rowData.status=='审核不通过') {
		                    fontColor="red";
		                }else if(rowData.status=='部门负责人待审核' || rowData.status=='车队负责人待审核' || rowData.status=='管理部门待审核') {
		                    fontColor="orange";
		                }
		                return "style='color:"+fontColor+"'";
		            }
					}
				};
	
				function formatDate(strDate){
					if(strDate==null||strDate=="")return ;
					var strDateArr=strDate.split(" ");
					var dates=strDateArr[0].split("-");

					var newDates=dates[1]+"/"+dates[2]+"/"+dates[0];
		  
					var sysdates=new Date(newDates);
		  
					return sysdates;
				}
				function addDates (sysDates,interval, value) {
		
					var d = sysDates.clone();
					if (!interval || value === 0) return d;

					switch(interval.toLowerCase()) {
						case Date.MILLI:
							d.setMilliseconds(sysDates.getMilliseconds() + value);
							break;
						case Date.SECOND:
							d.setSeconds(sysDates.getSeconds() + value);
							break;
						case Date.MINUTE:
							d.setMinutes(sysDates.getMinutes() + value);
							break;
						case Date.HOUR:
							d.setHours(sysDates.getHours() + value);
							break;
						case Date.DAY:
							d.setDate(sysDates.getDate() + value);
							break;
						case Date.MONTH:
							var day = sysDates.getDate();
							if (day > 28) {
								day = Math.min(day, sysDates.getFirstDateOfMonth().add('mo', value).getLastDateOfMonth().getDate());
							}
							d.setDate(day);
							d.setMonth(sysDates.getMonth() + value);
							break;
						case Date.YEAR:
							d.setFullYear(sysDates.getFullYear() + value);
							break;
					}
					return d;
				}
				//显示部门选择列表
				function showDepartList(){
					var unitId = "<sec:authentication property="principal.unit.id" />";
					var unitName = "<sec:authentication property="principal.unit.orgName" htmlEscape="false"/>";
					$(this).data('unitId',unitId);
					$(this).data('unitName',unitName);
					showOrgList.call(this);
				}

				function modify(item) {
					var selectedId = Qm.getValueByName("id");
					var width = 700;
					var height = 550;
					var windows = top.$.dialog({
						width : width,
						height : height,
						lock : true,
						title : "修改",
						data : {
							"window" : window
						},
						content : 'url:app/oa/car/repair_note_detail.jsp?pageStatus=modify'
							+ '&id=' + selectedId
					});
				}
				function add() {
					var width = 700;
					var height = 550;
					var windows = top.$.dialog({
						width : width,
						height : height,
						lock : true,
						title : "新增",
						data : {
							"window" : window
						},
						content : 'url:app/oa/car/repair_note_detail.jsp?pageStatus=add'
			
					});
				}

				//删除
		        function del(){
		            $.del("确定要删除？删除后无法恢复！","CarrepairNoteAction/delete.do",{"ids":Qm.getValuesByName("id").toString()});
		        }

				function detail() {
					var selectedId = Qm.getValueByName("id");
					var width = 700;
					var height = 550;
					var windows = top.$.dialog({
						width : width,
						height : height,
						lock : true,
						title : "详情",
						data : {
							"window" : window
						},
						content : 'url:app/oa/car/repair_note_detail.jsp?pageStatus=detail'
							+ '&id=' + selectedId
					});
				}
				function preview(){
					top.$.dialog({
						width : $(top).width(),
						height :  $(top).height()-40,
						lock : true,
						title : "预览",
						parent: api,
						content : 'url:app/oa/car/docEditor.jsp?pageStatus=detail&op_type=preview',	
						data : {
							"window" : window,
							"id":Qm.getValuesByName("id").toString(),
							"use_department":Qm.getValuesByName("use_department").toString(),
							"car_num":Qm.getValueByName("car_num").toString(),
							"car_logo":Qm.getValuesByName("car_logo").toString(),
							"engine_no":Qm.getValueByName("engine_no").toString(),
							"car_code":Qm.getValuesByName("car_code").toString(),
							"senrepair_name":Qm.getValueByName("senrepair_name").toString(),
							"senrepair_date":Qm.getValueByName("senrepair_date").toString(),
							"repair_item":Qm.getValuesByName("repair_item").toString(),
							"use_department_manager_opinion" : Qm.getValuesByName("use_department_manager_opinion").toString(),
							"fleet_manager_opinion" : Qm.getValuesByName("fleet_manager_opinion").toString(),
							"department_manager_opinion" : Qm.getValuesByName("department_manager_opinion").toString(),
							"type":Qm.getValueByName("type").toString(),
						}
					}).max();
				}
				function submit(){
		    		var id = Qm.getValueByName("id");
		            if(!id){
		           	 	top.$.notice('请先选择要提交审核的数据！',3);
		                return;
		            }
		            $.ligerDialog.confirm('是否提交审核？', function (yes){
		            if(!yes){return false; }
		             $("body").mask("正在提交，请稍后！");
		             getServiceFlowConfig("carrepair_flow","",function(result,data){
		            	 if(result){
		                     top.$.ajax({
		                         url: "CarrepairNoteAction/subFolw.do?id="+id+"&flowId="+data.id+"&typeCode=carrepair_flow",
		                         type: "GET",
		                         dataType:'json',
		                         async: false,
		                         success:function (data) {
		                             if (data) {
		                            	$("body").unmask();
		                           	 	top.$.notice('提交成功！！！');
		                                Qm.refreshGrid();
		                             }
		                         },
		                         error:function () {
		                        	 $("body").unmask();
		                        	 $.ligerDialog.error('出错了！请重试！!');
		                         }
		                     });
		                }else{
		                	$.ligerDialog.error('出错了！请重试！',3);
		                 $("body").unmask();
		                }
		             });
		            });
		    	}
				function deal1() {
			    	var list;
			    	var id;
			    	var title;
			    	var business_id = Qm.getValueByName("id");
			    	$.ajax({
			        	url: "equipMaintainAction/queryMainId.do?typeCode=carrepair_flow&id="+business_id,
			            type: "POST",
			            success: function (resp) {
			            	if(resp.success){
			            		list = resp.list;
			            		id=list[0].id;
			        	        title=list[0].title;
			            		var config={
			                	        width :800,
			                	        height : 630,
			                	        id:id,
			                	        title:title
			                	    }
			            		// 调用流程的方法
			               	    openWorktask(config);
			            	}else{
			            		$.ligerDialog.error('没有流程数据！');
			            	}
			            },
			            error: function (data,stats) {
			                $.ligerDialog.error('提示：' + data.msg);
			            }
			        });
			     }
				function deal(){
					top.$.dialog({
						width : $(top).width(),
						height :  $(top).height()-40,
						lock : true,
						title : "处理",
						parent: api,
						content : 'url:app/oa/car/docEditor.jsp?pageStatus=detail&op_type=deal',	
						data : {
							"window" : window,
							"id":Qm.getValuesByName("id").toString(),
							"use_department":Qm.getValuesByName("use_department").toString(),
							"car_num":Qm.getValueByName("car_num").toString(),
							"car_logo":Qm.getValuesByName("car_logo").toString(),
							"engine_no":Qm.getValueByName("engine_no").toString(),
							"car_code":Qm.getValuesByName("car_code").toString(),
							"senrepair_name":Qm.getValueByName("senrepair_name").toString(),
							"senrepair_date":Qm.getValueByName("senrepair_date").toString(),
							"repair_item":Qm.getValuesByName("repair_item").toString(),
							"use_department_manager_opinion" : Qm.getValuesByName("use_department_manager_opinion").toString(),
							"fleet_manager_opinion" : Qm.getValuesByName("fleet_manager_opinion").toString(),
							"department_manager_opinion" : Qm.getValuesByName("department_manager_opinion").toString(),
							"type":Qm.getValueByName("type").toString(),
						}
					}).max();
				} 
				function progress(){
		        	var service_id = Qm.getValueByName("id");
		        	trackServiceProcess(service_id);
		        }
		</script>
	</head>
	<%
		String unitId = SecurityUtil.getSecurityUser().getUnit().getId();
		Org org = (Org)SecurityUtil.getSecurityUser().getDepartment();
	%>
	<body>
	<div class="item-tm" id="titleElement">
        <div class="l-page-title">
            <div class="l-page-title-note">提示：列表数据项
                <font color="black">“黑色”</font>代表未提交，
                <font color="orange">“橙色”</font>代表待审核，
                <font color="green">“绿色”</font>代表审核通过，
                <font color="red">“红色”</font>代表审核不通过。
                
            </div>
        </div>
    </div>
    <qm:qm pageid="TJY2_CARREPAIR_NOTE" script="false" singleSelect="false">
    </qm:qm>
	</body>
</html>