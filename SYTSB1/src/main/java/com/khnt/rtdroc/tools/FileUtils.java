package com.khnt.rtdroc.tools;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.channels.FileChannel;
import java.util.Enumeration;
import java.util.zip.Deflater;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

import com.khnt.rtbox.template.constant.RtCharset;

/**
 * @author ZQ
 * @version 2016年3月9日 下午5:31:04 文件工具类
 */
public class FileUtils {

	/**
	 * 压缩
	 * 
	 * @param folderPath
	 * @param zipFile
	 * @param folderFlag
	 * @throws Exception
	 */
	public static void zip(String folderPath, String zipFile, boolean folderFlag) throws Exception {
		FileOutputStream fos = null;
		ZipOutputStream zos = null;
		try {
			File file = new File(zipFile);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			fos = new FileOutputStream(zipFile);
			zos = new ZipOutputStream(fos);
			writeZip(new File(folderPath), "", zos, folderFlag);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if (zos != null) {
					zos.close();
				}
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	private static void writeZip(File file, String parentPath, ZipOutputStream zos, boolean folderFlag) {
		if (file.exists()) {
			// 处理文件夹
			if (file.isDirectory()) {
				if (folderFlag) {
					parentPath += file.getName() + File.separator;
				}
				File[] files = file.listFiles();
				for (File f : files) {
					writeZip(f, parentPath, zos, true);
				}
			} else {
				FileInputStream fis = null;
				DataInputStream dis = null;
				try {
					fis = new FileInputStream(file);
					dis = new DataInputStream(new BufferedInputStream(fis));
					ZipEntry ze = new ZipEntry(parentPath + file.getName());
					// 解决中文乱码 ZQ。2017.02.08
					zos.setEncoding(RtCharset.XML);
					zos.setLevel(Deflater.NO_COMPRESSION);// 仅储存
					zos.putNextEntry(ze);
					byte[] content = new byte[1024];
					int len;
					while ((len = fis.read(content)) != -1) {
						zos.write(content, 0, len);
						zos.flush();
					}

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if (dis != null) {
							dis.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * 解压缩
	 * 
	 * @param zipFile
	 * @param folderPath
	 * @throws IOException
	 */
	public static void unZip(String zipFile, String folderPath) throws Exception {
		folderPath = folderPath + File.separator;
		File file = new File(zipFile);
		if (!file.exists()) {
			throw new Exception("Utils.unzip ,no find zipFile :" + zipFile);
		}
		unZipFiles(file, folderPath);
	}

	@SuppressWarnings("rawtypes")
	public static void unZipFiles(File zipFile, String folderPath) throws IOException {
		File pathFile = new File(folderPath);
		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}
		// 解决中文乱码 ZQ。2017.02.08
		ZipFile zip = new ZipFile(zipFile, RtCharset.XML);
		for (Enumeration entries = zip.getEntries(); entries.hasMoreElements();) {
			ZipEntry entry = (ZipEntry) entries.nextElement();
			String zipEntryName = entry.getName();
			InputStream in = zip.getInputStream(entry);
			String outPath = (folderPath + zipEntryName).replaceAll("\\*", "/");
			// 判断路径是否存在,不存在则创建文件路径
			File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
			if (!file.exists()) {
				file.mkdirs();
			}
			// 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
			if (new File(outPath).isDirectory()) {
				continue;
			}
			OutputStream out = new FileOutputStream(outPath);
			byte[] buf1 = new byte[1024];
			int len;
			while ((len = in.read(buf1)) > 0) {
				out.write(buf1, 0, len);
			}
			in.close();
			out.close();
		}
	}

	/**
	 * 递归删除目录下的所有文件及子目录下所有文件
	 * 
	 */
	public static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			// 递归删除目录中的子目录下
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		// 目录此时为空，可以删除
		return dir.delete();
	}

	/**
	 * 复制文件
	 * 
	 * @param s
	 *            源文件
	 * @param t
	 *            复制的新文件
	 * @throws IOException
	 */
	public static void copyFile(String s, String t) throws IOException {
		FileInputStream fi = null;
		FileOutputStream fo = null;
		FileChannel in = null;
		FileChannel out = null;
		try {
			fi = new FileInputStream(s);
			fo = new FileOutputStream(t);
			in = fi.getChannel();// 得到对应的文件通道
			out = fo.getChannel();// 得到对应的文件通道
			in.transferTo(0, in.size(), out);// 连接两个通道，并且从in通道读取，然后写入out通道
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				fi.close();
				in.close();
				fo.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw e;
			}
		}
	}

	public static void copyFile(File sourcefile, File targetFile) throws IOException {

		// 新建文件输入流并对它进行缓冲
		FileInputStream input = new FileInputStream(sourcefile);
		BufferedInputStream inbuff = new BufferedInputStream(input);

		// 新建文件输出流并对它进行缓冲
		FileOutputStream out = new FileOutputStream(targetFile);
		BufferedOutputStream outbuff = new BufferedOutputStream(out);

		// 缓冲数组
		byte[] b = new byte[1024 * 5];
		int len = 0;
		while ((len = inbuff.read(b)) != -1) {
			outbuff.write(b, 0, len);
		}

		// 刷新此缓冲的输出流
		outbuff.flush();

		// 关闭流
		inbuff.close();
		outbuff.close();
		out.close();
		input.close();

	}

	public static void copyDirectiory(String sourceDir, String targetDir) throws IOException {

		// 新建目标目录

		(new File(targetDir)).mkdirs();

		// 获取源文件夹当下的文件或目录
		File[] file = (new File(sourceDir)).listFiles();

		for (int i = 0; i < file.length; i++) {
			if (file[i].isFile()) {
				// 源文件
				File sourceFile = file[i];
				// 目标文件
				File targetFile = new File(new File(targetDir).getAbsolutePath() + File.separator + file[i].getName());

				copyFile(sourceFile, targetFile);

			}

			if (file[i].isDirectory()) {
				// 准备复制的源文件夹
				String dir1 = sourceDir + file[i].getName();
				// 准备复制的目标文件夹
				String dir2 = targetDir + "/" + file[i].getName();

				copyDirectiory(dir1, dir2);
			}
		}

	}

	public static String readFileToString(File file, String charset) throws Exception {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
			String line = null;
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			return sb.toString();
		} catch (Exception e) {
			throw e;
		} finally {
			if (br != null) {
				br.close();
			}
		}
	}

	public static void saveFileFromString(File file, String content, String charset) throws Exception {
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), charset));
		out.write(content);
		out.flush();
		out.close();
	}

	public static void main(String[] args) {
		FileUtils fu = new FileUtils();
		try {
			fu.printPdf();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void printPdf() throws Exception {
		int screenWidth = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
		
		String cmd = "phantomjs D:/phantomjs/bin/2.js " + screenWidth + " " + screenHeight;
		final Process process = Runtime.getRuntime().exec(cmd);
		printMessage(process.getInputStream(), "input");
		printMessage(process.getErrorStream(), "error");
		int value = process.waitFor();
		System.out.println("printPdf cmd end:" + value);

	}

	private void printMessage(final InputStream input, final String preFix) {
		new Thread(new Runnable() {
			public void run() {
				Reader reader = null;
				try {
					reader = new InputStreamReader(input, "GBK");
					BufferedReader bf = new BufferedReader(reader);
					String line = null;
					try {
						while ((line = bf.readLine()) != null) {
							System.out.println("preFix:" + line);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}

			}
		}).start();
	}

}
