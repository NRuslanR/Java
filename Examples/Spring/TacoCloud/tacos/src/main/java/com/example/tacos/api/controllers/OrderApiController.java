package com.example.tacos.api.controllers;

import java.lang.StackWalker.Option;
import java.util.Optional;

import com.example.tacos.data.jpa.OrderRepository;
import com.example.tacos.domain.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

    @Autowired
    public OrderApiController(OrderRepository orderRepository)
    {
        this.orderRepository = orderRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Order>> GetOrders()
    {
        Optional<Iterable<Order>> orders = Optional.ofNullable(orderRepository.findAll());

        return ResponseEntity.of(orders);
    }
    
    @RequestMapping(method = RequestMethod.GET, path = "/{orderId}")
    public ResponseEntity<Order> GetOrderById(@PathVariable("orderId") long orderId)
    {
        return ResponseEntity.of(orderRepository.findById(orderId));
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
