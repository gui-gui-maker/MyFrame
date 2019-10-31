package com.edu.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edu.bean.SourcePlan;
import com.edu.jdbc.repository.impl.SourcePlanJDao;
import com.edu.util.dbf.DBFReader;
import com.edu.util.dbf.DBFReader.Field;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@Controller
@RequestMapping("lyjh")
public class SourcePlanWeb {
	private static String UPLOADED_FOLDER = "E://temp//";
	
	@Autowired
	SourcePlanJDao baseJhRepository;
	
	@GetMapping("/uploadLyJh")
	public String uploadLyJh() {
		
		return "uploadLyJh";
	}
	
	@PostMapping("/uploadDbf") 
	public String singleFileUpload(@RequestParam("file") MultipartFile file,RedirectAttributes redirectAttributes) {
	    if (file.isEmpty()) {
	    	redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
	        return "redirect:uploadStatus";
	    }
	    try {
	        // Get the file and save it somewhere
	        byte[] bytes = file.getBytes();
	        // UPLOADED_FOLDER 文件本地存储地址
	        Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
	        Files.write(path, bytes);

	        redirectAttributes.addFlashAttribute("message",
	                "You successfully uploaded '" + file.getOriginalFilename() + "'");
	        
	        redirectAttributes.addFlashAttribute("filePath",UPLOADED_FOLDER + file.getOriginalFilename());

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return "redirect:saveDbfToDb";
	}
	

	@RequestMapping(value="saveDbfToDb")
	@ResponseBody
	public HashMap<String, Object> uploadDbf(@ModelAttribute("filePath") String filePath) {
		HashMap<String, Object> wapper = new HashMap<>();
		File file = new File(filePath);
		DBFReader dbfreader_SH = null;
		try {
			//
			dbfreader_SH = new DBFReader(file);
			Map<String, Field> map = new HashMap<String, Field>();

			Field[] fields = dbfreader_SH.getFields();
			for (int i = 0; i < fields.length; i++) {
				String columnName = fields[i].getName().toLowerCase();
				map.put(columnName, fields[i]);
				SourcePlan.class.getDeclaredField(columnName);
			}

			List<DBFReader.Record> list_sh = dbfreader_SH.loadRecords();
			List<SourcePlan> jhs = new ArrayList<SourcePlan>();
			for (DBFReader.Record reader : list_sh) {
				SourcePlan jh = new SourcePlan();
				jh.setJhid(reader.getString(map.get("jhid")));
				jh.setNf(reader.getString(map.get("nf")));
				jh.setYxdm(reader.getString(map.get("yxdm")));
				jh.setYxmc(reader.getString(map.get("yxmc")));
				jh.setYxdh(reader.getString(map.get("yxdh")));
				jh.setYxdhmc(reader.getString(map.get("yxdhmc")));
				jh.setZszydm(reader.getString(map.get("zszydm")));
				jh.setZszymc(reader.getString(map.get("zszymc")));
				jh.setZylbdm(reader.getString(map.get("zylbdm")));
				jh.setZylbmc(reader.getString(map.get("zylbmc")));
				jh.setBhzy(reader.getString(map.get("bhzy")));
				jh.setBhzygs(reader.getString(map.get("bhzygs")));
				jh.setCcdm(reader.getString(map.get("ccdm")));
				jh.setCcmc(reader.getString(map.get("ccmc")));
				jh.setSbzydh(reader.getString(map.get("sbzydh")));
				jh.setXbzydh(reader.getString(map.get("xbzydh")));
				jh.setZkfx(reader.getString(map.get("zkfx")));
				jh.setXzdm(reader.getString(map.get("xzdm")));
				jh.setXzmc(reader.getString(map.get("xzmc")));
				jh.setSfbz(reader.getString(map.get("sfbz")));
				jh.setBxdd(reader.getString(map.get("bxdd")));
				jh.setBxddssmc(reader.getString(map.get("bxddssmc")));
				jh.setBxdddjsmc(reader.getString(map.get("bxdddjsmc")));
				jh.setBxddqxmc(reader.getString(map.get("bxddqxmc")));
				jh.setBxddxxdz(reader.getString(map.get("bxddxxdz")));
				jh.setBxddbb(reader.getString(map.get("bxddbb")));
				jh.setSfks(reader.getString(map.get("sfks")));
				jh.setWyyz(reader.getString(map.get("wyyz")));
				jh.setYxbmdm(reader.getString(map.get("yxbmdm")));
				jh.setYxbmmc(reader.getString(map.get("yxbmmc")));
				jh.setKldm(reader.getString(map.get("kldm")));
				jh.setKlmc(reader.getString(map.get("klmc")));
				jh.setJhxzdm(reader.getString(map.get("jhxzdm")));
				jh.setJhxzmc(reader.getString(map.get("jhxzmc")));
				jh.setJhlbdm(reader.getString(map.get("jhlbdm")));
				jh.setJhlbmc(reader.getString(map.get("jhlbmc")));
				jh.setSyssdm(reader.getString(map.get("syssdm")));
				jh.setSyssmc(reader.getString(map.get("syssmc")));
				jh.setPcdm(reader.getString(map.get("pcdm")));
				jh.setPcmc(reader.getString(map.get("pcmc")));
				jh.setZklxdm(reader.getString(map.get("zklxdm")));
				jh.setZklxmc(reader.getString(map.get("zklxmc")));
				jh.setKskmyq(reader.getString(map.get("kskmyq")));
				jh.setKskmyqzw(reader.getString(map.get("kskmyqzw")));
				jh.setXkkmbhzy(reader.getString(map.get("xkkmbhzy")));
				jh.setSkyq(reader.getString(map.get("skyq")));
				jh.setZybz(reader.getString(map.get("zybz")));
				jh.setDz1(reader.getString(map.get("dz1")));
				jh.setDz2(reader.getString(map.get("dz2")));
				jh.setDz3(reader.getString(map.get("dz3")));
				jh.setDz4(reader.getString(map.get("dz4")));
				jh.setDz5(reader.getString(map.get("dz5")));
				jh.setDz6(reader.getString(map.get("dz6")));
				jh.setDz7(reader.getString(map.get("dz7")));
				jh.setDz8(reader.getString(map.get("dz8")));
				jh.setDz9(reader.getString(map.get("dz9")));
				jh.setDz10(reader.getString(map.get("dz10")));
				jh.setZsjhs(reader.getInt(map.get("zsjhs")));
				jh.setXfzy(reader.getString(map.get("xfzy")));
				jh.setZyxztj(reader.getString(map.get("zyxztj")));

				jhs.add(jh);
				
			}
			baseJhRepository.batchCreateArticle(jhs);
			wapper.put("success", true);
			wapper.put("data", jhs.size());
		} catch (Exception e) {
			e.printStackTrace();
			wapper.put("success", false);
			wapper.put("msg", e.getMessage());
		}finally {
			
			try {
				dbfreader_SH.close();
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				dbfreader_SH = null;
			}
		}
		return wapper;
	}
	
	@PostMapping("jhAll")
	public HashMap<String,Object> jhAll(int size,int page) {
		HashMap<String,Object> map =new HashMap<String,Object>();
		//map.put("data",baseJhRepository.page(size,page));
		return map;
	}
}
