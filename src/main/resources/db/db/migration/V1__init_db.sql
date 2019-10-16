create table customer_request
(
    customerid        bigserial not null
        constraint customer_request_pkey
            primary key,
    customer_name      varchar(255),
    request_name       varchar(255),
    attached_documents varchar(255),
    date_registration  timestamp,
    rq_status          varchar(255)
);

alter table customer_request
    owner to postgres;