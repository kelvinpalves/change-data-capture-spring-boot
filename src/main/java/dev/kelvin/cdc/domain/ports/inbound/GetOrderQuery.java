package dev.kelvin.cdc.domain.ports.inbound;

import dev.kelvin.cdc.domain.model.Order;

public interface GetOrderQuery {
    Order get(Long id);
}
