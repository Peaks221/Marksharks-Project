package com.marksharks.assessment.controller;

import com.marksharks.assessment.entity.Supplier;
import com.marksharks.assessment.service.SupplierService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping("/query")
    @Operation(summary = "Search suppliers by location, nature of business, and manufacturing processes")
    @ApiResponse(responseCode = "200", description = "List of suppliers matching the criteria", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Supplier.class))
    })
    public ResponseEntity<Object> querySuppliers(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String natureOfBusiness,
            @RequestParam(required = false) String manufacturingProcesses,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        if (page < 0 || size <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid page number or page size. Page number must be >= 0 and page size must be > 0.");
        }


        if (location == null && natureOfBusiness == null && manufacturingProcesses == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("At least one search parameter must be provided.");
        }
        Page<Supplier> suppliers = supplierService.searchSuppliers(location, natureOfBusiness,
                manufacturingProcesses, page, size);
        return ResponseEntity.ok(suppliers);
    }
}

