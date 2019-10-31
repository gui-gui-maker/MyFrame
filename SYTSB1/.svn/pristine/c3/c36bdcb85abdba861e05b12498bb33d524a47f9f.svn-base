package com.khnt.signseal.web;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.base.Factory;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.core.exception.KhntException;
import com.khnt.security.CurrentSessionUser;
import com.khnt.signseal.bean.SignSeal;
import com.khnt.signseal.service.SignSealManager;
import com.khnt.signseal.util.RemoteCallException;
import com.khnt.signseal.util.TakeSamples;
import com.khnt.utils.LogUtil;
import com.khnt.utils.StringUtil;

import cn.eseals.bbf.data.Base64;
import cn.eseals.seal.data.PictureObject;
import cn.eseals.seal.data.SealInfo;
import cn.eseals.seal.data.SecureKey;
import cn.eseals.seal.data.StandardDataObject;
import cn.eseals.seal.data.StaticClientDataProvider;

/**
 */
@Controller
@RequestMapping("/pub/signseal/")
public class SignSealAction extends SpringSupportAction<SignSeal, SignSealManager> {

	@Autowired
	private SignSealManager signSealManager;

	/**
	 * 获取签名列表
	 * 
	 * @Title: getListByIds @author bait @date 2013-3-25 下午5:07:11 @param
	 *         Ids @return HashMap<String,Object> @throws
	 */
	@RequestMapping("getListByIds")
	@ResponseBody
	public HashMap<String, Object> getListByIds(String Ids) {
		return JsonWrapper.successWrapper(signSealManager.getListByIds(Ids));
	}

	/**
	 * 保存签章或签名数据到数据库中
	 * 
	 * @Title: saveSignSeal @author bait @date 2013-3-25 上午11:04:16 @param
	 *         entity @return @throws Exception HashMap<String,Object> @throws
	 */
	@RequestMapping(value = "saveSignSeal")
	@ResponseBody
	public HashMap<String, Object> saveSignSeal(@RequestBody SignSeal entity) throws Exception {
		entity.setTime(new Date());
		entity.setCreator(getCurrentUser().getUserId());
		this.signSealManager.save(entity);
		return JsonWrapper.successWrapper(entity);
	}

