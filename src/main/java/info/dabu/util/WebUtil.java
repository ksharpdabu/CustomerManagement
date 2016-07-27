package info.dabu.util;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by AlexY on 2016/7/27.
 */
public class WebUtil {



    public static <T> T fillBean(HttpServletRequest req, Class<T> clazz) {

        T bean = null;


        try {
            bean = clazz.newInstance();

            System.out.println("req.getParameterMap()="+req.getParameterMap().size());

            BeanUtils.populate(bean,req.getParameterMap());
            return bean;



        } catch (Exception e) {
            throw new RuntimeException(e);
        }



    }
}
