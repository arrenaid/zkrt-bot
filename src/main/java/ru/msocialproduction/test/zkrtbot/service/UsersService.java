package ru.msocialproduction.test.zkrtbot.service;


import org.springframework.stereotype.Service;
import ru.msocialproduction.test.zkrtbot.controler.Bot;
import ru.msocialproduction.test.zkrtbot.entity.Users;
import ru.msocialproduction.test.zkrtbot.repository.UsersRepository;

import java.util.List;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public void createUser(Users users){
        if(findUserByChatId(users.getChatId())==null)
            usersRepository.save(users);
    }
    public List<Users> findAll(){
        return usersRepository.findAll();
    }
    public Users findUserByChatId(int chatId){
        try {
            return usersRepository.findUserByChatId(chatId);
        }catch (Exception e){
            Bot.logger.error("UserService - error findUserByChatId : ", e);
            e.printStackTrace();
            return null;
        }
    }
}
