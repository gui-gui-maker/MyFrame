<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	// 默认值，可自定义
		sp_fields:[
	         {name:"tianshu",compare: "like"},
	         {name:"state",compare: "="}
	    ],
		tbar: [ {
			text: '详情', id: 'detail', icon: 'detail', click: detail
		}, "-", {
			text: '新增', id: 'add', icon: 'add', click: add
		}, "-", {
			text: '修改', id: 'edit', icon: 'modify', click: edit
		}, "-", {
			text: '删除', id: 'del', icon: 'delete', click: del
		}, "-", {
			text: '启用', id: 'qishi', icon: 'modify', click: qishi
		}, "-", {
			text: '停用', id: 'stop', icon: 'modify', click: disable
		}],
		listeners: {
			rowClick: function(rowData, rowIndex) {
			},
			rowDblClick: function(rowData, rowIndex) {
				detail();
			},
	        rowAttrRender : function(rowData, rowIndex){
        		var fontColor="black";
        		if (rowData.state == '启用'){
        			fontColor="green";
        		}else if(rowData.state == '停用' ) {
        			fontColor="red";
        		}else if(rowData.state=='未启用'){
        			fontColor="blue";
        		}
        		return "style='color:"+fontColor+"'";
        	},
			selectionChange: function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();
				Qm.setTbar({
					detail: count==1,
					edit: count==1,
					qishi: count==1,
					del: count>0
				});
			},
			selectionChange : function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();//行选择个数
				var up_status = Qm.getValueByName("state");
				var sp_status = Qm.getValueByName("sp_status");
				var status={};
				if(count==0){
					status={detail:false, edit:false, del:false,qishi:false,stop:false};
				}else if(count==1){
					if("启用"==up_status){
						if("undefined"==sp_status){
							status={detail:true, edit:false, del:true,qishi:false,stop:true};
						}else{
							status={detail:true, edit:false, del:false,qishi:false,stop:true};
						}
					}else{
						status={detail:true, edit:true, del:true,qishi:true,stop:false};
					}
				}else{
					if("启用"==sp_status){
						status={detail:false, edit:false, del:true,qishi:false,stop:true};
					}else{
						status={detail:false, edit:false, del:false,qishi:false,stop:false};
					}
				}
				Qm.setTbar(status);
			
			}
		}
	};
	 function beginEdit(rowid) { //修改
	        manager.beginEdit(rowid);
    }
    function cancelEdit(rowid) { //取消
        manager.cancelEdit(rowid);
    }
    function endEdit(rowid){//提交
        manager.endEdit(rowid);
    }

    function deleteRow(rowid){
        if (confirm('确定删除?')){
            manager.deleteRow(rowid);
        }
    }
    var newrowid = 100;
    function addNewRow(){
        manager.addEditRow();
    } 
	function disable(){
		var id = Qm.getValueByName("id");
		var up_status = Qm.getValueByName("state");
        $.ligerDialog.confirm('是否要停用？', function (yes){
        	if(!yes){return false;}
            top.$.ajax({
                 url: "report/yjsz/tingyong.do?id="+id,
                 type: "GET",
                 dataType:'json',
                 async: false,
                 success:function (data) {
                	 if("停用"==up_status){
                		 $.ligerDialog.warn("此条已停用！");
                	 }else{
	                	 if(data.success){
	                         $.ligerDialog.success("停用成功！");
	                         Qm.refreshGrid();//刷新
	                     }else{
	                         $.ligerDialog.warn(data.msg);
	                     }
                	 }
                 },
                 error:function () {
                     $.ligerDialog.warn("停用失败！");
                 }
             });
        });
	}
	function qishi(){
		var id = Qm.getValueByName("id");
		var up_status = Qm.getValueByName("state");
        $.ligerDialog.confirm('是否要启用？', function (yes){
        	 if(!yes){return false;}
            top.$.ajax({
                 url: "report/yjsz/qiyong.do?id="+id,
                 type: "GET",
                 dataType:'json',
                 async: false,
                 success:function (data) {
                	 if("启用"==up_status){
                		 $.ligerDialog.warn("此条已启用！");
                	 }else{
	                	 if(data.success){
	                         $.ligerDialog.success("启用成功！");
	                         Qm.refreshGrid();//刷新
	                     }else{
	                         $.ligerDialog.warn(data.msg);
	                     }
                	 }
                 },
                 error:function () {
                     $.ligerDialog.warn("启用失败！");
                 }
             });
        });
	}
	function add() {
		top.$.dialog({
			width: 650,
			height: 290,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "新增",
			content: 'url:app/report_nk/report_yjsz_detail.jsp?pageStatus=add'
		});
	}
	function edit() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 650,
			height: 280,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "修改",
			content: 'url:app/report_nk/report_yjsz_detail.jsp?id=' + id + '&pageStatus=modify'
		});
	}
	function detail() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 650,
			height: 280,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "详情",
			content: 'url:app/report_nk/report_yjsz_detail.jsp?id=' + id + '&pageStatus=detail'
		});
	}
	function del() {
		$.del(kFrameConfig.DEL_MSG, "report/yjsz/delete.do", {
			"ids": Qm.getValuesByName("id").toString()
		});
	}
</script>
</head>
<body>
<div class="item-tm" id="titleElement">
		<div class="l-page-title">
			<div class="l-page-title-note">提示：状态
				<font color="blue">“蓝色”</font>代表未启用，
				<font color="green">“绿色”</font>代表启用，
                <font color="red">“红色”</font>代表停用。
			</div>
		</div>
	</div>
	<qm:qm pageid="TJY2_REPORTYJSZ" singleSelect="true"></qm:qm>
</body>
</html>