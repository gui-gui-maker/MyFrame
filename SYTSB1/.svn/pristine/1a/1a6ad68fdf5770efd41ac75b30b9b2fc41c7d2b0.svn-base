/**
 * 网页打印
 * User: GaoYa
 * Date: 2014-03-05
 * Time: 上午10:18
 */
//****************************************************************************
// 网页打印
var hkey_root,hkey_path,hkey_key;
hkey_root="HKEY_CURRENT_USER";
hkey_path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";

// 网页打印时清空页眉页脚
function pagesetup_null(){
	try{
 		var RegWsh = new ActiveXObject("WScript.Shell")
 		hkey_key="header" 
		RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"")
		hkey_key="footer"
		RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"")
	}catch(e){
	}
} 

// 网页打印的时恢复页眉页脚为默认值
function pagesetup_default(){
 	try{
		var RegWsh = new ActiveXObject("WScript.Shell")
		hkey_key="header" 
		RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"&w&b页码，&p/&P")
		hkey_key="footer"
		RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"&u&b&d")
	}catch(e){
	}
}