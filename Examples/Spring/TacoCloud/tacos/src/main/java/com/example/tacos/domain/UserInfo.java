package com.example.tacos.domain;

import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
public class UserInfo {
    
    private final String fullname;
    private final String phoneNumber;
    private final String street;
    private final String city;
    private final String state;
    private final String zip;

}
