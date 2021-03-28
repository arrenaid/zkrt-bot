package ru.msocialproduction.test.zkrtbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.msocialproduction.test.zkrtbot.entity.DomainEntity;
import ru.msocialproduction.test.zkrtbot.entity.Messages;

public interface DomainRepository extends JpaRepository<DomainEntity, Integer>{
}
