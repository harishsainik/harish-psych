package com.psych.harishpsych.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "playerAnswers")
public class PlayerAnswer extends Auditable{
    @NotBlank
    @Getter @Setter
    private String answer;

    @NotNull
    @Getter @Setter
    @ManyToOne
    private Round round;

    @NotNull
    @Getter @Setter
    @ManyToOne
    private Player player;
}
