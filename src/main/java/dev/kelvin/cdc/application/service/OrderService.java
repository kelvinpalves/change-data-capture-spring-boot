package dev.kelvin.cdc.application.service;

import dev.kelvin.cdc.domain.model.Order;
import dev.kelvin.cdc.domain.ports.inbound.*;
import dev.kelvin.cdc.domain.ports.outbound.OrderRepositoryPort;

import java.time.OffsetDateTime;
import java.util.List;

public class OrderService implements
        CreateOrderUseCase,
        UpdateOrderUseCase,
        DeleteOrderUseCase,
        GetOrderQuery,
        ListOrdersQuery {

    private final OrderRepositoryPort repo;

    public OrderService(OrderRepositoryPort repo) { this.repo = repo; }

    @Override public Order create(Order order) {
        var now = OffsetDateTime.now();
        var toSave = new Order(null, order.customerName(), order.status(),
                order.totalCents(), now, now);
        return repo.save(toSave);
    }

    @Override public Order update(Long id, Order order) {
        var existing = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found " + id));
        var updated = new Order(
                existing.id(),
                order.customerName(),
                order.status(),
                order.totalCents(),
                existing.createdAt(),
                OffsetDateTime.now()
        );
        return repo.save(updated);
    }

    @Override public void delete(Long id) { repo.deleteById(id); }

    @Override public Order get(Long id) { return repo.findById(id).orElse(null); }

    @Override public List<Order> list() { return repo.findAll(); }
}
