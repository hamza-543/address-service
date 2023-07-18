package com.example.addressservice.controllers;

import java.util.List;

import com.example.addressservice.services.QueryParamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.example.addressservice.models.Address;
import com.example.addressservice.services.AddressService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AddressController {

  private final AddressService addressService;

  @GetMapping("/addresses")
  public List<Address> index(){
    return addressService.allAddresses();
  }

  @GetMapping("/student_addresses")
  public List<Address> studentAddresses(@ModelAttribute QueryParamService queryParams){
    return addressService.studentAddresses(queryParams);
  }
  
  @PostMapping("/addresses")
  public Address createAddress(@RequestBody Address requestBody){
    return addressService.createAddress(requestBody);
  }

  // don't return exact response from saveAll() because its have iteratable objects;
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
  public ResponseEntity<String> deleteAddress(@PathVariable long id){
    boolean isDeleted = addressService.deleteById(id);

    if (isDeleted)
      return ResponseEntity.status(HttpStatus.OK).body("Record successfully deleted");

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No record found");
  }

  @PatchMapping("/addresses/{id}")
  public Address update(@PathVariable long id, @RequestBody Address address){
    return addressService.update(id, address);
  }
  
}
