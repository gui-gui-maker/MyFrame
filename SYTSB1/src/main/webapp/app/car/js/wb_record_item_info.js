var wbRecordItemGrid;

function createWbRecordItemGrid() {
    var columns=[
        { display: 'id', name: 'id', isAllowHide: false, hide:true},
        { display: 'fkWbRecordId', name: 'fkWbRecordId', isAllowHide: false, hide:true},
        { display: 'fkCarId', name: 'fkCarId', isAllowHide: false, hide:true},
        { display: 'dataStatus', name: 'dataStatus', isAllowHide: false, hide:true},
        { display: '项目', width: '57%', name: 'itemName', align:'left', type: 'text', editor: { type: 'text'},required:true,maxlength:4000},
        { display: '类别', width: '8%', name: 'itemType', align:'center', type: 'text', editor:{
            type:"select",
            data:[{id:"0",text:"维修"},{id:"1",text:"保养"}, { id:'2',text:'维修加保养'}],
            valueField:"id",
            textField:"text"
            },render: function (item) {
    	    	return render(item["itemType"],[{id:'0',text:'维修'},{id:'1',text:'保养'}, {id:'2',text:'维修加保养',}]);
    		},required:true,maxlength:32},
    	{ display: '是否更换零部件', width: '13%', name: 'isRenew', align:'center', type: 'text', editor:{
    		type:"select",
            data:[{id:"0",text:"否"},{id:"1",text:"是"}],
            valueField:"id",
            textField:"text"
            },render: function (item) {
    	    	return render(item["isRenew"],[{id:"0",text:"否"},{id:"1",text:"是"}]);
    		},required:true,maxlength:32},
        { display: '金额', width: '12%', name: 'itemValue', align:'center', type: 'float', editor: { type: 'float'},required:true,maxlength:32,
        	totalSummary:{
                type:'sum'
            }
        	}
    ];
	
    if(pageStatus!="detail"){
        columns.unshift({ display: "<a class='l-a iconfont l-icon-add' href='javascript:void(0);' onclick='addNewRow(\"wbRecordItem\")' title='增加'><span>增加</span></a>", isSort: false, width: '4%',height:'5%', render: function (rowdata, rowindex, value) {
            var h = "";
            if (!rowdata._editing) {
                h += "<a class='l-a l-icon-del' href='javascript:deleteRow(\"wbRecordItem\")' title='删除'><span>删除</span></a> ";
            }
            return h;
        }
        });
    }

    wbRecordItemGrid = $("#wbRecordItem").ligerGrid({
    	columns: columns,
    	enabledEdit: pageStatus!="detail" ,
        rownumbers: true,
        height:"70%",
        width:"100%",
        align:"left",
        //是否显示行序号
        frozenRownumbers: false,
        usePager: false,
        //onAfterEdit: f_onAfterEdit,
        data: {Rows: [
        ]},
        onAfterShowData:function(){
            $(".l-grid-row-cell-inner").css("height","auto"); //单元格高度自动化，撑开 
            var i=0;
            $("tr",".l-grid2","#maingrid").each(function ()  
            { 
                $($("tr",".l-grid1","#maingrid")[i]).css("height",$(this).height()); //2个表格的tr高度一致
                i++;
            });
        }
    });
    gridConfig["wbRecordItem"].manager=wbRecordItemGrid;
    
}

//编辑后事件 
function f_onAfterEdit(e){
	var grid=gridConfig["wbRecordItem"].manager; 
	var data = grid.getSelectedRow();   
	alert(data.device_type_code);
	alert(e.column.name);
	grid.updateCell('device_name', data.device_type_code, e.record);
}

function addNewRow(name) {
	var grid=gridConfig[name].manager; 
	grid.addEditRow({
		id: '', 
		fkWbRecordId : '',
		fkCarId : '',
		itemName : '',
		itemType: '',
		isRenew: '',
		itemValue: '',
		dataStatus : "0"}); //添加一行
}

function deleteRow(name) {
    var grid=gridConfig[name].manager;   //删除一行   
    var data = grid.getSelectedRow();   
    if(data.id==null||data.id==undefined||data.id==""){
    	grid.deleteSelectedRow();
    }else{
    	 $.ligerDialog.confirm(kui.DEL_MSG, function (yes) {
             if (yes) {
            	 grid.deleteSelectedRow();
                 /*$.getJSON(gridConfig[name]["delUrl"], {ids: data.id}, function (json) {
                     if (json.success) {
                         grid.deleteSelectedRow();
                     }
                 });*/
             }
         });
    }
}

var gridConfig={	
    wbRecordItem:{manager:wbRecordItemGrid,delUrl:"inspection/zzjdinfo/del.do"}
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
