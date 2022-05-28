package com.example.tacos.api.consume;

import java.time.Duration;
import java.util.Arrays;

import com.example.tacocloudmodels.TacoModel;
import com.example.tacos.domain.Ingredient;
import com.example.tacos.domain.Taco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class DesignTacoApiControllerConsumer {
    
    @Autowired
    private WebClient webClient;

    public DesignTacoApiControllerConsumer(WebClient webClient)
    {
        this.webClient = webClient;

    }

    @EventListener(value = { ApplicationReadyEvent.class })
    public void GetRecentTacosAsync()
    {
        Flux<CollectionModel<TacoModel>> tacoModels =

                webClient
                        .get()
                        .uri("/design/recent/async")
                        .retrieve()
                        .bodyToFlux(
                                new ParameterizedTypeReference<CollectionModel<TacoModel>>() {
                                });

        tacoModels
                .timeout(Duration.ofSeconds(10))
                .subscribe(model -> {

                    log.info(model.getContent().toString());

                });
    }
    
    @EventListener(ApplicationReadyEvent.class)
    public void GetTacoByIdAsync()
    {
        webClient
            .get()
            .uri("/design/{id}/async", 19)
                .<TacoModel>exchangeToMono(cr -> {
                    if (cr.headers().header("X_UNAVAILABLE").contains("true"))
                        return Mono.empty();

                    return cr.bodyToMono(TacoModel.class);
                })

            .subscribe(
                model -> {

                    log.info(model.toString());

                }, 
                error -> {

                    log.info("error: " + error.toString());
                        });
    }
  
    @EventListener(value = { ApplicationReadyEvent.class })
    public void CreateTacoAsync()
    {
        Taco newTaco = new Taco();

        newTaco.setName("My Favourite Taco");
        newTaco.addIngredients(
            Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.PROTEIN),
                new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.SAUCE)
            )
        );

        webClient
            .post()
            .uri("/design/async")
            .bodyValue(newTaco)
            .retrieve()
                .bodyToMono(TacoModel.class)
                .subscribe(model -> {

                    log.info(String.format("New Taco: %s", model));
                }
            );
    }

    @EventListener(ApplicationReadyEvent.class)
    public void DeleteTacoAsync()
    {
        webClient
            .delete()
                .uri("/design/{id}/async", 32)
            .retrieve()
            .bodyToMono(Void.class)
            .subscribe(m -> {
                log.info("taco deleted");
                });
    }
}
