package com.burkhardt.wiki;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import javax.swing.*;

@SpringBootApplication
public class WikiApplication {
	private static final Logger LOG = LoggerFactory.getLogger(WikiApplication.class);
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(WikiApplication.class);
		Environment env = app.run(args).getEnvironment();
		LOG.info("Start successfully ~ :)");
		LOG.info("Address: \thttp://127.0.0.1:{}", env.getProperty("server.port"));
	}

}
