package com.psych.harishpsych.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "rounds")
public class Round extends  Auditable{
    @ManyToOne
    @Getter @Setter
    @NotNull
    private Game game;

    @ManyToOne
    @Getter @Setter
    @NotNull
    private Question question;

    @NotNull
    @Getter @Setter
    @ManyToOne
    private EllenAnswer ellenAnswer;

    @ManyToMany(cascade = CascadeType.ALL)
    @Getter @Setter
    private Map<Player, PlayerAnswer> playerAnswers = new HashMap<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @Getter @Setter
    private Map<Player, PlayerAnswer> selectedAnswers = new HashMap<>();

    @NotNull
    @Getter @Setter
    private Integer roundNumber;
}
