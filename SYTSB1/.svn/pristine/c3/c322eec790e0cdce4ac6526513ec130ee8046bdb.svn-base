<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
d
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
<title></title>
<jsp:include page="/k/kui-base.jsp"/>
<script type="text/javascript">
    loadCoreLibrary("form");
</script>
<script type="text/javascript">

function BtnClick() {
    window.external.ButtonClick(0);
}

function BtnEnable() {
    window.external.ButtonEnable(0, 1);
}

function BtnDisable() {
    window.external.ButtonEnable(0, 0);
}

function BtnHide() {
    window.external.ButtonVisible(0, 0);
}

function BtnShow() {
    window.external.ButtonVisible(0, 1);
}

function SetStatusText() {
    window.external.SetStatusText(0, "状态栏测试文本", "http://www.baidu.com");

}

function BtnDelTemp() {
    window.external.DelTempFiles();
}

function BtnLockScreenState() {
    var state = window.external.GetLockScreenState();
    alert(state);
}

function BtnLockScreen() {
    window.external.LockScreen(1);
}

function BtnUnlockScreen() {
    window.external.LockScreen(0);
}

function BtnReadIni() {
    var value = window.external.ReadIni("main", "title", "test.ini");
    alert(value);
}

function BtnWriteIni() {
    window.external.WriteIni("main", "title", "WriteIni 测试", "test.ini");
}

function BtnStartTrayFlicker() {
    window.external.StartTrayFlicker("Skin/t1.ico;Skin/T2.ico", 400, "托盘动画测试");
}

function BtnStopTrayFlicker() {
    window.external.StopTrayFlicker();
}


function BtnQuit() {
    window.external.Quit();
}

function BtnIsShell() {
    var v = window.external.IsShell();
    alert(v);
}

function BtnShellVersion() {
    var v = window.external.ShellVersion();
    alert(v);
}

function BtnGetApplicationPath() {
    var path = window.external.GetApplicationPath();
    alert(path);
}

function BtnCreateNoShowPopup() {
    /*
     方法名称： PopupMessageEx()
     函数原型：window.external.PopupMessageEx(key, url, style, width, height, timer);
     参数说明： key -- 弹窗的查询关键字， 外壳内置一个map对象， 可以管理和容纳多个弹窗，该参数用于查询和控制弹窗
     url - 弹窗的URL。
     style - 样式表。 1-居中显示， 2-圆角显示。
     */
    var ret = window.external.PopupMessageEx("testkey1","demo/explorer/msg.jsp",2,250,150);
    if (!ret) {
        alert(window.external.GetLastErrorMessage());
    }
}
function BtnShowPopup()
{
    var ret = window.external.ShowPopup("testkey1");
    if (!ret) {
        alert(window.external.GetLastErrorMessage());
    }
}
function BtnHidePopup()
{
    var ret = window.external.HidePopup("testkey1");
    if (!ret) {
        alert(window.external.GetLastErrorMessage());
    }
}
function BtnClosePopup()
{
    var ret = window.external.ClosePopup("testkey1");
    if (!ret) {
        alert(window.external.GetLastErrorMessage());
    }
}


function BtnCallInternetOption() {
    window.external.CallInternetOption();
}

function hideFrameWin() {
    window.external.HideFrameWindow();
    window.setTimeout("window.external.ShowFrameWindow()", 3000);
}
var win;
function winOpen() {
    win = window.open("demo/explorer/t.html", "", "width=500,height=600;");
    win.run_test();

}
function opJs() {
    win.run_test();
}

function BtnGetAutoStartup() {
    var ret = window.external.IsAutoStartup();
    alert(ret);
}

function BtnAutoStart() {
    var ret = window.external.SetAutoStartup(true);
    if (!ret) {
        var error = window.external.GetLastError();
        alert(error);
    }
}

function BtnUndoAutoStart() {
    var ret = window.external.SetAutoStartup(false);
    if (!ret) {
        var error = window.external.GetLastError();
        alert(error);
    }
}

function BtnGetTrayTooltips() {
    var ret = window.external.GetTrayTooltips();
    alert(ret);
}
function BtnSetTrayTooltips() {
    window.external.SetTrayTooltips("托盘提示测试");
}

function CloseFrameTest() {
    window.external.HideFrameWindow();
    window.setTimeout("window.external.ShowFrameWindow()", 3000);
}

