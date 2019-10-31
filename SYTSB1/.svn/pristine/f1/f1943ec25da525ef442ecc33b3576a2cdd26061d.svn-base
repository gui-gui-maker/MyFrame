<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/worktask/js/worktask.js"></script>   
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<%
CurrentSessionUser useres = SecurityUtil.getSecurityUser();
User uu = (User)useres.getSysUser();
com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
e.getId();
String userId=e.getId();
String uId = SecurityUtil.getSecurityUser().getId();
%>
<title></title>


<script type="text/javascript">
var qmUserConfig={
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
		sp_fields:[
	           {name:"status",compare: "like"},
	           {name:"train_chart_number",compare:"like"},
	           {name:"tratn_type",compare:"like"},
	           {name:"tratn_file_num",compare:"like"},
	           {name:"apply_name",compare:"like"},
	           {group: [
				{name: "apply_time", compare: ">="},
				{label: "到", name: "apply_time", compare: "<=", labelAlign: "center", labelWidth:20}
		 ]}
	],
	
			tbar:[{
				text: '详情', id: 'detail', icon: 'detail', click: detail
			}, "-", {
				text: '新增', id: 'add', icon: 'add', click: add 
			}, "-", {
				text: '修改', id: 'edit', icon: 'modify', click:edit
			}, "-", {
				text: '删除', id: 'del', icon: 'delete', click:del
			}, "-", {
				text: '提交', id: 'submit', icon: 'submit', click:submit
		    }<sec:authorize access="hasRole('TJY2_CL_LCCL')">
		    , "-", {
		        text:'处理', id:'sh',icon:'dispose', click: shenhe
		    }</sec:authorize>
		    , "-",{ 
		    	text:'流转过程', id:'turnHistory',icon:'follow', click: turnHistory}],
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
						edit:count==1,
						del:count>0,
						submit:count>0
					});
					var count = Qm.getSelectedCount();//行选择个数
					//alert(count);
					var up_status = Qm.getValueByName("status");
					//alert(up_status);
					var sp_status = Qm.getValueByName("sp_status");
					var NEXT_STATUS = Qm.getValueByName("NEXT_STATUS");
					//alert(sp_status);
					var status={};
					if(count==0){
						status={detail:false, edit:false, del:false,submit:false,turnHistory:false,sh: false,deal: false};
					}else if(count==1){
						if("已提交"==up_status){
							if("undefined"==sp_status){
								status={detail:true, edit:false, del:true,submit:false,turnHistory:true,sh: true,deal: true};
							}else{
								status={detail:true, edit:false, del:false,submit:false,turnHistory:true,sh: true,deal: true};
							}
						}else if("审核通过"==up_status){
							status={detail:true, edit:false, del:false,submit:false,turnHistory:true,sh: false,deal: true};

						}else if("审核未通过"==up_status){
							if("undefined"==sp_status){
								status={detail:true, edit:false, del:false,submit:false,turnHistory:true,sh: false,deal: true};
							}else{
								status={detail:true, edit:false, del:true,submit:false,turnHistory:true,sh: false,deal: true};
							}
						}else if("审核中"==up_status){
							if("undefined"==sp_status){
								status={detail:true, edit:false, del:false,submit:false,turnHistory:true,sh: true,deal: true};
							}else{
								status={detail:true, edit:false, del:false,submit:false,turnHistory:true,sh: true,deal: true};
							}
						} else{
							status={detail:true, edit:true, del:true,submit:true,turnHistory:false,sh: false,deal: false};
						}
					}else{
						if("已提交1"==sp_status){
							status={detail:false, edit:false, del:true,submit:false,turnHistory:false,sh: false,deal: true};
						}else{
							status={detail:false, edit:false, del:false,submit:false,turnHistory:false,sh: false,deal: true};
						}
					}
					Qm.setTbar(status);
