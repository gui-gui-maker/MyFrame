/** --------------------------------
 * 以下函数为工作流客户端接口
 *
 * 1. startFlowProcess(flowId) 启动流程
 * 2. submitActivity(activityId, serviceTitle, dataBus) 提交
 * 3. returnedActivity(activityId, serviceTitle, dataBus) 退回
 * 4. finishProcess(processId) 正常结束流程
 * 5. terminateProcess(processId) 强制终止流程
 * 6. trackProcess(processId) 查看、监控流程
 *
 * ------------------------------- */

/**
 * 选取指定业务编码、单位的工作流程。 参数callback为回调函数，回调函数定义如下： function callback(flowData){
 * //flowData格式为：{id:'流程id',name:'流程名称'} //业务代码 }
 */
function getServiceFlowConfig(serviceCode, orgId, callback) {
	$.getJSON("bpm/serviceConfig/getFlowServiceConfig.do", {
		serviceCode : serviceCode,
		orgId : orgId || ""
	}, function(resp) {
		if (resp.success) {
			if (resp.data.length == 1)
				callback(true, {
					id : resp.data[0].flowId,
					name : resp.data[0].flowName
				});
			else if (resp.data.length == 0)
				callback(false, "该业务没有可使用的工作流定义");
			else {
				// 从多个流程配置中选取一个
				top.$.dialog({
					id : "flowConfigSel",
					width : 700,
					height : 350,
					lock : true,
					parent : api,
					title : "选择业务流程",
					content : "url:pub/bpm/flow_service_config_select_list.jsp",
					cancel : true,
					data : {
						data : resp.data
					},
					ok : function() {
						var flowData = this.iframe.contentWindow.getSelect();
						callback(true, {
							id : flowData.flowId,
							name : flowData.flowName
						});
					}
				});
			}
		} else {
			callback(false, resp.msg);
		}
	});
}

function startFlowProcess(options){
	var $option = $.extend({},options,{
		callback: function(result,data){
			if(!result){
				if(options.callback)
					options.callback(false,data);
				return;
			}
			
			if("2" == data['result_type']){
				// 启动了流程，并自动提交第一个环节
				options.callback(true);
			}else{
				// 只是启动了流程，并未提交，马上启动提交操作
				var firstNode = data['result_activity_list'][0];
				var funcs = $.kh.isNull(firstNode["function"])?"":firstNode["function"];
				if(funcs.indexOf("pub_signature")>-1){
					var sign = 0;
					if(funcs.indexOf("pub_signature")>-1) sign = 1;
					if(funcs.indexOf("pub_multi_signature")>-1) sign = 2;
					// 需要添加第一环节意见
					signSealOpinion({
						activityId: firstNode.id,
						sid: options.sid,
						callback: function(){
							_startAndSubmitFlow(options, firstNode.id);
						}
					});
				}else{
					_startAndSubmitFlow(options, firstNode.id);
				}
			}
		}
	});

	//启动流程
	createFlowProcess($option);
}

function _startAndSubmitFlow(options,aid){
	chooseNextActivitySubmit({
		activityId: aid,
		forward: true,
		callback: function(success,resultData){
			if(success){
				options.callback(true);
				api.close();
			}else{
				$.ligerDialog.error("提交失败！<br/>" + (resultData||""));
			}
		}
	});
}

/**
 * 创建流程，options 参数：
 *     sid 业务标识ID；
 *     typeCode流程业务编码；
 *     orgId 单位id
 *     sTitle业务标题；
 *     dataBus 数据总线（json格式字符串或者json object）；
 *     isChoosePerson 是否启动时，选则人员信息（1：是，0否）;
 *     signOpinion 是否签署第一环节意见
 *     callback回调函数，function(result,msg)，result为boolean值，表示成功与否，msg为操作信息，成功时为空，出错时为错误信息
 */
