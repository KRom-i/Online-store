package ru.gb.online.store.repositories;

import ru.gb.online.store.entities.OrderStatus;
import org.springframework.data.repository.CrudRepository;

public interface OrderStatusRepo extends CrudRepository<OrderStatus, Long> {

}
