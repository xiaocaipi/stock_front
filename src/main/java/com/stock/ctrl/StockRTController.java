package com.stock.ctrl;


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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import util.JsonUtil;

import com.stock.service.HbaseManageService;
import com.stock.vo.HbaseQuery;
import com.stock.vo.StockAlertVo;












@Controller
@RequestMapping("/stockrt")
public class StockRTController extends MultiActionController{

	 
	 @Autowired(required = false)
	  @Qualifier("hbaseManageService")
	private HbaseManageService hbaseManageService;
	 
	@RequestMapping(params = "method=getStockRT")
	public ModelAndView getStockRT(HttpServletRequest request,
			HttpServletResponse response) {
		
		
		Map<String, String> paraMap = new HashMap<String, String>();
		try {
			
			request.setCharacterEncoding("UTF-8");
		 	response.setContentType("text/xml;charset=utf-8"); 
			
			List<StockAlertVo> list = hbaseManageService.getStockAlertList(paraMap);
		     
		     JsonUtil.outputJson(list, response);
		
		} catch (Exception e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
		

		return null;
	}
	 
		@RequestMapping(params = "method=getStockRTInt")
		public ModelAndView getStockRTInt(HttpServletRequest request,
				HttpServletResponse response) {

			HashMap<String, String> paraMap = new HashMap<String, String>();
			try {
			} catch (Exception e) {

				e.printStackTrace();
				throw new RuntimeException(e);
			}

			return new ModelAndView("stock/stockRT");
		}
		
		@RequestMapping(params = "method=alterManager")
		public ModelAndView alterManager(HttpServletRequest request,
				HttpServletResponse response) {

			HashMap<String, String> paraMap = new HashMap<String, String>();
			try {
			} catch (Exception e) {

				e.printStackTrace();
				throw new RuntimeException(e);
			}

			return new ModelAndView("stock/alterManager");
		}
		
		@RequestMapping(params = "method=addAlert")
		public ModelAndView addAlert(HttpServletRequest request,
				HttpServletResponse response) {

			HashMap<String, String> paraMap = new HashMap<String, String>();
			try {
			} catch (Exception e) {

				e.printStackTrace();
				throw new RuntimeException(e);
			}

			return new ModelAndView("stock/addAlert");
		}
		
		@RequestMapping(params = "method=editAlert")
		public ModelAndView editAlert(HttpServletRequest request,
				HttpServletResponse response,HbaseQuery query) {
			String code = request.getParameter("code");
			try {
				query.setRowkey(code);
				StockAlertVo alertvo = hbaseManageService.getStockAlertVo(query);
				request.setAttribute("alertvo", alertvo);
			} catch (Exception e) {

				e.printStackTrace();
				throw new RuntimeException(e);
			}

			return new ModelAndView("stock/addAlert");
		}
		
		
		@RequestMapping(params = "method=saveAlert")
		public ModelAndView saveAlert(HttpServletRequest request,
				HttpServletResponse response,StockAlertVo alertVo) {

			HashMap<String, String> paraMap = new HashMap<String, String>();
			try {
				hbaseManageService.saveAlert(alertVo);
			} catch (Exception e) {

				e.printStackTrace();
				throw new RuntimeException(e);
			}

			return new ModelAndView("stock/addAlert");
		}
		
		
		
		
	 


}
