<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>�ɶ�ҽѧԺ���¹���ϵͳ</title>
<link type="text/css" rel="stylesheet" href="/app/webmanage/CSS/style.css" />
<script src="/app/webmanage/js/foucs.js" type="text/javascript"></script>
<script src="/app/webmanage/js/jquery.js" type="text/javascript"></script>
<script src="/app/webmanage/js/Vote.js" type="text/javascript"></script>
<script src="/app/webmanage/js/SearchData.js" type="text/javascript"></script>
<script>
$(document).ready(function(){
	$(".newlist2 li:even").css("margin-right","5px");
	$(".newlist2 li:odd").css("margin-left","5px");
	$(".boxesbb li.money , .boxesbb li.download").css("margin-right","0px");
});
</script>
</head>

<body>
<#include "top.ftl" parse=true encoding="UTF-8">
<script type="text/javascript">
	focusMenu("");
</script> 
<!--��Ҫ��������-->
<div class="main">
	<div class="latestnew">
		<div class="late-nw"><a href="/${siteConfig.siteHtmlDir}/xwdt/${newNotice.id}.html">${newNotice.title}</a></div>
	</div>
	<div class="content">
		<div class="leftwrap"> 
			<!--���Ŷ�̬-->
			<div class="boxesaa">
				<div class="caption"><strong>���Ŷ�̬</strong><span><a href="/${siteConfig.siteHtmlDir}/xwdt/index.html">MORE &gt;</a></span></div>
				<div class="boxwrap">
					<div class="focus"> 
						<!-- ���� ��ʼ -->
						<div class="mod_focus_show" id="divimgplay">
							<ul class="mod_focus_pic" id="divimginfog_imgPlayer">
							    <@kh_cms_article_tag tagName='list_lable' classId='402880c4436a392801436a666d360006' count='4' pageNo='1' pagesize='0' isAudit='on' datatype='img' recommendOff='uder'>
									 <#list list_lable as list_test>
										<li><a href="/${siteConfig.siteHtmlDir}/xwdt/${list_test.id}.html"> <img src="${list_test.imgUrl}" alt="" /></a></li>
									</#list>							 
								</@kh_cms_article_tag>
							</ul>
							<ul class="mod_focus_title" id="ptitleg_imgPlayer">
							<@kh_cms_article_tag tagName='list_lable' classId='402880c4436a392801436a666d360006' count='4' pageNo='1' pagesize='0' isAudit='on' datatype='img' recommendOff='uder'>
									 <#list list_lable as list_test>
										<li class="current">
											<div><a href="/${siteConfig.siteHtmlDir}/xwdt/${list_test.id}.html"></a></div>
										</li>
									</#list>							 
							</@kh_cms_article_tag>
							</ul>
							<div class="focus_switch"><a href="javascript:;" class="icon_prev"></a><a href="javascript:;" class="icon_next"></a></div>
							<ul class="mod_focus_list" id="divpageinfog_imgPlayer">
								<@kh_cms_article_tag tagName='list_lable' classId='402880c4436a392801436a666d360006' count='4' pageNo='1' pagesize='0' isAudit='on' datatype='img' recommendOff='uder'>
									 <#list list_lable as list_test>
										<li class="">
											<a href="">
												<img src="${list_test.imgUrl}" alt="${list_test.title}" />
												<span class="border"/>
											</a>
										</li>
									</#list>							 
								</@kh_cms_article_tag>
							</ul>
						</div>
						<script type="text/javascript">
							foucsbox(0);
						</script> 
						<!-- ���� ���� --> 
					</div>
					<div class="newlist">
						<ul>
							<@kh_cms_article_tag tagName='list_lable' classId='402880c4436a392801436a666d360006' count='8' pageNo='1' pagesize='0' isAudit='on' datatype='txt' recommendOff='uder'>
							     <#list list_lable as list_test>
								    <li>
										<div><a href="/${siteConfig.siteHtmlDir}/xwdt/${list_test.id}.html">${list_test.title}</a></div>
										<span class="time">${list_test.eidtDate?string("MM-dd")}</span>
									</li>
								</#list>							 
							</@kh_cms_article_tag>
						</ul>
					</div>
				</div>
			</div>
			<!--ʦ�ʽ���-->
			<div class="boxesaa">
				<div class="caption"><strong>ʦ�ʽ���</strong><span><a href="/${siteConfig.siteHtmlDir}/szjs/index.html">MORE &gt;</a></span></div>
				<div class="boxwrap2">
					<div class="shiziphotos">
						<ul>
							<@kh_cms_article_tag tagName='list_lable' classId='402880c4436a392801436a5ed9ba0001' count='5' pageNo='1' pagesize='0' isAudit='on' datatype='img' recommendOff='uder'>
							     <#list list_lable as list_test>
									<li>
										<div class="szpic"><a href="/${siteConfig.siteHtmlDir}/szjs/${list_test.id}.html"><img src="${list_test.imgUrl}" alt="" /></a></div>
										<div class="sztxt"><a href="/${siteConfig.siteHtmlDir}/szjs/${list_test.id}.html">${list_test.title}</a></div>
									</li>
								</#list>							 
							</@kh_cms_article_tag>
						</ul>
					</div>
					<div class="newlist2">
						<ul>
						   <@kh_cms_article_tag tagName='list_lable' classId='402880c4436a392801436a5ed9ba0001' count='8' pageNo='1' pagesize='0' isAudit='on'   datatype='txt' recommendOff='uder'>
							     <#list list_lable as list_test>
									<li>
										<div><a href="/${siteConfig.siteHtmlDir}/szjs/${list_test.id}.html">${list_test.title}</a></div>
										<span class="time">${list_test.eidtDate?string("MM-dd")}</span>
									</li>
								</#list>							 
							</@kh_cms_article_tag>
						</ul>
					</div>
				</div>
			</div>
			<!-- �����Ŀ� -->
			<div class="boxesbb">
				<ul>
					<li class="hrmanage">
						<div class="minbox">
							<div class="caption"><strong>������Դ����</strong><span><a href="/${siteConfig.siteHtmlDir}/rlzygl/index.html">MORE &gt;</a></span></div>
							<div class="boxwrap2">
								<div class="colorpic"><img src="/app/webmanage/images/clrp01.jpg" alt="������Դ����" /></div>
								<div class="newlist3">
									<ul>
										 <@kh_cms_article_tag tagName='list_lable' classId='402880c4436a392801436a5f4f970002' count='8' pageNo='1' pagesize='0' isAudit='on'   datatype='txt' recommendOff='uder'>
											 <#list list_lable as list_test>
												<li>
													<div><a href="/${siteConfig.siteHtmlDir}/rlzygl/${list_test.id}.html">${list_test.title}</a></div>
													<span class="time">${list_test.eidtDate?string("MM-dd")}</span>
												</li>
											</#list>							 
										 </@kh_cms_article_tag>
									</ul>
								</div>
							</div>
						</div>
					</li>
					<li class="money">
						<div class="minbox">
							<div class="caption"><strong>�Ͷ����ʼ�����</strong><span><a href="/${siteConfig.siteHtmlDir}/ldgzjfl/index.html">MORE &gt;</a></span></div>
							<div class="boxwrap2">
								<div class="colorpic"><img src="/app/webmanage/images/clrp02.jpg" alt="�Ͷ����ʼ�����" /></div>
								<div class="newlist3">
									<ul>
										 <@kh_cms_article_tag tagName='list_lable' classId='402880c4436a392801436a603a8a0003' count='8' pageNo='1' pagesize='0' isAudit='on'   datatype='txt' recommendOff='uder'>
											 <#list list_lable as list_test>
												<li>
													<div><a href="/${siteConfig.siteHtmlDir}/ldgzjfl/${list_test.id}.html">${list_test.title}</a></div>
													<span class="time">${list_test.eidtDate?string("MM-dd")}</span>
												</li>
											</#list>							 
										 </@kh_cms_article_tag>
									</ul>
								</div>
							</div>
						</div>
					</li>
					<li class="policy">
						<div class="minbox">
							<div class="caption"><strong>��������</strong><span><a href="/${siteConfig.siteHtmlDir}/rszc/index.html">MORE &gt;</a></span></div>
							<div class="boxwrap2">
								<div class="colorpic"><img src="/app/webmanage/images/clrp03.jpg" alt="��������" /></div>
								<div class="newlist3">
									<ul>
										<@kh_cms_article_tag tagName='list_lable' classId='402880c4436a392801436a60a2a40004' count='8' pageNo='1' pagesize='0' isAudit='on'   datatype='txt' recommendOff='uder'>
											 <#list list_lable as list_test>
												<li>
													<div><a href="/${siteConfig.siteHtmlDir}/rszc/${list_test.id}.html">${list_test.title}</a></div>
													<span class="time">${list_test.eidtDate?string("MM-dd")}</span>
												</li>
											</#list>							 
										 </@kh_cms_article_tag>
									</ul>
								</div>
							</div>
						</div>
					</li>
					<li class="download">
						<div class="minbox">
							<div class="caption"><strong>�������ؼ�����ָ��</strong><span><a href="/${siteConfig.siteHtmlDir}/zlxz/index.html">MORE &gt;</a></span></div>
							<div class="boxwrap2">
								<div class="colorpic"><img src="/app/webmanage/images/clrp04.jpg" alt="�������ؼ�����ָ��" /></div>
								<div class="newlist3">
									<ul>
										<@kh_cms_article_tag tagName='list_lable' classId='402880c4436a392801436a651a9d0005' count='8' pageNo='1' pagesize='0' isAudit='on'   datatype='txt' recommendOff='uder'>
											 <#list list_lable as list_test>
												<li>
													<div><a href="/${siteConfig.siteHtmlDir}/zlxz/${list_test.id}.html">${list_test.title}</a></div>
													<span class="time">${list_test.eidtDate?string("MM-dd")}</span>
												</li>
											</#list>							 
										 </@kh_cms_article_tag>
									</ul>
								</div>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
		<!--�ұ���-->
		<div class="sidewrap">
			<div class="sysextra"><a href="http://222.197.128.138:8213/app/index.jsp">��̨ϵͳ�������</a></div>
			<div class="special">
				<div class="caption"><strong>ר����Ŀ</strong><span></span></div>
				<div class="special-list">
					<ul>
						<li><a href="#" target="_black"><img src="/app/webmanage/images/ztimg01.jpg" alt="" /></a></li>
						<li><a href="http://qzlxw.cmc.edu.cn/" target="_black"><img src="/app/webmanage/images/ztimg02.jpg" alt="" /></a></li>
						<li><a href="#" target="_black"><img src="/app/webmanage/images/ztimg03.jpg" alt="" /></a></li>
						<li><a href="http://www.sichuan.newslake.cn/cmsAction!hostRequest.do?rand=1389835172880" target="_black"><img src="/app/webmanage/images/ztimg04.jpg" alt="" /></a></li>
						<li><a href="#" target="_black"><img src="/app/webmanage/images/ztimg05.jpg" alt="" /></a></li>
					</ul>
				</div>
			</div>
			<div class="search">
				<div class="caption"><strong>�ļ�����</strong><span><a href="/${siteConfig.siteHtmlDir}/search.html">MORE &gt;</a></span></div>
				<div class="search-opt">
					<div class="schtit">��ѡ��ؼ��֣�</div>
					<form action="" method="get">
					    <input type="hidden" name="siteId" value="${siteConfig.id}" id="siteId"/>
						<div class="icn">
							<label>
								<input type="radio" name="search" value="file" id="search_0" checked="checked" />
								����</label>
							<span class="s1"></span> </div>
						<div class="icn">
							<label>
								<input type="radio" name="search" value="downld" id="search_1" />
								����</label>
							<span class="s2"></span> </div>
						<div class="icn">
							<label>
								<input type="radio" name="search" value="pict" id="search_2" />
								ͼƬ</label>
							<span class="s3"></span> </div>
						<div>
							<button type="button" onClick="searchDataByIndex('/${siteConfig.siteHtmlDir}/search.html')">�������</button>
						</div>
					</form>
				</div>
			</div>
			<div class="diaocha">
				<div class="caption"><strong>��վ����</strong><span><a href="/${siteConfig.siteHtmlDir}/bzdc/index.html">MORE &gt;</a></span></div>
				<div class="diaocha-opt">
				   <@kh_cms_vote_tag tagName='list_lable' classId='402880c4438f82c801438f9eba330001' count='8' pageNo='1' pagesize='0' isAudit='on'   datatype='txt' recommendOff='uder'>
						 <#list list_lable as vote>
						    <div class="diaochatit">${vote.title}</div>
							<form action="" id="voteForm" name="voteForm" method="get">
									<@kh_cms_vote_tag itemtagName='list_vote_item' voteId='${vote.id}'>
										<#list list_vote_item as voteItem>
											<div class="icn">
												<label><input type="radio" name="itemIds" value="1" onClick="check_votes('${vote.id}',${vote.multiSelect})" id="${voteItem.id}" group="${vote.id}"/>${voteItem.itemTitle}</label>
											</div>
										</#list>
									</@kh_cms_vote_tag>
									<div>
									<button type="button" class="vote" onclick="ReVoteSave('/${siteConfig.siteHtmlDir}/bzdc/402880c4438f82c801438f9eba330001.html','402880c4438f82c801438f9eba330001')">ͶƱ</button>
									<button type="button" class="look" onClick="javascript:window.location.href='/${siteConfig.siteHtmlDir}/bzdc/402880c4438f82c801438f9eba330001.html'">�鿴</button>
								</div>
							</form>
						</#list>							 
					 </@kh_cms_vote_tag>
				</div>
			</div>
			<div class="ggpic"><img src="/app/webmanage/images/dkl.png" alt="" /></div>
		</div>
	</div>
	<div class="friendlk"><a href="http://www.moe.edu.cn/">���ҽ�����</a><a href="http://www.mohrss.gov.cn/">�������²�</a><a href="http://www.scedu.net/">�Ĵ�������</a><a href="http://www.sc.hrss.gov.cn/">�Ĵ�ʡ������</a><a href="http://www.uestc.edu.cn/">���ӿƼ���ѧ</a><a href="http://www.cdut.edu.cn/default.html">�ɶ�����ѧ</a><a href="http://www.scu.edu.cn/">�Ĵ���ѧ</a><a href="http://www.cdutcm.edu.cn/">�ɶ���ҽҩ��ѧ</a><a href="http://www.lzmc.edu.cn/">����ҽѧԺ</a><a href="http://www.nsmc.edu.cn/">����ҽѧԺ</a><a href="">�������...</a></div>
</div>
<!--��Ҫ��������-->
<#include "bottom.ftl" parse=true encoding="UTF-8">
</body>
</html>