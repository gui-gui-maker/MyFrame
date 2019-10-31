<%@page import="org.apache.poi.util.SystemOutLogger"%>
<%@page import="com.lsts.report.bean.SysOrg"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>综合部门个人岗位级别申报列表</title>
	<%@include file="/k/kui-base-list.jsp"%>
	<%@page import="com.khnt.security.util.SecurityUtil"%>
	<%@page import="com.khnt.security.CurrentSessionUser"%>
	<%@page import="com.khnt.rbac.impl.bean.User"%>
	<script type="text/javascript" src="pub/bpm/js/util.js"></script>
	<script type="text/javascript" src="pub/worktask/js/worktask.js"></script>   
	<%
	CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
	User uu = (User)curUser.getSysUser();
	com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
	String uId=e.getId();
	String userId = SecurityUtil.getSecurityUser().getId();
	%>
	<script type="text/javascript">
		var qmUserConfig = {
				sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	// 默认值，可自定义
	            sp_fields:[
					{name:'emp_name',compare:'like'},
	            	{name:'emp_sex',compare:'like'},
	            	{group:[
	            			{name:"emp_birth_date", compare:">=", value:""},
	            			{label:"到", name:"emp_birth_date", compare:"<=", value:"", labelAlign:"center", labelWidth:20}
	            		]},
	            	{name:"work_department_name", compare:"like"},
	            	{name:"position", compare:"like"},
	            	{group:[
	            			{name:"into_work_date", compare:">=", value:""},
	            			{label:"到", name:"into_work_date", compare:"<=", value:"", labelAlign:"center", labelWidth:20}
	            		]},
	            	{name:'emp_education',compare:'like'},
	       			{name:"post_level", compare:"="},
	       			{name:'status',compare:'='}
	       			
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
		            <sec:authorize access="hasAnyRole('TJY2_BMFZR','TJY2_DECLARE')">
		            	,"-",{ text:'处理', id:'deal',icon:'dispose', click: deal }
		            </sec:authorize>
		            <sec:authorize access="hasRole('TJY2_DECLARE_BATH')">
	            		,"-",{ text:'批量处理', id:'bathDeal',icon:'dispose', click: bathDeal }
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
		        					bathDeal: count>1,
		        					progress: count==1
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
		        								bathDeal:false,
		        								progress:false
		        							});
		        						}else if(strs[i] == '已提交' ||strs[i] == '审核中'){
		        							Qm.setTbar({
		        								submit: false,
		        								del:false,
		        								modify:false
		        							});
		        						}else if(strs[i] == '审核通过' ||strs[i] == '审核不通过'){
		        							Qm.setTbar({
		        								modify:false,
		        								del:false,
		        								submit: false,
		        								deal:false,
		        								bathDeal:false
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
		        		    if(rowData.status == '已提交'){
		        				fontColor="blue";
		        		    }else if(rowData.status == '审核中'){
		        				fontColor="orange";
		        		    }else if(rowData.status == '审核通过'){
		        				fontColor="green";
		        		    }else if(rowData.status == '审核不通过'){
		        				fontColor="red";
		        		    }
		        		    return "style='color:"+fontColor+"'";
		        		}
		            }
		};

		function detail(){
			var id = Qm.getValueByName("id").toString();
			top.$.dialog({
				width:800,
				height:630,
				lock:true,
				title:"详情",
				content: 'url:app/humanresources/declare/multiple_declare_detail.jsp?pageStatus=detail&id='+id,
				data:{window:window}
			});
		}
        function add(){
			top.$.dialog({
				width: 800,
				height: 630,
				lock:true,
				title:"新增",
				data: {window:window},
				content: 'url:app/humanresources/declare/multiple_declare_detail.jsp?pageStatus=add'
			});
        }
        function modify(){
			top.$.dialog({
				width: 800,
				height: 630,
				lock:true,
				title:"修改",
				data: {window:window},
				content: 'url:app/humanresources/declare/multiple_declare_detail.jsp?pageStatus=modify&id='+Qm.getValueByName("id").toString(),
			});
        } 
        function del(){
        	var id = Qm.getValueByName("id");
    		var people_id = Qm.getValueByName("people_id");
        	var ids = Qm.getValuesByName("id").toString();
        	$.ligerDialog.confirm('您确定要删除所选数据吗？', function (yes){
            	if(!yes){return false;}
        		top.$.ajax({
                    url: "multiple/declare/delete.do?ids="+ids,
                    type: "POST",
                    dataType:'json',
                    async: false,
                    success:function (data) {
                       if(data.success){
                          top.$.notice("操作成功");
                           Qm.refreshGrid();//刷新
                       }else{
                           $.ligerDialog.warn("操作失败");
                           Qm.refreshGrid();//刷新
                       }
                    },
                    error:function () {
                   	 $.ligerDialog.error("出错了！请重试！!");
                    }
                });
        	});
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
             getServiceFlowConfig("TJY2_MULTIPLE_DECLARE","",function(result,data){
            	 if(result){
                     top.$.ajax({
                         url: "multiple/declare/subFolw.do?id="+id+"&flowId="+data.id+"&typeCode=TJY2_MULTIPLE_DECLARE",
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
        function deal() {
        	var list;
        	var id;
        	var title;
        	var business_id = Qm.getValueByName("id");
        	$.ajax({
            	url: "equipMaintainAction/queryMainId.do?typeCode=TJY2_MULTIPLE_DECLARE&id="+business_id,
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
        function bathDeal() {
        	var ids = Qm.getValuesByName("id");
        	var w = $.ligerDialog.open(
        			{
        			width: 320,
        			type: 'question',
        			title:'批量处理',
        			content: '请选择审核结果',
        			buttons: [{ text: '退回', onclick: function(){
        				btnClick(w,ids,"R");
        			} },{ text: '通过', onclick: function(){
        				btnClick(w,ids,"Y");
        			}}, { text: '不通过', onclick: function(){
        				btnClick(w,ids,"N");
        			} },{ text: '取消', onclick: function(){
        				w.close();}}]
        			});
         }
        function btnClick(w,ids,dealResult){
        	$("body").mask("正在处理，请稍后！");
        	top.$.ajax({
                url: "multiple/declare/bathDeal.do?ids="+ids+"&dealResult="+dealResult,
                type: "GET",
                dataType:'json',
                async: false,
                success:function (data) {
                	if(data.success){
                		$("body").unmask();
                		top.$.notice('操作成功！！！');
                		w.close();
                		Qm.refreshGrid();
                	}else{
                		$("body").unmask();
                		$.ligerDialog.error("操作失败！");
                	}
                },
                error:function () {
                	$("body").unmask();
					$.ligerDialog.error('出错了！请重试！!');
                }
            });
        }
        function progress(){
        	var service_id = Qm.getValueByName("id");
        	trackServiceProcess(service_id);
        }
	</script>
	</head>
<body>
	<div class="item-tm" id="titleElement">
	    <div class="l-page-title">
	        <div class="l-page-title-note">提示：列表数据项
	            <font color="black">“黑色”</font>代表未提交，
	            <font color="blue">“蓝色”</font>代表已提交，
	            <font color="orange">“橙色”</font>代表审核中，
	            <font color="green">"绿色"</font>代表审核通过，
	            <font color="red">“红色”</font>代表审核不通过。
	        </div>
	    </div>
	</div>
	<%StringBuffer sql = new StringBuffer();
		String sql1="";
		String departmentid=SecurityUtil.getSecurityUser().getDepartment().getId();
	%>
	<!-- 普通员工查看列表 -->
    <sec:authorize ifNotGranted="TJY2_BMFZR,TJY2_DECLARE">
    	<%sql1="select * from TJY2_RL_MULTIPLE_DECLARE t where EMP_ID = '" + uId +"' order by t.create_date desc";%>
		<%System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@没有流程处理权限@@@@@@@@@@@@@@@@@@@@@@@@！");
		System.out.println(sql1.toString());
		%>
	</sec:authorize>
	<!-- 部门主任查看列表 -->
	<sec:authorize access="hasAnyRole('TJY2_BMFZR','TJY2_DECLARE')">
		<%sql1="select*from (select b.*,t.handler_id from TJY2_RL_MULTIPLE_DECLARE b, v_pub_worktask t "+
			"where b.id=t.SERVICE_ID   and  t.WORK_TYPE  like   '%TJY2_MULTIPLE_DECLARE%' "+  
			"and t.STATUS='0' ) s where s.handler_id = '"+userId+"' union "+
			"select b.*,EMP_ID from TJY2_RL_MULTIPLE_DECLARE b where EMP_ID = '" + uId +"' union "+
			"select b.*,EMP_ID from TJY2_RL_MULTIPLE_DECLARE b where b.WORK_DEPARTMENT = '" + departmentid +"' and b.STATUS = 'SHTG'";%>
		<%System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@部门负责人@@@@@@@@@@@@@@@@@@@@@@@@！");
		System.out.println(sql1.toString());%>
	</sec:authorize>
	<qm:qm pageid="TJY2_MUL_DECLARE" script="false" singleSelect="false" sql="<%=sql1.toString() %>">
	</qm:qm>
</body>
</html>