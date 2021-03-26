package ru.msocialproduction.test.zkrtbot.controler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

//  name: Zkkrt_bot
//  token: 1778061482:AAF7uzlekCKzlPc_b7R_mU6GJYNLQnwQx8I
@Component
public class Bot extends TelegramLongPollingBot {
//    @Value("{$bot.name}")
//    private String botUserName;
//    @Value("{$bot.token}")
//    private String botToken;

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
        if(update.getMessage().getText().equals("/start")){
            sendMessage.setText("Hi");
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        if(update.getMessage().hasText()){
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
            sendMessage.setText(update.getMessage().getText());
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

}
