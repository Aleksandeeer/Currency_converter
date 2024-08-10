package org.example.controller;

import org.example.service.Currency_service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {
    Currency_service cur_service;

    public Controller(){
        cur_service = new Currency_service();
        cur_service.setCurrentCurrency();
    }

    @GetMapping("/")
    public String main_page(Model model){
        model.addAttribute("currency_list", cur_service.getCurrencyList());
        return "main_page";
    }
}
