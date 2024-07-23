package com.multi.happytails.shop.model.dao;

import com.multi.happytails.shop.model.dto.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewDAO {
    void insertReview(ReviewDTO reviewDTO);

    void updateReview(ReviewDTO reviewDTO);

    void deleteReview(ReviewDTO reviewDTO);

    List<ReviewDTO> reviewList(ReviewDTO reviewDTO);
}