	/**
	 * 删除印章或者签名返回印章的相关内容
	 */
	@RequestMapping("delSignSeal")
	@ResponseBody
	public HashMap<String, Object> delete(String ids) throws Exception {
		SignSeal entity = signSealManager.get(ids);
		if (entity != null) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("id", ids);
			map.put("proData", entity.getProtectData());
			signSealManager.deleteBusiness(ids);
			return JsonWrapper.successWrapper(map);
		} else {
			return JsonWrapper.failureWrapper("msg", "参数错误，当前印章数据未找到！");
		}
	}

	/**
	 * web签章
	 * 
	 * @Title: signSeal
	 * @author liuSir
	 * @Description: 盖章
	 * @throws IOException
	 */
	@RequestMapping("signSeal")
	@ResponseBody
	public String signSeal(String clientData) {
		if(StringUtil.isEmpty(clientData))
			throw new KhntException("签章保护内容不能为空");
		
		String passWord = "1"; // 证书密码
		// byte[]印章内容
		byte[] contents = null;
		// 证书内容
		byte[] keyData = null;
		String userCertsFolder = Factory.getSysPara().getProperty("eseal_user_certs_folder");
		String sigProFileFolder = Factory.getSysPara().getProperty("eseal_sig_pro_folder");
		if (StringUtil.isEmpty(userCertsFolder)) {
			log.error("用户证书文件存放目录未配置。");
			throw new KhntException("签章失败，找不到用户证书");
		}
		CurrentSessionUser user = getCurrentUser();
		try {
			String pfxPath = userCertsFolder + File.separator + user.getUnit().getOrgName() + ".pfx";
			String sealPath = userCertsFolder + File.separator + user.getUnit().getOrgName() + ".xml";
			contents = TakeSamples.toByteArray(sealPath);
			keyData = TakeSamples.toByteArray(pfxPath);
			boolean flag = this.checkPsd(keyData, passWord);
			if (!flag) {
				throw new RemoteCallException(RemoteCallException.RCE_FAILED, "证书密码错误！");
			}
			StandardDataObject sdo = this.signSingleSeal(contents, keyData, passWord, clientData);
			// 盖章结果解析为byte数组
			byte[] resultByte = this.parseSignData(sdo);
			// 图片结果解析为byte数组
			byte[] imageByte = this.parsePictureData(sdo);
			String imagePng = Base64.encode(imageByte);
			// 获取图片对象
			PictureObject po = sdo.getSealInfo().getPictureObject();
			String signatureId = this.writeSigSealFile(resultByte, sigProFileFolder);
			writeProSealFile(signatureId, clientData, sigProFileFolder);
			double xWidth = po.getRealWidth();
			double yHeight = po.getRealHeight();
			return "{\"success\":true,\"data\":{\"signatureId\":\"" + signatureId + "\",\"image\":\"" + imagePng
			        + "\",\"xWidth\":\"" + xWidth + "\",\"yHeight\":\"" + yHeight + "\"}}";
		} catch (Exception e) {
			LogUtil.logError(log, e);
			return "{\"success\":false}";
		}
	}

	/**
	 * 查看印章
	 * 
	 * @param signatureId
	 * @return
	 */
	@RequestMapping("viewSeal")
	@ResponseBody
	public String viewSingleSeal(String signatureId) {
		try {
			StandardDataObject sdo = new StandardDataObject();
			byte[] sealContent = this.getWriteFile(signatureId, "sig");
			String signXml = new String(sealContent, "UTF-8");
			sdo.setXML(signXml);
			byte[] imageByte = this.parsePictureData(sdo);
			String imagePng = Base64.encode(imageByte);
			PictureObject po = sdo.getSealInfo().getPictureObject();
			double xWidth = po.getRealWidth();
			double yHeight = po.getRealHeight();
			return "{\"success\":true,\"data\":{\"image\":\"" + imagePng + "\",\"xWidth\":\"" + xWidth
			        + "\",\"yHeight\":\"" + yHeight + "\"}}";
		} catch (Exception e) {
			LogUtil.logError(log, e);
			return "{\"success\":false}";
		}
	}

	/**
	 * @Title: writeSigSealFile
	 * @author liuSir
	 * @Description: 写入文件后返回文件名称
	 * @param resultByte
	 * @return
	 * @throws Exception
	 */
	private String writeSigSealFile(byte[] resultByte, String sysSealSigProFileFolder) throws Exception {
		String uuid = UUID.randomUUID().toString();
		FileOutputStream fos = null;
		try {
			File tmp = new File(sysSealSigProFileFolder + File.separator + uuid + ".sig");
			fos = new FileOutputStream(tmp);
			fos.write(resultByte);
			fos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
		return uuid;
	}

	private void writeProSealFile(String signatureId, String clientData, String sigProFileFolder) {
		FileOutputStream fos = null;
		try {
			File tmp = new File(sigProFileFolder + File.separator + signatureId + ".pro");
			fos = new FileOutputStream(tmp);
			byte[] data = clientData.getBytes();
			fos.write(data, 0, data.length);
			fos.flush();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@RequestMapping("verify")
	@ResponseBody
	public String verifySealSingle(String signatureId, String protectValue) throws IOException {
		try {
			StandardDataObject sdo = new StandardDataObject();
			byte[] sealContent = this.getWriteFile(signatureId, "sig");
			String clientData = new String(this.getWriteFile(signatureId, "pro"));
			boolean flag = this.verify(sealContent, clientData);
			String signXml = new String(sealContent, "UTF-8");
			sdo.setXML(signXml);
			SealInfo sealInfo = sdo.getSealInfo();
			String department = sealInfo.getDepartment();
			String signerid = sealInfo.getSignerId();
			String createUser = sealInfo.getCreateUser();
			String title = sealInfo.getTitle();
			Date signSealTime = sdo.getSignSealInfo().getSignTime();

			String data = "{\"signSealTime\":\"" + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(signSealTime) + "\",\"department\":\"" + department
			        + "\",\"createUser\":\"" + createUser + "\",\"title\":\"" + title + "\",\"signerid\":\"" + signerid
			        + "\"}";
			if (protectValue.indexOf(clientData) == 0 && flag) {
				return "{\"success\":true,\"data\":" + data + "}";
			} else {
				// 验证失败
				return "{\"success\":false,\"data\":" + data + "}";
			}
		} catch (Exception e) {
			return "{\"success\":false}";
		}
	}

	/**
	 * @Title: verify
	 * @author liuSir
	 * @Description: 验证
	 * @param sealContent
	 *            印章
	 * @param clientData
	 *            验证的数据
	 * @return
	 * @throws Exception
	 */
	private boolean verify(byte[] sealContent, String clientData) throws Exception {

		String sealCon = new String(sealContent, "UTF-8");
		StandardDataObject sdo = new StandardDataObject();
		sdo.setClientDataProvider(new StaticClientDataProvider(clientData, "ClientData"));
		sdo.setXML(sealCon);
		try {
			// 验证
			sdo.verify();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * @Title: getWriteFile
	 * @author liuSir
	 * @Description: 根据文件名称获取
	 * @param signatureId
	 * @return
	 * @throws Exception
	 */
	private byte[] getWriteFile(String signatureId, String type) throws Exception {
		FileInputStream fis = null;
		String localPath = Factory.getSysPara().getProperty("eseal_sig_pro_folder");
		if (type.equals("sig")) {
			localPath += File.separator + signatureId + ".sig";
		} else {
			localPath += File.separator + signatureId + ".pro";
		}
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		byte[] buff = new byte[1024];
		byte[] bytes = null;
		try {
			fis = new FileInputStream(localPath);
			int rc = 0;
			while ((rc = fis.read(buff)) > 0) {
				swapStream.write(buff, 0, rc);
			}
			bytes = swapStream.toByteArray();
			swapStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				fis.close();
			}
			if (swapStream != null) {
				swapStream.close();
			}
		}
		return bytes;
	}

	/**
	 * 校验证书密码
	 * 
	 * @param keyData证书的byte数组
	 * @param password密码
	 * @return 正确返回true 否则返回false
	 * @throws Exception
	 * @author liu_zhiying
	 * @date 2014-4-28
	 */
	private boolean checkPsd(byte[] keyData, String password) throws Exception {
		InputStream certStream = new ByteArrayInputStream(keyData);
		KeyStore ks;

		char[] nPassword = null;
		if ((password == null) || password.trim().equals("")) {
			// throw new Exception("密码不能为空!");
			return false;
		} else {
			nPassword = password.toCharArray();
		}

		try {
			ks = KeyStore.getInstance("PKCS12");
		} catch (KeyStoreException e1) {
			throw new Exception("获取KeyStore实例错误 " + e1.getMessage());
		}

		try {
			ks.load(certStream, nPassword);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	/**
	 * 盖章方法
	 * 
	 * @desc
	 * @param sealContent
	 *            印章
	 * @param keyData
	 *            对应的证书
	 * @param passWord
	 *            盖章密码
	 * @param clientData
	 *            印章保护的数据
	 * @return StandardDataObject 标准数据对象
	 * @throws Exception
	 * @author liu_zhiying
	 * @date 2014-4-25
	 */
	private StandardDataObject signSingleSeal(byte[] sealContent, byte[] keyData, String passWord, String clientData)
	        throws Exception {

		// create sdo
		StandardDataObject sdo = new StandardDataObject();
		try {
			sdo.getSealInfo().setXML(new String(sealContent, "UTF-8"));

			// get key from its data
			SecureKey keyObj = new SecureKey(new ByteArrayInputStream(keyData), passWord);
			Key sealKey = keyObj.getKey();

			// sign
			sdo.setClientDataProvider(new StaticClientDataProvider(clientData, "ClientData"));// 设置印章保护内容
			sdo.getSignSealInfo().setOperationDescription("百润百成电子签章");// 设置签章描述
			sdo.setSignType(StandardDataObject.SIGN_TYPE_SEAL);// 设置签章类型
			sdo.sign(sealKey);// 盖章

			return sdo;
		} catch (Exception e) {
			throw new Exception("盖章出错 " + e.getMessage());
		}
	}

	/**
	 * 将盖章结果解析为byte数组(用于保存印章)
	 * 
	 * @desc
	 * @return StandardDataObject 标准印章数据对象
	 * @return byte[]
	 * @throws Exception
	 * @author liu_zhiying
	 * @date 2014-4-25
	 */
	private byte[] parseSignData(StandardDataObject sdo) throws Exception {
		return sdo.getXML().getBytes("UTF-8");
	}

	/**
	 * 从盖章结果中解析出印章图片数据
	 * 
	 * @desc
	 * @param StandardDataObject
	 *            标准印章数据对象
	 * @return @return byte[]
	 * @author liu_zhiying
	 * @date 2014-4-25
	 */
	private byte[] parsePictureData(StandardDataObject sdo) throws Exception {

		PictureObject po = sdo.getSealInfo().getPictureObject();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		po.toPNG(bos);
		return bos.toByteArray();
	}
}
