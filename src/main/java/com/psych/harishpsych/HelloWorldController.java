package com.psych.harishpsych;

import com.psych.harishpsych.model.Game;
import com.psych.harishpsych.model.GameMode;
import com.psych.harishpsych.model.Player;
import com.psych.harishpsych.model.Question;
import com.psych.harishpsych.repository.GameRepository;
import com.psych.harishpsych.repository.PlayerRepository;
import com.psych.harishpsych.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dev-test")
public class HelloWorldController {
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private GameRepository gameRepository;

    @GetMapping("/")
    public String hello(){
        return "Hello World.";
    }

    @GetMapping("/populate")
    public String populateDB(){
        for(Player player: playerRepository.findAll()) {
            player.getGames().clear();
            playerRepository.save(player);
        }
        gameRepository.deleteAll();
        playerRepository.deleteAll();
        questionRepository.deleteAll();
        Player saitama = new Player.Builder()
                .email("saitama@gmail.com")
                .alias("One punch man")
                .saltedHashedPassword("topsecret")
                .build();
        playerRepository.save(saitama);

        Player sachin = new Player.Builder()
                .email("sachin@gmail.com")
                .alias("little master")
                .saltedHashedPassword("magicalworld")
                .build();
        playerRepository.save(sachin);
        questionRepository.save(new Question("When was the first computer invented?", "1940s", GameMode.IS_THIS_A_FACT));
        Game game = new Game();
        game.getPlayers().add(saitama);
        game.setLeader(sachin);
        game.setGameMode(GameMode.IS_THIS_A_FACT);
        gameRepository.save(game);
        return "populated data.";
    }

    @GetMapping("/questions")
    public List<Question> getAllQuestions(){
        return questionRepository.findAll();
    }

    @GetMapping("/question/{id}")
    public Question getQuestionById(@PathVariable(name = "id") Long id){
        return questionRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @GetMapping("/players")
    public List<Player> getAllPlayers(){
        return playerRepository.findAll();
    }

    @GetMapping("/player/{id}")
    public Player getPlayerById(@PathVariable(name = "id") Long id){
        return playerRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }
    @GetMapping("/games")
    public List<Game> getAllGames(){
        return gameRepository.findAll();
    }

    @GetMapping("/game/{id}")
    public Game getGameById(@PathVariable(name = "id") Long id){
        return gameRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }
    //TODO:
    //Games
    //Players
    //Rounds
    //Content-Writers
}
