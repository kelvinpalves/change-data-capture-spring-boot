package dev.kelvin.cdc.adapters.in.web.dto;

public record CreateOrderRequest(String customerName, String status, long totalCents) {}
