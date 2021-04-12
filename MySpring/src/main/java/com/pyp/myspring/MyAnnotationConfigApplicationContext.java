package com.pyp.myspring;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class MyAnnotationConfigApplicationContext {

    private Map<String,Object> ioc = new HashMap<>();
    private List<String> beanNames = new ArrayList<>();

    public MyAnnotationConfigApplicationContext(String pack){
        //遍历包，找到所有的目标类(原材料)
        Set<BeanDefinition> beanDefinitions = findBeanDefinitions(pack);
        //根据原材料动态创建bean
        createObject(beanDefinitions);
        //自动装载
        autowireObject(beanDefinitions);
    }

    public Object getBean(String beanName){
        return ioc.get(beanName);
    }

    public String[] getBeanDefinitionNames(){
        return beanNames.toArray(new String[0]);
    }

    public Integer getBeanDefinitionCount(){
        return beanNames.size();
    }

    public void autowireObject(Set<BeanDefinition> beanDefinitions){
        Iterator<BeanDefinition> iterator = beanDefinitions.iterator();
        while (iterator.hasNext()) {
            BeanDefinition beanDefinition = iterator.next();
            //获取class
            Class clazz = beanDefinition.getBeanClass();
            //获取所有的属性
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                //传入Autowire注解
                Autowired annotation = declaredField.getAnnotation(Autowired.class);
                //判断是否有Autowire注解，
                if(annotation!=null){
                    //有Autowire注解，再判断是否有Qualifier注解
                    Qualifier qualifier = declaredField.getAnnotation(Qualifier.class);
                    if(qualifier!=null){
                        //有Qualifier注解，byName
                        try {
                            String beanName = qualifier.value();
                            Object bean = getBean(beanName);
                            //获取方法名字
                            String fieldName = declaredField.getName();
                            String methodName = "set" + fieldName.substring(0,1).toUpperCase()+ fieldName.substring(1);
                            //获取方法
                            Method method = clazz.getMethod(methodName, declaredField.getType());
                            //获取Account对象
                            Object object = getBean(beanDefinition.getBeanName());
                            //将bean放入Account对象中
                            method.invoke(object,bean);
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }else{
                        //没有Qualifier注解，byType

                    }
                }
            }
        }
    }

    public void createObject(Set<BeanDefinition> beanDefinitions){
        Iterator<BeanDefinition> iterator = beanDefinitions.iterator();
        while (iterator.hasNext()) {
            BeanDefinition beanDefinition = iterator.next();
            Class clazz = beanDefinition.getBeanClass();
            String beanName = beanDefinition.getBeanName();
            try {
                //创建的对象
                Object object = clazz.getConstructor().newInstance();
                //完成属性的赋值
                Field[] declaredFields = clazz.getDeclaredFields();
                for (Field declaredField : declaredFields) {
                    //判断是否添加Value注解
                    Value valueAnnotation = declaredField.getAnnotation(Value.class);
                    if (valueAnnotation!=null){
                        String value = valueAnnotation.value();
                        //赋值
                        //获得属性的名字
                        String fieldName = declaredField.getName();
                        //拼接成set方法,首字母大写
                        String methodName = "set"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
                        //获取method对象
                        Method method = clazz.getMethod(methodName, declaredField.getType());
                        //完成数据类型转换
                        Object val = null;
                        //输出类型名
                        //System.out.println(declaredField.getType().getName());
                        //判断数据类型
                        switch (declaredField.getType().getName()){
                            case "java.lang.Integer":
                                val = Integer.parseInt(value);
                                break;
                            case "java.lang.String":
                                val = value;
                                break;
                            case "java.lang.Float":
                                val = Float.parseFloat(value);
                                break;
                        }
                        method.invoke(object,val);
                    }
                }
                //将对象存入IoC中
                ioc.put(beanName,object);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    public Set<BeanDefinition> findBeanDefinitions(String pack){
        //1.获取包下的所有类
        //3.将这些类封装成BeanDefinition，装载到集合中
        Set<Class<?>> classes = MyTools.getClasses(pack);
        Iterator<Class<?>> iterator = classes.iterator();
        //定义集合
        Set<BeanDefinition> beanDefinitions = new HashSet<>();
        while (iterator.hasNext()){
            //2.遍历这些类，找到添加注解的类
            Class<?> clazz = iterator.next();
            Component componentAnnotation = clazz.getAnnotation(Component.class);
            if(componentAnnotation != null){
                //获取Component注解的值
                String beanName = componentAnnotation.value();
                //如果值为空，类名的首字母小写作为beanName
                if("".equals(beanName)){
                    //获取类名首字母小写
                    //System.out.println(clazz.getPackage().getName()+".");     /*获得包名*/
                    String className = clazz.getName().replaceAll(clazz.getPackage().getName() + ".", "");
                    //首字母变小写
                    beanName = className.substring(0,1).toLowerCase()+className.substring(1);
                }
                //获得class和beanName,封装成beanDefinition
                //BeanDefinition beanDefinition = new BeanDefinition(beanName, clazz);
                //装载到集合
                beanDefinitions.add(new BeanDefinition(beanName,clazz));
            }
        }
        return beanDefinitions;
    }
}
