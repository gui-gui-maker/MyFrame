<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<div data-role="page" data-control-title="test" id="page5">
    <div data-role="content">
    	<script type="text/javascript">
		$(function(){
			$("#formObj").initForm({
				success:function(res){
					alert(res.data.id);
					$.mobile.changePage('qm_2_list.jsp', {
						transition: "slide",
						reloadPage: false
					});
				},getSuccess:function(res){
					if($("head").attr("pageStatus")=='detail'){
						$("#formObj").setValues({property:'内部职能部门',date:'2012-00-00',slider:'关闭',slider1:'20',sex:'男',discrible:'这里是备注',effect:'效果1,效果2',search:'搜dfa件',choice4:'一天',choice3:'12小时'})
					}else{
						$("input[name='sex']").each(function(){
						if($(this).val()=='0'){
							$(this).attr("checked","checked");
						}
					})
					$("input[name='sex']").checkboxradio("refresh")
					
					$("#slider").val("off");
					$("#slider").slider("refresh");
					
					$("#select-choice-4").val("2d");
					$("#select-choice-4").selectmenu("refresh");
					
					$("#select-choice-3 option").each(function(){
					    $(this).removeAttr("selected")
						if($(this).val()=='2d'){
							$(this).attr("selected","selected")
						}
						if($(this).val()=='1d'){
							$(this).attr("selected","selected")
						}
					})	
					$("#select-choice-3").selectmenu("refresh");
					$("input[name='effect']").each(function(){
						var ttt = "1,3";
						for(var i=0;i<ttt.split(",").length;i++){
							if($(this).val()==ttt.split(",")[i]){
								$(this).prop( "checked", true ).checkboxradio( "refresh" );
							}
						}
					})
					//$("input[name='effect']").checkboxradio("refresh")
					
					$("#slider1").val(29).slider("refresh");
					$("#textarea1").val("2333333333333333").textinput();
					
					$("#date").val('2012-01-01')
					$("#search").val("2323")
					}
					//alert("解析成功");
				}
			})
		})
	</script>
        <form id="formObj" action="rbac/org/saveOrg.do" method="POST" getAction="rbac/org/detail.do?id=${param.orgId }">
            <div data-role="fieldcontain" data-controltype="textinput">
                <label for="textinput3">
                    	编号
                </label>
                <input name="orgCode" id="textinput3" placeholder="" value="" type="text">
            </div>
            <div data-role="fieldcontain" data-controltype="textinput">
                <label for="textinput4">
                    	名称
                </label>
                <input name="orgName" id="textinput4" placeholder="" value="" type="text">
            </div>
            <div data-role="fieldcontain" data-controltype="textinput">
                <label for="textinput5">
                    	联系电话
                </label>
                <input name="tellphone" id="textinput5" placeholder="" value="" type="text">
            </div>
            <div data-role="fieldcontain" data-controltype="selectmenu">
                <label for="selectmenu1">
                   	 机构性质
                </label>
                <select id="selectmenu1" name="property">
                    <option value="unit">
                        	独立法人单位
                    </option>
                    <option value="dep">
                        	内部职能部门
                    </option>
                </select>
            </div>
            <div data-role="fieldcontain" data-controltype="textarea">
                <label for="textarea1">
                  	  备注
                </label>
                <textarea name="discrible" id="textarea1" placeholder=""></textarea>
            </div>
             <div id="sex" data-role="fieldcontain" data-controltype="radiobuttons">
			            <fieldset data-role="controlgroup" data-type="horizontal">
			                <legend>性别</legend>
			                <label for="radio1">男</label>
			                <input id="radio1" name="sex" value="1" type="radio" checked="checked">
			                <label for="radio2"> 女</label>
			                <input id="radio2" name="sex" value="0" type="radio">
			                <label for="radio3"> 中性</label>
			                <input id="radio3" name="sex" value="2" type="radio">
			            </fieldset>
			        </div>
			        <div data-role="fieldcontain">
						<fieldset data-role="controlgroup" data-type="vertical" >
							<legend>复选框</legend> 
							<input type="checkbox" name="effect" id="horizon-effect1" value="1"/> 
							<label for="horizon-effect1">效果1</label>
							<input type="checkbox" name="effect" id="horizon-effect2" value="2" />
							<label for="horizon-effect2">效果2</label>
							<input type="checkbox" name="effect" id="horizon-effect3"  value="3" />
							<label for="horizon-effect3">效果3</label>
						</fieldset>
					</div>
					<div data-role="fieldcontain">
			            <label for="date">
			               	 日期
			            </label>
			            <input id="date" name="date" type="date" data-role="date" >
			        </div>
			        <div data-role="fieldcontain">
						<label for="slider">开关</label>
						<select name="slider" id="slider" data-role="slider">
							<option value="off" >关闭</option>
							<option value="on" selected="selected">开启</option>
						</select> 
					</div>
				
					<div data-role="fieldcontain">
						<label for="slider1">滑杆</label>
						<input type="range" name="slider1" id="slider1"  min="0" max="100" data-highlight="true"/>
					</div>
					<div data-role="fieldcontain">
						<label for="search">搜索输入框</label>
						<input type="search" name="search" id="search" value="" />
					</div>
					<div data-role="fieldcontain">
						<label for="select-choice-4" class="select">选择列表4</label>
						<select data-native-menu="false" name="choice4" id="select-choice-4">
							<optgroup label="第一组">
								<option value="12h" >12小时</option>
								<option value="1d">一天</option>
								<option value="2d">两天</option>
								<option value="week">一周</option>
							</optgroup>
							<optgroup label="第二组">
								<option value="1m">一个月</option>
								<option value="1q" selected="selected">一季度</option>
								<option value="1y">一年</option>
							</optgroup>
						</select>
					</div>
					<div data-role="fieldcontain">
						<label for="select-choice-3" class="select">选择列表3</label>
						<select data-native-menu=false multiple="multiple" name="choice3" id="select-choice-3">
							<option value="12h">12小时</option>
							<option value="1d" >一天</option>
							<option value="2d">两天</option>
							<option value="week" >一周</option>
						</select>
					</div>
            <div data-role="content">
			    <input id="submit" type="submit" data-theme="d" value="Submit" >
			</div>
        </form>
    </div>
</div>
