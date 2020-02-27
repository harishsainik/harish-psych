package com.psych.harishpsych.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User extends Auditable {
    @Getter @Setter
    @Email
    @NotBlank
    @Column(unique = true)
    private String email;

    @Getter
    @NotBlank
    private String saltedHashedPassword;

    public void setSaltedHashedPassword(String value){
        this.saltedHashedPassword = new BCryptPasswordEncoder(5).encode(value);
    }

    @Getter @Setter
    @ManyToMany(fetch = FetchType.EAGER)
    Set<Role> roles = new HashSet<>();

    public User(){

    }

    public User(User user){
        email = user.getEmail();
        saltedHashedPassword = user.getSaltedHashedPassword();
        roles = user.getRoles();
    }
}
