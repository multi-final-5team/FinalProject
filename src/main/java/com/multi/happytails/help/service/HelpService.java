package com.multi.happytails.help.service;

import com.multi.happytails.help.model.dao.HelpMapper;
import com.multi.happytails.help.model.dto.HelpCategoryDto;
import com.multi.happytails.help.model.dto.InquiryDto;
import com.multi.happytails.help.model.dto.PageDto;
import com.multi.happytails.help.model.dto.QuestionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HelpService {

    @Autowired
    HelpMapper helpMapper;

    public List<HelpCategoryDto> helpCategorySelectList() {
        return helpMapper.helpCategorySelectList();
    }

    public long inquiryInsert(InquiryDto inquiryDto) {
        helpMapper.inquiryInsert(inquiryDto);
        return helpMapper.getCurrentInquiryNo();
    }

    public List<QuestionDto> questionList(String categoryCode) {
        return helpMapper.questionList(categoryCode);
    }

    public int inquiryListCount(PageDto pageDto, String categoryCode) {
        return helpMapper.inquiryListCount(pageDto, categoryCode);
    }

    public List<InquiryDto> getInquiryList(PageDto pageDto, String categoryCode) {
        System.out.println(helpMapper.getInquiryList(pageDto, categoryCode) + "flfl");
        return helpMapper.getInquiryList(pageDto, categoryCode);
    }
}
