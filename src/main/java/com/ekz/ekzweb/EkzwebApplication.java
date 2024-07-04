package com.ekz.ekzweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.ekz.ekzweb.mapper")
public class EkzwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(EkzwebApplication.class, args);
	}

}
