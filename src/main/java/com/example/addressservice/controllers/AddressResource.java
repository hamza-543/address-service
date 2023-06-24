package com.example.addressservice.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.addressservice.models.Address;
import com.example.addressservice.services.AddressService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AddressResource {

  private final AddressService addressService;

  @GetMapping("/addresses")
  public List<Address> index(){
    return addressService.allAddresses();
  }
  
  @PostMapping("/addresses")
  public Address createUser(@RequestBody Address requestBody){
    return addressService.createAddress(requestBody);
  }

  @GetMapping("/addresses/{id}")
  public Address get(@PathVariable String id){
    Address user =  addressService.getById(Long.parseLong(id));
    if (user == null)
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    return user;
  }

  @DeleteMapping("/addresses/{id}")
  public String getUser(@PathVariable long id){
    addressService.deleteById(id);
    return "Address successfully deleted";
  }

  @PatchMapping("/addresses/{id}")
  public Address update(@PathVariable long id, @RequestBody Address user){
    return addressService.update(id, user);
  }
  
}