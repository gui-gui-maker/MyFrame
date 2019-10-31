package com.fwxm.scientific.service;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Clob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fwxm.scientific.bean.Tjy2ScientificOpinion;
import com.fwxm.scientific.bean.Tjy2ScientificResearch;
import com.fwxm.scientific.dao.Tjy2ScientificOpinionDao;
import com.fwxm.scientific.dao.Tjy2ScientificResearchDao;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.exception.KhntException;
import com.lsts.finance.bean.ScientificCwbz;
import com.lsts.finance.dao.ScientificCwbzDao;

import Decoder.BASE64Decoder;


@Service("tjy2ScientificResearch")
public class Tjy2ScientificResearchManager extends EntityManageImpl<Tjy2ScientificResearch, Tjy2ScientificResearchDao> {
	
	@Autowired
	Tjy2ScientificOpinionDao tjy2ScientificOpinionDao;
	  @Autowired
	    Tjy2ScientificResearchDao tjy2ScientificResearchDao;
	    @Autowired
	    ScientificCwbzDao scientificCwbzDao;


	/**
	 * 返回对应编号类型对应的编号
	 * 
	 * @param typeTag
	 * @return
	 * @throws Exception
	 */
	public String getProjectNo(String typeTag) throws Exception {
		Calendar a = Calendar.getInstance();
		String nowYear = a.get(Calendar.YEAR) + "";
		List<String> noList = tjy2ScientificResearchDao.getProjectNoList(typeTag, nowYear);
		String bh = this.getbh(noList, typeTag, nowYear);
		if (noList.contains(bh)) {// 万一由于编号的位数问题导致生成的编号为已有编号
			throw new Exception("分配编号错误");
		}
		return bh;
	}


	/**
	 * 根据编号的类型和当前年份生成下一个编号
	 * 
	 * @param noList
	 * @param typeTag
	 * @param nowYear
	 * @return
	 */
	public synchronized String getbh(List<String> noList, String typeTag, String nowYear) {
		String docNum = "";
		if (noList == null || noList.size() == 0) {// 如果编号中没有此类编号当年的记录，就生成此类编号当年的第一条记录
			docNum = typeTag + "-" + nowYear + "-" + "01";
		} else {
			String maxNo = Collections.max(noList);// 找出最大编号
			int n = Integer.valueOf(maxNo.substring(maxNo.length() - 2));// 取编号的后两位数字+1后生成新编号
			docNum = typeTag + "-" + nowYear + "-" + String.format("%02d", n + 1);
		}
		return docNum;
	}


   
  
	/**
	 * 复制科研申请内容
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Tjy2ScientificResearch reportCopy(Tjy2ScientificResearch research) throws Exception {

		Tjy2ScientificResearch scientificItemNew = new Tjy2ScientificResearch();

		BeanUtils.copyProperties(scientificItemNew, research);
		scientificItemNew.setId(null);

		scientificItemNew.setProjectFlag("2");//设置为项目管理数据
		scientificItemNew.setStatus("0");//状态改成录入状态
		String[] fieldNames= getFiledName(scientificItemNew);
		//将对应第9页和第10页的属性赋值
		if(fieldNames.length>0){
			for(String field:fieldNames){
				if(field.length()>=7&&(("P9").equals(field.substring(0, 2))||("P10").equals(field.substring(0, 3)))){
					setFieldValueByName(field,scientificItemNew);

				}
			}
		}
		// 保存对象
		tjy2ScientificResearchDao.save(scientificItemNew);
		return scientificItemNew;
	}

	/**
	 * 根据复制前后的对象id专门复制P1-P7页的内容
	 * 
	 * @param id
	 *            原对象id
	 * @param newId
	 *            复制对像id
	 * @throws Exception
	 */
	public void copyBase(String id, String newId) throws Exception {
		Object[] o = this.detailBase(id);
		Map<String, String> m = new HashMap<String, String>();
		if (o != null) {
			for (int i = 0; i < o.length; i++) {
				Clob clob = (Clob) o[i];
				if (clob != null) {
					int j = i + 1;
					m.put("P" + j + "00001", clob.getSubString((long) 1, (int) clob.length()));
				}
			}
		}
		this.saveBase(newId, m.get("P100001"), m.get("P200001"), m.get("P300001"), m.get("P400001"), m.get("P500001"),
				m.get("P600001"), m.get("P700001"));
	}

	/**
	 * 获取某个对像的属性名称
	 * 
	 * @param o
	 * @return
	 */
	public String[] getFiledName(Object o) {
		Field[] fields = o.getClass().getDeclaredFields();
		String[] fieldNames = new String[fields.length];
		for (int i = 0; i < fields.length; i++) {
			fieldNames[i] = fields[i].getName();
		}
		return fieldNames;
	}