function createFlowProcess(options) {
	var $options = {
		serviceId : options.sid,
		typeCode : options.typeCode,
		orgId: options.orgId||"",
		isChoosePerson: options.isChoosePerson||"0",
		chooseRange: options.chooseRange||"1",
		serviceTitle : options.sTitle||"",
		dataBus: options.dataBus||{}
	};

	getServiceFlowConfig($options.typeCode, $options.orgId, function(result, data) {
		if (result) {
			_startFlowProcess_s1($options,data,options.callback);
			/*$.getJSON("bpm/flowExt/getFirstActivityConfig.do?flowId=" + data.id,function(response){
				if(response.success){
					var funcs = $.kh.isNull(response.data["function"])?"":response.data["function"];
					if(funcs.indexOf("pub_signature")==-1){
						_startFlowProcess_s1($options,data,options.callback);
					}
					else{
						var sign = 0;
						if(funcs.indexOf("pub_signature")>-1) sign = 1;
						if(funcs.indexOf("pub_multi_signature")>-1) sign = 2;
						// 需要添加第一环节意见
						signSealOpinion({
							activityId: $options.serviceId,
							sid: $options.serviceId,
							seal: funcs.indexOf("pub_seal")>-1,
							signature: sign,
							callback: function(){
								_startFlowProcess_s1($options,data,options.callback);
							}
						});
					}
				}else{
					options.callback(false, response.msg);
				}
			});*/
		}else {
			options.callback(false, data);
		}
	});
}

function _startFlowProcess_s1($options, data, callback) {
	$options["flowId"] = data.id;
	if($options.isChoosePerson=="1"){
		//选择下一步参与人
		top.$.notice("请选择下一步处理人！",4);
		selectUnitOrUser($options.chooseRange, 0, null, null, function(datas){
			if($.kh.isNull(datas.code)){
				$.ligerDialog.warn("请选择至少一个人！");
				return;
			}
			var codeArr = datas.code.split(",");
			var nameArr = datas.name.split(",");
			var jsonArr = [];
			$.each(codeArr,function(i){
				jsonArr.push({id:codeArr[i],name:nameArr[i]});
			});
			$options.dataBus["paticipator"]=jsonArr;

			_startFlowProcess_s2($options,callback);
		});
	}
	else{
		_startFlowProcess_s2($options,callback);
	}
}

function _startFlowProcess_s2(options,callback){
	if(options.dataBus){
	    if(typeof options.dataBus=="object")
	    	options.dataBus=$.ligerui.toJSON(options.dataBus);
	    else
	    	options.dataBus=options.dataBus;
	}
	$.post("bpm/flowExt/startFlowProcess.do", options, function(resp) {
	    if(callback) {
	    	callback(resp.success, resp.success?resp.data:(resp.msg||""));
	    }
	},"json");
}

/**
 * 活动环节提交
 * 
 * activityId 当前节点ID serviceTitle 业务标题 dataBus 数据总线（JSON字符串）
 */
function submitActivity(options) {
	var $options = {
		activityId : options.activityId,
		serviceTitle : options.serviceTitle||""
	};
	if(options["dataBus"]){
		if($.isPlainObject(options.dataBus)) $options["dataBus"] = $.ligerui.toJSON(options.dataBus);
		else $options["dataBus"] = options.dataBus;
	}

	$('body').mask("正在提交...");
	$.post("bpm/flowExt/submitActivity.do", $options, function(response) {
		if(response.success){
			if(response.data["result_type"]=="6" && response.data["result_activity_list"].length == 1){
				_submitChildProcessFirstActivity(response.data["result_activity_list"][0],options.callback,options["userRange"]);
			}
			else if (options.callback){
				options.callback(true, response.data);
			}
		}else{
			if (options.callback)
				options.callback(false, response.msg||"");
			else
				$.ligerDialog.error("提交失败！<br/>"+ (response.msg||""));
		}
		$('body').unmask();
	}, "json");
}

/**
 * 启动子流程后，需要立即启动子流程的第一个环节
 * @param sfa
 * @param cbk
 */
function _submitChildProcessFirstActivity(sfa,cbk,userRange){
	chooseNextActivitySubmit({
		forward: true,
		userRange: userRange,
		activityId: sfa.id,
		callback: cbk
	});
}

/**
 * 活动环节回退
 * 
 * activityId 当前节点ID serviceTitle 业务标题 dataBus 数据总线（JSON字符串）
 */
