package com.example.statsservice.model;

public class TeamStatistics {
    private Object team;
    private Long matchesPlayed;
    private Long goalsScored;
    private Long goalsConceded;
    private Long wins;
    private Long losses;
    private Long draws;
    private Long goalDifference;

    public TeamStatistics() {
    }

    public TeamStatistics(Object team,Long matchesPlayed, Long goalsScored, Long goalsConceded, Long wins, Long losses, Long draws, Long goalDifference) {
        this.team = team;
        this.matchesPlayed = matchesPlayed;
        this.goalsScored = goalsScored;
        this.goalsConceded = goalsConceded;
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
        this.goalDifference = goalDifference;
    }


    public Object getTeam(){
        return this.team;
    }
    public Long getMatchesPlayed() {
        return matchesPlayed;
    }

    public Long getGoalsScored() {
        return goalsScored;
    }

    public Long getGoalsConceded() {
        return goalsConceded;
    }

    public Long getWins() {
        return wins;
    }

    public Long getLosses() {
        return losses;
    }

    public Long getDraws() {
        return draws;
    }

    public Long getGoalDifference() {
        return goalDifference;
    }
}
