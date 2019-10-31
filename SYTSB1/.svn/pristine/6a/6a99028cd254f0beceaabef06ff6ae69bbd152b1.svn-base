<%@page import="java.io.PrintWriter"%>
<%@page import="com.khnt.rtbox.config.bean.SpecialCharacter"%>
<%@page import="java.util.List"%>
<%@page import="com.khnt.rtbox.config.service.SpecialCharacterService"%>
<%@page import="javax.servlet.ServletContext"%>  
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>  
<%@page import="org.springframework.context.ApplicationContext"%> 
<%@page contentType="text/html; charset=UTF-8" %>

<%
    ServletContext sc = this.getServletContext();  
    ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(sc);  
    SpecialCharacterService specialCharacterService = (SpecialCharacterService) ac.getBean("specialCharacterService");

    List<SpecialCharacter> list = specialCharacterService.getAllData();
    
    int begin = 0, total = list.size(), end = begin + total;
    int size = total/30;
%>
<html>
<title>特殊字符列表</title>
<%@include file="/rtbox/public/base.jsp"%>
<script type="text/javascript" src="/k/kui/frame/main.js"></script> 
<SCRIPT type="text/JavaScript">
    var size =<%=size%>;
    function Show(n) {
        for (var i = 1; i <= size; i++) {
            if (i == n) {
                document.all["id" + n].style.display = 'block';
                if (i == 1)
                    document.all["Tabletd" + n].className = 'menustyle9';
                else
                    document.all["Tabletd" + n].className = 'menustyle5';
            }
            else {
                document.all["id" + i].style.display = 'none';
                document.all["Tabletd" + i].className = 'menustyle3';
            }
            if (n != 1) {
                document.all["Tabletd1"].className = 'menustyle8';
            }
        }
    }
    
function tip(){
    	
}

$(function(){
	$("Table")
});
</SCRIPT>
<STYLE>
    .menustyle3 {
        position: relative;
        left: 1px;
        top: 1px;
        background: #ffffff;
        cursor: default;
        BORDER-RIGHT: #5E95DF 0px solid;
        BORDER-TOP: #ffffff 1px solid;
        FONT-SIZE: 10pt;
        BORDER-LEFT: #5E95DF 1px solid;
        BORDER-BOTTOM: #ffffff 1px solid
    }

    .menustyle4 {
        cursor: default;
        BORDER-RIGHT: #5E95DF 1px solid;
        BORDER-TOP: #000000 0px solid;
        FONT-SIZE: 10pt;
        BORDER-LEFT: #ffffff 1px solid;
        BORDER-BOTTOM: #5E95DF 1px solid
    }

    .menustyle5 {
        cursor: default;
        background: #e7edf3;
        BORDER-RIGHT: #ffffff 0px solid;
        BORDER-TOP: #ffffff 1px solid;
        FONT-SIZE: 10pt;
        BORDER-LEFT: #ffffff 2px solid;
        BORDER-BOTTOM: #ffffff 0px solid
    }

    .menustyle8 {
        position: relative;
        left: 1px;
        top: 1px;
        background: #ffffff;
        cursor: default;
        BORDER-RIGHT: #5E95DF 0px solid;
        BORDER-TOP: #ffffff 1px solid;
        FONT-SIZE: 10pt;
        BORDER-LEFT: #ffffff 1px solid;
        BORDER-BOTTOM: #ffffff 1px solid
    }

    .menustyle9 {
        cursor: default;
        background: #e7edf3;
        BORDER-RIGHT: #5E95DF 0px solid;
        BORDER-TOP: #ffffff 1px solid;
        FONT-SIZE: 10pt;
        BORDER-LEFT: #ffffff 1px solid;
        BORDER-BOTTOM: #ffffff 0px solid
    }

    .queryButton {
        border-right: #000000 1px solid;
        border-top: #ffffff 1px solid;
        border-left: #ffffff 1px solid;
        border-bottom: #000000 1px solid;
        font-size: 12px;
        height: 20;
        text-align: center;
        background-color: transparent;
    }

    .queryButton10 {
        border-right: #ffffff 1px solid;
        border-top: #000000 1px solid;
        border-left: #000000 0px solid;
        border-bottom: #ffffff 1px solid;
        font-size: 12px;
        height: 20;
        text-align: center;
        background-color: transparent;
    }

