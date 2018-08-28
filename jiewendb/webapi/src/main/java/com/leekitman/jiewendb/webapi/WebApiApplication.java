package com.leekitman.jiewendb.webapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author LeeKITMAN
 */
@SpringBootApplication
@Slf4j
public class WebApiApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext cac = SpringApplication.run(WebApiApplication.class, args);
        String[] activeProfiles = cac.getEnvironment().getActiveProfiles();
        for (String profile : activeProfiles) {
            log.info("profile={}", profile);
        }
    }

}
