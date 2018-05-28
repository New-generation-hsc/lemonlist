package com.store.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.PreparedStatement;

public class JdbcUtils {
	private static final String dbUsername = "root";
	private static final String dbPassword = "";
	private static final String driver = "com.mysql.jdbc.Driver";
	//这里的userlogin是使用到的数据库，根据需求可以换成不同的数据库
	private static final String URL = "jdbc:mysql://localhost:3306/lemonlist?useSSL=false";
	
	private Connection connection;
	private PreparedStatement pstmt;
	private ResultSet resultSet;
	public JdbcUtils(){
		try{
			//连接数据库
			Class.forName(driver);//???
			System.out.println("数据库连接成功");
		}catch(Exception e){
			System.out.println("数据库连接失败");
		}
	}
	/*
	 * 登录数据库*/
	public Connection getConnection(){
		try{
			connection = DriverManager.getConnection(URL,dbUsername,dbPassword);
		}catch(SQLException e){
			e.printStackTrace();//?
		}
		return connection;
		}
	
	/*
	 * 对数据库数据进行增加，删除，更新
	 * sql 是传入的数据库操作语句
	 * param 是操作语句对应位置的参数
	 * */
	public boolean updateByPreparedStatement(String sql,List<Object> param) throws SQLException{
		boolean flag = false;
		int result = -1;
		//通过connection传入sql语句
		pstmt = connection.prepareStatement(sql);
		//通过循环传入参数
		if(param != null && !param.isEmpty()){
			for(int i=0,index=1;i<param.size();i++,index++){

				pstmt.setObject(index, param.get(i));
			}
		}
		result = pstmt.executeUpdate();//更新几条就返回几条数据
		System.out.println(result);
		flag = result>0? true:false;
		
		return flag;
		}
	/*
	 * 查询单个记录
	 * 返回该条记录的所有对应属性和属性值
	 * */
	public Map<String,Object> findSimpleResult(String sql,List<Object> param) throws SQLException{
		Map<String,Object> map= new HashMap<String,Object>();
		pstmt = connection.prepareStatement(sql);
		if(param != null && !param.isEmpty()){
			for(int i=0,index=1;i<param.size();i++,index++){
				//System.out.println(param.get(i));
				pstmt.setObject(index, param.get(i));
			}
		}
		resultSet = pstmt.executeQuery();//返回查询结果
		//???
		ResultSetMetaData metaData = resultSet.getMetaData();
		int col_len = metaData.getColumnCount();
		System.out.println(col_len);
		while(resultSet.next()){
			for(int i=0;i<col_len;i++){
				//获取指定列的名称
				String cols_name = metaData.getColumnName(i+1);
				//获取该列所对应的对象
				Object cols_value = resultSet.getObject(cols_name);
				if(cols_value == null){
					cols_value = "";
				}
//				System.out.println(cols_name);
//				System.out.println(cols_value);
				map.put(cols_name,cols_value);
			}
		}
		return map;
		
	}
	/*
	 * 查询多条记录*/
	public List<Map<String,Object>> findMoreResults(String sql,List<Object> param) throws SQLException{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();  
		pstmt = connection.prepareStatement(sql);
		System.out.println("!!!!!!!!!!!!!!");
		System.out.println(param);
		if(param != null && !param.isEmpty()){
			for(int i=0,index=1;i<param.size();i++,index++){
				pstmt.setObject(index, param.get(i));
			}
		}
        resultSet = pstmt.executeQuery();  
        ResultSetMetaData metaData = resultSet.getMetaData();  
        int cols_len = metaData.getColumnCount();  
        while(resultSet.next()){  
            Map<String, Object> map = new HashMap<String, Object>();  
            for(int i=0; i<cols_len; i++){  
                String cols_name = metaData.getColumnName(i+1);  
                Object cols_value = resultSet.getObject(cols_name);  
                if(cols_value == null){  
                    cols_value = "";  
                }  
                map.put(cols_name, cols_value);  
            }  
            list.add(map);  
        }  
  
        return list;  
	}
	//关闭连接
	public void releaseConn(){
		if(resultSet != null){
			try{
				resultSet.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
}
