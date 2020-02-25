package com.psych.harishpsych.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonBackReference
    private Game game;

    @ManyToOne
    @Getter @Setter
    @NotNull
    @JsonIdentityReference
    private Question question;

    @NotNull
    @Getter @Setter
    @ManyToOne
    @JsonIdentityReference
    private EllenAnswer ellenAnswer;

    @ManyToMany(cascade = CascadeType.ALL)
    @Getter @Setter
    @JsonManagedReference
    private Map<Player, PlayerAnswer> playerAnswers = new HashMap<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @Getter @Setter
    @JsonManagedReference
    private Map<Player, PlayerAnswer> selectedAnswers = new HashMap<>();

    @NotNull
    @Getter @Setter
    private Integer roundNumber;
}
