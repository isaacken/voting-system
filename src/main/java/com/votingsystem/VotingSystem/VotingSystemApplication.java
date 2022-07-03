package com.votingsystem.VotingSystem;

import com.votingsystem.VotingSystem.entities.Vote;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableMongoAuditing
public class VotingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotingSystemApplication.class, args);
	}

}
