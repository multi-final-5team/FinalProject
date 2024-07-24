package com.multi.happytails.upload.model.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UploadDto {
    private MultipartFile file;
    private long imageNo;
    private String imageName;
    private String storedFileName;
    private String categoryCode;
    private long foreignNo;
}
