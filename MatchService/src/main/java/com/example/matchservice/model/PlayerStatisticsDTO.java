package com.example.matchservice.model;


public class PlayerStatisticsDTO {

    private Object player;
    private Integer appearances;
    private Integer goalsScored;
    private Integer assists;

    public PlayerStatisticsDTO() {
    }

    public PlayerStatisticsDTO(Object player, Integer appearances, Integer goalsScored, Integer assists) {
        this.player = player;
        this.appearances = appearances;
        this.goalsScored = goalsScored;
        this.assists = assists;
    }

    public  Object getPlayer(){
        return  player;
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
