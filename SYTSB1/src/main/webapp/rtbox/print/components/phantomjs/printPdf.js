/**
 * 采用phantomjs打印PDF 已废弃
 * */
function waitFor(testFx, onReady, timeOutMillis) {
    var maxtimeOutMillis = timeOutMillis ? timeOutMillis : 50000, //< Default Max Timout is 30s
        start = new Date().getTime(),
        condition = false,
        interval = setInterval(function() {
            if ( (new Date().getTime() - start < maxtimeOutMillis) && !condition ) {
                // If not time-out yet and condition not yet fulfilled
                condition = (typeof(testFx) === "string" ? eval(testFx) : testFx()); //< defensive code
            } else {
                if(!condition) {
                    // If condition still not fulfilled (timeout but condition is 'false')
                    console.log("'waitFor()' timeout");
                    phantom.exit(1);
                } else {
                    // Condition fulfilled (timeout and/or condition is 'true')
                    console.log("'waitFor()' finished in " + (new Date().getTime() - start) + "ms.");
                    typeof(onReady) === "string" ? eval(onReady) : onReady(); //< Do what it's supposed to do once the condition is fulfilled
                    clearInterval(interval); //< Stop this interval
                }
            }
        }, 250); //< repeat check every 250ms
};

var page = require('webpage').create(),
    system = require('system');
	phantom.outputEncoding="gbk";
if (system.args.length < 3) {
    console.log('no enough param');
    phantom.exit(1);
} else {
    var address = system.args[1];
    var output = system.args[2];
    var x= system.args[3];
    var y= system.args[4];
    var width=700;
	var height=1000;
	if(x=="1366"){
		
		 width=700;
		 height=1000;
	}else if(x=="1920"){
		 width=1000;
		 height=1859;
	}
	
	var orientation='portrait';
	var border='1cm';
	var margin='0.8cm'; 
	
	page.open(address, function(status) {
		if ( status === "success" ) {		
			var landFlag = page.evaluate(function () {
				var element = document.getElementById('landFlag');
				var value='-1';
				if(element){
					value=element.value;
				}
				return  value;
			});
			
			var contentHeight = page.evaluate(function () {
				var ch = document.getElementById("formObj").offsetHeight;
				return  ch;
			});
			console.log("contentHeight :"+contentHeight);
			if(x<contentHeight){
				height=contentHeight;
				
			}
			console.log("height :"+height);
			
			//var $("#formObj").height();
			//横屏
			if(landFlag=='1'){
				width=1100;
				height=750;
				orientation='landscape';
				border= '0.1cm';
				margin='0.8cm';
			}
			
			
			page.viewportSize = { width: width
					, height: height		
			};
			page.paperSize = { format: 'A4', 
			  orientation: orientation, 
			  //border: border
			  margin:margin};
					
			 waitFor(function() {
					// Check in the page if a specific element is now visible
				 return page.evaluate(function() {
					var element = document.getElementById('initReadyFlag');
					return element;
				 });
			}, function() {
			   page.render(output);
			   phantom.exit(0);
			});
		
	    } else {
		  console.log("url open error");
		  phantom.exit(1);
		}
	});
}