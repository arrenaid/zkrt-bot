package ru.msocialproduction.test.zkrtbot;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.logging.Logger;

@EnableScheduling
@SpringBootApplication
public class ZkrtBotApplication {
	public static void main(String[] args) {
		SpringApplication.run(ZkrtBotApplication.class, args);
	}
}
