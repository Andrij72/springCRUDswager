package com.andrkul.transformers;


import com.andrkul.dto.CustomerRequestDto;
import com.andrkul.model.CustomerRequest;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class CustomerRequestTransformer {


    public List<CustomerRequestDto> buildListCustomerRequestDto(List<CustomerRequest> entities) {
        return entities.stream()
                .map(this::buildCustomerRequestDto)
                .collect(toList());
    }


    public CustomerRequestDto buildCustomerRequestDto(CustomerRequest customerRequest) {
        return CustomerRequestDto.builder()
                 .customerid(customerRequest.getCustomerid())
                .registrationDate(customerRequest.getRegistrationDate())
                .customerName(customerRequest.getCustomerName())
                .requestName(customerRequest.getRequestName())
                .attachedDocuments(customerRequest.getAttachedDocuments())
                .status(customerRequest.getStatus())
                .build();
    }

    public CustomerRequest buildCustomerRequest(CustomerRequestDto customerRequestDto) {
        CustomerRequest entity = new CustomerRequest();
        entity.setCustomerid(customerRequestDto.getCustomerid());
        entity.setRegistrationDate(customerRequestDto.getRegistrationDate());
        entity.setCustomerName(customerRequestDto.getCustomerName());
        entity.setRequestName(customerRequestDto.getRequestName());
        entity.setAttachedDocuments(customerRequestDto.getAttachedDocuments());
        entity.setStatus(customerRequestDto.getStatus());
        return entity;
    }
}
