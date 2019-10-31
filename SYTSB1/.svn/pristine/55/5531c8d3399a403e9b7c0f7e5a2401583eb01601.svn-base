var inspectionInfoGrid,g;
function createInspectionInfoGrid() {	
    var columns=[
        { display: 'id', name: 'id',  hide:true},
        { display: '业务流水号', name: 'sn', width: '10%'},
        { display: '报告书编号', name: 'report_sn', width: '15%'},
        { display: '制造单位', width: '20%', name: 'made_unit_name', type: 'text'},
        { display: '安装单位', width: '20%', name: 'install_unit_name', type: 'text'},
        { display: '设备类别', width: '15%', name: 'device_type_code', type: 'text', 
            render: function (item) {
                return render(item["device_type_code"],deviceType);
            }
        },
        { display: '产品名称', width: '12%', name: 'device_name', type: 'text'},
        { display: '产品编号', width: '12%', name: 'device_no', type: 'text'},
        { display: '设备代码', width: '12%', name: 'device_no', type: 'text'},   	
      	{ display: '应收金额', width: '10%', name: 'advance_fees', type: 'float', totalSummary: 
        	{
            	align: 'center', // 汇总单元格内容对齐方式:left/center/right 
            	type: 'sum', // 汇总类型sum,max,min,avg ,count。可以同时多种类型 
           		render: function (e){ 
                	//汇总渲染器，返回html加载到单元格 
            		//e 汇总Object(包括sum,max,min,avg,count) 
                	return "合计：" + e.sum; 
            	} 
        	}},
      	/*{ display: '实收金额', width: '10%', name: 'receivable', type: 'float', editor: { type: 'float'}, required:true, maxlength:20, totalSummary: 
        	{
            	align: 'center', // 汇总单元格内容对齐方式:left/center/right 
            	type: 'sum', // 汇总类型sum,max,min,avg ,count。可以同时多种类型 
           		render: function (e){ 
                	//汇总渲染器，返回html加载到单元格 
            		//e 汇总Object(包括sum,max,min,avg,count) 
            		TotalFee(e.sum);
                	return "合计：" + e.sum; 
            	} 
        	}
        },*/
        { display: '备注', name: 'remark', width: '30%', align: 'left', maxlength:200}    
    ];

    g = inspectionInfoGrid = $("#inspectionInfoList").ligerGrid({
    	columns: columns,
        enabledEdit: pageStatus!="detail",
        clickToEdit: true,
        rownumbers: true,    
        height:"200",
        width:"965",
        //是否显示行序号
        frozenRownumbers: false,
        usePager: false,
        data: {Rows: [
        ]}
    });
    gridConfig["inspectionInfoList"].manager=inspectionInfoGrid;
}

function createInspectionInfoGrid2() {	
    var columns=[
        { display: 'id', name: 'id',  hide:true},
		{ display: '业务流水号', name: 'sn', width: '10%'},
        { display: '报告书编号', name: 'report_sn', width: '15%'},
        { display: '制造单位', width: '20%', name: 'made_unit_name', type: 'text'},
        { display: '设备类别', width: '15%', name: 'device_type_code', type: 'text', 
            render: function (item) {
                return render(item["device_type_code"],deviceType);
            }
        },
        { display: '产品名称', width: '12%', name: 'device_name', type: 'text'},
        { display: '产品编号', width: '12%', name: 'device_no', type: 'text'},
        { display: '设备代码', width: '12%', name: 'device_no', type: 'text'},   	
      	{ display: '应收金额', width: '10%', name: 'advance_fees', type: 'float', totalSummary: 
        	{
            	align: 'center', // 汇总单元格内容对齐方式:left/center/right 
            	type: 'sum', // 汇总类型sum,max,min,avg ,count。可以同时多种类型 
           		render: function (e){ 
                	//汇总渲染器，返回html加载到单元格 
            		//e 汇总Object(包括sum,max,min,avg,count) 
                	return "合计：" + e.sum; 
            	} 
        	}},
      	{ display: '实收金额', width: '10%', name: 'receivable', type: 'float', editor: { type: 'float'}, required:true, maxlength:20, totalSummary: 
        	{
            	align: 'center', // 汇总单元格内容对齐方式:left/center/right 
            	type: 'sum', // 汇总类型sum,max,min,avg ,count。可以同时多种类型 
           		render: function (e){ 
                	//汇总渲染器，返回html加载到单元格 
            		//e 汇总Object(包括sum,max,min,avg,count) 
            		//TotalFee(e.sum);
                	return "合计：" + e.sum; 
            	} 
        	}
        },
        { display: '收费状态', width: '8%', name: 'fee_status', type: 'text',
			render: function (item) {
				return render(item["fee_status"],feeStatus);
			}
		},
        { display: '备注', name: 'remark', width: '30%', align: 'left', maxlength:200}    
    ];

    g = inspectionInfoGrid = $("#inspectionInfoList").ligerGrid({
    	columns: columns,
        enabledEdit: pageStatus!="detail",
        clickToEdit: true,
        rownumbers: true,    
        height:"200",
        width:"965",
        //是否显示行序号
        frozenRownumbers: false,
        usePager: false,
        data: {Rows: [
        ]}
    });
    gridConfig["inspectionInfoList"].manager=inspectionInfoGrid;
}

var gridConfig={
    inspectionInfoList:{manager:inspectionInfoGrid}
};

// 计算实收总金额
function TotalFee(objValue){
	//if(isNaN(objValue)){
	//	$.ligerDialog.error("请输入数字金额！"); 
	//	return false;
	//} 
	$("#pay_received").val(parseFloat(objValue));
}

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