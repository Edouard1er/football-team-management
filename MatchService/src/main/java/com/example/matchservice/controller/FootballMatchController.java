package com.example.matchservice.controller;

import com.example.matchservice.model.FootballMatch;
import com.example.matchservice.model.PlayerStatisticsDTO;
import com.example.matchservice.model.TeamStatisticsDTO;
import com.example.matchservice.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/football-matches")
public class FootballMatchController {
    private final FootballMatchService footballMatchService;
    private final AppearancePlayerService appearancePlayerService;

    private final GoalScorerService goalScorerService;

    private  final AssistService assistService;

    private  final TeamPlayerService teamPlayerService;

    @Autowired
    public FootballMatchController(FootballMatchService footballMatchService, AppearancePlayerService appearancePlayerService, GoalScorerService goalScorerService, AssistService assistService, TeamPlayerService teamPlayerService) {
        this.footballMatchService = footballMatchService;
        this.appearancePlayerService = appearancePlayerService;
        this.goalScorerService = goalScorerService;
        this.assistService = assistService;
        this.teamPlayerService = teamPlayerService;
    }

    @GetMapping
    public List<FootballMatch> getAllFootballMatches() {
        return footballMatchService.getAllFootballMatches();
    }

    @GetMapping("/stats/team/{teamId}")
    public TeamStatisticsDTO  getFootBallMatchStatByTeam(@PathVariable Long teamId) {
        return footballMatchService.getAllFootballMatchStatByTeam(teamId);
    }

    @GetMapping("/stats/player/{playerId}")
    public PlayerStatisticsDTO getFootBallMatchStatByPlayer(@PathVariable Long playerId) {
        Integer appearances = 0;
        Integer goalsScored = 0;
        Integer assists = 0;
        Object player = teamPlayerService.getPlayerById(playerId);

        Map<String, Object> appearancesMap = appearancePlayerService.getMatchPlayedPlayersForPlayer(playerId);
        if(appearancesMap != null){
            appearances = (Integer) appearancesMap.get("appearances");
        }

        Map<String, Object> goalsScoredMap = goalScorerService.getGoalsAmountForPlayer(playerId);
        if(goalsScoredMap != null){
            goalsScored = (Integer) goalsScoredMap.get("goals");
        }

        Map<String, Object> assistsMap = assistService.getAssistsAmountForPlayer(playerId);
        if(assistsMap != null){
            assists = (Integer) assistsMap.get("assists");
        }

        return new PlayerStatisticsDTO(player, appearances, goalsScored, assists);
    }

    @GetMapping("/{id}")
    public Optional<FootballMatch> getFootballMatchById(@PathVariable Long id) {
        return footballMatchService.getFootballMatchById(id);
    }

    @PostMapping
    public FootballMatch createFootballMatch(@RequestBody FootballMatch footballMatch) {
        return footballMatchService.createFootballMatch(footballMatch);
    }

    @PutMapping("/{id}")
    public FootballMatch updateFootballMatch(@PathVariable Long id, @RequestBody FootballMatch updatedFootballMatch) {
        return footballMatchService.updateFootballMatch(id, updatedFootballMatch);
    }

    @DeleteMapping("/{id}")
    public void deleteFootballMatch(@PathVariable Long id) {
        footballMatchService.deleteFootballMatch(id);
    }
}
