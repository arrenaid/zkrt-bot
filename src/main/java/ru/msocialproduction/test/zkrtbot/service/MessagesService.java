package ru.msocialproduction.test.zkrtbot.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.msocialproduction.test.zkrtbot.entity.Messages;
import ru.msocialproduction.test.zkrtbot.repository.MessagesRepository;

import java.util.List;

@Service
public class MessagesService {

    //@Autowired
    private final MessagesRepository messagesRepository;

    public MessagesService(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    public void createMessages(Messages messages){
        messagesRepository.save(messages);
    }
    public List<Messages> findAll(){
        return messagesRepository.findAll();
    }

}

