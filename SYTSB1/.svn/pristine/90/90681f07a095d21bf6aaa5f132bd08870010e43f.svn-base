var isInitFinished = false;//视频插件是否加载完成
var isInitFinishedBack =false;
$(function () {
//	initPlayerExample();
//	setTimeout("playVideo('"+devIdno+"','"+jsession+"')","2000");
	$("#ligerTab").ligerTab({
	        contextmenu : false,
	        onAfterSelectTabItem: function (tabId) {
	            if(tabId=="tabid1"){
	            	initPlayerExample();
	            	setTimeout("playVideo('"+devIdno+"','"+jsession+"')","2000");
	            	$("#stopTalkback").hide();
	            }
	        }
	  });
});


//初始化视频插件
function initPlayerExample() {
    //视频插件初始化参数
    var params = {
      allowFullscreen: "true",
      allowScriptAccess: "always",
      bgcolor: "#FFFFFF",
      wmode: "transparent"
      };
    //初始化flash
    var w = 1182;
    var h = 448;
    if(videoOpenType == "1"){
    	w = 1300;
    	h = 520;
    }else if(videoOpenType == "3"){
    	w = 570;
    	h = 437;
    }
    swfobject.embedSWF("app/gisydzf/video/player.swf", "cmsv6flash", w, h, "11.0.0", null, null, params, null);
    initFlash();
}

//视频插件是否加载完成
function initFlash() {
	
	var swfEl = swfobject.getObjectById("cmsv6flash");
    if (swfEl == null || !swfEl['setWindowNum']) {
        setTimeout(initFlash, 50);
    } else {
          //设置视频插件的语言
          swfobject.getObjectById("cmsv6flash").setLanguage("app/gisydzf/video/cn.xml");
          //先将全部窗口创建好
          swfobject.getObjectById("cmsv6flash").setWindowNum(36);
          //再配置当前的窗口数目
          swfobject.getObjectById("cmsv6flash").setWindowNum(1);
          //设置视频插件的服务器
          swfobject.getObjectById("cmsv6flash").setServerInfo("124.129.19.117", "6605");
          isInitFinished = true;
  }
}

function playVideo(devIdno,jsession) {
	var swfEl = swfobject.getObjectById("cmsv6flash");

	try {
		//	    停止播放视频
		swfEl.stopVideo(0);
		
		//设置视频窗口标题
	    swfEl.setVideoInfo(0, "vehicle1-CH1");
	} catch (e) {
		// TODO: handle exception
	}
    
    
    //播放视频
    swfEl.startVideo(0, jsession, devIdno, 0, 1, true);
}


//开始对讲
function startTalkback(devIdno,jsession) {
		  //开始对讲
	      var ret = swfobject.getObjectById("cmsv6flash").startTalkback(jsession, devIdno, 0, "211.162.125.99", "6605");
	      //返回 0成功，1表示正在对讲，2表示没有mic，3表示禁用了mic。
	      $("div[toolbarid='stopTalkback']").show();
	      $("div[toolbarid='startTalkback']").hide();
}

//停止对讲
function stopTalkback() {
      swfobject.getObjectById("cmsv6flash").stopTalkback();
      $("div[toolbarid='startTalkback']").show();
      $("div[toolbarid='stopTalkback']").hide();
}


/**
 * 字节转换
 * @param bytes
 * @returns {String}
 */
function bytesToSize(bytes) {  
    if (bytes === 0) return '0 B';  

     var k = 1024;  

     sizes = ['B','KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'];  

     i = Math.floor(Math.log(bytes) / Math.log(k));  

 return (bytes / Math.pow(k, i)).toPrecision(3) + ' ' + sizes[i];   
    //toPrecision(3) 后面保留一位小数，如1.0GB                                                                                                                  //return (bytes / Math.pow(k, i)).toPrecision(3) + ' ' + sizes[i];  
}  



/**
 * 录像下载
 */
function downloadLx(devidNo,fLength,fPath,saveName){ 
	var url= "http://124.129.19.117:6604/3/5?DownType=3&jsession="+jsession+"&DevIDNO="+devidNo+"&FLENGTH="+fLength+"&FOFFSET=0&MTYPE=1&FPATH="+fPath+"&SAVENAME="+saveName+"";
	window.open(url)
	
}

/**
 * 录像回放
 */
function playbackLx(devIdno,loc,svr,chn,flength,beg,end,filePath){ 
	var url = "http://124.129.19.117:6604/3/5?DownType=5&DevIDNO="+devIdno+"&FILELOC="+loc+"&FILESVR="+svr+"&FILECHN="+chn+"&FILEBEG="+beg+"&FILEEND="+end+"&PLAYIFRM=0&PLAYFILE="+filePath+"&PLAYBEG=0&PLAYEND=0&PLAYCHN=0"
	//开始回放
	startPlayback(url);
}


//开始远程回放
function startPlayback(url) {
      //停止远程回放
      swfobject.getObjectById("cmsv7flash").stopVideo(0);
      //开始远程回放
      var ret = swfobject.getObjectById("cmsv7flash").startVod(0,url);
}

//停止远程回放
function stopPlayback() {
      swfobject.getObjectById("cmsv7flash").stopVideo(0);
}

