<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title></title>
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@include file="/k/kui-base-form.jsp"%>
<%@ taglib uri="http://khnt.com/tags/chart" prefix="chart" %>
<script src="pub/chart/js/kh_fc.js"></script>
<script type="text/javascript">
	var ps;
	$(function() {
		$("#form1").initForm({
			//toolbarPosition : 'bottom'
		});
		$("#form2").initFormList({
			id:'pxchart',
			actionParam : {
				"chart.id" : $("#form1>[name=id]")
			}, 
			onSelectRow:function (rowdata, rowindex) {
                $("#form2").setValues(rowdata);
            },
            delSuccess:function (data) {
            	api.data.window.Qm.refreshGrid();
	        },
			columns : [
			{
				display : '主键',
				name : 'id',
				width : '5%',
				hide : true
			}, {
				display : '参数类型',
				name : 'paratype',
				width : '15%',
				align: 'right',
				isSort:true
			}, {
				display : '参数名称',
				name : 'paraname',
				width : '15%',
				align: 'left',
				isSort:true
			}, {
				display : '参数值',
				width : '65%',
				name : 'paravalue',
				align: 'left',
				isSort:true
			}]
		});
		$(".navtab").ligerTab({
			onAfterSelectTabItem : function(tabid) {
				if (tabid == 'tabitem3') {
					var a='';
					if("${param.status}"=='detail'){
						a=$.trim($("#chartid").text());
					}else{
						a=$.trim($("#chartid").val());
					}
					RenderChart(a,"","tbyl","ylChartId"+Math.random(100));
				}
			}
		});
		
		var ct =<u:dict code="chartsType"/>;
		var ctStr = "<table width='96%' border='0' cellspacing='2' cellpadding='0' style='margin-left:20px'><tr>";
		 $.each(ct,function(i,item){
			 ctStr = ctStr + "<td align='center'><label for='"+item.id+"'><img src='pub/chart/FusionCharts/image/"+item.id+".png' width='150' height='100' /></label><br/>";
			 ctStr = ctStr + "<input type='radio' name='radio_ct' id='"+item.id+"' value='"+item.id+"' onclick='setCType(this)'/>";
			 ctStr = ctStr + "<label for='"+item.id+"'>"+item.text+"</label></td>";
			 if ((i+1)%5==0) 
			 {
				 ctStr = ctStr + "</tr><tr>";
			 }
			// alert(i+":"+item.id+"-"+item.text);
		 });
		 ctStr = ctStr + "</tr></table>";
		 $("#dType").html(ctStr);
	});
	
	function setCType(ct)
	{
		$("#charttype").val(ct.value);
	}
</script>
</head>
<body>
	<div class="navtab">
		<div title="图表" lselected="true">
			<form id="form1" action="chart/chart/save.do"
				getAction="chart/chart/detail.do?id=${param.id}" name="form1">
				<input  id="chart_id" name="id" type="hidden">
				<input  name="modtime" type="hidden">
                <input id="charttype" name="charttype" type="hidden" />
					<table border="1" cellpadding="3" cellspacing="0"
						class="l-detail-table">
						<tr>
							<td class="l-t-td-left">图表ID：</td>
							<td class="l-t-td-right"><input  id="chartid" name="chartid" type="text"
								ltype='text' validate="{required:true,maxlength:100}"/></td>
						</tr>
						<tr>
							<td class="l-t-td-left">SQL：</td>
							<td class="l-t-td-right" colspan=""><textarea name="sqlstr" cols="" rows="6"
							class="l-textarea" validate="{maxlength:4000}" ></textarea></td>
						</tr>
						<tr>
							<td class="l-t-td-left">图表类型：</td>
							<td id="dType" class="l-t-td-right"></td>
						</tr>
					</table>
			</form>
		</div>
			
		<div title="图表参数">
			<form id="form2" action="chart/chartParameter/save.do"
				getAction="chart/chartParameter/getlist.do?id=${param.id}" delAction="chart/chartParameter/delete.do">
				<input type="hidden" id="id" name="id">
				<input id="chartParameterid" type="hidden" name="chart.id" />
					<table border="1" cellpadding="3" cellspacing="0"
						class="l-detail-table">
						<tr>
							<td class="l-t-td-left">参数类型：</td>
							<td class="l-t-td-right">
								<u:combo name="paratype" code="chartParaType"></u:combo></td>
						</tr>
						<tr>
							<td class="l-t-td-left">参数名称：</td>
							<td class="l-t-td-right"><input name="paraname" type="text"
								ltype='text' validate="{maxlength:100,required:true}"/></td>
						</tr>
						
						<tr>
							<td class="l-t-td-left">参数值：</td>
							<td class="l-t-td-right"><input id="paravalue" name="paravalue" type="text"
								ltype='text' validate="{maxlength:4000}" /></td>
						</tr>
					</table>
			</form>
		</div>
		<div title="图表预览">
            <div id="tbyl" style="margin:10px; width:98%; height:96%; border:hidden;">
            	<font color="#999999">正在加载图形数据,请稍候...</font>
			</div>
	  </div>
	</div>
</body>
</html>