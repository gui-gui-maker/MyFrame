<%@page import="util.ReportUtil"%>
<%@page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.DateUtil"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>

	<head>
		<base href="<%=basePath%>">  
		<%@include file="/k/kui-base-list.jsp"%>
		<title>单位设备信息</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">

		<!--标准mui.css-->
		<link rel="stylesheet" href="app/weixin/research/css/mui.min.css">
		<!--App自定义的css-->
		<link rel="stylesheet" type="text/css" href="app/weixin/research/css/app.css"/>
		<script type="text/javascript">
			function deviceInsp(id){
				window.location.href= $("base").attr("href")+"enterSearchAction/wSearchInspectionByDevice.do?device_id="+id;
				
			}
		</script>
	</head>
	<body>
		<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
			<h1 class="mui-title">单位设备信息</h1>
		</header>
		
		<div class="mui-content mui-scroll">
			<div class="mui-card">
				<ul class="mui-table-view mui-table-view-chevron">
					<li class="mui-table-view-cell">设备列表
						<div id="M_Toggle" class="mui-switch mui-active">
							<div class="mui-switch-handle"></div>
						</div>
					</li>
				<c:forEach var="insp" items="${deviceList}">
					<li class="mui-table-view-cell mui-collapse"><a class="mui-navigate-right" href="#">${insp.device_name}&nbsp;&nbsp;${insp.device_model}</a>
						<ul class="mui-table-view mui-table-view-chevron">
							<li class="mui-table-view-cell">
								<div class="mui-table">
					                <div class="mui-table-cell mui-col-xs-10">
					                    <label class="mui-ellipsis"><a href="##" id="ss">设备名称</a></label>
					                    <span style="display:block;float:right;">${insp.device_name}</span>
					                </div>
					            </div>
							</li>
							<li class="mui-table-view-cell">
								<div class="mui-table">
					                <div class="mui-table-cell mui-col-xs-10">
					                    <label class="mui-ellipsis"><a href="##" id="ss">设备注册代码</a></label>
					                    <span style="display:block;float:right;">${insp.device_registration_code}</span>
					                </div>
					            </div>
							</li>
							<li class="mui-table-view-cell">
								<div class="mui-table">
					                <div class="mui-table-cell mui-col-xs-10">
					                    <label class="mui-ellipsis"><a href="##" id="ss">使用登记证号</a></label>
					                    <span style="display:block;float:right;">${insp.registration_num}</span>
					                </div>
					            </div>
							</li>
							<li class="mui-table-view-cell">
								<div class="mui-table">
					                <div class="mui-table-cell mui-col-xs-10">
					                    <label class="mui-ellipsis"><a href="##" id="ss">设备型号</a></label>
					                    <span style="display:block;float:right;">${insp.device_model}</span>
					                </div>
					            </div>
							</li>
							<li class="mui-table-view-cell">
								<div class="mui-table">
					                <div class="mui-table-cell mui-col-xs-10">
					                    <label class="mui-ellipsis"><a href="##" id="ss">检验报告数量</a></label>
					                    <span style="display:block;float:right;">${insp.report_count}</span>
					                </div>
					            </div>
							</li>
							<li class="mui-table-view-cell">
								<div class="mui-table">
					                <div class="mui-table-cell mui-col-xs-10">
					                    <label class="mui-ellipsis"><a href="##" id="ss">详情</a></label>
					                    <span style="display:block;float:right;"><a href="javascript:deviceInsp('${insp.id}')"><p class="more">查看报告&gt;&gt;</p></a></span>
					                </div>
					            </div>
							</li>
						</ul>
					</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</body>
	<script src="app/weixin/research/js/mui.min.js"></script>
	<script>
		mui.init({
			swipeBack:true //启用右滑关闭功能
		});
		window.addEventListener('toggle', function(event) {
			if (event.target.id === 'M_Toggle') {
				var isActive = event.detail.isActive;
				var table = document.querySelector('.mui-table-view');
				var card = document.querySelector('.mui-card');
				if (isActive) {
					card.appendChild(table);
					card.style.display = '';
				} else {
					var content = document.querySelector('.mui-content');
					content.insertBefore(table, card);
					card.style.display = 'none';
				}
			}
		});
	</script>
</html>