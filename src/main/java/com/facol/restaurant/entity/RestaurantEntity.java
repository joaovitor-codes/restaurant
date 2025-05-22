package com.facol.restaurant.entity;

import com.facol.restaurant.entity.Enum.RestaurantEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;

    @Enumerated(EnumType.STRING)
    private RestaurantEnum tag;

    @OneToMany(mappedBy = "restaurant")
    @JsonIgnore
    private List<ReviewEntity> reviews = new ArrayList<>();
}
