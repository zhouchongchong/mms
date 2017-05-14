/**  
 * Copyright © 2017北京鼎九信息工程研究院有限公司. All rights reserved.
 *
 * @Title: Application.java
 * @Prject: mms
 * @Package: cn.d9ing.hello
 * @Description: TODO
 * @author:  aiying014
 * @date: 2017年2月27日 下午3:40:53
 * @version: V1.0  
 */
package cn.d9ing.hello;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author zcc
 * @data 2017年2月27日 下午3:40:53
 */
@Configuration
@ComponentScan
public class Application {
	@Bean
	MessageService mockMessageService() {
		return new MessageService() {
			public String getMessage() {
				return "Hello World!";
			}
		};
	}

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
		MessagePrinter printer = context.getBean(MessagePrinter.class);
		printer.printMessage();
	}
}
