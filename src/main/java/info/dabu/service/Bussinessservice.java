package info.dabu.service;

import info.dabu.domain.Customer;

import java.util.List;

/**
 * Created by AlexY on 2016/7/26.
 */
public interface Bussinessservice {
    /**
     * 查询所有的客户信息
     * @return List
     */
    List<Customer> findAll();

    /**
     * 添加一个新客户
     * @param c
     */
    void saveCustomer(Customer c);

    /**
     * 删除客户信息
     * @param customerId
     */
    void deleteCustomer(String customerId);

    /**
     * 查询一个客户信息
     * @param customerId
     * @return
     */
    Customer findOne(String customerId);


    /**
     * 更新客户信息
     * @param c
     */
    void updateCustomer(Customer c);

}
