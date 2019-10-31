<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.*"%>
<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<%
	DateFormat ds = new SimpleDateFormat("yyyyMMdd");
	String date = ds.format(new Date());
%>
<title></title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript"
	src="pub/fileupload1/fileupload/js/fileupload.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript" src="app/common/lodop/LodopFuncs.js"></script>
<script type="text/javascript">
	//所有可打印图片对象
	var records = [];
	//表格对象
	var g;
	$(function() {
		initGrid();
		$("#form1").initForm({
			toolbar : [{
				text : '打印',
				id : 'print',
				icon : 'print',
				click : print
			}, {
				text : '关闭',
				id : 'close',
				icon : 'close',
				click : function() {
					api.close();
				}
			} ],
			getSuccess : function(response) {
				if (response.success) {
					records = [];
					for (var i = 0; i < response.list.length; i++) {
						var record = response.list[i];
						if(record.uploadName.indexOf(".jpg")!=-1){
							records.push(record);
						}
					}
					//初始化列表
					g.loadData({
						Rows : records
					});
				}
			},afterParse:function(formObj){//form表单完成渲染后，回调函数
				$("input[type='checkbox'][name='uploadType']").change(function() { 
					check(this.value,this.checked);
				});
			}
		});
		$("body").append('<object style="height:1px;" id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0><embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed></object>');
	});
	
	
	/*
	*多选框选中改变事件绑定函数
	*@param elementValue 多选框的值，isChecked 是否选中
	*records 所有图片，全局变量
	*/
	function check(elementValue,isChecked){
		//如果没有图片，do nothing
		if(records.length==0){
			return;
		}
		for(var i in records){
			if(records[i].uploadType==elementValue){
				if(isChecked){
					//选中相应类型的图片
					g.select(records[i]);
				}
				else {
					//取消选中相应类型的图片
					g.unselect(records[i]);
				}
			}
		}
	}
	//初始化表格
	function initGrid() {
		g = $("#g").ligerGrid({
			columns : [ {
				display : 'empId',
				name : 'fileId',
				width : '1%',
				hide : true
			}, {
				display : 'id',
				name : 'id',
				width : '10%',
				hide : true
			}, {
				display : 'uploadId',
				name : 'uploadId',
				width : '10%',
				hide : true
			}, {
				display : '上传类型',
				name : 'uploadType',
				width : '20%',
				render : function(item) {
					var clsses = [ {
						text : '检验报告',
						id : '0'
					}, {
						text : '原始记录',
						id : '1'
					}, {
						text : '自检报告',
						id : '2'
					}, {
						text : '其他',
						id : '3'
					} ];
					for (var i = 0; i < clsses.length; i++) {
						if (item["uploadType"] == clsses[i].id) {
							return clsses[i].text;
						}
					}
				}
			}, {
				display : '文件名称',
				name : 'uploadName',
				width : '20%'
			} ],
			checkbox : true,
			rownumbers : true,
			height : "280",
			width : "98%",
			frozenRownumbers : false,
			usePager : false,
			data : {
				Rows : []
			},
			onSelectRow : function(rowdata, rowid, rowobj) {

			}
		});
	}



	function print() {
		var selecteds = g.getSelectedRows();
		$("body").mask();
		try{
			//获取lodop对象
			var LODOP = getLodop(document.getElementById('LODOP_OB'), document.getElementById('LODOP_EM'));
	        //添加打印对象
	        for(var i=0;i<selecteds.length;i++){
	        	//分页
	            if(i!=0)LODOP.NewPage();
	            LODOP.ADD_PRINT_IMAGE(0, 0,'210mm','297mm',"<img style='width:210mm;height:297mm' src='"+$("base").attr("href")+"fileupload/download.do?id="+selecteds[i].uploadId+"'/>");
	        }
	        //3纵向宽度固定，高度自适应，设置A4纸张
	        LODOP.SET_PRINT_PAGESIZE (0, 0, 0,"A4");
	        //预览
	        //LODOP.PREVIEW();
	        //打印
	        LODOP.PRINT(); 
		}catch(e){
			
		}finally{
			$("body").unmask();
		}
		

	}
	function refreshGrid() {
		$.post("uploadsAction/a/detailUpload.do?fileId=${param.id}", {},
			function(res) {
				if (res["success"]) {
					g.loadData({
						Rows : res["list"]
					});
				} else {
					$.ligerDialog.error('提示：刷新列表出错！');
				}
			}
		);
	}
	
</script>
</head>
<body>
	<form name="form1" id="form1" method="post" action="uploadsAction/a/save1.do"
		getAction="uploadsAction/a/detailUpload.do?fileId=${param.id}">
		<input type="hidden" name="fileId" id="fileId" value="${param.id}" />
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>选择报告类型打印</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">报告类型</td>
					<td class="l-t-td-right">
					
						<input type="checkbox" 
						name="uploadType" 
						id="uploadType" 
						ltype="checkboxGroup" 
						ligerui="{
							data: [{ text:'检验报告', id:'0' }, 
								{ text:'原始记录', id:'1' }, 
								{ text:'自检报告', id:'2' }, 
								{ text:'其他', id:'3' }] 
						}" />
					</td>
				</tr>
			</table>
		</fieldset>
		<fieldset class="l-fieldset" style="height:500px">
			<legend class="l-legend"> 选择要打印的文件 </legend>
			<div id="g" style="height: 400px;"></div>
		</fieldset>
	</form>
	
</body>
</html>