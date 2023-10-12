package com.example.teamservice.service;

import com.example.teamservice.exception.TeamNotFoundException;
import com.example.teamservice.model.Team;
import com.example.teamservice.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.lang.reflect.Field;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Modifier;


import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    private final TeamRepository teamRepository;

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
}
