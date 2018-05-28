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
	//�����userlogin��ʹ�õ������ݿ⣬����������Ի��ɲ�ͬ�����ݿ�
	private static final String URL = "jdbc:mysql://localhost:3306/lemonlist?useSSL=false";
	
	private Connection connection;
	private PreparedStatement pstmt;
	private ResultSet resultSet;
	public JdbcUtils(){
		try{
			//�������ݿ�
			Class.forName(driver);//???
			System.out.println("���ݿ����ӳɹ�");
		}catch(Exception e){
			System.out.println("���ݿ�����ʧ��");
		}
	}
	/*
	 * ��¼���ݿ�*/
	public Connection getConnection(){
		try{
			connection = DriverManager.getConnection(URL,dbUsername,dbPassword);
		}catch(SQLException e){
			e.printStackTrace();//?
		}
		return connection;
		}
	
	/*
	 * �����ݿ����ݽ������ӣ�ɾ��������
	 * sql �Ǵ�������ݿ�������
	 * param �ǲ�������Ӧλ�õĲ���
	 * */
	public boolean updateByPreparedStatement(String sql,List<Object> param) throws SQLException{
		boolean flag = false;
		int result = -1;
		//ͨ��connection����sql���
		pstmt = connection.prepareStatement(sql);
		//ͨ��ѭ���������
		if(param != null && !param.isEmpty()){
			for(int i=0,index=1;i<param.size();i++,index++){

				pstmt.setObject(index, param.get(i));
			}
		}
		result = pstmt.executeUpdate();//���¼����ͷ��ؼ�������
		System.out.println(result);
		flag = result>0? true:false;
		
		return flag;
		}
	/*
	 * ��ѯ������¼
	 * ���ظ�����¼�����ж�Ӧ���Ժ�����ֵ
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
		resultSet = pstmt.executeQuery();//���ز�ѯ���
		//???
		ResultSetMetaData metaData = resultSet.getMetaData();
		int col_len = metaData.getColumnCount();
		System.out.println(col_len);
		while(resultSet.next()){
			for(int i=0;i<col_len;i++){
				//��ȡָ���е�����
				String cols_name = metaData.getColumnName(i+1);
				//��ȡ��������Ӧ�Ķ���
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
	 * ��ѯ������¼*/
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
	//�ر�����
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
