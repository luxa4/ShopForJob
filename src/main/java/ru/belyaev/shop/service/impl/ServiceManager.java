package ru.belyaev.shop.service.impl;


import org.apache.commons.dbcp2.BasicDataSource;
import ru.belyaev.shop.service.OrderService;
import ru.belyaev.shop.service.ProductService;

import javax.servlet.ServletContext;

import java.io.IOException;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

public class ServiceManager {
    public static ServiceManager getInstance(ServletContext context) {
        ServiceManager instance = (ServiceManager) context.getAttribute("SERVICE_MANAGER");
        if (instance == null) {
            instance = new ServiceManager(context);
            context.setAttribute("SERVICE_MANAGER", instance);
        }
        return instance;
    }

    public ProductService getProductService() {
        return productService;
    }
    public OrderService getOrderService() {
        return orderService;
    }
    public String getApplicationProperty(String key) {
        return applicationProperties.getProperty(key);
    }
    public void close()  {

        try {
            dataSource.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private Properties applicationProperties = new Properties();
    private BasicDataSource dataSource;
    private final ProductService productService;
    private final OrderService orderService;
    private ServiceManager(ServletContext context) {
        loadApplicationProperties();
        dataSource = createDataSource();
        productService = new ProductServiceImpl(dataSource);
        orderService = new OrderServiceImpl();
    }
    private BasicDataSource createDataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDefaultAutoCommit(false);
        dataSource.setRollbackOnReturn(true);
        dataSource.setDriverClassName(getApplicationProperty("db.driver"));
        dataSource.setUrl(getApplicationProperty("db.url"));
        dataSource.setUsername(getApplicationProperty("db.username"));
        dataSource.setPassword(getApplicationProperty("db.password"));
        dataSource.setInitialSize(Integer.parseInt(getApplicationProperty("db.pool.initSize")));
        dataSource.setMaxTotal(Integer.parseInt(getApplicationProperty("db.pool.maxSize")));
        return dataSource;
    }

    private void loadApplicationProperties() {
        try (InputStream in = ServiceManager.class.getClassLoader().getResourceAsStream("application.properties")) {
            System.out.println("Загружены ApplicationProperties");
            applicationProperties.load(in);

        } catch (IOException e) {
            System.out.println("Не загрузились ApplicationProperties");
            throw new RuntimeException(e);
        }
    }












}