function returnedActivity(options) {
	var $options = {
		activityId : options.activityId,
		dataBus : options.dataBus,
		serviceTitle : options.serviceTitle||""
	};
	$('body').mask("正在退回...");
	$.post("bpm/flowExt/returnedActivity.do", $options, function(response) {
		if (options.callback){
			if(response.success)
				options.callback(true, response.data);
			else
				options.callback(false, response.msg||"");
		}else{
			$.ligerDialog.error("退回失败！<br/>"+ (response.msg||""));
		}
		$('body').unmask();
	}, "json");
}

/**
 * 活动环节撤回
 * options:{
 * 	   activityId: 当前节点ID
 *     callback： function(result,msg){}{
 * }
 */
function recallActivity(options) {
	$('body').mask("正在撤回...");
	$.getJSON("bpm/flowExt/recallActivity.do?activityId=" + options.activityId, function(response) {
		if (options.callback){
			if(response.success)
				options.callback(true);
			else
				options.callback(false, response.msg||"");
		}else{
			$.ligerDialog.error("撤回失败！<br/>"+ (response.msg||""));
		}
		$('body').unmask();
	});
}

/**
 * 正常结束流程
 * 
 * processId 工作流ID
 */
function finishProcess(processId, callback) {
	$.post("bpm/flowExt/finishProcess.do", {
		"processId" : processId
	}, function(response) {
		if (callback)
			callback(response.success, response.success ? "" : response.msg);
		else{
			$.ligerDialog.error("结束流程失败！<br/>"+ (response.msg||""));
		}
	}, "json");
}

/**
 * 强制终止流程
 * 
 * processId 工作流ID
 */
function terminateProcess(processId, callback) {
	$.post("bpm/flowExt/terminateProcess.do", {
		"processId" : processId
	}, function(response) {
		if (callback)
			callback(response.success, response.success ? "" : response.msg);
		else if(!response.success){
			$.ligerDialog.error("终止流程失败！<br/>"+ (response.msg||""));
		}
	}, "json");
}

/**
 * 选择目标环节再提交
 * 
 * @param options
 */
function chooseNextActivitySubmit(options){
	var $options = $.extend({
		serviceTitle : "",
		callback : function(){top.$.notice("提交成功！");},
		dataBus : {}
	},options);
	
	$('body').mask("正在处理，请稍等...");
	_chooseNextActivity({
		activityId : $options.activityId,
		forward : $options.forward,
		dataBus: $options.dataBus,
		ignorSubFlow : $options.ignorSubFlow || false,
		callback: function(r,nextActivity,processDataBus,isSubflow,nextPerson){
			if(!r){
				$('body').unmask();
				return;
			}
			
			//子流程或者没有下一步环节，表示当前处于会签，直接提交会签
			if(isSubflow || nextActivity==null){
				_preSubmitActivity($options);
				return;
			}
			// 设定要提交的目标环节
			$options.dataBus["nextActivityId"] = nextActivity["id"];
			// 环节内部流转
			if(nextActivity["aid"]==$options.activityId && nextActivity["innerFlow"]=="1" && nextPerson){
				$options.dataBus["paticipator"] = nextPerson;
				_preSubmitActivity($options);
			}else if(nextActivity["chooseRolePerson"]=="1" && nextPerson){
				//如果在选择环节的时候直接选择了角色人员
				$options.dataBus["paticipator"] = nextPerson;
				$("body").mask("正在处理，请稍侯...");
				//alert("选择的人：" + JSON.stringify(nextPerson));
				_preSubmitActivity($options);
			}else{
				// 延迟0.1秒执行下一步，避免出现dialog遮罩层问题
				_checkNextActivityParticipateToSubmit(nextActivity,$options,processDataBus||{});
				//window.setTimeout(function(){
					//_checkNextActivityIsEndOfSubflow(nextActivity,$options,processDataBus||{});
				//},100);
			}
			//$('body').unmask();
		}
	});
}

/**
 * 检查下一环节是否子流程结束环节
 * @param nextActivity
 * @param options
 * @param processDataBus
 */
