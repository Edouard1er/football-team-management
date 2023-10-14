package com.example.matchservice.controller;

import com.example.matchservice.model.AppearancePlayer;
import com.example.matchservice.service.AppearancePlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/players-appearances")
public class MatchPlayedPlayerController {

    private final AppearancePlayerService appearancePlayerService;

    @Autowired
    public MatchPlayedPlayerController(AppearancePlayerService appearancePlayerService) {
        this.appearancePlayerService = appearancePlayerService;
    }

    @GetMapping("/{playerId}")
    public Map<String, Object> getMatchPlayedPlayersForPlayer(@PathVariable Long playerId) {
        return appearancePlayerService.getMatchPlayedPlayersForPlayer(playerId);
    }

    @PostMapping
    public AppearancePlayer addMatchPlayedPlayer(@RequestBody AppearancePlayer appearancePlayer) {
        return appearancePlayerService.addMatchPlayedPlayer(appearancePlayer);
    }

    @DeleteMapping("/{id}")
    public void deleteMatchPlayedPlayer(@PathVariable Long id) {
        appearancePlayerService.deleteMatchPlayedPlayer(id);
    }
}
