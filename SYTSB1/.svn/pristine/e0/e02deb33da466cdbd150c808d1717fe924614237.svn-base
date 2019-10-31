package com.scts.payment.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.StringUtil;
import com.scts.payment.bean.ReportBorrow;
import com.scts.payment.bean.ReportBorrowDTO;


/**
 * 外借记录数据处理对象
 * @ClassName ReportBorrowDao
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-05-12 下午05:22:00
 */
@Repository("reportBorrowDao")
public class ReportBorrowDao extends EntityDaoImpl<ReportBorrow> {

	/**
	 * 根据业务ID查询业务外借记录
	 * @param info_id -- 业务id
	 * @return 
	 * @author GaoYa
	 * @date 2014-05-13 上午09:05:00
	 */
	@SuppressWarnings("unchecked")
    public ReportBorrow queryByInspectionInfoID(String info_id) {
		ReportBorrow reportBorrow = null;
    	try {
    		if (StringUtil.isNotEmpty(info_id)) {
    			String hql = "from ReportBorrow p where p.fk_inspection_info_id like :fk_inspection_info_id and p.borrow_status!='99'";
    			List list = this.createQuery(hql).setParameter("fk_inspection_info_id", "%"+info_id+"%").list();
    			if(!list.isEmpty()){
    				return (ReportBorrow)list.get(0);
    			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return reportBorrow;
    }
	
	/**
	 * 借票导出（承压）
	 * 
	 * @param check_unit_id
	 *            检验部门id
	 * @param borrow_start_date
	 *            外借开始日期
	 * @param borrow_end_date
	 *            外借结束日期
	 * @return
	 * @author GaoYa
	 * @date 2015-12-12 上午12:45:00
	 */
	@SuppressWarnings("unchecked")
	public List<ReportBorrowDTO> exportBorrowCY(String check_unit_id,
			String borrow_start_date, String borrow_end_date) {
		List<ReportBorrowDTO> list = new ArrayList<ReportBorrowDTO>();
		try {
			String sql = "select invoice_no,borrow_date,com_name,sum(unpay_amount),borrow_name,check_department"
					+ " from (select nvl(b.invoice_no, ' ') as invoice_no,to_char(b.borrow_date, 'yyyy-MM-dd') as borrow_date,b.com_name,"
					+ " b.unpay_amount,b.borrow_name,o.another_name as check_department "
					+ " from TZSB_REPORT_BORROW b,SYS_ORG o"
					+ " where b.borrow_type='1' and b.device_type='CY'" ;		
			if (StringUtil.isNotEmpty(check_unit_id.trim())) {
				sql += " and b.check_dep_id = '" + check_unit_id.trim() + "'  ";
			}
			sql += " and b.check_dep_id = o.id ";
			if (StringUtil.isNotEmpty(borrow_start_date)) {
				sql += " and b.borrow_date >= to_date('" + borrow_start_date
						+ "','yyyy-MM-dd')";
			}
			if (StringUtil.isNotEmpty(borrow_end_date)) {
				sql += " and b.borrow_date <= to_date('" + borrow_end_date
						+ "','yyyy-MM-dd')";
			}
			sql += " and b.borrow_status='2') temp";
			sql += " group by invoice_no,borrow_date, com_name, borrow_name,check_department order by invoice_no";
			List rslist = this.createSQLQuery(sql).list();
			if (!rslist.isEmpty()) {
				for (int i = 0; i < rslist.size(); i++) {
					Object[] objArr = rslist.toArray();
					Object[] obj = (Object[]) objArr[i];
					ReportBorrowDTO reportBorrowDTO = new ReportBorrowDTO();
					reportBorrowDTO.setInvoice_no(obj[0]!=null?String.valueOf(obj[0]):"");
					reportBorrowDTO.setBorrow_date(obj[1]!=null?String.valueOf(obj[1]):"");
					reportBorrowDTO.setCom_name(obj[2]!=null?String.valueOf(obj[2]):"");
					reportBorrowDTO.setUnpay_amount(obj[3]!=null?String.valueOf(obj[3]):"");
					reportBorrowDTO.setBorrow_name(obj[4]!=null?String.valueOf(obj[4]):"");
					reportBorrowDTO.setCheck_department(obj[5]!=null?String.valueOf(obj[5]):"");
					list.add(reportBorrowDTO);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 根据借票业务id获取借票时使用的发票号
	 * 
	 * @param id --
	 *            收费业务id
	 * @return
	 * @author GaoYa
	 * @date 2017-02-28 下午14:32:00
	 */
	@SuppressWarnings("unchecked")
	public String queryInvoice_no(String id) {
		String invoice_no = "";
		String sql = "select t.invoice_no from TZSB_REPORT_BORROW t where t.id = ?";
		List list = this.createSQLQuery(sql, id).list();
		if (!list.isEmpty()) {
			invoice_no = list.get(0) + "";
		}
		return invoice_no;
	}
	
	/**
	 * 根据外借业务id获取外借总金额
	 * 
	 * @param id --
	 *            外借业务id
	 * @return
	 * @author GaoYa
	 * @date 2017-02-28 下午15:03:00
	 */
	@SuppressWarnings("unchecked")
	public double queryMoney(String id) {
		double money = 0;
		String sql = "select t.unpay_amount from TZSB_REPORT_BORROW t where t.id = ?";
		List list = this.createSQLQuery(sql, id).list();
		if (!list.isEmpty()) {
			money = list.get(0)!=null?Double.parseDouble(list.get(0) + ""):0;
		}
		return money;
	}
	public ReportBorrow getReportBorrowByInvoice_no(String invoice_no){
		String hql=" from ReportBorrow where invoice_no=?";
		List<ReportBorrow> list=this.createQuery(hql  , invoice_no).list();
		if(list!=null&& list.size()!=0){
			return list.get(0);
		}else{
		return null;
		}
		
	}
}
