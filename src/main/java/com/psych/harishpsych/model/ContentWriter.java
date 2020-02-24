package com.psych.harishpsych.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "contentwriters")
public class ContentWriter extends Employee {
}