function _checkNextActivityIsEndOfSubflow(nextActivity,options,processDataBus){
	// 如果子流程结束，需要检查父流程下一步环节
	if(nextActivity.type=="end" && !$.kh.isNull(nextActivity.process.pid)){
		$('body').mask("正在处理，请稍等...");
		_chooseNextActivity({
			activityId : nextActivity.process.pid,
			forward : options.forward,
			ignorSubFlow : true,
			callback : function(r,snextActivity,processDataBus,isSubflow,nextPerson){
				if(r){
					_checkNextActivityParticipateToSubmit(snextActivity,options,processDataBus);
				}else{
					$.ligerDialog.error("获取可提交目标环节失败！");
				}
				$('body').unmask();
			}
		});
	}else{
		_checkNextActivityParticipateToSubmit(nextActivity,options,processDataBus);
	}
}

/**
 * 提交下一步前检查下一步的参与人设置
 * @param nextActivity
 * @param options
 */
function _checkNextActivityParticipateToSubmit(nextActivity,options,processDataBus){
	if(!options.dataBus["nextActivityId"]) 
		options.dataBus["nextActivityId"]= nextActivity.id;
	if(options.forward && nextActivity.participateType=="databus"){
		if(options.dataBus["paticipator"] || processDataBus["paticipator"]){
			_preSubmitActivity(options);
		}
		else{
			var userRange = $.kh.isNull(options["userRange"])?"1":options["userRange"];
			//选择下一步参与人
			top.$.notice("请选择下一步处理人！",4);
			$("body").unmask();
			selectUnitOrUser(userRange, (nextActivity.type=="subFlow"||nextActivity.signature=="1"||nextActivity.signature=="2"?"1":"0"), null, null, function(datas){
				if(!datas || !datas.code){
					$.ligerDialog.warn("请选择至少一个人！");
					return;
				}
				var codeArr = datas.code.split(",");
				var nameArr = datas.name.split(",");
				var jsonArr = [];
				$.each(codeArr,function(i){
					jsonArr.push({id:codeArr[i],name:nameArr[i]});
				});
				options.dataBus["paticipator"]=jsonArr;
				$("body").mask("正在处理，请稍侯...");
				_preSubmitActivity(options);
			});
		}
	}else if(options.forward && nextActivity.participateType=="role"){
		// 参与者角色范围非数据总线指定，或则数据总线已存在范围值，直接下一步
		if(nextActivity.participateRange!="databus" || !$.kh.isNull(options.dataBus["roleRange"]))
			_checkRolePersonChooseToSubmit(options,nextActivity);
		else if(!$.kh.isNull(processDataBus["roleRange"])){
			options.dataBus["roleRange"] = processDataBus["roleRange"];
			_checkRolePersonChooseToSubmit(options,nextActivity);
		}
		else{
			//其它情况，先要选择角色范围
			var orgRange = $.kh.isNull(options["orgRange"])?"0":options["orgRange"];
			$("body").unmask();
			selectUnitOrUser(orgRange, 0, null, null, function(datas){
				options.dataBus["roleRange"]=datas.code;
				$("body").mask("正在处理，请稍侯...");
				window.setTimeout(function(){
					_checkRolePersonChooseToSubmit(options,nextActivity);
				},200);
			});
			top.$.notice("请选择下一步处理单位！",4);
		}
	}else{
		_preSubmitActivity(options);
	}
}

/**
 * 检查环节角色参与者是否需要实时选择准确人员
 * 
 * @param options
 * @param nextActivity
 */
function _checkRolePersonChooseToSubmit(options,nextActivity){
	if(nextActivity["chooseRolePerson"]=="1"){//需要选择角色人员
		var range = "", dataBus={};
		if(options.dataBus["roleRange"]){
			range = options.dataBus["roleRange"];
		}
		var multipart = nextActivity.signature=="1"||nextActivity.signature=="2"?"1":"0";
		$("body").unmask();
		chooseRoleUser(nextActivity.participantId,range,multipart,"","",function(persons){
			var participators = [];
			for(var idx in persons.code){
				participators.push({id: persons.code[idx],name: persons.name[idx]});
			}
			dataBus.paticipator = participators;
			options.dataBus = dataBus;
			$("body").mask("正在处理请稍候...");
			_preSubmitActivity(options);
		});
	}else{
		_preSubmitActivity(options);
	}
}

/**
 * 提交前准备数据总线
 * @param options
 * @param dataBus
 */
function _preSubmitActivity(options){
	options.dataBus = $.ligerui.toJSON(options.dataBus);
	if(options.forward)
		submitActivity(options);
	else
		returnedActivity(options);
}

