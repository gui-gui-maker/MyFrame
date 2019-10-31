<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd"); 
	String nowTime=""; 
	nowTime = dateFormat.format(new Date());%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	// 默认值，可自定义 
		sp_fields:[
					{name: "send_name", compare: "like"},
					 {name: "send_number", compare: "like"},
					 {name: "is_temporary_tel", compare: "like"},
				     {name: "send_type", compare: "like"},
				     {group:[
								{name:"send_time", compare:">=", value:""},
								{label:"到", name:"send_time", compare:"<=", value:"", labelAlign:"center", labelWidth:20}
							]},
							{name: "send_status", compare: "like"}	
		] , 
		tbar: [ {
			text: '详情', id: 'detail', icon: 'detail', click: detail
		},
		{
			text: '新增', id: 'add', icon: 'add', click: add
		},
		{
			text: '修改', id: 'modify', icon: 'modify', click: modify
		},
		{
			text: '删除', id: 'del', icon: 'del', click: del
		},
		{
			text: '发送', id: 'send', icon: 'outbox', click: send
		}
		],
        listeners: {
	        rowClick: function(rowData, rowIndex) {
	        },
	        rowDblClick: function(rowData, rowIndex) {
		      detail();
	        },	
	        selectionChange: function(rowData, rowIndex) {
		         var count = Qm.getSelectedCount();
		         var send_status = Qm.getValuesByName("send_status");
		         var allowSend =  true;
		         for(var i=0;i<send_status.length;i++){
		        	 if(send_status[i]=="已发送"){
		        		 allowSend =  false;
		        	 }
		         }
		         Qm.setTbar({
		          	detail: count==1,
		          	modify:count==1&&allowSend,
		          	del:count>0&&allowSend,
		          	send:count>0&&allowSend
		       });
	        },
	        rowAttrRender : function(rowData, rowid) {
	        	var fontColor="black";
	            if(rowData.send_status=='已发送'){
	           	 fontColor="green";
	            }
	            return "style='color:"+fontColor+"'";
				}
		}
}
		
function detail(){
    top.$.dialog({
        width: 700,
        height: 400,
        lock: true,
        parent: api,
        data: {
      	 window: window
        },
        title: "详情",
        content: 'url:app/office/office_message_detail.jsp?pageStatus=detail&id='+Qm.getValueByName("id")
     });
}
function add(){
	top.$.dialog({
         width: 700,
         height: 400,
         lock: true,
         parent: api,
         data: {
       	 window: window
         },
         title: "新增",
         content: 'url:app/office/office_message_detail.jsp?pageStatus=add'
      });
}
function modify(){
	top.$.dialog({
	       width: 700,
	       height: 400,
	       lock: true,
	       parent: api,
	       data: {
	     	 window: window
	       },
	       title: "修改",
	       content: 'url:app/office/office_message_detail.jsp?pageStatus=modify&id='+Qm.getValueByName("id")
	    });

}
function del(){
	$.del("确定删除?",
	  		"officeMessageAction/delete.do",
	  		{"ids":Qm.getValuesByName("id").toString()});
}
function send(){
	$.ligerDialog.confirm('是否发送消息？', function (yes){
		if(!yes){return false;}
		$.ajax({
	    	url: "officeMessageAction/sendByHand.do?ids="+Qm.getValuesByName("id").toString(),
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
            <div class="l-page-title-note">提示：列表数据项
                <font color="black">“黑色”</font>代表未发送，
                <font color="green">“绿色”</font>代表已发送。
            </div>
        </div>
    </div>
       <qm:qm pageid="TJY2_OFFICE_MESSAGE">
       </qm:qm>
</body>
</html>