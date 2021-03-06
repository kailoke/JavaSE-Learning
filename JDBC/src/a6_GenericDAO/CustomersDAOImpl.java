package a6_GenericDAO;

import a0_Bean.Customers;
import a5_DAO.CustomersDAO;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * 每个DB-table实现类指定继承DAO<T>为自身处理的Bean类
 */

public class CustomersDAOImpl extends BaseGenericDAO<Customers> implements CustomersDAO {
    @Override
    public int insert(Connection conn,Customers cust) {
        String sql = "insert into customers(name,email,birth) values (?,?,?)";
        return update(conn, sql, cust.getName(), cust.getEmail(), cust.getBirth());
    }

    @Override
    public int deleteById(Connection conn, int id) {
        String sql = "delete from customers where id = ?";
        return update(conn, sql, id);
    }

    @Override
    public int update(Connection conn, Customers cust) {
        String sql = "update customers set name=?,email=?,birth=? where id=?";
        return update(conn, sql, cust.getName(), cust.getEmail(), cust.getBirth(), cust.getId());
    }

    @Override
    public Customers getCustomerById(Connection conn, int id) {
        String sql = "select id,name,email,birth from customers where id = ?";
        return queryInstance(conn, sql, id);
    }

    @Override
    public List<Customers> getAll(Connection conn) {
        String sql = "select id,name,email,birth from customers";
        return queryForList(conn, sql);
    }

    @Override
    public long getCount(Connection conn) {
        String sql = "select count(*) from customers";
        return getValue(conn, sql);
    }

    @Override
    public Date getMaxBirth(Connection conn) {
        String sql = "select max(birth) from customers";
        return getValue(conn, sql);
    }
}
