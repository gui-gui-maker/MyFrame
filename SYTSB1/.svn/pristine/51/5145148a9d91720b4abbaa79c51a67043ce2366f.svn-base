<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>demo-list</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
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
var iscw="0";
var businessPart="100020,100021,100022,100023,100024,100063,100034,100035,100037,100033,100065,100036,100049,100066,100067,100068"
<sec:authorize access="hasRole('TJY2_CW_CHECK')">
iscw = "1";
</sec:authorize>
	var qmUserConfig = {
			sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
			sp_fields:[
	         {name:'IDENTIFIER',compare:'like',columnWidth:0.3},
	         {name:"status",compare: "like",columnWidth:0.3},
	         {name:"people_concerned",compare: "like",columnWidth:0.4},
	         {name:'department',compare:'like',columnWidth:0.3 },
	         {group: [
	  				{name: "total", compare: ">="},
	  				{label: "到", name: "total", compare: "<=", labelAlign: "center", labelWidth:20}
	  		 ],columnWidth:0.3},
	  		 {group: [
	  				{name: "bs_date", compare: ">="},
	  				{label: "到", name: "bs_date", compare: "<=", labelAlign: "center", labelWidth:20}
	  		 ],columnWidth:0.4}
	        ],
		tbar: [ {
			text: '详情', id: 'detail', icon: 'detail', click: detail
		}, "-", {
			text: '新增', id: 'add', icon: 'add', click: add
		}, "-", {
			text: '修改', id: 'edit', icon: 'modify', click: edit
		}, "-", {
			text: '删除', id: 'del', icon: 'delete', click: del
		}
		, "-", {
			text:' 提交', id: 'submit', icon: 'submit', click: submitData
		}
		<sec:authorize access="hasRole('TJY2_CW_CHECK')">
		, "-", {
			text:' 财务审核', id: 'submitsh', icon: 'dispose', click:submitsh
		}
    	</sec:authorize>
		, "-", {
			text:'审批记录', id: 'submityj', icon: 'bluebook', click:submityj
		}<sec:authorize access="hasRole('TJY2_CW_CHECK')">
		, "-", {
			text:'单据作废', id: 'submitshzf', icon: 'forbid', click:submitshzf
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
			del: count<0,submit:count==1
		});
	},
	selectionChange : function(rowData, rowIndex) {
		var count = Qm.getSelectedCount();
		//alert(count);
		var up_status = Qm.getValueByName("status");
	//	alert(up_status);
			
				
				var sp_status = Qm.getValueByName("sp_status");
				
				var status={};
				if(iscw !='1'){
					if(up_status=="未审批"){
						Qm.setTbar({
							detail: count==1,
							edit: count==1,
							del: count==1,
							submit:count<0,
							submitsh:count==1,
							submityj:count<0
						});
					}else if( up_status=="已作废" ) {
	                    Qm.setTbar({
	                        
	                        detail: count==1,
	                        edit: count<0,
	                        del: count<0,
	                        submit:count<0,
	                        submitsh:count<0,
	                        submityj:count==1,
	                        submitshzf:count<0
	                        });
	                    }else if (up_status=="审批通过"){
						
						Qm.setTbar({
							detail: count==1,
							edit: count<0,
							del: count<0,
							submit:count<0,
							submitsh:count<0,
							submityj:count==1
						});
						
					}else if( up_status=="审批未通过"){
						Qm.setTbar({
							detail: count==1,
							edit: count==1,
							submit:count==1,
							del: count==1,
							submityj:count==1,
							submitsh:count<0
						});
					}
					    else {
	                        Qm.setTbar({
	                
	                        detail: count==1,
	                        edit: count==1,
	                        del: count==1,
	                        submit:count==1,
	                        submitsh:count<0,
	                        submityj:count<0
	                        });
	                    }
					} else if (up_status=="未审批"){
						Qm.setTbar({
					
						detail: count==1,
						edit: count==1,
						del: count==1,
						submit:false,
						submitsh:count==1,
						submityj:count==1,
						submitshzf:count==1
					    });
					}else if(up_status=="审批未通过"){
						Qm.setTbar({
							detail: count==1,
							edit: count==1,
							submit:count==1,
							del: count==1,
							submityj:count==1,
							submitsh:count<0,
							submitshzf:count==1
						});
					} else if(up_status=="未提交"){
						  Qm.setTbar({
				                
			                    detail: count==1,
			                    edit: count==1,
			                    del: count==1,
			                    submit:true,
			                    submitsh:false,
			                    submityj:count==1,
			                    submitshzf:count==1
			                    });
					}else if( up_status=="审批通过" ) {
	                    Qm.setTbar({
	                
	                    detail: count==1,
	                    edit: count<0,
	                    del: count<0,
	                    submit:false,
	                    submitsh:false,
	                    submityj:count==1,
	                    submitshzf:count==1
	                    });
	                }else if( up_status=="已作废" ) {
	                    Qm.setTbar({
	                        
	                        detail: count==1,
	                        edit: count<0,
	                        del: count<0,
	                        submit:count<0,
	                        submitsh:count<0,
	                        submityj:count==1,
	                        submitshzf:count<0
	                        });
	                    }else {
	                    Qm.setTbar({
	                        
	                        detail: count==1,
	                        edit: count<0,
	                        del: count<0,
	                        submit:false,
	                        submitsh:false,
	                        submityj:count==1,
	                        submitshzf:count=1
	                        });
	                    }
	    			
				
				},
        rowAttrRender : function(rowData, rowid) {
                        var fontColor="black";
                        if (rowData.status=='审批通过'){
                            fontColor="green";
                        }else if(rowData.status=='审批未通过') {
                            fontColor="red";
                        }else if(rowData.status=='未审批') {
                            fontColor="orange";
                        }else if(rowData.status=='已作废') {
                            fontColor="#8E8E8E";
                        }
                        return "style='color:"+fontColor+"'";
                    }
			}

};
	
