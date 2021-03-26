package ru.msocialproduction.test.zkrtbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.msocialproduction.test.zkrtbot.controler.Bot;

@SpringBootApplication
public class ZkrtBotApplication {

	public static void main(String[] args) {
		//ApiContextInitializer.Init();
		try {
			TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
			botsApi.registerBot(new Bot());
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
		//SpringApplication.run(ZkrtBotApplication.class, args);
	}

}