package com.lsts;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.khnt.base.Factory;
import com.khnt.base.SysParaInf;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.common.dao.AttachmentsDao;
import com.lsts.log.service.SysLogService;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 图片工具类
 * 
 * @ClassName ImageTool
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-03-07 上午10:25:00
 */
@Service("imageTool")
public class ImageTool {
	
	@Autowired
	private SysLogService logService;
	
	/**
	 * 获取人员电子签名（图片）并转换成字节数组
	 * 
	 * @param op_emp_id --
	 *            人员ID
	 * @return
	 * @throws Exception
	 */
	public synchronized byte[] getEmployeeImg(String op_emp_id){
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		byte[] op_user_img = null;
		try {
			if (StringUtil.isNotEmpty(op_emp_id)) {
				AttachmentsDao attachmentsDao = new AttachmentsDao();
				SysParaInf sp = Factory.getSysPara();
				boolean relative = AttachmentManager.SAVE_PATH_R.equals(sp
						.getProperty("attachmentPathType"));
				String uploadPath = sp.getProperty("attachmentPath");
				String filePath = "";
				String[] op_emps = op_emp_id.split(",");
				for (int i = 0; i < op_emps.length; i++) {
					if (StringUtil.isNotEmpty(op_emps[i])) {
						String file_path = attachmentsDao.queryByBusinessId(op_emps[i]);
						if (StringUtil.isNotEmpty(file_path)) {
							String[] fPath = file_path.split(",");
							for (int j = 0; j < fPath.length; j++) {
								if (filePath.length() > 0) {
									filePath += ",";
								}
								filePath += (relative ? (Factory.getWebRoot() + "/" + uploadPath + "/") : "")
										+ fPath[j];
							}
						}
					}
				}
				op_user_img = setImageToByteArray(op_emp_id,filePath);
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				logService.setLogs(op_emp_id, "获取人员电子签名", e.toString()+e.getCause().getMessage(), user.getId(), user
						.getName(), new Date(), "");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return op_user_img;
	}
	
	/**
	 * 获取人员电子签名（图片）并转换成字节数组
	 * 
	 * @param info_id --
	 *            业务ID
	 * @return
	 * @throws Exception
	 */
	public synchronized byte[] getRecordImg(String info_id){
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		byte[] op_user_img = null;
		try {
			if (StringUtil.isNotEmpty(info_id)) {
				AttachmentsDao attachmentsDao = new AttachmentsDao();
				SysParaInf sp = Factory.getSysPara();
				boolean relative = AttachmentManager.SAVE_PATH_R.equals(sp
						.getProperty("attachmentPathType"));
				String uploadPath = sp.getProperty("mo_sign_compress_attachmentPath");
				String filePath = "";
				String[] op_emps = info_id.split(",");
				for (int i = 0; i < op_emps.length; i++) {
					if (StringUtil.isNotEmpty(op_emps[i])) {
						String file_path = attachmentsDao
								.querySignPicByBusinessId(op_emps[i]);
						if (StringUtil.isNotEmpty(file_path)) {
							String[] fPath = file_path.split(",");
							for (int j = 0; j < fPath.length; j++) {
								if (filePath.length() > 0) {
									filePath += ",";
								}
								filePath += (relative ? (Factory.getWebRoot() + "/"
										+ uploadPath + "/") : "")
										+ fPath[j];
							}
						}
					}
				}
				op_user_img = setImageToByteArray(info_id,filePath);
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				logService.setLogs(info_id, "获取原始记录图片", e.toString()+e.getCause().getMessage(), user.getId(), user
						.getName(), new Date(), "");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return op_user_img;
	}

	/**
	 * 将本地磁盘中的图片转换成字节数组
	 * @param op_emp_id --
	 *            业务id
	 * @param fileName --
	 *            文件完整路径
	 * @return
	 * @throws Exception
	 */
	public byte[] setImageToByteArray(String op_emp_id, String fileName) {
		if (StringUtil.isNotEmpty(fileName)) {
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			String[] fileNames = fileName.split(",");
			try {
				File file = new File(fileNames[0]);
				if (file.isFile()) {
					BufferedImage image = ImageIO.read(file);
					// 创建一个不带透明色（TYPE_INT_RGB）的BufferedImage对象
					BufferedImage imageArray = new BufferedImage(image
							.getWidth()
							* fileNames.length, image.getHeight(),
							BufferedImage.TYPE_INT_RGB); // TYPE_INT_ARGB带透明色
					imageArray.getGraphics().drawImage(image, 0, 0,
							image.getWidth(), image.getHeight(), null);
					BufferedImage img = null;
					for (int i = 1; i < fileNames.length; i++) {
						File file1 = new File(fileNames[i]);
						img = ImageIO.read(file1);
						imageArray.getGraphics().drawImage(img,
								image.getWidth() * i, 0, image.getWidth(),
								image.getHeight(), null);
					}
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
					encoder.encode(imageArray);
					byte[] val = bos.toByteArray();
					bos.close();
					return val;
				}
			} catch (IOException e) {
				e.printStackTrace();
				try {
					logService.setLogs(op_emp_id, "获取人员图片", e.toString()+e.getCause().getMessage(), user.getId(), user
							.getName(), new Date(), "");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		return null;
	}
	/**
	 * 将二维码写入图片（查询设备基本信息及报告基本信息，含收费金额）
	 * @param report_sn --
	 *            报告书编号
	 * @param fileName --
	 *            文件完整路径
	 * @return
	 * @throws Exception 
	 */
	 @SuppressWarnings("unchecked")
	public byte[] setCodeToByteArray(String report_sn,int h,int w) throws Exception {
		
		if (StringUtil.isNotEmpty(report_sn)) {
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			//String[] fileNames = fileName.split(",");
			StringBuffer params = new StringBuffer();  
			
	        
	        params.append("http://m.scsei.org.cn/?action=q&s="+report_sn+"");  

	        int width = w; 
	        int height = h; 

	        
	        //二维码的图片格式 
	        String format = "gif"; 
	        Hashtable hints = new Hashtable(); 
	        //内容所使用编码 
	        hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); 
	        BitMatrix bitMatrix = new MultiFormatWriter().encode(params.toString(), 
	                BarcodeFormat.QR_CODE, width, height, hints); 
	        //生成二维码 流
	        ByteArrayOutputStream   out = new ByteArrayOutputStream ();
	        //File outputFile = new File("d:"+File.separator+"new.gif"); 
	        MatrixToImageWriter.writeToStream(bitMatrix, format, out); 
	        
	        //输出流转换输入流
	        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray()) ;

	            

			
			
			try {
				
				if (in!=null) {
					BufferedImage image = ImageIO.read(in);
					// 创建一个不带透明色（TYPE_INT_RGB）的BufferedImage对象

					BufferedImage imageArray = new BufferedImage(w, h,

							BufferedImage.TYPE_INT_RGB); // TYPE_INT_ARGB带透明色
					imageArray.getGraphics().drawImage(image, 0, 0,
							image.getWidth(), image.getHeight(), null);
					BufferedImage img = null;
					
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
					encoder.encode(imageArray);
					byte[] val = bos.toByteArray();
					bos.close();
					return val;
				}
				
			} catch (IOException e) {
				e.printStackTrace();
				try {
					logService.setLogs("", "获取二维码", e.toString()+e.getCause().getMessage(), user.getId(), user
							.getName(), new Date(), "");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		return null;
	}
	
	/**
	 * 将电子印章二维码写入图片（查询报告基本信息，含盖章信息）
	 * @param report_sn --
	 *            报告书编号
	 * @param h --
	 *            高
	 * @param w --
	 *            宽
	 * @return
	 * @throws Exception 
	 * @author GaoYa
	 * @date 2016-09-27 下午05:56:00
	 */
	@SuppressWarnings("unchecked")
	public byte[] setCodeToDZYZByteArray(String report_sn, int w, int h) throws Exception {	
		if (StringUtil.isNotEmpty(report_sn)) {
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			//String[] fileNames = fileName.split(",");
			StringBuffer params = new StringBuffer();  

	        params.append("http://m.scsei.org.cn/?action=querydzyz&searchvalue="+report_sn);  

	        int width = w; 
	        int height = h; 

	        //二维码的图片格式 
	        String format = "gif"; 
	        Hashtable hints = new Hashtable(); 
	        //内容所使用编码 
	        hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); 
	        BitMatrix bitMatrix = new MultiFormatWriter().encode(params.toString(), 
	                BarcodeFormat.QR_CODE, width, height, hints); 
	        //生成二维码 流
	        ByteArrayOutputStream   out = new ByteArrayOutputStream ();
	        //File outputFile = new File("d:"+File.separator+"new.gif"); 
	        MatrixToImageWriter.writeToStream(bitMatrix, format, out);   
	        //输出流转换输入流
	        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray()) ;

	        try {				
				if (in!=null) {
					BufferedImage image = ImageIO.read(in);
					// 创建一个不带透明色（TYPE_INT_RGB）的BufferedImage对象

					BufferedImage imageArray = new BufferedImage(w, h,
							BufferedImage.TYPE_INT_RGB); // TYPE_INT_ARGB带透明色
					imageArray.getGraphics().drawImage(image, 0, 0,
							image.getWidth(), image.getHeight(), null);
					BufferedImage img = null;
					
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
					encoder.encode(imageArray);
					byte[] val = bos.toByteArray();
					bos.close();
					return val;
				}
				
			} catch (IOException e) {
				e.printStackTrace();
				try {
					logService.setLogs("", "获取报告电子印章二维码", e.toString()+e.getCause().getMessage(), user.getId(), user
							.getName(), new Date(), "");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		return null;
	}
	
	/**
	 * 将罐车检验报告封面二维码写入图片（查询使用单位、车牌、报告编号、咨询电话18190875982）
	 * @param device_id --
	 *            罐车检验报告设备信息ID
	 * @param h --
	 *            高
	 * @param w --
	 *            宽
	 * @return
	 * @throws Exception 
	 * @author GaoYa
	 * @date 2016-10-27 上午10:34:00
	 */
	@SuppressWarnings("unchecked")
	public byte[] setGCCodeToByteArray(String device_id, int w, int h) throws Exception {	
		if (StringUtil.isNotEmpty(device_id)) {
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			//String[] fileNames = fileName.split(",");
			StringBuffer params = new StringBuffer();  

	        params.append("http://m.scsei.org.cn/?action=q&searchvalue="+device_id);  

	        int width = w; 
	        int height = h; 

	        //二维码的图片格式 
	        String format = "gif"; 
	        Hashtable hints = new Hashtable(); 
	        //内容所使用编码 
	        hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); 
	        BitMatrix bitMatrix = new MultiFormatWriter().encode(params.toString(), 
	                BarcodeFormat.QR_CODE, width, height, hints); 
	        //生成二维码 流
	        ByteArrayOutputStream   out = new ByteArrayOutputStream ();
	        //File outputFile = new File("d:"+File.separator+"new.gif"); 
	        MatrixToImageWriter.writeToStream(bitMatrix, format, out);   
	        //输出流转换输入流
	        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray()) ;

	        try {				
				if (in!=null) {
					BufferedImage image = ImageIO.read(in);
					// 创建一个不带透明色（TYPE_INT_RGB）的BufferedImage对象

					BufferedImage imageArray = new BufferedImage(w, h,
							BufferedImage.TYPE_INT_RGB); // TYPE_INT_ARGB带透明色
					imageArray.getGraphics().drawImage(image, 0, 0,
							image.getWidth(), image.getHeight(), null);
					BufferedImage img = null;
					
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
					encoder.encode(imageArray);
					byte[] val = bos.toByteArray();
					bos.close();
					return val;
				}
			} catch (IOException e) {
				e.printStackTrace();
				try {
					logService.setLogs("", "获取罐车检验报告封面二维码", e.toString()+e.getCause().getMessage(), user.getId(), user
							.getName(), new Date(), "");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		return null;
	}
	/**
	 * 生成使用标志二维码（此方法会生成本地文件）
	 * @param device_registration_code
	 * @param device_sort
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws WriterException
	 */
	public String showTwoDim(String device_registration_code,String device_sort,HttpServletRequest request) throws IOException, WriterException {
		StringBuffer params = new StringBuffer();  
		String imagepath=request.getSession().getServletContext().getRealPath("upload");
		//String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getSession().getServletContext().getContextPath();
		//System.out.println("============"+request.getScheme());
		//System.out.println("============"+request.getServerName());
		//System.out.println("============"+request.getServerPort());
		//System.out.println("============"+request.getSession().getServletContext().getContextPath());
		params.append("http://m.scsei.org.cn/?action=queryreport&searchvalue="+device_registration_code+"");
		//params.append(path+"/baseDeviceDocumentAction/getMobileInfo.do?id="+code);  
    //System.out.println(path+"/baseDeviceDocumentAction/getMobileInfo.do?id="+code);
    int width = 250; 
    int height = 250; 
    if(device_sort!=null&&(device_sort.substring(0,1).equals("6")||device_sort.substring(0,1).equals("9"))){
    	width = 800; 
        height = 800; 
    }
    //二维码的图片格式 
    String format = "gif"; 
    Hashtable hints = new Hashtable(); 
    //内容所使用编码 
    hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); 
    BitMatrix bitMatrix = new MultiFormatWriter().encode(params.toString(), 
            BarcodeFormat.QR_CODE, width, height, hints); 
    //System.out.println("-------------"+imagepath+File.separator+"new.gif"+"--------------");
    File file1 = new File(imagepath);
    if(!file1.mkdir()){
    	file1.mkdir();
    }
    File file = new File(imagepath+File.separator+"new.gif");
    if(!file.exists()){
    	file.createNewFile();
    }
    //生成二维码 
    OutputStream stream = new FileOutputStream(imagepath+File.separator+"new.gif"); 
    
    MatrixToImageWriter.writeToStream(bitMatrix, format, stream); 
    
    return imagepath+File.separator+"new.gif";
}
	/**
	 * 生成使用标志二维码（此方法直接以流传到页面）
	 * @param device_registration_code
	 * @param device_sort
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	 @SuppressWarnings("unchecked")
	public void showTwoDimStream(String report_sn,String device_sort,HttpServletRequest request,HttpServletResponse response) throws Exception {
		 StringBuffer params = new StringBuffer();
		 /*params.append("http://m.scsei.org.cn/?action=queryreport&searchvalue="+device_registration_code+"");*/
		 params.append("http://m.scsei.org.cn/?action=q&s="+report_sn+"");
		 int width = 250; 
		 int height = 250; 
		 if(device_sort!=null&&(device_sort.substring(0,1).equals("6")||device_sort.substring(0,1).equals("9"))){
			 width = 800; 
			 height = 800; 
		 }
		 String format = "gif"; //二维码的图片格式 
         Hashtable<EncodeHintType, Comparable> hints = new Hashtable<EncodeHintType, Comparable>(); 
         hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); //内容所使用编码
         hints.put(EncodeHintType.MARGIN, 0); //设置白边
         BitMatrix bitMatrix = new MultiFormatWriter().encode(params.toString(), BarcodeFormat.QR_CODE, width, height, hints); 
         //生成二维码 流
         ServletOutputStream out = response.getOutputStream();
		 try {
			 MatrixToImageWriter.writeToStream(bitMatrix, format, out); 
		 } catch (IOException e) {
			 e.printStackTrace();
		 }finally{
	        if(out != null){
	        	out.flush();
	        	out.close();
	        }
	    }
	}
	 
	 /**
	 * 将支付宝生成的当前预下单二维码码串写入图片（供用户扫描支付）
	 * 
	 * @param qr_code
	 *            -- 二维码码串
	 * @return
	 * @throws Exception
	 * @author GaoYa
	 * @date 2018-07-05 上午10:49:00
	 */
	@SuppressWarnings("unchecked")
	public void createAlipayQrCode(String qr_code, HttpServletResponse response) throws Exception {
		if (StringUtil.isNotEmpty(qr_code)) {
			int width = 200;
			int height = 200;

			String format = "gif"; // 二维码的图片格式
			Hashtable hints = new Hashtable();
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); // 内容所使用编码
			BitMatrix bitMatrix = new MultiFormatWriter().encode(qr_code, BarcodeFormat.QR_CODE, width, height,
					hints);
			// 生成二维码 流
			ServletOutputStream out = response.getOutputStream();
			try {
				MatrixToImageWriter.writeToStream(bitMatrix, format, out);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (out != null) {
					out.flush();
					out.close();
				}
			}
		}
	}
}
