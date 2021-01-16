/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myjava.java;

/**
 *
 * @author ruslan
 */
public abstract class ProgramExample {
    
    public void run(Object... args) throws Exception
    {
        doBeforeRun();
        
        try
        {
            doRun(args);
            doAfterRun(); 
        }
        
        catch (Exception ex)
        {
            doOnFail(ex);
            
            throw ex;
        }
        
        finally
        {
           doOnClean();
        }
       
    }
    
    protected void doBeforeRun() throws Exception
    {
    
    }
    
    protected abstract void doRun(Object... args) throws Exception;
    
    protected void doAfterRun() throws Exception
    {

    }

    protected void doOnFail(Exception ex) {

    }
    
    protected void doOnClean()
    {
        
    }
}
