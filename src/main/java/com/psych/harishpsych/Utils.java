package com.psych.harishpsych;

import com.psych.harishpsych.config.ApplicationContextProvider;
import com.psych.harishpsych.config.SpringConfiguration;
import com.psych.harishpsych.model.EllenAnswer;
import com.psych.harishpsych.model.GameMode;
import com.psych.harishpsych.model.Question;
import com.psych.harishpsych.repository.EllenAnswerRepository;
import com.psych.harishpsych.repository.QuestionRepository;

public class Utils {
    private static QuestionRepository questionRepository;
    private static EllenAnswerRepository ellenAnswerRepository;

    static {
        questionRepository = (QuestionRepository) ApplicationContextProvider
                .getApplicationContext()
                .getBean("questionRepository");
        ellenAnswerRepository = (EllenAnswerRepository) ApplicationContextProvider
                .getApplicationContext()
                .getBean("ellenAnswerRepository");;
    }

    public static Question getRandomQuestion(GameMode gameMode) {
        return questionRepository.getRandomQuestion(gameMode);
    }

    public static EllenAnswer getRandomEllenAnswer(Question question) {
        return ellenAnswerRepository.getRandomEllenAnswer(question);
    }
}
