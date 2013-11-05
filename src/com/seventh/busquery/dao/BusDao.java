package com.seventh.busquery.dao;

import java.util.HashMap;
import java.util.List;

public interface BusDao {
	/**
	 * 换乘查询
	 * @param beginstop 起点
	 * @param endstop 终点
	 * @return 
	 */
	public Object searchChange(String beginstop,String endstop);
	/**
	 * 站点查询
	 * @param stop 站点
	 * @return
	 */
	public List<HashMap<String, String>> searchStop(String stop);
	/**
	 * 路线查询
	 * @param route 路线
	 * @return
	 */
	public List<HashMap<String, String>> searchRoute(String route);
}
