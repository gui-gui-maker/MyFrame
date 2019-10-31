var degreeGrid;
function createDegreeGrid() {
	
    var columns=[
        { display: 'id', name: 'id',  hide:true},
        { display: '设备类别', width: '20%', name: 'device_type', type: 'text',required:true,
        	editor: { type: 'select', data: deviceType ,ext:{emptyOption:false}},
            render: function (item) {
                return render(item["device_type"],deviceType);
            }
        },
        { display: '设备数量', width: '10%', name: 'device_no', type: 'text', editor: { type: 'spinner'},required:true,maxlength:32  },
      
        { display: '流转科室', width: '15%', name: 'unit_code', type: 'text',required:true,
        	editor: { type: 'select', data: unitname ,ext:{emptyOption:true}},
            render: function (item) {
                return render(item["unit_code"],unitname);
            }
        },
        { display: '收费情况', width: '10%', name: 'charge_situation', type: 'text',required:true,
        	editor: { type: 'select', data: charge ,ext:{emptyOption:true}},
            render: function (item) {
                return render(item["charge_situation"],charge);
            }
        },
        
        { display: '备注', name: 'remark', width: '38%', editor: { type: 'text'},maxlength:232}
       
    ];
    if(pageStatus!="detail"){
        columns.push({ display: "<a class='l-a iconfont l-icon-add' href='javascript:void(0);' onclick='addNewRow(\"degree\")' title='增加'><span>增加</span></a>", isSort: false, width: '4%',height:'5%', render: function (rowdata, rowindex, value) {
            var h = "";
            if (!rowdata._editing) {
                h += "<a class='l-a l-icon-del' href='javascript:deleteRow(\"degree\")' title='删除'><span>删除</span></a> ";
            }
            return h;
        }
        });
    }
    degreeGrid = $("#degree").ligerGrid({
    	columns: columns,
        enabledEdit: pageStatus!="detail",
        clickToEdit: true,
        rownumbers: true,    
        height:"200",
        width:"770",
        //是否显示行序号
        frozenRownumbers: false,
        usePager: false,
        data: {Rows: [
        ]}
    });
    gridConfig["degree"].manager=degreeGrid;
}


function addNewRow(name) {
	var grid=gridConfig[name].manager; 
	grid.addEditRow({}); //添加一行
}
function deleteRow(name) {

    var grid=gridConfig[name].manager;   //删除一行
    var data = grid.getSelectedRow();
    if (data.id) {

    	$.getJSON('department/basic/getPara.do',{paraId:data.id},function(dataPara){
    		if(dataPara.success){
    			top.$.notice("设备已报检！");
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
    	});
    } else {
        grid.deleteSelectedRow();
    }
}

var gridConfig={
	
    degree:{manager:degreeGrid,delUrl:"inspection/para/delete.do"}
 
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