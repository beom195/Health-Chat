package com.beom195.health_chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // 생성일, 수정일 자동 생성
public class HealthChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthChatApplication.class, args);
    }

}
