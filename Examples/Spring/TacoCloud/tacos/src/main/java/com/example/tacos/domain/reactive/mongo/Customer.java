package com.example.tacos.domain.reactive.mongo;

import java.io.Serializable;

import com.example.tacos.domain.UserInfo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Document(collection = "users")
public class Customer implements Serializable {
    
    private static final long serialVersionUID = 4L;

    @Id
    private long id;

    private final UserInfo userInfo;
  
    public String getFullname()
    {
        return userInfo.getFullname();
    }

    public String getPhoneNumber()
    {
        return userInfo.getFullname();
    }

    public String getStreet()
    {
        return userInfo.getStreet();
    }

    public String getCity()
    {
        return userInfo.getCity();
    }

    public String getState()
    {
        return userInfo.getState();
    }

    public String getZip()
    {
        return userInfo.getZip();
    }
}
