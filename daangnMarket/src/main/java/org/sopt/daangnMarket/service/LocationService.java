package org.sopt.daangnMarket.service;

import lombok.RequiredArgsConstructor;
import org.sopt.daangnMarket.domain.Location;
import org.sopt.daangnMarket.exception.NotFoundException;
import org.sopt.daangnMarket.repository.LocationRepository;
import org.sopt.daangnMarket.util.dto.ErrorMessage;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    public Location getLocationById(Long locationId) {
        return locationRepository.findById(locationId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.LOCATION_NOT_FOUND));
    }

    public Location getLocationByStreet(String location) {
        return locationRepository.findByStreet(location)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.LOCATION_NOT_FOUND));
    }
}
