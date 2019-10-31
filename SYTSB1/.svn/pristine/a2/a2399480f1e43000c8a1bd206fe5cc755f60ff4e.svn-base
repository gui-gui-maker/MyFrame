<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>银行转账账户</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="text/javascript">
    var khFileUploader;
    var qmUserConfig = {
   		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
   		sp_fields : [
   		             {name:"account_name",compare:"like"},
   		          	 {name:"transfer_person",compare:"like"},
   					 {name:"transfer_address",compare:"like"}
   		          	 ],
   		tbar:[{
       			text : '详情',
       			id : 'detail',
       			click : detail,
       			icon : 'detail'
       		   },'-', {
       			text : '新增',
       			id : 'add',
       			click : add,
       			icon : 'add'
       		   },'-', {
       			text : '修改',
       			id : 'modify',
       			click : edit,
       			icon : 'modify'
       		   },'-', {
       			text : '删除',
       			id : 'del',
       			click : del,
       			icon : 'del'
       	}],
        listeners: {
            rowClick: function(rowData, rowIndex) {
            },
            rowDblClick: function(rowData, rowIndex) {
            },  
            selectionChange: function(rowData, rowIndex) {
                var count = Qm.getSelectedCount();
               // Qm.setTbar();
            }
        }
    };


    function add(){
        top.$.dialog({
            width: 900,
            height: 500,
            lock: true,
            parent: api,
            data: {
                window: window
            },
            title: "新增",
            content: 'url:app/finance/cw_bank_transfer_detail.jsp?&pageStatus=add'
        });
    }
    function edit() {
        var id = Qm.getValueByName("id");
        top.$.dialog({
            width: 900,
            height: 500,
            lock: true,
            parent: api,
            data: {
                window: window
            },
            title: "修改",
            content: 'url:app/finance/cw_bank_transfer_detail.jsp?id=' + id + '&pageStatus=modify'
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
            content: 'url:app/finance/cw_bank_transfer_detail.jsp?id=' + id + '&pageStatus=detail'
        });
    }
    
    //删除
    function del() {
            $.del("确定要删除？删除后无法恢复！","cwBankAccountAction/deletes.do",{"ids":Qm.getValuesByName("id").toString()});
    }
    
</script>
</head>
<body>
    <qm:qm pageid="TJY2_CW_ACCOUNTS" script="false" singleSelect="false"></qm:qm>
</body>
</html>