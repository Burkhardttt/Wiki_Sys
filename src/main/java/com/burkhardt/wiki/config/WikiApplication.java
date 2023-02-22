package com.burkhardt.wiki.config;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

//@ComponentScan({"com.burkhardt", "com.test"})
@ComponentScan("com.burkhardt")
@SpringBootApplication
@MapperScan("com.burkhardt.wiki.mapper")
@EnableScheduling
public class WikiApplication {
	private static final Logger LOG = LoggerFactory.getLogger(WikiApplication.class);
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(WikiApplication.class);
//		app.setBannerMode(Banner.Mode.OFF);
		Environment env = app.run(args).getEnvironment();
		LOG.info("Start successfully ~ :)");
		LOG.info("Address: \thttp://127.0.0.1:{}", env.getProperty("server.port"));
	}

}
