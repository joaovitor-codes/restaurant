package com.facol.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity // anotação do spring, para ele mapear que é um entidade.
@Data // getter e setters
@NoArgsConstructor // construtor vazio
@AllArgsConstructor // construtor cheio
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;

    @OneToMany(mappedBy = "author")
    @JsonIgnore
    private List<ReviewEntity> reviews = new ArrayList<>();
}
