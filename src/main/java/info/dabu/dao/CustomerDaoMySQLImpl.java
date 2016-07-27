package info.dabu.dao;

import info.dabu.Exception.DaoException;
import info.dabu.domain.Customer;
import info.dabu.util.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlexY on 2016/7/26.
 */
public class CustomerDaoMySQLImpl implements CustomerDao {


    @Override
    public List<Customer> findAll() {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Customer> cs = null;


        try {
            conn = JdbcUtil.getConnection();
            stmt = conn.prepareStatement("select id,name,gender,birthday,phone,email,hobby,type,description from customer");
            cs = new ArrayList<>();
            rs = stmt.executeQuery();


            while (rs.next()) {

                Customer c = new Customer();
                c.setId(rs.getString("id"));
                c.setName(rs.getString("name"));
                c.setGender(rs.getString("gender"));
                c.setBirthday(rs.getDate("birthday"));
                c.setPhone(rs.getString("phone"));
                c.setEmail(rs.getString("email"));
                c.setHobby(rs.getString("hobby"));
                c.setType(rs.getString("type"));
                c.setDescription(rs.getString("description"));
                cs.add(c);

            }


            return cs;


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.release(rs, stmt, conn);
        }

        return cs;


    }

    @Override
    public void save(Customer c) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;



        try {
            conn = JdbcUtil.getConnection();
            stmt = conn.prepareStatement("INSERT INTO customer(id,name,gender,birthday,phone,email,hobby,type,description) values (?,?,?,?,?,?,?,?,?)");

            stmt.setString(1, c.getId());
            stmt.setString(2, c.getName());
            stmt.setString(3, c.getGender());

            stmt.setDate(4, new Date(c.getBirthday().getTime())); //这里需要java.sql.Date而不是java.util.Date
            stmt.setString(5, c.getPhone());
            stmt.setString(6, c.getEmail());
            stmt.setString(7, c.getHobby());
            stmt.setString(8, c.getType());
            stmt.setString(9, c.getDescription());


//            执行更新语句
            stmt.executeUpdate();


        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            JdbcUtil.release(rs, stmt, conn);
        }


    }

    @Override
    public void delete(String customerId) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;


        try {
            conn = JdbcUtil.getConnection();
            stmt = conn.prepareStatement("DELETE   from customer WHERE id=?");


              stmt.setString(1,customerId);

//            执行更新语句
            stmt.executeUpdate();


        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            JdbcUtil.release(rs, stmt, conn);
        }


    }

    @Override
    public Customer findOne(String customerId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;


        try {
            conn = JdbcUtil.getConnection();
            stmt = conn.prepareStatement("select id,name,gender,birthday,phone,email,hobby,type,description from customer where id=?");
              stmt.setString(1,customerId);

//            执行更新语句
            rs = stmt.executeQuery();


            if ( rs.next()){

                Customer c = new Customer();
                c.setId(rs.getString("id"));
                c.setName(rs.getString("name"));
                c.setGender(rs.getString("gender"));
                c.setBirthday(rs.getDate("birthday"));
                c.setPhone(rs.getString("phone"));
                c.setEmail(rs.getString("email"));
                c.setHobby(rs.getString("hobby"));
                c.setType(rs.getString("type"));
                c.setDescription(rs.getString("description"));

                return c;


            }


        } catch (Exception e) {

               throw new DaoException(e);

        } finally {
            JdbcUtil.release(rs, stmt, conn);
        }


        return null;

    }

    @Override
    public void update(Customer c) {


        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            conn = JdbcUtil.getConnection();
            stmt = conn.prepareStatement("update customer set name=?,gender=?,birthday=?,phone=?,email=?,hobby=?,type=?,description=? where id=?");

            stmt.setString(1, c.getName());
            stmt.setString(2, c.getGender());
            stmt.setDate(3, new java.sql.Date(c.getBirthday().getTime()));
            stmt.setString(4, c.getPhone());
            stmt.setString(5, c.getEmail());
            stmt.setString(6, c.getHobby());
            stmt.setString(7, c.getType());
            stmt.setString(8, c.getDescription());
            stmt.setString(9, c.getId());

            stmt.executeUpdate();
        }catch(Exception e){
            throw new DaoException(e);
        }finally{
            JdbcUtil.release(rs, stmt, conn);
        }

    }
}
