package ru.belyaev.shop.service.impl;


import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.belyaev.shop.model.SocialAccount;
import ru.belyaev.shop.service.OrderService;
import ru.belyaev.shop.service.ProductService;
import ru.belyaev.shop.service.SocialService;

import javax.servlet.ServletContext;

import java.io.IOException;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

@Service
public class ServiceManager {

    @Autowired
    public SocialService socialService;
    @Autowired
    public ProductService productService;
    @Autowired
    public OrderService orderService;

    public ServiceManager() {

    }
}












