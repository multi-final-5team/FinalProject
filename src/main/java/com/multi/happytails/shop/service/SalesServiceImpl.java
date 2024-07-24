package com.multi.happytails.shop.service;

import com.multi.happytails.shop.model.dao.SalesGoodsDAO;
import com.multi.happytails.shop.model.dto.SalesGoodsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesServiceImpl implements SalesService {

    @Autowired
    private SalesGoodsDAO salesGoodsDAO;

    @Override
    public void insertSales(SalesGoodsDTO salesGoodsDTO) {
        salesGoodsDAO.insertSales(salesGoodsDTO);
    }

    @Override
    public void updateSales(SalesGoodsDTO salesGoodsDTO) {
        salesGoodsDAO.updateSales(salesGoodsDTO);
    }

    @Override
    public List<SalesGoodsDTO> salesList(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return salesGoodsDAO.salesList(offset, pageSize);
    }

    @Override
    public List<SalesGoodsDTO> salesListBusiness(int page, int pageSize, String id) {
        int offset = (page - 1) * pageSize;
        return salesGoodsDAO.salesListBusiness(offset, pageSize, id);
    }

    @Override
    public void deleteSales(SalesGoodsDTO salesGoodsDTO) {
        salesGoodsDAO.deleteSales(salesGoodsDTO);
    }

    @Override
    public SalesGoodsDTO selectSales(SalesGoodsDTO salesGoodsDTO) {
        return salesGoodsDAO.selectSales(salesGoodsDTO);
    }

    @Override
    public int getSalesNo() {
        return salesGoodsDAO.getSalesNo();
    }

    @Override
    public int salesPageCount() {
        return salesGoodsDAO.salesPageCount();
    }
}
