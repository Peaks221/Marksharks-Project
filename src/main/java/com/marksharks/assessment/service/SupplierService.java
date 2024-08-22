package com.marksharks.assessment.service;

import com.marksharks.assessment.entity.Supplier;
import com.marksharks.assessment.exception.SupplierNotFoundException;
import com.marksharks.assessment.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public Page<Supplier> searchSuppliers(String location, String natureOfBusiness,
                                          String manufacturingProcesses, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Supplier> suppliers = supplierRepository.searchSuppliers(
                location, natureOfBusiness, manufacturingProcesses, pageable);
        if (suppliers.isEmpty()) {
            throw new SupplierNotFoundException("No suppliers found matching the criteria");
        }
        return suppliers;
    }
}

