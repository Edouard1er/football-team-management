package com.example.matchservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TeamPlayerService {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;

    public Object getPlayerById(Long playerId){
        Object player = restTemplate.getForObject("http://player-service/players/" + playerId , Object.class);


        return player;
    }

    public Object getTeamById(Long teamId){
        Object team = restTemplate.getForObject("http://team-service/teams/" + teamId , Object.class);


        return team;
    }
}
