package ru.msocialproduction.test.zkrtbot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.msocialproduction.test.zkrtbot.entity.DomainEntity;
import ru.msocialproduction.test.zkrtbot.repository.DomainRepository;
import java.util.List;

@Component
public class BackorderService {
    @Autowired
    private final DomainRepository domainRepository;

    public BackorderService(DomainRepository domainRepository) {
        this.domainRepository = domainRepository;
    }
    public List<DomainEntity> getDomains() throws UnirestException, JsonProcessingException {
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.get("https://backorder.ru/json/?order=desc&expired=1&by=hotness&page=1&items=50")
                .asString();

        return new ObjectMapper().readValue(response.getBody(), new TypeReference<List<DomainEntity>>(){});
    }
    public void addDomains(List<DomainEntity> domainEntities){
        domainRepository.saveAll(domainEntities);
    }
    public void clearDomains(){
        domainRepository.deleteAll();
    }
}
