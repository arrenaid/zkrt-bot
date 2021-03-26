package ru.msocialproduction.test.zkrtbot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.msocialproduction.test.zkrtbot.entity.Users;

@Service
public class UsersRegistration {
    @Autowired
    private final UsersRegistration usersRegistration;

    public UsersRegistration(UsersRegistration usersRegistration) {
        this.usersRegistration = usersRegistration;
    }
    public void createUser(Users users){
      //  usersRegistration.add(users);
    }
    public void updateLastMessage(Users users){

    }

}
