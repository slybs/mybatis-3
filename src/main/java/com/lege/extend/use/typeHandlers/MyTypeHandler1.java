package com.lege.extend.use.typeHandlers;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * @Author 了个
 * @date 2020/1/8 12:37
 */
public class MyTypeHandler1 implements TypeHandler<List<String>> {
    /**
     * 插入操作
     * @param preparedStatement
     * @param i
     * @param strings
     * @param jdbcType
     * @throws SQLException
     */
    public void setParameter(PreparedStatement preparedStatement, int i, List<String> strings, JdbcType jdbcType) throws SQLException {
        StringBuffer sb = new StringBuffer();
        for(String s : strings){
            sb.append(s).append(",");
        }
        preparedStatement.setString(i,sb.toString().substring(0,sb.toString().length() - 1));
    }

    public List<String> getResult(ResultSet resultSet, String s) throws SQLException {
        String[] arr = resultSet.getString(s).split(",");
        return Arrays.asList(arr);
    }

    public List<String> getResult(ResultSet resultSet, int i) throws SQLException {
        String[] arr = resultSet.getString(i).split(",");
        return Arrays.asList(arr);
    }

    public List<String> getResult(CallableStatement callableStatement, int i) throws SQLException {
        String[] arr = callableStatement.getString(i).split(",");
        return Arrays.asList(arr);
    }
}
