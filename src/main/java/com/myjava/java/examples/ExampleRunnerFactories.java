/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myjava.java.examples;

import java.util.*;
import java.io.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.stream.Stream;
import org.apache.commons.beanutils.ConvertUtils;
/**
 *
 * @author ruslan
 */
class ParameterPublicFieldConfig
{
    public String name;
    public String value;     
}
    
class ParameterConfig
{
    public String type;
    public String implType;
    public ParameterPublicFieldConfig[] publicFields;
    public ParameterConfig[] parameters;
}
    
class ExampleRunnerFactoryConfig
{
    public String type;
    public ParameterConfig[] parameters;      
}
   
class ExampleRunnerConfig
{
    public String id;
    public ExampleRunnerFactoryConfig factory;
}
    
class ExampleRunnersConfig
{
    public ExampleRunnerConfig[] exampleRunners;
}

public class ExampleRunnerFactories {
    
    private static Map<String, ExampleRunnerFactory> exampleRunnerFactoryMap;
    
    static {
        
        exampleRunnerFactoryMap = new HashMap<>();
        
        try
        {
            Init();
        }
        
        catch (Exception ex)
        {
            throw new RuntimeException(ex.getMessage());
        }
    }
    
    public static ExampleRunnerFactory getExampleRunnerFactory(
            String exampleRunnerId
    ) throws IllegalArgumentException
    {
        ExampleRunnerFactory exampleRunnerFactory = 
                (ExampleRunnerFactory)exampleRunnerFactoryMap.get(exampleRunnerId);
        
        if (exampleRunnerFactory == null)
        {
            throw new IllegalArgumentException(
                    String.format(
                            "Example runner with id = %s not found", 
                            exampleRunnerId
                    )
            );
        }
    
        return exampleRunnerFactory;
    }

    private static void Init() throws Exception {

        createExampleRunnerFactoriesFrom(getExampleRunnersConfig());
    }
    
    private static ExampleRunnersConfig getExampleRunnersConfig() throws Exception {
        
        ObjectMapper exampleRunnersConfigMapper = new ObjectMapper();
        
        return
            exampleRunnersConfigMapper
                    .readValue(
                            ExampleRunnerFactories
                                    .class
                                        .getClassLoader()
                                            .getResourceAsStream(
                                                "exampleRunners.json"
                                            ), 
                            ExampleRunnersConfig.class
                    );

    }
    
    private static void createExampleRunnerFactoriesFrom(
            ExampleRunnersConfig exampleRunnersConfig
    ) throws Exception {

        for (
                ExampleRunnerConfig exampleRunnerConfig : 
                exampleRunnersConfig.exampleRunners
        ) {
            
            exampleRunnerFactoryMap.putIfAbsent(
                    exampleRunnerConfig.id, 
                    createExampleRunnerFactoryFrom(exampleRunnerConfig.factory)
            );
        }
    }

    private static ExampleRunnerFactory createExampleRunnerFactoryFrom(
            ExampleRunnerFactoryConfig factoryConfig
    ) throws Exception {
        
        Class<?> factoryType = Class.forName(factoryConfig.type);
        
        Constructor<?> factoryCtor =
            factoryType.getDeclaredConstructor(
                    getExampleRunnerFactoryParameterTypesFrom(
                        factoryConfig.parameters
                    )
            );
        
        return 
                (ExampleRunnerFactory)
                    factoryCtor
                            .newInstance(
                                    createExampleRunnerFactoryParametersFrom(
                                            factoryConfig.parameters
                                    )
                            );
 
    }

    private static Class<?>[] getExampleRunnerFactoryParameterTypesFrom(
            ParameterConfig[] parameterConfigs
    ) throws Exception {
        
        if (parameterConfigs == null || parameterConfigs.length == 0)
            return null;
        
        Class<?>[] parameterTypes = new Class<?>[parameterConfigs.length];
        
        for (
            int i = 0; 
            i < parameterTypes.length; 
            parameterTypes[i] = Class.forName(parameterConfigs[i].type), 
            ++i
        );
            
        return parameterTypes;
    }

    private static Object[] createExampleRunnerFactoryParametersFrom(
            ParameterConfig[] parameterConfigs
    ) throws Exception {
         
        if (parameterConfigs == null || parameterConfigs.length == 0)
            return null;
        
        Object[] parameters = new Object[parameterConfigs.length];
        
        for (
            int i = 0;
            i < parameters.length;
            parameters[i] = 
                    createExampleRunnerFactoryParameterFrom(
                            parameterConfigs[i]
                    ),
            ++i
        );
        
        return parameters;
    }

    private static Object createExampleRunnerFactoryParameterFrom(
            ParameterConfig parameterConfig
    ) throws Exception {
        
        String realParamType = 
            (parameterConfig.implType == null || parameterConfig.implType.isEmpty()) ? 
                parameterConfig.type : parameterConfig.implType;
        
        Class<?> parameterType = Class.forName(realParamType);
        
        Object parameter = 
                parameterType.getDeclaredConstructor(
                        getExampleRunnerFactoryParameterTypesFrom(
                            parameterConfig.parameters
                        )
                )
                .newInstance(
                    createExampleRunnerFactoryParametersFrom(
                        parameterConfig.parameters
                    )
                );
             
        setParameterPublicFields(parameter, parameterConfig.publicFields);
        
        return parameter;
    }

    private static void setParameterPublicFields(
            Object parameter, 
            ParameterPublicFieldConfig[] publicFields
            
    ) throws Exception{
       
        if (publicFields == null || publicFields.length == 0)
            return;
        
        Class<?> parameterType = parameter.getClass();
        
        for (ParameterPublicFieldConfig publicFieldConfig : publicFields)
        {
            Field publicField = parameterType.getField(publicFieldConfig.name);
            
            Object fieldValue;
            
            if (ConvertUtils.lookup(publicField.getType()) != null)
            {
                fieldValue = 
                        ConvertUtils.convert(
                                publicFieldConfig.value, 
                                publicField.getType()
                        );
            }
            
            else if (Enum.class.isAssignableFrom(publicField.getType()))
            {
                fieldValue = 
                        Enum.valueOf(
                                (Class)publicField.getType(), 
                                publicFieldConfig.value
                        );
            }
                           
            else
            {
                throw new UnsupportedOperationException();
            }
            
            publicField.set(parameter, fieldValue);
        }
    }

}
