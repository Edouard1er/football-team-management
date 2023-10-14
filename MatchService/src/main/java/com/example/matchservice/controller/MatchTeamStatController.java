package com.example.matchservice.controller;

import com.example.matchservice.exception.MatchTeamStatNotFoundException;
import com.example.matchservice.model.MatchTeamStat;
import com.example.matchservice.service.MatchTeamStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/match-team-stats")
public class MatchTeamStatController {
    private final MatchTeamStatService matchTeamStatService;

    @Autowired
    public MatchTeamStatController(MatchTeamStatService matchTeamStatService) {
        this.matchTeamStatService = matchTeamStatService;
    }

    @GetMapping
    public List<MatchTeamStat> getAllMatchTeamStats() {
        return matchTeamStatService.getAllMatchTeamStats();
    }

    @GetMapping("/{id}")
    public MatchTeamStat getMatchTeamStatById(@PathVariable Long id) {
        Optional<MatchTeamStat> matchTeamStat = matchTeamStatService.getMatchTeamStatById(id);
        if (matchTeamStat.isPresent()) {
            return matchTeamStat.get();
        } else {
            // Gérer le cas où l'entité n'est pas trouvée
            throw new MatchTeamStatNotFoundException(id);
        }
    }

    @PostMapping
    public MatchTeamStat createMatchTeamStat(@RequestBody MatchTeamStat matchTeamStat) {
        return matchTeamStatService.createMatchTeamStat(matchTeamStat);
    }

    @PutMapping("/{id}")
    public MatchTeamStat updateMatchTeamStat(@PathVariable Long id, @RequestBody MatchTeamStat updatedMatchTeamStat) {
        return matchTeamStatService.updateMatchTeamStat(id, updatedMatchTeamStat);
    }

    @DeleteMapping("/{id}")
    public void deleteMatchTeamStat(@PathVariable Long id) {
        matchTeamStatService.deleteMatchTeamStat(id);
    }
}
