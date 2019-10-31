<%@ page contentType="text/html;charset=GBK"%>
<%@ page import="com.khnt.foundation.*,cfd.pub.*"%>
<%@ page import="java.io.File" %>
<%@ page import="java.util.*" %>
<%@ page import="java.net.InetAddress" %>
<%
	String height = request.getParameter("height");
	String width = request.getParameter("width");
	String file_name = request.getParameter("old_vlaue");
	

	InetAddress addr = InetAddress.getLocalHost();
	String ip = addr.getHostAddress().toString();
	
	boolean flag = false  ;
	/*
    if(file_name!=null && !file_name.equals("")){
		Entity entity = new Entity();
		String sql = "select count(1) myCount from pub_upload_file where file_name='" + file_name + "' and xmlcontent is not null";
    	entity.excuteQuery(sql);

		if(entity.getIntegerValue("myCount",0)!= 0 ){
			flag = true ;
		}
	}
    
    String openfile = "" ;
    if(file_name!= null && !file_name.equals("")){
    	String midPath = file_name.substring(0, 6);
    	String xmlpathFile = null;
    	String openpath = null;
    	if(Integer.parseInt(midPath) - Integer.parseInt("201009") >= 0){
    		System.out.println("in");
    		xmlpathFile = (new StringBuilder()).append(Resources.getProperty("WEB_ROOT")).append("/tsjy/jsp/pic_tools/picTlb/").append(midPath+"/").append(file_name.substring(0,file_name.lastIndexOf("."))+".tbl").toString();
    		openpath = (new StringBuilder()).append("tsjy/jsp/pic_tools/picTlb/").append(midPath+"/").append(file_name.substring(0,file_name.lastIndexOf("."))+".tbl").toString();
    	} else {
    		System.out.println("else");
    		xmlpathFile = (new StringBuilder()).append(Resources.getProperty("WEB_ROOT")).append("/tsjy/jsp/pic_tools/picTlb/").append(file_name.substring(0,file_name.lastIndexOf("."))+".tbl").toString();
    		openpath = (new StringBuilder()).append("tsjy/jsp/pic_tools/picTlb/").append(file_name.substring(0,file_name.lastIndexOf("."))+".tbl").toString();
    	}
	    File file = new File(xmlpathFile);
	    if(file.exists()){
	    	openfile = Resources.getProperty("SERVER_ADDRESS")+ openpath ;
	    }
    }*/
%>
<html>
<head>
<title>Visual Graph</title>

<SCRIPT ID=clientEventHandlersVBS LANGUAGE=vbscript>
<!--
Sub window_onload
vg1.Design ""
vg1.register("192.168.92.21")
s = vg1.SystemParams.DefaultPath
vg2.Run s + "listview.tbl"
vg2.vg.ActiveSheet.SetPropertyValue "ie", True
vg2.vg.ActiveSheet.SetPropertyValue "libUrl"

vg3.Run s + "toolbar.tbl"
vg3.vg.ActiveSheet.SetPropertyValue "ie", True
vg3.vg.ActiveSheet.SetPropertyValue "w", "800"
vg3.vg.ActiveSheet.SetPropertyValue "h", "600"
vg3.vg.ActiveSheet.SetPropertyValue "upUrl"
vg3.vg.ActiveSheet.SetPropertyValue "tmpFile", "c:\temp.jpg" 

vg4.Run s + "propwin.tbl"
vg5.Run s + "xruler.tbl"
vg6.Run s + "yruler.tbl"
vg5.vg.ActiveSheet.SetPropertyValue "cursheet", vg1.vg.ActiveSheet
vg6.vg.ActiveSheet.SetPropertyValue "cursheet", vg1.vg.ActiveSheet
vg5.vg.Execute "ChangeRuler()"
vg6.vg.Execute "ChangeRuler()"
vg7.Run s + "btnview.tbl"
vg7.vg.ActiveSheet.SetPropertyValue "cursheet", vg1.vg.ActiveSheet
vg1_OnSheetChange( vg1.vg.ActiveSheet )
vg3.vg.Execute "btnPaste.Enable=true"
vg1.vg.ActiveSheet.Refresh()

