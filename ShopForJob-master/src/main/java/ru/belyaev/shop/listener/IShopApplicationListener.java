package ru.belyaev.shop.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.belyaev.shop.service.impl.ServiceManager;




@WebListener
public class IShopApplicationListener implements ServletContextListener {
	protected static final Logger LOGGER = LoggerFactory.getLogger(IShopApplicationListener.class);
	private ServiceManager serviceManager;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			serviceManager = ServiceManager.getInstance(sce.getServletContext());
			sce.getServletContext().setAttribute("CATEGORY_LIST", serviceManager.getProductService().listAllCategories());
			sce.getServletContext().setAttribute("PRODUCER_LIST", serviceManager.getProductService().listAllProducers());
		} catch (RuntimeException e) {
			LOGGER.error("Web application 'ishop' init failed: "+e.getMessage(), e);
			throw e;
		}
		LOGGER.info("Web application 'ishop' initialized");


	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		serviceManager.close();
		LOGGER.info("Web application 'ishop' destroyed");
	}
}
