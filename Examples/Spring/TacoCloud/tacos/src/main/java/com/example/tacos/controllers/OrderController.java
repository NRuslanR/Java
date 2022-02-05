package com.example.tacos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.security.Principal;
import java.util.Arrays;

import javax.validation.Valid;

import com.example.tacos.data.jpa.OrderRepository;

import com.example.tacos.domain.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository)
    {
        this.orderRepository = orderRepository;
    }

    @ModelAttribute("order")
    public com.example.tacos.domain.Order order()
    {
        return new com.example.tacos.domain.Order();
    }

    @GetMapping("/current")
    public String getOrderForm(Model model) {

        return "order";
    }

    @PostMapping
    public String processOrder(
            @Valid com.example.tacos.domain.Order order,
            Errors errors,
            SessionStatus sessionStatus,
            @AuthenticationPrincipal User user
    ) {

        if (errors.hasErrors())
            return "order";
        
        log.info("order's tacos: " + Arrays.toString(order.getTacos().toArray()));
  
        order.setUser(user);
        
        orderRepository.save(order);

        sessionStatus.setComplete();
        
        log.info("Processed order: " + order);

        return "redirect:/";
    }
}
