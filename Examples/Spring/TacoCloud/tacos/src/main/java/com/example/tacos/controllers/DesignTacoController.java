package com.example.tacos.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;

import com.example.tacos.data.jpa.IngredientRepository;
import com.example.tacos.data.jpa.TacoRepository;
import com.example.tacos.domain.*;
import com.example.tacos.domain.Ingredient.Type;
import com.example.tacos.services.messaging.OrderReceivingService;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;
    private final TacoRepository tacoRepository;

    @Qualifier("orderReceivingService")
    private final OrderReceivingService orderReceivingService;

    @Autowired
    public DesignTacoController(
        IngredientRepository ingredientRepository,
        TacoRepository tacoRepository,
        OrderReceivingService orderReceivingService
    )
    {
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
        this.orderReceivingService = orderReceivingService;
    }

    @ModelAttribute(name = "taco")
    public Taco taco()
    {
        return new Taco();
    }

    @ModelAttribute(name = "order")
    public Order order()
    {
        return new Order();
    }

    @GetMapping
    public String showDesignForm(Model model) {

        List<Ingredient> ingredients = new ArrayList<>();

        ingredientRepository.findAll().forEach(i -> ingredients.add(i));

        log.info("Ingredients: " + Arrays.toString(ingredients.toArray()));

        Type[] types = Ingredient.Type.values();

        for (Type type : types) {
            List<Ingredient> filteredIngredients = filterByType(ingredients, type);

            model.addAttribute(type.toString().toLowerCase(), filteredIngredients);
        }

        model.addAttribute("design", new Taco());

        return "design";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        
        return ingredients.stream().filter(i -> i.getType() == type).collect(Collectors.toList());
    }

    @PostMapping
    public String handleDesign(
        @Valid @ModelAttribute("design") Taco design,
        Errors errors,
        @ModelAttribute("order") Order order
    ) {

        if (errors.hasErrors())
            return "design";

        Taco saved = tacoRepository.save(design);
        
        order.addDesign(saved);

        log.info("Handle Taco design: " + design);

        return "redirect:/orders/current";
    }

    @GetMapping("/msgs/orders")
    public void showOrderMessage() throws Exception
    {
        Order order = orderReceivingService.receiveOrder();

        if (order != null)
            log.info(order.toString());

        else log.info("Order is not found");
    }
}
