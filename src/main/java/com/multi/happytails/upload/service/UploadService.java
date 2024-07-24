package com.multi.happytails.upload.service;

import com.multi.happytails.upload.model.dao.UploadMapper;
import com.multi.happytails.upload.model.dto.UploadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UploadService {

    String projectPath = System.getProperty("user.dir");
    @Value("${file.dir}")
    String fileDir;

    @Autowired
    UploadMapper uploadMapper;

    // 이미지 파일 넣을때 사용
    // uploadDto에 넣어야 하는 정보 MultipartFile file, categoryCode, foreignNo
    // 예시1 ) uploadService.uploadInsert(UploadDto.builder().categoryCode("ap").foreignNo(2).file(file).build());
    // 예시2 ) UploadDto uploadDto = new UploadDto();
    // uploadDto.setFile(file); uploadDto.setCategoryCode(categoryCode); uploadDto.setForeignNo(foreignNo);
    // uploadService.uploadInsert(uploadDto);
    public int uploadInsert(UploadDto uploadDto) {
        UploadDto uploadDto1 = createUploadImage(uploadDto);

        return uploadMapper.uploadInsert(uploadDto1);
    }

    // 해당 게시글의 저장된 이미지 Dto를 받아옴
    // order by file_no asc로 되어있습니다. 먼저 들어온 이미지가 제일 앞으로 갑니다.
    // categoryCode과 foreignNo을 파라미터 값으로 받아 줌
    // 예시) List<UploadDto> imageList = uploadSelect(categoryCode, foreignNo);
    // SelectOne으로 한개만 받는 것도 하나 더 만들어도 괜찮으나 범용성을 위해서 이런식으로 개발
    public List<UploadDto> uploadSelect(String categoryCode, long foreignNo) {
        UploadDto uploadDto = UploadDto.builder().categoryCode(categoryCode).foreignNo(foreignNo).build();
        System.out.println(uploadDto);
        System.out.println(uploadMapper.uploadSelectList(uploadDto));
        return uploadMapper.uploadSelectList(uploadDto);
    }

    public int uploadDelete(long imageNo) {
        deleteFile(uploadMapper.uploadSelect(imageNo));
        return uploadMapper.uploadDelete(imageNo);
    }

    public int uploadUpdate(long imageNo, MultipartFile multipartFile) {
        UploadDto uploadDto = uploadMapper.uploadSelect(imageNo);
        uploadDto.setFile(multipartFile);
        createUploadImage(uploadDto);
        return uploadMapper.uploadUpdate(uploadDto);
    }

    private UploadDto createUploadImage(UploadDto uploadDto) {
        String fileName = null;
        String storeFileName = null;

        if (uploadDto.getFile() != null) {
            fileName = uploadDto.getFile().getOriginalFilename();
            String uuid = UUID.randomUUID().toString();
            int extIndex = fileName.lastIndexOf(".") + 1;
            String ext = fileName.substring(extIndex);
            storeFileName = uuid + "." + ext;
            saveFile(uploadDto.getFile(), storeFileName, uploadDto.getCategoryCode());

            uploadDto.setImageName(fileName);
            uploadDto.setStoredFileName(storeFileName);
        }

        return uploadDto;
    }

    private String saveFile(MultipartFile file, String storeFileName, String categoryCode) {

        categoryCode = "/" + categoryCode + "/";
        // 파일 저장 경로
        String filePath = projectPath + fileDir + categoryCode + storeFileName;


        // 파일 디렉토리가 없으면 생성
        File directory = new File(projectPath + fileDir + categoryCode);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // 파일 저장
        try {
            file.transferTo(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filePath;
    }

    private void deleteFile(UploadDto uploadDto) {

        try {
            if (uploadDto.getStoredFileName() != null) {
                Path filePath = Paths.get(projectPath + fileDir + uploadDto.getCategoryCode() + uploadDto.getStoredFileName());
                System.out.println(filePath);
                Files.deleteIfExists(filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
