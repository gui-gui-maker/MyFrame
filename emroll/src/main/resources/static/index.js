	var _menus = {
		"menus" : [ {
			"menuid" : "1",
			"icon" : "icon-sys",
			"menuname" : "系统基本设置",
			"menus" : [ {
				"menuid" : "12",
				"menuname" : "Angel工作室官方",
				"icon" : "icon-add",
				"url" : "http://www.angelasp.com",
				"child" : [ {
					"menuid" : "140",
					"menuname" : "权限设置",
					"icon" : "icon-role",
					"url" : "1.jpg"
				}, {
					"menuid" : "150",
					"menuname" : "权限设置 3",
					"icon" : "icon-set",
					"url" : "1.jpg"
				} ]
			}, {
				"menuid" : "13",
				"menuname" : "用户管理",
				"icon" : "icon-users",
				"url" : "2.jpg",
				"child" : [ {
					"menuid" : "141",
					"menuname" : "角色管理 3",
					"icon" : "icon-role",
					"url" : "3.jpg"
				}, {
					"menuid" : "151",
					"menuname" : "权限设置 3",
					"icon" : "icon-set",
					"url" : "demo.html"
				} ]
			}, {
				"menuid" : "14",
				"menuname" : "CRM客户关系管理系统",
				"icon" : "icon-role",
				"url" : "http://www.angelasp.com",
				"child" : [ {
					"menuid" : "142",
					"menuname" : "角色管理 3",
					"icon" : "icon-role",
					"url" : "demo2.html"
				}, {
					"menuid" : "152",
					"menuname" : "权限设置 3",
					"icon" : "icon-set",
					"url" : "demo.html"
				} ]
			}, {
				"menuid" : "15",
				"menuname" : "CRM客户关系管理系统",
				"icon" : "icon-set",
				"url" : "http://www.angelasp.com",
				"child" : [ {
					"menuid" : "143",
					"menuname" : "角色管理 3",
					"icon" : "icon-role",
					"url" : "demo2.html"
				}, {
					"menuid" : "153",
					"menuname" : "权限设置 3",
					"icon" : "icon-set",
					"url" : "demo.html"
				} ]
			}, {
				"menuid" : "16",
				"menuname" : "系统日志",
				"icon" : "icon-log",
				"url" : "demo1.html",
				"child" : [ {
					"menuid" : "144",
					"menuname" : "角色管理 3",
					"icon" : "icon-role",
					"url" : "demo2.html"
				}, {
					"menuid" : "154",
					"menuname" : "权限设置 3",
					"icon" : "icon-set",
					"url" : "demo.html"
				} ]
			} ]
		}, {
			"menuid" : "8",
			"icon" : "icon-sys",
			"menuname" : "系统设置管理",
			"menus" : [ {
				"menuid" : "21",
				"menuname" : "员工列表",
				"icon" : "icon-nav",
				"url" : "demo.html"
			}, {
				"menuid" : "22",
				"menuname" : "视频监控",
				"icon" : "icon-nav",
				"url" : "demo1.html",
				"child" : [ {
					"menuid" : "221",
					"menuname" : "员工列表 3",
					"icon" : "icon-nav",
					"url" : "demo.html"
				}, {
					"menuid" : "222",
					"menuname" : "视频监控 3",
					"icon" : "icon-nav",
					"url" : "demo1.html"
				} ]
			} ]
		}, {
			"menuid" : "10",
			"icon" : "icon-sys",
			"menuname" : "统计分析",
			"menus" : [ {
				"menuid" : "21",
				"menuname" : "区/县领用情况",
				"icon" : "icon-nav",
				"url" : "demo.html"
			}, {
				"menuid" : "22",
				"menuname" : "视频监控",
				"icon" : "icon-nav",
				"url" : "demo1.html",
				"child" : [ {
					"menuid" : "221",
					"menuname" : "员工列表 3",
					"icon" : "icon-nav",
					"url" : "demo.html"
				}, {
					"menuid" : "222",
					"menuname" : "视频监控 3",
					"icon" : "icon-nav",
					"url" : "demo1.html"
				} ]
			} ]
		}, {
			"menuid" : "56",
			"icon" : "icon-sys",
			"menuname" : "信息管理",
			"menus" : [ {
				"menuid" : "31",
				"menuname" : "专业列表",
				"icon" : "icon-nav",
				"url" : "enroll/toProfessionList"
			}]
			} ]
		} ]
	};

	//修改密码
	function serverLogin() {
		var $newpass = $('#txtNewPass');
		var $rePass = $('#txtRePass');

		if ($newpass.val() == '') {
			msgShow('系统提示', '请输入密码！', 'warning');
			return false;
		}
		if ($rePass.val() == '') {
			msgShow('系统提示', '请在一次输入密码！', 'warning');
			return false;
		}

		if ($newpass.val() != $rePass.val()) {
			msgShow('系统提示', '两次密码不一至！请重新输入', 'warning');
			return false;
		}

		$.post('/ajax/editpassword.ashx?newpass=' + $newpass.val(), function(
				msg) {
			msgShow('系统提示', '恭喜，密码修改成功！<br>您的新密码为：' + msg, 'info');
			$newpass.val('');
			$rePass.val('');
			close();
		})

	}

	$(function() {
		//init
		if(true){
			//没有登陆
			$('#loginWin').window('open');
		}else{
			//已登陆
			$('#loginWin').window('close');
		}
		$('#passWin').window('close');

		$('#editpass').click(function() {
			$('#passWin').window('open');
		});

		$('#btnEp').click(function() {
			serverLogin();
		})

		$('#btnCancel').click(function() {
			closePwd();
		})

		$('#loginOut').click(function() {
			$.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {
				if (r) {
					location.href = '/ajax/loginout.ashx';
				}
			});
		})
	});