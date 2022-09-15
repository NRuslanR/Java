package com.learning.hibernate.examples;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.hibernate.Session;

import com.learning.hibernate.entities.CategorizedProduct;
import com.learning.hibernate.entities.Oak;
import com.learning.hibernate.entities.ProductCategory;
import com.learning.hibernate.entities.Spruce;
import com.learning.hibernate.entities.Spruce.ConeType;
import com.learning.hibernate.entities.Tree;
import com.learning.hibernate.utils.HibernateUtils;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import static java.lang.System.out;

public class SortingAndPaginationExample extends HibernateExample {

    public SortingAndPaginationExample() {
        super("SortingAndPagination");
    }

    @Override
    protected void doRun(Session session, Object data) throws Exception {
        
        prepareData(session);
        runSortingExample(session);
        runPaginationExample(session);
        
    }

    private void prepareData(Session session) {
        
        prepareProductCategories(session);
        prepareTrees(session);
    }

    private void prepareProductCategories(Session session) {

        final int categoryCount = 10, productPerCategory = 4;

        List<ProductCategory> categories = new ArrayList<>(categoryCount);

        for (int i = 1; i <= categoryCount; ++i)
        {
            ProductCategory category = new ProductCategory(i + "Category");

            List<CategorizedProduct> products = new ArrayList<>(productPerCategory);

            for (int j = 1; j <= productPerCategory; ++j)
            {
                products.add(
                    new CategorizedProduct(j + "Product")
                );
            }
            
            category.setProducts(products);

            categories.add(category);
        }

        HibernateUtils.persistInTransaction(session, categories);
    }

    private void prepareTrees(Session session) {

        final int treeCount = 33;

        Tree tree;
        List<Tree> trees = new ArrayList<>(treeCount);
        
        for (int i = 1; i <= treeCount; ++i)
        {
            int number = RandomUtils.nextInt(0, 100);

            if (number < 50)
                tree = new Oak(i + "Oak", RandomUtils.nextInt(10, 74), RandomUtils.nextDouble(43, 237));

            else
                tree = new Spruce(i + "Spruce", RandomUtils.nextInt(10, 73), ConeType.Green);

            trees.add(tree);
        }

        HibernateUtils.persistInTransaction(session, trees);
    }

    private void runSortingExample(Session session) {

        List<ProductCategory> categories =
            session
                .createQuery(
                    "select c from ProductCategory c " +
                    "order by c.name desc, c.id asc nulls first",
                    ProductCategory.class
                ).getResultList();

        printEntities(categories, "fetched product categories ordered by name");

        for (ProductCategory category : categories)
        {
            printEntities(
                category.getProducts(),
                "products of category" + category.getName() +
                "ordered by name desc and id asc"
            );
        }
        
        List<Tree> trees = 
            session.createQuery(
                "select t from Tree as t " + 
                "order by t.branchCount desc, t.name asc nulls first",
                Tree.class
            ).getResultList();

        printEntities(trees, "trees ordered by name and branch count");

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery<Tree> cq = cb.createQuery(Tree.class);
        Root<Tree> treeRoot = cq.from(Tree.class);

        cq.select(treeRoot).orderBy(
            cb.desc(treeRoot.get("branchCount")),
            cb.asc(treeRoot.get("name"))
        );

        trees =
            session.createQuery(cq).getResultList();

        printEntities(
            trees,
            "trees ordered by branch count and name with Criteria API"
        );
    }

    private void runPaginationExample(Session session) {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Tree> cq = cb.createQuery(Tree.class);
        Root<Tree> treeRoot = cq.from(Tree.class);  
        CriteriaQuery<Long> treeCountQuery = cb.createQuery(Long.class);
        
        //treeCountQuery.select(cb.count(treeRoot.get("id")));

        final long
            treeCount =
                session.createQuery(
                    "select COUNT(c) from Tree c", Long.class
                ).getSingleResult();
                //session.createQuery(treeCountQuery).getSingleResult();

        final int pageSize = 5;

        final long 
            totalPageCount = (long)Math.ceil((float)treeCount / pageSize),
            lastPageNumber = totalPageCount - 1;

        final int pageNumber = 7;

        List<Tree> trees =
            session
                .createQuery(cq.select(treeRoot))
                .setFirstResult((pageNumber - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();

        out.printf(
            "pageNumber: %d, pageSize: %d, totalPageCount: %d, " +
            "lastPageNumber: %d, treeCount: %d%n", 
            pageNumber, pageSize, totalPageCount, lastPageNumber, treeCount
        );

        printEntities(trees, "page content");

    }


}
