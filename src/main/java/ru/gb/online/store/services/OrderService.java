package ru.gb.online.store.services;

import ru.gb.online.store.entities.Order;
import ru.gb.online.store.entities.OrderItem;
import ru.gb.online.store.entities.ShoppingCart;
import ru.gb.online.store.entities.User;
import ru.gb.online.store.repositories.OrderItemRepository;
import ru.gb.online.store.repositories.OrderRepository;
import ru.gb.online.store.repositories.OrderStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    OrderStatusRepo orderStatusRepo;


    public Order save (ShoppingCart cart, HttpSession session) {

        User user = (User) session.getAttribute ("user");

        Order order = new Order ();
        order.setOrderItems (new ArrayList<OrderItem> ());
        order.setUser (user);
        order.setCreateAt (LocalDateTime.now ());
        order.setStatus (orderStatusRepo.findById (1L).get ());

        orderRepository.save (order);

        cart.getOrderItems ().forEach ((item) ->{
            item.setOrder (order);
            order.getOrderItems ().add (orderItemRepository.save (item));
        });
        
        cart.clear ();

        sendMsg(order);

        return order;
    }


    public void getHistory () {
    }


    private void sendMsg(Order order){
        String message = String.format("NEW ORDER User: %s Items: %s",
                order.getUser().getUserName(), order.getOrderItems());
        System.out.println (message);
    };
}
