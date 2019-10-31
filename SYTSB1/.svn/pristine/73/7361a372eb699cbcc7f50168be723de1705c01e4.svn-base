<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<head>
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript" src="pub/worktask/js/worktask.js"></script>    
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%
CurrentSessionUser useres1 = SecurityUtil.getSecurityUser();
User uu = (User)useres1.getSysUser();
com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
e.getId();
String userId=e.getId();
String uId = SecurityUtil.getSecurityUser().getId();
%>

<script type="text/javascript">
	var QM="TJY2_ZL_SGCJJYBG";
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	// 默认值，可自定义	
		sp_fields:[
	         {name:"apply_name",compare: "like"},
	         {name:"department",compare: "like"},
	         {group: [
	  				{name: "apply_time", compare: ">="},
	  				{label: "到", name: "apply_time", compare: "<=", labelAlign: "center", labelWidth:20}
	  		 ]},
	  		{name:"user_name",compare: "like"},
	  		 {name:"fwbjhbg",compare: "like"},
	  		{name:"status",compare: "="}
	    ],
		tbar: [ {
				text: '详情', id: 'detail', icon: 'detail', click: detail
			}, "-", {
				text: '新增', id: 'add', icon: 'add', click: add
			}, "-", {
				text: '修改', id: 'edit', icon: 'modify', click: edit
			}, "-", {
				text: '删除', id: 'del', icon: 'delete', click: del
			}, "-", {
		        text: '提交', id: 'submit',icon: 'submit', click: submitTj
		    }<sec:authorize access="hasRole('TJY2_CL_LCCL')">
		    , "-", {
		        text: '处理', id: 'sh',icon: 'dispose', click: shenhe
		    }, "-", {
		        text: '作废', id: 'cancel',icon: 'cancel', click: cancel
		    }</sec:authorize>
		    , "-", {
		        text: '审核过程', id: 'gc',icon: 'follow', click: gc
		    }, "-", {
		        text: '流转过程', id: 'turnHistory',icon: 'follow', click: turnHistory
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
					del: count>0,
					submit: count==1
				});
			},
			selectionChange : function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();//行选择个数
				var up_status = Qm.getValueByName("status");
				var sp_status = Qm.getValueByName("sp_status");
				var status={};
				var registrant_id = Qm.getValueByName("registrant_id");
				var flag = registrant_id=="<%=uId %>";
				if(count==0){
					status={detail:false, edit:false, del:false,submit:false,gc:false,turnHistory:false,sh: false,deal: false,cancel: false};
				}else if(count==1){
					if("已提交"==up_status){
						status={detail:true, edit:false, del:false,submit:false,turnHistory:true,sh: true,deal: true,cancel: true};
					}else if("审核通过"==up_status){
						status={detail:true, edit:false, del:false,submit:false,gc:true,turnHistory:true,sh: false,deal: true,cancel: true};
					}else if("审核中"==up_status){
						status={detail:true, edit:false, del:false,submit:false,gc:true,turnHistory:true,sh: true,deal: true,cancel: true};
					}else if("审核未通过"==up_status){
						status={detail:true, edit:true&&flag, del:true&&flag,submit:false,gc:true,turnHistory:true,sh: false,deal: true,cancel: true};
					}else if("已作废"==up_status){
						status={detail:true, edit:true&&flag, del:true&&flag,submit:false,gc:true,turnHistory:true,sh: false,deal: true,cancel: false};
					}else{
						status={detail:true, edit:true&&flag, del:true&&flag,submit:true&&flag,gc:false,turnHistory:false,sh: false,deal: false,cancel: false};
					}
				}else{
					if("已提交"==sp_status){
						status={detail:false, edit:false, del:false,submit:false,turnHistory:false,sh: false,deal: false,cancel: true};
					}else{
						status={detail:false, edit:false, del:false,submit:false,turnHistory:false,sh: false,deal: false,cancel: true};
					}
				}
				Qm.setTbar(status);
			},
			rowAttrRender : function(rowData, rowid) {
	            var fontColor="black";
	            if (rowData.status=='已提交'){
	                fontColor="blue";
	            }else if(rowData.status=='审核中'){
	            	fontColor="orange";
	            }else if(rowData.status=='审核通过'){
	            	fontColor="green";
	            }else if(rowData.status=='审核未通过'){
	            	fontColor="red";
	            }else if(rowData.status=='已作废'){
	            	fontColor="#8E8E8E";
	            }
	            return "style='color:"+fontColor+"'";
	        }
		}
	}
	//处理
	function shenhe(){
		var id = Qm.getValueByName("id");
		top.$.ajax({
            url: "quality/sgcjjybg/getLcid.do?id="+id,
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
	//作废
	function del(){
            $.del("确定要删除？删除后无法恢复！","equipPpeAction/delete.do",{"ids":Qm.getValuesByName("id").toString()});
        }
      	//报废
        function cancel(){
	        $.ligerDialog.confirm('确定作废？', function (yes){
	        	if(!yes){return false;}
	            top.$.ajax({
	                 url: "quality/sgcjjybg/cancel.do?ids=" + Qm.getValuesByName("id").toString(),
	                 type: "post",
	                 async: false,
	                 success:function (data) {
	                    if(data.success){
	                    	top.$.notice("操作成功！");
							Qm.refreshGrid();
	                    }else{
	                    	top.$.notice("操作失败！");
	                    }
	                 },
	                 error:function () {
	                	 $.ligerDialog.error("出错了！请重试！！！");
	                 }
	             });
	        });
	}
	//查看审批进度
	function look(){
		var id = Qm.getValueByName("id");
		trackServiceProcess(id);
	}
	// 流转过程
	function turnHistory(){	
		top.$.dialog({
   			width : 400, 
   			height : 700, 
   			lock : true, 
   			title: "流程卡",
   			content: 'url:quality/sgcjjybg/getFlowStep.do?id='+Qm.getValueByName("id"),
   			data : {"window" : window}
   		});
	}
	function gc(){
		var id = Qm.getValueByName("id");
		if(!id){
           // $.ligerDialog.alert("请先选择要查看的数据！");
            var manager = $.ligerDialog.waitting('请先选择要查看的数据！');
            setTimeout(function (){manager.close();}, 4000);
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
			content: 'url:app/qualitymanage/sug_datail.jsp?ids=' + id + '&pageStatus=detail'
		});
	}
	function submitTj(){
		var id = Qm.getValueByName("id");
		var statusa=Qm.getValueByName("statusa");
        if(!id){
            $.ligerDialog.alert("请先选择要提交审核的数据！");
            return;
        }
        if(statusa=="0"){
	        $.ligerDialog.confirm('是否提交审核？', function (yes){
	        if(!yes){return false; }
	         $("body").mask("提交中...");
	         getServiceFlowConfig("TJY2_ZL_SGCJJYBG","",function(result,data){
	        	 if(result){
	                 top.$.ajax({
	                     url: "quality/sgcjjybg/subFolws.do?id="+id+"&flowId="+data.id+"&typeCode=TJY2_ZL_SGCJJYBG&status="+status,
	                     type: "GET",
	                     dataType:'json',
	                     async: false,
	                     success:function (data) {
	                         if (data) {
	                        	$("body").unmask();
	                        	top.$.notice("提交成功！！！",5);
	                            Qm.refreshGrid();
	                         }
	                     },
	                     error:function () {
	                    	 $("body").unmask();
	                         $.ligerDialog.error("出错了！请重试！!");
	                     }
	                 });
	            }else{
	             $.ligerDialog.error("出错了！请重试！");
	             $("body").unmask();
	            }
	         });
	        });
        }else if(statusa=="1"){
        	$.ligerDialog.confirm('是否提交审核？', function (yes){
    	        if(!yes){return false; }
    	         $("body").mask("提交中...");
    	         getServiceFlowConfig("TJY2_ZL_SGCJJYBG_WT","",function(result,data){
    	        	 if(result){
    	                 top.$.ajax({
    	                     url: "quality/sgcjjybg/subFolws.do?id="+id+"&flowId="+data.id+"&typeCode=TJY2_ZL_SGCJJYBG_WT&status="+status,
    	                     type: "GET",
    	                     dataType:'json',
    	                     async: false,
    	                     success:function (data) {
    	                         if (data) {
    	                        	$("body").unmask();
    	                        	top.$.notice("提交成功！！！",5);
    	                            Qm.refreshGrid();
    	                         }
    	                     },
    	                     error:function () {
    	                    	 $("body").unmask();
    	                         $.ligerDialog.error("出错了！请重试！!");
    	                     }
    	                 });
    	            }else{
    	             $.ligerDialog.error("出错了！请重试！");
    	             $("body").unmask();
    	            }
    	         });
    	        });
        }
	}
	function add() {
		top.$.dialog({
			width: 400,
			height: 150,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "选择检验性质",
			content: 'url:app/qualitymanage/sgcjjybg_dx.jsp?'
		});
	}

	function edit() {
		var statusa=Qm.getValueByName("statusa");
		var id = Qm.getValueByName("id");
		if(statusa=="0"){
			top.$.dialog({
				width: 900,
				height: 550,
				lock: true,
				parent: api,
				data: {
					window: window
				},
				title: "修改",
				content: 'url:app/qualitymanage/sgcjjybg_datail.jsp?id=' + id + '&pageStatus=modify'
			});
		}else if(statusa=="1"){
			top.$.dialog({
				width: 900,
				height: 550,
				lock: true,
				parent: api,
				data: {
					window: window
				},
				title: "修改",
				content: 'url:app/qualitymanage/sgcjjybg_datail2.jsp?id=' + id + '&pageStatus=modify'
			});
		}
	}
	function detail() {
		var statusa=Qm.getValueByName("statusa");
		var id = Qm.getValueByName("id");
		if(statusa=="0"){
			top.$.dialog({
				width: 900,
				height: 550,
				lock: true,
				parent: api,
				data: {
					window: window
				},
				title: "详情",
				content: 'url:app/qualitymanage/sgcjjybg_datail.jsp?id=' + id + '&pageStatus=detail'
			});
		}else if(statusa=="1"){
			top.$.dialog({
				width: 900,
				height: 550,
				lock: true,
				parent: api,
				data: {
					window: window
				},
				title: "详情",
				content: 'url:app/qualitymanage/sgcjjybg_datail2.jsp?id=' + id + '&pageStatus=detail'
			});
		}
	}
	function del() {
		var id = Qm.getValueByName("id");
		$.ligerDialog.confirm('您确定要删除所选数据吗？', function (yes){
        	if(!yes){return false;}
			top.$.ajax({
	            url: "quality/sgcjjybg/delete.do?ids="+id,
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
	           	 $.ligerDialog.error("出错了！请重试！!");
	            }
	        });
		});
	}
	function refreshGrid() {
	    Qm.refreshGrid();
	}
	function close(){
		api.close();
	}
</script>
</head>
<body>
	<div class="item-tm" id="titleElement">
        <div class="l-page-title">
            <div class="l-page-title-note">提示：列表数据项
                <font color="black">“黑色”</font>代表未提交，
                <font color="blue">"蓝色"</font>代表已提交，
                <font color="green">“绿色”</font>代表审核通过，
                <font color="orange">“橙色”</font>代表审核中，
                <font color="red">“红色”</font>代表审核未通过，
                <font color="#8E8E8E">“灰色”</font>代表已作废。
                
            </div>
        </div>
    </div>
<%StringBuffer sql = new StringBuffer(); %>
    <%String sql1 = ""; %>
    <%CurrentSessionUser useres = SecurityUtil.getSecurityUser();
    String departmentId= useres.getDepartment().getId();%>
	<%String lists=""; %>
	<sec:authorize access="!hasRole('TJY2_ZL_GLY')">
		<%sql1="select t.*,t.registrant_id handler_id from TJY2_QUALITY_SGCJJYBG t where t.registrant_id='"+ uId +"' order by t.identifier desc Nulls last,t.REGISTRANT_DATE desc"; %>
		<%System.out.print("=========我靠我自己！！"); %>
	</sec:authorize>
	
	<sec:authorize access="hasRole('TJY2_BMFZR')">
		<%sql1="select * from (select b.*,t.handler_id from TJY2_QUALITY_SGCJJYBG b, v_pub_worktask t "+
		       "where b.id=t.SERVICE_ID "+   
		       "and  t.WORK_TYPE  like   '%TJY2_ZL_SGCJJYBG%' and t.STATUS='0' and t.handler_id = '"+uId+"' union "+ 
			   "select b.*,b.registrant_id handler_id from TJY2_QUALITY_SGCJJYBG b "+  
		  	   "where b.status in('PASS','CANCEL') and b.department_id= '" + departmentId +"' union select b.*,registrant_id from TJY2_QUALITY_SGCJJYBG b "+   
		  	   "where registrant_id = '" + uId +"' ) order by IDENTIFIER desc,REGISTRANT_DATE desc"; %>
		<%System.out.print("=========我部门负责人！！"); %>
	</sec:authorize>
	
	<sec:authorize access="hasRole('TJY2_ZL_ZLBFZR')">
	<%sql1="select * from (select b.*,t.handler_id from TJY2_QUALITY_SGCJJYBG b, v_pub_worktask t "+
		       "where b.id=t.SERVICE_ID "+   
		       "and  t.WORK_TYPE  like   '%TJY2_ZL_SGCJJYBG%' and t.STATUS='0' and t.handler_id = '"+uId+"' union "+ 
			   "select b.*,b.registrant_id handler_id from TJY2_QUALITY_SGCJJYBG b "+  
		  	   "where b.status in('PASS','CANCEL') union select b.*,registrant_id from TJY2_QUALITY_SGCJJYBG b "+   
		  	   "where registrant_id = '" + uId +"' ) order by IDENTIFIER desc,REGISTRANT_DATE desc"; %>
		<%System.out.print("=========我是质量部负责人！！"); %>
	</sec:authorize>
	
	<sec:authorize access="hasRole('TJY2_ZL_SGRJFZR')">
	<%sql1="select * from (select b.*,t.handler_id from TJY2_QUALITY_SGCJJYBG b, v_pub_worktask t "+
		       "where b.id=t.SERVICE_ID "+   
		       "and  t.WORK_TYPE  like   '%TJY2_ZL_SGCJJYBG%' and t.STATUS='0' and t.handler_id = '"+uId+"' union "+ 
			   "select b.*,b.registrant_id handler_id from TJY2_QUALITY_SGCJJYBG b "+  
		  	   "where b.status in('PASS','CANCEL') union select b.*,registrant_id from TJY2_QUALITY_SGCJJYBG b "+   
		  	   "where registrant_id = '" + uId +"' ) order by IDENTIFIER desc,REGISTRANT_DATE desc"; %>
		<%System.out.println("=========我是软件负责人！！"); 
		System.out.println(sql1.toString()); 
		%>
	</sec:authorize>
	
	<sec:authorize access="hasRole('TJY2_ZL_GLY')"><!-- 质量管理-审核（非用章）-->
<%-- 		<%sql1="select t.*,t.CREATERID handler_id from TJY2_QUALITY_TXWJSP_SEALREG t order by t.identifier desc Nulls last "; %> --%>
		<%sql1="select * from (select b.*,t.handler_id from TJY2_QUALITY_SGCJJYBG b, v_pub_worktask t "+
		       "where b.id=t.SERVICE_ID "+   
		       "and  t.WORK_TYPE  like   '%TJY2_ZL_SGCJJYBG%' and t.STATUS='0' and t.handler_id = '"+uId+"' union "+ 
			   "select b.*,b.registrant_id handler_id from TJY2_QUALITY_SGCJJYBG b "+  
		  	   "where b.status in('PASS','CANCEL') union select b.*,registrant_id from TJY2_QUALITY_SGCJJYBG b "+   
		  	   "where registrant_id = '" + uId +"' ) order by IDENTIFIER desc,REGISTRANT_DATE desc"; %>
		<%System.out.println("=========我业务经办人！！"); 
		System.out.println(sql1.toString()); 
		%>
	</sec:authorize>
	<qm:qm pageid="TJY2_ZL_SGCJJYBG" singleSelect="true" sql="<%=sql1.toString() %>">
<!-- 	登记人 --><!-- 	申请人 -->
<%-- 		<sec:authorize access="!hasRole('TJY2_ZL_GLY')"> --%>
<%-- 			<qm:param name="registrant_id" value="<%=uId%>" compare="=" /> --%>
<%-- 			<qm:param name="handler_id" compare="=" value="<%=uId%>" logic="or"/> --%>
<%-- 		</sec:authorize> --%>
<%-- 		<sec:authorize ifAnyGranted="TJY2_BMFZR"> --%>
<%--           	 <qm:param name="department_id" value="<%= useres.getDepartment().getOrgName()%>" compare="="/> --%>
<%-- 	    </sec:authorize> --%>
	</qm:qm>

</body>
</html>