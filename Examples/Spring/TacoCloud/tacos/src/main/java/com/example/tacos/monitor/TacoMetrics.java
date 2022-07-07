package com.example.tacos.monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;
import org.springframework.stereotype.Component;

import com.example.tacos.domain.Ingredient;
import com.example.tacos.domain.Taco;

import io.micrometer.core.instrument.MeterRegistry;

@Component
public class TacoMetrics extends AbstractRepositoryEventListener<Taco>
{
    private MeterRegistry meterRegistry;

    @Autowired
    public TacoMetrics(MeterRegistry meterRegistry)
    {
        this.meterRegistry = meterRegistry;

    }

    @Override
    protected void onAfterSave(Taco taco) {
        
        Iterable<Ingredient> ingredients = taco.getIngredients();

        for (Ingredient ingredient : ingredients)
        {
            meterRegistry.counter(
                "tacocloud", "ingredient", ingredient.getId()
            ).increment();
        }
    }
    
}