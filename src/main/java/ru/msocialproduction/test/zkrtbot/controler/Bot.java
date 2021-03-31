package ru.msocialproduction.test.zkrtbot.controler;

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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

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
    @Autowired
    private UsersService userService;
    @Autowired
    private MessagesService messagesService;
    @Autowired
    private BackorderService backorderService;

    @Override
    public void onUpdateReceived(Update update) {
        if(isMessageWithText(update)) {
            String messageText = update.getMessage().getText();
            switch (messageText) {
                case "/start":
                    Users users = userService.findUserByChatId(Math.toIntExact(update.getMessage().getChatId()));
                    if (users == null) {
                        users = new Users(Math.toIntExact(update.getMessage().getChatId()));
                        try {
                            userService.createUser(users);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        addMessage(users, messageText,"new user registered\nHi " +users.getName());
                    }
                    else {
                        addMessage(users, messageText,"Hi again "+users.getName());
                    }
                    break;
                case "/get":
                    try {
                        backorderService.clearDomains();
                        List<DomainEntity> domainsList = backorderService.getDomains();
                        backorderService.addDomains(domainsList);
                        LocalDateTime ldt = LocalDateTime.now();
                        String answer = DateTimeFormatter.ofPattern("yyyy-MM-dd hh-mm", Locale.ENGLISH).format(ldt)
                                + " Найдено " + domainsList.size() + " доменов \n0 - "+ domainsList.get(0).getDomainName()
                                + "\n1 - "+ domainsList.get(1).getDomainName()+ "\n2 - "+ domainsList.get(2).getDomainName()
                                + "\n3 - "+ domainsList.get(3).getDomainName()+ "\n4 - "+ domainsList.get(4).getDomainName()
                                + "\n......\n"+(domainsList.size() - 2)+" - "+ domainsList.get(domainsList.size() - 2).getDomainName()
                                + "\n"+(domainsList.size() - 1)+" - "+ domainsList.get(domainsList.size() -1).getDomainName();
                        addMessage(userService.findUserByChatId(Math.toIntExact(update.getMessage().getChatId())),
                                messageText,answer);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    addMessage(userService.findUserByChatId(Math.toIntExact(update.getMessage().getChatId())),
                            messageText,messageText + "\nOk, nice ");
                    break;
            }
        }
    }
    private boolean isMessageWithText(Update update) {
        return !update.hasCallbackQuery() && update.hasMessage() && update.getMessage().hasText();
    }
    private void sentAnswer(String chatId,String quest,String answer){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(answer);
        sendMessage.setChatId(chatId);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public void addMessage(Users user,String quest,String answer){
        Messages msg = new Messages(user,quest,answer);
        sentAnswer(user.getChatId().toString(),quest,answer);
        try {
            messagesService.createMessages(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
