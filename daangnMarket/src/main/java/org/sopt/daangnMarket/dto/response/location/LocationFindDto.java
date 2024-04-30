package org.sopt.daangnMarket.dto.response.location;

import org.sopt.daangnMarket.domain.Location;

public record LocationFindDto(

        String city,
        String district,
        String street
) {
    public static LocationFindDto of(Location location) {
        return new LocationFindDto(
                location.getCity(),
                location.getDistrict(),
                location.getStreet()
        );
    }
}
