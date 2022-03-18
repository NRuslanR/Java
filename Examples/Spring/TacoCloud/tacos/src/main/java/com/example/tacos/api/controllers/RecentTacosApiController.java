package com.example.tacos.api.controllers;

import java.util.List;

import com.example.tacos.api.resources.TacoModel;
import com.example.tacos.api.resources.assemblers.TacoModelAssembler;
import com.example.tacos.data.jpa.TacoRepository;
import com.example.tacos.domain.Taco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RepositoryRestController
public class RecentTacosApiController {

    private final TacoRepository tacoRepository;
    private final TacoModelAssembler tacoModelAssembler;

    @Autowired
    public RecentTacosApiController(
        TacoRepository tacoRepository,
        TacoModelAssembler tacoModelAssembler
    )
    {
        this.tacoRepository = tacoRepository;
        this.tacoModelAssembler = tacoModelAssembler;
        
    }

    @GetMapping(path = "/tacos/recent", produces = "application/hal+json")
    public ResponseEntity<CollectionModel<TacoModel>> GetRecentTacos()
    {
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Direction.DESC, "createdAt"));

        List<Taco> recentTacos = tacoRepository.findAll(pageRequest).getContent();

        CollectionModel<TacoModel> model = tacoModelAssembler.toCollectionModel(recentTacos);

        model.add(
            WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(RecentTacosApiController.class).GetRecentTacos()
            )
            .withSelfRel()
        );

        return ResponseEntity.ok(model);
    }
}
