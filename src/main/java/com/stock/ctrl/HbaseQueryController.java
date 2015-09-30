package com.stock.ctrl;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;








import com.stock.service.HbaseManageService;
import com.stock.vo.StockRealTimeData;

import util.CommonUtil;
import util.JsonUtil;




@Controller
@RequestMapping("/hbaseManage")
public class HbaseQueryController extends MultiActionController{

	 @Autowired(required = false)
	  @Qualifier("hbaseManageService")
	private HbaseManageService hbaseManageService;
	 
	 
	 
	 
	 
	@RequestMapping(params = "method=hbaseManageInt")
	public ModelAndView hbaseManageInt(HttpServletRequest request,
			HttpServletResponse response) {

		HashMap<String, String> paraMap = new HashMap<String, String>();
		try {
		} catch (Exception e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return new ModelAndView("stock/hbaseManage");
	}
	
	
	@RequestMapping(params = "method=hbaseQuery")
	public ModelAndView hbaseQuery(HttpServletRequest request,
			HttpServletResponse response) {
		String tab_name = request.getParameter("tab_name");
		String type = request.getParameter("type");
		String rowkey_prefix = request.getParameter("rowkey_prefix");
		String hiveServerUrl ="";
		String hivePrincipal ="";
		StockRealTimeData rt = new StockRealTimeData();
		Map<String,Object> paraMap = new HashMap<String, Object>();
		paraMap.put("type", type);
		paraMap.put("rowkey_prefix",rowkey_prefix);
		
		
		
		try {
			List<String> columList = new ArrayList<String>();
			List<List<String>> returnlist = new ArrayList<List<String>>();
			
			columList = hbaseManageService.getColum(rt);
			List<StockRealTimeData> tmplist = hbaseManageService.queryHbaseByConditionList(paraMap, rt, tab_name);
			returnlist = hbaseManageService.changeObject2String(tmplist,rt);
			

//			while (rs.next()) {
//				List<String> list = new ArrayList<String>();
//				for (int i = 1; i <= columnCount; i++) {
//					System.out.print(String.valueOf(rs.getObject(i)) + "\t");
//					list.add(String.valueOf(rs.getObject(i)));
//				}
//				returnlist.add(list);
//			}
			request.setAttribute("columList", columList);
			request.setAttribute("returnlist", returnlist);
		} catch (Exception e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return new ModelAndView("stock/hiveManage");
	}
		
	 


}
