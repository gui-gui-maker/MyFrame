
<%@ page import="com.khnt.security.util.SecurityUtil" %>
<%@ page import="com.khnt.security.CurrentSessionUser" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<%@include file="/k/kui-base.jsp"%>

<style>
*{ box-sizing: content-box;}
a{text-decoration: none; transition: all .5s;}
/* ul{list-style: none;} */
html{font-family: '微软雅黑';}
.fl{float: left;}
.fr{float: right;}
.cl{clear: both;}
.none{display: none;}
/* .block{display: block!important;} */
/* table{border-collapse:collapse;} */
/* a,p,span,label,input,select{font-size: 14px;font-family: '微软雅黑';} */
</style>
<body>
 	<style>
.rtcolor_picker{
	padding: 0px 10px 10px;
	z-index: 1;
	width: 170px;
}
.rtcolor_items{
	border-color: #000;
	border-width: 0 1px 1px 0;
	border-style: solid;
	width: 156px;
	margin-top: 10px;
}
.rtcolor_btn{
	line-height:20px;
	margin-top:5px;
}
.rtcolor_extend{
	margin-top: 10px;
	width: 156px;
	position: relative;
}
.rtcolor_extend .toolbar_button{
	display: inline-block;
	padding: 0px 2px;
}
.rtcolor_transparent{
	display:inline-block;
	float:left;
	margin-right:8px;
}
.rtcolor_hex{
	display:inline-block;
	width:76px;
	height:18px;
	line-height:18px;
	color:#666;
	float:left;
	clear:right;
	margin-left:0px;
	position:relative;
	margin-top:4px;
}
.rtcolor_hex input{
	position:absolute;
	width:54px;
	height:16px;
	line-height:16px;
	color:#666;
	margin:0px;
	padding:0px 2px;
	font-size:12px;
	top:0px;
	left:10px;
	border:1px solid #ccc;
	border-radius:3px;
}

.rtcolor_items div{
	height: 12px;
	width: 12px;
	float: left;
	border: #000 solid 1px;
	border-width: 1px 0 0 1px;
}
.rtcolor_items div:hover{
	position: relative;
	border: #FFF solid 2px;
	margin: -1px -2px -2px -1px;
}
.rtcolor_items .selected{
	position: relative;
	border: #f7ddaa solid 2px;
	margin: -1px -2px -2px -1px;
}
.rtcolor_items .clear{
	border: 0px;
	float: none;
	height: 0px;
	width: 100%;
	clear: both;
}
.options_menu{
	display: none;
}

