/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myjava.java.hibernate.examples;

import com.myjava.java.ProgramExample;
import com.myjava.java.hibernate.entities.Pen;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.*;
import static java.lang.System.out;
/**
 *
 * @author ruslan
 */
public class SimpleCRUDHibernateSessionExample extends HibernateSessionExample {
    

    @Override
    protected void doRun(Object... args) throws Exception {
        
        Pen pen = new Pen();
        
        pen.setLabel("Kohinor");
        pen.setMaterialName("Wood");
        pen.setWeight(100);
        pen.setLength(10);
        
        wrapInTransaction(() -> session.save(pen));      
        
        out.printf("Created pen id = %d%n", pen.getId());
       
        Pen fetchedPen = session.get(Pen.class, pen.getId());
        
        out.printf(
                "Fetched Pen By Id = %d:%n%s%n", 
                pen.getId(), 
                fetchedPen
        );
        
        fetchedPen.setLabel(fetchedPen.getLabel() + "  (UPD)");
        fetchedPen.setMaterialName(fetchedPen.getMaterialName() + " (UPD)");
        
        wrapInTransaction(() -> session.update(fetchedPen));
        
        Pen updatedPen = session.get(Pen.class, fetchedPen.getId());
        
        out.printf("Updated Pen:%n%s%n", updatedPen);
        
        wrapInTransaction(() -> session.remove(updatedPen));
        
        out.printf(
                "Pen Id = %d removed ? %b%n", 
                updatedPen.getId(), 
                session.get(Pen.class, updatedPen.getId()) == null
        );
    }
}
