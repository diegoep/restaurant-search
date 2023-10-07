package com.diegoep.restaurantsearch.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Restaurant {
    private String name;
    private Integer rating;
    private Long distance;
    private Float price;
    private Integer cuisineId;
}
