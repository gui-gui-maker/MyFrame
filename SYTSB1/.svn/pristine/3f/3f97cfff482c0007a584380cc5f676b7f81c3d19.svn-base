/**
 * 添加规则公式要素到公式中.
 * 
 * @param item
 */
function formulaItemAdd(item) {
	$("#formulaDesc").append(item.name);
	$("#formula").val($("#formula").val() + item.variable);
}

/**
 * 添加操作符到公式
 * 
 * @param oprName
 */
function formulaOprAdd(param) {
	var oprName = opratorMap[param].name.replace(/\]/gi,">").replace(/\[/gi,"<").replace(/@/,"/");
	$("#formulaDesc").append(oprName);
	$("#formula").val($("#formula").val() + " " +opratorMap[param].opr + " ");
}

/**
 * 解析公式，将代码转换为自然语言表述
 */
function parseFormulaToDesc(){
	var formulaStr = $("#formula").val();
	$.each(opratorMap,function(key,val){
		if(!this.ignor){
			formulaStr = formulaStr.replace(this.regOpr,this.name);
		}
	});
	formulaStr = formulaStr.replace(/\]/g,">").replace(/\[/g,"<").replace(/\@/g,"/");
	$("#formulaDesc").html(formulaStr);
}

/**
 * 添加数字到公式
 */
function addNumberToFormula(num){
	$("#formulaDesc").append(num);
	$("#formula").val($("#formula").val() + num);
}

/**
 * 退格操作，这将会从公式中删除最后添加的内容
 */
function doBackSpace(){
	$("#formulaDesc").html($("#formulaDesc").html().replace(/\s+$/gi).replace(/\s(\S|(\w*))$/,""));
	$("#formula").val($("#formula").val().replace(/\s+|\n$/gi).replace(/\s(\S|(\w*))$/,""));
}

//操作符名称代码对应表
var opratorMap = {
    "add" : {
        name : " [span]+[@span] ",
        opr : "+",
        regOpr : /\+/ig,
        ignor : false
    },

    "subtract" : {
        name : " [span]-[@span] ",
        opr : "-",
        regOpr : /\-/ig,
        ignor : false
    },

    "multiply" : {
        name : " [span]×[@span] ",
        opr : "*",
        regOpr : /\*/ig,
        ignor : false
    },

    "divide" : {
        name : " [span]÷[@span] ",
        opr : "/",
        regOpr : /\//ig,
        ignor : false
    },

    "left_bracket" : {
        name : "（",
        opr : "(",
        regOpr : /\(/ig,
        ignor : false
    },

    "right_bracket" : {
        name : "）",
        opr : ")",
        regOpr : /\)/ig,
        ignor : false
    },

    "left_big_bracket" : {
        name : "[span]｛[@span]",
        opr : "{",
        regOpr : /\{/ig,
        ignor : false
    },

    "right_big_bracket" : {
        name : "[span]｝[@span]",
        opr : "}",
        regOpr : /\}/ig,
        ignor : false
    },

    "if" : {
        name : "[span]如果[@span]",
        opr : "if",
        regOpr : /if/ig,
        ignor : false
    },

    "then" : {
        name : "[span]那么[@span]",
        opr : "",
        ignor : true
    },

    "else" : {
        name : "[span]否则[@span]",
        opr : "else",
        regOpr : /else/ig,
        ignor : false
    },

    "gte" : {
        name : " [span]≥[@span] ",
        opr : ">=",
        regOpr : /\>\=/ig,
        ignor : false
    },

    "equal" : {
        name : " [span]＝[@span] ",
        opr : "==",
        regOpr : /\=\=/ig,
        ignor : false
    },

    "no_equal" : {
        name : " [span]≠[@span] ",
        opr : "!=",
        regOpr : /\!\=/ig,
        ignor : false
    },

    "lte" : {
        name : " [span]≤[@span] ",
        opr : "<=",
        regOpr : /\<\=/ig,
        ignor : false
    },

    "gt" : {
        name : " [span]＞[@span] ",
        opr : ">",
        regOpr : /\>/ig,
        ignor : false
    },

    "lt" : {
        name : " [span]＜[@span] ",
        opr : "<",
        regOpr : /\</ig,
        ignor : false
    },

    "and" : {
        name : " [span]并且[@span] ",
        opr : "&&",
        regOpr : /\&\&/ig,
        ignor : false
    },

    "or" : {
        name : " [span]或者[@span] ",
        opr : "||",
        regOpr : /\|\|/ig,
        ignor : false
    },

    "round" : {
        name : "[span]四舍五入[@span]",
        opr : "round",
        regOpr : /round/ig,
        ignor : false
    },
    
    "enter" : {
    	name : "<br/>",
    	opr : "\n",
        regOpr : /\n/ig,
        ignor : false
    }
}