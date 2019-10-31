<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>固定资产列表</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript" src="pub/bpm/js/util.js"></script>
		<script type="text/javascript" src="app/common/js/render.js"></script>
		<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
		<!-- 生成条形码JS导入 -->
		<script type="text/javascript" src="app/common/lodop/LodopFuncs.js"></script>
		<script type="text/javascript">
		var arrayObj;
        var qmUserConfig = {
        	sp_defaults:{columnWidth:0.25,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
            sp_fields:[
				{name:"card_no", compare:"like"},
				{name:"have_cardno", compare:"="},
				{name:"self_no", compare:"like"},
            	{name:"asset_name", compare:"like"},
            	{name:"spaci_model", compare:"like"},
            	{name:"sn", compare:"like"},
            	{name:"asset_value", compare:"like"},
				{name:"place_location", compare:"like"},
				{name:"user_department", compare:"like"},
				{name:"user_name", compare:"like"},
				{name:"owner", compare:"like"},
				{name:"asset_status", compare:"like"},
				{name:"inventory", compare:"like"},
				{group:[
						{name:"inventory_date", compare:">=", value:""},
						{label:"到", name:"inventory_date", compare:"<=", value:"", labelAlign:"center", labelWidth:20}
					]},
				{name:"loan_status", compare:"="},
				{group:[
					{name:"instock_date", compare:">=", value:""},
					{label:"到", name:"instock_date", compare:"<=", value:"", labelAlign:"center", labelWidth:20}
				]},
				{name:"remark", compare:"like"},
				{name:"isExpired", compare:"="}
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
                { text:'报废', id:'scrap',icon:'recycle', click:scrap},
                "-",
                { text:'打印标签', id:'print',icon:'print', click:print},
                "-",
                { text:'批量打印标签', id:'prints',icon:'print', click:prints},
                "-",
                { text:'盘点', id:'inventory',icon:'ok', click:inventory},
                "-",
                { text:'手动盘点', id:'inventoryByHand',icon:'ok', click:inventoryByHand},
                "-",
                { text:'批量修改', id:'bathModify',icon:'modify', click:bathModify}
            	</sec:authorize> 
            	<sec:authorize access="hasRole('sys_administrate')">
            	,"-",
                { text:'更新二维码', id:'updateErrBarcode',icon:'f5', click: updateErrBarcode}
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
    	            if(rowData.inventory=='已盘点'){
    	            	if(rowData.isExpired=='已过期'){
    	            		fontColor="red";
    	            	}else if(rowData.isExpired=='即将过期'){
    	            		fontColor="orange";
    	            	}
    	            }else{
    	           		fontColor="#8E8E8E";
    	            }
    	            return "style='color:"+fontColor+"'";
    	        }
            }
        };
        
        // 行选择改变事件
		function selectionChange() {
	   		var count = Qm.getSelectedCount(); //行选择个数
	   		if(count>=1){
	   			var assetName = Qm.getValuesByName("asset_name").toString();//名称
	    		var owner = Qm.getValuesByName("owner").toString();//归属
	    		var selfNo = Qm.getValuesByName("self_no").toString();//自编号/编号
	    		var spaciModel = Qm.getValuesByName("spaci_model").toString();//规格型号
	    		var sn = Qm.getValuesByName("sn").toString();//序列号/出厂编号
	    		var productDate = Qm.getValuesByName("product_date").toString();//出厂日期
	    		var userDepartment = Qm.getValuesByName("user_department").toString();//使用部门
	    		var BarCodeValue = Qm.getValuesByName("barcode").toString();//条码
	    		arrayObj = [assetName,owner,selfNo,spaciModel,sn,productDate,userDepartment,BarCodeValue];
	   		}
			Qm.setTbar({
				detail: count==1,
				modify: count==1,
				del: count>0,
				scrap: count>0,
				print:count==1,
				prints:count>0,
				bathModify:count>0,
				inventoryByHand:count>0
			});
		}
        
		function detail(){
			var id = Qm.getValueByName("id").toString();
			var owner = Qm.getValueByName("owner").toString();
			if(owner == "鼎盛公司"){
				owner = "100047";
        	}else if(owner == "司法鉴定中心"){
        		owner = "100044";
        	}else if(owner == "四川省特种设备检验检测协会"){
        		owner = "100042";
        	}else if(owner == "四川省特种设备检验研究院"){
        		owner = "100000";
        	}else{
        		owner = "UNKNOWN";
        	}
			top.$.dialog({
				width:700,
				height:470,
				lock:true,
				title:"详情",
				content: 'url:app/equipment/ppe/ppe_basic_detail.jsp?pageStatus=detail&id='+id+'&owner='+owner,
				data:{window:window}
			});
		}
        
        //新增
        function add(){
			top.$.dialog({
				width:700,
				height:470,
				lock:true,
				title:"新增",
				data: {window:window},
				content: 'url:app/equipment/ppe/ppe_basic_detail.jsp?pageStatus=add'
			});
        }
        
        //修改
        function modify(){
        	var id = Qm.getValueByName("id").toString();
			top.$.dialog({
				width:700,
				height:470,
				lock:true,
				title:"修改",
				data: {window:window},
				content: 'url:app/equipment/ppe/ppe_basic_detail.jsp?pageStatus=modify&id='+id
			});
        } 
        
        //删除
        function del(){
            $.del("确定要删除？删除后无法恢复！","equipPpeAction/delete.do",{"ids":Qm.getValuesByName("id").toString()});
        }
      	//报废
        function scrap(){
	        $.ligerDialog.confirm('确定报废？', function (yes){
	        	if(!yes){return false;}
	            top.$.ajax({
	                 url: "equipPpeAction/scrap.do?ids=" + Qm.getValuesByName("id").toString(),
	                 type: "post",
	                 async: false,
	                 success:function (data) {
	                    if(data.success){
	                    	top.$.notice("操作成功！");
							Qm.refreshGrid();
	                    }else{
	                    	top.$.notice("操作失败！");
	                    }
	                 },
	                 error:function () {
	                     //$.ligerDialog.warn("提交失败！");
	                	 $.ligerDialog.error("出错了！请重试！！！");
	                 }
	             });
	        });
		}
        //打印标签
        function print(){
        	var assetName = Qm.getValuesByName("asset_name").toString();//名称
    		var owner = Qm.getValuesByName("owner").toString();//归属
    		var selfNo = Qm.getValuesByName("self_no").toString();//自编号/编号
    		var spaciModel = Qm.getValuesByName("spaci_model").toString();//规格型号
    		var sn = Qm.getValuesByName("sn").toString();//序列号/出厂编号
    		var productDate = Qm.getValuesByName("product_date").toString();//出厂日期
    		var userDepartment = Qm.getValuesByName("user_department").toString();//使用部门
    		var BarCodeValue = Qm.getValuesByName("barcode").toString();//条码
    		//var BarCodeValue = $("#barcode").text().trim();
        	var LODOP = getLodop(document.getElementById('LODOP_OB'), document.getElementById('LODOP_EM'));
        	//条形码
        	if(owner == "鼎盛公司"){
        		LODOP.ADD_PRINT_TEXT(0,10,300,10,"鼎盛公司办公设备标签");
        	}else if(owner == "司法鉴定中心"){
        		LODOP.ADD_PRINT_TEXT(0,10,300,10,"司法鉴定中心办公设备标签");
        	}else if(owner == "四川省特种设备检验检测协会"){
        		LODOP.ADD_PRINT_TEXT(0,10,300,10,"检验检测协会办公设备标签");
        	}else if(owner == "四川省特种设备检验研究院"){
        		LODOP.ADD_PRINT_TEXT(0,10,300,10,"川特院办公设备标签");
        	}else{
        		$.ligerDialog.alert("请确认自编号为<font color='red'>"+selfNo+"</font>,名称为<font color='red'>"+assetName+"</font>的归属！");
        		return
        	}
    	    LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
    		LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
    		LODOP.SET_PRINT_STYLEA(0,"Bold",50);
    		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true);
    		
    	    LODOP.ADD_PRINT_TEXT(22,10,80,10,"资产名称：");
    	    LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
    		LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
    		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
    		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true);
    		LODOP.ADD_PRINT_TEXT(22,70,125,10,assetName);
    	    LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
    		LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
    		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
    		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true);

    		LODOP.ADD_PRINT_TEXT(35,10,80,10,"资产编号：");
    	    LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
    		LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
    		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
    		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true);
    		LODOP.ADD_PRINT_TEXT(35,70,125,10,selfNo);
    	    LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
    		LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
    		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
    		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true);
    		
    	    LODOP.ADD_PRINT_TEXT(48,10,80,10,"规格型号：");
    	    LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
    		LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
    		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
    		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true);
    		LODOP.ADD_PRINT_TEXT(48,70,125,10,spaciModel);
    	    LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
    		LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
    		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
    		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true);
    		
    	    LODOP.ADD_PRINT_TEXT(62.5,10,80,10,"出厂编号：");
    		LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
    		LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
    		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
    		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true);
    		if(sn.length>16){
    			LODOP.ADD_PRINT_TEXT(61,70,125,15,sn);
        		LODOP.SET_PRINT_STYLEA(0,"LineSpacing",-4);
        		LODOP.SET_PRINT_STYLEA(0,"FontSize",5);
    		}else{
    			LODOP.ADD_PRINT_TEXT(62.5,70,125,15,sn);
        		LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
    		}
    		LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
    		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
    		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true);
    		
    		LODOP.ADD_PRINT_TEXT(77,10,80,10,"出厂日期：");
    		LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
    		LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
    		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
    		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true); 
    		LODOP.ADD_PRINT_TEXT(77,70,125,10,productDate);
    		LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
    		LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
    		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
    		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true); 
    		
    		LODOP.ADD_PRINT_TEXT(90,10,80,10,"使用部门：");
    		LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
    		LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
    		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
    		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true); 
    		LODOP.ADD_PRINT_TEXT(90,70,125,10,userDepartment);
    		LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
    		LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
    		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
    		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true); 
    		
    		
    		/* ADD_PRINT_BARCODE(Top,Left,Width,Height,BarCodeType,BarCodeValue); */
            /* LODOP.ADD_PRINT_BARCODE(54,50,300,45,"Code93",BarCodeValue); //打印包含英文字母的条码*/
            /* LODOP.ADD_PRINT_BARCODE(0,165,45,170,"EAN13",BarCodeValue);//打印13位数的条码 
            LODOP.SET_PRINT_STYLEA(0,"Angle",90);  */
            LODOP.ADD_PRINT_BARCODE(13,181,114,114,"QRCode",BarCodeValue);
            //LODOP.PREVIEW();
            LODOP.PRINT();
        }
      	//批量打印标签
        function prints(){
      		if(arrayObj[0].split(",").length==0){
      			 $.ligerDialog.error("请选择要打印标签的固定资产!");
      		}else{
      			for(i=0;i<arrayObj[0].split(",").length;i++){
      				var assetName = arrayObj[0].split(",")[i];//名称
      	    		var owner = arrayObj[1].split(",")[i];//归属
      	    		var selfNo = arrayObj[2].split(",")[i];//自编号/编号
      	    		var spaciModel = arrayObj[3].split(",")[i];//规格型号
      	    		var sn = arrayObj[4].split(",")[i];//序列号/出厂编号
      	    		var productDate = arrayObj[5].split(",")[i];//出厂日期
      	    		var userDepartment = arrayObj[6].split(",")[i];//使用部门
      	    		var BarCodeValue = arrayObj[7].split(",")[i];//条码
      	    		var LODOP = getLodop(document.getElementById('LODOP_OB'), document.getElementById('LODOP_EM'));
      	        	//条形码
      	        	if(owner == "鼎盛公司"){
      	        		LODOP.ADD_PRINT_TEXT(0,10,300,10,"鼎盛公司办公设备标签");
      	        	}else if(owner == "司法鉴定中心"){
      	        		LODOP.ADD_PRINT_TEXT(0,10,300,10,"司法鉴定中心办公设备标签");
      	        	}else if(owner == "四川省特种设备检验检测协会"){
      	        		LODOP.ADD_PRINT_TEXT(0,10,300,10,"检验检测协会办公设备标签");
      	        	}else if(owner == "四川省特种设备检验研究院"){
      	        		LODOP.ADD_PRINT_TEXT(0,10,300,10,"川特院办公设备标签");
      	        	}else{
      	        		$.ligerDialog.alert("请确认自编号为<font color='red'>"+selfNo+"</font>,名称为<font color='red'>"+assetName+"</font>的归属！");
      	        		return
      	        	}
      	    	    LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
      	    		LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
      	    		LODOP.SET_PRINT_STYLEA(0,"Bold",50);
      	    		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true);
      	    		
      	    	    LODOP.ADD_PRINT_TEXT(22,10,80,10,"资产名称：");
      	    	    LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
      	    		LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
      	    		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
      	    		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true);
      	    		LODOP.ADD_PRINT_TEXT(22,70,125,10,assetName);
      	    	    LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
      	    		LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
      	    		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
      	    		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true);

      	    		LODOP.ADD_PRINT_TEXT(35,10,80,10,"资产编号：");
      	    	    LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
      	    		LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
      	    		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
      	    		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true);
      	    		LODOP.ADD_PRINT_TEXT(35,70,125,10,selfNo);
      	    	    LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
      	    		LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
      	    		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
      	    		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true);
      	    		
      	    	    LODOP.ADD_PRINT_TEXT(48,10,80,10,"规格型号：");
      	    	    LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
      	    		LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
      	    		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
      	    		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true);
      	    		LODOP.ADD_PRINT_TEXT(48,70,125,10,spaciModel);
      	    	    LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
      	    		LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
      	    		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
      	    		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true);
      	    		
      	    	    LODOP.ADD_PRINT_TEXT(62.5,10,80,10,"出厂编号：");
      	    		LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
      	    		LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
      	    		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
      	    		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true);
      	    		if(sn.length>16){
      	    			LODOP.ADD_PRINT_TEXT(61,70,125,15,sn);
      	        		LODOP.SET_PRINT_STYLEA(0,"LineSpacing",-4);
      	        		LODOP.SET_PRINT_STYLEA(0,"FontSize",5);
      	    		}else{
      	    			LODOP.ADD_PRINT_TEXT(62.5,70,125,15,sn);
      	        		LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
      	    		}
      	    		LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
      	    		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
      	    		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true);
      	    		
      	    		LODOP.ADD_PRINT_TEXT(77,10,80,10,"出厂日期：");
      	    		LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
      	    		LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
      	    		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
      	    		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true); 
      	    		LODOP.ADD_PRINT_TEXT(77,70,125,10,productDate);
      	    		LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
      	    		LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
      	    		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
      	    		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true); 
      	    		
      	    		LODOP.ADD_PRINT_TEXT(90,10,80,10,"使用部门：");
      	    		LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
      	    		LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
      	    		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
      	    		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true); 
      	    		LODOP.ADD_PRINT_TEXT(90,70,125,10,userDepartment);
      	    		LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
      	    		LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
      	    		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
      	    		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",true); 
      	    		
      	            LODOP.ADD_PRINT_BARCODE(13,181,114,114,"QRCode",BarCodeValue);
      	            //LODOP.PREVIEW();
      	            LODOP.PRINT();
      			}
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
				content: 'url:app/equipment/ppe_inventory_detail.jsp?type=ppe'
			});
        }
      	//报废
        function inventoryByHand(){
	        $.ligerDialog.confirm('确定标记选中资产为已盘点？', function (yes){
	        	if(!yes){return false;}
	            top.$.ajax({
	                 url: "equipPpeAction/inventoryByHand.do?ids=" + Qm.getValuesByName("id").toString(),
	                 type: "post",
	                 async: false,
	                 success:function (data) {
	                    if(data.success){
	                    	top.$.notice("操作成功！");
							Qm.refreshGrid();
	                    }else{
	                    	top.$.notice("操作失败！");
	                    }
	                 },
	                 error:function () {
	                     //$.ligerDialog.warn("提交失败！");
	                	 $.ligerDialog.error("出错了！请重试！！！");
	                 }
	             });
	        });
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
		      title: "固定资产",//文件选择框提示
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
		  $.post("equipPpeAction/saveTask.do",{files:$.ligerui.toJSON(files)},function (data) {
		          $("body").unmask();
		          if (data.success) {
	                    $.ligerDialog.success("成功导入&nbsp;"+"<span style='color:green;'>"+data.total+"</span>"+"&nbsp;条固定资产!");
		              Qm.refreshGrid();
		          } else {
		        	  $.ligerDialog.error("保存失败！<br/>");
		          }
		     },"json");
	  }
	  //更新二维码
	  function updateErrBarcode(){
	        $.ligerDialog.confirm('是否要更新二维码？', function (yes){
	        	if(!yes){return false;}
	            top.$.ajax({
	                 url: "equipPpeAction/updateErrBarcode.do",
	                 type: "GET",
	                 dataType:'json',
	                 async: false,
	                 success:function (data) {
	                    if(data){
	                       top.$.notice(data.msg,3);
	                       Qm.refreshGrid();//刷新
	                    }else{
	                        $.ligerDialog.warn(data.msg);
	                    }
	                 },
	                 error:function () {
	                     //$.ligerDialog.warn("提交失败！");
	                	 $.ligerDialog.error("出错了！请重试！！！");
	                 }
	             });
	        });
	}
	function bathModify(){
		top.$.dialog({
			width : 700,
			height : 360,
			lock : true,
			title : "批量修改",
			content : 'url:app/equipment/ppe/ppe_change.jsp?pageStatus=modify&id=' + Qm.getValuesByName("id"),
			data : {
				"window" : window
			}
		});	
	}
    </script>
	</head>
	<body>
	<div class="item-tm" id="titleElement">
        <div class="l-page-title">
            <div class="l-page-title-note">提示：列表数据项
                <font color="black">“黑色”</font>代表已盘点，
                <font color="#8E8E8E">“灰色”</font>代表未盘点，
                <font color="orange">“橙色”</font>代表即将超过使用年限，
                <font color="red">“红色”</font>代表已超过使用年限。
            </div>
        </div>
    </div>
	<p id="filecontainer3" style="margin:5px">
		<a class="l-button-warp l-button" id="importData">
			<span class="l-button-main l-button-hasicon">
				<span class="l-button-text">导入固定资产</span>
				<span class="l-button-icon iconfont l-icon-excel-import"></span>
			</span>
		</a>
	</p>
		<qm:qm  pageid="TJY2_PPE_LIST" singleSelect="false">
		</qm:qm>
		<script type="text/javascript">
		Qm.config.columnsInfo.loan_status.binddata = [
			{id: '0', text: '未借用'},
			{id: '1', text: '已借用'}
		];
		Qm.config.columnsInfo.isExpired.binddata = [
			{id: '2', text: '未过期'},
			{id: '1', text: '即将过期'},
			{id: '0', text: '已过期'}
		];
	</script>
	</body>
</html>
