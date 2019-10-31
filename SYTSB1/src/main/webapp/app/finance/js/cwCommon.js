// 创建意见签署框，针对不通环节意见框显示在不同区域
function initSignOpinionArea() {
    var allOpinionItem = ["ry_bg_report_sign","ry_bg_zgbm_sign","ry_bg_ks_sign","ry_bg_fgld_sign","ry_bg_center_sign"];
    $.each(allOpinionItem,function(){
        if (_a_function.indexOf(this) >= 0) {
            $("#" + this + " .opinion").html("<textarea class='sign-opinion' name='" + this + "'></textarea>");
            $("#" + this + " .signer").html("<input class='signerName' name='" + this + "-signer' />");
            $("#" + this + " .date").text(approveSignDate);
            window["sealArea"] = this;
        }
    });
}

// 意见内容初始化
function initSignedOpinion(){
    $.getJSON("bpm/opinion/serviceOpinion.do",{serviceId:_service_id},function(resp){
        if(resp.data.length > 0){
            $.each(resp.data,function(idx){
                if(!this.workitem)return;
                $("#" + this.workitem).attr("oid",this.id);
                $("#" + this.workitem + " .opinion").html("<span>"+this.opinion+"</span>");
                $("#" + this.workitem + " .date").text(this.signDate.substring(0,10).replace(/\-/g,"."));
                $("#" + this.workitem + " .signer").text(this.signerName);
                $("#" + this.workitem).append("<div class='seal' id='" + this.workitem + "_seal'></div>");
                if(this.seal)
                    showSecSeal(this.seal,this.workitem + "_seal");
            });
        }
    });
    initSignOpinionArea();
}

// 退回到前面步骤
function turnback() {
    _saveOpinion(function(){
        $.ligerDialog.confirm("确定要退回？", function(yes) {
            if (!yes)
                return;
            _turnback();
        });
    },false);
}
function _turnback() {
    // 选择要退回的目标环节
    chooseNextActivitySubmit({
        forward : false,// 标识为退回
        activityId : _activity_id,
        callback : function(result, data) {
            if (result) {
                top.$.notice("退回成功！");
                if(api.data.callback) api.data.callback();
                api.close();
            } else {
                $.ligerDialog.error("操作失败！<br/>" + data);
            }
        }
    });
}

// 终止流程
function reject() {
    _saveOpinion(function(){
        $.ligerDialog.confirm("确定拒绝该申请？<br/>注意：这将直接终止流程！", function(yes) {
            if (!yes)
                return;
            _reject();
        });
    },false);
}

function _reject(){
    $("body").mask("正在处理....");
    terminateProcess(_process_id,function(result,data){
        $("body").unmask();
        if(result){
            top.$.notice("操作成功！该申请流程已终止！");
            if(api.data.callback) api.data.callback();
            api.close();
        }else{
            $.ligerDialog.error("操作失败！<br/>" + data);
        }
    });
}