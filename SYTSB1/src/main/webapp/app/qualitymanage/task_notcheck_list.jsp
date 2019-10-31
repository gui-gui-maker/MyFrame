<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
var qmUserConfig={
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
		sp_fields:[
			         {name:"task_sn",compare: "like"},
			         {name:"item_name",compare: "like"},
			         {name:"status",compare: "="},
			         {name:"duty_dep",compare: "like"},
			         {name:"duty_name",compare: "like"},
			         {group: [
			  				{name: "register_date", compare: ">="},
			  				{label: "到", name: "register_date", compare: "<=", labelAlign: "center", labelWidth:20}
			  		 ]}
			  		 
			        ],
		
		
		
			tbar:[{
				text: '详情', id: 'detail', icon: 'detail', click: detail
			}, "-", {
				text: '审核', id: 'check',icon:'check', click: check
			}
			],
			 listeners:{
					rowClick:function(rowData,rowIndex){
					},
					rowDblClick:function(rowDate,rowIndex){
						detail();
					},
					selectionChange:function(rowDate,rowIndex){
						var count = Qm.getSelectedCount();
						Qm.setTbar({
							detail:count==1,
							edit:count==1,
							del:count>0,
							submit:count>0,
							check:count==1,
							dispose:count==1
						});
						if(count > 0){
							var up_status = Qm.getValuesByName("status");
							if (count == 0) {
								Qm.setTbar({
									detail: false,
									check:false
								});
							} else if (count == 1) {
								Qm.setTbar({
									detail: true,
									check:true
								});
							} else {
								Qm.setTbar({
									detail: false,
									check:false
								});
							}
							
						}
					},
					rowAttrRender : function(rowData, rowid) {
			            var fontColor="black";
			            if (rowData.status=='待审核')
			        	 	fontColor="#6F00D2";
			            return "style='color:"+fontColor+"'";
			        }
         						
				
				 }
			
	};


function detail() {
	var id = Qm.getValueByName("id");
	var status = Qm.getValueByName("status1");
	top.$.dialog({
		width: 900,
		height: 350,
		lock: true,
		parent: api,
		title: "任务详情",
		content: 'url:app/qualitymanage/task_allot_fb_datail.jsp?pageStatus=detail&id='+ id+"&status="+status,
		data: {
			window: window
		}
	});
}


function check(){
	var id = Qm.getValueByName("id");
	var up_status = Qm.getValueByName("status");
	top.$.dialog({
		width: 900,
		height: 500,
		lock: true,
		parent: api,
		data: {
			window: window
		},
		title: "审核",
		content: 'url:app/qualitymanage/task_allot_datail.jsp?pageStatus=modify&id='+ id+"&up_status="+up_status+"&isCheck=1"
	});
}

function dispose(){
	var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 900,
			height: 500,
			lock: true,
			parent: api,
			title: "处理任务",
			content: 'url:app/qualitymanage/task_allot_fb.jsp?pageStatus=modify&id='+ id,
			data: {
				window: window
			}
	});
}

</script>
	<div class="item-tm" id="titleElement">
        <div class="l-page-title">
            <div class="l-page-title-note">提示：列表数据项
                <font color="#6F00D2">“紫色”</font>代表待审核。
            </div>
        </div>
    </div>
</head>
<body>
	<qm:qm pageid="TJY2_ZLGL_NOTCHECK" script="false" singleSelect="true"></qm:qm>
</body>
</html>