package com.neo.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.neo.model.Zydh;
import com.neo.model.ext.YxdmOnly;
import com.neo.mapper.plan.FieldDescMapper;
import com.neo.mapper.plan.YxdmOnlyMapper;
import com.neo.mapper.plan.ZydhMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZydhMapperTest {

	@Autowired
	private ZydhMapper zydhMapper;
	@Autowired
	private YxdmOnlyMapper yxdmOnlyMapper;
	@Autowired
	private FieldDescMapper fieldDescMapper;

	
	
	/*
	 * @Test public void testUpdate() throws Exception { List<Zydh> list=
	 * zydhMapper.getAll(); for (Zydh zydh : list) {
	 * System.out.println(zydh.toString()); } }
	 */
	/*
	 * @Test public void testGetAll() throws Exception { List<YxdmOnly> list=
	 * yxdmOnlyMapper.getAll(); for (YxdmOnly zydh : list) {
	 * System.out.println(zydh.toString()); } }
	 */
	/*
	 * @Test public void findYxdmByYxdm() throws Exception { List<YxdmOnly> list=
	 * yxdmOnlyMapper.findYxdmByYxdm("11937"); for (YxdmOnly zydh : list) {
	 * System.out.println(zydh.toString()); } }
	 */
	/*
	 * @Test public void findYxdhByYxdm() throws Exception { List<YxdmOnly> list=
	 * yxdmOnlyMapper.findYxdhByYxdm("10616"); for (YxdmOnly zydh : list) {
	 * System.out.println(zydh.toString()); } }
	 */
	@Test
	public void hasField() throws Exception {
			System.out.println(fieldDescMapper.hasField("YXDHMC"));
	}
	/*
	 * @Test public void testGetOnlyYxdmAndMcAll() throws Exception { List<Zydh>
	 * list= zydhMapper.getOnlyYxdmAndMcAll(); for (Zydh zydh : list) {
	 * System.out.println(zydh.toString()); } }
	 */

}