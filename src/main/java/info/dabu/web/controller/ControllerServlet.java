package info.dabu.web.controller;

import info.dabu.domain.Customer;
import info.dabu.service.Bussinessservice;
import info.dabu.service.BussinessserviceImpl;
import info.dabu.util.WebUtil;
import info.dabu.web.bean.CustomerFormBean;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

/**
 * Created by AlexY on 2016/7/27.
 */
public class ControllerServlet extends HttpServlet {


    private Bussinessservice s = new BussinessserviceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        req.setCharacterEncoding("UTF-8");
        resp.setContentType("UTF-8");


        String op = req.getParameter("op");

        if ("showAllCustomers".equals(op)) {

            showAllCustomers(req, resp);

        } else if ("addCustomer".equals(op)) {
            System.out.println("name:" + req.getParameter("name"));


            addCustomer(req, resp);
        } else if ("editCustomer".equals(op)) {

            editCustomer(req, resp);

        } else if ("editCustomerUI".equals(op)) {

            editCustomerUI(req, resp);
        } else if ("delOneCustomer".equals(op)) {
            delOneCustomer(req, resp);
        } else if ("delMultiCustomer".equals(op)) {


            delMultiCustomer(req, resp);
        }


    }


    //    删除多个客户
    private void delMultiCustomer(HttpServletRequest req, HttpServletResponse resp) {

        String[] ids = req.getParameterValues("id");

        if (null != ids && ids.length > 0) {

            for (String id : ids) {

                s.deleteCustomer(id);
            }
        }


        try {
            resp.sendRedirect(req.getContextPath()+"/");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    //    删除一个指定的客户
    private void delOneCustomer(HttpServletRequest req, HttpServletResponse resp) {

        String customerId = req.getParameter("customerId");

        s.deleteCustomer(customerId);


        try {
            resp.sendRedirect(req.getContextPath() + "/");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //根据id查询客户信息，显示信息
    private void editCustomerUI(HttpServletRequest request, HttpServletResponse resp) {

        String customerId = request.getParameter("customerId");
        Customer c = s.findOne(customerId);

        if (null != c) {
            System.out.println("editCustomer is not null");
        } else {
            System.out.println("editCustomer is null");

        }

        request.setAttribute("c", c);
        try {
            request.getRequestDispatcher("/editCustomer.jsp").forward(request, resp);


        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void editCustomer(HttpServletRequest request, HttpServletResponse resp) {
        try {

            System.out.println("editCustomer: id = " + request.getParameter("id"));
            //把数据封装到FormBean中
            CustomerFormBean formBean = WebUtil.fillBean(request, CustomerFormBean.class);
            //验证与回显
            if (!formBean.validate()) {
                //回显：略
            }
            //填充模型：formBean---->domain:JavaBean
            Customer c = new Customer();
            ConvertUtils.register(new DateLocaleConverter(), Date.class);
            BeanUtils.copyProperties(c, formBean);

            //单独处理爱好
            String[] hobbies = formBean.getHobbies();
            if (hobbies != null && hobbies.length > 0) {
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < hobbies.length; i++) {
                    if (i > 0) {
                        sb.append(",");
                    }
                    sb.append(hobbies[i]);
                }
                c.setHobby(sb.toString());
            }
            s.updateCustomer(c);

            //Redirect after Post
            resp.sendRedirect(request.getContextPath() + "/");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void addCustomer(HttpServletRequest req, HttpServletResponse resp) {

//        把用户输入的数据封装到FormBean
        CustomerFormBean formBean = WebUtil.fillBean(req, CustomerFormBean.class);


//        回显错误信息
        if (null != formBean) {

            if (!formBean.validate()) {

//            将FormBean中的错误列表保存到request对象中，用于来jsp页面提示用户错误信息
//              省略
            }

        } else {


            System.out.println("formBean is null");
        }


//       使用Common-util库 将FormBean中的属性拷贝到Customer对象中，
        try {

            Customer c = new Customer();
//            注册Date的转换器，否则没法将String类型的日期转换为Date类型的日期
            ConvertUtils.register(new DateLocaleConverter(), Date.class);
            BeanUtils.copyProperties(c, formBean);


//            checkbox类型的，如果用户什么都不选，则后台收不到checkbox的任何信息，字段都没有
//              所以需要单独处理
            String[] hobbies = formBean.getHobbies();
            if (hobbies != null && hobbies.length > 0) {
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < hobbies.length; i++) {
                    if (i > 0) {
                        sb.append(",");
                    }
                    sb.append(hobbies[i]);
                }
                c.setHobby(sb.toString());
            }


            s.saveCustomer(c);


//            使用重定向，避免表单重复提交。提交成功后，返回默认主页（这里就是index.jsp）
            resp.sendRedirect(req.getContextPath()+ "/");


        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doGet(req, resp);


    }

    private void showAllCustomers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Customer> cs = s.findAll();


        req.setAttribute("cs", cs);

        System.out.println("cs=" + cs.size());

//        转发到jsp页面，显示查询的数据
        req.getRequestDispatcher("/listCustomer.jsp").forward(req, resp);


    }
}
