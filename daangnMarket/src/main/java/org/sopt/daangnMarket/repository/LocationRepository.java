package org.sopt.daangnMarket.repository;

import org.sopt.daangnMarket.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {

    Optional<Location> findByStreet(String street);
}
