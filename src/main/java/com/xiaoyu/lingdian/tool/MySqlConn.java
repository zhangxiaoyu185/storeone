package com.xiaoyu.lingdian.tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MySqlConn {
	private static Map<String, Connection> _mapMySql = null;
	public MySqlConn(int ThreadCount){ 
		if(_mapMySql == null)
			_mapMySql = new HashMap<String,Connection>();
    } 
	
	public static Connection getConn(int ThreadTag){
    	try { 
    		if(_mapMySql == null){
    			_mapMySql = new HashMap<String,Connection>();
    		}
    		String strTag = String.valueOf(ThreadTag);
    		Connection tag_conn = _mapMySql.get(strTag);
			if(tag_conn == null){      
				Class.forName("com.mysql.jdbc.Driver"); 
				String hostname = "121.41.46.75"; 
				String port = "3306"; 
				String database = "tuikebao"; 
				String url = "jdbc:mysql://" + hostname + ":" + Integer.valueOf(port) + "/"+database;
				String strUser = "lingdian";
				String strPass = "lingdianwu";
				tag_conn = DriverManager.getConnection(url, strUser, strPass); 
				_mapMySql.put(strTag, tag_conn);
			}
			return tag_conn;
    	}catch(Exception e){
    		return null;
    	}
	}
	
	//
	public static void removeConn(int ThreadTag){
		if(_mapMySql != null){
			String strKey = String.valueOf(ThreadTag);
			if(_mapMySql.get(strKey) != null) {
				try {
					_mapMySql.get(strKey).close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//_mapMySql.remove(strKey);
			_mapMySql.put(strKey, null);
		}
	}
	
	public static void free(){
		if(_mapMySql != null){
			Iterator<Connection> iter = _mapMySql.values().iterator();
			while (iter.hasNext()) {
				try{
					Connection conn = iter.next();
					conn.close();
				}catch (Exception e){
				}
			}
			_mapMySql.clear();
		}
	}
	
}