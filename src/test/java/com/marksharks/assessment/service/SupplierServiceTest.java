package com.marksharks.assessment.service;

import com.marksharks.assessment.entity.Supplier;
import com.marksharks.assessment.repository.SupplierRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SupplierServiceTest {

    @Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    private SupplierService supplierService;



    @Test
    public void testSearchSuppliers_WithValidParams_ShouldReturnSuppliers() {

        Supplier supplier = new Supplier();
        supplier.setSupplierId(1L);
        supplier.setCompanyName("ABC Manufacturing");
        supplier.setWebsite("http://abc.com");
        supplier.setLocation("India");
        supplier.setNatureOfBusiness("small_scale");
        supplier.setManufacturingProcesses("3d_printing");

        Pageable pageable = PageRequest.of(0, 10);
        Page<Supplier> supplierPage = new PageImpl<>(Collections.singletonList(supplier), pageable, 1);

        when(supplierRepository.searchSuppliers("India", "small_scale", "3d_printing", pageable))
                .thenReturn(supplierPage);


        Page<Supplier> result = supplierService.searchSuppliers("India", "small_scale", "3d_printing", 0, 10);


        assertEquals(1, result.getTotalElements());
        assertEquals(supplier, result.getContent().get(0));
        verify(supplierRepository, times(1))
                .searchSuppliers("India", "small_scale", "3d_printing", pageable);
    }

    @Test
    public void testSearchSuppliers_WithNullParams_ShouldStillReturnResults() {

        Supplier supplier = new Supplier();
        supplier.setSupplierId(2L);
        supplier.setCompanyName("XYZ Manufacturing");
        supplier.setWebsite("http://xyz.com");
        supplier.setLocation("USA");
        supplier.setNatureOfBusiness("medium_scale");
        supplier.setManufacturingProcesses("moulding");

        Pageable pageable = PageRequest.of(0, 10);
        Page<Supplier> supplierPage = new PageImpl<>(Collections.singletonList(supplier), pageable, 1);

        when(supplierRepository.searchSuppliers(null, null, null, pageable))
                .thenReturn(supplierPage);


        Page<Supplier> result = supplierService.searchSuppliers(null, null, null, 0, 10);


        assertEquals(1, result.getTotalElements());
        assertEquals(supplier, result.getContent().get(0));
        verify(supplierRepository, times(1))
                .searchSuppliers(null, null, null, pageable);
    }
}

