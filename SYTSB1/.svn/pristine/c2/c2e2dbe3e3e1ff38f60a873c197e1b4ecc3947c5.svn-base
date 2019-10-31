<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.khnt.utils.StringUtil" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";

	String type = request.getParameter("type");
	String roomId0 = request.getParameter("roomId");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>通用查询</title>
<%@ include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
  var toolbar =  [
               /* {text:'详情', id:'detail',icon:'detail', click:detail },
                "-",*/
                {text : '申请使用', id : 'useReq', icon : 'accept', click : useReq}
               ];
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.25,labelAlign:'right',labelSeparator:'',labelWidth:80},//可以自己定义 layout:column,float,
		sp_fields : [
				{
					 name : "name", compare : "like"
				},{
					group:[
					       {name:"start_time",compare:">=",value:""},
					       {label:"到",name:"end_time",compare:"<=",value:"",labelAlign:"center",labelWidth:20}
					       ],columnWidth:0.50
				}
		],
		tbar:toolbar,
		<%if ("apply".equals(type)) {%>
		tbar : [{
			text : '查看使用情况',
			id : 'detail',
			icon : 'detail',
			click : detail
		}],
		<%}%>
		listeners : {
			rowClick : function(rowData, rowIndex) {
			}, rowDblClick : function(rowData, rowIndex) {
			}, selectionChange : function(rowData, rowIndex) {
		<%if ("apply".equals(type)) {%>
			selectionChange();
		<%}%>
			},rowAttrRender : function(rowData, rowid) {
				if (rowData.roomcode != "")
					rowData.roomcode = rowData.roomcode + "(" + rowData.roomcode + ")";
			}, afterQmReady: function() {
				var orgCode = "<sec:authentication property='principal.unit.id'/>";
				Qm.setCondition([{name: "unit_id", compare: "llike", value: orgCode}]);
				Qm.searchData();
			}
		}
	};
	
	function detail(){
		
	}
	//会议室使用申请
	function useReq(){
		var roomId="${param.roomId}";
		var roomName=decodeURI("${param.roomName}");
		var hyadress=decodeURI("${param.hyadress}");
		var url="url:app/office/meeting/meetingReq_detail.jsp?status=add&roomId="+roomId+"&roomName="+roomName;
		var width = 1000;
		var height = 800;
		var windows = top.$.dialog({
			width : width, height : height, parent:api,lock : true, title : "会议室使用申请", data : {"window" : window,"roomName":roomName,"hyadress":hyadress},
			content : url
		});
	}

	//选择结果
	function getSelectResult() {
		var result = {
			roomid : "", roomcode : ""
		};
		result.roomid = Qm.getValuesByName("id");
		result.roomcode = Qm.getValuesByName("code");
		return result;
	}

	function selectionChange() {
		var count = Qm.getSelectedCount();//行选择个数
		var noticeState = Qm.getValuesByName("release").toString();//行选择个数
	}
</script>
</head>
<%
String sql=" select "+
			" room.*, "+
			" req.start_time,req.end_time,req.name "+
			" from TJY2_OFFICE_MEETING_REQ req,TJY2_OFFICE_MEETING_ROOM room "+
			" where room.id=req.FK_OFFICE_MEETING_ROOM and  req.MEETING_STATUS<>'1'  ";
String startTime=request.getParameter("startTime");
String endTime=request.getParameter("endTime");
//取指定时间段空闲的会议室
if(!(StringUtil.isEmpty(startTime)||StringUtil.isEmpty(endTime))){
	sql+=" and( req.end_time<=to_date('"+startTime+"','yyyy-mm-dd HH24:mi:ss') or req.start_time>=to_date('"+endTime+"','yyyy-mm-dd HH24:mi:ss'))";
}
%>
<body>
<!-- 只显示该时间段有未用的会议室-->
	<qm:qm pageid="TJY2_MEET_ABLEROOM" script="false" singleSelect="true" >
	<qm:param name="id" value="<%=roomId0%>" compare="like" />
	</qm:qm>
</body>
</html>