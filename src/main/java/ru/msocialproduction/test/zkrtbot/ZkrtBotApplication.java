package ru.msocialproduction.test.zkrtbot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ZkrtBotApplication {
	public static final Logger logger = LoggerFactory.getLogger(ZkrtBotApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(ZkrtBotApplication.class, args);
	}
}
