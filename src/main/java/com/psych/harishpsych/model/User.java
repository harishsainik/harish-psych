package com.psych.harishpsych.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User extends Auditable {
    @Getter
    @Email
    @NotBlank
    @Column(unique = true)
    private String email;

    @Getter
    @NotBlank
    private String saltedHashedPassword;

    @Getter @Setter
    @ManyToMany
    Set<Role> roles = new HashSet<>();

    public void setEmail(String email){
        this.email = email;
    }

    public void setSaltedHashedPassword(String saltedHashedPassword) {
        this.saltedHashedPassword = saltedHashedPassword;
    }
}
