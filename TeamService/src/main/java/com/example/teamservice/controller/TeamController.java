package com.example.teamservice.controller;

import com.example.teamservice.model.Team;
import com.example.teamservice.service.TeamService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
@Api(value = "Team Controller", description = "API endpoints for teams")
public class TeamController {
    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get team by ID", response = Team.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "Not authorized!"),
            @ApiResponse(code = 403, message = "Forbidden!!!"),
            @ApiResponse(code = 404, message = "Not found!!!")
    })
    public Object getTeamById(
            @ApiParam(value = "Team ID", required = true)
            @PathVariable Long id) {
        return teamService.getTeamById(id);
    }

    @GetMapping("/{id}/withPlayerDetails")
    @ApiOperation(value = "Get team by ID with player details", response = Object.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "Not authorized!"),
            @ApiResponse(code = 403, message = "Forbidden!!!"),
            @ApiResponse(code = 404, message = "Not found!!!")
    })
    public Object getTeamByIdWithPlayerDetails(
            @ApiParam(value = "Team ID", required = true)
            @PathVariable Long id) {
        return teamService.getTeamByIdWithPlayerDetails(id);
    }

    @GetMapping
    @ApiOperation(value = "Get all teams", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "Not authorized!"),
            @ApiResponse(code = 403, message = "Forbidden!!!"),
            @ApiResponse(code = 404, message = "Not found!!!")
    })
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @PostMapping
    @ApiOperation(value = "Create a new team", response = Team.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Not authorized!"),
            @ApiResponse(code = 403, message = "Forbidden!!!")
    })
    public Team createTeam(
            @ApiParam(value = "Team data", required = true)
            @RequestBody Team team) {
        return teamService.createTeam(team);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update an existing team", response = Team.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Not authorized!"),
            @ApiResponse(code = 403, message = "Forbidden!!!"),
            @ApiResponse(code = 404, message = "Not found!!!")
    })
    public Team updateTeam(
            @ApiParam(value = "Team ID", required = true)
            @PathVariable Long id,
            @ApiParam(value = "Updated team data", required = true)
            @RequestBody Team updatedTeam) {
        return teamService.updateTeam(id, updatedTeam);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a team by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No content"),
            @ApiResponse(code = 401, message = "Not authorized!"),
            @ApiResponse(code = 403, message = "Forbidden!!!"),
            @ApiResponse(code = 404, message = "Not found!!!")
    })
    public void deleteTeam(
            @ApiParam(value = "Team ID", required = true)
            @PathVariable Long id) {
        teamService.deleteTeam(id);
    }
}
