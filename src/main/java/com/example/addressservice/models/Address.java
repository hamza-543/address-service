package com.example.addressservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="addresses")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
  
  @Id
  @GeneratedValue
  private Long id;
  private String zipcode;
  //should be a enum
  private String city;
  private String country;
}
