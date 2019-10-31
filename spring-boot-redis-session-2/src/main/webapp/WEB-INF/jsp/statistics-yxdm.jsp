<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>计划列表</title>

		<meta name="description" content="Dynamic tables and grids using jqGrid plugin" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<!-- bootstrap & fontawesome -->
		<link rel="stylesheet" href="/static/assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="/static/assets/font-awesome/4.5.0/css/font-awesome.min.css" />

		<!-- page specific plugin styles -->
		<link rel="stylesheet" href="/static/assets/css/jquery-ui.min.css" />
		<link rel="stylesheet" href="/static/assets/css/bootstrap-datepicker3.min.css" />
		<link rel="stylesheet" href="/static/assets/css/ui.jqgrid.min.css" />

		<!-- text fonts -->
		<link rel="stylesheet" href="/static/assets/css/fonts.googleapis.com.css" />

		<!-- ace styles -->
		<link rel="stylesheet" href="/static/assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />

		<!--[if lte IE 9]>
			<link rel="stylesheet" href="/static/assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
		<![endif]-->
		<link rel="stylesheet" href="/static/assets/css/ace-skins.min.css" />
		<link rel="stylesheet" href="/static/assets/css/ace-rtl.min.css" />

		<!--[if lte IE 9]>
		  <link rel="stylesheet" href="/static/assets/css/ace-ie.min.css" />
		<![endif]-->

		<!-- inline styles related to this page -->

		<!-- ace settings handler -->
		<script src="/static/assets/js/ace-extra.min.js"></script>

		<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

		<!--[if lte IE 8]>
		<script src="/static/assets/js/html5shiv.min.js"></script>
		<script src="/static/assets/js/respond.min.js"></script>
		<![endif]-->
		<link rel="stylesheet" href="/static/assets/css/bootstrapStyle/bootstrapStyle.css" type="text/css">
		<style>
		table thead tr th{
			text-align:center;
			line-height:100%;
		}
		table tr td{
			text-align:center;
			line-height:100%;
		}
		</style>
	</head>

	<body class="no-skin">
		<div id="navbar" class="navbar navbar-default          ace-save-state">
			<div class="navbar-container ace-save-state" id="navbar-container">
				<button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
					<span class="sr-only">Toggle sidebar</span>

					<span class="icon-bar"></span>

					<span class="icon-bar"></span>

					<span class="icon-bar"></span>
				</button>

				<div class="navbar-header pull-left">
					<a href="index.html" class="navbar-brand">
						<small>
							<i class="fa fa-leaf"></i>
							Ace Admin
						</small>
					</a>
				</div>

				<div class="navbar-buttons navbar-header pull-right" role="navigation">
					<ul class="nav ace-nav">
						<li class="grey dropdown-modal">
							<a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<i class="ace-icon fa fa-tasks"></i>
								<span class="badge badge-grey">4</span>
							</a>

							<ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
								<li class="dropdown-header">
									<i class="ace-icon fa fa-check"></i>
									4 Tasks to complete
								</li>

								<li class="dropdown-content">
									<ul class="dropdown-menu dropdown-navbar">
										<li>
											<a href="#">
												<div class="clearfix">
													<span class="pull-left">Software Update</span>
													<span class="pull-right">65%</span>
												</div>

												<div class="progress progress-mini">
													<div style="width:65%" class="progress-bar"></div>
												</div>
											</a>
										</li>

										<li>
											<a href="#">
												<div class="clearfix">
													<span class="pull-left">Hardware Upgrade</span>
													<span class="pull-right">35%</span>
												</div>

												<div class="progress progress-mini">
													<div style="width:35%" class="progress-bar progress-bar-danger"></div>
												</div>
											</a>
										</li>

										<li>
											<a href="#">
												<div class="clearfix">
													<span class="pull-left">Unit Testing</span>
													<span class="pull-right">15%</span>
												</div>

												<div class="progress progress-mini">
													<div style="width:15%" class="progress-bar progress-bar-warning"></div>
												</div>
											</a>
										</li>

										<li>
											<a href="#">
												<div class="clearfix">
													<span class="pull-left">Bug Fixes</span>
													<span class="pull-right">90%</span>
												</div>

												<div class="progress progress-mini progress-striped active">
													<div style="width:90%" class="progress-bar progress-bar-success"></div>
												</div>
											</a>
										</li>
									</ul>
								</li>

								<li class="dropdown-footer">
									<a href="#">
										See tasks with details
										<i class="ace-icon fa fa-arrow-right"></i>
									</a>
								</li>
							</ul>
						</li>

						<li class="purple dropdown-modal">
							<a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<i class="ace-icon fa fa-bell icon-animated-bell"></i>
								<span class="badge badge-important">8</span>
							</a>

							<ul class="dropdown-menu-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close">
								<li class="dropdown-header">
									<i class="ace-icon fa fa-exclamation-triangle"></i>
									8 Notifications
								</li>

								<li class="dropdown-content">
									<ul class="dropdown-menu dropdown-navbar navbar-pink">
										<li>
											<a href="#">
												<div class="clearfix">
													<span class="pull-left">
														<i class="btn btn-xs no-hover btn-pink fa fa-comment"></i>
														New Comments
													</span>
													<span class="pull-right badge badge-info">+12</span>
												</div>
											</a>
										</li>

										<li>
											<a href="#">
												<i class="btn btn-xs btn-primary fa fa-user"></i>
												Bob just signed up as an editor ...
											</a>
										</li>

										<li>
											<a href="#">
												<div class="clearfix">
													<span class="pull-left">
														<i class="btn btn-xs no-hover btn-success fa fa-shopping-cart"></i>
														New Orders
													</span>
													<span class="pull-right badge badge-success">+8</span>
												</div>
											</a>
										</li>

										<li>
											<a href="#">
												<div class="clearfix">
													<span class="pull-left">
														<i class="btn btn-xs no-hover btn-info fa fa-twitter"></i>
														Followers
													</span>
													<span class="pull-right badge badge-info">+11</span>
												</div>
											</a>
										</li>
									</ul>
								</li>

								<li class="dropdown-footer">
									<a href="#">
										See all notifications
										<i class="ace-icon fa fa-arrow-right"></i>
									</a>
								</li>
							</ul>
						</li>

						<li class="green dropdown-modal">
							<a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<i class="ace-icon fa fa-envelope icon-animated-vertical"></i>
								<span class="badge badge-success">5</span>
							</a>

							<ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
								<li class="dropdown-header">
									<i class="ace-icon fa fa-envelope-o"></i>
									5 Messages
								</li>

								<li class="dropdown-content">
									<ul class="dropdown-menu dropdown-navbar">
										<li>
											<a href="#" class="clearfix">
												<img src="/static/assets/images/avatars/avatar.png" class="msg-photo" alt="Alex's Avatar" />
												<span class="msg-body">
													<span class="msg-title">
														<span class="blue">Alex:</span>
														Ciao sociis natoque penatibus et auctor ...
													</span>

													<span class="msg-time">
														<i class="ace-icon fa fa-clock-o"></i>
														<span>a moment ago</span>
													</span>
												</span>
											</a>
										</li>

										<li>
											<a href="#" class="clearfix">
												<img src="/static/assets/images/avatars/avatar3.png" class="msg-photo" alt="Susan's Avatar" />
												<span class="msg-body">
													<span class="msg-title">
														<span class="blue">Susan:</span>
														Vestibulum id ligula porta felis euismod ...
													</span>

													<span class="msg-time">
														<i class="ace-icon fa fa-clock-o"></i>
														<span>20 minutes ago</span>
													</span>
												</span>
											</a>
										</li>

										<li>
											<a href="#" class="clearfix">
												<img src="/static/assets/images/avatars/avatar4.png" class="msg-photo" alt="Bob's Avatar" />
												<span class="msg-body">
													<span class="msg-title">
														<span class="blue">Bob:</span>
														Nullam quis risus eget urna mollis ornare ...
													</span>

													<span class="msg-time">
														<i class="ace-icon fa fa-clock-o"></i>
														<span>3:15 pm</span>
													</span>
												</span>
											</a>
										</li>

										<li>
											<a href="#" class="clearfix">
												<img src="/static/assets/images/avatars/avatar2.png" class="msg-photo" alt="Kate's Avatar" />
												<span class="msg-body">
													<span class="msg-title">
														<span class="blue">Kate:</span>
														Ciao sociis natoque eget urna mollis ornare ...
													</span>

													<span class="msg-time">
														<i class="ace-icon fa fa-clock-o"></i>
														<span>1:33 pm</span>
													</span>
												</span>
											</a>
										</li>

										<li>
											<a href="#" class="clearfix">
												<img src="/static/assets/images/avatars/avatar5.png" class="msg-photo" alt="Fred's Avatar" />
												<span class="msg-body">
													<span class="msg-title">
														<span class="blue">Fred:</span>
														Vestibulum id penatibus et auctor  ...
													</span>

													<span class="msg-time">
														<i class="ace-icon fa fa-clock-o"></i>
														<span>10:09 am</span>
													</span>
												</span>
											</a>
										</li>
									</ul>
								</li>

								<li class="dropdown-footer">
									<a href="inbox.html">
										See all messages
										<i class="ace-icon fa fa-arrow-right"></i>
									</a>
								</li>
							</ul>
						</li>

						<li class="light-blue dropdown-modal">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle">
								<img class="nav-user-photo" src="/static/assets/images/avatars/user.jpg" alt="Jason's Photo" />
								<span class="user-info">
									<small>Welcome,</small>
									Jason
								</span>

								<i class="ace-icon fa fa-caret-down"></i>
							</a>

							<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								<li>
									<a href="#">
										<i class="ace-icon fa fa-cog"></i>
										Settings
									</a>
								</li>

								<li>
									<a href="profile.html">
										<i class="ace-icon fa fa-user"></i>
										Profile
									</a>
								</li>

								<li class="divider"></li>

								<li>
									<a href="#">
										<i class="ace-icon fa fa-power-off"></i>
										Logout
									</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>
			</div><!-- /.navbar-container -->
		</div>

		<div class="main-container ace-save-state" id="main-container">
			<script type="text/javascript">
				try{ace.settings.loadState('main-container')}catch(e){}
			</script>

			<div id="sidebar" class="sidebar                  responsive                    ace-save-state">
				<script type="text/javascript">
					try{ace.settings.loadState('sidebar')}catch(e){}
				</script>

				<div class="sidebar-shortcuts" id="sidebar-shortcuts">
					<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
						<button class="btn btn-success">
							<i class="ace-icon fa fa-signal"></i>
						</button>

						<button class="btn btn-info">
							<i class="ace-icon fa fa-pencil"></i>
						</button>

						<button class="btn btn-warning">
							<i class="ace-icon fa fa-users"></i>
						</button>

						<button class="btn btn-danger">
							<i class="ace-icon fa fa-cogs"></i>
						</button>
					</div>

					<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
						<span class="btn btn-success"></span>

						<span class="btn btn-info"></span>

						<span class="btn btn-warning"></span>

						<span class="btn btn-danger"></span>
					</div>
				</div><!-- /.sidebar-shortcuts -->

				<ul id="mainMenu" class="nav nav-list"></ul>

				<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
					<i id="sidebar-toggle-icon" class="ace-icon fa fa-angle-double-left ace-save-state" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
				</div>
			</div>

			<div class="main-content">
				<div class="main-content-inner">
					<div class="breadcrumbs ace-save-state" id="breadcrumbs">
						<ul class="breadcrumb">
							<li>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="#">Home</a>
							</li>

							<li>
								<a href="#">招生计划</a>
							</li>
							<li class="active">计划列表</li>
						</ul><!-- /.breadcrumb -->

						<div class="nav-search" id="nav-search">
							<form class="form-search">
								<span class="input-icon">
									<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
									<i class="ace-icon fa fa-search nav-search-icon"></i>
								</span>
							</form>
						</div><!-- /.nav-search -->
					</div>

					<div class="page-content">
						<div class="ace-settings-container" id="ace-settings-container">
							<div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
								<i class="ace-icon fa fa-cog bigger-130"></i>
							</div>

							<div class="ace-settings-box clearfix" id="ace-settings-box">
								<div class="pull-left width-50">
									<div class="ace-settings-item">
										<div class="pull-left">
											<select id="skin-colorpicker" class="hide">
												<option data-skin="no-skin" value="#438EB9">#438EB9</option>
												<option data-skin="skin-1" value="#222A2D">#222A2D</option>
												<option data-skin="skin-2" value="#C6487E">#C6487E</option>
												<option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
											</select>
										</div>
										<span>&nbsp; Choose Skin</span>
									</div>

									<div class="ace-settings-item">
										<input type="checkbox" class="ace ace-checkbox-2 ace-save-state" id="ace-settings-navbar" autocomplete="off" />
										<label class="lbl" for="ace-settings-navbar"> Fixed Navbar</label>
									</div>

									<div class="ace-settings-item">
										<input type="checkbox" class="ace ace-checkbox-2 ace-save-state" id="ace-settings-sidebar" autocomplete="off" />
										<label class="lbl" for="ace-settings-sidebar"> Fixed Sidebar</label>
									</div>

									<div class="ace-settings-item">
										<input type="checkbox" class="ace ace-checkbox-2 ace-save-state" id="ace-settings-breadcrumbs" autocomplete="off" />
										<label class="lbl" for="ace-settings-breadcrumbs"> Fixed Breadcrumbs</label>
									</div>

									<div class="ace-settings-item">
										<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-rtl" autocomplete="off" />
										<label class="lbl" for="ace-settings-rtl"> Right To Left (rtl)</label>
									</div>

									<div class="ace-settings-item">
										<input type="checkbox" class="ace ace-checkbox-2 ace-save-state" id="ace-settings-add-container" autocomplete="off" />
										<label class="lbl" for="ace-settings-add-container">
											Inside
											<b>.container</b>
										</label>
									</div>
								</div><!-- /.pull-left -->

								<div class="pull-left width-50">
									<div class="ace-settings-item">
										<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-hover" autocomplete="off" />
										<label class="lbl" for="ace-settings-hover"> Submenu on Hover</label>
									</div>

									<div class="ace-settings-item">
										<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-compact" autocomplete="off" />
										<label class="lbl" for="ace-settings-compact"> Compact Sidebar</label>
									</div>

									<div class="ace-settings-item">
										<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-highlight" autocomplete="off" />
										<label class="lbl" for="ace-settings-highlight"> Alt. Active Item</label>
									</div>
								</div><!-- /.pull-left -->
							</div><!-- /.ace-settings-box -->
						</div><!-- /.ace-settings-container -->

						<div class="page-header">
							<h1>
								jqGrid
								<small>
									<i class="ace-icon fa fa-angle-double-right"></i>
									Dynamic tables and grids using jqGrid plugin
								</small>
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<form id="formSearch" class="form-horizontal">
				                    <div class="form-group" style="margin-top:15px">
				                        <label class="control-label col-sm-1" style="margin-left: 20px;" for="yxdm">院校代号</label>
				                        <div class="col-sm-2">
				                            <input type="text" class="form-control" id="yxdm" name="yxdm" placeholder="院校代码,逗号分隔">
				                        </div>
				                       <!--  <label class="control-label col-sm-1" style="width: 120px" for="yxmc">院校名称 </label>
				                        <div class="col-sm-2">
				                            <input type="text" class="form-control" id="yxmc" placeholder="请输入院校名称">
				                        </div> -->
				                        <div class="col-sm-1" style="text-align:center;">
				                            <button type="button"  id="find_btn" class="btn btn-primary">查询</button>
				                        </div>
				                        <div class="col-sm-1" style="text-align:center;">
				                            <button type="button"  id="export" class="btn btn-primary">导出</button>
				                        </div>
				                    </div>
				                </form>
								<!-- PAGE CONTENT BEGINS -->
								<div class="alert alert-info">
									<button class="close" data-dismiss="alert">
										<i class="ace-icon fa fa-times"></i>
									</button>
									<i class="ace-icon fa fa-hand-o-right"></i>
									<span id="message" >
									Please note that demo server is not configured to save the changes, 
									therefore you may see an error message.
									</span>
								</div>

								<!-- <table id="grid-table"></table> -->
								<table id="statistics" class="table table-striped">
								    <thead>
									    <tr>
									        <th rowspan="2">类别</th>
									        <th rowspan="2">科类</th>
									        <th colspan="3">2018年</th>
									        <th colspan="3">2017年</th>
									        <th colspan="3">2016年</th>
									    </tr>
									    <tr>
									        <th>计划数</th>
									        <th>录取数</th>
									        <th>调档线</th>
									        <th>计划数</th>
									        <th>录取数</th>
									        <th>调档线</th>
									        <th>计划数</th>
									        <th>录取数</th>
									        <th>调档线</th>
									    </tr>
								    </thead>
								    <tbody></tbody>
								</table>

								<!-- <div id="grid-pager"></div> -->
								
								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div>
			</div><!-- /.main-content -->

			<div class="footer">
				<div class="footer-inner">
					<div class="footer-content">
						<span class="bigger-120">
							<span class="blue bolder">Ace</span>
							Application &copy; 2013-2014
						</span>

						&nbsp; &nbsp;
						<span class="action-buttons">
							<a href="#">
								<i class="ace-icon fa fa-twitter-square light-blue bigger-150"></i>
							</a>

							<a href="#">
								<i class="ace-icon fa fa-facebook-square text-primary bigger-150"></i>
							</a>

							<a href="#">
								<i class="ace-icon fa fa-rss-square orange bigger-150"></i>
							</a>
						</span>
					</div>
				</div>
			</div>

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->

		<!-- basic scripts -->

		<!--[if !IE]> -->
		<script src="/static/assets/js/jquery-2.1.4.min.js"></script>
		<!-- <![endif]-->

		<!--[if IE]>
			<script src="/static/assets/js/jquery-1.11.3.min.js"></script>
		<![endif]-->

		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='/static/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="/static/assets/js/bootstrap.min.js"></script>
		<script src="/static/assets/js/menu.js"></script>
		<!-- ace scripts -->
		<script src="/static/assets/js/ace-elements.min.js"></script>
		<script src="/static/assets/js/ace.min.js"></script>
		<script src="/static/tableExport/libs/FileSaver/FileSaver.min.js"></script>
		<script src="/static/tableExport/libs/js-xlsx/xlsx.core.min.js"></script>
		<script src="/static/tableExport/tableExport.min.js"></script>
		<script type="text/javascript">
			$(function(){
				$('#find_btn').click(function(){
					$("#message").html("");
					var yxdm = $("#yxdm").val();
					//验证
					var reg = /^[0-9]{5,5}$/;
					var yxdms = yxdm.split(",");
					$.each(yxdms,function(i){
						if(!reg.test(yxdms[i])){
							$("#message").html(yxdms[i]+"格式不正确，注意院校代码为5位数字字符，代码之间使用英文逗号分隔！");
							return;
						}
					})
					$.post("/statisticsWeb/statisticsByYxdmData",{'yxdm':yxdm},function(res){
						if(res.success){
							var data = res.data;
							if(res.notExistYxdm.length>0){
								$("#message").html("院校代码 "+res.notExistYxdm.join(",")+" 没数据，请检查！");
							}
							$("#statistics tbody").empty();
							for(var i=0;i<data.length;i++){
								var row = data[i];
								//alert( typeof $("#statistics tbody tr."+row.yxdh)[0]);
								if(!$("#statistics tbody tr#"+row.yxdh)[0]){
									$("#statistics tbody").append("<tr id='"+row.yxdh+"' style='background-color:#ccc'><td>"+row.yxdm+"</td><td colspan='10'>"+row.yxdh+"&nbsp;&nbsp;&nbsp;&nbsp;"+row.yxmc+"</td></tr>");
									$("#statistics tbody").append("<tr class='sum "+row.yxdh+"' style='background-color:#ccc'><td>合计</td><td></td>"+
											"<td class='sum_jh18'></td><td class='sum_lq18'></td><td></td>"+
											"<td class='sum_jh17'></td><td class='sum_lq17'></td><td></td>"+
											"<td class='sum_jh16'></td><td class='sum_lq16'></td><td></td></tr>"); 
								} 
								$("#statistics tbody tr#"+row.yxdh).after(
										"<tr class='data "+row.yxdh+"'><td>"+row.pcmc+
										"</td><td>"+row.kldm+row.klmc+
										"</td><td class='jh18'>"+ (isEmpty(row.jhs18)?"":row.jhs18)+
										"</td><td class='lq18'>"+ (isEmpty(row.lqs18)?"":row.lqs18)+
										"</td><td>"+ (isEmpty(row.ddx18)?"":row.ddx18)+
										"</td><td class='jh17'>"+ (isEmpty(row.jhs17)?"":row.jhs17)+
										"</td><td class='lq17'>"+ (isEmpty(row.lqs17)?"":row.lqs17)+
										"</td><td>"+ (isEmpty(row.ddx17)?"":row.ddx17)+
										"</td><td class='jh16'>"+ (isEmpty(row.jhs16)?"":row.jhs16)+
										"</td><td class='lq16'>"+ (isEmpty(row.lqs16)?"":row.lqs16)+
										"</td><td>"+ (isEmpty(row.ddx16)?"":row.ddx16)+
										"</td></tr>"); 
								
							}
							//合计
							var sum_objs = $("#statistics tbody tr.sum").get();
							$.each(sum_objs,function(i){
								var clss = $(sum_objs[i]).attr('class').split(" ");
								var index = (clss[0].trim() == 'sum' ? clss[1].trim():clss[0].trim());
								var $jh18 = $("#statistics tbody tr.data."+index).find("td.jh18");
								var sum_jh18 = f_sum($jh18);
								var $jh17 = $("#statistics tbody tr.data."+index).find("td.jh17");
								var sum_jh17 = f_sum($jh17);
								var $jh16 = $("#statistics tbody tr.data."+index).find("td.jh16");
								var sum_jh16 = f_sum($jh16);
								var $lq18 = $("#statistics tbody tr.data."+index).find("td.lq18");
								var sum_lq18 = f_sum($lq18);
								var $lq17 = $("#statistics tbody tr.data."+index).find("td.lq17");
								var sum_lq17 = f_sum($lq17);
								var $lq16 = $("#statistics tbody tr.data."+index).find("td.lq16");
								var sum_lq16 = f_sum($lq16);
								$(sum_objs[i]).find("td.sum_jh18").html(sum_jh18);
								$(sum_objs[i]).find("td.sum_jh17").html(sum_jh17);
								$(sum_objs[i]).find("td.sum_jh16").html(sum_jh16);
								$(sum_objs[i]).find("td.sum_lq18").html(sum_lq18);
								$(sum_objs[i]).find("td.sum_lq17").html(sum_lq17);
								$(sum_objs[i]).find("td.sum_lq16").html(sum_lq16);
							});
						}
					})
				});
				
				//导出
				<!--初始化插件-->
				$("#export").click(function(){
				    $("#statistics").tableExport({
				        type:"xlsx",
				        escape:"false",
				    });
				});
				
			})
			function isEmpty(str){
				if(null==str||''==str||'null'==str){
					return true;
				}
				return false;
			}
			function f_sum($obj){
				var _sum = 0;
				$obj.each(function(i){
					_sum += parseInt($(this).html()==''?0:$(this).html());
				})
				return _sum;
			}
		</script>
	</body>
</html>
