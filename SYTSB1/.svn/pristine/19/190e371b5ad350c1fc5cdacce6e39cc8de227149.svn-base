package com.khnt.common.utils;

import com.khnt.base.Factory;
import com.khnt.core.exception.KhntException;
import com.khnt.utils.LogUtil;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.SimpleDateFormat;


public class FastDFSUtil {
	static Logger log = LoggerFactory.getLogger(FastDFSUtil.class);
	private static TrackerClient trackerClient = null;
	private static TrackerServer trackerServer = null;
	private static StorageServer storageServer = null;
	private static StorageClient1 storageClient = null;
	
	/**
	 * 初始化配置
	 */
	static {
        try {
        	String basePath = Factory.getWebRoot() + "WEB-INF" + File.separator + "config" + File.separator;
    		String propsFilePath = basePath + "fastdfs-client.properties";
            ClientGlobal.initByProperties(propsFilePath);
            
            //trackerClient = new TrackerClient();
            trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();
            storageServer = trackerClient.getStoreStorage(trackerServer);
            storageClient = new StorageClient1(trackerServer, storageServer);
        } catch (Exception e) {
        	log.error("初始化文件存储系统配置错误！");
        	LogUtil.logError(log, e);
            e.printStackTrace();
        }
    }
	
	/**
	 * 上传文件（基于文件流），附加属性
	 * @param inputStream
	 * @param valuePairs
	 * @return
	 * @throws Exception
	 */
	public static String upload(InputStream inputStream, NameValuePair[] valuePairs) throws Exception{
        byte[] buffer = null;
    	ByteArrayOutputStream bos = new ByteArrayOutputStream();
    	byte[] b = new byte[1024];
    	int n;
    	while ((n = inputStream.read(b)) != -1) {
    	    bos.write(b, 0, n);
    	}
    	bos.close();
    	buffer = bos.toByteArray();
    	
        //String file_ext_name=FilenameUtils.getExtension(fileName);
    	String fileName = valuePairs == null ? "": valuePairs[0].getValue();
    	String fileExtName = valuePairs == null ? "": valuePairs[2].getValue();
        String path = storageClient.upload_file1(buffer, fileExtName, valuePairs);
        log.debug("FastDFS save(" + fileName + ") to " + path);
        inputStream.close();
        
        return path;
	}

	/**
	 * 上传文件（基于文件流）
	 * @param inputStream
	 * @return
	 * @throws Exception
	 */
	public static String upload(InputStream inputStream) throws Exception{
		return upload(inputStream,null);
	}

	/**
	 * 上传文件（基于File），附加属性
	 * @param file
	 * @param valuePairs
	 * @return
	 * @throws Exception
	 */
	public static String upload(File file, NameValuePair[] valuePairs) throws Exception{
		InputStream inputStream = new FileInputStream(file);
		return upload(inputStream,valuePairs);
	}

	/**
	 * 上传文件（基于File）
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static String upload(File file) throws Exception{
		return upload(file,null);
	}
	
    
	/**
	 * 下载文件，返回Byte[]
	 * @param path
	 * @return
	 * @throws Exception
	 */
    public static byte[] downloadByte(String path) throws Exception {
    	return storageClient.download_file1(path);
    }
    
    /**
     * 下载文件，返回InputStream
     * @param path
     * @return
     * @throws Exception
     */
    public static InputStream downloadInputStream(String path) throws Exception {
    	InputStream inputStream = null;
    	byte[] buf = storageClient.download_file1(path);
    	if(buf != null ) {
    		inputStream = new ByteArrayInputStream(buf); 
    	}
    	return inputStream;
    }

