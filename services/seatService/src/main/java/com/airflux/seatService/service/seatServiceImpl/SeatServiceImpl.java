package com.airflux.seatService.service.seatServiceImpl;

import com.airflux.payload.enums.SeatType;
import com.airflux.payload.exception.DuplicateResourceException;
import com.airflux.payload.exception.ResourceNotFoundException;
import com.airflux.payload.request.SeatRequest;
import com.airflux.payload.response.SeatResponse;
import com.airflux.seatService.entity.CabinClass;
import com.airflux.seatService.entity.Seat;
import com.airflux.seatService.entity.SeatMap;
import com.airflux.seatService.mapper.SeatMapper;
import com.airflux.seatService.repository.CabinClassRepository;
import com.airflux.seatService.repository.SeatMapRepository;
import com.airflux.seatService.repository.SeatRepository;
import com.airflux.seatService.service.SeatService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;
    private final SeatMapRepository seatMapRepository;
    private final CabinClassRepository cabinClassRepository;

    public SeatServiceImpl(SeatRepository seatRepository, SeatMapRepository seatMapRepository, CabinClassRepository cabinClassRepository) {
        this.seatRepository = seatRepository;
        this.seatMapRepository = seatMapRepository;
        this.cabinClassRepository = cabinClassRepository;
    }


    @Override
    public void generateSeats(Long seatMapId) {

        boolean exists = seatRepository.existsBySeatMapId(seatMapId);

        if (exists) {
            throw new DuplicateResourceException("Seat already exists for seat map.");
        }

        SeatMap seatMap = seatMapRepository.findById(seatMapId).orElseThrow(
                () -> new ResourceNotFoundException("No seat map found for given id")
        );

        int leftSeatsPerRow = seatMap.getLeftSeatsPerRow();
        int rightSeatsPerRow = seatMap.getRightSeatsPerRow();
        int totalRow = seatMap.getTotalRows();
        int totalSeatsPerRow = leftSeatsPerRow + rightSeatsPerRow;

        List<Seat> seats = new ArrayList<>();

        for(int row = 1; row <= totalRow; row++) {
            for(int col = 0; col <= totalSeatsPerRow; col++) {

                String seatNumber = row + getSeatLetter(col);
                SeatType seatType = getSeatType(col, leftSeatsPerRow, rightSeatsPerRow);

                Seat seat = Seat.builder()
                        .seatNumber(seatNumber)
                        .seatRow(row)
                        .columnLetter(getSeatLetter(col).charAt(0))
                        .seatType(seatType)
                        .seatMap(seatMap)
                        .build();

                seats.add(seat);
            }
        }

        seatRepository.saveAll(seats);

    }

    private SeatType getSeatType(int col, int leftSeatsPerRow, int rightSeatsPerRow) {

        int totalSeats = leftSeatsPerRow + rightSeatsPerRow;

        if(col == 0 || col == totalSeats-1) {
            return SeatType.WINDOW;
        }
        else if(col == leftSeatsPerRow-1) {
            return SeatType.AISLE;
        }
        else if(col == leftSeatsPerRow) {
            return SeatType.AISLE;
        }
        else {
            return SeatType.MIDDLE;
        }
    }

    private String getSeatLetter(int col) {

        StringBuilder sb = new StringBuilder();

        while(col >= 0) {
            sb.insert(0, (char) ('A' + col % 26));
            col = col / 26-1;
        }

        return sb.toString();
    }


    @Override
    public List<SeatResponse> getAllSeats() {

        return seatRepository.findAll().stream()
                .map(SeatMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public SeatResponse getSeatById(Long seatId) {

        Seat seat = seatRepository.findById(seatId).orElseThrow(
                () -> new ResourceNotFoundException("No seat found for given id")
        );

        return SeatMapper.toResponse(seat);
    }

    @Override
    public SeatResponse updateSeat(Long id, SeatRequest seatRequest) {

        Seat seat = seatRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No seat found for given id")
        );

        SeatMap seatMap = seatMapRepository.findById(seatRequest.getSeatMapId()).orElseThrow(
                () -> new ResourceNotFoundException("No seat map found for given id")
        );

        CabinClass cabinClass = null;

        if(seatRequest.getCabinClassId() != null) {
            cabinClass = cabinClassRepository.findById(seatRequest.getCabinClassId()).orElseThrow(
                    () -> new ResourceNotFoundException("No cabin class found for given id")
            );
        }

        SeatMapper.updateEntity(seatRequest, seat, seatMap, cabinClass);
        Seat savedSeat = seatRepository.save(seat);

        return SeatMapper.toResponse(savedSeat);
    }
}
