package com.gang.oneclickshoppingmall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class OneclickShoppingmallApplication {

	public static void main(String[] args) {
		SpringApplication.run(OneclickShoppingmallApplication.class, args);
	}

}
