package com.stock.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Service;

import service.BaseHbaseService;
import util.CommonUtil;

import com.stock.service.HbaseManageService;
import com.stock.vo.HbaseQuery;
import com.stock.vo.StockAlertVo;
import com.stock.vo.StockRealTimeData;
@Service("hbaseManageService")
public class HbaseManageServiceImpl  extends BaseHbaseService implements HbaseManageService{

	@Override
	public List queryHbaseByConditionList(Map condition, Object originObject,
			String tableTmp) throws Exception {
		HbaseQuery query = new HbaseQuery();
		query.setType("1");
		return this.queryByConditionList(query, originObject, tableTmp);
    
	}

	@Override
	public List<String> getColum(Object originObject) {
		
		Field[] childFields = originObject.getClass().getDeclaredFields();
        Field[] superFields = originObject.getClass().getSuperclass().getDeclaredFields();
        List<Field> fields = new ArrayList<Field>();
        List<String> returnList = new ArrayList<String>();
        for(Field field:childFields){
        	fields.add(field);
        }
        for(Field field:superFields){
        	fields.add(field);
        }
        
        for (int i = 0; i < fields.size(); i++) {
        	Field field = fields.get(i);
        	field.setAccessible(true);
            String filedName = field.getName();
            returnList.add(filedName);
        }
		
		return returnList;
	}

	@Override
	public List<List<String>> changeObject2String(
			List<StockRealTimeData> tmplist,Object originObject) {
		
		List<List<String>> returnList = new ArrayList<List<String>>();
		Field[] childFields = originObject.getClass().getDeclaredFields();
        Field[] superFields = originObject.getClass().getSuperclass().getDeclaredFields();
        List<Field> fields = new ArrayList<Field>();
        for(Field field:childFields){
        	fields.add(field);
        }
        for(Field field:superFields){
        	fields.add(field);
        }
        
		for(StockRealTimeData rt : tmplist){
			List<String> tmp1 = new ArrayList<String>();
			 for (int i = 0; i < fields.size(); i++) {
		        	Field field = fields.get(i);
		        	field.setAccessible(true);
		            String filedName = field.getName();
		            try {
						String value = CommonUtil.obj2string(CommonUtil.getter(originObject,filedName));
						tmp1.add(value);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
			 returnList.add(tmp1);
			
		}
		return returnList;
	}

	@Override
	public void saveAlert(StockAlertVo alertVo) throws Exception {
		
		alertVo.setRowkey(alertVo.getCode());
		alertVo.setStatus("2");
		this.hbaseInsertByObject(alertVo,"test_alert", "base_cf");
		
	}

	@Override
	public List<StockAlertVo> getStockAlertList(Map<String, String> paraMap) throws Exception {
		
		HbaseQuery query = new HbaseQuery();
		query.setType("1");
		
		return this.queryByConditionList(query, new StockAlertVo(), "test_alert");
	}
	

}
