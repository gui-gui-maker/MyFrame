<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>

<head>
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>

<script type="text/javascript">
		function sgAdd(){
			top.$.dialog({
				width: 900,
				height: 450,
				lock: true,
				parent: api,
				data: {
					window: window
				},
				title: "申请",
				content: 'url:app/archives/archives_borrow_detail.jsp?pageStatus=add&isSg=1'
			});
		}
		function add(){
			top.$.dialog({
				width: 900,
				height: 450,
				lock: true,
				parent: api,
				data: {
					window: window
				},
				title: "申请",
				content: 'url:app/archives/archives_borrow_detail.jsp?pageStatus=add&isSg=0'
			});
		}
		
	</script>
</head>
<body>
	<h1 id="sg2" align="center" style="padding:5mm 0 2mm 0;font-family:微软雅黑;font-size:6mm;">检验报告借阅</h1></br>
    <table id="sg1" class="check">
		 <tr>
	         <td width="60px">&nbsp;</td>
	         <td style="width: 100px;" class="l-t-td-right">
             	<input name="sgAdd" id="sgAdd" type="button" value="手工检验报告申请" onclick="sgAdd();"/>
             </td>
	         <td width="50px"></td>
             <td width="100px" class="l-t-td-right">
             	<input name="add" id="add" type="button" value="检验报告申请" onclick="add();"/>	
             </td>
          </tr>
	</table>
</body>
</html>