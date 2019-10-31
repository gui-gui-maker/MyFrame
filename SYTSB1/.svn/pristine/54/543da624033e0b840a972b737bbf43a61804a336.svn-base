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
	  
	}
	
	//计算分配金额总和是否等于实际总和
	function count(){
		 var data = getData();
		  var sum=0;
		  for(var i=0;i<data.length;i++){
			  var a = (data[i]["money"]-0);
				  sum = (sum-0)+(a-0);
			
		  }
		if(sum<(pay_received-0)){
			return false;
		}else{
			return true;
		}
	}
	
	
	
	function pushBox(e){
	   var cellId=e.column.__domid.replace('hcell','2|'+e.record.__id);
	   var ids=cellId.split('|');
	   var id=ids[0]+'\\|'+ids[1]+'\\|'+ids[2]+'\\|'+ids[3];
	   var top = $("#"+id).offset().top;
	   
	   var left=$("#"+id).offset().left;
	   $("#panl_select").css({position:'absolute',top:top,left:left,width:e.column.width});
		//异步加载报告类型
	  if(e.column.name=='unit_name'){
		  //alert(inspecUnits)
		  var data=inspecUnits;
		  
          var obj=$("#panl_select").ChipCombobox({data:data,top:top,left:left,width:e.column.width,
        	  height:'300px',mult:false,onSelected:function(item,ids,texts){
			   deviceGrid.updateRow(e.record,{unit_name:item.text,unit_id:item.id});
			},icon:'k/kui/images/icons/16/org.png'}).show();
	   return false;
	  }
		
	}
	//定义显示列
	function defineColumns(){
		var columns=[];
		if(pageStatus!="detail"){
			columns.push({ display: "<a class='l-a iconfont l-icon-add' href='javascript:void(0);' onclick='javascript:addRow()' title='增加'><span>增加</span></a>", isSort: false, width: '30',height:'5%', render: function (rowdata, rowindex, value) {
				var h = "";
				if (!rowdata._editing) {
					h += "<a class='l-a l-icon-del' href='javascript:delDevice(\"device\")' title='删除'><span>删除</span></a> ";
				}
				return h;
			}
			});
		}
		columns.push(
			{ display: 'id', name: 'id', hide:true},
			{ display: 'payID',name: 'fk_pay_info_id',  hide:true},
			{ display: '部门id',name: 'unit_id',  hide:true,required:true},
       		{ display: '检验科室', width: '200', name: 'unit_name', type: 'text',required:false, editor: { type: 'text'}},
			 { display: '金额', width: '100', name: 'money', type: 'text', editor: { type: 'float',minValue:'0',isNegative :true},required:true,min:0 }
       		,
            { display: '备注', name: 'remark', width: '24%', editor: { type: 'text'},maxlength:232}
		   
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
	
	function addRow(){
		var selectRow=[];
		var tt = {fk_pay_info_id:id};
   		selectRow.push(tt);
   		deviceGrid.addRow(tt);
	}
	//添加检验设备
	function addDevice() {
	var fk_unit_id = document.getElementById("fk_unit_id").value;
	var com_name = document.getElementById("com_name").value;
	var com_address = document.getElementById("com_address").value;
	var device_type = $('#device_type').ligerComboBox().getValue();
	
	if(fk_unit_id==null||fk_unit_id==undefined || fk_unit_id ==""){
		top.$.notice("请先选择受检单位！");
	}else if(device_type==null||device_type==undefined || device_type ==""){
		top.$.notice("请先选择设备类型！");
	}else{
	
		top.$.dialog({
				parent: api,
				width : 1024, 
				height : 550, 
				lock : true, 
				title:"选择报检设备",
				content: 'url:app/tzsb/inspection/flow/insing/support/dev_list.jsp?org_id='+fk_unit_id+'&device_type='+device_type,
				data : {"parentWindow" : window,com_name:com_name,com_address:com_address}
			});
		}
	
}

	//删除报检设备
	function delDevice(name) {
		var data = deviceGrid.getSelectedRow();
		
		if(data.id==null||data.id==undefined||data.id==""){
			deviceGrid.deleteSelectedRow();
		}else{
			 $.ligerDialog.confirm(kui.DEL_MSG, function (yes) {
				 if (yes) {
					 $.getJSON("reportOpinspAction/delItem.do", {id: data.id}, function (json) {
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

















