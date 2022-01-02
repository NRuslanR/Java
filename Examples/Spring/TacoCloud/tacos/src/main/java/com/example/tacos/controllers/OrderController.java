package com.example.tacos.controllers;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import com.example.tacos.domain.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {

    @GetMapping("/current")
    public String getOrderForm(Model model) {

        model.addAttribute("order", new com.example.tacos.domain.Order());

        return "order";
    }

    @PostMapping
    public String processOrder(@Valid @ModelAttribute com.example.tacos.domain.Order order, Errors errors) {

        if (errors.hasErrors())
            return "order";

        log.info("Processed order: " + order);

        return "redirect:/";
    }
}
