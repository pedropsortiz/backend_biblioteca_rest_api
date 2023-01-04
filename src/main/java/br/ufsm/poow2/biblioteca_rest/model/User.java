package br.ufsm.poow2.biblioteca_rest.model;

import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @NotBlank
    private String name;

    @Column(name = "email", unique=true)
    @NotBlank
    private String email;

    @Column(name = "password")
    @NotBlank
    private String password;

    @Column(name = "permission")
    @NotBlank
    private String permission;

    @Column(name = "token")
    private String token;

    public void update(User newValues) {
        this.name = newValues.name;
        this.email = newValues.email;
        this.password = new BCryptPasswordEncoder().encode(newValues.password);
        this.permission = newValues.permission;
    }

}
