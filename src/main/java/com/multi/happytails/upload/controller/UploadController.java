package com.multi.happytails.upload.controller;

import com.multi.happytails.upload.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
/*
    업로드 부분에 필요한 부분이 있으시면 말해주세요
 */
@Controller
public class UploadController {

    @Autowired
    UploadService uploadService;

    @Value("${file.dir}")
    String fileDir;

    // 이미지 불러오는 부분 <img src='/image/categoryCode/fileName'>으로 불러오면 됨
    @GetMapping({"/images/{categoryCode}/{fileName}"})
    @ResponseBody
    public ResponseEntity<Resource> downloadImage(@PathVariable("fileName") String fileName,
                                                  @PathVariable("categoryCode")  String categoryCode) {
        System.out.println(fileName + categoryCode);
        String projectPath = System.getProperty("user.dir");
        String directory = projectPath + this.fileDir;
        String filePath = directory + categoryCode + "/" +fileName;
        File file = new File(filePath);
        Resource resource = new FileSystemResource(file);
        String mimeType;
        switch (fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase()) {
            case "jpg":
            case "jpeg":
                mimeType = "image/jpeg";
                break;
            case "png":
                mimeType = "image/png";
                break;
            case "gif":
                mimeType = "image/gif";
                break;
            default:
                mimeType = "application/octet-stream";
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", mimeType);
        return new ResponseEntity(resource, headers, HttpStatus.OK);
    }
}
