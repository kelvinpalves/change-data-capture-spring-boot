package dev.kelvin.cdc.domain.model;

import java.time.OffsetDateTime;

public record Order(
        Long id,
        String customerName,
        String status,
        long totalCents,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {}
