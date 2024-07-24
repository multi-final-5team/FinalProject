package com.multi.happytails.shop.service;

import com.multi.happytails.shop.model.dto.SalesGoodsDTO;

import java.util.List;

public interface SalesService {

    void insertSales(SalesGoodsDTO salesGoodsDTO);

    void updateSales(SalesGoodsDTO salesGoodsDTO);

    List<SalesGoodsDTO> salesList(int page, int pageSize);

    List<SalesGoodsDTO> salesListBusiness(int page, int pageSize, String id);

    void deleteSales(SalesGoodsDTO salesGoodsDTO);

    SalesGoodsDTO selectSales(SalesGoodsDTO salesGoodsDTO);

    int getSalesNo();

    int salesPageCount();
}
