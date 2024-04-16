package org.sopt.daangnMarket.repository;

import org.sopt.daangnMarket.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String category);
}
