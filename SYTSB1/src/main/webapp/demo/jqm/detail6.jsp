<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head pageStatus="detail">
    <base href="<%=basePath%>">
    <title>iscrollview Demo</title>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>

    <link href="k/jqm/themes/default/jquery.mobile.min.css" media="screen" rel="stylesheet" type="text/css"/>
    <link href="k/jqm/themes/default/jquery.mobile.iscrollview.css" media="screen" rel="stylesheet" type="text/css"/>
    <link href="k/jqm/themes/default/jquery.mobile.iscrollview-pull.css" media="screen" rel="stylesheet" type="text/css"/>
    <script src="k/jqm/jquery2.js" type="text/javascript"></script>
    <script src="k/jqm/jquery.mobile.js" type="text/javascript"></script>
    <script src="k/jqm/iscroll.js" type="text/javascript"></script>
    <script src="k/jqm/jquery.mobile.iscrollview.js" type="text/javascript"></script>
    <script src="k/kui/frame/mobile_lib.js" type="text/javascript"></script>
  </head>
	<div data-role="page" data-control-title="test" id="page5">
    <div data-role="content">
        <form id="formObj" action="rbac/org/saveOrg.do" method="POST" getAction="rbac/org/detail.do?id=100001">
            <div data-role="fieldcontain" data-controltype="textinput">
                <label for="textinput3">
                    	编号
                </label>
                <input name="orgCode" id="textinput3" placeholder="" value="" type="text">
            </div>
            <div data-role="fieldcontain" data-controltype="textinput">
                <label for="textinput4">
                    	名称
                </label>
                <input name="orgName" id="textinput4" placeholder="" value="" type="text">
            </div>
            <div data-role="fieldcontain" data-controltype="textinput">
                <label for="textinput5">
                    	联系电话
                </label>
                <input name="tellphone" id="textinput5" placeholder="" value="" type="text">
            </div>
            <!--  
             <div id="sex" data-role="fieldcontain" data-controltype="radiobuttons">
			            <fieldset data-role="controlgroup" data-type="horizontal">
			                <legend>机构性质</legend>
			                <u:wbSelect type="radio" id="selectmenu1" name="property" code="sys_org_property" attribute="code=sys_org_property"/>
			            </fieldset>
			</div>
			 -->
			     
            <div data-role="fieldcontain" data-controltype="selectmenu">
                <label for="selectmenu1">
                   	 机构性质
                </label> 
                <u:wbSelect  id="selectmenu1" name="property" code="sys_org_property" attribute="code=sys_org_property"/>
            </div>
            
            <div data-role="fieldcontain" data-controltype="textarea">
                <label for="textarea1">
                  	    备注
                </label>
                <textarea name="discrible" id="textarea1" placeholder=""></textarea>
            </div>
            <div data-role="content">
			    <input id="submit" type="submit" data-theme="d" value="Submit" >
			</div>
        </form>
    </div>
</div>
</html>
