package util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.khnt.utils.StringUtil;

//import com.artofsolving.jodconverter.DocumentConverter;
//import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
//import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
//import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
//import com.jacob.activeX.ActiveXComponent;
//import com.jacob.com.Dispatch;
//import com.jacob.com.DispatchEvents;
//import com.jacob.com.Variant;

/**
 * 对文件及文件夹操作的工具类
 * 
 * @author: ttaomemg@163.com Date: 2007-12-15 11:27:52
 * @version: 1.0
 */
public class FileUtil {

	static Logger log = Logger.getLogger(FileUtil.class);

	public FileUtil() {
	}

	/**
	 * 在指定目录下生成随机文件名
	 * 
	 * @param originalName
	 *            原始文件名
	 * @param realPath
	 *            上传目录
	 * @return
	 * @throws IOException
	 */
	public static String createRandomFile(String originalName, String realPath) throws IOException {
		// 获取原始文件的后缀
		String suffix = getSuffix(originalName);
		while (true) {
			// 文件名 = 当前时间+随机数
			String fileName = System.currentTimeMillis() + "" + new Double(899999 * Math.random() + 100000).intValue()
					+ suffix;
			File file = new File(realPath + "/" + fileName);
			if (file.exists() && file.isFile()) {// 如果文件已存在则重新生成文件名
				continue;
			}
			if (!file.createNewFile()) {
				continue;
			}
			return fileName;
		}
	}

