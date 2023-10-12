package com.example.teamservice.exception;

public class TeamNotFoundException extends RuntimeException {
    public TeamNotFoundException(Long teamId) {
        super("Équipe non trouvée avec l'identifiant : " + teamId);
    }
}

