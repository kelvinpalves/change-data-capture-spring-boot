package dev.kelvin.cdc.adapters.in.web;

import dev.kelvin.cdc.adapters.in.web.dto.*;
import dev.kelvin.cdc.domain.model.Order;
import dev.kelvin.cdc.domain.ports.inbound.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final CreateOrderUseCase createOrderUseCase;
    private final UpdateOrderUseCase updateOrderUseCase;
    private final DeleteOrderUseCase deleteOrderUseCase;
    private final GetOrderQuery getOrderQuery;
    private final ListOrdersQuery listOrdersQuery;

    public OrderController(CreateOrderUseCase createOrderUseCase,
                           UpdateOrderUseCase updateOrderUseCase,
                           DeleteOrderUseCase deleteOrderUseCase,
                           GetOrderQuery getOrderQuery,
                           ListOrdersQuery listOrdersQuery) {
        this.createOrderUseCase = createOrderUseCase;
        this.updateOrderUseCase = updateOrderUseCase;
        this.deleteOrderUseCase = deleteOrderUseCase;
        this.getOrderQuery = getOrderQuery;
        this.listOrdersQuery = listOrdersQuery;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> create(@RequestBody CreateOrderRequest req) {
        var created = createOrderUseCase.create(new Order(null, req.customerName(), req.status(), req.totalCents(), null, null));
        return ResponseEntity.ok(toResponse(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> update(@PathVariable Long id, @RequestBody UpdateOrderRequest req) {
        var updated = updateOrderUseCase.update(id, new Order(id, req.customerName(), req.status(), req.totalCents(), null, null));
        return ResponseEntity.ok(toResponse(updated));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> get(@PathVariable Long id) {
        var o = getOrderQuery.get(id);
        return o == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(toResponse(o));
    }

    @GetMapping
    public Iterable<OrderResponse> list() { return listOrdersQuery.list().stream().map(this::toResponse).toList(); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { deleteOrderUseCase.delete(id); }

    private OrderResponse toResponse(Order o) { return new OrderResponse(o.id(), o.customerName(), o.status(), o.totalCents()); }
}
