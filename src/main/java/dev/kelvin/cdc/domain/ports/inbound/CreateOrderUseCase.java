package dev.kelvin.cdc.domain.ports.inbound;

import dev.kelvin.cdc.domain.model.Order;

public interface CreateOrderUseCase {
    Order create(Order order);
}
