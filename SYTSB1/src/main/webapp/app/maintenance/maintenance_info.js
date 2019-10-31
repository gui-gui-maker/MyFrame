var maintenanceGrid;
var typeArr = [ {  id:'1' ,text:'新功能'}, { id:'2', text:'优化功能'}, { id:'3', text:'数据维护'}];
var priorityArr = [ {  id:'0' ,text:'一般 '}, { id:'1', text:'重要'}, { id:'2', text:'紧急'}];
function createInfoGrid() {
    var columns=[
        { display: 'id', name: 'id', hide:true},
        //{ display: 'sn', name: 'sn', hide:true},
        { display: '类型', width: '12%', name: 'info_type', editor: { type: 'text'},required:true,
        	 editor: { type: 'select', data:typeArr ,ext:{emptyOption:false}},
        	 	render: function (item) {
        	    	if (item["info_type"] == "1"){
        	    		return "新功能";
        	    	}else if(item["info_type"] == "2"){
        	    		return "优化功能";
        	    	}else if(item["info_type"] == "3"){
        	    		return "数据维护";
        	    	}else{
        	    		return "";
        	    	}
        		}
        },
        /*{ display: '优先级', width: '8%', name: 'priority', editor: { type: 'text'},required:true,
        	 editor: { type: 'select', data:priorityArr ,ext:{emptyOption:false}},
        	 	render: function (item) {
        	    	return render(item["priority"],priorityList);
        		}
        },*/
        { display: '功能模块', width: '15%', name: 'func_name', type: 'text', editor: { type: 'text'},required:true,
        	 editor: { type: 'select', data:functionList ,ext:{emptyOption:false}},
	     	 	render: function (item) {
	     	    	return render(item["func_name"],functionList);
	     		}
        },
        { display: '内容描述', width: '40%', name: 'pro_desc', type: 'text', editor: { type: 'text'},required:true,maxlength:1000},
        { display: '业务部门', width: '20%', name: 'org_id', type: 'text',required:true,        	
            editor: { type: 'select', data: orgList ,ext:{emptyOption:false,selectBoxHeight:900}},
            render: function (item) {
            	var arr1=[];
				for (var i in orgList) {
					var bds=(","+item["org_id"]+",").indexOf(","+orgList[i]["id"]+",")>=0;
					if (bds) {
						arr1.push(orgList[i]['text']);
					}
				}
				if(arr1.toString()=='' || arr1.toString()==null){
					return render(item["org_id"],orgList);
				}else{
             		return arr1.toString();
				}
        	}
        },
        /*{ display: '报告人', width: '10%', name: 'advance_user_id', type: 'text', required:true,
        	editor: { type: 'select', data: userList ,ext:{emptyOption:false,selectBoxHeight:360}},
            render: function (item) {
            	var arr1=[];
				for (var i in userList) {
					var bds=(","+item["advance_user_id"]+",").indexOf(","+userList[i]["id"]+",")>=0;
					if (bds) {
						arr1.push(userList[i]['text']);
					}
				}
				if(arr1.toString()=='' || arr1.toString()==null){
					return render(item["advance_user_id"],userList);
				}else{
             		return arr1.toString();
				}
        	}
        },   */
        { display: '报告人', width: '10%', name: 'advance_user_name', type: 'text', editor: { type: 'text'},required:true,maxlength:20},
        { display: '报告日期', width: '10%', name: 'advance_date', editor: { type: 'date'},format:'yyyy-MM-dd',type:'date',required:false},
        { display: '记录人', width: '8%', name: 'create_user_name', type: 'text', editor: { type: 'text'},required:false,maxlength:20}, 
        { display: '记录日期', width: '10%', name: 'create_date', editor: { type: 'date'},format:'yyyy-MM-dd',type:'date',required:false},
        { display: '数据状态', width: '8%', name: 'data_status', hide:true},
        { display: '受理人', width: '8%', name: 'receive_user_name', type: 'text', editor: { type: 'text'},required:false,maxlength:20},   
        { display: '受理日期', width: '10%', name: 'receive_date', editor: { type: 'date'},format:'yyyy-MM-dd',type:'date',required:false},
        { display: '开发员', width: '8%', name: 'develop_user_name', hide:true},   
        { display: '开始开发日期', width: '10%', name: 'develop_start_date', hide:true},
        { display: '完成开发日期', width: '10%', name: 'develop_end_date', hide:true},
        { display: '完成情况', width: '10%', name: 'develop_desc', hide:true},
        { display: '发布人', width: '8%', name: 'publish_user_name', hide:true}, 
        { display: '发布日期', width: '10%', name: 'publish_date', hide:true},
        { display: '备注', width: '10%', name: 'remarks', type: 'text', editor: { type: 'text'},required:false,maxlength:1000}
    ];
	
    if(pageStatus!="detail"){
        columns.unshift({ display: "<a class='l-a iconfont l-icon-add' href='javascript:void(0);' onclick='addNewRow(\"infos\")' title='增加'><span>增加</span></a>", isSort: false, width: '4%',height:'5%', render: function (rowdata, rowindex, value) {
            var h = "";
            if (!rowdata._editing) {
                h += "<a class='l-a l-icon-del' href='javascript:deleteRow(\"infos\")' title='删除'><span>删除</span></a> ";
            }
            return h;
        }
        });
    }

    maintenanceGrid = $("#infos").ligerGrid({
    	columns: columns,
    	enabledEdit: pageStatus!="detail",
        rownumbers: true,    
        height:"45%",
        width:"100%",
        //是否显示行序号
        frozenRownumbers: false,
        usePager: false,
        //onAfterEdit: f_onAfterEdit,
        data: {Rows: [
        ]}
    });
    gridConfig["infos"].manager=maintenanceGrid;
    
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
    infos:{manager:maintenanceGrid,delUrl:"maintenance/del.do"}
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
