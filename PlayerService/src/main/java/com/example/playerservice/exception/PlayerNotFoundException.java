package com.example.playerservice.exception;

public class PlayerNotFoundException extends RuntimeException{
    public PlayerNotFoundException(Long playerId) {
        super("Joueur non trouvé avec l'ID : " + playerId);
    }
}
