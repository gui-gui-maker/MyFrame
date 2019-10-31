<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<%
	CurrentSessionUser user = SecurityUtil.getSecurityUser();
	String userId = user.getId();
%>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	// 默认值，可自定义
		 sp_fields:[
		           {label: '购买人',name:'buy_name',compare:'like'},
			{group:[
			      {label:'发票编号从',name:'invoice_start',compare:'>='},
			      {label:'至',name:'invoice_end', compare:'<=',labelAlign:"center",labelWidth:20,width:100}
			]},
			{group:[
				{label:"购买时间从",name:"buy_date",compare:">=",width:100},
				{label:"至",name:"buy_date",compare:"<=",labelAlign:"center",labelWidth:20,width:100}
			]}
		],
			
			tbar:[{
				text: '详情', id: 'detail', icon: 'detail', click: detail
			}, "-", {
				text: '新增', id: 'add', icon: 'add', click: add 
			} , "-",{
				text: '修改', id: 'edit', icon: 'modify', click: edit 
			} , "-",{
				text: '删除', id: 'del', icon: 'del', click: del
			} , "-",{
				text: '提交', id: 'sub', icon: 'submit', click: submit
			} 
			
		
			],
			listeners:{
			rowClick:function(rowData,rowIndex){
			},
			rowDblClick:function(rowDate,rowIndex){
				detail();
			},
			selectionChange:function(rowDate,rowIndex){
				var count = Qm.getSelectedCount();
				Qm.setTbar({
					detail:count==1,
					edit:count==1,
					del:count>0,
					sub:count>0
				});
            },
   			rowAttrRender : function(rowData, rowid) {
                   var fontColor="green";
                   if (rowData.data_status=='0'){
                       fontColor="black";
                   }else if(rowData.data_status=='3') {
                       fontColor="red";
                   }else if(rowData.data_status=='1') {
                       fontColor="blue";
                   }
                   return "style='color:"+fontColor+"'";
               }			
		}
			
	};
	
	function add(){
		top.$.dialog({
			width:900,
			height:300,
			lock:true,
			parent:api,
			data:{
				window:window
			},
			title:"新增",
			content:'url:app/finance/cw_bill_apply_detail.jsp?pageStatus=add'
		});
	}
	function edit(){
		var id = Qm.getValueByName("id");
		var data_status = Qm.getValueByName("data_status");
		if(data_status=="2"||data_status=="1"){
			$.ligerDialog.alert("所选数据已提交审核或审核通过，不能修改，请重新选择！！！");
			return false;
		}
		top.$.dialog({
			width:900,
			height:300,
			lock:true,
			parent:api,
			data:{
				window:window
			},
			title:"修改",
			content:'url:app/finance/cw_bill_apply_detail.jsp?id=' + id + '&pageStatus=modify'
		});
	}
	
	
	function detail() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 900,
			height: 500,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "详情",
			content: 'url:app/finance/cw_bill_audit_detail.jsp?id=' + id + '&pageStatus=detail'
		});
	}
	function del() {
		var id = Qm.getValueByName("id");
		var data_status = Qm.getValueByName("data_status");
		if(data_status=="2"||data_status=="1"){
			$.ligerDialog.alert("所选数据已提交审核或审核通过，不能修改，请重新选择！！！");
			return false;
		}
		var ids = Qm.getValuesByName("id");
		$.del(kFrameConfig.DEL_MSG, "cwBillAction/del.do?ids="+ids);
	}
	function submit(){
		var status=Qm.getValuesByName("data_status");
		for(var i=0;i<status.length;i++){
			if(status[i]!=="0"){
				$.ligerDialog.alert("所选数据有已提交审核的，不能重复提交，请重新选择！！！");
				return;
			}
		}
		var ids=Qm.getValuesByName("id");
		top.$.dialog({
			width : 400, 
			height : 200, 
			lock : true, 
			title:"选择审核人员",
			content: 'url: app/finance/cw_bill_choose_audit_op.jsp?ids='+ids,
			data : {"window" : window}
		});
      
   }
</script>
</head>
<body>
	<div class="item-tm" id="titleElement">
        <div class="l-page-title">
            <div class="l-page-title-note">提示：列表数据项
                <font color="black">“黑色”</font>代表单据未提交，
                <font color="blue">“蓝色”</font>代表待审核，
                <font color="green">“绿色”</font>代表审核通过，
                <font color="red">“红色”</font>代表审核未通过，
                
            </div>
        </div>
    </div>
	<qm:qm pageid="cw_bill_list" script="false" singleSelect="false" >
		<qm:param name="create_op_id" value="<%=userId %>" compare="=" />
	</qm:qm>
</body>
</html>