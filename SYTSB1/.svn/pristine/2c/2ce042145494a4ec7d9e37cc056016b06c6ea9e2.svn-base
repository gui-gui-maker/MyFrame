<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<head>
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<script type="text/javascript" src="pub/worktask/js/worktask.js"></script>    
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%
CurrentSessionUser user = SecurityUtil.getSecurityUser();
User uu = (User)user.getSysUser();
com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
e.getId();
String userId=e.getId();
String uId = SecurityUtil.getSecurityUser().getId();
%>

<script type="text/javascript">
	var qmUserConfig = {
			sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
			sp_fields:[
	         {name:"proposer",compare: "like"},
	         {name:"apply_unit",compare: "="},
	         {group: [
	  				{name: "apply_time", compare: ">="},
	  				{label: "到", name: "apply_time", compare: "<=", labelAlign: "center", labelWidth:20}
	  		 ],columnWidth:0.4}
		],
		tbar: [ {
			text: '详情', id: 'detail', icon: 'detail', click: detail
		}, "-", {
			text: '申请', id: 'add', icon: 'add', click: add
		}, "-", {
			text: '修改', id: 'edit', icon: 'modify', click: edit
		}, "-", {
			text: '删除', id: 'del', icon: 'delete', click: del
		}, "-", {
	        text:'提交', id:'submit',icon:'submit', click: submit
	    }, "-", {
	        text:'审核过程', id:'gc',icon:'follow', click: gc
	    }<sec:authorize access="hasRole('TJY2_CL_LCCL')">
	    , "-", {text:'处理', id:'sh',icon:'dispose', click: shenhe
	    }</sec:authorize>
	    , "-",{text:'查看审批进度', id:'deal',icon:'detail', click:look}
	    <sec:authorize access="hasRole('TJY2_AC_JBR')">
		    , "-", {
		        text:'打印', id:'dy',icon:'print', click: dy}
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
					edit: count==1,
					del: count>0,
					submit: count==1
						
				});
			},
			selectionChange : function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();//行选择个数
				//alert(count);
				var up_status = Qm.getValueByName("status");
				//alert(up_status);
				var sp_status = Qm.getValueByName("sp_status");
				//alert(sp_status);
				var status={};
				if(count==0){
					status={detail:false, edit:false, del:false,submit:false,dy:false,sh: false,deal: false};
				}else if(count==1){
					if("已提交"==up_status){
						if("undefined"==sp_status){
							status={detail:true, edit:false, del:true,submit:false,dy:false};
						}else{
							status={detail:true, edit:false, del:false,submit:false,dy:false,sh: true,deal: true};
						}
					}else if("审核中"==up_status){
						if("undefined"==sp_status){
							status={detail:true, edit:false, del:true,submit:false,dy:false};
						}else{
							status={detail:true, edit:false, del:false,submit:false,sdy:false,sh: true,deal: true};
						}
					}else if("审核通过"==up_status){
						if("undefined"==sp_status){
							status={detail:true, edit:false, del:true,submit:false,dy:true};
						}else{
							status={detail:true, edit:false, del:false,submit:false,dy:true,sh: false,deal: true};
						}
					}else if("已打印"==up_status){
						if("undefined"==sp_status){
							status={detail:true, edit:false, del:true,submit:false,dy:true};
						}else{
							status={detail:true, edit:false, del:false,submit:false,dy:true,sh: false,deal: true};
						}
					}else if("被退回"==up_status){
						if("undefined"==sp_status){
							status={detail:true, edit:true, del:true,submit:true,dy:false};
						}else{
							status={detail:true, edit:true, del:true,submit:true,dy:false,sh: false,deal: true};
						}
					}else if("审核未通过"==up_status){
						if("undefined"==sp_status){
							status={detail:true, edit:false, del:true,submit:false,dy:false};
						}else{
							status={detail:true, edit:false, del:true,submit:false,dy:false,sh: false,deal: true};
						}
					}else{
						status={detail:true, edit:true, del:true,submit:true,dy:false,sh: false,deal: false};
					}
				}else{
					if("已提交"==sp_status){
						status={detail:false, edit:false, del:true,submit:false,dy:true,sh: false,deal: true};
					}else{
						status={detail:false, edit:false, del:false,submit:false,dy:false,sh: false,deal: true};
					}
				}
				Qm.setTbar(status);
			},
	        rowAttrRender : function(rowData, rowid) {
                var fontColor="black";
                if (rowData.status=='审核通过'){
                    fontColor="green";
                }else if(rowData.status=='审核未通过') {
                    fontColor="red";
                }else if(rowData.status=='已提交') {
                    fontColor="orange";
                }else if(rowData.status=='审核中') {
                    fontColor="blue";
                }else if(rowData.status=='已打印') {
                    fontColor="purple";
                }
                return "style='color:"+fontColor+"'";
            }
		}
	};
	
	function shenhe(){//处理
		var id = Qm.getValueByName("id");
		top.$.ajax({
            url: "archives/print/getLcid.do?id="+id,
            type: "GET",
            dataType:'json',
            async: false,
            success:function (data) {
                if (data) {
                	var ids=data.ids;
                	 var config={
                             width :900,
                             height : 500,
                             id:ids,
                             title:"手工出具检验报告/证书审批表"
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
// 		top.$.ajax({
//             url: "archives/print/getprocess_id.do?id="+id,
//             type: "GET",
//             dataType:'json',
//             async: false,
//             success:function (data) {
//                 if (data) {
//                 	var process_id=data.process_id;
//                 	if(process_id==""){
//                 		$.ligerDialog.error("提交流程后才可以查看！！");
//                 	}else{
//                       trackProcess(process_id);
// 	                   Qm.refreshGrid();
//                 	}
//                 }
//             },
//             error:function () {
//                 $.ligerDialog.error("出错了！请重试！!");
//             }
//         });
		
	}
	
	function dy(){
		var id = Qm.getValueByName("id");
		var up_status = Qm.getValueByName("status");
		if(!id){
			$.ligerDialog.warn('请先选择要查看的数据！',3);
            return;
        }
		 $.ligerDialog.confirm('是否要打印？', function (yes){
        	 if(!yes){return false;}
            top.$.ajax({
                 url: "archives/print/dy.do?id="+id,
                 type: "GET",
                 dataType:'json',
                 async: false,
                 success:function (data) {
//                 	 if("已打印"==up_status){
//                 		 $.ligerDialog.warn("此条已！");
//                 	 }else{
	                	 if(data.success){
	                		 top.$.notice("打印成功！",3);
	                         Qm.refreshGrid();//刷新
	                     }else{
	                         $.ligerDialog.warn(data.msg);
	                     }
//                	 }
                 },
                 error:function () {
                     $.ligerDialog.warn("启用失败！");
                 }
             });
        });
	}
	function gc(){
		var id = Qm.getValueByName("id");
		if(!id){
			$.ligerDialog.warn('请先选择要查看的数据！',3);
            return;
        }
		top.$.dialog({
			width: 715,
			height: 350,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "审核过程",
			content: 'url:app/archives/archives_process.jsp?ids=' + id + '&pageStatus=detail'
		});
	}
	function submit() {
		var id = Qm.getValueByName("id");
        if(!id){
        	$.ligerDialog.warn('请先选择要提交审核的数据！',3);
            return;
        }
        $.ligerDialog.confirm('是否提交审核？', function (yes){
        if(!yes){return false;}
         $("body").mask("提交中...");
         getServiceFlowConfig("TJY2_FN_DYSQ1","",function(result,data){
             if(result){
                  top.$.ajax({
                      url: "archives/print/subFolws.do?id="+id+"&flowId="+data.id+"&typeCode=TJY2_FN_DYSQ1&status="+status,
                      type: "GET",
                      dataType:'json',
                      async: false,
                      success:function (data) {
                          if (data) {
                             top.$.notice('提交成功！！！',3);
                             Qm.refreshGrid();
                             $("body").unmask();
                          }
                      },
                      error:function () {
                     	 $.ligerDialog.error('出错了！请重试！！!');
                          $("body").unmask();
                      }
                  });
             }else{
             	$.ligerDialog.error('出错了！请重试！!');
              $("body").unmask();
             }
          });
        });
	}
	function add() {
		top.$.dialog({
			width: 900,
			height: 450,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "申请",
			content: 'url:app/archives/archives_print_detail.jsp?pageStatus=add'
		});
	}

	function edit() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 900,
			height: 450,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "修改",
			content: 'url:app/archives/archives_print_detail.jsp?id=' + id + '&pageStatus=modify'
		});
	}
	function detail() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 900,
			height: 450,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "详情",
			content: 'url:app/archives/archives_print_detail.jsp?id=' + id + '&pageStatus=detail'
		});
	}
	function del() {
		$.del(kFrameConfig.DEL_MSG, "archives/print/delete.do", {
			"ids": Qm.getValuesByName("id").toString()
		});
	}
