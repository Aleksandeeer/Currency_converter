package org.example.controller;

import org.example.service.Currency_service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        model.addAttribute("result_input",
                Math.round(cur_service.getRes_value() * 10000.0) / 10000.0);
        model.addAttribute("input_value", cur_service.getInput_value());
        return "main_page";
    }

    @PostMapping("/converting")
    public String convert(@RequestParam("first_list") String selectedCurrency_1,
                          @RequestParam("second_list") String selectedCurrency_2,
                          @RequestParam("input_currency_amount") String input_value) {
        cur_service.convertCurrency(selectedCurrency_1,
                selectedCurrency_2, input_value);
        return "redirect:/";
    }
}
