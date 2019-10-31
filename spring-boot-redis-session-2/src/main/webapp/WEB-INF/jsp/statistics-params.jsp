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
		<link rel="stylesheet" href="/static/bootstrap-select/dist/css/bootstrap-select.css" type="text/css">
		<link rel="stylesheet" href="/static/bootstrap-table/bootstrap-table.css" type="text/css">
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
								统计
								<small>
									<i class="ace-icon fa fa-angle-double-right"></i>
									按各批次、科类、计划性质、计划类别、学校等统计计划数、专业数与录取数
								</small>
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<form id="formSearch" class="form-horizontal">
									<div class="form-group" style="margin-top:15px">
										<div class="row" style="margin-top:20px;">
											<label class="control-label col-lg-1"  for="year">年份</label>
											<div class="col-lg-3">
												<select class="selectpicker form-control" id="year">
													<option value=''></option>
												    <option value='2019'>2019</option>
												    <option value='2018'>2018</option>
												    <option value='2017'>2017</option>
												    <option value='2016'>2016</option>
												  </select>
											</div>
											<label class="control-label col-lg-1" for="pcdm">批次</label>
											<div class="col-lg-3">
												<select multiple class="selectpicker form-control" id="pcdm" data-live-search="true"></select>
											</div>
											<label class="control-label col-lg-1"  for="kldm">科类</label>
											<div class="col-lg-3">
												<select multiple class="selectpicker form-control" id="kldm" data-live-search="true"></select>
											</div>
										</div>
										<div class="row" style="margin-top:20px";>
											<label class="control-label col-lg-1"  for="jhxzdm">计划性质</label>
											<div class="col-lg-3">
												<select multiple class="selectpicker form-control" id="jhxzdm" data-live-search="true"></select>
											</div>
											<label class="control-label col-lg-1"  for="jhlbdm" >计划类别</label>
											<div class="col-lg-3">
												<select multiple class="selectpicker form-control" id="jhlbdm" data-live-search="true"></select>
											</div>
											<label class="control-label col-lg-1"  for="zklxdm">招考类型</label>
											<div class="col-lg-3">
												<select multiple class="selectpicker form-control" id="zklxdm" data-live-search="true"></select>
											</div>
										</div>
				                    </div>
				                </form>
	
							<!-- 	bootstrap-table begin -->
							<div class="panel-body" style="padding-bottom: 0px;">
								<div id="toolbar" class="btn-group">
									<button id="btn_add" type="button" class="btn btn-default"
										data-toggle="modal" data-target="#addModal">
										<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
									</button>
									<button id="btn_update" type="button" class="btn btn-default">
										<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>编辑
									</button>
									<button id="btn_delete" type="button" class="btn btn-default"
										onclick="BtchDeleteBook()">
										<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
									</button>
								</div>
								<!-- <button type="button" id="download" style="margin-left:50px" id="btn_download" class="btn btn-primary" onClick ="$('#myTable').tableExport({ type: 'excel', escape: 'false' })">数据导出</button> -->
								<table id="myTable"></table>
								<!-- 添加 -->
								<div class="modal fade" id="addModal" tabindex="-1"
									role="dialog" aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">&times;</button>
												<h4 class="modal-title" id="myModalLabel">添加记录</h4>
											</div>
											<div class="modal-body">
												<form role="form" action="javascript:void(0)" id="addForm">
													<div class="form-group">
														<input type="text" class="form-control" name="goodsName"
															placeholder="请输入商品名称">
													</div>
													<div class="form-group">
														<input type="text" class="form-control" name="goodsPrice"
															placeholder="请输入商品价格">
													</div>
													<div class="form-group">
														<input type="text" class="form-control" name="goodsType"
															placeholder="请输入商品型号">
													</div>
													<div class="form-group">
														<input type="text" class="form-control" name="statusId"
															placeholder="请输入商品状态">
													</div>
													<div class="form-group">
														<input type="text" class="form-control" name="goodsStore"
															placeholder="请输入商品数量">
													</div>

												</form>
												<div class="modal-footer">
													<button type="button" class="btn btn-default"
														data-dismiss="modal">取消</button>
													<button type="button" class="btn btn-primary"
														id="addRecord">提交</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- 	bootstrap-table end -->
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
		
		<script src="/static/bootstrap-select/js/bootstrap-select.js"></script>
		<script src="/static/bootstrap-table/bootstrap-table.js"></script>
		<script src="/static/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
		
		<script src="/static/tableExport/libs/FileSaver/FileSaver.min.js"></script>
		<script src="/static/tableExport/libs/js-xlsx/xlsx.core.min.js"></script>
		<script src="/static/tableExport/tableExport.min.js"></script>
		<script src="/static/bootstrap-select/dist/js/select-data.js"></script>
		<script type="text/javascript">
			$(function() {
				var itable = TableInit();//.初始化Table
				itable.Init();
	
				$("#year").bind("change", function() {
					getSelectData("/codeWeb/getPcCode",$("#pcdm"),{nf:this.value.substring(2)},"pcdm","pcmc",function(){$("#pcdm").selectpicker("refresh");});
					getSelectData("/codeWeb/getKlCode",$("#kldm"),{nf:this.value.substring(2)},"kldm","klmc",function(){$("#kldm").selectpicker("refresh");});
					getSelectData("/codeWeb/getJhxzCode",$("#jhxzdm"),{nf:this.value.substring(2)},"jhxzdm","jhxzmc",function(){$("#jhxzdm").selectpicker("refresh");});
					getSelectData("/codeWeb/getJhlbCode",$("#jhlbdm"),{nf:this.value.substring(2)},"jhlbdm","jhlbmc",function(){$("#jhlbdm").selectpicker("refresh");});
					getSelectData("/codeWeb/getZklxCode",$("#zklxdm"),{nf:this.value.substring(2)},"zklxdm","zklxmc",function(){$("#zklxdm").selectpicker("refresh");});
				})
				//导出
				<!--初始化插件-->
				$("#export").click(function() {
					$("#statistics").tableExport({
						type : "xlsx",
						escape : "false",
					});
				});
	
			})
			var TableInit = function() {
				var myTableInit = new Object();
				//.初始化Table
				myTableInit.Init = function() {
					$("#myTable").bootstrapTable({
						url : '/statisticsWeb/statisticsByParams.do',
						method : 'post',
						toolbar : '#toolbar',//工具列
						striped : true,//隔行换色
						cache : false,//禁用缓存
						pagination : true,//关闭分页
						showFooter : false,//是否显示列脚
						showPaginationSwitch : true,//是否显示 数据条数选择框
						sortable : false,//排序
						search : true,//启用搜索
						showFullscreen : true,//全屏按钮
						/* showToggle:true,//显示详细视图和列表 */
						showColumns : true,//是否显示 内容列下拉框
						showRefresh : true,//显示刷新按钮
						clickToSelect : true,//点击选中checkbox
						pageNumber : 1,//初始化加载第一页，默认第一页
						pageSize : 10, //每页的记录行数
						pageList : [ 5, 10, 15, 20, 25 ],//可选择的每页行数
						paginationPreText : "上一页",
						paginationNextText : "下一页",
						paginationFirstText : "First",
						paginationLastText : "Last",
	
						showExport : true, //是否显示导出按钮  
						buttonsAlign : "right", //按钮位置  
						exportTypes : [ 'excel', 'json', 'txt', 'csv', 'xml' ], //导出文件类型  
						Icons : 'glyphicon-export',
						columns : [ {
							title : "全选",
							field : "select",
							checkbox : true
						}, {
							field : 'yxdh',
							title : '院校代号',
							switchable : true
						}, {
							field : 'yxdm',
							title : '院校代码',
							switchable : true
						}, {
							field : 'yxmc',
							title : '院校名称',
							switchable : true
						}, {
							field : 'jhs',
							title : '计划数',
							switchable : true
						}, {
							field : 'jhzxs',
							title : '计划执行数',
							switchable : true,
							sortable : true
						}, {
							field : 'lqs',
							title : '录取数',
							switchable : true
						}, ],
	
					});
				};
	
				return myTableInit;
			};
			function isEmpty(str) {
				if (null == str || '' == str || 'null' == str) {
					return true;
				}
				return false;
			}
			function f_sum($obj) {
				var _sum = 0;
				$obj.each(function(i) {
					_sum += parseInt($(this).html() == '' ? 0 : $(this).html());
				})
				return _sum;
			}
			
		</script>
	</body>
</html>