function submitshzf(){
		
        var id = Qm.getValueByName("id");
    	 
        $.ligerDialog.confirm('是否作废单据？', function (yes){
            if(!yes){return false;}
            top.$.ajax({
                         url: "lsts/carfinance/submitzf.do?id="+id,
                         type: "GET",
                         dataType:'json',
                         async: false,
                         success:function (data) {
                            if(data.success){
                                $.ligerDialog.success("已作废！");
                                Qm.refreshGrid();//刷新
                            }else{
                                $.ligerDialog.warn(data.msg);
                            }
                         },
                         error:function () {
                             $.ligerDialog.warn("提交失败！");
                         }
                     });
        }); 
        
    }
	
	 function submitData(){
	        var id = Qm.getValueByName("id");
	        $.ligerDialog.confirm('是否提交审核？', function (yes){
	            if(!yes){return false;}
	            top.$.ajax({
	                         url: "lsts/carfinance/submit.do?id="+id,
	                         type: "GET",
	                         dataType:'json',
	                         async: false,
	                         success:function (data) {
	                            if(data.success){
	                                $.ligerDialog.success("提交成功！");
	                                Qm.refreshGrid();//刷新
	                            }else{
	                                $.ligerDialog.warn(data.msg);
	                            }
	                         },
	                         error:function () {
	                             $.ligerDialog.warn("提交失败！");
	                         }
	                     });
	        });
	    }
	 function del() {
			var id = Qm.getValueByName("id");
//	 		$.del(kFrameConfig.DEL_MSG, "money/borrow/delete.do", {
//	 			"ids": Qm.getValuesByName("id").toString()
//	 		});
			$.ligerDialog.confirm('您确定要删除所选数据吗？', function (yes){
	        	if(!yes){return false;}
				top.$.ajax({
		            url: "lsts/carfinance/delete.do?ids="+id,
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
		}
	function add() {
	//默认url，没有分管领导
	var url="app/finance/carfybxd_detail.jsp?pageStatus=add";
	var dpId=<%=useres.getDepartment().getId() %>
	//如果所选单据的部门为业务部门（机电、承压），则修改url为有分管领导的页面
	if(businessPart.indexOf(dpId)>-1){
	   url="app/finance/carfybxd_detail_bs.jsp?pageStatus=add";
	}
		top.$.dialog({
			width: 900,
			height: 500,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "新增",
			content: 'url:'+url
		});
	}
	
	function submit() {
		var id = Qm.getValueByName("id");
        if(!id){
            $.ligerDialog.alert("请先选择要提交审核的数据！");
            return;
        }
        $.ligerDialog.confirm('是否提交审核？', function (yes){
        if(!yes){ 
                    return false;
                 }
         $("body").mask("提交中...");
         getServiceFlowConfig("TJY2_CW_FYBX","",function(result,data){
                if(result){
                     top.$.ajax({
                         url: "lsts/carfinance/subFolw.do?id="+id+"&flowId="+data.id+"&typeCode=TJY2_CW_FYBX&status="+status,
                         type: "GET",
                         dataType:'json',
                         async: false,
                         success:function (data) {
                             if (data) {
                                $.ligerDialog.alert("提交成功！");
                                Qm.refreshGrid();
                                $("body").unmask();
                             }
                         },
                         error:function () {
                             $.ligerDialog.alert("出错了！请重试！");
                             $("body").unmask();
                         }
                     });
                }else{
                 $.ligerDialog.alert("出错了！请重试！");
                 $("body").unmask();
                }
                
             });
        });
	}
	
	function edit() {
	var id = Qm.getValueByName("id");
	//默认url，没有分管领导
    var url="app/finance/carfybxd_detail.jsp?id=" + id + "&pageStatus=modify";
    var dpId=Qm.getValueByName("department_id");
    //如果所选单据的部门为业务部门（机电、承压），则修改url为有分管领导的页面
    if(businessPart.indexOf(dpId)>-1){
       url="app/finance/carfybxd_detail_bs.jsp?id=" + id + "&pageStatus=modify";
    }
		
		top.$.dialog({
			width: 900,
			height: 500,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "修改",
			content: 'url:'+url
		});
	}
	
	function submitsh() {
		var id = Qm.getValueByName("id");
    //默认url，没有分管领导
    var url="app/finance/carfybxd_yijian.jsp?id=" + id + "&pageStatus=modify";
    var dpId=Qm.getValueByName("department_id");
    //如果所选单据的部门为业务部门（机电、承压），则修改url为有分管领导的页面
    if(businessPart.indexOf(dpId)>-1){
       url="app/finance/carfybxd_yijian_bs.jsp?id=" + id + "&pageStatus=modify";
    }
		top.$.dialog({
			width: 900,
			height: 500,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "审核",
			content: 'url:'+url
		});
	}
	/* function del() {
		$.del(kFrameConfig.DEL_MSG, "lsts/finance/delete.do", {
			"ids": Qm.getValuesByName("id").toString()
			
		});
	} */
	function detail() {
		var id = Qm.getValueByName("id");
    //默认url，没有分管领导
    var url="app/finance/carfybxd_detail.jsp?id=" + id + "&pageStatus=detail";
    var dpId=Qm.getValueByName("department_id");
    //如果所选单据的部门为业务部门（机电、承压），则修改url为有分管领导的页面
    if(businessPart.indexOf(dpId)>-1){
       url="app/finance/carfybxd_detail_bs.jsp?id=" + id + "&pageStatus=detail";
    }
		top.$.dialog({
			width: 900,
			height: 500,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "详情",
			content: 'url:'+url
		});
	}
	
	function submityj() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 715,
			height: 350,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "详情",
			content: 'url:app/finance/cw_yijian.jsp?ids=' + id + '&pageStatus=detail'
		});
	}
	
	
	
	
	
</script>
</head>
<body>
    <div class="item-tm" id="titleElement">
        <div class="l-page-title">
            <div class="l-page-title-note">提示：列表数据项
                <font color="black">“黑色”</font>代表单据未提交，
                <font color="green">“绿色”</font>代表审批通过，
                <font color="orange">“橙色”</font>代表未审批，
                <font color="red">“红色”</font>代表审批未通过。
                <font color="#8E8E8E">“灰色”</font>代表单据作废。
            </div>
        </div>
    </div>
     <%StringBuffer sql = new StringBuffer(); %>
    <%String sql1 = ""; %>
    <%String departmentId= useres.getDepartment().getId();%>
    <sec:authorize access="!hasRole('TJY2_CW_CHECK')">
		<%sql1="select * from TJY2_FYBXD_CAR t where t.user_id='"+ userId +"' order by t.identifier desc Nulls last "; %>
		<%System.out.print("=========可怜的我只能看到自己的！！"+userId); %>    
	</sec:authorize>
	
	<sec:authorize access="hasRole('TJY2_BMFZR')">
   		<%sql1="select * from (select * from TJY2_FYBXD_CAR t where t.department_id ='"+ departmentId +"' and t.department_id !='"+ 100025 +"' union all select * from TJY2_FYBXD_CAR t where t.user_id='"+ userId +"' and t.department_id !='"+ departmentId +"') order by identifier desc Nulls last "; %>
   		<%System.out.print("=========我才是部长！！"+departmentId); %>
    </sec:authorize>

   	<sec:authorize access="hasRole('TJY2_CW_CHECK')">
   		<%sql1="select * from TJY2_FYBXD_CAR t where t.status in ('DJZF','SUBMIT','CSTG','PASS','NO_PASS','submit','REGISTER') order by t.identifier desc Nulls last "; %>
		<%System.out.print("=========我是财务部长！！"); %>       
	</sec:authorize>
	
	<qm:qm pageid="TJY2_FYBXD_CAR"  script="false" singleSelect="true" sql="<%=sql1.toString() %>">
<%-- 	<sec:authorize access="!hasRole('TJY2_CW_CHECK')"> --%>
<%-- 		<qm:param name="user_id" value="<%=userId%>" compare="=" logic="or"/> --%>
<%-- 	</sec:authorize> --%>
<%-- 		<sec:authorize ifAnyGranted="TJY2_CW_CHECK"> --%>
<%--             <qm:param name="status" value="('DJZF','SUBMIT','CSTG','PASS','NO_PASS','submit')" compare="in" dataType="user"/> --%>
<%--        </sec:authorize> --%>
	</qm:qm>
</body>
</html>