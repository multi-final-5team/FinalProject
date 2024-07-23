package com.multi.happytails.shop.service;

import com.multi.happytails.shop.model.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    void insertReview(ReviewDTO reviewDTO);

    void updateReview(ReviewDTO reviewDTO);

    void deleteReview(ReviewDTO reviewDTO);

    List<ReviewDTO> reviewList(ReviewDTO reviewDTO);
}
