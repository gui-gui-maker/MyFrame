<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>demo-list</title>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<%

CurrentSessionUser usee = SecurityUtil.getSecurityUser();
User uu = (User)usee.getSysUser();
com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
e.getId();
String userId=e.getId();
%>
<script type="text/javascript">
var iscw="0";
<sec:authorize access="hasRole('TJY2_CW_CHECK')">
iscw = "1";
</sec:authorize>
	var qmUserConfig = {
			sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},
			sp_fields:[
						 <sec:authorize ifAnyGranted="TJY2_CW_QR_Y,PL_INSPECTION,TJY2_CW_QR_XGG,TJY2_CW_QR_XS">
				  		 	{group: [
				  				{name: "IDENTIFIER", compare: ">="},
				  				{label: "到", name: "IDENTIFIER", compare: "<=", labelAlign: "center", labelWidth:20}
				  		 	]},
				  		</sec:authorize>
				  		 {name:"IDENTIFIER",compare: "like"},
				         {name:"statue",compare: "like"},
				         {name:"user_",compare: "like"},
				         {name:'cl_dw',compare:'like'},
				         {name:'department',compare:'like'},
				         {group: [
				  				{name: "cl_hj_jexj", compare: ">="},
				  				{label: "到", name: "cl_hj_jexj", compare: "<=", labelAlign: "center", labelWidth:20}
				  		 ]},
				  		 {name:'cl_sy',compare:'like'},
				  		{group: [
			  				{name: "cl_sj", compare: ">="},
			  				{label: "到", name: "cl_sj", compare: "<=", labelAlign: "center", labelWidth:20}
			  		 	]},
					  	 {group: [
				  				{name: "bx_qr_time", compare: ">="},
				  				{label: "到", name: "bx_qr_time", compare: "<=", labelAlign: "center", labelWidth:20}
				  		 ]}
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
    	<sec:authorize access="hasRole('TJY2_CW_QR_Y')">
    	, "-", {text:'报销确认', id:'bxy',icon:'dispose', click: bxy}
    	, "-", {text:'报销批量确认', id:'bxys',icon:'dispose', click: bxys}
    	, "-", {text:'金额统计', id:'bxTotal',icon:'count', click: bxTotal}
    	</sec:authorize>
		<sec:authorize access="hasRole('TJY2_CW_QR_XGG')">
    	, "-", {text:'报销确认', id:'bxgxx',icon:'dispose', click: bxgxx}
    	, "-", {text:'报销批量确认', id:'bxgxxs',icon:'dispose', click: bxgxxs}
    	, "-", {text:'金额统计', id:'bxTotal',icon:'count', click: bxTotal}
    	</sec:authorize>
    	<sec:authorize access="hasRole('TJY2_CW_QR_XS')">
    	, "-", {text:'报销确认', id:'bxxs',icon:'dispose', click: bxxs}
    	, "-", {text:'报销批量确认', id:'bxxss',icon:'dispose', click: bxxss}
    	, "-", {text:'金额统计', id:'bxTotal',icon:'count', click: bxTotal}
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
					del: count>0,submit:count==1,bxy:count==1,bxgxx:count==1,bxTotal:count>0
				});
			},
			selectionChange : function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();
			
				var up_status = Qm.getValueByName("statue");
				
				var sp_status = Qm.getValueByName("sp_status");
				
				var up_statuss = Qm.getValuesByName("statue");
				var unit = Qm.getValuesByName("cl_dw");
				var allow1=true;//状态是否为允许，默认为true
				var allow2=true;//院单位是否为允许，默认为true
				var allow3=true;//司法、公司、协会、工会单位是否为允许，默认为true
				var allow4=true;//是否允许审核，默认为true
				if(up_statuss!=null&&up_statuss!=''){
					for(var i=0;i<up_statuss.length;i++){
						if(up_statuss[i]!="审批通过"){
							allow1=false;
						}
					}
				}
				if(unit!=null&&unit!=''){
					for(var i=0;i<unit.length;i++){
						if("司法鉴定中心"==unit[i] || "鼎盛公司"==unit[i] || "四川省特种设备检验检测协会"==unit[i] || "四川省特种设备检验研究院工会委员会"==unit[i]){
							allow2=false;
						}else if("司法鉴定中心"!=unit && "鼎盛公司"!=unit && "四川省特种设备检验检测协会"!=unit && "四川省特种设备检验研究院工会委员会"!=unit){
							allow3=false;
						}
						if("司法鉴定中心"!=unit && "鼎盛公司"!=unit && "四川省特种设备检验检测协会"!=unit){
					    	<sec:authorize access="hasRole('TJY2_CW_CHECK_XSG')">
					    	allow4=false;
							</sec:authorize>
							<sec:authorize access="!hasRole('TJY2_CW_CHECK_XSG')">
					    	allow4=true;
							</sec:authorize>
						}
					}
				}
				var status={};
				if(iscw !='1'){
					if(up_status=="未审批"){
						Qm.setTbar({
							detail: count==1,
							edit: count==1,
							del: count==1,
							submit:count<0,
							submitsh:count==1&&allow4,
							submityj:count<0,
							bxy:count<0,
							bxys:count>1&&allow1&&allow2,
							bxgxx:count<0,
							bxgxxs:count>1&&allow1&&allow3,
							bxxs:count<0,
							bxxss:count>1&&allow1,
							bxTotal:count>0
						});
					}else if( up_status=="已作废" ) {
	                    Qm.setTbar({
	                        
	                        detail: count==1,
	                        edit: count<0,
	                        del: count<0,
	                        submit:count<0,
	                        submitsh:count<0,
	                        submityj:count==1,
	                        submitshzf:count<0,
	                        bxy:count<0,
	                        bxys:count>1&&allow1&&allow2,
	                        bxgxx:count<0,
	                        bxgxxs:count>1&&allow1&&allow3,
	                        bxxs:count<0,
	                        bxxss:count>1&&allow1,
	                        bxTotal:count>0
	                        });
	                    }else if (up_status=="审批通过"){
						
						Qm.setTbar({
							detail: count==1,
							edit: count<0,
							del: count<0,
							submit:count<0,
							submitsh:count<0,
							submityj:count==1,
							bxy:count==1,
							bxys:count>1&&allow1&&allow2,
							bxgxx:count==1,
							bxgxxs:count>1&&allow1&&allow3,
							bxxs:count==1,
							bxxss:count>1&&allow1,
							bxTotal:count>0
						});
						
					}else if( up_status=="审批未通过"){
						Qm.setTbar({
							detail: count==1,
							edit: count==1,
							submit:count==1,
							del: count==1,
							submityj:count==1,
							submitsh:count<0,
							bxy:count<0,
							bxys:count>1&&allow1&&allow2,
							bxgxx:count<0,
							bxgxxs:count>1&&allow1&&allow3,
							bxxs:count<0,
							bxxss:count>1&&allow1,
							bxTotal:count>0
						});
					}else if( up_status=="已报销"){
						Qm.setTbar({
							detail: count==1,
							edit: count<0,
							del: count<0,
							submit:count<0,
							submitsh:count<0,
							submityj:count==1,
							bxy:count==1,
							bxys:count>1&&allow1&&allow2,
							bxgxx:count==1,
							bxgxxs:count>1&&allow1&&allow3,
							bxxs:count==1,
							bxxss:count>1&&allow1,
							bxTotal:count>0
						});
					}
					    else {
	                        Qm.setTbar({
	                
	                        detail: count==1,
	                        edit: count==1,
	                        del: count==1,
	                        submit:count==1,
	                        submitsh:count<0,
	                        submityj:count<0,
	                        bxy:count<0,
	                        bxys:false,
							bxgxx:count<0,
							bxgxxs:false,
							bxxs:count<0,
							bxxss:false,
							bxTotal:false
	                        });
	                    }
					} else if (up_status=="未审批"){
						Qm.setTbar({
					
						detail: count==1,
						edit: count==1,
						del: count==1,
						submit:false,
						submitsh:count==1&&allow4,
						submityj:count==1,
						submitshzf:count==1,
						bxy:count<0,
						bxys:count>1&&allow1&&allow2,
						bxgxx:count<0,
						bxgxxs:count>1&&allow1&&allow3,
						bxxs:count<0,
						bxxss:count>1&&allow1,
						bxTotal:count>0
					    });
					}else if(up_status=="审批未通过"){
						Qm.setTbar({
							detail: count==1,
							edit: count==1,
							submit:count==1,
							del: count==1,
							submityj:count==1,
							submitsh:count<0,
							submitshzf:count==1,
							bxy:count<0,
							bxys:count>1&&allow1&&allow2,
							bxgxx:count<0,
							bxgxxs:count>1&&allow1&&allow3,
							bxxs:count<0,
							bxxss:count>1&&allow1,
							bxTotal:count>0
						});
					} else if(up_status=="未提交"){
						  Qm.setTbar({
				                
			                    detail: count==1,
			                    edit: count==1,
			                    del: count==1,
			                    submit:count==1,
			                    submitsh:false,
			                    submityj:count==1,
			                    submitshzf:count==1,
			                    bxy:count<0,
			                    bxys:count>1&&allow1&&allow2,
								bxgxx:count<0,
								bxgxxs:count>1&&allow1&&allow3,
								bxxs:count<0,
								bxxss:count>1&&allow1,
								bxTotal:count>0
			                    });
					}else if( up_status=="审批通过" ) {
	                    Qm.setTbar({
	                
	                    detail: count==1,
	                    edit: count<0,
	                    del: count<0,
	                    submit:false,
	                    submitsh:false,
	                    submityj:count==1,
	                    submitshzf:count==1,
	                    bxy:count==1,
	                    bxys:count>1&&allow1&&allow2,
						bxgxx:count==1,
						bxgxxs:count>1&&allow1&&allow3,
						bxxs:count==1,
						bxxss:count>1&&allow1,
						bxTotal:count>0
	                    });
	                }else if( up_status=="已作废" ) {
	                    Qm.setTbar({
	                        
	                        detail: count==1,
	                        edit: count<0,
	                        del: count<0,
	                        submit:count<0,
	                        submitsh:count<0,
	                        submityj:count==1,
	                        submitshzf:count<0,
	                        bxy:count<0,
	                        bxys:count>1&&allow1&&allow2,
							bxgxx:count<0,
							bxgxxs:count>1&&allow1&&allow3,
							bxxs:count<0,
							bxxss:count>1&&allow1,
							bxTotal:count>0
	                        });
	                    }else if( up_status=="已报销"){
							Qm.setTbar({
								detail: count==1,
								edit: count<0,
								del: count<0,
								submit:count<0,
								submitsh:count<0,
								submityj:count==1,
								bxy:count==1,
								bxys:count>1&&allow1&&allow2,
								bxgxx:count==1,
								bxgxxs:count>1&&allow1&&allow3,
								bxxs:count==1,
								bxxss:count>1&&allow1,
								bxTotal:count>0
							});
						}else {
	                    Qm.setTbar({
	                        
	                        detail: count==1,
	                        edit: count<0,
	                        del: count<0,
	                        submit:false,
	                        submitsh:false,
	                        submityj:count==1,
	                        submitshzf:count=1,
	                        bxy:count<0,
	                        bxys:false,
							bxgxx:count<0,
							bxgxxs:false,
							bxxs:count<0,
							bxxss:false,
							bxTotal:false
	                        });
	                    }
	    			
				
				},
			rowAttrRender : function(rowData, rowid) {
                        var fontColor="black";
                        if (rowData.statue=='审批通过'){
                            fontColor="green";
                        }else if(rowData.statue=='审批未通过') {
                            fontColor="red";
                        }else if(rowData.statue=='未审批') {
                            fontColor="orange";
                        }else if(rowData.statue=='已作废') {
                            fontColor="#8E8E8E";
                        }else if(rowData.statue=='已报销'){
                          	 fontColor="#800080";
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
                         url: "finance/pxfbxd/invalidate.do?id="+id,
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
                         url: "finance/pxfbxd/submit.do?id="+id,
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
		            url: "finance/pxfbxd/delete.do?ids="+id,
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
		top.$.dialog({
			width: 900,
			height: 520,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "新增",
			content: 'url:app/finance/pxfbxd_detail.jsp?pageStatus=add'
		});
	}
	function edit() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 900,
			height: 520,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "修改",
			content: 'url:app/finance/pxfbxd_detail.jsp?id=' + id + '&pageStatus=modify'
		});
	}
	function detail() {
		var id = Qm.getValueByName("id");
		
		top.$.dialog({
			width: 900,
			height: 620,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "详情",
			content: 'url:app/finance/pxfbxd_detail_print.jsp?id=' + id + '&pageStatus=detail'
		});
	}
	function note(){
		top.$.dialog({
			width: 900,
			height: 520,
			lock: true,
			parent: api,
			title: "示例说明",
			content: $("#note").html()
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
	
	function submitsh() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 900,
			height: 520,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "审核",
			content: 'url:app/finance/pxfbxd_yijian.jsp?id=' + id + '&pageStatus=modify'
		});
	}
	function bxy() {
		var id = Qm.getValueByName("id");
		var bxstatus = Qm.getValueByName("statue");
		var unit = Qm.getValueByName("cl_dw");
		var a="a";
		if(bxstatus=="审批通过"){
			bxstatus="0";
		}else if(bxstatus=="已报销"){
			bxstatus="1";
		}
		if("司法鉴定中心"==unit || "鼎盛公司"==unit || "四川省特种设备检验检测协会"==unit || "四川省特种设备检验研究院工会委员会"==unit || "中共四川省特种设备检验研究院委员会"==unit){
   		 $.ligerDialog.warn("您不是“司法、协会、公司、工会”财务确认人，不能操作此条数据！");
   	 	}else{
		top.$.dialog({
			width: 900,
			height: 500,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "报销确认",
			content: 'url:app/finance/pxfbxd_detail.jsp?id='+id+'&a='+a+'&pageStatus=detail&bxstatus='+bxstatus
		});}
	}
	function bxys() {
		var ids = Qm.getValuesByName("id");
		$.ligerDialog.confirm('您确定要确认所选数据吗？', function (yes){
        	if(!yes){return false;}
			top.$.ajax({
	            url: "finance/pxfbxd/bxsubmits.do?ids="+ids,
	            type: "POST",
	            dataType:'json',
	            async: false,
	            success:function (data) {
	               if(data.success){
	                  top.$.notice("操作成功！",3);
	                   Qm.refreshGrid();//刷新
	               }else{
	                   $.ligerDialog.warn("操作失败！");
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
	function bxgxx() {
		var id = Qm.getValueByName("id");
		var bxstatus = Qm.getValueByName("statue");
		var unit = Qm.getValueByName("cl_dw");
		var a="a";
		if(bxstatus=="审批通过"){
			bxstatus="0";
		}else if(bxstatus=="已报销"){
			bxstatus="1";
		}
		if("司法鉴定中心"!=unit && "鼎盛公司"!=unit && "四川省特种设备检验检测协会"!=unit && "四川省特种设备检验研究院工会委员会"!=unit && "中共四川省特种设备检验研究院委员会"!=unit){
   		 $.ligerDialog.warn("您不是“院”财务确认人，不能操作此条数据！");
   	 	}else{
		top.$.dialog({
			width: 900,
			height: 500,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "报销确认",
			content: 'url:app/finance/pxfbxd_detail.jsp?id='+id+'&a='+a+'&pageStatus=detail&bxstatus='+bxstatus
		});}
	}
	function bxgxxs() {
		var ids = Qm.getValuesByName("id");
		$.ligerDialog.confirm('您确定要确认所选数据吗？', function (yes){
        	if(!yes){return false;}
			top.$.ajax({
	            url: "finance/pxfbxd/bxsubmits.do?ids="+ids,
	            type: "POST",
	            dataType:'json',
	            async: false,
	            success:function (data) {
	               if(data.success){
	                  top.$.notice("操作成功！",3);
	                   Qm.refreshGrid();//刷新
	               }else{
	                   $.ligerDialog.warn("操作失败！");
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
	function bxxs() {
		var id = Qm.getValueByName("id");
		var bxstatus = Qm.getValueByName("statue");
		var unit = Qm.getValueByName("cl_dw");
		var a="a";
		if(bxstatus=="审批通过"){
			bxstatus="0";
		}else if(bxstatus=="已报销"){
			bxstatus="1";
		}
		if("司法鉴定中心"!=unit && "四川省特种设备检验检测协会"!=unit){
			$.ligerDialog.warn("只能确认司法鉴定中心和协会的财务报销！");
   	 	}else{
		top.$.dialog({
			width: 900,
			height: 500,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "报销确认",
			content: 'url:app/finance/pxfbxd_detail.jsp?id='+id+'&a='+a+'&pageStatus=detail&bxstatus='+bxstatus
		});}
	}
	function bxxss() {
		var ids = Qm.getValuesByName("id");
		$.ligerDialog.confirm('您确定要确认所选数据吗？', function (yes){
        	if(!yes){return false;}
			top.$.ajax({
	            url: "finance/pxfbxd/bxsubmits.do?ids="+ids,
	            type: "POST",
	            dataType:'json',
	            async: false,
	            success:function (data) {
	               if(data.success){
	                  top.$.notice("操作成功！",3);
	                   Qm.refreshGrid();//刷新
	               }else{
	                   $.ligerDialog.warn("操作失败！");
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
	// 报销金额合计
	function bxTotal(){
		var count = Qm.getSelectedCount();
		var amount = Qm.getValuesByName("cl_hj_jexj").toString();
		var point_positions = new Array();
		var positions = new Array();
		var newAmount = new Array();
		point_positions=searchSubStr(amount,".");
		if (point_positions != null && point_positions.length > 0) {
			for ( var i = 0; i < point_positions.length; i++) {
				positions.push(point_positions[i]+2);
			}
		}
		if (positions != null && positions.length > 0) {
			for ( var j = 0; j < positions.length; j++) {
				if(j == 0){
					newAmount.push(amount.substring(j,positions[j]+1).replace(/,/g,''));
				}else{
					newAmount.push(amount.substring(positions[j-1]+2,positions[j]+1).replace(/,/g,''));
				}
			}
		}
		doTotal(count,newAmount,"报销金额");
	}
	function doTotal(count,newAmount,title){
		var total = 0;
		if (newAmount != null && newAmount.length > 0) {
			for ( var i = 0; i < newAmount.length; i++) {
				if(newAmount[i]==''||newAmount[i]==null){
					newAmount[i]=0;
				}
				total = accAdd(total,parseFloat(newAmount[i]));
			}
			$.ligerDialog.alert('您已选择'+count+'条数据，合计金额：' + total + "元。");
		}
	}
	function accAdd(arg1,arg2){ 
		var r1,r2,m; 
		try{
		r1=arg1.toString().split(".")[1].length
		}catch(e){
		r1=0} try{
		r2=arg2.toString().split(".")[1].length}catch(e){r2=0} m=Math.pow(10,Math.max(r1,r2)) 
		return (arg1*m+arg2*m)/m
		}
	/*
	 * 查找一个字符串中的某个子串的所有位置
	 */
	function searchSubStr(str,subStr){
		var positions = new Array();
	    var pos = str.indexOf(subStr);
	    while(pos>-1){
	        positions.push(pos);
	        pos = str.indexOf(subStr,pos+1);
	    }
	    return positions
	}
</script>
</head>
<body>
    <div class="item-tm" id="titleElement">
        <div class="l-page-title">
            <div class="l-page-title-note">提示：列表数据项
                <font color="black">“黑色”</font>代表单据未提交，
                <font color="orange">“橙色”</font>代表未审批，
                <font color="green">“绿色”</font>代表审批通过，
                <font color="red">“红色”</font>代表审批未通过，
                <font color="#800080">“紫色”</font>代表已报销，
                <font color="#8E8E8E">“灰色”</font>代表单据作废。
            </div>
        </div>
    </div>
    
     <%StringBuffer sql = new StringBuffer(); %>
    <%String sql1 = ""; %>
    <%CurrentSessionUser useres = SecurityUtil.getSecurityUser();
    String departmentId= useres.getDepartment().getId();%>
    <sec:authorize access="!hasRole('TJY2_CW_CHECK')">
		<%sql1="select * from TJY2_PXFBXD t where t.user_id='"+ userId +"' order by t.identifier desc Nulls last "; %>
		<%System.out.print("=========可怜的我只能看到自己的！！"+userId); %>    
	</sec:authorize>
	
	<sec:authorize access="hasRole('TJY2_BMFZR')">
   		<%sql1="select * from (select * from TJY2_PXFBXD t where t.department_id ='"+ departmentId +"' and t.department_id !='"+ 100025 +"' union all select * from TJY2_PXFBXD t where t.user_id='"+ userId +"' and t.department_id !='"+ departmentId +"') order by identifier desc Nulls last "; %>
   		<%System.out.print("=========我是部长哒！！"+departmentId); %>
    </sec:authorize>

   	<sec:authorize access="hasRole('TJY2_CW_CHECK')">
   		<%sql1="select * from (select * from TJY2_PXFBXD t where t.statue in ('DJZF','SUBMIT','CSTG','PASS','NO_PASS','submit','BXYES','BXNO') union select * from TJY2_PXFBXD t where t.user_id='"+ userId +"')order by identifier desc Nulls last "; %>
		<sec:authorize access="hasRole('TJY2_CW_CHECK_XSG')">
			<%sql1="select t0.* from ( "+
				"select t.*from TJY2_PXFBXD t where t.statue in ('DJZF', 'SUBMIT', 'CSTG', 'PASS', 'NO_PASS', 'BXYES', 'BXNO') and t.cl_dw in ('xh', 'sfjd', 'ds') "+
				"union "+
				"select t1.*from TJY2_PXFBXD t1 where t1.user_id = '"+ userId +"') t0 "+
				"order by t0.identifier desc Nulls last"; %>
   		</sec:authorize>
   		<sec:authorize access="hasRole('TJY2_CW_CHECK_XS')">
			<%sql1="select t0.* from ( "+
				"select t.*from TJY2_PXFBXD t where t.statue in ('DJZF', 'SUBMIT', 'CSTG', 'PASS', 'NO_PASS', 'BXYES', 'BXNO') and t.cl_dw in ('xh', 'sfjd') "+
				"union "+
				"select t1.*from TJY2_PXFBXD t1 where t1.user_id = '"+ userId +"') t0 "+
				"order by t0.identifier desc Nulls last"; %>
   		</sec:authorize>
		<%System.out.print("=========我是财务报销审核人！！"); %>       
	</sec:authorize>
    <sec:authorize access="hasRole('TJY2_CW_QR_Y')">
   		<%sql1="select * from (select * from TJY2_PXFBXD t where t.statue in ('CSTG','BXYES','BXNO') and t.cl_dw not in ('xh','gh','ds','sfjd','zgsctjywyh') union select * from TJY2_PXFBXD t where t.user_id='"+ userId +"')order by identifier desc Nulls last "; %>
		<%System.out.print("=========我是院报销、还款确认人！！"); %>       
	</sec:authorize>
	<sec:authorize access="hasRole('TJY2_CW_QR_XGG')">
   		<%sql1="select * from (select * from TJY2_PXFBXD t where t.statue in ('CSTG','BXYES','BXNO') and t.cl_dw in ('xh','gh','ds','sfjd','zgsctjywyh')  union select * from TJY2_PXFBXD t where t.user_id='"+ userId +"')order by identifier desc Nulls last "; %>
		<%System.out.print("=========我是协会、公司、工会、中共委员会报销、还款确认人！！"); %>  
		<sec:authorize access="hasRole('TJY2_CW_CHECK')">
   			<%sql1="select * from (select * from TJY2_PXFBXD t where t.statue in ('DJZF','SUBMIT','CSTG','PASS','NO_PASS','submit','BXYES','BXNO') union select * from TJY2_PXFBXD t where t.user_id='"+ userId +"')order by identifier desc Nulls last "; %>
		</sec:authorize>     
	</sec:authorize>
	<qm:qm pageid="TJY2_PXFBXD"  script="false" singleSelect="false" sql="<%=sql1.toString() %>">
<%-- 	<sec:authorize access="!hasRole('TJY2_CW_CHECK')"> --%>
<%-- 		<qm:param name="user_id" value="<%=userId%>" compare="=" /> --%>
<%-- 	</sec:authorize> --%>
<%-- 		<sec:authorize ifAnyGranted="TJY2_CW_CHECK"> --%>
<%--             <qm:param name="statue" value="('DJZF','SUBMIT','CSTG','PASS','NO_PASS','submit')" compare="in" dataType="user"/> --%>
<%--        </sec:authorize> --%>
	</qm:qm>		
</body>
</html>