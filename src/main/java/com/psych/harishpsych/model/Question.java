package com.psych.harishpsych.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "questions")
public class Question extends Auditable{
    @NotBlank
    @Getter @Setter
    private String question;

    @NotBlank
    @Getter @Setter
    private String correctAnswer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
    @Getter @Setter
    @JsonManagedReference
    private Set<EllenAnswer> ellenAnswers =  new HashSet<>();

    @NotNull
    @Enumerated(EnumType.STRING)
    @Getter @Setter
    private GameMode gameMode;

    public Question(){

    }

    public Question(@NotBlank String question, @NotBlank String correctAnswer, @NotNull GameMode gameMode) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.gameMode = gameMode;
    }
}
