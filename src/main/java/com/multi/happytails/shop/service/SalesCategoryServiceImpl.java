package com.multi.happytails.shop.service;

import com.multi.happytails.shop.model.dao.SalesCategoryDAO;
import com.multi.happytails.shop.model.dto.SalesCategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesCategoryServiceImpl implements SalesCategoryService {

    @Autowired
    private SalesCategoryDAO salesCategoryDAO;


    @Override
    public List<SalesCategoryDTO> categoryList() {
        return salesCategoryDAO.categoryList();
    }
}
