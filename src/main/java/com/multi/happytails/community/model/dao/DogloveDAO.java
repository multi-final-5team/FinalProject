package com.multi.happytails.community.model.dao;

import com.multi.happytails.community.model.dto.DogloveDTO;
import com.multi.happytails.help.model.dto.HelpCategoryDto;
import com.multi.happytails.help.model.dto.InquiryDto;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface DogloveDAO {

    List<DogloveDTO> findAll(String sortOrder);

    DogloveDTO findById(Long dogloveNo);

    void update(DogloveDTO dogloveDTO);

    void delete(Long dogloveNo);

    void dogloveInsert(DogloveDTO dogloveDTO);

    public int getCurrentDogloveNo();

    void incrementRecommendCount(Long dogloveNo);
}