	/**
	 * 文件拷贝
	 * 
	 * @param src
	 * @param des
	 * @return
	 */
	public static boolean copyFile(File src, File des) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			// 取得輸入输出流
			fis = new FileInputStream(src);
			fos = new FileOutputStream(des);
			byte[] bt = new byte[1024];
			int readNum = 0;
			while ((readNum = fis.read(bt)) != -1) {
				fos.write(bt, 0, readNum);
			}
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			try {
				if (fis != null)
					fis.close();
				if (fos != null)
					fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 文件拷贝
	 * 
	 * @param src
	 * @param des
	 * @return
	 */
	public static boolean copyFile(String src, String des) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			log.debug("copy文件：" + des);
			// 取得輸入输出流
			fis = new FileInputStream(src);
			fos = new FileOutputStream(des);
			byte[] bt = new byte[1024];
			int readNum = 0;
			while ((readNum = fis.read(bt)) != -1) {
				fos.write(bt, 0, readNum);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (fis != null)
					fis.close();
				if (fos != null)
					fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean moveFile(String src, String des) {
		return moveFile(new File(src), new File(des));
	}

	public static boolean moveFile(File src, File des) {
		if (copyFile(src, des)) {
			src.delete();
			return true;
		} else
			return false;
	}

	/**
	 * 目录拷贝
	 * 
	 * @param srcDirectoryPath
	 * @param desDirectoryPath
	 * @return
	 */
	public static boolean copyDirectory(String srcDirectoryPath, String desDirectoryPath) {
		try {
			File fo = new File(desDirectoryPath);
			if (!fo.exists()) {
				fo.mkdir();
			}
			File F = new File(srcDirectoryPath);
			File[] allFile = F.listFiles();
			int totalNum = allFile.length;
			String srcName = "";
			String desName = "";
			int currentFile = 0;
			// 一个一个的拷贝文件
			for (currentFile = 0; currentFile < totalNum; currentFile++) {
				if (!allFile[currentFile].isDirectory()) {
					srcName = allFile[currentFile].toString();
					desName = desDirectoryPath + "/" + allFile[currentFile].getName();
					copyFile(srcName, desName);
				} else {
					if (copyDirectory(allFile[currentFile].getPath(),
							desDirectoryPath + "/" + allFile[currentFile].getName())) {
					} else {
						System.out.println("SubDirectory Copy Error!");
					}
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 文件删除
	 * 
	 * @param filePath
	 * @return
	 */
	public static void delAllFile(String filePath) {
		delAllFile(new File(filePath));
	}

	/**
	 * 文件删除
	 * 
	 * @param file
	 * @return
	 */
	public static void delAllFile(File file) {
		/**
		 * 指定删除目录路径构造一个文件对象
		 */
		File[] fileList = file.listFiles();
		/**
		 * 初始化子目录路径
		 */
		String dirPath = null;

		if (fileList != null)
			for (int i = 0; i < fileList.length; i++) {
				/**
				 * 如果是文件就将其删除
				 */
				if (fileList[i].isFile())
					fileList[i].delete();
				/**
				 * 如果是目录,那么将些目录下所有文件删除后再将其目录删除,
				 */
				if (fileList[i].isDirectory()) {

					dirPath = fileList[i].getPath();
					// 递归删除指定目录下所有文件

					delAllFile(dirPath);
				}
			}
		/**
		 * 删除给定根目录
		 */
		file.delete();
	}

	/**
	 * 公用的写出文件的静态方法。
	 * 
	 * @param path
	 * @param source
	 * @throws Exception
	 */
	public static void writeFile(String path, InputStream source) throws Exception {
		try {
			File f = new File(path);
			BufferedOutputStream bufferOut = new BufferedOutputStream(new FileOutputStream(f));
			byte[] b = new byte[10240];
			int j;
			while ((j = source.read(b)) > 0) {
				bufferOut.write(b, 0, j);
			}
			source.close();
			bufferOut.close();
		} catch (FileNotFoundException e) {
			log.error("写出文件的路径不正确:" + path);
			throw e;
		} catch (IOException e) {
			log.error("写出文件时发生错误:" + path);
			throw e;
		}
	}

	/**
	 * 公用的写出文件的静态方法。
	 * 
	 * @param path
	 * @param data
	 * @throws Exception
	 */
	public static void writeFile(String path, byte[] data) throws Exception {
		try {
			File f = new File(path);
			BufferedOutputStream bufferOut = new BufferedOutputStream(new FileOutputStream(f));
			bufferOut.write(data);
			bufferOut.flush();
			bufferOut.close();
		} catch (FileNotFoundException e) {
			log.error("写出文件的路径不正确:" + path);
			throw e;
		} catch (IOException e) {
			log.error("写出文件时发生错误:" + path);
			throw e;
		}
	}

	/**
	 * 获取文件后缀名
	 * 
	 * @param fileName
	 *            文件名
	 * @return String 后缀名
	 */
	public static String getSuffix(String fileName) {
		int index = fileName.lastIndexOf('.');
		if (index == fileName.length() - 1) {
			index = -1;
		}
		String suffix = index == -1 ? "" : ("." + fileName.substring(index + 1));
		return suffix;
	}

	/**
	 * 下载文件。目标为内存二进制数据。
	 * 
	 * @param response
	 * @param data
	 *            要下载的文件二进制数据
	 * @param fileName
	 *            文件名
	 * @param contentType
	 *            文件类型
	 * @throws Exception
	 */
	public static void download(HttpServletResponse response, byte[] data, String fileName, String contentType)
			throws Exception {
		InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(data));
		download(response, inputStream, fileName, contentType);
	}

	/**
	 * 下载文件。目标为磁盘文件。
	 * 
	 * @param response
	 * @param file
	 *            要下载的文件
	 * @param fileName
	 *            文件名
	 * @param contentType
	 *            文件类型
	 * @throws Exception
	 */
	public static void download(HttpServletResponse response, File file, String fileName, String contentType)
			throws Exception {
		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
		download(response, inputStream, fileName == null ? file.getName() : fileName, contentType);
	}
	
	/**
	 * 下载文件。目标为磁盘文件。
	 * 
	 * @param response
	 * @param file
	 *            要下载的文件
	 * @param fileName
	 *            文件名
	 * @param contentType
	 *            文件类型
	 * @param sufName
	 *            文件后缀名（包含.）
	 * @throws Exception
	 */
	public static void download(HttpServletResponse response, File file, String fileName, String contentType, String sufName)
			throws Exception {
		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
		download(response, inputStream, fileName == null ? file.getName() : fileName, contentType, sufName);
	}

	/**
	 * 下载文件。目标为一个输入流。
	 * 
	 * @param response
	 * @param inputStream
	 *            文件输入流
	 * @param fileName
	 *            文件名
	 * @param contentType
	 *            文件类型
	 * @throws Exception
	 */
	public static void download(HttpServletResponse response, InputStream inputStream, String fileName,
			String contentType) throws Exception {
		if (StringUtil.isNotEmpty(contentType))
		response.setContentType(contentType);
	else
			response.setContentType("application/octet-stream");

		fileName = StringUtil.isEmpty(fileName) ? "unknow_file" : fileName;
		response.setHeader("Content-Disposition",
				"attachment; filename=" + new String(fileName.getBytes("GBK"), "ISO-8859-1"));
	response.setContentLength(inputStream.available());
		InputStream bis = new BufferedInputStream(inputStream);
		byte[] b = new byte[1024];// 相当于我们的缓存
		int j;
		OutputStream out = response.getOutputStream();
	while ((j = bis.read(b)) > 0) {
			// 将缓存中的数据写到客户端的内存
			out.write(b, 0, j);
		}
		bis.close();
		out.flush();
		out.close();
	}
	
	/**
	 * 下载文件。目标为一个输入流。
	 * 
	 * @param response
	 * @param inputStream
	 *            文件输入流
	 * @param fileName
	 *            文件名
	 * @param contentType
	 *            文件类型
	 * @param sufName
	 *            文件后缀名（包含.）
	 * @throws Exception
	 */
	public static void download(HttpServletResponse response, InputStream inputStream, String fileName,
			String contentType, String sufName) throws Exception {
		if (StringUtil.isNotEmpty(contentType))
		response.setContentType(contentType);
	else
			response.setContentType("application/octet-stream");

		fileName = StringUtil.isEmpty(fileName) ? "unknow_file" : fileName;
		response.setHeader("Content-Disposition",
				"attachment; filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1")+sufName);
	response.setContentLength(inputStream.available());
		InputStream bis = new BufferedInputStream(inputStream);
		byte[] b = new byte[1024];// 相当于我们的缓存
		int j;
		OutputStream out = response.getOutputStream();
	while ((j = bis.read(b)) > 0) {
			// 将缓存中的数据写到客户端的内存
			out.write(b, 0, j);
		}
		bis.close();
		out.flush();
		out.close();
	}

	/**
	 * 下载文件。参数filePath为下载的文件路径
	 * 
	 * @param response
	 * @param filePath
	 *            下载路径
	 * @param fileName
	 *            文件原名
	 * @throws Exception
	 */
	public static void download(HttpServletResponse response, String filePath, String fileName, String type)
			throws Exception {
		File file = new File(filePath);
	if (!file.exists())
			throw new IOException("该文件不存在，或已被删除！");
		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
		download(response, inputStream,
				StringUtil.isNotEmpty(fileName) ? fileName : filePath.substring(filePath.lastIndexOf("/") + 1), type);
	}

	/**
	 * 下载文件。参数filePath为下载的文件路径
	 * 
	 * @param response
	 * @param filePath
	 *            下载路径
	 * @param fileName
	 *            文件原名
	 * @throws Exception
	 */
	public static void download(HttpServletRequest request, HttpServletResponse response, String filePath,
			String fileName, String type) throws Exception {
		File file = new File(filePath);
		if (!file.exists())
			throw new IOException("该文件不存在，或已被删除！");
		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
		download(response, inputStream,
				StringUtil.isNotEmpty(fileName) ? fileName : filePath.substring(filePath.lastIndexOf("/") + 1), type);
	}

//	public static String convertWordToPdf(String filePath) {
//		File pdf = new File(filePath.replace(filePath.substring(filePath.lastIndexOf(".")), ".pdf"));
//		ActiveXComponent app = null;
//		try {
//			app = new ActiveXComponent("Word.Application"); // 启动word
//			// 设置APP不可见
//			app.setProperty("Visible", new Variant(false));
//
//			// 打开word文件
//			Dispatch docs = app.getProperty("Documents").toDispatch();
//			Dispatch doc = Dispatch.invoke(docs, "Open", Dispatch.Method,
//					new Object[] { filePath, new Variant(false), new Variant(true) }, new int[1]).toDispatch();
//
//			// 作为PDF格式保存到临时文件 变量值为17表示存为pdf
//			Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[] { pdf.getAbsolutePath(), new Variant(17) },
//					new int[1]);
//
//			// 关闭文件
//			Variant f = new Variant(false);
//			Dispatch.call(doc, "Close", f);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			// 组件退出
//			if (app != null)
//				app.invoke("Quit", new Variant[] {});
//		}
//
//		return pdf.getAbsolutePath();
//	}

//	public static String wpsConvertWordToPdf(String filePath) {
//		File pdf = new File(filePath.replace(filePath.substring(filePath.lastIndexOf(".")), ".pdf"));
//		File word = new File(filePath);
//		ActiveXComponent wps = null;
//		try {
//			wps = new ActiveXComponent("wps.application");
//			ActiveXComponent doc = wps.invokeGetComponent("Documents").invokeGetComponent("Open",
//					new Variant(word.getAbsolutePath()));
//			doc.invoke("ExportPdf", new Variant(pdf.getAbsolutePath()));
//			doc.invoke("Close");
//			doc.safeRelease();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (wps != null) {
//				wps.invoke("Terminate");
//				wps.safeRelease();
//			}
//		}
//		return pdf.getAbsolutePath();
//	}

//	public static String pdfCreatorConvertWordToPdf(String filePath) {
//		// convert("d:\\plstemp.doc","d:\\plstemp.pdf");
//		File pdf = new File(filePath.replace(filePath.substring(filePath.lastIndexOf(".")), ".pdf"));
//		File word = new File(filePath);
//		// File pdf = new File("d:\\plstemp.pdf");
//		// File word = new File("d:\\plstemp.doc");
//		Variant defaultPrinter = null;
//		DispatchEvents dispatcher = null;
//		ActiveXComponent pdfCreator = null;
//		try {
//			pdfCreator = new ActiveXComponent("PDFCreator.clsPDFCreator");
//			dispatcher = new DispatchEvents(pdfCreator, pdfCreator);
//			pdfCreator.setProperty("cVisible", new Variant(false));
//			pdfCreator.invoke("cStart", new Variant[] { new Variant("/NoProcessingAtStartup"), new Variant(true) });
//			setCOption(pdfCreator, "UseAutosave", 1);
//			setCOption(pdfCreator, "UseAutosaveDirectory", 1);
//			setCOption(pdfCreator, "AutosaveFormat", 0);
//			defaultPrinter = pdfCreator.getProperty("cDefaultPrinter");
//			pdfCreator.setProperty("cDefaultprinter", "PDFCreator");
//			pdfCreator.invoke("cClearCache");
//			pdfCreator.setProperty("cPrinterStop", false);
//
//			setCOption(pdfCreator, "AutosaveDirectory", pdf.getParentFile().getAbsolutePath());
//			setCOption(pdfCreator, "AutosaveFilename", pdf.getName());
//			pdfCreator.invoke("cPrintfile", word.getAbsolutePath());
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			// 组件退出
//			if (pdfCreator != null) {
//				pdfCreator.setProperty("cDefaultprinter", defaultPrinter);
//				pdfCreator.invoke("cClearCache");
//				pdfCreator.setProperty("cPrinterStop", true);
//				pdfCreator.invoke("cClose");
//				pdfCreator.safeRelease();
//				pdfCreator = null;
//			}
//			if (dispatcher != null) {
//				dispatcher.safeRelease();
//				dispatcher = null;
//			}
//		}
//
//		return pdf.getAbsolutePath();
//	}

//	public static String openOfficeConvertWordToPdf(String filePath) {
//		File pdf = new File(filePath.replace(filePath.substring(filePath.lastIndexOf(".")), ".pdf"));
//		File word = new File(filePath);
//		try {
//			// 指定openoffice端口
//			OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
//			connection.connect();
//			// convert
//			DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
//			converter.convert(word, pdf);
//			// close the connection
//			connection.disconnect();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return pdf.getAbsolutePath();
//	}

//	private static void setCOption(ActiveXComponent pdfCreator, String property, Object value) {
//		Dispatch.invoke(pdfCreator, "cOption", Dispatch.Put, new Object[] { property, value }, new int[2]);
//	}

//	public static String convertWordToPdf(File file) {
//		String fname = file.getAbsolutePath();
//		return convertWordToPdf(fname);
//	}

	/**
	 * 
	 * @param binaryData
	 * @param fileName
	 * @param filePath
	 * @param convertType
	 *            指定转换程序:word,wps,pdfcreator,openoffice
	 * @return
	 * @throws IOException
	 */
//	public static String convertWordToPdf(byte[] binaryData, String fileName, String filePath, String convertType)
//			throws IOException {
//		if (StringUtil.isEmpty(filePath))
//			filePath = "C:/Windows/temp";
//		File temp = new File("C:/Windows/temp");
//		if (!temp.exists())
//			temp.mkdir();
//		filePath = temp.getAbsolutePath();
//		fileName = filePath + "/" + fileName;
//
//		File file = new File(fileName);
//		file.createNewFile();
//		BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(file));
//		fos.write(binaryData);
//		fos.flush();
//		fos.close();
//
//		String path = "";
//		if (convertType.equals("word")) {
//			path = convertWordToPdf(fileName);
//		} else if (convertType.equals("wps")) {
//			path = wpsConvertWordToPdf(fileName);
//		} else if (convertType.equals("pdfcreator")) {
//			path = pdfCreatorConvertWordToPdf(fileName);
//		} else if (convertType.equals("openoffice")) {
//			path = pdfCreatorConvertWordToPdf(fileName);
//		}
//
//		file.delete();
//		return path;
//	}

	/**
	 * 创建文件
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public File newFile(String path) throws Exception {
		File file = new File(path);
		File parent = file.getParentFile();
		if (parent != null && !parent.exists()) {
			parent.mkdirs();
		}
		if (!file.exists()) {
			file.createNewFile();
		}
		return file;
	}

	/**
	 * 读取文件内容成字符串
	 * 
	 * @param path
	 * @return
	 */
	public static String getFileStr(String path) {
		try {
			InputStreamReader isr = new InputStreamReader(new FileInputStream(path), "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			StringBuffer bd = new StringBuffer();
			while (true) {
				String str = br.readLine();
				if (str == null) {
					break;
				}
				bd.append(str);
			}
			br.close();
			return bd.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 将文本文件内容读出为java string
	 * 
	 * @param fpath
	 * @return
	 * @throws Exception
	 */
	public static String readFileAsText(String fpath) throws Exception {
		File file = new File(fpath);
		if (!file.exists())
			throw new Exception("指定的文件不存在");
		if (!file.isFile())
			throw new Exception("指定的路径不是具体文件而是一个文件夹");

		BufferedReader breader = new BufferedReader(new FileReader(file));
		StringBuilder strbuilder = new StringBuilder();
		String linestr = null;
		while ((linestr = breader.readLine()) != null) {
			strbuilder.append(linestr);
		}
		breader.close();
		return strbuilder.toString();
	}
	
    
    /**
     * 通过递归得到某一路径下所有的文件的全路径,分装到list里面
     * 
     * @param filePath
     * @param filelist
     * @param name 
     * @return
     */
    public static List<File> getFiles(String filePath, List<File> filelist, String name) {

        File root = new File(filePath);
        if (!root.exists()) {
            log.info(filePath + " not exist!");
        } else {
            File[] files = root.listFiles();
            Arrays.sort(files, new CompratorByLastModified());  
            for (File file : files) {
                if (file.isDirectory()) {
                	if(file.getName().indexOf(name)!=-1){
                		 getFiles(file.getAbsolutePath(), filelist,name);
                         filelist.add(file);
                	}
                }
                /*else {
                    //logger.info("目录:" + filePath + "文件全路径:" + file.getAbsolutePath());
                    filelist.add(file);
                }*/
            }
        }
        return filelist;
    }
     
    /**
     * 得到某一路径下所有的文件和文件夹,分装到list里面
     * 
     * @param filePath
     * @param filelist
     * @return
     */
    public static List<File> getFilesChild(String filePath, List<File> filelist) {

        File root = new File(filePath);
        if (!root.exists()) {
            log.info(filePath + " not exist!");
        } else {
            File[] files = root.listFiles();
            Arrays.sort(files, new CompratorByLastModified());  
            for (File file : files) {
                   filelist.add(file);
            }
        }
        return filelist;
    }
    //根据文件修改时间进行比较的内部类
    public  static class CompratorByLastModified implements Comparator<File> {  
        
        public int compare(File f1, File f2) { 
        /*	System.out.println(f1.getName()+"----------------"+f1.length()/1024+"KB----hashCode:"
        +f1.hashCode()+"-----最后修改日期："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((new Date(f1.lastModified()))));*/
            long diff = f1.lastModified() - f2.lastModified();  
            if (diff > 0) {  
                   return 1;  
            } else if (diff == 0) {  
                   return 0;  
            } else {  
                  return -1;  
            }  
        }  
    }
    
    
    /**
     * 获取指定目录下特定文件后缀名的文件列表(不包括子文件夹)
     * @param dirPath  目录路径
     * @param suffix   文件后缀
     * @param fileName 
     * @return
     */
    public static ArrayList<File> getDirFiles(String dirPath,final String suffix, final String fileName) {
        File path = new File(dirPath);
        File[] fileArr = path.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                String lowerName = name.toLowerCase();
                String file1 = fileName.toLowerCase();
                String lowerSuffix = suffix.toLowerCase();
                if (lowerName.endsWith(lowerSuffix)&&lowerName.startsWith(file1)) {
                    return true;
                }
                return false;
            }

        });
        ArrayList<File> files = new ArrayList<File>();

        for (File f : fileArr) {
            if (f.isFile()) {
                files.add(f);
            }
        }
        return files;
    }


    /**
     * 合并文件
     * 
     * @param dirPath 上传文件所在的目录名称
     * @param partFileSuffix 拆分文件后缀名
     * @param partFileSize拆分文件的字节数大小
     * @param mergeFileName 合并后的文件名
     * @param fileName 
     * @throws IOException
     */
    public void mergePartFiles(String dirPath, String partFileSuffix,
            int partFileSize, String mergeFileName, String fileName) throws IOException {
        ArrayList<File> partFiles = FileUtil.getDirFiles(dirPath, partFileSuffix,fileName);
        Collections.sort(partFiles, new FileComparator());

        RandomAccessFile randomAccessFile = new RandomAccessFile(mergeFileName,"rw");
        randomAccessFile.setLength(partFileSize * (partFiles.size() - 1)
                + partFiles.get(partFiles.size() - 1).length());
        randomAccessFile.close();

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                partFiles.size(), partFiles.size() * 3, 1, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(partFiles.size() * 2));

        for (int i = 0; i < partFiles.size(); i++) {
            threadPool.execute(new MergeRunnable(i * partFileSize,mergeFileName, partFiles.get(i)));
        }
    }
    
    /**删除临时文件
     * @param dirPath
     * @param partFileSuffix
     * @param fileName 
     */
    public void deleteSplitFiles(String dirPath,String partFileSuffix, String fileName){
        ArrayList<File> partFiles = FileUtil.getDirFiles(dirPath, partFileSuffix,fileName);
        for (int i = 0; i < partFiles.size(); i++) {
            partFiles.get(i).delete();
        }
        
    }

    /**
     * 根据文件名，比较文件 ，根据文件名排序
     */
    private class FileComparator implements Comparator<File> {
        public int compare(File o1, File o2) {
            return o1.getName().compareToIgnoreCase(o2.getName());
        }
    }

    /**
     * 合并处理Runnable
     * 
     */
    private class MergeRunnable implements Runnable {
        long startPos;
        String mergeFileName;
        File partFile;

        public MergeRunnable(long startPos, String mergeFileName, File partFile) {
            this.startPos = startPos;
            this.mergeFileName = mergeFileName;
            this.partFile = partFile;
        }

        public void run() {
            RandomAccessFile rFile;
            try {
                rFile = new RandomAccessFile(mergeFileName, "rw");
                rFile.seek(startPos);
                FileInputStream fs = new FileInputStream(partFile);
                byte[] b = new byte[fs.available()];
                fs.read(b);
                fs.close();
                rFile.write(b);
                rFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 判断文件是否有读权限
     * @param file
     * @return
     */
    public static Boolean canRead(File file) {
        if (file.isDirectory()) {
            try {
                File[] listFiles = file.listFiles();
                if (listFiles == null) { // 返回null表示无法读取或访问，如果为空目录返回的是一个空数组
                    return false;
                } else {
                    return true;
                }
            } catch (Exception e) {
                return false;
            }
        } else if (!file.exists()) { // 文件不存在
            return false;
        }
        return checkRead(file);
    }
    
    /**
     * 检测文件是否有读权限
     * @param file
     * @return
     */
    private static boolean checkRead(File file) {
        FileReader fd = null;
        try {
            fd = new FileReader(file);
            while ((fd.read()) != -1) {
                break;
            }
            return true;
        } catch (IOException e) {
            return false;
        } finally {
            //ReflectUtil.close(fd);
        	try {
				fd.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
    }
    
    /**
     * 判断文件是否有写权限
     * @param file
     * @return
     */
    public static Boolean canWrite(File file) {
        if (file.isDirectory()) {
            try {
                file = new File(file, "canWriteTestDeleteOnExit.temp");
                if (file.exists()) {
                    boolean checkWrite = checkWrite(file);
                    if (!deleteFile(file)) {
                        file.deleteOnExit();
                    }
                    return checkWrite;
                } else if (file.createNewFile()) {
                    if (!deleteFile(file)) {
                        file.deleteOnExit();
                    }
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        }
        return checkWrite(file);
    }
    
    /**
     * 检测文件是否有写权限
     * @param file
     * @return
     */
    private static boolean checkWrite(File file) {
        FileWriter fw = null;
        boolean delete = !file.exists();
        boolean result = false;
        try {
            fw = new FileWriter(file, true);
            fw.write("");
            fw.flush();
            result = true;
            return result;
        } catch (IOException e) {
            return false;
        } finally {
            //ReflectUtil.close(fw);
        	try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
            if (delete && result) {
                deleteFile(file);
            }
        }
    }

    
    /**
     * 删除文件，如果要删除的对象是文件夹，先删除所有子文件(夹)，再删除该文件
     * @param file 要删除的文件对象
     * @return 删除是否成功
     */
    public static boolean deleteFile(File file) {
        return deleteFile(file, true);
    }
 
    /**
     * 删除文件，如果要删除的对象是文件夹，则根据delDir判断是否同时删除文件夹
     * @param file 要删除的文件对象
     * @param delDir 是否删除目录
     * @return 删除是否成功
     */
    public static boolean deleteFile(File file, boolean delDir) {
        if (!file.exists()) { // 文件不存在
            return true;
        }
        if (file.isFile()) {
            return file.delete();
        } else {
            boolean result = true;
            File[] children = file.listFiles();
            for (int i = 0; i < children.length; i++) { // 删除所有子文件和子文件夹
                result = deleteFile(children[i], delDir);// 递归删除文件
                if (!result) {
                    return false;
                }
            }
            if (delDir) {
                result = file.delete(); // 删除当前文件夹
            }
            return result;
        }
    }


}
