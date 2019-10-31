<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>

<head>
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>

<script type="text/javascript">
		function fdd(){
			top.$.dialog({
				width: 900,
				height: 550,
				lock: true,
				parent: api.data,
				data: {
					window: window
				},
				title: "新增",
				content: 'url:app/qualitymanage/sgcjjybg_datail.jsp?pageStatus=add'
			});
		}
		function wtt(){
			top.$.dialog({
				width: 900,
				height: 550,
				lock: true,
				parent: api.data,
				data: {
					window: window
				},
				title: "新增",
				content: 'url:app/qualitymanage/sgcjjybg_datail2.jsp?pageStatus=add'
			});
		}
		
	</script>
</head>
<body>
	<h1 id="sg2" align="center" style="padding:5mm 0 2mm 0;font-family:微软雅黑;font-size:6mm;">选择检验性质</h1></br>
    <table id="sg1" class="check">
		 <tr>
	         <td width="60px">&nbsp;</td>
	         <td style="width: 100px;" class="l-t-td-right">
             	<input name="fd" id="fd" type="button" value="法定检验报告申请" onclick="fdd();"/>
             </td>
	         <td width="50px"></td>
             <td width="100px" class="l-t-td-right">
             	<input name="wt" id="wt" type="button" value="委托检验报告申请" onclick="wtt();"/>	
             </td>
          </tr>
	</table>
</body>
</html>