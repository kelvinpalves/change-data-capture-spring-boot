package dev.kelvin.cdc.domain.ports.inbound;

import dev.kelvin.cdc.domain.model.Order;

public interface UpdateOrderUseCase {
    Order update(Long id, Order order);
}
