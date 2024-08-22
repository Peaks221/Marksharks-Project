package com.marksharks.assessment.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

import com.marksharks.assessment.entity.Supplier;
import com.marksharks.assessment.service.SupplierService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;

@WebMvcTest(SupplierController.class)
public class SupplierControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SupplierService supplierService;

    @Test
    public void testQuerySuppliers_WithValidParams_ShouldReturnSuppliers() throws Exception {
        Supplier supplier = new Supplier();
        supplier.setSupplierId(1L);
        supplier.setCompanyName("ABC Manufacturing");
        supplier.setWebsite("http://abc.com");
        supplier.setLocation("India");
        supplier.setNatureOfBusiness("small_scale");
        supplier.setManufacturingProcesses("3d_printing");

        Page<Supplier> supplierPage = new PageImpl<>(Collections.singletonList(supplier), PageRequest.of(0, 10), 1);

        // Mocking the service method
        when(supplierService.searchSuppliers("India", "small_scale", "3d_printing", 0, 10))
                .thenReturn(supplierPage);

        mockMvc.perform(get("/api/supplier/query")
                        .param("location", "India")
                        .param("natureOfBusiness", "small_scale")
                        .param("manufacturingProcesses", "3d_printing")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].supplierId", is(1)))
                .andExpect(jsonPath("$.content[0].companyName", is("ABC Manufacturing")))
                .andExpect(jsonPath("$.content[0].location", is("India")))
                .andExpect(jsonPath("$.content[0].natureOfBusiness", is("small_scale")))
                .andExpect(jsonPath("$.content[0].manufacturingProcesses", is("3d_printing")));
    }

    @Test
    public void testQuerySuppliers_NoResults_ShouldReturnEmptyList() throws Exception {
        Page<Supplier> emptyPage = new PageImpl<>(Collections.emptyList(), PageRequest.of(0, 10), 0);

        // Mocking the service method
        when(supplierService.searchSuppliers("India", "large_scale", "casting", 0, 10))
                .thenReturn(emptyPage);

        mockMvc.perform(get("/api/supplier/query")
                        .param("location", "India")
                        .param("natureOfBusiness", "large_scale")
                        .param("manufacturingProcesses", "casting")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(0)));
    }

    @Test
    public void testQuerySuppliers_MissingParams_ShouldReturnBadRequest() throws Exception {
        mockMvc.perform(get("/api/supplier/query"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testQuerySuppliers_InvalidPageParam_ShouldReturnBadRequest() throws Exception {
        mockMvc.perform(get("/api/supplier/query")
                        .param("page", "-1")
                        .param("size", "10"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testQuerySuppliers_InvalidSizeParam_ShouldReturnBadRequest() throws Exception {
        mockMvc.perform(get("/api/supplier/query")
                        .param("page", "0")
                        .param("size", "0"))
                .andExpect(status().isBadRequest());
    }
}

