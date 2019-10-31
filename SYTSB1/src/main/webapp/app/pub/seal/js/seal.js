var sealClassId="CLSID:C1FB7513-9A44-4C64-B653-63C6965D7F4C";
var codeBase="app/pub/seal/Word.CAB#version=1,50,7428,4";

/**
 * 配置签章环境
 * 1、Cert：用户证书
 * 2、VerifyWay：验证方式，0验证、1本地验证、2在线验证
 * 3、ServerURL：印章服务器地址
 */
function initBicengConfig(){
    var sealOLDObject = new ActiveXObject("OL2D.ModeChanger");
    var sealConfig = {
        verifyWay : 2,
        serverUrl : "",
        certUrl : kFrameConfig.base + "pub/certificate/currentCertificate.do"
    };
    if(kFrameConfig){
        sealConfig.verifyWay = kFrameConfig.sealConfig.verifyWay;
        sealConfig.serverUrl = kFrameConfig.sealConfig.serverUrl;
        sealConfig.certUrl = kFrameConfig.sealConfig.certUrl;
    }
    //cert每次使用时，需要动态获取用户印章.
    sealOLDObject.VerifyWay = sealConfig.verifyWay;  
    sealOLDObject.ServerURL = sealConfig.serverUrl;
    var isCertAvaliable = false;
    $.ajax({
        url : sealConfig.certUrl,
        async : false,
        dataType : "json",
        success : function(response){
            if(response.success){
                sealOLDObject.Cert = response.data;
                sealParam.cert=response.data;
                isCertAvaliable = true;
            }
        }
    });
    
    if(!isCertAvaliable){
        $.ligerDialog.error('获取用户数字证书失败，请使用正确的数字证书登录本系统！');
        return false;
    }
    else
        return true;
}

//参数列表
/**
 * CurrentState 的状态，0－尚未签章或签名；1－已经签章；2－已经签名
 * 触发签章或签名、批注的方法；为'true'时签章，否则为签名或批注。obj.SignSeal(true);
 */
var sealParam = {
	    sealClassId:sealClassId,
	    businessId:'',//业务ID
	    busTypes:null,//业务分类扩展
	    Authority:4,//禁止移动,0则禁止移动
	    /*left:0,//印章位置
	    top:0,//印章位置
*/	    DrawModeUnsign:0,//签章前显示淡蓝印章图案
		DrawMode:18,//使用透明和自适应的方式显示印章
		OnGetProtectedData:'',//受保护数据
		//SignSeal:true,//为'true'时签章，否则为签名或批注
		callback:null,//添加触发回调函数
		delcallback:null,//删除触发回掉函数
		cert:''
	};
var sealObj;//生成的object对象
/**
 * 参数设置
 * @param Authority 禁止移动,0则禁止移动
 * @param left 印章位置
 * @param top 印章位置
 * @param OnGetProtectedData 受保护数据
 * @param SignSeal 为'true'时签章，否则为签名或批注
 * @param callback 回调函数
 * businessId:'',//业务ID
 * busTypes:null,//业务分类扩展
 * @param delcallback //删除触发回掉函数
 * @returns
 */
function configSetting(Authority,OnGetProtectedData,businessId,busTypes,callback,delcallback){
	if(Authority)
		sealParam.Authority=Authority;
	/*if(left)
		sealParam.left=left;
	if(top)
		sealParam.top=top;*/
	if(OnGetProtectedData)
		sealParam.OnGetProtectedData=OnGetProtectedData;
	
	if(businessId)
		sealParam.businessId=businessId;
	if(busTypes)
		sealParam.busTypes=busTypes;
	if(callback)
		sealParam.callback=callback;
	if(delcallback)
		sealParam.delcallback=delcallback;
}
/**
 * 插入印章或签名
 * @param Container 放置印章或签名的容易ID
 * @param SignedDataStoreElement
 * width;//印章宽度 height;//印章高度
 * @returns
 */
