package com.andrkul.services;

import com.andrkul.model.CustomerRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;


public interface CustomerRequestService {

    List<CustomerRequest> findAll();

    CustomerRequest findById(Long id);

   CustomerRequest createCustomerRequest(CustomerRequest customerRequest);

    CustomerRequest save(CustomerRequest customerRequest);

    List<CustomerRequest> findByCustomerName(String customerName, Sort sort);
    
    Page<CustomerRequest> search(Timestamp searchDate, String customerName, String rqName, String rqStatus, PageRequest pageRequest);
}
