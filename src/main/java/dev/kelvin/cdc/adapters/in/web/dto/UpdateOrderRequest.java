package dev.kelvin.cdc.adapters.in.web.dto;

public record UpdateOrderRequest(String customerName, String status, long totalCents) {}
