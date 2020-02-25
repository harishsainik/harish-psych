package com.psych.harishpsych.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
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
    @JsonBackReference
    private Round round;

    @NotNull
    @Getter @Setter
    @ManyToOne
    @JsonIdentityReference
    private Player player;
}
