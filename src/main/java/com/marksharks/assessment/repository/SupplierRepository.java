package com.marksharks.assessment.repository;

import com.marksharks.assessment.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    @Query("SELECT s FROM Supplier s WHERE " +
            "(:location IS NULL OR s.location = :location) AND " +
            "(:natureOfBusiness IS NULL OR s.natureOfBusiness = :natureOfBusiness) AND " +
            "(:manufacturingProcesses IS NULL OR s.manufacturingProcesses LIKE %:manufacturingProcesses%)")
    Page<Supplier> searchSuppliers(@Param("location") String location,
                                   @Param("natureOfBusiness") String natureOfBusiness,
                                   @Param("manufacturingProcesses") String manufacturingProcesses,
                                   Pageable pageable);
}
