package com.example.teamservice.service;

import com.example.teamservice.exception.TeamNotFoundException;
import com.example.teamservice.model.Team;
import com.example.teamservice.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Modifier;


import java.util.*;

@Service
public class TeamService {
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    private final TeamRepository teamRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    // Récupérer une équipe par son identifiant
    public Team getTeamById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new TeamNotFoundException(id));
    }

    public Object getTeamByIdWithPlayerDetails(Long id) {

        Optional<Team> team = teamRepository.findById(id);
        if (!team.isPresent()) {
            throw new TeamNotFoundException(id);
        }

        // Effectuez un appel à distance au service PlayerService pour récupérer les informations des joueurs
        Object players = restTemplate.getForObject("http://player-service/players/team/" + id, Object.class);

        // Créez une structure de données (Map) pour combiner les informations de l'équipe et des joueurs
        Map<String, Object> teamWithPlayerDetails = new HashMap<>();
        teamWithPlayerDetails.put("id", team.get().getId());
        teamWithPlayerDetails.put("name", team.get().getName());
        teamWithPlayerDetails.put("coach", team.get().getCoach());
        teamWithPlayerDetails.put("city", team.get().getCity());
        teamWithPlayerDetails.put("players", players);

        // Renvoyez la structure en tant qu'Object
        return (Object) teamWithPlayerDetails;
    }

    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }

    public Team updateTeam(Long id, Team updatedTeam) {
        Team existingTeam = getTeamById(id);

        // Obtenez la liste des champs de l'entité Team
        Field[] fields = Team.class.getDeclaredFields();

        // Itérez sur les champs
        for (Field field : fields) {
            try {
                // Rendre le champ accessible (peut être privé)
                field.setAccessible(true);

                // Obtenez la valeur du champ dans l'entité mise à jour
                Object updatedValue = field.get(updatedTeam);

                // Si la valeur du champ dans l'entité mise à jour n'est pas nulle, mettez à jour la valeur correspondante
                if (updatedValue != null) {
                    field.set(existingTeam, updatedValue);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return teamRepository.save(existingTeam);
    }

    public void deleteTeam(Long id) {
        Team existingTeam = getTeamById(id);

        teamRepository.delete(existingTeam);
    }

    public Object getPlayerListByTeamId(Long teamId){
        return restTemplate.getForObject("http://player-service/players/team/" + teamId, Object.class);
    }
}
