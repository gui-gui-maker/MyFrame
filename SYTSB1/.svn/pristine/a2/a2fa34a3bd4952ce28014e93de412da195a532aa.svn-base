<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<% 
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
 %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
 <html xmlns="http://www.w3.org/1999/xhtml"> 
 <head> 
 <title>通用查询</title>
<%@ include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
		sp_fields : [
				{
					name : "name", compare : "like"
				}, {
					name : "SQ_DEPARTMENT", compare : "like"
				}
		],
		listeners : {
			rowClick : function(rowData, rowIndex) {}, 
			rowDblClick : function(rowData, rowIndex) {}, 
			selectionChange : function(rowData, rowIndex) {
				selectionChange();
			},
			rowAttrRender : function(rowData, rowid) {
			}
		}, afterQmReady: function() {
			var orgCode = "<sec:authentication property='principal.unit.id' htmlEscape='false' />";
			Qm.setCondition([{name: "unit_id", compare: "=", value: orgCode}]);
			Qm.searchData();
		}
	};

	//选择结果
	function getSelectResult() {
		var result = {
			reqid : "", reqname : "",roomid:"",department:"",start_time:"",end_time:"",content:""
		};
		result.reqid = Qm.getValuesByName("id");
		result.reqname = Qm.getValuesByName("name");
		result.roomid=Qm.getValuesByName("roomid");
		result.sqDepartment=Qm.getValuesByName("SQ_DEPARTMENT");
		result.start_time=Qm.getValuesByName("start_time");
		result.end_time=Qm.getValuesByName("end_time");
		result.content=Qm.getValuesByName("content");
		return result;
	}

	function selectionChange() {
		var count = Qm.getSelectedCount();//行选择个数
		var noticeState = Qm.getValuesByName("release").toString();//行选择个数
	}
</script>
</head>
<body>
	<qm:qm pageid="TJY2_MEETING_CHANGE" script="false" singleSelect="true"></qm:qm>
</body>
</html>