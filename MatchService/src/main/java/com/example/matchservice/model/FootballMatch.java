package com.example.matchservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class FootballMatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date matchDate;
    private String refereeName;
    private String stadium;
    private Long winnerId;
    private Long perdantId;
    private boolean isMatchNull;

    private Long homeTeam;

    private Long awayTeam;

    private Long homeTeamScore;

    private Long awayTeamScore;

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<MatchTeamStat> matchTeamStats;

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<GoalScorer> goalScorers;

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Assist> assists;

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<AppearancePlayer> appearancePlayers;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    public String getRefereeName() {
        return refereeName;
    }

    public void setRefereeName(String refereeName) {
        this.refereeName = refereeName;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public Long getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(Long winnerId) {
        this.winnerId = winnerId;
    }

    public Long getPerdantId() {
        return perdantId;
    }

    public void setPerdantId(Long perdantId) {
        this.perdantId = perdantId;
    }

    public boolean isMatchNull() {
        return isMatchNull;
    }

    public void setMatchNull(boolean matchNull) {
        isMatchNull = matchNull;
    }

    public Long getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Long homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Long getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Long awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Long getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(Long homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public Long getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(Long awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    public List<MatchTeamStat> getMatchTeamStats() {
        return matchTeamStats;
    }

    public void setMatchTeamStats(List<MatchTeamStat> matchTeamStats) {
        this.matchTeamStats = matchTeamStats;
    }

    public List<GoalScorer> getGoalScorers() {
        return goalScorers;
    }

    public void setGoalScorers(List<GoalScorer> goalScorers) {
        this.goalScorers = goalScorers;
    }

    public List<Assist> getAssists() {
        return assists;
    }

    public void setAssists(List<Assist> assists) {
        this.assists = assists;
    }

    public List<Map<String, Object>> getMatchPlayedPlayers() {
        // Regrouper les joueurs par équipe et stocker les identifiants des joueurs dans une liste
        Map<Long, List<Long>> playersGroupedByTeamId = appearancePlayers.stream()
                .collect(Collectors.groupingBy(
                        AppearancePlayer::getTeamId,
                        LinkedHashMap::new,
                        Collectors.mapping(AppearancePlayer::getPlayerId, Collectors.toList())
                ));

        return playersGroupedByTeamId.entrySet().stream()
                .map(entry -> {
                    // Créer une carte pour stocker les informations
                    Map<String, Object> teamPlayerMap = new LinkedHashMap<>();
                    // Ajouter l'identifiant de l'équipe comme clé "teamId"
                    teamPlayerMap.put("teamId", entry.getKey());
                    // Ajouter la liste des identifiants de joueurs comme clé "players"
                    teamPlayerMap.put("players", entry.getValue());
                    return teamPlayerMap;
                })
                .collect(Collectors.toList());
    }


    public void setMatchPlayedPlayers(List<AppearancePlayer> appearancePlayers) {
        this.appearancePlayers = appearancePlayers;
    }
}
