<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri="http://bpm.khnt.com" prefix="bpm"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u"%>
<head>
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%
	CurrentSessionUser user = SecurityUtil.getSecurityUser();
	User uu = (User) user.getSysUser();
	com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee) uu.getEmployee();
	e.getId();
	String userId = e.getId();
	String uId = SecurityUtil.getSecurityUser().getId();
%>
<script type="text/javascript" src="pub/worktask/js/worktask.js"></script>


<script type="text/javascript">
	var qmUserConfig = {
			sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	// 默认值，可自定义
			sp_fields:[
			 {name:"identifier",compare: "like"},
	         {name:"apply_unit_id",compare: "="},
	         {name:"proposer",compare: "like"},
	  		 {group:[
				{name:"apply_time", compare:">=", value:""},
				{label:"到", name:"apply_time", compare:"<=", value:"", labelAlign:"center", labelWidth:20}
			 ]},
	  		 {name:"report_number",compare: "like"},
	  		 {name:"status",compare: "like"}
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
	    <sec:authorize access="hasRole('TJY2_AC_JYSQ_LQGH')">
		    , "-", {
		        text:'领取', id:'lq',icon:'nav-next', click: lq
		    }, "-", {
		        text:'部分归还', id:'bfgh',icon:'nav-prev', click: bfgh
		    }, "-", {
		        text:'归还', id:'gh',icon:'nav-prev', click: gh
		    }, "-", {
		        text:'撤销归还', id:'gh2',icon:'logout', click: cxgh
		    }, "-", {
		        text:'短信提示', id:'send',icon:'outbox', click: send
		    }
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
					submit: count==1,
					gc:count==1,
					send:count==1
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
					status={detail:false, edit:false, del:false,submit:false,gc:false,lq:false,gh:false,bfgh:false,sh: false,deal: false,gh2: false,send:false};
				}else if(count==1){
					if("已提交"==up_status){
						if("undefined"==sp_status){
							status={detail:true, edit:false, del:true,submit:false,gc:true,lq:false,gh:false,bfgh:false};
						}else{
							status={detail:true, edit:false, del:false,submit:false,gc:true,lq:false,gh:false,bfgh:false,sh: true,deal: true,gh2: false};
						}
					}else if("审核中"==up_status){
						if("undefined"==sp_status){
							status={detail:true, edit:false, del:true,submit:false,gc:true,lq:false,gh:false,bfgh:false};
						}else{
							status={detail:true, edit:false, del:false,submit:false,gc:true,lq:false,gh:false,bfgh:false,sh: true,deal: true,gh2: false};
						}
					}else if("审核通过"==up_status){
						if("undefined"==sp_status){
							status={detail:true, edit:false, del:true,submit:false,gc:true,lq: true,gh:false,bfgh:false};
						}else{
							status={detail:true, edit:false, del:false,submit:false,gc:true,lq: true,gh:false,bfgh:false,sh: false,deal: true,gh2: false};
						}
					}else if("审核未通过"==up_status){
						if("undefined"==sp_status){
							status={detail:true, edit:false, del:true,submit:false,gc:true,lq:false,gh:false,bfgh:false};
						}else{
							status={detail:true, edit:false, del:true,submit:false,gc:true,lq:false,gh:false,bfgh:false,sh: false,deal: true,gh2: false};
						}
					}else if("已领取"==up_status){
						if("undefined"==sp_status){
							status={detail:true, edit:false, del:true,submit:false,gc:true,lq: false,gh:true,bfgh:true};
						}else{
							status={detail:true, edit:false, del:false,submit:false,gc:true,lq: false,gh:true,bfgh:true,sh: false,deal: true,gh2: false,send:true};
						}
					}else if("部分归还"==up_status){
						if("undefined"==sp_status){
							status={detail:true, edit:false, del:false,submit:false,gc:true,lq: false,gh:true,bfgh:true,sh: false,deal: true,gh2: true};
						}else{
							status={detail:true, edit:false, del:false,submit:false,gc:true,lq: false,gh:true,bfgh:true,sh: false,deal: true,gh2: true,send:true};
						}
					}else if("已归还"==up_status){
						if("undefined"==sp_status){
							status={detail:true, edit:false, del:false,submit:false,gc:true,lq: false,gh:false,bfgh:false,sh: false,deal: true,gh2: true};
						}else{
							status={detail:true, edit:false, del:false,submit:false,gc:true,lq: false,gh:false,bfgh:false,sh: false,deal: true,gh2: true};
						}
					}else{
						status={detail:true, edit:true, del:true,submit:true,gc:true,lq:false,gh:false,bfgh:false,sh: false,deal: false,deal: true,gh2: false};
					}
				}else{
					if("已提交"==sp_status){
						status={detail:false, edit:false, del:true,submit:false,gc:true,lq:false,gh:false,bfgh:false,sh: false,deal: true};
					}else{
						status={detail:false, edit:false, del:false,submit:false,gc:true,lq:false,gh:false,bfgh:false,sh: false,deal: true};
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
                }else if(rowData.status=='已领取') {
                    fontColor="indigo";
                }else if(rowData.status=='已归还' || rowData.status=='部分归还') {
                    fontColor="gray";
                }
                return "style='color:"+fontColor+"'";
            }
		}
	};
	
	function shenhe(){//处理
		var id = Qm.getValueByName("id");
		top.$.ajax({
            url: "archives/borrow/getLcid.do?id="+id+"&uId=<%=uId%>",
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
//             url: "archives/borrow/getprocess_id.do?id="+id,
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
	
	function lq(){
		var id = Qm.getValueByName("id");
		var org_id = Qm.getValueByName("apply_unit_id");
		if(!id){
            top.$.notice('请先选择要操作的数据！',3);
            return;
        }
		top.$.dialog({
			width: 300,
			height: 450,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "领取人",
			content: 'url:app/archives/archives_borrow_receiptor.jsp?org_id='+org_id,
			cancel: true,
            ok: function(){
                var p = this.iframe.contentWindow.getSelectedPerson();
                if(!p){
                    top.$.notice("请选择领取人！", 3, "k/kui/images/icons/dialog/32X32/hits.png");
                    return false;
                }
                top.$.ajax({
    	            url: "archives/borrow/getlq.do?id="+id+"&reportReceiptorId="+p.id,
    	            type: "GET",
    	            dataType:'json',
    	            async: false,
    	            success:function (data) {
    	                if (data) {
    	                   top.$.notice('领取成功！！！',3);
    	                   Qm.refreshGrid();
    	                   $("body").unmask();
    	                }
    	            },
    	            error:function () {
    	           	 	$.ligerDialog.error('出错了！请重试！！!',3);
    	                $("body").unmask();
    	            }
    	        });
            }
		});
        /* $.ligerDialog.confirm('是否要领取报告？', function (yes){
            if(!yes){return false;}
			top.$.ajax({
	            url: "archives/borrow/getlq.do?id="+id,
	            type: "GET",
	            dataType:'json',
	            async: false,
	            success:function (data) {
	                if (data) {
	                  // $.ligerDialog.alert("提交成功！！！");
	                   top.$.notice('领取成功！！！',3);
	
	                   Qm.refreshGrid();
	                   $("body").unmask();
	                }
	            },
	            error:function () {
	           	 $.ligerDialog.error('出错了！请重试！！!',3);
	                $("body").unmask();
	            }
	        });
        }); */
	}
	function gh(){
		var id = Qm.getValueByName("id");
		if(!id){
            top.$.notice('请先选择要查看的数据！',3);
            return;
        }
        $.ligerDialog.confirm('是否要归还报告？', function (yes){
            if(!yes){return false;}
			top.$.ajax({
	            url: "archives/borrow/getgh.do?id="+id,
	            type: "GET",
	            dataType:'json',
	            async: false,
	            success:function (data) {
	                if (data) {
	                  // $.ligerDialog.alert("提交成功！！！");
	                   top.$.notice('归还成功！！！',3);
	
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
		var identifier=Qm.getValueByName("identifier");
		top.$.dialog({
			width: 900,
			height: 500,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "\""+identifier+"\""+"归还选择",
			content: 'url:app/archives/archives_borrow_partback.jsp?id=' + id
		});
	}
	function cxgh(){
		var id = Qm.getValueByName("id");
		if(!id){
            top.$.notice('请先选择要查看的数据！',3);
            return;
        }
        $.ligerDialog.confirm('是否要撤销归还报告？', function (yes){
            if(!yes){return false;}
			top.$.ajax({
	            url: "archives/borrow/cxgetgh.do?id="+id,
	            type: "GET",
	            dataType:'json',
	            async: false,
	            success:function (data) {
	                if (data) {
	                   top.$.notice('撤销成功！！！',3);
	
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
	function gc(){
		var id = Qm.getValueByName("id");
		if(!id){
            top.$.notice('请先选择要查看的数据！',3);
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
//            $.ligerDialog.alert("请先选择要提交审核的数据！");
           // var manager = $.ligerDialog.waitting('请先选择要提交审核的数据！');
            //setTimeout(function (){manager.close();}, 2000);
            $.ligerDialog.warn('请先选择要提交审核的数据！',3);

            return;
        }
        $.ligerDialog.confirm('是否提交审核？', function (yes){
        if(!yes){return false;}
         $("body").mask("提交中...");
         getServiceFlowConfig("TJY2_FN_JYSQ1","",function(result,data){
                if(result){
                     top.$.ajax({
                         url: "archives/borrow/subFolws.do?id="+id+"&flowId="+data.id+"&typeCode=TJY2_FN_JYSQ1&status="+status,
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
			width: 400,
			height: 150,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "选择借阅报告类型",
			content: 'url:app/archives/archives_dx.jsp'
		});
	}
	/* function add() {
		top.$.dialog({
			width: 900,
			height: 450,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "申请",
			content: 'url:app/archives/archives_borrow_detail.jsp?pageStatus=add'
		});
	} */

	function edit() {
		var id = Qm.getValueByName("id");
		var isSg = Qm.getValueByName("is_sg");
		top.$.dialog({
			width: 900,
			height: 450,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "修改",
			content: 'url:app/archives/archives_borrow_detail.jsp?id=' + id + '&pageStatus=modify'+'&isSg='+isSg
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
			content: 'url:app/archives/archives_borrow_detail.jsp?id=' + id + '&pageStatus=detail'
		});
	}
	function del() {
		$.del(kFrameConfig.DEL_MSG, "archives/borrow/delete.do", {
			"ids": Qm.getValuesByName("id").toString()
		});
	}
	function send(){
		$.ligerDialog.confirm('是否发送消息？', function (yes){
			if(!yes){return false;}
			$.ajax({
		    	url: "archives/borrow/sendMessage.do?ids="+Qm.getValuesByName("id").toString(),
		        type: "POST",
		        datatype: "json",
		        contentType: "application/json; charset=utf-8",
		        success: function (resp) {
		        	$.ligerDialog.alert(resp.data);
		            Qm.refreshGrid();
		        },
		        error: function (resp) {
		        	$.ligerDialog.alert(resp.data);
		        }
		    });
		});
	  }
</script>
</head>
<body>
	<div class="item-tm" id="titleElement">
		<div class="l-page-title">
			<div class="l-page-title-note">
				提示：列表数据项 <font color="black">“黑色”</font>代表单据未提交， <font
					color="orange">“橙色”</font>代表未审核， <font color="blue">“蓝色”</font>代表审核中，
				<font color="green">“绿色”</font>代表审核通过， <font color="red">“红色”</font>代表审核未通过，
				<font color="indigo">“紫色”</font>代表报告已领取，<br /> <font color="gray">“灰色”</font>代表报告部分归还/已归还。
			</div>
		</div>
	</div>
	<%
		String sql1 = "";
	%>
	<%
		CurrentSessionUser useres = SecurityUtil.getSecurityUser();
		String departmentId = useres.getDepartment().getId();
	%>
	<%
		String lists = "";
	%>
	<sec:authorize access="!hasRole('TJY2_AC_JBR')">
		<%
			sql1 = "select t.*,t.proposer_id handler_id from TJY2_ARCHIVES_BORROW t where t.proposer_id='" + userId
						+ "' order by t.identifier desc Nulls last ";
		%>
		<%
			System.out.print("=========我看我自己！！" + uId);
		%>
	</sec:authorize>

	<sec:authorize access="hasRole('TJY2_BMFZR')">
		<!--TJY2_BMFZR  -->
		<%
			sql1 = "select * from (select b.*,t.handler_id from TJY2_ARCHIVES_BORROW b, v_pub_worktask t "
						+ "where b.id=t.SERVICE_ID "
						+ "and  t.WORK_TYPE  like   '%TJY2_FN_JYSQ1%' and t.STATUS='0' and t.handler_id = '" + uId
						+ "' union " + "select b.*,b.proposer_id handler_id from TJY2_ARCHIVES_BORROW b "
						+ "where b.status in('PASS','NO_PASS','LQ','GH','BFGH') and b.APPLY_UNIT_ID= '" + departmentId
						+ "' union " + "select b.*,proposer_id from TJY2_ARCHIVES_BORROW b " + "where proposer_id = '"
						+ userId + "' ) order by IDENTIFIER desc";
				//如果部门负责人是院领导韩绍义则进行特殊处理
				if (uId.equals("402883a0552937ce015529b10cfd5010")) {
					sql1 = "select * from (select * from (select b.*,t.handler_id,t.org_id from TJY2_ARCHIVES_BORROW b, v_pub_worktask_add_position t "
							+ "where b.id=t.SERVICE_ID and  t.WORK_TYPE  like   '%TJY2_FN_JYSQ1%' and t.STATUS='0') s where s.apply_unit_id = '100030' "
							+ "and s.handler_id= '" + uId + "' union "
							+ "select b.*,b.proposer_id handler_id,b.apply_unit_id org_id from TJY2_ARCHIVES_BORROW b "
							+ "where b.status in('PASS','NO_PASS','LQ','GH','BFGH') and b.APPLY_UNIT_ID= '100030' union "
							+ "select b.*,proposer_id,b.apply_unit_id org_id from TJY2_ARCHIVES_BORROW b "
							+ "where proposer_id = '" + userId + "') order by IDENTIFIER desc";
				}
				if (uId.equals("402883a04a055c63014a3342f08461d7")) {
					sql1 = "select * from (select * from (select b.*,t.handler_id,t.org_id from TJY2_ARCHIVES_BORROW b, v_pub_worktask_add_position t "
							+ "where b.id=t.SERVICE_ID and  t.WORK_TYPE  like   '%TJY2_FN_JYSQ1%' and t.STATUS='0') s where s.apply_unit_id = '100025' "
							+ "and s.handler_id= '" + uId + "' union "
							+ "select b.*,b.proposer_id handler_id,b.apply_unit_id org_id from TJY2_ARCHIVES_BORROW b "
							+ "where b.status in('PASS','NO_PASS','LQ','GH','BFGH') and b.APPLY_UNIT_ID= '100025' union "
							+ "select b.*,proposer_id,b.apply_unit_id org_id from TJY2_ARCHIVES_BORROW b "
							+ "where proposer_id = '" + userId + "') order by IDENTIFIER desc";
				}
				if (uId.equals("402883a0515e5d76015160cebd8a3c8f")) {
					sql1 = "select * from (select * from (select b.*,t.handler_id,t.org_id from TJY2_ARCHIVES_BORROW b, v_pub_worktask_add_position t "
							+ "where b.id=t.SERVICE_ID and  t.WORK_TYPE  like   '%TJY2_FN_JYSQ1%' and t.STATUS='0') s where s.apply_unit_id = '100044' "
							+ "and s.handler_id= '" + uId + "' union "
							+ "select b.*,b.proposer_id handler_id,b.apply_unit_id org_id from TJY2_ARCHIVES_BORROW b "
							+ "where b.status in('PASS','NO_PASS','LQ','GH','BFGH') and b.APPLY_UNIT_ID= '100044' union "
							+ "select b.*,proposer_id,b.apply_unit_id org_id from TJY2_ARCHIVES_BORROW b "
							+ "where proposer_id = '" + userId + "') order by IDENTIFIER desc";
				}
		%>
		<%
			System.out.print("=========我部门负责人！！" + userId);
		%>
	</sec:authorize>

	<sec:authorize access="hasRole('TJY2_AC_JBR')">
		<!-- //业务服务部廖灿特殊处理 -->
		<%
			if (uId.equals("402884c4477c9bac01477fe0792a0018")) {
					sql1 = "select * from (select b.*,t.handler_id from TJY2_ARCHIVES_BORROW b, v_pub_worktask t "
							+ "where b.id=t.SERVICE_ID "
							+ "and  t.WORK_TYPE  like   '%TJY2_FN_JYSQ1%' and t.STATUS='0' and t.handler_id = '" + uId
							+ "' union " + "select b.*,b.proposer_id handler_id from TJY2_ARCHIVES_BORROW b "
							+ "where b.status in('PASS','NO_PASS','LQ','GH','BFGH') and b.APPLY_UNIT_ID= '"
							+ departmentId + "' union " + "select b.*,proposer_id from TJY2_ARCHIVES_BORROW b "
							+ "where proposer_id = '" + userId + "' ) order by IDENTIFIER desc";
				}
		%>
		<%
			sql1 = "select * from (select b.*,t.handler_id from TJY2_ARCHIVES_BORROW b, v_pub_worktask t "
						+ "where b.id=t.SERVICE_ID "
						+ "and  t.WORK_TYPE  like   '%TJY2_FN_JYSQ1%' and t.STATUS='0' and t.handler_id = '" + uId
						+ "' union " + "select b.*,b.proposer_id handler_id from TJY2_ARCHIVES_BORROW b "
						+ "where b.status in('PASS','NO_PASS','LQ','GH','BFGH') union "
						+ "select b.*,proposer_id from TJY2_ARCHIVES_BORROW b " + "where proposer_id = '" + userId
						+ "' ) order by IDENTIFIER desc";
		%>
		<%
			System.out.print("=========业务服务部负责人！！");
		%>
	</sec:authorize>

	<qm:qm pageid="TJY2_ARCHIVES_JY" singleSelect="true"
		sql="<%=sql1.toString()%>">
		<%-- 		<sec:authorize access="!hasRole('TJY2_AC_JBR')"> --%>
		<%-- 			<qm:param name="proposer_id" value="<%=userId%>" compare="=" /> --%>
		<%-- 		</sec:authorize> --%>
	</qm:qm>
	<script type="text/javascript">
	Qm.config.columnsInfo.apply_unit_id.binddata=<u:dict sql="select id code, ORG_NAME text from SYS_ORG t where t.status = 'used' and t.property = 'dep' and t.parent_id='100000' and t.org_name not in ('成都质监局', '区县局', '天府新区','院部') order by t.parent_id,t.orders, t.id"></u:dict>;
	</script>
</body>
</html>