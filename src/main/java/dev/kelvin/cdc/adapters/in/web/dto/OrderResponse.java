package dev.kelvin.cdc.adapters.in.web.dto;

public record OrderResponse(Long id, String customerName, String status, long totalCents) {}
