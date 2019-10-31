<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.StringUtil"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>设备查询列表</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript" src="app/common/js/render.js"></script>
		<script type="text/javascript">
			
			var qmUserConfig = {
	         	sp_fields:[
					{name:"eq_name",compare:"like",value:""},
					{name:"eq_no",compare:"like",value:""},
					{name:"eq_model",compare:"like",value:""},
					{name:"eq_sn",compare:"like",value:""}
	            ],
	            tbar:[
			        { text:'选择', id:'select',icon:'check',click:selectValue},
			        "-",
			        { text:'关闭', id:'close',icon:'modify',click:close}
	            ],
	            listeners: {
	                selectionChange : function(rowData,rowIndex){
	                	var count=Qm.getSelectedCount();//行选择个数
	                    Qm.setTbar({select:count==1,close:count>0});
	                }
	            }
	        };
        	
			function selectValue(){		
				var result = new Object();
				result.id = Qm.getValueByName("id");
				result.eq_name = Qm.getValueByName("eq_name");
				result.eq_no = Qm.getValueByName("eq_no");
				result.eq_model = Qm.getValueByName("eq_model");
				result.eq_sn = Qm.getValueByName("eq_sn");
				result.eq_manufacturer = Qm.getValueByName("eq_manufacturer");
				result.eq_buy_price = Qm.getValueByName("eq_buy_price");
				result.eq_accurate = Qm.getValueByName("eq_accurate");
				result.eq_use_department = Qm.getValueByName("eq_use_department");
				result.eq_status = Qm.getValueByName("eq_status");			    
				result.eq_check_cycle = Qm.getValueByName("eq_check_cycle");//检定周期
				result.eq_next_check_date = Qm.getValueByName("eq_next_check_date");// 下次检定日期 
				result.check_unit = Qm.getValueByName("check_unit");// 检定单位
				api.data.parentWindow.callBack(result);
				api.close();	
			}
			function close(){
				api.close();
			}
			
			function submitAction(o) {
			    Qm.refreshGrid();
			}
        </script>
	</head>
	<body>
		<%-- <%
			String isScrap = request.getParameter("isScrap");	// 报废申请
			String isStop = request.getParameter("isStop");		// 停用申请
		%> --%>
		<qm:qm pageid="TJY2_EQ_DOCIMASY" script="false" singleSelect="true">
			<%-- <%
				if(StringUtil.isNotEmpty(isScrap)){
					%>
					<qm:param name="eq_status" value="03" compare="="/><!-- 03：待报废 -->
					<qm:param name="eq_status" value="04" compare="=" logic="or"/><!-- 04：已停用 -->
					<%
				}else{
					if(StringUtil.isNotEmpty(isStop)){
						%>
						<qm:param name="eq_status" value="01" compare="="/><!-- 01：完好在用 -->
						<qm:param name="eq_status" value="02" compare="=" logic="or"/><!-- 02：待维修 -->
						<qm:param name="eq_status" value="03" compare="=" logic="or"/><!-- 03：待报废 -->
						<%
					}else{
						%>
						<qm:param name="eq_status" value="01" compare="="/><!-- 03：完好在用 -->
						<%
					}
				}
			%> --%>
		</qm:qm>
	</body>
</html>