<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<title>公式管理</title>
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
	$(function() {
		$(".layout").ligerLayout({
			bottomHeight : 35,
			space : 0
		});
		$("#toolbar").ligerButton({
			items:[{
				id : "confirm",
				icon : "accept",
				text : "确定",
				click : function(){
					if(api.data.callback){
						var win = $("#f_frame").get(0).contentWindow.window;
						var data = win.chooseFormula();
						if(data){
							api.data.callback(data);
							api.close();
						}
						else{
							top.$.dialog.tips('请选择公式！',4,"k/kui/images/icons/dialog/32X32/hits.png",null,0)
						}
					}
				}
			},{
				id : "cancel",
				icon : "cancel",
				text : "取消",
				click : function(){
					api.close();
				}
			}]
		});
	});
</script>
</head>
<body>
	<div class="layout">
		<div position="center">
			<iframe id="f_frame" name="item_frame" src="pub/formula/formula_select_list.jsp?type=${param.type}"
				width="100%" height="100%" frameborder="0"></iframe>
		</div>
		<div position="bottom" id="toolbar" style="padding:5px;text-align:right;"></div>
	</div>
</body>
</html>