//初始化视频插件
function initPlayerExamplePlaybackLx() {
    //视频插件初始化参数
    var params = {
      allowFullscreen: "true",
      allowScriptAccess: "always",
      bgcolor: "#FFFFFF",
      wmode: "transparent"
      };
    //初始化flash
    swfobject.embedSWF("app/gisydzf/video/player.swf", "cmsv7flash", 600, 570, "11.0.0", null, null, params, null);
    initFlashPlaybackLx();
}

//视频插件是否加载完成
function initFlashPlaybackLx() {
	var swfEl = swfobject.getObjectById("cmsv7flash");
    if (swfEl == null || !swfEl['setWindowNum']) {
        setTimeout(initFlashPlaybackLx, 50);
    } else {
          //设置视频插件的语言
          swfobject.getObjectById("cmsv7flash").setLanguage("app/gisydzf/video/cn.xml");
          //先将全部窗口创建好
          swfobject.getObjectById("cmsv7flash").setWindowNum(36);
          //再配置当前的窗口数目
          swfobject.getObjectById("cmsv7flash").setWindowNum(1);
          //设置视频插件的服务器
          swfobject.getObjectById("cmsv7flash").setServerInfo("124.129.19.117", "6605");
          isInitFinishedBack = true;
  }
}



/**
 * 查询录像
 */
function searchLx(){
	var seachDate = $("#chosseStartDate").val();
	var DownType=2;var DevIDNO=devIdno;var LOC=1;var CHN=0;
    var YEAR = seachDate.substring(0,4);var MON = seachDate.substring(5,7);
    var DAY = seachDate.substring(8,10);var RECTYPE=-1;var FILEATTR=2;
    var BEG=0;
	var END=86399;var ARM1=0;var ARM2=0;var RES=0;var STREAM=0;var STORE=0;
	var url= "http://124.129.19.117:6604/3/5?DownType="+DownType+"&jsession="+jsession+"&DevIDNO="+DevIDNO+"&LOC="+LOC+"&CHN="+CHN+"&YEAR="+YEAR+"&MON="+MON+"&DAY="+DAY+"&RECTYPE="+RECTYPE+"&FILEATTR="+FILEATTR+"&BEG="+BEG+"&END="+END+"&ARM1="+ARM1+"&ARM2="+ARM2+"&RES="+RES+"&STREAM="+STREAM+"&STORE="+STORE+""
	$.ajax({
	    type:'POST',
	    url: url,
	    cache:false,
	    dataType:'json', 
	    success: function (data) {
	    	var jsonObj = [];
	    	for (var i = 0; i < data.files.length; i++) {
	    		var len = bytesToSize(data.files[i].len)
	    		var saveName = data.files[i].file.substring(data.files[i].file.lastIndexOf("/")+1);
	    		var timeStart = sec_to_time(data.files[i].beg);
	    		if(data.files[i].end>86400){
	    			var convertDateFrom = convertDateFromString($("#chosseStartDate").val());
	    			var addDay = addDate(convertDateFrom);
	    			var timeEnd = sec_to_time(data.files[i].end-86400);
	    			seachDate = seachDate+" "+timeStart+"-"+addDay+" "+timeEnd;
	    		}else{
	    			var timeEnd = sec_to_time(data.files[i].end);
	    			seachDate = seachDate+" "+timeStart+"-"+" "+timeEnd;
	    		}
	    		
	    		
	    		var row = {loc:data.files[i].loc, svr:data.files[i].svr, beg:data.files[i].beg, end:data.files[i].end, lxDate:seachDate, lxType: data.files[i].type, devIdno: data.files[i].devIdno, chn: data.files[i].chn,filePath:data.files[i].file,len:len,flength:data.files[i].len,saveName:saveName};
	    		jsonObj.push(row);
	    		seachDate = $("#chosseStartDate").val();
			}
	    	fjGrid.loadData({
                Rows : jsonObj
            });
	    	
	    },
	});
}


function convertDateFromString(dateString) {
	if (dateString) { 
	var date = new Date(dateString.replace(/-/,"/")) 
	return date;
	}
}


//日期，在原有日期基础上，增加days天数，默认增加1天
function addDate(date, days) {
    if (days == undefined || days == '') {
        days = 1;
    }
    var date = new Date(date);
    date.setDate(date.getDate() + days);
    var month = date.getMonth() + 1;
    var day = date.getDate();
    return date.getFullYear() + '-' + getFormatDate(month) + '-' + getFormatDate(day);
}

// 日期月份/天的显示，如果是1位数，则在前面加上'0'
function getFormatDate(arg) {
    if (arg == undefined || arg == '') {
        return '';
    }

    var re = arg + '';
    if (re.length < 2) {
        re = '0' + re;
    }

    return re;
}

/**
 * 时间秒数格式化
 * @param s 时间戳（单位：秒）
 * @returns {*} 格式化后的时分秒
 */
 function sec_to_time (s) {
    var t;
    if(s > -1){
        var hour = Math.floor(s/3600);
        var min = Math.floor(s/60) % 60;
        var sec = s % 60;
        if(hour < 10) {
            t = '0'+ hour + ":";
        } else {
            t = hour + ":";
        }

        if(min < 10){t += "0";}
        t += min + ":";
        if(sec < 10){t += "0";}
        t += sec;
    }
    return t;
}

