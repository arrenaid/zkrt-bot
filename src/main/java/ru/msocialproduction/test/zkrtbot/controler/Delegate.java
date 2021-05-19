package ru.msocialproduction.test.zkrtbot.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.msocialproduction.test.zkrtbot.entity.Messages;
import ru.msocialproduction.test.zkrtbot.service.UsersService;

@Component
public class Delegate {
    @Autowired
    UsersService usersService;
    @Autowired
    Bot bot;

    public void reply(Message message){
        String answer =message.getText() + "\nOk, NICE)";
        bot.sentAnswer(usersService.findUserByChatId(Math.toIntExact(message.getChatId())), message.getText(),answer);
    }
}
