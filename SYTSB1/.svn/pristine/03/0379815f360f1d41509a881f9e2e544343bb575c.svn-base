var cwBankInfoGrid,g1;
function createCwBankInfoGrid() {	
    var columns=[
        { display: 'id', name: 'id',  hide:true},
        { display: '对方户名', width: '25%', name: 'accountName', type: 'text'},
        { display: '转入金额', width: '12%', name: 'money', type: 'float'},
        { display: '交易时间', width: '15%', name: 'jyTime', format:'yyyy-MM-dd hh:mm:ss',type:'date'},
        { display: '剩余金额', width: '12%', name: 'restMoney', type: 'float'},     	
      	{ display: '本次收费金额', width: '12%', name: 'usedMoney', type: 'float', editor: { type: 'float'}, required:true, maxlength:20},
        { display: '备注', name: 'pay_remark', width: '15%', align: 'left', editor: { type: 'text'}, required:false, maxlength:1000}    
    ];
    
    if(pageStatus!="detail"){
        columns.unshift({ display: "", isSort: false, width: '4%',height:'5%', render: function (rowdata, rowindex, value) {
            var h = "";
            if (!rowdata._editing) {
                h += "<a class='l-a l-icon-del' href='javascript:deleteRow(\"cwBankInfoList\")' title='删除'><span>删除</span></a> ";
            }
            return h;
        }
        });
    }

    g1 = cwBankInfoGrid = $("#cwBankInfoList").ligerGrid({
    	columns: columns,
        enabledEdit: pageStatus!="detail",
        clickToEdit: true,
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
