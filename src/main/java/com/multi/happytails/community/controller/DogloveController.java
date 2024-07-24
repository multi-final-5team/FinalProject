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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * packageName    : com.multi.happytails.community.controller
 * fileName       : DogloveController
 * author         : Nayoung Yeo
 * date           : 2024-07-24
 * description    : Doglovecontroller.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-07-24        Nayoung Yeo       최초 생성
 */

@Controller
@RequestMapping("/community")
public class DogloveController {


    @Autowired
    private DogloveService dogloveService;

    @Autowired
    private UploadService uploadService;

    /**
     * 이미지 코드
     */
    final String IMAGE_CODE = "L";

    /**
     * 게시판 카테고리 코드
     */
    final String categoryCode = "DOGLOVE_CODE";


    /**
     * methodName : list
     * author : Nayoung Yeo
     * description :
     *
     * @return string
     */

    @GetMapping
    public String list() {
        return "community/list";
    }

    /**
     * Instantiates a new Doglove controller.
     *
     * @param dogloveService the doglove service
     */
    public DogloveController(DogloveService dogloveService) {
        this.dogloveService = dogloveService;

    }

    /**
     * methodName : dogloveList
     * author : Nayoung Yeo
     * description :
     *
     * @param sort
     * @param model
     * @return string
     */
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

    /**
     * methodName : dogloveDetail
     * author : Nayoung Yeo
     * description :
     *
     * @param dogloveNo no
     * @param model
     * @return string
     */
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

    /**
     * methodName : dogloveCreateForm
     * author : Nayoung Yeo
     * description :
     *
     * @return string
     */
    @GetMapping("/doglove/create")
    public String dogloveCreateForm() {
        return "community/doglovecreate";
    }

    /**
     * methodName : savePost
     * author : Nayoung Yeo
     * description :
     *
     * @param imageFiles   dto
     * @param dogloveDTO     files
     * @param session
     * @param principal
     * @return string
     */

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

        UploadDto uploadDto = new UploadDto();
        uploadDto.setForeignNo(dogloveService.dogloveInsert(dogloveDTO));

        uploadDto.setCategoryCode(IMAGE_CODE); // 이미지 카테고리 코드
        if (imageFiles != null && !imageFiles.isEmpty()) {
            for (int i = 0; i < imageFiles.size(); i++) {
                uploadDto.setFile(imageFiles.get(i));
                uploadService.uploadInsert(uploadDto);
            }
        }
        return "redirect:/community/doglove";
    }

    /**
     * methodName : recommendPost
     * author : Nayoung Yeo
     * description :
     *
     * @param dogloveNo
     * @param session
     * @return string
     */

    @PostMapping("/doglove/{dogloveNo}/recommend")
    public String recommendPost(@PathVariable("dogloveNo") Long dogloveNo, HttpSession session) {
        // 추천 수 증가
        dogloveService.incrementRecommendCount(dogloveNo);

        // 게시글 상세 페이지로 리다이렉트
        return "redirect:/community/doglove/" + dogloveNo;
    }
}
