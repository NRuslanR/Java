package com.example.tacos.api.controllers;

import java.util.Optional;

import com.example.tacos.api.resources.OrderModel;
import com.example.tacos.api.resources.assemblers.OrderModelAssembler;
import com.example.tacos.data.jpa.OrderRepository;
import com.example.tacos.domain.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/orders", produces = "application/json")
@CrossOrigin(origins = "*")
public class OrderApiController {

    private final OrderRepository orderRepository;
    private final OrderModelAssembler orderModelAssembler;

    @Autowired
    public OrderApiController(
        OrderRepository orderRepository,
        OrderModelAssembler orderModelAssembler
    )
    {
        this.orderRepository = orderRepository;
        this.orderModelAssembler = orderModelAssembler;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<CollectionModel<OrderModel>> GetOrders()
    {
        Iterable<Order> orders = orderRepository.findAll();

        CollectionModel<OrderModel> model = orderModelAssembler.toCollectionModel(orders);
            
        model.add(
            WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(OrderApiController.class).GetOrders()
            ).withSelfRel()
        );

        return ResponseEntity.ok(model);
    }
    
    @RequestMapping(method = RequestMethod.GET, path = "/{orderId}")
    public ResponseEntity<OrderModel> GetOrderById(@PathVariable("orderId") long orderId)
    {
        Optional<Order> order = orderRepository.findById(orderId);

        if (order.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(orderModelAssembler.toModel(order.get()));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<Order> UpdateOrder(
        @PathVariable("id") long orderId,
        @RequestBody Order order
    )
    {
        if (order.getId() != orderId)
            return ResponseEntity.of(Optional.empty());

        Optional<Order> existingOrder = orderRepository.findById(orderId);

        if (existingOrder.isEmpty())
            return ResponseEntity.of(Optional.empty());
            
        return new ResponseEntity<>(orderRepository.save(order), HttpStatus.OK);
    }
    
    @RequestMapping(
        method = RequestMethod.PATCH,
        path = "/{id}",
        consumes = "application/json"
    )
    public ResponseEntity<Order> ChangeOrder(
        @PathVariable("id") long id,
        @RequestBody Order order
    )
    {
        if (id != order.getId())
            return ResponseEntity.of(Optional.empty());

        Optional<Order> foundOrder = orderRepository.findById(id);

        if (foundOrder.isEmpty())
            return ResponseEntity.of(Optional.empty());

        Order  existingOrder = foundOrder.get();

        if (order.getCcCvv() != null)
            existingOrder.setCcCvv(order.getCcCvv());

        if (order.getCcExpiration() != null)
            existingOrder.setCcExpiration(order.getCcExpiration());

        if (order.getCcNumber() != null)
            existingOrder.setCcNumber(order.getCcNumber());
        
        if (order.getCity() != null)
            existingOrder.setCity(order.getCity());

        if (order.getState() != null)
            existingOrder.setState(order.getState());

        if (order.getStreet() != null)
            existingOrder.setStreet(order.getState());

        if (order.getZip() != null)
            existingOrder.setZip(order.getZip());

        return ResponseEntity.of(Optional.ofNullable(orderRepository.save(existingOrder)));
    }
    
    @DeleteMapping("/{orderId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") long orderId)
    {
        try {
            
            orderRepository.deleteById(orderId);

        } catch (EmptyResultDataAccessException e) {
            
        }
    }
}
