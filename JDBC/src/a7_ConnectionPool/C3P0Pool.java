package a7_ConnectionPool;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class C3P0Pool {
    // 唯一数据库连接池
    private static ComboPooledDataSource CPDS = new ComboPooledDataSource("helloc3p0");

    public static Connection getConnection() throws SQLException {
        return CPDS.getConnection();
    }
}
