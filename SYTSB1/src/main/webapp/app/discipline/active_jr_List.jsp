<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.StringUtil"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<%

CurrentSessionUser usee = SecurityUtil.getSecurityUser();
User uu = (User)usee.getSysUser();
com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>业务信息查询</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript" src="pub/bpm/js/util.js"></script>
		<script type="text/javascript" src="pub/worktask/js/worktask.js"></script>
<script type="text/javascript" src="/app/fwxm/scientific/instruction/selectUser/selectUnitOrUser.js"></script>
<%
	CurrentSessionUser user = SecurityUtil.getSecurityUser();
	String userId = user.getId();
	String user_account = user.getSysUser().getAccount();
	String userType = ((com.khnt.rbac.impl.bean.Org)(user.getDepartment())).getProperty();

	String org_code = user.getDepartment().getOrgCode();
	String isDt = "";
%>
		<script type="text/javascript">
		var w = window.screen.availWidth;
		var h = window.screen.availHeight;
		var deviceType=<u:dict sql="select t.value id,t.name text from PUB_CODE_TABLE_VALUES t,pub_code_table t1 where t.code_table_id=t1.id and   t1.code = 'device_classify' "/>
		var areas = <u:dict sql="select id,regional_name text from V_AREA_CODE where ID in ('510104','510106','510109','510122','510189')"></u:dict>;
		var check_types = <u:dict code="BASE_CHECK_TYPE"></u:dict>;
		var check_deps = <u:dict sql="select id code, ORG_NAME text from SYS_ORG where ORG_CODE like 'jd%' or ORG_CODE like 'cy%' order by orders "></u:dict>;
		var reports = <u:dict sql='select id,report_name from base_reports'></u:dict>;
		var payments = <u:dict code="PAYMENT_STATUS"></u:dict>;
		var selector;
		
        var qmUserConfig = {
        	sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	// 默认值，可自定义
            sp_fields:[
                {name:"sn",compare:"like",value:""},
                {name:"jdlb",compare:"like",value:""},
            	{name:"jdfs", id:"check_unit_id", compare:"like"},
            	{name:"szbm", id:"enter_op_name", compare:"like"},
            	{name:"jdsj", id:"jdsj", compare:"="},
                {name:"state", id:"state", compare:"="}
            ],
			<tbar:toolBar type="tbar" code="zdjr"> </tbar:toolBar>,
          listeners: {
                rowDblClick :function(rowData,rowIndex){
                    detail();
                    },
                selectionChange :function(rowData,rowIndex){
                    var count=Qm.getSelectedCount();
            		var state=Qm.getValueByName("state");
            		Qm.setTbar({
    					detail : count == 1,
    					modify :  count == 1 &&  (state=='未提交' || state=='审核通过' || (<%=uu.getId().equals("402883a0515e5d76015160cc24d63c77")%> && (state=='审核通过' || state=='完结') )),
    					submit : count ==1 && state=='未提交',
    					sp : count ==1 && state!='未提交',
    					del : count == 1 && state=='未提交',
    					turnHistory:count==1,
    					fpry:count==1 && state=='审核通过',
    					gzap:count==1 && state=='审核通过',
    					wj:count==1 && state=='审核通过'
    				});
            		if(count>0){
            			Qm.setTbar({
        					sp : count ==1 && state!='未提交' && rowData.HANDLER_ID=='<%=uu.getId()%>'
        				});
            		}
            		
                },
                rowAttrRender: function(rowData, rowid) {
                	 var fontColor="black";
                	if(rowData.state=='部门负责人审核' || rowData.state=='纪检负责人审核'|| rowData.state=='纪检分管院领导审核'){
                		 fontColor='orange';
                     }else if(rowData.state=='审核通过'){
                    	 fontColor='blue';
                     }else if(rowData.state=='完结'){
                    	 fontColor='green';
                     }else if(rowData.state=='审核不通过'){
                    	 fontColor='red';
                     }
                	return "style='color:"+fontColor+"'";
                }
            }
        };
        function update(){
		var id=Qm.getValueByName("id");
		var type=Qm.getValueByName("state");
		var a=0;
        if(type=='审核通过' || type=='完结'){
        	a=4;
        }
			top.$.dialog({
				width: 900,
				height: 500, 
				lock : true, 
				title:"修改",
				content: 'url:/app/discipline/active_jr_detail.jsp?status=modify&type='+a+'&id='+id,
				data : {"window" : window}
			});
        }
		function submit(){
			var id=Qm.getValueByName("id");
			$("body").mask("正在提交请稍后...");
			getServiceFlowConfig("ZDJC_ZDJR_FLOW", "", function (result, dataflow) {
                if ( result ) {
                	 $.ajax({
                         url: "disciplineZdjrAction/subflow.do",
                         data: {
                             id: id,
                             flowId: dataflow.id
                         },
                         dataType: 'json',
                         contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                         type: "post",
                         async: true,
                         success: function (data) {
                        	 if(data.success){
                  				top.$.notice("提交成功！");
                        		 Qm.refreshGrid();
                        		 $("body").unmask("");
                        	 }
                         }
                	 });
                }
			});
			
		}
		// 详情
		function detail(id){
		var id=Qm.getValueByName("id");
			top.$.dialog({
				width: 900,
				height: 500, 
				lock : true, 
				title:"详情",
				content: 'url:/app/discipline/active_jr_detail.jsp?status=detail&type=0&id='+id,
				data : {"window" : window}
			});
		}
		
		// 流转过程
		function turnHistory(){
			top.$.dialog({
	   			width : 400, 
	   			height : 700, 
	   			lock : true, 
	   			title: "流程卡",
	   			content: 'url:com/zdsx/sqjr/getFlowStep.do?id='+Qm.getValueByName("id")+"&sn="+Qm.getValueByName("sn"),
	   			data : {"window" : window}
	   		});
		}
		
		
		function save(){
			top.$.dialog({
				width: 900,
				height: 500, 
				lock : true, 
				title:"新增",
				content: 'url:/app/discipline/active_jr_detail.jsp?type=0',
				data : {"window" : window}
			});
		}
		//审批流程
		function spFlow(){
			var id=Qm.getValueByName("id");
			var activity_id=Qm.getValueByName("activity_id");
			var type=Qm.getValueByName("state");
			var process_id=Qm.getValueByName("process_id");
			if(type=='纪检负责人审核'){
				type="2";
			}else if(type=='部门负责人审核'){
				type="1"
			}else if(type=='纪检分管院领导审核'){
				type="3"
			}
				top.$.dialog({
					width: 900,
					height: 500, 
					lock : true, 
					title:"审批",
					content: 'url:/app/discipline/active_jr_detail.jsp?status=modify&type='+type+'&id='+id+'&activity_id='+activity_id+'&process_id='+process_id,
					data : {"window" : window}
				});
		}
		//分配人员处理
		function fpry(){
			var czuserids=Qm.getValueByName("cz_user_ids");
			selector = $.ligerDialog.open({ 
				 title: '选择', 
				 width: 800, 
				 height: 400,
				 parent:api, 
				 url: 'app/discipline/choose_user_list.jsp?czuserids='+czuserids,
				 data: {"window" : window},
				 buttons: [
				    { text: '确定', onclick: f_importOK },
					{ text: '取消', onclick: f_importCancel }
				 ]
		   });
			 
		}
		function f_importOK(item, selector){
			var f_rows = selector.frame.f_select();
		    if (f_rows.length==0){
		    	$.ligerDialog.error("请选择人员信息！");
		        return;
		    }
		    var ids="";
		    var names="";
		    var nameList = new Array();
		    for(var i =0;i<f_rows.length;i++){
		    	ids=ids+f_rows[i].id+",";
		    	names=names+f_rows[i].name+",";
		    	nameList.push(f_rows[i].name);
		    }
		    ids=ids.substring(0,ids.length-1);
		    names=names.substring(0,names.length-1);
		    var czusernames=Qm.getValueByName("cz_user_names");
		    if(czusernames!=null && czusernames!=""){
		    	var czusername=czusernames.split(",");
				for(var i=0;i<czusername.length;i++){
					if($.inArray(czusername[i],nameList)<0){
		    			$.ligerDialog.error("必须包含工作人员："+czusername[i]);
		    			return false;
		    		}
				}
		    }
		    var id=Qm.getValueByName("id");
		    $("body").mask("正在处理请稍后...");
		    $.ajax({
		        url: "disciplineZdjrAction/zdjrSzCzr.do",
		        data: {'id':id,"ids":ids,"names":names},
		        type: "post",
		        async: true,
		        success: function (data) {
		       	 if(data.success){
					 $("body").unmask("");
						top.$.notice("设置成功！");					
						 selector.close();
						 Qm.refreshGrid();
		       	 }
		        }
		    });
		   
		    
		    
		   
		}
		function f_importCancel(){selector.close();}
		
		function del(){	
			$.ligerDialog.confirm(kui.DEL_MSG, function(yes) {
				if (yes) {
					$.ajax({
						url : "disciplineZdjrAction/del.do?ids=" + Qm.getValuesByName("id").toString(),
						type : "post",
						async : false,
						success : function(data) {
							if (data.success) {
								top.$.notice("删除成功！");
								Qm.refreshGrid();
							} else {
								$.ligerDialog.error('删除失败！');
							}
						}
					});
				}
			});
		}
		//工作安排
		function gzap(){

		    var id=Qm.getValueByName("id");
			top.$.dialog({
				width: 900,
				height: 500, 
				lock : true, 
				title:"工作安排",
				content: 'url:/app/discipline/active_jr_detail.jsp?status=modify&type=5&id='+id,
				data : {"window" : window}
			});
		}
		function wj(){
			$("body").mask("正在处理请稍后...");
			var id=Qm.getValueByName("id");
				$.ajax({
			        url: "disciplineZdjrAction/zdjrWj.do",
			        data: {'id':id},
			        type: "post",
			        async: true,
			        success: function (data) {
			       	 if(data.success){
						 $("body").unmask("");
							top.$.notice("办理完成！");					
							Qm.refreshGrid();
			       	 }
			        }
			    });
			
		}
		
    </script>
	</head>
	<body>
	<div class="item-tm" id="titleElement">
    <div class="l-page-title">
        <div class="l-page-title-note">提示：列表数据项
            <font color="black">“黑色”</font>代表未提交；
            <font color="orange">“橙色”</font>代表审核中；
            <font color="red">“红色”</font>代表审核不通过；
            <font color="blue">“蓝色”</font>代表审核通过；
            <font color="green">“绿色”</font>代表完结。
        </div>
    </div>
    </div>
		<qm:qm pageid="zdjdsx_zdjr" singleSelect="true">
			<% if(!user.getId().equals("402883a0515e5d76015160cc24d63c77")){ %> <!-- 不是曹伟平-->
			<qm:param name="cz_user_ids" value="<%=uu.getId() %>" compare="like" logic="or" />
			<qm:param name="HANDLER_ID" value="<%=uu.getId() %>" compare="=" logic="or"  /> 
			<qm:param name="create_user_id" value="<%=uu.getId() %>" compare="="   logic="or"/>
			<%} else{%><!-- 曹伟平可以查看全部已经审核通过的和自己提交的申请 -->
			<qm:param name="create_user_id" value="<%=usee.getId() %>" compare="="  logic="or" />
			<qm:param name="HANDLER_ID" value="<%=uu.getId() %>" compare="=" logic="or"  /> 
			<qm:param name="state" value="4" compare=">=" logic="or"/>
			<%} %>
		</qm:qm>
		<script type="text/javascript">
		 $(function(){
			// getData();
		 });
		 function getData() {
			 <% if(!user.getDepartment().getId().equals("100085")){ %>
					Qm.config.defaultSearchCondition[3].value="5";
					<%} %>
// 					Qm.searchData();
			 }
		</script>
	</body>
</html>
