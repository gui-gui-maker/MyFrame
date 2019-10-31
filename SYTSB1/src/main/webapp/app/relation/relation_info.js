var relationInfoGrid;
var formuleType = [ {  id:'1' ,text:'计算'}, { id:'2', text:'判断'}, { id:'3', text:'预定义规则'}, { id:'4', text:'反转规则'}];

function createInfoGrid() {
    var columns=[
        { display: 'id', name: 'id', hide:true},
        { display: 'fkReportId', name: 'fkReportId', value: fk_report_id, hide: true},
        { display: 'reportName', name: 'reportName', value: "", hide: true},
        { display: '原始记录项目', width: '20%', name: 'recordItemName', type: 'text', required: true, maxlength: 200},
        { display: '报告项目', width: '20%', name: 'reportItemName', type: 'text', required: true, maxlength: 100},      
        { display: '转换计算公式', width: '20%', name: 'formule', type:'text', editor: { type: 'text'}, required: true, maxlength: 200},
        { display: '转换类型', width: '10%', name: 'formuleType', editor: { type: 'text'},required: true,
        	 editor: { type: 'select', data: formuleType ,ext:{emptyOption:false}},
        	 	render: function (item) {
        	    	if (item["formuleType"] == "1"){
        	    		return "计算";
        	    	}else if(item["formuleType"] == "2"){
        	    		return "判断";
        	    	}else if(item["formuleType"] == "3"){
        	    		return "预定义规则";
        	    	}else if(item["formuleType"] == "4"){
        	    		return "反转规则";
        	    	}else{
        	    		return "";
        	    	}
        		}
        },      
        { display: '转换默认值', width: '20%', name: 'defaultValue', type:'text', editor: { type: 'text'}, required: false, maxlength: 20},
        { display: '原始记录页码', name: 'recordPageNo', value: cur_ysjl_page/*, hide: true*/},
        { display: '报告页码', name: 'reportPageNo', value: cur_report_page/*, hide: true*/}
    ];
	
    if(pageStatus!="detail"){
        columns.unshift({ display: "<a class='l-a l-icon-del' href='javascript:void(0);' onclick='clearGrid(\"infos\")' title='清空'><span>清空</span></a>", isSort: false, width: '4%',height:'5%', render: function (rowdata, rowindex, value) {
            var h = "";
            if (!rowdata._editing) {
                h += "<a class='l-a l-icon-del' href='javascript:deleteRow(\"infos\")' title='删除'><span>删除</span></a> ";
            }
            return h;
        }
        });
    }

    relationInfoGrid = $("#infos").ligerGrid({
    	columns: columns,
    	enabledEdit: pageStatus!="detail",
        clickToEdit: true,
        rownumbers: true,    
        height:"55%",
        width:"100%",
        //是否显示行序号
        frozenRownumbers: false,
        usePager: false,
        //onBeforeEdit:pushBox,
        //onAfterEdit: f_onAfterEdit,
        data: {Rows: []}
    });
    gridConfig["infos"].manager=relationInfoGrid;
    
}

function createInfoGrid2() {
    var columns=[
        { display: 'id', name: 'id', hide:true},
        { display: 'fkReportId', name: 'fkReportId', hide: true},
        { display: '报告模版名称', width: '20%', name: 'reportName'},
        { display: '原始记录项目', width: '15%', name: 'recordItemName', type: 'text', required: true, maxlength: 200},
        { display: '报告项目', width: '15%', name: 'reportItemName', type: 'text', required: true, maxlength: 100},      
        { display: '转换计算公式', width: '20%', name: 'formule', type:'text', editor: { type: 'text'}, required: true, maxlength: 200},
        { display: '转换类型', width: '10%', name: 'formuleType', editor: { type: 'text'},required: true,
        	 editor: { type: 'select', data: formuleType ,ext:{emptyOption:false}},
        	 	render: function (item) {
        	    	if (item["formuleType"] == "1"){
        	    		return "计算";
        	    	}else if(item["formuleType"] == "2"){
        	    		return "判断";
        	    	}else if(item["formuleType"] == "3"){
        	    		return "预定义规则";
        	    	}else if(item["formuleType"] == "4"){
        	    		return "反转规则";
        	    	}else{
        	    		return "";
        	    	}
        		}
        },      
        { display: '转换默认值', width: '15%', name: 'defaultValue', type:'text', editor: { type: 'text'}, required: false, maxlength: 20},
        { display: 'recordPageNo', name: 'recordPageNo', hide: true},
        { display: 'reportPageNo', name: 'reportPageNo', hide: true},
        { display: 'data_status', name: 'data_status', hide: true}
    ];
	
    /*if(pageStatus!="detail"){
        columns.unshift({ display: "<a class='l-a l-icon-del' href='javascript:void(0);' onclick='clearGrid(\"infos\")' title='清空'><span>清空</span></a>", isSort: false, width: '4%',height:'5%', render: function (rowdata, rowindex, value) {
            var h = "";
            if (!rowdata._editing) {
                h += "<a class='l-a l-icon-del' href='javascript:deleteRow(\"infos\")' title='删除'><span>删除</span></a> ";
            }
            return h;
        }
        });
    }*/

    relationInfoGrid = $("#infos").ligerGrid({
    	columns: columns,
    	enabledEdit: pageStatus!="detail",
        clickToEdit: true,
        rownumbers: true,    
        height:"55%",
        width:"100%",
        //是否显示行序号
        frozenRownumbers: false,
        usePager: false,
        //onBeforeEdit:pushBox,
        //onAfterEdit: f_onAfterEdit,
        data: {Rows: []}
    });
    gridConfig["infos"].manager=relationInfoGrid;
    
}

