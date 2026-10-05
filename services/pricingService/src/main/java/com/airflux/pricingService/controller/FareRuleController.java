package com.airflux.pricingService.controller;

import com.airflux.payload.request.FareRulesRequest;
import com.airflux.payload.response.ApiResponse;
import com.airflux.payload.response.FareRulesResponse;
import com.airflux.pricingService.service.FareRulesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vi/fare-rules")
public class FareRuleController {

    private final FareRulesService fareRulesService;

    public FareRuleController(FareRulesService fareRulesService) {
        this.fareRulesService = fareRulesService;
    }


    @PostMapping
    public ResponseEntity<FareRulesResponse> createFareRules(@Valid @RequestBody
                                                             FareRulesRequest fareRulesRequest) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(fareRulesService.createFareRules(fareRulesRequest));
    }

    @GetMapping("/{fareRulesId}")
    public ResponseEntity<FareRulesResponse> getFareRules(@PathVariable Long fareRulesId) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(fareRulesService.getFareRulesById(fareRulesId));
    }

    @GetMapping("/{fareId}")
    public ResponseEntity<FareRulesResponse> getFareByFareId(@PathVariable Long fareId) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(fareRulesService.getFareRulesByFareId(fareId));
    }

    @GetMapping("/{airlineId}")
    public ResponseEntity<List<FareRulesResponse>> getFareByAirlineId(@PathVariable Long airlineId) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(fareRulesService.getFareRulesByAirlineId(airlineId));
    }

    @PutMapping("/{fareRulesId}")
    public ResponseEntity<FareRulesResponse> updateFareRules(@PathVariable Long fareRulesId,
                                                             @Valid @RequestBody FareRulesRequest fareRulesRequest) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(fareRulesService.updateFareRules(fareRulesId, fareRulesRequest));
    }

    @DeleteMapping("/{fareRuleId}")
    public ResponseEntity<ApiResponse> deleteFareRules(@PathVariable Long fareRuleId) {

        fareRulesService.deleteFareRules(fareRuleId);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Fare Rule deleted successfully.");

        return ResponseEntity.status(HttpStatus.OK)
                .body(apiResponse);
    }
}
