package dev.kelvin.cdc.adapters.out.persistence;

import dev.kelvin.cdc.domain.model.Order;
import dev.kelvin.cdc.domain.ports.outbound.OrderRepositoryPort;

import java.util.List;
import java.util.Optional;

public class OrderPersistenceAdapter implements OrderRepositoryPort {
    private final SpringDataOrderJpaRepository jpa;

    public OrderPersistenceAdapter(SpringDataOrderJpaRepository jpa) { this.jpa = jpa; }

    @Override public Order save(Order order) {
        var entity = toEntity(order);
        var saved = jpa.save(entity);
        return toDomain(saved);
    }

    @Override public Optional<Order> findById(Long id) {
        return jpa.findById(id).map(this::toDomain);
    }

    @Override public List<Order> findAll() {
        return jpa.findAll().stream().map(this::toDomain).toList();
    }

    @Override public void deleteById(Long id) { jpa.deleteById(id); }

    private OrderEntity toEntity(Order o) {
        var e = new OrderEntity();
        e.setId(o.id());
        e.setCustomerName(o.customerName());
        e.setStatus(o.status());
        e.setTotalCents(o.totalCents());
        e.setCreatedAt(o.createdAt());
        e.setUpdatedAt(o.updatedAt());
        return e;
    }
    private Order toDomain(OrderEntity e) {
        return new Order(e.getId(), e.getCustomerName(), e.getStatus(),
                e.getTotalCents(), e.getCreatedAt(), e.getUpdatedAt());
    }
}
