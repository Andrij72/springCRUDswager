package com.andrkul.controllers;


import com.andrkul.dto.CustomerRequestDto;
import com.andrkul.model.CustomerRequest;
import com.andrkul.model.Status;
import com.andrkul.repositories.CustomerRequestRepository;
import com.andrkul.services.CustomerRequestService;
import com.andrkul.transformers.CustomerRequestTransformer;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;


@RestController
@RequestMapping("/api")
public class CustomerRequestController {
    final Logger logger = LoggerFactory.getLogger(CustomerRequestController.class);

    @Autowired
    private CustomerRequestTransformer customerRequestTransformer;

    @Autowired
    private CustomerRequestService customerRequestService;

    @ApiOperation(value = "View a list of available customer_requests", response = List.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })

    @GetMapping(value = "/department")
    public List<CustomerRequestDto> findAll() {
        return customerRequestTransformer.buildListCustomerRequestDto(customerRequestService.findAll());
    }

    @GetMapping("/department/find/{customerName}")
    public List<CustomerRequestDto> findByCustomerName(@PathVariable String customerName){
    return customerRequestTransformer.buildListCustomerRequestDto(customerRequestService.findByCustomerName(customerName,Sort.by("customerName")));
    }

    @ApiOperation(value = "View a list of available customer_requests( search and sort by parameters)", response = List.class)
    @GetMapping(path = "/search")
    public @ResponseBody
    Page<CustomerRequest> search(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "") Timestamp searchDate,
            @RequestParam(required = false, defaultValue = "") String customerName,
            @RequestParam(required = false, defaultValue = "") String rqName,
            @RequestParam(required = false, defaultValue = "") String rqStatus,
            @RequestParam(required = false, defaultValue = "id") String sortBy,
            @RequestParam(required = false, defaultValue = "ask") String order
    ) {
        Sort sort;
        if (order.equals("desc")) {
            sort = new Sort(Sort.Direction.DESC, sortBy);
        } else {
            sort = new Sort(Sort.Direction.ASC, sortBy);
        }

        Integer pageNumber = (page > 0) ? page - 1 : 0;
        PageRequest pageRequest = new PageRequest(pageNumber, 5, sort);
        return customerRequestService.search( searchDate, customerName, rqName, rqStatus, pageRequest);

    }
    @ApiOperation(value = "Add an customer_request")
    @PostMapping( value = "")
    public CustomerRequest create(
            @ApiParam(value = "Customer_request object store in database table", required = true)
            @Valid @RequestBody CustomerRequest customerRequest) {
        logger.info("Create customer_request:" + customerRequest);
        customerRequestService.createCustomerRequest(customerRequest);
        logger.info("Customer_request created successfully with info: " + customerRequest);

        return customerRequestService.createCustomerRequest(customerRequest);
    }

    @ApiOperation(value = "Changing status of the customer_request")
    @PostMapping(path = "/department/status/{id}")
    public CustomerRequest isCustomerRequest(@PathVariable Long id) {
        CustomerRequest requestById = customerRequestService.findById(id);
        requestById.setStatus(Status.TERMINATED);

        return customerRequestService.save(requestById);
    }

}
