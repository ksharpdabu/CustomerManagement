package info.dabu.service;

import info.dabu.dao.CustomerDao;
import info.dabu.dao.CustomerDaoMySQLImpl;
import info.dabu.domain.Customer;

import java.util.List;
import java.util.UUID;

/**
 * Created by AlexY on 2016/7/26.
 */
public class BussinessserviceImpl implements Bussinessservice {

    private CustomerDao  dao = new CustomerDaoMySQLImpl();

    @Override
    public List<Customer> findAll() {



        return dao.findAll();
    }

    @Override
    public void saveCustomer(Customer c) {
//        设置Id，因为数据库中Id字段不是自增的，所以需要使用GUID确保Id是唯一的
        c.setId(UUID.randomUUID().toString());
        dao.save(c);

    }

    @Override
    public void deleteCustomer(String customerId) {

        dao.delete(customerId);

    }

    @Override
    public Customer findOne(String customerId) {

         return dao.findOne(customerId);
    }

    @Override
    public void updateCustomer(Customer c) {

        if ( null == c.getId() || "".equals(c.getId().trim())){
            throw new IllegalArgumentException("更新的客户Id不能为空");
        }


        dao.update(c);
    }
}
