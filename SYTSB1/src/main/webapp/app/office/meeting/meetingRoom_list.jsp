<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>通用查询</title>
<%@ include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
		 sp_defaults:{columnWidth:0.33,labelAlign:'right',labelSeparator:'',labelWidth:80},//可以自己定义 layout:column,float,
		 sp_fields : [
				{
					name : "code", compare : "like"
				}, {
					name : "manager_name", compare : "like"
				},{group: [
							{name: "accommodate_no", compare: ">="},
							{label: "到", name: "accommodate_no", compare: "<=", labelAlign: "center", labelWidth: 20}
							]}, 				 
	          
	        ],
		tbar:[{
                text: '详情', id: 'detail', icon: 'detail', click: detail
            }, "-", {
                text: '新增', id: 'add', icon: 'add', click: add 
            }, "-", {
                text: '修改', id: 'modify', icon: 'modify', click:modify
            }, "-", {
                text: '删除', id: 'del', icon: 'delete', click:del
            }, "-", {
                text:'申请使用', id: 'useReq', icon: 'accept', click:useReq
            }, "-", {
                text:'使用记录 ', id: 'availableRoom', icon: 'search', click:availableRoom
            }
            ],
		////            //提供以下4个事件
		listeners : {
			rowClick : function(rowData, rowIndex) {
			}, rowDblClick : function(rowData, rowIndex) {
				detail(rowData);
			}, selectionChange : function(rowData, rowIndex) {
				selectionChange();
			}, rowAttrRender: function(rowData, rowid) {
				var fontColor="black";
				 //3天之内或今天之后没有被申请使用的会议室
				if(rowData.fromendtoday/24<0 || rowData.fromtoday/24>3){
					fontColor="blue";
				}
				else{
					fontColor="black";
				}
				return "style='color:"+fontColor+"'";
			}, 
			afterQmReady: function() {
			}
		}
	};
	//行选择改变事件
	function selectionChange() {
		var count = Qm.getSelectedCount();//行选择个数
		if (count == 0) {
			Qm.setTbar({
				add : true, modify : false, del : false, detail : false,useReq:false,availableRoom:false
			});
		} else if (count == 1) {
			Qm.setTbar({
				add : true, modify : true, del : true, detail : true,useReq:true,availableRoom:true
			});
		} else {
			Qm.setTbar({
				add : true, modify : false, del : true, detail : false,useReq:false,availableRoom:false
			});
		}
	}

	function modify(item) {
		var status = "modify";
		selectedId = Qm.getValuesByName("id");
		if (selectedId.length > 1) {
			$.ligerDialog.alert('不支持批量修改，请只选择一条数据！', "提示");
			return;
		} else if (selectedId.length < 1) {
			$.ligerDialog.alert('请先选择要修改的数据！', "提示");
			return;
		}
		var width = 700;
		var height = 400;
		var windows = top.$.dialog({
			width : width, height : height, lock : true, title : "修改", data : {
				"window" : window
			}, content : 'url:app/office/meeting/meetingRoom_detail.jsp?status=' + status + '&id=' + selectedId
		});
	}
	function add(item) {
		var width = 700;
		var height = 400;
		var windows = top.$.dialog({
			width : width, height : height, lock : true, title : "新增", data : {
				"window" : window
			}, content : 'url:app/office/meeting/meetingRoom_detail.jsp?status=add'

		});
	}

	function del() {
		var selectedId = Qm.getValuesByName("id");
		if (selectedId.length < 1) {
			$.ligerDialog.alert('请先选择要删除的事项！', "提示");
			return;
		}
		$.ligerDialog.confirm(kui.DEL_MSG, function (yes) {
			if(yes){
				$.ajax({
					url:"oa/meetingRoom/info/delMeetingRoom.do?ids="+selectedId,
					type:"post",
					async:false,
					success:function(data){
						if(data.success){
							if(data.data.msg!=null){
								$.ligerDialog.alert(data.data.msg, "提示");
							}
							else{
								top.$.notice("删除成功！");
							}
							Qm.refreshGrid();
						}
					}
				});
			}
		});
	}

	function detail(item) {
		var selectedId = item.id;
		if (selectedId == "detail") {
			selectedId = Qm.getValuesByName("id");
		} else {
			selectedId = item.id;
		}
		var width = 700;
		var height = 400;
		var windows = top.$.dialog({
			width : width, height : height, lock : true, title : "详情", data : {
				"window" : window
			}, content : 'url:app/office/meeting/meetingRoom_detail.jsp?status=detail' + '&id=' + selectedId
		});
	}
	
	function useReq(){
		var roomId=Qm.getValuesByName("id");
		var roomName=Qm.getValuesByName("code");
		var hyadress=Qm.getValuesByName("place");
		var url="url:app/office/meeting/meetingReq_detail.jsp?status=add&roomId="+roomId;
		var width = 800;
		var height = 1000;
		var windows = top.$.dialog({
			width : width, height : height, lock : true, title : "会议室使用申请", data : {"window" : window,"roomName":roomName,"hyadress":hyadress},
			content : url
		});
	}
	
	//会议室使用记录
	function availableRoom(){
		var roomId=Qm.getValuesByName("id");
		var roomName=encodeURI(encodeURI(Qm.getValuesByName("code")));
		var hyadress=encodeURI(encodeURI(Qm.getValuesByName("place")));
		var url="url:app/office/meeting/availableRoom_list.jsp?status=add&roomId="+roomId+"&roomName="+roomName+"&hyadress="+hyadress;
		var width = 850;
		var height = 480;
		var windows = top.$.dialog({
			width : width, height : height, lock : true, title : "会议室使用记录", data : {"window" : window},
			content : url
		});
	}
</script>
</head>
<%
	CurrentSessionUser user = SecurityUtil.getSecurityUser();
	String orgId=user.getUnit().getId();
	String departmentId=user.getDepartment().getId();
%>
<body>
	<!-- 提示文字 -->
    <div class="item-tm" id="titleElement">
	    <div class="l-page-title">
			<div class="l-page-title-note">提示：列表数据项
				<font color="blue">“蓝色”</font>代表3天之内或今天之后没有被申请使用的会议室。
				<font color="black">“黑色”</font>代表3天之内已被申请使用的会议室。
			</div>
		</div>
	</div>
	<!-- 提示文字 -->
	<qm:qm pageid="TJY2_OFFICE_MROOM" script="false" singleSelect="false">
	<qm:param name="creator_unit_id" value="<%=orgId%>" compare="=" />
	</qm:qm>
</body>
</html>