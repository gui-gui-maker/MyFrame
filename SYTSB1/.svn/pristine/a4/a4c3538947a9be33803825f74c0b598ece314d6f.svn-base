<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript">
	var qmUserConfig = {
		<sec:authorize ifNotGranted="super_administrate">
					<tbar:toolBar type="tbar" code="bpm_process_track">
				</tbar:toolBar>,
				</sec:authorize>
				<sec:authorize access="hasRole('super_administrate')">
					tbar : [ {
			text : '跟踪',
			icon : 'role',
			id : 'detail',
			click : doTrack
		}, "-", {
			text : '终止',
			icon : 'busy',
			id : 'over',
			click : over
		}/*, "-", {
			text : '提醒',
			icon : 'discuss',
			id : 'tixin',
			click : tixin
		} */],
				</sec:authorize>
		sp_fields:[{
			name:"flow_name",compare:"like"
		}],
		listeners : {
			rowDblClick : doTrack,
			selectionChange : function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();
				Qm.setTbar({detail: count==1,over: count==1 && Qm.getValueByName("state")==""});
			}
		}
	};
	//跟踪
	function doTrack() {
		trackProcess(Qm.getValueByName("id"));
	}
    //结束流程
    function over() {
        $.ligerDialog.confirm("确定要终止？",function(yy){
            if(yy){
		        $("body").mask("正在处理...");
		    	terminateProcess(Qm.getValueByName("id"),function(r,m){
		        	$("body").unmask();
		        	if(r){
		            	Qm.refreshGrid();
		            	top.$.notice("流程成功终止！",3);
		            }
		        	else $.ligerDialog.error("终止失败！<br/>" + (m||""));
		        });
            }
        });
    }
</script>
</head>
<body>
	<qm:qm pageid="bpm_4" script="false" singleSelect="true"></qm:qm>
	<script test="text/javascript">
		Qm.config.sortInfo= [{field:'creator_date',direction:'desc'}];
	</script>
</body>
</html>
