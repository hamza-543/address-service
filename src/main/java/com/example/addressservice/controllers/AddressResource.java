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
  public Address createAddress(@RequestBody Address requestBody){
    return addressService.createAddress(requestBody);
  }

  // don't return exact response from saveAll();
  // Store to a list object than return it.
  @PostMapping("/addresses/bulk")
  public List<Address> createBulkAdresses(@RequestBody List<Address> requestBody){
    List<Address> addresses = addressService.createBulkAddresses(requestBody);
    return addresses;
  }

  @GetMapping("/addresses/{id}")
  public Address get(@PathVariable String id){
    Address address =  addressService.getById(Long.parseLong(id));
    if (address == null)
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    return address;
  }

  @DeleteMapping("/addresses/{id}")
  public String getAddress(@PathVariable long id){
    addressService.deleteById(id);
    return "Address successfully deleted";
  }

  @PatchMapping("/addresses/{id}")
  public Address update(@PathVariable long id, @RequestBody Address address){
    return addressService.update(id, address);
  }
  
}
