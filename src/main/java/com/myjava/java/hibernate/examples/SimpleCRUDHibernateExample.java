/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myjava.java.hibernate.examples;

import com.myjava.java.hibernate.entities.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;
import java.lang.*;
import static java.lang.System.out;
import javax.persistence.criteria.*;
/**
 *
 * @author ruslan
 */
public class SimpleCRUDHibernateExample extends HibernateExample {
    
    public SimpleCRUDHibernateExample()
    {
        super();
        
        setGlobalTransactionEnabled(false);
    }
    
    @Override
    public void doRun(Object... args)
    {
        super.doRun(args);
        
        List<Book> newBooks = new ArrayList<>();
        
        Date publishingDate = Date.from(Instant.parse("2017-11-29T10:12:05Z"));
        
        for (int i = 1; i <= 10; ++i)
        {
            Book newBook = new Book();
        
            newBook.setTitle("Book1");
            newBook.setPublishingDate(publishingDate);
            newBook.setAuthor("Radik");
            newBook.setPageCount(i * 10);
            
            newBooks.add(newBook);
        }
        
        wrapInTransaction(() -> {
            
            for (Book iBook : newBooks)
                entityManager.persist(iBook);
        });
        
        List<Book> books =
                findByFieldsEquality(
                        Book.class, 
                        new Pair<>("publishingDate", publishingDate)
                );
        
        out.println("Book count:" + books.size());
        
        for (Book iBook : books)
            out.println(iBook);
        
        out.println("Updating all books...");
        
        wrapInTransaction(() -> {
            
            for (Book iBook : books)
            {
                iBook.setAuthor(iBook.getAuthor() + " [UPDATED]");
                iBook.setTitle(iBook.getTitle() + " [UPDATED]");
            }
        });
        
        out.println("Updated books:");
        
        for (Book iBook : books)
            out.println(iBook);
        
        out.println("Removing all books...");
        
        wrapInTransaction(
            () -> {
                
                for (Book iBook : books)
                    entityManager.remove(iBook);
                    
            }
        );
        
        out.printf("All books deleted ?%b %n", findByFieldsEquality(Book.class).isEmpty());
    }
    
}
