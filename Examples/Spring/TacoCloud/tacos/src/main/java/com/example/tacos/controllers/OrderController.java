package com.example.tacos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

import antlr.collections.List;

import java.security.Principal;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.example.tacos.data.jpa.OrderRepository;

import com.example.tacos.domain.*;
import com.example.tacos.props.OrderProps;
import com.example.tacos.services.messaging.OrderMessagingService;
import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
//@ConfigurationProperties(prefix = "taco.orders")
public class OrderController {

    private OrderRepository orderRepository;
    private OrderProps orderProps;
    private OrderMessagingService orderMessagingService;

    @Autowired
    public OrderController(
        OrderRepository orderRepository,
        OrderProps orderProps,
        OrderMessagingService orderMessagingService
    )
    {
        this.orderRepository = orderRepository;
        this.orderProps = orderProps;
        this.orderMessagingService = orderMessagingService;
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
        
        orderMessagingService.sendOrder(order);
        
        log.info("Processed order: " + order);
        
        return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.GET, path="/ordersForUser")
    public String getOrdersForUser(@AuthenticationPrincipal User user, Model model)
    {
        Pageable pageable = PageRequest.of(0, orderProps.getPageSize());

        java.util.List<com.example.tacos.domain.Order> userOrders = 
            orderRepository.findByUserOrderByPlacedAtDesc(user, pageable);

        model.addAttribute("orders", userOrders);
        
        return "ordersForUser";
    }    
}
