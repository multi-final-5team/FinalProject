package com.multi.happytails.shop.model.dao;

import com.multi.happytails.shop.model.dto.SalesGoodsDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * packageName    : com.multi.happytails.shop.model.dao
 * fileName       : SalesGoodsDAO.java
 * author         : ShinHyeoncheol
 * date           : 2024-07-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-07-24        ShinHyeoncheol       최초 생성
 */
@Mapper
public interface SalesGoodsDAO {

    void insertSales(SalesGoodsDTO salesGoodsDTO);

    void updateSales(SalesGoodsDTO salesGoodsDTO);

    List<SalesGoodsDTO> salesList(@Param("offset") int offset, @Param("limit") int limit);

    List<SalesGoodsDTO> salesListBusiness(@Param("offset") int offset, @Param("limit") int limit, @Param("id") String id);

    void deleteSales(SalesGoodsDTO salesGoodsDTO);

    SalesGoodsDTO selectSales(SalesGoodsDTO salesGoodsDTO);

    int getSalesNo();

    int salesPageCount();
}
