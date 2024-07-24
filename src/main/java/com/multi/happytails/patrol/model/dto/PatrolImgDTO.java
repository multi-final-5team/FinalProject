package com.multi.happytails.patrol.model.dto;

import com.multi.happytails.upload.model.dto.UploadDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PatrolImgDTO {
    List<UploadDto> uploadDtos;
    List<PatrolDTO> list;;
}
