var formulaItemArr=[];

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
	var oprName = opratorMap[param].name;
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
			formulaStr = formulaStr.replace(this.regOpr, this.name);
		}
	});
	$.each(formulaItemArr,function(key,item){
		var reg = new RegExp(item.variable + "", "gi");
		formulaStr = formulaStr.replace(reg, item.name);
	});
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
	var formula = $("#formula").val();
	formula = formula.replace(/(((\s|^)\S+)|\s)$/,"");
	$("#formula").val(formula);
	parseFormulaToDesc();
}

//操作符名称代码对应表
var opratorMap = {
    "add" : {
        name : " + ",
        opr : "+",
        regOpr : /\+/g,
        ignor : false
    },

    "subtract" : {
        name : " - ",
        opr : "-",
        regOpr : /\-/g,
        ignor : false
    },

    "multiply" : {
        name : " × ",
        opr : "*",
        regOpr : /\*/g,
        ignor : false
    },

    "divide" : {
        name : " ÷ ",
        opr : "/",
        regOpr : /\//g,
        ignor : false
    },

    "left_bracket" : {
        name : "（",
        opr : "(",
        regOpr : /\(/g,
        ignor : false
    },

    "right_bracket" : {
        name : "）",
        opr : ")",
        regOpr : /\)/g,
        ignor : false
    },

    "left_big_bracket" : {
        name : "｛",
        opr : "{",
        regOpr : /\{/g,
        ignor : false
    },

    "right_big_bracket" : {
        name : "｝",
        opr : "}",
        regOpr : /\}/g,
        ignor : false
    },

    "if" : {
        name : "如果",
        opr : "if",
        regOpr : /if/g,
        ignor : false
    },

    "then" : {
        name : "那么",
        opr : "",
        ignor : true
    },

    "else" : {
        name : "否则",
        opr : "else",
        regOpr : /else/ig,
        ignor : false
    },

    "gte" : {
        name : " ≥ ",
        opr : ">=",
        regOpr : /\>\=/g,
        ignor : false
    },

    "equal" : {
        name : " ＝ ",
        opr : "==",
        regOpr : /\=\=/g,
        ignor : false
    },

    "no_equal" : {
        name : " ≠ ",
        opr : "!=",
        regOpr : /\!\=/g,
        ignor : false
    },

    "lte" : {
        name : " ≤ ",
        opr : "<=",
        regOpr : /\<\=/g,
        ignor : false
    },

    "gt" : {
        name : " ＞ ",
        opr : ">",
        regOpr : /\>/g,
        ignor : false
    },

    "lt" : {
        name : " ＜ ",
        opr : "<",
        regOpr : /\</g,
        ignor : false
    },

    "and" : {
        name : " 并且 ",
        opr : "&&",
        regOpr : /\&\&/g,
        ignor : false
    },

    "or" : {
        name : " 或者 ",
        opr : "||",
        regOpr : /\|\|/g,
        ignor : false
    },

    "round" : {
        name : "四舍五入",
        opr : "round",
        regOpr : /round/ig,
        ignor : false
    },
    
    "enter" : {
    	name : "\n",
    	opr : "\n",
        regOpr : /\n/g,
        ignor : false
    }
}