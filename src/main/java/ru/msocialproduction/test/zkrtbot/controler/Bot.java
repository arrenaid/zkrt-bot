package ru.msocialproduction.test.zkrtbot.controler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.msocialproduction.test.zkrtbot.ZkrtBotApplication;
import ru.msocialproduction.test.zkrtbot.entity.Messages;
import ru.msocialproduction.test.zkrtbot.entity.Users;
import ru.msocialproduction.test.zkrtbot.service.MessagesService;
import ru.msocialproduction.test.zkrtbot.service.UsersService;

@Component
public class Bot extends TelegramLongPollingBot {
    @Value("${telegrambot.botName}")
    private String botName;
    @Override
    public String getBotUsername() {
        return botName;
    }
    @Value("${telegrambot.botToken}")
    private String botToken;
    @Override
    public String getBotToken() {
        return botToken;
    }

    public static final Logger logger = LoggerFactory.getLogger(ZkrtBotApplication.class);

    @Autowired
    private UsersService usersService;
    @Autowired
    private MessagesService messagesService;
    @Autowired
    private DomainsScheduler scheduler;
    @Autowired
    private Delegate delegate;
    @Autowired
    private StartReply startReply;

    @Override
    public void onUpdateReceived(Update update) {
        if(isMessageWithText(update)) {
            String messageText = update.getMessage().getText();
            switch (messageText) {
                case "/start":
                    startReply.startReply(update.getMessage());
                    break;
                case "/get":
                    scheduler.getDom(update.getMessage());
                    break;
                default:
                    delegate.reply(update.getMessage());
                    break;
            }
        }
    }
    private boolean isMessageWithText(Update update) {
        return !update.hasCallbackQuery() && update.hasMessage() && update.getMessage().hasText();
    }

    public void sentAnswer(Users user,String text, String answer){
        writeMessage(new Messages(user,
                text,answer));
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(answer);
        sendMessage.setChatId(user.getChatId().toString());
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            logger.error("Bot.java - error execute : ", e);
            e.printStackTrace();
        }
    }
    public void writeMessage(Messages message){
        try{
            messagesService.createMessages(message);
        } catch (Exception e) {
            Bot.logger.error(e.toString());
            e.printStackTrace();
        }
    }
}