// 					if(count > 0){
// 						var up_status = Qm.getValuesByName("status");
// 						/* var strs = new Array(); //定义一数组
// 		 					strs = up_status.split(","); //字符分割\ */
// 		 				var isUsd=true; //未提交
// 		 				var isStart=true; //已提交
// 						var isCheck = true; //审核中
// 						var isCheckPass=true;
// 						var isNoCheck = true; //审核不通过
// 						for(var i=0;i<up_status.length;i++){
// 								/* alert(up_status[i]); */
// 							if(up_status[i]!="未提交"){
// 								isUsd = false;
// 							}if(up_status[i]!="已提交"){
// 								isStart = false;
// 							}if(up_status[i]!="审核中"){
// 								isCheck = false;
// 							}if(up_status[i]!="审核通过"){
// 								isCheckPass=true
// 							}if(up_status[i]!="审核未通过"){
// 								isNoCheck=false;
// 							}
// 						}
// 						if(count == 0 ){
// 							Qm.setTbar({
// 								detail:false,
// 								edit:false,
// 								del:false,
// 								submit:false
// 							});
// 						}else if(count == 1){
// 							Qm.setTbar({
// 								detail:true,
// 								edit:true&&isUsd,
// 								del:true&&isUsd,
// 								submit:true&&isUsd
// 							});
// 						}else{
// 							Qm.setTbar({
// 								detail:false,
// 								edit:false,
// 								del:false,
// 								submit:true&&isStart
// 							});
// 						}
// 					}
				},rowAttrRender : function(rowData, rowid) {
		            var fontColor="black";
		            if (rowData.status=='已提交'){
		                fontColor="blue";
		            }else if(rowData.status=='审核中'){
		            	fontColor="orange";
		            }else if(rowData.status=='审核通过'){
		            	fontColor="green";
		            }else if(rowData.status=='审核未通过'){
		            	fontColor="red";
		            }
		            return "style='color:"+fontColor+"'";
		        }
			}
		
};

