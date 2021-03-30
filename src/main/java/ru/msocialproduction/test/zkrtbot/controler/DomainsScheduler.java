package ru.msocialproduction.test.zkrtbot.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.msocialproduction.test.zkrtbot.entity.DomainEntity;
import ru.msocialproduction.test.zkrtbot.entity.Users;
import ru.msocialproduction.test.zkrtbot.service.BackorderService;
import ru.msocialproduction.test.zkrtbot.service.UsersService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Component
public class DomainsScheduler{
    @Autowired
    BackorderService backorderService;
    @Autowired
    UsersService usersService;
    @Autowired
    Bot bot;
    @Scheduled (cron = "0 0 0/23 * * *", zone ="Europe/Moscow")//(cron = "0 * * * * *")
    public void go(){
        try{
            backorderService.clearDomains();
            List<DomainEntity> domainsList = backorderService.getDomains();
            backorderService.addDomains(domainsList);
            List<Users> users = usersService.findAll();
            users.forEach(users1 -> {
                SendMessage sendMessage = new SendMessage();
                LocalDateTime ldt = LocalDateTime.now();
                sendMessage.setText(
                        DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(ldt)
                                + " Найдено доменов - " + domainsList.size());
                sendMessage.setChatId(String.valueOf(users1.getChatId()));
                try {
                    bot.execute(sendMessage);
                } catch (TelegramApiException e1) {
                    e1.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
