package ru.msocialproduction.test.zkrtbot;

//import liquibase.pro.packaged.U;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.msocialproduction.test.zkrtbot.controler.Bot;
import ru.msocialproduction.test.zkrtbot.entity.Messages;
import ru.msocialproduction.test.zkrtbot.entity.Users;
import ru.msocialproduction.test.zkrtbot.service.MessagesService;
import ru.msocialproduction.test.zkrtbot.service.UsersService;
@EnableScheduling
@SpringBootApplication
public class ZkrtBotApplication {
//	@Autowired
//	private UsersService userService;
//	@Autowired
//	private MessagesService messagesService;

	public static void main(String[] args) {

		SpringApplication.run(ZkrtBotApplication.class, args);
	}

//	@EventListener(ApplicationReadyEvent.class)
//	private void testJpaMethods(){
//		Messages messages = new Messages();
//		messages.setAnswer("hi-1");
//		messages.setQuestion("go out");
//		messages.setUserId(1);
//		Messages messages1 = new Messages();
//		messages1.setAnswer("hi-2");
//		messages1.setQuestion("test start");
//		messages1.setUserId(2);
//
//		Users users = new Users();
//		users.setChatId(1);
//		users.setLastMessage("go out");
//		users.setName("Victor");
//		userService.createUser(users);
//
//		Users users1 = new Users();
//		users1.setChatId(2);
//		users1.setLastMessage("test start");
//		users1.setName("Vladislav");
//		userService.createUser(users1);
//		messagesService.createMessages(messages);
//		messagesService.createMessages(messages1);
//		userService.findAll().forEach(it-> System.out.println(it));
//		messagesService.findAll().forEach(it->System.out.println(it));

//		users.setAddress(address);
//		users.setEmail("someEmail@gmail.com");
//		users.setName("Smith");
//		userService.createUsers(users);
//		Users users1 = new Users();
//		users1.setName("Jon Dorian");
//		users1.setEmail("gmailEmail@gmail.com");
//		users1.setAddress(address1);
//		userService.createUsers(users1);


	//}

}
