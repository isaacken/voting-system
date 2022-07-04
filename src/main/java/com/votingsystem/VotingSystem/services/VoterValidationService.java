package com.votingsystem.VotingSystem.services;

import com.votingsystem.VotingSystem.interfaces.IVoterValidationService;
import com.votingsystem.VotingSystem.responses.VoterValidationResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class VoterValidationService implements IVoterValidationService {
    public boolean isValidVoter(String voterId) {
        var client = WebClient.create("https://user-info.herokuapp.com");

        VoterValidationResponse result;
        try {
            result = client.get().uri("/users/{id}", voterId).retrieve().bodyToMono(VoterValidationResponse.class).block();
        } catch (Exception e) {
            return false;
        }

        return result.status.equals("ABLE_TO_VOTE");
    }
}