function BtnSetTitle() {
    window.external.SetWindowText("JS 设置window标题测试！");
}
function BtnGetTitle() {
    var vText = window.external.GetWindowText();
    alert(vText);
}
function BtnSetWindowState() {
    // 0 - 正常 1- 最小化 2- 最大化
    // 设置框架窗口最大化示例
    window.external.SetWindowState(2);
}
function BtnGetWindowState() {
    var nState = window.external.GetWindowState();
    alert(nState);
}

function BtnResizeWindow() {
    window.external.SetWindowPos(50, 50, 600, 500);
}

function BtnCenterWindow() {
    window.external.CenterWindow();
}

function BtnUpdateFrame() {
    window.external.UpdateFrame();
}

function opComp() {
    window.location.href = 'demo/explorer/comtest.htm';
}

function opTest() {
    //window.external.PopupMessageEx("Popup message",".\open_test.htm",1,500,300);
    win = window.open("demo/demo_list.jsp", "_blank", "width=800,height=600;");
}
function sessionTest() {
    window.external.PopupMessageEx("Popup message","demo/qm/qm_1_list.jsp",1,800,600);
}

function setTrayMenu()
{

    var trayMenu = [
        { type:"cmd", text:"显示主窗口", value:"showframe" },
        { type:"",text:"",value:"" },
        { type:"url", text:"公司首页", value:"http://www.baidu.com" },
        { type:"js", text:"弹窗测试", value:"winOpen" },
        { type:"menu", text:"下级菜单", value:
                [
                    { type:"url", text:"公司首页", value:"http://www.baidu.com" },
                    { type:"", text:"",value:"" },
                    { type:"js", text:"扩展弹窗测试", value:"opTest" }
                ]
        },

        { type:"", text:"","value":"" },
        { type:"cmd", text:"退出系统", value:"quit" }
    ];

    var json_string = JSON.stringify(trayMenu);
    //alert(json_string);

    // SetTrayMenu() API 默认JSON字串是非UTF8编码， 如果传入字串为UTF8串， 则需要设定第二个参数为true
    //var ret = window.external.SetTrayMenu(json_string, true /*utf8=true*/ );
    var ret =  window.external.SetTrayMenu(json_string);

    if (!ret) {
        alert(window.external.GetLastErrorMessage());
    }
}

function SetIEComp()
{
    var obj = document.getElementById( "iec" );
    var new_ver = obj.options[obj.selectedIndex].value;

    var old_ver = window.external.GetCompatibleVersion();
    if (old_ver == new_ver) {
        alert("选定版本和当前IE兼容版本相同");
    }
    else {
        var ret = window.external.SetCompatibleVersion(new_ver);
        if (ret) {
            if(confirm("IE兼容模式已经发生改变！需要重新启动，您是否重启程序？")) {
                window.external.Restart();
            }
        }
    }
}
function execMainJS()
{
    // ExecuteJS() API 原型： ExecuteJS(strKey, strFunc, p1, p2, p3, p4);
    // 参数说明：
    //	strKey -- 弹窗的关键字，如果为空串则为主框架窗体
    //  strFunc -- 窗体内当前文档中包含的js 函数名称
    //  p1 - p4 为JS函数调用的入参， 这些参数均为可选， 最大只支持4个入参
    // 返回说明：
    //    可返回JS所支持的任意类型
    //
    var result = window.external.ExecuteJS('', 'extAlert', "测试消息");

    alert (result);
}
function extAlert(str){
    return str;
}
</script>
<style type="text/css">
    .l-button{
        margin-top: 5px;
    }
</style>
</head>
<body>
<div class="item-tm" isTitle="true">
    <div class="l-page-note">
        <div class="l-page-note-div">
            外壳程序下载：<a href="demo/explorer/explorerDemo20141203.exe">explorerDemo20141203.exe</a><br>
            该外壳程序使用WinRar打包，项目组可以选择其它安装程序制作工具，为了方便大家学习，该包中打入了EncpyXML.exe和Config.xml文件，实际项目中请不要打入这两个文件
        </div>
    </div>