function InsertSeal(Container,SignSeal,width,height,left,top){
	try{
	    //初始化环境设置，证书、服务器地址
       /* if(!initBicengConfig()){
            return;
        }*/
		parentDiv=document.createElement("div");
		parentDiv.style.height="0px";
		parentDiv.style.border="0px";
		parentDiv.style.position="relative";   //绝对位置
		//style="height:170px;POSITION: relative;border: 0px;"
		
		divObj=document.createElement("div");
		divObj.style.left=left+"px";
		divObj.style.top=top+"px";
		divObj.style.position="absolute";   //绝对位置
		divObj.style.zIndex="1000";   //绝对位置
		
		Container=document.getElementById(Container);
		Container.appendChild(parentDiv);
		parentDiv.appendChild(divObj);
		
		divObj.innerHTML='<object id="SEAL_OBJ_SIGN" codeBase="'+codeBase+'" classid="'+sealClassId+'">'+
		'<span style="color:red;">由于浏览器安全设置原因。<br/>'+
		'不能自动安装电子印章控件。<br/>'+
		'请手动<a href="app/pub/seal/电子印章安装包.exe">下载安装</a>，<br/>'+
		'同时把本站加入信任站点。<br/>'+
		'安装完成后必须重启浏览器。</span></object>';
		
		
		sealObj=document.getElementById("SEAL_OBJ_SIGN");
		sealObj.width=width;//印章宽度
		sealObj.height=height;//印章高度
		sealObj.OnGetProtectedData=getProtectedData;  // 受保护的字段	
		sealObj.Authority=sealParam.Authority;
		//印章的显示模式
		sealObj.DrawModeUnsign=0;
		sealObj.DrawMode=18;
		
		//签章完成
		sealObj.ScriptRatherThanFunction=false;
		sealObj.OnAfterSign=checkState;
		
		sealObj.OnAfterDel=DelSignSeal;
		//触发签章或签名、批注的方法；为'true'时签章，否则为签名或批注。
		sealObj.object.SignSeal(SignSeal);
	} catch(e){
		alert("签章错误："+e.message);
		throw new Error(0,e.message);
		//sealObj.parentElement.parentElement.removeChild(sealObj.parentElement);	
		return false;
	}
	

	
}
/**
 * 带控制签字签章按钮的方法,避免点击过快，造成错误。
 */
function InsertSealButton(Container,SignSeal,width,height,left,top,button_name){
	try{
		if(button_name!=""){
			$("#"+button_name).ligerGetTextBoxManager().setDisabled();
		}
		InsertSeal(Container,SignSeal,width,height,left,top);
	}catch(e){
		alert("签章错误："+e.message);
		if(button_name!=""){
			$("#"+button_name).ligerGetTextBoxManager().setEnabled();
		}
		return false;
	}
}
/**
 * 获取保护字段
 */
function getProtectedData(){
	//alert(sealParam.cert+"##__@@"+sealParam.OnGetProtectedData);
	return sealParam.cert+"##__@@"+sealParam.OnGetProtectedData;
}
/**
 * 删除印章数据
 */
function DelSignSeal(Obj){
	if (Obj.CurrentState==0)  //（CurrentState）的状态，0－尚未签章或签名；1－已经签章；2－已经签名。
	{
		//删除印章数据
		$.post("pub/signseal/delSignSeal.do",{"ids":Obj.id},function(data){
			if(data.success){
				var proData=data.data.proData;
				var index=proData.indexOf("##__@@");
				if(index!=-1){
					proData=proData.substring((index+6),(proData.length-index));
				}
				Obj.parentElement.parentElement.removeChild(Obj.parentElement);		
				if(sealParam.delcallback){
            		sealParam.delcallback(data.data.id,proData);
            	}
				return false;
			}else{
				Obj.parentElement.parentElement.removeChild(Obj.parentElement);		
				$.ligerDialog.error("错误："+data.msg);
				return false;
			}
		},"json");
		//去除未添加成功的印章控件
		
	}
}
/**
 * 检测是否已经成功上传印章
 */
function checkState(Obj){
	//alert("11");
	if (Obj.CurrentState==0)  //（CurrentState）的状态，0－尚未签章或签名；1－已经签章；2－已经签名。
	{
		//去除未添加成功的印章控件
		Obj.parentElement.parentElement.removeChild(Obj.parentElement);		
		return false;
	}else{
		//保存数据到数据库中
		submitSignSeal(Obj);
	}
}

