//package ru.belyaev.shop.listener;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import ru.belyaev.shop.service.impl.ServiceManager;
//
//
//
//
//@Component
//public class IShopApplicationListener {
//	protected static final Logger LOGGER = LoggerFactory.getLogger(IShopApplicationListener.class);
//
//
//	@Autowired
//	private ServiceManager serviceManager;
//
//
//	@ModelAttribute
//	public void addAttributes(Model model) {
//		model.addAttribute("CATEGORY_LIST", serviceManager.productService.listAllCategories());
//		model.addAttribute("PRODUCER_LIST", serviceManager.productService.listAllProducers());
//		LOGGER.info("Web application 'ishop' initialized");
//
//	}
//
//
//	@PreDestroy
//	public void contextDestroyed() {
//		LOGGER.info("Web application 'ishop' destroyed");
//	}
//}
