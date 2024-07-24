package com.multi.happytails.shop.model.dao;

import com.multi.happytails.shop.model.dto.SalesCategoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SalesCategoryDAO {
    List<SalesCategoryDTO> categoryList();
}