	/**
	 * 下载文件，返回String
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static String downloadString(String path) throws Exception {
		String content = "";
		byte[] buf = storageClient.download_file1(path);
		if(buf != null ) {
			content = new String(buf,"UTF-8");
		}
		return content;
	}

    
    /**
     * 删除文件
     * @param path
     * @return -1失败,0成功
     */
    public static Integer delete(String path){
		int result=-1;
		try {
			result = storageClient.delete_file1(path);
			log.debug("FastDFS delete filePath : " + path + " , status : " + result);
		} catch (Exception e) {
			LogUtil.logError(log,e);
			throw new KhntException("从文件存储系统中删除文件失败！");
		}
		return result;
    }
	
	
    public static String test_upload(byte[] file_buff,String filename) throws Exception{
        //1.创建classPathResouce对象，用于加载配置文件
        //ClassPathResource resource = new ClassPathResource("fdfs_client.conf");
        //2.初始化配置文件
        //ClientGlobal.init(resource.getClassLoader().getResource("fdfs_client.conf").getPath());
        //ClientGlobal.init("D:\\workspace\\workspace_tzsb\\khnt-cdts\\khnt-cdts-common\\src\\main\\resources\\fdfs_client.conf");
        ClientGlobal.initByProperties("D:\\workspace\\idea_workspace\\khnt-cdts\\khnt-cdts-webapp\\src\\main\\webapp\\WEB-INF\\config\\fastdfs-client.properties");
        //3.获取跟踪服务器的客户端 
        TrackerClient trackerClient = new TrackerClient();
        //4.通过跟踪服务器的客户端获取服务端 
        TrackerServer trackerServer = trackerClient.getConnection();
        //5通过跟踪服务器的服务端获取存储服务器的客户端 
        StorageClient1 storageClient1 = new StorageClient1(trackerServer,null);
        //6.将附件上传至FastDFS
        String  file_ext_name=FilenameUtils.getExtension(filename);
        String path=storageClient1.upload_file1(file_buff, file_ext_name, null);
        return path;
    }

    
    public static void test_download(String path) throws Exception {
    	try {
    		ClientGlobal.init("D:\\workspace\\workspace_tzsb\\khnt-cdts\\khnt-cdts-common\\src\\main\\resources\\fdfs_client.conf");
    		TrackerClient trackerClient = new TrackerClient();
    		TrackerServer trackerServer = trackerClient.getConnection();
    		StorageServer storageServer = null;
    		StorageClient1 storageClient1 = new StorageClient1(trackerServer, storageServer);
            // 根据文件标识下载文件
    		//group1/M00/00/00/wKhUg1wjQbmAb2FJAApuDr1vsPQ7374184
            byte[] by = storageClient1.download_file1("group1/M00/00/00/wKhUgVwa79mACKDdAApuDr1vsPQ2857416");
            // 将数据写入输出流
            IOUtils.write(by, new FileOutputStream("D:\\FastDFS下载.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public static void monitor(String path) throws Exception {
    	try {
    		ClientGlobal.init("D:\\workspace\\workspace_tzsb\\khnt-cdts\\khnt-cdts-common\\src\\main\\resources\\fdfs_client.conf");
    		TrackerClient trackerClient = new TrackerClient();
    		TrackerServer trackerServer = trackerClient.getConnection();
    		
    		StructGroupStat[] groupStats = trackerClient.listGroups(trackerServer);
    		
    		int count = 0;
    	      for (StructGroupStat groupStat : groupStats) {
    	        count++;
    	        System.out.println("Group " + count + ":");
    	        System.out.println("group name = " + groupStat.getGroupName());
    	        System.out.println("disk total space = " + groupStat.getTotalMB() + "MB");
    	        System.out.println("disk free space = " + groupStat.getFreeMB() + " MB");
    	        System.out.println("trunk free space = " + groupStat.getTrunkFreeMB() + " MB");
    	        System.out.println("storage server count = " + groupStat.getStorageCount());
    	        System.out.println("active server count = " + groupStat.getActiveCount());
    	        System.out.println("storage server port = " + groupStat.getStoragePort());
    	        System.out.println("storage HTTP port = " + groupStat.getStorageHttpPort());
    	        System.out.println("store path count = " + groupStat.getStorePathCount());
    	        System.out.println("subdir count per path = " + groupStat.getSubdirCountPerPath());
    	        System.out.println("current write server index = " + groupStat.getCurrentWriteServer());
    	        System.out.println("current trunk file id = " + groupStat.getCurrentTrunkFileId());

    	        StructStorageStat[] storageStats = trackerClient.listStorages(trackerServer, groupStat.getGroupName());
    	        if (storageStats == null) {
    	          System.out.println("");
    	          System.out.println("ERROR! list storage error, error no: " + trackerClient.getErrorCode());
    	          System.out.println("");
    	          break;
    	        }

    	        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	        int stroageCount = 0;
    	        for (StructStorageStat storageStat : storageStats) {
    	          stroageCount++;
    	          System.out.println("\tStorage " + stroageCount + ":");
    	          System.out.println("\t\tstorage id = " + storageStat.getId());
    	          System.out.println("\t\tip_addr = " + storageStat.getIpAddr() + "  " + ProtoCommon.getStorageStatusCaption(storageStat.getStatus()));
    	          System.out.println("\t\thttp domain = " + storageStat.getDomainName());
    	          System.out.println("\t\tversion = " + storageStat.getVersion());
    	          System.out.println("\t\tjoin time = " + df.format(storageStat.getJoinTime()));
    	          System.out.println("\t\tup time = " + (storageStat.getUpTime().getTime() == 0 ? "" : df.format(storageStat.getUpTime())));
    	          System.out.println("\t\ttotal storage = " + storageStat.getTotalMB() + "MB");
    	          System.out.println("\t\tfree storage = " + storageStat.getFreeMB() + "MB");
    	          System.out.println("\t\tupload priority = " + storageStat.getUploadPriority());
    	          System.out.println("\t\tstore_path_count = " + storageStat.getStorePathCount());
    	          System.out.println("\t\tsubdir_count_per_path = " + storageStat.getSubdirCountPerPath());
    	          System.out.println("\t\tstorage_port = " + storageStat.getStoragePort());
    	          System.out.println("\t\tstorage_http_port = " + storageStat.getStorageHttpPort());
    	          System.out.println("\t\tcurrent_write_path = " + storageStat.getCurrentWritePath());
    	          System.out.println("\t\tsource ip_addr = " + storageStat.getSrcIpAddr());
    	          System.out.println("\t\tif_trunk_server = " + storageStat.isTrunkServer());
    	          //System.out.println("\t\tconntion.alloc_count  = " + storageStat.getConnectionAllocCount());
    	         // System.out.println("\t\tconntion.current_count  = " + storageStat.getConnectionCurrentCount());
    	          //System.out.println("\t\tconntion.max_count  = " + storageStat.getConnectionMaxCount());
    	          System.out.println("\t\ttotal_upload_count = " + storageStat.getTotalUploadCount());
    	          System.out.println("\t\tsuccess_upload_count = " + storageStat.getSuccessUploadCount());
    	          System.out.println("\t\ttotal_append_count = " + storageStat.getTotalAppendCount());
    	          System.out.println("\t\tsuccess_append_count = " + storageStat.getSuccessAppendCount());
    	          System.out.println("\t\ttotal_modify_count = " + storageStat.getTotalModifyCount());
    	          System.out.println("\t\tsuccess_modify_count = " + storageStat.getSuccessModifyCount());
    	          System.out.println("\t\ttotal_truncate_count = " + storageStat.getTotalTruncateCount());
    	          System.out.println("\t\tsuccess_truncate_count = " + storageStat.getSuccessTruncateCount());
    	          System.out.println("\t\ttotal_set_meta_count = " + storageStat.getTotalSetMetaCount());
    	          System.out.println("\t\tsuccess_set_meta_count = " + storageStat.getSuccessSetMetaCount());
    	          System.out.println("\t\ttotal_delete_count = " + storageStat.getTotalDeleteCount());
    	          System.out.println("\t\tsuccess_delete_count = " + storageStat.getSuccessDeleteCount());
    	          System.out.println("\t\ttotal_download_count = " + storageStat.getTotalDownloadCount());
    	          System.out.println("\t\tsuccess_download_count = " + storageStat.getSuccessDownloadCount());
    	          System.out.println("\t\ttotal_get_meta_count = " + storageStat.getTotalGetMetaCount());
    	          System.out.println("\t\tsuccess_get_meta_count = " + storageStat.getSuccessGetMetaCount());
    	          System.out.println("\t\ttotal_create_link_count = " + storageStat.getTotalCreateLinkCount());
    	          System.out.println("\t\tsuccess_create_link_count = " + storageStat.getSuccessCreateLinkCount());
    	          System.out.println("\t\ttotal_delete_link_count = " + storageStat.getTotalDeleteLinkCount());
    	          System.out.println("\t\tsuccess_delete_link_count = " + storageStat.getSuccessDeleteLinkCount());
    	          System.out.println("\t\ttotal_upload_bytes = " + storageStat.getTotalUploadBytes());
    	          System.out.println("\t\tsuccess_upload_bytes = " + storageStat.getSuccessUploadBytes());
    	          System.out.println("\t\ttotal_append_bytes = " + storageStat.getTotalAppendBytes());
    	          System.out.println("\t\tsuccess_append_bytes = " + storageStat.getSuccessAppendBytes());
    	          System.out.println("\t\ttotal_modify_bytes = " + storageStat.getTotalModifyBytes());
    	          System.out.println("\t\tsuccess_modify_bytes = " + storageStat.getSuccessModifyBytes());
    	          System.out.println("\t\ttotal_download_bytes = " + storageStat.getTotalDownloadloadBytes());
    	          System.out.println("\t\tsuccess_download_bytes = " + storageStat.getSuccessDownloadloadBytes());
    	          System.out.println("\t\ttotal_sync_in_bytes = " + storageStat.getTotalSyncInBytes());
    	          System.out.println("\t\tsuccess_sync_in_bytes = " + storageStat.getSuccessSyncInBytes());
    	          System.out.println("\t\ttotal_sync_out_bytes = " + storageStat.getTotalSyncOutBytes());
    	          System.out.println("\t\tsuccess_sync_out_bytes = " + storageStat.getSuccessSyncOutBytes());
    	          System.out.println("\t\ttotal_file_open_count = " + storageStat.getTotalFileOpenCount());
    	          System.out.println("\t\tsuccess_file_open_count = " + storageStat.getSuccessFileOpenCount());
    	          System.out.println("\t\ttotal_file_read_count = " + storageStat.getTotalFileReadCount());
    	          System.out.println("\t\tsuccess_file_read_count = " + storageStat.getSuccessFileReadCount());
    	          System.out.println("\t\ttotal_file_write_count = " + storageStat.getTotalFileWriteCount());
    	          System.out.println("\t\tsuccess_file_write_count = " + storageStat.getSuccessFileWriteCount());
    	          System.out.println("\t\tlast_heart_beat_time = " + df.format(storageStat.getLastHeartBeatTime()));
    	          System.out.println("\t\tlast_source_update = " + df.format(storageStat.getLastSourceUpdate()));
    	          System.out.println("\t\tlast_sync_update = " + df.format(storageStat.getLastSyncUpdate()));
    	          //System.out.println("\t\tlast_synced_timestamp = " + df.format(storageStat.getLastSyncedTimestamp()) + getSyncedDelayString(storageStats, storageStat));
    	        }
    	      }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
    	try {
    		// 上传
    		byte[] buffer = null;
	    	File file = new File("D:\\data.json");
	    	FileInputStream fis = new FileInputStream(file);
	    	ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    	byte[] b = new byte[1024];
	    	int n;
	    	while ((n = fis.read(b)) != -1) {
	    	    bos.write(b, 0, n);
	    	}
	    	fis.close();
	    	bos.close();
	    	buffer = bos.toByteArray();
	    	
	    	String path = test_upload(buffer,"test");
	    	System.out.println("FastDFS上传文件保存路径：" + path);
	    	
	    	// 下载
	    	//download(null);
    	} catch (Exception e) {
    	    e.printStackTrace();
    	}
	}
}