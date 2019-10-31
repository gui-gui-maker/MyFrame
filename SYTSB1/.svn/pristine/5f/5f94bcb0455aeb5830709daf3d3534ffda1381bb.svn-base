<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>固定资产列表</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript" src="pub/bpm/js/util.js"></script>
		<script type="text/javascript" src="app/common/js/render.js"></script>
		<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
		<!-- 生成条形码JS导入 -->
		<script type="text/javascript" src="app/common/lodop/LodopFuncs.js"></script>
		<script type="text/javascript">
		var arrayObj;
        var qmUserConfig = {
        	sp_defaults:{columnWidth:0.25,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
            sp_fields:[
				{name:"card_no", compare:"like"},
				{name:"have_cardno", compare:"="},
				{name:"self_no", compare:"like"},
            	{name:"asset_name", compare:"like"},
            	{name:"spaci_model", compare:"like"},
            	{name:"sn", compare:"like"},
            	{name:"asset_value", compare:"like"},
				{name:"place_location", compare:"like"},
				{name:"user_department", compare:"like"},
				{name:"user_name", compare:"like"},
				{name:"owner", compare:"like"},
				{name:"asset_status", compare:"like"},
				{name:"inventory", compare:"like"},
				{group:[
						{name:"inventory_date", compare:">=", value:""},
						{label:"到", name:"inventory_date", compare:"<=", value:"", labelAlign:"center", labelWidth:20}
					]},
				{name:"loan_status", compare:"="},
				{group:[
					{name:"instock_date", compare:">=", value:""},
					{label:"到", name:"instock_date", compare:"<=", value:"", labelAlign:"center", labelWidth:20}
				]},
				{name:"remark", compare:"like"},
				{name:"isExpired", compare:"="}
            ],
            tbar:[
            	{ text:'详情', id:'detail',icon:'detail', click: detail}
            	<sec:authorize access="hasRole('TJY2_EQ_MANAGER')">
            	,"-",
                { text:'增加', id:'add',icon:'add', click: add},
                "-",
                { text:'修改', id:'modify',icon:'modify', click:modify},
                "-",
                { text:'删除', id:'del',icon:'delete', click:del}
            	</sec:authorize>
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
	   		if(count>=1){
	   			var assetName = Qm.getValuesByName("asset_name").toString();//名称
	    		var owner = Qm.getValuesByName("owner").toString();//归属
	    		var selfNo = Qm.getValuesByName("self_no").toString();//自编号/编号
	    		var spaciModel = Qm.getValuesByName("spaci_model").toString();//规格型号
	    		var sn = Qm.getValuesByName("sn").toString();//序列号/出厂编号
	    		var productDate = Qm.getValuesByName("product_date").toString();//出厂日期
	    		var userDepartment = Qm.getValuesByName("user_department").toString();//使用部门
	    		var BarCodeValue = Qm.getValuesByName("barcode").toString();//条码
	    		arrayObj = [assetName,owner,selfNo,spaciModel,sn,productDate,userDepartment,BarCodeValue];
	   		}
			Qm.setTbar({
				detail: count==1,
				modify: count==1,
				del: count>0
			});
		}
        
		function detail(){
			var id = Qm.getValueByName("id").toString();
			var owner = Qm.getValueByName("owner").toString();
			if(owner=="鼎盛公司"){
				owner = "100047";
			}else{
				owner = "100000";
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
        
        //新增
        function add(){
			top.$.dialog({
				width:700,
				height:470,
				lock:true,
				title:"新增",
				data: {window:window},
				content: 'url:app/equipment/ppe/ppe_basic_detail.jsp?pageStatus=add'
			});
        }
        
        //修改
        function modify(){
        	var id = Qm.getValueByName("id").toString();
			top.$.dialog({
				width:700,
				height:470,
				lock:true,
				title:"修改",
				data: {window:window},
				content: 'url:app/equipment/ppe/ppe_basic_detail.jsp?pageStatus=modify&id='+id
			});
        } 
        
        //删除
        function del(){
            $.del("确定要删除？删除后无法恢复！","equipPpeAction/delete.do",{"ids":Qm.getValuesByName("id").toString()});
        }
        // 刷新Grid
        function refreshGrid() {
            Qm.refreshGrid();
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
		<qm:qm  pageid="TJY2_PPE_LIST" singleSelect="false">
			<qm:param name="asset_status" value="YBF" compare="=" />
		</qm:qm>
		<script type="text/javascript">
		Qm.config.columnsInfo.loan_status.binddata = [
			{id: '0', text: '未借用'},
			{id: '1', text: '已借用'}
		];
		Qm.config.columnsInfo.isExpired.binddata = [
			{id: '2', text: '未过期'},
			{id: '1', text: '即将过期'},
			{id: '0', text: '已过期'}
		];
	</script>
	</body>
</html>
