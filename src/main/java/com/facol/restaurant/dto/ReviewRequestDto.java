package com.facol.restaurant.dto;

public record ReviewRequestDto (Long userId, Long restaurantId, String reviewText, Double rating){
}
