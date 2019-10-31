<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<%
	String quality_zssq_fk=request.getParameter("id");
%>
<head>
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
			sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	// 默认值，可自定义
			sp_fields:[
		    ],
		tbar: [
		{text: '归还', id: 'gh', icon: 'dispose', click: gh},
		"-",
	    {text:'关闭', id:'close',icon:'close', click: close}
		],
	   
		listeners: {
			rowClick: function(rowData, rowIndex) {
			},
			rowDblClick: function(rowData, rowIndex) {
			},
			selectionChange: function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();
				Qm.setTbar({
					gh: count>0
				});
			},
			selectionChange : function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();//行选择个数
				var isback = Qm.getValueByName("isback");
				var isbacks = Qm.getValuesByName("isback");
				var allowgh =  true;
		        for(var i=0;i<isbacks.length;i++){
		        	 if(isbacks[i]=="已归还"){
		        		 allowgh =  false;
		        	 }
		        }
				if(count==0){
					Qm.setTbar({
						gh: false
					});
				}else if(count==1){
					if("已归还"==isback){
						Qm.setTbar({
							gh: false
						});
					}else{
						Qm.setTbar({
							gh: true
						});
					}
				}else{
					Qm.setTbar({
						gh: true&&allowgh
					});
				}
			},
	        rowAttrRender : function(rowData, rowid) {
                var fontColor="red";
                if (rowData.isback=='已归还'){
                    fontColor="green";
                }
                return "style='color:"+fontColor+"'"; 
            }
		} 
	};
	function gh() {
		var ids = Qm.getValuesByName("id");
        $.ligerDialog.confirm('确定归还？', function (yes){
        if(!yes){return false;}
        $("body").mask("提交中...");
        top.$.ajax({
             url: "QualityZssqSubAction/gh.do?ids="+ids+"&quality_zssq_fk="+"<%=quality_zssq_fk%>",
             type: "GET",
             dataType:'json',
             async: false,
             success:function (data) {
                 if (data) {
                    top.$.notice('操作成功！！！',3);
                    Qm.refreshGrid();
                    $("body").unmask();
                    api.data.window.Qm.refreshGrid();
                 }
             },
             error:function () {
            	 $.ligerDialog.error('出错了！请重试！！!',3);
                 $("body").unmask();
             }
         });    
        });
	}
	function close(){
		api.data.window.Qm.refreshGrid();
		api.close();
	}
</script>
</head>
<body>
<div class="item-tm" id="titleElement">
		 <div class="l-page-title">
			<div class="l-page-title-note">提示：列表数据项
				<font color="red">“红色”</font>代表未归还，
                <font color="green">“绿色”</font>代表已归还。
			</div>
		</div> 
	</div> 
	<qm:qm pageid="TJY2_ZSSQ_PARTBACK" singleSelect="false">
		<qm:param name="quality_zssq_fk" value="<%=quality_zssq_fk%>" compare="=" />
	</qm:qm>
</body>
</html>