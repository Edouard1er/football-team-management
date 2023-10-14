package com.example.playerservice.service;

import com.example.playerservice.exception.PlayerNotFoundException;
import com.example.playerservice.model.Player;
import com.example.playerservice.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.lang.reflect.Field;

@Service
public class PlayerService {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    private final PlayerRepository playerRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public List<Player> getAllPlayersByTeamId(Long teamId) {
        return playerRepository.findAllByTeamId(teamId);
    }

    public Player getPlayerById(Long id) {
        Optional<Player> player = playerRepository.findById(id);
        if (player.isPresent()) {
            return player.get();
        } else {
            throw new PlayerNotFoundException(id);
        }
    }

   // Cette méthode ne respecte clairement pas les bonnes pratiques hahaha, je la fais la plus simple possible
    public Object getPlayerWithTeamDetails(Long playerId) {
        Optional<Player> player = playerRepository.findById(playerId);
        if (!player.isPresent()) {
            throw new PlayerNotFoundException(playerId);
        }

        // Effectuez un appel à distance au service TeamService pour récupérer les informations de l'équipe
        Object teamDetails = restTemplate.getForObject("http://team-service/teams/" + player.get().getTeamId(), Object.class);

        // Créez une structure de données (Map) pour combiner les informations du joueur et de l'équipe
        Map<String, Object> playerWithTeamDetails = new HashMap<>();
        playerWithTeamDetails.put("id", player.get().getId());
        playerWithTeamDetails.put("name", player.get().getName());
        playerWithTeamDetails.put("position", player.get().getPosition());
        playerWithTeamDetails.put("team", teamDetails);

        // Renvoyez la structure en tant qu'Object
        return (Object) playerWithTeamDetails;
    }

    public Player createPlayer(Player player) {
        System.out.print(player);
        return playerRepository.save(player);
    }

    public Player updatePlayer(Long id, Player updatedPlayer) {
        Player existingPlayer = getPlayerById(id);

        // Obtenez la liste des champs de l'entité Player
        Field[] fields = Player.class.getDeclaredFields();

        // Itérez sur les champs
        for (Field field : fields) {
            try {
                // Rendre le champ accessible (peut être privé)
                field.setAccessible(true);

                // Obtenez la valeur du champ dans l'entité mise à jour
                Object updatedValue = field.get(updatedPlayer);

                // Si la valeur du champ dans l'entité mise à jour n'est pas nulle, mettez à jour la valeur correspondante
                if (updatedValue != null) {
                    field.set(existingPlayer, updatedValue);
                }
            } catch (IllegalAccessException e) {
                // Gérez les exceptions, par exemple, si le champ est inaccessible
                e.printStackTrace();
            }
        }

        return playerRepository.save(existingPlayer);
    }

    public void deletePlayer(Long id) {
        if (playerRepository.existsById(id)) {
            playerRepository.deleteById(id);
        } else {
            throw new PlayerNotFoundException(id);
        }
    }
}
