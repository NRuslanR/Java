/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myjava.java.hibernate.entities;

import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.TypeDef;
import com.fasterxml.jackson.databind.*;
import java.io.*;
/**
 *
 * @author ruslan
 */
@Entity
@Table(name = "books")
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    
    private String title;
    
    @Column(name = "publishing_date")
    @Temporal(TemporalType.DATE)
    private Date publishingDate;

    private String author;
    
    @Column(name = "page_count")
    private int pageCount;
    
    public Book() {
    }
    public long getId()
    {
        return id;
    }
    
    public void setId(long id)
    {
        this.id = id;
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    public Date getPublishingDate()
    {
        return publishingDate;
    }
    
    public void setPublishingDate(Date publishingDate)
    {
        this.publishingDate = publishingDate;
    }
    
    public String getAuthor()
    {
        return author;
    }
    
    public void setAuthor(String author)
    {
        this.author = author;
    }
    
    public int getPageCount()
    {
        return pageCount;
    }
    
    public void setPageCount(int pageCount)
    {
        this.pageCount = pageCount;
    }
    
    @Override
    public String toString()
    {
        StringWriter writer = new StringWriter();
        
        ObjectMapper jsonMapper = new ObjectMapper();
        
        try
        {
            jsonMapper.writeValue(writer, this);
        }
        
        catch (Exception ex)
        {
            writer.write("Incorrect representation");
        }
        
        return writer.toString();
    }
}
