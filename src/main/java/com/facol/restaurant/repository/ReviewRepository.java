package com.facol.restaurant.repository;

import com.facol.restaurant.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    List<ReviewEntity> findAllByRestaurantId(long restaurantId);
}
