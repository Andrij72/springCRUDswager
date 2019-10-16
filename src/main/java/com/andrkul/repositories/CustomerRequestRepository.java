package com.andrkul.repositories;


import com.andrkul.model.CustomerRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface CustomerRequestRepository extends PagingAndSortingRepository<CustomerRequest, Long> {
    @Query("SELECT p FROM customer_request p WHERE p.customer_name = :customerName ORDER BY p.request_name  ASC")
    List<CustomerRequest> findCustomerRequestsBy(@Param("customerName") String customerName,
                                                 @Param("rqName") Sort rqName);

    @Query("SELECT t FROM customer_request t WHERE" +
            "(t.date_registration)  LIKE :searchDate  OR" +
            "(LOWER(t.customer_name) LIKE LOWER(CONCAT('%', :customerName, '%'))) OR" +
            "(LOWER(t.request_name ) LIKE LOWER(CONCAT('%', :rqName, '%'))) OR" +
            "(t.rq_status  LIKE  :rqStatus)")
    Page<CustomerRequest> findBySearchParams(
            @Param("searchDate") Timestamp searchDate,
            @Param("customerName") String customerName,
            @Param("rqName") String rqName,
            @Param("rqStatus") String rqStatus,

            Pageable pageRequest
    );
}
