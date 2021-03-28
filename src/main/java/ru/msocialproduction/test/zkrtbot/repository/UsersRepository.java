package ru.msocialproduction.test.zkrtbot.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.msocialproduction.test.zkrtbot.entity.Users;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users,Integer> {
    Users findUserByChatId(Integer chatId);
}
