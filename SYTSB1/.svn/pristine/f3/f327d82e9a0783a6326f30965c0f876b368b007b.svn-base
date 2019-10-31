<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>任务接收/反馈</title>
<%@include file="/k/kui-base-list.jsp"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
User uu = (User)curUser.getSysUser();
com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
e.getId();

String userId=e.getId();
String uId = SecurityUtil.getSecurityUser().getId();
CurrentSessionUser useres = SecurityUtil.getSecurityUser();
%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="text/javascript">
    var khFileUploader;
	var qmUserConfig = {
			 sp_defaults:{columnWidth:0.33,labelWidth:60},
				sp_fields : [
		{name:"status",compare:"like",value:""},
		{name:"responsible_person",compare:"like",value:""},
		{name:"department",compare:"like",value:""},
				],
		tbar:[
              {text: '详情', id: 'detail', icon: 'detail', click: detail}
		      ,"-",
		      {text: '接收', id: 'submit', icon: 'give-back', click: addxf}
		      ,"-",
              {text: '反馈', id: 'edit', icon: 'feedback', click: edit}
            ],
		listeners: {
			rowClick: function(rowData, rowIndex) {
			},
			rowDblClick: function(rowData, rowIndex) {
				detail();
			},	
			selectionChange: function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();
				Qm.setTbar({
					detail: count==1,
					edit: count==1,
					del: count>0
				});
			},
				selectionChange: function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();
				var up_status = Qm.getValueByName("status");
			
				if("未开始"==up_status ){

				Qm.setTbar({
					detail: count==1,
					edit: count<1,
					del: count>0,
					submit:count==1
				});}else if("进行中"==up_status){
					
					Qm.setTbar({
						detail: count==1,
						edit: count==1,
						del: count>0,
						submit:count<0
					});
				} else {
					Qm.setTbar({
						detail: count==1,
						edit: count<0,
						del: count>0,
						submit:count<0
					});
				}

			},
			rowAttrRender : function(rowData, rowid) {
                var fontColor="black";
                if (rowData.status=='已完成'){
                    fontColor="green";
                }else if(rowData.status=='进行中'){
                	fontColor="blue";
                }else if(rowData.status=='未完成') {
                    fontColor="red";
                }else if(rowData.status=='未开始') {
                    fontColor="orange";
                }
                return "style='color:"+fontColor+"'";
            }
		}
	};
	





function add() {
	top.$.dialog({
		width: 700,
		height: 400,
		lock: true,
		parent: api,
		data: {
			window: window
		},
		title: "新增",
		content: 'url:app/office/ywhbsgz_fb_detail.jsp?pageStatus=add'
	});
}







function addxf(){
	
	  var id = Qm.getValueByName("id");
	    $.ligerDialog.confirm('是否接收？', function (yes){
	        if(!yes){return false;}
	        top.$.ajax({
	                     url: "office/ywhbsgzAction/receive.do?id="+id,
	                     type: "GET",
	                     dataType:'json',
	                     async: false,
	                     success:function (data) {
	                        if(data.success){
	                        	 top.$.notice('接收成功！！！',3);
	                            Qm.refreshGrid();//刷新
	                        }else{
	                            $.ligerDialog.warn(data.msg);
	                        }
	                     },
	                     error:function () {
	                    	 $.ligerDialog.error("出错了！请重试！！！");
	                     }
	                 });
	    });
	
	
}
	function edit() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 805,
			height: 400,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "反馈",
			content: 'url:app/office/ywhbsgz_fb_detail.jsp?id=' + id + '&pageStatus=modify'
		});
	}
	function detail() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 900,
			height: 400,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "详情",
			content: 'url:app/office/Ywhbsgz_yx.jsp?id=' + id + '&pageStatus=detail'
		});
	}
	function del() {
		$.del(kFrameConfig.DEL_MSG, "app/office/delete.do", {
			"ids": Qm.getValuesByName("id").toString()
		});
	}
	
</script>

</head>
<body>
<div class="item-tm" id="titleElement">
        <div class="l-page-title">
            <div class="l-page-title-note">提示：列表数据项
                <font color="green">“绿色”</font>代表已完成，
                <font color="orange">“橙色”</font>代表未开始，
                <font color="red">“红色”</font>代表未完成，
                <font color="blue">“蓝色”</font>代表进行中。
            </div>
        </div>
    </div>
	<qm:qm pageid="TJY2_YWHBSGZ_FK" script="false" singleSelect="true">
		<qm:param name="responsible_personid" value="<%=userId%>" compare="like" />
	</qm:qm>
</body>
</html>