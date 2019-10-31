<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/k/kui-base-list.jsp" %>
<title></title>
<script test="text/javascript">
	/*$(function () {//jQuery页面载入事件
		pageTitle({to:"#title",text:"页面标题",note:"页面介绍",icon:"k/kui/images/icons/32/places.png",show:true});
	});*/
</script>
</head>
<style type="text/css">
table, tr, td, th, img { margin: 0; padding: 0; border: 0; }
.tbl-tlbar { border: 1px solid #ccc; border-collapse: collapse; width: 99%; margin: 5px auto; }
.tbl-tlbar td, .tbl-tlbar th { border: 1px solid #ccc; padding: 3px 5px; text-align: center; }
.tbl-tlbar th { font-size: 14px; font-weight: bold; background:#eee; }
.red {color:#f00;}
</style>
<body>
<div class="item-tm">
	<div id="title"></div>
</div>
<div class="scroll-tm">
	<table border="0" cellspacing="0" cellpadding="0" class="tbl-tlbar">
		<tr>
			<th>图标 icon</th>
			<th>功能</th>
			<th>图标样式名称(icon属性)<br />
				k\kui\images\icons\16【例：<span class="red">.l-icon-add</span>】</th>
			<th>图标 icon</th>
			<th>功能</th>
			<th>图标样式名称(icon属性)<br />
				k\kui\images\icons\16【例：<span class="red">.l-icon-add</span>】</th>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/add.png" alt="新增" /></td>
			<td>新增</td>
			<td>add</td>
			<td><img src="k/kui/images/icons/16/plus.png" alt="加号，加上，添加" /></td>
			<td>加号，加上，添加</td>
			<td>plus</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/delete.png" alt="删除" /></td>
			<td>删除</td>
			<td>delete　/　del</td>
			<td><img src="k/kui/images/icons/16/modify.png" alt="修改" /></td>
			<td>修改</td>
			<td>modify</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/detail.png" alt="详情" /></td>
			<td>详情</td>
			<td>detail</td>
			<td><img src="k/kui/images/icons/16/copy.png" alt="复制" /></td>
			<td>复制</td>
			<td>copy</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/cut.png" alt="剪切" /></td>
			<td>剪切</td>
			<td>cut</td>
			<td><img src="k/kui/images/icons/16/cancel.png" alt="取消　关闭" /></td>
			<td>取消　/　关闭</td>
			<td>cancel　/　close</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/submit.png" alt="提交" /></td>
			<td>提交</td>
			<td>submit</td>
			<td><img src="k/kui/images/icons/16/submit_more.png" alt="批量提交" /></td>
			<td>批量提交</td>
			<td>submit-more</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/save.png" alt="保存" /></td>
			<td>保存</td>
			<td>save</td>
			<td><img src="k/kui/images/icons/16/save-disabled.png" alt="禁止保存" /></td>
			<td>禁止保存</td>
			<td>save-disabled</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/ok.png" alt="确定，确认" /></td>
			<td>确定，确认</td>
			<td>ok</td>
			<td><img src="k/kui/images/icons/16/print.png" alt="打印" /></td>
			<td>打印</td>
			<td>print</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/edit.png" alt="编辑" /></td>
			<td>编辑</td>
			<td>edit</td>
			<td><img src="k/kui/images/icons/16/prev.png" alt="上一页" /></td>
			<td>上一页</td>
			<td>prev</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/nav-prev.png" alt="上一个" /></td>
			<td>上一个</td>
			<td>nav-prev</td>
			<td><img src="k/kui/images/icons/16/nav-next.png" alt="下一个" /></td>
			<td>下一个</td>
			<td>nav-next</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/up.png" alt="向上" /></td>
			<td>向上</td>
			<td>up</td>
			<td><img src="k/kui/images/icons/16/right.png" alt="正确" /></td>
			<td>正确</td>
			<td>right</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/true.png" alt="真实，正确" /></td>
			<td>真实，正确</td>
			<td>true</td>
			<td><img src="k/kui/images/icons/16/check.png" alt="检查，核对，校对" /></td>
			<td>检查，核对，校对</td>
			<td>check</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/info-edit.png" alt="登记" /></td>
			<td>登记</td>
			<td>check-in</td>
			<td><img src="k/kui/images/icons/16/page_white_stack.png" alt="批量登记" /></td>
			<td>批量登记</td>
			<td>check-more</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/icon-down.png" alt="下拉选项，下拉箭头" /></td>
			<td>下拉选项，下拉箭头</td>
			<td>selectbox</td>
			<td><img src="k/kui/images/icons/16/accept.png" alt="启用" /></td>
			<td>启用</td>
			<td>accept</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/forbid.png" alt="禁用" /></td>
			<td>禁用</td>
			<td>forbid</td>
			<td><img src="k/kui/images/icons/16/photo.png" alt="照片" /></td>
			<td>照片</td>
			<td>photo</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/photograph.png" alt="拍照，照相" /></td>
			<td>拍照，照相</td>
			<td>photograph</td>
			<td><img src="k/kui/images/icons/16/database_link.png" alt="比对" /></td>
			<td>比对</td>
			<td>compare</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/error.png" alt="错误" /></td>
			<td>错误</td>
			<td>error</td>
			<td><img src="k/kui/images/icons/16/book.png" alt="图书，书籍" />　<img src="k/kui/images/icons/16/book2.png" alt="图书，书籍" /></td>
			<td>图书，书籍</td>
			<td>book　/　book-5</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/bluebook.png" alt="蓝皮书" /></td>
			<td>蓝皮书</td>
			<td>bluebook</td>
			<td><img src="k/kui/images/icons/16/bookpen.png" alt="编书，笔书" /></td>
			<td>编书，笔书</td>
			<td>bookpen</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/folder.png" alt="文件夹，目录" /></td>
			<td>文件夹，目录</td>
			<td>folder</td>
			<td><img src="k/kui/images/icons/16/total_plan_cost.png" alt="台账" /></td>
			<td>台账</td>
			<td>standing-book</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/pi_math.png" alt="公式" /></td>
			<td>公式</td>
			<td>formula</td>
			<td><img src="k/kui/images/icons/16/chart_pie.png" alt="计数，计算" /></td>
			<td>计数，计算</td>
			<td>count</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/user.png" alt="用户" /></td>
			<td>用户</td>
			<td>user</td>
			<td><img src="k/kui/images/icons/16/user-move.png" alt="传送" /></td>
			<td>传送</td>
			<td>userMove</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/lock.png" alt="用户锁定" />　<img src="k/kui/images/icons/16/lock-3.png" alt="锁定" /></td>
			<td>用户锁定　/　锁定</td>
			<td>userLock　/　lock</td>
			<td><img src="k/kui/images/icons/16/lock-3-open.png" alt="解除用户锁定，解锁" /></td>
			<td>解除用户锁定，解锁</td>
			<td>userUnlock</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/memeber.png" alt="会员" /></td>
			<td>会员</td>
			<td>memeber</td>
			<td><img src="k/kui/images/icons/16/role.png" alt="角色，任务" /></td>
			<td>角色，任务</td>
			<td>role</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/customers.png" alt="客户，顾客" /></td>
			<td>客户，顾客</td>
			<td>customers</td>
			<td><img src="k/kui/images/icons/16/myaccount.png" alt="我的帐户" /></td>
			<td>我的帐户</td>
			<td>myaccount</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/setting.png" alt="设置" />　<img src="k/kui/images/icons/16/settings.png" alt="设置" />　<img src="k/kui/images/icons/16/setting2.png" alt="设置" /></td>
			<td>设置</td>
			<td>setting　/　settings　/　setting3</td>
			<td><img src="k/kui/images/icons/16/org.png" alt="域名，站点" /></td>
			<td>域名，站点</td>
			<td>org</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/org-add.png" alt="添加域名，添加站点" /></td>
			<td>添加域名，添加站点</td>
			<td>orgAdd</td>
			<td><img src="k/kui/images/icons/16/org-del.png" alt="删除域名，删除站点" /></td>
			<td>删除域名，删除站点</td>
			<td>orgDel</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/time.png" alt="时间" /></td>
			<td>时间</td>
			<td>time</td>
			<td><img src="k/kui/images/icons/16/date.png" alt="日期，日历" />　/　<img src="k/kui/images/icons/16/calendar.png" alt="日期，日历" /></td>
			<td>日期，日历</td>
			<td>date　/　calendar</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/f5.png" alt="刷新" /></td>
			<td>刷新</td>
			<td>f5</td>
			<td><img src="k/kui/images/icons/16/find.png" alt="发现，查找" /></td>
			<td>发现，查找</td>
			<td>find</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/search.png" alt="搜索，查询" />　/　<img src="k/kui/images/icons/16/search2.png" alt="搜索，查询" /></td>
			<td>搜索，查询</td>
			<td>search，search-list　/　search2</td>
			<td><img src="k/kui/images/icons/16/door.png" alt="门，通道" /></td>
			<td>门，通道</td>
			<td>door</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/door_in.png" alt="进入" /></td>
			<td>进入</td>
			<td>door-in</td>
			<td><img src="k/kui/images/icons/16/door_open.png" alt="开门，打开" /></td>
			<td>开门，打开</td>
			<td>door-open</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/door_out.png" alt="退出，关闭" />　/　<img src="k/kui/images/icons/16/logout.png" alt="退出，注销" /></td>
			<td>退出，关闭　/　退出，注销</td>
			<td>door-out　/　logout</td>
			<td><img src="k/kui/images/icons/16/card.png" alt="卡片，证件" /></td>
			<td>卡片，证件</td>
			<td>card</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/table.png" alt="表格" /></td>
			<td>表格</td>
			<td>table</td>
			<td><img src="k/kui/images/icons/16/table_add.png" alt="添加表格" /></td>
			<td>添加表格</td>
			<td>table-add</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/table_delete.png" alt="删除表格" /></td>
			<td>删除表格</td>
			<td>table-delete　/　table-del</td>
			<td><img src="k/kui/images/icons/16/table_edit.png" alt="编辑表格" /></td>
			<td>编辑表格</td>
			<td>table-edit</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/table_excel.png" alt="excel表格" /></td>
			<td>excel表格</td>
			<td>table-excel</td>
			<td><img src="k/kui/images/icons/16/excel-import.png" alt="导出excel" /></td>
			<td>导出excel</td>
			<td>excel-import</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/excel-export.png" alt="导入excel" /></td>
			<td>导入excel</td>
			<td>excel-export</td>
			<td><img src="k/kui/images/icons/16/table_export.png" alt="导出表格" /></td>
			<td>导出表格</td>
			<td>table-export</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/table_import.png" alt="导入表格" /></td>
			<td>导入表格</td>
			<td>table-import</td>
			<td><img src="k/kui/images/icons/16/table_gear.png" alt="设置表格" /></td>
			<td>设置表格</td>
			<td>table-set</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/table_refresh.png" alt="刷新表格" /></td>
			<td>刷新表格</td>
			<td>table-refresh</td>
			<td><img src="k/kui/images/icons/16/table_save.png" alt="保存表格" /></td>
			<td>保存表格</td>
			<td>table-save</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/table_select.png" alt="选择表格" /></td>
			<td>选择表格</td>
			<td>table-select</td>
			<td><img src="k/kui/images/icons/16/link.png" alt="链接，连接" /></td>
			<td>链接，连接</td>
			<td>link</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/link_add.png" alt="添加链接，添加连接" /></td>
			<td>添加链接，添加连接</td>
			<td>link-add</td>
			<td><img src="k/kui/images/icons/16/link_delete.png" alt="删除链接，删除连接" /></td>
			<td>删除链接，删除连接</td>
			<td>link-delete</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/link_edit.png" alt="编辑链接，编辑连接" /></td>
			<td>编辑链接，编辑连接</td>
			<td>link-edit</td>
			<td><img src="k/kui/images/icons/16/link_break.png" alt="断开链接，断开连接" /></td>
			<td>断开链接，断开连接</td>
			<td>link-break</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/tel.png" alt="电话" /></td>
			<td>电话</td>
			<td>tel</td>
			<td><img src="k/kui/images/icons/16/talk.png" alt="谈话，交谈，说说" /></td>
			<td>谈话，交谈，说说</td>
			<td>talk</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/discuss.png" alt="讨论，议论，辩论" /></td>
			<td>讨论，议论，辩论</td>
			<td>discuss</td>
			<td><img src="k/kui/images/icons/16/comment.png" alt="评论，注释" /></td>
			<td>评论，注释</td>
			<td>comment</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/back.png" alt="后退，返回" /></td>
			<td>后退，返回</td>
			<td>back</td>
			<td><img src="k/kui/images/icons/16/go.png" alt="向前" /></td>
			<td>向前</td>
			<td>forward</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/refresh.png" alt="刷新" />　/　<img src="k/kui/images/icons/16/refurbish.png" alt="刷新" /></td>
			<td>刷新</td>
			<td>refresh　/　refurbish</td>
			<td><img src="k/kui/images/icons/16/return.png" alt="返回，退回" /></td>
			<td>返回，退回</td>
			<td>return</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/ask_and_answer.png" alt="退回原因" /></td>
			<td>退回原因</td>
			<td>return-cause</td>
			<td><img src="k/kui/images/icons/16/coffee.png" alt="咖啡" /></td>
			<td>咖啡</td>
			<td>coffee</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/graywarn.png" alt="灰色警告" /></td>
			<td>灰色警告</td>
			<td>graywarn</td>
			<td><img src="k/kui/images/icons/16/greenwarn.png" alt="警告" /></td>
			<td>警告</td>
			<td>greenwarn</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/help.png" alt="帮助" /></td>
			<td>帮助</td>
			<td>help</td>
			<td><img src="k/kui/images/icons/16/home.png" alt="首页，主页" /></td>
			<td>首页，主页</td>
			<td>home</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/mail.png" alt="邮件" />　/　<img src="k/kui/images/icons/16/email.png" alt="邮件" /></td>
			<td>邮件</td>
			<td>mail　/　email</td>
			<td><img src="k/kui/images/icons/16/mailbox.png" alt="邮箱，信箱" /></td>
			<td>邮箱，信箱</td>
			<td>mailbox</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/outbox.png" alt="发件箱" /></td>
			<td>发件箱</td>
			<td>outbox</td>
			<td><img src="k/kui/images/icons/16/msn.png" alt="MSN" /></td>
			<td>MSN</td>
			<td>msn</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/qq.png" alt="QQ，腾讯" /></td>
			<td>QQ，腾讯</td>
			<td>qq</td>
			<td><img src="k/kui/images/icons/16/communication.png" alt="通讯，沟通，通信" /></td>
			<td>通讯，沟通，通信</td>
			<td>communication</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/pager.png" alt="纸张，页" /></td>
			<td>纸张，页</td>
			<td>pager</td>
			<td><img src="k/kui/images/icons/16/config.png" alt="配置，布局" /></td>
			<td>配置，布局</td>
			<td>config</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/address.png" alt="地址" /></td>
			<td>地址</td>
			<td>address</td>
			<td><img src="k/kui/images/icons/16/process.png" alt="进程，进度" />　/　<img src="k/kui/images/icons/16/process1.png" alt="进程，进度" /></td>
			<td>进程，进度</td>
			<td>process　/　hd-process</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/archives.png" alt="档案" /></td>
			<td>档案</td>
			<td>archives</td>
			<td><img src="k/kui/images/icons/16/attibutes.png" alt="属性" /></td>
			<td>属性</td>
			<td>attibutes</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/busy.png" alt="忙碌，占用" /></td>
			<td>忙碌，占用</td>
			<td>busy</td>
			<td><img src="k/kui/images/icons/16/database.png" alt="数据库" /></td>
			<td>数据库</td>
			<td>database</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/computer.png" alt="电脑，计算机" /></td>
			<td>电脑，计算机</td>
			<td>compute-2</td>
			<td><img src="k/kui/images/icons/16/info-add.png" alt="添加信息" /></td>
			<td>添加信息</td>
			<td>info-add</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/info-edit.png" alt="编辑信息" /></td>
			<td>编辑信息</td>
			<td>info-edit</td>
			<td><img src="k/kui/images/icons/16/info-save.png" alt="保存信息" /></td>
			<td>保存信息</td>
			<td>info-save</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/info-del.png" alt="删除信息" /></td>
			<td>删除信息</td>
			<td>info-del</td>
			<td><img src="k/kui/images/icons/16/lightbulb.png" alt="灯泡" /></td>
			<td>灯泡</td>
			<td>bulb</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/picture.png" alt="图片" /></td>
			<td>图片</td>
			<td>picture</td>
			<td><img src="k/kui/images/icons/16/picture_add.png" alt="添加图片" /></td>
			<td>添加图片</td>
			<td>picture-add</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/picture_delete.png" alt="删除图片" /></td>
			<td>删除图片</td>
			<td>picture-del</td>
			<td><img src="k/kui/images/icons/16/picture_edit.png" alt="编辑图片" /></td>
			<td>编辑图片</td>
			<td>picture-edit</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/picture_go.png" alt="图片" /></td>
			<td>图片</td>
			<td>picture-go</td>
			<td><img src="k/kui/images/icons/16/picture_link.png" alt="图片链接" /></td>
			<td>图片链接</td>
			<td>picture-link</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/picture_save.png" alt="保存图片" /></td>
			<td>保存图片</td>
			<td>picture-save</td>
			<td><img src="k/kui/images/icons/16/piechart.png" alt="饼图，图表" /></td>
			<td>饼图，图表</td>
			<td>piechart</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/stamp_pattern.png" alt="印章" /></td>
			<td>印章</td>
			<td>seal</td>
			<td><img src="k/kui/images/icons/16/sort_ascending.png" alt="上报，上升，升序" /></td>
			<td>上报，上升，升序</td>
			<td>appear</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/control_repeat_blue.png" alt="流程跟踪，任务跟踪" /></td>
			<td>流程跟踪，任务跟踪</td>
			<td>follow</td>
			<td><img src="k/kui/images/icons/16/small_business.png" alt="处理" /></td>
			<td>处理</td>
			<td>dispose</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/timeline_marker.png" alt="处理记录" /></td>
			<td>处理记录</td>
			<td>dispose-record</td>
			<td><img src="k/kui/images/icons/16/shape_square_edit.png" alt="记录" /></td>
			<td>记录</td>
			<td>take-notes</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/arrow_join.png" alt="合并" /></td>
			<td>合并</td>
			<td>merge</td>
			<td><img src="k/kui/images/icons/16/arrow_right.png" alt="下发" /></td>
			<td>下发</td>
			<td>issued</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/arrow_down.png" alt="下拨" /></td>
			<td>下拨</td>
			<td>appropriation</td>
			<td><img src="k/kui/images/icons/16/arrow_inout.png" alt="发放" /></td>
			<td>发放</td>
			<td>provide</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/distribution_partnerships.png" alt="分发" /></td>
			<td>分发</td>
			<td>distribution</td>
			<td><img src="k/kui/images/icons/16/ok.png" alt="发布" /></td>
			<td>发布</td>
			<td>release</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/move_to_folder.png" alt="接收" /></td>
			<td>接收</td>
			<td>receive</td>
			<td><img src="k/kui/images/icons/16/page_edit.png" alt="签收" /></td>
			<td>签收</td>
			<td>sign-in</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/time_go.png" alt="查看记录" /></td>
			<td>查看记录</td>
			<td>view</td>
			<td><img src="k/kui/images/icons/16/timeline.png" alt="查看处理进度" /></td>
			<td>查看处理进度</td>
			<td>view-progress</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/cart_put.png" alt="购置" /></td>
			<td>购置</td>
			<td>cart-put</td>
			<td><img src="k/kui/images/icons/16/coins_in_hand.png" alt="领用" /></td>
			<td>领用</td>
			<td>consuming</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/table_replace.png" alt="归还" /></td>
			<td>归还</td>
			<td>give-back</td>
			<td><img src="k/kui/images/icons/16/transform_shear.png" alt="报损" /></td>
			<td>报损</td>
			<td>breakage</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/toolbox.png" alt="维修" /></td>
			<td>维修</td>
			<td>toolbox</td>
			<td><img src="k/kui/images/icons/16/recycle_bag.png" alt="报废" /></td>
			<td>报废</td>
			<td>recycle</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/account_balances.png" alt="调拨" /></td>
			<td>调拨</td>
			<td>balances</td>
			<td><img src="k/kui/images/icons/16/arrow_switch.png" alt="同步" /></td>
			<td>同步</td>
			<td>same</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/layers_map.png" alt="生成" /></td>
			<td>生成</td>
			<td>generate</td>
			<td><img src="k/kui/images/icons/16/application_get.png" alt="申请" /></td>
			<td>申请</td>
			<td>apply-for</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/domain_template.png" alt="模板" /></td>
			<td>模板</td>
			<td>template</td>
			<td><img src="k/kui/images/icons/16/basket_put.png" alt="入库" /></td>
			<td>入库</td>
			<td>basket-put</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/preview.png" alt="预览" /></td>
			<td>预览</td>
			<td>preview</td>
			<td><img src="k/kui/images/icons/16/layer_export.png" alt="导出" /></td>
			<td>导出</td>
			<td>export</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/to_do_list.png" alt="起草" /></td>
			<td>起草</td>
			<td>todolist</td>
			<td><img src="k/kui/images/icons/16/page_white_text.png" alt="日志" /></td>
			<td>日志</td>
			<td>daily</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/color_adjustment.png" alt="调整" /></td>
			<td>调整</td>
			<td>adjustment</td>
			<td><img src="k/kui/images/icons/16/change.png" alt="变更" /></td>
			<td>变更</td>
			<td>change</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/feedback.png" alt="反馈" /></td>
			<td>反馈</td>
			<td>feedback</td>
			<td><img src="k/kui/images/icons/16/shape_group.png" alt="汇总" /></td>
			<td>汇总</td>
			<td>group</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/node-tree.png" alt="节点树" /></td>
			<td>节点树</td>
			<td>node-tree</td>
			<td><img src="k/kui/images/icons/16/accept.png" alt="确定按钮" /></td>
			<td>确定按钮</td>
			<td>win-okval</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/cancel.png" alt="取消按钮" /></td>
			<td>取消按钮</td>
			<td>win-cancelVal</td>
			<td><img src="k/kui/images/icons/16/word2.png" alt="Word" /></td>
			<td>Word</td>
			<td>word</td>
		</tr>
		<tr>
			<td><img src="k/kui/images/icons/16/report_word.png" alt="Word报告" /></td>
			<td>Word报告</td>
			<td>word-report</td>
		</tr>
		<!--<tr>
			<td><img src="k/kui/images/icons/16/add.png" alt="新增" /></td>
			<td>新增</td>
			<td>add</td>
			<td><img src="k/kui/images/icons/16/add.png" alt="新增" /></td>
			<td>新增</td>
			<td>add</td>
		</tr>-->
	</table>
</div>
</body>
</html>