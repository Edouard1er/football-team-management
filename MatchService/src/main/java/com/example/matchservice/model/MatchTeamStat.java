package com.example.matchservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
@Entity
public class MatchTeamStat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int nombreDeTir;
    private int tirCadre;
    private int but;
    private int possession;
    private int passes;
    private int fautes;
    private int cartonJaune;
    private int cartonRouge;
    private int horsJeu;
    private int corner;

    private Long teamId;

    @ManyToOne
    @JoinColumn(name = "match_id")
    @JsonBackReference
    private FootballMatch match;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNombreDeTir() {
        return nombreDeTir;
    }

    public void setNombreDeTir(int nombreDeTir) {
        this.nombreDeTir = nombreDeTir;
    }

    public int getTirCadre() {
        return tirCadre;
    }

    public void setTirCadre(int tirCadre) {
        this.tirCadre = tirCadre;
    }

    public int getBut() {
        return but;
    }

    public void setBut(int but) {
        this.but = but;
    }

    public int getPossession() {
        return possession;
    }

    public void setPossession(int possession) {
        this.possession = possession;
    }

    public int getPasses() {
        return passes;
    }

    public void setPasses(int passes) {
        this.passes = passes;
    }

    public int getFautes() {
        return fautes;
    }

    public void setFautes(int fautes) {
        this.fautes = fautes;
    }

    public int getCartonJaune() {
        return cartonJaune;
    }

    public void setCartonJaune(int cartonJaune) {
        this.cartonJaune = cartonJaune;
    }

    public int getCartonRouge() {
        return cartonRouge;
    }

    public void setCartonRouge(int cartonRouge) {
        this.cartonRouge = cartonRouge;
    }

    public int getHorsJeu() {
        return horsJeu;
    }

    public void setHorsJeu(int horsJeu) {
        this.horsJeu = horsJeu;
    }

    public int getCorner() {
        return corner;
    }

    public void setCorner(int corner) {
        this.corner = corner;
    }

    public FootballMatch getMatch() {
        return match;
    }

    public void setMatch(FootballMatch match) {
        this.match = match;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
}
