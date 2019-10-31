<%@page import="java.util.Map"%>
<%@page import="com.khnt.rbac.impl.bean.Role"%>
<%@page import="com.khnt.utils.StringUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>报告领取</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	//var	bar = [{ text:'添加设备', id:'addComputer',icon:'add', click: addComputer}];
	var	bar = [{ text:'添加设备', id:'addComputer',icon:'add', click: addComputer},
	   	       { text:'刷新缓存', id:'refreshCache',icon:'f5', click: refreshCache},
   	    	   { text:'绑定用户', id:'lockUser',icon:'userLock', click: lockUser},
 	    	   { text:'删除设备', id:'deleteTableValue',icon:'delete', click: deleteTableValue}];
	
	
	var qmUserConfig = {
			sp_defaults:{columnWidth:0.33,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
			sp_fields:[
		    ],
			tbar:bar,
	        listeners: {
				selectionChange :function(rowData,rowIndex){
					//selectionChange();
				},
				rowAttrRender : function(rowData, rowid) {

	        	},
	        	selectionChange: function(rowData, rowIndex) {
					var count = Qm.getSelectedCount();
					Qm.setTbar({
						lockUser: count>0,
						deleteTableValue:count>0
					});
				}
			}
	};
	

	//报告领取
	function addComputer() {
		$.ajax({
			type:'POST',
			url:'payment/payInfo/addPanelComputer.do',
			data:{},
    		dataType:'JSON',
			success:function(ress){
				if(ress){
					top.$.notice("推送成功！");
					//api.close();
				}
			}
			
		});
	}
	
	//刷新码表缓存
	function refreshCache(){
		$("body").mask("正在刷新...");
		 $.getJSON("pub/codetable/refreshCache.do",function(resp){
         	if(resp.success){
         		top.$.notice('刷新缓存成功！',4)
         		treeManager.clear();
         		$.getJSON("pub/codetable/codetableTree.do",function(response){
         			reCreateTree(response);
         		});
         	}
         	else
         		$.ligerDialog.error('刷新缓存失败！请稍后再试。');
			$("body").unmask();
    	});
	}
	
	function lockUser(){
		var cid = Qm.getValueByName("value");
		top.$.dialog({
			width:$(top).width()*0.5,
            height:$(top).height()*0.6,
			lock:true,
			title:"设置设备【" + Qm.getValueByName("name") + "】绑定的用户",
			parent: api,
			data: {
				window: window
			},
			content: 'url:payment/order/lockUserCid/getSelectdData.do?cid='+cid+'&type=1&checkbox=1',
			ok:function(w){
				var datas = this.iframe.contentWindow.getSelectResult();
	            if(datas){
	            	$.getJSON("payment/order/lockUserCid/saveMiddle.do", {userId:datas.code,cid:cid}, function(res){
	            		if(res.success){
	            			top.$.notice("绑定成功！",3);
	            			Qm.refreshGrid();
	    					//api.close();
	            		}   
         	       });
	               return true;
	            }
	            else return false;
			},
			cancel:true
		});
	}
	
	function deleteTableValue(){
		$.del(kFrameConfig.DEL_MSG, "payment/order/lockUserCid/deleteTableValues.do", {
			"ids": Qm.getValuesByName("id").toString()
		});
	}
</script>
</head>

<body>
	<qm:qm pageid="SCSEI_COMPUTER" singleSelect="true" >
	</qm:qm>
</body>
</html>