function pushBox(e){
	
}

//编辑后事件 
function f_onAfterEdit(e){
	var grid=gridConfig["infos"].manager; 
	var data = grid.getSelectedRow();   
	alert(data.device_type_code);
	alert(e.column.name);
	grid.updateCell('device_name', data.device_type_code, e.record);
}

function addNewRow(name) {
	var grid=gridConfig[name].manager; 
	grid.addEditRow({}); //添加一行
}

function clearGrid(name) {
	$.ligerDialog.confirm("亲，确定清空所有数据吗？清空操作请慎重哦！", function (yes) {
        if (yes) {
        	var grid=gridConfig[name].manager; 
        	jsonObj = {};
        	grid.options.data = jsonObj;
        	grid.reload(1);
        }
	})
}

function addRelationRow(grid_name, ysjl_key, report_key) {
	var grid=gridConfig[grid_name].manager; 
	grid.addEditRow({
		id : "",
		fkReportId : fk_report_id,
		reportName : "",
		recordItemName : ysjl_key,
		reportItemName : report_key,
		formule : "=",
		formuleType : "2",
		defaultValue : "",
		recordPageNo : cur_ysjl_page,
		reportPageNo : cur_report_page
	});
} 

function addRowOfYsjlKey(grid_name, column_value) {
	var grid=gridConfig[grid_name].manager; 
	grid.addEditRow({
		id : "",
		fkReportId : fk_report_id,
		reportName : "",
		recordItemName : column_value,
		reportItemName : "",
		formule : "=",
		formuleType : "1",
		defaultValue : "",
		recordPageNo : cur_ysjl_page,
		reportPageNo : cur_report_page
	});
} 

function updateRowOfReportKey(grid_name,column_value)
{
	var manager = gridConfig[grid_name].manager;
	var selected = manager.getSelected();
	if (!selected) {
		alert('亲，请先选择您要加入的对应关系行！');
		return;
	}
	var row_data = manager.getSelectedRow();   
	manager.updateRow(selected, {
		id : row_data.id,
		fkReportId : row_data.fkReportId,
		reportName : "",
		recordItemName : row_data.recordItemName,
		reportItemName : column_value,
		formule : row_data.formule,
		formuleType : row_data.formuleType,
		defaultValue : row_data.defaultValue,
		recordPageNo : row_data.recordPageNo,
		reportPageNo : row_data.reportPageNo
	});
}

function updateRowOfYsjlKey(grid_name,column_value)
{
	var manager = gridConfig[grid_name].manager;
	var selected = manager.getSelected();
	if (!selected) {
		alert('亲，请先选择您要处理的对应关系行！');
		return;
	}
	var row_data = manager.getSelectedRow();   
	if(row_data.recordItemName.indexOf(column_value)==-1){
		manager.updateRow(selected, {
			id : row_data.id,
			fkReportId : row_data.fkReportId,
			reportName : row_data.reportName,
			recordItemName : row_data.recordItemName+","+column_value,
			reportItemName : row_data.reportItemName,
			formule : row_data.formule,
			formuleType : row_data.formuleType,
			defaultValue : row_data.defaultValue,
			recordPageNo : row_data.recordPageNo,
			reportPageNo : row_data.reportPageNo
		});
	}else{
		alert('亲，原始记录项目不能重复，请重新选择！');
		return;
	}
}

function updateRowOfFormule(grid_name,column_value)
{
	var manager = gridConfig[grid_name].manager;
	var selected = manager.getSelected();
	if (!selected) {
		alert('亲，请先选择您要处理的对应关系行！');
		return;
	}
	var row_data = manager.getSelectedRow();   
	if(row_data.formule.indexOf(column_value)==-1){
		manager.updateRow(selected, {
			id : row_data.id,
			fkReportId : row_data.fkReportId,
			reportName : row_data.reportName,
			recordItemName : row_data.recordItemName,
			reportItemName : row_data.reportItemName,
			formule : row_data.formule!="="?row_data.formule+","+column_value:column_value,
			formuleType : row_data.formuleType,
			defaultValue : row_data.defaultValue,
			recordPageNo : row_data.recordPageNo,
			reportPageNo : row_data.reportPageNo
		});
	}else{
		alert('亲，计算公式内容不能重复，请重新选择！');
		return;
	}
}

function deleteRow(name) {
    var grid=gridConfig[name].manager;   // 删除一行
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
    infos:{manager:relationInfoGrid,delUrl:"r3Action/del.do"}
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