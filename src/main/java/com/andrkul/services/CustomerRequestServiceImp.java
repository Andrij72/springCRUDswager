package com.andrkul.services;

import com.andrkul.handler.exception.NotFoundRuntimeException;
import com.andrkul.model.CustomerRequest;
import com.andrkul.repositories.CustomerRequestRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
public class CustomerRequestServiceImp implements CustomerRequestService {

    @Autowired
    private CustomerRequestRepository customerRequestRepository;

    @Override
    public List<CustomerRequest> findAll() {
        return Lists.newArrayList(customerRequestRepository.findAll());
    }

    @Override
    public CustomerRequest findById(Long id) {
        return customerRequestRepository.findById(id)
                .orElseThrow(() -> new NotFoundRuntimeException(id, "CustomerRequest can't be found!"));
    }

    @Override
    public CustomerRequest createCustomerRequest(CustomerRequest customerRequest) {
        return customerRequestRepository.save(customerRequest);
    }

    @Override
    public CustomerRequest save(CustomerRequest customerRequest) {
        return customerRequestRepository.save(customerRequest);
    }

    @Override
    public List<CustomerRequest> findByCustomerName(String customerName, Sort sort) {
        return customerRequestRepository.findCustomerRequestsBy(customerName, sort);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CustomerRequest> search(Timestamp searchDate, String customerName, String rqName, String rqStatus, PageRequest pageRequest) {
        return customerRequestRepository.findBySearchParams(searchDate, customerName, rqName, rqStatus, pageRequest);
    }

}
