package com.example.tacos.data;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

import com.example.tacos.domain.Taco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcTacoRepository implements TacoRepository {
    
    private final JdbcTemplate jdbc;

    @Autowired
    public JdbcTacoRepository(JdbcTemplate jdbc)
    {
        this.jdbc = jdbc;
    }

    @Override
    public Taco save(Taco taco) {
        
        long tacoId = saveTaco(taco);

        taco.setId(tacoId);

        saveTacoIngredients(taco);
        
        return taco;
    }

    private long saveTaco(Taco taco) {

        taco.setCreatedAt(new Date());
        
        PreparedStatementCreatorFactory pscf = 
            new PreparedStatementCreatorFactory(
                "insert into tacos (name, created_at) values (?, ?)", 
                Types.VARCHAR, Types.TIMESTAMP
            );
            
        pscf.setReturnGeneratedKeys(true);

        PreparedStatementCreator psc =
            pscf.newPreparedStatementCreator(
                Arrays.asList(
                    taco.getName(),
                    new Timestamp(taco.getCreatedAt().getTime())
                )
            );

        
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update(psc, keyHolder);

        return keyHolder.getKey().longValue();
    }

    private void saveTacoIngredients(Taco taco) {

        jdbc.batchUpdate(
            "insert into tacos_ingredients (taco_id, ingredient_id) values (?, ?)", 
            new BatchPreparedStatementSetter() {

                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    
                    String ingredientId = taco.getIngredients().get(i).getId();

                    ps.setLong(1, taco.getId());
                    ps.setString(2, ingredientId);
                }

                @Override
                public int getBatchSize() {

                    return taco.getIngredients().size();

                }

            }
        );
    }
    
}
