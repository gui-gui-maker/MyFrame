<%@page contentType="text/html;charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<base href="<%=basePath%>">
<title>质量管理</title>
<%@include file="/k/kui-base-list.jsp"%>
<link rel="stylesheet" type="text/css"
	href="app/gis/scjy/v3/css/stylegx.css" media="all" />
<link rel="stylesheet" type="text/css"
	href="app/gis/scjy/v3/css/animate.css" media="all" />
<!-- <script type="text/javascript"
	src="app/gis/scjy/v3/js/jquery-1.8.3.min.js"></script> -->
<!-- <script type="text/javascript" src="app/gis/scjy/v3/js/lhgdialog.min.js?self=true&skin=chrome"></script> -->
<script type="text/javascript" src="app/gis/scjy/v3/js/main.js"></script>
<script type="text/javascript" src="app/gis/scjy/v3/js/mainData.js"></script>
<script type="text/javascript" src="app/gis/scjy/v3/js/queryData.js"></script>
<script type="text/javascript">
(function(config){
    config['extendDrag'] = true; // 注意，此配置参数只能在这里使用全局配置，在调用窗口的传参数使用无效
    config['lock'] = true;
    config['fixed'] = true;
    config['okVal'] = 'Ok';
    config['cancelVal'] = 'Cancel';
    // [more..]
})($.dialog.setting);

</script>
<!--[if IE 8]>
            <link rel="stylesheet" type="text/css" href="app/gis/scjy/v3/css/style-ie8.css" media="all" />
        <![endif]-->