</div>
<input class="l-button" type="button" value="退出程序" onclick="BtnQuit()">
<input class="l-button" type="button" value="设置托盘菜单" onclick = "setTrayMenu()" >
<br>
<input class="l-button" type="button" value="设置窗体大小" onclick="BtnResizeWindow()">
<input class="l-button" type="button" value="窗体居中" onclick="BtnCenterWindow()">
<input class="l-button" type="button" value="设置标题" onclick="BtnSetTitle()">
<input class="l-button" type="button" value="获取标题" onclick="BtnGetTitle()">
<input class="l-button" type="button" value="设置窗体最大化" onclick="BtnSetWindowState()">
<input class="l-button" type="button" value="获取窗体状态" onclick="BtnGetWindowState()">
<br>
<input class="l-button" type="button" value="删除临时文件" onclick="BtnDelTemp()">
<input class="l-button" type="button" value="调用Internet选项" onclick="BtnCallInternetOption()">
<br>
<input class="l-button" type="button" value="INI读取" onclick="BtnReadIni()">
<input class="l-button" type="button" value="INI写入" onclick="BtnWriteIni()">
<input class="l-button" type="button" value="是否为壳程序" onclick="BtnIsShell()">
<input class="l-button" type="button" value="外壳版本号" onclick="BtnShellVersion()">
<input class="l-button" type="button" value="获取安装路径" onclick="BtnGetApplicationPath()">
<br>
<input class="l-button" type="button" value="获取屏幕锁定状态" onclick="BtnLockScreenState()">
<input class="l-button" type="button" value="锁定屏幕" onclick="BtnLockScreen()">
<input class="l-button" type="button" value="解除屏幕锁定" onclick="BtnUnlockScreen()">
<br>
<input class="l-button" type="button" value="任务栏图标闪烁" onclick="BtnStartTrayFlicker()">
<input class="l-button" type="button" value="停止任务栏图标闪烁" onclick="BtnStopTrayFlicker()">
<input class="l-button" type="button" value="获取任务栏提示" onclick="BtnGetTrayTooltips()">
<input class="l-button" type="button" value="设置任务栏提示" onclick="BtnSetTrayTooltips()">
<br>
<input class="l-button" type="button" value="创建非显示弹窗" onclick="BtnCreateNoShowPopup()">
<input class="l-button" type="button" value="显示弹窗" onclick="BtnShowPopup()">
<input class="l-button" type="button" value="隐藏弹窗" onclick="BtnHidePopup()">
<input class="l-button" type="button" value="销毁弹窗" onclick="BtnClosePopup()">
<br>
<input class="l-button" type="button" value="是否自启动" onclick="BtnGetAutoStartup()">
<input class="l-button" type="button" value="设定自启动" onclick="BtnAutoStart()">
<input class="l-button" type="button" value="取消自启动" onclick="BtnUndoAutoStart()">
<input class="l-button" type="button" value="升级更新" onclick="BtnUpdateFrame()">
<br>
<input class="l-button" type="button" value="显示隐藏的主框架窗口（3秒后显示）" onclick="hideFrameWin()">
<input class="l-button" type="button" value="弹窗" onclick="winOpen()">
<input class="l-button" type="button" value="操作弹窗js" onclick="opJs()">
<br>
<input class="l-button" type="button" value="默认浏览器打开" onclick = "window.external.OpenUrl(kui['base']+'demo/demo_list.jsp',0);" >
<input class="l-button" type="button" value="IE浏览器打开" onclick = "window.external.OpenUrl('demo/demo_list.jsp',1);" >
<input class="l-button" type="button" value="客户端打开" onclick = "window.external.OpenUrl(kui['base']+'demo/demo_list.jsp',2);">
<br>
<input class="l-button" type="button" value="兼容模式测试" onclick="opComp()">
<input class="l-button" type="button" value="扩展弹窗测试" onclick="opTest()">
<input class="l-button" type="button" value="Session测试" onclick="sessionTest()">
<br>
<select class="l-button" id="iec">
    <option value="7000">IE7(7000)</option>
    <option value="8000">IE8(8000)</option>
    <option value="8888">IE8(8888)</option>
    <option value="9000">IE9(9000)</option>
    <option value="9999">IE9(9999)</option>
    <option value="10000">IE10(10000)</option>
    <option value="10001">IE10(10001)</option>
    <option value="11000">IE11(11000)</option>
    <option value="11001">IE11(11001)</option>
</select>
<input class="l-button" type="button" value="设置IE兼容模式" onclick = "SetIEComp()" >
<input type="button" value="调用主窗口JS" onclick = "execMainJS()" >
</body>
</html>
