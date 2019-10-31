var reportBorrowGrid;

function createReportBorrowRecordGrid() {
	var columns=[
	             { display: 'id', name: 'id', hide:true},
	             { display: '检验时间', name: 'advanceTime', hide:true},
	             { display: '报告外键ID', name: 'fkReportId', hide:true},
	             { display: '报告书编号', width: '20%', name: 'reportSn', type: 'text', editor: { type: 'text'},required:true,maxlength:200},
	             { display: '台数', width: '16%', name: 'reportCount', type: 'int', editor: { type: 'spinner'},required:false,maxlength:32},
	             { display: '使用单位', width: '50%', name: 'comName', type: 'text', editor: { type: 'text'},required:false,maxlength:200}, 
	             { display: '参检人员名字', name: 'checkOpName',hide:true, type: 'text', editor: { type: 'text'},required:false,maxlength:200},
	             { display: '检验部门ID', name: 'checkUnitId',hide:true, type: 'text', editor: { type: 'text'},required:false,maxlength:32},
	             { display: '检验部门', name: 'orgName',hide:true, type: 'text', editor: { type: 'text'},required:false,maxlength:200},
	             { display: '设备注册代码', name: 'deviceRegistrationCode',hide:true, type: 'text', editor: { type: 'text'},required:false,maxlength:200},
	             { display: '是否已归还',width: '10%', name: 'isBack',type: 'text',align: 'center',
	    	 			render:function(rowData,rowid){
	                    	if(rowData.isBack=="0"){
	                        	return "未归还";
	                    	}else if(rowData.isBack=="1"){
	                        	return "已归还";
	                    	}
	                	}
	             }];
	
    if(pageStatus=="modify"||pageStatus=="add"){
    	if(isSg=="0"){
    		columns.unshift({ display: "", isSort: false, width: '4%',height:'5%', render: function (rowdata, rowindex, value) {
                var h = "";
                if (!rowdata._editing) {
                    h += "<a class='l-a l-icon-del' href='javascript:deleteRow(\"reportBorrowRecords\")' title='删除'><span>删除</span></a> ";
                }
                return h;
            }
            });
    	}else if(isSg=="1"){
    		columns.unshift({ display: "<a class='l-a iconfont l-icon-add' href='javascript:void(0);' onclick='addNewRow(\"reportBorrowRecords\")' title='增加'><span>增加</span></a>", isSort: false, width: '4%',height:'5%', render: function (rowdata, rowindex, value) {
                var h = "";
                if (!rowdata._editing) {
                    h += "<a class='l-a l-icon-del' href='javascript:deleteRow(\"reportBorrowRecords\")' title='删除'><span>删除</span></a> ";
                }
                return h;
            }
            });
    	}
    }

    reportBorrowGrid = $("#reportBorrowRecords").ligerGrid({
    	columns: columns,
    	enabledEdit: pageStatus!="detail"&&isSg=="1",
        rownumbers: true,    
        height:"85%",
        width:"100%",
        //是否显示行序号
        frozenRownumbers: false,
        usePager: false,
        //onAfterEdit: f_onAfterEdit,
        data: {Rows: [
        ]},
        rowAttrRender : function(rowData, rowid) {
            var fontColor="black";
            if(rowData.isBack=='0') {
                fontColor="red";
            }else if(rowData.isBack=='1') {
                fontColor="green";
            }
            return "style='color:"+fontColor+"'"; 
        }
    });
    gridConfig["reportBorrowRecords"].manager=reportBorrowGrid;
    
}

//编辑后事件 
function f_onAfterEdit(e){
	var grid=gridConfig["reportBorrowRecords"].manager; 
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
    	grid.deleteSelectedRow();
    	 /*$.ligerDialog.confirm(kui.DEL_MSG, function (yes) {
             if (yes) {
                 $.getJSON(gridConfig[name]["delUrl"], {ids: data.id}, function (json) {
                     if (json.success) {
                         grid.deleteSelectedRow();
                     }
                 });
             }
         });*/
    }
}

var gridConfig={	
	reportBorrowRecords:{manager:reportBorrowGrid,delUrl:""}
    /*reportBorrowRecords:{manager:reportBorrowGrid,delUrl:"archivesBorrowSubAction/delete.do"}*/
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
