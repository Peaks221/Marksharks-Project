package com.marksharks.assessment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long supplierId;
    public String companyName;
    public String website;
    public String natureOfBusiness;
    public String manufacturingProcesses;
    public String location;

}
