package com.jd.redah;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class RecreationalDataHarvesterApplication {

  @Autowired
  public JobManager jobManager;

	public static void main(String[] args) {
		System.out.println("Hello Rpi!");
		SpringApplication.run(RecreationalDataHarvesterApplication.class, args);
	}
}
