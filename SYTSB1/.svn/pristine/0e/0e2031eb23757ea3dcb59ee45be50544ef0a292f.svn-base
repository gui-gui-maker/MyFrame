<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>

<head >
    <title></title>
    <%@include file="/k/kui-base-form.jsp" %>
	<script type="text/javascript" src="pub/bpm/js/util.js"></script>
    <script type="text/javascript">
    var equipmentGrid;
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
				{display:'设备主键', name:'id', hide:true},
                {display:'卡片编号', name:'card_no', width:'15%'},
                {display:'档案分类号', name:'eq_archive_class_id',width:'15%'},
                {display:'设备编号', name:'eq_no',width:'15%'},
                {display:'设备名称',name:'eq_name',width:'15%'},
                {display:'规格型号',name:'eq_model',width:'15%'},
                {display:'出厂编号',name:'eq_sn',width:'15%'},
                {display:'购进日期',name:'eq_buy_date',width:'15%',type: 'date', format: 'yyyy-MM-dd',},
                {display:'管理（使用）人员',name:'eq_user',width:'20%'},
                {display:'管理（使用）部门',name:'eq_use_department',width:'20%'}
		);
	}
    //初始化Grid
    function initGrid() {
    	/* alert("初始化Grid"); */
        equipmentGrid = $("#equipmentdiv").ligerGrid({
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
    	var devRow = equipmentGrid.rows;
	    var isexist=false;
    	if(barcode.length==13){
    		$.ajax({
    	    	url: "equipment2Action/inventory.do?barcode="+barcode,
    	        type: "POST",
    	        success: function (resp) {
    	        	if(resp.message){
    	        		$.ligerDialog.alert('提示：'+resp.message);
    	        	}else{
    	        		var equipment = resp.equipment;//接收设备对象
    	        		for(var i in devRow){
    	        			if(devRow[i].id == equipment.id){
   	        	    			isexist = true;
   	        	    			break;
   	        	    		}else{
   	        	    			isexist = false;
   	        	    		}
    	        		}
    	        		if(!isexist){
    	        			//将盘点了的设备列在盘点页面
        	        		var equipmentRow = {id : equipment.id,
        	        				card_no: equipment.card_no,
        	        				eq_archive_class_id : equipment.eq_archive_class_id,
        	        				eq_no: equipment.eq_no,
        	        				eq_name : equipment.eq_name,
        	        				eq_model: equipment.eq_model,
        	        				eq_sn: equipment.eq_sn,
        	        				eq_buy_date: equipment.eq_buy_date,
        	        				eq_user: equipment.eq_user,
        	        				eq_use_department: equipment.eq_use_department,
        	        				};
    						equipmentGrid.addRow(equipmentRow);
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
	<div id = "div1">
		<form id = "formObj">
		<h1 style="padding:5mm 0 2mm 0;font-family:微软雅黑;font-size:6mm;text-align:center;">设&nbsp;备&nbsp;盘&nbsp;点</h1></br>
		<table cellpadding="1" cellspacing="0" class="l-detail-table">
			<tr>
			    <td class="l-t-td-left">条码框</td>
	    		<td class="l-t-td-right"><input name="barcode" id="barcode" type="text" ltype='text' validate="{maxlength:13}"/></td>
			    <td class="l-t-td-left"></td>
			</tr>
		</table>
		<div id="equipmentdiv" style="width:100%"></div>
		</form>
	</div>
</body>
</html>