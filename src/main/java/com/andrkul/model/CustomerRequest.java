package com.andrkul.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer_request")
public class CustomerRequest{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerid;

    @Column(name = "date_registration")
    private Timestamp registrationDate;

    @Column(name = "request_name")
    private String requestName;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "attached_documents")
    private String attachedDocuments;

    @Column(name = "rq_status")
    @Enumerated(EnumType.STRING)
    private Status status;

   @Override
    public String toString() {
        return "CustomerRequest[" + "customerid=" + customerid +
                ", registrationDate=" + this.registrationDate +
                ", RequestName='" + requestName + '\'' +
                ", CustomerName='" + customerName + '\'' +
                ", attachedDocuments=" + attachedDocuments +
                ", status=" + status +
                ']';
    }
}
