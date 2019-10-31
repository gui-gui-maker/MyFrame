<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.util.Properties" %>
<%

    DecimalFormat mbFormat = new DecimalFormat("#0.00");
    DecimalFormat percentFormat = new DecimalFormat("#0.0");
    int NUM_BLOCKS = 50;
    Runtime runtime = Runtime.getRuntime();
    double freeMemory = (double) runtime.freeMemory() / (1024 * 1024);
    double totalMemory = (double) runtime.totalMemory() / (1024 * 1024);
    double usedMemory = totalMemory - freeMemory;
    double percentFree = ((double) freeMemory / (double) totalMemory) * 100.0;
    int free = 100 - (int) Math.round(percentFree);
    Properties props = System.getProperties();
%>
<head>
<%@include file="/k/kui-base-list.jsp"%>
<link rel="stylesheet" type="text/css" href="app/public/skins/Aqua/css/ligerui-desktop.css" />
<script type="text/javascript">
$(function(){//jQuery页面载入事件
	if ($.browser.msie && parseFloat($.browser.version)<=6) {//png透明
		try{
			DD_belatedPNG.fix('.load_load_l,.load_load_r');
		}catch (e){
			alert(e)
		}
	}
});
</script>
</head>
<body class="oa_global">
<form name="form1" method="post">
	<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0" class="layww_tbl">
		<tr>
			<td align="left" height="20"><fieldset class="fieldSet">
					<legend class="fieldLeg" id="titleId">JAVA虚拟机状态</legend>
					<table border=1 width="460" cellpadding="0" cellspacing="0">
						<tr class="padding">
							<td align="right" width="130">已用内存：</td>
							<td align="left" width="280"><%= mbFormat.format(usedMemory) %> MB</td>
						</tr>
						<tr class="padding">
							<td width="130" align="right">内存总量：</td>
							<td align="left"><%= mbFormat.format(totalMemory) %> MB</td>
						</tr>
						<tr>
							<td colspan="2" class="td_loading">

								<div class="loading_all">
									<div class="loading_normal">
										<table width="84%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td class="load_n_l"></td>
												<td class="load_n_m">&nbsp;</td>
												<td class="load_n_r"></td>
											</tr>
										</table>
									</div>
									<div class="loading_load" style="width:<%=free%>%;">
										<table width="84%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td class="load_load_l"></td>
												<td class="load_load_m">&nbsp;</td>
												<td class="load_load_r"></td>
											</tr>
										</table>
									</div>
									<div class="loading_txt"><%= percentFormat.format(percentFree) %>%空闲</div>
								</div>
							</td>
						</tr>
					</table>
				</fieldset>
				<fieldset class="fieldSet">
					<legend class="fieldLeg" id="osId">服务器信息</legend>
					<table border="1" cellspacing="0" cellpadding="0" width="97%" class="padding">
						<tr>
							<td align="right" width="120">操作系统版本：</td>
							<td align="left"><span class="black"><%=props.get("os.name")%> <%=props.get("os.version")%></span></td>
						</tr>
						<tr>
							<td width="120" align="right">操作系统类型：</td>
							<td align="left"><span
                                    class="black"><%=props.get("os.arch")%> <%=props.get("sun.arch.data.model")%>位</span></td>
						</tr>
						<tr>
							<td width="120" align="right">用户：</td>
							<td align="left"><span class="black"><%=props.get("user.name")%></span></td>
						</tr>
						<tr>
							<td width="120" align="right">目录：</td>
							<td align="left"><span class="black"><%=props.get("user.dir")%></span></td>
						</tr>
						<tr>
							<td width="120" align="right">临时目录：</td>
							<td align="left"><span class="black"><%=props.get("java.io.tmpdir")%></span></td>
						</tr>
						<tr>
							<td width="120" align="right">JAVA运行环境：</td>
							<td align="left"><span><%=props.get("java.runtime.name")%><%=props.get("java.runtime.version")%></span></td>
						</tr>
						<tr>
							<td width="120" align="right">JAVA虚拟机：</td>
							<td align="left"><span><%=props.get("java.vm.name")%><%=props.get("java.vm.version")%></span></td>
						</tr>
					</table>
				</fieldset></td>
		</tr>
		<tr>
			<td height="5"></td>
		</tr>
	</table>
</form>
</body>
</html>