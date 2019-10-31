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
var iszl="0";
<sec:authorize access="hasRole('TJY2_ZL_CHECK')">
iszl = "1";
</sec:authorize>
	var qmUserConfig = {
			sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
			sp_fields:[
	         {name:"apply_name",compare: "like"},
	         {name:"scheme_name",compare: "like"},
	         {group: [
	  				{name: "apply_time", compare: ">="},
	  				{label: "到", name: "apply_time", compare: "<=", labelAlign: "center", labelWidth:20}
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
		}, "-", {
	        text:'提交', id:'submit',icon:'submit', click: submitTj
	    }
		<sec:authorize access="hasRole('TJY2_ZL_CHECK')">
			,"-", {text:'经办人审核', id:'submitsh',icon:'dispose', click: chu1}
		</sec:authorize>
		, "-", {
	        text:'申请作废', id:'cancel',icon:'apply-for', click: cancel
	    }
		<sec:authorize access="hasRole('TJY2_ZL_CHECK')">
			,"-", {text:'作废审核', id:'canclesh',icon:'feedback', click: canclesh}
		</sec:authorize>
	    /*, "-", {
	        text:'审核过程', id:'gc',icon:'follow', click: gc
	    }*/
	    , "-", {
	        text:'流转过程', id:'turnHistory',icon:'follow', click: turnHistory
	    }],
	   
		listeners: {
			rowClick: function(rowData, rowIndex) {
			},
			rowDblClick: function(rowData, rowIndex) {
				detail();
			},
			selectionChange: function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();
				
				Qm.setTbar({detail:count<0,edit:count<0,del: count<0,submit: count<0,turnHistory: count<0,cancel:count<0,canclesh:count<0,submitsh:count<0});
			},
			selectionChange : function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();
				var up_status = Qm.getValueByName("status");
				var sp_status = Qm.getValueByName("sp_status");
				var status={};
				if(iszl !='1'){
					if(up_status=="经办人审核中"){
						Qm.setTbar({detail:true, edit:false, del:false,submit:false,submitsh:true,turnHistory:true,cancel:false,canclesh:false});
					}else if(up_status=="审核通过"){
						Qm.setTbar({detail:true, edit:false, del:false,submit:false,submitsh:false,turnHistory:true,cancel:true,canclesh:false});
					}else if(up_status=="审核未通过"){
						Qm.setTbar({detail:true, edit:false, del:true,submit:false,submitsh:false,turnHistory:true,cancel:false,canclesh:false});
					}else if(up_status=="未提交"){
						Qm.setTbar({detail:true, edit:true, del:true,submit:true,submitsh:false,turnHistory:false,cancel:false,canclesh:false});
					}else if(up_status=="作废审核中"){
						Qm.setTbar({detail:true, edit:false, del:false,submit:false,submitsh:false,turnHistory:true,cancel:false,canclesh:true});
					}else if(up_status=="已作废"){
						Qm.setTbar({detail:true, edit:false, del:false,submit:false,submitsh:false,turnHistory:true,cancel:false,canclesh:false});
					}else{
						Qm.setTbar({detail:count<0,edit:count<0,del: count<0,submit: count<0,submitsh:count<0,turnHistory:count<0,cancel:count<0,canclesh:count<0});
					}
				}else if(up_status=="经办人审核中"){
					Qm.setTbar({detail:true, edit:false, del:false,submit:false,submitsh:true,turnHistory:true,cancel:false,canclesh:false});
				}else if(up_status=="审核通过"){
					Qm.setTbar({detail:true, edit:false, del:false,submit:false,submitsh:false,turnHistory:true,cancel:true,canclesh:false});
				}else if(up_status=="审核未通过"){
					Qm.setTbar({detail:true, edit:false, del:true,submit:false,submitsh:false,turnHistory:true,cancel:false,canclesh:false});
				}else if(up_status=="未提交"){
					Qm.setTbar({detail:true, edit:true, del:true,submit:true,submitsh:false,turnHistory:false,cancel:false,canclesh:false});
				}else if(up_status=="作废审核中"){
					Qm.setTbar({detail:true, edit:false, del:false,submit:false,submitsh:false,turnHistory:true,cancel:false,canclesh:true});
				}else if(up_status=="已作废"){
					Qm.setTbar({detail:true, edit:false, del:false,submit:false,submitsh:false,turnHistory:true,cancel:false,canclesh:false});
				}else{
					Qm.setTbar({detail:count<0,edit:count<0,del: count<0,submit: count<0,submitsh:count<0,turnHistory:count<0,cancel:count<0,canclesh:count<0});
				}
				
			}
			/*selectionChange : function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();//行选择个数
				//alert(count);
				var up_status = Qm.getValueByName("status");
				//alert(up_status);
				var sp_status = Qm.getValueByName("sp_status");
				//alert(sp_status);
				var status={};
				if(count==0){
					status={detail:false, edit:false, del:false,submit:false,gc:false,submitsh:false};
				}else if(count==1){
					if("经办人审核中"==up_status){
						if("undefined"==sp_status){
							status={detail:true, edit:false, del:true,submit:false,submitsh:true};
						}else{
							status={detail:true, edit:false, del:false,submit:false,submitsh:true};
						}
					}else if("审核通过"==up_status){
						if("undefined"==sp_status){
							status={detail:true, edit:false, del:false,submit:false,gc:true,submitsh:false};
						}else{
							status={detail:true, edit:false, del:false,submit:false,gc:true,submitsh:false};
						}
					}else if("审核未通过"==up_status){
						if("undefined"==sp_status){
							status={detail:true, edit:false, del:false,submit:false,gc:true,submitsh:false};
						}else{
							status={detail:true, edit:false, del:true,submit:false,gc:true,submitsh:false};
						}
					}else{
						status={detail:true, edit:true, del:true,submit:true,gc:true,submitsh:false};
					}
				}else{
					if("已提交"==sp_status){
						status={detail:false, edit:false, del:true,submit:false,submitsh:false};
					}else{
						status={detail:false, edit:false, del:false,submit:false,submitsh:false};
					}
				}
				Qm.setTbar(status);
			}*/,rowAttrRender : function(rowData, rowid) {
	            var fontColor="black";
	            if (rowData.status=='经办人审核中'){
	            	fontColor="orange";
	            }else if(rowData.status=='审核通过'){
	            	fontColor="green";
	            }else if(rowData.status=='审核未通过'){
	            	fontColor="red";
	            }else if(rowData.status=='作废审核中'){
	            	fontColor="blue";
	            }else if(rowData.status=='已作废'){
	            	fontColor="#8E8E8E";
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
				content: 'url:Tyfabh/a/getFlowStep.do?id='+Qm.getValueByName("id"),
				data : {"window" : window}
			});
	}
	function chu1(){
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
			content: 'url:app/qualitymanage/tyfabhsqb_datail.jsp?id=' + id + '&pageStatus=modify'+'&a='+"1"
		});
	}
	function canclesh(){
		var id = Qm.getValueByName("id");
		var status=Qm.getValueByName("status");
		var status1=change(status);
		top.$.dialog({
			width: 900,
			height: 550,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "作废审核",
			content: 'url:app/qualitymanage/tyfabhsqb_datail.jsp?id=' + id + '&pageStatus=modify'+'&a='+"2"+'&status1='+status1
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
		top.$.ajax({
            url: "Tyfabh/a/setzt.do?id="+id,
            type: "POST",
            dataType:'json',
            async: false,
            success:function (data) {
            	if(data.success){
	                  top.$.notice("提交成功！！",3);
	                   Qm.refreshGrid();//刷新
	               }else{
	                   $.ligerDialog.warn("提交失败！！");
	                   Qm.refreshGrid();//刷新
	               }
            }
        });
	}
	function cancel(){
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 600,
			height: 200,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "申请作废",
			content: 'url:app/qualitymanage/tyfabhsqb_cancel.jsp?id=' + id
		});
	}
	function add() {
		top.$.dialog({
			width: 900,
			height: 550,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "选择检验性质",
			content: 'url:app/qualitymanage/tyfabhsqb_datail.jsp?pageStatus=add'
		});
	}

	function edit() {
		var statusa=Qm.getValueByName("statusa");
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 900,
			height: 550,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "修改",
			content: 'url:app/qualitymanage/tyfabhsqb_datail.jsp?id=' + id + '&pageStatus=modify'
		});
	}
	function detail() {
		var status=Qm.getValueByName("status");
		var status1=change(status);
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
			content: 'url:app/qualitymanage/tyfabhsqb_datail.jsp?id=' + id + '&pageStatus=detail'+'&status1='+status1
		});
	}
	function del() {
		var id = Qm.getValueByName("id");
		$.ligerDialog.confirm('您确定要删除所选数据吗？', function (yes){
        	if(!yes){return false;}
			top.$.ajax({
	            url: "Tyfabh/a/delete.do?ids="+id,
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
	function change(status){
		if(status=="作废审核中"){
			status1 = "CANCLLING";
		}else if(status=="已作废"){
			status1 = "CANC";
		}else{
			status1 = "";
		}
		return status1;
	}
</script>
</head>
<body>
	<div class="item-tm" id="titleElement">
        <div class="l-page-title">
            <div class="l-page-title-note">提示：列表数据项
                <font color="black">“黑色”</font>代表未提交，
                <font color="green">“绿色”</font>代表审核通过，
                <font color="orange">“橙色”</font>代表经办人审核中，
                <font color="blue">“蓝色”</font>代表作废审核中，
                <font color="#8E8E8E">“灰色”</font>代表已作废，
                <font color="red">“红色”</font>代表审核未通过。
                
            </div>
        </div>
    </div>
	<qm:qm pageid="TJY2_ZL_TYFABH" singleSelect="true">
<!-- 	登记人 --><!-- 	申请人 -->
		<sec:authorize access="!hasRole('TJY2_ZL_CHECK')">
			<qm:param name="registrant_id" value="<%=uId%>" compare="=" />
		</sec:authorize>
<%-- 		<qm:param name="apply_name_id" value="<%=userId%>" compare="=" logic="or"/> --%>
	</qm:qm>
</body>
</html>