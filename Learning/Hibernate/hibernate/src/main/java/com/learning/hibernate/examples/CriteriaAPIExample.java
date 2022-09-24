package com.learning.hibernate.examples;

import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.hibernate.Session;

import com.learning.hibernate.entities.ShoppingCartItem;
import com.learning.hibernate.utils.HibernateUtils;
import com.learning.hibernate.values.Money;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import static java.lang.System.out;

public class CriteriaAPIExample extends HibernateExample {

    @Data
    @RequiredArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
    private static class Statistics {

        @NonNull
        private long count;

        @NonNull
        private double avg;

        @NonNull
        private int max;
    }
    
    @Data
    @RequiredArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
    private static class GroupByStatistics {
        @NonNull
        private int expression;

        @NonNull
        private long count;
    }
    
    public CriteriaAPIExample() {
        super("CriteriaAPI");
    }

    @Override
    protected void doRun(Session session, Object data) throws Exception {
        
        prepareData(session);
        runCriteriaAPIExample(session);
        
    }

    private void prepareData(Session session) {

        final int itemCount = 30;

        HibernateUtils.wrapInTransaction(session, () -> {

            for (int i = 1; i <= itemCount; ++i)
            {
                session.persist(
                    ShoppingCartItem.of(
                        RandomUtils.nextInt(1, 100), 
                        RandomStringUtils.randomAlphanumeric(5, 25), 
                        Money.ofEUR(RandomUtils.nextDouble(5.689, 46.943))
                    ) 
                );
            }

        });
    }

    private void runCriteriaAPIExample(Session session) {

        CriteriaBuilder cb = session.getCriteriaBuilder();

        runSimpleSelectionsExample(session, cb);
        runAggregatesExample(session, cb);
        runGroupingsExample(session, cb);
        runUpdatesExample(session, cb);
        runDeletionsExample(session, cb);
    }

    private void runSimpleSelectionsExample(Session session, CriteriaBuilder cb) {

        CriteriaQuery<ShoppingCartItem> cq = cb.createQuery(ShoppingCartItem.class);
        Root<ShoppingCartItem> root = cq.from(ShoppingCartItem.class);

        List<ShoppingCartItem> cartItems =
            session.createQuery(
                    cq.select(root)
                        .where(
                            cb.and(
                                cb.between(root.get("productPrice").get("amount"), 10.985, 30.34), 
                                cb.greaterThan(cb.length(root.get("productName")), 10)
                            )
                        )
                )
                        .getResultList();
            
        printEntities(cartItems, "Cart Items filtered");
        
    }

    private void runAggregatesExample(Session session, CriteriaBuilder cb) {

        CriteriaQuery<Statistics> statsQuery = cb.createQuery(Statistics.class);
        Root<ShoppingCartItem> root = statsQuery.from(ShoppingCartItem.class);

        statsQuery.where(cb.greaterThan(root.get("quantity"), 15));
        
        Statistics stats =
            session.createQuery(
            statsQuery.multiselect(
                cb.count(root), 
                cb.avg(root.get("productPrice").get("amount")),
                cb.max(root.get("quantity"))
                        )).getSingleResult();
            
        out.println(stats);
    }

    private void runGroupingsExample(Session session, CriteriaBuilder cb) {

        CriteriaQuery<GroupByStatistics> cq = cb.createQuery(GroupByStatistics.class);
        Root<ShoppingCartItem> root = cq.from(ShoppingCartItem.class);
        Expression<Integer> groupByExpr = cb.length(root.get("productName"));

        Collection<GroupByStatistics> stats =
            session.createQuery(
                cq.multiselect(
                    groupByExpr.alias("product_name_length"),
                    cb.count(root)
                ).groupBy(groupByExpr))
                .getResultList();
                
        printEntities(stats, "groups");
    }   

    private void runUpdatesExample(Session session, CriteriaBuilder cb) {

        CriteriaUpdate<ShoppingCartItem> cq = cb.createCriteriaUpdate(ShoppingCartItem.class);
        Root<ShoppingCartItem> root = cq.from(ShoppingCartItem.class);
        Path<Integer> amountAttr = root.get("productPrice").<Integer>get("amount");
        
        cq
        .set(amountAttr, cb.sum(amountAttr, 100))
                .where(cb.lessThan(amountAttr, 20));
        
        HibernateUtils.wrapInTransaction(session, () -> {

            int updatedCount = 
                    session.createMutationQuery(cq).executeUpdate();

            out.printf("updated count: %d%n", updatedCount);
                
        });
    }

    private void runDeletionsExample(Session session, CriteriaBuilder cb) {

        CriteriaDelete<ShoppingCartItem> cq = cb.createCriteriaDelete(ShoppingCartItem.class);
        Root<ShoppingCartItem> root = cq.from(ShoppingCartItem.class);

        cq.where(
            cb.lessThanOrEqualTo(
                cb.length(root.get("productName")), 10
            )
        );

        HibernateUtils.wrapInTransaction(session, () -> {

            int deletedCount = 
                session.createMutationQuery(cq).executeUpdate();

            out.printf("deleted count: %d%n", deletedCount);
        
        });
    }
    
}
