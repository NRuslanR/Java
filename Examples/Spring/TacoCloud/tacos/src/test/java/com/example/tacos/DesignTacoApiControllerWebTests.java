package com.example.tacos;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.example.tacos.domain.Ingredient;
import com.example.tacos.domain.Ingredient.Type;
import com.example.tacos.domain.Taco;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DesignTacoApiControllerWebTests {
    
    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void shouldReturnRecentTacos()
    {
        List<Taco> testTacos = 
            Arrays.asList(
                createTestTaco(1),
                createTestTaco(2),
                createTestTaco(3)
                );
            
        webTestClient
            .get()
            .uri(URI.create("/api/design/async"))
            .accept(MediaType.APPLICATION_JSON)
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
            
        webTestClient
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
                new Ingredient("INGA", "Ingredient A", Type.WRAP),
                new Ingredient("INGB", "Ingredient B", Type.PROTEIN)
            )
        );

        return taco;
    }
}