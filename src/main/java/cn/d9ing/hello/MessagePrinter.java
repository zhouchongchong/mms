/**  
 * Copyright © 2017北京鼎九信息工程研究院有限公司. All rights reserved.
 *
 * @Title: MessagePrinter.java
 * @Prject: mms
 * @Package: cn.d9ing.hello
 * @Description: TODO
 * @author:  aiying014
 * @date: 2017年2月27日 下午3:38:56
 * @version: V1.0  
 */
package cn.d9ing.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Company: </p>
 * @author zcc
 * @data 2017年2月27日 下午3:38:56
 */

@Component
public class MessagePrinter {
	final private MessageService service;

    @Autowired
    public MessagePrinter(MessageService service) {
        this.service = service;
    }

    public void printMessage() {
        System.out.println(this.service.getMessage());
    }
}
