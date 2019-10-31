<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>

<head >
    <title></title>
    <%@include file="/k/kui-base-form.jsp" %>
	<script type="text/javascript" src="pub/bpm/js/util.js"></script>
    <script type="text/javascript">
    var ppeGrid;
    var columns=[];
    $(function(){
    	defineColumns();
    	initGrid();
    	$("#formObj").initForm({
			showToolbar: true,
            toolbarPosition: "bottom",
            toolbar: tbar=[{text: "关闭", icon: "cancel", click: function(){api.close();api.data.window.refreshGrid();}}]
    	});
    	
    	$("#barcode").bind("input propertychange", function () { 
        	var bar_code = $("#barcode").val();
        	if(bar_code.length == 13){
        		inventory(bar_code);
        	}
        });
    	
    	/* $("#ppediv").initFormList({
            columns:[
                 {display:'固定资产主键', name:'id', width:'1%', hide:true},
                 {display:'卡片编号', name:'cardNo', width:'15%'},
                 {display:'自编号', name:'selfNo',width:'15%'},
                 {display:'资产名称',name:'assetName',width:'20%'},
                 {display:'规格型号',name:'spaciModel',width:'15%'},
                 {display:'序列号',name:'sn',width:'20%'},
                 {display:'放置地点',name:'placeLocation',width:'30%'},
                 {display:'使用部门',name:'userDepartment',width:'20%'},
                 {display:'使用人',name:'userName',width:'20%'},
            	]
        }); */
    })
    
    
    <!-- 页面加载完，条形码文本输入框框自动获取焦点 -->
    setTimeout( function(){
  			try{
   				 document.getElementById('barcode').focus();
 			 } catch(e){}
				}, 500);
  	//定义列表
	function defineColumns(){
  		/* alert("定义列表"); */
		columns.push(
				{display:'固定资产主键', name:'id', hide:true},
                {display:'卡片编号', name:'cardNo', width:'15%'},
                {display:'自编号', name:'selfNo',width:'15%'},
                {display:'资产名称',name:'assetName',width:'15%'},
                {display:'规格型号',name:'spaciModel',width:'15%'},
                {display:'序列号',name:'sn',width:'15%'},
                {display:'放置地点',name:'placeLocation',width:'20%'},
                {display:'使用部门',name:'userDepartment',width:'20%'},
                {display:'使用人',name:'userName',width:'20%'}
		);
	}
    //初始化Grid
    function initGrid() {
    	/* alert("初始化Grid"); */
        ppeGrid = $("#ppediv").ligerGrid({
	    	columns: columns,
	    	enabledEdit: false,
	    	clickToEdit: false,
	    	rownumbers : true,
		    width:"99.6%",
		    height:"86%",
		    frozenRownumbers: false,
		    usePager: false,
		    isScroll: true,
		    data: {Rows: []}
    	});
	}
    //盘点方法
    function inventory(barcode){
    	//当扫描同一个二维码则覆盖重复的数据
    	var devRow = ppeGrid.rows;
	    var isexist=false;
    	if(barcode.length==13){
    		$.ajax({
    	    	url: "equipPpeAction/inventory.do?barcode="+barcode,
    	        type: "POST",
    	        success: function (resp) {
    	        	if(resp.message){
    	        		$.ligerDialog.alert('提示：'+resp.message);
    	        	}else{
    	        		var equipPpe = resp.equipPpe;//接收资产对象
    	        		for(var i in devRow){
    	        			if(devRow[i].id == equipPpe.id){
   	        	    			isexist = true;
   	        	    			break;
   	        	    		}else{
   	        	    			isexist = false;
   	        	    		}
    	        		}
    	        		if(!isexist){
    	        			//将盘点了的资产列在盘点页面
        	        		var equipPpe = resp.equipPpe;
        	        		var equipPpeRow = {id : equipPpe.id,
        	        				cardNo: equipPpe.cardNo,
        	        				selfNo : equipPpe.selfNo,
        	        				assetName: equipPpe.assetName,
        	        				spaciModel : equipPpe.spaciModel,
        	        				sn: equipPpe.sn,
        	        				placeLocation: equipPpe.placeLocation,
        	        				userDepartment: equipPpe.userDepartment,
        	        				userName: equipPpe.userName
        	        				};
    						ppeGrid.addRow(equipPpeRow);
	        	    	}
    	        	}
    	        },
    	        error: function (data0,stats) {
    	            $.ligerDialog.warn('提示：盘点失败！');
    	        }
    	    }); 
    	}else{
    		$.ligerDialog.err('提示：此二维码不正确！');
    	}
    	$("#barcode").val("").focus(); 
    }
    </script>
</head>
<body>
	<div id = "formdiv">
		<form id = "formObj">
		<h1 style="padding:5mm 0 2mm 0;font-family:微软雅黑;font-size:6mm;text-align:center;">固&nbsp;定&nbsp;资&nbsp;产&nbsp;盘&nbsp;点</h1></br>
		<table cellpadding="1" cellspacing="0" class="l-detail-table">
			<tr>
			    <td class="l-t-td-left">条码框</td>
	    		<td class="l-t-td-right"><input name="barcode" id="barcode" type="text" ltype='text' validate="{maxlength:13}"/></td>
	    		<td class="l-t-td-left"></td>
			</tr>
		</table>
		<div id="ppediv" style="width:100%"></div>
		</form>
	</div>
</body>
</html>