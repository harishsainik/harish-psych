package com.psych.harishpsych.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name = "games")
public class Game extends Auditable{
    @ManyToMany
    @Getter @Setter
    private Set<Player> players;

    @Getter @Setter
    @Enumerated(EnumType.STRING)
    private GameMode gameMode;

    @Getter @Setter
    private InternalError numRounds;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    @Getter @Setter
    private List<Round> rounds = new ArrayList<>();

    @Getter @Setter
    private Boolean hasEllen = false;

    @ManyToOne
    @Getter @Setter
    @NotNull
    private Player leader;

    @ManyToMany(cascade = CascadeType.ALL)
    @Getter @Setter
    private Map<Player, Stat> playerStats = new HashMap<>();

    @Enumerated(EnumType.STRING)
    @Getter @Setter
    private GameStatus gameStatus;

    @ManyToMany
    private Set<Player> readyPlayers = new HashSet<>();
}
