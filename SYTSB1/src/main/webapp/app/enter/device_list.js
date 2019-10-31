var deviceGrid;
function createDeviceGrid() {	
    var columns=[
        { display: 'id', name: 'id',  hide:true},
        { display: '设备类别', width: '15%', name: 'device_sort_code', type: 'text', 
            render: function (item) {
                return render(item["device_sort_code"],deviceType);
            }
        },
        { display: '设备名称', width: '15%', name: 'device_name', type: 'text'},
        { display: '设备注册代码', name: 'device_registration_code', width: '15%'},
      	{ display: '使用登记证号', width: '15%', name: 'registration_num', type: 'text'},
      	{ display: '出厂编号', width: '15%', name: 'factory_code', type: 'float'},
      	{ display: '下次检验日期', width: '15%', name: 'inspect_next_date', type: 'date'},
        { display: '备注', name: 'note', width: '30%', type: 'text', align: 'left', maxlength:200}    
    ];
    deviceGrid = $("#deviceList").ligerGrid({
    	columns: columns,
        enabledEdit: pageStatus!="detail",
        clickToEdit: true,
        rownumbers: true,    
        height:"200",
        width:"755",
        //是否显示行序号
        frozenRownumbers: false,
        usePager: false,
        data: {Rows: [
        ]}
    });
    gridConfig["deviceList"].manager=deviceGrid;
}

var gridConfig={
    deviceList:{manager:deviceGrid}
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