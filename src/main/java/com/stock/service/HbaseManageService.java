package com.stock.service;

import java.util.List;
import java.util.Map;

import com.stock.vo.StockAlertVo;
import com.stock.vo.StockRealTimeData;

public interface HbaseManageService {
	
	public  List queryHbaseByConditionList(Map condition, Object originObject,String tableTmp) throws Exception ;

	public List<String> getColum(Object obj);

	public List<List<String>> changeObject2String(
			List<StockRealTimeData> tmplist,Object originObject);

	public void saveAlert(StockAlertVo alertVo) throws Exception;

	public List<StockAlertVo> getStockAlertList(Map<String, String> paraMap) throws Exception ;

}
