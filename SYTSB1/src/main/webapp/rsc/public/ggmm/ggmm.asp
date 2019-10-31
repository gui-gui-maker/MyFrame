<%SiteRoot="../../"%>

<!--#include file="../../n/Inc/Conn.asp"-->
<!--#include file="../../n/Inc/Config.asp"-->

<%

'广告展示页面
'2007年08月28日 星期二 00:43:49 lybykw

Id=getValue("Id",0)	'0表示字符串，1表示数字，2表示日期，3表示不过滤
if(isNullCheck(Id)) then
	response.write("document.write('广告ID号不能为空。');")
	response.end
end if

OpenDatabase

gg_ms=0

sql="select * from "&TDN&"Ad where gg_sh=1 and Id="&Id
rsConn sql,0,rs
if(rs.eof and rs.bof) then
	set rs=nothing
	closeDatabase
	response.write("//document.write('没有找到相关广告。');")
	response.end
else
	Id=rs("Id")
	gg_ms=rs("gg_ms")
	gg_fl=rs("gg_fl")
	gg_lx=rs("gg_lx")
	gg_mc=rs("gg_mc")
	gg_dz=rs("gg_dz")
	gg_gg=rs("gg_gg")
	gg_ljdz=rs("gg_ljdz")
	gg_ckdk=rs("gg_ckdk")
	gg_gqsj=rs("gg_gqsj")
	gg_hit=rs("gg_hit")
	gg_zs=rs("gg_zs")
	gg_sh=rs("gg_sh")
	gg_ps=rs("gg_ps")
	webSite=rs("webSite")
	rs.close
	set rs=nothing
	closeDatabase

	Arr_gg_gg=split(gg_gg,"~")

end if

if(gg_ms=1) then
	PicAdSub
elseif(gg_ms=2) then
	FlashAdSub
elseif(gg_ms=3) then
	HtmlAdSub
elseif(gg_ms=4) then
	JavascriptAdSub
end if

Sub PicAdSub
	response.write("document.write(""<a href='"&gg_ljdz&"' target='"&gg_ckdk&"'><img src='"&gg_dz&"' width='"&Arr_gg_gg(0)&"' height='"&Arr_gg_gg(1)&"' border='0'></a>"");")
End Sub

Sub FlashAdSub
	response.write("document.write(""<object classid='clsid:D27CDB6E-AE6D-11CF-96B8-444553540000' id='obj1' codebase='http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,40,0' border='0' width='"&Arr_gg_gg(0)&"' height='"&Arr_gg_gg(1)&"'> 	<param name='movie' value='"&gg_dz&"'> 	<param name='wmode' value='opaque'> 	<param name='quality' value='High'> 	<param name='menu' value='True'> 	<embed src='"&gg_dz&"' pluginspage='http://www.macromedia.com/go/getflashplayer' type='application/x-shockwave-flash' name='obj1' width='"&Arr_gg_gg(0)&"' height='"&Arr_gg_gg(1)&"' quality='High'> </object>"");")
End Sub

Sub HtmlAdSub
	response.write("document.write(""<iframe name='chinaddv_ads' src='"&SiteRoot&"Public/ggmm/ggmmIframe.asp?Id="&Id&"' width='"&Arr_gg_gg(0)&"' height='"&Arr_gg_gg(1)&"' frameborder='0' border='0' marginwidth='0' marginheight='0' vspace='0' hspace='0' allowtransparency='true' scrolling='no'></iframe>"");")
End Sub

Sub JavascriptAdSub
	response.write(gg_zs)
End Sub




%>
