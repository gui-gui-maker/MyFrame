	var deviceGrid;
	var deviceType='';
	var reportType='';
	var fk_unit_id;
	var com_name;
	var com_address;
	var device_type;
	var inspecUnits;
	
	var selectUser=[];
	//初始化表格
	function initGrid() {
        deviceGrid = $("#device").ligerGrid({
    	columns: defineColumns(),
        enabledEdit: pageStatus!="detail",
        clickToEdit: true,
        rownumbers: true,    
        height:"280",
        width:"99%",
        frozenRownumbers: false,
        usePager: false,
        data: {Rows: []},
        onBeforeEdit:pushBox,
        onAfterEdit:changeBox
    });
   
}
	function changeBox(e){
		var name = e.column.name
	  if("other_applicants"==name){
		  var obj=$("#panl_select").ChipCombobox({data:opAttr,top:top,left:left,width:e.column.width,mult:false,onSelected:function(item){
			  deviceGrid.updateRow(e.record,{other_applicants:item.text,other_applicantsId:item.id});
			},icon:'k/kui/images/icons/16/application_view_tile.png'}).show();
			obj.setText(e.record.other_applicants);
	  }
		 if("subsidy_money"==name){
			var data = getData();
			  var sum=0;
			  for(var i=0;i<data.length;i++){
			      if(data[i]["subsidy_money"]!=undefined&&data[i]["subsidy_money"]!=""){
					 sum = sum+(data[i]["subsidy_money"]-0);
						 
				  }
			  }
			 sum = sum.toFixed(2);
			  
			 $("#sumMoney").val(sum);
			  
			  
		 }
		
	}
	
	function pushBox(e){
	   var cellId=e.column.__domid.replace('hcell','2|'+e.record.__id);
	   var ids=cellId.split('|');
	   var id=ids[0]+'\\|'+ids[1]+'\\|'+ids[2]+'\\|'+ids[3];
	   var top = $("#"+id).offset().top;
	   
	   var left=$("#"+id).offset().left;
	   
	   $("#panl_user").css({position:'absolute',top:top,left:left,width:e.column.width});
	   if(e.column.name=='other_applicants'){
		   var obj=$("#panl_select").ChipCombobox({data:opAttr,top:top,left:left,width:e.column.width,height:'120px', mult:false,onSelected:function(item){
			    	deviceGrid.updateRow(e.record,{other_applicants:item.text,other_applicants_id:item.id});
				},icon:'k/kui/images/icons/16/application_view_tile.png'}).show();
				obj.setText(e.record.other_applicants);
				return false;
	   }
	   
	}
	//定义显示列
	function defineColumns(){
		var columns=[];
		if(pageStatus!="detail"){
			columns.push({ display: "<a class='l-a l-icon-add iconfont' href='javascript:void(0);' onclick='javascript:addDevice()' title='增加'><span>增加</span></a>", isSort: false, width: '30',height:'5%', render: function (rowdata, rowindex, value) {
				var h = "";
				if (!rowdata._editing) {
					h += "<a class='l-a l-icon-del iconfont' href='javascript:delDevice(\"device\")' title='删除'><span>删除</span></a> ";
				}
				return h;
			}
			});
		}
		columns.push(
			{ display: 'id', name: 'id', hide:true},
			{ display: 'fk_user_id', name: 'fk_user_id', hide:true},
			/*{ display: '序号', width: '10%', name: 'xuhao', type: 'text', required:false,maxlength:32},  */
			{ display: '加班人员ID', name: 'other_applicants_id', type: 'text',hide:true,maxlength:32, editor: { type: 'text'} },
			{ display: '加班人员', width: '20%', name: 'other_applicants', type: 'text',required:false,maxlength:32, editor: { type: 'text'} },		
			{ display: '加班人意见(补休/补助)', width: '20%', name: 'overtime_type', type: 'text',required:false,editor: { type: 'select',data: deviceType ,ext:{emptyOption:true}},
				render: function (item) {
				return render(item["overtime_type"],deviceType);}
			},
			{ display: '补助金额', width: '10%', name: 'subsidy_money', type: 'text',required:false, editor: { type: 'float'}},
			{ display: '补休开始时间', width: '15%', name: 'take_date_start', type: 'text', editor: { type: 'text'}},
			{ display: '补休结束时间', width: '15%', name: 'take_date_end', type: 'text', editor: { type: 'text'}}
			
			/*{ display: '补休开始时间', width: '15%', name: 'take_date_start', type: 'text',required:false, editor: { type: 'date',ext:{format:'yyyy-MM-dd hh:mm'}},format:'yyyy-MM-dd hh:mm'},
			{ display: '补休结束时间', width: '15%', name: 'take_date_end', type: 'text',required:false, editor: { type: 'date',ext:{format:'yyyy-MM-dd hh:mm'}},format:'yyyy-MM-dd hh:mm'}
			*/
		);
		
		
		
		return columns;
	}
	//重新渲染列
	function reRenderColumns(){
	deviceGrid.setOptions( 
				{ columns: defineColumns() } 
				);
				
	deviceGrid.loadData(true);  
	}
	
	
	
	//添加
	function addDevice() {
		
		var selectRow=[];
		var l = deviceGrid.getData().length;
		var tt = {};
		selectRow.push(tt);
		deviceGrid.addRow(tt);
}

	
	function callBack(sn){
		updateSelRow({audit_task:sn})
	}
	
	//删除报检设备
	function delDevice(name) {
		var data = deviceGrid.getSelectedRow();
		
		if(data.id==null||data.id==undefined||data.id==""){
			deviceGrid.deleteSelectedRow();
		}else{
			 $.ligerDialog.confirm(kui.DEL_MSG, function (yes) {
				 if (yes) {
					 $.getJSON("allowancefoAction/delete.do", {ids: data.id}, function (json) {
						 if (json.success) {
							 deviceGrid.deleteSelectedRow();
						 }
					 });
				 }
			 });
		}
		
	}
	

	function render(value,data,fname){
		if(fname=='checkOpId'){
		if(value!=undefined&&value.indexOf(',')!=-1){
				var values = value.split(",");
				var text=[];
				for(var ele in values){
					 for (var i in data) {
					  if (data[i]["id"] == values[ele]){
						  text.push(data[i]['text']);
						 }
						}
					}
				return text;
			}
		}
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
	//获取表格数据
	function getData(){
	return deviceGrid.getData();
	}
	//设置报告类型
	function setReportTypes(data){
	   reportType=data;

	}
	//获取表格数据
	function getRows(){
	 return deviceGrid.rows;

	}
	//获取表格数据
	function setInspecUnits(data){
	 inspecUnits=data;
	
	}
	function updateRow(rows,param){
	 deviceGrid.updateRow(rows,param);
	}
	function updateSelRow(param){
	 updateRow(deviceGrid.getSelectedRow(),param);
	}
	function setUsers(data){
	 selectUser=data;
	}
	function getUsers(){
	 return selectUser;
	}

















