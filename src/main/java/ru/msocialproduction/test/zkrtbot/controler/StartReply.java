package ru.msocialproduction.test.zkrtbot.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.msocialproduction.test.zkrtbot.entity.Messages;
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
                    Bot.logger.error("startReply - error createUser : ", e);
                    e.printStackTrace();
                }
                bot.sentAnswer(users, message.getText(), "new user registered\nHi " + users.getName());
            } else {
                bot.sentAnswer(users, message.getText(), "Hi again " + users.getName());
            }
        }     catch (Exception e){
            Bot.logger.error("startReply - error findUser : ", e);
            e.printStackTrace();
        }
    }


//    @Autowired
//    private UsersService userService;
//    @Autowired
//    private MessagesService messagesService;
//
//    public SendMessage startReply(Message message){
//        Users users = null;
//        try {
//               users  = userService.findUserByChatId(Math.toIntExact(message.getChatId()));
//            if (users == null) {
//                users = new Users(Math.toIntExact(message.getChatId()));
//                try {
//                    userService.createUser(users);
//                } catch (Exception e) {
//                    Bot.logger.error("startReply - error createUser : ", e);
//                    e.printStackTrace();
//                }
//               return addMessage(users, message.getText(),"new user registered\nHi " +users.getName());
//            }
//            else {
//               return addMessage(users, message.getText(), "Hi again "+users.getName());
//            }
//        }
//        catch (Exception e){
//            Bot.logger.error("startReply - error findUser : ", e);
//            e.printStackTrace();
//            return null;
//        }
//    }
//    public SendMessage reply(Message message){
//        return addMessage(userService.findUserByChatId(Math.toIntExact(message.getChatId())),
//                message.getText(), message.getText() + "\nOk, nice ");
//    }
//
//    public SendMessage addMessage(Users user, String quest, String answer){
//        Messages msg = new Messages(user,quest,answer);
//
//        try {
//            messagesService.createMessages(msg);
//        } catch (Exception e) {
//            Bot.logger.error("startReply - error addMessage : ", e);
//            e.printStackTrace();
//        }
//        SendMessage sendMessage = new SendMessage();
//        sendMessage.setText(answer);
//        sendMessage.setChatId(user.getChatId().toString());
//
//        return sendMessage;
//    }



}
