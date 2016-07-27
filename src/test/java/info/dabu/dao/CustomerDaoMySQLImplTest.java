package info.dabu.dao;

import info.dabu.domain.Customer;
import info.dabu.service.Bussinessservice;
import info.dabu.service.BussinessserviceImpl;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * Created by AlexY on 2016/7/27.
 */
public class CustomerDaoMySQLImplTest {

    private Bussinessservice s = new BussinessserviceImpl();


    @org.junit.Test
    public void findAll() throws Exception {

        List<Customer> cs = s.findAll();

        assertEquals(1, cs.size());

    }

    @org.junit.Test
    public void save() throws Exception {


        Customer c = new Customer();
        c.setName("Alex");
        c.setGender("male");
        c.setBirthday(new Date());
        c.setPhone("135258578");
        c.setEmail("546@qq.com");
        c.setHobby("吃饭,睡觉,学java");
        c.setType("VIP");
        c.setDescription("if you rich ，狗");
        s.saveCustomer(c);


    }

    @org.junit.Test
    public void delete() throws Exception {
        s.deleteCustomer("f66bc2d2-6afa-4ddb-a7b2-6b60ee4abc95");

    }

    @org.junit.Test
    public void findOne() throws Exception {
        Customer c = s.findOne("f66bc2d2-6afa-4ddb-a7b2-6b60ee4abc95");

         assertNotNull(c);

    }

    @Test
    public void update() {
        Customer c  = s.findOne("f66bc2d2-6afa-4ddb-a7b2-6b60ee4abc95");
        c.setPhone("110");

        s.updateCustomer(c);
    }




//    测试异常情况
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void update1() throws Exception {
        Customer c = new Customer();
        s.updateCustomer(c);

    }

}