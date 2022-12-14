package dev.t1dmlgus.catalogservice.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductToken(String productToken);
}
