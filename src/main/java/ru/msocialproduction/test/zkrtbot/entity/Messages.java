package ru.msocialproduction.test.zkrtbot.entity;

import javax.persistence.*;

@Entity
@Table(name = "messages")
public class Messages {
    @Id
    @SequenceGenerator(name = "messages_generator", sequenceName = "seq_messages", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "messages_generator")
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private Users user;
    @Column
    private String question;
    @Column
    private String answer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
