package com.facol.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public record RestaurantResponseDto (Long id, String name, String address, String tag){
}