/**
 * 选择后续环节,用于提交前进行选择
 * 
 *  @param activityId 当前环节id
 * @param forward 提交还是退回 true/false
 * @param callback 回调方法
 * @param ignorSubFlow 是否忽略子流程
 */
function chooseNextActivity(activityId,forward,callback,ignorSubFlow){
	_chooseNextActivity({
		activityId : activityId,
		forward : forward || true,
		callback : callback,
		ignorSubFlow : ignorSubFlow || false
	});
}
function _chooseNextActivity(options){
	if(options.dataBus){
	    if(typeof options.dataBus=="object")
	    	options.dataBus = $.ligerui.toJSON(options.dataBus);
	    else
	    	options.dataBus = options.dataBus;
	}
	$.getJSON("bpm/flowExt/nextActivities.do", {
		"activityId" : options.activityId,
		"forward": options.forward,
		"dataBus": options.dataBus || ""
	}, function(response) {
		if(response.success){
			if(response.subFlow && !options.ignorSubFlow){
				//子流程，不能选择，直接提交启动子流程
				options.callback(true,response.data[0],response.dataBus,true,null);
			}
			else if(response.isMultiSign){
				//会签未完成，不能直接提交下一步
				options.callback(true,null,null,false,null);
			}
			else if(!response.data || response.data.length==0){
				$.ligerDialog.error("找不到可以到达的目标环节！<br/>可能的原因是"+(options.forward?"参数错误！":"此环节不允许退回！"));
				options.callback(false);
				$("body").unmask();
			}
			else{
				// 只有一个环节，不需要动态选人
				if(response.data.length == 1 
						&& (response.data[0]["participateType"] == "role" && response.data[0]["chooseRolePerson"] != "1")
						&& (response.data[0]["participateType"] == "databus" && response.dataBus["paticipator"])){
					options.callback(true,response.data[0],response.dataBus,false,null);
				}
				else{
					top.winOpen({
						lock: true,
						data: response.data,
						title: "选择下一步",
						width: 500,
						height: 255,
						parent: api,
						cancel: function(){
							$("body").unmask();
							return true;
						},
						content: "url:pub/bpm/_fun_choose_next_activity.jsp?caid=" + options.activityId,
						ok: function(){
							var v = this.iframe.contentWindow.getSelected();
							if($.kh.isNull(v)){
								//$.ligerDialog.warn("请选择下一步！");
								return false;
							}else{
								for(var i in response.data){
									if(response.data[i].id==v.activityId){
										options.callback(true,response.data[i],response.dataBus,false,v["nextPerson"]);//回调
										return true;
									}
								}
								return false;
							}
						}
					});
					top.$.notice("请选择下一步！",4);
				}
			}
		}else{
			$("body").unmask();
			$.ligerDialog.error((response.msg||"获取目标环节失败！"));
			options.callback(false);
		}
	});
}

/**
 * 查看,监控指定流程的进度
 * 
 * @param  processId 工作流ID
 */
function trackProcess(processId) {
	top.$.dialog({
		width : $(top).width() * 0.9,
		height : $(top).height() * 0.9,
		lock : true,
		parent : api,
		title : "流程监控",
		content : "url:bpm/flowExt/trackProcess.do?status=track&processId=" + processId + "&serviceId=",
		cancel : true
	});
}

/**
 * 查看,监控指定业务的流程进度
 * 
 * @param  serviceId 业务ID
 */
function trackServiceProcess(serviceId) {
	top.$.dialog({
		width : $(top).width() * 0.9,
		height : $(top).height() * 0.9,
		lock : true,
		parent : api,
		title : "流程监控",
		content : "url:bpm/flowExt/trackProcess.do?status=track&processId=&serviceId=" + serviceId,
		cancel : true
	});
}

/**
 * 签署意见
 * 
 * @param  activityId 环节ID
 * @param  callback 回调函数，只在成功时有效
 * 
 */
function signOpinion(activityId, callback) {
	signSealOpinion({
		activityId: activityId,
		callback: callback
	});
}

/**
 * 签署意见，只能签署意见内容。
 * 
 * @param  activityId 环节ID
 * @param  sid 业务id
 * @param  workItem 业务标识
 * @param  callback 回调函数，只在成功时有效
 * 
 */