</script>
</head>
<body>
<div class="item-tm" id="titleElement">
		<div class="l-page-title">
			<div class="l-page-title-note">提示：列表数据项
				<font color="black">“黑色”</font>代表单据未提交，
                <font color="orange">“橙色”</font>代表未审核，
                <font color="blue">“蓝色”</font>代表审核中，
                <font color="green">“绿色”</font>代表审核通过，
                <font color="red">“红色”</font>代表审核未通过，
                <font color="purple">“紫色”</font>代表已打印。
			</div>
		</div>
	</div>
	<%String sql1 = ""; %>
    <%CurrentSessionUser useres = SecurityUtil.getSecurityUser();
    String departmentId= useres.getDepartment().getId();%>
	<%String lists=""; %>
	
	<sec:authorize access="!hasRole('TJY2_AC_JBR')">
		<%sql1="select t.*,t.proposer_id handler_id from TJY2_ARCHIVES_PRINT t where t.proposer_id='"+ userId +"' order by t.identifier desc Nulls last "; %>
		<%System.out.println("=========我看我自己！！"); %>
	</sec:authorize>
	
	<sec:authorize access="hasRole('TJY2_BMFZR')">
		<%sql1="select * from (select b.*,t.handler_id from TJY2_ARCHIVES_PRINT b, v_pub_worktask t "+
		       "where b.id=t.SERVICE_ID "+   
		       "and  t.WORK_TYPE  like   '%TJY2_FN_DYSQ1%' and t.STATUS='0' and t.handler_id = '"+uId+"' union "+ 
			   "select b.*,b.proposer_id handler_id from TJY2_ARCHIVES_PRINT b "+  
		  	   "where b.status in('PASS','NO_PASS') and b.APPLY_UNIT_ID= '" + departmentId +"' union "+ 
			   "select b.*,registrant_id from TJY2_ARCHIVES_PRINT b "+   
		  	   "where registrant_id = '" + uId +"' ) order by IDENTIFIER desc"; %>
		<%System.out.println("=========我部门负责人！！"+uId+"@@@"+departmentId); %>
	</sec:authorize>
	
	<sec:authorize access="hasRole('TJY2_AC_JBR')">
		<%sql1="select * from (select b.*,t.handler_id from TJY2_ARCHIVES_PRINT b, v_pub_worktask t "+
		       "where b.id=t.SERVICE_ID "+   
		       "and  t.WORK_TYPE  like   '%TJY2_FN_DYSQ1%' and t.STATUS='0' and t.handler_id = '"+uId+"' union "+ 
			   "select b.*,b.proposer_id handler_id from TJY2_ARCHIVES_PRINT b "+  
		  	   "where b.status in('PASS','NO_PASS') union "+ 
			   "select b.*,registrant_id from TJY2_ARCHIVES_PRINT b "+   
		  	   "where registrant_id = '" + uId +"' ) order by IDENTIFIER desc"; %>
		<%System.out.println("=========业务服务部负责人！！"); %>
	</sec:authorize>
	
	<sec:authorize access="hasRole('TJY2_ZL_WYSSH')">
		<%sql1="select * from (select b.*,t.handler_id from TJY2_ARCHIVES_PRINT b, v_pub_worktask t "+
		       "where b.id=t.SERVICE_ID "+   
		       "and  t.WORK_TYPE  like   '%TJY2_FN_DYSQ1%' and t.STATUS='0' and t.handler_id = '"+uId+"' union "+ 
			   "select b.*,b.proposer_id handler_id from TJY2_ARCHIVES_PRINT b "+  
		  	   "where b.status in('PASS','NO_PASS') union "+ 
			   "select b.*,registrant_id from TJY2_ARCHIVES_PRINT b "+   
		  	   "where registrant_id = '" + uId +"' ) order by IDENTIFIER desc"; %>
		<%System.out.println("=========业务服务部机电负责人！！"); %>
	</sec:authorize>
	
	<sec:authorize access="hasRole('TJY2_ZL_WYSSH2')">
		<%sql1="select * from (select b.*,t.handler_id from TJY2_ARCHIVES_PRINT b, v_pub_worktask t "+
		       "where b.id=t.SERVICE_ID "+   
		       "and  t.WORK_TYPE  like   '%TJY2_FN_DYSQ1%' and t.STATUS='0' and t.handler_id = '"+uId+"' union "+ 
			   "select b.*,b.proposer_id handler_id from TJY2_ARCHIVES_PRINT b "+  
		  	   "where b.status in('PASS','NO_PASS') union "+ 
			   "select b.*,registrant_id from TJY2_ARCHIVES_PRINT b "+   
		  	   "where registrant_id = '" + uId +"' ) order by IDENTIFIER desc"; %>
		<%System.out.println("=========业务服务部承压负责人！！"); %>
	</sec:authorize>
	
	<qm:qm pageid="TJY2_ARCHIVES_PRINT" singleSelect="true" sql="<%=sql1.toString() %>">
<%-- 		<sec:authorize access="!hasRole('TJY2_AC_JBR')"> --%>
<%-- 			<qm:param name="registrant_id" value="<%=uId%>" compare="=" /> --%>
<%-- 		</sec:authorize> --%>
	</qm:qm>
</body>
</html>