var paymentGrid;
var pay_types = [ {  id:'5' ,text:'POS'}];

function createPaymentInfoGrid() {
    var columns=[
        { display: 'id', name: 'id', hide:true},
        { display: 'fk_inspection_info_id', name: 'fk_inspection_info_id', hide:true},
        { display: '受检单位', width: '20%', name: 'report_com_name', type: 'text', editor: { type: 'text'},required:true,maxlength:200},
        { display: '开票单位', width: '20%', name: 'company_name', type: 'text', editor: { type: 'text'},required:true,maxlength:200},
        { display: '报告编号', width: '15%', name: 'report_sn', type: 'text', editor: { type: 'text'},required:true,maxlength:32},
        { display: '发票号', width: '15%', name: 'invoice_no', type: 'text', editor: { type: 'text'},required:true,maxlength:9},
        { display: '合同号', width: '15%', name: 'pay_no', type: 'text', editor: { type: 'text'},required:false,maxlength:200},
        { display: '检验部门', width: '15%', name: 'check_dep_id', type: 'text', required:true, editor : {
				type : 'select',
				data : orgList,
				ext : {
					emptyOption : false,
					selectBoxHeight : 400
				}
			},
			render : function(item) {
				return render(item["check_dep_id"], orgList);
			}
		},
        { display: '收费方式', width: '10%', name: 'pay_type', type: 'text', required:true, editor : {
				type : 'select',
				data : pay_types,
				ext : {
					emptyOption : false,
					selectBoxHeight : 100
				}
			},
			render : function(item) {
				return render(item["pay_type"], pay_types);
			}
		},
        { display: '金额', width: '10%', name: 'pay_received', type: 'float',  editor: { type: 'float'}, required:true,maxlength:32},
        { display: '数量', width: '6%', name: 'report_count', type: 'int', editor: { type: 'spinner'},required:true,maxlength:32},
        { display: '开票日期', width: '10%', name: 'pay_date', type: 'date', editor: { type: 'date'},format : 'yyyy-MM-dd',required:true}
    ];
	
    if(pageStatus!="detail"){
        columns.unshift({ display: "<a class='l-a iconfont l-icon-add' href='javascript:void(0);' onclick='addNewRow(\"paymentInfos\")' title='增加'><span>增加</span></a>", isSort: false, width: '4%',height:'5%', render: function (rowdata, rowindex, value) {
            var h = "";
            if (!rowdata._editing) {
                h += "<a class='l-a l-icon-del' href='javascript:deleteRow(\"paymentInfos\")' title='删除'><span>删除</span></a> ";
            }
            return h;
        }
        });
    }

    paymentGrid = $("#paymentInfos").ligerGrid({
    	columns: columns,
    	enabledEdit: pageStatus!="detail" ,
        rownumbers: true,    
        height:"70%",
        width:"100%",
        //是否显示行序号
        frozenRownumbers: false,
        usePager: false,
        //onAfterEdit: f_onAfterEdit,
        data: {Rows: [
        ]}
    });
    gridConfig["paymentInfos"].manager=paymentGrid;
    
}

//编辑后事件 
function f_onAfterEdit(e){
	var grid=gridConfig["paymentInfos"].manager; 
	var data = grid.getSelectedRow();   
	alert(data.device_type_code);
	alert(e.column.name);
	grid.updateCell('device_name', data.device_type_code, e.record);
}

function addNewRow(name) {
	var grid=gridConfig[name].manager; 
	grid.addEditRow({}); //添加一行
}

function deleteRow(name) {
    var grid=gridConfig[name].manager;   //删除一行   
    var data = grid.getSelectedRow();   
    if(data.id==null||data.id==undefined||data.id==""){
    	grid.deleteSelectedRow();
    }else{
    	 $.ligerDialog.confirm(kui.DEL_MSG, function (yes) {
             if (yes) {
                 $.getJSON(gridConfig[name]["delUrl"], {id: data.id}, function (json) {
                     if (json.success) {
                         grid.deleteSelectedRow();
                     }
                 });
             }
         });
    }
}

var gridConfig={	
    paymentInfos:{manager:paymentGrid,delUrl:"payment/payInfo/del.do"}
};

function render(value,data){
    for (var i in data) {
    	if (data[i]["id"] == value)
        {
    		
        	return data[i]['text'];
        }
		if(data[i].children)
		{
			for(var j in data[i].children)
			{
				if(data[i].children[j]["id"] ==value)
					return data[i].children[j]['text'];
				if(data[i].children[j].children)
				{
					for(var k in data[i].children[j].children)
						if(data[i].children[j].children[k]["id"]==value)
						{
							return data[i].children[j].children[k]["text"];
						}
				}
			}
		}
    }
    return value;
}