function submitSignSeal(Obj){
	var protectData=sealParam.cert+"##__@@"+sealParam.OnGetProtectedData;
	var formData={width:Obj.width,height:Obj.height,protectData:protectData,top:Obj.parentElement.style.top,left:Obj.parentElement.style.left,sealdata:Obj.SignedData,businessId:sealParam.businessId,types:sealParam.busTypes};
	$.ajax({
        url: "pub/signseal/saveSignSeal.do",
        type: "POST",
        datatype: "json",
        contentType: "application/json; charset=utf-8",
        data: $.ligerui.toJSON(formData),
        success: function (data, stats) {
            if (data["success"]) {
            	Obj.id=data.data.id;
            	if(sealParam.callback){
            		sealParam.callback(data.data);
            	}
          	 	
            }else{
                $.ligerDialog.error('保存失败！' + data.msg);
            }
        },
        error: function (data) {
            $.ligerDialog.error('保存失败！' + data.msg);
        }
    });
}

/**
 * 显示印章
 * @param Container 放置印章或签名的容易ID
 * @param data 为一个json数据。list的json数据
 */
function showSignSeal(Container,data,Authority){
	//alert(data.length);
	
	Container=document.getElementById(Container);
	//init(Container);
	for(var i=0;i<data.length;i++){
		try{
			parentDiv=document.createElement("div");
			parentDiv.style.height="0px";
			parentDiv.style.border="0px";
			parentDiv.style.position="relative";   //绝对位置
			var divObj=document.createElement("div");
			divObj.style.left=data[i].left;
			divObj.style.top=data[i].top;	
			divObj.style.position="absolute";   //绝对位置
			divObj.style.zIndex="1000";   //绝对位置
			
			Container.appendChild(parentDiv);
			parentDiv.appendChild(divObj);
			divObj.innerHTML='<object id="'+data[i].id+'" codeBase="'+codeBase+'" classid="'+sealClassId+'">'+
			'<span style="color:red;">由于浏览器安全设置原因。<br/>'+
			'不能自动安装电子印章控件。<br/>'+
			'请手动<a href="app/pub/seal/电子印章安装包.exe">下载安装</a>，<br/>'+
			'同时把本站加入信任站点。<br/>安装完成后必须重启浏览器。</span></object>';
			
			sealObj=document.getElementById(data[i].id);
			sealObj.width=data[i].width;//印章宽度
			sealObj.height=data[i].height;//印章高度

			function getProtectedDataShow(){
				return data[i].protectData==null?"":data[i].protectData;
			}
			//sealObj.OnGetProtectedData=data[i].protectData==null?"":data[i].protectData;  // 受保护的字段	
			sealObj.OnGetProtectedData=getProtectedDataShow;
			sealObj.Authority=Authority;
			//印章的显示模式
			sealObj.DrawModeUnsign=0;
			sealObj.DrawMode=18;
			sealObj.ScriptRatherThanFunction=false;
			sealObj.OnAfterDel=DelSignSeal;
			
			sealObj.SignedData=data[i].sealdata;
		} catch(e){
			alert("印章显示错误："+e.message);
			//sealObj.parentElement.parentElement.removeChild(sealObj.parentElement);	
			return false;
		}
		
		
	}
}

/**
 * 显示印章单个印章，支持重新设置相对位置，left和top
 * @param Container 放置印章或签名的容易ID
 * @param data 为一个json数据。list的json数据
 */
function showSingleSeal(Container,data,left,top,Authority){
	//alert(data.length);
	
	Container=document.getElementById(Container);
	if(data!=null){
		try{
			parentDiv=document.createElement("div");
			parentDiv.style.height="0px";
			parentDiv.style.border="0px";
			parentDiv.style.position="relative";   //绝对位置
			var divObj=document.createElement("div");
			if(left!=""){
				divObj.style.left=left+"px";
			}else
			divObj.style.left=data.left;
			if(top!=""){
				divObj.style.top=top+"px";
			}else
			divObj.style.top=data.top;	
			divObj.style.position="absolute";   //绝对位置
			divObj.style.zIndex="1000";   //绝对位置
			
			Container.appendChild(parentDiv);
			parentDiv.appendChild(divObj);
			divObj.innerHTML='<object id="'+data.id+'" codeBase="'+codeBase+'" classid="'+sealClassId+'">'+
			'<span style="color:red;">由于浏览器安全设置原因。<br/>'+
			'不能自动安装电子印章控件。<br/>'+
			'请手动<a href="app/pub/seal/电子印章安装包.exe">下载安装</a>，<br/>'+
			'同时把本站加入信任站点。<br/>安装完成后必须重启浏览器。</span></object>';
			
			sealObj=document.getElementById(data.id);
			sealObj.width=data.width;//印章宽度
			sealObj.height=data.height;//印章高度

			function getProtectedDataShow(){
				return data.protectData==null?"":data.protectData;
			}
			//sealObj.OnGetProtectedData=data[i].protectData==null?"":data[i].protectData;  // 受保护的字段	
			sealObj.OnGetProtectedData=getProtectedDataShow;
			sealObj.Authority=Authority;
			//印章的显示模式
			sealObj.DrawModeUnsign=0;
			sealObj.DrawMode=18;
			sealObj.ScriptRatherThanFunction=false;
			sealObj.OnAfterDel=DelSignSeal;
			
			sealObj.SignedData=data.sealdata;
		} catch(e){
			alert("印章显示错误："+e.message);
			//sealObj.parentElement.parentElement.removeChild(sealObj.parentElement);	
			return false;
		}
	}else{
		alert("印章数据丢失！");
		return false;
	}
	
}

