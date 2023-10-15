package com.example.playerservice.controller;

import com.example.playerservice.model.Player;
import com.example.playerservice.service.PlayerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
@Api(value = "Player Controller", description = "API endpoints for players")
public class PlayerController {
    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/team/{teamId}")
    @ApiOperation(value = "Get players by team ID", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "Not authorized!"),
            @ApiResponse(code = 403, message = "Forbidden!!!"),
            @ApiResponse(code = 404, message = "Not found!!!")
    })
    public List<Player> getPlayerByTeamId(
            @ApiParam(value = "Team ID", required = true)
            @PathVariable Long teamId) {
        return playerService.getAllPlayersByTeamId(teamId);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get player by ID", response = Player.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "Not authorized!"),
            @ApiResponse(code = 403, message = "Forbidden!!!"),
            @ApiResponse(code = 404, message = "Not found!!!")
    })
    public Player getPlayerById(
            @ApiParam(value = "Player ID", required = true)
            @PathVariable Long id) {
        return playerService.getPlayerById(id);
    }

    @GetMapping("/{id}/withTeamDetails")
    @ApiOperation(value = "Get player by ID with team details", response = Object.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "Not authorized!"),
            @ApiResponse(code = 403, message = "Forbidden!!!"),
            @ApiResponse(code = 404, message = "Not found!!!")
    })
    public Object getPlayerByIdWithTeamDetails(
            @ApiParam(value = "Player ID", required = true)
            @PathVariable Long id) {
        return playerService.getPlayerWithTeamDetails(id);
    }

    @GetMapping
    @ApiOperation(value = "Get all players", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "Not authorized!"),
            @ApiResponse(code = 403, message = "Forbidden!!!"),
            @ApiResponse(code = 404, message = "Not found!!!")
    })
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @PostMapping
    @ApiOperation(value = "Create a new player", response = Player.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Not authorized!"),
            @ApiResponse(code = 403, message = "Forbidden!!!")
    })
    public Player createPlayer(
            @ApiParam(value = "Player data", required = true)
            @RequestBody Player player) {
        return playerService.createPlayer(player);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update an existing player", response = Player.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Not authorized!"),
            @ApiResponse(code = 403, message = "Forbidden!!!"),
            @ApiResponse(code = 404, message = "Not found!!!")
    })
    public Player updatePlayer(
            @ApiParam(value = "Player ID", required = true)
            @PathVariable Long id,
            @ApiParam(value = "Updated player data", required = true)
            @RequestBody Player updatedPlayer) {
        return playerService.updatePlayer(id, updatedPlayer);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a player by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No content"),
            @ApiResponse(code = 401, message = "Not authorized!"),
            @ApiResponse(code = 403, message = "Forbidden!!!"),
            @ApiResponse(code = 404, message = "Not found!!!")
    })
    public void deletePlayer(
            @ApiParam(value = "Player ID", required = true)
            @PathVariable Long id) {
        playerService.deletePlayer(id);
    }
}
