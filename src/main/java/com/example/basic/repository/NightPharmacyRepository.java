package com.example.basic.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.basic.model.NightPharmacy;

@Repository
public interface NightPharmacyRepository
    extends JpaRepository<NightPharmacy, Integer> {

  List<NightPharmacy> 
    findByRoadAddressContainingOrJibunAddressContaining(
        String roadAddress, String jibunAddress, Pageable pageable);

  List<NightPharmacy> 
    findByRoadAddressContainingOrJibunAddressContaining(
        String roadAddress, String jibunAddress);
}
