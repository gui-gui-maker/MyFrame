<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>设备信息</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript" src="app/common/js/render.js"></script>
		<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
		<script type="text/javascript">
        var qmUserConfig = {
        	sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	// 默认值，可自定义
            sp_fields:[
				{name:"card_no", compare:"like"},
				{name:"eq_archive_class_id", compare:"like"}, 
				{name:"eq_no", compare:"like"},
            	{name:"eq_name", compare:"like"},
            	{name:"eq_model", compare:"like"},
            	{name:"eq_sn", compare:"like"},
            	{group:[
						{name:"eq_buy_date", compare:">=", value:""},
						{label:"到", name:"eq_buy_date", compare:"<=", value:"", labelAlign:"center", labelWidth:20}
					]},
				{name:"eq_user", compare:"like"},
				{name:"eq_use_department", compare:"like"},
            	{name:"eq_status", compare:"="},
				{name:"eq_inout_status", compare:"="},
				{name:"eq_check_cycle", compare:"like"},
				{name:"inventory", compare:"like"},
				{name:"BOX_NUMBER", compare:"like"},
				{name:"owner", compare:"="},
				{name:"position", compare:"like"},
				{group:[
					{name:"yuanzhi", compare:">=", value:""},
					{label:"到", name:"yuanzhi", compare:"<=", value:"", labelAlign:"center", labelWidth:20}
				]},
				{name:"remark", compare:"like"}
            ],
            tbar:[
            	{ text:'详情', id:'detail',icon:'detail', click: detail}
            	<sec:authorize ifAnyGranted="TJY2_EQ_MANAGER,sys_administrate">
            	,"-",
                { text:'增加', id:'add',icon:'add', click: add},
                "-",
                { text:'修改', id:'modify',icon:'modify', click:modify},
                "-",
                { text:'删除', id:'del',icon:'delete', click:del},
                "-",
                { text:'启用', id:'start',icon:'accept', click:start},
                "-",
                { text:'封存', id:'sealed',icon:'forbid', click:sealed},
                "-",
                { text:'报废', id:'scraped',icon:'recycle', click:scraped},
                "-",
                { text:'盘点', id:'inventory',icon:'ok', click:inventory},
                "-",
                { text:'查看', id:'look',icon:'search', click:look}
            	</sec:authorize> 
            ],
            listeners: {
                selectionChange : function(rowData,rowIndex){
                	selectionChange();
                },
        		rowDblClick :function(rowData,rowIndex){
        			detail(rowData.id);
        		},
        		rowAttrRender : function(rowData, rowid) {
    	            var fontColor="black";
    	            if(rowData.inventory=='未盘点'){
    	           	 fontColor="#8E8E8E";
    	            }
    	            return "style='color:"+fontColor+"'";
    	        }
            }
        };
        
        // 行选择改变事件
		function selectionChange() {
	   		var count = Qm.getSelectedCount(); // 行选择个数
	   		var eq_status = Qm.getValueByName("eq_status");//设备状态
	   	    if(eq_status == "启用"){ 
			Qm.setTbar({
				detail: count==1,
				edit: count==1,
				del: count>0,
				modify:count==1,
				start:count<0,
				sealed:count==1,
				scraped:count==1
				
			});}else if(eq_status == "报废"){
				Qm.setTbar({
					detail: count==1,
					edit: count==1,
					del: count>0,
					modify:count==1,
					add:count==1,
					start:count<0,
					sealed:count<1,
					scraped:count<1
					
				});
			}else if (eq_status == "封存"){
				Qm.setTbar({
					detail: count==1,
					edit: count==1,
					del: count>0,
					modify:count==1,
					start:count==1,
					sealed:count<0,
					scraped:count==1
					
				});
			}else{
				Qm.setTbar({
					detail: count==1,
					edit: count==1,
					del: count>0,
					modify:count==1,
					start:count<0,
					sealed:count<0,
					scraped:count<0
					
				});
				
			}
		}
	
        
        function detail(id){
			if($.type(id)!="string"){
				id = Qm.getValueByName("id").toString();
			}
			top.$.dialog({
				width:700,
				height:470,
				lock:true,
				title:"查看详情",
				/* content: 'url:app/equipment/base/equipment_basic_detail.jsp?status=detail&id=' + id+'&eq_name='+eq_name+'&eq_no='+eq_no+'&eq_model='+eq_model+'&eq_manufacturer='+eq_manufacturer, */
				content: 'url:app/equipment/base/equipment_basic_detail.jsp?status=detail&id=' + id,
				data:{window:window}
			});
		}
        
        //新增
        function add(){
			top.$.dialog({
				width:700,
				height:500,
				lock:true,
				title:"新增",
				data: {window:window},
				content: 'url:app/equipment/base/equipment_basic_detail.jsp?status=add'
			});
        }
        
        //修改
        function modify(){
			top.$.dialog({
				width:700,
				height:500,
				lock:true,
				title:"修改",
				data: {window:window},
				content: 'url:app/equipment/base/equipment_basic_detail.jsp?status=modify&id='+Qm.getValueByName("id")
			});
        } 
        
        //删除
        function del(){
            $.del("确定要删除？删除后无法恢复！","equipment2Action/delete.do",{"ids":Qm.getValuesByName("id").toString()});
        }      
     	 //启用
        function start(){
        	$.del("确定要启用设备？","equipment2Action/start.do",{"ids":Qm.getValueByName("id").toString()});
        }
        //封存
        function sealed(){
        	 var inoutstatus = Qm.getValueByName("eq_inout_status").toString();
        	 var box_status = Qm.getValueByName("box_status").toString();
        	 var ids = Qm.getValuesByName("id").toString();
        	 if(inoutstatus == "已出库"||inoutstatus == "待入库"){
        		 $.ligerDialog.error("请先将设备入库！");
        	 }else if(box_status == "已装箱"){
        		 $.ligerDialog.error("请先将设备移除箱子！");
        	 }else{
        		 $.del("确定要封存设备？","equipment2Action/sealed.do",{"ids":Qm.getValuesByName("id").toString()}); 
        	 }
        	 /* $.del("确定要封存设备？","equipment2Action/sealed.do",{"ids":Qm.getValuesByName("id").toString()}); */
        }
        //报废
        function scraped(){
        	var inoutstatus = Qm.getValueByName("eq_inout_status").toString();
        	var box_status = Qm.getValueByName("box_status").toString();
       	 	var ids = Qm.getValuesByName("id").toString();
       	 	if(inoutstatus == "已出库"||inoutstatus == "待入库"){
       			 $.ligerDialog.error("请先将设备入库！");
       		 }else if(box_status == "已装箱"){
        		 $.ligerDialog.error("请先将设备移除箱子！");
        	 }else{
       			$.del("确定要报废设备？","equipment2Action/scraped.do",{"ids":Qm.getValuesByName("id").toString()});
       		 }
        }
      	//盘点
        function inventory(){
			top.$.dialog({
				width:700,
				height:350,
				lock:true,
				title:"盘点",
				data: {window:window},
				content: 'url:app/equipment/equipment_inventory_detail.jsp?type=ppe'
			});
        }
        //查看
        function look(){
			top.$.dialog({
				width:875,
				height:520,
				lock:true,
				title:"扫码查询",
				data: {window:window},
				content: 'url:app/equipment/equipment_look_index.jsp?type=ppe'
			});
        }
        // 刷新Grid
        function refreshGrid() {
            Qm.refreshGrid();
        }
        $(function(){
         <sec:authorize access="hasRole('TJY2_EQ_MANAGER')">
	        importData();
	        </sec:authorize> 
	    });
	function importData() {
	  //创建上传实例
	  khFileUploader = new KHFileUploader({
	      fileSize: "10mb",//文件大小限制
	      buttonId: "importData",//上传按钮ID
	      container: "filecontainer3",//上传控件容器ID
	      title: "设备档案信息",//文件选择框提示
	      saveDB : false,
	      extName: "xls,xlsx",//文件扩展名限制
	      fileNum: 1,//限制上传文件数目
	      callback : function(files){
	                  //文件无误，执行保存
	                  saveData(files);
	      }
	  });
	}

	  //上传完成，开始保存汇编数据
	  function saveData(files){
		  $("body").mask("正在上传...");
		  $.post("equipment2Action/saveTask.do",{files:$.ligerui.toJSON(files)},function (data) {
		          $("body").unmask();
		          if (data.success) {
	                    $.ligerDialog.success("成功导入&nbsp;"+"<span style='color:green;'>"+data.total+"</span>"+"&nbsp;条设备信息!");
		              Qm.refreshGrid();
		          } else {
		              $.ligerDialog.error("保存失败！<br/>");
		          }
		     },"json");
	  }
    </script>
	</head>
	<body>
	<div class="item-tm" id="titleElement">
        <div class="l-page-title">
            <div class="l-page-title-note">提示：列表数据项
                <font color="black">“黑色”</font>代表已盘点，
                <font color="#8E8E8E">“灰色”</font>代表未盘点。
            </div>
        </div>
    </div>	
		<p id="filecontainer3" style="margin:5px">
		<a class="l-button-warp l-button" id="importData">
			<span class="l-button-main l-button-hasicon">
				<span class="l-button-text">导入设备信息</span>
				<span class="l-button-icon iconfont l-icon-excel-import"></span>
			</span>
		</a>
	</p>
	
		<qm:qm  pageid="TJY2_EQUIPMENT_LIST" singleSelect="false">
		<qm:param name="eq_category" value="01" compare="="/>
		</qm:qm>
	</body>
</html>
