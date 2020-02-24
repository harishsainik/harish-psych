package com.psych.harishpsych.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "stats")
public class Stat extends Auditable{
    @Getter @Setter
    private Long gotPsychCount = 0L;

    @Getter @Setter
    private Long psychedOthersCount = 0L;

    @Getter @Setter
    private Long correctAnswersCount = 0L;
}
