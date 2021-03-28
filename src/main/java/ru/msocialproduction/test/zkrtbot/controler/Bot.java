package ru.msocialproduction.test.zkrtbot.controler;

import liquibase.pro.packaged.U;
import org.hibernate.jpa.internal.ExceptionMapperLegacyJpaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.msocialproduction.test.zkrtbot.entity.DomainEntity;
import ru.msocialproduction.test.zkrtbot.entity.Messages;
import ru.msocialproduction.test.zkrtbot.entity.Users;
import ru.msocialproduction.test.zkrtbot.service.BackorderService;
import ru.msocialproduction.test.zkrtbot.service.MessagesService;
import ru.msocialproduction.test.zkrtbot.service.UsersService;

import java.util.Date;
import java.util.List;

//  name: Zkkrt_bot
//  token: 1778061482:AAF7uzlekCKzlPc_b7R_mU6GJYNLQnwQx8I
@Component
public class Bot extends TelegramLongPollingBot {
//    @Value("{$bot.name}")
//    private String botUserName;
//    @Value("{$bot.token}")
//    private String botToken;

    @Autowired
    private UsersService userService;
    @Autowired
    private MessagesService messagesService;
    @Autowired
    private BackorderService backorderService;


    private final String BOT_NAME = "Zkkrt_bot";
    private final String BOT_TOKEN = "1778061482:AAF7uzlekCKzlPc_b7R_mU6GJYNLQnwQx8I";

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        //execute(new SendMessage().setChatId(String.valueOf(update.getMessage().getChatId())).setText("hi!"));
        SendMessage sendMessage = new SendMessage();
        if(update.getMessage() == null) return;
        String value = update.getMessage().getText();
        switch (value){
            case "/start":
                Users users = new Users();
                users.setChatId(Math.toIntExact(update.getMessage().getChatId()));//344962348
                users.setLastMessage(new Date());
                users.setName("name");
                //users.setName(update.getMessage().getForwardSenderName());
                try {
                    userService.createUser(users);
                }catch (Exception e){
                    e.printStackTrace();
                }


                sendMessage.setText("Hi");
                sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "GET":
                try{
                    List<DomainEntity> domainEntity = backorderService.getDomains();
                    backorderService.addDomains(domainEntity);


                    sendMessage.setText(domainEntity.get(0).getDomainName());
                    sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e1) {
                        e1.printStackTrace();
                    }

                }catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            default:
                Messages msg = new Messages();

                msg.setUser(userService.findUserByChatId(Math.toIntExact(update.getMessage().getChatId())));
                msg.setQuestion(update.getMessage().getText());
                msg.setAnswer(update.getMessage().getText());
                try{
                    messagesService.createMessages(msg);
                }catch (Exception e){
                    e.printStackTrace();
                }

                sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
                sendMessage.setText(update.getMessage().getText());


                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
        }


//        if(update.getMessage().getText().equals("/start")){
//
//        }
//        else if(update.getMessage().getText().equals("1")){
//
//        }
//        else if(update.getMessage().hasText()){
//            Messages msg = new Messages();
//            msg.setUser(userService.findUserByChatId(Math.toIntExact(update.getMessage().getChatId())));
//            msg.setQuestion(update.getMessage().getText());
//            msg.setAnswer(update.getMessage().getText());
//
//            messagesService.createMessages(msg);
//
//            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
//            sendMessage.setText(update.getMessage().getText());
//
//
//            try {
//                execute(sendMessage);
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        }

    }

}
