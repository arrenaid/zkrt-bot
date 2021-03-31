package ru.msocialproduction.test.zkrtbot.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
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
    @Scheduled (cron = "0 0 18 * * *", zone ="Europe/Moscow")//(cron = "0 * * * * *")
    public void go(){
        try{
            backorderService.clearDomains();
            List<DomainEntity> domainsList = backorderService.getDomains();
            backorderService.addDomains(domainsList);
            List<Users> users = usersService.findAll();
            users.forEach(users1 -> {
                LocalDateTime ldt = LocalDateTime.now();
                String answer = DateTimeFormatter.ofPattern("yyyy-MM-dd hh-mm", Locale.ENGLISH).format(ldt)
                        + " Найдено " + domainsList.size() + " доменов \n0 - "+ domainsList.get(0).getDomainName()
                        + "\n1 - "+ domainsList.get(1).getDomainName()+ "\n2 - "+ domainsList.get(2).getDomainName()
                        + "\n3 - "+ domainsList.get(3).getDomainName()+ "\n4 - "+ domainsList.get(4).getDomainName()
                        + "\n......\n"+(domainsList.size() - 2)+" - "+ domainsList.get(domainsList.size() - 2).getDomainName()
                        + "\n"+(domainsList.size() - 1)+" - "+ domainsList.get(domainsList.size() -1).getDomainName();
                bot.addMessage(users1,DateTimeFormatter.ofPattern("yyyy-MM-dd hh-mm", Locale.ENGLISH).format(ldt),answer);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
