var errorRecordGrid;
function createInfoGrid() {
    var columns=[
        { display: 'id', name: 'id', hide:true},
        { display: 'info_id', name: 'info_id', hide:true},
        { display: 'report_id', name: 'report_id', hide:true},
        { display: 'new_info_id', name: 'new_info_id', hide:true},
        { display: 'new_report_id', name: 'new_report_id', hide:true},
        { display: '报告书编号', width: '15%', name: 'report_sn', type: 'text',required:false,maxlength:24},
        { display: '报告状态', width: '12%', name: 'report_status',required:true, 
        	 editor: { type: 'select', data:reportStatusList ,ext:{emptyOption:false}},
        	 	render: function (item) {
        	    	return render(item["report_status"],reportStatusList);
        		}
        },
        { display: '不符合事实陈述', width: '36%', name: 'error_desc', type: 'text',required:false,maxlength:1000},
        { display: '不符合类型', width: '12%', name: 'error_type',required:true, 
        	 	render: function (item) {
        	    	return render(item["error_type"],errorTypeList);
        		}
        },
        { display: '新报告编号', width: '15%', name: 'new_report_sn', type: 'text', editor: { type: 'text'},required:false,maxlength:14}//,
        //{ display: '处理结果', width: '15%', name: 'deal_remark', type: 'text', editor: { type: 'text'},required:false,maxlength:1000}
        
    ];
	/*
    if(pageStatus!="detail" && op_type==""){
        columns.unshift({ display: "<a class='l-a iconfont l-icon-add' href='javascript:void(0);' onclick='addNewRow(\"infos\")' title='增加'><span>增加</span></a>", isSort: false, width: '4%',height:'5%', render: function (rowdata, rowindex, value) {
            var h = "";
            if (!rowdata._editing) {
                h += "<a class='l-a l-icon-del' href='javascript:deleteRow(\"infos\")' title='删除'><span>删除</span></a> ";
            }
            return h;
        }
        });
    }
    */
	errorRecordGrid = $("#infos").ligerGrid({
		columns: columns,
		enabledEdit: true,
		rownumbers: true,    
		height:"55%",
		width:"100%",
		//是否显示行序号
		frozenRownumbers: false,
		usePager: false,
		//onAfterEdit: f_onAfterEdit,
		data: {Rows: [
		]}
	});
    gridConfig["infos"].manager=errorRecordGrid;
    
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

function deleteRow(name) {
    var grid=gridConfig[name].manager;   //删除一行   
    var data = grid.getSelectedRow();   
    if(data.id==null||data.id==undefined||data.id==""){
    	grid.deleteSelectedRow();
    }else{
    	 $.ligerDialog.confirm(kui.DEL_MSG, function (yes) {
             if (yes) {
                 $.getJSON(gridConfig[name]["delUrl"], {ids: data.id}, function (json) {
                     if (json.success) {
                         grid.deleteSelectedRow();
                     }
                 });
             }
         });
    }
}

var gridConfig={	
    infos:{manager:errorRecordGrid,delUrl:"report/error/record/info/del.do"}
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
