<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<%
	CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
	User user = (User)curUser.getSysUser();
	String user_id = user.getId();
	%>
<script type="text/javascript">
var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
		sp_fields : [
              {group: [{name: "jy_time", compare: ">="},
                       {label: "到", name: "jy_time", compare: "<=", labelAlign: "center", labelWidth:20}
                      ]},
                 {name: "money", compare: "="},
             	 {name:"account_name",compare:"like"},
              	 {name:"rest_money",compare:"like"},
              	 {name:"fefund_money",compare:"like"},
              	 {name:"data_status",compare:"="}
        ],
        tbar :[
    		{ text:'详情', id:'detail',icon:'detail', click: detail},'-',
    		{ text:'修改', id:'modify',icon:'modify', click: modify},'-',
    		{ text:'撤销申请', id:'revokeApply',icon:'delete', click: revokeApply},'-',
    		<sec:authorize access="hasRole('TJY2_BANK_FEFUND_CHECK')">
	    		{ text:'审核', id:'check',icon:'dispose', click: check},'-',
	    		{ text:'撤销审核', id:'revokeChecked',icon:'delete', click: revokeChecked},'-',
    		</sec:authorize>
    		<sec:authorize access="hasRole('TJY2_BANK_FEFUND_CONFIRM')">
    			{ text:'确认', id:'confirm',icon:'ok', click: confirm},'-',
    		</sec:authorize>
		    {text:'流转过程', id:'turnHistory',icon:'follow', click: turnHistory}
        ],
        listeners: {
            rowClick: function(rowData, rowIndex) {
            },
            rowDblClick: function(rowData, rowIndex) {
                detail();
            },  
            selectionChange: function(rowData, rowIndex) {
            	var count = Qm.getSelectedCount(); //行选择个数
            	var data_status = Qm.getValuesByName("data_status").toString();//数据状态
            	if(count == 1){
            		if("待审核"==data_status){
                		Qm.setTbar({detail: true,
                			modify: true,
                			revokeApply: true,
                			check: true,
            				revokeChecked: false,
            				confirm: false,
            				turnHistory: true
            			});
                	}else if("待确认"==data_status){
                		Qm.setTbar({detail: true,
                			modify: false,
                			revokeApply: false,
                			check: false,
            				revokeChecked: true,
            				confirm: true,
            				turnHistory: true
            			});
                	}else if("已确认"==data_status){
                		Qm.setTbar({detail: true,
                			modify: false,
                			revokeApply: false,
                			check: false,
            				revokeChecked: false,
            				confirm: false,
            				turnHistory: true
            			});
                	}else{
                		Qm.setTbar({detail: true,
                			modify: false,
                			revokeApply: false,
                			check: false,
            				revokeChecked: false,
            				confirm: false,
            				turnHistory: true
            			});
                	}
            	}else{
            		Qm.setTbar({detail: false,
            			modify: false,
            			revokeApply: false,
            			check: false,
        				revokeChecked: false,
        				confirm: false,
        				turnHistory: false
        			});
            	}
            },
            rowAttrRender: function(rowData, rowid) {
            	var fontColor="black";
	            if(rowData.data_status=='待审核'){
	            	fontColor="blue";
	            }else if(rowData.data_status=='待确认'){
	            	fontColor="orange";
	            }else if(rowData.data_status=='已确认'){
	           		fontColor="green";
	            }else if(rowData.data_status=='审核不通过'){
	           		fontColor="red";
	            }else if(rowData.data_status=='已撤销'){
	           		fontColor="gray";
	            }
	            return "style='color:"+fontColor+"'";
            }
        }
    };
//详情
function detail() {
    var fefund_id = Qm.getValueByName("fefund_id");
    top.$.dialog({
        width: 700,
        height: 300,
        lock: true,
        parent: api,
        data: {
            window: window
        },
        title: "详情",
        content: 'url:app/finance/cwBankFefund_detail.jsp?fefund_id=' + fefund_id + '&pageStatus=detail&op_type=detail'
    });
}
//修改
function modify() {
	var operator_id = Qm.getValueByName("operator_id");
	if(operator_id=="<%=user_id%>"){
		var fefund_id = Qm.getValueByName("fefund_id");
	    var rest_money = Qm.getValueByName("rest_money");
	    top.$.dialog({
	        width: 700,
	        height: 300,
	        lock: true,
	        parent: api,
	        data: {
	            window: window
	        },
	        title: "修改",
	        content: 'url:app/finance/cwBankFefund_detail.jsp?fefund_id=' + fefund_id + '&pageStatus=modify&&rest_money='+rest_money+'&op_type=modify'
	    });
	}else{
		$.ligerDialog.warn("只能修改自己提交的退款申请！");
		return;
	}
}
//审核
function check() {
	var fefund_id = Qm.getValueByName("fefund_id");
    top.$.dialog({
        width: 700,
        height: 300,
        lock: true,
        parent: api,
        data: {
            window: window
        },
        title: "审核",
        content: 'url:app/finance/cwBankFefund_detail.jsp?fefund_id=' + fefund_id + '&pageStatus=detail' + '&op_type=check'
    });
}
//退款确认
function confirm() {
	var fefund_id = Qm.getValueByName("fefund_id");
    top.$.dialog({
        width: 700,
        height: 300,
        lock: true,
        parent: api,
        data: {
            window: window
        },
        title: "退款确认",
        content: 'url:app/finance/cwBankFefund_detail.jsp?fefund_id=' + fefund_id + '&pageStatus=detail' + '&op_type=confirm'
    });
}
//撤销申请
function revokeApply() {
	var operator_id = Qm.getValueByName("operator_id");
	if(operator_id=="<%=user_id%>"){
		var fefund_id = Qm.getValueByName("fefund_id");
	    top.$.dialog({
	        width: 700,
	        height: 300,
	        lock: true,
	        parent: api,
	        data: {
	            window: window
	        },
	        title: "撤销申请",
	        content: 'url:app/finance/cwBankFefund_detail.jsp?fefund_id=' + fefund_id + '&pageStatus=detail' + '&op_type=revokeApply'
	    });
	}else{
		$.ligerDialog.warn("只能撤销自己提交的退款申请！");
		return;
	}
}
//撤销审核
function revokeChecked() {
	var fefund_id = Qm.getValueByName("fefund_id");
    top.$.dialog({
        width: 700,
        height: 300,
        lock: true,
        parent: api,
        data: {
            window: window
        },
        title: "撤销审核",
        content: 'url:app/finance/cwBankFefund_detail.jsp?fefund_id=' + fefund_id + '&pageStatus=detail' + '&op_type=revokeChecked'
    });
}
//流转卡
function turnHistory(){	
	var fefund_id = Qm.getValueByName("fefund_id");
	top.$.dialog({
			width : 400, 
			height : 700, 
			lock : true, 
			title: "流转卡",
			content: 'url:bank/fefund/getFlowStep.do?id='+fefund_id,
			data : {"window" : window}
		});
}
</script>
</head>
<body>
	<div class="item-tm" id="titleElement">
        <div class="l-page-title">
            <div class="l-page-title-note">提示：列表数据项
                <font color="blue">“蓝色”</font>代表待审核，
                <font color="orange">“橙色”</font>代表待确认，
                <font color="green">“绿色”</font>代表已确认，
                <font color="gray">“灰色”</font>代表已撤销，
                <font color="red">“红色”</font>代表审核不通过,
                <font color="black">“黑色”</font>代表老数据。
            </div>
        </div>
    </div>
	<qm:qm pageid="TJY2_CW_FEFUND_CHECK" script="false" singleSelect="true">
	
	</qm:qm>
</body>
</html>