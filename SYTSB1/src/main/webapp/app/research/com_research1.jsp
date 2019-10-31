<%@page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.DateUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>检验科室检验计划列表</title>
 <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>

    <%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="app/js/desktop.js"></script>
<script type="text/javascript" src="app/js/jquery.min.js?v=1.4.4"></script>
    <script type="text/javascript" src="app/js/jquery-powerSwitch.js"></script>
<script type="text/javascript" src="app/common/lodop/LodopFuncs.js"></script>
<script type="text/javascript">
var flag=false;
var unit_name  = "全所";
var device_sort="";
var date="";
	$(function() {
		
		var toDayDate="<%=DateUtil.getDate(new Date())%>";
    	/* $("#form").initForm({
			toolbar : [
			{icon: "search", id: "search", text: "点击查询", disabled: false, click: function () {
				save();
			}}  ,'-',{
				text : '打印',
				icon : 'print',
				click :PreviewMytable},
				{
					text : '导出excel文件',
					icon : 'table-excel',
					click :SaveAsFile}],
			success : function(response) {
			},toolbarPosition :'top'
		});  */
		$("#search").click(function(){
			var com_name = $("#com_name").val();
			if(com_name==""){
				$.ligerDialog.warn('请填写单位名称！');
				return;
			}
			$.post("enterSearchAction/searchAllByComname.do",
					{com_name:com_name
				},function(res){
				if(res.success){
					var list = res.comList;
					//alert(list)
					$("#tab2").html("");
					
					for (var i = 0; i < list.length; i++) {
						$("#tab2").append("<tr><td>"+list[i].com_name+" 特种设备数量"+list[i].device_count
								+"</td></tr>")
						
					}
					}else{
						$.ligerDialog.error('查询失败！');
					}
				
				}
				)
		})
	})
	
	
	
	
	function changeArea(val,text){
		if(val==""){
			unit_name = "全所";
		} else {
			unit_name = text;
		}
	}
	function changeDevice(val,text){
		device_sort = text;
	}
	function PreviewMytable(){
		if(!flag){
			$.ligerDialog.alert('请先查询结果！','提示');
			return;
		}
		var start_date = $("#start_date").val();
		var end_date = $("#end_date").val();
		LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));  
		LODOP.PRINT_INIT("检验科室收入汇总表"+unit_name+" "+start_date+"-"+end_date);
		LODOP.SET_PRINT_PAGESIZE(1,0,0,"A4");	
		LODOP.ADD_PRINT_TEXT("5%","38%",300,30,"检验科室收入汇总表");
		LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
        LODOP.SET_PRINT_STYLEA(1, "FontSize", 16);
        LODOP.SET_PRINT_STYLEA(1, "Bold", 1);
        var strBodyStyle= "<style>table { border: 1 solid #000000;border-collapse:collapse }</style>"  ; 

       var strFormHtml=strBodyStyle+ "<body>"  +document.getElementById( "div1" ).innerHTML+

       "</body>" ;  

		LODOP.ADD_PRINT_TABLE("10%","7%","90%","88%",strFormHtml);
	    	LODOP.ADD_PRINT_TEXT("8%","16%",150,30,"部门名称："+unit_name); 
	    	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	    	LODOP.SET_PRINT_STYLEA(1, "FontSize", 13);
	        LODOP.ADD_PRINT_TEXT("8%","46%",300,30,"结算月份："+date);
	        LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	        LODOP.SET_PRINT_STYLEA(1, "FontSize", 13);
		LODOP.SET_PREVIEW_WINDOW(0,0,0,1000,800,"");
		LODOP.PREVIEW();
		
		
		/* LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));  
		LODOP.PRINT_INIT("打印控件功能演示_Lodop功能_预览打印表格");
		LODOP.ADD_PRINT_TABLE(100,20,500,80,document.getElementById("div1").innerHTML);
		//LODOP.SET_PRINT_STYLEA(0,"TableHeightScope",1);		
		LODOP.PREVIEW(); */
	};
	

	
	 function SaveAsFile(){
		if(!flag){
			$.ligerDialog.alert('请先查询结果！','提示');
			return;
		}


		var start_date = $("#start_date").val();
		var end_date = $("#end_date").val();
		
		LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));  
		LODOP.PRINT_INIT("");
		LODOP.ADD_PRINT_TABLE(100,20,500,80,document.getElementById("div1").innerHTML);
//		LODOP.SET_SAVE_MODE("QUICK_SAVE",true);//快速生成（无表格样式,数据量较大时或许用到）
		LODOP.SAVE_TO_FILE("检验科室收入汇总表-"+unit_name+" "+start_date+"-"+end_date+".xls");
	};	
</script>
</head>
<body>
<form id="form">
<br/>
<div align="center">
	<table cellpadding="3" cellspacing="0">
		<tr>
			<td  style="width: 80px;" align="center">单位名称：</td>
			<td style="width: 200px;" align="left">
				<input type="text" id="com_name" style="height: 25px;font-size: 20px;" name="com_name" ltype="text" validate="{required:true}"
				size="40" />
			</td>
			<td style="width: 100px;"  align="center">
			<a id="search" class="l-button-warp l-button" ligeruiid="Button1012">
					<span class="l-button-main l-button-hasicon"> <span
						class="l-button-text">查询</span> <span
						class="l-button-icon l-icon-search"></span>
				</span>
				</a></td>
			</tr>
	</table>
	</div>
	<div class="item-tm">
		<div id="toolbar1"></div>
	</div>
	<fieldset class="l-fieldset">
		<legend class="l-legend">
			<div>新闻</div>
		</legend>
	<div align="center" style="width:80%;" id="div1">
		<br/>
		查询到的新闻信息
		<br/>
	</div>
	</fieldset>
	
	<fieldset class="l-fieldset">
		<legend class="l-legend">
			<div>单位信息</div>
		</legend>
		<div align="center" style="width:80%;" id="div2">
		<br/>
			<table border="1" cellspacing="3" cellpadding="3" style="width: 90%;" id="tab2">
			</table>
		<br/>
	</div>
	</fieldset>
</form>
</body>
</html>