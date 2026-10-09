package com.airflux.seatService.mapper;

import com.airflux.payload.request.CabinClassRequest;
import com.airflux.payload.response.CabinClassResponse;
import com.airflux.seatService.entity.CabinClass;
import com.airflux.seatService.entity.SeatMap;

public class CabinClassMapper {

    public static CabinClass toEntity(CabinClassRequest request) {

        if (request == null) return null;

        return CabinClass.builder()
                .type(request.getType())
                .code(request.getCode())
                .description(request.getDescription())
                .aircraftId(request.getAircraftId())
                .displayOrder(request.getDisplayOrder() != null
                        ? request.getDisplayOrder()
                        : 0)
                .isActive(request.getIsActive() != null
                        ? request.getIsActive()
                        : true)
                .isBookable(request.getIsBookable() != null
                        ? request.getIsBookable()
                        : true)
                .typicalSeatPitch(request.getTypicalSeatPitch())
                .typicalSeatWidth(request.getTypicalSeatWidth())
                .seatType(request.getSeatType())
                .build();
    }


    // Entity -> Response
    public static CabinClassResponse toResponse(CabinClass entity, SeatMap seatMap) {

        if (entity == null) return null;

        return CabinClassResponse.builder()
                .id(entity.getId())
                .name(entity.getType() != null
                        ? entity.getType().name()
                        : null)
                .code(entity.getCode())
                .description(entity.getDescription())
                .aircraftId(entity.getAircraftId())
                .seatMapResponse(seatMap != null ? SeatMapMapper.toResponse(seatMap) : null)
                .displayOrder(entity.getDisplayOrder())
                .isActive(entity.getIsActive())
                .isBookable(entity.getIsBookable())
                .typicalSeatPitch(entity.getTypicalSeatPitch())
                .typicalSeatWidth(entity.getTypicalSeatWidth())
                .seatType(entity.getSeatType())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }


    // Update existing Entity from Request
    public static void updateEntity(CabinClass entity, CabinClassRequest request) {

        if (entity == null || request == null) return;

        if (request.getType() != null) {
            entity.setType(request.getType());
        }

        if (request.getCode() != null) {
            entity.setCode(request.getCode());
        }

        if (request.getDescription() != null) {
            entity.setDescription(request.getDescription());
        }

        if (request.getAircraftId() != null) {
            entity.setAircraftId(request.getAircraftId());
        }

        if (request.getDisplayOrder() != null) {
            entity.setDisplayOrder(request.getDisplayOrder());
        }

        if (request.getIsActive() != null) {
            entity.setIsActive(request.getIsActive());
        }

        if (request.getIsBookable() != null) {
            entity.setIsBookable(request.getIsBookable());
        }

        if (request.getTypicalSeatPitch() != null) {
            entity.setTypicalSeatPitch(request.getTypicalSeatPitch());
        }

        if (request.getTypicalSeatWidth() != null) {
            entity.setTypicalSeatWidth(request.getTypicalSeatWidth());
        }

        if (request.getSeatType() != null) {
            entity.setSeatType(request.getSeatType());
        }
    }

}