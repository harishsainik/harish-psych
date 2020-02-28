package com.psych.harishpsych.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.psych.harishpsych.exception.InvalidGameActionException;
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

    public Round() {
    }

    public Round(@NotNull Game game, @NotNull Question question, @NotNull Integer roundNumber) {
        this.game = game;
        this.question = question;
        this.roundNumber = roundNumber;
    }

    public void submitAnswer(Player player, String answer) throws InvalidGameActionException {
        if(playerAnswers.containsKey(player))
            throw new InvalidGameActionException("Player has already submitted answer.");
        for(PlayerAnswer existingAnswer: playerAnswers.values())
            if(answer.equals(existingAnswer.getAnswer()))
                throw new InvalidGameActionException("Duplicate answer");
        playerAnswers.put(player, new PlayerAnswer(answer, this, player));
    }

    public boolean allAnswersSubmitted(int numPlayers) {
        return playerAnswers.size() == numPlayers;
    }

    public void selectAnswer(Player player, PlayerAnswer playerAnswer) throws InvalidGameActionException {
        if(playerAnswers.containsKey(player))
            throw new InvalidGameActionException("Player has already selected answer.");
        if(playerAnswer.getPlayer().equals(player))
            throw new InvalidGameActionException("Can't select your own answer.");
        selectedAnswers.put(player, playerAnswer);
    }

    public boolean allAnswersSelected(int numPlayers) {
        return selectedAnswers.size() == numPlayers;
    }
}
