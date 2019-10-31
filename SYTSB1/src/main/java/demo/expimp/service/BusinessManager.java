package demo.expimp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.exception.KhntException;
import com.khnt.pub.expimp.service.ExpImpValidateHandler;
import com.khnt.pub.expimp.service.ValidateException;
import com.khnt.pub.expimp.task.ExcelExportTask;
import com.khnt.pub.expimp.task.ExportTask;
import com.khnt.pub.expimp.task.ImportTask;

import demo.expimp.bean.DemoBusinessBean;
import demo.expimp.dao.DemoBusinessDao;

/**
 * 数据导入导出演示：业务处理接口实现
 * 
 */
@Service("demoExpImpHandler")
public class BusinessManager extends EntityManageImpl<DemoBusinessBean, DemoBusinessDao> implements
		ExpImpValidateHandler {

	@Autowired
	DemoBusinessDao businessDao;

	Log log = LogFactory.getLog(getClass());

	/**
	 * 导入成功后业务后续处理
	 * 
	 * @param result
	 *            导入的数据
	 */
	public void importHandle(List<Object> result) {
		log.debug("本批次数据总数为" + result.size());
		// 检查数据包装方式
		Object obj = result.get(0);
		if (obj instanceof Map)// Hashmap包装
			mapImport(result);
		else
			// bean包装
			beanImport(result);
	}

	/**
	 * 业务Bean方式导入
	 * 
	 * 将数据分批次进行导入，每一批次100行数据
	 */
	protected void beanImport(List<Object> beans) {
		log.debug("Entity Bean方式导入数据");
		List<DemoBusinessBean> batchData = new ArrayList<DemoBusinessBean>();
		for (int i = 0; i < beans.size(); i++) {
			batchData.add((DemoBusinessBean) beans.get(i));
		}
		businessDao.saveBatch(batchData);
	}

	/**
	 * Map方式导入数据。
	 * 
	 * 需要将Map转换成业务 entity bean
	 */
	@SuppressWarnings("unchecked")
	protected void mapImport(List<Object> maps) {
		log.debug("Map方式导入数据");
		List<DemoBusinessBean> batchData = new ArrayList<DemoBusinessBean>();
		for (int i = 0; i < maps.size(); i++) {
			Map<String, Object> map = (Map<String, Object>) maps.get(i);
			DemoBusinessBean bean = new DemoBusinessBean();
			Date date = (Date) map.get("t_date");
			Long longVal = (Long) map.get("t_long");
			String str = (String) map.get("t_string");
			String bool = (String) map.get("t_bool");
			Integer intVal = (Integer) map.get("t_int");
			Float floatVal = (Float) map.get("t_float");

			bean.setStr(str);
			bean.setDate(date);
			bean.setBool(bool == "1" ? true : false);
			bean.setIntVal(intVal == null ? 0 : intVal);
			bean.setLongVal(longVal == null ? 0 : longVal);
			bean.setFloatVal(floatVal == null ? 0 : floatVal);

			batchData.add(bean);
		}
		businessDao.saveBatch(batchData);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void importValidate(Object rowObj) throws ValidateException {
		if (rowObj instanceof DemoBusinessBean) {
			DemoBusinessBean dbb = (DemoBusinessBean) rowObj;
			if(100==dbb.getIntVal())throw new ValidateException("第2列数据不允许为100");
		}
		else {
			Map<String, Object> rm = (Map<String, Object>) rowObj;
			if(rm.get("t_int").equals(100))throw new ValidateException("第2列数据不允许为100");
		}
	}

	/**
	 * 业务自行处理数据源。
	 * 
	 * 这里，通过importTask.getSource()方法获取数据源对象，转换成合适的格式，解析出要导入的数据总数，
	 * 并通过参数importTask对象的setTotal( int)方法设置数据总数以便返回给前端进行进度跟踪。
	 * 
	 * 在每处理完一行数据之后，调用importTask.finish(1)，更新数据导入进度。
	 * 
	 * @param source
	 *            导入的数据源，格式由业务自行决定
	 * @param importTask
	 *            数据导入任务信息
	 */
	public void doBusImport(ImportTask importTask) throws Exception {
		log.debug("业务自行解析数据并导入");
		Workbook wb = (Workbook) importTask.getSource();
		// 获取到下一个待处理的Excel数据行
		Sheet s = wb.getSheetAt(0);
		int total = s.getLastRowNum();
		log.debug("Excel中的数据总行数为" + (total - 1));
		importTask.setTotal(total);

		List<DemoBusinessBean> batchData = new ArrayList<DemoBusinessBean>();

		// 数据从第二行开始
		for (int i = 1; i <= total; i++) {
			Row row = s.getRow(i);
			DemoBusinessBean tbb = new DemoBusinessBean();
			tbb.setStr(row.getCell(0).getStringCellValue());
			tbb.setBool(row.getCell(1).getBooleanCellValue());
			tbb.setDate(row.getCell(2).getDateCellValue());
			tbb.setIntVal(new Double(row.getCell(3).getNumericCellValue()).intValue());
			tbb.setLongVal(new Double(row.getCell(4).getNumericCellValue()).longValue());
			tbb.setFloatVal(new Double(row.getCell(4).getNumericCellValue()).floatValue());
			batchData.add(tbb);

			// 测试数据导入过程中发生意外
			if (i == 5000) {
				// 如果要终止本次操作，直接抛出KhntException，并附上错误提示！
				throw new KhntException("第106行数据不正确！");

				// 如果不终止操作，在这里标记一个错误行,然后继续下一行
				// importTask.error(1);
				// continue;
			}

			// 一批100个已满或者循环到最后一个时，提交这一批数据
			if (batchData.size() == 100 || i == total) {
				log.debug("一批数据已满，保存这一批");
				businessDao.saveBatch(batchData);

				// 清空这一批数据，重新装入
				batchData.clear();

				// 返回进度，标志为每批次的数据量
				importTask.finish(i == total ? total % 100 : 100);

				log.debug("当前导入进度：" + i);
			}
		}

		// 如果导入过程中有错误的数据，可以将这些错误的数据汇总起来，形成一个文件返回给用户
		// 调用 importTask.completeSuccess方法完成本次导入，并将错误的数据汇总为一个File作为参数。
		// 如果没有错误数据，不需要调用此方法
		// importTask.completeSuccess(file);
	}

	/**
	 * 通用数据导出时，业务提供要导出的数据集合。
	 * 
	 * @param parameter
	 *            业务数据查询条件
	 * @return 要导出的数据集合，该集合的元素对象必须与配置模板中设定的数据包装类型一致。
	 *         比如配置为BusinessBean，则这里的List<Object>实际为List<BusinessBean>
	 *         配置为haspMap或者数据库表，则这里的List<Object>实际为List<HashMap>，
	 *         hashmap的key就是列信息中的字段名称
	 */
	public List<Object> exportHandle(String parameters) {
		log.debug("导出数据的业务查询参数:" + parameters);

		// 这里我们前端传来的参数格式是json object
		JSONObject jo = JSONObject.fromString(parameters);
		String strVal = jo.getString("strVal");

		// 查询数据并返回
		return this.businessDao.getData(strVal, 1000);
	}

	/**
	 * 业务自行处理数据导出，返回一个数据导出任务。该任务必须设置导出的数据结果和文件名称。
	 * 
	 * @param parameter
	 *            业务数据查询条件
	 */
	public ExportTask doBusExport(String parameters) throws Exception {
		log.debug("导出数据的业务查询参数:" + parameters);

		// 这里我们前端传来的参数格式是json object
		JSONObject jo = JSONObject.fromString(parameters);
		String strVal = jo.getString("strVal");

		// 查询数据
		List<Object> datas = this.businessDao.getData(strVal, 1000);

		// 使用SXSSFWorkbook处理海量数据
		Workbook wb = new SXSSFWorkbook();
		Sheet st = wb.createSheet("演示数据导出结果");
		for (Object obj : datas) {
			DemoBusinessBean bean = (DemoBusinessBean) obj;
			Row r0 = st.createRow(0);
			r0.createCell(0).setCellValue("string");
			r0.createCell(1).setCellValue("bool");
			r0.createCell(2).setCellValue("date");
			r0.createCell(3).setCellValue("int");
			r0.createCell(4).setCellValue("long");
			r0.createCell(5).setCellValue("flaot");

			Row r1 = st.createRow(1);
			r1.createCell(0).setCellValue(bean.getStr());
			r1.createCell(1).setCellValue(bean.isBool());
			r1.createCell(2).setCellValue(bean.getDate());
			r1.createCell(3).setCellValue(bean.getIntVal());
			r1.createCell(4).setCellValue(bean.getLongVal());
			r1.createCell(5).setCellValue(bean.getFloatVal());
		}

		// 构造一个数据导出对象，将excelworkbook和文件名称返回给平台组件，由组件将文件下载给客户端
		ExportTask et = new ExcelExportTask("演示数据导出.xlsx", wb);

		return et;
	}

	/* (non-Javadoc)
	 * @see com.khnt.pub.expimp.service.ExpImpHandler#importHandle(java.util.List, java.lang.String, java.lang.String)
	 */

	@Override
	public void importHandle(List<Object> arg0, String arg1, String arg2, String arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}
}