package com.ics.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ics.service.ModelService;
import com.ics.service.TemplateService;
import com.ics.util.utils;

@Controller
@RequestMapping("/model")
public class ProduceallController extends BaseController{
	private static Logger log = LogManager.getLogger(ProduceallController.class.getName());

	@Autowired
	private ModelService model;
	@Autowired
	private TemplateService template;
    @Value("${ics.config.pakagedir}")
	String pakagedirdir;
    @Value("${ics.config.mybatdir}")
	String mybatdir;
    
	@RequestMapping(value = "/produceall", method = RequestMethod.GET)
	@ResponseBody
    public Object RunModel() throws FileNotFoundException {
		//导入写文件类
        FileOutputStream out = null;
        FileOutputStream outSTr = null;
        BufferedOutputStream Buff = null;
        FileWriter fw = null;
		//取得modelName
		List modellist = model.getModel(paramMap);
		//生成接口XXXmapper.java
		for(int i=0;i<modellist.size();i++){
			String name = (String)((HashMap)modellist.get(i)).get("name");
			String tablename = (String)((HashMap)modellist.get(i)).get("tablename");
			//System.out.println("test:"+);
			name = utils.toUpperFristChar(name);

	        try {
	        	paramMap.put("type", "1");
				String strPathmapper = pakagedirdir+"\\mapper\\"+name+"Mapper.java";
	        	String file =(String)((HashMap)template.getTemplate(paramMap).get(0)).get("template");

	        	//template.getTemplate(paramMap);
	        	//log.info(file.replace("@ModelMapper", name+"Mapper"));
	        	file = file.replace("@ModelMapper", name+"Mapper");
	        	file = file.replace("@addModel", ("add"+name));
	        	file =file.replace("@delModel", ("del"+name));
	        	file = file.replace("@updateModel", ("update"+name));
	        	file = file.replace("@getModel", ("get"+name));
	        	outSTr = new FileOutputStream(new File(strPathmapper));
	            Buff = new BufferedOutputStream(outSTr);
				Buff.write(file.getBytes());
			    Buff.flush();
			    Buff.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        //生成xxxService
	        try {
	        	paramMap.put("type", "2");
				String strPathservice = pakagedirdir+"\\service\\"+name+"Service.java";
	        	String file =(String)((HashMap)template.getTemplate(paramMap).get(0)).get("template");

	        	//template.getTemplate(paramMap);
	        	//log.info(file.replace("@ModelMapper", name+"Mapper"));
	        	file = file.replaceAll("@ModelMapper", name+"Mapper");
	        	file = file.replaceAll("@model", name.toLowerCase());
	        	file = file.replaceAll("@ModelService", name+"Service");
	        	file =file.replaceAll("@addModel", ("add"+name));
	        	file =file.replaceAll("@delModel", ("del"+name));
	        	file = file.replaceAll("@updateModel", ("update"+name));
	        	file = file.replaceAll("@getModel", ("get"+name));
	        	outSTr = new FileOutputStream(new File(strPathservice));
	            Buff = new BufferedOutputStream(outSTr);
				Buff.write(file.getBytes());
			    Buff.flush();
			    Buff.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        //生成xml文件
	        try {
	        	paramMap.put("type", "3");
	        	paramMap.put("tablename", tablename);
				String strPathservice = mybatdir+"\\"+name+"Mapper.xml";
	        	String file =(String)((HashMap)template.getTemplate(paramMap).get(0)).get("template");
	        	List columnslist = template.getTableColumn(paramMap);
	        	String columnstr = "";
	        	String columnstrvalue = "";
	        	String columnstrupdate = "";
	        	for(int j=0 ;j<columnslist.size();j++){
	        		columnstr = columnstr+((HashMap)columnslist.get(j)).get("COLUMN_NAME")+",";
	        		columnstrvalue = columnstrvalue+"#{"+((HashMap)columnslist.get(j)).get("COLUMN_NAME")+"},";
	        		columnstrupdate = columnstrupdate+((HashMap)columnslist.get(j)).get("COLUMN_NAME")+"=#{"+((HashMap)columnslist.get(j)).get("COLUMN_NAME")+"},";
	        		//log.info("columnslist:"+columnslist.get(j));
	        	}
	        	log.info("columnslist1:"+tablename+":"+columnslist);
	        	columnstr = columnstr.substring(0,columnstr.length()-1);
	        	columnstrvalue = columnstrvalue.substring(0,columnstrvalue.length()-1);
	        	columnstrupdate = columnstrupdate.substring(0,columnstrupdate.length()-1);
	        	
	        	file = file.replaceAll("@insertsqlcolumn",columnstr);
	        	file = file.replaceAll("@insertsqlvalue",columnstrvalue);
	        	file = file.replaceAll("@updatesql",columnstrupdate);
	        	
	        	
	        	//template.getTemplate(paramMap);
	        	//log.info(file.replace("@ModelMapper", name+"Mapper"));
	        	file = file.replaceAll("@ModelMapper", name+"Mapper");
	        	file = file.replaceAll("@tablename", tablename);
	        	file = file.replaceAll("@ModelService", name+"Service");
	        	file =file.replaceAll("@addModel", ("add"+name));
	        	file =file.replaceAll("@delModel", ("del"+name));
	        	file = file.replaceAll("@updateModel", ("update"+name));
	        	file = file.replaceAll("@getModel", ("get"+name));
	        	outSTr = new FileOutputStream(new File(strPathservice));
	            Buff = new BufferedOutputStream(outSTr);
				Buff.write(file.getBytes());
			    Buff.flush();
			    Buff.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        //生成controller文件
	        try {
	        	paramMap.put("type", "4");
	        	paramMap.put("tablename", tablename);
	        	String strPathscontroller = pakagedirdir+"\\controller\\"+name+"Controller.java";
	        	String file =(String)((HashMap)template.getTemplate(paramMap).get(0)).get("template");
//	        	List columnslist = template.getTableColumn(paramMap);
//	        	String columnstr = "";
//	        	String columnstrvalue = "";
//	        	String columnstrupdate = "";
//	        	for(int j=0 ;j<columnslist.size();j++){
//	        		columnstr = columnstr+((HashMap)columnslist.get(j)).get("COLUMN_NAME")+",";
//	        		columnstrvalue = columnstrvalue+"#{"+((HashMap)columnslist.get(j)).get("COLUMN_NAME")+"},";
//	        		columnstrupdate = columnstrupdate+((HashMap)columnslist.get(j)).get("COLUMN_NAME")+"=#{"+((HashMap)columnslist.get(j)).get("COLUMN_NAME")+"},";
//	        		//log.info("columnslist:"+columnslist.get(j));
//	        	}
//	        	log.info("columnslist1:"+columnstrupdate);
//	        	columnstr = columnstr.substring(0,columnstr.length()-1);
//	        	columnstrvalue = columnstrvalue.substring(0,columnstrvalue.length()-1);
//	        	columnstrupdate = columnstrupdate.substring(0,columnstrupdate.length()-1);
	        	
//	        	file = file.replaceAll("@insertsqlcolumn",columnstr);
//	        	file = file.replaceAll("@insertsqlvalue",columnstrvalue);
//	        	file = file.replaceAll("@updatesql",columnstrupdate);
	        	
	        	
	        	//template.getTemplate(paramMap);
	        	//log.info(file.replace("@ModelMapper", name+"Mapper"));
	        	file = file.replaceAll("@ModelMapper", name+"Mapper");
	        	file = file.replaceAll("@ModelController", name+"Controller");
	        	file = file.replaceAll("@model", name.toLowerCase());
	        	file = file.replaceAll("@tablename", tablename);
	        	file = file.replaceAll("@ModelService", name+"Service");
	        	file =file.replaceAll("@addModel", ("add"+name));
	        	file =file.replaceAll("@delModel", ("del"+name));
	        	file = file.replaceAll("@updateModel", ("update"+name));
	        	file = file.replaceAll("@getModel", ("get"+name));
	        	outSTr = new FileOutputStream(new File(strPathscontroller));
	            Buff = new BufferedOutputStream(outSTr);
				Buff.write(file.getBytes());
			    Buff.flush();
			    Buff.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//生成XXXService
//		String strPath = dir+"\\controller\\test1.java"; 
//        //经过测试：ufferedOutputStream执行耗时:1,1，1 毫秒
//		outSTr = new FileOutputStream(new File(dir+"\\controller\\test1.java"));
//        Buff = new BufferedOutputStream(outSTr);
//        long begin0 = System.currentTimeMillis();
//        //for (int i = 0; i < count; i++) {
//        
//        String str="@RequestMapping(value=/test,"+"method=RequestMethod.GET) \r"+
//              "public User getUserById(@PathVariable String name){ \r"+
//              "User user=new User(); \r"+
//              "user.setName("+"hello world"+"); \r"+
//              "return user; \r"+
//         " } \r";
//        try {
//			Buff.write(str.getBytes());
//	        Buff.flush();
//	        Buff.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

//        System.out.println(dir);
//        for(int i=0;i<model.getDept(paramMap).size();i++){
//        	String code =(String) ((HashMap)model.getDept(paramMap).get(i)).get("code");
//            Buff.write(code.getBytes());
//            Buff.flush();
//            Buff.close();
//        }
        //log.info("paraMAP:"+dir);
        log.info(model.updateModel(paramMap));
        return null;
    }
    // 1.生成XXXMapper.java
	// 2.生成xxxService.java
	// 3.生成XXXController.java
	// 4.生成xxxMapper.xml
}
