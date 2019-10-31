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
		<title>设备检验详情</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">

		<!--标准mui.css-->
		<link rel="stylesheet" href="app/weixin/research/css/mui.min.css">
		<!--App自定义的css-->
		<link rel="stylesheet" type="text/css" href="app/weixin/research/css/app.css"/>
	</head>
		<body>
			<header class="mui-bar mui-bar-nav">
				<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
				<h1 class="mui-title">设备检验详情</h1>
			</header>
			
			<div class="mui-content mui-scroll">
				<div class="mui-card">
					<ul class="mui-table-view mui-table-view-chevron">
						<li class="mui-table-view-cell">报告列表
							<div id="M_Toggle" class="mui-switch mui-active">
								<div class="mui-switch-handle"></div>
							</div>
						</li>
					<c:forEach var="insp" items="${inspList}">
						<li class="mui-table-view-cell mui-collapse"><a class="mui-navigate-right" href="#">报告书${insp.report_sn}</a>
							<ul class="mui-table-view mui-table-view-chevron">
								<li class="mui-table-view-cell">
									<div class="mui-table">
						                <div class="mui-table-cell mui-col-xs-10">
						                    <label class="mui-ellipsis"><a href="##" id="ss">报告书使用单位</a></label>
						                    <span style="display:block;float:right;">${insp.com_name}</span>
						                </div>
						            </div>
								</li>
								<li class="mui-table-view-cell">
									<div class="mui-table">
						                <div class="mui-table-cell mui-col-xs-10">
						                    <label class="mui-ellipsis"><a href="##" id="ss">报告书编号</a></label>
						                    <span style="display:block;float:right;">${insp.report_sn}</span>
						                </div>
						            </div>
								</li>
								<li class="mui-table-view-cell">
									<div class="mui-table">
						                <div class="mui-table-cell mui-col-xs-10">
						                    <label class="mui-ellipsis"><a href="##" id="ss">检验时间</a></label>
						                    <span style="display:block;float:right;">${insp.advance_time}</span>
						                </div>
						            </div>
								</li>
								<li class="mui-table-view-cell">
									<div class="mui-table">
						                <div class="mui-table-cell mui-col-xs-10">
						                    <label class="mui-ellipsis"><a href="##" id="ss">报告书名称</a></label>
						                    <span style="display:block;float:right;">${insp.report_name}</span>
						                </div>
						            </div>
								</li>
								<li class="mui-table-view-cell">
									<div class="mui-table">
						                <div class="mui-table-cell mui-col-xs-10">
						                    <label class="mui-ellipsis"><a href="##" id="ss">检验机构</a></label>
						                    <span style="display:block;float:right;">${insp.org_name}</span>
						                </div>
						            </div>
								</li>
								<li class="mui-table-view-cell">
									<div class="mui-table">
						                <div class="mui-table-cell mui-col-xs-10">
						                    <label class="mui-ellipsis"><a href="##" id="ss">参检人员</a></label>
						                     <span style="display:block;float:right;">${insp.check_op_name}</span>
						                </div>
						            </div>
								</li>
								<li class="mui-table-view-cell">
									<div class="mui-table">
						                <div class="mui-table-cell mui-col-xs-10">
						                    <label class="mui-ellipsis"><a href="##" id="ss">检验结论</a></label>
						                    <span style="display:block;float:right;">${insp.inspection_conclusion}</span>
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