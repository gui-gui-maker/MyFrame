<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%
CurrentSessionUser usee = SecurityUtil.getSecurityUser();
User uu = (User)usee.getSysUser();
com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();

String userId=e.getId();
String uId = SecurityUtil.getSecurityUser().getId();
%>
<head>
<title>退款单</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript">
var iscw="0";
<sec:authorize access="hasRole('TJY2_CW_CHECK')">
	iscw = "1";
</sec:authorize>
	var qmUserConfig = {
			sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	// 默认值，可自定义
			sp_fields:[
					  <sec:authorize ifAnyGranted="TJY2_CW_QR_Y,PL_INSPECTION,TJY2_CW_QR_XGG,TJY2_CW_QR_XS">
			  		 	{group: [
			  				{name: "number_tab", compare: ">="},
			  				{name: "number_tab", compare: "<=", labelAlign: "center", labelWidth:20}
			  		 	],columnWidth:0.3},
			  		 </sec:authorize>
			  		 {name:"number_tab",compare: "like",columnWidth:0.3},
					 {name:'status',compare:'like',columnWidth:0.3},
					 {name:'dm_name',compare:'like',columnWidth:0.3 },
					 {name:"unit",compare: "=",columnWidth:0.3},
			         {name:"department",compare: "like",columnWidth:0.3},
			         {group: [
			  				{name: "money", compare: ">="},
			  				{label: "到", name: "money", compare: "<=", labelAlign: "center", labelWidth:20}
			  		 ],columnWidth:0.3},
			  		 {name:'dm_for',compare:'like',columnWidth:0.3 },
			  		 {group: [
			  				{name: "dm_qr_date", compare: ">="},
			  				{label: "到", name: "dm_qr_date", compare: "<=", labelAlign: "center", labelWidth:20}
			  		 ],columnWidth:0.3},
			  		{group: [
		  				{name: "dm_qr_date", compare: ">="},
		  				{label: "到", name: "dm_qr_date", compare: "<=", labelAlign: "center", labelWidth:20}
		  		 ],columnWidth:0.3}
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
				        text:'提交', id:'submit',icon:'submit', click: submitData
				    }
					<sec:authorize access="hasRole('TJY2_CW_CHECK')">
			        	, "-", {text:'财务审核', id:'audit',icon:'dispose', click: audit
			        }
			        </sec:authorize>
			        	, "-", {text:'审核意见', id:'chuyi',icon:'bluebook', click: chuyi
			        }
			        <sec:authorize access="hasRole('TJY2_CW_CHECK')">
					, "-", {
						text:'单据作废', id: 'submitshzf', icon: 'forbid', click:submitshzf
					}
					</sec:authorize>
					<sec:authorize access="hasRole('TJY2_CW_QR_Y')">
		        	, "-", {text:'退款确认', id:'tky',icon:'dispose', click: tky}
		        	, "-", {text:'退款批量确认', id:'tkys',icon:'dispose', click: tkys}
		        	, "-", {text:'金额统计', id:'tkTotal',icon:'count', click: tkTotal}
		        	</sec:authorize>
					<sec:authorize access="hasRole('TJY2_CW_QR_XGG')">
		        	, "-", {text:'退款确认', id:'tkgxx',icon:'dispose', click: tkgxx}
		        	, "-", {text:'退款批量确认', id:'tkgxxs',icon:'dispose', click: tkgxxs}
		        	, "-", {text:'金额统计', id:'tkTotal',icon:'count', click: tkTotal}
		        	</sec:authorize>
		        	<sec:authorize access="hasRole('TJY2_CW_QR_XS')">
		        	, "-", {text:'退款确认', id:'tkxs',icon:'dispose', click: tkxs}
		        	, "-", {text:'退款批量确认', id:'tkxss',icon:'dispose', click: tkxss}
		        	, "-", {text:'金额统计', id:'tkTotal',icon:'count', click: tkTotal}
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
					
						var up_status = Qm.getValueByName("status");
						var up_statuss = Qm.getValuesByName("status");
						var unit = Qm.getValuesByName("unit");
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
						if(iscw !='1'){
							if(up_status=="未提交"){
								Qm.setTbar({detail: count==1,edit: count==1,del:count==1,submit:count==1,audit:count<0,chuyi:count==1,
									submityj:count<0,submitshzf:count<0,tky:count<0,tkys:count>1&&allow1&&allow2,tkgxx:count<0,tkgxxs:count>1&&allow1&&allow3,
									tkxs:count<0,tkxss:count>1&&allow1,tkTotal:count>0
								});
							}else if(up_status=="未审批"){
								Qm.setTbar({detail: count==1,edit: count==1,del:count==1,submit:count<0,audit:count==1&&allow4,chuyi:count==1,
									submityj:count<0,submitshzf:count<0,tky:count<0,tkys:count>1&&allow1&&allow2,tkgxx:count<0,tkgxxs:count>1&&allow1&&allow3,
									tkxs:count<0,tkxss:count>1&&allow1,tkTotal:count>0
								});
							}else if (up_status=="审批通过"){
								Qm.setTbar({detail: count==1,edit: count<0,del: count<0,submit:count<0,audit:count<0,chuyi:count==1,
									submityj:count==1,submitshzf:count<0,tky:count==1,tkys:count>1&&allow1&&allow2,tkgxx:count==1,tkgxxs:count>1&&allow1&&allow3,
									tkxs:count==1,tkxss:count>1&&allow1,tkTotal:count>0
								});
							}else if(up_status=="未退款"){
								Qm.setTbar({detail: count==1,edit: count<0,del: count<0,submit:false,audit:false, chuyi:count==1,submityj:count==1,
				                    submitshzf:count==1,tky:count==1,tkys:count>1&&allow1&&allow2,tkgxx:count==1,tkgxxs:count>1&&allow1&&allow3,
				                    tkxs:count==1,tkxss:count>1&&allow1,tkTotal:count>0
				                    });
							}else if(up_status=="已退款"){
								Qm.setTbar({detail: count==1,edit: count<0,del: count<0,submit:false,audit:false, chuyi:count==1,submityj:count==1,
				                    submitshzf:count<0,tky:count==1,tkys:count>1&&allow1&&allow2,tkgxx:count==1,tkgxxs:count>1&&allow1&&allow3,
				                    tkxs:count==1,tkxss:count>1&&allow1,tkTotal:count>0
				                    });
							}else if(up_status=="审批未通过"){
								Qm.setTbar({detail: count==1,edit: count==1,submit:count==1,del: count==1,submityj:count==1,
									audit:count<1,chuyi:count==1,submitshzf:count<0,tky:count<0,tkys:count>1&&allow1&&allow2,tkgxx:count<0,tkgxxs:count>1&&allow1&&allow3,
									tkxs:count<0,tkxss:count>1&&allow1,tkTotal:count>0
								});
							}else if(up_status=="已作废" ){
								Qm.setTbar({detail: count==1,edit: count<0,del: count<0,submit:count<0,audit:count<0, chuyi:count==1,submityj:count==1,
				                    submitshzf:count<0,tky:count<0,tkys:count>1&&allow1&&allow2,tkgxx:count<0,tkgxxs:count>1&&allow1&&allow3,
				                    tkxs:count<0,tkxss:count>1&&allow1,tkTotal:count>0
				                });
							}else {
			                    Qm.setTbar({detail: count==1,edit: count==1,del: count==1,submit:count==1,audit:count<0,chuyi:false,
			                        submityj:count<0,submitshzf:count<0,tky:count<0,tkys:false,tkgxx:count<0,tkgxxs:false,
			                        tkxs:count<0,tkxss:false,tkTotal:false
			                    });
		                	}
						}else if (up_status=="未提交"){
							Qm.setTbar({detail: count==1,edit: count==1,del:count==1,submit:count==1,audit:count<0,chuyi:count==1,
								submityj:count==1,submitshzf:count==1,tky:count<0,tkys:count>1&&allow1&&allow2,tkgxx:count<0,tkgxxs:count>1&&allow1&&allow3,
								tkxs:count<0,tkxss:count>1&&allow1,tkTotal:count>0
						    });
						}else if (up_status=="未审批"){
							Qm.setTbar({detail: count==1,edit: count==1,del:count==1,submit:false,audit:count==1&&allow4,chuyi:count==1,
								submityj:count==1,submitshzf:count==1,tky:count<0,tkys:count>1&&allow1&&allow2,tkgxx:count<0,tkgxxs:count>1&&allow1&&allow3,
								tkxs:count<0,tkxss:count>1&&allow1,tkTotal:count>0
						    });
						}else if( up_status=="审批未通过"){
							Qm.setTbar({detail: count==1,edit: count==1,submit:count==1,del: count==1,submityj:count==1,
								audit:count<1,chuyi:count==1,submitshzf:count==1,tky:count<0,tkys:count>1&&allow1&&allow2,tkgxx:count<0,tkgxxs:count>1&&allow1&&allow3,
								tkxs:count<0,tkxss:count>1&&allow1,tkTotal:count>0
							});
						}else if(up_status=="审批通过"){
							Qm.setTbar({detail: count==1,edit: count<0,del: count<0,submit:false,audit:false, chuyi:count==1,submityj:count==1,
			                    submitshzf:count==1,tky:count==1,tkys:count>1&&allow1&&allow2,tkgxx:count==1,tkgxxs:count>1&&allow1&&allow3,
			                    tkxs:count==1,tkxss:count>1&&allow1,tkTotal:count>0
			                    });
						}else if(up_status=="未退款"){
							Qm.setTbar({detail: count==1,edit: count<0,del: count<0,submit:false,audit:false, chuyi:count==1,submityj:count==1,
			                    submitshzf:count==1,tky:count==1,tkys:count>1&&allow1&&allow2,tkgxx:count==1,tkgxxs:count>1&&allow1&&allow3,
			                    tkxs:count==1,tkxss:count>1&&allow1,tkTotal:count>0
			                    });
						}else if(up_status=="已退款"){
							Qm.setTbar({detail: count==1,edit: count<0,del: count<0,submit:false,audit:false, chuyi:count==1,submityj:count==1,
			                    submitshzf:count<0,tky:count==1,tkys:count>1&&allow1&&allow2,tkgxx:count==1,tkgxxs:count>1&&allow1&&allow3,
			                    tkxs:count==1,tkxss:count>1&&allow1,tkTotal:count>0
			                    });
						}else if( up_status=="已作废" ){
							Qm.setTbar({detail: count==1,edit: count<0,del: count<0,submit:count<0,audit:count<0, chuyi:count==1,submityj:count==1,
			                    submitshzf:count<0,tky:count<0,tkys:count>1&&allow1&&allow2,tkgxx:count<0,tkgxxs:count>1&&allow1&&allow3,
			                    tkxs:count<0,tkxss:count>1&&allow1,tkTotal:count>0
			                    });
						}else {
		                    Qm.setTbar({detail: count==1,edit: count<0,del: count<0,submit:false,audit:false,chuyi:false,
		                    	submityj:count==1,submitshzf:count<0,tky:count<0,tkys:false,tkgxx:count<0,tkgxxs:false,
		                    	tkxs:count<0,tkxss:false,tkTotal:false
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
	                        }else if(rowData.status=='已作废'){
	                        	 fontColor="#8E8E8E";
	                        }else if(rowData.status=='已退款'){
	                       	 fontColor="#800080";
	                        }
	                        return "style='color:"+fontColor+"'";
	                    }
					}
				
				};
	
	 function audit(){
			var id = Qm.getValueByName("id");
			top.$.dialog({
				width: 900,
				height: 550,
				lock: true,
				parent: api,
				data: {
					window: window
				},
				title: "财务审批",
				content: 'url:app/finance/refundment_bill_detail.jsp?pageStatus=modify&id='+ id+"&isCheck=1"
			});
		}
	 
	 function chuyi() {
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
				title: "审批意见",
				content: 'url:app/finance/cw_yijian.jsp?ids=' + id + '&pageStatus=detail'
			});
		}
	
	 
	 function submitshzf(){
	       var id = Qm.getValueByName("id");
	       $.ligerDialog.confirm('是否作废单据？', function (yes){
	           if(!yes){return false;}
	           top.$.ajax({
	                        url: "cwRefundmentAction/submitzf.do?id="+id,
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
	                            $.ligerDialog.warn("作废失败！");
	                        }
	                    });
	       });
	   }
	
	
	
	function add(){
		top.$.dialog({
			width:900,
			height:550,
			lock:true,
			parent:api,
			data:{
				window:window
			},
			title:"新增",
			content:'url:app/finance/refundment_bill_detail.jsp?pageStatus=add'+"&isCheck=0"
		});
	}
	function edit(){
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width:900,
			height:550,
			lock:true,
			parent:api,
			data:{
				window:window
			},
			title:"修改",
			content:'url:app/finance/refundment_bill_detail.jsp?id=' + id + '&pageStatus=modify'+"&isCheck=0"
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
         getServiceFlowConfig("TJY2_CW_LK","",function(result,data){
                if(result){
                     top.$.ajax({
                         url: "cwDrawmoney/one/subFolw.do?id="+id+"&flowId="+data.id+"&typeCode=TJY2_CW_LK&status="+status,
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
	
	function detail() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 900,
			height: 550,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "详情",
			content: 'url:app/finance/refundment_bill_detail.jsp?id=' + id + '&pageStatus=detail'+"&isCheck=0"
		});
	}
	function del() {
		$.del(kFrameConfig.DEL_MSG, "cwRefundmentAction/getdelete.do", {
			"ids": Qm.getValuesByName("id").toString()
		});
	}
	
	 function submitData(){
        var id = Qm.getValueByName("id");
        $.ligerDialog.confirm('是否提交审核？', function (yes){
            if(!yes){return false;}
            top.$.ajax({
                         url: "cwRefundmentAction/submit.do?id="+id,
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
	 function tky() {
			var id = Qm.getValueByName("id");
			var tkstatus = Qm.getValueByName("status");
			var unit = Qm.getValueByName("unit");
			var a="a";
			if(tkstatus=="审批通过"){
				tkstatus="0";
			}else if(tkstatus=="已退款"){
				tkstatus="1";
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
				title: "退款确认",
				content: 'url:app/finance/refundment_bill_detail.jsp?id='+id+'&a='+a+'&pageStatus=detail&tkstatus='+tkstatus+"&isCheck=0"
			});}
		}
	 function tkys() {
			var ids = Qm.getValuesByName("id");
			$.ligerDialog.confirm('您确定要确认所选数据吗？', function (yes){
	        	if(!yes){return false;}
				top.$.ajax({
		            url: "cwRefundmentAction/tuisubmits.do?ids="+ids,
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
		function tkgxx() {
			var id = Qm.getValueByName("id");
			var tkstatus = Qm.getValueByName("status");
			var unit = Qm.getValueByName("unit");
			var a="a";
			if(tkstatus=="审批通过"){
				tkstatus="0";
			}else if(tkstatus=="已退款"){
				tkstatus="1";
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
				title: "退款确认",
				content: 'url:app/finance/refundment_bill_detail.jsp?id='+id+'&a='+a+'&pageStatus=detail&tkstatus='+tkstatus+"&isCheck=0"
			});}
		}
		function tkgxxs() {
			var ids = Qm.getValuesByName("id");
			$.ligerDialog.confirm('您确定要确认所选数据吗？', function (yes){
	        	if(!yes){return false;}
				top.$.ajax({
		            url: "cwRefundmentAction/tuisubmits.do?ids="+ids,
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
		function tkxs() {
			var id = Qm.getValueByName("id");
			var tkstatus = Qm.getValueByName("status");
			var unit = Qm.getValueByName("unit");
			var a="a";
			if(tkstatus=="审批通过"){
				tkstatus="0";
			}else if(tkstatus=="已退款"){
				tkstatus="1";
			}
			if("司法鉴定中心"!=unit && "四川省特种设备检验检测协会"!=unit){
				$.ligerDialog.warn("只能确认司法鉴定中心和协会的财务信息！");
	   	 	}else{
			top.$.dialog({
				width: 900,
				height: 500,
				lock: true,
				parent: api,
				data: {
					window: window
				},
				title: "退款确认",
				content: 'url:app/finance/refundment_bill_detail.jsp?id='+id+'&a='+a+'&pageStatus=detail&tkstatus='+tkstatus+"&isCheck=0"
			});}
		}
		function tkxss() {
			var ids = Qm.getValuesByName("id");
			$.ligerDialog.confirm('您确定要确认所选数据吗？', function (yes){
	        	if(!yes){return false;}
				top.$.ajax({
		            url: "cwRefundmentAction/tuisubmits.do?ids="+ids,
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
	function refreshGrid() {
		    Qm.refreshGrid();
	}
	// 金额合计
	function tkTotal(){
		var count = Qm.getSelectedCount();
		var amount = Qm.getValuesByName("money").toString();
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
		doTotal(count,newAmount,"金额");
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
                <font color="red">“红色”</font>代表审批未通过
                <font color="#800080">“紫色”</font>代表已退款，
                <font color="#8E8E8E">“灰色”</font>代表单据作废。
            </div>
        </div>
    </div>
    
    <%StringBuffer sql = new StringBuffer(); %>
    <%String sql1 = ""; %>
    <%CurrentSessionUser useres = SecurityUtil.getSecurityUser();
    String departmentId= useres.getDepartment().getId();%>
    <sec:authorize access="!hasRole('TJY2_CW_CHECK')">
		<%sql1="select * from TJY2_CW_REFUNDMENT t where t.dm_id='"+ userId +"' order by t.NUMBER_TAB desc Nulls last "; %>
		<%System.out.print("=========可怜的我只能看到自己的！！"+userId); %>    
	</sec:authorize>
	
	<sec:authorize access="hasRole('TJY2_BMFZR')">
   		<%sql1="select * from (select * from TJY2_CW_REFUNDMENT t where t.department_id ='"+ departmentId +"' and t.department_id !='"+ 100025 +"' union all select * from TJY2_CW_REFUNDMENT t where t.dm_id='"+ userId +"' and t.department_id !='"+ departmentId +"') order by NUMBER_TAB desc Nulls last "; %>
   		<%System.out.print("=========我是部长哒！！"+departmentId); %>
    </sec:authorize>

   	<sec:authorize access="hasRole('TJY2_CW_CHECK')">
   		<%sql1="select *from (select * from TJY2_CW_REFUNDMENT  t where t.status in ('DJZF','SUBMIT','CSTG','PASS','NO_PASS','submit','TKYES','TKNO') union select * from TJY2_CW_REFUNDMENT  t where t.dm_id='"+ userId +"')order by NUMBER_TAB desc Nulls last "; %>
		<sec:authorize access="hasRole('TJY2_CW_CHECK_XSG')">
			<%sql1="select t0.* from ( "+
				"select t.*from TJY2_CW_REFUNDMENT t where t.status in ('DJZF', 'SUBMIT', 'CSTG', 'PASS', 'NO_PASS','TKYES','TKNO') and t.unit in ('xh', 'sfjd', 'ds') "+
				"union "+
				"select t1.*from TJY2_CW_REFUNDMENT t1 where t1.dm_id = '"+ userId +"') t0 "+
				"order by t0.NUMBER_TAB desc Nulls last"; %>
   		</sec:authorize>
   		<sec:authorize access="hasRole('TJY2_CW_CHECK_XS')">
			<%sql1="select t0.* from ( "+
				"select t.*from TJY2_CW_REFUNDMENT t where t.status in ('DJZF', 'SUBMIT', 'CSTG', 'PASS', 'NO_PASS', 'TKYES','TKNO') and t.unit in ('xh', 'sfjd') "+
				"union "+
				"select t1.*from TJY2_CW_REFUNDMENT t1 where t1.dm_id = '"+ userId +"') t0 "+
				"order by t0.NUMBER_TAB desc Nulls last"; %>
   		</sec:authorize>
		<%System.out.print("=========我是财务报销审核人！！"); %>           
	</sec:authorize>
	<sec:authorize access="hasRole('TJY2_CW_QR_Y')">
   		<%sql1="select * from (select * from TJY2_CW_REFUNDMENT  t where t.status in ('CSTG','TKYES','TKNO') and t.unit not in ('xh','gh','ds','zgsctjywyh') union select * from TJY2_CW_REFUNDMENT  t where t.dm_id='"+ userId +"')order by NUMBER_TAB desc Nulls last "; %>
		<%System.out.print("=========我是院报销、还款确认人！！"); %>       
	</sec:authorize>
	<sec:authorize access="hasRole('TJY2_CW_QR_XGG')">
   		<%sql1="select * from (select * from TJY2_CW_REFUNDMENT  t where t.status in ('CSTG','TKYES','TKNO') and t.unit in ('xh','gh','ds','zgsctjywyh')  union select * from TJY2_CW_REFUNDMENT  t where t.dm_id='"+ userId +"')order by NUMBER_TAB desc Nulls last "; %>
		<%System.out.print("=========我是协会、公司、工会、中共委员会报销、还款确认人！！"); %>   
		<sec:authorize access="hasRole('TJY2_CW_CHECK')">
   			<%sql1="select *from (select * from TJY2_CW_REFUNDMENT  t where t.status in ('DJZF','SUBMIT','CSTG','PASS','NO_PASS','submit','TKYES','TKNO') union select * from TJY2_CW_REFUNDMENT  t where t.dm_id='"+ userId +"')order by NUMBER_TAB desc Nulls last "; %>
		</sec:authorize>    
	</sec:authorize>
	<qm:qm pageid="TJY2_CW_TKBD" script="false" singleSelect="false" sql="<%=sql1.toString() %>">
<%-- 		<sec:authorize access="!hasRole('TJY2_CW_CHECK')"> --%>
<%--     <qm:param name="dm_id" value="<%=userId%>" compare="="/> --%>
<%--     <qm:param name="registrant_id" value="<%=uId%>" compare="=" logic="or"/> --%>
<%--     </sec:authorize> --%>
<%-- 	   <sec:authorize ifAnyGranted="TJY2_CW_CHECK"> --%>
<%--             <qm:param name="status" value="('DJZF','SUBMIT','CSTG','PASS','NO_PASS','submit')" compare="in" dataType="user"/> --%>
<%--        </sec:authorize> --%>
	</qm:qm>
</body>
</html>