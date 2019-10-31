<%@page import="java.util.Date"%>
<%@page import="com.khnt.utils.DateUtil"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<%@include file="/k/kui-base-form.jsp" %>
<script type="text/javascript" src="app/gisydzf/video/swfobject.js"></script>
<script type="text/javascript" src="app/gisydzf/video/jweixin-1.0.0.js"></script>
<script type="text/javascript" src="app/gisydzf/video/video.js"></script>
<script type="text/javascript" src="app/gisydzf/video/videojs-contrib-hls.js"></script>
<script type="text/javascript" src="app/gisydzf/gs_ydzf/sbxx_video.js"></script>
<script type="text/javascript">
var fjGrid = null;
var devIdno = '${param.devIdno}';
var jsession = '${param.jsession}';
var videoOpenType = "1";

$(function () {
	//加载grid
	initFormBase();
	initFormDownLx();
	initFileGrid("formGrid"); 
	$("#devIdno").val(devIdno);
});

window.onload=function(){
// 	$("#stopTalkback").attr("style","display:none;");
	$("div[toolbarid='stopTalkback']").hide();
	 $("li[tabid='tabid1']").click();
// 	 $("#stopTalkback").hide();
};

/**
 * 初始化实时视频页面按钮
 */
function initFormBase() {
	   $("#formObject").initForm({
			toolbarPosition:"top",  //设置按钮位置在顶部
			toolbar : [ {
				id   :"startTalkback",
				text : "对讲",
				icon : "communication",
				click : function() {
					startTalkback(devIdno,jsession)
				}
			}, {
				id   :"stopTalkback",
				text : "停止对讲",
				icon : "cancel",
				click : function() {
					stopTalkback();
				}
			} ],
		});
	   
	}
	
/**
 * 初始化录像下载按钮
 */
function initFormDownLx() {
	   $("#formObject1").initForm({
			toolbar : null,
		});
		$("#searchLx").ligerButton({icon:"search",text:"查询",click:function(){
			searchLx() ;
		}});
	}
function initFileGrid(gridId) {
    var columns = [
        {display: "操作", name: "picture",type: 'text', width: 50,isSort:false, align: "center",render:function(item, index){
            return "<a class='l-a l-icon-appropriation' href='javascript:downloadLx("+ item.devIdno +"," + item.flength + ",\"" + item.filePath + "\",\"" + item.saveName + "\")'><span>下载</span></a>";
        }},
        {display: "文件大小", name: "flength",type: 'text',hide:true},
        {display: "文件名称", name: "saveName",type: 'text',hide:true},
        {display: "搜索位置", name: "loc",type: 'text',hide:true},//1表示设备
        {display: "服务器ID", name: "svr",type: 'text',hide:true},//1表示设备
        {display: "文件开始时间", name: "beg",type: 'text',hide:true},//1表示设备
        {display: "文件结束时间", name: "end",type: 'text',hide:true},//1表示设备
        {display: "时间", name: "lxDate",type: 'text',width:200},
        {display: "大小", name: "len",type: 'text', align: "center",width:100}, 
        {display: "录像类型", name: "lxType",type: 'text',width: 80,
        	render:function(item){
			if(item.lxType=='0'){
				return '常规';
			}else{
				return '报警';
			}
		}},
        {display: "终端设备", name: "devIdno",type: 'text',width: 100},
        {display: "通道", name: "chn",type: 'text', align: "center",width: 80},
        {display: "文件位置", name: "filePath", type: 'text', align: "left",width:580}
      ];
    fjGrid = $("#" + gridId).ligerGrid({
        columns: columns,
        enabledEdit: true,
        rownumbers: true,
        usePager: false,
        width: 1260,
        frozen: false,
        height: 460,
        delayLoad: false,
        isScroll: true,
        checkbox: false,
        data: {Rows: []},
        onSelectRow: function (rowdata, rowindex, rowDomElement) {
        }
    });
	}

</script>
</head>
<body>
	<div class="navtab" id="ligerTab">
	<div title="实时视频" lselected="true" tabid="tabid1">
		<form  id="formObject" >
		<table>
			<tr>
				<td>
					<div id="cmsv6flash"></div>
				</td>
			</tr>
		</table>
		</form>
	</div>
    <div title="录像下载" lselected="false" tabid="tabid2">
    	<form id="formObject1">
    	<div>
			<table class="l-detail-table">
			            <tr>
				            <td class="l-t-td-left">终端设备：</td>
				            <td class="l-t-td-right">
				            	<input name="devIdno" id="devIdno" type="text" ligerui="{disabled:true}" />
				            </td>
				            <td class="l-t-td-left">录像日期：</td>
				            <td class="l-t-td-right">
				            	<input name="chosseStartDate" id="chosseStartDate" type="text" ltype="date" ligerui="{format:'yyyy-MM-dd'}"  value="<%=DateUtil.getDateTime("yyyy-MM-dd", new Date()) %>"/>
				            </td>
							<td class="l-t-td-left">
								<div id="searchLx"></div>
							</td>
						</tr>
			        </table>
			<fieldset class="l-fieldset">
            <legend class="l-legend">
                <div>录像信息</div>
            </legend>
            <div id="formGrid"></div>
          </fieldset>
<!-- 			<table style="margin-left: 695px;margin-top: -585px"> -->
<!-- 				 <tr> -->
<!-- 					<td> -->
<!-- 						<div id="cmsv7flash"></div> -->
<!-- 					</td> -->
<!-- 				</tr> -->
<!-- 			</table> -->
        </div>
        </form>
	</div>
	</div>
</body>
</html>
