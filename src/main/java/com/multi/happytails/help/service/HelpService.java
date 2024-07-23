package com.multi.happytails.help.service;

import com.multi.happytails.help.model.dao.HelpMapper;
import com.multi.happytails.help.model.dto.HelpCategoryDto;
import com.multi.happytails.help.model.dto.InquiryDto;
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
}