</head>
<body>
	<div id="sysMain" class="sysmain">
		<div class="m-area-top">
			<div class="m-a-tit">质量管理</div>
			<div class="m-top-l-img"></div>
			<div class="m-top-r-img"></div>
		</div>
		<div id="systemTitle" class="m-top-logo">
			<div class="m-top-logo-txt">
				<span id="systemTitleText" class="text1"> <span
					class="m-top-logo-img"><img
						src="app/gis/scjy/v3/images/tjy_lo.png"></span>
					四川省特检院智慧特检大数据-D3(测试版)
				</span>
			</div>
		</div>
		<!--中间主要内容区  start -->
		<div class="m-center2">
			<div class="map_point" style="left: 19.5%; top: 25%;">
				<div class="addnum" id="addnum0" style="animation-delay: 1s;">
					<span class="anum0" id="anum0">+1</span>
				</div>
			</div>
			<div class="map_point" style="left: 52.5%; top: 25%;">
				<div class="addnum" id="addnum1" style="animation-delay: 1s;">
					<span class="anum0" id="anum0">+1</span>
				</div>
			</div>
			<div class="map_point" style="left: 83.5%; top: 25%;">
				<div class="addnum" id="addnum2" style="animation-delay: 1s;">
					<span class="anum0" id="anum0">+1</span>
				</div>
			</div>
			<div class="map_point" style="left: 19.5%; top: 45%;">
				<div class="addnum" id="addnum3" style="animation-delay: 1s;">
					<span class="anum0" id="anum0">+1</span>
				</div>
			</div>
			<div class="map_point" style="left: 52.5%; top: 45%;">
				<div class="addnum" id="addnum4" style="animation-delay: 1s;">
					<span class="anum0" id="anum0">+1</span>
				</div>
			</div>
			<div class="map_point" style="left: 83.5%; top: 45%;">
				<div class="addnum" id="addnum5" style="animation-delay: 1s;">
					<span class="anum0" id="anum0">+1</span>
				</div>
			</div>
			<div class="m-center-wrap">


				<div class="half">
					<ul class="tjbox">
						<li><a
							href="javascript:showDig('任务书','/app/qualitymanage/task_allot_list.jsp')">
								<div class="item">
									<div class="item_tit">下达任务书</div>
									<div class="item_box">
										<span class="numbx" id="rws">14,170,810</span>
									</div>
								</div>
								<div class="borbg"></div>
								<div class="bor_ltopbg"></div>
								<div class="bor_rbotbg"></div>
								<div class="bor_botbg"></div>
						</a></li>
						<li><a
							href="javascript:showDig('内控到期','/app/report_nk/report_nk_list.jsp')">
								<div class="item">
									<div class="item_tit">内控到期报告</div>
									<div class="item_box">

										<span class="numbx" id="nkdq">921,360</span>

									</div>
								</div>
								<div class="borbg"></div>
								<div class="bor_ltopbg"></div>
								<div class="bor_rbotbg"></div>
								<div class="bor_botbg"></div>
						</a></li>
						<li><a
							href="javascript:showDig('不符合报告','/app/query/report_query_list.jsp')">
								<div class="item">
									<div class="item_tit">不符合报告</div>
									<div class="item_box">

										<span class="numbx" id="bhg">526</span>

									</div>
								</div>
								<div class="borbg"></div>
								<div class="bor_ltopbg"></div>
								<div class="bor_rbotbg"></div>
								<div class="bor_botbg"></div>
						</a></li>
						<li><a
							href="javascript:showDig('软件任务书','/app/func_task/func_task_list.jsp')">
								<div class="item">
									<div class="item_tit">软件任务书</div>
									<div class="item_box">
										<span class="numbx" id="rjrws">22,901</span>
									</div>
								</div>
								<div class="borbg"></div>
								<div class="bor_ltopbg"></div>
								<div class="bor_rbotbg"></div>
								<div class="bor_botbg"></div>
						</a></li>
						<li><a
							href="javascript:showDig('纠错报告','/app/report/report_error_record_list.jsp')">
								<div class="item">
									<div class="item_tit">纠错报告</div>
									<div class="item_box">
										<span class="numbx" id="jcbg">0</span>
									</div>
								</div>
								<div class="borbg"></div>
								<div class="bor_ltopbg"></div>
								<div class="bor_rbotbg"></div>
								<div class="bor_botbg"></div>
						</a></li>
						<li><a
							href="javascript:showDig('体系文件','/app/qualitymanage/qualityFiles_list.jsp?file_type=zltxfl')">
								<div class="item">
									<div class="item_tit">体系文件</div>
									<div class="item_box">
										<span class="numbx" id="txwj">1,727</span>
									</div>
								</div>
								<div class="borbg"></div>
								<div class="bor_ltopbg"></div>
								<div class="bor_rbotbg"></div>
								<div class="bor_botbg"></div>
						</a></li>
					</ul>
				</div>
				<div class="half mrt10">
					<div class="cxnews">
						<div class="tit">
							<span class="tname" id="tname">质量管理统计</span>
							<div class="serch">
								<div class="ser_cont">
									<input class="inpt" name="" type="text" id="query"
										value="输入报告书编号或关键字查询" style="color: #9C9A9C;"
										onfocus="if(this.value=='输入报告书编号或关键字查询'){this.value='';this.style.color = '#fff'};"
										onblur="if(this.value==''){this.value='输入报告书编号或关键字查询';this.style.color='#9C9A9C'};">
										<input name="" class="ser_bnt" type="button" value="搜一下"
										id="getReportNo">
								</div>
								<div class="serbg"></div>
							</div>
						</div>
						<div class="cxn_list" id="listShow">
							<div class="ta-list-cont" id="demohq_a" style="overflow: hidden;">
								<div id="demohq1" class="table-list1">
									<ul>
										<li>
											<div>
												<div>
													<span>锦江</span>
												</div>
											</div>
											<div>
												<div>
													<span>成都诚悦时代物业服务有限公司（时代广场）</span>
												</div>
											</div>
											<div>
												<div>
													<span>2017-12-02</span>
												</div>
											</div>
											<div>
												<div>
													<span><span style="color: #FFFF33">出具完成</span></span>
												</div>
											</div>
										</li>
										<li>
											<div>
												<div>
													<span>金牛区</span>
												</div>
											</div>
											<div>
												<div>
													<span>成都伊厦小商品市场有限公司（金牛之心荷花池广场）</span>
												</div>
											</div>
											<div>
												<div>
													<span>2017-12-02</span>
												</div>
											</div>
											<div>
												<div>
													<span><span style="color: #FFFF33">出具完成</span></span>
												</div>
											</div>
										</li>
										<li>
											<div>
												<div>
													<span>高新</span>
												</div>
											</div>
											<div>
												<div>
													<span>中国建筑一局(集团)有限公司</span>
												</div>
											</div>
											<div>
												<div>
													<span>2017-12-02</span>
												</div>
											</div>
											<div>
												<div>
													<span><span style="color: #FFFF33">出具完成</span></span>
												</div>
											</div>
										</li>
										<li>
											<div>
												<div>
													<span>金牛区</span>
												</div>
											</div>
											<div>
												<div>
													<span>成都和祥实业有限公司(蓝光中央天地广场)</span>
												</div>
											</div>
											<div>
												<div>
													<span>2017-12-02</span>
												</div>
											</div>
											<div>
												<div>
													<span><span style="color: #FFFF33">出具完成</span></span>
												</div>
											</div>
										</li>
										<li>
											<div>
												<div>
													<span>双流县</span>
												</div>
											</div>
											<div>
												<div>
													<span>成都锦毅房地产开发有限公司(优品道)</span>
												</div>
											</div>
											<div>
												<div>
													<span>2017-12-02</span>
												</div>
											</div>
											<div>
												<div>
													<span><span style="color: #33FF99">出具中</span></span>
												</div>
											</div>
										</li>
										<li>
											<div>
												<div>
													<span>锦江</span>
												</div>
											</div>
											<div>
												<div>
													<span>成都万润锦置业有限公司（万科第五城）</span>
												</div>
											</div>
											<div>
												<div>
													<span>2017-12-02</span>
												</div>
											</div>
											<div>
												<div>
													<span><span style="color: #FFFF33">出具完成</span></span>
												</div>
											</div>
										</li>
										<li>
											<div>
												<div>
													<span>锦江</span>
												</div>
											</div>
											<div>
												<div>
													<span>成都市城乡东锦房地产开发有限公司（绿岛筑）</span>
												</div>
											</div>
											<div>
												<div>
													<span>2017-12-02</span>
												</div>
											</div>
											<div>
												<div>
													<span><span style="color: #33FF99">出具中</span></span>
												</div>
											</div>
										</li>
										<li>
											<div>
												<div>
													<span>双流县</span>
												</div>
											</div>
											<div>
												<div>
													<span>成都和祥实业有限公司(蓝光中央天地广场)</span>
												</div>
											</div>
											<div>
												<div>
													<span>2017-12-02</span>
												</div>
											</div>
											<div>
												<div>
													<span><span style="color: #FFFF33">出具完成</span></span>
												</div>
											</div>
										</li>
										<li>
											<div>
												<div>
													<span>锦江</span>
												</div>
											</div>
											<div>
												<div>
													<span>成都城投地产有限公司（锦城尚苑）</span>
												</div>
											</div>
											<div>
												<div>
													<span>2017-12-02</span>
												</div>
											</div>
											<div>
												<div>
													<span><span style="color: #FFFF33">出具完成</span></span>
												</div>
											</div>
										</li>
										<li>
											<div>
												<div>
													<span>双流县</span>
												</div>
											</div>
											<div>
												<div>
													<span>成都和祥实业有限公司(蓝光中央天地广场)</span>
												</div>
											</div>
											<div>
												<div>
													<span>2017-12-02</span>
												</div>
											</div>
											<div>
												<div>
													<span><span style="color: #FFFF33">出具完成</span></span>
												</div>
											</div>
										</li>
									</ul>
								</div>
							</div>
						</div>
						<!--查询显示部分  -->
						<div class="cxn_list" id="area_warp">
							<div class="year_wrap">
								<ul class="year_list"></ul>
							</div>
						</div>
						<div class="boxbg"></div>
						<div class="box_ltopbg"></div>
						<div class="box_rbotbg"></div>
						<div class="box_botbg"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
