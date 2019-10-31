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
		<link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css" />
		<link rel="stylesheet" href="/static/bootstrap/font-awesome/4.5.0/css/font-awesome.min.css" />

		<!-- page specific plugin styles -->
		<link rel="stylesheet" href="/static/bootstrap/css/jquery-ui.min.css" />
		<link rel="stylesheet" href="/static/bootstrap/css/bootstrap-datepicker3.min.css" />


		<!-- text fonts -->
		<link rel="stylesheet" href="/static/bootstrap/css/fonts.googleapis.com.css" />

		<!-- ace styles -->
		<link rel="stylesheet" href="/static/bootstrap/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />

		<!--[if lte IE 9]>
			<link rel="stylesheet" href="/static/bootstrap/css/ace-part2.min.css" class="ace-main-stylesheet" />
		<![endif]-->
		<link rel="stylesheet" href="/static/bootstrap/css/ace-skins.min.css" />
		<link rel="stylesheet" href="/static/bootstrap/css/ace-rtl.min.css" />

		<!--[if lte IE 9]>
		  <link rel="stylesheet" href="/static/bootstrap/css/ace-ie.min.css" />
		<![endif]-->

		<!-- inline styles related to this page -->

		<!-- ace settings handler -->
		<script src="/static/bootstrap/js/ace-extra.min.js"></script>

		<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

		<!--[if lte IE 8]>
		<script src="/static/bootstrap/js/html5shiv.min.js"></script>
		<script src="/static/bootstrap/js/respond.min.js"></script>
		<![endif]-->
		<link rel="stylesheet" href="/static/bootstrap/css/bootstrapStyle/bootstrapStyle.css" type="text/css"/>
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
												<img src="/static/bootstrap/images/avatars/avatar.png" class="msg-photo" alt="Alex's Avatar" />
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
												<img src="/static/bootstrap/images/avatars/avatar3.png" class="msg-photo" alt="Susan's Avatar" />
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
												<img src="/static/bootstrap/images/avatars/avatar4.png" class="msg-photo" alt="Bob's Avatar" />
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
												<img src="/static/bootstrap/images/avatars/avatar2.png" class="msg-photo" alt="Kate's Avatar" />
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
												<img src="/static/bootstrap/images/avatars/avatar5.png" class="msg-photo" alt="Fred's Avatar" />
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
								<img class="nav-user-photo" src="/static/bootstrap/images/avatars/user.jpg" alt="Jason's Photo" />
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
							<h1>计划编制<small><i class="ace-icon fa fa-angle-double-right"></i>修改审核</small></h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- 	bootstrap-table begin -->
								<div class="panel-body" style="padding-bottom: 0px;">
									<table id="myTable"></table>
									<div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-hidden="true">
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
																<label class="control-label col-lg-1" for="bxdd">办学地点</label>
																<div class="col-lg-5">
																	<input type="text" class="form-control" id="bxdd"  name="bxdd" readonly="readonly"/>
																</div>
																<label class="control-label col-lg-1"  for="sfbz">收费标准</label>
																<div class="col-lg-5">
																	<input type="text" class="form-control" id="sfbz"  name="sfbz"  readonly="readonly"/>
																</div>
															</div>
														</div>
														<div class="form-group">
															<div class="row" style="margin-top:20px;">
																<label class="control-label col-lg-1"  for="zkfx">招考方向</label>
																<div class="col-lg-5">
																	<input type="text" class="form-control" id="zkfx" name="zkfx" readonly="readonly"/>
																</div>
																<label class="control-label col-lg-1" for="bhzy">包含专业</label>
																<div class="col-lg-5">
																	<textarea rows="3" class="form-control" id="bhzy"  name="bhzy" readonly="readonly"></textarea>
																</div>
												
															</div>
														</div>
														<div class="form-group">
															<div class="row" style="margin-top:20px;">
																<label class="control-label col-lg-1" for="zybz">专业备注</label>
																<div class="col-lg-5">
																	<textarea rows="3" class="form-control" id="zybz" name="zybz" readonly="readonly"></textarea>
																</div>
																<label class="control-label col-lg-1"  for="yxbz">院校备注</label>
																<div class="col-lg-5">
																	<textarea rows="3" class="form-control" id="yxbz" name="yxbz" readonly="readonly"></textarea>
																</div>
															</div>
														</div>
													</form>
													<div class="modal-footer">
														<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
														<button type="button" class="btn btn-primary" id="editRecord">保存</button>
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
		<script src="/static/bootstrap/js/jquery-2.1.4.min.js"></script>

		<!-- <![endif]-->

		<!--[if IE]>
			<script src="/static/bootstrap/js/jquery-1.11.3.min.js"></script>
		<![endif]-->
		
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='/static/bootstrap/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="/static/bootstrap/js/bootstrap.min.js"></script>
		<!-- ace scripts -->
		<script src="/static/bootstrap/js/ace-elements.min.js"></script>
		<script src="/static/bootstrap/js/ace.min.js"></script>
		<script src="/static/app/Menu.js"></script>
		
		<script src="/static/bootstrap-select/js/bootstrap-select.js"></script>
		<script src="/static/bootstrap-table/bootstrap-table.js"></script>
		<script src="/static/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
		
		<script src="/static/tableExport/libs/FileSaver/FileSaver.min.js"></script>
		<script src="/static/tableExport/libs/js-xlsx/xlsx.core.min.js"></script>
		<script src="/static/tableExport/tableExport.min.js"></script>
		<script src="/static/treeview/js/bootstrap-treeview.js"></script>
		<script src="/static/app/DateUtil.js"></script>
		<script src="/static/app/UuidUtil.js"></script>
		<script src="/static/app/FormUtil.js"></script>

		<!-- inline scripts related to this page -->
		<script type="text/javascript">
			var dict = {'zylbdm':<%=dict_zylb%>,'pcdm':<%=dict_pc%>,'kldm':<%=dict_kl%>,'jhxzdm':<%=dict_jhxz%>,'jhlbdm':<%=dict_jhlb%>,'zklxdm':<%=dict_zklx%>};
		 	var $table;
			$(function() {
				//表格数据
				InitMainTable();
			})
			//初始化表格
			function InitMainTable() {
				var queryUrl = '/planWeb/getAllApplyPlan';
				$table= $("#myTable").bootstrapTable({
					url : queryUrl,
					//method : 'post',
					//contentType : "application/x-www-form-urlencoded",
					//toolbar : '#toolbar',//工具列
					striped : true,//隔行换色
					cache : false,//禁用缓存
					pagination : true,//关闭分页
					sidePagination: "client",
					showFooter : false,//是否显示列脚
					showPaginationSwitch : true,//是否显示 数据条数选择框
					sortable : false,//排序
					search : true,//启用搜索
					searchOnEnterKey : true,//按enter键搜索
					showFullscreen : true,//全屏按钮
					showToggle:true,//显示详细视图和列表 
					showColumns : true,//是否显示 内容列下拉框
					showRefresh : true,//显示刷新按钮
					clickToSelect : true,//点击选中checkbox
					singleSelect:true,
					maintainSelected:true,
					pageNumber : 1,//初始化加载第一页，默认第一页
					pageSize : 15, //每页的记录行数
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
	                      temp.rnd = Math.random();
	                      return temp;
	                },
					columns : [
						/* {title : "全选",field : "select",checkbox : true},   */
						{
							field:'id',
							title: 'ID',
							formatter:actionFormatter
						},
						{field:'yxdh',title:'院校代号'},
						{field:'yxmc',title:'院校名称'},
						{field:'yxdz',title:'院校地址',visible:false},
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
						{
							field:'zylbdm',
							title:'专业类别',
							formatter:function(value,row,index){
								return g_value(dict['zylbdm'],value);
							}
						},
						{field:'sbzydh',title:'专业代号'},
						{field:'zszymc',title:'专业名称'},
						{field:'bhzy',title:'包含专业'},
						{field:'zkfx',title:'招考方向'},
						{field:'zybz',title:'专业备注'},
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
						if(row.checked_applies>0){
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
			
			var $subTable;
			function InitSubTable(index, row, $detail) {
				//关闭其他
				$detail.parent('tr').siblings('tr[data-index]').each(function(){
					var ind =$(this).attr('data-index');
					if(ind != index){
						$table.bootstrapTable('collapseRow',ind);
					}
				});
				//展开当前点击的行
		        var parentid = row.id;
		        var cur_table = $detail.html('<table></table>').find('table');
		        $subTable = $(cur_table).bootstrapTable({
		            url: '/planWeb/getPlanEditApply',
		           // method: 'get',
		            //contentType: 'application/json;charset=UTF-8',//这里我就加了个utf-8
		            dataType: 'json',
		            queryParams: { id: parentid,tim : Math.random()},
		            ajaxOptions: { id: parentid },
		            clickToSelect: true,
		            //height: 500,
		            detailView: false,//父子表
		            uniqueId: "id",
		            sidePagination: "client",
		            pageSize: 10,
		            pageList: [10, 25],
		            columns: [{
					                field: 'id',
					                title: '操作',
					                formatter:subTableActionFormatter
					            }, {
					                field: 'field',
					                title: '项目',
					                formatter:function(value,row,index){
					                	var items = [{id:'bxdd',text:'办学地点'},{id:'sfbz',text:'收费标准'},{id:'zkfx',text:'招考方向'},{id:'bhzy',text:'包含专业'},{id:'zybz',text:'专业备注'},{id:'yxbz',text:'院校备注'}];
					                	for(var i in items){
					                		if(row.field == items[i].id){
					                			return items[i].text;
					                		}
					                	}
					                }
					            }, {
					                field: 'oValue',
					                title: '旧值'
					            }, {
					                field: 'nValue',
					                title: '新值'
					            }, {
					                field: 'pValue',
					                title: '确认值'
					            }, {
					                field: 'status',
					                title: '状态',
					                formatter:function(value,row,index){
					                	if(value=='0'){
					                		return "待审核";
					                	}else if(value=='1'){
					                		return "已审核";
					                	}
					                }
					            }],
		            onClickCell: function(field, value, row, $element) {
		            	if(field == 'pValue'){
		            		$element.attr('contenteditable', true);
			                $element.blur(function() {
			                    let index = $element.parent().data('index');
			                    let tdValue = $element.html();

			                    saveData($subTable,index, field, tdValue);
			                })
		            	}
		            },
		            rowStyle:function(row,index){
						if(row.status=='1'){
							 return {
								 css: {'background-color':'#90EE90'}
							 };
						}else{
							return {
								 css: {}
							};
						}
					}
		        });
		    };
		    function saveData($table,index, field, value) {
		        $table.bootstrapTable('updateCell', {
		            index: index,       //行索引
		            field: field,       //列名
		            value: value        //cell值
		        })
		    }

			//修改数据
			function checkOk(row,index){
				var msg = "是否确认？";
				if (confirm(msg) == true) {
					$.ajax({
						url : "/planWeb/ckeckApply",
						type : "post",
						dataType : "json",
						data : row,
						success : function(data) {
							if(data.success){
								$subTable.bootstrapTable('refresh',{ query: {tim : Math.random()}});
								alert("审核成功！");
							}
						},
						error : function(data) {
							alert("错误"+data.msg);
						}
					});
				}
			}
			//编辑页面
			function detailView(row){
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
				$("#dataForm textarea").each(function(){
					var name = this.name;
					$(this).html(row[name]);
				});
                $("#modal").modal();
			}
			//操作按钮
			function actionFormatter(value, row, index) {
			   		return '<a class="detail" href="javascript:;" onclick="detailView('+JSON.stringify(row).replace(/"/g,'&quot;') +','+ index +')" data-toggle="tooltip" title="详情"><i class="glyphicon glyphicon-zoom-out"></i></a>';
			}
			//子表操作按钮
			function subTableActionFormatter(value, row, index) {
				if(row.status=='0'){
			   		return '<a class="update" href="javascript:;" onclick="checkOk('+JSON.stringify(row).replace(/"/g,'&quot;') +','+ index +')" data-toggle="tooltip" title="审核"><i class="glyphicon glyphicon-ok"></i></a>';
				}else{
					return "";
				}
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
