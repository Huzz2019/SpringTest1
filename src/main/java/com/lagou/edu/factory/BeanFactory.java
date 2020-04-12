package com.lagou.edu.factory;

import com.lagou.edu.customeAnnotation.CustomeService;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BeanFactory {

    /**
     * Bean对象容器
     */
    private static final Map<String, Object> beanContainer = new HashMap<String, Object>();

    /**
     * 初始化指定包下的所有@Service注解标记的类
     *
     * @param packageName 初始化包路径
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static void init(String packageName) throws InstantiationException, IllegalAccessException {
        Reflections f = new Reflections(packageName);
        Set<Class<?>> set = f.getTypesAnnotatedWith(CustomeService.class);
        for (Class<?> c : set) {
            Object bean = c.newInstance();
            CustomeService annotation = c.getAnnotation(CustomeService.class);

            beanContainer.put(annotation.value(), bean);
        }
    }

    /**
     * 根据注解名获取实例
     *
     * @param beanName 注解的名称
     * @return 对应实例
     */
    public static Object getBean(String beanName) {
        return beanContainer.get(beanName);
    }

    /**
     * 根据注解名获取指定类型的实例
     *
     * @param beanName bean名称，注解指定的value值
     * @param beanClass bean类型
     * @return 指定类型的实例
     */
    public static <T> T getBean(String beanName, Class<T> beanClass) {
        return beanClass.cast(getBean(beanName));
    }

}
