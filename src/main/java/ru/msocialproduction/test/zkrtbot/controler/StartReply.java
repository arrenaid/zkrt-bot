package ru.msocialproduction.test.zkrtbot.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.msocialproduction.test.zkrtbot.ZkrtBotApplication;
import ru.msocialproduction.test.zkrtbot.entity.Users;
import ru.msocialproduction.test.zkrtbot.service.UsersService;

@Component
public class StartReply {

    @Autowired
    UsersService userService;
    @Autowired
    Bot bot;

    public void startReply(Message message){
        try {
            Users users = userService.findUserByChatId(Math.toIntExact(message.getChatId()));
            if (users == null) {
                users = new Users(Math.toIntExact(message.getChatId()));
                try {
                    userService.createUser(users);
                } catch (Exception e) {
                    ZkrtBotApplication.logger.error("startReply - error createUser : ", e);
                    e.printStackTrace();
                }
                bot.sentAnswer(users, message.getText(), "new user registered\nHi " + users.getName());
            } else {
                bot.sentAnswer(users, message.getText(), "Hi again " + users.getName());
            }
        }     catch (Exception e){
            ZkrtBotApplication.logger.error("startReply - error findUser : ", e);
            e.printStackTrace();
        }
    }
}
