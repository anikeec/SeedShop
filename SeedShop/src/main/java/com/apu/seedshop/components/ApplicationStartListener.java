
package com.apu.seedshop.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 *
 * @author al
 */
@Component
public class ApplicationStartListener implements ApplicationListener<ContextRefreshedEvent>{
private static final Logger logger =  LoggerFactory.getLogger(ApplicationStartListener.class);


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
       logger.warn("======================== Application prepared event fired! ===========================");
    }
    
}
