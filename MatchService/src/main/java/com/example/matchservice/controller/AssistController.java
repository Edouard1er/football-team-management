package com.example.matchservice.controller;

import com.example.matchservice.model.Assist;
import com.example.matchservice.service.AssistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/assists")
public class AssistController {
    private final AssistService assistService;

    @Autowired
    public AssistController(AssistService assistService) {
        this.assistService = assistService;
    }

    @GetMapping
    public List<Assist> getAllAssists() {
        return assistService.getAllAssists();
    }

    @GetMapping("/{playerId}")
    public Map<String, Object> getAmountOfAssistsForPlayer(@PathVariable Long playerId) {
        return assistService.getAssistsAmountForPlayer(playerId);
    }

    @PostMapping
    public Assist createAssist(@RequestBody Assist assist) {
        return assistService.createAssist(assist);
    }


    @DeleteMapping("/{id}")
    public void deleteAssist(@PathVariable Long id) {
        assistService.deleteAssist(id);
    }
}
