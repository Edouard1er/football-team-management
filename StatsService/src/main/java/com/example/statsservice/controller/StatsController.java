package com.example.statsservice.controller;

import com.example.statsservice.model.PlayerStatistics;
import com.example.statsservice.model.TeamStatistics;
import com.example.statsservice.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stats")
public class StatsController {

    private final StatsService statsService;

    @Autowired
    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping("/team-stats/{teamId}")
    public TeamStatistics getTeamStats(@PathVariable Long teamId) {
        return statsService.getStatsForTeamById(teamId);
    }

    @GetMapping("/player-stats/{playerId}")
    public PlayerStatistics getPlayerStats(@PathVariable Long playerId) {
        return statsService.getStatsForPlayerById(playerId);
    }
}