function signBusOpinion(activityId, sid, workItem, callback) {
	signSealOpinion({
		activityId: activityId,
		sid: sid,
		workItem: workItem,
		callback: callback
	});
}

/**
 * 签署审批意见。使用此方法签署带有选择意向的审批意见，会出现同意不同意的选项
 * @param  activityId 环节ID
 * @param  sid 业务id
 * @param  workItem 业务标识
 * @param  callback 回调函数，只在成功时有效
 */
function signBusAppOpinion(activityId, sid, workItem, callback) {
	signSealOpinion({
		activityId: activityId,
		sid: sid,
		workItem: workItem,
		callback: callback,
		isApp: true
	});
}

/**
 * 签署审批意见。使用此方法签署带有选择意向的审批意见，会出现同意不同意的选项。
 * 同时该方式会提供数字签名和电子印章功能。
 * @param  参数对象
 */
function signSealOpinion(ops) {
	var defaultOpts = {
		workItem: "",
		sid: "",
		activityId: "",
//		isApp: false,
//		seal: false,
//		signature: false,
//		noReturn: false,
//		noSeal: false,
//		noSignature: false,
//		noReturnSeal: false,
//		noReturnSignature: false,
		callback: function(){}
	};
    var $ops = $.extend(defaultOpts,ops);
    winOpen({
        width : 900,
        height : $(top).height()-50,
        lock : true,
        parent : api,
        title : "签署意见",
        data : $ops,
        content : "url:pub/bpm/flow_opinion_detail.jsp?serviceId=" + $ops.sid + "&activityId=" + $ops.activityId + "&workitem=" + $ops.workItem
    });
}

/**
 * 检查环节是否签署过意见
 */
function checkActivitySignOpinion($options,callback){
    var options = $.extend({sid: "",aid: "",item: ""},$options);
    
	// 检查是否已签署意见
	$.getJSON("bpm/opinion/signOpinion.do",options,function(response){
		if(response.success){
		    var afunc = response.activity['function'];
		    // 环节需要签意见并且尚未签写,返回false
			if(afunc && (afunc.indexOf("pub_wf_approve") >= 0||afunc.indexOf("pub_wf_read_sign") >= 0) 
			        && (response.data==null || response.data.id==null))
				callback(false);
			else 
				callback(true,response.data);
		}else{
			$.ligerDialog.error("请求失败！<br/>" + response.msg);
		}
	});
}

/**
 * 查看意见 列表，根据以下参数，参数可为null,但不能全为null
 * 
 * @param {Object}
 *            serviceId 业务ID
 * @param {Object}
 *            activityId 环节ID
 * @param {Object}
 *            userId 用户ID
 */
function showOpinion(serviceId, activityId, userId) {
	var condition = "";
	if (serviceId)
		condition += "&serviceId=" + serviceId;
	if (activityId)
		condition += "&activityId=" + activityId;
	if (userId)
		condition += "&signerId=" + userId;
	if (condition == "") {
		top.$.ligerDialog.error("参数错误！");
		return;
	}
	condition = condition.replace(/^\&/, "?");
	top.$.dialog({
		width : 750,
		height : 400,
		lock : true,
		parent : api,
		title : "签署意见",
		content : "url:pub/bpm/flow_opinion_list.jsp" + condition,
		cancel : true
	});
}

/**
 * 展示流程意见
 * @param container 显示区域
 * @param sid 业务ID
 * @param noseal 是否不显示印章签名
 */
