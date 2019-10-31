

//控制显示优先级
var isClientQuery = false;
//普通查询
var clientQuery = [];
//批量查询数据缓存
var companyQueryData = '';
//查询定时器
var queryTimer = null;

//清除查询条件(重置)
function clearQuery(){
	$("#device_registration_code").val("");
	$("#device_qr_code").val("");
	$("#company_name").val("");
	//清除查询
	clearNormalQueried();
	//启动显示缓存start
	 clearTimeout(queryTimer);//清除定时器
	 //关闭窗口
	 closeFlowWindow();
	 //聚焦默认中心
	 map.centerAndZoom(new BMap.Point(104.049914,30.668745), 12);
	 //重新开启缓存显示
	 if(isClientQuery){
		 isClientQuery = false;
		 startShowRtCache(4,12000);
		 startShowWxCache(5,15000);
	 }
  //启动显示缓存end
}
//清除已经查询显示的图标
function clearNormalQueried(){
	if(clientQuery.length>0){
		for(var i=0;i<clientQuery.length;i++){
			map.removeOverlay(clientQuery[i]);
		}
	}
	clientQuery.length = 0;
}
//根据客户查询设备
function query(){
	openLoading();
	var rgCode = $("#device_registration_code").val();
	var qrCode = $("#device_qr_code").val();
	var companyName = $("#company_name").val();
	
	if(rgCode==""&&qrCode==""&&companyName==""){
		closeLoading();
		alert("请输入查询条件！");
		return;
	}else if(rgCode==""&&qrCode==""&&companyName!=""){
		queryByCompany(companyName);
	}else{
		queryOne(rgCode,qrCode);
		
	}
	
	 
}
function queryOne(rgCode,qrCode){
	 $.ajax({
			url:"gis/device/static/queryDevices.do",
			type:"post",
			data:{
				code:rgCode,
				device_qr_code:qrCode,
			},
			dataType:"json",
			success:function(data){
				clearNormalQueried();//清除查询
				queryCloseCache()//清除缓存
				if(data.success){
					//查询历史
					var history = data.history;
					addHistory(history);
					//添加图标
					var items = data.data;
					if(items.length>0){
						closeLoading();
						queryStar(items[0],{"type":"query","isNew":true});
					}else{
						closeLoading();
						alert("未查询到设备！");
					}
				}else{
					closeLoading();
					alert("未查询到设备！");
				}
			},
			error:function(data){
				alert(data.msg);
				closeLoading();
			}
	});
}
function queryByCompany(conpany){
	 $.ajax({
			url:"gis/device/static/queryByCompany.do",
			type:"post",
			data:{
				company:conpany
			},
			dataType:"json",
			success:function(data){
				if(data.success){
					clearNormalQueried();
					queryCloseCache();
					var items = data.data;
					//查询历史
					var history = data.history;
					addHistory(history);
					if(items.length>0){
						var queryAddress = '';
						var queryAreaCode = '';
						for(var i=0;i<items.length;i++){
							if(queryAddress==''&&items[i].DEVICE_USE_PLACE!=''&&items[i].DEVICE_USE_PLACE!=null){
								queryAddress = items[i].DEVICE_USE_PLACE;
							}
							if(queryAreaCode==''&&items[i].DEVICE_AREA_CODE!=''&&items[i].DEVICE_AREA_CODE!=null){
								queryAreaCode = items[i].DEVICE_AREA_CODE;
							}
							if(queryAddress!=""&&queryAreaCode!=""){
								break;
							}
						}
						companyQueryData={"id":items[0].ID,"data":items};
						//在使用单位地址显示标记（明天完成）
						myGeo.getPoint(queryAddress, function(point){
							if (!point&&areaCodes[queryAreaCode]) {
								point = new BMap.Point(areaCodes[queryAreaCode].center[0],areaCodes[queryAreaCode].center[1]);
							}
							if(point){
								var mo = new MultiOverlay({"point":point,"number":items.length,"id":items[0].ID});
								map.addOverlay(mo);
								clientQuery.push(mo);
								closeLoading();
							}else{
								closeLoading();
								alert("未查询到设备地址！");
							}
						}, "成都市");
						closeLoading();
					}else{
						closeLoading();
						alert("未查询到设备！");
					}
				}else{
					closeLoading();
					alert("未查询到设备！");
				}
			}
	 });
}
function queryCloseCache(){
	//设置查询显示为true
	isClientQuery = true;
	//0.关闭缓存显示1.关闭流程图2.清除timer3.打开新窗口4.设置新timer
	stopShowWxCache();//关闭微信缓存
	stopShowRtCache();//关闭出报告缓存
	closeFlowWindow();//关闭流程图
	clearTimeout(queryTimer);//清除timer
	//60秒钟后关闭窗口
	queryTimer = setTimeout(function(){
		 //关闭窗口
		 closeFlowWindow();
		 //聚焦默认中心
		 //map.setCenter(new BMap.Point(104.049914,30.668745));
		 map.centerAndZoom(new BMap.Point(104.049914,30.668745), 12);
		 //重新开启缓存显示
		 isClientQuery = false;
		 startShowRtCache(4,12000);
		 startShowWxCache(5,15000);
	}, 60000);
}

