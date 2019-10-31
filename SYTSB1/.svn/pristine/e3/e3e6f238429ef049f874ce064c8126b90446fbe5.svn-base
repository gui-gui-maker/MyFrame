<%@ page import="com.khnt.utils.DateUtil" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://khnt.com/tags/qm" prefix="q" %>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
   <%@include file="/k/kui-base-list.jsp"%>
    <script test="text/javascript">
	var toDayDate = '<%=DateUtil.getDateTime("MM",new Date())%>';
	$(function(){//jQuery页面载入事件
		$("#formObj").ligerForm();
		$("#button1").ligerButton({icon:"count",text:"统计",click:function(){
			count();
		}});
		initGrid();
	});
	
	
	function initGrid(){
		  var month = $("#month").val();
		  grid = $("#countGrid").ligerGrid({
          columns: [
                   { display: '车牌号', name: 'car_num',align: 'left', width: 80}, 
                   { display: '日期', name: 'buy_date',hide:true}, 
                   { display: '变更号', name: 'change_num',align: 'center', width: 80}, 
				   { display: '管理股室', name: 'manager_room_name',align: 'center', width: 200},
					{ display: '驾驶员', name: 'driver',align: 'center', width: 80},
					{ display: '开始公里数', name: 'startkm',align: 'center', width: 80},
					{ display: '结束公里数', name: 'endkm',align: 'center', width: 80},
					{ display: '实际里程', name: 'allkm',align: 'center', width: 80},
					{ display: '汽油量(升)', name: 'V',align: 'center', width: 80},
					{ display: '汽油金额(元)', name: 'y',align: 'center', width: 80,type:'float'},
                   { display: '百公里油耗(升)', name: 'km100',align: 'center', width: 200}
            ], 
            height:'100%',
            pageSize:20,
            url:encodeURI("oa/car/apply/countCarConsume.do?month="+month)
     });
	}
	
	//执行统计
	function count(){
		 var month = $("#month").val();
		 $.getJSON("oa/car/apply/countCarConsume.do?month="+month,function(response){
			  if(response.success){
				   var gridData=response.data;
				   if(gridData!=null){
					   grid.loadData({Rows:response.data});
				   }
			  }
		  });
	}
	
	</script>
</head>
<body>
<div class="item-tm">
	<div id="toolbar1"></div>
</div>

<div class="item-tm">
	<div class="l-page-title2 has-icon has-note">
		<div class="l-page-title2-div"></div>
		<div class="l-page-title2-text"><h1>车辆消耗统计</h1></div>
		<div class="l-page-title2-note">统计指定月份车辆的消耗情况</div>
		<div class="l-page-title2-icon"><img src="k/kui/images/icons/32/statistics.png" border="0"></div>
		<div class="l-page-title-content" style="top:5px;">
			<form id="formObj" name="formObj">
				<table border="0" cellpadding="0" cellspacing="0" width="" class="l-table1">
					<tr>
						<td width="60" align="right">统计月份&nbsp;&nbsp;</td>
	                    <td width="60">
	                      <input type="text" ltype="select" name="month" id="month" validate="{required:true}" 
											ligerui="{initValue:'<%=DateUtil.getDateTime("MM", new Date()) %>', data: [  
                                                                { text:'01月', id:'01' }
                                                                , { text:'02月', id:'02' }
                                                                , { text:'03月', id:'03' }
                                                                , { text:'04月', id:'04' }
                                                                , { text:'05月', id:'05' }
                                                                , { text:'06月', id:'06' }
                                                                , { text:'07月', id:'07' }
                                                                , { text:'08月', id:'08' }
                                                                , { text:'09月', id:'09' }
                                                                , { text:'10月', id:'10' }
                                                                , { text:'11月', id:'11' }
                                                                , { text:'12月', id:'12' }] }" />
	                    </td>
	                    <td width=""><div id="button1"></div></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div id="countGrid" style="overflow: auto"></div>
</div>
</body>
</html>