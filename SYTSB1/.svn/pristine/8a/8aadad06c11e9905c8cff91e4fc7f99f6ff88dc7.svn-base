<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
			sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
			sp_fields:[
	         {name:"checkout_type",compare: "like"},
	         {name:"status",compare: "like"},
	         {group: [{name: "effective_stoptime", compare: ">="},
	  				{label: "到", name: "effective_stoptime", compare: "<=", labelAlign: "center", labelWidth: 20}
	  			]}
	    ],
		tbar: [ {
			text: '详情', id: 'detail', icon: 'detail', click: detail
		}, "-", {
			text: '添加', id: 'add', icon: 'add', click: add
		}, "-", {
			text: '修改', id: 'edit', icon: 'modify', click: edit
		}, "-", {
			text: '删除', id: 'del', icon: 'delete', click: del
		}, "-", {
			text: '启用', id: 'qishi', icon: 'accept', click: qishi
		}, "-", {
			text: '停用', id: 'stop', icon: 'forbid', click: disable
		}],
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
					qishi: count==1,
					del: count>0
				});
			},
			selectionChange : function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();//行选择个数
				//alert(count);
				var up_status = Qm.getValueByName("status");
				//alert(up_status);
				var sp_status = Qm.getValueByName("sp_status");
				//alert(sp_status);
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
			
			},rowAttrRender : function(rowData, rowid) {
                var fontColor="black";
                if(rowData.status=='启用'){
                  if (rowData.warning>="0") {
                      fontColor="red";
                  }
                }
                return "style='color:"+fontColor+"'";
            }
		}
	};
	 
	function disable(){
		var id = Qm.getValueByName("id");
		var up_status = Qm.getValueByName("status");
        $.ligerDialog.confirm('是否要停用？', function (yes){
        	if(!yes){return false;}
            top.$.ajax({
                 url: "xybz/file1/tingyong.do?id="+id,
                 type: "GET",
                 dataType:'json',
                 async: false,
                 success:function (data) {
                	 if("停用"==up_status){
                		 $.ligerDialog.warn("此条已停用！");
                	 }else{
	                	 if(data.success){
	                         top.$.notice('停用成功！',3);
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
		var up_status = Qm.getValueByName("status");
        $.ligerDialog.confirm('是否要启用？', function (yes){
        	 if(!yes){return false;}
            top.$.ajax({
                 url: "xybz/file1/qiyong.do?id="+id,
                 type: "GET",
                 dataType:'json',
                 async: false,
                 success:function (data) {
                	 if("启用"==up_status){
                		 $.ligerDialog.warn("此条已启用！");
                	 }else{
	                	 if(data.success){
	                         top.$.notice('启用成功！',3);
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
			width: 600,
			height: 350,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "添加",
			content: 'url:app/qualitymanage/jybz_file_datail.jsp?pageStatus=add'
		});
	}
	function edit() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 600,
			height: 350,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "修改",
			content: 'url:app/qualitymanage/jybz_file_datail.jsp?id=' + id + '&pageStatus=modify'
		});
	}
	function detail() {
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
			content: 'url:app/qualitymanage/jybz_file_datail.jsp?id=' + id + '&pageStatus=detail'
		});
	}
	function del() {
		$.del(kFrameConfig.DEL_MSG, "xybz/file1/delete.do", {
			"ids": Qm.getValuesByName("id").toString()
		});
	}
</script>
</head>
<body>
<div class="item-tm" id="titleElement">
	    <div class="l-page-title">
			<div class="l-page-title-note">提示：列表数据项
				<font color="red">“红色”</font>代表此标准文件已过期		
			</div>
		</div>
	</div>
	<qm:qm pageid="TJY2_XYBZWJ1" singleSelect="true"></qm:qm>
</body>
</html>