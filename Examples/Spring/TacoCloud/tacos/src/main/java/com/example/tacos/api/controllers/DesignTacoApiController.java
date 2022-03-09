package com.example.tacos.api.controllers;

import java.util.Optional;

import com.example.tacos.data.OrderRepository;
import com.example.tacos.data.jpa.TacoRepository;
import com.example.tacos.domain.Taco;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = { "/api/design" }, produces = { "application/json" })
@CrossOrigin(origins = "*")
public class DesignTacoApiController {
    
    private final TacoRepository tacoRepository;

    @Autowired
    public DesignTacoApiController(
        TacoRepository tacoRepository
    ) {
        this.tacoRepository = tacoRepository;
    }

    @GetMapping
    public Iterable<Taco> GetAllTacos()
    {
        return tacoRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/recent")
    public Iterable<Taco> GetRecentTacos()
    {
        PageRequest pageRequest = PageRequest.of(0, 12, Sort.by(Direction.DESC, "createdAt"));

        return tacoRepository.findAll(pageRequest).getContent();
    }
    
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<Taco> GetTacoById(@PathVariable("id") long tacoId)
    {
        Optional<Taco> taco = tacoRepository.findById(tacoId);

        return ResponseEntity.of(taco);
    }

    @RequestMapping(
        method = RequestMethod.POST, 
        path = "", 
        consumes = "application/json"
    )
    public Taco CreateTaco(@RequestBody Taco taco)
    {
        return tacoRepository.save(taco);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{tacoId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteTaco(@PathVariable("tacoId") long tacoId)
    {
        try  {
            
            tacoRepository.deleteById(tacoId);

        } catch (EmptyResultDataAccessException e) {
            
        }
    }
}
