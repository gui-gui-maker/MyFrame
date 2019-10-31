function selectUser() {  	
        var a = selectUnitOrUser_id(1, 1, "receiversId", "receiversName","1")    
    }
    
    function selectUnitOrUser_id(type, isCheckBox, code, name, status) {
    	var title = "选择";
    	var url = "url:app/oa/car/car_type_manager.jsp?type=" + type
    	var width = 1040;
    	url += "&checkbox=" + isCheckBox + "&fieldName=" + name + "&fieldId=" + code + "&status=" + status;	
    	top.$.dialog({
    		width : width,
    		height : 500,
    		lock : true,
    		parent : api,
    		id : "selectUnitOrUser",
    		title : title,
    		content : url,
    		cancel: true,
    		ok : function() {
    			var datas = this.iframe.contentWindow.getSelectResult();
    			if(datas){
    				if(code){
    					$("#" + code).val(datas.code);
    				}
    				if(name)
    					$("#" + name).val(datas.name);
    				/*if(callback) 
    					callback(datas);*/
    				return true;
    			}
    			else return false;
    		}
    	});
   	    	
    }