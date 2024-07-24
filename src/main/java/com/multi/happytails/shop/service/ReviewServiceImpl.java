package com.multi.happytails.shop.service;

import com.multi.happytails.shop.model.dao.ReviewDAO;
import com.multi.happytails.shop.model.dto.ReviewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    private ReviewDAO reviewDAO;

    @Override
    public void insertReview(ReviewDTO reviewDTO) {
        reviewDAO.insertReview(reviewDTO);
    }

    @Override
    public void updateReview(ReviewDTO reviewDTO) {
        reviewDAO.updateReview(reviewDTO);
    }

    @Override
    public void deleteReview(ReviewDTO reviewDTO) {
        reviewDAO.deleteReview(reviewDTO);
    }

    @Override
    public List<ReviewDTO> reviewList(ReviewDTO reviewDTO) {
        return reviewDAO.reviewList(reviewDTO);
    }
}
