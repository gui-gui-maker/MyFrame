var degreeGrid;
function createDegreeGrid() {
	
    var columns=[
                 { display: 'id', name: 'id', hide:true},
                 { display: 'sn', name: 'sn', hide:true},
                 { display: 'fk_tsjc_device_document_id',name: 'fk_tsjc_device_document_id',  hide:true},
                 { display: '检验性质', width: '10%', name: 'check_type', type: 'text',required:true,
                	 render: function (item) {
                     return render(item["check_type"],checkType);
                	 }
                 },
                 { display: '业务流水号', width: '15%', name: 'sn', type: 'text',required:true,maxlength:32  },
                 { display: '受检单位', width: '15%', name: 'company_name', type: 'text',required:true,maxlength:300 },
                
                 { display: '设备类别', width: '10%', name: 'device_sort_code', type: 'text',required:true,
                     render: function (item) {
                         return render(item["device_sort_code"],deviceType);
                     }
                 },
                 { display: '设备名称', width: '21%', name: 'device_name', type: 'text',required:true,maxlength:32  },
                 { display: '预检日期', width: '10%', name: 'advance_time', editor: { type: 'date'},format:'yyyy-MM-dd',type:'date',required:true },
                 
                 //{ display: '项目负责人', width: '10%', name: 'item_op_id', type: 'text', required:true,
                //	 editor: { type: 'select', data: fzr ,ext:{emptyOption:false}},
                //	 render: function (item) {
                 //        return render(item["item_op_id"],fzr);
                //     }
                // },
                  { display: '参检人员', width: '17%', name: 'check_op_id', type: 'text', required:true,
                	 editor: { type: 'select', data: fzr ,ext:{emptyOption:false,isMultiSelect: true}},
                	 render: function (item) {
                		var arr1=[];
						for (var i in fzr) {
							var bds=(","+item["check_op_id"]+",").indexOf(","+fzr[i]["id"]+",")>=0;
							if (bds) {
								arr1.push(fzr[i]['text']);
							}
						}
						if(arr1.toString()=='' || arr1.toString()==null){
							 return render(item["check_op_id"],fzr);
						}else{
             			 return arr1.toString();
						}
                         
                     }
                	
                  }
                 
       
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
        width:"100%",
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


