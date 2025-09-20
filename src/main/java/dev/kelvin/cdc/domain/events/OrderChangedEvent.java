package dev.kelvin.cdc.domain.events;

public record OrderChangedEvent(String op, OrderPayload before, OrderPayload after) {
    public record OrderPayload(Long id, String customerName, String status, long totalCents,
                               String createdAt, String updatedAt) {}
}
