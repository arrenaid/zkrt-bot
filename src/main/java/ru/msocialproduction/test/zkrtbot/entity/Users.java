package ru.msocialproduction.test.zkrtbot.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @SequenceGenerator(name = "users_generator", sequenceName = "seq_users", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_generator")////)
    private Integer id;
    @Column(name = "chat_id")
    private Integer chatId;
    @Column
    private String name;
    @Column(name = "last_message_at")
    private Date lastMessageAt;

    public Users(){}
    public Users(Integer chatId) {
        this.chatId = chatId;
        this.name = String.valueOf(chatId);
        this.lastMessageAt = new Date();
    }

    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLastMessage() {
        return lastMessageAt;
    }

    public void setLastMessageAt() {
        this.lastMessageAt = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
