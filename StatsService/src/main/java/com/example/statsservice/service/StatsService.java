package com.example.statsservice.service;

import com.example.statsservice.model.PlayerStatistics;
import com.example.statsservice.model.TeamStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StatsService {
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;

    public PlayerStatistics getStatsForPlayerById(Long playerId){
        PlayerStatistics stats = restTemplate.getForObject("http://match-service/football-matches/stats/player/" + playerId , PlayerStatistics.class);


        return stats;
    }

    public TeamStatistics getStatsForTeamById(Long teamId){
        TeamStatistics stats = restTemplate.getForObject("http://match-service/football-matches/stats/team/" + teamId , TeamStatistics.class);


        return stats;
    }
}
