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
	         {name:"test_nature",compare: "like"},
	         {name:"report_number",compare: "like"},
	         {group: [
	  				{name: "bjwtsj", compare: ">="},
	  				{label: "到", name: "bjwtsj", compare: "<=", labelAlign: "center", labelWidth:20}
	  		 ],columnWidth:0.4}
	    ],
		tbar: [ {
			text: '详情', id: 'detail', icon: 'detail', click: detail
		}/*, "-", {
			text: '新增', id: 'add', icon: 'add', click: add
		}*/, "-", {
			text: '填写', id: 'edit', icon: 'modify', click: edit
		}/*, "-", {
			text: '删除', id: 'del', icon: 'delete', click: del
		}, "-", {
	        text:'打印', id:'dy',icon:'print', click: dy
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
					dy: count<0,
				});
			}/*,
			selectionChange : function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();//行选择个数
				//alert(count);
				var up_status = Qm.getValueByName("status");
				//alert(up_status);
				var sp_status = Qm.getValueByName("sp_status");
				//alert(sp_status);
				var status={};
				if(count==0){
					status={detail:false, edit:false, del:false,submit:false};
				}else if(count==1){
					if("已提交"==up_status){
						if("undefined"==sp_status){
							status={detail:true, edit:false, del:true,submit:false};
						}else{
							status={detail:true, edit:false, del:false,submit:false};
						}
					}else if("审核通过"==up_status){
						if("undefined"==sp_status){
							status={detail:true, edit:false, del:false,submit:false};
						}else{
							status={detail:true, edit:false, del:false,submit:false};
						}
					}else if("审核中"==up_status){
						if("undefined"==sp_status){
							status={detail:true, edit:false, del:false,submit:false};
						}else{
							status={detail:true, edit:false, del:false,submit:false};
						}
					}else if("审核未通过"==up_status){
						if("undefined"==sp_status){
							status={detail:true, edit:false, del:false,submit:false};
						}else{
							status={detail:true, edit:false, del:true,submit:false};
						}
					}else{
						status={detail:true, edit:true, del:true,submit:true};
					}
				}else{
					if("已提交1"==sp_status){
						status={detail:false, edit:false, del:true,submit:false};
					}else{
						status={detail:false, edit:false, del:false,submit:false};
					}
				}
				Qm.setTbar(status);
			},rowAttrRender : function(rowData, rowid) {
	            var fontColor="black";
	            if (rowData.status=='已提交'){
	                fontColor="blue";
	            }else if(rowData.status=='审核中'){
	            	fontColor="orange";
	            }else if(rowData.status=='审核通过'){
	            	fontColor="green";
	            }else if(rowData.status=='审核未通过'){
	            	fontColor="red";
	            }
	            return "style='color:"+fontColor+"'";
	        }*/
		}
	};
	
	function dy(){
		
	}
	function add() {
		top.$.dialog({
			width: 900,
			height: 550,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "新增",
			content: 'url:app/qualitymanage/taskbook_datail.jsp?pageStatus=add'
		});
	}

	function edit() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 900,
			height: 550,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "修改",
			content: 'url:app/qualitymanage/taskbook_datail.jsp?id=' + id + '&pageStatus=modify'
		});
	}
	function detail() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 900,
			height: 550,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "详情",
			content: 'url:app/qualitymanage/taskbook_datail.jsp?id=' + id + '&pageStatus=detail'
		});
	}
	function del() {
		var id = Qm.getValueByName("id");
		$.ligerDialog.confirm('您确定要删除所选数据吗？', function (yes){
        	if(!yes){return false;}
			top.$.ajax({
	            url: "/delete.do?ids="+id,
	            type: "POST",
	            dataType:'json',
	            async: false,
	            success:function (data) {
	               if(data.success){
	                  top.$.notice(data.msg,3);
	                   Qm.refreshGrid();//刷新
	               }else{
	                   $.ligerDialog.warn(data.msg);
	                   Qm.refreshGrid();//刷新
	               }
	            },
	            error:function () {
	           	 $.ligerDialog.error("出错了！请重试！!");
	            }
	        });
		});
	}
	function refreshGrid() {
	    Qm.refreshGrid();
	}
	function close(){
		api.close();
	}
</script>
</head>
<body>
<!-- 	<div class="item-tm" id="titleElement"> -->
<!--         <div class="l-page-title"> -->
<!--             <div class="l-page-title-note">提示：列表数据项 -->
<!--                 <font color="black">“黑色”</font>代表未提交， -->
<!--                 <font color="blue">"灰色"</font>代表已提交， -->
<!--                 <font color="green">“绿色”</font>代表审核通过， -->
<!--                 <font color="orange">“橙色”</font>代表审核中， -->
<!--                 <font color="red">“红色”</font>代表审核未通过。 -->
                
<!--             </div> -->
<!--         </div> -->
<!--     </div> -->
	<qm:qm pageid="TJY2_ZL_TASKBOOK" singleSelect="true"></qm:qm>
</body>
</html>