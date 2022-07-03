package com.votingsystem.VotingSystem.services;

import com.votingsystem.VotingSystem.interfaces.IVoterValidationService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class VoterValidationService implements IVoterValidationService {
    public boolean isValidVoter(String voterId) {
        var client = WebClient.create("https://user-info.herokuapp.com");

        String result;
        try {
            result = client.get().uri("/users/{id}", voterId).retrieve().bodyToMono(String.class).block();
        } catch (Exception e) {
            return false;
        }

        return result == "ABLE_TO_VOTE";
    }
}
