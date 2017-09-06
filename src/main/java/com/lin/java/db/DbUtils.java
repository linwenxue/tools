package com.lin.java.db;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * 数据库操作类，注意关闭JDBC连接对象
 */

public class DbUtils {

	private static Logger logger = LoggerFactory.getLogger(DbUtils.class);

	private Connection connection = null;
	private Statement statement = null;
	private ResultSet result = null;

	public DbUtils() {
		String url = "jdbc:mysql://192.168.101.66:3306/iamssimple";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager.getConnection(url, "root", "mysql");
			logger.info("初始化连接【{}】成功！", url);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("初始化连接【{}】失败！", url);
		}
	}

	/**
	 * 执行查询
	 * 
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public ResultSet executeQuery(String sql) {
		try {
			this.statement = connection.createStatement();
			result = statement.executeQuery(sql);
			logger.info("执行sql语句：【{}】 成功!", sql);
		} catch (SQLException ex) {
			logger.error("执行sql语句：" + sql + "失败!异常信息：【{}】", ex.getMessage());
		}
		return result;
	}

	/**
	 * 执行更新
	 * 
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public int executeUpdate(String sql) {
		int countNum = 0;
		try {
			this.statement = connection.createStatement();
			countNum = statement.executeUpdate(sql);
			logger.info("执行sql语句：【{}】 成功!", sql);
		} catch (SQLException ex) {
			logger.error("执行sql语句：【{}】失败!异常信息：【{}】", sql, ex.getMessage());
		}
		return countNum;
	}

	/**
	 * 删除
	 * 
	 * @param sql
	 * @return 删除是否成功
	 */
	public boolean executeDelete(String sql) {
		return executeUpdate(sql) == 0 ? false : true;
	}

	/**
	 * 插入
	 * 
	 * @param sql
	 * @return
	 */
	public boolean executeInsert(String sql) {
		return executeDelete(sql);
	}
	
	/**
	 * 获取序列的 值
	 * @param seqName 序列的名字
	 * @return
	 * @throws SQLException
	 */
	public int getSeqNextValue(String seqName) throws SQLException{
		int seqNextValue=0;
		if(StringUtils.isBlank(seqName)){
			return seqNextValue;
		}
		String sql="SELECT "+seqName+".nextval FROM DUAL";
		ResultSet rs=executeQuery(sql);
		while(rs.next()){
			seqNextValue=rs.getInt("NEXTVAL");
		}	
		return seqNextValue;
	}
	

	public Connection getConnection() {
		return connection;
	}
	
	

	/**
	 * 释放连接对象
	 */
	public void releaseJdbc() {
		try {
			org.apache.commons.dbutils.DbUtils.close(connection);
			org.apache.commons.dbutils.DbUtils.close(result);
			org.apache.commons.dbutils.DbUtils.close(statement);
			logger.info("释放连接JDBC对象成功");
		} catch (SQLException ex) {
			logger.error("初始化连接JDBC失败，异常信息：【{}】", ex.getMessage());
		}
	}
	
	
	public static void main(String[] args) {
		DbUtils db = new DbUtils();
		System.out.println(db.getConnection());
		db.releaseJdbc();
	}

}
