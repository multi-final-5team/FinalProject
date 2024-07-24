package com.multi.happytails.help.model.dao;

import com.multi.happytails.help.model.dto.HelpCategoryDto;
import com.multi.happytails.help.model.dto.InquiryDto;
import com.multi.happytails.help.model.dto.PageDto;
import com.multi.happytails.help.model.dto.QuestionDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HelpMapper {
    public List<HelpCategoryDto> helpCategorySelectList();
    public int inquiryInsert(InquiryDto inquiryDto);
    public int getCurrentInquiryNo();

    public List<QuestionDto> questionList(String categoryCode);

    public int inquiryListCount(@Param("pageDto")PageDto pageDto, @Param("categoryCode")String categoryCode);

    public List<InquiryDto> getInquiryList(@Param("pageDto")PageDto pageDto, @Param("categoryCode")String categoryCode);
}
