package com.example.tacos.api.controllers;

import java.util.Comparator;
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
import org.springframework.hateoas.server.reactive.WebFluxLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

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

        CollectionModel<TacoModel> model = CreateTacoModels(tacos);

        return ResponseEntity.ok(model);
    }

    @GetMapping(path = "/async")
    public Mono<ResponseEntity<CollectionModel<TacoModel>>> GetAllTacosAsync()
    {
        return 
            Flux.defer(
                () -> Flux.fromIterable(tacoRepository.findAll())
            )
            .flatMap(
                t -> Mono.just(tacoModelAssembler.toModel(t))
            )
            .collectList()
            .flatMap(l -> Mono.just(CollectionModel.of(l)))
            .flatMap(
                model -> 
                    WebFluxLinkBuilder.linkTo(
                        WebFluxLinkBuilder.methodOn(
                            DesignTacoApiController.class
                        ).GetAllTacosAsync()
                    ).withSelfRel()
                    .toMono()
                    .map(link -> ResponseEntity.ok(model.add(link)))
            );
    }

    @RequestMapping(method = RequestMethod.GET, path = "/recent")
    public ResponseEntity<CollectionModel<TacoModel>> GetRecentTacos()
    {
        PageRequest pageRequest = PageRequest.of(0, 12, Sort.by(Direction.DESC, "createdAt"));

        Iterable<Taco> recentTacos = tacoRepository.findAll(pageRequest).getContent();
        
        CollectionModel<TacoModel> model =  CreateTacoModels(recentTacos);

        return ResponseEntity.ok(model);
    }

    @RequestMapping(path = "/recent/async", method = RequestMethod.GET)
    public Mono<ResponseEntity<CollectionModel<TacoModel>>> GetRecentTacosAsync()
    {
        return
            Flux.defer(
                () -> Flux.fromIterable(tacoRepository.findAll())
            )
            .take(12)
                .sort(Comparator.comparing(Taco::getCreatedAt).reversed())
            .flatMap(t -> Mono.just(tacoModelAssembler.toModel(t)))
            .collectList()
            .flatMap(models -> WebFluxLinkBuilder.linkTo(
                WebFluxLinkBuilder.methodOn(DesignTacoApiController.class).GetRecentTacosAsync()
                ).withRel("recents")
                .toMono()
                .flatMap(l -> Mono.just(CollectionModel.of(models).add(l)))
            )
            .flatMap(model -> Mono.just(ResponseEntity.ok(model)));
    }

    private CollectionModel<TacoModel> CreateTacoModels(Iterable<Taco> recentTacos) {
        
        CollectionModel<TacoModel> model = tacoModelAssembler.toCollectionModel(recentTacos);

        model.add(
            WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(
                    DesignTacoApiController.class
                )
                .GetRecentTacos()
            ).withRel("recents")
        );

        return model;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<TacoModel> GetTacoById(@PathVariable("id") long tacoId)
    {
        Optional<Taco> taco = tacoRepository.findById(tacoId);

        if (taco.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(tacoModelAssembler.toModel(taco.get()));
    }

    @GetMapping(path = "/{id}/async")
    public Mono<ResponseEntity<TacoModel>> GetTacoByIdAsync(@PathVariable("id") long tacoId)
    {
        return
            Mono.defer(
                () -> Mono.justOrEmpty(tacoRepository.findById(tacoId))
            )
            .flatMap(
                t -> Mono.just(ResponseEntity.ok(tacoModelAssembler.toModel(t)))
            )
            .switchIfEmpty(Mono.just(ResponseEntity.notFound().build())); 
    }

    @RequestMapping(
        method = RequestMethod.POST, 
        path = "", 
        consumes = "application/json"
    )
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TacoModel> CreateTaco(@RequestBody Taco taco)
    {
        return ResponseEntity.ok(tacoModelAssembler.toModel(tacoRepository.save(taco)));
    }
    
    @RequestMapping(
        method = RequestMethod.POST, 
        path = "/async", 
        consumes = "application/json"
    )
    public Mono<ResponseEntity<TacoModel>> CreateTacoAsync(@RequestBody /*Mono<Taco>*/ Taco tacoMono)
    {
        return
            Mono.defer(
                () -> Mono.just(tacoRepository.save(tacoMono))
            )
            .flatMap(t -> Mono.just(tacoModelAssembler.toModel(t)))
            .flatMap(
                model -> 
                    Mono.just(
                        new ResponseEntity<TacoModel>(model, HttpStatus.CREATED)
                    )
            );
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{tacoId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void DeleteTaco(@PathVariable("tacoId") long tacoId)
    {
        try {

            tacoRepository.deleteById(tacoId);

        } catch (EmptyResultDataAccessException e) {

        }
    }
    
    @DeleteMapping(path = "/{tacoId}/async")
    public Mono<ResponseEntity<Void>> DeleteTacoAsync(@PathVariable("tacoId") long tacoId)
    {
        return 
            Mono.defer(
                () -> {
                    
                    tacoRepository.deleteById(tacoId);

                    return Mono.just(new ResponseEntity<Void>(HttpStatus.OK));
                }
            )
            .onErrorResume(
                EmptyResultDataAccessException.class, 
                e -> Mono.just(ResponseEntity.noContent().build())
            );
    }
}
