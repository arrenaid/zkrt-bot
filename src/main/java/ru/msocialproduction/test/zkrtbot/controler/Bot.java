package ru.msocialproduction.test.zkrtbot.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
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

import java.util.List;

//  name: Zkkrt_bot
//  token: 1778061482:AAF7uzlekCKzlPc_b7R_mU6GJYNLQnwQx8I
@Component
@ConfigurationProperties(prefix = "telegrambot")
public class Bot extends TelegramLongPollingBot {

    private String BOT_NAME= "Zkkrt_bot";
    private String BOT_TOKEN="1778061482:AAG1THZdL14CiAweHWo8TkiJip_zs81VFEQ";
    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }
    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Autowired
    private UsersService userService;
    @Autowired
    private MessagesService messagesService;
    @Autowired
    private BackorderService backorderService;

    @Override
    public void onUpdateReceived(Update update) {
        if(isMessageWithText(update)) {
            SendMessage sendMessage = new SendMessage();
//        if(update.getMessage() == null) return;
            String messageText = update.getMessage().getText();
            switch (messageText) {
                case "/start":
                    Users users = userService.findUserByChatId(Math.toIntExact(update.getMessage().getChatId()));
                    if (users == null) {
                        users = new Users(Math.toIntExact(update.getMessage().getChatId()));//344962348
                        //users.setName(update.getMessage().getForwardSenderName());
                        try {
                            userService.createUser(users);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        sendMessage.setText("Hi");
                        sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
                        Messages msg = new Messages(users,messageText,sendMessage.getText());
                        messagesService.createMessages(msg);
                        try {
                            execute(sendMessage);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        sendMessage.setText("Hi");
                        sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
                        Messages msg = new Messages(users,messageText,sendMessage.getText());
                        messagesService.createMessages(msg);
                        try {
                            execute(sendMessage);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case "GET":
                    try {
                        List<DomainEntity> domainEntity = backorderService.getDomains();
                        backorderService.addDomains(domainEntity);
                        sendMessage.setText(domainEntity.get(0).getDomainName());
                        sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
                        Messages msg = new Messages(userService.findUserByChatId(Math.toIntExact(
                                update.getMessage().getChatId())),messageText,sendMessage.getText());
                        messagesService.createMessages(msg);
                        try {
                            execute(sendMessage);
                        } catch (TelegramApiException e1) {
                            e1.printStackTrace();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    Messages msg = new Messages(userService.findUserByChatId(Math.toIntExact(update.getMessage().getChatId()))
                            ,update.getMessage().getText(),update.getMessage().getText());
                    try {
                        messagesService.createMessages(msg);
                    } catch (Exception e) {
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
        }
        else {
            List<Users> users = userService.findAll();
            SendMessage sendMessage = new SendMessage();
            sendMessage.setText("Bot Started");
            users.forEach(users1 -> {
                sendMessage.setChatId(String.valueOf(users1.getChatId()));
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private boolean isMessageWithText(Update update) {
        return !update.hasCallbackQuery() && update.hasMessage() && update.getMessage().hasText();
    }
}
