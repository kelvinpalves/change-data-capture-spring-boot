package dev.kelvin.cdc.domain.ports.inbound;

import dev.kelvin.cdc.domain.model.Order;

import java.util.List;

public interface ListOrdersQuery {
    List<Order> list();
}
