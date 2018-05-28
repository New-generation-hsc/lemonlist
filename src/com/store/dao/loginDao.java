package com.store.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.store.dbutil.JdbcUtils;
import com.store.service.loginService;

public class loginDao implements loginService {

	private JdbcUtils jdbcUtils = new JdbcUtils();
	@Override
	public boolean addTask(List<Object> param) throws SQLException {
		// TODO Auto-generated methodt.stub
		boolean flag = false;
		System.out.println(param);
		jdbcUtils.getConnection();
		String sql = "insert into task (taskname,date) values (?,?)";
		try{
			flag  = jdbcUtils.updateByPreparedStatement(sql, param);
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			jdbcUtils.releaseConn();
		}
		return flag;
	}
    public List<Map<String,Object>> transforList(List<Object> param) throws SQLException{
    	jdbcUtils.getConnection();
    	List<Map<String, Object>> list = null ;
    	String sql = "select * from task where taskname = ?";
    	try{
    		list = jdbcUtils.findMoreResults(sql, param);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	finally{
    		jdbcUtils.releaseConn();
    	}
    	return list;
    }
	
}
