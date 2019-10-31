<%@ page import="java.util.Date"%>
<%@ page import="com.edu.service.UniversityService"%>
<%@ page import="org.springframework.web.context.WebApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%
WebApplicationContext context = (WebApplicationContext)this.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE); 
UniversityService universityService = (UniversityService)context.getBean("universityService"); 
int year = new Date().getYear();
List<CodeUniversity> xx = universityService.findByYxdmAndNf(yxdm,year);
//将学校设置入页面
pageContext.setAttribute("xx",xx);


%>
<!DOCTYPE html>
<html>
<head></head>
<body>
	<div>
	<c:forEach items="${xx }" var="yx" >
		<p style='margin: 0cm'>
			<span style='font-size: 21.5pt; font-family: 黑体'><c:out value="${yx.yxdh }"></c:out><span style='mso-tab-count: 1'></span><c:out value="${yx.yxmc }"></c:out></span>
		</p>
		<p style='margin: 0cm'>
			<span style='font-size: 14.0pt; font-family: 黑体'><br />核对结果：_______________</span>
		</p>
		<p style='margin: 0cm'>
			<span style='font-size: 14.0pt; font-family: 黑体'>核对人签字：_______________联系方式：_______________</span>
		</p>
		<p style='margin: 0cm'>
			<span style='font-size: 14.0pt; font-family: 黑体'>招生负责人签字（盖公章）：_______________</span>
		</p>
		<p style='margin: 0cm'>
			<span style='font-size: 14.0pt; font-family: 黑体'>打印时间：2019-06-13</span><br />
		</p>
		<p style='margin: 0cm'>
			<span style='font-size: 14.0pt; font-family: 黑体'>——————————————————————————<br />此稿为出版稿，注意事项如下：
			</span><br />
		</p>
		<p style='margin: 0cm'>
			<span style='font-size: 12.0pt; font-family: 黑体'>
			    1.自主招生不公布。
				<br>2.高水平艺术团、高水平运动队只公布院校户头,不公布具体专业。
				<br/>
				3.如有修改，请将修改内容签字盖章扫描发送至scpzjh@qq.com邮箱。
				否则，必须在来源计划系统中点击核对无误按钮。
				逾期未按此要求核对的高校我省将以教育部下达并经我省修正后的计划信息，向社会公布。
				<br>4.因高校疏于核对产生的一切后果，由院校负责解释。
			</span>
			<br />
		</p>
		<br>
		<p style='margin: 0cm'>
			<span style='font-size: 21.5pt; font-family: 黑体'><c:out value="${yx.yxdh }"></c:out><span style='mso-tab-count: 1'></span><c:out value="${yx.yxmc }"></c:out></span>
		</p>
		<br>
	<%
	List<CodeUniversity> yxdhs =  universityService.findByYxdhAndNf(yx.getYxdh(),year);
	%>
	
	</c:forEach>
		<p style='margin: 0cm'>
			<span style='font-size: 21.5pt; font-family: 黑体'>一、文史类本科</span>
		</p>
		<br>
		<p style='margin: 0cm'>
			<span style='font-size: 15.5pt; font-family: 黑体'>(一)本科提前批录取院校</span>
		</p>
		<p style='margin: 0cm'>
			<span style='font-size: 14.0pt; font-family: 黑体'>2．高校专项</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 黑体'>学校代号、名称及专业代号、名称<span style='mso-tab-count: 1'></span>计划<span style='mso-tab-count: 1'></span>收费</span>
		</p>
		<p style='margin: 0cm;'>
			<span style='font-size: 10.5pt; font-family: 黑体'><c:out value="${yx.yxdh }"></c:out> <c:out value="${yx.yxmc }"></c:out><span style='mso-tab-count: 1'></span>2</span>
		</p>
		<p style='margin: 0cm'>
			<span style='font-size: 10.5pt; font-family: 黑体'>地址：</span><span
				style='font-size: 10.5pt; font-family: 楷体_GB2312'>吉林省长春市人民大街5268号</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>A2 思想政治教育(师范)(国家公费师范生)(净月校区)<span
				style='mso-tab-count: 1'></span>1<span style='mso-tab-count: 1'></span>免费</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'>
			<span style='mso-spacerun: yes'>&nbsp;</span>
				A3 汉语言文学(师范)(国家公费师范生)
				<span style='mso-tab-count: 1'></span>
				1<span style='mso-tab-count: 1'></span>
				免费
			</span>
		</p>
		<p style='margin: 0cm'>
			<span style='font-size: 10.5pt; font-family: 黑体'> 备注：</span><span
				style='font-size: 10.5pt; font-family: 楷体_GB2312'>认同四川省少数民族地区加分项目，但分值最高20分。
			</span>
		</p>
		<p style='margin: 0cm'>
			<span style='font-size: 14.0pt; font-family: 黑体'>6．本科</span>
		</p>
		<p style='margin: 0cm'>
			<span style='font-size: 14.0pt; font-family: 黑体'>(5)教育部直属师范院校</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 黑体'>学校代号、名称及专业代号、名称<span
				style='mso-tab-count: 1'></span>计划<span style='mso-tab-count: 1'></span>收费
			</span>
		</p>
		<p style='margin: 0cm;'>
			<span style='font-size: 10.5pt; font-family: 黑体'><c:out value="${yx.yxdh }"></c:out> <c:out value="${yx.yxmc }"></c:out><span
				style='mso-tab-count: 1'></span>61
			</span>
		</p>
		<p style='margin: 0cm'>
			<span style='font-size: 10.5pt; font-family: 黑体'>地址：</span><span
				style='font-size: 10.5pt; font-family: 楷体_GB2312'>吉林省长春市人民大街5268号</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>AD 思想政治教育(师范)<span
				style='mso-tab-count: 1'></span>2<span style='mso-tab-count: 1'></span>3850</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>B1 教育学类(师范)(包含专业:教育学、学前教育、小学教育)(专业备注:入学一年后按成绩志愿分专业培养)<span
				style='mso-tab-count: 1'></span>3<span style='mso-tab-count: 1'></span>3850</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>B3 汉语言文学(师范)<span
				style='mso-tab-count: 1'></span>7<span style='mso-tab-count: 1'></span>3850</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>B4 历史学(师范)<span
				style='mso-tab-count: 1'></span>3<span style='mso-tab-count: 1'></span>3850</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>C1 法学(净月校区)<span
				style='mso-tab-count: 1'></span>2<span style='mso-tab-count: 1'></span>3850</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>C2 行政管理(净月校区)<span
				style='mso-tab-count: 1'></span>2<span style='mso-tab-count: 1'></span>3850</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>C3
				政治学类(净月校区)(包含专业:政治学与行政学、国际政治)(专业备注:入学一年后按成绩志愿分专业培养)<span
				style='mso-tab-count: 1'></span>3<span style='mso-tab-count: 1'></span>3850</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>C6 旅游管理<span
				style='mso-tab-count: 1'></span>1<span style='mso-tab-count: 1'></span>3850</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>C7 俄语(师范)(净月校区)(外语口试)<span
				style='mso-tab-count: 1'></span>1<span style='mso-tab-count: 1'></span>3850</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>C8 日语(师范)(净月校区)(外语口试)<span
				style='mso-tab-count: 1'></span>2<span style='mso-tab-count: 1'></span>3850</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>C9 商务英语(净月校区)(招收英语语种考生)(外语口试)<span
				style='mso-tab-count: 1'></span>2<span style='mso-tab-count: 1'></span>5060</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>D1 英语(师范)(招收英语语种考生)(外语口试)<span
				style='mso-tab-count: 1'></span>1<span style='mso-tab-count: 1'></span>5060</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>D2 社会学<span
				style='mso-tab-count: 1'></span>1<span style='mso-tab-count: 1'></span>3850</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>D4 哲学<span
				style='mso-tab-count: 1'></span>2<span style='mso-tab-count: 1'></span>3850</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>E7
				新闻传播学类(净月校区)(包含专业:新闻学、广告学)(专业备注:入学一年半后按成绩志愿分专业培养)<span
				style='mso-tab-count: 1'></span>2<span style='mso-tab-count: 1'></span>6600</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>A2 思想政治教育(师范)(国家公费师范生)(净月校区)<span
				style='mso-tab-count: 1'></span>8<span style='mso-tab-count: 1'></span>免费</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>A3 汉语言文学(师范)(国家公费师范生)<span
				style='mso-tab-count: 1'></span>9<span style='mso-tab-count: 1'></span>免费</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>A4 历史学(师范)(国家公费师范生)<span
				style='mso-tab-count: 1'></span>3<span style='mso-tab-count: 1'></span>免费</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>A5
				英语(师范)(国家公费师范生)(招收英语语种考生)(外语口试)<span style='mso-tab-count: 1'></span>2<span
				style='mso-tab-count: 1'></span>免费</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>AB 地理科学(师范)(国家公费师范生)<span
				style='mso-tab-count: 1'></span>1<span style='mso-tab-count: 1'></span>免费</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>AE 学前教育(师范)(国家公费师范生)<span
				style='mso-tab-count: 1'></span>4<span style='mso-tab-count: 1'></span>免费</span>
		</p>
		<p style='margin: 0cm'>
			<span style='font-size: 10.5pt; font-family: 黑体'> 备注：</span><span
				style='font-size: 10.5pt; font-family: 楷体_GB2312'>认同四川省少数民族地区加分项目，但分值最高20分。
			</span>
		</p>
		<br>
		<p style='margin: 0cm'>
			<span style='font-size: 15.5pt; font-family: 黑体'>(二)国家专项</span>
		</p>
		<p style='margin: 0cm'>
			<span style='font-size: 14.0pt; font-family: 黑体'>①执行本科一批控制线院校</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 黑体'>学校代号、名称及专业代号、名称<span
				style='mso-tab-count: 1'></span>计划<span style='mso-tab-count: 1'></span>收费
			</span>
		</p>
		<p style='margin: 0cm;'>
			<span style='font-size: 10.5pt; font-family: 黑体'><c:out value="${yx.yxdh }"></c:out> <c:out value="${yx.yxmc }"></c:out><span
				style='mso-tab-count: 1'></span>7
			</span>
		</p>
		<p style='margin: 0cm'>
			<span style='font-size: 10.5pt; font-family: 黑体'>地址：</span><span
				style='font-size: 10.5pt; font-family: 楷体_GB2312'>吉林省长春市人民大街5268号</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>C7 俄语(师范)(净月校区)(外语口试)<span
				style='mso-tab-count: 1'></span>1<span style='mso-tab-count: 1'></span>3850</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>A2 思想政治教育(师范)(国家公费师范生)(净月校区)<span
				style='mso-tab-count: 1'></span>1<span style='mso-tab-count: 1'></span>免费</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>A3 汉语言文学(师范)(国家公费师范生)<span
				style='mso-tab-count: 1'></span>2<span style='mso-tab-count: 1'></span>免费</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>A4 历史学(师范)(国家公费师范生)<span
				style='mso-tab-count: 1'></span>1<span style='mso-tab-count: 1'></span>免费</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>A5
				英语(师范)(国家公费师范生)(招收英语语种考生)(外语口试)<span style='mso-tab-count: 1'></span>1<span
				style='mso-tab-count: 1'></span>免费</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>AB 地理科学(师范)(国家公费师范生)<span
				style='mso-tab-count: 1'></span>1<span style='mso-tab-count: 1'></span>免费</span>
		</p>
		<p style='margin: 0cm'>
			<span style='font-size: 10.5pt; font-family: 黑体'> 备注：</span><span
				style='font-size: 10.5pt; font-family: 楷体_GB2312'>认同四川省少数民族地区加分项目，但分值最高20分。
			</span>
		</p>
		<br>
		<p style='margin: 0cm'>
			<span style='font-size: 21.5pt; font-family: 黑体'>四、文史类预科</span>
		</p>
		<p style='margin: 0cm'>
			<span style='font-size: 14.0pt; font-family: 黑体'>(一)普通预科</span>
		</p>
		<p style='margin: 0cm'>
			<span style='font-size: 14.0pt; font-family: 黑体'>1．本科提前批预科</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 黑体'>学校代号、名称及专业代号、名称<span
				style='mso-tab-count: 1'></span>计划<span style='mso-tab-count: 1'></span>收费
			</span>
		</p>
		<p style='margin: 0cm;'>
			<span style='font-size: 10.5pt; font-family: 黑体'><c:out value="${yx.yxdh }"></c:out> <c:out value="${yx.yxmc }"></c:out><span
				style='mso-tab-count: 1'></span>1
			</span>
		</p>
		<p style='margin: 0cm'>
			<span style='font-size: 10.5pt; font-family: 黑体'>地址：</span><span
				style='font-size: 10.5pt; font-family: 楷体_GB2312'>吉林省长春市人民大街5268号</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>Y1
				少数民族预科(政治学类)(包含专业:政治学与行政学、国际政治)(专业备注:培养地点：东北师范大学)<span
				style='mso-tab-count: 1'></span>1<span style='mso-tab-count: 1'></span>3850</span>
		</p>
		<br>
		<p style='margin: 0cm'>
			<span style='font-size: 21.5pt; font-family: 黑体'>一、理工类本科</span>
		</p>
		<br>
		<p style='margin: 0cm'>
			<span style='font-size: 15.5pt; font-family: 黑体'>(一)本科提前批录取院校</span>
		</p>
		<p style='margin: 0cm'>
			<span style='font-size: 14.0pt; font-family: 黑体'>2．高校专项</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 黑体'>学校代号、名称及专业代号、名称<span
				style='mso-tab-count: 1'></span>计划<span style='mso-tab-count: 1'></span>收费
			</span>
		</p>
		<p style='margin: 0cm;'>
			<span style='font-size: 10.5pt; font-family: 黑体'><c:out value="${yx.yxdh }"></c:out> 东北师范大学<span
				style='mso-tab-count: 1'></span>2
			</span>
		</p>
		<p style='margin: 0cm'>
			<span style='font-size: 10.5pt; font-family: 黑体'>地址：</span><span
				style='font-size: 10.5pt; font-family: 楷体_GB2312'>吉林省长春市人民大街5268号</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>A6 数学与应用数学(师范)(国家公费师范生)<span
				style='mso-tab-count: 1'></span>1<span style='mso-tab-count: 1'></span>免费</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>AA 生物科学(师范)(国家公费师范生)<span
				style='mso-tab-count: 1'></span>1<span style='mso-tab-count: 1'></span>免费</span>
		</p>
		<p style='margin: 0cm'>
			<span style='font-size: 10.5pt; font-family: 黑体'> 备注：</span><span
				style='font-size: 10.5pt; font-family: 楷体_GB2312'>认同四川省少数民族地区加分项目，但分值最高20分。
			</span>
		</p>
		<p style='margin: 0cm'>
			<span style='font-size: 14.0pt; font-family: 黑体'>6．本科</span>
		</p>
		<p style='margin: 0cm'>
			<span style='font-size: 14.0pt; font-family: 黑体'>(5)教育部直属师范院校</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 黑体'>学校代号、名称及专业代号、名称<span
				style='mso-tab-count: 1'></span>计划<span style='mso-tab-count: 1'></span>收费
			</span>
		</p>
		<p style='margin: 0cm;'>
			<span style='font-size: 10.5pt; font-family: 黑体'><c:out value="${yx.yxdh }"></c:out> 东北师范大学<span
				style='mso-tab-count: 1'></span>112
			</span>
		</p>
		<p style='margin: 0cm'>
			<span style='font-size: 10.5pt; font-family: 黑体'>地址：</span><span
				style='font-size: 10.5pt; font-family: 楷体_GB2312'>吉林省长春市人民大街5268号</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>B1
				教育学类(师范)(包含专业:教育学、学前教育、小学教育)(专业备注:入学一年后按成绩志愿分专业培养)<span
				style='mso-tab-count: 1'></span>2<span style='mso-tab-count: 1'></span>3850</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>B2 心理学(师范)<span
				style='mso-tab-count: 1'></span>2<span style='mso-tab-count: 1'></span>3850</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>B5 数学与应用数学(师范)<span
				style='mso-tab-count: 1'></span>3<span style='mso-tab-count: 1'></span>3850</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>B7 物理学(师范)<span
				style='mso-tab-count: 1'></span>4<span style='mso-tab-count: 1'></span>3850</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>B8 化学(师范)<span
				style='mso-tab-count: 1'></span>2<span style='mso-tab-count: 1'></span>3850</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>B9 生物科学(师范)<span
				style='mso-tab-count: 1'></span>2<span style='mso-tab-count: 1'></span>3850</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>BA 地理科学(师范)<span
				style='mso-tab-count: 1'></span>3<span style='mso-tab-count: 1'></span>3850</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>C4
				经济学类(净月校区)(包含专业:经济学、金融学、国际经济与贸易、财政学)(专业备注:入学一年后按成绩志愿分专业培养)<span
				style='mso-tab-count: 1'></span>9<span style='mso-tab-count: 1'></span>3850</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>C5
				工商管理类(净月校区)(包含专业:会计学、市场营销、财务管理、人力资源管理)(专业备注:入学一年后按成绩志愿分专业培养)<span
				style='mso-tab-count: 1'></span>4<span style='mso-tab-count: 1'></span>3850</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>C7 俄语(师范)(净月校区)(外语口试)<span
				style='mso-tab-count: 1'></span>2<span style='mso-tab-count: 1'></span>3850</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>C8 日语(师范)(净月校区)(外语口试)<span
				style='mso-tab-count: 1'></span>2<span style='mso-tab-count: 1'></span>3850</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>C9 商务英语(净月校区)(招收英语语种考生)(外语口试)<span
				style='mso-tab-count: 1'></span>2<span style='mso-tab-count: 1'></span>5060</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>D5 统计学<span
				style='mso-tab-count: 1'></span>2<span style='mso-tab-count: 1'></span>3850</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>D6 计算机科学与技术(净月校区)<span
				style='mso-tab-count: 1'></span>4<span style='mso-tab-count: 1'></span>5060</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>D7
				图书情报与档案管理类(净月校区)(包含专业:图书馆学、信息资源管理)(专业备注:入学两年后按成绩志愿分专业培养)<span
				style='mso-tab-count: 1'></span>4<span style='mso-tab-count: 1'></span>3850</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>D8 软件工程(净月校区)<span
				style='mso-tab-count: 1'></span>6<span style='mso-tab-count: 1'></span>9800</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>E1 电子信息科学与技术<span
				style='mso-tab-count: 1'></span>5<span style='mso-tab-count: 1'></span>3850</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>E2 生物技术<span
				style='mso-tab-count: 1'></span>2<span style='mso-tab-count: 1'></span>3850</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>E4 人文地理与城乡规划<span
				style='mso-tab-count: 1'></span>1<span style='mso-tab-count: 1'></span>3850</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>E5
				环境科学与工程类(净月校区)(包含专业:环境工程、环境科学)(专业备注:入学一年半后按成绩志愿分专业培养)<span
				style='mso-tab-count: 1'></span>6<span style='mso-tab-count: 1'></span>3850</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>E6 生态学(净月校区)<span
				style='mso-tab-count: 1'></span>4<span style='mso-tab-count: 1'></span>3850</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>E8 数字媒体技术(师范)(净月校区)<span
				style='mso-tab-count: 1'></span>2<span style='mso-tab-count: 1'></span>5060</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>A5
				英语(师范)(国家公费师范生)(招收英语语种考生)(外语口试)<span style='mso-tab-count: 1'></span>3<span
				style='mso-tab-count: 1'></span>免费</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>A6 数学与应用数学(师范)(国家公费师范生)<span
				style='mso-tab-count: 1'></span>8<span style='mso-tab-count: 1'></span>免费</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>A7 教育技术学(师范)(国家公费师范生)(净月校区)<span
				style='mso-tab-count: 1'></span>3<span style='mso-tab-count: 1'></span>免费</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>A8 物理学(师范)(国家公费师范生)<span
				style='mso-tab-count: 1'></span>7<span style='mso-tab-count: 1'></span>免费</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>A9 化学(师范)(国家公费师范生)<span
				style='mso-tab-count: 1'></span>6<span style='mso-tab-count: 1'></span>免费</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>AA 生物科学(师范)(国家公费师范生)<span
				style='mso-tab-count: 1'></span>1<span style='mso-tab-count: 1'></span>免费</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>AB 地理科学(师范)(国家公费师范生)<span
				style='mso-tab-count: 1'></span>3<span style='mso-tab-count: 1'></span>免费</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>AE 学前教育(师范)(国家公费师范生)<span
				style='mso-tab-count: 1'></span>3<span style='mso-tab-count: 1'></span>免费</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>AF 心理学(师范)(国家公费师范生)<span
				style='mso-tab-count: 1'></span>5<span style='mso-tab-count: 1'></span>免费</span>
		</p>
		<p style='margin: 0cm'>
			<span style='font-size: 10.5pt; font-family: 黑体'> 备注：</span><span
				style='font-size: 10.5pt; font-family: 楷体_GB2312'>认同四川省少数民族地区加分项目，但分值最高20分。
			</span>
		</p>
		<br>
		<p style='margin: 0cm'>
			<span style='font-size: 15.5pt; font-family: 黑体'>(二)国家专项</span>
		</p>
		<p style='margin: 0cm'>
			<span style='font-size: 14.0pt; font-family: 黑体'>①执行本科一批控制线院校</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 黑体'>学校代号、名称及专业代号、名称<span
				style='mso-tab-count: 1'></span>计划<span style='mso-tab-count: 1'></span>收费
			</span>
		</p>
		<p style='margin: 0cm;'>
			<span style='font-size: 10.5pt; font-family: 黑体'><c:out value="${yx.yxdh }"></c:out> 东北师范大学<span
				style='mso-tab-count: 1'></span>9
			</span>
		</p>
		<p style='margin: 0cm'>
			<span style='font-size: 10.5pt; font-family: 黑体'>地址：</span><span
				style='font-size: 10.5pt; font-family: 楷体_GB2312'>吉林省长春市人民大街5268号</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>C5
				工商管理类(净月校区)(包含专业:会计学、市场营销、财务管理、人力资源管理)(专业备注:入学一年后按成绩志愿分专业培养)<span
				style='mso-tab-count: 1'></span>1<span style='mso-tab-count: 1'></span>3850</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>E3 地理信息科学<span
				style='mso-tab-count: 1'></span>1<span style='mso-tab-count: 1'></span>3850</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>E5
				环境科学与工程类(净月校区)(包含专业:环境工程、环境科学)(专业备注:入学一年半后按成绩志愿分专业培养)<span
				style='mso-tab-count: 1'></span>1<span style='mso-tab-count: 1'></span>3850</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>A5
				英语(师范)(国家公费师范生)(招收英语语种考生)(外语口试)<span style='mso-tab-count: 1'></span>2<span
				style='mso-tab-count: 1'></span>免费</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>A6 数学与应用数学(师范)(国家公费师范生)<span
				style='mso-tab-count: 1'></span>2<span style='mso-tab-count: 1'></span>免费</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>A9 化学(师范)(国家公费师范生)<span
				style='mso-tab-count: 1'></span>1<span style='mso-tab-count: 1'></span>免费</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>AA 生物科学(师范)(国家公费师范生)<span
				style='mso-tab-count: 1'></span>1<span style='mso-tab-count: 1'></span>免费</span>
		</p>
		<p style='margin: 0cm'>
			<span style='font-size: 10.5pt; font-family: 黑体'> 备注：</span><span
				style='font-size: 10.5pt; font-family: 楷体_GB2312'>认同四川省少数民族地区加分项目，但分值最高20分。
			</span>
		</p>
		<br>
		<p style='margin: 0cm'>
			<span style='font-size: 21.5pt; font-family: 黑体'>四、理工类预科</span>
		</p>
		<p style='margin: 0cm'>
			<span style='font-size: 14.0pt; font-family: 黑体'>(一)普通预科</span>
		</p>
		<p style='margin: 0cm'>
			<span style='font-size: 14.0pt; font-family: 黑体'>1．本科提前批预科</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 黑体'>学校代号、名称及专业代号、名称<span
				style='mso-tab-count: 1'></span>计划<span style='mso-tab-count: 1'></span>收费
			</span>
		</p>
		<p style='margin: 0cm;'>
			<span style='font-size: 10.5pt; font-family: 黑体'><c:out value="${yx.yxdh }"></c:out> 东北师范大学<span
				style='mso-tab-count: 1'></span>3
			</span>
		</p>
		<p style='margin: 0cm'>
			<span style='font-size: 10.5pt; font-family: 黑体'>地址：</span><span
				style='font-size: 10.5pt; font-family: 楷体_GB2312'>吉林省长春市人民大街5268号</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>Y1 少数民族预科(心理学)<span
				style='mso-tab-count: 1'></span>1<span style='mso-tab-count: 1'></span>3850</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>Y2 少数民族预科(材料物理)<span
				style='mso-tab-count: 1'></span>1<span style='mso-tab-count: 1'></span>3850</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>Y3 少数民族预科(地理信息科学)<span
				style='mso-tab-count: 1'></span>1<span style='mso-tab-count: 1'></span>3850</span>
		</p>
		<p style='margin: 0cm'>
			<span style='font-size: 10.5pt; font-family: 黑体'> 备注：</span><span
				style='font-size: 10.5pt; font-family: 楷体_GB2312'>培养地点：东北师范大学
			</span>
		</p>
		<br>
		<p style='margin: 0cm'>
			<span style='font-size: 21.5pt; font-family: 黑体'>五、艺术类院校</span>
		</p>
		<br>
		<p style='margin: 0cm'>
			<span style='font-size: 15.5pt; font-family: 黑体'>(一)艺术本科提前批院校</span>
		</p>
		<p style='margin: 0cm'>
			<span style='font-size: 14.0pt; font-family: 黑体'>2．单独举行专业考试(计划分到四川的省外艺术院校)</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 黑体'>学校代号、名称及专业代号、名称<span
				style='mso-tab-count: 1'></span>计划<span style='mso-tab-count: 1'></span>收费
			</span>
		</p>
		<p style='margin: 0cm;'>
			<span style='font-size: 10.5pt; font-family: 黑体'><c:out value="${yx.yxdh }"></c:out> 东北师范大学<span
				style='mso-tab-count: 1'></span>2
			</span>
		</p>
		<p style='margin: 0cm'>
			<span style='font-size: 10.5pt; font-family: 黑体'>地址：</span><span
				style='font-size: 10.5pt; font-family: 楷体_GB2312'>吉林省长春市人民大街5268号</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>11 音乐学(钢琴，师范)<span
				style='mso-tab-count: 1'></span>1<span style='mso-tab-count: 1'></span>12000</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>13 音乐学(声乐，师范)<span
				style='mso-tab-count: 1'></span>1<span style='mso-tab-count: 1'></span>12000</span>
		</p>
		<p style='margin: 0cm'>
			<span style='font-size: 10.5pt; font-family: 黑体'> 备注：</span><span
				style='font-size: 10.5pt; font-family: 楷体_GB2312'>认同四川省少数民族地区加分项目，但分值最高20分。净月校区就读。
			</span>
		</p>
		<span><br clear=all
			style='mso-special-character: line-break; page-break-before: always'></span>
		<p style='margin: 0cm'>
			<span style='font-size: 21.5pt; font-family: 黑体'>0522<span
				style='mso-tab-count: 1'></span>东北师范大学(中外合作办学)
			</span>
		</p>
		<br>
		<p style='margin: 0cm'>
			<span style='font-size: 21.5pt; font-family: 黑体'>一、理工类本科</span>
		</p>
		<br>
		<p style='margin: 0cm'>
			<span style='font-size: 15.5pt; font-family: 黑体'>(一)本科提前批录取院校</span>
		</p>
		<p style='margin: 0cm'>
			<span style='font-size: 14.0pt; font-family: 黑体'>6．本科</span>
		</p>
		<p style='margin: 0cm'>
			<span style='font-size: 14.0pt; font-family: 黑体'>(5)教育部直属师范院校</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 黑体'>学校代号、名称及专业代号、名称<span
				style='mso-tab-count: 1'></span>计划<span style='mso-tab-count: 1'></span>收费
			</span>
		</p>
		<p style='margin: 0cm;'>
			<span style='font-size: 10.5pt; font-family: 黑体'>0522 东北师范大学<span
				style='mso-tab-count: 1'></span>3
			</span>
		</p>
		<p style='margin: 0cm'>
			<span style='font-size: 10.5pt; font-family: 黑体'>地址：</span><span
				style='font-size: 10.5pt; font-family: 楷体_GB2312'>吉林省长春市人民大街5268号</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>G1 会计学(中外合作办学)<span
				style='mso-tab-count: 1'></span>2<span style='mso-tab-count: 1'></span>28000</span>
		</p>
		<p style='margin: 0cm; tab-stops: right 336.0pt;'>
			<span style='font-size: 10.5pt; font-family: 宋体'><span
				style='mso-spacerun: yes'>&nbsp;</span>G3 计算机科学与技术(中外合作办学)<span
				style='mso-tab-count: 1'></span>1<span style='mso-tab-count: 1'></span>19000</span>
		</p>
		<p style='margin: 0cm'>
			<span style='font-size: 10.5pt; font-family: 黑体'> 备注：</span><span
				style='font-size: 10.5pt; font-family: 楷体_GB2312'>认同四川省少数民族地区加分项目，但分值最高20分。只招有专业志愿考生，不接受调剂
			</span>
		</p>
	</div>
</body>
</html>