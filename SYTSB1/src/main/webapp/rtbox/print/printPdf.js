/**
 * 采用puppeteer 调用google headless打印PDF
 * 
 */
const puppeteer = require('puppeteer');

if (process.argv.length < 4) {
    console.log('error: no enough param');
    process.exit(-1);
}
(async () => {
   const browser = await puppeteer.launch({headless:true});
	const page = await browser.newPage();
	page.setUserAgent('Mozilla/5.0 Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36');
	page.emulateMedia('print'); //设置打印机媒体样式ʽ

	try{
		var address = process.argv[2];
		console.log(address);
		var output = process.argv[3];
		console.log(output);

		//var address='http://localhost:8047/rtbox/app/templates/SYTS_ZWDTDQJYBG/index4.jsp?fk_report_id=402880ee655f8246016560b78d270008&fk_inspection_info_id=402880ee655f8246016560c6c47c000c&pageStatus=detail&mobile=1';
		await page.goto(address, {waitUntil: 'networkidle2'});
		/*const imgs = await page.$$eval('img', imgs =>   
			Promise.all(
			imgs.map(img => {
				if (img.getAttribute('data-ks-lazyload')) {
					img.src = img.getAttribute('data-ks-lazyload');
					return new Promise(resolve => img.onload = resolve);
				} else {
					return new Promise(resolve => resolve())
				}
	
			})
		));*/

		//await page.waitForSelector('#initReadyFlag');//等待页面加载完成
		await page.waitForSelector('#initReadyFlag', {
			timeout: 60000
		});
		
		const landFlag = await page.evaluate(() => {
			var obj=document.getElementById("landFlag");
			//var i =obj.value;
			if(obj&&obj.value=="1"){
				return true;
			}
			return false;
		});
		console.log("landFlag:"+landFlag);
	
		const documentSize = await page.evaluate(() => {
			return {
				width : document.body.clientWidth,
				height : document.body.clientHeight
			}
		})		
		console.log("height:"+documentSize.height);
		console.log("width:"+documentSize.width);
		await page.pdf({path:output, format:"A4",landscape:landFlag});
		await browser.close();
	}catch(e){
		console.log(e);
		
        process.exit(-1);
	}
})();