<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>固定资产列表</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript" src="app/common/js/render.js"></script>
		<script type="text/javascript">
		var arrayObj;
        var qmUserConfig = {
        	sp_defaults:{columnWidth:0.45,labelAlign:'right',labelSeparator:'',labelWidth:120},	// 默认值，可自定义
            sp_fields:[
				{name:"self_no", compare:"like"},
            	{name:"asset_name", compare:"like"},
            	{name:"spaci_model", compare:"like"},
            	{name:"sn", compare:"like"}
            ],
            tbar:[
            	{ text:'详情', id:'detail',icon:'detail', click: detail}
            ],
            listeners: {
                selectionChange : function(rowData,rowIndex){
                	selectionChange();
                },
        		rowDblClick :function(rowData,rowIndex){
        			detail(rowData.id);
        		},
        		rowAttrRender : function(rowData, rowid) {
        			var fontColor="black";
    	            if(rowData.inventory=='未盘点'){
    	           	 fontColor="#8E8E8E";
    	            }else if(rowData.inventory=='已盘点'){
    	            	if(rowData.isExpired=='已过期'){
    	            		fontColor="red";
    	            	}else if(rowData.isExpired=='即将过期'){
    	            		fontColor="orange";
    	            	}
    	            }
    	            return "style='color:"+fontColor+"'";
    	        }
            }
        };
        
        // 行选择改变事件
		function selectionChange() {
	   		var count = Qm.getSelectedCount(); //行选择个数
			Qm.setTbar({
				detail: count==1
			});
		}
        
		function detail(){
			var id = Qm.getValueByName("id").toString();
			var owner = Qm.getValueByName("owner").toString();
			if(owner == "鼎盛公司"){
				owner = "100047";
        	}else if(owner == "司法鉴定中心"){
        		owner = "100044";
        	}else if(owner == "四川省特种设备检验检测协会"){
        		owner = "100042";
        	}else if(owner == "四川省特种设备检验研究院"){
        		owner = "100000";
        	}else{
        		owner = "UNKNOWN";
        	}
			top.$.dialog({
				width:700,
				height:470,
				lock:true,
				title:"详情",
				content: 'url:app/equipment/ppe/ppe_basic_detail.jsp?pageStatus=detail&id='+id+'&owner='+owner,
				data:{window:window}
			});
		}
        // 刷新Grid
        function refreshGrid() {
            Qm.refreshGrid();
        }
	    function f_select(){ 
	   		var rows = Qm.getQmgrid().getSelectedRows();
	   		Qm.refreshGrid();
         	return rows;
        }
    </script>
	</head>
	<body>
	<div class="item-tm" id="titleElement">
        <div class="l-page-title">
            <div class="l-page-title-note">提示：列表数据项
                <font color="black">“黑色”</font>代表已盘点，
                <font color="#8E8E8E">“灰色”</font>代表未盘点，
                <font color="orange">“橙色”</font>代表即将超过使用年限，
                <font color="red">“红色”</font>代表已超过使用年限。
            </div>
        </div>
    </div>
		<qm:qm  pageid="TJY2_PPE_LIST" singleSelect="false"></qm:qm>
	</body>
</html>
