package com.psych.harishpsych.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name =  "ellenAnswers")
public class EllenAnswer extends  Auditable{
    @NotNull
    @Getter @Setter
    @ManyToOne
    @JsonBackReference
    private Question question;

    @NotNull
    @Getter @Setter
    private Long voteCount = 0L;

    @NotBlank
    @Getter @Setter
    private String answer;
}

