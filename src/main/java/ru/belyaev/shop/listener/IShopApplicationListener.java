package ru.belyaev.shop.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.belyaev.shop.service.impl.ServiceManager;




@WebListener
public class IShopApplicationListener  implements ServletContextListener {
	protected static final Logger LOGGER = LoggerFactory.getLogger(IShopApplicationListener.class);


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
            ServiceManager serviceManager = context.getBean("serviceManager", ServiceManager.class);
            sce.getServletContext().setAttribute("CATEGORY_LIST", serviceManager.productService.listAllCategories());
            sce.getServletContext().setAttribute("PRODUCER_LIST", serviceManager.productService.listAllProducers());
            context.close();
        } catch (RuntimeException e) {
            LOGGER.error("Web application 'ishop' init failed: "+e.getMessage(), e);
            throw e;
        }
        LOGGER.info("Web application 'ishop' initialized");
    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOGGER.info("Web application 'ishop' destroyed");
    }
}
