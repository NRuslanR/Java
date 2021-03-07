/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

/**
 *
 * @author ruslan
 */
public class Greeting {
    
    private long id;
    private String content;
    
    public Greeting(long id, String content)
    {
        this.id = id;
        this.content = content;
    }
    
    public long getId()
    {
        return id;
    }
    
    public String getContent()
    {
        return content;
    }
    
}
