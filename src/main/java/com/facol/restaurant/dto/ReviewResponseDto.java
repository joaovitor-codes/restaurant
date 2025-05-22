package com.facol.restaurant.dto;


public record ReviewResponseDto(Long id, Long userId, String restaurantName, String author, String reviewText, double rating) {
}
