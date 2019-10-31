<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>

<head>
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>

<script type="text/javascript">
	var qmUserConfig = {
			sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
			sp_fields:[
	         {name:"checker",compare: "like",width:"150",columnWidth:0.25},
	         {name:"report_number",compare: "=",width:"150",columnWidth:0.25},
	         {group: [
	  				{ name: "issuing_time", compare: ">="},
	  				{label: "到", name: "issuing_time", compare: "<=", labelAlign: "center", labelWidth:20}
	  		 ],columnWidth:0.4}
		],
		tbar: [ {
			text: '详情', id: 'detail', icon: 'detail', click: detail
		}, "-", {
			text: '新增', id: 'add', icon: 'add', click: add
		}, "-", {
			text: '修改', id: 'edit', icon: 'modify', click: edit
		}, "-", {
			text: '删除', id: 'del', icon: 'delete', click: del
		}/*, "-", {
	        text:'提交', id:'submit',icon:'submit', click: submit
	    }, "-", {
	        text:'1', id:'add',icon:'submit', click: xx
	    }, "-", {
	        text:'2', id:'add',icon:'submit', click: zz
	    }, "-", {
	        text:'xxx', id:'add',icon:'submit', click: qq
	    }*/],
	   
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
					del: count>0,
					submit: count==1
						
				});
			}
		}
	};
	function xx() {
		top.$.dialog({
			width: 900,
			height: 550,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "新增",
			content: 'url:app/archives/attachment_upload.jsp?pageStatus=add'
		});
	}
	function zz() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 600,
			height: 350,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "详情",
			content: 'url:app/archives/attachment_upload.jsp?id=' + id + '&pageStatus=detail'
		});
	}
	function qq() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 600,
			height: 350,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "详情",
			content: 'url:app/archives/xxxxx.jsp?id=' + id + '&pageStatus=detail'
		});
	}
	function submit(){
		var id = Qm.getValueByName("id");
		var up_status = Qm.getValueByName("status");
        $.ligerDialog.confirm('是否要提交？', function (yes){
        	 if(!yes){return false;}
            top.$.ajax({
                 url: "update/destroy/xhtj.do?id="+id,
                 type: "GET",
                 dataType:'json',
                 async: false,
                 success:function (data) {
                	 if("已提交到分管院领导"==up_status){
                		 $.ligerDialog.warn("此条已提交！");
                	 }else{
	                	 if(data.success){
	                         $.ligerDialog.success("提交成功！");
	                         Qm.refreshGrid();//刷新
	                     }else{
	                         $.ligerDialog.warn(data.msg);
	                     }
                	 }
                 },
                 error:function () {
                     $.ligerDialog.warn("提交失败！");
                 }
             });
        });
	}
	
	function add() {
		top.$.dialog({
			width: 900,
			height: 450,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "新增",
			content: 'url:app/archives/archives_file_detail.jsp?pageStatus=add'
		});
	}

	function edit() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 900,
			height: 450,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "修改",
			content: 'url:app/archives/archives_file_detail.jsp?id=' + id + '&pageStatus=modify'
		});
	}
	function detail() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 900,
			height: 450,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "详情",
			content: 'url:app/archives/archives_file_detail.jsp?id=' + id + '&pageStatus=detail'
		});
	}
	function del() {
		$.del(kFrameConfig.DEL_MSG, "archives/file/delete.do", {
			"ids": Qm.getValuesByName("id").toString()
		});
	}
	//列表刷新
	function refreshGrid() {
	    Qm.refreshGrid();
	}
	function close(){
		api.close();
	}
</script>
</head>
<body>

	<qm:qm pageid="TJY2_ARCHIVES_FILE" singleSelect="true"></qm:qm>
</body>
</html>