/**
 * 初始化印章参数，用于检测是否已经安装控件
 */
function initSeal(containerId){
	var container = document.getElementById(containerId);
	if (container) {
		container.innerHTML +='<br/><object id="SEAL_OBJ_SIGN" height="0" width="0" codeBase="'+codeBase+'" classid="'+sealClassId+'">'+
		'<span style="color:red;"><param name=DrawMode value=18/><param name=Authority value=9/>'+
	      '<param name=DrawModeUnsign value=0/>于浏览器安全设置原因。'+
		'不能自动安装电子印章控件。'+
		'请手动<a href="app/pub/seal/电子印章安装包.exe">下载安装</a>，'+
		'同时把本站加入信任站点。安装完成后必须重启浏览器。</span></object>';
	} else {
		alert("未找到id为(" + containerId + ")的容器");
	}
}
//word签章初始化
var funcs;//签章功能集
var sealex;//提前扩展签章对象
var params;//签章参数
function initWordSeal(obj){
    //初始化环境设置，证书、服务器地址
    /*if(!initBicengConfig()){
        return;
    }*/
	try{
		//创建一个签章功能集	
		funcs = new ActiveXObject("BCClientGlobalLib.ClientFuncs");
		//初始化这个功能集为针对Word签章的功能
		funcs.Init("WordClient");
		//从功能集里面提前扩展签章对象
		if(obj=="sealex"){
			sealex = funcs.sealex;
		}else if(obj=="seal"){
			sealex = funcs.Seal;
		}
		
        if (sealex == undefined){
        	alert("签章错误："+"扩展签章对象失败");
    		return;
        }
        //创建签章参数
        params = new ActiveXObject("BCDataLib.DataPersister");
	} catch(e){
		alert("签章初始化错误："+e.message);
		return;
	}
}
/**
 * word中插入印章
 * @param position 书签位置
 * @param activedoc 当前的文档对象
 * @param AreaToProtect 保护字段
 * @param isAuto 是否为自动选择印章 为true时候sealNum不能为空
 * @param sealNum 选择印章的序号。
 * @returns {Boolean}
 */
function insertWordSeal(position,activedoc,AreaToProtect,isAuto,sealNum){
	initWordSeal("sealex");//初始化参数
	try{
		
		//解除保护文档，因为如果之前盖过章的话会将文档锁定，要先解除锁定，才能再次盖章。参数为“保护印章”时锁定文档。
		funcs.Protection("解除保护", "Document", activedoc);
		if (AreaToProtect == undefined) {//如果印章所保护的内容的书签为空，那么印章默认保护文档中所在位置之前的部分
	        var bm; //印章位置的书签对象，ActiveX对象
            bm = activedoc.Bookmarks(position);
            if (bm == null){
            	alert("签章错误："+"添加书签失败！");
        		return false;
            }
	        AreaToProtect = "prt" + position; //印章保护的内容的书签名
	        //将保护区域（从文档开头到印章位置）设置为一个书签
	       // activedoc.Bookmarks.Add(AreaToProtect, activedoc.Range(0, bm.Range.End));
	    }
		//设置印章参数
		params.Document = activedoc; //要签章的文档对象
        params.SealPosition = position; //要签章的位置的书签名
        params.AreaToProtect = AreaToProtect; //该印章要保护的内容的书签名
        var selectSealData;
		if(isAuto){
			if(sealNum==null||sealNum==""){
				alert("自动签名，必须选择有印章序号参数！");
        		return false;
			}else{
				selectSealData=selectSeal(sealNum);
			}
			//alert("1");
			//参数设置
			if(selectSealData==null){
				alert("没有找到可用印章，请确认是否已经导入印章，或有可加盖的印章");
        		return false;
			}else{
				tmpObj=document.getElementById("SEAL_OBJ_SIGN");
				if(tmpObj==undefined)
					return false;
				var sdo=tmpObj.SealData;
				sdo.SealInfo=selectSealData;
				sdo.SignType=0;
				sdo.DataValid=true;
				params.sdo=sdo;
			}
		}else{
			if (!params.Contains("ShowForm"))
	            params.ShowForm = "请选择批量签章要使用的印章"; //这个是选择印章的界面的标题，如果不设置这个参数，则不显示任何界面，直接使用第一个印章
		}
        //加盖印章  返回值为false时表示用户取消操作 
        var ret = false;
        ret = sealex.InvokeFunc("自动签章", params);
        //完成签章 ，解除文档保护
        params.Document = null;
        funcs.Protection("解除保护", "Document", activedoc);
        
        return ret;
	}catch(e){
		alert("签章错误："+e.message);
		return false;
	}   
}
/**
 * 选择对应的印章
 */
