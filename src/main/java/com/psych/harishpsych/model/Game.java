package com.psych.harishpsych.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.psych.harishpsych.Utils;
import com.psych.harishpsych.exception.InvalidGameActionException;
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
    @JsonIdentityReference
    private Set<Player> players = new HashSet<>();

    @Getter @Setter
    @Enumerated(EnumType.STRING)
    @NotNull
    private GameMode gameMode;

    @Getter @Setter
    private Integer numRounds;

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

    public Game() {
    }

    public Game(@NotNull GameMode gameMode, Integer numRounds, Boolean hasEllen, @NotNull Player leader) {
        this.gameMode = gameMode;
        this.numRounds = numRounds;
        this.hasEllen = hasEllen;
        this.leader = leader;
        players.add(leader);
    }

    public void addPlayer(Player player) throws InvalidGameActionException {
        if(!gameStatus.equals(GameStatus.PLAYERS_JOINING)){
            throw new InvalidGameActionException("Can't join after the game has started.");
        }
        players.add(player);
    }

    public void removePlayer(Player player) throws InvalidGameActionException {
        if(!players.contains(player)){
            throw new InvalidGameActionException("No such player in game.");
        }
        players.remove(player);
        if(players.size()==0 || (players.size()==1 && !gameStatus.equals(GameStatus.PLAYERS_JOINING))){
            endGame();
        }
    }

    public void startGame(Player player) throws InvalidGameActionException {
        if(!player.equals(leader)){
            throw new InvalidGameActionException("Only leader can start the game.");
        }
        startNewRound();
    }

    private void startNewRound() {
        gameStatus = GameStatus.SUBMITTING_ANSWERS;
        Question question = Utils.getRandomQuestion(gameMode);
        Round round = new Round(this, question, rounds.size()+1);
        if(hasEllen){
            round.setEllenAnswer(Utils.getRandomEllenAnswer(question));
        }
        rounds.add(round);
    }

    public void submitAnswer(Player player, String answer) throws InvalidGameActionException {
        if(!players.contains(player)){
            throw new InvalidGameActionException("No such player in game.");
        }
        if(answer.length() == 0)
            throw new InvalidGameActionException("Answer cannot be empty.");
        if(!gameStatus.equals(GameStatus.SUBMITTING_ANSWERS))
            throw new InvalidGameActionException("Game is not accepting answers at present.");
        Round currentRound = getCurrentRound();
        currentRound.submitAnswer(player, answer);
        if(currentRound.allAnswersSubmitted(players.size()))
            gameStatus = GameStatus.SELECTING_ANSWERS;
    }

    public void selectAnswer(Player player, PlayerAnswer playerAnswer) throws InvalidGameActionException {
        if(!players.contains(player)){
            throw new InvalidGameActionException("No such player in game.");
        }
        if(!gameStatus.equals(GameStatus.SELECTING_ANSWERS))
            throw new InvalidGameActionException("Game is not selecting answers at present.");
        Round currentRound = getCurrentRound();
        currentRound.selectAnswer(player, playerAnswer);
        if(currentRound.allAnswersSelected(players.size())){
            if(rounds.size() < numRounds)
                gameStatus = GameStatus.WAITING_FOR_READY;
            else
                endGame();
        }
    }

    public void playerIsReady(Player player) throws InvalidGameActionException {
        if(!players.contains(player)){
            throw new InvalidGameActionException("No such player in game.");
        }
        if(!gameStatus.equals(GameStatus.WAITING_FOR_READY))
            throw new InvalidGameActionException("Game is not waiting for player to get ready at present.");
        readyPlayers.add(player);
    }

    public void playerIsNotReady(Player player) throws InvalidGameActionException {
        if(!players.contains(player)){
            throw new InvalidGameActionException("No such player in game.");
        }
        if(!gameStatus.equals(GameStatus.WAITING_FOR_READY))
            throw new InvalidGameActionException("Game is not waiting for player to get ready at present.");
        readyPlayers.add(player);
        if(readyPlayers.size() == players.size())
            startNewRound();
    }

    private Round getCurrentRound() throws InvalidGameActionException {
        if(rounds.size()==0)
            throw new InvalidGameActionException("Game has not started yet.");
        return rounds.get(rounds.size()-1);
    }

    private void endGame(){
        gameStatus = GameStatus.GAME_ENDED;
    }

    public String getGameState(){
        //todo
        return "game data that will be displayed in frontend.";
    }

}
