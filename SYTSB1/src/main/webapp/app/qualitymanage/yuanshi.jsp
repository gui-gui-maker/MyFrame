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
CurrentSessionUser useres = SecurityUtil.getSecurityUser();
User uu = (User)useres.getSysUser();
com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
e.getId();
String userId=e.getId();
String uId = SecurityUtil.getSecurityUser().getId();
%>
<%
String user=useres.getName();

%>

<script type="text/javascript">
	var qmUserConfig = {
			sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	// 默认值，可自定义
			sp_fields:[
		         {name:"apply_name",compare: "like"},
		         {name:"department_id",compare: "like"},
		         {group:[
						{name:"apply_time", compare:">=", value:""},
						{label:"到", name:"apply_time", compare:"<=", value:"", labelAlign:"center", labelWidth:20}
					]},
				{name:"file_number",compare: "like"},
		        {name:"report_number",compare: "like"},
		        {name:"use_unit",compare: "like"},
		        {name:"next_people",compare: "like"},
				{name:"status",compare: "like"},
				{name:"NEXT_STATUS",compare: "like"}
		    ],
		tbar: [
		{text: '详情', id: 'detail', icon: 'detail', click: detail} 
		, "-",
	    {text:'增加', id:'cc',icon:'add', click: yuanshi}
		, "-", 
		{text: '修改', id: 'edit', icon: 'modify', click: edit}, 
		"-", 
		{text: '删除', id: 'del', icon: 'delete', click: del}
		, "-", 
		{text:'提交', id:'submit',icon:'submit', click: submit}
		<sec:authorize access="hasRole('TJY2_CL_LCCL')">
	    , "-", 
	    {text:'处理', id:'sh',icon:'dispose', click: shenhe}
	    </sec:authorize>
	    , "-", 
		{text:'审核过程', id:'gc',icon:'follow', click: gc}
	    , "-",
	    {text:'流转过程', id:'turnHistory',icon:'follow', click: turnHistory}
		<sec:authorize access="hasRole('TJY2_WYSZL')">
		, "-", 
		{text:'部分归还', id:'bfgh',icon:'dispose', click: bfgh}
		, "-", 
		{text:'批量归还', id:'gh',icon:'dispose', click: gh}
		</sec:authorize>
		
	    <sec:authorize access="hasRole('TJY2_WYSZLJD')">
	    , "-", 
	    {text:'部分归还', id:'bfgh',icon:'dispose', click: bfgh}
	    , "-", 
	    {text:'批量归还', id:'gh',icon:'dispose', click: gh}
	    </sec:authorize> 
	    /* <sec:authorize access="hasRole('TJY2_WYSZL')">
	    , "-", 
	    {text:'归还确认', id:'ghqr',icon:'dispose', click: ghqr}
	    </sec:authorize>
	    
		<sec:authorize access="hasRole('TJY2_WYSZLJD')">
	    , "-", 
	    {text:'归还确认', id:'ghqr',icon:'dispose', click: ghqr}
	    </sec:authorize> */
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
				var handler_ids = Qm.getValuesByName("handler_id");
				var count = Qm.getSelectedCount();//行选择个数
				var up_status = Qm.getValueByName("status");
				var up_statuss = Qm.getValuesByName("status");
				var sp_status = Qm.getValueByName("sp_status");
				var NEXT_STATUS = Qm.getValueByName("NEXT_STATUS");
				var NEXT_STATUSs = Qm.getValuesByName("NEXT_STATUS");
				var status={};
				var allowgh =  true;
		        for(var i=0;i<up_statuss.length;i++){
		        	 if(up_statuss[i]!="审核通过" || (NEXT_STATUSs[i]!="未归还" && NEXT_STATUSs[i]!="部分归还")){
		        		 allowgh =  false;
		        	 }
		        }
				if(count==0){
					status={detail:false, edit:false, del:false,submit:false,bfgh:false,gh:false,ghqr:false,gc:false,turnHistory:false,sh: false,deal: false};
				}else if(count==1){
					if("已提交"==up_status){
						if("undefined"==sp_status){
							status={detail:true, edit:false, del:true,submit:false,bfgh:false,gh:false,ghqr:false,gc:false,turnHistory:true,sh: true,deal: true};
						}else{
							status={detail:true, edit:false, del:false,submit:false,bfgh:false,gh:false,ghqr:false,gc:false,turnHistory:true,sh: true,deal: true};
						}
					}else if("审核通过"==up_status){
						if("undefined"==sp_status){
							if(NEXT_STATUS=="未归还"){
								status={detail:true, edit:false, del:false,submit:false,bfgh:true,gh:true,ghqr:false,gc:true,turnHistory:true,sh: false,deal: true};
							}else if(NEXT_STATUS=="已归还"){
								status={detail:true, edit:false, del:false,submit:false,bfgh:false,gh:false,ghqr:true,gc:true,turnHistory:true,sh: false,deal: true};
							}else if(NEXT_STATUS=="部分归还"){
								status={detail:true, edit:false, del:false,submit:false,bfgh:true,gh:true,ghqr:false,gc:true,turnHistory:true,sh: false,deal: true};
							}else{
								status={detail:true, edit:false, del:false,submit:false,bfgh:false,gh:false,ghqr:false,gc:true,turnHistory:true,sh: false,deal: true};
							}
						}else{
							if(NEXT_STATUS=="未归还"){
								status={detail:true, edit:false, del:false,submit:false,bfgh:true,gh:true,ghqr:false,gc:true,turnHistory:true,sh: false,deal: true};
							}else if(NEXT_STATUS=="已归还"){
								status={detail:true, edit:false, del:false,submit:false,bfgh:false,gh:false,ghqr:true,gc:true,turnHistory:true,sh: false,deal: true};
							}else if(NEXT_STATUS=="部分归还"){
								status={detail:true, edit:false, del:false,submit:false,bfgh:true,gh:true,ghqr:false,gc:true,turnHistory:true,sh: false,deal: true};
							}else{
								status={detail:true, edit:false, del:false,submit:false,bfgh:false,gh:false,ghqr:false,gc:true,turnHistory:true,sh: false,deal: true};
							}
						}
					}else if("审核未通过"==up_status){
						if("undefined"==sp_status){
							status={detail:true, edit:false, del:false,submit:false,bfgh:false,turnHistory:true,gh:false,ghqr:false,gc:true,sh: false,deal: true};
						}else{
							status={detail:true, edit:false, del:true,submit:false,bfgh:false,turnHistory:true,gh:false,ghqr:false,gc:true,sh: false,deal: true};
						}
					}else if("审核中"==up_status){
						if("undefined"==sp_status){
							status={detail:true, edit:false, del:false,submit:false,bfgh:false,turnHistory:true,gh:false,ghqr:false,gc:true,sh: true,deal: true};
						}else{
							status={detail:true, edit:false, del:false,submit:false,bfgh:false,turnHistory:true,gh:false,ghqr:false,gc:true,sh: true,deal: true};
						}
					} else{
						status={detail:true, edit:true, del:true,submit:true,bfgh:false,turnHistory:false,gh:false,ghqr:false,gc:false,sh: false,deal: false};
					}
				}else{
					<sec:authorize ifAnyGranted="TJY2_ZL_WYSSH,TJY2_ZL_WYSSH2">
						var allowBatchCheck = true;
			    		for(var i=0;i<up_statuss.length;i++){
				        	 if(up_statuss[i]!="审核中"){
				        		 allowBatchCheck = false;
				        	 }
				        }
			    		status={detail:false, edit:false, del:false,submit:false,bfgh:false,turnHistory:false,gh:false,ghqr:false,gc:false,sh: allowBatchCheck,deal: false};
				    </sec:authorize>
				    <sec:authorize ifNotGranted="TJY2_ZL_WYSSH,TJY2_ZL_WYSSH2">
					    if("已提交"==sp_status){
							status={detail:false, edit:false, del:true,submit:false,bfgh:false,turnHistory:false,gh:false,ghqr:false,gc:false,sh: false,deal: true};
						}else{
							status={detail:false, edit:false, del:false,submit:false,bfgh:false,turnHistory:false,gh:true&&allowgh,ghqr:false,gc:false,sh: false,deal: true};
						}
			    	</sec:authorize>
				}
				Qm.setTbar(status);
			},
	        rowAttrRender : function(rowData, rowid) {
                var fontColor="black";
                if (rowData.status=='审核通过'){
                	if(rowData.wing>3 /* && rowData.NEXT_STATUS=="未归还" */){
                    	fontColor="#8B008B";
                    }else{
                    fontColor="green";
                    }
                }else if(rowData.status=='审核未通过') {
                    fontColor="red";
                }else if(rowData.status=='已提交') {
                    fontColor="orange";
                }else if(rowData.status=='审核中') {
                    fontColor="blue";
                }
                return "style='color:"+fontColor+"'"; 
            }
		} 
	};
	
	function shenhe(){//处理
		if(Qm.getSelectedCount()==1){
			var id = Qm.getValueByName("id");
			top.$.ajax({
	            url: "qualitymanage/QualityZssqAction/getLcid.do?id="+id,
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
	                             title:"无原始资料打印检验报告证书申请表"
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
		}else if(Qm.getSelectedCount()>1){
			var ids = Qm.getValuesByName("id");
			var file_numbers = Qm.getValuesByName("file_number");
			var m = $.ligerDialog.open(
					{
					width: 280,
					type: 'donne',
					title:'批量审核',
					content: '所选文件编号：'+file_numbers,
					buttons: [{ text: '通过', onclick: function (){
							top.$.ajax({
					            url: "qualitymanage/QualityZssqAction/zssq_bathCheck.do?ids="+ids+"&result=1",
					            type: "GET",
					            dataType:'json',
					            async: false,
					            success:function (data) {
					                if (data.success) {
					                	$.ligerDialog.alert(data.msg);
					                	Qm.refreshGrid();
					                	m.close();
					                }else{
					                	$.ligerDialog.error(data.msg);
					                }
					            },
					            error:function () {
					                $.ligerDialog.error("出错了！请重试！!");
					            }
					        });
						}},
						{ text: '不通过', onclick: function (){
								top.$.ajax({
						            url: "qualitymanage/QualityZssqAction/zssq_bathCheck.do?ids="+ids+"&result=0",
						            type: "GET",
						            dataType:'json',
						            async: false,
						            success:function (data) {
						                if (data.success) {
						                	$.ligerDialog.alert(data.msg);
						                	Qm.refreshGrid();
						                	m.close();
						                }else{
						                	$.ligerDialog.error(data.msg);
						                }
						            },
						            error:function () {
						                $.ligerDialog.error("出错了！请重试！!");
						            }
						        });
							}},
						{ text: '关闭', onclick: function (){
							m.close();}
						}]
					});
		}
	}
	function look(){//查看审批进度
		var id = Qm.getValueByName("id");trackServiceProcess(id);
// 		top.$.ajax({
//             url: "qualitymanage/QualityZssqAction/getprocess_id.do?id="+id,
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
	
	function ghqr(){
		var id = Qm.getValueByName("id");
	       $.ligerDialog.confirm('是否确认归还？', function (yes){
	        	
	        if(!yes){return false;}
	         $("body").mask("提交中...");
	                    top.$.ajax({
	                         url: "qualitymanage/QualityZssqAction/ghqr.do?id="+id,
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
	                
	        });
	}
	function yuanshi(){
		top.$.dialog({
			width: 900,
			height: 600,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "增加",
			content: 'url:app/qualitymanage/yuanshi_datail.jsp?&pageStatus=add'
		});
	}
	function submit() {
		var id = Qm.getValueByName("id");
        if(!id){
//            $.ligerDialog.alert("请先选择要提交审核的数据！");
           // var manager = $.ligerDialog.waitting('请先选择要提交审核的数据！');
            //setTimeout(function (){manager.close();}, 2000);
            $.ligerDialog.warn('请先选择要提交审核的数据！',3);

            return;
        }
        $.ligerDialog.confirm('是否提交审核？', function (yes){
        	
        if(!yes){return false;}
         $("body").mask("提交中...");
         getServiceFlowConfig("TJY2_ZL_ZSSQ1","",function(result,data){
                if(result){
                     top.$.ajax({
                         url: "qualitymanage/QualityZssqAction/subFolws.do?id="+id+"&flowId="+data.id+"&typeCode=TJY2_ZL_ZSSQ1&status="+status,
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

	function gh() {
		var ids = Qm.getValuesByName("id");
		var count = Qm.getSelectedCount();
        $.ligerDialog.confirm('是否归还所选 '+count+' 条数据包含的全部报告？', function (yes){
        if(!yes){return false;}
        $("body").mask("提交中...");
        top.$.ajax({
             url: "qualitymanage/QualityZssqAction/gh.do?ids="+ids,
             type: "GET",
             dataType:'json',
             async: false,
             success:function (data) {
                 if (data) {
                   // $.ligerDialog.alert("提交成功！！！");
                    top.$.notice('操作成功！！！',3);
                    Qm.refreshGrid();
                    $("body").unmask();
                 }
             },
             error:function () {
            	 $.ligerDialog.error('出错了！请重试！！!',3);
                 $("body").unmask();
             }
         }); 
                
        });
	}
	
	function bfgh() {
		var id = Qm.getValueByName("id").toString();
		if(!id){
	        // $.ligerDialog.alert("请先选择要查看的数据！");
	        var manager = $.ligerDialog.waitting('请先选择要归还的数据！');
	        setTimeout(function (){manager.close();}, 4000);
	        return;
	    }
		var file_number=Qm.getValueByName("file_number");
		top.$.dialog({
			width: 900,
			height: 500,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "\""+file_number+"\""+"归还选择",
			content: 'url:app/qualitymanage/yuanshi_partback.jsp?id=' + id
		});
	}

	function edit() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 900,
			height: 600,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "修改",
			content: 'url:app/qualitymanage/yuanshi_datail.jsp?id=' + id + '&pageStatus=modify'
		});
	}
	// 流转过程
	function turnHistory(){	
		top.$.dialog({
   			width : 400, 
   			height : 700, 
   			lock : true, 
   			title: "流程卡",
   			content: 'url:qualitymanage/QualityZssqAction/getFlowStep.do?id='+Qm.getValueByName("id"),
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
	function detail() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 900,
			height: 600,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "详情",
			content: 'url:app/qualitymanage/yuanshi_datail.jsp?id=' + id + '&pageStatus=detail'
		});
	}
	function del() {
		$.del(kFrameConfig.DEL_MSG, "qualitymanage/QualityZssqAction/delete.do", {
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
                <font color="orange">“橙色”</font>代表已提交，
                <font color="blue">“蓝色”</font>代表审核中，
                <font color="green">“绿色”</font>代表审核通过，
                <font color="red">“红色”</font>代表审核未通过。
                <font color="#8B008B">“紫色”</font>代表三天内未归还。
			</div>
		</div> 
	</div> 
	 <%String lists=""; %>
	 <%String sql1 = ""; %>
    <%
    String departmentId= useres.getDepartment().getId();%>
<%--     <sec:authorize access="hasRole('TJY2_BMFZR')">  --%>
<%-- 		<%lists="TJY2_QUALITY_ZSSQ"; %> --%>
<%-- 		<%System.out.print("===========你还不进来1！！"); %> --%>
<%-- 	</sec:authorize> --%>
<%-- 	    <sec:authorize access="hasRole('TJY2_ZL_YWBZ')">  --%>
<%-- 		<%lists="TJY2_QUALITY_ZSSQ"; %> --%>
<%-- 		<%System.out.print("===========你还不进来1！！"); %> --%>
<%-- 	</sec:authorize> --%>
<%-- 	<sec:authorize access="hasRole('TJY2_ZL_WYSSH')"> --%>
<%-- 		<%lists="TJY2_QUALITY_ZSSQ"; %> --%>
<%-- 		<%System.out.print("===========你还不进来1！！"); %> --%>
<%-- 	</sec:authorize> --%>
<%-- 	<sec:authorize access="hasRole('TJY2_ZL_WYSSH2 ')"> --%>
<%-- 		<%lists="TJY2_QUALITY_ZSSQ"; %> --%>
<%-- 		<%System.out.print("===========你还不进来1！！"); %> --%>
<%-- 	</sec:authorize> --%>
	
<%-- 	<sec:authorize access="hasRole('TJY2_CL_LCCL')"> --%>
<%-- 		<%lists="TJY2_QUALITY_ZSSQ"; %> --%>
<%-- 		<%System.out.print("=========你为什么要进来！！"); %> --%>
<%-- 	</sec:authorize> --%>
<%-- 	<sec:authorize access="!hasRole('TJY2_CL_LCCL')"> --%>
<%-- 		<%lists="TJY2_QUALITY_ZSSQ2"; %> --%>
<%-- 		<%System.out.print("=========你为什么要进来！！"); %> --%>
<%-- 	</sec:authorize> --%>
<%-- 	<sec:authorize access="hasRole('TJY2_ZL_GLY')"> --%>
<%-- 		<%lists="TJY2_QUALITY_ZSSQ2"; %> --%>
<%-- 		<%System.out.print("=========你为什么要进来！！"); %> --%>
<%-- 	</sec:authorize> --%>
	<sec:authorize access="!hasRole('TJY2_ZL_GLY')">
		<%sql1="select (b.next_time-b.implementation_date) wing,b.*,b.CREATE_ID handler_id from TJY2_QUALITY_ZSSQ b where b.CREATE_ID='"+uId+"' order by create_time desc Nulls last "; %>
		<%System.out.println("=========我看我自己！！"); %>
	</sec:authorize>
	
	<sec:authorize access="hasRole('TJY2_BMFZR')">
		<%sql1="select * from (select (b.next_time-b.implementation_date) wing,b.*,t.handler_id from TJY2_QUALITY_ZSSQ b, v_pub_worktask t "+
		       "where b.id=t.SERVICE_ID "+   
		       "and t.WORK_TYPE  like '%TJY2_ZL_ZSSQ1%' and t.STATUS='0' and t.handler_id = '"+uId+"' union "+ 
			   "select (b.next_time-b.implementation_date) wing,b.*,b.CREATE_ID handler_id from TJY2_QUALITY_ZSSQ b "+  
		  	   "where b.status in('PASS','NO_PASS') and b.department_id= '" + departmentId +"' union "+ 
			   "select (b.next_time-b.implementation_date) wing,b.*,b.CREATE_ID from TJY2_QUALITY_ZSSQ b "+   
		  	   "where CREATE_ID = '" + uId +"' ) order by create_time desc Nulls last "; %>
		<%System.out.println("=========我部门负责人！！"+uId+"@@@"+departmentId); %>
	</sec:authorize>
	
	<sec:authorize access="hasRole('TJY2_AC_JBR')">
				<%sql1="select * from (select (b.next_time-b.implementation_date) wing,b.*,t.handler_id from TJY2_QUALITY_ZSSQ b, v_pub_worktask t "+
		       "where b.id=t.SERVICE_ID "+   
		       "and t.WORK_TYPE  like '%TJY2_ZL_ZSSQ1%' and t.STATUS='0' and t.handler_id = '"+uId+"' union "+ 
			   "select (b.next_time-b.implementation_date) wing,b.*,b.CREATE_ID handler_id from TJY2_QUALITY_ZSSQ b "+  
		  	   "where b.status in('PASS','NO_PASS') union "+ 
			   "select (b.next_time-b.implementation_date) wing,b.*,b.CREATE_ID from TJY2_QUALITY_ZSSQ b "+   
		  	   "where CREATE_ID = '" + uId +"' ) order by create_time desc Nulls last "; %>
		<%System.out.println("=========业务服务部负责人！！"); %>
	</sec:authorize>
	
	<sec:authorize access="hasRole('TJY2_ZL_WYSSH')">
		<%sql1="select * from (select (b.next_time-b.implementation_date) wing,b.*,t.handler_id from TJY2_QUALITY_ZSSQ b, v_pub_worktask t "+
		       "where b.id=t.SERVICE_ID "+   
		       "and t.WORK_TYPE  like '%TJY2_ZL_ZSSQ1%' and t.STATUS='0' and t.handler_id = '"+uId+"' union "+ 
			   "select (b.next_time-b.implementation_date) wing,b.*,b.CREATE_ID handler_id from TJY2_QUALITY_ZSSQ b "+  
		  	   "where b.status in('PASS','NO_PASS') union "+ 
			   "select (b.next_time-b.implementation_date) wing,b.*,b.CREATE_ID from TJY2_QUALITY_ZSSQ b "+   
		  	   "where CREATE_ID = '" + uId +"' ) order by create_time desc Nulls last "; %>
		<%System.out.println("=========业务服务部机电负责人！！"); %>
	</sec:authorize>
	
	<sec:authorize access="hasRole('TJY2_ZL_WYSSH2')">
		<%sql1="select * from (select (b.next_time-b.implementation_date) wing,b.*,t.handler_id from TJY2_QUALITY_ZSSQ b, v_pub_worktask t "+
		       "where b.id=t.SERVICE_ID "+   
		       "and t.WORK_TYPE  like '%TJY2_ZL_ZSSQ1%' and t.STATUS='0' and t.handler_id = '"+uId+"' union "+ 
			   "select (b.next_time-b.implementation_date) wing,b.*,b.CREATE_ID handler_id from TJY2_QUALITY_ZSSQ b "+  
		  	   "where b.status in('PASS','NO_PASS') union "+ 
			   "select (b.next_time-b.implementation_date) wing,b.*,b.CREATE_ID from TJY2_QUALITY_ZSSQ b "+   
		  	   "where CREATE_ID = '" + uId +"' ) order by create_time desc Nulls last "; %>
		<%System.out.println("=========业务服务部承压负责人！！"); %>
	</sec:authorize>
	<sec:authorize access="hasRole('TJY2_WYSZLJD')">
		<%sql1="select * from (select (b.next_time-b.implementation_date) wing,b.*,t.handler_id from TJY2_QUALITY_ZSSQ b, v_pub_worktask t "+
		       "where b.id=t.SERVICE_ID "+   
		       "and t.WORK_TYPE  like '%TJY2_ZL_ZSSQ1%' and t.STATUS='0' and t.handler_id = '"+uId+"' union "+ 
			   "select (b.next_time-b.implementation_date) wing,b.*,b.CREATE_ID handler_id from TJY2_QUALITY_ZSSQ b "+  
		  	   "where b.status in('PASS','NO_PASS') union "+ 
			   "select (b.next_time-b.implementation_date) wing,b.*,b.CREATE_ID from TJY2_QUALITY_ZSSQ b "+   
		  	   "where CREATE_ID = '" + uId +"' ) order by create_time desc Nulls last "; %>
		<%System.out.println("=========业务服务部承机电责人！！"); %>
	</sec:authorize>
	
	<qm:qm pageid="TJY2_QUALITY_ZSSQ" singleSelect="false" sql="<%=sql1.toString() %>">
<%-- 	<sec:authorize access="!hasRole('TJY2_ZL_GLY')"> --%>
<%-- 		<qm:param name="CREATE_ID" value="<%=uId%>" compare="=" /> --%>
<%-- 		<qm:param name="handler_id" compare="=" value="<%=uId%>" logic="or"/> --%>
<%-- 	</sec:authorize> --%>
<%-- 	<sec:authorize access="hasRole('TJY2_ZL_GLY')"> --%>
<%-- 		<qm:param name="handler_id" compare="=" value="<%=uId%>" /> --%>
<%-- 	</sec:authorize> --%>
<%-- 	 <sec:authorize access="hasRole('TJY2_WYSZL')"> --%>
<%-- 	 <qm:param name="department_id" value="('100063','100020','100021','100022','100023','100024')" compare="in" dataType="user" /> --%>
<%-- 	</sec:authorize>  --%>
	
<%-- 	 <sec:authorize access="hasRole('TJY2_WYSZLJD')"> --%>
<%-- 	 <qm:param name="department_id" value="('100033','100034','100035','100036','100037')" compare="in" dataType="user" /> --%>
<%-- 	</sec:authorize>  --%>
<%-- 	 <sec:authorize access="!hasRole('TJY2_WYSZL')"> --%>
<%-- 	<sec:authorize access="!hasRole('TJY2_WYSZLJD')"> --%>
<%-- 	 <qm:param name="people_concerned_id" value="<%=userid %>" compare="="  /> --%>
<%-- 	 </sec:authorize>  --%>
<%-- 	 </sec:authorize>  --%>
	</qm:qm>
	<script type="text/javascript">
	Qm.config.columnsInfo.department_id.binddata=<u:dict sql="select id code, ORG_NAME text from SYS_ORG where ORG_CODE like 'jd%' or ( ORG_CODE like 'cy%' and ORG_CODE != 'cy4_1' and ORG_CODE != 'cy8') order by orders "></u:dict>;
	</script>
</body>
</html>