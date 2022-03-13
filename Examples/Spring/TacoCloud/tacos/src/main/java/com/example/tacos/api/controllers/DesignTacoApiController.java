package com.example.tacos.api.controllers;

import java.util.Optional;

import com.example.tacos.api.resources.TacoModel;
import com.example.tacos.api.resources.assemblers.TacoModelAssembler;
import com.example.tacos.data.OrderRepository;
import com.example.tacos.data.jpa.TacoRepository;
import com.example.tacos.domain.Taco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
    private final TacoModelAssembler tacoModelAssembler;
    
    @Autowired
    public DesignTacoApiController(
        TacoRepository tacoRepository,
        TacoModelAssembler tacoModelAssembler
    ) {
        this.tacoRepository = tacoRepository;
        this.tacoModelAssembler = tacoModelAssembler;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<TacoModel>> GetAllTacos()
    {
        Iterable<Taco> tacos = tacoRepository.findAll();

        CollectionModel<TacoModel> tacosModel = tacoModelAssembler.toCollectionModel(tacos);

        tacosModel.add(
            WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(DesignTacoApiController.class).GetAllTacos()
            ).withSelfRel()
        );

        return ResponseEntity.ok(tacosModel);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/recent")
    public ResponseEntity<CollectionModel<TacoModel>> GetRecentTacos()
    {
        PageRequest pageRequest = PageRequest.of(0, 12, Sort.by(Direction.DESC, "createdAt"));

        Iterable<Taco> recentTacos = tacoRepository.findAll(pageRequest).getContent();

        CollectionModel<TacoModel> model = tacoModelAssembler.toCollectionModel(recentTacos);

        model.add(
            WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(
                    DesignTacoApiController.class
                )
                .GetRecentTacos()
            ).withRel("recents")
        );

        return ResponseEntity.ok(model);
    }
    
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<TacoModel> GetTacoById(@PathVariable("id") long tacoId)
    {
        Optional<Taco> taco = tacoRepository.findById(tacoId);

        if (taco.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(tacoModelAssembler.toModel(taco.get()));
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