function shenhe(){//处理
	var id = Qm.getValueByName("id");
	top.$.ajax({
        url: "quality/staff/train/getLcid.do?id="+id,
        type: "GET",
        dataType:'json',
        async: false,
        success:function (data) {
            if (data) {
            	var ids=data.ids;
            	//var tempArr = ids.split(",");
            	 var config={
                         width :900,
                         height : 500,
                         id:ids,
                         title:"四川省特种设备检验研究院职工培训审批表"
                     }
                     //调用流程的方法
                     openWorktask(config);
               Qm.refreshGrid();
            }
        },
        error:function () {
            $.ligerDialog.error("出错了！请重试！!");
        }
    });
	
}
function look(){//查看审批进度
	var id = Qm.getValueByName("id");trackServiceProcess(id);
// 	top.$.ajax({
//         url: "quality/staff/train/getprocess_id.do?id="+id,
//         type: "GET",
//         dataType:'json',
//         async: false,
//         success:function (data) {
//             if (data) {
//             	var process_id=data.process_id;
//             	if(process_id==""){
//             		$.ligerDialog.error("提交流程后才可以查看！！");
//             	}else{
//                   trackProcess(process_id);
//                    Qm.refreshGrid();
//             	}
//             }
//         },
//         error:function () {
//             $.ligerDialog.error("出错了！请重试！!");
//         }
//     });
}
	//流转过程
	function turnHistory(){	
		top.$.dialog({
				width : 400, 
				height : 700, 
				lock : true, 
				title: "流程卡",
				content: 'url:quality/staff/train/getFlowStep.do?id='+Qm.getValueByName("id"),
				data : {"window" : window}
			});
	}
	function detail() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 900,
			height: 700,
			lock: true,
			parent: api,
			title: "任务详情",
			content: 'url:app/qualitymanage/quality_staff_train_detail.jsp?pageStatus=detail&id='+ id,
			data: {
				window: window
			}
		});
	}
	
	function add(){
		top.$.dialog({
			width: 900,
			height: 700,
			lock: true,
			parent: api,
			title: "新增",
			content: 'url:app/qualitymanage/quality_staff_train_detail.jsp?pageStatus=add',
			data: {
				window: window
			}
		});
	}
	
	function edit(){
		var id=Qm.getValueByName("id");
		top.$.dialog({
			width:900,
			height:700,
			lock:true,
			parent:api,
			title:"修改",
			content:'url:app/qualitymanage/quality_staff_train_detail.jsp?pageStatus=modify&id='+id,
			data: {
				window: window
			}
		});
	}
	
	function del(){
		var id = Qm.getValueByName("id");
		$.ligerDialog.confirm('您确定要删除所选数据吗？', function (yes){
	    	if(!yes){return false;}
			top.$.ajax({
	            url: "quality/staff/train/delete.do?ids="+id,
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
		/* $.del(kFrameConfig.DEL_MSG, "quality/staff/train/delete.do", {
			"ids": Qm.getValuesByName("id").toString()
		}); */
	}
		
	function submit(){
		var id = Qm.getValueByName("id");
        if(!id){
           // $.ligerDialog.alert("请先选择要提交审核的数据！");
       	 	top.$.notice('请先选择要提交审核的数据！',3);
            return;
        }
        $.ligerDialog.confirm('是否提交审核？', function (yes){
        if(!yes){return false; }
         $("body").mask("提交中...");
         getServiceFlowConfig("TJY2_ZL_ZGPX","",function(result,data){
        	 if(result){
                 top.$.ajax({
                     url: "quality/staff/train/subFolws.do?id="+id+"&flowId="+data.id+"&typeCode=TJY2_ZL_ZGPX",
                     type: "GET",
                     dataType:'json',
                     async: false,
                     success:function (data) {
                         if (data) {
                        	$("body").unmask();
                       	 	top.$.notice('提交成功！！！',3);
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
		
</script>


</head>
<body>
	<div class="item-tm" id="titleElement">
        <div class="l-page-title">
            <div class="l-page-title-note">提示：列表数据项
                <font color="black">“黑色”</font>代表未提交，
                <font color="blue">"灰色"</font>代表已提交，
                <font color="green">“绿色”</font>代表审核通过，
                <font color="orange">“橙色”</font>代表审核中，
                <font color="red">“红色”</font>代表审核未通过。
                
            </div>
        </div>
    </div>
    <%String lists=""; %>
    <%StringBuffer sql = new StringBuffer(); %>
    <%String sql1 = ""; %>
    <%String departmentId= useres.getDepartment().getId();%>
    
    <sec:authorize access="!hasRole('TJY2_ZL_GLY')">
		<%sql1="select t.*,t.registrant_id handler_id from TJY2_QUALITY_STAFF_TRAIN t where t.registrant_id='"+ uId +"' order by t.TRAIN_CHART_NUMBER desc Nulls last "; %>
		<%System.out.println("=========我看我自己！！"); %>
	</sec:authorize>
    
    <sec:authorize access="hasRole('TJY2_BMFZR')">
		<%sql1="select * from (select b.*,t.handler_id from TJY2_QUALITY_STAFF_TRAIN b, v_pub_worktask t "+
		       "where b.id=t.SERVICE_ID "+   
		       "and  t.WORK_TYPE like '%TJY2_ZL_ZGPX%' and t.STATUS='0' and t.handler_id = '"+uId+"' union "+ 
			   "select b.*,b.registrant_id handler_id from TJY2_QUALITY_STAFF_TRAIN b "+  
		  	   "where b.status in('PASS','NO_PASS') and b.USER_DEP_ID= '" + departmentId +"' union select b.*,registrant_id from TJY2_QUALITY_STAFF_TRAIN b "+   
		  	   "where registrant_id = '" + uId +"' ) order by TRAIN_CHART_NUMBER desc"; %>
		<%System.out.print("=========我部门负责人！！"+departmentId); %>
	</sec:authorize>
	
	 <sec:authorize access="hasRole('TJY2_ZL_KYJS')">
		<%sql1="select * from (select b.*,t.handler_id from TJY2_QUALITY_STAFF_TRAIN b, v_pub_worktask t "+
		       "where b.id=t.SERVICE_ID "+   
		       "and  t.WORK_TYPE like '%TJY2_ZL_ZGPX%' and t.STATUS='0' and t.handler_id = '"+uId+"' union "+ 
			   "select b.*,b.registrant_id handler_id from TJY2_QUALITY_STAFF_TRAIN b "+  
		  	   "where b.status in('PASS','NO_PASS') union select b.*,registrant_id from TJY2_QUALITY_STAFF_TRAIN b "+   
		  	   "where registrant_id = '" + uId +"' ) order by TRAIN_CHART_NUMBER desc"; %>
		<%System.out.print("=========我科研部门负责人！！"); %>
	</sec:authorize>
	
	<sec:authorize access="hasRole('TJY2_ZL_FGYLDSH')">
		<%sql1="select * from (select b.*,t.handler_id from TJY2_QUALITY_STAFF_TRAIN b, v_pub_worktask t "+
		       "where b.id=t.SERVICE_ID "+   
		       "and  t.WORK_TYPE like '%TJY2_ZL_ZGPX%' and t.STATUS='0' and t.handler_id = '"+uId+"' union "+ 
			   "select b.*,b.registrant_id handler_id from TJY2_QUALITY_STAFF_TRAIN b "+  
		  	   "where b.status in('PASS','NO_PASS') union select b.*,registrant_id from TJY2_QUALITY_STAFF_TRAIN b "+   
		  	   "where registrant_id = '" + uId +"' ) order by TRAIN_CHART_NUMBER desc"; %>
		<%System.out.print("=========我分管领导！！"); %>
	</sec:authorize>
	
	<sec:authorize access="hasRole('TJY2_ZL_PXFZR')">
		<%sql1="select * from (select b.*,t.handler_id from TJY2_QUALITY_STAFF_TRAIN b, v_pub_worktask t "+
		       "where b.id=t.SERVICE_ID "+   
		       "and  t.WORK_TYPE like '%TJY2_ZL_ZGPX%' and t.STATUS='0' and t.handler_id = '"+uId+"' union "+ 
			   "select b.*,b.registrant_id handler_id from TJY2_QUALITY_STAFF_TRAIN b "+  
		  	   "where b.status in('PASS','NO_PASS') union select b.*,registrant_id from TJY2_QUALITY_STAFF_TRAIN b "+   
		  	   "where registrant_id = '" + uId +"' ) order by TRAIN_CHART_NUMBER desc"; %>
		<%System.out.print("=========我培训的分管领导！！"); %>
	</sec:authorize>
	
	<sec:authorize access="hasRole('TJY2_LEADER')">
		<%sql1="select * from (select b.*,t.handler_id from TJY2_QUALITY_STAFF_TRAIN b, v_pub_worktask t "+
		       "where b.id=t.SERVICE_ID "+   
		       "and  t.WORK_TYPE like '%TJY2_ZL_ZGPX%' and t.STATUS='0' and t.handler_id = '"+uId+"' union "+ 
			   "select b.*,b.registrant_id handler_id from TJY2_QUALITY_STAFF_TRAIN b "+  
		  	   "where b.status in('PASS','NO_PASS') union select b.*,registrant_id from TJY2_QUALITY_STAFF_TRAIN b "+   
		  	   "where registrant_id = '" + uId +"' ) order by TRAIN_CHART_NUMBER desc"; %>
		<%System.out.print("=========我院长！！"); %>
	</sec:authorize>
    
	<qm:qm pageid="TJY2_QUALITY_STAFF2" singleSelect="true"  sql="<%=sql1.toString() %>">
	</qm:qm>
<%--     <sec:authorize access="hasRole('TJY2_BMFZR')"> --%>
<%-- 		<sec:authorize access="!hasRole('TJY2_ZL_GLY')"> --%>
<%-- 			<qm:param name="registrant_id" value="<%=uId%>" compare="=" /> --%>
<%-- 			<qm:param name="handler_id" compare="=" value="<%=uId%>" logic="or"/> --%>
<%-- 		</sec:authorize> --%>
<%-- 		<%lists="TJY2_QUALITY_STAFF"; %> --%>
<%-- 		<%System.out.print("===========你还不进来1！！"); %> --%>
<%-- 	</sec:authorize> --%>
<%-- 	<sec:authorize access="hasRole('TJY2_ZL_KYJS')"> --%>
<%-- 		<%lists="TJY2_QUALITY_STAFF"; %> --%>
<%-- 		<%System.out.print("===========你还不进来2！！"); %> --%>
<%-- 	</sec:authorize> --%>
<%-- 	<sec:authorize access="hasRole('TJY2_ZL_FGYLDSH')"> --%>
<%-- 		<%lists="TJY2_QUALITY_STAFF"; %> --%>
<%-- 		<%System.out.print("===========你还不进来2！！"); %> --%>
<%-- 	</sec:authorize> --%>
<%-- 	<sec:authorize access="hasRole('TJY2_ZL_PXFZR')"> --%>
<%-- 		<%lists="TJY2_QUALITY_STAFF"; %> --%>
<%-- 		<%System.out.print("===========你还不进来2！！"); %> --%>
<%-- 	</sec:authorize> --%>
<%-- 	<sec:authorize access="hasRole('TJY2_LEADER')"> --%>
<%-- 	<%System.out.print("===========你还不进来3！！"); %> --%>
<%-- 		<%lists="TJY2_QUALITY_STAFF"; %> --%>
<%-- 	</sec:authorize> --%>
<%--     <sec:authorize access="!hasRole('TJY2_CL_LCCL')"> --%>
<%-- 		<%lists="TJY2_QUALITY_STAFF2"; %> --%>
<%-- 		<%System.out.print("=========你为什么要进来！！"); %> --%>
<%-- 	</sec:authorize> --%>
<%-- 	<sec:authorize access="hasRole('TJY2_ZL_GLY')"> --%>
<%-- 		<%lists="TJY2_QUALITY_STAFF2"; %> --%>
<%-- 		<%System.out.print("=========你为什么要进来！！"); %> --%>
<%-- 	</sec:authorize> --%>

</body>

</html>