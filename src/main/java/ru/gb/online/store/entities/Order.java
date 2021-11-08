package ru.gb.online.store.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@ToString
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order")
    @ToString.Exclude
    private List<OrderItem> orderItems;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private OrderStatus status;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    public Long quantity() {
        return orderItems.stream().mapToLong (ru.gb.online.store.entities.OrderItem::getQuantity).sum ();
    }

    public Double totalPrice () {
        return orderItems.stream().mapToDouble (ru.gb.online.store.entities.OrderItem::getTotalPrice).sum ();
    }
}
