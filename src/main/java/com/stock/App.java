package com.stock;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.security.UserGroupInformation;

import com.stock.service.impl.HbaseManageServiceImpl;
import com.stock.vo.StockAlertVo;

import util.CommonTool;
import util.CommonUtil;

/**
 * Hello world!
 *
 */
public class App 
{
	private static SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
	

    	public static void main(String[] args) throws Exception {
    		
		}
}
