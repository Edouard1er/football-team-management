package com.example.matchservice.controller;

import com.example.matchservice.model.GoalScorer;
import com.example.matchservice.service.GoalScorerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/goalscorers")
public class GoalScorerController {
    private final GoalScorerService goalScorerService;

    @Autowired
    public GoalScorerController(GoalScorerService goalScorerService) {
        this.goalScorerService = goalScorerService;
    }

    @GetMapping
    public List<GoalScorer> getAllGoalScorers() {
        return goalScorerService.getAllGoalScorers();
    }

    @GetMapping("/{playerId}")
    public Map<String, Object> getAmountOfGoalsForPlayer(@PathVariable Long playerId) {
        return goalScorerService.getGoalsAmountForPlayer(playerId);
    }
    @PostMapping
    public GoalScorer createGoalScorer(@RequestBody GoalScorer goalScorer) {
        return goalScorerService.createGoalScorer(goalScorer);
    }

    @DeleteMapping("/{id}")
    public void deleteGoalScorer(@PathVariable Long id) {
        goalScorerService.deleteGoalScorer(id);
    }
}
