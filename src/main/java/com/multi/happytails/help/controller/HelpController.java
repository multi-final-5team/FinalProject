package com.multi.happytails.help.controller;

import com.multi.happytails.help.model.dto.HelpCategoryDto;
import com.multi.happytails.help.model.dto.InquiryDto;
import com.multi.happytails.help.model.dto.PageDto;
import com.multi.happytails.help.model.dto.QuestionDto;
import com.multi.happytails.help.service.HelpService;
import com.multi.happytails.upload.model.dto.UploadDto;
import com.multi.happytails.upload.service.UploadService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/help")
public class HelpController {

    @Autowired
    HelpService helpService;

    @Autowired

    UploadService uploadService;

    final String UPLOAD_INQUIRY_CODE = "I";

    @GetMapping("/inquiry/write")
    public void inquiryWriteForm() {}
    @GetMapping("/inquiry/detail")
    public void inquiryDetail() {}
    @GetMapping("/inquiry/list")
    public void inquiryList() {}
    @GetMapping("/main")
    public void helpMain() {}

    @GetMapping("/getCategory")
    @ResponseBody
    public List<HelpCategoryDto> getCategoryList() {
        return helpService.helpCategorySelectList();
    }

    @PostMapping("/inquiry/write")
    @ResponseBody
    public String inquiryWrite(@ModelAttribute InquiryDto inquiryDto, @RequestParam(value = "imageFiles") @Nullable List<MultipartFile> imageFiles, Principal principal) {

        // login User
            inquiryDto.setWriterId(principal.getName());
        // login User
        if (imageFiles != null && !imageFiles.isEmpty()) {
            UploadDto uploadDto = new UploadDto();
            uploadDto.setForeignNo(helpService.inquiryInsert(inquiryDto));
            uploadDto.setCategoryCode(UPLOAD_INQUIRY_CODE);

            for (int i = 0; i < imageFiles.size(); i++) {
                uploadDto.setFile(imageFiles.get(i));
                uploadService.uploadInsert(uploadDto);
            }
        }
        
        return "문의 작성이 완료 되었습니다.";
    }

    @GetMapping("/question/list")
    @ResponseBody
    public List<QuestionDto> questionList(@RequestParam("categoryCode") @Nullable String categoryCode) {
        List<QuestionDto> list = helpService.questionList(categoryCode);
        return list;
    }

    @GetMapping("/inquiry/getList")
    @ResponseBody
    public ResponseEntity<?> getInquiryList(PageDto pageDto
            , @RequestParam(value="nowPage", required=false)String nowPage
            , @RequestParam(value="categoryCode", required=false)String categoryCode) {
        System.out.println(pageDto);
        System.out.println(nowPage);
        System.out.println(categoryCode);
        List<InquiryDto> list = null;

        int total = helpService.inquiryListCount(pageDto, categoryCode);

        if (nowPage == null) {
            nowPage = "1";
        } else if (nowPage == null) {
            nowPage = "1";
        }
        pageDto = new PageDto(total, Integer.parseInt(nowPage),10 ,pageDto.getKeyword(),pageDto.getSearchValue());

        Map<String, Object> response = new HashMap<>();
        response.put("paging", pageDto);
        response.put("viewAll", helpService.getInquiryList(pageDto, categoryCode));

        return ResponseEntity.ok(response);

    }

}
