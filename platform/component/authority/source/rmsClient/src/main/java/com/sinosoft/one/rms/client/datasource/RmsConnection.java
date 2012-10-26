package com.sinosoft.one.rms.client.datasource;

import ins.framework.utils.StringUtils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.sinosoft.one.rms.client.DataRuleStringCreat;
import com.sinosoft.one.rms.client.EnvContext;

public class RmsConnection implements Connection {
	
	
	private DataRuleStringCreat dataRuleStringCreat;
	private Connection realConnection;
	
	public RmsConnection(Connection connection,DataRuleStringCreat dataRuleStringCreat) {
		this.dataRuleStringCreat=dataRuleStringCreat;
		this.realConnection = connection;
	}
	
	public Statement createStatement() throws SQLException {
		return realConnection.createStatement();
	}

	public PreparedStatement prepareStatement(String sql) throws SQLException {
		realDataRule(sql);
		return realConnection.prepareStatement(sql);
	}

	public CallableStatement prepareCall(String sql) throws SQLException {

		return realConnection.prepareCall(sql);
	}

	public String nativeSQL(String sql) throws SQLException {

		return realConnection.nativeSQL(sql);
	}

	public void setAutoCommit(boolean autoCommit) throws SQLException {

		realConnection.setAutoCommit(autoCommit);
	}

	public boolean getAutoCommit() throws SQLException {

		return realConnection.getAutoCommit();
	}

	public void commit() throws SQLException {
		realConnection.commit();

	}

	public void rollback() throws SQLException {
		realConnection.rollback();

	}

	public void close() throws SQLException {
		realConnection.close();

	}

	public boolean isClosed() throws SQLException {
		return realConnection.isClosed();
	}

	public DatabaseMetaData getMetaData() throws SQLException {
		return realConnection.getMetaData();
	}

	public void setReadOnly(boolean readOnly) throws SQLException {
		realConnection.setReadOnly(readOnly);

	}

	public boolean isReadOnly() throws SQLException {
		return realConnection.isReadOnly();
	}

	public void setCatalog(String catalog) throws SQLException {
		realConnection.setCatalog(catalog);

	}

	public String getCatalog() throws SQLException {
		return realConnection.getCatalog();
	}

	public void setTransactionIsolation(int level) throws SQLException {
		realConnection.setTransactionIsolation(level);

	}

	public int getTransactionIsolation() throws SQLException {
		return realConnection.getTransactionIsolation();
	}

	public SQLWarning getWarnings() throws SQLException {
		return realConnection.getWarnings();
	}

	public void clearWarnings() throws SQLException {
		realConnection.clearWarnings();
	}

	public Statement createStatement(int resultSetType, int resultSetConcurrency)
			throws SQLException {
		return realConnection.createStatement(resultSetType, resultSetConcurrency);
	}

	public PreparedStatement prepareStatement(String sql, int resultSetType,
			int resultSetConcurrency) throws SQLException {
		return realConnection.prepareStatement(sql, resultSetType, resultSetConcurrency);
	}

	public CallableStatement prepareCall(String sql, int resultSetType,
			int resultSetConcurrency) throws SQLException {
		return realConnection.prepareCall(sql, resultSetType, resultSetConcurrency);
	}

	public Map<String, Class<?>> getTypeMap() throws SQLException {
		return realConnection.getTypeMap();
	}

	public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
		realConnection.setTypeMap(map);
	}

	public void setHoldability(int holdability) throws SQLException {
		realConnection.setHoldability(holdability);
	}

	public int getHoldability() throws SQLException {
		return realConnection.getHoldability();
	}

	public Savepoint setSavepoint() throws SQLException {
		return realConnection.setSavepoint();
	}

	public Savepoint setSavepoint(String name) throws SQLException {
		return realConnection.setSavepoint(name);
	}

	public void rollback(Savepoint savepoint) throws SQLException {
		realConnection.rollback(savepoint);
	}

	public void releaseSavepoint(Savepoint savepoint) throws SQLException {
		realConnection.releaseSavepoint(savepoint);
	}

	public Statement createStatement(int resultSetType,
			int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {
		return realConnection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	public PreparedStatement prepareStatement(String sql, int resultSetType,
			int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {
		System.out.println(sql);
		return realConnection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	public CallableStatement prepareCall(String sql, int resultSetType,
			int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {
		return realConnection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys)
			throws SQLException {
		return realConnection.prepareStatement(sql);
	}

	public PreparedStatement prepareStatement(String sql, int[] columnIndexes)
			throws SQLException {
		return realConnection.prepareStatement(sql, columnIndexes);
	}

	public PreparedStatement prepareStatement(String sql, String[] columnNames)
			throws SQLException {
		return realConnection.prepareStatement(sql, columnNames);
	}

	public DataRuleStringCreat getDataRuleStringCreat() {
		return dataRuleStringCreat;
	}

	public void setDataRuleStringCreat(DataRuleStringCreat dataRuleStringCreat) {
		this.dataRuleStringCreat = dataRuleStringCreat;
	}

	String realDataRule(String sql){
		String s =dataRuleStringCreat.editSqlQueryRule(sql);
		return s;
	}
	
	
}
