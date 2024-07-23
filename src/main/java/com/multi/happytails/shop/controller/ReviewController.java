package com.multi.happytails.shop.controller;

import com.multi.happytails.authentication.model.dto.CustomUser;
import com.multi.happytails.shop.model.dto.ReviewDTO;
import com.multi.happytails.shop.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/reviewList")
    @ResponseBody
    public List<ReviewDTO> reviewList(@RequestBody ReviewDTO reviewDTO) {
        return reviewService.reviewList(reviewDTO);
    }
    // List

    @PostMapping("/insertReview")
    public String insertReview(@AuthenticationPrincipal CustomUser customUser, @RequestBody ReviewDTO reviewDTO) {
        String Id = customUser.getId();
        reviewDTO.setId(Id);
        reviewService.insertReview(reviewDTO);
        return "redirect:/sales/salesList"; // adjust the redirect as necessary
    }
    // Create

    @PostMapping("/updateReview")
    public String updateReview(@AuthenticationPrincipal CustomUser customUser, @RequestBody ReviewDTO reviewDTO) {
        String userId = customUser.getId();
        reviewDTO.setId(userId);
        reviewService.updateReview(reviewDTO);
        return "redirect:/sales/salesList";
    }
    // Update

    @GetMapping("/deleteReview")
    public String deleteReview(@RequestParam("reviewNo") int reviewNo,
                               @RequestParam("goodsNo") int goodsNo,
                               @AuthenticationPrincipal CustomUser customUser
                                ) {
        ReviewDTO reviewDTO = new ReviewDTO();
        String Id = customUser.getId();
        reviewDTO.setId(Id);
        reviewDTO.setNo(reviewNo);
        reviewDTO.setGoodsNo(goodsNo);
        reviewService.deleteReview(reviewDTO);
        return "redirect:/sales/salesList";
    }
    // Delete


    //PosetMan

//    @PostMapping("/reviewList")
//    @ResponseBody
//    public List<ReviewDTO> reviewList(@RequestBody ReviewDTO reviewDTO) {
//        return reviewService.reviewList(reviewDTO);
//    }
    //List

//    @PostMapping("/insertReview")
//    public String insertReview(@RequestBody ReviewDTO reviewDTO) {
//
//        String Id = "admin";
//        reviewDTO.setId(Id);
//        reviewService.insertReview(reviewDTO);
//        return "redirect:/sales/salesList";
//    }
    // Create

//    @PostMapping("/updateReview")
//    public String updateReview(@RequestBody ReviewDTO reviewDTO) {
//        String Id = "admin";
//        reviewDTO.setId(Id);
//        reviewService.updateReview(reviewDTO);
//        return "redirect:/sales/salesList";
//    }
    // Update

//    @GetMapping("/deleteReview")
//    public String deleteReview(@RequestBody ReviewDTO reviewDTO) {
//        String Id = "admin";
//        reviewDTO.setId(Id);
//        reviewService.deleteReview(reviewDTO);
//        return "redirect:/sales/salesList";
//    }
    // Delete
}
