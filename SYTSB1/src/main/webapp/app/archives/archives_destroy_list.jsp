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
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:''},	
		sp_fields:[
	         {name:"proposer",compare: "like"},
	         {name:"apply_unit",compare: "="},
	         {group: [
	  				{name: "apply_time", compare: ">=", width:100},
	  				{label: "到", name: "apply_time", compare: "<=", labelAlign: "center", labelWidth:20}
	  		 ],columnWidth:0.4}
		],
		tbar: [ {
			text: '详情', id: 'detail', icon: 'detail', click: detail
		}, "-", {
			text: '申请', id: 'add', icon: 'add', click: add
		}, "-", {
			text: '修改', id: 'edit', icon: 'modify', click: edit
		}, "-", {
			text: '删除', id: 'del', icon: 'delete', click: del
		}, "-", {
	        text:'提交', id:'submit',icon:'submit', click: submit
	    }, "-", {
	        text:'审核过程', id:'gc',icon:'follow', click: gc
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
					del: count>0,
					submit: count==1
						
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
					status={detail:false, edit:false, del:false,submit:false,gc:false};
				}else if(count==1){
					if("已提交"==up_status){
						if("undefined"==sp_status){
							status={detail:true, edit:false, del:true,submit:false,gc:true};
						}else{
							status={detail:true, edit:false, del:false,submit:false,gc:true};
						}
					}else if("审核中"==up_status){
						if("undefined"==sp_status){
							status={detail:true, edit:false, del:true,submit:false,gc:true};
						}else{
							status={detail:true, edit:false, del:false,submit:false,gc:true};
						}
					}else if("审核通过"==up_status){
						if("undefined"==sp_status){
							status={detail:true, edit:false, del:true,submit:false,gc:true};
						}else{
							status={detail:true, edit:false, del:false,submit:false,gc:true};
						}
					}else if("审核未通过"==up_status){
						if("undefined"==sp_status){
							status={detail:true, edit:false, del:true,submit:false,gc:true};
						}else{
							status={detail:true, edit:false, del:true,submit:false,gc:true};
						}
					}else{
						status={detail:true, edit:true, del:true,submit:true,gc:false};
					}
				}else{
					if("已提交"==sp_status){
						status={detail:false, edit:false, del:true,submit:false,gc:true};
					}else{
						status={detail:false, edit:false, del:false,submit:false,gc:false};
					}
				}
				Qm.setTbar(status);
			},
	        rowAttrRender : function(rowData, rowid) {
                var fontColor="black";
                if (rowData.status=='审核通过'){
                    fontColor="green";
                }else if(rowData.status=='审核未通过') {
                    fontColor="red";
                }else if(rowData.status=='已提交') {
                    fontColor="orange";
                }else if(rowData.status=='审核中') {
                    fontColor="blue";
                }
                return "style='color:"+fontColor+"'";
            }
		}
	};
	function gc(){
		var id = Qm.getValueByName("id");
		if(!id){
            top.$.notice('请先选择要查看的数据！',3);
            return;
        }
		top.$.dialog({
			width: 715,
			height: 350,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "审核过程",
			content: 'url:app/archives/archives_process.jsp?ids=' + id + '&pageStatus=detail'
		});
	}
	function submit() {
		var id = Qm.getValueByName("id");
        if(!id){
        	$.ligerDialog.warn('请先选择要提交审核的数据！',3);
            return;
        }
        $.ligerDialog.confirm('是否提交审核？', function (yes){
        if(!yes){return false;}
         $("body").mask("提交中...");
         getServiceFlowConfig("TJY2_FN_XHSQ1","",function(result,data){
                if(result){
                     top.$.ajax({
                         url: "archives/destroy/subFolws.do?id="+id+"&flowId="+data.id+"&typeCode=TJY2_FN_XHSQ1&status="+status,
                         type: "GET",
                         dataType:'json',
                         async: false,
                         success:function (data) {
                             if (data) {
                               // $.ligerDialog.alert("提交成功！！！");
                                top.$.notice('提交成功！！！',3);

                                Qm.refreshGrid();
                                $("body").unmask();
                             }
                         },
                         error:function () {
                        	 $.ligerDialog.error('出错了！请重试！！!',3);
                             $("body").unmask();
                         }
                     });
                }else{
                	$.ligerDialog.error('出错了！请重试！!',3);
                 $("body").unmask();
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
			title: "申请",
			content: 'url:app/archives/archives_destroy_detail.jsp?pageStatus=add'
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
			content: 'url:app/archives/archives_destroy_detail.jsp?id=' + id + '&pageStatus=modify'
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
			content: 'url:app/archives/archives_destroy_detail.jsp?id=' + id + '&pageStatus=detail'
		});
	}
	function del() {
		$.del(kFrameConfig.DEL_MSG, "archives/destroy/delete.do", {
			"ids": Qm.getValuesByName("id").toString()
		});
	}
</script>
</head>
<body>
<div class="item-tm" id="titleElement">
		<div class="l-page-title">
			<div class="l-page-title-note">提示：列表数据项
				<font color="black">“黑色”</font>代表单据未提交，
                <font color="orange">“橙色”</font>代表未审核，
                <font color="blue">“蓝色”</font>代表审核中，
                <font color="green">“绿色”</font>代表审核通过，
                <font color="red">“红色”</font>代表审核未通过。
			</div>
		</div>
	</div>
	<qm:qm pageid="TJY2_ARCHIVES_xh" singleSelect="true"></qm:qm>
</body>
</html>