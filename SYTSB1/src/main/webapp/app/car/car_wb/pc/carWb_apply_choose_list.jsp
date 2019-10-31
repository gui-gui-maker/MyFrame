<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>车辆维保申请选择列表</title>
<%@ include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults : {columnWidth : 0.33, labelAlign : 'right', labelSeparator : '', labelWidth : 60 },//可以自己定义 layout:column,float,
		sp_fields : [
				{ name : "car_num", compare : "like" },
				{ group : [
					{ name : "apply_date", compare : ">=", value : "" },
					{ label : "到", name : "apply_date", compare : "<=",value:"",labelAlign:"center",labelWidth:20}
				],columnWidth:0.33
				}, 
				{ name : "data_status", compare : "=" } ],
		tbar : [ { text : '详情', id : 'detail', click : detail, icon : 'detail' }, '-',
				 { text : '流转过程', id : 'turnHistory', icon : 'follow', click : turnHistory } ],
		listeners : {
			rowClick : function(rowData, rowIndex) {
				
			},
			rowDblClick : function(rowData, rowIndex) {
				detail();
			},
			selectionChange : function(rowData, rowIndex) {
				selectionChange();
			},
			<c:if test="${param.chooseType=='wbRecord'}">
			onSelectRow: function(rowdata, rowindex, rowDomElement){
				checkIsRecorded();
			},
			</c:if>
			rowAttrRender : function(rowData, rowid) {
				var fontColor="black";
				if(rowData.data_status=="车队负责人待审核"){
					fontColor = "blue";
				}else if(rowData.data_status=="办公室负责人待审核"){
					fontColor = "maroon";
				}else if(rowData.data_status=="车队负责人待盖章"){
					fontColor = "orange";
				}else if(rowData.data_status=="已盖章"){
					fontColor = "green";
				}else if(rowData.data_status=="维保已完成"){
					fontColor = "#8FBC8F";
				}else if(rowData.data_status=="审核不通过"){
					fontColor = "red";
				}
	            return "style='color:"+fontColor+"'";
			}
		}
	};

	//行选择改变事件
	function selectionChange() {
		var count = Qm.getSelectedCount();//行选择个数
		var data_status = Qm.getValueByName("data_status");
		if (count == 1) {
			Qm.setTbar({ detail : true, turnHistory : true });
		} else {
			Qm.setTbar({ detail : false, turnHistory : false });
		}
	}
	//详情
	function detail() {
		var selectedId = Qm.getValueByName("id");
		var width = 800;
		var height = 800;
		var windows = top.$.dialog({
			width : width,
			height : height,
			lock : true,
			title : "详情",
			data : {
				"window" : window
			},
			content : 'url:app/car/car_wb/pc/carWb_apply_detail.jsp?pageStatus=detail&id='+ selectedId
		});
	}
	//查看流转过程
	function turnHistory() {
		top.$.dialog({
			width : 400,
			height : 700,
			lock : true,
			title : "流转过程",
			content : 'url:carWbAction/getFlowStep.do?id='+ Qm.getValueByName("id").toString(),
			data : {
				"window" : window
			}
		});
	}
	function getSelectedCarWb(){
        return {
            id: Qm.getValuesByName("id"),
            sn: Qm.getValuesByName("sn"),
            fk_car_id: Qm.getValuesByName("fk_car_id"),
            car_num: Qm.getValuesByName("car_num"),
        };
    }
	function checkIsRecorded(){
		var selectedId = Qm.getValueByName("id").toString();  // 业务信息ID
		$.getJSON("carWbRecordAction/getWbRecordsByFkWbId.do?fkWbId="+selectedId, function(resp){
			if(resp.success){
				if(resp.data.length!=0){
					$.ligerDialog.confirm('选择的数据已填写维保记录，是否继续？', function (yes){
				        if(!yes){Qm.refreshGrid(); }
				        });
				}else{
					
				}
			}
		})
	}
	function refreshGrid() {
		Qm.refreshGrid();
	}
</script>
</head>

<body>
	<div class="item-tm" id="titleElement">
		<div class="l-page-title">
			<div class="l-page-title-note">
				提示： <font color="#336666">“暗绿色”</font>代表维保已完成。
			</div>
		</div>
	</div>
	<qm:qm pageid="TJY2_CAR_WB" singleSelect="true">
	<qm:param name="data_status" value="5" compare="=" />
	</qm:qm>
</body>
</html>