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
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%
CurrentSessionUser useres = SecurityUtil.getSecurityUser();
User uu = (User)useres.getSysUser();
com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
e.getId();
String userId=e.getId();
String uId = SecurityUtil.getSecurityUser().getId();
%>
<script type="text/javascript">
	var qmUserConfig = {
			sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
			sp_fields:[
			           {name:"matters",compare:"like"},
			           {name:"chapter_name",compare: "like"},
			           {group: [
						{name: "APPLY_TIME", compare: ">="},
						{label: "到", name: "APPLY_TIME", compare: "<=", labelAlign: "center", labelWidth:20}
				 ],columnWidth:0.33}
			],
			
		tbar: [ {
			 text: '详情', id: 'detail', icon: 'detail', click: detail
		}, "-",  {
			text: '新增', id: 'add', icon: 'add', click: add
		} , "-", {
			text: '修改', id: 'edit', icon: 'modify', click: edit
		}, "-", {
			text: '删除', id: 'del', icon: 'delete', click: del
		}, "-", {
	        text:'提交', id:'submit',icon:'submit', click: submit
	    }
		<sec:authorize access="hasRole('TJY2_CL_LCCL')">
	    , "-", {
	        text:'处理', id:'sh',icon:'dispose', click: shenhe
	    }</sec:authorize>
	    /* , "-",{ 
	    	text:'查看审批进度', id:'deal',icon:'detail', click:look} */
    	, "-", {
            text:'审核过程', id:'shjl',icon:'follow', click: shjl
            }
    	, "-",{ 
	    	text:'流转过程', id:'turnHistory',icon:'follow', click: turnHistory}],
	   
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
				var up_status = Qm.getValueByName("status");
				var sp_status = Qm.getValueByName("sp_status");
				var status={};
				if(count==0){
					status={detail:false, edit:false, del:false,submit:false,shjl:false,turnHistory:false,sh: false,deal: false};
				}else if(count==1){
					if("待审核"==up_status){
						if("undefined"==sp_status){
							status={detail:true, edit:true, del:false,submit:false,shjl:false,turnHistory:true,sh: true,deal: true};
						}else{
							status={detail:true, edit:false, del:false,submit:false,shjl:false,turnHistory:true,sh: true,deal: true};
						}
					}else if("部门负责人待审核"==up_status){
						status={detail:true, edit:false, del:false,submit:false,shjl:true,turnHistory:true,sh: true,deal: true};
					}else if("服务部待审核"==up_status || "分管领导待审核"==up_status || "盖章人待审核"==up_status){
						status={detail:true, edit:false, del:false,submit:false,shjl:true,turnHistory:true,sh: true,deal: true};
					}else if("已完成"==up_status){
						status={detail:true, edit:false, del:false,submit:false,shjl:true,turnHistory:true,sh: false,deal: true};
					}else if("审核不通过"==up_status){
							status={detail:true, edit:false, del:true,submit:false,shjl:true,turnHistory:true,sh: false,deal: true};
					}else if("未提交"==up_status){
							status={detail:true, edit:true, del:true,submit:true,shjl:false,turnHistory:false,sh: false,deal: true};
					}else{
						status={detail:true, edit:false, del:true,submit:true,shjl:false,turnHistory:false,sh: false,deal: false};
					}
				}else{
					if("已提交"==sp_status){
						status={detail:false, edit:false, del:true,submit:false,shjl:false,turnHistory:true,sh: false,deal: false};
					}else{
						status={detail:false, edit:false, del:false,submit:false,shjl:false,turnHistory:false,sh: false,deal: false};
					}
				}
				Qm.setTbar(status);
			},
	        rowAttrRender : function(rowData, rowid) {
                var fontColor="black";
                if (rowData.status=='已完成'){
                    fontColor="green";
                }else if(rowData.status=='审核不通过') {
                    fontColor="red";
                }else if(rowData.status=='部门负责人待审核' || rowData.status=='服务部待审核' || rowData.status=='分管领导待审核' || rowData.status=='盖章人待审核') {
                    fontColor="orange";
                }
                return "style='color:"+fontColor+"'";
            }
		}
	};
	//流转过程
	function turnHistory(){	
		top.$.dialog({
				width : 400, 
				height : 700, 
				lock : true, 
				title: "流程卡",
				content: 'url:seal/regist/getFlowStep.do?id='+Qm.getValueByName("id"),
				data : {"window" : window}
			});
	}
	function shenhe(){//处理
		var id = Qm.getValueByName("id");
		top.$.ajax({
            url: "seal/regist/getLcid.do?id="+id,
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
                             title:"非用章范围需盖章申请表"
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
 		top.$.ajax({
             url: "seal/regist/getprocess_id.do?id="+id,
             type: "GET",
             dataType:'json',
             async: false,
             success:function (data) {
                 if (data) {
                 	var process_id=data.process_id;
                 	if(process_id==""){
						$.ligerDialog.error("提交流程后才可以查看！！");
                 	}else{
                       trackProcess(process_id);
	                   Qm.refreshGrid();
                	}
                 }
             },
             error:function () {
                 $.ligerDialog.error("出错了！请重试！!");
             }
         });
		
	}
	
     function shjl(){
    	 var selectedId = Qm.getValuesByName("id");
 		 if (selectedId.length < 1) {
 			$.ligerDialog.alert('请先选择要查看的事项！', "提示");
 			return;
 		  }
 		var width = 700;
 		var height = 320;
 		var windows = top.$.dialog({
 			width : width, height : height, lock : true, title : "审核记录", data : {
 				"window" : window
 			}, content : 'url:app/qualitymanage/sealNotes_list.jsp?status=detail&id=' + selectedId
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
         getServiceFlowConfig("TJY2_ZL_GZDJ","",function(result,data){
                if(result){
                     top.$.ajax({
                         url: "seal/regist/subFolws.do?id="+id+"&flowId="+data.id+"&typeCode=TJY2_ZL_GZDJ",
                         type: "GET",
                         dataType:'json',
                         async: false,
                         success:function (data) {
                             if (data) {
                               // $.ligerDialog.alert("提交成功！！！");
                                top.$.notice('提交成功！！！',3);

                                Qm.refreshGrid();
                                $("body").unmask();
                             }
                         },
                         error:function () {
                        	 $.ligerDialog.error('出错了！请重试！！!',3);
                             $("body").unmask();
                         }
                     });
                }else{
                	$.ligerDialog.error('出错了！请重试！!',3);
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
			title: "新增",
			content: 'url:app/qualitymanage/seal_registration_detail.jsp?pageStatus=add'
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
			content: 'url:app/qualitymanage/seal_registration_detail.jsp?id=' + id + '&pageStatus=modify'
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
			content: 'url:app/qualitymanage/seal_registration_detail.jsp?id=' + id + '&pageStatus=detail'
		});
	}
	function del() {
		$.del(kFrameConfig.DEL_MSG, "seal/regist/delete.do", {
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
                <font color="orange">“橙色”</font>代表待审核，
                <font color="green">“绿色”</font>代表已完成，
                <font color="red">“红色”</font>代表审核不通过。
			</div>
		</div>
	</div>
 	<%StringBuffer sql = new StringBuffer(); %>
    <%String sql1 = ""; %>
    <%String departmentId= useres.getDepartment().getId();%>
	<%String lists=""; %>
	
	<sec:authorize access="!hasRole('TJY2_SH_FYZ')">
		<%sql1="select t.*,t.CREATERID handler_id from TJY2_QUALITY_TXWJSP_SEALREG t where t.CREATERID='"+ uId +"' order by t.status,t.identifier desc Nulls last "; %>
		<%System.out.println("=========我看我自己！！"); %>
	</sec:authorize>
	
	<sec:authorize access="hasRole('TJY2_BMFZR')">
		<%sql1="select * from (select b.*,t.handler_id from TJY2_QUALITY_TXWJSP_SEALREG b, v_pub_worktask t "+
		       "where b.id=t.SERVICE_ID "+   
		       "and  t.WORK_TYPE  like   '%TJY2_ZL_GZDJ%' and t.STATUS='0' and t.handler_id = '"+uId+"' union "+ 
			   "select b.*,b.CREATERID handler_id from TJY2_QUALITY_TXWJSP_SEALREG b "+  
		  	   "where b.status in('6','7') and b.ORGID= '" + departmentId +"' union select b.*,CREATERID from TJY2_QUALITY_TXWJSP_SEALREG b "+   
		  	   "where CREATERID = '" + uId +"' ) order by STATUS,IDENTIFIER desc"; %>
		<%System.out.print("=========我部门负责人！！"); %>
	</sec:authorize>
	
	<sec:authorize access="hasRole('TJY2_ZL_YWBZ')">
		<%sql1="select * from (select b.*,t.handler_id from TJY2_QUALITY_TXWJSP_SEALREG b, v_pub_worktask t "+
		       "where b.id=t.SERVICE_ID "+   
		       "and  t.WORK_TYPE  like   '%TJY2_ZL_GZDJ%' and t.STATUS='0' and t.handler_id = '"+uId+"' union "+ 
			   "select b.*,b.CREATERID handler_id from TJY2_QUALITY_TXWJSP_SEALREG b "+  
		  	   "where b.status in('6','7') union select b.*,CREATERID from TJY2_QUALITY_TXWJSP_SEALREG b "+   
		  	   "where CREATERID = '" + uId +"' ) order by STATUS,IDENTIFIER desc"; %>
		<%System.out.print("=========我是业务部门部长！！"); %>
	</sec:authorize>
	<%-- <sec:authorize access="hasRole('TJY2_ZL_YWFGLD')">
		<%sql1="select * from (select b.*,t.handler_id from TJY2_QUALITY_TXWJSP_SEALREG b, v_pub_worktask t "+
		       "where b.id=t.SERVICE_ID "+   
		       "and  t.WORK_TYPE  like   '%TJY2_ZL_GZDJ%' and t.STATUS='0' and t.handler_id = '"+uId+"' union "+ 
			   "select b.*,b.CREATERID handler_id from TJY2_QUALITY_TXWJSP_SEALREG b "+  
		  	   "where b.status in('6','7') union select b.*,CREATERID from TJY2_QUALITY_TXWJSP_SEALREG b "+   
		  	   "where CREATERID = '" + uId +"' ) order by STATUS,IDENTIFIER desc"; %>
		<%System.out.print("=========我是分管领导！！"); %>
	</sec:authorize> --%>
	<sec:authorize access="hasRole('TJY2_ZL_YWFGLD')">
		<%sql1="select * from (select b.*,t.handler_id from TJY2_QUALITY_TXWJSP_SEALREG b, v_pub_worktask t "+
		       "where b.id=t.SERVICE_ID "+   
		       "and  t.WORK_TYPE  like   '%TJY2_ZL_GZDJ%' and t.STATUS='0' and t.handler_id = '"+uId+
		       "' and instr((select d.fk_sys_org_id from TJY2_RL_ORGID_LEADERID d "+
				"where d.fk_rl_emplpyee_id='"+userId+"'), b.orgid) > 0 "+
		       "union "+ 
			   "select b.*,b.CREATERID handler_id from TJY2_QUALITY_TXWJSP_SEALREG b "+  
		  	   "where b.status in('6','7') and instr((select d.fk_sys_org_id from TJY2_RL_ORGID_LEADERID d "+
			   "where d.fk_rl_emplpyee_id='"+userId+"'), b.orgid) > 0 "+
			   "union "+
		  	   "select b.*,CREATERID from TJY2_QUALITY_TXWJSP_SEALREG b "+   
		  	   "where CREATERID = '" + uId +"' ) order by STATUS,IDENTIFIER desc"; %>
		<%System.out.print("=========我是分管领导！！"); %>
	</sec:authorize>
	
	<sec:authorize access="hasRole('TJY2_SH_FYZ')">
		<%sql1="select * from (select b.*,t.handler_id from TJY2_QUALITY_TXWJSP_SEALREG b, v_pub_worktask t "+
		       "where b.id=t.SERVICE_ID "+   
		       "and  t.WORK_TYPE  like '%TJY2_ZL_GZDJ%' and t.STATUS='0' and t.handler_id = '"+uId+"' union "+ 
			   "select b.*,b.CREATERID handler_id from TJY2_QUALITY_TXWJSP_SEALREG b "+  
		  	   "where b.status in('6','7') union select b.*,CREATERID from TJY2_QUALITY_TXWJSP_SEALREG b "+   
		  	   "where CREATERID = '" + uId +"' ) order by STATUS,IDENTIFIER desc"; %>
		<%System.out.print("=========我全看！！（盖章人）"); %>
	</sec:authorize>
	
	<qm:qm pageid="TJY2_ZL_GZDJ" singleSelect="true" sql="<%=sql1.toString() %>">
	</qm:qm>
<%-- 	<sec:authorize access="!hasRole('TJY2_SH_FYZ')"> --%>
<%-- 		<%sql1="select t.*,t.CREATERID handler_id from TJY2_QUALITY_TXWJSP_SEALREG t where t.CREATERID='"+ userId +"' order by t.identifier desc Nulls last "; %> --%>
<%-- 		<%System.out.print("=========我靠我自己！！"); %> --%>
<%-- 	</sec:authorize> --%>
	
<%-- 	<sec:authorize access="hasRole('TJY2_BMFZR')"> --%>
<%-- 		<%sql1="select * from (select b.*,t.handler_id from TJY2_QUALITY_TXWJSP_SEALREG b, v_pub_worktask t where b.id=t.SERVICE_ID and  t.WORK_TYPE  like '%TJY2_ZL_GZDJ%' and t.STATUS='0' and t.handler_id='"+ uId +"' union select b.*,b.CREATERID handler_id from TJY2_QUALITY_TXWJSP_SEALREG b where b.status in('4','5') and b.ORGID= '" + departmentId +"' union select b.*,b.CREATERID handler_id from TJY2_QUALITY_TXWJSP_SEALREG b where b.CREATERID='"+ userId +"')order by identifier desc Nulls last"; %> --%>
<%-- 		<%System.out.print("=========我部门负责人！！"); %> --%>
<%-- 	</sec:authorize> --%>
	
<%-- 	<sec:authorize access="hasRole('TJY2_ZL_YWBZ')"> --%>
<%-- 		<%sql1="select * from (select b.*,t.handler_id from TJY2_QUALITY_TXWJSP_SEALREG b, v_pub_worktask t where b.id=t.SERVICE_ID and  t.WORK_TYPE  like '%TJY2_ZL_GZDJ%' and t.STATUS='0' and t.handler_id='"+ uId +"' union select b.*,b.CREATERID handler_id from TJY2_QUALITY_TXWJSP_SEALREG b where b.status in('4','5') union select b.*,b.CREATERID handler_id from TJY2_QUALITY_TXWJSP_SEALREG b where b.CREATERID='"+ userId +"')order by identifier desc Nulls last"; %> --%>
<%-- 		<%System.out.print("=========我是业务部门部长！！"); %> --%>
<%-- 	</sec:authorize> --%>
	
<%-- 	<sec:authorize access="hasRole('TJY2_ZL_YWFGLD')"> --%>
<%-- 		<%sql1="select * from (select b.*,t.handler_id from TJY2_QUALITY_TXWJSP_SEALREG b, v_pub_worktask t where b.id=t.SERVICE_ID and  t.WORK_TYPE  like '%TJY2_ZL_GZDJ%' and t.STATUS='0' and t.handler_id='"+ uId +"' union select b.*,b.CREATERID handler_id from TJY2_QUALITY_TXWJSP_SEALREG b where b.status in('4','5') union select b.*,b.CREATERID handler_id from TJY2_QUALITY_TXWJSP_SEALREG b where b.CREATERID='"+ userId +"')order by identifier desc Nulls last"; %> --%>
<%-- 		<%System.out.print("=========我是分管领导！！"); %> --%>
<%-- 	</sec:authorize> --%>
	
<%-- 	<sec:authorize access="hasRole('TJY2_SH_FYZ')"><!-- 质量管理-审核（非用章）--> --%>
<%--  		<%sql1="select t.*,t.CREATERID handler_id from TJY2_QUALITY_TXWJSP_SEALREG t order by t.identifier desc Nulls last "; %>  --%>
<%-- 		<%sql1="select * from (select b.*,t.handler_id from TJY2_QUALITY_TXWJSP_SEALREG b, v_pub_worktask t where b.id=t.SERVICE_ID and  t.WORK_TYPE  like '%TJY2_ZL_GZDJ%' and t.STATUS='0' and t.handler_id='"+ uId +"' union select b.*,b.CREATERID handler_id from TJY2_QUALITY_TXWJSP_SEALREG b where b.status in('4','5') union select b.*,b.CREATERID handler_id from TJY2_QUALITY_TXWJSP_SEALREG b where b.CREATERID='"+ userId +"')order by identifier desc Nulls last"; %> --%>
<%-- 		<%System.out.print("=========我全看！！"); %> --%>
<%-- 	</sec:authorize> --%>
<%-- 	<qm:qm pageid="TJY2_ZL_GZDJ" singleSelect="true" sql="<%=sql1.toString() %>"> --%>
<%-- 	<sec:authorize access="!hasRole('TJY2_ZL_GLY')"> - --%>
<%--  		<qm:param name="CREATERID" value="<%=userId%>" compare="=" />  --%>
<%-- 		<qm:param name="handler_id" compare="=" value="<%=uId%>" logic="or"/>  --%>
<%-- 	</sec:authorize> --%>
<%-- 	</qm:qm> --%>
</body>
</html>