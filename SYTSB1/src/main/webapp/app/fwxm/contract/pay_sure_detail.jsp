<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri="http://bpm.khnt.com" prefix="bpm"%>
<head pageStatus="${param.status}">
<title></title>
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">

$(function() {
	$("#basePeop").initForm({
		 toolbar:[
                   {text:"保存", icon:"save", click:function(){
                	  /*  var contract_type = $("#contract_type").ligerGetRadioGroupManager().getValue();
                	   var fk_contract_id = $("#fk_contract_id").val();
                	   if(contract_type=="2"&&fk_contract_id==""){
                		   $.ligerDialog.warn('分包合同需要选择总合同!')
                		   return;
                	   } */
                           $("#basePeop").submit();
                       }
                   },
	            	{text:"取消", icon:"cancel", click:function(){
			        	api.close();
		    }}
               ],
        
        toolbarPosition :"bottom",
		getSuccess : function(resp) {
		},
		success : function(resp) {//处理成功
			if (resp.success) {
				top.$.notice("保存成功！");
				api.data.window.submitAction();
				api.close();
			} else {

					$.ligerDialog.error('保存失败!')
			}
		}
	});
	
});
	function callBackPeo(id,peop_name,id_card){
		//选择单位回调方法
		$("#peo_id").val(id);
		$("#peopName").val(peop_name);
		$("#idCard").val(id_card);
	}

	function selectPeo(){
		var url = 'url:app/tzsb/supervise/test/test_peo_select.jsp?id=${param.id}';
		top.$.dialog({
			parent: api,
			width : 800, 
			height : 550, 
			lock : true, 
			title:"添加考试人员",
			content: url,
			data : {"parentWindow" : window}
		});
	}
	
	function changeType(val,text){
		$("#operation_type").val(text);
	}
	
	function changeItem(val,text){
		$("#operation_item").val(text);
	}
	
</script>
</head>
<body>
	<form name="basePeop" id="basePeop" method="post"
		action="contractInfoAction/paySure.do" >
		<input type="hidden" name="ids" value="${param.ids }"/>
		<br/>
		<table cellpadding="3" cellspacing="0" class="l-detail-table">
			<tr>
				<td class="l-t-td-left">收费对账情况：</td>
				<td class="l-t-td-right" >
					<input id="paySure" name="paySure" type="text" ltype='text' validate="{required:true}"/>
				</td>
				</td>
			</tr> 
		</table>
	</form>
</body>
</html>