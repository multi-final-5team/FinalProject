package com.multi.happytails.help.controller;

import com.multi.happytails.help.model.dto.HelpCategoryDto;
import com.multi.happytails.help.model.dto.InquiryDto;
import com.multi.happytails.help.service.HelpService;
import com.multi.happytails.upload.model.dto.UploadDto;
import com.multi.happytails.upload.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    public void inquiryDetail() {
        System.out.println(uploadService.uploadSelect(UPLOAD_INQUIRY_CODE,5));
        uploadService.uploadDelete(4);
    }

    @GetMapping("/main")
    public void helpMain() {}

    @GetMapping("/getCategory")
    @ResponseBody
    public List<HelpCategoryDto> getCategoryList() {
        return helpService.helpCategorySelectList();
    }

    @PostMapping("/inquiry/write")
    @ResponseBody
    public String inquiryWrite(@ModelAttribute InquiryDto inquiryDto, @RequestParam("imageFiles")List<MultipartFile> imageFiles) {

        // login User
            inquiryDto.setWriterId("admin");
        // login User

        UploadDto uploadDto = new UploadDto();
        uploadDto.setForeignNo(helpService.inquiryInsert(inquiryDto));
        uploadDto.setCategoryCode(UPLOAD_INQUIRY_CODE);

        for (int i = 0; i < imageFiles.size(); i++) {
            uploadDto.setFile(imageFiles.get(i));
            uploadService.uploadInsert(uploadDto);
        }
        
        return "문의 작성이 완료 되었습니다.";
    }


}
