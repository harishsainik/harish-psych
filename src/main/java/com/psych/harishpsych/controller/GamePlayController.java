package com.psych.harishpsych.controller;

import com.psych.harishpsych.exception.InvalidGameActionException;
import com.psych.harishpsych.model.Game;
import com.psych.harishpsych.model.Player;
import com.psych.harishpsych.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequestMapping("/play")
public class GamePlayController {
    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping("/")
    public String play(Authentication authentication){
        return authentication.getName();
    }

    @GetMapping("/submit-answer/{answer}")
    public void submitAnswer(Authentication authentication, @PathVariable(name="answer") String answer) throws InvalidGameActionException {
        Player player = playerRepository.findByEmail(authentication.getName()).orElseThrow(IllegalArgumentException::new);
        player.getCurrentGame().submitAnswer(player, answer);
    }
}
