package com.example.addressservice.services;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class QueryParamService {
    // define list of query params here
    private Long student_id = null;
    private String Search = null;
    private int page = 1;
    private int page_size = 10;

}
