package com.example.tacos.domain;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class Customer implements Serializable {
    
    private static final long serialVersionUID = 4L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Embedded
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
