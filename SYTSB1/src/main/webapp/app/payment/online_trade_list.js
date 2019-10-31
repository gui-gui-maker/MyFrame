var onlineInfoGrid,tradeGrid;
var pay_type = [ {  id:'alipay' ,text:'支付宝'}, { id:'weixin', text:'微信' } ];
function createOnlineInfoGrid() {	
    var columns=[
        { display: 'id', name: 'id',  hide:true},
        { display: '订单号', width: '12%', name: 'order_no', type: 'text'},
        { display: '交易方式', width: '8%', name: 'pay_type', type: 'text'}, 
        { display: '交易号', width: '22%', name: 'trade_no', type: 'text'},
        { display: '支付账号', width: '20%', name: 'buyer_logon_id', type: 'text'},
        { display: '支付金额（元）', width: '10%', name: 'buyer_pay_amount', type: 'float'},     	
        { display: '支付时间', width: '15%', name: 'send_pay_date', format:'yyyy-MM-dd hh:mm:ss',type:'date'}
        /*,
        { display: '交易状态', width: '10%', name: 'trade_status', type: 'text'}*/
    ];
    
    if(pageStatus!="detail"){
        columns.unshift({ display: "", isSort: false, width: '4%',height:'5%', render: function (rowdata, rowindex, value) {
            var h = "";
            if (!rowdata._editing) {
                h += "<a class='l-a l-icon-del' href='javascript:deleteRow(\"onlineInfoList\")' title='删除'><span>删除</span></a> ";
            }
            return h;
        }
        });
    }

    tradeGrid = onlineInfoGrid = $("#onlineInfoList").ligerGrid({
    	columns: columns,
        enabledEdit: false,
        clickToEdit: false,
        rownumbers: true,    
        height:"10%",
        width:"100%",
        //是否显示行序号
        frozenRownumbers: false,
        usePager: false,
        data: {Rows: [
        ]}
    });
}

function deleteRow(name) {
    var grid = $("#"+name).ligerGetGridManager();
    grid.deleteSelectedRow();
}
