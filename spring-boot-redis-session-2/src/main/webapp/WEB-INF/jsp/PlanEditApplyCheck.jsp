<%@page import="com.edu.bean.CodeKl"%>
<%@page import="com.edu.bean.CodeJhxz"%>
<%@page import="com.edu.bean.CodeJhlb"%>
<%@page import="com.edu.bean.CodeZklx"%>
<%@page import="com.edu.bean.CodePc"%>
<%@page import="com.edu.bean.CodeZylb"%>
<%@page import="com.edu.util.UserDictUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String dict_zylb = UserDictUtil.dict(CodeZylb.class, "zylbdm", "zylbmc","nf=19");
String dict_pc = UserDictUtil.dict(CodePc.class, "pcdm", "pcmc","nf=19");
String dict_kl = UserDictUtil.dict(CodeKl.class, "kldm","klmc","nf=19");
String dict_jhxz = UserDictUtil.dict(CodeJhxz.class, "jhxzdm","jhxzmc","nf=19");
String dict_jhlb = UserDictUtil.dict(CodeJhlb.class, "jhlbdm","jhlbmc","nf=19");
String dict_zklx = UserDictUtil.dict(CodeZklx.class, "zklxdm","zklxmc","nf=19");

%>
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
		<link rel="stylesheet" href="/static/assets/css/bootstrapStyle/bootstrapStyle.css" type="text/css"/>
		<link rel="stylesheet" href="/static/bootstrap-select/dist/css/bootstrap-select.css" type="text/css"/>
		<link rel="stylesheet" href="/static/bootstrap-table/bootstrap-table.css" type="text/css"/>
	</head>
	<body class="no-skin">
		<div id="navbar" class="navbar navbar-default  ace-save-state">
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
									<a href="/loginout">
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
							<h1>计划编制<small><i class="ace-icon fa fa-angle-double-right"></i>院校编制</small></h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-2">
								<h4>目录</h4>
								<div id="catalog"></div>
							</div>
							<div class="col-xs-10">
								<div style="padding: 10px 15px 10px;">
								    <form id="formSearch" class="bs-example bs-example-form" role="form">
								    	<input type="hidden" name="dyml" id="dyml" value=""/>
								        <div class="row ">
									        <div class="col-lg-12">
								                <div class="input-group">
								                    <span class="input-group-addon">院校</span>
								                    <select id="s-yxdh" class="selectpicker form-control" data-live-search="true"></select>
								                </div>
									        </div>
								        </div>
								    </form>
								</div>
								<!-- 	bootstrap-table begin -->
								<div class="panel-body" style="padding-bottom: 0px;">
									<div id="toolbar" class="btn-group">
										<button id="btn_update" type="button" class="btn btn-default" onclick="EditView()">
											<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改申请
										</button>
									</div>
									<table id="myTable"></table>
									<!-- 添加 -->
									<div class="modal fade" id="modal" tabindex="-1"
										role="dialog" aria-hidden="true">
										<div class="modal-dialog" style="width:900px;">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal"
														aria-hidden="true">&times;</button>
													<h4 class="modal-title" id="myModalLabel">修改申请</h4>
												</div>
												<div class="modal-body" >
													<form role="form" action="javascript:void(0)" id="dataForm">
														<input type='hidden' name='id' id='id'>
														<div class="form-group">
															<div class="row" style="margin-top:20px;">
																<label class="control-label col-lg-1"  for="yxdz">院校地址</label>
																<div class="col-lg-5">
																	<input class="form-control" id="yxdz" name="yxdz" readonly="readonly"/>
																</div>
																<label class="control-label col-lg-1"  for="yxdz1">新</label>
																<div class="col-lg-5">
																	<input class="form-control" id="yxdz1" name="yxdz1"/>
																</div>
															</div>
														</div>
														<div class="form-group">
															<div class="row" style="margin-top:20px;">
																<label class="control-label col-lg-1" for="bxdd">办学地点</label>
																<div class="col-lg-5">
																	<input type="text" class="form-control" id="bxdd"  name="bxdd" readonly="readonly"/>
																</div>
																<label class="control-label col-lg-1" for="bxdd1">新</label>
																<div class="col-lg-5">
																	<input type="text" class="form-control" id="bxdd1"  name="bxdd1"/>
																</div>
															</div>
														</div>
														<div class="form-group">
															<div class="row" style="margin-top:20px;">
																<label class="control-label col-lg-1"  for="sfbz">收费标准</label>
																<div class="col-lg-5">
																	<input type="text" class="form-control" id="sfbz"  name="sfbz"  readonly="readonly"/>
																</div>
																<label class="control-label col-lg-1"  for="sfbz1">新</label>
																<div class="col-lg-5">
																	<input type="text" class="form-control" id="sfbz1"  name="sfbz1"  />
																</div>
															</div>
														</div>
														<div class="form-group">
															<div class="row" style="margin-top:20px;">
																<label class="control-label col-lg-1"  for="zkfx">招考方向</label>
																<div class="col-lg-5">
																	<input type="text" class="form-control" id="zkfx" name="zkfx" readonly="readonly"/>
																</div>
																<label class="control-label col-lg-1"  for="zkfx1">新</label>
																<div class="col-lg-5">
																	<input type="text" class="form-control" id="zkfx1" name="zkfx1" />
																</div>
															</div>
														</div>
														<div class="form-group">
															<div class="row" style="margin-top:20px;">
																<label class="control-label col-lg-1" for="bhzy">包含专业</label>
																<div class="col-lg-5">
																	<textarea rows="3" class="form-control" id="bhzy"  name="bhzy" readonly="readonly"></textarea>
																</div>
																<label class="control-label col-lg-1" for="bhzy1">新</label>
																<div class="col-lg-5">
																	<textarea rows="3" class="form-control" id="bhzy1"  name="bhzy1"></textarea>
																</div>
															</div>
														</div>
														<div class="form-group">
															<div class="row" style="margin-top:20px;">
																<label class="control-label col-lg-1" for="zybz">专业备注</label>
																<div class="col-lg-5">
																	<textarea rows="3" class="form-control" id="zybz" name="zybz" readonly="readonly"></textarea>
																</div>
																<label class="control-label col-lg-1" for="zybz1">新</label>
																<div class="col-lg-5">
																	<textarea rows="3" class="form-control" id="zybz1" name="zybz1"></textarea>
																</div>
															</div>
														</div>
														<div class="form-group">
															<div class="row" style="margin-top:20px;">
																<label class="control-label col-lg-1"  for="yxbz">院校备注</label>
																<div class="col-lg-5">
																	<textarea rows="3" class="form-control" id="yxbz" name="yxbz" readonly="readonly"></textarea>
																</div>
																<label class="control-label col-lg-1"  for="yxbz1">新</label>
																<div class="col-lg-5">
																	<textarea rows="3" class="form-control" id="yxbz1" name="yxbz1"></textarea>
																</div>
															</div>
														</div>
														<div class="form-group">
															<div class="row" style="margin-top:20px;">
																<label class="control-label col-lg-1"  for="others">其他修改申请</label>
																<div class="col-lg-11">
																	<textarea rows="3" class="form-control" id="others" name="others"></textarea>
																</div>
															</div>
														</div>
													</form>
													<div class="modal-footer">
														<button type="button" class="btn btn-default"
															data-dismiss="modal">取消</button>
														<button type="button" class="btn btn-primary"
															id="editRecord">保存</button>
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
		<!-- ace scripts -->
		<script src="/static/assets/js/ace-elements.min.js"></script>
		<script src="/static/assets/js/ace.min.js"></script>
		<script src="/static/assets/js/menu.js"></script>
		
		<script src="/static/bootstrap-select/js/bootstrap-select.js"></script>
		<script src="/static/bootstrap-table/bootstrap-table.js"></script>
		<script src="/static/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
		
		<script src="/static/tableExport/libs/FileSaver/FileSaver.min.js"></script>
		<script src="/static/tableExport/libs/js-xlsx/xlsx.core.min.js"></script>
		<script src="/static/tableExport/tableExport.min.js"></script>
		<script src="/static/treeview/js/bootstrap-treeview.js"></script>
		<script src="/static/app/DictUtil.js"></script>
		<script src="/static/app/DateUtil.js"></script>
		<script src="/static/app/UuidUtil.js"></script>
		<script src="/static/app/FormUtil.js"></script>

		<!-- inline scripts related to this page -->
		<script type="text/javascript">
			var dict = {'zylbdm':<%=dict_zylb%>,'pcdm':<%=dict_pc%>,'kldm':<%=dict_kl%>,'jhxzdm':<%=dict_jhxz%>,'jhlbdm':<%=dict_jhlb%>,'zklxdm':<%=dict_zklx%>};
		 	var $table;
			$(function() {
				//初始化下拉框
				$("#dataForm select.needinit").each(function(){
					initSelectTag($(this),dict[this.name],function(data){
						//如果是插件需要刷新
					});
				});
				//初始化搜索框
				$.post("/codeWeb/getUniversityList",{nf:19},function(res){
					if(res.success){
						var data= res.data;
						$("#s-yxdh").empty().append("<option value=''></option>");
						for ( var i in data) {
							$("#s-yxdh").append("<option value='" + data[i]['yxdh'] + "'>" + data[i]['yxdh'] + "-" + data[i]['yxmc'] + "</option>");
						}
						$("#s-yxdh").selectpicker("refresh");
					}
					$("#s-yxdh").bind("change",function(){
						//设置dyml为空
						$("#dyml").val("");
						//更新表数据
						$table.bootstrapTable('refreshOptions',{pageNumber:1});//重置到第一页
						$table.bootstrapTable('refresh');//刷新Table，Bootstrap Table 会自动执行重新查询
						//更新目录
						$.post("/catalogWeb/listOneByYxdh",{yxdh:this.value},function(res){
							if(res.success){
			        			$('#catalog').treeview({
			        				data: res.data,
			        				onNodeSelected:nodeSelected
		        				});
			        		}
						});
					});
				});
				//目录树
				$.post("/catalogWeb/showFullCatalog",{},function(res){
	        		if(res.success){
	        			$('#catalog').treeview({
	        				data: res.data,
	        				onNodeSelected:nodeSelected
        				});
	        		}
	        	});
				//表格数据
				InitMainTable();
			})
			//初始化表格
			function InitMainTable() {
				var queryUrl = '/planWeb/list?rnd=' + Math.random();
				$table= $("#myTable").bootstrapTable({
					url : queryUrl,
					//method : 'post',
					//contentType : "application/x-www-form-urlencoded",
					toolbar : '#toolbar',//工具列
					striped : true,//隔行换色
					cache : false,//禁用缓存
					pagination : true,//关闭分页
					sidePagination: "server",
					showFooter : false,//是否显示列脚
					showPaginationSwitch : true,//是否显示 数据条数选择框
					sortable : false,//排序
					search : false,//启用搜索
					showFullscreen : true,//全屏按钮
					showToggle:true,//显示详细视图和列表 
					showColumns : true,//是否显示 内容列下拉框
					showRefresh : true,//显示刷新按钮
					clickToSelect : true,//点击选中checkbox
					singleSelect:true,
					maintainSelected:true,
					pageNumber : 1,//初始化加载第一页，默认第一页
					pageSize : 10, //每页的记录行数
					pageList : [ 10, 15, 20, 25, 30 ],//可选择的每页行数
					paginationPreText : "上一页",
					paginationNextText : "下一页",
					paginationFirstText : "First",
					paginationLastText : "Last",
					cardView: false,                    //是否显示详细视图
		            detailView: true,                   //是否显示父子表
					showExport : true, //是否显示导出按钮  
					buttonsAlign : "right", //按钮位置  
					exportTypes : [ 'excel', 'json', 'txt', 'csv', 'xml' ], //导出文件类型  
					Icons : 'glyphicon-export',
					queryParams : function (params) {
	                      //这里的键的名字和控制器的变量名必须一致，这边改动，控制器也需要改成一样的
	                      var temp = {   
	                          rows: params.limit,                         //页面大小
	                          page: (params.offset / params.limit) + 1,   //页码
	                          sort: params.sort,      //排序列名  
	                          sortOrder: params.order //排位命令（desc，asc） 
	                      };
	                      temp.yxdm = '${yxdm}';
	                      temp.yxdh = $("#s-yxdh").val();
	                      temp.dyml = $("#dyml").val();
	                      return temp;
	                },
					columns : [
						{title : "全选",field : "select",checkbox : true},  
						{
							field:'id',
							title: 'ID',
							visible:false
						},
						{field:'yxdh',title:'院校代号', visible:false},
						{field:'yxmc',title:'院校名称', visible:false},
						{field:'yxdz',title:'院校地址', visible:false},
						{field:'sbzydh',title:'专业代号'},
						{field:'zszymc',title:'专业名称'},
						{
							field:'zylbdm',
							title:'专业类别',
							formatter:function(value,row,index){
								return g_value(dict['zylbdm'],value);
							}
						},
						{field:'zkfx',title:'招考方向'},
						{
							field:'pcdm',
							title:'批次代码', 
							formatter:function(value,row,index){
								return g_value(dict['pcdm'],value);
							}
						},
						{field:'kldm',title:'科类代码', 
							formatter:function(value,row,index){
								return g_value(dict['kldm'],value);
							}
						},
						{
							field:'ccdm',
							title:'层次代码',
							formatter:function(value,row,index){
								if(value=='1'){
									return "1-本科";
								}else{
									return "2-高职（专科）";
								}
							}
						},
						{field:'jhxzdm',title:'计划性质',  
							formatter:function(value,row,index){
								return g_value(dict['jhxzdm'],value);
							}
						},
						{field:'jhlbdm',title:'计划类别', 
							formatter:function(value,row,index){
								return g_value(dict['jhlbdm'],value);
							}
						},
						{field:'zklxdm',title:'招考类型',  
							formatter:function(value,row,index){
								return g_value(dict['zklxdm'],value);
							}
						},
						{field:'zsjhs',title:'计划数'},
						{field:'status',titile:'状态',visible:false,formatter:function(){
							if(value=='0'){
								return "待审核";
							}else{
								return '审核结束';
							}
						}}
					],
					rowStyle:function(row,index){
						if(row.status==0){
							 return {
								 css: {'background-color':'orange'}
							 };
						}else{
							return {
								 css: {}
							 };
						}
					},
					onDblClickRow: function (row, $element) {
						//EditView(row);
					},
					onLoadSuccess: function (data) {
						
					},
					onLoadError: function () {
						//showTips("数据加载失败！");
					},
					onExpandRow:InitSubTable
				});
			};
			function InitSubTable(index, row, $detail) {
		        var parentid = row.id;
		        var cur_table = $detail.html('<table></table>').find('table');
		        $(cur_table).bootstrapTable({
		            url: '/planWeb/getPlanEditApply',
		           // method: 'get',
		            //contentType: 'application/json;charset=UTF-8',//这里我就加了个utf-8
		            dataType: 'json',
		            queryParams: { id: parentid },
		            ajaxOptions: { id: parentid },
		            clickToSelect: true,
		            //height: 500,
		            detailView: false,//父子表
		            uniqueId: "id",
		            sidePagination: "client",
		            pageSize: 10,
		            pageList: [10, 25],
		            columns: [{
		                checkbox: true
		            }, {
		                field: 'id',
		                title: '操作',
		                formatter:subTableActionFormatter
		            }, {
		                field: 'field',
		                title: '项目'
		            }, {
		                field: 'oValue',
		                title: '旧值'
		            }, {
		                field: 'nValue',
		                title: '新值'
		            }, {
		                field: 'otherApply',
		                title: '其他'
		            }, {
		                field: 'status',
		                title: '状态',
		                formatter:function(value,row,index){
		                	if(value=='0'){
		                		return "待审核";
		                	}else if(value=='1'){
		                		return "已审核";
		                	}else if(value=='2'){
		                		return '审核不通过'
		                	}else if(value=="3"){
		                		return "院校已同意";
		                	}else{
		                		return "院校不同意";
		                	}
		                }
		            }]
		        });
		    };

			//编辑页面
			function EditView(row){
				if(!row){
					var rows = $table.bootstrapTable('getSelections');
					if(rows.length != 1){
						alert("请选择1行数据");
						return;
					}
					row=rows[0];
				}
				$("#dataForm input").each(function(){
					$(this).val(row[this.name]);
				});
				$("#dataForm select").each(function(){
					var name = this.name;
					$(this).val(row[name]);
				});
                $("#modal").modal();
			}
			//修改数据
			$("#editRecord").click(function() {
				var formData = $("#dataForm").serializeObject();
				var data = {};
				$.each(formData,function(k,v){
					if(k=="id"){
						data['id'] = v;
						return;
					}else if(k=="others"){
						if(v != ""){
							data['others'] = v;
						}
						return;
					}else{
						var _k = k+"1";
						var reg = /(\d+)$/g;
		                //匹配正则获取小分组捕获结果
		                var result = reg.exec(k);
						if(!result){
							if(formData[_k]!="" && v != formData[_k]){
								data[k]={oValue:v,nValue:formData[_k]};
							}
						}
					}
					
				})
				$.ajax({
					url : "/planWeb/editApply",
					dataType : "json",
					type : "post",
					data : {'apply':JSON.stringify(data)},
					success : function(data) {
						if (data.success) {
							$('#modal').modal('hide');
							$table.bootstrapTable('refresh');
							alert("申请成功");
						} else {
							alert("申请失败"+data.msg);
						}
					},
					error : function(data) {
						alert("错误"+data.msg);
					}
				}); 
			});
			
			//目录被选中绑定函数
			function nodeSelected(event,node){
				if(node.sflb==1){
					$("#dyml").val(node.id);
					$table.bootstrapTable('refresh');//刷新Table，Bootstrap Table 会自动执行重新查询
				}
			}
			//表格操作按钮
			function actionFormatter(value, row, index) {
				      return [
				          '<a class="update" href="javascript:;" onclick="EditView('+JSON.stringify(row).replace(/"/g,'&quot;') +')" data-toggle="tooltip" title="Edit"><i class="glyphicon glyphicon-edit"></i></a>　'
				     ].join('');
			}
			//子表操作按钮
			function subTableActionFormatter(value, row, index) {
			      return [
			          '<a class="update" href="javascript:;" onclick="subEdit('+JSON.stringify(row).replace(/"/g,'&quot;') +')" data-toggle="tooltip" title="Edit"><i class="glyphicon glyphicon-edit"></i></a>　'
			     ].join('');
			}
			function g_value(data,key){
	        	for(var i=0;i<data.length;i++){
	        		if(key==data[i].key){
	        			return key+ "-" +data[i].value;
	        		}
	        	}
	        }
		</script>
	</body>
</html>