If openfile<>"" Then
	vg1.vg.Execute "LoadFromFile("""+openfile+""")"
else
	vg3.vg.ActiveSheet.Execute "btnNew.OnClick(0)"
End if
End Sub

Sub vg1_OnSheetChange( ASheet )
if not ( vg2.vg is Nothing ) then
  if not ( vg2.vg.ActiveSheet is Nothing ) then
    vg2.vg.ActiveSheet.SetPropertyValue "cursheet", vg1.vg.ActiveSheet
  end if
end if
if not ( vg3.vg is Nothing ) then
  if not ( vg3.vg.ActiveSheet is Nothing ) then
    vg3.vg.ActiveSheet.SetPropertyValue "cursheet", vg1.vg.ActiveSheet
  end if
end if
if not ( vg4.vg is Nothing ) then
  if not ( vg4.vg.ActiveSheet is Nothing ) then
    vg4.vg.ActiveSheet.SetPropertyValue "selected", vg1.vg.ActiveSheet.Selection
  end if
end if
if not ( vg5.vg is Nothing ) then
  if not ( vg5.vg.ActiveSheet is Nothing ) then
    vg5.vg.ActiveSheet.SetPropertyValue "cursheet", vg1.vg.ActiveSheet
  end if
end if
if not ( vg6.vg is Nothing ) then
  if not ( vg6.vg.ActiveSheet is Nothing ) then
    vg6.vg.ActiveSheet.SetPropertyValue "cursheet", vg1.vg.ActiveSheet
  end if
end if
if not ( vg7.vg is Nothing ) then
  if not ( vg7.vg.ActiveSheet is Nothing ) then
    vg7.vg.ActiveSheet.SetPropertyValue "CurSheet", vg1.vg.ActiveSheet
  end if
end if
End Sub

Sub vg1_OnMouseMove( x, y )
'vg5.vg.ActiveSheet.Execute( "mousemove()" )
'vg6.vg.ActiveSheet.Execute( "mousemove()" )
End Sub

Sub vg1_OnSelectChange
	if vg4.vg is nothing then
		s = vg1.SystemParams.DefaultPath
		vg4.Run s + "propwin.tbl"
	end if
	vg4.vg.ActiveSheet.SetPropertyValue "selected", vg1.vg.ActiveSheet.Selection
	vg3.vg.Execute "OnSelectChange()"
	vg3.vg.Execute "btnUndo.Enable=CurSheet.Owner.CanUndo()"
	vg3.vg.Execute "btnRedo.Enable=CurSheet.Owner.CanRedo()"
End Sub

Sub vg1_OnModifyChange( m )
if not ( vg3.vg is Nothing ) then
  if not ( vg3.vg.ActiveSheet is Nothing ) then
    vg3.vg.Execute "btnSave.Enable=CurSheet.Owner.Modified"
  end if
end if
End Sub

Sub vg1_OnRButtonDown( x, y )
vg1.vg.Execute( "menu=Create(systemparams.defaultpath+'popupmenu1.tbl', true, false);menu.ActiveSheet.CurSheet = ActiveSheet; px = 0; py = 0; GetCursorPos( px, py ); menu.Popup( px, py ); menu.Free()" )
End Sub

Sub vg1_OnOrgChanging()
if not ( vg5.vg is Nothing ) then
  if not ( vg5.vg.ActiveSheet is Nothing ) then
   ' vg5.vg.Execute "ChangeRuler()"
  end if
end if
if not ( vg6.vg is Nothing ) then
  if not ( vg6.vg.ActiveSheet is Nothing ) then
   ' vg6.vg.Execute "ChangeRuler()"
  end if
end if
End Sub

Sub vg1_OnError(s)
MsgBox s
End Sub

Sub vg2_OnError(s)
MsgBox s
End Sub

Sub vg3_OnError(s)
MsgBox s
End Sub

-->
</SCRIPT>
</head>

<body leftmargin="0" topmargin="0">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td height="60" colspan="4">

    <object classid="clsid:465BBD45-50E1-11D7-8803-00E09876BB62" codebase="http://www.visual-graph.com/activex/vg.cab#version=11,0,0,0"  width="100%" height="60%" id="vg1" viewastext name="toolbar">
</object></td>
  </tr>
  <tr>
    <td width="200" rowspan="2"><object classid="clsid:465BBD45-50E1-11D7-8803-00E09876BB62" codebase="http://www.visual-graph.com/vg.cab#version=11,0,0,0" id="vg2" width="100%" height="100%" viewastext>
    </object></td>
    <td width="20" height="20"><object classid="clsid:465BBD45-50E1-11D7-8803-00E09876BB62" codebase="http://www.visual-graph.com/vg.cab#version=11,0,0,0" id="vg7" width="100%" height="100%" viewastext>
                                </object></td>
    <td width="65%" height="20"><object classid="clsid:465BBD45-50E1-11D7-8803-00E09876BB62" codebase="http://www.visual-graph.com/vg.cab#version=11,0,0,0" id="vg5" width="100%" height="100%" viewastext>
                </object></td>
    <td width="240" rowspan="2"><object classid="clsid:465BBD45-50E1-11D7-8803-00E09876BB62" codebase="http://www.visual-graph.com/vg.cab#version=11,0,0,0" id="vg4" width="100%" height="100%" viewastext>
    </object></td>
  </tr>
  <tr>
    <td width="20"><object classid="clsid:465BBD45-50E1-11D7-8803-00E09876BB62" codebase="http://www.visual-graph.com/vg.cab#version=11,0,0,0" id="vg6" width="100%" height="100%" viewastext>
        </object></td>
    <td><object classid="clsid:465BBD45-50E1-11D7-8803-00E09876BB62" codebase="http://www.visual-graph.com/vg.cab#version=11,0,0,0" width="100%" height="100%" id="vg1" VIEWASTEXT>
    </object></td>
  </tr>
</table>
</body>

</html>