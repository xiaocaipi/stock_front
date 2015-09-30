package com.stock.ctrl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import util.CommonUtil;

@Controller
@RequestMapping("/hiveManage")
public class HiveManageController extends MultiActionController {


	@RequestMapping(params = "method=hiveQuery")
	public ModelAndView hiveQuery(HttpServletRequest request,
			HttpServletResponse response) {
		String querysql = request.getParameter("querysql");
		String type = request.getParameter("type");
		String db_name = request.getParameter("db_name");
		String isudf = request.getParameter("isudf");
		String hiveServerUrl ="";
		String hivePrincipal ="";
		if (querysql == null || querysql.equals("")) {
			querysql = "select * from uts_act_user";
		}
		if(StringUtils.isEmpty("db_name")){
			db_name ="hdw";
		}
		if(StringUtils.isEmpty(type)){
			type ="1";
		}
		if(type.equals("1")){
			hiveServerUrl = "hadoop3:10000";
			hivePrincipal = "hive/hadoop3@hadoop3";
		}else if(type.equals("2")){
			hiveServerUrl = "cdh-namenode-002:10000";
			hivePrincipal = "hive/cdh-namenode-002.lufax.storage@LUFAX.STORAGE";
		}
		
		
		try {
			Configuration conf = CommonUtil.getConf(type);
			List<String> columList = new ArrayList<String>();
			List<List<String>> returnlist = new ArrayList<List<String>>();

			Class.forName("org.apache.hive.jdbc.HiveDriver");
			Connection con = null;
			con = DriverManager
					.getConnection(
							"jdbc:hive2://"+hiveServerUrl+"/"+db_name+";principal="+hivePrincipal+"",
							"", "");
				
			Statement stmt = con.createStatement();
			if (isudf != null && isudf.equals("1")) {
				String initialsql = "";
			}
			String createsql = querysql;
			querysql = querysql + "  limit 1000";
			ResultSet rs = stmt.executeQuery(querysql);
			ResultSetMetaData md = rs.getMetaData();
			int columnCount = md.getColumnCount();

			for (int i = 1; i <= columnCount; i++) {
				columList.add(md.getColumnName(i));
			}
			while (rs.next()) {
				List<String> list = new ArrayList<String>();
				for (int i = 1; i <= columnCount; i++) {
					System.out.print(String.valueOf(rs.getObject(i)) + "\t");
					list.add(String.valueOf(rs.getObject(i)));
				}
				returnlist.add(list);
			}
			request.setAttribute("columList", columList);
			request.setAttribute("returnlist", returnlist);
		} catch (Exception e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return new ModelAndView("stock/hiveManage");
	}

	@RequestMapping(params = "method=hiveQueryInt")
	public ModelAndView hiveQueryInt(HttpServletRequest request,
			HttpServletResponse response) {
		String querysql = request.getParameter("querysql");
		String type = request.getParameter("type");
		try {

		} catch (Exception e) {

		}
		return new ModelAndView("stock/hiveManage");
	}

}
