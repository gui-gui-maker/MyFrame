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
			}, "-",{
				text: '接受', id: 'edit', icon: 'modify', click: edit 
			} ],
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
					del:count>0
				});
             },
    			rowAttrRender : function(rowData, rowid) {
                    var fontColor="black";
                    if (rowData.data_status=='7'){
                        fontColor="green";
                    }
                    return "style='color:"+fontColor+"'";
                }				
		}
			
	};
	function edit(){
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width:900,
			height:600,
			lock:true,
			parent:api,
			data:{
				window:window
			},
			title:"审核",
			content:'url:app/finance/cw_bill_return_received_detail.jsp?id=' + id + '&pageStatus=modify&step=receivedApply'
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
			content: 'url:app/finance/cw_bill_return_apply_detail.jsp?id=' + id + '&pageStatus=detail&step=receivedApply'
		});
	}
</script>
</head>
<body>
	<div class="item-tm" id="titleElement">
        <div class="l-page-title">
            <div class="l-page-title-note">提示：列表数据项
                <font color="black">“黑色”</font>代表待审核
                <font color="green">“绿色”</font>代表接受退回票据         
            </div>
        </div>
    </div>
	<qm:qm pageid="cw_bill_list_r" script="false" singleSelect="false" >
		<qm:param name="data_status" value="(6,7)" compare="in" />
		<qm:param name="return_op_id" value="<%=userId %>" compare="=" />
	</qm:qm>
</body>
</html>