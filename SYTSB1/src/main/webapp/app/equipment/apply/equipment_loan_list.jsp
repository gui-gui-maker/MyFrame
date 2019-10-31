<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>设备借用/领用申请</title>
	<%@include file="/k/kui-base-list.jsp"%>
	<%@page import="com.khnt.security.util.SecurityUtil"%>
	<%@page import="com.khnt.security.CurrentSessionUser"%>
	<%@page import="com.khnt.rbac.impl.bean.User"%>
	<script type="text/javascript" src="pub/bpm/js/util.js"></script>
	<script type="text/javascript" src="pub/worktask/js/worktask.js"></script>  
	<%CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
	User uu = (User)curUser.getSysUser();
	com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
	e.getId();
	String userId=e.getId();
	String uId = SecurityUtil.getSecurityUser().getId();
	%>
	<script type="text/javascript">
		var qmUserConfig = {
				sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	// 默认值，可自定义
	            sp_fields:[
						   {name:'loan_type',compare:'like'},
						   {name:'AUDIT_STATUS',compare:'like'},
	                       {name:'loan_name',compare:'like'}
	            ],
				tbar:[
		            	{ text:'详情', id:'detail',icon:'detail', click: detail},
		                "-",
		                { text:'增加', id:'add',icon:'add', click: add},
		                "-",
		                { text:'修改', id:'modify',icon:'modify', click:modify},
		                "-",
		                { text:'删除', id:'del',icon:'delete', click:del},
		                "-",
		                { text:'提交', id:'submit',icon:'submit',click:submit}
		                <sec:authorize access="hasRole('TJY2_EQ_MANAGER')">
		                ,"-",
		                { text:'处理', id:'deal',icon:'dispose', click: deal}
		                ,"-",
		                { text:'打印', id:'print',icon:'print', click: print}
		                </sec:authorize> 
		                ,"-",
		                { text:'查看审批进度', id:'progress',icon:'view-progress', click:progress}
		            ],
		            listeners: {
		                selectionChange : function(rowData,rowIndex){
		                	var count = Qm.getSelectedCount(); // 行选择个数
		        				Qm.setTbar({
		        					detail: count==1,
		        					modify: count==1,
		        					del: count>0,
		        					submit: count==1,
		        					deal: count==1,
		        					progress: count==1,
		        					print: count==1
		        				});
		        				if(count>0){
		        					//判断按钮可用情况
		        					var status =Qm.getValuesByName("AUDIT_STATUS").toString();
		        					var strs= new Array(); //定义一数组
		        	 				strs=status.split(","); //字符分割
		        	 				//已提交的情况
		        	 				for (i=0;i<count;i++){
		        						if(strs[i] == '已提交'){
		        							Qm.setTbar({
		        								submit: false,
		        								del:false,
		        								modify:true,
		        								deal: true,
		    		        					progress: true,
		    		        					print:false
		        							});
		        						}else if(strs[i] == '未提交'){
		        							Qm.setTbar({
		        								submit: true,
		        								del:true,
		        								modify:true,
		        								deal: false,
		    		        					progress: false,
		    		        					print:false
		        							});
		        						}else if(strs[i] == '不通过'){
		        							Qm.setTbar({
		        								submit: false,
		        								del:true,
		        								modify:false,
		        								deal: false,
		    		        					progress: true,
		    		        					print:false
		        							});
		        						}else if(strs[i] == '通过' || strs[i] == '已领用' || strs[i] == '已借用'){
		        							Qm.setTbar({
		        								submit: false,
		        								del:false,
		        								modify:false,
		        								deal: false,
		    		        					progress: true,
		    		        					print:true
		        							});
		        						}
		        					}
		        				}
		                },
		        		rowDblClick :function(rowData,rowIndex){
		        			detail(rowData.id);
		        		},
		        		rowAttrRender : function(rowData, rowid) {
		        		    var fontColor="black";
		        		    if(rowData.AUDIT_STATUS == '已提交'){
		        				fontColor="blue";
		        		    }else if(rowData.AUDIT_STATUS == '通过'){
		        				fontColor="green";
		        		    }else if(rowData.AUDIT_STATUS == '已领用' || rowData.AUDIT_STATUS == '已借用'){
		        				fontColor="#6F00D2";
		        		    }else if(rowData.AUDIT_STATUS == '不通过'){
		        				fontColor="red";
		        		    }
		        		    return "style='color:"+fontColor+"'";
		        		}
		            }
		};

		function detail(){
			var id = Qm.getValueByName("id").toString();
			top.$.dialog({
				width:900,
				height:630,
				lock:true,
				title:"详情",
				content: 'url:app/equipment/apply/equipment_loan_detail.jsp?pageStatus=detail&id=' + id,
				data:{window:window},
				cancel:true
			});
		}
        
        //新增
        function add(){
			top.$.dialog({
				width: 900,
				height: 630,
				lock:true,
				title:"新增",
				data: {window:window},
				content: 'url:app/equipment/apply/equipment_loan_detail.jsp?pageStatus=add'
			});
        }
        
        //修改
        function modify(){
        	var register_id = Qm.getValueByName("register_id");
        	if(register_id!="<%=uId%>"){
  	          $.ligerDialog.alert("只能修改自己的申请！");
  	          return;
  	        }
			top.$.dialog({
				width: 900,
				height: 630,
				lock:true,
				title:"修改",
				data: {window:window},
				content: 'url:app/equipment/apply/equipment_loan_detail.jsp?pageStatus=modify&id='+Qm.getValueByName("id")
			});
        } 
        
        //删除
        function del(){
        	var ids = Qm.getValuesByName("id").toString();
        	var register_id = Qm.getValueByName("register_id");
        	if(register_id!="<%=uId%>"){
  	          $.ligerDialog.alert("只能删除自己的申请！");
  	          return;
  	        }
        	$.ligerDialog.confirm('您确定要删除所选数据吗？', function (yes){
            	if(!yes){return false;}
        		top.$.ajax({
                    url: "equipment/Loan/delete.do?ids="+ids,
                    type: "POST",
                    dataType:'json',
                    async: false,
                    success:function (data) {
                       if(data.success){
                          top.$.notice(data.msg,3);
                           Qm.refreshGrid();//刷新
                       }else{
                           $.ligerDialog.warn(data.msg);
                           Qm.refreshGrid();//刷新
                       }
                    },
                    error:function () {
                        //$.ligerDialog.warn("提交失败！");
                   	 $.ligerDialog.error("出错了！请重试！!");
                    }
                });
        	});
        	/* $.del("确定要删除？删除后无法恢复！","equipment/Loan/delete.do",{"ids":Qm.getValuesByName("id").toString()}); */
        }  
        
        function submit(){
    		var id = Qm.getValueByName("id");
            if(!id){
               // $.ligerDialog.alert("请先选择要提交审核的数据！");
           	 	top.$.notice('请先选择要提交审核的数据！',3);
                return;
            }
            var register_id = Qm.getValueByName("register_id");
        	if(register_id!="<%=uId%>"){
  	          $.ligerDialog.alert("只能提交自己的申请！");
  	          return;
  	        }
            $.ligerDialog.confirm('是否提交审核？', function (yes){
            if(!yes){return false; }
             $("body").mask("正在提交，请稍后！");
             getServiceFlowConfig("TJY2_EQ_APPLY","",function(result,data){
            	 if(result){
                     top.$.ajax({
                         url: "equipment/Loan/subFolw.do?id="+id+"&flowId="+data.id+"&typeCode=TJY2_EQ_APPLY",
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
                        	 $.ligerDialog.error('请出错了！请重试！!');
                         }
                     });
                }else{
                	$.ligerDialog.error('出错了！请重试！',3);
                 $("body").unmask();
                }
             });
            });
    	}
        
      	//处理
        function deal() {
        	var list;
        	var id;
        	var title;
        	var service_id = Qm.getValueByName("id");
        	$.ajax({
            	url: "equipMaintainAction/queryMainId.do?id="+service_id,
                type: "POST",
                success: function (resp) {
                	if(resp.success){
                		list = resp.list;
                		id=list[0].id;
            	        title=list[0].title;
            	        //alert(title);
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
        function print(){
			var id = Qm.getValueByName("id");
			top.$.dialog({
				width:900,
				height:630,
				lock:true,
				title:"打印设备借用登记表",
				content: 'url:app/equipment/apply/equipment_borrow_print.jsp?status=detail&id=' + id,
				data:{window:window}
			});
		}
        function progress(){
        	var service_id = Qm.getValueByName("id");
        	trackServiceProcess(service_id);
        }
	</script>
<div class="item-tm" id="titleElement">
        <div class="l-page-title">
            <div class="l-page-title-note">提示：列表数据项
                <font color="black">“黑色”</font>代表未提交，
                <font color="blue">“蓝色”</font>代表已提交，
                <font color="green">"绿色"</font>代表审核通过，
                <font color="red">“红色”</font>代表审核不通过，
                <font color="#6F00D2">“紫色”</font>代表审核通过并且已领用/借用。
            </div>
        </div>
    </div>

<body>
	<%-- <qm:qm pageid="TJY2_EQUIPMENT_LOAN" singleSelect="true"></qm:qm> --%>
	<!-- 没有流程处理权限 -->
    <%StringBuffer sql = new StringBuffer();
		String sql1="";
		String departmentid=SecurityUtil.getSecurityUser().getDepartment().getId();
	%>
    <sec:authorize access="!hasRole('TJY2_EQ_MANAGER')">
    	<%sql1="select t.* from tjy2_equipment_loan t where loan_id = '" + userId +"'"+"order by t.register_name,t.AUDIT_STATUS,t.register_time desc";%>
		<%System.out.print("@@@@@@@@@@@@@@@@@@@@@@@@@@@@没有流程处理权限@@@@@@@@@@@@@@@@@@@@@@@@！");%>
	</sec:authorize>
	<sec:authorize access="hasRole('TJY2_EQ_MANAGER')">
    	<%sql1="select t.* from tjy2_equipment_loan t order by t.register_name,t.AUDIT_STATUS,t.register_time desc";%>
		<%System.out.print("@@@@@@@@@@@@@@@@@@@@@@@@@@@@有流程处理权限@@@@@@@@@@@@@@@@@@@@@@@@！");%>
	</sec:authorize>
	<qm:qm pageid="TJY2_EQUIPMENT_LOAN" script="false" singleSelect="true" sql="<%=sql1.toString() %>">
	</qm:qm>
</body>
</html>