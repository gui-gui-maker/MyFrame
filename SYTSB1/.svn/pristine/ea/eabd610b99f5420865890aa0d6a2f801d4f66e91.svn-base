
function waitFor(testFx, onReady, timeOutMillis) {
    var maxtimeOutMillis = timeOutMillis ? timeOutMillis : 10000, //< Default Max Timout is 10s
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
	var width=700;
	var height=1000;
	var orientation='portrait';
	var border='1cm';
	
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
			//横屏
			if(landFlag=='1'){
				width=1100;
				height=750;
				orientation='landscape';
				border= '0.1cm';
			}
			console.log("landFlag :"+landFlag);

			page.viewportSize = { width: width, height: height };
			page.paperSize = { format: 'A4', 
			  orientation: orientation, 
			  border: border };
					
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