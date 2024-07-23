package com.multi.happytails.community.controller;

import com.multi.happytails.community.service.DogloveService;
import com.multi.happytails.community.model.dto.DogloveDTO;
import com.multi.happytails.upload.model.dto.UploadDto;
import com.multi.happytails.upload.service.UploadService;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/community")
public class DogloveController {


    @Autowired
    private DogloveService dogloveService;

    @Autowired
    private UploadService uploadService;

    final String IMAGE_CODE = "L";

    final String categoryCode = "DOGLOVE_CODE";


    // list.html 보여줌
    @GetMapping
    public String list() {
        return "community/list";
    }

    // 게시글 목록
    @GetMapping("/doglove")
    public String dogloveList(
            @RequestParam(value = "sort", defaultValue = "date") String sort,
            Model model) {

        List<DogloveDTO> dogloves;
        if ("recommendCount".equals(sort)) {
            dogloves = dogloveService.findAllSortedByRecommendation();
        } else {
            dogloves = dogloveService.findAllSortedByDate();
        }

        model.addAttribute("dogloves", dogloves);
        model.addAttribute("sort", sort);
        return "community/doglovelist";
    }

    // 게시글 상세 페이지
    @GetMapping("/doglove/{dogloveNo}")
    public String dogloveDetail(@PathVariable("dogloveNo") Long dogloveNo, Model model) {
        DogloveDTO doglove = dogloveService.findById(dogloveNo);
        System.out.println(uploadService.uploadSelect(IMAGE_CODE, dogloveNo));

        if (doglove != null) {
            model.addAttribute("doglove", doglove);
            return "community/dogloveDetail";
        } else {
            return "redirect:/community/doglove";
        }

    }

    @GetMapping("/doglove/create")
    public String dogloveCreateForm() {
        return "community/doglovecreate";
    }

    //게시글 저장
    @PostMapping("/doglove")
    public String savePost(@ModelAttribute DogloveDTO dogloveDTO,
                           @RequestParam("imageFiles") @Nullable List<MultipartFile> imageFiles,
                           HttpSession session, Principal principal) {

        String userId = principal.getName();
        // String userId = (String) session.getAttribute("USER_ID");
        if (userId == null) {
            return "redirect:/member/login";
        }
        dogloveDTO.setUserId(userId);

        //게시판 카테고리 코드
        dogloveDTO.setCategoryCode(categoryCode);
        dogloveDTO.setCreateTime(LocalDateTime.now());
        System.out.println("---------------------------------------------------" + imageFiles); //null

        UploadDto uploadDto = new UploadDto();
        uploadDto.setForeignNo(dogloveService.dogloveInsert(dogloveDTO));

        //이미지 카테고리 코드
        uploadDto.setCategoryCode(IMAGE_CODE); // 이미지 카테고리 코드
        if (imageFiles != null && !imageFiles.isEmpty()) {
            for (int i = 0; i < imageFiles.size(); i++) {
                uploadDto.setFile(imageFiles.get(i));
                uploadService.uploadInsert(uploadDto);
            }
        }
        return "redirect:/community/doglove";
    }


/*
    @PostMapping("/doglove/{dogloveNo}/recommend")
    public String recommendPost(@PathVariable("dogloveNo") Long dogloveNo) {
        dogloveService.addRecommendation(dogloveNo);
        return "redirect:/community/doglove";

    }*/
}