	/**
	 * 根据属性名给对应属性赋值
	 * 
	 * @param fieldName
	 * @param o
	 */
	public void setFieldValueByName(String fieldName, Object o) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String setter = "set" + firstLetter + fieldName.substring(1);
			Method method = o.getClass().getMethod(setter, String.class);
			method.invoke(o, "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存文档
	 * 
	 * @param inputStream
	 * @param order
	 */
	public String saveO(InputStream inputStream, Tjy2ScientificResearch research) {
		String orderId = tjy2ScientificResearchDao.saveO(inputStream, research);
		return orderId;
	}

	/**
	 * 取出文档
	 * 
	 * @param id
	 * @return
	 * @throws KhntException
	 */
	public byte[] getFile(String id) throws KhntException {
		// 取得文档
		byte[] order = tjy2ScientificResearchDao.getFile(id);
		// 取得表记录
		/*
		 * Tjy2ScientificResearch research = tjy2ScientificResearchDao.get(id);
		 * research.setFileBlob(order);
		 */
		if (order == null) {
			return null;
		}
		return order;
	}

	public void saveBase(String id, String P100001, String P200001, String P300001, String P400001, String P500001,
			String P600001, String P700001) {
		String sql = "update TJY2_SCIENTIFIC_RESEARCH set P100001=?,P200001=?,P300001=?,P400001=?,P500001=?,P600001=?,P700001=? where id=?";
		tjy2ScientificResearchDao.createSQLQuery(sql, P100001, P200001, P300001, P400001, P500001, P600001, P700001, id)
				.executeUpdate();
	}

	public Object[] detailBase(String id) {
		Object[] o = null;
		String sql = "select t.P100001,t.P200001,t.P300001,t.P400001,t.P500001,t.P600001,t.P700001 from TJY2_SCIENTIFIC_RESEARCH t where t.id = ?";
		List<Object> list = tjy2ScientificResearchDao.createSQLQuery(sql, id).list();
		if (list.size() > 0) {
			o = (Object[]) list.get(0);

		}
		return o;

	}

	/*
	 * public static void input(String clob) throws IOException, ZipException,
	 * SAXException, ParserConfigurationException, TransformerException,
	 * TransformerConfigurationException, org.xml.sax.SAXException { //
	 * 使用java.util打开文件 File file=new File("D:1.docx"); boolean
	 * exist=file.exists(); boolean read=file.canRead(); boolean
	 * write=file.canWrite(); System.out.println(exist);
	 * System.out.println(read); System.out.println(write); ZipFile docxFile =
	 * new ZipFile(file); // 返回ZipEntry应用程序接口 ZipEntry documentXML =
	 * docxFile.getEntry("word/document.xml");
	 * 
	 * InputStream documentXMLIS = docxFile.getInputStream(documentXML);
	 * 
	 * DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	 * 
	 * Document doc = dbf.newDocumentBuilder().parse(documentXMLIS);
	 * 
	 * // linkMan tel proCode companyName fundName fundCode sysProCode
	 * Map<String, String> bookMarkMap = new HashMap<String, String>(); byte b[]
	 * = clob.getBytes(); ByteArrayInputStream bais = new
	 * ByteArrayInputStream(b); POIFSFileSystem poifs = new POIFSFileSystem();
	 * DirectoryEntry directory = poifs.getRoot(); DocumentEntry documentEntry =
	 * directory.createDocument("WordDocument", bais);
	 * bookMarkMap.put("userName", clob); bookMarkMap.put("password", "888888");
	 * 
	 *//**
		 * 书签列表
		 *//*
		 * NodeList this_book_list =
		 * doc.getElementsByTagName("w:bookmarkStart"); if
		 * (this_book_list.getLength() != 0) { for (int j = 0; j <
		 * this_book_list.getLength(); j++) { // 获取每个书签 Element oldBookStart =
		 * (Element) this_book_list.item(j); // 书签名 String bookMarkName =
		 * oldBookStart.getAttribute("w:name"); // 书签名，跟需要替换的书签传入的map集合比较 for
		 * (Map.Entry<String, String> entry : bookMarkMap.entrySet()) { //
		 * 书签处值开始 Node wr = doc.createElement("w:r"); Node wt =
		 * doc.createElement("w:t"); Node wt_text =
		 * doc.createTextNode(entry.getValue()); wt.appendChild(wt_text);
		 * wr.appendChild(wt); // 书签处值结束 if
		 * (entry.getKey().equals(bookMarkName)) { Element node = (Element)
		 * oldBookStart.getNextSibling();// 获取兄弟节点w:r //
		 * 如果书签处无文字,则在书签处添加需要替换的内容，如果书签处存在描述文字，则替换内容,用w:r NodeList wtList =
		 * node.getElementsByTagName("w:t");// 获取w:r标签下的显示书签处内容标签w:t if
		 * (wtList.getLength() == 0) { // 如果不存在，即，书签处本来就无内容，则添加需要替换的内容
		 * oldBookStart.appendChild(wr); } else { // 如果书签处有内容，则直接替换内容 Element
		 * wtNode = (Element) wtList.item(0);
		 * wtNode.setTextContent(entry.getValue()); }
		 * 
		 * } }
		 * 
		 * } }
		 * 
		 * Transformer t = TransformerFactory.newInstance().newTransformer();
		 * ByteArrayOutputStream baos = new ByteArrayOutputStream();
		 * t.transform(new DOMSource(doc), new StreamResult(baos));
		 * ZipOutputStream docxOutFile = new ZipOutputStream(new
		 * FileOutputStream( "D:\\response.docx")); Enumeration entriesIter =
		 * docxFile.entries(); while (entriesIter.hasMoreElements()) { ZipEntry
		 * entry = (ZipEntry) entriesIter.nextElement();
		 * //如果是document.xml则修改，别的文件直接拷贝，不改变word的样式 if
		 * (entry.getName().equals("word/document.xml")) { byte[] data =
		 * baos.toByteArray(); docxOutFile.putNextEntry(new
		 * ZipEntry(entry.getName())); docxOutFile.write(data, 0, data.length);
		 * docxOutFile.closeEntry(); } else { InputStream incoming =
		 * docxFile.getInputStream(entry);
		 * //此处设定值需慎重，如果设置小了，会破坏word文档，至于为什么会破坏，自己去思考 byte[] data = new
		 * byte[1024 * 512]; int readCount = incoming.read(data, 0, (int)
		 * entry.getSize()); docxOutFile.putNextEntry(new
		 * ZipEntry(entry.getName())); docxOutFile.write(data, 0, readCount);
		 * docxOutFile.closeEntry(); } } docxOutFile.close(); }
		 * 
		 */
	public synchronized String inputFile(String s, String type) {
		BASE64Decoder decoder = new BASE64Decoder();
		try {// 解密
			byte[] b = decoder.decodeBuffer(s);
			// 处理数据
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			Date currentTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			String path = formatter.format(currentTime);
			// D:/SCSEI_WEB/webapps/ROOT/upload/
			// D:/apache-tomcat-8.0.46/wtpwebapps/SYTS/upload/
			OutputStream out = new FileOutputStream("D:/Servers/SCSEI_WEB/webapps/ROOT/upload/" + path + "." + type);

			out.write(b);
			out.flush();
			out.close();
			return "D:/Servers/SCSEI_WEB/webapps/ROOT/upload/" + path + "." + type;// http://kh.scsei.org.cn/upload/
		} catch (Exception e) {
			System.out.println(e);
			return "";
		}

	}

	public void updateAddType(String type) {
		String sql = "update TJY2_SCIENTIFIC_RESEARCH set PROJECT_TYPE=? where id='10000'";
		tjy2ScientificResearchDao.createSQLQuery(sql, type).executeUpdate();
	}


	public void createGrade(String id, String[] audit_names, String[] audit_ids) throws Exception{
		
		for (int j = 0; j < audit_names.length; j++) {
					Tjy2ScientificOpinion tjy2ScientificOpinion = new Tjy2ScientificOpinion();
					tjy2ScientificOpinion.setFk_tjy2_scientific_research(id);
					tjy2ScientificOpinion.setProject_audit_state("0");
					tjy2ScientificOpinion.setProject_audit(audit_names[j]);
					tjy2ScientificOpinion.setProject_audit_id(audit_ids[j]);
					tjy2ScientificOpinionDao.save(tjy2ScientificOpinion);
		}
	}
							

	public List<Tjy2ScientificOpinion> getaudit(String... args) {
		String hql1 = "from Tjy2ScientificOpinion  where fk_tjy2_scientific_research=? and project_audit_id=?";
		String hql2 = "from Tjy2ScientificOpinion  where fk_tjy2_scientific_research=?";
		List<Tjy2ScientificOpinion> list = new ArrayList<Tjy2ScientificOpinion>();
		try {
			if (args != null && args.length == 2) {
				list = tjy2ScientificOpinionDao.listQuery(hql1, args[0], args[1]);
			} else if(args != null && args.length == 1){
				list = tjy2ScientificOpinionDao.listQuery(hql2, args[0]);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public String  queryMoney(String id,String fyType) throws Exception{
		String temp="";
		Tjy2ScientificResearch search=tjy2ScientificResearchDao.get(id);
		//获取科研项目总金额
		String total = search.getP1000004()!=null?search.getP1000004():"0";
		//获取报销记录的金额
		String hql =" from ScientificCwbz where fk_researcj_id=? and status<>'99' and fy_type='"+fyType+"'";
		List<ScientificCwbz> list = scientificCwbzDao.createQuery(hql,id).list();
		//循环取出报销金额
		BigDecimal je = new BigDecimal("0"); 
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				if(list.get(i).getBxje()!=null){
					BigDecimal	tmp=list.get(i).getBxje();
					je=je.add(tmp);
				}
			}
		}
		
//	int flag =new BigDecimal(total).compareTo(je);//判段报销金额是否已经大于总金额
		
		BigDecimal over=new BigDecimal(total).subtract(je);//算出剩余金额
		
//		if(flag==-1){//其实没必要判断是否已经报销完成 报销完成的应该选择都不能选择
//			temp="该项目已经完成报销";
//		}else{
			temp="该项目差旅费金额："+total+" ,已报销金额："+je+" ,可报销金额："+over+" ";
//		}
	   return temp;
	}
	
	
	public String  queryMoneyOnly(String id,String fyType,String money,String bxId) throws Exception{
		String temp="";
		Tjy2ScientificResearch search=tjy2ScientificResearchDao.get(id);
		//获取科研项目总金额
		String total="";
		//先判断项目费用类型
  	  if("1".equals(fyType)){
  		  
  		  total=search.getP1000001()!=null?search.getP1000001():"0";
  		  
  	  }else if("2".equals(fyType)){
  		  total=search.getP1000002()!=null?search.getP1000002():"0";
  		 
  	  }else if("3".equals(fyType)){
  		  total=search.getP1000003()!=null?search.getP1000003():"0";
  		 
  	  }else if("5".equals(fyType)){
  		  total=search.getP1000005()!=null?search.getP1000005():"0";
  		  
  	  }else if("6".equals(fyType)){//怕字符串传递过程有问题采用这种方式判断
  		  total=search.getP1000006()!=null?search.getP1000006():"0";
  		  
  	  }else if("7".equals(fyType)){
  		  total=search.getP1000007()!=null?search.getP1000007():"0";
  		 
  	  }else if("8".equals(fyType)){
  		  total=search.getP1000008()!=null?search.getP1000008():"0";
  		  
  	  }else if("9".equals(fyType)){
  		  total=search.getP1000009()!=null?search.getP1000009():"0";
  		
  	  }else if("10".equals(fyType)){
  		  total=search.getP10000010()!=null?search.getP10000010():"0";
  		  
  	  }
  	  	
  	  	String hql="";
  	  	//如果是修改的，查询时排除之前记录的数据
  	  	if(!"".equals(bxId)&&bxId!=null){
  	  		hql =" from ScientificCwbz where fk_researcj_id=? and status<>'99' and fk_clfbxd_id<>'"+bxId+"' and fy_type='"+fyType+"'";	
  	  	}else{
  	  		hql =" from ScientificCwbz where fk_researcj_id=? and status<>'99'  and fy_type='"+fyType+"'";
  	  	}
  	  
  	  
		//获取报销记录的金额
		
		List<ScientificCwbz> list = scientificCwbzDao.createQuery(hql,id).list();
		//循环取出报销金额
		BigDecimal je = new BigDecimal("0"); 
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				if(list.get(i).getBxje()!=null){
					BigDecimal	tmp=list.get(i).getBxje();
					je=je.add(tmp);
				}
			}
		}
		
		int flag =new BigDecimal(total).compareTo(je.add(new BigDecimal(money)));//判段项目金额是否已经大于项目总金额，-1表示小于，0是等于，1是大于
		BigDecimal over=new BigDecimal(total).subtract(je);//算出剩余金额
		
		 if(flag==-1){
			temp="所填金额："+money+"  超出了该费用项目总金额："+total+ ", 可报金额："+over+" ,请重新填写！";
			
		}
		
		
	   return temp;
	}

	public void saveOpinion(Tjy2ScientificOpinion tjy2ScientificOpinion){
		tjy2ScientificOpinionDao.save(tjy2ScientificOpinion);
	}



}
