package com.psych.harishpsych.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name = "games")
public class Game extends Auditable{
    @ManyToMany
    @Getter @Setter
    @JsonIdentityReference
    private Set<Player> players = new HashSet<>();

    @Getter @Setter
    @Enumerated(EnumType.STRING)
    @NotNull
    private GameMode gameMode;

    @Getter @Setter
    private InternalError numRounds;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    @Getter @Setter
    @JsonManagedReference
    private List<Round> rounds = new ArrayList<>();

    @Getter @Setter
    private Boolean hasEllen = false;

    @ManyToOne
    @Getter @Setter
    @NotNull
    @JsonIdentityReference
    private Player leader;

    @ManyToMany(cascade = CascadeType.ALL)
    @Getter @Setter
    @JsonIdentityReference
    private Map<Player, Stat> playerStats = new HashMap<>();

    @Enumerated(EnumType.STRING)
    @Getter @Setter
    private GameStatus gameStatus;

    @ManyToMany
    @JsonIdentityReference
    private Set<Player> readyPlayers = new HashSet<>();
}
