package dev.kelvin.cdc.adapters.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataOrderJpaRepository extends JpaRepository<OrderEntity, Long> { }
