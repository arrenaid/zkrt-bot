# zkrt-bot
Telegram bot
#### ***Функционал:***
1. Регистрирует всех обратившихся к нему пользователей в таблице users и обновляет поле last_message_at при каждом сообщении.
2. Регистрирует все входящие к нему сообщения и ответы который он отправил в таблице messages (связанной с users).
3. Раз в сутки забирает данные из https://backorder.ru/json/?order=desc&expired=1&by=hotness&page=1&items=50 и складывает в таблицу daily_domains (данные прошлого дня удаляются).
4. Раз в сутки, после сбора базы, рассылает всем пользователям которые у него зарегистрированы сообщение "YYY-MM-dd. Собрано N доменов".

#### ***Используемые технологии***
* Java 12
* Maven
* Spring Boot 2
* PostgreSQL
* Liquibase
* spring-boot-starter-logging (ежедневное ротирование log файла)
* telegrambots-spring-boot-starter