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
            	{name:"eq_name", compare:"like"},
            	{name:"eq_no", compare:"like"},
            	{group:[
						{name:"eq_next_check_date", compare:">=", value:""},
						{label:"到", name:"eq_next_check_date", compare:"<=", value:"", labelAlign:"center", labelWidth:20}
					]}
            ],
            tbar:[
            	{ text:'详情', id:'detail',icon:'detail', click: detail}
            ],
             listeners: {
	                selectionChange : function(rowData,rowIndex){
                		selectionChange();
                	},
	        		rowDblClick :function(rowData,rowIndex){
	        			detail(rowData.id);
	        		},				
	                rowAttrRender : function(rowData, rowid) {
	           		    var green = 30;	// 1个月内到期
	           			var red = 0;	// 已过期	           		    
	            		var fontColor="black";            		
	            		//到期 显示红色
	            		if(rowData.warning <= red || (rowData.eq_next_check_date == "" || rowData.eq_next_check_date == undefined || rowData.eq_next_check_date == null)) {
	            			fontColor="red";
	            		}
	            		
	            		
	            		//到期30天至0天之间 显示绿色
	            		else if (red < rowData.warning <= green){
	            			fontColor="green";
	            		}
	            		return "style='color:"+fontColor+"'";
	            	}
	
	
	
	            }
        };
        
        // 行选择改变事件
		function selectionChange() {
	   		var count = Qm.getSelectedCount(); // 行选择个数
	     	if(count == 1){
	            Qm.setTbar({add: true, modify: true, del: true, detail: true});            	
	 		}else if(count > 1){
	       		Qm.setTbar({add: true, modify: false, del: true, detail: false});
	    	}else{
	    		Qm.setTbar({add: true, modify: false, del: false, detail: false});
	    	}
		}
	
        
        function detail(id){
			if($.type(id)!="string"){
				id = Qm.getValueByName("id").toString();
			}
			top.$.dialog({
				width:700,
				height:440,
				lock:true,
				title:"查看详情",
				content: 'url:app/equipment/base/equipment_basic_detail.jsp?status=detail&id=' + id,
				data:{window:window}
			});
		}
        
        //新增
        function add(){
			top.$.dialog({
				width: 700,
				height: 650,
				lock:true,
				title:"新增",
				data: {window:window},
				content: 'url:app/equipment/base/equipment_basic_detail.jsp?status=add'
			});
        }
        
        //修改
        function modify(){
			top.$.dialog({
				width: 700,
				height: 650,
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
        
        // 刷新Grid
        function refreshGrid() {
            Qm.refreshGrid();
        }
        $(function(){
	        importData();
	    });
		function importData() {
			  //创建上传实例
			  khFileUploader = new KHFileUploader({
			      fileSize: "10mb",//文件大小限制
			      buttonId: "importData",//上传按钮ID
			      container: "filecontainer3",//上传控件容器ID
			      title: "检定周期表",//文件选择框提示
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
			  $.post("equipment2Action/saveJdzq.do",{files:$.ligerui.toJSON(files)},function (data) {
			          $("body").unmask();
			          if (data.success) {
		                    $.ligerDialog.success("成功导入&nbsp;"+"<span style='color:green;'>"+data.total+"</span>"+"&nbsp;条设备检定周期信息!");
			              Qm.refreshGrid();
			          } else {
			              $.ligerDialog.error("保存失败！<br/>");
			          }
			     },"json");
		  }
    </script>
	</head>
	
	<div class="item-tm" id="titleElement">
	    <div class="l-page-title">
			<div class="l-page-title-note">提示：列表数据项
				<font color="green">“绿色”</font>代表设备30内校核到期，
				<font color="red">“红色”</font>代表设备校核已过期或无计划检定时间。			
			</div>
		</div>
	</div>
	<p id="filecontainer3" style="margin:5px">
		<a class="l-button-warp l-button" id="importData">
			<span class="l-button-main l-button-hasicon">
				<span class="l-button-text">导入检定周期表</span>
				<span class="l-button-icon iconfont l-icon-excel-import"></span>
			</span>
		</a>
	</p>
	<body>	
		<qm:qm  pageid="TJY2_EQ_JDHCYJ_LIST" singleSelect="true"></qm:qm>
	</body>
</html>