function createOpinionView(container,sid,isSeal,style,callback){
	if(kui.opinionViewStyle=='1'){
		if(style=='1'){
			createOpinionView1(container,sid,isSeal);
		}else{
			createOpinionView2(container,sid,isSeal,callback);
		}
	}else{
		createOpinionView1(container,sid,isSeal);
	}
}
function createOpinionView1(container,sid,isSeal,style){
	var isShowSeal = null==isSeal?true:isSeal;
	if($("#bpmopiniontable").length>0)
		$("#bpmopiniontable").remove();
	var headHtml = "<table class='l-table' id='bpmopiniontable' width='100%'><tr><td width='100' class='l-table-head' align='center'>环节</td><td width='70' class='l-table-head' align='center'>处理人</td><td width='130' class='l-table-head' align='center'>操作时间</td><td width='60' class='l-table-head' align='center'>操作结果</td><td class='l-table-head' align='center'>意见</td>";
	if(isShowSeal){
		headHtml += "<td width='60' class='l-table-head' align='center'>手写意见</td><td width='60' class='l-table-head' align='center'>签名</td><td width='60' class='l-table-head' align='center'>印章</td>";
	}
	$("#" + container).append(headHtml + "<td width='200' class='l-table-head' align='center'>附件</td></tr></table>");
	var rmap = {"0":"终止","1":"提交","-1":"退回","9":"提交"};
	$.getJSON("bpm/opinion/serviceOpinion.do",{serviceId:sid},function(resp){
		if(resp.data.length > 0){
			var html_str = "";
			$.each(resp.data,function(){
				html_str += "<tr><td class='l-table-td' align='center'>" + this.activityName + 
						"</td><td class='l-table-td' align='center'>" + this.signerName + 
						"</td><td class='l-table-td' align='center'>" + this.signDate + 
						"</td><td class='l-table-td' align='center'>" + (rmap[this.result]||"") + 
                        "</td><td class='l-table-td'>" + (this.opinion||"") + "</td>";
				if(isShowSeal){
					html_str +="<td class='l-table-td' align='center'>"+
					(this["opinionSignature"]?"<a class='l-a l-icon-detail' href='javascript:viewSignature(\"" + this["opinionSignature"] + "\")'><span>查看</span></a>":"—") + 
					"</td><td class='l-table-td' align='center'>" +
					(this["signature"]?"<a class='l-a l-icon-detail' href='javascript:viewSignature(\"" + this["signature"] + "\")'><span>查看</span></a>":"—") + 
					"</td><td class='l-table-td' align='center'>" +
					(this["seal"]?"<a class='l-a l-icon-detail' href='javascript:viewSeal(\"" + this["seal"] + "\")'><span>查看</span></a>":"—") + 
					"</td>";
				}
				html_str += "<td class='l-table-td' align='center'>";
				if(this.atts!=null && this.atts.length>0){
					$.each(this.atts,function(){
						html_str += "<p><a target='_blank' href='fileupload/download.do?id=" + this.attId + "'>"+this.attName+"</a></p>";
					});
				}else{
					html_str += "—";
				}
				html_str += "</td></tr>";
			}); 
			$("#bpmopiniontable").append(html_str);
		}
		else{
			$("#bpmopiniontable").append("<tr><td class='l-table-td' colspan='"+(isShowSeal?8:6)+"'>没有数据</td></tr>");
		}
	});
}

function createOpinionView2(container,sid,isSeal,callback){
	$("#"+container).append("<iframe id='_frame' name='_frame' width='100%' height='100%' frameBorder='0' scrolling='auto' style='BACKGROUND-COLOR: transparent'  src='pub/bpm/timeline/opinion_timeline_blue.jsp?new="+Math.random()+"&serviceId="+sid+"&isSeal="+isSeal+"'></iframe>")
    var iframe = document.getElementById("_frame");
	if (iframe.attachEvent) {
		iframe.attachEvent("onload", function() {
			setHeight(callback);
		});
	} else {
		iframe.onload = function() {
			setHeight(callback);
		};
	}
}
function setHeight(callback){
	$("#_frame").height($(window).height()+20)
	if(callback){
		callback();
	}
}
/**
 * 查看签名
 * 
 * @param signIds
 */
function viewSignature(signIds){
	winOpen({
		content: "url:pub/eseal/bc/view_sign_seal.jsp",
		data: {ids:signIds},
		width: 800,
		height: 450,
		title: "查看签名",
		parent: api
	});
}

/**
 * 查看印章
 * @param sealIds
 */
function viewSeal(sealIds){
	winOpen({
		content: "url:pub/eseal/bc/view_sign_seal.jsp",
		data: {ids:sealIds},
		width: 350,
		height: 300,
		title: "查看印章",
		parent: api
	});
}

//获取业务意见
function getServiceOpinions(serviceId,callback){
	$.getJSON("bpm/opinion/serviceOpinion.do?serviceId="+serviceId,function(resp){
		if(resp.success)
			callback(resp.data);
	});
}