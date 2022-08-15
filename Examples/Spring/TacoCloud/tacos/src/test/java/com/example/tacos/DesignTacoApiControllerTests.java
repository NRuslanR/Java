package com.example.tacos;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.example.tacos.api.controllers.DesignTacoApiController;
import com.example.tacos.api.resources.assemblers.IngredientModelAssembler;
import com.example.tacos.api.resources.assemblers.TacoModelAssembler;
//import com.example.tacos.data.jpa.reactive.TacoRepository;
import com.example.tacos.data.jpa.TacoRepository;
import com.example.tacos.domain.Ingredient;
import com.example.tacos.domain.Taco;
//import com.example.tacos.domain.reactive.mongo.Ingredient;
//import com.example.tacos.domain.reactive.mongo.Taco;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class DesignTacoApiControllerTests {
    
    @Test
    public void shouldReturnRecentTacos()
    {
        List<Taco> testTacos = 
            Arrays.asList(
                createTestTaco(1),
                createTestTaco(2),
                createTestTaco(3)
                );
            
        TacoRepository tacoRepository = Mockito.mock(TacoRepository.class);

        when(tacoRepository.findAll()).thenReturn(testTacos);

        DesignTacoApiController controller = 
            new DesignTacoApiController(
                tacoRepository, 
                new TacoModelAssembler(
                    new IngredientModelAssembler()
                )
                );
            
        WebTestClient testClient = 
                WebTestClient.bindToController(controller).build();

        testClient
            .get()
            .uri(URI.create("/api/design/async"))
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody()
            .jsonPath("$").isNotEmpty()
            .jsonPath("$.content").isArray()
            .jsonPath("$.content[0].id").isEqualTo(testTacos.get(0).getId())
            .jsonPath("$.content[0].name").isEqualTo(testTacos.get(0).getName())
            .jsonPath("$.content[1].id").isEqualTo(testTacos.get(1).getId())
            .jsonPath("$.content[1].name").isEqualTo(testTacos.get(1).getName())
            .jsonPath("$.content[2].id").isEqualTo(testTacos.get(2).getId())
            .jsonPath("$.content[2].name").isEqualTo(testTacos.get(2).getName())
            .jsonPath("$.content[3]").doesNotExist();
    }

    @Test
    public void shouldCreateTaco()
    {
        Taco unsavedTaco = createTestTaco(1, false), savedTaco = createTestTaco(1);
            
        TacoRepository tacoRepository = Mockito.mock(TacoRepository.class);

        when(tacoRepository.save(any())).thenReturn(savedTaco);

        DesignTacoApiController controller =
            new DesignTacoApiController(
                tacoRepository, 
                new TacoModelAssembler(
                    new IngredientModelAssembler()
                )
                );
            
        WebTestClient testClient = 
                WebTestClient.bindToController(controller).build();
            
        testClient
            .post()
            .uri(URI.create("/api/design/async"))
                .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(unsavedTaco)
            .exchange()
            .expectStatus()
            .isCreated()
            .expectBody()
            .jsonPath("$").isNotEmpty()
            .jsonPath("$.id").isEqualTo(savedTaco.getId());
    }

    private Taco createTestTaco(int number) {

        return createTestTaco(number, true);
    }
    
    private Taco createTestTaco(int number, boolean generateId) {
        
        Taco taco = new Taco();

        taco.setId(generateId ? UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE : null);
        taco.setName(String.format("Taco%d", number));
        taco.setIngredients(
            Arrays.asList(
                new Ingredient("INGA", "Ingredient A", Ingredient.Type.WRAP),
                new Ingredient("INGB", "Ingredient B", Ingredient.Type.PROTEIN)
            )
        );

        return taco;
    }
}
