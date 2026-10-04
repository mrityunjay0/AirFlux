package com.airflux.pricingService.repository;

import com.airflux.pricingService.entity.Fare;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FareRepository extends JpaRepository<Fare, Long> {

}
