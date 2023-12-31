package com.example.addressservice.services;


import java.beans.PropertyDescriptor;
import java.util.*;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.addressservice.models.Address;
import com.example.addressservice.repositories.AddressRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AddressService {

  private final AddressRepository addressRepository;

  public List<Address> allAddresses(){
    return addressRepository.findAll();
  }

  public List<Address> studentAddresses(QueryParamService queryParams){
    List<Address> addresses = new ArrayList<>();
    Long student_id = queryParams.getStudent_id();
    if (student_id != null){
      addresses = addressRepository.findByStudentId(student_id);
    }
    return addresses;
  }

  public Address getById(Long id){
    Optional <Address> optionalAddress = addressRepository.findById(id);
    if (optionalAddress.isPresent()){
      Address address = optionalAddress.get();
      return address;
    }
    return null;
  }

  public boolean deleteById(Long id){
    // now use a hack to get user id first after that found better way
    Address address = this.getById(id);
    if (address != null){
      addressRepository.deleteById(id);
      return true;
    }
    return false;
  }

  public Address update(Long id,Address address){
    Address existedAddress = getById(id);
    if (existedAddress == null)
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    BeanUtils.copyProperties(address, existedAddress, getNullPropertyNames(address));
    return addressRepository.save(existedAddress);
  }

  public Address createAddress(Address address){
   return addressRepository.save(address);
  }

  private String[] getNullPropertyNames(Object source) {
    final BeanWrapper src = new BeanWrapperImpl(source);
    PropertyDescriptor[] pds = src.getPropertyDescriptors();

    Set<String> nullNames = new HashSet<>();
    for (PropertyDescriptor pd : pds) {
        Object srcValue = src.getPropertyValue(pd.getName());
        if (srcValue == null) {
            nullNames.add(pd.getName());
        }
    }
    // add id
    nullNames.add("id");

    String[] result = new String[nullNames.size()];
    return nullNames.toArray(result);
  }

  public List<Address> createBulkAddresses(List<Address> addresses) {
    return addressRepository.saveAll(addresses);
  }
}
