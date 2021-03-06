package a5_DAO;

import a0_JDBCUtil.JDBCUtil;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;

/** (Base)DAO : Data Access Object
 *  抽象类，不需要实例化 abstract
 *  封针对数据库的通用操作，DB table实现类继承获得通用方法
 */

public abstract class BaseDAO {
    // Update
    public int update(Connection c,String sql, Object... args) {
        PreparedStatement ps = null;
        try {
            // 1.Precompile
            ps = c.prepareStatement(sql);
            // 2.SetValue
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1,args[i]);
            }
            // 3. 执行
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(null,ps);
        }
        return 0;
    }
    // 单行查询
    public <T> T queryInstance(Connection c, Class<T> clazz, String sql, Object ... args) {
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            ps = c.prepareStatement(sql);
            for (int i = 0;i < args.length;i++){
                ps.setObject(i+1,args[i]);
            }
            resultSet = ps.executeQuery();
            ResultSetMetaData metaData = ps.getMetaData();

            if (resultSet.next()){
                T t = clazz.getDeclaredConstructor().newInstance();

                int columnCount = metaData.getColumnCount();
                for (int i = 0; i < columnCount; i++) {
                    String columnLabel = metaData.getColumnLabel(i+1);
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,resultSet.getObject(i+1));
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(null,ps,resultSet);
        }
        return null;
    }
    // 多行查询
    public <T> ArrayList<T> queryForList(Connection c, Class<T> clazz, String sql, Object ... args) {
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            ps = c.prepareStatement(sql);
            for (int i = 0;i < args.length;i++){
                ps.setObject(i+1,args[i]);
            }
            resultSet = ps.executeQuery();
            ResultSetMetaData metaData = ps.getMetaData();
            ArrayList<T> list = new ArrayList<>();

            while (resultSet.next()){
                T t = clazz.getDeclaredConstructor().newInstance();

                int columnCount = metaData.getColumnCount();
                for (int i = 0; i < columnCount; i++) {
                    String columnLabel = metaData.getColumnLabel(i+1);
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,resultSet.getObject(i+1));
                }
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(null,ps,resultSet);
        }
        return null;
    }
    // 标量查询
    public <E> E getValue(Connection c,String sql,Object... args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            rs = ps.executeQuery();
            if (rs.next()){
                return (E)rs.getObject(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(null,ps,rs);
        }
        return null;
    }
}