function selectSeal(sealNum) {
	try {
		var agent = new ActiveXObject("BCSecureLib.SecureAgent");
		var provider = new ActiveXObject("BCStandardDataLib.SealArray");
		// agent.GetAllSeals()获取所有印章，返回数组
		provider.RawData = agent.GetAllSeals();
		var i, len = provider.Count;// 获取有几个印章
		if (len == 0) {
			alert("没有发现可用印章，请确认是否已经导入印章，或有可加盖的印章");
			return;
		}
		for (i = 0; i < len; i++) {
			// 通过序列号获得私章数据
			var seal = provider.Item(i);
			try {
				if (seal.SealSerialNubmber.indexOf(sealNum) > -1) {// 该例子中的序列号sealNum为演示章序列号
					var selectedSeal = seal;
					return selectedSeal;
					break;
				}
				if (i >= len) {
					alert("没有找到可用印章，请确认是否已经导入印章，或有可加盖的印章");
					return;
				}
			} catch (e) {
				continue;
			}
		}
	} catch (e) {
		alert("印章选择错误："+e.message);
		return;
	}
}
/**
 * word中删除印章调用
 * @param activedoc
 * @returns {Boolean}
 */
function delWordSeal(activedoc){
	initWordSeal("seal");//初始化参数
	try{
		params.Document = activedoc; //要签章的文档对象
		//加盖印章  返回值为false时表示用户取消操作 
	    var ret = false;
	    ret = sealex.InvokeFunc("验证", params);
	    //完成签章 ，解除文档保护
	    params.Document = null;
	    funcs.Protection("解除保护", "Document", activedoc);
	    return ret;
	}catch(e){
		alert("印章删除错误："+e.message);
		return;
	}
	
}
/**
 * word中签名
 */
function handSeal(activedoc){
	initWordSeal("seal");//初始化参数
	try{
		funcs.Protection("解除保护", "Document", activedoc);
		params.Document = activedoc; //要签章的文档对象
		params.IsSeal = false;//签名
		//加盖印章  返回值为false时表示用户取消操作 
	    var ret = false;
	    ret = sealex.InvokeFunc("签章", params);
	    //完成签章 ，解除文档保护
	    params.Document = null;
	    funcs.Protection("解除保护", "Document", activedoc);
	    return ret;
	}catch(e){
		alert("签名错误："+e.message);
		return;
	}
}

/**
 * 调用实例 5为印章显示类型 150为印章相对left。 *
 * 
 * @param Authority
 *            禁止移动,0则禁止移动
 * @param OnGetProtectedData
 *            受保护数据
 * @param SignSeal
 *            为'true'时签章，否则为签名或批注
 * @param callback
 *            回调函数 businessId:'',//业务ID busTypes:null,//业务分类扩展
 * @param delcallback
 *            //删除触发回掉函数 configSetting(5,null,'','',function(data){
 *            //处理添加印章成功后回调函数 alert(data.id);}, function(data){ //处理删除成功后，回调函数
 *            alert(data) }); //添加印章
 * @param Container
 *            放置印章或签名的容易ID
 * @param SignedDataStoreElement
 *            width;//印章宽度 height;//印章高度
 *            InsertSeal("sealPositon",true,150,150,150,65);
 */