</style>			
				 <div id ="ui_container" >
							<div id="rtcolor_picker" class="menu rtcolor_picker "
								style="top: 102px; left: 500px; display: block;" >
								<div class="rtcolor_items" col="(0,0,0,0">
									<div style="background-color: rgb(255, 255, 255);"
										col="255,255,255" class=""></div>
									<div style="background-color: rgb(229, 229, 229);"
										col="229,229,229"></div>
									<div style="background-color: rgb(207, 207, 207);"
										col="207,207,207"></div>
									<div style="background-color: rgb(184, 184, 184);"
										col="184,184,184"></div>
									<div style="background-color: rgb(161, 161, 161);"
										col="161,161,161" class=""></div>
									<div style="background-color: rgb(138, 138, 138);"
										col="138,138,138"></div>
									<div style="background-color: rgb(115, 115, 115);"
										col="115,115,115"></div>
									<div style="background-color: rgb(92, 92, 92);" col="92,92,92"></div>
									<div style="background-color: rgb(69, 69, 69);" col="69,69,69"></div>
									<div style="background-color: rgb(50, 50, 50);" col="50,50,50"></div>
									<div style="background-color: rgb(23, 23, 23);" col="23,23,23"></div>
									<div style="background-color: rgb(0, 0, 0);" col="0,0,0"></div>
									<div class="clear" col="(0,0,0,0"></div>
								</div>
								<div class="rtcolor_items" col="(0,0,0,0">
									<div style="background-color: rgb(255, 204, 204);"
										col="255,204,204"></div>
									<div style="background-color: rgb(255, 230, 204);"
										col="255,230,204"></div>
									<div style="background-color: rgb(255, 255, 204);"
										col="255,255,204"></div>
									<div style="background-color: rgb(230, 255, 204);"
										col="230,255,204"></div>
									<div style="background-color: rgb(204, 255, 204);"
										col="204,255,204"></div>
									<div style="background-color: rgb(204, 255, 230);"
										col="204,255,230"></div>
									<div style="background-color: rgb(204, 255, 255);"
										col="204,255,255"></div>
									<div style="background-color: rgb(204, 229, 255);"
										col="204,229,255"></div>
									<div style="background-color: rgb(204, 204, 255);"
										col="204,204,255"></div>
									<div style="background-color: rgb(229, 204, 255);"
										col="229,204,255"></div>
									<div style="background-color: rgb(255, 204, 255);"
										col="255,204,255"></div>
									<div style="background-color: rgb(255, 204, 230);"
										col="255,204,230"></div>
									<div style="background-color: rgb(255, 153, 153);"
										col="255,153,153"></div>
									<div style="background-color: rgb(255, 204, 153);"
										col="255,204,153"></div>
									<div style="background-color: rgb(255, 255, 153);"
										col="255,255,153"></div>
									<div style="background-color: rgb(204, 255, 153);"
										col="204,255,153"></div>
									<div style="background-color: rgb(153, 255, 153);"
										col="153,255,153"></div>
									<div style="background-color: rgb(153, 255, 204);"
										col="153,255,204"></div>
									<div style="background-color: rgb(153, 255, 255);"
										col="153,255,255"></div>
									<div style="background-color: rgb(153, 204, 255);"
										col="153,204,255" class="selected"></div>
									<div style="background-color: rgb(153, 153, 255);"
										col="153,153,255"></div>
									<div style="background-color: rgb(204, 153, 255);"
										col="204,153,255"></div>
									<div style="background-color: rgb(255, 153, 255);"
										col="255,153,255"></div>
									<div style="background-color: rgb(255, 153, 204);"
										col="255,153,204"></div>
									<div style="background-color: rgb(255, 102, 102);"
										col="255,102,102"></div>
									<div style="background-color: rgb(255, 179, 102);"
										col="255,179,102"></div>
									<div style="background-color: rgb(255, 255, 102);"
										col="255,255,102" class=""></div>
									<div style="background-color: rgb(179, 255, 102);"
										col="179,255,102"></div>
									<div style="background-color: rgb(102, 255, 102);"
										col="102,255,102"></div>
									<div style="background-color: rgb(102, 255, 179);"
										col="102,255,179"></div>
									<div style="background-color: rgb(102, 255, 255);"
										col="102,255,255"></div>
									<div style="background-color: rgb(102, 178, 255);"
										col="102,178,255"></div>
									<div style="background-color: rgb(102, 102, 255);"
										col="102,102,255"></div>
									<div style="background-color: rgb(178, 102, 255);"
										col="178,102,255"></div>
									<div style="background-color: rgb(255, 102, 255);"
										col="255,102,255" class=""></div>
									<div style="background-color: rgb(255, 102, 179);"
										col="255,102,179"></div>
									<div style="background-color: rgb(255, 51, 51);"
										col="255,51,51"></div>
									<div style="background-color: rgb(255, 153, 51);"
										col="255,153,51"></div>
									<div style="background-color: rgb(255, 255, 51);"
										col="255,255,51"></div>
									<div style="background-color: rgb(153, 255, 51);"
										col="153,255,51" class=""></div>
									<div style="background-color: rgb(51, 255, 51);"
										col="51,255,51"></div>
									<div style="background-color: rgb(51, 255, 153);"
										col="51,255,153"></div>
									<div style="background-color: rgb(51, 255, 255);"
										col="51,255,255"></div>
									<div style="background-color: rgb(51, 153, 255);"
										col="51,153,255"></div>
									<div style="background-color: rgb(51, 51, 255);"
										col="51,51,255"></div>
									<div style="background-color: rgb(153, 51, 255);"
										col="153,51,255"></div>
									<div style="background-color: rgb(255, 51, 255);"
										col="255,51,255"></div>
									<div style="background-color: rgb(255, 51, 153);"
										col="255,51,153"></div>
									<div style="background-color: rgb(255, 0, 0);" col="255,0,0"></div>
									<div style="background-color: rgb(255, 128, 0);"
										col="255,128,0"></div>
									<div style="background-color: rgb(255, 255, 0);"
										col="255,255,0"></div>
									<div style="background-color: rgb(128, 255, 0);"
										col="128,255,0"></div>
									<div style="background-color: rgb(0, 255, 0);" col="0,255,0"></div>
									<div style="background-color: rgb(0, 255, 128);"
										col="0,255,128"></div>
									<div style="background-color: rgb(0, 255, 255);"
										col="0,255,255"></div>
									<div style="background-color: rgb(0, 127, 255);"
										col="0,127,255"></div>
									<div style="background-color: rgb(0, 0, 255);" col="0,0,255"></div>
									<div style="background-color: rgb(127, 0, 255);"
										col="127,0,255"></div>
									<div style="background-color: rgb(255, 0, 255);"
										col="255,0,255"></div>
									<div style="background-color: rgb(255, 0, 128);"
										col="255,0,128"></div>
									<div style="background-color: rgb(204, 0, 0);" col="204,0,0"></div>
									<div style="background-color: rgb(204, 102, 0);"
										col="204,102,0"></div>
									<div style="background-color: rgb(204, 204, 0);"
										col="204,204,0"></div>
									<div style="background-color: rgb(102, 204, 0);"
										col="102,204,0"></div>
									<div style="background-color: rgb(0, 204, 0);" col="0,204,0"></div>
									<div style="background-color: rgb(0, 204, 102);"
										col="0,204,102" class=""></div>
									<div style="background-color: rgb(0, 204, 204);"
										col="0,204,204"></div>
									<div style="background-color: rgb(0, 102, 204);"
										col="0,102,204"></div>
									<div style="background-color: rgb(0, 0, 204);" col="0,0,204"></div>
									<div style="background-color: rgb(102, 0, 204);"
										col="102,0,204"></div>
									<div style="background-color: rgb(204, 0, 204);"
										col="204,0,204"></div>
									<div style="background-color: rgb(204, 0, 102);"
										col="204,0,102"></div>
									<div style="background-color: rgb(153, 0, 0);" col="153,0,0"></div>
									<div style="background-color: rgb(153, 76, 0);" col="153,76,0"></div>
									<div style="background-color: rgb(153, 153, 0);"
										col="153,153,0"></div>
									<div style="background-color: rgb(77, 153, 0);" col="77,153,0"></div>
									<div style="background-color: rgb(0, 153, 0);" col="0,153,0"></div>
									<div style="background-color: rgb(0, 153, 77);" col="0,153,77"></div>
									<div style="background-color: rgb(0, 153, 153);"
										col="0,153,153"></div>
									<div style="background-color: rgb(0, 76, 153);" col="0,76,153"></div>
									<div style="background-color: rgb(0, 0, 153);" col="0,0,153"></div>
									<div style="background-color: rgb(76, 0, 153);" col="76,0,153"></div>
									<div style="background-color: rgb(153, 0, 153);"
										col="153,0,153"></div>
									<div style="background-color: rgb(153, 0, 77);" col="153,0,77"></div>
									<div style="background-color: rgb(102, 0, 0);" col="102,0,0"></div>
									<div style="background-color: rgb(102, 51, 0);" col="102,51,0"></div>
									<div style="background-color: rgb(102, 102, 0);"
										col="102,102,0"></div>
									<div style="background-color: rgb(51, 102, 0);" col="51,102,0"></div>
									<div style="background-color: rgb(0, 102, 0);" col="0,102,0"></div>
									<div style="background-color: rgb(0, 102, 51);" col="0,102,51"></div>
									<div style="background-color: rgb(0, 102, 102);"
										col="0,102,102"></div>
									<div style="background-color: rgb(0, 51, 102);" col="0,51,102"></div>
									<div style="background-color: rgb(0, 0, 102);" col="0,0,102"></div>
									<div style="background-color: rgb(51, 0, 102);" col="51,0,102"></div>
									<div style="background-color: rgb(102, 0, 102);"
										col="102,0,102"></div>
									<div style="background-color: rgb(102, 0, 51);" col="102,0,51"></div>
									<div style="background-color: rgb(51, 0, 0);" col="51,0,0"></div>
									<div style="background-color: rgb(51, 26, 0);" col="51,26,0"></div>
									<div style="background-color: rgb(51, 51, 0);" col="51,51,0"></div>
									<div style="background-color: rgb(26, 51, 0);" col="26,51,0"></div>
									<div style="background-color: rgb(0, 51, 0);" col="0,51,0"></div>
									<div style="background-color: rgb(0, 51, 26);" col="0,51,26"></div>
									<div style="background-color: rgb(0, 51, 51);" col="0,51,51"></div>
									<div style="background-color: rgb(0, 25, 51);" col="0,25,51"></div>
									<div style="background-color: rgb(0, 0, 51);" col="0,0,51"></div>
									<div style="background-color: rgb(25, 0, 51);" col="25,0,51"></div>
									<div style="background-color: rgb(51, 0, 51);" col="51,0,51"></div>
									<div style="background-color: rgb(51, 0, 26);" col="51,0,26"></div>
									<div class="clear" col="(0,0,0,0"></div>
								</div>
								<div title="透明" class="ico transparent rtcolor_transparent"
									col="(0,0,0,0" style="display: none;"></div>
								<div class="rtcolor_hex" col="(0,0,0,0">
									#<input type="text">
								</div>
								<div class="clear" col="(0,0,0,0"></div>
							</div>
						</div>
			
		</body>				
</html>						