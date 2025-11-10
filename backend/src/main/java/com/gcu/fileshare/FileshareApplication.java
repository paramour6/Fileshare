package com.gcu.fileshare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class FileshareApplication
{
	public static void main(String[] args)
	{
		log.info("[FileshareApplication] Entry point called in FileshareApplication.java");

		SpringApplication.run(FileshareApplication.class, args);
	}
}