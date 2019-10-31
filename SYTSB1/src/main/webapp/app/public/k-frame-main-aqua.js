$.extend(kFrameConfig,{
	user:{
		icon:"k/kui/images/head/head.png",iconTop:0,iconLeft:0
	},
	mainOnload:function(){
		//$("#userHeadImg").html('<img src="'+this.user.icon+'">');
		$("#userHeadImg").css({'background-image':'url("'+this.user.icon+'")','width':'40px','height':'40px','background-position':''+this.user.iconLeft+'px '+this.user.iconTop+'px'});
		return false;
	}
});