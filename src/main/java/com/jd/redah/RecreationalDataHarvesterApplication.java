package com.jd.redah;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class RecreationalDataHarvesterApplication {

	public static void main(String[] args) {
		System.out.println("Hello Rpi!");
		SpringApplication.run(RecreationalDataHarvesterApplication.class, args);
	}
}
