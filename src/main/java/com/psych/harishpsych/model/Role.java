package com.psych.harishpsych.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "roles")
public class Role extends Auditable{
    @NotBlank
    @Getter @Setter
    @Column(unique =  true)
    private String name;

    @Getter @Setter
    @NotBlank
    private String description;
}
