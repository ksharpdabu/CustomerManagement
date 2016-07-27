package info.dabu.dao;

import info.dabu.domain.Customer;

import java.util.List;

/**
 * Created by AlexY on 2016/7/26.
 */
public interface CustomerDao {


    List<Customer> findAll();

    void save(Customer c);

    void delete(String customerId);

    Customer findOne(String customerId);

    void update(Customer c);
}
