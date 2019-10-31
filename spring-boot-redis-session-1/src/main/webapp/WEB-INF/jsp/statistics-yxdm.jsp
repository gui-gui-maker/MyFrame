<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>计划列表</title>

		<meta name="description" content="" />
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
		<link rel="stylesheet" href="/static/bootstrap/css/bootstrapStyle/bootstrapStyle.css" type="text/css">
		<link rel="stylesheet" href="/static/bootstrap-select/dist/css/bootstrap-select.css" type="text/css"/>
		<link rel="stylesheet" href="/static/bootstrap-table/bootstrap-table.css" type="text/css"/>
		<style>
			#search-group div.dropdown div.open div.bs-actionsbox button{
				margin:0;
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
									院校前三年计划、录取
								</small>
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
				                <div style="padding: 10px 15px 10px;">
								    <form id="formSearch" class="bs-example bs-example-form" role="form">
								    	<input type="hidden" id="currentMl" name="currentMl" value=""/>
								        <div class="row ">
									        <div class="col-lg-12">
								                <div id="search-group" class="input-group">
								                    <span class="input-group-addon">院校</span>
								                    <select id="search" class="selectpicker form-control" data-live-search="true" multiple data-actions-box="true"></select>
								                </div>
									        </div>
								        </div>
								    </form>
								</div>
								<table id="dataTable"></table>
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
		
		<script src="/static/bootstrap-select/js/bootstrap-select.js"></script>
		<script src="/static/bootstrap-table/bootstrap-table.js"></script>
		<script src="/static/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
		
		<script type="text/javascript" src="/static/tableExport/libs/FileSaver/FileSaver.min.js"></script>
		<script type="text/javascript" src="/static/tableExport/libs/js-xlsx/xlsx.core.min.js"></script>
		<script type="text/javascript" src="/static/tableExport/libs/jsPDF/jspdf.min.js"></script>
  		<script type="text/javascript" src="/static/tableExport/libs/jsPDF-AutoTable/jspdf.plugin.autotable.js"></script>
  		<script type="text/javascript" src="/static/tableExport/libs/es6-promise/es6-promise.auto.min.js"></script>
  		<script type="text/javascript" src="/static/tableExport/libs/html2canvas/html2canvas.min.js"></script>
		<script type="text/javascript" src="/static/tableExport/tableExport.min.js"></script>
		<script type="text/javascript" src="/static/bootstrap-table/extensions/export/bootstrap-table-export.js"></script>
		
		
		<script src="/static/app/DateUtil.js"></script>
		<script src="/static/app/FormUtil.js"></script>
		<script src="/static/app/UuidUtil.js"></script>
		<script src="/static/app/Menu.js"></script>
		<script type="text/javascript">
			var $table = null;
			$(function(){
				//初始化搜索框
				$.post("/codeWeb/getUniversityList",{nf:19},function(res){
					if(res.success){
						var data= res.data;
						$("#search").empty().append("<option value=''></option>");
						for ( var i in data) {
							$("#search").append("<option value='" + data[i]['yxmc'].substring(0,5) + "'>" + data[i]['yxdh'] + "-" + data[i]['yxmc'] + "</option>");
						}
						$("#search").selectpicker("refresh");
						$('#search').on('changed.bs.select', function (e, clickedIndex, isSelected, previousValue) {
							//更新表数据
							$table.bootstrapTable('refresh');//刷新Table，Bootstrap Table 会自动执行重新查询
						});
	
					}
				});
				
				InitMainTable() ;
				
			})
			//初始化表格
			function InitMainTable() {
				var queryUrl = '/statisticsWeb/statisticsByYxdmData?rnd=' + Math.random();
				$table= $("#dataTable").bootstrapTable({
					url : queryUrl,
					//method : 'post',
					//contentType : "application/x-www-form-urlencoded",
					toolbar : '#toolbar',//工具列
					striped : true,//隔行换色
					cache : false,//禁用缓存
					pagination : false,//关闭分页
					//sidePagination: "client",
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
					detailView: false,                  //是否显示父子表
					showExport : true, //是否显示导出按钮  
					buttonsAlign : "right", //按钮位置  
					exportTypes : [ 'excel', 'json', 'txt', 'csv', 'xml' ], //导出文件类型  
					Icons : 'glyphicon-export',
					sortable:true,
					queryParams : function (params) {
	                      //这里的键的名字和控制器的变量名必须一致，这边改动，控制器也需要改成一样的
	                      var temp = {   
	                          rows: params.limit,                         //页面大小
	                          page: (params.offset / params.limit) + 1,   //页码
	                          sort: params.sort,      //排序列名  
	                          sortOrder: params.order //排位命令（desc，asc） 
	                      }; 
	                      var yxs = $("#search").val();
	                      temp.yxdm= (yxs==null ? "" : yxs.join(","));
	                      return temp;
	                },
					columns : [ [{"title": "前三年计划统计表","halign":"center","align":"center","colspan": 15}],
	                			[
	                				{field: 'yxdh', title: "院校代号",valign:"middle",align:"center",rowspan: 2,sortable:true},
	                				{field: 'yxmc', title: "院校名称",valign:"middle",align:"center",rowspan: 2,sortable:true},
	                				{field: 'pcdm', title: "批次代码",valign:"middle",align:"center",rowspan: 2,sortable:true},
	                				{field: 'pcmc', title: "批次",valign:"middle",align:"center",rowspan: 2,sortable:true},
	                    			{field: 'kldm', title: "科类代码",valign:"middle", align:"center",  rowspan: 2 ,sortable:true},
	                    			{field: 'klmc', title: "科类",valign:"middle", align:"center",  rowspan: 2 ,sortable:true},
	                    			{title: "18年",valign:"middle",align:"center",colspan: 3},
	                    			{title: "17年",valign:"middle",align:"center",colspan: 3},
	                    			{title: "16年",valign:"middle",align:"center",colspan: 3} 
	                    			],
	                    		[
	                    			{field:'jhs18',title:'计划数'},
									{field:'lqs18',title:'录取数'},
									{field:'ddx18',title:'调档线'},
									{field:'jhs17',title:'计划数'},
									{field:'lqs17',title:'录取数'},
									{field:'ddx17',title:'调档线'},
									{field:'jhs16',title:'计划数'},
									{field:'lqs16',title:'录取数'},
									{field:'ddx16',title:'调档线'}
								]
						
					],
					onDblClickRow: function (row, $element) {
						//EditView(row);
					},
					onLoadSuccess: function (data) {
						
					},
					onLoadError: function () {
						//showTips("数据加载失败！");
					},

				});
			};
			
		</script>
	</body>
</html>
