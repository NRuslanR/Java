package com.learning.hibernate;

import com.learning.hibernate.utils.HibernateExamplesUtils;
import com.learning.hibernate.utils.HibernateUtils;

public class HibernateLearning 
{
    public static void main( String[] args ) throws Exception
    {
        HibernateUtils.Initialize();
        
        try
        {
            HibernateExamplesUtils.runExample(args);
        }

        finally
        {
            HibernateUtils.shutdown();
        }
    }
    
}
