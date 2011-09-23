package ar.kennedy.is2011.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ar.kennedy.is2011.constants.Constants;

/**
 * @author mlabarinas
 */
public class SpringContext {
	
	private static ApplicationContext context = new ClassPathXmlApplicationContext(Constants.SPRING_CONFIG_XML_PATH);
	
	private SpringContext() { }
	
	public static Object getBean(String key) {
		return context.getBean(key);
	}
	
}