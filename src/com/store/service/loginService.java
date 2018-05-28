package com.store.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface loginService {
	public boolean addTask(List <Object>param) throws SQLException;
	public List<Map<String,Object>> transforList(List<Object> param) throws SQLException;
}
