//加载capicom activex控件
/*
 document.write('<object id="oCAPICOM" codeBase="http://download.microsoft.com/download/E/1/8/E18ED994-8005-4377-A7D7-0A8E13025B94/capicom.cab #version=2,0,0,3"'
				+ 'classid="clsid:A996E48C-D3DC-4244-89F7-AFA33EC60679"></object>');
*/
var CAPICOM_CURRENT_USER_STORE = 2;
var CAPICOM_STORE_OPEN_READ_ONLY = 0;
var CAPICOM_ENCODE_BASE64 = 0;
var CAPICOM_ENCODE_BINARY = 1;

var CAPICOM_INFO_SUBJECT_SIMPLE_NAME = 0;
var CAPICOM_INFO_ISSUER_SIMPLE_NAME = 1;
var CAPICOM_INFO_SUBJECT_EMAIL_NAME = 2;
var CAPICOM_INFO_ISSUER_EMAIL_NAME = 3;

function checkIssuerCert(isser) {
	var mystore = new ActiveXObject("CAPICOM.Store");
	mystore.Open(CAPICOM_CURRENT_USER_STORE, "My", CAPICOM_STORE_OPEN_READ_ONLY);
	var certs = mystore.Certificates;
	var count = 0;
	for (i = 1; i <= certs.Count; i++) {
		var IssuerName = certs.Item(i).IssuerName;
		if (IssuerName.indexOf("CN=" + isser ) < 0) // not myca
			continue;
		count++;
	}
	return count;
}

function SignText(strtext) {
	// select cert
	var mysigncert = SelectMySignCert();
	if (mysigncert == null)
		return null;
	// signed data
	var signer = new ActiveXObject("CAPICOM.Signer");
	signer.Certificate = mysigncert;
	var signeddata = new ActiveXObject("CAPICOM.SignedData");
	var utils = new ActiveXObject("CAPICOM.Utilities");
	signeddata.Content = utils.BinaryStringToByteArray(strtext);
	try{
		return signeddata.Sign(signer, false, CAPICOM_ENCODE_BASE64);
	}
	catch(e){
		alert(e);
	}
}
/*
 * var signature= SignText("hello"); WScript.Echo(signature);
 */