</STYLE>
<body bgcolor="#e7edf3">
<table width="98%" border="0" align="center" cellpadding="4" cellspacing="0">
    <tr>
        <%for (int i = 1; i <= size; i++) {%>
        <td align="center" class="<%=i==1?"menustyle5":"menustyle3"%>" onClick="Show(<%=i%>)" id="Tabletd<%=i%>">特殊字符<%=i%>
        </td>
        <%}%>
        <td></td>
    </tr>
    <tr>
        <td colspan="<%=size%>" class="menustyle4" valign="top">
            <%for (int j = 0; j < size+1;j++) {%>
            <div id="id<%=j+1%>" style="display:<%=j==0?"block":"none"%>">
                <table width="98%" border="0" align="center" bordercolor="0" cellpadding="0" cellspacing="0"
                       bgcolor="#e7edf3">
                    <tr>
                        <td>
                            <br>
                            <table width="98%" border="0" align="center" bordercolor="0" cellpadding="4" cellspacing="0"
                                   bgcolor="#e7edf3">
                                <%
                                    for (int r = 0; r < 5; r++) {
                                        out.println("<tr>");
                                        for (int c = 0; c < 10; c++) {
                                %>
                                <td width=10% class="queryButton" onclick="kk(this,'<%=begin>=end?"":list.get(begin).getValue()%>')"
                                    style="font-size:18pt;"><%-- <div class="tips" ><%=begin>=end?"":list.get(begin).getName()%></div> --%><%=begin>=end?"":list.get(begin).getValue()%>
                                </td>
                                <%		
                                            	begin++;
                                        }
                                        out.println("</tr>");
                                    }
                                %>
                            </table>
                            <br>
                        </td>
                    </tr>
                </table>
            </div>
            <%}%>
        </td>
    </tr>
</table>

<table width="98%" border="0" align="center" bordercolor="0" cellpadding="0" cellspacing="0" bgcolor="">
    <br>
    <tr>
        <td width="10%"></td>
        <td id="showbig" class="queryButton" style="font-size:32pt;" colspan="10" height="50" width="25%" rowspan="2">
            &nbsp;</td>
        <td width="25%"></td>
        <td colspan="10" height="25" align="right" width="40%">
        </td>
    </tr>
    <tr>
        <td width="10%"></td>
        <td width="25%"></td>
        <td colspan="10" height="25" align="right" width="40%">
            <input type="button" value="插 入" class="queryButton" onclick="insert()">&nbsp;&nbsp;
            <input id="copy" type="button" value="复 制" class="queryButton" onclick="copyText()">&nbsp;&nbsp;&nbsp;
            <input type="button" value="取 消" class="queryButton" onclick="window.close()">&nbsp;&nbsp;&nbsp;
        </td>
    </tr>
    <tr>
        <td width="10%" colspan="14">&nbsp;</td>
    </tr>
    <tr>
        <td width="10%" colspan="14" style="font-size:10pt;">无找到需要的特殊字符，请自行添加。
        </td>
    </tr>
</table>

</body>
</html>
<script>
    var globalObj;
    var thsstr = 0;
    function kk(ths, str) {
        if (globalObj) {
            globalObj.className = "queryButton";
        }
        ths.className = "queryButton10";
        document.all["showbig"].innerHTML = str;
        globalObj = ths;
        thsstr = str;
    }
    function insert() {
    	/* if(thsstr != 0 && parent.focus_element.range){
    		parent.focus_element.selection.removeAllRanges();
    	    // 把光标移动到内容之后
    		// range.setStart(div, txt.length);
    		// range.setEnd(div, txt.length);		 
    		// selection.addRange(range);
    	} */
    	var el = share.data('focus_element');
         if (thsstr != 0 && el){
        	 if(el.tagName=="DIV"){
        		 el.innerHTML += thsstr;  
        	 }else{
        		 el.value += thsstr; 
        	 }
         }
         api.close();
    }
    function copyText()
    {
        if (thsstr != 0){
        	window.clipboardData.setData('text', thsstr);
        }
    }
    
  
</script>

