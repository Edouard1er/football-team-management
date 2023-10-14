package com.example.statsservice.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Player statistics for the season")
public class PlayerStatistics {

    @ApiModelProperty(value = "Player information")
    private Object player;

    @ApiModelProperty(value = "Number of appearances made by the player")
    private Integer appearances;

    @ApiModelProperty(value = "Number of goals scored by the player")
    private Integer goalsScored;

    @ApiModelProperty(value = "Number of assists provided by the player")
    private Integer assists;

    public PlayerStatistics() {
    }

    public PlayerStatistics(Object player, Integer appearances, Integer goalsScored, Integer assists) {
        this.player = player;
        this.appearances = appearances;
        this.goalsScored = goalsScored;
        this.assists = assists;
    }

    public Object getPlayer() {
        return player;
    }

    public Integer getAppearances() {
        return appearances;
    }

    public Integer getGoalsScored() {
        return goalsScored;
    }

    public Integer getAssists() {
        return assists;
    }
}
