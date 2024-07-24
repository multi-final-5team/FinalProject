package com.multi.happytails.shop.controller;

import com.multi.happytails.shop.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sales")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;
}
