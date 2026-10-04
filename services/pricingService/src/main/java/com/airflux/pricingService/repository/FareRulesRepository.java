package com.airflux.pricingService.repository;

import com.airflux.pricingService.entity.FareRules;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FareRulesRepository extends JpaRepository<FareRules, Long> {

    FareRules getByFareId(Long fareId);
    List<FareRules> getFareRulesByAirlineId(Long airlineId);
}