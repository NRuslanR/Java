package com.example.tacos.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.tacos.domain.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.CaseFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class JdbcOrderRepository implements OrderRepository {

    private final SimpleJdbcInsert orderInserter;
    private final SimpleJdbcInsert tacosOrdersInserter;
    private final ObjectMapper objectMapper;

    @Autowired
    public JdbcOrderRepository(JdbcTemplate jdbc)
    {
        orderInserter = new SimpleJdbcInsert(jdbc)
                .withTableName("orders")
                .usingGeneratedKeyColumns("id");

        tacosOrdersInserter = new SimpleJdbcInsert(jdbc)
                .withTableName("order_tacos");

        objectMapper = new ObjectMapper();
    }
    
    @Override
    public Order save(Order order) {
        
        Order savedOrder = saveOrder(order);

        saveOrderTacos(savedOrder);

        return savedOrder;
    }

    private Order saveOrder(Order order) {

        order.setPlacedAt(new Date());

        Map<String, Object> orderEntries = 
            ((Map<String, Object>)objectMapper.convertValue(order, Map.class))
                .entrySet()
                    .stream()
                        .collect(
                            HashMap::new, 
                            (m, e) -> 
                                m.put(
                                    CaseFormat.LOWER_CAMEL.to(
                                        CaseFormat.LOWER_UNDERSCORE,
                                        e.getKey()
                                    ),
                                    e.getValue()
                                ), 
                            HashMap::putAll
                        );

        log.info("order entries: " + orderEntries);

        orderEntries.put("placed_at", order.getPlacedAt());

        long orderId = orderInserter.executeAndReturnKey(orderEntries).longValue();

        order.setId(orderId);

        return order;
    }

    private void saveOrderTacos(Order order) {

        Map<String, Object>[] tacoOrderEntries = createTacoOrderEntriesFrom(order);

        log.info("tacoOrderEntries: " + Arrays.toString(tacoOrderEntries));
        
        tacosOrdersInserter.executeBatch(tacoOrderEntries);
    }

    private Map<String, Object>[] createTacoOrderEntriesFrom(Order order) {
        
        return 
            (Map<String, Object>[]) 
                order
                    .getTacos()
                        .stream()
                            .map(
                                t -> {

                                    Map<String, Object> tacoOrderEntry = 
                                        new HashMap<String, Object>();

                                    tacoOrderEntry.put("order_id", order.getId());
                                    tacoOrderEntry.put("taco_id", t.getId());

                                    return tacoOrderEntry;
                                }
                            )
                        .collect(Collectors.toList())
                        .toArray(Map[]::new);
    }
    
}
