package com.example.statsservice.controller;

import com.example.statsservice.model.PlayerStatistics;
import com.example.statsservice.model.TeamStatistics;
import com.example.statsservice.service.StatsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/stats")
@Api(value = "Stats Controller", description = "API endpoints for statistics")
public class StatsController {

    private final StatsService statsService;

    @Autowired
    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/client/frontend")
    public String hi() {
        String randomString = this.restTemplate.getForObject("http://match-service/football-matches/backend", String.class);
        return "Server Response :: " + randomString;
    }

    @GetMapping("/team-stats/{teamId}")
    @ApiOperation(value = "Get team statistics", response = TeamStatistics.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "Not authorized!"),
            @ApiResponse(code = 403, message = "Forbidden!!!"),
            @ApiResponse(code = 404, message = "Not found!!!")
    })
    public TeamStatistics getTeamStats(
            @ApiParam(value = "Team ID", required = true)
            @PathVariable Long teamId) {
        return statsService.getStatsForTeamById(teamId);
    }

    @GetMapping("/player-stats/{playerId}")
    @ApiOperation(value = "Get player statistics", response = PlayerStatistics.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "Not authorized!"),
            @ApiResponse(code = 403, message = "Forbidden!!!"),
            @ApiResponse(code = 404, message = "Not found!!!")
    })
    public PlayerStatistics getPlayerStats(
            @ApiParam(value = "Player ID", required = true)
            @PathVariable Long playerId) {
        return statsService.getStatsForPlayerById(playerId);
    }
}
