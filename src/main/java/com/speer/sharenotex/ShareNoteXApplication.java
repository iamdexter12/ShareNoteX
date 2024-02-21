package com.speer.sharenotex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ShareNoteXApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShareNoteXApplication.class, args);
	}

}
